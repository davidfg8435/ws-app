package es.udc.ws.app.model.respuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import es.udc.ws.util.exceptions.InstanceNotFoundException;

public abstract class AbstractSqlRespuestaDao implements SqlRespuestaDao {
	
	protected AbstractSqlRespuestaDao() {
    }
	
	public Respuesta find(Connection connection, Long idRespuesta)
        	throws InstanceNotFoundException {

            /* Create "queryString". */
            String queryString = "SELECT idEvento, respuesta, descripcion, "
                    + " fechaRespuesta, idUsuario FROM Respuesta WHERE idRespuesta = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

                /* Fill "preparedStatement". */
                int i = 1;
                preparedStatement.setLong(i++, idRespuesta.longValue());

                /* Execute query. */
                ResultSet resultSet = preparedStatement.executeQuery();

                if (!resultSet.next()) {
                    throw new InstanceNotFoundException(idRespuesta,
                            Respuesta.class.getName());
                }

                /* Get results. */
                i = 1;
                Long idEvento = resultSet.getLong(i++);
                Boolean respuesta = resultSet.getBoolean(i++);
                String descripcion = resultSet.getString(i++);
                Calendar fechaRespuesta = Calendar.getInstance();
                fechaRespuesta.setTime(resultSet.getTimestamp(i++));
                Long idUsuario = resultSet.getLong(i++);

                /* Return respuesta. */
                return new Respuesta(idEvento, respuesta, descripcion, fechaRespuesta, idUsuario);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
	
	/*Busqueda segun idUsuario y segun idEvento*/
	public List<Respuesta> findByKeys(Connection connection, Long idUsuario, Long idEvento, Boolean respuesta) {
			
        String queryString = "SELECT idRespuesta, idEvento, respuesta, descripcion, "
                + " fechaRespuesta, idUsuario FROM Respuesta";
        
        if ((idUsuario != null) || (idEvento != null) || (respuesta != null)){
        	queryString += " WHERE";
        }    
        if (idUsuario != null){
        	queryString += " idUsuario = ?";
        }  
        if ((idUsuario != null) && (idEvento != null)){
        	queryString += " AND";
        }
        if (idEvento != null){
        	queryString += " idEvento = ?";
        }
        
        if (((idUsuario != null) && (respuesta != null)) || ((idEvento != null) && (respuesta != null))){
        	queryString += " AND";
        }
        
        if (respuesta != null){
        	queryString += " respuesta = ?";
        }
   
        queryString += " ORDER BY idEvento";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	    	/* Fill "preparedStatement". */
	    	int i = 1;
	    	if (idUsuario != null){
	    		preparedStatement.setLong(i++, idUsuario);
	    	}
	    	if (idEvento != null){
	    		preparedStatement.setLong(i++, idEvento);
	    	}
	        if (respuesta != null){
	        	preparedStatement.setBoolean(i++, respuesta);
	        }
	        System.out.println(preparedStatement);
	        /* Execute query. */
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        /* Read respuestas. */
	        List<Respuesta> respuestas = new ArrayList<Respuesta>();
	        System.out.println("antes bucle");
	        while (resultSet.next()){
	        	i = 1;
	        	Long idRespuesta = resultSet.getLong(i++);
                Long idEventoaux = resultSet.getLong(i++);
                Boolean respuesta2 = resultSet.getBoolean(i++);
                String descripcion = resultSet.getString(i++);
                Calendar fechaRespuesta = Calendar.getInstance();
                fechaRespuesta.setTime(resultSet.getTimestamp(i++));
                Long idUsuarioaux = resultSet.getLong(i++);
                
                respuestas.add(new Respuesta(idRespuesta,idEventoaux,respuesta2,descripcion,fechaRespuesta,idUsuarioaux));
	        }
	        System.out.println("despues bucle");
	        return respuestas;
	    }catch (SQLException e) {
        throw new RuntimeException(e);
	    }
	}

	public void update(Connection connection, Respuesta respuesta)
	        throws InstanceNotFoundException {
	
	    /* Create "queryString". */
	    String queryString = "UPDATE Respuesta"
	            + " SET idEvento = ?, respuesta = ?, descripcion = ?, "
	            + "fechaRespuesta = ?, idUsuario = ? WHERE idRespuesta = ?";
	
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	
	        /* Fill "preparedStatement". */
	        int i = 1;

	        
	        preparedStatement.setLong(i++, respuesta.getIdEvento());
	        preparedStatement.setBoolean(i++, respuesta.getRespuesta());
            preparedStatement.setString(i++, respuesta.getDescripcion());
            Timestamp x = respuesta.getFechaRespuesta() != null ? new Timestamp(
                    respuesta.getFechaRespuesta().getTime().getTime()) : null;;
            preparedStatement.setTimestamp(i++, x);
            preparedStatement.setLong(i++, respuesta.getIdUsuario());
           
            
	        /* Execute query. */
	        int updatedRows = preparedStatement.executeUpdate();
	
	        if (updatedRows == 0) {
	            throw new InstanceNotFoundException(respuesta.getIdRespuesta(),
	                    Respuesta.class.getName());
	        }
	
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

    }

	/*No cambia nada en la tabla evento*/
	public void remove(Connection connection, Long idRespuesta)
	        throws InstanceNotFoundException {
	
	    /* Create "queryString". */
	    String queryString = "DELETE FROM Respuesta WHERE" + " idRespuesta = ?";
	
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	
	        /* Fill "preparedStatement". */
	        int i = 1;
	        preparedStatement.setLong(i++, idRespuesta);
	
	        
	        /* Execute query. */
	        int removedRows = preparedStatement.executeUpdate();
	
	        if (removedRows == 0) {
	            throw new InstanceNotFoundException(idRespuesta,
	                    Respuesta.class.getName());
	        }
	
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

    }   

}
