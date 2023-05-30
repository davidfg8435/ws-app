
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para addEvento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="addEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventoDto" type="{http://soap.ws.udc.es/}eventoDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEvento", propOrder = {
    "eventoDto"
})
public class AddEvento {

    protected EventoDto eventoDto;

    /**
     * Obtiene el valor de la propiedad eventoDto.
     * 
     * @return
     *     possible object is
     *     {@link EventoDto }
     *     
     */
    public EventoDto getEventoDto() {
        return eventoDto;
    }

    /**
     * Define el valor de la propiedad eventoDto.
     * 
     * @param value
     *     allowed object is
     *     {@link EventoDto }
     *     
     */
    public void setEventoDto(EventoDto value) {
        this.eventoDto = value;
    }

}
