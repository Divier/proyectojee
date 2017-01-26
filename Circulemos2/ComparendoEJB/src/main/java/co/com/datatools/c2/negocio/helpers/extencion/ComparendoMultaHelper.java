package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.helpers.enumeracion.EnumTipoEntidades;

/**
 * @author luis.forero(2016-05-06)
 * 
 */
public class ComparendoMultaHelper {

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

    /**
     * Helper para procesamiento de la clase {@link ItMultaDTO} en un {@link ProcesaComparendoDTO}
     * 
     * @author luis.forero
     */
    public static class ToProcesaComparendoDto {

        public static ProcesarComparendoDTO convertComparendoM(ItMultaDTO pMulta) {
            // ENCABEZADO
            ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
            procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);

            ProcesaComparendoDTO pcDTO = new ProcesaComparendoDTO();

            pcDTO.setFechaRecepcion(new Date());
            pcDTO.setOrganismoTransito(construirOrganismo(pMulta.getCodigoOrganismoTransito()));
            pcDTO.setNumeroCitacion(pMulta.getNumeroCitacion());
            pcDTO.setFechaInfraccion(pMulta.getFechaInfraccion());
            if (pMulta.getHoraInfraccion() != null) {
                pcDTO.setHoraInfraccion(pMulta.getHoraInfraccion());
            } else {
                pcDTO.setHoraInfraccion(pMulta.getFechaInfraccion());
            }
            pcDTO.setExisteFugaInfractor(pMulta.getExisteFugaInfractor());
            pcDTO.setCodigoInfraccion(pMulta.getCodigoInfraccion());
            pcDTO.setIdFacturaAxis(pMulta.getIdFacturaAxis());

            pcDTO.setVelocidadVehiculo(pMulta.getVelocidadVehiculo());

            pcDTO.setProcesaDireccionComparendo(procesarDireccionM(pMulta, EnumTipoEntidades.INFRACCION));

            pcDTO.setFechaNotificacion(pMulta.getFechaNotificacion());

            // VEHICULO
            pcDTO.setPlacaVehiculo(pMulta.getPlacaVehiculo());
            pcDTO.setIdentificacionVehiculo(pMulta.getIdentificacionVehiculo());
            pcDTO.setCodigoOrganismoMatriculaVehiculo(pMulta.getCodigoOrganismoMatriVehic());
            pcDTO.setNumeroTarjetaOperacion(pMulta.getNumeroTarjetaOperacion());
            pcDTO.setNumeroLicenciaTransito(pMulta.getNumeroLicenciaTransito());

            pcDTO.setProcesaComparendoPersonas(new ArrayList<ProcesaComparendoPersonaDTO>(0));
            // EMPRESA
            ProcesaComparendoPersonaDTO empresaVehiculo = procesarEmpresa(pMulta);
            pcDTO.getProcesaComparendoPersonas().add(empresaVehiculo);

            pcDTO.setCodigoOrganismoLicenciaTransito(pMulta.getCodigoOrganismoLicenTrans());

            // INFRACTOR
            ProcesaComparendoPersonaDTO infractor = procesarInfractor(pMulta);
            infractor.setProcesaDireccion(procesarDireccionM(pMulta, EnumTipoEntidades.INFRACTOR));
            pcDTO.getProcesaComparendoPersonas().add(infractor);

            // PROPIETARIO
            ProcesaComparendoPersonaDTO propietario = procesarPropietario(pMulta);
            pcDTO.getProcesaComparendoPersonas().add(propietario);

            // AGENTE DE TRANSITO
            pcDTO.setPlacaAgente(clStr(pMulta.getPlacaAgente()));
            // pcDTO.setNumeroIdentificacionAgente(clStr(pMulta.getNumeroIdentificacionAgente()));
            // pcDTO.setApellido1Agente(clStr(pMulta.getApellido1Agente()));
            // pcDTO.setApellido2Agente(clStr(pMulta.getApellido2Agente()));
            // pcDTO.setNombre1Agente(clStr(pMulta.getNombre1Agente()));
            // pcDTO.setNombre2Agente(clStr(pMulta.getNombre2Agente()));
            pcDTO.setObservacionesAgente(clStr(pMulta.getObservacionesAgente()));

            // INMOVILIZACION
            pcDTO.setInmovilizado(pMulta.getInmoviliza());
            pcDTO.setNumeroPatio(pMulta.getNumeroPatio());
            pcDTO.setNombrePatio(clStr(pMulta.getNombrePatio()));
            pcDTO.setNumeroGrua(pMulta.getNumeroGrua());
            pcDTO.setPlacaGrua(clStr(pMulta.getPlacaGrua()));
            pcDTO.setConsecutivoInmovilizacion(clStr(pMulta.getConsecutivoInmovilizacion()));
            pcDTO.setProcesaDireccionPatio(procesarDireccionM(pMulta, EnumTipoEntidades.PATIO));

            // TESTIGO
            ProcesaComparendoPersonaDTO testigo = procesarTestigo(pMulta);
            pcDTO.getProcesaComparendoPersonas().add(testigo);

            // EMBRIAGUEZ
            pcDTO.setNiegaPruebaAlcoholemia(pMulta.getNiegaPruebaAlcoholemia());
            pcDTO.setGradoAlcoholemia(pMulta.getGradoAlcoholemia());
            pcDTO.setFechaPruebaAlcoholemia(pMulta.getFechaPruebaAlcoholemia());
            pcDTO.setNumeroPruebaAlcoholemia(clStr(pMulta.getNumeroPruebaAlcoholemia()));
            pcDTO.setValorGradoAlcoholemia(pMulta.getValorGradoAlcoholemia());
            pcDTO.setNumeroReincidencia(pMulta.getNumeroReincidencia());

            // EVIDENCIAS FISICAS
            pcDTO.setProcesaEvidencias(procesarEvidencias(pMulta));

            // VALOR DEL COMPARENDO
            pcDTO.setValorConcepto(pMulta.getValorConcepto());
            // Secuencia unica
            pcDTO.setSecuenciaUnica(pMulta.getSecuenciaUnica());

            procesarComparendoDTO.setProcesaComparendoDTO(pcDTO);

            return procesarComparendoDTO;
        }

        private static ProcesaDireccionDTO procesarDireccionM(ItMultaDTO dirM, EnumTipoEntidades tipoDireccion) {
            if (dirM == null) {
                return null;
            }

            ProcesaDireccionDTO dto = null;
            switch (tipoDireccion) {
            case INFRACCION:
                if (clStr(dirM.getDireccionBasicaInfraccion()) != null) {
                    dto = new ProcesaDireccionDTO();
                    dto.setComplemento(dirM.getDireccionBasicaInfraccion());
                    if (dirM.getLatitudDireccionInfraccion() != null)
                        dto.setLatitud(dirM.getLatitudDireccionInfraccion().doubleValue());
                    if (dirM.getLongitudDireccionInfraccion() != null)
                        dto.setLongitud(dirM.getLongitudDireccionInfraccion().doubleValue());
                }
                break;
            case INFRACTOR:
                if (clStr(dirM.getDireccionBasicaInfractor()) != null) {
                    dto = new ProcesaDireccionDTO();
                    dto.setComplemento(dirM.getDireccionBasicaInfractor());
                }
                break;
            case TESTIGO:
                if (clStr(dirM.getDireccionBasicaTestigo()) != null) {
                    dto = new ProcesaDireccionDTO();
                    dto.setComplemento(dirM.getDireccionBasicaTestigo());
                }
                break;
            case PATIO:
                if (clStr(dirM.getDireccionBasicaPatio()) != null) {
                    dto = new ProcesaDireccionDTO();
                    dto.setComplemento(dirM.getDireccionBasicaPatio());
                }
                break;
            default:
                break;
            }

            if (dto != null) {
                if (dto.getComplemento() != null && !dto.getComplemento().isEmpty()) {
                    dto.setCodigoTipoViaPrincipal(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
                }
            }
            return dto;

        }

        private static ProcesaComparendoPersonaDTO procesarInfractor(ItMultaDTO infractorM) {
            ProcesaComparendoPersonaDTO infractor = new ProcesaComparendoPersonaDTO();
            infractor.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
            if (infractorM == null) {
                return infractor;
            }
            infractor.setNumeroIdentificacion(clStr(infractorM.getNumeroIdentificacionInfractor()));
            infractor.setApellido1(clStr(infractorM.getApellido1Infractor()));
            infractor.setApellido2(clStr(infractorM.getApellido2Infractor()));
            infractor.setNombre1(clStr(infractorM.getNombre1Infractor()));
            infractor.setNombre2(clStr(infractorM.getNombre2Infractor()));
            infractor.setEmail(clStr(infractorM.getEmail()));
            infractor.setTelefonoFijo(clStr(infractorM.getTelefonoFijoInfractor()));
            infractor.setTelefonoMovil(clStr(infractorM.getTelefonoMovilInfractor()));
            infractor.setEdad(infractorM.getEdadInfractor());
            infractor.setProcesaDireccion(procesarDireccionM(infractorM, EnumTipoEntidades.INFRACTOR));
            infractor.setCodigoOrganismoLicencia(infractorM.getCodigoOrganLicenConduInfr());
            infractor.setNumeroLicencia(clStr(infractorM.getNumeroLicencia()));
            infractor.setFechaExpedicionLicenCondu(infractorM.getFechaExpedLicenConduInfra());
            infractor.setFechaVencimientoLicenCondu(infractorM.getFechaVenciLicenConduInfra());
            if (infractorM.getRazonSocialInfractor() != null) {
                infractor.setRazonSocial(infractorM.getRazonSocialInfractor());
            }
            return infractor;
        }

        private static ProcesaComparendoPersonaDTO procesarPropietario(ItMultaDTO propietarioM) {
            ProcesaComparendoPersonaDTO propietario = new ProcesaComparendoPersonaDTO();
            propietario.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo());
            if (propietarioM == null) {
                return propietario;
            }
            propietario.setNumeroIdentificacion(clStr(propietarioM.getNumeroIdentPropietario()));
            propietario.setApellido1(clStr(propietarioM.getApellido1Propietario()));
            propietario.setApellido2(clStr(propietarioM.getApellido2Propietario()));
            propietario.setNombre1(clStr(propietarioM.getNombre1Propietario()));
            propietario.setNombre2(clStr(propietarioM.getNombre2Propietario()));
            propietario.setRazonSocial(clStr(propietarioM.getRazonSocialPropietario()));
            return propietario;
        }

        private static ProcesaComparendoPersonaDTO procesarTestigo(ItMultaDTO testigoM) {
            ProcesaComparendoPersonaDTO testigo = new ProcesaComparendoPersonaDTO();
            testigo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getCodigo());
            if (testigoM == null) {
                return testigo;
            }

            testigo.setNumeroIdentificacion(clStr(testigoM.getNumeroIdentTestigo()));
            testigo.setApellido1(clStr(testigoM.getApellido1Testigo()));
            testigo.setApellido2(clStr(testigoM.getApellido2Testigo()));
            testigo.setNombre1(clStr(testigoM.getNombre1Testigo()));
            testigo.setNombre2(clStr(testigoM.getNombre2Testigo()));
            testigo.setProcesaDireccion(procesarDireccionM(testigoM, EnumTipoEntidades.TESTIGO));
            testigo.setTelefonoMovil(clStr(testigoM.getTelefonoMovilTestigo()));
            return testigo;
        }

        private static ProcesaComparendoPersonaDTO procesarEmpresa(ItMultaDTO empresaM) {
            ProcesaComparendoPersonaDTO empresa = new ProcesaComparendoPersonaDTO();
            empresa.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo());
            if (empresaM == null) {
                return empresa;
            }
            empresa.setRazonSocial(clStr(empresaM.getRazonSocialEmpresa()));
            empresa.setNumeroIdentificacion(clStr(empresaM.getNumeroIdentificacionEmpresa()));
            return empresa;
        }

        private static List<ProcesaEvidenciaDTO> procesarEvidencias(ItMultaDTO evidenciasM) {
            List<ProcesaEvidenciaDTO> resp = new ArrayList<>(2);
            ProcesaEvidenciaDTO dto = null;
            if (StringUtils.isNotBlank(evidenciasM.getEvidencia1Nombre())
                    || StringUtils.isNotBlank(evidenciasM.getEvidencia1Url())
                    || StringUtils.isNotBlank(evidenciasM.getEvidencia1CodigoTipo())) {
                dto = new ProcesaEvidenciaDTO();
                dto.setUrl(evidenciasM.getEvidencia1Url());
                dto.setNombreEvidencia(clStr(evidenciasM.getEvidencia1Nombre()));
                resp.add(dto);
            }
            if (StringUtils.isNotBlank(evidenciasM.getEvidencia2Nombre())
                    || StringUtils.isNotBlank(evidenciasM.getEvidencia2Url())
                    || StringUtils.isNotBlank(evidenciasM.getEvidencia2CodigoTipo())) {
                dto = new ProcesaEvidenciaDTO();
                dto.setUrl(evidenciasM.getEvidencia2Url());
                dto.setNombreEvidencia(clStr(evidenciasM.getEvidencia2Nombre()));
                resp.add(dto);
            }
            return resp;
        }
    }

}
