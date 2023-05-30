
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.udc.ws.app.client.service.soap.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemoveEventoResponse_QNAME = new QName("http://soap.ws.udc.es/", "removeEventoResponse");
    private final static QName _FindEventoResponse_QNAME = new QName("http://soap.ws.udc.es/", "findEventoResponse");
    private final static QName _FindRespuestas_QNAME = new QName("http://soap.ws.udc.es/", "findRespuestas");
    private final static QName _UpdateEvento_QNAME = new QName("http://soap.ws.udc.es/", "updateEvento");
    private final static QName _FindEventosResponse_QNAME = new QName("http://soap.ws.udc.es/", "findEventosResponse");
    private final static QName _InputValidationException_QNAME = new QName("http://soap.ws.udc.es/", "InputValidationException");
    private final static QName _FindEvento_QNAME = new QName("http://soap.ws.udc.es/", "findEvento");
    private final static QName _SoapInstanceNotFoundException_QNAME = new QName("http://soap.ws.udc.es/", "SoapInstanceNotFoundException");
    private final static QName _InstanceNotFoundException_QNAME = new QName("http://soap.ws.udc.es/", "InstanceNotFoundException");
    private final static QName _AddEvento_QNAME = new QName("http://soap.ws.udc.es/", "addEvento");
    private final static QName _Responder_QNAME = new QName("http://soap.ws.udc.es/", "responder");
    private final static QName _SoapInputValidationException_QNAME = new QName("http://soap.ws.udc.es/", "SoapInputValidationException");
    private final static QName _ResponderResponse_QNAME = new QName("http://soap.ws.udc.es/", "responderResponse");
    private final static QName _FindEventos_QNAME = new QName("http://soap.ws.udc.es/", "findEventos");
    private final static QName _RemoveEvento_QNAME = new QName("http://soap.ws.udc.es/", "removeEvento");
    private final static QName _FindRespuestasResponse_QNAME = new QName("http://soap.ws.udc.es/", "findRespuestasResponse");
    private final static QName _UpdateEventoResponse_QNAME = new QName("http://soap.ws.udc.es/", "updateEventoResponse");
    private final static QName _AddEventoResponse_QNAME = new QName("http://soap.ws.udc.es/", "addEventoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.udc.ws.app.client.service.soap.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SoapInstanceNotFoundExceptionInfo }
     * 
     */
    public SoapInstanceNotFoundExceptionInfo createSoapInstanceNotFoundExceptionInfo() {
        return new SoapInstanceNotFoundExceptionInfo();
    }

    /**
     * Create an instance of {@link InstanceNotFoundException }
     * 
     */
    public InstanceNotFoundException createInstanceNotFoundException() {
        return new InstanceNotFoundException();
    }

    /**
     * Create an instance of {@link AddEvento }
     * 
     */
    public AddEvento createAddEvento() {
        return new AddEvento();
    }

    /**
     * Create an instance of {@link Responder }
     * 
     */
    public Responder createResponder() {
        return new Responder();
    }

    /**
     * Create an instance of {@link ResponderResponse }
     * 
     */
    public ResponderResponse createResponderResponse() {
        return new ResponderResponse();
    }

    /**
     * Create an instance of {@link FindRespuestasResponse }
     * 
     */
    public FindRespuestasResponse createFindRespuestasResponse() {
        return new FindRespuestasResponse();
    }

    /**
     * Create an instance of {@link FindEventos }
     * 
     */
    public FindEventos createFindEventos() {
        return new FindEventos();
    }

    /**
     * Create an instance of {@link RemoveEvento }
     * 
     */
    public RemoveEvento createRemoveEvento() {
        return new RemoveEvento();
    }

    /**
     * Create an instance of {@link AddEventoResponse }
     * 
     */
    public AddEventoResponse createAddEventoResponse() {
        return new AddEventoResponse();
    }

    /**
     * Create an instance of {@link UpdateEventoResponse }
     * 
     */
    public UpdateEventoResponse createUpdateEventoResponse() {
        return new UpdateEventoResponse();
    }

    /**
     * Create an instance of {@link RemoveEventoResponse }
     * 
     */
    public RemoveEventoResponse createRemoveEventoResponse() {
        return new RemoveEventoResponse();
    }

    /**
     * Create an instance of {@link FindEventoResponse }
     * 
     */
    public FindEventoResponse createFindEventoResponse() {
        return new FindEventoResponse();
    }

    /**
     * Create an instance of {@link FindRespuestas }
     * 
     */
    public FindRespuestas createFindRespuestas() {
        return new FindRespuestas();
    }

    /**
     * Create an instance of {@link FindEventosResponse }
     * 
     */
    public FindEventosResponse createFindEventosResponse() {
        return new FindEventosResponse();
    }

    /**
     * Create an instance of {@link UpdateEvento }
     * 
     */
    public UpdateEvento createUpdateEvento() {
        return new UpdateEvento();
    }

    /**
     * Create an instance of {@link InputValidationException }
     * 
     */
    public InputValidationException createInputValidationException() {
        return new InputValidationException();
    }

    /**
     * Create an instance of {@link FindEvento }
     * 
     */
    public FindEvento createFindEvento() {
        return new FindEvento();
    }

    /**
     * Create an instance of {@link EventoDto }
     * 
     */
    public EventoDto createEventoDto() {
        return new EventoDto();
    }

    /**
     * Create an instance of {@link RespuestaDto }
     * 
     */
    public RespuestaDto createRespuestaDto() {
        return new RespuestaDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "removeEventoResponse")
    public JAXBElement<RemoveEventoResponse> createRemoveEventoResponse(RemoveEventoResponse value) {
        return new JAXBElement<RemoveEventoResponse>(_RemoveEventoResponse_QNAME, RemoveEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findEventoResponse")
    public JAXBElement<FindEventoResponse> createFindEventoResponse(FindEventoResponse value) {
        return new JAXBElement<FindEventoResponse>(_FindEventoResponse_QNAME, FindEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRespuestas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findRespuestas")
    public JAXBElement<FindRespuestas> createFindRespuestas(FindRespuestas value) {
        return new JAXBElement<FindRespuestas>(_FindRespuestas_QNAME, FindRespuestas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "updateEvento")
    public JAXBElement<UpdateEvento> createUpdateEvento(UpdateEvento value) {
        return new JAXBElement<UpdateEvento>(_UpdateEvento_QNAME, UpdateEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEventosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findEventosResponse")
    public JAXBElement<FindEventosResponse> createFindEventosResponse(FindEventosResponse value) {
        return new JAXBElement<FindEventosResponse>(_FindEventosResponse_QNAME, FindEventosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InputValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "InputValidationException")
    public JAXBElement<InputValidationException> createInputValidationException(InputValidationException value) {
        return new JAXBElement<InputValidationException>(_InputValidationException_QNAME, InputValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findEvento")
    public JAXBElement<FindEvento> createFindEvento(FindEvento value) {
        return new JAXBElement<FindEvento>(_FindEvento_QNAME, FindEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapInstanceNotFoundExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapInstanceNotFoundException")
    public JAXBElement<SoapInstanceNotFoundExceptionInfo> createSoapInstanceNotFoundException(SoapInstanceNotFoundExceptionInfo value) {
        return new JAXBElement<SoapInstanceNotFoundExceptionInfo>(_SoapInstanceNotFoundException_QNAME, SoapInstanceNotFoundExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstanceNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "InstanceNotFoundException")
    public JAXBElement<InstanceNotFoundException> createInstanceNotFoundException(InstanceNotFoundException value) {
        return new JAXBElement<InstanceNotFoundException>(_InstanceNotFoundException_QNAME, InstanceNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "addEvento")
    public JAXBElement<AddEvento> createAddEvento(AddEvento value) {
        return new JAXBElement<AddEvento>(_AddEvento_QNAME, AddEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Responder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "responder")
    public JAXBElement<Responder> createResponder(Responder value) {
        return new JAXBElement<Responder>(_Responder_QNAME, Responder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapInputValidationException")
    public JAXBElement<String> createSoapInputValidationException(String value) {
        return new JAXBElement<String>(_SoapInputValidationException_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "responderResponse")
    public JAXBElement<ResponderResponse> createResponderResponse(ResponderResponse value) {
        return new JAXBElement<ResponderResponse>(_ResponderResponse_QNAME, ResponderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEventos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findEventos")
    public JAXBElement<FindEventos> createFindEventos(FindEventos value) {
        return new JAXBElement<FindEventos>(_FindEventos_QNAME, FindEventos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEvento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "removeEvento")
    public JAXBElement<RemoveEvento> createRemoveEvento(RemoveEvento value) {
        return new JAXBElement<RemoveEvento>(_RemoveEvento_QNAME, RemoveEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRespuestasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findRespuestasResponse")
    public JAXBElement<FindRespuestasResponse> createFindRespuestasResponse(FindRespuestasResponse value) {
        return new JAXBElement<FindRespuestasResponse>(_FindRespuestasResponse_QNAME, FindRespuestasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "updateEventoResponse")
    public JAXBElement<UpdateEventoResponse> createUpdateEventoResponse(UpdateEventoResponse value) {
        return new JAXBElement<UpdateEventoResponse>(_UpdateEventoResponse_QNAME, UpdateEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "addEventoResponse")
    public JAXBElement<AddEventoResponse> createAddEventoResponse(AddEventoResponse value) {
        return new JAXBElement<AddEventoResponse>(_AddEventoResponse_QNAME, AddEventoResponse.class, null, value);
    }

}
