
package co.com.datatools.c2.clientes.ws.simit.carga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo;


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
 *         &lt;element name="comparendo" type="{http://webservices.qxmultas.quipux.com.co/xsd}DatosComparendo" minOccurs="0"/>
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
    "comparendo"
})
@XmlRootElement(name = "cargaArchivoComparendos")
public class CargaArchivoComparendos {

    @XmlElementRef(name = "comparendo", namespace = "http://webservices.qxmultas.quipux.com.co", type = JAXBElement.class, required = false)
    protected JAXBElement<DatosComparendo> comparendo;

    /**
     * Obtiene el valor de la propiedad comparendo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DatosComparendo }{@code >}
     *     
     */
    public JAXBElement<DatosComparendo> getComparendo() {
        return comparendo;
    }

    /**
     * Define el valor de la propiedad comparendo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DatosComparendo }{@code >}
     *     
     */
    public void setComparendo(JAXBElement<DatosComparendo> value) {
        this.comparendo = value;
    }

}
