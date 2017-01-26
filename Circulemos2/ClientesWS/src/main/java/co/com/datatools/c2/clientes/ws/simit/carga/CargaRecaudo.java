
package co.com.datatools.c2.clientes.ws.simit.carga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosRecaudo;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recaudo" type="{http://webservices.qxmultas.quipux.com.co/xsd}DatosRecaudo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "recaudo"
})
@XmlRootElement(name = "cargaRecaudo")
public class CargaRecaudo {

    @XmlElementRef(name = "recaudo", namespace = "http://webservices.qxmultas.quipux.com.co", type = JAXBElement.class, required = false)
    protected JAXBElement<DatosRecaudo> recaudo;

    /**
     * Obtiene el valor de la propiedad recaudo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DatosRecaudo }{@code >}
     *     
     */
    public JAXBElement<DatosRecaudo> getRecaudo() {
        return recaudo;
    }

    /**
     * Define el valor de la propiedad recaudo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DatosRecaudo }{@code >}
     *     
     */
    public void setRecaudo(JAXBElement<DatosRecaudo> value) {
        this.recaudo = value;
    }

}
