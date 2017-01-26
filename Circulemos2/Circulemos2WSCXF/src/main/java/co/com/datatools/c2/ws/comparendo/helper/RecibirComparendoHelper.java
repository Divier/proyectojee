package co.com.datatools.c2.ws.comparendo.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.DireccionWSDTO;
import co.com.datatools.c2.dto.ws.EvidenciaWSDTO;
import co.com.datatools.c2.dto.ws.PersonaWSDTO;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;

/**
 * Clase auxiliar para procesar objetos {@link ComparendoWSDTO} en otro dto similar
 * 
 * @author luis.forero(2015-11-12)
 */
public class RecibirComparendoHelper {

    private static OrganismoTransitoDTO construirOrganismo(Integer codigoOrganismoTransito) {
        OrganismoTransitoDTO orgTransito = new OrganismoTransitoDTO();
        orgTransito.setCodigoOrganismo(codigoOrganismoTransito);
        return orgTransito;
    }

    private static String clStr(String string) {
        if (string != null) {
            return StringUtils.trimToNull(string);
        }
        return string;
    }

    private static Date getDateXMLGregorianCalendar(XMLGregorianCalendar fecha) {
        Date fechaD = null;
        if (fecha != null) {
            fechaD = fecha.toGregorianCalendar().getTime();
        }
        return fechaD;
    }

    /**
     * Helper para procesamiento de la clase {@link ComparendoWSDTO} en un {@link ProcesaComparendoDTO}
     * 
     * @author felipe.martinez
     */
    public static class ToProcesaComparendoDto {
        public static ProcesarComparendoDTO convertComparendoWs(ComparendoWSDTO pCompWs) {
            // ENCABEZADO
            ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
            procesarComparendoDTO.setEnumProcesamiento(
                    Utilidades.buscarElemEnum(EnumProcesamiento.class, pCompWs.getCodigoOrigenValidacion()));

            ProcesaComparendoDTO pcDTO = new ProcesaComparendoDTO();
            pcDTO.setFechaRecepcion(new Date());
            pcDTO.setOrganismoTransito(construirOrganismo(pCompWs.getCodigoOrganismoTransito()));
            pcDTO.setIdFacturaAxis(pCompWs.getNumeroFactura());
            // Numero de citación
            if (pCompWs.getNumeroCitacion() != null) {
                pcDTO.setNumeroCitacion(pCompWs.getNumeroCitacion().toString());
            }
            pcDTO.setFechaInfraccion(getDateXMLGregorianCalendar(pCompWs.getFechaImposicion()));
            pcDTO.setHoraInfraccion(getDateXMLGregorianCalendar(pCompWs.getHoraImposicion()));
            pcDTO.setExisteFugaInfractor(pCompWs.getExisteFugaInfractor());
            pcDTO.setCodigoInfraccion(pCompWs.getCodigoInfraccion());

            pcDTO.setVelocidadVehiculo(pCompWs.getVelocidadVehiculo());

            pcDTO.setProcesaDireccionComparendo(procesarDireccionWs(pCompWs.getDireccionInfraccion()));

            pcDTO.setCodigoOrigen(pCompWs.getCodigoOrigen());
            pcDTO.setFechaNotificacion(getDateXMLGregorianCalendar(pCompWs.getFechaNotificacion()));

            // VEHICULO
            pcDTO.setPlacaVehiculo(pCompWs.getPlacaVehiculo());
            pcDTO.setIdentificacionVehiculo(pCompWs.getIdentificacionVehiculo());
            pcDTO.setCodigoOrganismoMatriculaVehiculo(pCompWs.getCodigoOrganismoMatriculaVehiculo());
            pcDTO.setNumeroTarjetaOperacion(pCompWs.getNumeroTarjetaOperacion());
            pcDTO.setNumeroLicenciaTransito(pCompWs.getNumeroLicenciaTransito());

            pcDTO.setProcesaComparendoPersonas(new ArrayList<ProcesaComparendoPersonaDTO>(0));
            // EMPRESA
            ProcesaComparendoPersonaDTO empresaVehiculo = procesarEmpresa(pCompWs.getEmpresa());
            pcDTO.getProcesaComparendoPersonas().add(empresaVehiculo);

            pcDTO.setCodigoOrganismoLicenciaTransito(pCompWs.getCodigoOrgTransLicenciaTransito());

            // INFRACTOR
            ProcesaComparendoPersonaDTO infractor = procesarInfractor(pCompWs.getInfractor());
            if (pCompWs.getInfractor() != null) {
                if (pCompWs.getInfractor() != null) {
                    DireccionWSDTO direccion = pCompWs.getInfractor().getDireccion();
                    if (direccion != null) {
                        infractor.setProcesaDireccion(procesarDireccionWs(direccion));
                    }
                }
            }
            pcDTO.getProcesaComparendoPersonas().add(infractor);
            pcDTO.setObservacionesInfractor(clStr(pCompWs.getObservacionesInfractor()));

            // PROPIETARIO
            ProcesaComparendoPersonaDTO propietario = procesarPropietario(pCompWs.getPropietario());
            pcDTO.getProcesaComparendoPersonas().add(propietario);

            // AGENTE DE TRANSITO
            pcDTO.setPlacaAgente(clStr(pCompWs.getPlacaAgente()));
            pcDTO.setNumeroIdentificacionAgente(clStr(pCompWs.getNumeroIdentificacionAgente()));
            pcDTO.setApellido1Agente(clStr(pCompWs.getApellido1Agente()));
            pcDTO.setApellido2Agente(clStr(pCompWs.getApellido2Agente()));
            pcDTO.setNombre1Agente(clStr(pCompWs.getNombre1Agente()));
            pcDTO.setNombre2Agente(clStr(pCompWs.getNombre2Agente()));
            pcDTO.setObservacionesAgente(clStr(pCompWs.getObservacionesAgente()));

            // INMOVILIZACION
            pcDTO.setInmovilizado(pCompWs.getInmoviliza());
            pcDTO.setNumeroPatio(pCompWs.getNumeroPatio());
            pcDTO.setNombrePatio(clStr(pCompWs.getNombrePatio()));
            pcDTO.setNumeroGrua(pCompWs.getNumeroGrua());
            pcDTO.setPlacaGrua(clStr(pCompWs.getPlacaGrua()));
            pcDTO.setConsecutivoInmovilizacion(clStr(pCompWs.getConsecutivoInmovilizacion()));
            pcDTO.setProcesaDireccionPatio(procesarDireccionWs(pCompWs.getDireccionPatio()));

            // TESTIGO
            ProcesaComparendoPersonaDTO testigo = procesarTestigo(pCompWs.getTestigo());
            if (testigo != null) {
                if (pCompWs.getTestigo() != null) {
                    DireccionWSDTO direccion = pCompWs.getTestigo().getDireccion();
                    if (direccion != null) {
                        testigo.setProcesaDireccion(procesarDireccionWs(direccion));
                    }
                }
            }
            pcDTO.getProcesaComparendoPersonas().add(testigo);

            // EMBRIAGUEZ
            pcDTO.setNiegaPruebaAlcoholemia(pCompWs.getNiegaPruebaAlcoholemia());
            pcDTO.setGradoAlcoholemia(pCompWs.getGradoAlcoholemia());
            pcDTO.setFechaPruebaAlcoholemia(getDateXMLGregorianCalendar(pCompWs.getFechaPruebaAlcoholemia()));
            pcDTO.setNumeroPruebaAlcoholemia(clStr(pCompWs.getNumeroPruebaAlcoholemia()));
            pcDTO.setValorGradoAlcoholemia(pCompWs.getValorGradoAlcoholemia());
            pcDTO.setNumeroReincidencia(pCompWs.getNumeroReincidencia());

            // EVIDENCIAS FISICAS
            pcDTO.setProcesaEvidencias(
                    procesarEvidencias(pCompWs.getEvidencias(), String.valueOf(pCompWs.getNumeroFactura())));

            // VALOR DEL COMPARENDO
            pcDTO.setValorConcepto(pCompWs.getValorConcepto());
            pcDTO.setSecuenciaUnica(pCompWs.getSecuenciaUnica());

            procesarComparendoDTO.setProcesaComparendoDTO(pcDTO);

            return procesarComparendoDTO;
        }

        private static ProcesaDireccionDTO procesarDireccionWs(DireccionWSDTO dirWs) {
            if (dirWs == null) {
                return null;
            }

            ProcesaDireccionDTO dto = new ProcesaDireccionDTO();
            dto.setCodigoTipoViaPrincipal(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
            dto.setComplemento(clStr(dirWs.getDireccionBasica()));
            return dto;

        }

        private static ProcesaComparendoPersonaDTO procesarInfractor(PersonaWSDTO infractorWs) {
            ProcesaComparendoPersonaDTO infractor = new ProcesaComparendoPersonaDTO();
            infractor.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
            if (infractorWs == null) {
                return infractor;
            }
            infractor.setNumeroIdentificacion(clStr(infractorWs.getNumeroIdentificacion()));
            infractor.setApellido1(clStr(infractorWs.getApellido1()));
            infractor.setApellido2(clStr(infractorWs.getApellido2()));
            infractor.setNombre1(clStr(infractorWs.getNombre1()));
            infractor.setNombre2(clStr(infractorWs.getNombre2()));
            infractor.setEmail(clStr(infractorWs.getCorreoElectronico()));
            infractor.setTelefonoFijo(clStr(infractorWs.getTelefonoFijo()));
            infractor.setEdad(infractorWs.getEdad());
            infractor.setProcesaDireccion(procesarDireccionWs(infractorWs.getDireccion()));
            infractor.setCodigoOrganismoLicencia(infractorWs.getCodigoOrganismoLicencia());
            infractor.setNumeroLicencia(clStr(infractorWs.getNumeroLicenciaConduccion()));
            infractor.setFechaExpedicionLicenCondu(
                    getDateXMLGregorianCalendar(infractorWs.getFechaExpedicionLicenciaConduccion()));
            infractor.setFechaVencimientoLicenCondu(
                    getDateXMLGregorianCalendar(infractorWs.getFechaVencimientoLicenciaConduccion()));
            if (infractorWs.getNombreEmpresaRazonSocial() != null) {
                infractor.setRazonSocial(infractorWs.getNombreEmpresaRazonSocial());
            }
            return infractor;
        }

        private static ProcesaComparendoPersonaDTO procesarPropietario(PersonaWSDTO propietarioWs) {
            ProcesaComparendoPersonaDTO propietario = new ProcesaComparendoPersonaDTO();
            propietario.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo());
            if (propietarioWs == null) {
                return propietario;
            }
            propietario.setNumeroIdentificacion(clStr(propietarioWs.getNumeroIdentificacion()));
            propietario.setApellido1(clStr(propietarioWs.getApellido1()));
            propietario.setApellido2(clStr(propietarioWs.getApellido2()));
            propietario.setNombre1(clStr(propietarioWs.getNombre1()));
            propietario.setNombre2(clStr(propietarioWs.getNombre2()));
            propietario.setRazonSocial(clStr(propietarioWs.getNombreEmpresaRazonSocial()));
            return propietario;
        }

        private static ProcesaComparendoPersonaDTO procesarTestigo(PersonaWSDTO testigoWs) {
            ProcesaComparendoPersonaDTO testigo = new ProcesaComparendoPersonaDTO();
            testigo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getCodigo());
            if (testigoWs == null) {
                return testigo;
            }

            testigo.setNumeroIdentificacion(clStr(testigoWs.getNumeroIdentificacion()));
            testigo.setApellido1(clStr(testigoWs.getApellido1()));
            testigo.setApellido2(clStr(testigoWs.getApellido2()));
            testigo.setNombre1(clStr(testigoWs.getNombre1()));
            testigo.setNombre2(clStr(testigoWs.getNombre2()));
            testigo.setProcesaDireccion(procesarDireccionWs(testigoWs.getDireccion()));
            testigo.setTelefonoMovil(clStr(testigoWs.getTelefonoMovil()));
            return testigo;
        }

        private static ProcesaComparendoPersonaDTO procesarEmpresa(PersonaWSDTO empresaWs) {
            ProcesaComparendoPersonaDTO empresa = new ProcesaComparendoPersonaDTO();
            empresa.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo());
            if (empresaWs == null) {
                return empresa;
            }
            empresa.setRazonSocial(clStr(empresaWs.getNombreEmpresaRazonSocial()));
            empresa.setNumeroIdentificacion(clStr(empresaWs.getNumeroIdentificacion()));
            return empresa;
        }

        private static List<ProcesaEvidenciaDTO> procesarEvidencias(EvidenciaWSDTO[] evidenciasWs,
                String numeroComparendo) {
            List<ProcesaEvidenciaDTO> resp = new ArrayList<>(0);
            if (evidenciasWs != null && evidenciasWs.length > 0) {
                resp = new ArrayList<>(evidenciasWs.length);
                ProcesaEvidenciaDTO dto = null;
                for (EvidenciaWSDTO evidenciaWs : evidenciasWs) {
                    if (evidenciaWs != null) {
                        dto = new ProcesaEvidenciaDTO();
                        ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO(
                                evidenciaWs.getNombre(), evidenciaWs.getArchivo());
                        dto.setArchivoTransportable(archivoTransportableDTO);
                        resp.add(dto);
                    }
                }
            }
            return resp;
        }
    }

}
