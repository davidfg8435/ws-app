package es.udc.ws.app.model.eventoservice;

import es.udc.ws.util.configuration.ConfigurationParametersManager;

public class EventoServiceFactory {
	
	/**
	 * Parámetro de configuración localizado en el fichero de conf
	 * resources/ConfigurationParameters.properties, que nos indica
	 * cual es la clase implementadora del servicios
	 */
    private final static String CLASS_NAME_PARAMETER = "EventoServiceFactory.className";
    
    /**
     * Instancia única de esta clase (static)
     */
    private static EventoService service = null;
    
    /**
     * Constructor pricado y vacío necesario para emplear esta clase como Singleton
     */
    private EventoServiceFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static EventoService getInstance() {
        try {
            String serviceClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            Class serviceClass = Class.forName(serviceClassName);
            return (EventoService) serviceClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    /**
     * Si no hay instancia creada nos la crea sino nos devuelve siempre 
     * la misma.
     */
    public synchronized static EventoService getService() {

        if (service == null) {
            service = getInstance();
        }
        return service;

    }
}