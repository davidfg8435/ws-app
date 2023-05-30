package es.udc.ws.app.model.eventoservice;



import static es.udc.ws.app.model.util.ModelConstants.EVENTO_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import es.udc.ws.app.model.evento.Evento;
import es.udc.ws.app.model.evento.SqlEventoDao;
import es.udc.ws.app.model.evento.SqlEventoDaoFactory;
import es.udc.ws.app.model.respuesta.Respuesta;
import es.udc.ws.app.model.respuesta.SqlRespuestaDao;
import es.udc.ws.app.model.respuesta.SqlRespuestaDaoFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.validation.PropertyValidator;

import javax.sql.DataSource;

import es.udc.ws.util.sql.DataSourceLocator;



public class EventoServiceImpl implements EventoService{
	
    private DataSource dataSource;
    private SqlEventoDao eventoDao = null;
    private SqlRespuestaDao respuestaDao = null;

    public EventoServiceImpl() {
        dataSource = DataSourceLocator.getDataSource(EVENTO_DATA_SOURCE);
        eventoDao = SqlEventoDaoFactory.getDao();
        respuestaDao = SqlRespuestaDaoFactory.getDao();
    }
    //Para validar eventos
    public static void validateInteger(String propertyName,
            Integer value, int lowerValidLimit, int upperValidLimit)
            throws InputValidationException {
    	if (value == null){
    		 throw new InputValidationException("Invalid " + propertyName 
    				 + "can't be null");
    	}
        if ( (value < lowerValidLimit) || (value > upperValidLimit) ) {
            throw new InputValidationException("Invalid " + propertyName +
                    " value (it must be greater than " + lowerValidLimit +
                    " and lower than " + upperValidLimit + "): " + value);
        }

    }
	
    public static void validateDate(String propertyName,
            Calendar propertyValue) throws InputValidationException {

        // miramos que no esté en el pasado, le damos 10 segundos de ventaja
        Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -10); 
		if (propertyValue == null || propertyValue.compareTo(calendar) <= 0  ){
			throw new InputValidationException("Invalid " + propertyName +
                    " date value: " +
                    propertyValue);	
		}
	
    }
    
    /**
     * Va a validar además de la validación de fecha normal que la fecha de 
     * fin no sea igual o anterior a la fecha de inicio 
     * @param propertyName
     * @param beginDate
     * @param endDate
     * @throws InputValidationException
     */
    private static void validateEndDate(String propertyName,
    		Calendar beginDate, Calendar endDate) throws InputValidationException {
 
    	validateDate(propertyName, endDate);
    	if(beginDate != null){
	    	// si es fecha fin, comprobar que no sea igual o menor que la fecha de incio
			if (endDate.compareTo(beginDate) < 0){
				throw new InputValidationException(propertyName);		
			}
    	}
    	
    }
    //validar eventos
    private void validateEvento(Evento evento) throws InputValidationException {

        PropertyValidator.validateMandatoryString("nombre", evento.getNombre() );

        PropertyValidator.validateMandatoryString("descripcion",
                evento.getDescripcion());
        validateDate("fecha inicio",
                evento.getFechaInicio());   
        validateEndDate("fecha fin", 
        		evento.getFechaInicio(),
                evento.getFechaFin()
        );
        validateInteger("aforo", 
        		evento.getAforo(), 0, Integer.MAX_VALUE);
        validateInteger("aforo", 
        		evento.getNumeroRegistrados() , 0, evento.getAforo());
    }
    //Para validar respuestas  
    private void validateIdEvento(String propertyName, Long idEvento) 
    	throws InputValidationException {
    	 	try (Connection connection = dataSource.getConnection()) {
    	 		Evento aux = eventoDao.find(connection, idEvento);
            	if (aux == null){
            		throw new InputValidationException("El evento de id " + propertyName + " no existe.");
            	}
    	 	}
    	 	catch(InstanceNotFoundException e){
    	 		throw new InputValidationException(propertyName);
    	 	}
    	 	catch (SQLException e) {
    	 		throw new RuntimeException(e);
    	 	}
    }
    
    private void validaterespuesta(String propertyName, Boolean bool, Long idUsuario, Long idEvento)
    		throws InputValidationException  {
			if (bool == null){
				throw new InputValidationException(propertyName + " no puede ser nulo.");
			}
    		try (Connection connection = dataSource.getConnection()) {
				Evento aux = eventoDao.find(connection, idEvento);
				Respuesta ultimarespuesta = null;
	    		List<Respuesta> respuestas = respuestaDao.findByKeys(connection,idUsuario,idEvento,null);
	    		System.out.println("hata aqui bien");
	    		System.out.println("tira ou non tira");
	    		if (respuestas.size() != 0){
	    			ultimarespuesta = respuestas.get(0);
	    		}
	    		if (ultimarespuesta != null){
	    			for (Respuesta aux2 : respuestas){
	    				System.out.println("fechas " + aux2.getFechaRespuesta().compareTo(ultimarespuesta.getFechaRespuesta()));
	    				if (aux2.getFechaRespuesta().compareTo(ultimarespuesta.getFechaRespuesta()) > 0){
	    					ultimarespuesta = aux2;
	    				}
	    			}
		    		if(ultimarespuesta.getRespuesta()== false && bool==true){
		    			if (aux.getAforo() < (aux.getNumeroRegistrados()+1)){
		    				throw new InputValidationException(propertyName + " no hay aforo suficiente");
		    			}
		    		}
	    		}else{
		    		if(bool){
		    			System.out.println("entra aqui");
		    			if (aux.getAforo() < (aux.getNumeroRegistrados()+1)){
		    				throw new InputValidationException(propertyName + " no hay aforo suficiente");
		    			}
		    		}
	    		}
			} catch (InstanceNotFoundException e) {
				e.printStackTrace();
			}
    	 	catch (SQLException e) {
    	 		throw new RuntimeException(e);
    	 	}
    }
    
    private void validatefecha(String propertyName,Long idEvento, Calendar Date)
    		throws InputValidationException {
    	
    	validateDate(propertyName, Date);
    	try (Connection connection = dataSource.getConnection()) {
	 		Evento aux = eventoDao.find(connection, idEvento);
    		if (aux.getFechaFin().compareTo(Date) <= 0){
    			throw new InputValidationException("Evento caducado " + propertyName);
    		}
	 	}
	 	catch(InstanceNotFoundException e){
	 		throw new InputValidationException(propertyName);
	 	}
	 	catch (SQLException e) {
	 		throw new RuntimeException(e);
	 	}
    }
    
	//validar respuestas
    private void validateRespuesta (Respuesta respuesta) throws InputValidationException {
    	Long aux = respuesta.getIdUsuario();
    	int aux2 = aux.intValue();
        validateIdEvento("idEvento", respuesta.getIdEvento());
        validaterespuesta("Respuesta ", respuesta.getRespuesta(),respuesta.getIdUsuario(), respuesta.getIdEvento());
//        PropertyValidator.validateMandatoryString("descripcion",
//                respuesta.getDescripcion());
        validatefecha("FechaRespuesta ",respuesta.getIdEvento(), respuesta.getFechaRespuesta());
        validateInteger("idUsuario", aux2, 0, Integer.MAX_VALUE );
    }
    
    
    public Evento addEvento(Evento evento) throws InputValidationException{
        validateEvento(evento);

        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                Evento createdEvento = eventoDao.create(connection, evento);

                /* Commit. */
                connection.commit();

                return createdEvento;

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException | Error e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };

    public void updateEvento(Evento evento) throws InputValidationException,
            InstanceNotFoundException{
        validateEvento(evento);

        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                eventoDao.update(connection, evento);

                /* Commit. */
                connection.commit();

            } catch (InstanceNotFoundException e) {
                connection.commit();
                throw e;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException | Error e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    	
    }
   

    public void removeEvento(Long idEvento) throws InstanceNotFoundException{
    	
        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                eventoDao.remove(connection, idEvento);

                /* Commit. */
                connection.commit();

            } catch (InstanceNotFoundException e) {
                connection.commit();
                throw e;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException | Error e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Evento findEvento(Long idEvento) throws InstanceNotFoundException{
        try (Connection connection = dataSource.getConnection()) {
            return eventoDao.find(connection, idEvento);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Evento> findEventos(String keywords, Calendar fecha1, Calendar fecha2){
        try (Connection connection = dataSource.getConnection()) {
            return eventoDao.findByKeywords(connection, keywords, fecha1, fecha2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Servicios con Respuesta
    
    public Respuesta responder (Respuesta respuesta) throws InputValidationException,
    	InstanceNotFoundException{
    	validateRespuesta(respuesta);
        /*Comprobamos las respuestas que habia antes y cambiamos numero registrados si es necesario */
    	try (Connection connection = dataSource.getConnection()) {

    		Evento eventoaux = eventoDao.find(connection, respuesta.getIdEvento());
    		validateEvento(eventoaux);
    		eventoaux.setIdEvento(respuesta.getIdEvento());
    		List<Respuesta> respuestas = new ArrayList<Respuesta>();
    		respuestas = respuestaDao.findByKeys(connection, respuesta.getIdUsuario(), respuesta.getIdEvento(), null);
    		Respuesta ultimarespuesta = null;
    		if (respuestas.size() == 0){
				eventoaux.setNumeroRegistrados(eventoaux.getNumeroRegistrados()+1);
				eventoDao.update(connection, eventoaux);
    		}else ultimarespuesta = respuestas.get(0);
    		respuestaDao.create(connection, respuesta);
    		System.out.println("AQUI TAMBIEN");
    		for (Respuesta aux : respuestas){
    			System.out.println("comparacion fechas "+ aux.getFechaRespuesta().compareTo(respuesta.getFechaRespuesta()));
    			System.out.println("comparacion inversa fechas "+ respuesta.getFechaRespuesta().compareTo(aux.getFechaRespuesta()));
    			 if (aux.getFechaRespuesta().compareTo(ultimarespuesta.getFechaRespuesta()) > 0){
    				 ultimarespuesta = aux;
    			 }
    		}
    		System.out.println("AQUI TAMBIEN");
    		System.out.println("Ultima respuesta " + ultimarespuesta.getRespuesta());
    		System.out.println("respuesta " + respuesta.getRespuesta());
    		if (ultimarespuesta.getRespuesta() != respuesta.getRespuesta()){
    			if (ultimarespuesta.getRespuesta()){
    				eventoaux.setNumeroRegistrados(eventoaux.getNumeroRegistrados()-1);
    				eventoDao.update(connection, eventoaux);
    				System.out.println("Y AQUI, INCREIBLE");
    			}else{
    				eventoaux.setNumeroRegistrados(eventoaux.getNumeroRegistrados()+1);
    				eventoDao.update(connection, eventoaux);
    				System.out.println("Y AQUI, Suma");
    			}
    		}
    		return respuesta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Respuesta> findByKeysRespuesta(Long idUsuario, Long idEvento, Boolean respuesta)
    		throws InputValidationException, InstanceNotFoundException{
        try (Connection connection = dataSource.getConnection()) {
            return respuestaDao.findByKeys(connection, idUsuario, idEvento, respuesta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

}
