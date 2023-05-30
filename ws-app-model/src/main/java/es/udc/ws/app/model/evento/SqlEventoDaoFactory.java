package es.udc.ws.app.model.evento;

import es.udc.ws.util.configuration.ConfigurationParametersManager;

public class SqlEventoDaoFactory {

	/**
	 * Parámetro de configuración localizado en el fichero de conf
	 * resources/ConfigurationParameters.properties, que nos indica
	 * cual es la clase implementadora del dao
	 */
    private final static String CLASS_NAME_PARAMETER = "SqlEventoDaoFactory.className";
    
    /**
     * Instancia única de esta clase (static)
     */
    private static SqlEventoDao dao = null;

    /**
     * Constructor pricado y vacío necesario para emplear esta clase como Singleton
     */
    private SqlEventoDaoFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static SqlEventoDao getInstance() {
        try {
            String daoClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (SqlEventoDao) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    /**
     * Si no hay instancia creada nos la crea sino nos devuelve siempre 
     * la misma.
     */
    public synchronized static SqlEventoDao getDao() {

        if (dao == null) {
            dao = getInstance();
        }
        return dao;

    }
}
