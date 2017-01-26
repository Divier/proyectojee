
package co.com.datatools.c2.clientes.ws.simit.carga.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosComparendo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosComparendo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNaccidente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNapellidoInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNciudadInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcodigoInfraccion1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcodigoMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcodigoPa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcodigoRa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNcomparendoElectronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNconsecutivoComparendo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNconsecutivoIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdirPatioInmovi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdirecTesti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdireccionInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdivipoLicencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdivipoMatricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNdivipoMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNedadIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNemail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNestado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNestadoComparendoPolca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfechaNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfechaVence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNfuga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNgradoAlcohol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNgruaNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNhora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidSecretariaExpide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidTipoDocP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNidentificacionTestigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNinmovilizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNlicenciaConduccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNlicenciaTransito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNlocalidadComuna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnitEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnombreEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnombreInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnombreProp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnombreTestigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNnumeroComparendo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNobservaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNorganismoTransito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNpatioInmoviliza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNplaca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNplacaAgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNplacaGrua" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtarjetaOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNteleTestigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtelefonoInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoInfractor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNtipoV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorAdi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iNvalorInf1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DatosComparendo", propOrder = {
    "clave",
    "iNaccidente",
    "iNapellidoInfractor",
    "iNciudadInfractor",
    "iNcodigoInfraccion1",
    "iNcodigoMo",
    "iNcodigoPa",
    "iNcodigoRa",
    "iNcomparendoElectronico",
    "iNconsecutivoComparendo",
    "iNconsecutivoIn",
    "iNdirPatioInmovi",
    "iNdirecTesti",
    "iNdireccion",
    "iNdireccionInfractor",
    "iNdivipoLicencia",
    "iNdivipoMatricula",
    "iNdivipoMunicipio",
    "iNedadIn",
    "iNemail",
    "iNestado",
    "iNestadoComparendoPolca",
    "iNfecha",
    "iNfechaNotificacion",
    "iNfechaVence",
    "iNfuga",
    "iNgradoAlcohol",
    "iNgruaNumero",
    "iNhora",
    "iNidInfractor",
    "iNidSecretariaExpide",
    "iNidTipoDocP",
    "iNidentificacion",
    "iNidentificacionTestigo",
    "iNinmovilizacion",
    "iNlicenciaConduccion",
    "iNlicenciaTransito",
    "iNlocalidadComuna",
    "iNnitEmpresa",
    "iNnombreEmpresa",
    "iNnombreInfractor",
    "iNnombreProp",
    "iNnombreTestigo",
    "iNnumeroComparendo",
    "iNobservaciones",
    "iNorganismoTransito",
    "iNpatioInmoviliza",
    "iNplaca",
    "iNplacaAgente",
    "iNplacaGrua",
    "iNtarjetaOperacion",
    "iNteleTestigo",
    "iNtelefonoInfractor",
    "iNtipoC",
    "iNtipoD",
    "iNtipoInfractor",
    "iNtipoS",
    "iNtipoV",
    "iNvalorAdi",
    "iNvalorComp",
    "iNvalorInf1",
    "secretaria",
    "usuario"
})
public class DatosComparendo {

    @XmlElementRef(name = "clave", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clave;
    @XmlElementRef(name = "iNaccidente", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNaccidente;
    @XmlElementRef(name = "iNapellidoInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNapellidoInfractor;
    @XmlElementRef(name = "iNciudadInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNciudadInfractor;
    @XmlElementRef(name = "iNcodigoInfraccion1", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcodigoInfraccion1;
    @XmlElementRef(name = "iNcodigoMo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcodigoMo;
    @XmlElementRef(name = "iNcodigoPa", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcodigoPa;
    @XmlElementRef(name = "iNcodigoRa", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcodigoRa;
    @XmlElementRef(name = "iNcomparendoElectronico", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNcomparendoElectronico;
    @XmlElementRef(name = "iNconsecutivoComparendo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNconsecutivoComparendo;
    @XmlElementRef(name = "iNconsecutivoIn", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNconsecutivoIn;
    @XmlElementRef(name = "iNdirPatioInmovi", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdirPatioInmovi;
    @XmlElementRef(name = "iNdirecTesti", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdirecTesti;
    @XmlElementRef(name = "iNdireccion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdireccion;
    @XmlElementRef(name = "iNdireccionInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdireccionInfractor;
    @XmlElementRef(name = "iNdivipoLicencia", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdivipoLicencia;
    @XmlElementRef(name = "iNdivipoMatricula", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdivipoMatricula;
    @XmlElementRef(name = "iNdivipoMunicipio", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNdivipoMunicipio;
    @XmlElementRef(name = "iNedadIn", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNedadIn;
    @XmlElementRef(name = "iNemail", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNemail;
    @XmlElementRef(name = "iNestado", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNestado;
    @XmlElementRef(name = "iNestadoComparendoPolca", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNestadoComparendoPolca;
    @XmlElementRef(name = "iNfecha", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfecha;
    @XmlElementRef(name = "iNfechaNotificacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfechaNotificacion;
    @XmlElementRef(name = "iNfechaVence", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfechaVence;
    @XmlElementRef(name = "iNfuga", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNfuga;
    @XmlElementRef(name = "iNgradoAlcohol", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNgradoAlcohol;
    @XmlElementRef(name = "iNgruaNumero", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNgruaNumero;
    @XmlElementRef(name = "iNhora", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNhora;
    @XmlElementRef(name = "iNidInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidInfractor;
    @XmlElementRef(name = "iNidSecretariaExpide", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidSecretariaExpide;
    @XmlElementRef(name = "iNidTipoDocP", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidTipoDocP;
    @XmlElementRef(name = "iNidentificacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidentificacion;
    @XmlElementRef(name = "iNidentificacionTestigo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNidentificacionTestigo;
    @XmlElementRef(name = "iNinmovilizacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNinmovilizacion;
    @XmlElementRef(name = "iNlicenciaConduccion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNlicenciaConduccion;
    @XmlElementRef(name = "iNlicenciaTransito", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNlicenciaTransito;
    @XmlElementRef(name = "iNlocalidadComuna", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNlocalidadComuna;
    @XmlElementRef(name = "iNnitEmpresa", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnitEmpresa;
    @XmlElementRef(name = "iNnombreEmpresa", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnombreEmpresa;
    @XmlElementRef(name = "iNnombreInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnombreInfractor;
    @XmlElementRef(name = "iNnombreProp", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnombreProp;
    @XmlElementRef(name = "iNnombreTestigo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnombreTestigo;
    @XmlElementRef(name = "iNnumeroComparendo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNnumeroComparendo;
    @XmlElementRef(name = "iNobservaciones", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNobservaciones;
    @XmlElementRef(name = "iNorganismoTransito", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNorganismoTransito;
    @XmlElementRef(name = "iNpatioInmoviliza", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNpatioInmoviliza;
    @XmlElementRef(name = "iNplaca", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNplaca;
    @XmlElementRef(name = "iNplacaAgente", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNplacaAgente;
    @XmlElementRef(name = "iNplacaGrua", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNplacaGrua;
    @XmlElementRef(name = "iNtarjetaOperacion", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtarjetaOperacion;
    @XmlElementRef(name = "iNteleTestigo", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNteleTestigo;
    @XmlElementRef(name = "iNtelefonoInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtelefonoInfractor;
    @XmlElementRef(name = "iNtipoC", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoC;
    @XmlElementRef(name = "iNtipoD", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoD;
    @XmlElementRef(name = "iNtipoInfractor", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoInfractor;
    @XmlElementRef(name = "iNtipoS", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoS;
    @XmlElementRef(name = "iNtipoV", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNtipoV;
    @XmlElementRef(name = "iNvalorAdi", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorAdi;
    @XmlElementRef(name = "iNvalorComp", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorComp;
    @XmlElementRef(name = "iNvalorInf1", namespace = "http://webservices.qxmultas.quipux.com.co/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> iNvalorInf1;
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
     * Obtiene el valor de la propiedad iNaccidente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINaccidente() {
        return iNaccidente;
    }

    /**
     * Define el valor de la propiedad iNaccidente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINaccidente(JAXBElement<String> value) {
        this.iNaccidente = value;
    }

    /**
     * Obtiene el valor de la propiedad iNapellidoInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINapellidoInfractor() {
        return iNapellidoInfractor;
    }

    /**
     * Define el valor de la propiedad iNapellidoInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINapellidoInfractor(JAXBElement<String> value) {
        this.iNapellidoInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNciudadInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINciudadInfractor() {
        return iNciudadInfractor;
    }

    /**
     * Define el valor de la propiedad iNciudadInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINciudadInfractor(JAXBElement<String> value) {
        this.iNciudadInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcodigoInfraccion1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcodigoInfraccion1() {
        return iNcodigoInfraccion1;
    }

    /**
     * Define el valor de la propiedad iNcodigoInfraccion1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcodigoInfraccion1(JAXBElement<String> value) {
        this.iNcodigoInfraccion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcodigoMo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcodigoMo() {
        return iNcodigoMo;
    }

    /**
     * Define el valor de la propiedad iNcodigoMo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcodigoMo(JAXBElement<String> value) {
        this.iNcodigoMo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcodigoPa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcodigoPa() {
        return iNcodigoPa;
    }

    /**
     * Define el valor de la propiedad iNcodigoPa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcodigoPa(JAXBElement<String> value) {
        this.iNcodigoPa = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcodigoRa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcodigoRa() {
        return iNcodigoRa;
    }

    /**
     * Define el valor de la propiedad iNcodigoRa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcodigoRa(JAXBElement<String> value) {
        this.iNcodigoRa = value;
    }

    /**
     * Obtiene el valor de la propiedad iNcomparendoElectronico.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINcomparendoElectronico() {
        return iNcomparendoElectronico;
    }

    /**
     * Define el valor de la propiedad iNcomparendoElectronico.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINcomparendoElectronico(JAXBElement<String> value) {
        this.iNcomparendoElectronico = value;
    }

    /**
     * Obtiene el valor de la propiedad iNconsecutivoComparendo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINconsecutivoComparendo() {
        return iNconsecutivoComparendo;
    }

    /**
     * Define el valor de la propiedad iNconsecutivoComparendo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINconsecutivoComparendo(JAXBElement<String> value) {
        this.iNconsecutivoComparendo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNconsecutivoIn.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINconsecutivoIn() {
        return iNconsecutivoIn;
    }

    /**
     * Define el valor de la propiedad iNconsecutivoIn.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINconsecutivoIn(JAXBElement<String> value) {
        this.iNconsecutivoIn = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdirPatioInmovi.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdirPatioInmovi() {
        return iNdirPatioInmovi;
    }

    /**
     * Define el valor de la propiedad iNdirPatioInmovi.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdirPatioInmovi(JAXBElement<String> value) {
        this.iNdirPatioInmovi = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdirecTesti.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdirecTesti() {
        return iNdirecTesti;
    }

    /**
     * Define el valor de la propiedad iNdirecTesti.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdirecTesti(JAXBElement<String> value) {
        this.iNdirecTesti = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdireccion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdireccion() {
        return iNdireccion;
    }

    /**
     * Define el valor de la propiedad iNdireccion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdireccion(JAXBElement<String> value) {
        this.iNdireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdireccionInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdireccionInfractor() {
        return iNdireccionInfractor;
    }

    /**
     * Define el valor de la propiedad iNdireccionInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdireccionInfractor(JAXBElement<String> value) {
        this.iNdireccionInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdivipoLicencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdivipoLicencia() {
        return iNdivipoLicencia;
    }

    /**
     * Define el valor de la propiedad iNdivipoLicencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdivipoLicencia(JAXBElement<String> value) {
        this.iNdivipoLicencia = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdivipoMatricula.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdivipoMatricula() {
        return iNdivipoMatricula;
    }

    /**
     * Define el valor de la propiedad iNdivipoMatricula.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdivipoMatricula(JAXBElement<String> value) {
        this.iNdivipoMatricula = value;
    }

    /**
     * Obtiene el valor de la propiedad iNdivipoMunicipio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINdivipoMunicipio() {
        return iNdivipoMunicipio;
    }

    /**
     * Define el valor de la propiedad iNdivipoMunicipio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINdivipoMunicipio(JAXBElement<String> value) {
        this.iNdivipoMunicipio = value;
    }

    /**
     * Obtiene el valor de la propiedad iNedadIn.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINedadIn() {
        return iNedadIn;
    }

    /**
     * Define el valor de la propiedad iNedadIn.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINedadIn(JAXBElement<String> value) {
        this.iNedadIn = value;
    }

    /**
     * Obtiene el valor de la propiedad iNemail.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINemail() {
        return iNemail;
    }

    /**
     * Define el valor de la propiedad iNemail.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINemail(JAXBElement<String> value) {
        this.iNemail = value;
    }

    /**
     * Obtiene el valor de la propiedad iNestado.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINestado() {
        return iNestado;
    }

    /**
     * Define el valor de la propiedad iNestado.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINestado(JAXBElement<String> value) {
        this.iNestado = value;
    }

    /**
     * Obtiene el valor de la propiedad iNestadoComparendoPolca.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINestadoComparendoPolca() {
        return iNestadoComparendoPolca;
    }

    /**
     * Define el valor de la propiedad iNestadoComparendoPolca.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINestadoComparendoPolca(JAXBElement<String> value) {
        this.iNestadoComparendoPolca = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfecha.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfecha() {
        return iNfecha;
    }

    /**
     * Define el valor de la propiedad iNfecha.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfecha(JAXBElement<String> value) {
        this.iNfecha = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfechaNotificacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfechaNotificacion() {
        return iNfechaNotificacion;
    }

    /**
     * Define el valor de la propiedad iNfechaNotificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfechaNotificacion(JAXBElement<String> value) {
        this.iNfechaNotificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfechaVence.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfechaVence() {
        return iNfechaVence;
    }

    /**
     * Define el valor de la propiedad iNfechaVence.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfechaVence(JAXBElement<String> value) {
        this.iNfechaVence = value;
    }

    /**
     * Obtiene el valor de la propiedad iNfuga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINfuga() {
        return iNfuga;
    }

    /**
     * Define el valor de la propiedad iNfuga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINfuga(JAXBElement<String> value) {
        this.iNfuga = value;
    }

    /**
     * Obtiene el valor de la propiedad iNgradoAlcohol.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINgradoAlcohol() {
        return iNgradoAlcohol;
    }

    /**
     * Define el valor de la propiedad iNgradoAlcohol.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINgradoAlcohol(JAXBElement<String> value) {
        this.iNgradoAlcohol = value;
    }

    /**
     * Obtiene el valor de la propiedad iNgruaNumero.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINgruaNumero() {
        return iNgruaNumero;
    }

    /**
     * Define el valor de la propiedad iNgruaNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINgruaNumero(JAXBElement<String> value) {
        this.iNgruaNumero = value;
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
     * Obtiene el valor de la propiedad iNidInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINidInfractor() {
        return iNidInfractor;
    }

    /**
     * Define el valor de la propiedad iNidInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINidInfractor(JAXBElement<String> value) {
        this.iNidInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNidSecretariaExpide.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINidSecretariaExpide() {
        return iNidSecretariaExpide;
    }

    /**
     * Define el valor de la propiedad iNidSecretariaExpide.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINidSecretariaExpide(JAXBElement<String> value) {
        this.iNidSecretariaExpide = value;
    }

    /**
     * Obtiene el valor de la propiedad iNidTipoDocP.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINidTipoDocP() {
        return iNidTipoDocP;
    }

    /**
     * Define el valor de la propiedad iNidTipoDocP.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINidTipoDocP(JAXBElement<String> value) {
        this.iNidTipoDocP = value;
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
     * Obtiene el valor de la propiedad iNidentificacionTestigo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINidentificacionTestigo() {
        return iNidentificacionTestigo;
    }

    /**
     * Define el valor de la propiedad iNidentificacionTestigo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINidentificacionTestigo(JAXBElement<String> value) {
        this.iNidentificacionTestigo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNinmovilizacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINinmovilizacion() {
        return iNinmovilizacion;
    }

    /**
     * Define el valor de la propiedad iNinmovilizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINinmovilizacion(JAXBElement<String> value) {
        this.iNinmovilizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNlicenciaConduccion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINlicenciaConduccion() {
        return iNlicenciaConduccion;
    }

    /**
     * Define el valor de la propiedad iNlicenciaConduccion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINlicenciaConduccion(JAXBElement<String> value) {
        this.iNlicenciaConduccion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNlicenciaTransito.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINlicenciaTransito() {
        return iNlicenciaTransito;
    }

    /**
     * Define el valor de la propiedad iNlicenciaTransito.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINlicenciaTransito(JAXBElement<String> value) {
        this.iNlicenciaTransito = value;
    }

    /**
     * Obtiene el valor de la propiedad iNlocalidadComuna.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINlocalidadComuna() {
        return iNlocalidadComuna;
    }

    /**
     * Define el valor de la propiedad iNlocalidadComuna.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINlocalidadComuna(JAXBElement<String> value) {
        this.iNlocalidadComuna = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnitEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnitEmpresa() {
        return iNnitEmpresa;
    }

    /**
     * Define el valor de la propiedad iNnitEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnitEmpresa(JAXBElement<String> value) {
        this.iNnitEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnombreEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnombreEmpresa() {
        return iNnombreEmpresa;
    }

    /**
     * Define el valor de la propiedad iNnombreEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnombreEmpresa(JAXBElement<String> value) {
        this.iNnombreEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnombreInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnombreInfractor() {
        return iNnombreInfractor;
    }

    /**
     * Define el valor de la propiedad iNnombreInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnombreInfractor(JAXBElement<String> value) {
        this.iNnombreInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnombreProp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnombreProp() {
        return iNnombreProp;
    }

    /**
     * Define el valor de la propiedad iNnombreProp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnombreProp(JAXBElement<String> value) {
        this.iNnombreProp = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnombreTestigo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnombreTestigo() {
        return iNnombreTestigo;
    }

    /**
     * Define el valor de la propiedad iNnombreTestigo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnombreTestigo(JAXBElement<String> value) {
        this.iNnombreTestigo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNnumeroComparendo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINnumeroComparendo() {
        return iNnumeroComparendo;
    }

    /**
     * Define el valor de la propiedad iNnumeroComparendo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINnumeroComparendo(JAXBElement<String> value) {
        this.iNnumeroComparendo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNobservaciones.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINobservaciones() {
        return iNobservaciones;
    }

    /**
     * Define el valor de la propiedad iNobservaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINobservaciones(JAXBElement<String> value) {
        this.iNobservaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad iNorganismoTransito.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINorganismoTransito() {
        return iNorganismoTransito;
    }

    /**
     * Define el valor de la propiedad iNorganismoTransito.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINorganismoTransito(JAXBElement<String> value) {
        this.iNorganismoTransito = value;
    }

    /**
     * Obtiene el valor de la propiedad iNpatioInmoviliza.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINpatioInmoviliza() {
        return iNpatioInmoviliza;
    }

    /**
     * Define el valor de la propiedad iNpatioInmoviliza.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINpatioInmoviliza(JAXBElement<String> value) {
        this.iNpatioInmoviliza = value;
    }

    /**
     * Obtiene el valor de la propiedad iNplaca.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINplaca() {
        return iNplaca;
    }

    /**
     * Define el valor de la propiedad iNplaca.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINplaca(JAXBElement<String> value) {
        this.iNplaca = value;
    }

    /**
     * Obtiene el valor de la propiedad iNplacaAgente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINplacaAgente() {
        return iNplacaAgente;
    }

    /**
     * Define el valor de la propiedad iNplacaAgente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINplacaAgente(JAXBElement<String> value) {
        this.iNplacaAgente = value;
    }

    /**
     * Obtiene el valor de la propiedad iNplacaGrua.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINplacaGrua() {
        return iNplacaGrua;
    }

    /**
     * Define el valor de la propiedad iNplacaGrua.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINplacaGrua(JAXBElement<String> value) {
        this.iNplacaGrua = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtarjetaOperacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtarjetaOperacion() {
        return iNtarjetaOperacion;
    }

    /**
     * Define el valor de la propiedad iNtarjetaOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtarjetaOperacion(JAXBElement<String> value) {
        this.iNtarjetaOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad iNteleTestigo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINteleTestigo() {
        return iNteleTestigo;
    }

    /**
     * Define el valor de la propiedad iNteleTestigo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINteleTestigo(JAXBElement<String> value) {
        this.iNteleTestigo = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtelefonoInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtelefonoInfractor() {
        return iNtelefonoInfractor;
    }

    /**
     * Define el valor de la propiedad iNtelefonoInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtelefonoInfractor(JAXBElement<String> value) {
        this.iNtelefonoInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoC.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoC() {
        return iNtipoC;
    }

    /**
     * Define el valor de la propiedad iNtipoC.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoC(JAXBElement<String> value) {
        this.iNtipoC = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoD.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoD() {
        return iNtipoD;
    }

    /**
     * Define el valor de la propiedad iNtipoD.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoD(JAXBElement<String> value) {
        this.iNtipoD = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoInfractor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoInfractor() {
        return iNtipoInfractor;
    }

    /**
     * Define el valor de la propiedad iNtipoInfractor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoInfractor(JAXBElement<String> value) {
        this.iNtipoInfractor = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoS() {
        return iNtipoS;
    }

    /**
     * Define el valor de la propiedad iNtipoS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoS(JAXBElement<String> value) {
        this.iNtipoS = value;
    }

    /**
     * Obtiene el valor de la propiedad iNtipoV.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINtipoV() {
        return iNtipoV;
    }

    /**
     * Define el valor de la propiedad iNtipoV.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINtipoV(JAXBElement<String> value) {
        this.iNtipoV = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorAdi.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorAdi() {
        return iNvalorAdi;
    }

    /**
     * Define el valor de la propiedad iNvalorAdi.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorAdi(JAXBElement<String> value) {
        this.iNvalorAdi = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorComp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorComp() {
        return iNvalorComp;
    }

    /**
     * Define el valor de la propiedad iNvalorComp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorComp(JAXBElement<String> value) {
        this.iNvalorComp = value;
    }

    /**
     * Obtiene el valor de la propiedad iNvalorInf1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getINvalorInf1() {
        return iNvalorInf1;
    }

    /**
     * Define el valor de la propiedad iNvalorInf1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setINvalorInf1(JAXBElement<String> value) {
        this.iNvalorInf1 = value;
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
