package es.udc.ws.app.client.service.soap;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import es.udc.ws.app.dto.RespuestaDto;

public class RespuestaDtoToSoapRespuestaDtoConversor {
	
    public static es.udc.ws.app.client.service.soap.wsdl.RespuestaDto 
		   toSoapRespuestaDto(RespuestaDto respuestaDto) throws DatatypeConfigurationException {
		es.udc.ws.app.client.service.soap.wsdl.RespuestaDto soapRespuestaDto = 
		        new es.udc.ws.app.client.service.soap.wsdl.RespuestaDto();
		soapRespuestaDto.setIdRespuesta(respuestaDto.getIdRespuesta());
		soapRespuestaDto.setIdEvento(respuestaDto.getIdEvento());
		soapRespuestaDto.setIdUsuario(respuestaDto.getIdUsuario());
		soapRespuestaDto.setRespuesta(respuestaDto.getRespuesta());
		soapRespuestaDto.setFechaRespuesta(
				DatatypeFactory.newInstance().newXMLGregorianCalendar( (GregorianCalendar) respuestaDto.getFechaRespuesta()));
		return soapRespuestaDto;
    }

	public static RespuestaDto toRespuestaDto(
	    es.udc.ws.app.client.service.soap.wsdl.RespuestaDto respuestaDto) {
		return new RespuestaDto(respuestaDto.getIdRespuesta(), respuestaDto.getIdEvento(), 
			respuestaDto.isRespuesta() , respuestaDto.getFechaRespuesta().toGregorianCalendar(),
			respuestaDto.getIdUsuario());
	}     

	public static List<RespuestaDto> toRespuestaDtos(
	    List<es.udc.ws.app.client.service.soap.wsdl.RespuestaDto> respuestas) {
		List<RespuestaDto> respuestaDtos = new ArrayList<>(respuestas.size());
		for (int i = 0; i < respuestas.size(); i++) {
		    es.udc.ws.app.client.service.soap.wsdl.RespuestaDto respuesta = 
		            respuestas.get(i);
		    respuestaDtos.add(toRespuestaDto(respuesta));
		    
		}
		return respuestaDtos;
	}    
}
