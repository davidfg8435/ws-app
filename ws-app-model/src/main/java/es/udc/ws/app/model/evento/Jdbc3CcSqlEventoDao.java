package es.udc.ws.app.model.evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Jdbc3CcSqlEventoDao extends AbstractSqlEventoDao {
	
	@Override
	public Evento create(Connection connection, Evento event){
		
		/* Create "queryString". */
        String queryString = "INSERT INTO Evento"
                + " (nombre, descripcion, fechaInicio, fechaFin, aforo, numeroRegistrados)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        queryString, Statement.RETURN_GENERATED_KEYS)) {

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

            /* Execute query. */
            preparedStatement.executeUpdate();

            /* Get generated identifier. */
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException(
                        "JDBC driver did not return generated key.");
            }
            Long idEvento = resultSet.getLong(1);

            /* Return event. */
            return new Evento(idEvento, event.getNombre(), event.getDescripcion(),
                    event.getFechaInicio(), event.getFechaFin(),
                    event.getAforo(), event.getNumeroRegistrados());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
