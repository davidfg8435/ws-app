package es.udc.ws.app.client.service.soap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.udc.ws.app.dto.EventoDto;

public class EventoDtoToSoapEventoDtoConversor {
	

    public static es.udc.ws.app.client.service.soap.wsdl.EventoDto toSoapEventoDto(EventoDto evento) 
    		throws DatatypeConfigurationException {
		es.udc.ws.app.client.service.soap.wsdl.EventoDto soapEventoDto = 
        new es.udc.ws.app.client.service.soap.wsdl.EventoDto();
    	soapEventoDto.setIdEvento(evento.getIdEvento());
    	soapEventoDto.setNombre(evento.getNombre());
    	soapEventoDto.setDescripcion(evento.getDescripcion());
    	soapEventoDto.setFechaInicio(
    	DatatypeFactory.newInstance().newXMLGregorianCalendar( (GregorianCalendar) evento.getFechaInicio()));
    	soapEventoDto.setDuracion(evento.getDuracion());
    	soapEventoDto.setAforo(evento.getAforo());

    	return soapEventoDto;
    }    

    public static EventoDto toEventoDto(
    		es.udc.ws.app.client.service.soap.wsdl.EventoDto evento) throws DatatypeConfigurationException {
    	return new EventoDto(evento.getIdEvento(), evento.getNombre(), evento.getDescripcion(), 
    			evento.getFechaInicio().toGregorianCalendar(),evento.getDuracion(),evento.getAforo());
    }     

	public static List<EventoDto> toEventoDtos(
		List<es.udc.ws.app.client.service.soap.wsdl.EventoDto> eventos) throws DatatypeConfigurationException {
			List<EventoDto> eventoDtos = new ArrayList<>(eventos.size());
			for (int i = 0; i < eventos.size(); i++) {
				es.udc.ws.app.client.service.soap.wsdl.EventoDto evento = eventos.get(i);
				eventoDtos.add(toEventoDto(evento));   
		}
		return eventoDtos;
	}
 
}
