package es.udc.ws.app.model.respuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import es.udc.ws.app.model.evento.Evento;
import es.udc.ws.app.model.eventoservice.EventoService;
import es.udc.ws.app.model.eventoservice.EventoServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class Jdbc3CcSqlRespuestaDao extends AbstractSqlRespuestaDao {
	
	private EventoService eventoService = null;
	
	@Override
	public Respuesta create(Connection connection, Respuesta respuesta){
		
		eventoService = EventoServiceFactory.getService();
		/* Create "queryString". */
        String queryString = "INSERT INTO Respuesta"
                + " (idEvento, respuesta, descripcion, fechaRespuesta, idUsuario)"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        queryString, Statement.RETURN_GENERATED_KEYS)) {

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
            preparedStatement.executeUpdate();

            /* Get generated identifier. */
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException(
                        "JDBC driver did not return generated key.");
            }
            Long idRespuesta = resultSet.getLong(1);
            try {
				Evento evento = eventoService.findEvento(respuesta.getIdEvento());
				evento.setIdEvento(respuesta.getIdEvento());
			} catch (InstanceNotFoundException e) {
				e.printStackTrace();
				System.out.println("El evento no existe");
			}	
            
            /* Return respuesta. */
            return new Respuesta(idRespuesta, respuesta.getIdEvento(),
            		respuesta.getRespuesta(), respuesta.getDescripcion(),
                    respuesta.getFechaRespuesta(), respuesta.getIdUsuario());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
