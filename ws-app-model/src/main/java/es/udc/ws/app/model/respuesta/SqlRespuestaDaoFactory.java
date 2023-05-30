package es.udc.ws.app.model.respuesta;

import es.udc.ws.util.configuration.ConfigurationParametersManager;

public class SqlRespuestaDaoFactory {
	
	private final static String CLASS_NAME_PARAMETER = "SqlRespuestaDaoFactory.className";
    private static SqlRespuestaDao dao = null;

    private SqlRespuestaDaoFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static SqlRespuestaDao getInstance() {
        try {
            String daoClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            System.out.println("daoClassName "+daoClassName);
            Class daoClass = Class.forName("es.udc.ws.app.model.respuesta.Jdbc3CcSqlRespuestaDao");
            return (SqlRespuestaDao) daoClass.newInstance();        
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static SqlRespuestaDao getDao() {

        if (dao == null) {
            dao = getInstance();
        }
        return dao;

    }

}
