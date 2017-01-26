
package co.com.datatools.c2.clientes.ws.simit.carga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosResolucion;


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
 *         &lt;element name="resolucion" type="{http://webservices.qxmultas.quipux.com.co/xsd}DatosResolucion" minOccurs="0"/>
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
    "resolucion"
})
@XmlRootElement(name = "cargaArchivoResolucion")
public class CargaArchivoResolucion {

    @XmlElementRef(name = "resolucion", namespace = "http://webservices.qxmultas.quipux.com.co", type = JAXBElement.class, required = false)
    protected JAXBElement<DatosResolucion> resolucion;

    /**
     * Obtiene el valor de la propiedad resolucion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DatosResolucion }{@code >}
     *     
     */
    public JAXBElement<DatosResolucion> getResolucion() {
        return resolucion;
    }

    /**
     * Define el valor de la propiedad resolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DatosResolucion }{@code >}
     *     
     */
    public void setResolucion(JAXBElement<DatosResolucion> value) {
        this.resolucion = value;
    }

}
