package es.udc.ws.app.model.evento;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface SqlEventoDao {
	
	public Evento create(Connection connection, Evento event);

    public Evento find(Connection connection, Long idEvento)
            throws InstanceNotFoundException;

    public List<Evento> findByKeywords(Connection connection,
            String keywords, Calendar fecha1, Calendar fecha2);
    
    /*public List<Evento> findByDate(Connection connection,
            Calendar fecha1, Calendar fecha2);*/

    public void update(Connection connection, Evento event)
            throws InstanceNotFoundException;

    public void remove(Connection connection, Long idEvento)
            throws InstanceNotFoundException;

}
