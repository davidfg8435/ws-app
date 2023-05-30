package es.udc.ws.app.client.service;

import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import es.udc.ws.app.client.service.soap.wsdl.InputValidationException_Exception;
import es.udc.ws.app.client.service.soap.wsdl.InstanceNotFoundException_Exception;
import es.udc.ws.app.dto.EventoDto;
import es.udc.ws.app.dto.RespuestaDto;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientEventoService {

	public Long addEvento(EventoDto evento)
			throws InputValidationException;
	
	public EventoDto findEvento (Long idEvento)
			throws InputValidationException, InstanceNotFoundException, DatatypeConfigurationException;
	
    public void removeEvento(Long idEvento) 
    		throws InstanceNotFoundException;

    public void updateEvento(EventoDto eventoDto) 
    		throws InputValidationException, InstanceNotFoundException;
    
    public List<EventoDto> findEventos(String keywords, Calendar fechaInicio, Calendar fechaFin)
    		throws DatatypeConfigurationException;
    
    public Long responder(RespuestaDto respuestaDto) 
            throws InputValidationException, InstanceNotFoundException;
    
	public List<RespuestaDto> findResponses(Long idUsuario, Long idEvento, Boolean respuesta)
	throws InputValidationException_Exception, InstanceNotFoundException_Exception;
}
