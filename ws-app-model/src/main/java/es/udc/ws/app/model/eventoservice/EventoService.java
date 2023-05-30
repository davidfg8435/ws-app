package es.udc.ws.app.model.eventoservice;

import java.util.Calendar;
import java.util.List;





import es.udc.ws.app.model.evento.Evento;
import es.udc.ws.app.model.respuesta.Respuesta;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface EventoService {
	
    public Evento addEvento(Evento evento) throws InputValidationException;

    public void updateEvento(Evento evento) throws InputValidationException,
            InstanceNotFoundException;

    public void removeEvento(Long idEvento) throws InstanceNotFoundException;

    public Evento findEvento(Long IdEvento) throws InstanceNotFoundException;

    public List<Evento> findEventos(String keywords, Calendar fecha1, Calendar fecha2);
    
    public Respuesta responder (Respuesta respuesta) throws InputValidationException,
    InstanceNotFoundException;
    
    public List<Respuesta> findByKeysRespuesta(Long idUsuario, Long idEvento, Boolean respuesta) throws InputValidationException,
    InstanceNotFoundException;

}
