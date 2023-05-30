package es.udc.ws.app.model.evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.ws.app.model.evento.Evento;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public abstract class AbstractSqlEventoDao implements SqlEventoDao {

    protected AbstractSqlEventoDao() {
    }

    public Evento find(Connection connection, Long idEvento)
        	throws InstanceNotFoundException {

            /* Create "queryString". */
            String queryString = "SELECT nombre, descripcion, "
                    + " fechaInicio, fechaFin, aforo, numeroRegistrados FROM Evento WHERE idEvento = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
                /* Fill "preparedStatement". */
                int i = 1;
                preparedStatement.setLong(i++, idEvento.longValue());
                
                /* Execute query. */
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(idEvento.longValue());
                if (!resultSet.next()) {
                	System.out.println("e tronzou");
                    throw new InstanceNotFoundException(idEvento,
                            Evento.class.getName());
                }

                /* Get results. */
                i = 1;
                String nombre = resultSet.getString(i++);
                String descripcion = resultSet.getString(i++);
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(resultSet.getTimestamp(i++));
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(resultSet.getTimestamp(i++));
                Integer aforo = resultSet.getInt(i++);
                Integer numeroRegistrados = resultSet.getInt(i++);

                /* Return evento. */
                return new Evento(nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    public List<Evento> findByKeywords(Connection connection, String keywords, Calendar fecha1, Calendar fecha2){
	    /* Create "queryString". */
	    String[] words = keywords != null ? keywords.split(" ") : null;
	    String queryString = "SELECT idEvento, nombre, descripcion, "
	                    + " fechaInicio, fechaFin, aforo, numeroRegistrados FROM Evento";
	    
	    if ((words != null && words.length > 0) || (fecha1 != null && fecha2 != null && fecha2.after(fecha1))){
	        queryString += " WHERE";
	    }
	    if (words != null && words.length > 0) {
	        for (int i = 0; i < words.length; i++) {
	            if (i > 0) {
	                queryString += " AND";
	            }
	            queryString += " LOWER(nombre) LIKE LOWER(?)";
	        }
	    }
	    if ((words != null && words.length > 0) && (fecha1 != null && fecha2 != null && fecha2.after(fecha1))){
	    	queryString += " AND";
	    }
	    if (fecha1 != null && fecha2 != null && fecha2.after(fecha1)){
	    	Timestamp f1 = fecha1 != null ? new Timestamp(fecha1.getTime().getTime()) : null;;
	    	Timestamp f2 = fecha2 != null ? new Timestamp(fecha2.getTime().getTime()) : null;;
	    	queryString += " ((fechaInicio BETWEEN '" + f1 + "' AND '" + f2
	    	+ "') OR (fechaFin BETWEEN '" + f1 + "' AND '" + f2 + "'))";
	    }
	    queryString += " ORDER BY nombre";

	
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	
	        if (words != null) {
	            /* Fill "preparedStatement". */
	            for (int i = 0; i < words.length; i++) {
	                preparedStatement.setString(i + 1, "%" + words[i] + "%");
	            }
	        }
		    System.out.println(preparedStatement.toString());
	
	        /* Execute query. */
	        ResultSet resultSet = preparedStatement.executeQuery();
	
	        /* Read eventos. */
	        List<Evento> eventos = new ArrayList<Evento>();
	
	        while (resultSet.next()) {  	
	            int i = 1;
	            Long idEvento = new Long(resultSet.getLong(i++));
	            String nombre = resultSet.getString(i++);
	            String descripcion = resultSet.getString(i++);
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(resultSet.getTimestamp(i++));
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(resultSet.getTimestamp(i++));
	            Integer aforo = resultSet.getInt(i++);
	            Integer numeroRegistrados = resultSet.getInt(i++);
	        	
	
	            eventos.add(new Evento(idEvento, nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados));
	            
	
	        }
	
	        /* Return eventos. */
	        return eventos;
	
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

    }
    

    public void update(Connection connection, Evento event)
	        throws InstanceNotFoundException {
	
	    /* Create "queryString". */
	    String queryString = "UPDATE Evento"
	            + " SET nombre = ?, descripcion = ?, fechaInicio = ?, "
	            + "fechaFin = ?, aforo = ?, numeroRegistrados = ? WHERE idEvento = ?";
	
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	
	        /* Fill "preparedStatement". */
	        int i = 1;
	        
	       
	        preparedStatement.setString(i++, event.getNombre());
	        preparedStatement.setString(i++, event.getDescripcion());
	        Timestamp x = event.getFechaInicio() != null ? new Timestamp(
	                event.getFechaInicio().getTime().getTime()) : null;;
	        preparedStatement.setTimestamp(i++, x);
	        Timestamp y = event.getFechaFin() != null ? new Timestamp(
	                event.getFechaFin().getTime().getTime()) : null;;
	        preparedStatement.setTimestamp(i++, y);
	        preparedStatement.setInt(i++, event.getAforo());
	        preparedStatement.setInt(i++, event.getNumeroRegistrados());
	        preparedStatement.setLong(i++, event.getIdEvento());
	
	        /* Execute query. */
	        int updatedRows = preparedStatement.executeUpdate();
	
	        if (updatedRows == 0) {
	            throw new InstanceNotFoundException(event.getIdEvento(),
	                    Evento.class.getName());
	        }
	
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

    }

    public void remove(Connection connection, Long idEvento)
	        throws InstanceNotFoundException {
	
	    /* Create "queryString". */
	    String queryString = "DELETE FROM Evento WHERE" + " idEvento = ?";
	
	    try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
	
	        /* Fill "preparedStatement". */
	        int i = 1;
	        preparedStatement.setLong(i++, idEvento);
	
	        /* Execute query. */
	        int removedRows = preparedStatement.executeUpdate();
	
	        if (removedRows == 0) {
	            throw new InstanceNotFoundException(idEvento,
	                    Evento.class.getName());
	        }
	
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

    }    
    
}
