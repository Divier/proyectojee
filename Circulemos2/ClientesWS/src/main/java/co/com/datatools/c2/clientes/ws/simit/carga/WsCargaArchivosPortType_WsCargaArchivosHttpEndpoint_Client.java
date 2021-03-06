package co.com.datatools.c2.clientes.ws.simit.carga;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.7.13 2016-03-28T16:24:10.943-05:00 Generated source version: 2.7.13
 * 
 */
public final class WsCargaArchivosPortType_WsCargaArchivosHttpEndpoint_Client {

    private static final QName SERVICE_NAME = new QName("http://webservices.qxmultas.quipux.com.co", "WsCargaArchivos");

    private WsCargaArchivosPortType_WsCargaArchivosHttpEndpoint_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = WsCargaArchivos.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        WsCargaArchivos ss = new WsCargaArchivos(wsdlURL, SERVICE_NAME);
        WsCargaArchivosPortType port = ss.getWsCargaArchivosHttpEndpoint();

        {
            System.out.println("Invoking cargaRecaudo...");
            co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosRecaudo _cargaRecaudo_recaudo = new co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosRecaudo();
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoClave = null;
            _cargaRecaudo_recaudo.setClave(_cargaRecaudo_recaudoClave);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoCuenta = null;
            _cargaRecaudo_recaudo.setCuenta(_cargaRecaudo_recaudoCuenta);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINIdTipoDocumento = null;
            _cargaRecaudo_recaudo.setINIdTipoDocumento(_cargaRecaudo_recaudoINIdTipoDocumento);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINNumeroCuota = null;
            _cargaRecaudo_recaudo.setINNumeroCuota(_cargaRecaudo_recaudoINNumeroCuota);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINcodigoCO = null;
            _cargaRecaudo_recaudo.setINcodigoCO(_cargaRecaudo_recaudoINcodigoCO);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINconsecutivoRecaudo = null;
            _cargaRecaudo_recaudo.setINconsecutivoRecaudo(_cargaRecaudo_recaudoINconsecutivoRecaudo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINdescripcionOrigen = null;
            _cargaRecaudo_recaudo.setINdescripcionOrigen(_cargaRecaudo_recaudoINdescripcionOrigen);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINestadoRecaudoPolca = null;
            _cargaRecaudo_recaudo.setINestadoRecaudoPolca(_cargaRecaudo_recaudoINestadoRecaudoPolca);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINfechaContable = null;
            _cargaRecaudo_recaudo.setINfechaContable(_cargaRecaudo_recaudoINfechaContable);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINfechaTransaccion = null;
            _cargaRecaudo_recaudo.setINfechaTransaccion(_cargaRecaudo_recaudoINfechaTransaccion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINhora = null;
            _cargaRecaudo_recaudo.setINhora(_cargaRecaudo_recaudoINhora);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINidentificacion = null;
            _cargaRecaudo_recaudo.setINidentificacion(_cargaRecaudo_recaudoINidentificacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINnumeroConsignacion = null;
            _cargaRecaudo_recaudo.setINnumeroConsignacion(_cargaRecaudo_recaudoINnumeroConsignacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINnumeroReferencia = null;
            _cargaRecaudo_recaudo.setINnumeroReferencia(_cargaRecaudo_recaudoINnumeroReferencia);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINsecretaria = null;
            _cargaRecaudo_recaudo.setINsecretaria(_cargaRecaudo_recaudoINsecretaria);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINtipoRecaudoReferencia = null;
            _cargaRecaudo_recaudo.setINtipoRecaudoReferencia(_cargaRecaudo_recaudoINtipoRecaudoReferencia);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINvalorCheque = null;
            _cargaRecaudo_recaudo.setINvalorCheque(_cargaRecaudo_recaudoINvalorCheque);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINvalorEfectivo = null;
            _cargaRecaudo_recaudo.setINvalorEfectivo(_cargaRecaudo_recaudoINvalorEfectivo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoINvalorTotal = null;
            _cargaRecaudo_recaudo.setINvalorTotal(_cargaRecaudo_recaudoINvalorTotal);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoSecretaria = null;
            _cargaRecaudo_recaudo.setSecretaria(_cargaRecaudo_recaudoSecretaria);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaRecaudo_recaudoUsuario = null;
            _cargaRecaudo_recaudo.setUsuario(_cargaRecaudo_recaudoUsuario);
            try {
                java.lang.String _cargaRecaudo__return = port.cargaRecaudo(_cargaRecaudo_recaudo);
                System.out.println("cargaRecaudo.result=" + _cargaRecaudo__return);

            } catch (Exception_Exception e) {
                System.out.println("Expected exception: Exception has occurred.");
                System.out.println(e.toString());
            }
        }
        {
            System.out.println("Invoking cargaArchivoResolucion...");
            co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosResolucion _cargaArchivoResolucion_resolucion = new co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosResolucion();
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionApellido = null;
            _cargaArchivoResolucion_resolucion.setApellido(_cargaArchivoResolucion_resolucionApellido);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionClave = null;
            _cargaArchivoResolucion_resolucion.setClave(_cargaArchivoResolucion_resolucionClave);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionCodigoI1 = null;
            _cargaArchivoResolucion_resolucion.setCodigoI1(_cargaArchivoResolucion_resolucionCodigoI1);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionCodigoOrganismo = null;
            _cargaArchivoResolucion_resolucion.setCodigoOrganismo(_cargaArchivoResolucion_resolucionCodigoOrganismo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionConsecutivoR = null;
            _cargaArchivoResolucion_resolucion.setConsecutivoR(_cargaArchivoResolucion_resolucionConsecutivoR);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionDireccion = null;
            _cargaArchivoResolucion_resolucion.setDireccion(_cargaArchivoResolucion_resolucionDireccion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionEstadoResolucionPolca = null;
            _cargaArchivoResolucion_resolucion
                    .setEstadoResolucionPolca(_cargaArchivoResolucion_resolucionEstadoResolucionPolca);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionFechaComparendo = null;
            _cargaArchivoResolucion_resolucion.setFechaComparendo(_cargaArchivoResolucion_resolucionFechaComparendo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionFechaHasta = null;
            _cargaArchivoResolucion_resolucion.setFechaHasta(_cargaArchivoResolucion_resolucionFechaHasta);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionFechaResolucion = null;
            _cargaArchivoResolucion_resolucion.setFechaResolucion(_cargaArchivoResolucion_resolucionFechaResolucion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionFotoMulta = null;
            _cargaArchivoResolucion_resolucion.setFotoMulta(_cargaArchivoResolucion_resolucionFotoMulta);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionGradoAlcohol = null;
            _cargaArchivoResolucion_resolucion.setGradoAlcohol(_cargaArchivoResolucion_resolucionGradoAlcohol);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionHorasComunitarias = null;
            _cargaArchivoResolucion_resolucion
                    .setHorasComunitarias(_cargaArchivoResolucion_resolucionHorasComunitarias);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionIdCiudad = null;
            _cargaArchivoResolucion_resolucion.setIdCiudad(_cargaArchivoResolucion_resolucionIdCiudad);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionIdContraventor = null;
            _cargaArchivoResolucion_resolucion.setIdContraventor(_cargaArchivoResolucion_resolucionIdContraventor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionNombre = null;
            _cargaArchivoResolucion_resolucion.setNombre(_cargaArchivoResolucion_resolucionNombre);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionNumeroComparendo = null;
            _cargaArchivoResolucion_resolucion.setNumeroComparendo(_cargaArchivoResolucion_resolucionNumeroComparendo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionNumeroResolucion = null;
            _cargaArchivoResolucion_resolucion.setNumeroResolucion(_cargaArchivoResolucion_resolucionNumeroResolucion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionNumeroResolucionAnterior = null;
            _cargaArchivoResolucion_resolucion
                    .setNumeroResolucionAnterior(_cargaArchivoResolucion_resolucionNumeroResolucionAnterior);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionSecretaria = null;
            _cargaArchivoResolucion_resolucion.setSecretaria(_cargaArchivoResolucion_resolucionSecretaria);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionTelefono = null;
            _cargaArchivoResolucion_resolucion.setTelefono(_cargaArchivoResolucion_resolucionTelefono);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionTipoD = null;
            _cargaArchivoResolucion_resolucion.setTipoD(_cargaArchivoResolucion_resolucionTipoD);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionTipoR = null;
            _cargaArchivoResolucion_resolucion.setTipoR(_cargaArchivoResolucion_resolucionTipoR);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionUsuario = null;
            _cargaArchivoResolucion_resolucion.setUsuario(_cargaArchivoResolucion_resolucionUsuario);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionValorAdicionalResolucion = null;
            _cargaArchivoResolucion_resolucion
                    .setValorAdicionalResolucion(_cargaArchivoResolucion_resolucionValorAdicionalResolucion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionValorInfraccionResolucion1 = null;
            _cargaArchivoResolucion_resolucion
                    .setValorInfraccionResolucion1(_cargaArchivoResolucion_resolucionValorInfraccionResolucion1);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionValorPagarInfraccionResolucion1 = null;
            _cargaArchivoResolucion_resolucion
                    .setValorPagarInfraccionResolucion1(_cargaArchivoResolucion_resolucionValorPagarInfraccionResolucion1);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoResolucion_resolucionValorTotalResolucion = null;
            _cargaArchivoResolucion_resolucion
                    .setValorTotalResolucion(_cargaArchivoResolucion_resolucionValorTotalResolucion);
            try {
                java.lang.String _cargaArchivoResolucion__return = port
                        .cargaArchivoResolucion(_cargaArchivoResolucion_resolucion);
                System.out.println("cargaArchivoResolucion.result=" + _cargaArchivoResolucion__return);

            } catch (Exception_Exception e) {
                System.out.println("Expected exception: Exception has occurred.");
                System.out.println(e.toString());
            }
        }
        {
            System.out.println("Invoking cargaArchivoComparendos...");
            co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo _cargaArchivoComparendos_comparendo = new co.com.datatools.c2.clientes.ws.simit.carga.dto.DatosComparendo();
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoClave = null;
            _cargaArchivoComparendos_comparendo.setClave(_cargaArchivoComparendos_comparendoClave);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINaccidente = null;
            _cargaArchivoComparendos_comparendo.setINaccidente(_cargaArchivoComparendos_comparendoINaccidente);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINapellidoInfractor = null;
            _cargaArchivoComparendos_comparendo
                    .setINapellidoInfractor(_cargaArchivoComparendos_comparendoINapellidoInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINciudadInfractor = null;
            _cargaArchivoComparendos_comparendo
                    .setINciudadInfractor(_cargaArchivoComparendos_comparendoINciudadInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINcodigoInfraccion1 = null;
            _cargaArchivoComparendos_comparendo
                    .setINcodigoInfraccion1(_cargaArchivoComparendos_comparendoINcodigoInfraccion1);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINcodigoMo = null;
            _cargaArchivoComparendos_comparendo.setINcodigoMo(_cargaArchivoComparendos_comparendoINcodigoMo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINcodigoPa = null;
            _cargaArchivoComparendos_comparendo.setINcodigoPa(_cargaArchivoComparendos_comparendoINcodigoPa);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINcodigoRa = null;
            _cargaArchivoComparendos_comparendo.setINcodigoRa(_cargaArchivoComparendos_comparendoINcodigoRa);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINcomparendoElectronico = null;
            _cargaArchivoComparendos_comparendo
                    .setINcomparendoElectronico(_cargaArchivoComparendos_comparendoINcomparendoElectronico);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINconsecutivoComparendo = null;
            _cargaArchivoComparendos_comparendo
                    .setINconsecutivoComparendo(_cargaArchivoComparendos_comparendoINconsecutivoComparendo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINconsecutivoIn = null;
            _cargaArchivoComparendos_comparendo.setINconsecutivoIn(_cargaArchivoComparendos_comparendoINconsecutivoIn);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdirPatioInmovi = null;
            _cargaArchivoComparendos_comparendo
                    .setINdirPatioInmovi(_cargaArchivoComparendos_comparendoINdirPatioInmovi);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdirecTesti = null;
            _cargaArchivoComparendos_comparendo.setINdirecTesti(_cargaArchivoComparendos_comparendoINdirecTesti);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdireccion = null;
            _cargaArchivoComparendos_comparendo.setINdireccion(_cargaArchivoComparendos_comparendoINdireccion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdireccionInfractor = null;
            _cargaArchivoComparendos_comparendo
                    .setINdireccionInfractor(_cargaArchivoComparendos_comparendoINdireccionInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdivipoLicencia = null;
            _cargaArchivoComparendos_comparendo
                    .setINdivipoLicencia(_cargaArchivoComparendos_comparendoINdivipoLicencia);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdivipoMatricula = null;
            _cargaArchivoComparendos_comparendo
                    .setINdivipoMatricula(_cargaArchivoComparendos_comparendoINdivipoMatricula);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINdivipoMunicipio = null;
            _cargaArchivoComparendos_comparendo
                    .setINdivipoMunicipio(_cargaArchivoComparendos_comparendoINdivipoMunicipio);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINedadIn = null;
            _cargaArchivoComparendos_comparendo.setINedadIn(_cargaArchivoComparendos_comparendoINedadIn);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINemail = null;
            _cargaArchivoComparendos_comparendo.setINemail(_cargaArchivoComparendos_comparendoINemail);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINestado = null;
            _cargaArchivoComparendos_comparendo.setINestado(_cargaArchivoComparendos_comparendoINestado);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINestadoComparendoPolca = null;
            _cargaArchivoComparendos_comparendo
                    .setINestadoComparendoPolca(_cargaArchivoComparendos_comparendoINestadoComparendoPolca);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINfecha = null;
            _cargaArchivoComparendos_comparendo.setINfecha(_cargaArchivoComparendos_comparendoINfecha);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINfechaNotificacion = null;
            _cargaArchivoComparendos_comparendo
                    .setINfechaNotificacion(_cargaArchivoComparendos_comparendoINfechaNotificacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINfechaVence = null;
            _cargaArchivoComparendos_comparendo.setINfechaVence(_cargaArchivoComparendos_comparendoINfechaVence);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINfuga = null;
            _cargaArchivoComparendos_comparendo.setINfuga(_cargaArchivoComparendos_comparendoINfuga);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINgradoAlcohol = null;
            _cargaArchivoComparendos_comparendo.setINgradoAlcohol(_cargaArchivoComparendos_comparendoINgradoAlcohol);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINgruaNumero = null;
            _cargaArchivoComparendos_comparendo.setINgruaNumero(_cargaArchivoComparendos_comparendoINgruaNumero);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINhora = null;
            _cargaArchivoComparendos_comparendo.setINhora(_cargaArchivoComparendos_comparendoINhora);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINidInfractor = null;
            _cargaArchivoComparendos_comparendo.setINidInfractor(_cargaArchivoComparendos_comparendoINidInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINidSecretariaExpide = null;
            _cargaArchivoComparendos_comparendo
                    .setINidSecretariaExpide(_cargaArchivoComparendos_comparendoINidSecretariaExpide);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINidTipoDocP = null;
            _cargaArchivoComparendos_comparendo.setINidTipoDocP(_cargaArchivoComparendos_comparendoINidTipoDocP);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINidentificacion = null;
            _cargaArchivoComparendos_comparendo
                    .setINidentificacion(_cargaArchivoComparendos_comparendoINidentificacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINidentificacionTestigo = null;
            _cargaArchivoComparendos_comparendo
                    .setINidentificacionTestigo(_cargaArchivoComparendos_comparendoINidentificacionTestigo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINinmovilizacion = null;
            _cargaArchivoComparendos_comparendo
                    .setINinmovilizacion(_cargaArchivoComparendos_comparendoINinmovilizacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINlicenciaConduccion = null;
            _cargaArchivoComparendos_comparendo
                    .setINlicenciaConduccion(_cargaArchivoComparendos_comparendoINlicenciaConduccion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINlicenciaTransito = null;
            _cargaArchivoComparendos_comparendo
                    .setINlicenciaTransito(_cargaArchivoComparendos_comparendoINlicenciaTransito);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINlocalidadComuna = null;
            _cargaArchivoComparendos_comparendo
                    .setINlocalidadComuna(_cargaArchivoComparendos_comparendoINlocalidadComuna);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnitEmpresa = null;
            _cargaArchivoComparendos_comparendo.setINnitEmpresa(_cargaArchivoComparendos_comparendoINnitEmpresa);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnombreEmpresa = null;
            _cargaArchivoComparendos_comparendo.setINnombreEmpresa(_cargaArchivoComparendos_comparendoINnombreEmpresa);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnombreInfractor = null;
            _cargaArchivoComparendos_comparendo
                    .setINnombreInfractor(_cargaArchivoComparendos_comparendoINnombreInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnombreProp = null;
            _cargaArchivoComparendos_comparendo.setINnombreProp(_cargaArchivoComparendos_comparendoINnombreProp);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnombreTestigo = null;
            _cargaArchivoComparendos_comparendo.setINnombreTestigo(_cargaArchivoComparendos_comparendoINnombreTestigo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINnumeroComparendo = null;
            _cargaArchivoComparendos_comparendo
                    .setINnumeroComparendo(_cargaArchivoComparendos_comparendoINnumeroComparendo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINobservaciones = null;
            _cargaArchivoComparendos_comparendo.setINobservaciones(_cargaArchivoComparendos_comparendoINobservaciones);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINorganismoTransito = null;
            _cargaArchivoComparendos_comparendo
                    .setINorganismoTransito(_cargaArchivoComparendos_comparendoINorganismoTransito);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINpatioInmoviliza = null;
            _cargaArchivoComparendos_comparendo
                    .setINpatioInmoviliza(_cargaArchivoComparendos_comparendoINpatioInmoviliza);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINplaca = null;
            _cargaArchivoComparendos_comparendo.setINplaca(_cargaArchivoComparendos_comparendoINplaca);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINplacaAgente = null;
            _cargaArchivoComparendos_comparendo.setINplacaAgente(_cargaArchivoComparendos_comparendoINplacaAgente);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINplacaGrua = null;
            _cargaArchivoComparendos_comparendo.setINplacaGrua(_cargaArchivoComparendos_comparendoINplacaGrua);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtarjetaOperacion = null;
            _cargaArchivoComparendos_comparendo
                    .setINtarjetaOperacion(_cargaArchivoComparendos_comparendoINtarjetaOperacion);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINteleTestigo = null;
            _cargaArchivoComparendos_comparendo.setINteleTestigo(_cargaArchivoComparendos_comparendoINteleTestigo);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtelefonoInfractor = null;
            _cargaArchivoComparendos_comparendo
                    .setINtelefonoInfractor(_cargaArchivoComparendos_comparendoINtelefonoInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtipoC = null;
            _cargaArchivoComparendos_comparendo.setINtipoC(_cargaArchivoComparendos_comparendoINtipoC);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtipoD = null;
            _cargaArchivoComparendos_comparendo.setINtipoD(_cargaArchivoComparendos_comparendoINtipoD);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtipoInfractor = null;
            _cargaArchivoComparendos_comparendo.setINtipoInfractor(_cargaArchivoComparendos_comparendoINtipoInfractor);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtipoS = null;
            _cargaArchivoComparendos_comparendo.setINtipoS(_cargaArchivoComparendos_comparendoINtipoS);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINtipoV = null;
            _cargaArchivoComparendos_comparendo.setINtipoV(_cargaArchivoComparendos_comparendoINtipoV);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINvalorAdi = null;
            _cargaArchivoComparendos_comparendo.setINvalorAdi(_cargaArchivoComparendos_comparendoINvalorAdi);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINvalorComp = null;
            _cargaArchivoComparendos_comparendo.setINvalorComp(_cargaArchivoComparendos_comparendoINvalorComp);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoINvalorInf1 = null;
            _cargaArchivoComparendos_comparendo.setINvalorInf1(_cargaArchivoComparendos_comparendoINvalorInf1);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoSecretaria = null;
            _cargaArchivoComparendos_comparendo.setSecretaria(_cargaArchivoComparendos_comparendoSecretaria);
            javax.xml.bind.JAXBElement<java.lang.String> _cargaArchivoComparendos_comparendoUsuario = null;
            _cargaArchivoComparendos_comparendo.setUsuario(_cargaArchivoComparendos_comparendoUsuario);
            try {
                java.lang.String _cargaArchivoComparendos__return = port
                        .cargaArchivoComparendos(_cargaArchivoComparendos_comparendo);
                System.out.println("cargaArchivoComparendos.result=" + _cargaArchivoComparendos__return);

            } catch (Exception_Exception e) {
                System.out.println("Expected exception: Exception has occurred.");
                System.out.println(e.toString());
            }
        }

        System.exit(0);
    }

}
