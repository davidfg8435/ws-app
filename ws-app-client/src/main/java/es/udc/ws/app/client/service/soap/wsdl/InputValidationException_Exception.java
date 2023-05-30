
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InputValidationException", targetNamespace = "http://soap.ws.udc.es/")
public class InputValidationException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InputValidationException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InputValidationException_Exception(String message, InputValidationException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InputValidationException_Exception(String message, InputValidationException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: es.udc.ws.app.client.service.soap.wsdl.InputValidationException
     */
    public InputValidationException getFaultInfo() {
        return faultInfo;
    }

}
