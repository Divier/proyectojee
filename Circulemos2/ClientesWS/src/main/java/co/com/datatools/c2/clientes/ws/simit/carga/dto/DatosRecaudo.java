
package co.com.datatools.c2.clientes.ws.simit.carga.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosRecaudo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosRecaudo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNIdTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNNumeroCuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcodigoCO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNconsecutivoRecaudo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdescripcionOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNestadoRecaudoPolca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfechaContable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfechaTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNhora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnumeroConsignacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnumeroReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNsecretaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoRecaudoReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorCheque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorEfectivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secretaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosRecaudo", propOrder = {
    "clave",
    "cuenta",
    "inIdTipoDocumento",
    "inNumeroCuota",
    "iNcodigoCO",
    "iNconsecutivoRecaudo",
    "iNdescripcionOrigen",
    "iNestadoRecaudoPolca",
    "iNfechaContable",
    "iNfechaTransaccion",
    "iNhora",
    "iNidentificacion",
    "iNnumeroConsignacion",
    "iNnumeroReferencia",
    "iNsecretaria",
    "iNtipoRecaudoReferencia",
    "iNvalorCheque",
    "iNvalorEfectivo",
    "iNvalorTotal",
    "secretaria",
    "usuario"
})
public class DatosRecaudo {

    @XmlElementRef(name = "clave", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clave;
    @XmlElementRef(name = "cuenta", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cuenta;
    @XmlElementRef(name = "iNIdTipoDocumento", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> inIdTipoDocumento;
    @XmlElementRef(name = "iNNumeroCuota", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> inNumeroCuota;
    @XmlElementRef(name = "iNcodigoCO", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcodigoCO;
    @XmlElementRef(name = "iNconsecutivoRecaudo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNconsecutivoRecaudo;
    @XmlElementRef(name = "iNdescripcionOrigen", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdescripcionOrigen;
    @XmlElementRef(name = "iNestadoRecaudoPolca", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNestadoRecaudoPolca;
    @XmlElementRef(name = "iNfechaContable", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfechaContable;
    @XmlElementRef(name = "iNfechaTransaccion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfechaTransaccion;
    @XmlElementRef(name = "iNhora", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNhora;
    @XmlElementRef(name = "iNidentificacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidentificacion;
    @XmlElementRef(name = "iNnumeroConsignacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnumeroConsignacion;
    @XmlElementRef(name = "iNnumeroReferencia", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnumeroReferencia;
    @XmlElementRef(name = "iNsecretaria", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNsecretaria;
    @XmlElementRef(name = "iNtipoRecaudoReferencia", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoRecaudoReferencia;
    @XmlElementRef(name = "iNvalorCheque", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorCheque;
    @XmlElementRef(name = "iNvalorEfectivo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorEfectivo;
    @XmlElementRef(name = "iNvalorTotal", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorTotal;
    @XmlElementRef(name = "secretaria", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> secretaria;
    @XmlElementRef(name = "usuario", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> usuario;

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClave(JAXBElement<String> value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuenta(JAXBElement<String> value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad inIdTipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINIdTipoDocumento() {
        return inIdTipoDocumento;
    }

    /**
     * Define el valor de la propiedad inIdTipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINIdTipoDocumento(JAXBElement<String> value) {
        this.inIdTipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad inNumeroCuota.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINNumeroCuota() {
        return inNumeroCuota;
    }

    /**
     * Define el valor de la propiedad inNumeroCuota.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINNumeroCuota(JAXBElement<String> value) {
        this.inNumeroCuota = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcodigoCO.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcodigoCO() {
        return iNcodigoCO;
    }

    /**
     * Define el valor de la propiedad iNcodigoCO.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcodigoCO(JAXBElement<String> value) {
        this.iNcodigoCO = value;
    }

    /**
     * Obtiene el valor de la propiedad iNconsecutivoRecaudo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINconsecutivoRecaudo() {
        return iNconsecutivoRecaudo;
    }

    /**
     * Define el valor de la propiedad iNconsecutivoRecaudo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINconsecutivoRecaudo(JAXBElement<String> value) {
        this.iNconsecutivoRecaudo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdescripcionOrigen.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdescripcionOrigen() {
        return iNdescripcionOrigen;
    }

    /**
     * Define el valor de la propiedad iNdescripcionOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdescripcionOrigen(JAXBElement<String> value) {
        this.iNdescripcionOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad iNestadoRecaudoPolca.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINestadoRecaudoPolca() {
        return iNestadoRecaudoPolca;
    }

    /**
     * Define el valor de la propiedad iNestadoRecaudoPolca.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINestadoRecaudoPolca(JAXBElement<String> value) {
        this.iNestadoRecaudoPolca = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfechaContable.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfechaContable() {
        return iNfechaContable;
    }

    /**
     * Define el valor de la propiedad iNfechaContable.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfechaContable(JAXBElement<String> value) {
        this.iNfechaContable = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfechaTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfechaTransaccion() {
        return iNfechaTransaccion;
    }

    /**
     * Define el valor de la propiedad iNfechaTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfechaTransaccion(JAXBElement<String> value) {
        this.iNfechaTransaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNhora.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINhora() {
        return iNhora;
    }

    /**
     * Define el valor de la propiedad iNhora.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINhora(JAXBElement<String> value) {
        this.iNhora = value;
    }

    /**
     * Obtiene el valor de la propiedad iNidentificacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINidentificacion() {
        return iNidentificacion;
    }

    /**
     * Define el valor de la propiedad iNidentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINidentificacion(JAXBElement<String> value) {
        this.iNidentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnumeroConsignacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnumeroConsignacion() {
        return iNnumeroConsignacion;
    }

    /**
     * Define el valor de la propiedad iNnumeroConsignacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnumeroConsignacion(JAXBElement<String> value) {
        this.iNnumeroConsignacion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnumeroReferencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnumeroReferencia() {
        return iNnumeroReferencia;
    }

    /**
     * Define el valor de la propiedad iNnumeroReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnumeroReferencia(JAXBElement<String> value) {
        this.iNnumeroReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad iNsecretaria.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINsecretaria() {
        return iNsecretaria;
    }

    /**
     * Define el valor de la propiedad iNsecretaria.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINsecretaria(JAXBElement<String> value) {
        this.iNsecretaria = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoRecaudoReferencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoRecaudoReferencia() {
        return iNtipoRecaudoReferencia;
    }

    /**
     * Define el valor de la propiedad iNtipoRecaudoReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoRecaudoReferencia(JAXBElement<String> value) {
        this.iNtipoRecaudoReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorCheque.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorCheque() {
        return iNvalorCheque;
    }

    /**
     * Define el valor de la propiedad iNvalorCheque.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorCheque(JAXBElement<String> value) {
        this.iNvalorCheque = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorEfectivo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorEfectivo() {
        return iNvalorEfectivo;
    }

    /**
     * Define el valor de la propiedad iNvalorEfectivo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorEfectivo(JAXBElement<String> value) {
        this.iNvalorEfectivo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorTotal.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorTotal() {
        return iNvalorTotal;
    }

    /**
     * Define el valor de la propiedad iNvalorTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorTotal(JAXBElement<String> value) {
        this.iNvalorTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad secretaria.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecretaria() {
        return secretaria;
    }

    /**
     * Define el valor de la propiedad secretaria.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecretaria(JAXBElement<String> value) {
        this.secretaria = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuario(JAXBElement<String> value) {
        this.usuario = value;
    }

}
