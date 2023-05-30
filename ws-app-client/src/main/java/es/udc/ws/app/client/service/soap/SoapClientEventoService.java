package es.udc.ws.app.client.service.soap;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;

import es.udc.ws.app.client.service.ClientEventoService;
import es.udc.ws.app.client.service.soap.wsdl.EventoProvider;
import es.udc.ws.app.client.service.soap.wsdl.EventoProviderService;
import es.udc.ws.app.client.service.soap.wsdl.InputValidationException_Exception;
import es.udc.ws.app.client.service.soap.wsdl.InstanceNotFoundException_Exception;
import es.udc.ws.app.client.service.soap.wsdl.SoapInputValidationException;
import es.udc.ws.app.client.service.soap.wsdl.SoapInstanceNotFoundException;
import es.udc.ws.app.dto.EventoDto;
import es.udc.ws.app.dto.RespuestaDto;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class SoapClientEventoService implements ClientEventoService {

    private final static String ENDPOINT_ADDRESS_PARAMETER =
            "SoapClientEventoService.endpointAddress";
    
    private String endpointAddress;
    
    private EventoProvider eventoProvider;
    
    public SoapClientEventoService() {
        init(getEndpointAddress());
    }
    
    private void init(String eventoProviderURL) {
        EventoProviderService stockQuoteProviderService =
                new EventoProviderService();
        eventoProvider = stockQuoteProviderService
                .getEventoProviderPort();
        ((BindingProvider) eventoProvider).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                eventoProviderURL);
    }
    
    private String getEndpointAddress() {

        if (endpointAddress == null) {
            endpointAddress = ConfigurationParametersManager.getParameter(
                ENDPOINT_ADDRESS_PARAMETER);
        }

        return endpointAddress;
    }
    
	public Long addEvento(EventoDto evento) throws InputValidationException {
        try {
            return eventoProvider.addEvento(EventoDtoToSoapEventoDtoConversor
                    .toSoapEventoDto(evento));
        } catch (SoapInputValidationException ex) {
            throw new InputValidationException(ex.getMessage());
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
	}
	
	public EventoDto findEvento (Long idEvento)
			throws InputValidationException, InstanceNotFoundException, DatatypeConfigurationException{
    	try {
    		return EventoDtoToSoapEventoDtoConversor.toEventoDto(
    				eventoProvider.findEvento(idEvento) );
    	} catch (SoapInstanceNotFoundException ex) {
            throw new InstanceNotFoundException(
                    ex.getFaultInfo().getInstanceId(), 
                    ex.getFaultInfo().getInstanceType());
        }
	}
	
    public void removeEvento(Long idEvento) 
    		throws InstanceNotFoundException{
    	try {
    		eventoProvider.removeEvento(idEvento);
    	} catch (SoapInstanceNotFoundException ex) {
    		throw new InstanceNotFoundException(
    			ex.getFaultInfo().getInstanceId(),
                ex.getFaultInfo().getInstanceType());
    	}
    }

    public void updateEvento(EventoDto evento) 
    		throws InputValidationException, InstanceNotFoundException{
	    try {
	        eventoProvider.updateEvento(EventoDtoToSoapEventoDtoConversor
	                .toSoapEventoDto(evento));
	    } catch (SoapInputValidationException ex) {
	        throw new InputValidationException(ex.getMessage());
	    } catch (SoapInstanceNotFoundException ex) {
				throw new InstanceNotFoundException(
				        ex.getFaultInfo().getInstanceId(),
				        ex.getFaultInfo().getInstanceType());
	    } catch(Exception ex) {
	        throw new RuntimeException(ex);
	    }
    }
    
    public List<EventoDto> findEventos(String keywords, Calendar fechaInicio, Calendar fechaFin) 
    		throws DatatypeConfigurationException{
		if(fechaInicio != null && fechaFin != null) {
			GregorianCalendar f1 = new GregorianCalendar();
            GregorianCalendar f2 = new GregorianCalendar();

            f1.setTime(fechaInicio.getTime());
            f2.setTime(fechaFin.getTime());
            
            try {
				return EventoDtoToSoapEventoDtoConversor.toEventoDtos(
						eventoProvider.findEventos(keywords, DatatypeFactory.newInstance().newXMLGregorianCalendar(f1),
								DatatypeFactory.newInstance().newXMLGregorianCalendar(f2)));
			} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
					return null;
			}
		} else {
			return EventoDtoToSoapEventoDtoConversor.toEventoDtos(
                    eventoProvider.findEventos(keywords, null, null));
		}
    }
    
    public Long responder(RespuestaDto respuestaDto) 
            throws InputValidationException, InstanceNotFoundException{
        try {
            return eventoProvider.responder(RespuestaDtoToSoapRespuestaDtoConversor
                    .toSoapRespuestaDto(respuestaDto));
        } catch (SoapInputValidationException ex) {
            throw new InputValidationException(ex.getMessage());
        } catch (SoapInstanceNotFoundException ex) {
            throw new InstanceNotFoundException(
                    ex.getFaultInfo().getInstanceId(), 
                    ex.getFaultInfo().getInstanceType());  
    	} catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
	public List<RespuestaDto> findResponses(Long idUsuario, Long idEvento, Boolean respuesta) 
			throws InputValidationException_Exception, InstanceNotFoundException_Exception{
    	return RespuestaDtoToSoapRespuestaDtoConversor.toRespuestaDtos(
    			eventoProvider.findRespuestas(idUsuario, idEvento, respuesta));
	}


}
