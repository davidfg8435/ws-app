package es.udc.ws.app.model.respuesta;

import java.sql.Connection;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface SqlRespuestaDao {
	
	public Respuesta create(Connection connection, Respuesta respuesta);

    public Respuesta find(Connection connection, Long idRespuesta)
            throws InstanceNotFoundException;

    public List<Respuesta> findByKeys(Connection connection,
            Long idUsuario, Long idEvento, Boolean respuesta);

    public void update(Connection connection, Respuesta respuesta)
            throws InstanceNotFoundException;

    public void remove(Connection connection, Long idRespuesta)
            throws InstanceNotFoundException;

}
