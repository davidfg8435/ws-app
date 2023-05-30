
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responder complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuestaDto" type="{http://soap.ws.udc.es/}respuestaDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responder", propOrder = {
    "respuestaDto"
})
public class Responder {

    protected RespuestaDto respuestaDto;

    /**
     * Obtiene el valor de la propiedad respuestaDto.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaDto }
     *     
     */
    public RespuestaDto getRespuestaDto() {
        return respuestaDto;
    }

    /**
     * Define el valor de la propiedad respuestaDto.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaDto }
     *     
     */
    public void setRespuestaDto(RespuestaDto value) {
        this.respuestaDto = value;
    }

}
