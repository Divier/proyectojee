
package co.com.datatools.c2.clientes.ws.simit.carga.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosResolucion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosResolucion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoI1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoOrganismo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consecutivoR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoResolucionPolca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaComparendo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fotoMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoAlcohol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horasComunitarias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCiudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idContraventor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroComparendo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroResolucionAnterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secretaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorAdicionalResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorInfraccionResolucion1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorPagarInfraccionResolucion1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorTotalResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosResolucion", propOrder = {
    "apellido",
    "clave",
    "codigoI1",
    "codigoOrganismo",
    "consecutivoR",
    "direccion",
    "estadoResolucionPolca",
    "fechaComparendo",
    "fechaHasta",
    "fechaResolucion",
    "fotoMulta",
    "gradoAlcohol",
    "horasComunitarias",
    "idCiudad",
    "idContraventor",
    "nombre",
    "numeroComparendo",
    "numeroResolucion",
    "numeroResolucionAnterior",
    "secretaria",
    "telefono",
    "tipoD",
    "tipoR",
    "usuario",
    "valorAdicionalResolucion",
    "valorInfraccionResolucion1",
    "valorPagarInfraccionResolucion1",
    "valorTotalResolucion"
})
public class DatosResolucion {

    @XmlElementRef(name = "apellido", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> apellido;
    @XmlElementRef(name = "clave", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clave;
    @XmlElementRef(name = "codigoI1", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codigoI1;
    @XmlElementRef(name = "codigoOrganismo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codigoOrganismo;
    @XmlElementRef(name = "consecutivoR", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> consecutivoR;
    @XmlElementRef(name = "direccion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> direccion;
    @XmlElementRef(name = "estadoResolucionPolca", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> estadoResolucionPolca;
    @XmlElementRef(name = "fechaComparendo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fechaComparendo;
    @XmlElementRef(name = "fechaHasta", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fechaHasta;
    @XmlElementRef(name = "fechaResolucion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fechaResolucion;
    @XmlElementRef(name = "fotoMulta", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fotoMulta;
    @XmlElementRef(name = "gradoAlcohol", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> gradoAlcohol;
    @XmlElementRef(name = "horasComunitarias", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> horasComunitarias;
    @XmlElementRef(name = "idCiudad", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> idCiudad;
    @XmlElementRef(name = "idContraventor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> idContraventor;
    @XmlElementRef(name = "nombre", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nombre;
    @XmlElementRef(name = "numeroComparendo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numeroComparendo;
    @XmlElementRef(name = "numeroResolucion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numeroResolucion;
    @XmlElementRef(name = "numeroResolucionAnterior", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numeroResolucionAnterior;
    @XmlElementRef(name = "secretaria", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> secretaria;
    @XmlElementRef(name = "telefono", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> telefono;
    @XmlElementRef(name = "tipoD", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoD;
    @XmlElementRef(name = "tipoR", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoR;
    @XmlElementRef(name = "usuario", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> usuario;
    @XmlElementRef(name = "valorAdicionalResolucion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valorAdicionalResolucion;
    @XmlElementRef(name = "valorInfraccionResolucion1", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valorInfraccionResolucion1;
    @XmlElementRef(name = "valorPagarInfraccionResolucion1", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valorPagarInfraccionResolucion1;
    @XmlElementRef(name = "valorTotalResolucion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> valorTotalResolucion;

    /**
     * Obtiene el valor de la propiedad apellido.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellido() {
        return apellido;
    }

    /**
     * Define el valor de la propiedad apellido.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellido(JAXBElement<String> value) {
        this.apellido = value;
    }

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
     * Obtiene el valor de la propiedad codigoI1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoI1() {
        return codigoI1;
    }

    /**
     * Define el valor de la propiedad codigoI1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoI1(JAXBElement<String> value) {
        this.codigoI1 = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    /**
     * Define el valor de la propiedad codigoOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoOrganismo(JAXBElement<String> value) {
        this.codigoOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad consecutivoR.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConsecutivoR() {
        return consecutivoR;
    }

    /**
     * Define el valor de la propiedad consecutivoR.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConsecutivoR(JAXBElement<String> value) {
        this.consecutivoR = value;
    }

    /**
     * Obtiene el valor de la propiedad direccion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDireccion() {
        return direccion;
    }

    /**
     * Define el valor de la propiedad direccion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDireccion(JAXBElement<String> value) {
        this.direccion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoResolucionPolca.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoResolucionPolca() {
        return estadoResolucionPolca;
    }

    /**
     * Define el valor de la propiedad estadoResolucionPolca.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoResolucionPolca(JAXBElement<String> value) {
        this.estadoResolucionPolca = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaComparendo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaComparendo() {
        return fechaComparendo;
    }

    /**
     * Define el valor de la propiedad fechaComparendo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaComparendo(JAXBElement<String> value) {
        this.fechaComparendo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHasta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Define el valor de la propiedad fechaHasta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaHasta(JAXBElement<String> value) {
        this.fechaHasta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaResolucion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaResolucion() {
        return fechaResolucion;
    }

    /**
     * Define el valor de la propiedad fechaResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaResolucion(JAXBElement<String> value) {
        this.fechaResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoMulta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFotoMulta() {
        return fotoMulta;
    }

    /**
     * Define el valor de la propiedad fotoMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFotoMulta(JAXBElement<String> value) {
        this.fotoMulta = value;
    }

    /**
     * Obtiene el valor de la propiedad gradoAlcohol.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGradoAlcohol() {
        return gradoAlcohol;
    }

    /**
     * Define el valor de la propiedad gradoAlcohol.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGradoAlcohol(JAXBElement<String> value) {
        this.gradoAlcohol = value;
    }

    /**
     * Obtiene el valor de la propiedad horasComunitarias.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHorasComunitarias() {
        return horasComunitarias;
    }

    /**
     * Define el valor de la propiedad horasComunitarias.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHorasComunitarias(JAXBElement<String> value) {
        this.horasComunitarias = value;
    }

    /**
     * Obtiene el valor de la propiedad idCiudad.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCiudad() {
        return idCiudad;
    }

    /**
     * Define el valor de la propiedad idCiudad.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCiudad(JAXBElement<String> value) {
        this.idCiudad = value;
    }

    /**
     * Obtiene el valor de la propiedad idContraventor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdContraventor() {
        return idContraventor;
    }

    /**
     * Define el valor de la propiedad idContraventor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdContraventor(JAXBElement<String> value) {
        this.idContraventor = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombre(JAXBElement<String> value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroComparendo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroComparendo() {
        return numeroComparendo;
    }

    /**
     * Define el valor de la propiedad numeroComparendo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroComparendo(JAXBElement<String> value) {
        this.numeroComparendo = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroResolucion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroResolucion() {
        return numeroResolucion;
    }

    /**
     * Define el valor de la propiedad numeroResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroResolucion(JAXBElement<String> value) {
        this.numeroResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroResolucionAnterior.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroResolucionAnterior() {
        return numeroResolucionAnterior;
    }

    /**
     * Define el valor de la propiedad numeroResolucionAnterior.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroResolucionAnterior(JAXBElement<String> value) {
        this.numeroResolucionAnterior = value;
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
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelefono(JAXBElement<String> value) {
        this.telefono = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoD.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoD() {
        return tipoD;
    }

    /**
     * Define el valor de la propiedad tipoD.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoD(JAXBElement<String> value) {
        this.tipoD = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoR.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoR() {
        return tipoR;
    }

    /**
     * Define el valor de la propiedad tipoR.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoR(JAXBElement<String> value) {
        this.tipoR = value;
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

    /**
     * Obtiene el valor de la propiedad valorAdicionalResolucion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorAdicionalResolucion() {
        return valorAdicionalResolucion;
    }

    /**
     * Define el valor de la propiedad valorAdicionalResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorAdicionalResolucion(JAXBElement<String> value) {
        this.valorAdicionalResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad valorInfraccionResolucion1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorInfraccionResolucion1() {
        return valorInfraccionResolucion1;
    }

    /**
     * Define el valor de la propiedad valorInfraccionResolucion1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorInfraccionResolucion1(JAXBElement<String> value) {
        this.valorInfraccionResolucion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad valorPagarInfraccionResolucion1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorPagarInfraccionResolucion1() {
        return valorPagarInfraccionResolucion1;
    }

    /**
     * Define el valor de la propiedad valorPagarInfraccionResolucion1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorPagarInfraccionResolucion1(JAXBElement<String> value) {
        this.valorPagarInfraccionResolucion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad valorTotalResolucion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorTotalResolucion() {
        return valorTotalResolucion;
    }

    /**
     * Define el valor de la propiedad valorTotalResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorTotalResolucion(JAXBElement<String> value) {
        this.valorTotalResolucion = value;
    }

}
