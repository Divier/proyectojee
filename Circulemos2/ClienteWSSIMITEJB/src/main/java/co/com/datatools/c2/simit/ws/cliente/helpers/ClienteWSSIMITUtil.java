package co.com.datatools.c2.simit.ws.cliente.helpers;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo;
import co.com.datatools.c2.clientes.ws.simit.carga.dto.ObjectFactory;
import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.common.HistoricoComparendoDTO;
import co.com.datatools.c2.dto.common.RespuestaConsultaComparendoDTO;

/**
 * Utilidades utilizadas en el cliente de SIMIT
 * 
 * @author luis.forero(2016-04-08)
 * 
 */
public class ClienteWSSIMITUtil {

    public ClienteWSSIMITUtil() {
    }

    /**
     * Transforma una homologación de comprendo SIMIT al tipo de dato enviado en el Web Service
     * 
     * @param comparendo
     *            comparendo con los datos a enviar
     * @return Datos del comparendo a ser enviado
     * @author luis.forero(2016-04-06)
     */
    public static DatosComparendo homologacionCompDTOToDatosComparendoSIMITDTO(HomologacionComparendoSIMITDTO comparendo) {
        ObjectFactory objectFactory = new ObjectFactory();
        DatosComparendo datosComparendo = new DatosComparendo();
        JAXBElement<java.lang.String> comparendoClave = objectFactory.createDatosComparendoClave(comparendo
                .getReportaAccidente());
        datosComparendo.setClave(comparendoClave);
        JAXBElement<java.lang.String> comparendoINaccidente = objectFactory.createDatosComparendoINaccidente(comparendo
                .getReportaAccidente());
        datosComparendo.setINaccidente(comparendoINaccidente);
        JAXBElement<java.lang.String> comparendoINapellidoInfractor = objectFactory
                .createDatosComparendoINapellidoInfractor(comparendo.getApellidosInfractor());
        datosComparendo.setINapellidoInfractor(comparendoINapellidoInfractor);
        JAXBElement<java.lang.String> comparendoINciudadInfractor = objectFactory
                .createDatosComparendoINciudadInfractor(comparendo.getCiudadResidenciaInfractor());
        datosComparendo.setINciudadInfractor(comparendoINciudadInfractor);
        JAXBElement<java.lang.String> comparendoINcodigoInfraccion1 = objectFactory
                .createDatosComparendoINcodigoInfraccion1(comparendo.getCodigoInfraccion());
        datosComparendo.setINcodigoInfraccion1(comparendoINcodigoInfraccion1);
        JAXBElement<java.lang.String> comparendoINcodigoMo = objectFactory.createDatosComparendoINcodigoMo(comparendo
                .getModalidadTransporte());
        datosComparendo.setINcodigoMo(comparendoINcodigoMo);
        JAXBElement<java.lang.String> comparendoINcodigoPa = objectFactory.createDatosComparendoINcodigoPa(comparendo
                .getTransportePasajeros());
        datosComparendo.setINcodigoPa(comparendoINcodigoPa);
        JAXBElement<java.lang.String> comparendoINcodigoRa = objectFactory.createDatosComparendoINcodigoRa(comparendo
                .getRadioAccion());
        datosComparendo.setINcodigoRa(comparendoINcodigoRa);
        JAXBElement<java.lang.String> comparendoINcomparendoElectronico = objectFactory
                .createDatosComparendoINcomparendoElectronico(comparendo.getTipoComparendo());
        datosComparendo.setINcomparendoElectronico(comparendoINcomparendoElectronico);
        JAXBElement<java.lang.String> comparendoINconsecutivoComparendo = objectFactory
                .createDatosComparendoINconsecutivoComparendo(comparendo.getConsecutivo());
        datosComparendo.setINconsecutivoComparendo(comparendoINconsecutivoComparendo);
        JAXBElement<java.lang.String> comparendoINconsecutivoIn = objectFactory
                .createDatosComparendoINconsecutivoIn(comparendo.getConsecutivoInmovilizacion());
        datosComparendo.setINconsecutivoIn(comparendoINconsecutivoIn);
        JAXBElement<java.lang.String> comparendoINdirPatioInmovi = objectFactory
                .createDatosComparendoINdirPatioInmovi(comparendo.getDireccionPatioInmovilizacion());
        datosComparendo.setINdirPatioInmovi(comparendoINdirPatioInmovi);
        JAXBElement<java.lang.String> comparendoINdirecTesti = objectFactory
                .createDatosComparendoINdirecTesti(comparendo.getDireccionTestigo());
        datosComparendo.setINdirecTesti(comparendoINdirecTesti);
        JAXBElement<java.lang.String> comparendoINdireccion = objectFactory.createDatosComparendoINdireccion(comparendo
                .getDireccionLugarInfraccion());
        datosComparendo.setINdireccion(comparendoINdireccion);
        JAXBElement<java.lang.String> comparendoINdireccionInfractor = objectFactory
                .createDatosComparendoINdireccionInfractor(comparendo.getDireccionInfractor());
        datosComparendo.setINdireccionInfractor(comparendoINdireccionInfractor);
        JAXBElement<java.lang.String> comparendoINdivipoLicencia = objectFactory
                .createDatosComparendoINdivipoLicencia(comparendo.getOrganismoLicenciaTransito());
        datosComparendo.setINdivipoLicencia(comparendoINdivipoLicencia);
        JAXBElement<java.lang.String> comparendoINdivipoMatricula = objectFactory
                .createDatosComparendoINdivipoMatricula(comparendo.getMunicipio());
        datosComparendo.setINdivipoMatricula(comparendoINdivipoMatricula);
        JAXBElement<java.lang.String> comparendoINdivipoMunicipio = objectFactory
                .createDatosComparendoINdivipoMunicipio(comparendo.getMunicipio());
        datosComparendo.setINdivipoMunicipio(comparendoINdivipoMunicipio);
        JAXBElement<java.lang.String> comparendoINedadIn = objectFactory.createDatosComparendoINedadIn(comparendo
                .getEdadInfractor());
        datosComparendo.setINedadIn(comparendoINedadIn);
        JAXBElement<java.lang.String> comparendoINemail = objectFactory.createDatosComparendoINemail(comparendo
                .getCorreoElectronicoInfractor());
        datosComparendo.setINemail(comparendoINemail);
        JAXBElement<java.lang.String> comparendoINestado = objectFactory.createDatosComparendoINestado(comparendo
                .getEstadoComparendo());
        datosComparendo.setINestado(comparendoINestado);
        JAXBElement<java.lang.String> comparendoINestadoComparendoPolca = objectFactory
                .createDatosComparendoINestadoComparendoPolca(comparendo.getPolca());
        datosComparendo.setINestadoComparendoPolca(comparendoINestadoComparendoPolca);
        JAXBElement<java.lang.String> comparendoINfecha = objectFactory.createDatosComparendoINfecha(comparendo
                .getFechaImposicionComparendo());
        datosComparendo.setINfecha(comparendoINfecha);
        JAXBElement<java.lang.String> comparendoINfechaNotificacion = objectFactory
                .createDatosComparendoINfechaNotificacion(comparendo.getFechaNotificacion());
        datosComparendo.setINfechaNotificacion(comparendoINfechaNotificacion);
        JAXBElement<java.lang.String> comparendoINfechaVence = objectFactory
                .createDatosComparendoINfechaVence(comparendo.getFechaVencimientoLicenciaConduccionInfractor());
        datosComparendo.setINfechaVence(comparendoINfechaVence);
        JAXBElement<java.lang.String> comparendoINfuga = objectFactory.createDatosComparendoINfuga(comparendo
                .getExisteFugaInfractor());
        datosComparendo.setINfuga(comparendoINfuga);
        JAXBElement<java.lang.String> comparendoINgradoAlcohol = objectFactory
                .createDatosComparendoINgradoAlcohol(comparendo.getGradoAlcoholemia());
        datosComparendo.setINgradoAlcohol(comparendoINgradoAlcohol);
        JAXBElement<java.lang.String> comparendoINgruaNumero = objectFactory
                .createDatosComparendoINgruaNumero(comparendo.getNumeroGrua());
        datosComparendo.setINgruaNumero(comparendoINgruaNumero);
        JAXBElement<java.lang.String> comparendoINhora = objectFactory.createDatosComparendoINhora(comparendo
                .getHoraImposicionComparendo());
        datosComparendo.setINhora(comparendoINhora);
        JAXBElement<java.lang.String> comparendoINidInfractor = objectFactory
                .createDatosComparendoINidInfractor(comparendo.getNumeroDocumentoInfractor());
        datosComparendo.setINidInfractor(comparendoINidInfractor);
        JAXBElement<java.lang.String> comparendoINidSecretariaExpide = objectFactory
                .createDatosComparendoINidSecretariaExpide(comparendo.getOrganismoExpideLicencia());
        datosComparendo.setINidSecretariaExpide(comparendoINidSecretariaExpide);
        JAXBElement<java.lang.String> comparendoINidTipoDocP = objectFactory
                .createDatosComparendoINidTipoDocP(comparendo.getTipoDocumentoPropietario());
        datosComparendo.setINidTipoDocP(comparendoINidTipoDocP);
        JAXBElement<java.lang.String> comparendoINidentificacion = objectFactory
                .createDatosComparendoINidentificacion(comparendo.getNumeroDocumentoPropietario());
        datosComparendo.setINidentificacion(comparendoINidentificacion);
        JAXBElement<java.lang.String> comparendoINidentificacionTestigo = objectFactory
                .createDatosComparendoINidentificacionTestigo(comparendo.getNumeroDocumentoTestigo());
        datosComparendo.setINidentificacionTestigo(comparendoINidentificacionTestigo);
        JAXBElement<java.lang.String> comparendoINinmovilizacion = objectFactory
                .createDatosComparendoINinmovilizacion(comparendo.getReportaInmovilizacion());
        datosComparendo.setINinmovilizacion(comparendoINinmovilizacion);
        JAXBElement<java.lang.String> comparendoINlicenciaConduccion = objectFactory
                .createDatosComparendoINlicenciaConduccion(comparendo.getNumeroLicenciaConduccion());
        datosComparendo.setINlicenciaConduccion(comparendoINlicenciaConduccion);
        JAXBElement<java.lang.String> comparendoINlicenciaTransito = objectFactory
                .createDatosComparendoINlicenciaTransito(comparendo.getNumeroLicenciaTransito());
        datosComparendo.setINlicenciaTransito(comparendoINlicenciaTransito);
        JAXBElement<java.lang.String> comparendoINlocalidadComuna = objectFactory
                .createDatosComparendoINlocalidadComuna(comparendo.getLocalidad());
        datosComparendo.setINlocalidadComuna(comparendoINlocalidadComuna);
        JAXBElement<java.lang.String> comparendoINnitEmpresa = objectFactory
                .createDatosComparendoINnitEmpresa(comparendo.getNumeroDocumentoEmpresa());
        datosComparendo.setINnitEmpresa(comparendoINnitEmpresa);
        JAXBElement<java.lang.String> comparendoINnombreEmpresa = objectFactory
                .createDatosComparendoINnombreEmpresa(comparendo.getRazonSocial());
        datosComparendo.setINnombreEmpresa(comparendoINnombreEmpresa);
        JAXBElement<java.lang.String> comparendoINnombreInfractor = objectFactory
                .createDatosComparendoINnombreInfractor(comparendo.getNombresInfractor());
        datosComparendo.setINnombreInfractor(comparendoINnombreInfractor);
        JAXBElement<java.lang.String> comparendoINnombreProp = objectFactory
                .createDatosComparendoINnombreProp(comparendo.getNombresApellidosPropietario());
        datosComparendo.setINnombreProp(comparendoINnombreProp);
        JAXBElement<java.lang.String> comparendoINnombreTestigo = objectFactory
                .createDatosComparendoINnombreTestigo(comparendo.getNombresApellidosTestigo());
        datosComparendo.setINnombreTestigo(comparendoINnombreTestigo);
        JAXBElement<java.lang.String> comparendoINnumeroComparendo = objectFactory
                .createDatosComparendoINnumeroComparendo(comparendo.getNumeroComparendo());
        datosComparendo.setINnumeroComparendo(comparendoINnumeroComparendo);
        JAXBElement<java.lang.String> comparendoINobservaciones = objectFactory
                .createDatosComparendoINobservaciones(comparendo.getObservacionesAgente());
        datosComparendo.setINobservaciones(comparendoINobservaciones);
        JAXBElement<java.lang.String> comparendoINorganismoTransito = objectFactory
                .createDatosComparendoINorganismoTransito(comparendo.getOrganismoReporta());
        datosComparendo.setINorganismoTransito(comparendoINorganismoTransito);
        JAXBElement<java.lang.String> comparendoINpatioInmoviliza = objectFactory
                .createDatosComparendoINpatioInmoviliza(comparendo.getNombrePatio());
        datosComparendo.setINpatioInmoviliza(comparendoINpatioInmoviliza);
        JAXBElement<java.lang.String> comparendoINplaca = objectFactory.createDatosComparendoINplaca(comparendo
                .getPlacaVehiculo());
        datosComparendo.setINplaca(comparendoINplaca);
        JAXBElement<java.lang.String> comparendoINplacaAgente = objectFactory
                .createDatosComparendoINplacaAgente(comparendo.getPlacaAgenteTransito());
        datosComparendo.setINplacaAgente(comparendoINplacaAgente);
        JAXBElement<java.lang.String> comparendoINplacaGrua = objectFactory.createDatosComparendoINplacaGrua(comparendo
                .getPlacaGrua());
        datosComparendo.setINplacaGrua(comparendoINplacaGrua);
        JAXBElement<java.lang.String> comparendoINtarjetaOperacion = objectFactory
                .createDatosComparendoINtarjetaOperacion(comparendo.getNumeroTarjetaOperacion());
        datosComparendo.setINtarjetaOperacion(comparendoINtarjetaOperacion);
        JAXBElement<java.lang.String> comparendoINteleTestigo = objectFactory
                .createDatosComparendoINteleTestigo(comparendo.getNumeroCelularTestigo());
        datosComparendo.setINteleTestigo(comparendoINteleTestigo);
        JAXBElement<java.lang.String> comparendoINtelefonoInfractor = objectFactory
                .createDatosComparendoINtelefonoInfractor(comparendo.getTelefonoInfractor());
        datosComparendo.setINtelefonoInfractor(comparendoINtelefonoInfractor);
        JAXBElement<java.lang.String> comparendoINtipoC = objectFactory.createDatosComparendoINtipoC(comparendo
                .getCategoriaLicenciaConduccion());
        datosComparendo.setINtipoC(comparendoINtipoC);
        JAXBElement<java.lang.String> comparendoINtipoD = objectFactory.createDatosComparendoINtipoD(comparendo
                .getTipoDocumentoInfractor());
        datosComparendo.setINtipoD(comparendoINtipoD);
        JAXBElement<java.lang.String> comparendoINtipoInfractor = objectFactory
                .createDatosComparendoINtipoInfractor(comparendo.getTipoInfractor());
        datosComparendo.setINtipoInfractor(comparendoINtipoInfractor);
        JAXBElement<java.lang.String> comparendoINtipoS = objectFactory.createDatosComparendoINtipoS(comparendo
                .getClaseServicio());
        datosComparendo.setINtipoS(comparendoINtipoS);
        JAXBElement<java.lang.String> comparendoINtipoV = objectFactory.createDatosComparendoINtipoV(comparendo
                .getTipoVehiculo());
        datosComparendo.setINtipoV(comparendoINtipoV);
        JAXBElement<java.lang.String> comparendoINvalorAdi = objectFactory.createDatosComparendoINvalorAdi(comparendo
                .getValoresAdicionales());
        datosComparendo.setINvalorAdi(comparendoINvalorAdi);
        JAXBElement<java.lang.String> comparendoINvalorComp = objectFactory.createDatosComparendoINvalorComp(comparendo
                .getValorComparendo());
        datosComparendo.setINvalorComp(comparendoINvalorComp);
        JAXBElement<java.lang.String> comparendoINvalorInf1 = objectFactory.createDatosComparendoINvalorInf1(comparendo
                .getValorTarifaInfraccion());
        datosComparendo.setINvalorInf1(comparendoINvalorInf1);

        return datosComparendo;
    }

    /**
     * Obtiene los datos de la
     * 
     * @param respuestaConsultaComparendoDTO
     * @param responseWSHistorialConductor
     * @return
     * @throws Exception
     * @author luis.forero(2016-04-18)
     */
    public static RespuestaConsultaComparendoDTO responseToRespuesataConsultaComparendoDTO(
            RespuestaConsultaComparendoDTO respuestaConsultaComparendoDTO, String responseWSHistorialConductor)
            throws Exception {
        String xml = responseWSHistorialConductor;
        if (responseWSHistorialConductor.contains("<![CDATA[")) {
            xml = responseWSHistorialConductor.substring("<![CDATA[".length(),
                    responseWSHistorialConductor.indexOf("]]>"));
        }
        try {
            DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
            DocumentBuilder bldr = fctr.newDocumentBuilder();
            InputSource insrc = new InputSource(new StringReader(xml));
            org.w3c.dom.Document parse = bldr.parse(insrc);

            NodeList nlist = parse.getElementsByTagName("comparendo");
            if (nlist != null && nlist.getLength() > 0) {
                for (int i = 0; i < nlist.getLength(); i++) {
                    Node item = nlist.item(i);
                    if (item.getNodeType() == Node.ELEMENT_NODE
                            && item.getParentNode().getNodeName().equals("historial")) {
                        Element e = (Element) item;
                        HistoricoComparendoDTO comparendoDTO = new HistoricoComparendoDTO();
                        comparendoDTO.setNumeroComparendo(getTagValue("numero", e));
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        comparendoDTO.setFechaImposicion(dateFormat.parse(getTagValue("fecha", e)));
                        comparendoDTO.setNombreInfractor(getTagValue("nombre", e));

                        // Infraccion
                        NodeList lst = e.getElementsByTagName("infraccion");
                        if (lst != null && lst.getLength() > 0) {
                            String tagValue = getTagValue("codigo", (Element) lst.item(0));
                            String[] split = tagValue.split(" ");
                            comparendoDTO.setCodigoInfraccion(tagValue);
                        }

                        // TODO Faltan demas atributos de request
                    }
                }
            }
        } catch (ParserConfigurationException | DOMException | SAXException | IOException ex) {
            throw ex;
        }

        return respuestaConsultaComparendoDTO;
    }

    /**
     * Obtiene de un elemento del documento xml su valor
     * 
     * @param nom
     *            nombre del campo
     * @param e
     *            elemento del documento
     * @return retorna valor del tag o null en caso de no encontrarlo
     * @author luis.forero(2016-04-19)
     */
    private static String getTagValue(String nom, Element e) {
        final NodeList lst = e.getElementsByTagName(nom);
        if (lst.getLength() > 0) {
            final Node nodonumero = (Node) lst.item(0).getChildNodes().item(0);
            String nodeValue = (nodonumero).getNodeValue();
            return nodeValue;
        }
        return null;
    }
}
