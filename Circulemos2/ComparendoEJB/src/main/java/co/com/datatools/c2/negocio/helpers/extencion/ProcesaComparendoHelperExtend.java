package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.dto.ColorDTO;
import co.com.datatools.c2.dto.ProcesaDireccionDTO;
import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.TipoEvidenciaDTO;
import co.com.datatools.c2.dto.TipoPersonaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.NivelServicioDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.TipoAgenteImpositorDTO;
import co.com.datatools.c2.dto.comparendo.TipoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoOrigenComparendoDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;
import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.ClaseVehiculoDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.ModalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.RadioAccionDTO;
import co.com.datatools.c2.dto.comun.TipoCategLicenciaConduccionDTO;
import co.com.datatools.c2.dto.comun.TipoCoordenadaDTO;
import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.regveh.LineaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.MarcaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.TipoTransportePasajeroDTO;
import co.com.datatools.c2.entidades.Agente;
import co.com.datatools.c2.entidades.ClaseVehiculo;
import co.com.datatools.c2.entidades.Color;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.ComparendoPatio;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.entidades.DetalleProcesamiento;
import co.com.datatools.c2.entidades.GradoAlcoholemia;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.entidades.LineaVehiculo;
import co.com.datatools.c2.entidades.MarcaVehiculo;
import co.com.datatools.c2.entidades.MedioImposicionComparendo;
import co.com.datatools.c2.entidades.Modalidad;
import co.com.datatools.c2.entidades.NivelServicio;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.entidades.ProcesaComparendoPersona;
import co.com.datatools.c2.entidades.ProcesaEvidencia;
import co.com.datatools.c2.entidades.RadioAccion;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;
import co.com.datatools.c2.entidades.TipoInfractor;
import co.com.datatools.c2.entidades.TipoPersonaComparendo;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.entidades.TipoTransportePasajero;
import co.com.datatools.c2.entidades.UnificacionResponsable;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.enumeracion.EnumCampoEntidad;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumCamposInconsistencias;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.negocio.helpers.comparendos.CampoEntidadHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.GradoAlcoholemiaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.InfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.MedioImposicionComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ProcesaEvidenciaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoInfractorHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.opciones.ConstantesGestionDocumentos;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.file.archivo.ArchivoFactory;
import co.com.datatools.util.file.archivo.ContenidoArchivoDTO;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;
import co.com.datatools.util.file.archivo.IArchivo;

/**
 * Logica para procesar los datos almacenados en los DTO de ProcesaEvidencia
 * 
 * @author felipe.martinez
 */
public class ProcesaComparendoHelperExtend extends ProcesaComparendoHelper {

    private final static Logger logger = Logger.getLogger(ProcesaComparendoHelperExtend.class.getName());

    private static String clStr(String string) {
        return StringUtils.trimToNull(string);
    }

    public static class ToComparendoDto {
        static public ComparendoDTO convert(ProcesaComparendoDTO prComp) {
            logger.debug("ProcesaComparendoHelperExtend:ToComparendoDto:convert");
            ComparendoDTO resp = new ComparendoDTO();

            // Verifica que relacion con orden ya fue creada
            Long cicomparendo = null;
            if (prComp.getComparendo() != null) {
                cicomparendo = prComp.getComparendo().getCicomparendo();
            }
            resp.setCicomparendo(cicomparendo);

            final ProcesaComparendoPersonaDTO infractor = findPersona(prComp.getProcesaComparendoPersonas(),
                    EnumTipoPersonaComparendo.INFRACTOR);
            final ProcesaComparendoPersonaDTO propietario = findPersona(prComp.getProcesaComparendoPersonas(),
                    EnumTipoPersonaComparendo.PROPIETARIO);
            final ProcesaComparendoPersonaDTO testigo = findPersona(prComp.getProcesaComparendoPersonas(),
                    EnumTipoPersonaComparendo.TESTIGO);
            final ProcesaComparendoPersonaDTO empresa = findPersona(prComp.getProcesaComparendoPersonas(),
                    EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE);

            resp.setRetieneLicencia(prComp.getRetieneLicencia());
            resp.setExisteFugaInfractor(prComp.getExisteFugaInfractor());
            resp.setFechaNotificacion(prComp.getFechaNotificacion());
            resp.setObservacionesInfractor(prComp.getObservacionesInfractor());
            resp.setFechaRegistro(prComp.getFechaRecepcion());
            resp.setNumeroCitacion(prComp.getNumeroCitacion());
            resp.setIdFacturaAxis(prComp.getIdFacturaAxis());
            resp.setSecuenciaUnica(prComp.getSecuenciaUnica());
            resp.setValorGradoAlcoholemia(prComp.getValorGradoAlcoholemia());
            resp.setFechaPruebaAlcoholemia(prComp.getFechaPruebaAlcoholemia());
            resp.setNiegaPruebaAlcoholemia(prComp.getNiegaPruebaAlcoholemia());
            resp.setNumeroPruebaAlcoholemia(prComp.getNumeroPruebaAlcoholemia());
            resp.setNumeroReincidencia(prComp.getNumeroReincidencia());

            // Fecha y hora de infraccion
            resp.setFechaInfraccion(prComp.getFechaInfraccion());
            resp.setHoraInfraccion(prComp.getHoraInfraccion());

            // direccion infraccion
            resp.setDireccion(procesarDireccion(prComp.getProcesaDireccionComparendo()));

            // Tipo Origen
            if (prComp.getCodigoOrigen() != null) {
                TipoOrigenComparendoDTO tipoOrigen = new TipoOrigenComparendoDTO();
                tipoOrigen.setId(prComp.getCodigoOrigen());
                resp.setTipoOrigen(tipoOrigen);
            }

            // Medio imposicion
            MedioImposicionComparendoDTO medioImposicion = new MedioImposicionComparendoDTO();
            medioImposicion.setId(prComp.getCodigoMedioImposicion());
            resp.setMedioImposicion(medioImposicion);

            // Tipo notificacion
            if (prComp.getIdTipoNotificacionComparendo() != null) {
                TipoNotificacionComparendoDTO tipoNotificacion = new TipoNotificacionComparendoDTO();
                tipoNotificacion.setId(prComp.getIdTipoNotificacionComparendo());
                resp.setTipoNotificacion(tipoNotificacion);
            }

            // Orden comparendo nacional
            OrdenComparendoNacionalDTO ordenCompNac = new OrdenComparendoNacionalDTO();
            ordenCompNac.setCicomparendo(cicomparendo);
            ordenCompNac.setNumeroComparendo(prComp.getNumeroComparendo());
            ordenCompNac.setOrganismoTransito(prComp.getOrganismoTransito());
            resp.setOrdenComparendoNacional(ordenCompNac);

            // Grado alcoholemia
            if (prComp.getIdGradoAlcoholemia() != null) {
                GradoAlcoholemiaDTO gradoAlcohol = new GradoAlcoholemiaDTO();
                gradoAlcohol.setId(prComp.getIdGradoAlcoholemia());
                resp.setGradoAlcoholemia(gradoAlcohol);
            }

            // Tipo Infractor
            if (prComp.getCodigoTipoInfractor() != null) {
                TipoInfractorDTO tipoInfractor = new TipoInfractorDTO();
                tipoInfractor.setId(prComp.getCodigoTipoInfractor());
                resp.setTipoInfractor(tipoInfractor);
            }

            // Ruta
            if (prComp.getIdRuta() != null) {
                RutaDTO ruta = new RutaDTO();
                ruta.setId(prComp.getIdRuta());
                resp.setRuta(ruta);
            }

            // Tipo Agente Impositor
            if (prComp.getIdTipoAgenteImpositor() != null) {
                TipoAgenteImpositorDTO tipoAgenteImpositorDTO = new TipoAgenteImpositorDTO();
                tipoAgenteImpositorDTO.setId(prComp.getIdTipoAgenteImpositor());
                resp.setTipoAgenteImpositorDTO(tipoAgenteImpositorDTO);
            }

            // Infraccion
            InfraccionDTO infraccion = new InfraccionDTO();
            infraccion.setCodigo(prComp.getCodigoInfraccion());
            infraccion.setId(prComp.getIdInfraccion());
            resp.setInfraccion(infraccion);

            // Agente
            resp.setComparendoAgente(procesarComparendoAgente(prComp));
            // Fin - Agente

            // Vehiculo
            resp.setComparendoVehiculo(procesarComparendoVehiculo(prComp));
            // Fin - Vehiculo

            // Patio
            resp.setComparendoPatio(procesarComparendoPatio(prComp));
            // Fin - Patio

            // Tipo comparendo
            TipoComparendoDTO tipoComparendo = new TipoComparendoDTO();
            tipoComparendo.setId(prComp.getIdTipoComparendo());
            resp.setTipoComparendo(tipoComparendo);

            // Para agregar lista de personas
            resp.setPersonaList(new ArrayList<ComparendoPersonaDTO>());
            // Infractor
            ComparendoPersonaDTO cpInfractor = procesarComparendoPersona(infractor);
            if (cpInfractor != null) {
                cpInfractor.setComparendo(prComp.getComparendo());
                resp.getPersonaList().add(cpInfractor);
            }
            resp.setInfractor(cpInfractor);

            // Propietario
            ComparendoPersonaDTO cpPropietario = procesarComparendoPersona(propietario);
            if (cpPropietario != null) {
                cpPropietario.setComparendo(prComp.getComparendo());
                resp.getPersonaList().add(cpPropietario);
            }
            resp.setPropietario(cpPropietario);

            // testigo
            ComparendoPersonaDTO cpTestigo = procesarComparendoPersona(testigo);
            if (cpTestigo != null) {
                cpTestigo.setComparendo(prComp.getComparendo());
                resp.getPersonaList().add(cpTestigo);
            }
            resp.setTestigo(cpTestigo);

            // Empresa
            ComparendoPersonaDTO cpEmpresa = procesarComparendoPersona(empresa);
            if (cpEmpresa != null) {
                cpEmpresa.setComparendo(prComp.getComparendo());
                resp.getPersonaList().add(cpEmpresa);
            }
            resp.setEmpresa(cpEmpresa);
            // Fin - Empresa

            // Evidencias
            resp.setEvidenciaList(procesarEvidencias(prComp));
            return resp;

        }

        private static List<EvidenciaDTO> procesarEvidencias(ProcesaComparendoDTO prComp) {
            List<EvidenciaDTO> resp = new ArrayList<>(0);
            List<ProcesaEvidenciaDTO> evidencias = prComp.getProcesaEvidencias();
            if (evidencias != null && !evidencias.isEmpty()) {
                resp = new ArrayList<>(evidencias.size());
                EvidenciaDTO dto = null;
                for (ProcesaEvidenciaDTO prEvidencia : evidencias) {
                    dto = new EvidenciaDTO();
                    // Si el ProcesaEvidencia llega a traves de interfaz debe venir el id
                    dto.setFechaEvidencia(prEvidencia.getFechaEvidencia());
                    dto.setIdDocumento(prEvidencia.getIdDocumento());
                    dto.setArchivoTransportable(prEvidencia.getArchivoTransportable());

                    TipoEvidenciaDTO tipoEvidencia = new TipoEvidenciaDTO();
                    tipoEvidencia.setId(prEvidencia.getCodigoTipoEvidencia());
                    dto.setTipoEvidencia(tipoEvidencia);
                    dto.setComparendo(prComp.getComparendo());

                    resp.add(dto);
                }
            }
            return resp;
        }

        private static ComparendoPersonaDTO procesarComparendoPersona(ProcesaComparendoPersonaDTO pCompPer) {
            ComparendoPersonaDTO comparendoPersona = new ComparendoPersonaDTO();
            if (pCompPer == null) {
                return null;
            }
            comparendoPersona
                    .setTipoIdentificacion(new TipoIdentificacionPersonaDTO(pCompPer.getIdTipoIdentificacion()));
            comparendoPersona.setNumeroIdentificacion((pCompPer.getNumeroIdentificacion()));
            comparendoPersona.setApellido1(clStr(pCompPer.getApellido1()));
            comparendoPersona.setApellido2(clStr(pCompPer.getApellido2()));
            comparendoPersona.setNombre1(clStr(pCompPer.getNombre1()));
            comparendoPersona.setNombre2(clStr(pCompPer.getNombre2()));
            comparendoPersona.setNumeroLicencia(clStr(pCompPer.getNumeroLicencia()));
            comparendoPersona.setEmail(clStr(pCompPer.getEmail()));
            comparendoPersona.setTelefonoFijo(clStr(pCompPer.getTelefonoFijo()));
            comparendoPersona.setTelefonoMovil(clStr(pCompPer.getTelefonoMovil()));
            comparendoPersona.setEdad(pCompPer.getEdad());
            comparendoPersona.setFechaExpedicionLicenCondu(pCompPer.getFechaExpedicionLicenCondu());
            comparendoPersona.setFechaVencimientoLicenCondu(pCompPer.getFechaVencimientoLicenCondu());
            comparendoPersona.setDigitoVerificacionNit(pCompPer.getDigitoVerificacionNit());
            comparendoPersona.setRazonSocial(clStr(pCompPer.getRazonSocial()));

            if (pCompPer.getCodigoOrganismoLicencia() != null) {
                OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
                organismoTransitoDTO.setCodigoOrganismo(pCompPer.getCodigoOrganismoLicencia());
                comparendoPersona.setOrganismoTransito(organismoTransitoDTO);
            }

            if (pCompPer.getIdCategoriaLicenciaCondu() != null) {
                TipoCategLicenciaConduccionDTO tipoCategLicenCond = new TipoCategLicenciaConduccionDTO();
                tipoCategLicenCond.setId(pCompPer.getIdCategoriaLicenciaCondu());
                comparendoPersona.setCategoriaLicencia(tipoCategLicenCond);
            }

            TipoPersonaComparendoDTO tipoPersonaComparendo = new TipoPersonaComparendoDTO();
            tipoPersonaComparendo.setCodigo(pCompPer.getCodigoTipoPersonaComparendo());
            comparendoPersona.setTipoPersonaComparendo(tipoPersonaComparendo);

            // Persona
            if (pCompPer.getPersonaDTO() != null) {
                comparendoPersona.setPersona(pCompPer.getPersonaDTO());
            }

            // Direccion
            if (pCompPer.getProcesaDireccion() != null) {
                comparendoPersona.setDireccion(procesarDireccion(pCompPer.getProcesaDireccion()));
            }
            return comparendoPersona;
        }

        public static DireccionDTO procesarDireccion(ProcesaDireccionDTO dirWs) {
            if (dirWs == null) {
                return null;
            }
            DireccionDTO dto = new DireccionDTO();
            TipoViaDTO tipoViaPrincipal = new TipoViaDTO();
            tipoViaPrincipal.setCodigo(dirWs.getCodigoTipoViaPrincipal());
            dto.setTipoViaPrincipal(tipoViaPrincipal);

            NombreViaDTO nombreViaPrincipal = new NombreViaDTO();
            nombreViaPrincipal.setCodigo(dirWs.getCodigoNombreViaPrincipal());
            dto.setNombreViaPrincipal(nombreViaPrincipal);

            TipoViaDTO tipoViaSecundaria = new TipoViaDTO();
            tipoViaSecundaria.setCodigo(dirWs.getCodigoTipoViaSecundaria());
            dto.setTipoViaSecundaria(tipoViaSecundaria);

            NombreViaDTO nombreViaSecundaria = new NombreViaDTO();
            nombreViaSecundaria.setCodigo(dirWs.getCodigoNombreViaSecundaria());
            dto.setNombreViaSecundaria(nombreViaSecundaria);

            dto.setNumeroViaPrincipal(dirWs.getNumeroViaPrincipal());
            dto.setLetraViaPrincipal(dirWs.getLetraViaPrincipal());
            dto.setBisViaPrincipal(clStr(dirWs.getBisViaPrincipal()));
            dto.setLetraBisViaPrincipal(dirWs.getLetraBisViaPrincipal());

            dto.setNumeroViaSecundaria(dirWs.getNumeroViaSecundaria());
            dto.setLetraViaSecundaria(dirWs.getLetraViaSecundaria());
            dto.setBisViaSecundaria(clStr(dirWs.getBisViaSecundaria()));
            dto.setLetraBisViaSecundaria(dirWs.getLetraBisViaSecundaria());

            dto.setNumeroPlacaDomiciliaria(dirWs.getNumeroPlacaDomiciliaria());
            dto.setComplemento(clStr(dirWs.getComplemento()));
            dto.setBarrio(clStr(dirWs.getBarrio()));
            dto.setLatitud(dirWs.getLatitud());
            dto.setLongitud(dirWs.getLongitud());

            MunicipioDTO municipio = new MunicipioDTO();
            municipio.setId(dirWs.getIdMunicipio());
            dto.setMunicipio(municipio);

            LocalidadDTO localidad = new LocalidadDTO();
            localidad.setId(dirWs.getIdLocalidad());
            dto.setLocalidad(localidad);

            PaisDTO pais = new PaisDTO();
            pais.setId(dirWs.getIdPais());
            dto.setPais(pais);

            DepartamentoDTO departamento = new DepartamentoDTO();
            departamento.setId(dirWs.getIdDepartamento());
            dto.setDepartamento(departamento);

            TipoUbicabilidadDTO tipoUbicabilidad = new TipoUbicabilidadDTO();
            tipoUbicabilidad.setId(dirWs.getCodigoTipoUbicabilidad());
            dto.setTipoUbicabilidad(tipoUbicabilidad);

            TipoCoordenadaDTO tipoCoordenada = new TipoCoordenadaDTO();
            tipoCoordenada.setCodigo(dirWs.getCodigoTipoCoordenada());
            dto.setTipoCoordenada(tipoCoordenada);

            CardinalidadDireccionDTO cardViaPrincipal = new CardinalidadDireccionDTO();
            cardViaPrincipal.setCodigo(dirWs.getCodigoCardinalidadViaPrincipal());
            dto.setCardinalidadViaPrincipal(cardViaPrincipal);

            CardinalidadDireccionDTO cardViaSecundaria = new CardinalidadDireccionDTO();
            cardViaSecundaria.setCodigo(dirWs.getCodigoCardinalidadViaSecundario());
            dto.setCardinalidadViaSecundaria(cardViaSecundaria);

            return dto;
        }

        private static ComparendoVehiculoDTO procesarComparendoVehiculo(ProcesaComparendoDTO prComp) {
            ComparendoVehiculoDTO comparendoVehiculo = new ComparendoVehiculoDTO();
            if (StringUtils.isEmpty(prComp.getIdentificacionVehiculo())
                    && StringUtils.isEmpty(prComp.getPlacaVehiculo())) {
                return null;
            }

            comparendoVehiculo.setIdentificacionVehiculo(prComp.getIdentificacionVehiculo());
            comparendoVehiculo.setModelo(prComp.getModelo());
            comparendoVehiculo.setNumeroLicenciaTransito(prComp.getNumeroLicenciaTransito());
            comparendoVehiculo.setNumeroMotor(prComp.getNumeroMotor());
            comparendoVehiculo.setNumeroTarjetaOperacion(prComp.getNumeroTarjetaOperacion());
            comparendoVehiculo.setPesoNeto(prComp.getPesoNeto());
            comparendoVehiculo.setPesoSeco(prComp.getPesoSeco());
            comparendoVehiculo.setPlacaVehiculo(prComp.getPlacaVehiculo());

            if (prComp.getCodigoOrganismoMatriculaVehiculo() != null) {
                OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
                organismoTransitoDTO.setCodigoOrganismo(prComp.getCodigoOrganismoMatriculaVehiculo());
                comparendoVehiculo.setOrganismoMatriVehic(organismoTransitoDTO);
            }
            if (prComp.getCodigoOrganismoLicenciaTransito() != null) {
                OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
                organismoTransitoDTO.setCodigoOrganismo(prComp.getCodigoOrganismoLicenciaTransito());
                comparendoVehiculo.setOrganismoLicenciaTransito(organismoTransitoDTO);
            }
            if (prComp.getIdClaseVehiculo() != null) {
                ClaseVehiculoDTO clase = new ClaseVehiculoDTO();
                clase.setId(prComp.getIdClaseVehiculo());
                comparendoVehiculo.setClaseVehiculo(clase);
            }
            if (prComp.getIdColor() != null) {
                ColorDTO color = new ColorDTO();
                color.setId(prComp.getIdColor());
                comparendoVehiculo.setColor(color);
            }
            if (prComp.getIdLineaVehiculo() != null) {
                LineaVehiculoDTO linea = new LineaVehiculoDTO();
                linea.setId(prComp.getIdLineaVehiculo());
                comparendoVehiculo.setLineaVehiculo(linea);
            }
            if (prComp.getIdMarcaVehiculo() != null) {
                MarcaVehiculoDTO marca = new MarcaVehiculoDTO();
                marca.setId(prComp.getIdMarcaVehiculo());
                comparendoVehiculo.setMarcaVehiculo(marca);
            }
            if (prComp.getIdModalidad() != null) {
                ModalidadDTO modalidad = new ModalidadDTO();
                modalidad.setId(prComp.getIdModalidad());
                comparendoVehiculo.setModalidad(modalidad);
            }
            if (prComp.getIdNivelServicio() != null) {
                NivelServicioDTO nivel = new NivelServicioDTO();
                nivel.setId(prComp.getIdNivelServicio());
                comparendoVehiculo.setNivelServicio(nivel);
            }
            if (prComp.getIdRadioAccion() != null) {
                RadioAccionDTO radio = new RadioAccionDTO();
                radio.setId(prComp.getIdRadioAccion());
                comparendoVehiculo.setRadioAccion(radio);
            }
            if (prComp.getIdTipoServicio() != null) {
                TipoServicioDTO tipoServicio = new TipoServicioDTO();
                tipoServicio.setId(prComp.getIdTipoServicio());
                comparendoVehiculo.setTipoServicio(tipoServicio);
            }
            if (prComp.getCodigoTipoTransPasajero() != null) {
                TipoTransportePasajeroDTO tipoTransporte = new TipoTransportePasajeroDTO();
                tipoTransporte.setId(prComp.getCodigoTipoTransPasajero());
                comparendoVehiculo.setTipoTransPasajero(tipoTransporte);
            }
            // comparendoVehiculo.setIdComparendoVehiculo(prComp.getComparendo().getCicomparendo());
            // comparendoVehiculo.setComparendo(prComp.getComparendo());
            return comparendoVehiculo;
        }

        private static ComparendoAgenteDTO procesarComparendoAgente(ProcesaComparendoDTO prComp) {
            ComparendoAgenteDTO comparendoAgente = new ComparendoAgenteDTO();
            if (StringUtils.isEmpty(prComp.getNumeroIdentificacionAgente())
                    && StringUtils.isEmpty(prComp.getPlacaAgente())) {
                return null;
            }
            comparendoAgente.setNumeroIdentificacion(prComp.getNumeroIdentificacionAgente());
            comparendoAgente.setApellido1(prComp.getApellido1Agente());
            comparendoAgente.setApellido2(prComp.getApellido2Agente());
            comparendoAgente.setNombre1(prComp.getNombre1Agente());
            comparendoAgente.setNombre2(prComp.getNombre2Agente());
            comparendoAgente.setObservacionesAgente(prComp.getObservacionesAgente());
            comparendoAgente.setPlaca(prComp.getPlacaAgente());

            if (prComp.getIdUnificacionResponsable() != null) {
                UnificacionResponsableDTO unificacionResponsable = new UnificacionResponsableDTO();
                unificacionResponsable.setId(prComp.getIdUnificacionResponsable());
                comparendoAgente.setUnificacionResponsable(unificacionResponsable);
            }

            if (prComp.getIdTipoAgenteImpositor().equals(EnumTipoAgenteImpositor.POLCA.getValue())
                    && StringUtils.isNotEmpty(prComp.getNombreResponsable())) {
                comparendoAgente.setNombreResponsable(prComp.getNombreResponsable());
            }

            if (prComp.getIdTipoIdentificacionAgente() != null) {
                TipoIdentificacionPersonaDTO tipoIdentAgente = new TipoIdentificacionPersonaDTO();
                tipoIdentAgente.setId(prComp.getIdTipoIdentificacionAgente());
                comparendoAgente.setTipoIdentificacionPersona(tipoIdentAgente);
            }

            // AgenteDTO
            if (prComp.getIdAgenteTransito() != null) {
                AgenteDTO agente = new AgenteDTO();
                agente.setId(prComp.getIdAgenteTransito());
                comparendoAgente.setAgente(agente);
            }

            // comparendoAgente.setIdComparendoAgente(prComp.getComparendo().getCicomparendo());
            // comparendoAgente.setComparendo(prComp.getComparendo());

            return comparendoAgente;
        }

        private static ComparendoPatioDTO procesarComparendoPatio(ProcesaComparendoDTO prComp) {
            ComparendoPatioDTO comparendoPatio = new ComparendoPatioDTO();
            if (StringUtils.isEmpty(prComp.getPlacaGrua()) && prComp.getNumeroPatio() == null
                    && prComp.getNumeroGrua() == null && prComp.getConsecutivoInmovilizacion() == null) {
                return null;
            }
            comparendoPatio.setConsecutivoAsignadoGrua(prComp.getConsecutivoInmovilizacion());
            if (prComp.getProcesaDireccionPatio() != null) {
                // direccion patio
                comparendoPatio.setDireccion(procesarDireccion(prComp.getProcesaDireccionPatio()));
            }
            comparendoPatio.setIdPatio(prComp.getIdPatio());
            comparendoPatio.setNombre(prComp.getNombrePatio());
            comparendoPatio.setNumeroGrua(prComp.getNumeroGrua());
            comparendoPatio.setNumeroInforme(prComp.getNumeroInforme());
            comparendoPatio.setNumeroPatio(prComp.getNumeroPatio());
            comparendoPatio.setPlacaGrua(prComp.getPlacaGrua());

            // comparendoPatio.setId(prComp.getComparendo().getCicomparendo());
            // comparendoPatio.setComparendo(prComp.getComparendo());
            return comparendoPatio;
        }

        public static ProcesaComparendoPersonaDTO findPersona(List<ProcesaComparendoPersonaDTO> personas,
                EnumTipoPersonaComparendo tipo) {
            for (ProcesaComparendoPersonaDTO persona : personas) {
                if (StringUtils.isNotEmpty(persona.getNumeroIdentificacion())
                        && persona.getCodigoTipoPersonaComparendo().equals(tipo.getCodigo())) {
                    return persona;
                }
            }
            return null;
        }
    }

    /**
     * Se encarga de verificar que datos cambiaron para construir la data que recibe el metodo de recibir comparendo para las validaciones
     * 
     * @param comparendo
     * @param procesaComparendoRectificadoDTO
     * @param comparendoDTO
     * @author giovanni.velandia
     * @return
     */
    public static Comparendo toComparendoRectificado(Comparendo comparendo,
            ProcesaComparendoRectificadoDTO procesaComparendoRectificadoDTO, ComparendoDTO comparendoDTO) {

        comparendo = ComparendoHelper.toLevel0Entity(comparendoDTO, comparendo);

        /*
         * Recorremos la lisata de campos y los guardamos en la base de datos por otro lado insertamnos los campos de las nuevas relaciones
         */
        boolean esInfractor = false;
        boolean esPropietario = false;
        boolean esTestigo = false;
        boolean esComparendoVehiculo = false;
        boolean esComparendoAgente = false;
        boolean esEmpresa = false;
        boolean esComparendoPatio = false;
        for (CampoRectificaComparendoDTO campoRectificaComparendoDTO : procesaComparendoRectificadoDTO
                .getCampoRectificaComparendoDTOs()) {

            switch (Utilidades.buscarElemEnum(EnumCampoEntidad.class,
                    campoRectificaComparendoDTO.getCampoEntidad().getCodigo())) {
            // Medio Imposicion (Tipo de comparendo)
            case MEDIO_IMPOSICION:
                comparendo.setMedioImposicion(MedioImposicionComparendoHelper
                        .toLevel1Entity(comparendoDTO.getMedioImposicion(), new MedioImposicionComparendo()));
                break;
            // Infraccion
            case INFRACCION:
                comparendo.setInfraccion(
                        InfraccionHelper.toLevel1Entity(comparendoDTO.getInfraccion(), new Infraccion()));
                break;
            // Grado de alcholemia
            case GRADO_ALCOHOLEMIA:
                comparendo.setGradoAlcoholemia(GradoAlcoholemiaHelper
                        .toLevel1Entity(comparendoDTO.getGradoAlcoholemia(), new GradoAlcoholemia()));
                break;
            // Empresa
            // Tipo documento empresa
            case TIPO_DOCUMENTO_EMPRESA:
                // Numero documento empresa
            case NUMERO_DOCUMENTO_EMPRESA:
                // Razon social
            case RAZON_SOCIAL_EMPRESA:
                esEmpresa = true;
                break;
            // Comparendo agente
            // Placa agente
            case PLACA_AGENTE:
                // Tipo Documento agente
            case TIPO_IDENTIFICACION_AGENTE:
                // Numero de identificacion agente
            case NUMERO_IDENTIFICACION_AGENTE:
                // primer nombre agente
            case PRIMER_NOMBRE_AGENTE:
                // Segundo nombre agente
            case SEGUNDO_NOMBRE_AGENTE:
                // Primer apellido agente
            case PRIMER_APELLIDO_AGENTE:
                // Segundo apellido agente
            case SEGUNDO_APELLIDO_AGENTE:
                // Agente transito
            case AGENTE_TRANSITO:
                // Observaciones agente
            case OBSERVACIONES_AGENTE:
                esComparendoAgente = true;
                break;
            // Comparendo vehiculo
            // Placa Comparendo
            case PLACA_VEHICULO:
                // Identificacion del vehiculo
            case IDENTIFICACION_VEHICULO:
                // Matricula
            case ORGANISMO_MATRICULA_VEHICULO:
                // Tipo servicio
            case TIPO_SERVICIO:
                // Numero tarjeta operacion
            case NUMERO_TARJETA_OPERACION:
                // Tipo transporte pasajeros
            case TIPO_TRANSPORTE_PASAJEROS:
                // Modalidad
            case MODALIDAD:
                // Radio accion
            case RADIO_ACCION:
                // Clase vehiculo
            case CLASE_VEHICULO:
                esComparendoVehiculo = true;
                break;
            // Infractor
            // Tipo documento infractor
            case TIPO_DOCUMENTO_INFRACTOR:
                // Numero de documento
            case NUMERO_DOCUMENTO_INFRACTOR:
                // Primer Nombre
            case PRIMER_NOMBRE_INFRACTOR:
                // Segundo nombre
            case SEGUNDO_NOMBRE_INFRACTOR:
                // Primer apellido
            case PRIMER_APELLIDO_INFRACTOR:
                // Segundo apellido
            case SEGUNDO_APELLIDO_INFRACTOR:
                // Tipo de infractor
            case TIPO_INFRACTOR:
                // Edad
            case EDAD_INFRACTOR:
                // Correo electronico
            case EMAIL_INFRACTOR:
                // Telefono
            case TELEFONO_FIJO_INFRACTOR:
                // Numero de celular
            case TELEFONO_MOVIL_INFRACTOR:
                // Licencia de condudccion
                // Organismo de transito licencia conduccion
            case CODIGO_ORG_TRANS_LICENCIA_INFRACTOR:
                // Numero de licencia de conduccion
            case NUMERO_LICENCIA_CONDUCCION_INFRACTOR:
                // categoria
            case CATEGORIA_LICENCIA_CONDUCCIÓN_INFRACTOR:
                // Fecha de expedicion
            case FECHA_EXPEDICION_LICENCIA_CONDUC_INFRAC:
                // Fecha de vencimiento
            case FECHA_VENCIMIENTO_LICENCIA_CONDUC_INFRAC:
                // Direccion infractor
            case DIRECCION_INFRACTOR:
                esInfractor = true;
                break;
            // Propietario
            // Tipo documento infractor
            case TIPO_DOCUMENTO_PROPIETARIO:
                // Numero de documento
            case NUMERO_DOCUMENTO_PROPIETARIO:
                // Primer Nombre
            case PRIMER_NOMBRE_PROPIETARIO:
                // Segundo nombre
            case SEGUNDO_NOMBRE_PROPIETARIO:
                // Primer apellido
            case PRIMER_APELLIDO_PROPIETARIO:
                // Segundo apellido
            case SEGUNDO_APELLIDO_PROPIETARIO:
                // Licencia de transito
                // Organismo de licencia de transito
            case ORGANISMO_LICENCIA_TRANSITO:
                // Numero de licencia de transito
            case NUMERO_LICENCIA_TRANSITO:
                esPropietario = true;
                esComparendoVehiculo = true;
                break;
            // Testigo
            // Tipo documento Testigo
            case TIPO_DOCUMENTO_TESTIGO:
                // Numero de documento
            case NUMERO_DOCUMENTO_TESTIGO:
                // Primer Nombre
            case PRIMER_NOMBRE_TESTIGO:
                // Segundo nombre
            case SEGUNDO_NOMBRE_TESTIGO:
                // Primer apellido
            case PRIMER_APELLIDO_TESTIGO:
                // Segundo apellido
            case SEGUNDO_APELLIDO_TESTIGO:
                // Numero de celular
            case TELEFONO_MOVIL_TESTIGO:
                esTestigo = true;
                break;
            // Inmovilizacion
            // Numero pario
            case NUMERO_PATIO:
                // nombre patio
            case NOMBRE_PATIO:
                // Numero de la grua
            case NUMERO_GRUA:
                // placa grua
            case PLACA_GRUA:
                // consecutivo de inmovilizacion
            case CONSECUTIVO_INMOVILIZACION:
                // Direccion
            case DIRECCION_PATIO:
                esComparendoPatio = true;
                break;
            default:
                break;
            }
        }
        // Empresa
        if (esEmpresa) {
            convertirProcesaComparendoPersona(comparendoDTO, comparendo, EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE);
        }
        // Comparendo agente
        if (esComparendoAgente) {
            comparendo.setComparendoAgente(procesarComparendoAgenteRectificar(comparendoDTO.getComparendoAgente(),
                    comparendo.getComparendoAgente(), comparendo));
        }
        // Infractor
        if (esInfractor) {
            convertirProcesaComparendoPersona(comparendoDTO, comparendo, EnumTipoPersonaComparendo.INFRACTOR);
            if (comparendoDTO.getTipoInfractor() != null) {
                comparendo.setTipoInfractor(
                        TipoInfractorHelper.toLevel1Entity(comparendoDTO.getTipoInfractor(), new TipoInfractor()));
            }
        }
        // Comparendo vehiculo
        if (esComparendoVehiculo) {
            comparendo.setComparendoVehiculo(procesarComparendoVehiculoRectificar(comparendoDTO.getComparendoVehiculo(),
                    comparendo.getComparendoVehiculo(), comparendo));
        }
        // Propietario
        if (esPropietario) {
            convertirProcesaComparendoPersona(comparendoDTO, comparendo, EnumTipoPersonaComparendo.PROPIETARIO);
        }
        // Testigo
        if (esTestigo) {
            convertirProcesaComparendoPersona(comparendoDTO, comparendo, EnumTipoPersonaComparendo.TESTIGO);
        }
        // Comparendo patio
        if (esComparendoPatio) {
            comparendo.setComparendoPatio(procesarComparendoPatioRectificar(comparendoDTO.getComparendoPatio(),
                    comparendo.getComparendoPatio(), comparendo));
        }
        return comparendo;
    }

    /**
     * Se encarga de construir los datos del procesa comparendo a un comparendo a rectificar
     * 
     * @param comparendoPatioDTO
     * @param comparendoPatio
     * @param comparendo
     * @author giovanni.velandia
     * @return
     */
    private static ComparendoPatio procesarComparendoPatioRectificar(ComparendoPatioDTO comparendoPatioDTO,
            ComparendoPatio comparendoPatio, Comparendo comparendo) {

        if (comparendoPatioDTO == null) {
            return null;
        }

        if (comparendoPatio == null) {
            comparendoPatio = new ComparendoPatio();
            comparendoPatio.setId(comparendo.getCicomparendo());
        }

        if (StringUtils.isEmpty(comparendoPatioDTO.getPlacaGrua()) && comparendoPatioDTO.getNumeroPatio() == null
                && comparendoPatioDTO.getNumeroGrua() == null
                && comparendoPatioDTO.getConsecutivoAsignadoGrua() == null) {
            return null;
        }
        comparendoPatio.setConsecutivoAsignadoGrua(comparendoPatioDTO.getConsecutivoAsignadoGrua());
        if (comparendoPatioDTO.getDireccion() != null) {
            // direccion patio
            Direccion direccion = new Direccion();
            direccion.setId(comparendoPatioDTO.getDireccion().getId());
            comparendoPatio.setDireccion(direccion);
        }
        comparendoPatio.setIdPatio(comparendoPatioDTO.getIdPatio());
        comparendoPatio.setNombre(comparendoPatioDTO.getNombre());
        comparendoPatio.setNumeroGrua(comparendoPatioDTO.getNumeroGrua());
        comparendoPatio.setNumeroInforme(comparendoPatioDTO.getNumeroInforme());
        comparendoPatio.setNumeroPatio(comparendoPatioDTO.getNumeroPatio());
        comparendoPatio.setPlacaGrua(comparendoPatioDTO.getPlacaGrua());

        return comparendoPatio;
    }

    /**
     * Se encarga de construir los datos del procesa comparendo a un comparendo a rectificar
     * 
     * @param comparendoAgenteDTO
     * @param comparendoAgente
     * @param comparendo
     * @author giovanni.velandia
     * @return
     */
    private static ComparendoAgente procesarComparendoAgenteRectificar(ComparendoAgenteDTO comparendoAgenteDTO,
            ComparendoAgente comparendoAgente, Comparendo comparendo) {

        if (comparendoAgenteDTO == null) {
            return null;
        }

        if (comparendoAgente == null) {// TODO cambiado para que lo encuentre Giovanni
            comparendoAgente = new ComparendoAgente();
            comparendoAgente.setComparendo(new Comparendo());
            comparendoAgente.getComparendo().setCicomparendo(comparendo.getCicomparendo());
        }

        if (StringUtils.isEmpty(comparendoAgenteDTO.getNumeroIdentificacion())
                && StringUtils.isEmpty(comparendoAgenteDTO.getPlaca())) {
            return null;
        }
        comparendoAgente.setNumeroIdentificacion(comparendoAgenteDTO.getNumeroIdentificacion());
        comparendoAgente.setApellido1(comparendoAgenteDTO.getApellido1());
        comparendoAgente.setApellido2(comparendoAgenteDTO.getApellido2());
        comparendoAgente.setNombre1(comparendoAgenteDTO.getNombre1());
        comparendoAgente.setNombre2(comparendoAgenteDTO.getNombre2());
        comparendoAgente.setObservacionesAgente(comparendoAgenteDTO.getObservacionesAgente());
        comparendoAgente.setPlaca(comparendoAgenteDTO.getPlaca());

        if (comparendoAgenteDTO.getUnificacionResponsable() != null) {
            UnificacionResponsable unificacionResponsable = new UnificacionResponsable();
            unificacionResponsable.setId(comparendoAgenteDTO.getUnificacionResponsable().getId());
            comparendoAgente.setUnificacionResponsable(unificacionResponsable);
        }

        if (StringUtils.isNotEmpty(comparendoAgenteDTO.getNombreResponsable())) {
            comparendoAgente.setNombreResponsable(comparendoAgenteDTO.getNombreResponsable());
        }

        TipoIdentificacionPersona tipoIdentAgente = new TipoIdentificacionPersona();
        tipoIdentAgente.setId(comparendoAgenteDTO.getTipoIdentificacionPersona().getId());
        comparendoAgente.setTipoIdentificacionPersona(tipoIdentAgente);

        // AgenteDTO
        if (comparendoAgenteDTO.getAgente() != null) {
            Agente agente = new Agente();
            agente.setId(comparendoAgenteDTO.getAgente().getId());
            comparendoAgente.setAgente(agente);
        }
        return comparendoAgente;
    }

    /**
     * Se encarga de construir los datos del procesa comparendo a un comparendo a rectificar
     * 
     * @param comparendoVehiculoDTO
     * @param comparendoVehiculo
     * @param comparendo
     * @author giovanni.velandia
     * @return
     */
    private static ComparendoVehiculo procesarComparendoVehiculoRectificar(ComparendoVehiculoDTO comparendoVehiculoDTO,
            ComparendoVehiculo comparendoVehiculo, Comparendo comparendo) {

        if (comparendoVehiculoDTO == null) {
            return null;
        }

        if (comparendoVehiculo == null) {
            comparendoVehiculo = new ComparendoVehiculo();
            comparendoVehiculo.setId(comparendo.getCicomparendo());
        }

        if (StringUtils.isEmpty(comparendoVehiculoDTO.getIdentificacionVehiculo())
                && StringUtils.isEmpty(comparendoVehiculoDTO.getPlacaVehiculo())) {
            return null;
        }
        comparendoVehiculo.setIdentificacionVehiculo(comparendoVehiculoDTO.getIdentificacionVehiculo());
        comparendoVehiculo.setModelo(comparendoVehiculoDTO.getModelo());
        comparendoVehiculo.setNumeroLicenciaTransito(comparendoVehiculoDTO.getNumeroLicenciaTransito());
        comparendoVehiculo.setNumeroMotor(comparendoVehiculoDTO.getNumeroMotor());
        comparendoVehiculo.setNumeroTarjetaOperacion(comparendoVehiculoDTO.getNumeroTarjetaOperacion());
        comparendoVehiculo.setPesoNeto(comparendoVehiculoDTO.getPesoNeto());
        comparendoVehiculo.setPesoSeco(comparendoVehiculoDTO.getPesoSeco());
        comparendoVehiculo.setPlacaVehiculo(comparendoVehiculoDTO.getPlacaVehiculo());

        if (comparendoVehiculoDTO.getOrganismoMatriVehic() != null) {
            OrganismoTransito organismoTransito = new OrganismoTransito();
            organismoTransito.setCodigoOrganismo(comparendoVehiculoDTO.getOrganismoMatriVehic().getCodigoOrganismo());
            comparendoVehiculo.setOrganismoMatriVehic(organismoTransito);
        }
        if (comparendoVehiculoDTO.getOrganismoLicenciaTransito() != null) {
            OrganismoTransito organismoTransito = new OrganismoTransito();
            organismoTransito
                    .setCodigoOrganismo(comparendoVehiculoDTO.getOrganismoLicenciaTransito().getCodigoOrganismo());
            comparendoVehiculo.setOrganismoLicenciaTransito(organismoTransito);
        }
        if (comparendoVehiculoDTO.getClaseVehiculo() != null) {
            ClaseVehiculo clase = new ClaseVehiculo();
            clase.setId(comparendoVehiculoDTO.getClaseVehiculo().getId());
            comparendoVehiculo.setClaseVehiculo(clase);
        }
        if (comparendoVehiculoDTO.getColor() != null) {
            Color color = new Color();
            color.setId(comparendoVehiculoDTO.getColor().getId());
            comparendoVehiculo.setColor(color);
        }
        if (comparendoVehiculoDTO.getLineaVehiculo() != null) {
            LineaVehiculo linea = new LineaVehiculo();
            linea.setId(comparendoVehiculoDTO.getLineaVehiculo().getId());
            comparendoVehiculo.setLineaVehiculo(linea);
        }
        if (comparendoVehiculoDTO.getMarcaVehiculo() != null) {
            MarcaVehiculo marca = new MarcaVehiculo();
            marca.setId(comparendoVehiculoDTO.getMarcaVehiculo().getId());
            comparendoVehiculo.setMarcaVehiculo(marca);
        }
        if (comparendoVehiculoDTO.getModalidad() != null) {
            Modalidad modalidad = new Modalidad();
            modalidad.setId(comparendoVehiculoDTO.getModalidad().getId());
            comparendoVehiculo.setModalidad(modalidad);
        }
        if (comparendoVehiculoDTO.getNivelServicio() != null) {
            NivelServicio nivel = new NivelServicio();
            nivel.setId(comparendoVehiculoDTO.getNivelServicio().getId());
            comparendoVehiculo.setNivelServicio(nivel);
        }
        if (comparendoVehiculoDTO.getRadioAccion() != null) {
            RadioAccion radio = new RadioAccion();
            radio.setId(comparendoVehiculoDTO.getRadioAccion().getId());
            comparendoVehiculo.setRadioAccion(radio);
        }
        if (comparendoVehiculoDTO.getTipoServicio() != null) {
            TipoServicio tipoServicio = new TipoServicio();
            tipoServicio.setId(comparendoVehiculoDTO.getTipoServicio().getId());
            comparendoVehiculo.setTipoServicio(tipoServicio);
        }
        if (comparendoVehiculoDTO.getTipoTransPasajero() != null) {
            TipoTransportePasajero tipoTransporte = new TipoTransportePasajero();
            tipoTransporte.setId(comparendoVehiculoDTO.getTipoTransPasajero().getId());
            comparendoVehiculo.setTipoTransPasajero(tipoTransporte);
        }

        return comparendoVehiculo;
    }

    /**
     * Se encarga de construir los datos del procesa comparendo a un comparendo a rectificar
     * 
     * @param comparendoPersonaDTO
     * @param comparendoPersona
     * @param comparendo
     * @author giovanni.velandia
     * @return
     */
    private static ComparendoPersona procesarComparendoPersonaRectificar(ComparendoPersonaDTO comparendoPersonaDTO,
            ComparendoPersona comparendoPersona, Comparendo comparendo) {

        if (comparendoPersonaDTO == null) {
            return null;
        }

        if (comparendoPersona == null) {
            comparendoPersona = new ComparendoPersona();
            comparendoPersona.setComparendo(comparendo);
        }

        // Tipo identificacion persona
        TipoIdentificacionPersona tipoIdentificacionPersona = new TipoIdentificacionPersona();
        tipoIdentificacionPersona.setId(comparendoPersonaDTO.getTipoIdentificacion().getId());
        comparendoPersona.setTipoIdentificacion(tipoIdentificacionPersona);

        comparendoPersona.setNumeroIdentificacion((comparendoPersonaDTO.getNumeroIdentificacion()));
        comparendoPersona.setApellido1(clStr(comparendoPersonaDTO.getApellido1()));
        comparendoPersona.setApellido2(clStr(comparendoPersonaDTO.getApellido2()));
        comparendoPersona.setNombre1(clStr(comparendoPersonaDTO.getNombre1()));
        comparendoPersona.setNombre2(clStr(comparendoPersonaDTO.getNombre2()));
        comparendoPersona.setNumeroLicencia(clStr(comparendoPersonaDTO.getNumeroLicencia()));
        comparendoPersona.setEmail(clStr(comparendoPersonaDTO.getEmail()));
        comparendoPersona.setTelefonoFijo(clStr(comparendoPersonaDTO.getTelefonoFijo()));
        comparendoPersona.setTelefonoMovil(clStr(comparendoPersonaDTO.getTelefonoMovil()));
        comparendoPersona.setEdad(comparendoPersonaDTO.getEdad());
        comparendoPersona.setFechaExpedicionLicenCondu(comparendoPersonaDTO.getFechaExpedicionLicenCondu());
        comparendoPersona.setFechaVencimientoLicenCondu(comparendoPersonaDTO.getFechaVencimientoLicenCondu());
        comparendoPersona.setDigitoVerificacionNit(comparendoPersonaDTO.getDigitoVerificacionNit());
        comparendoPersona.setRazonSocial(clStr(comparendoPersonaDTO.getRazonSocial()));

        if (comparendoPersonaDTO.getOrganismoTransito() != null) {
            OrganismoTransito organismoTransito = new OrganismoTransito();
            organismoTransito.setCodigoOrganismo(comparendoPersonaDTO.getOrganismoTransito().getCodigoOrganismo());
            comparendoPersona.setOrganismoTransito(organismoTransito);
        }

        if (comparendoPersonaDTO.getCategoriaLicencia() != null) {
            TipoCategLicenciaConduccion tipoCategLicenCond = new TipoCategLicenciaConduccion();
            tipoCategLicenCond.setId(comparendoPersonaDTO.getCategoriaLicencia().getId());
            comparendoPersona.setCategoriaLicencia(tipoCategLicenCond);
        }

        TipoPersonaComparendo tipoPersonaComparendo = new TipoPersonaComparendo();
        tipoPersonaComparendo.setCodigo(comparendoPersonaDTO.getTipoPersonaComparendo().getCodigo());
        comparendoPersona.setTipoPersonaComparendo(tipoPersonaComparendo);

        // Persona
        if (comparendoPersonaDTO.getPersona() != null) {
            Persona persona = new Persona();
            persona.setId(comparendoPersonaDTO.getPersona().getId());
            comparendoPersona.setPersona(persona);
        }

        // Direccion
        if (comparendoPersonaDTO.getDireccion() != null) {
            Direccion direccion = DireccionHelper.toLevel1Entity(comparendoPersonaDTO.getDireccion(), null);
            comparendoPersona.setDireccion(direccion);
        }
        return comparendoPersona;
    }

    /**
     * Se encarga de tranasformar un procesa comparendo persona en datos del comparendo
     * 
     * @param comparendoDTO
     * @param comparendo
     * @param enumTipoPersonaComparendo
     */
    private static void convertirProcesaComparendoPersona(ComparendoDTO comparendoDTO, Comparendo comparendo,
            EnumTipoPersonaComparendo enumTipoPersonaComparendo) {
        ComparendoPersona comparendoPersona = null;
        // Recorremos la lista de comparendoPersona para obtener la informacion que esta en la base de datos
        boolean esPersonaNueva = false;
        for (ComparendoPersona comparendoPersonaBD : comparendo.getPersonaList()) {
            if (comparendoPersonaBD.getTipoPersonaComparendo().getCodigo()
                    .equals(enumTipoPersonaComparendo.getCodigo())) {
                comparendoPersona = comparendoPersonaBD;
                esPersonaNueva = true;
                break;
            }
        }

        // Recorremos la lista de comparendoPersonaDTO para obtener la informacion que llega
        for (ComparendoPersonaDTO comparendoPersonaDTO : comparendoDTO.getPersonaList()) {
            if (comparendoPersonaDTO.getTipoPersonaComparendo().getCodigo()
                    .equals(enumTipoPersonaComparendo.getCodigo())) {
                comparendoPersona = procesarComparendoPersonaRectificar(comparendoPersonaDTO, comparendoPersona,
                        comparendo);
                break;
            }
        }

        if (!esPersonaNueva) {
            // Agregamos la fecha inicio que es la misma de la infraccion
            comparendoPersona.setFechaInicio(comparendo.getFechaInfraccion());
            comparendo.getPersonaList().add(comparendoPersona);
        }
    }

    public static ProcesaComparendo toLevel0Entity(ProcesaComparendoDTO dto, ProcesaComparendo entidad) {
        if (null == entidad) {
            entidad = new ProcesaComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroComparendo(dto.getNumeroComparendo());
        entidad.setCodigoOrigen(dto.getCodigoOrigen());
        entidad.setCodigoMedioImposicion(dto.getCodigoMedioImposicion());
        entidad.setCodigoTipoInfractor(dto.getCodigoTipoInfractor());
        entidad.setIdTipoNotificacionComparendo(dto.getIdTipoNotificacionComparendo());
        entidad.setFechaInfraccion(dto.getFechaInfraccion());
        entidad.setHoraInfraccion(dto.getHoraInfraccion());
        entidad.setRetieneLicencia(dto.getRetieneLicencia());
        entidad.setIdTipoServicio(dto.getIdTipoServicio());
        entidad.setIdRadioAccion(dto.getIdRadioAccion());
        entidad.setIdModalidad(dto.getIdModalidad());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        entidad.setIdentificacionVehiculo(dto.getIdentificacionVehiculo());
        entidad.setIdNivelServicio(dto.getIdNivelServicio());
        entidad.setCodigoOrganismoMatriculaVehiculo(dto.getCodigoOrganismoMatriculaVehiculo());
        entidad.setExisteFugaInfractor(dto.getExisteFugaInfractor());
        entidad.setCodigoInfraccion(dto.getCodigoInfraccion());
        entidad.setIdRuta(dto.getIdRuta());
        entidad.setNumeroTarjetaOperacion(dto.getNumeroTarjetaOperacion());
        entidad.setValorGradoAlcoholemia(dto.getValorGradoAlcoholemia());
        entidad.setPlacaAgente(dto.getPlacaAgente());
        entidad.setIdTipoIdentificacionAgente(dto.getIdTipoIdentificacionAgente());
        entidad.setNumeroIdentificacionAgente(dto.getNumeroIdentificacionAgente());
        entidad.setIdPatio(dto.getIdPatio());
        entidad.setIdClaseVehiculo(dto.getIdClaseVehiculo());
        entidad.setNumeroGrua(dto.getNumeroGrua());
        entidad.setNumeroLicenciaTransito(dto.getNumeroLicenciaTransito());
        entidad.setPlacaGrua(dto.getPlacaGrua());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setObservacionesAgente(dto.getObservacionesAgente());
        entidad.setObservacionesInfractor(dto.getObservacionesInfractor());
        entidad.setInmovilizado(dto.getInmovilizado());
        entidad.setFechaOperacion(dto.getFechaOperacion());
        entidad.setFechaRecepcion(dto.getFechaRecepcion());

        return entidad;
    }

    public static ProcesaComparendo toProcesaComparendo(ProcesaComparendoDTO dto, ProcesaComparendo entidad) {
        ProcesaComparendo resp = toLevel0Entity(dto, entidad);

        // Personas
        List<ProcesaComparendoPersonaDTO> personas = dto.getProcesaComparendoPersonas();
        if (personas != null) {
            resp.setProcesaComparendoPersonas(new ArrayList<ProcesaComparendoPersona>());
            for (ProcesaComparendoPersonaDTO personaDto : personas) {
                ProcesaComparendoPersona persona = ProcesaComparendoPersonaHelperExtend.toLevel1Entity(personaDto,
                        null);
                persona.setProcesaComparendo(resp);
                resp.getProcesaComparendoPersonas().add(persona);
            }
        }
        // Evidencia
        List<ProcesaEvidenciaDTO> evidencias = dto.getProcesaEvidencias();
        if (evidencias != null) {
            resp.setProcesaEvidencias(new ArrayList<ProcesaEvidencia>());
            for (ProcesaEvidenciaDTO evidenciaDto : evidencias) {
                ProcesaEvidencia evidencia = ProcesaEvidenciaHelper.toLevel0Entity(evidenciaDto, null);
                evidencia.setProcesaComparendo(resp);
                resp.getProcesaEvidencias().add(evidencia);
            }
        }
        // Usuario
        final UsuarioPersonaDTO usuarioPersona = dto.getUsuarioPersona();
        if (usuarioPersona != null) {
            entidad.setUsuarioPersona(new UsuarioPersona(usuarioPersona.getUsuario().getId()));
        }
        return resp;
    }

    /**
     * Permite truncar la longitud de los campos de acuerdo a las restricciones del comparendo, para impedir q el procesa comparendo intente almacenar
     * campos con longitud erronea
     * 
     * @param prComp
     *            dto a procesar
     * @return DTO procesado
     */
    public static ProcesaComparendoDTO ajustarLongitudCampos(final ProcesaComparendoDTO prComp) {
        String numeroComparendo = prComp.getNumeroComparendo();
        String placaVehiculo = prComp.getPlacaVehiculo();
        String identificacionVehiculo = prComp.getIdentificacionVehiculo();
        // String codigoInfraccion = prCompDTO.getCodigoInfraccion();
        String numeroTarjetaOperacion = prComp.getNumeroTarjetaOperacion();
        String placaAgente = prComp.getPlacaAgente();
        String numeroDocumentoAgente = prComp.getNumeroIdentificacionAgente();
        String numeroLicenciaTransito = prComp.getNumeroLicenciaTransito();
        String placaGrua = prComp.getPlacaGrua();
        String observacionesAgente = prComp.getObservacionesAgente();
        String observacionesInfractor = prComp.getObservacionesInfractor();

        /*
         * prComp.setNumeroComparendo(truncar(EnumCampoComparendo.NUMERO_COMPARENDO, numeroComparendo));
         * prComp.setPlacaVehiculo(truncar(EnumCampoComparendo.PLACA_VEHICULO, placaVehiculo));
         * prComp.setIdentificacionVehiculo(truncar(EnumCampoComparendo.IDENTIFICACION_VEHICULO, identificacionVehiculo));
         * prComp.setNumeroTarjetaOperacion(truncar(EnumCampoComparendo.NUMERO_TARJETA_OPERACION, numeroTarjetaOperacion));
         * prComp.setPlacaAgente(truncar(EnumCampoComparendo.PLACA_AGENTE_TRANSITO, placaAgente));
         * prComp.setNumeroIdentificacionAgente(truncar(EnumCampoComparendo.NUMERO_DOCUMENTO_AGENTE, numeroDocumentoAgente));
         * prComp.setNumeroLicenciaTransito(truncar(EnumCampoComparendo.NUMERO_LICENCIA_TRANSITO, numeroLicenciaTransito));
         * prComp.setPlacaGrua(truncar(EnumCampoComparendo.PLACA_GRUA, placaGrua));
         * prComp.setObservacionesAgente(truncar(EnumCampoComparendo.OBSERVACIONES_AGENTE, observacionesAgente));
         * prComp.setObservacionesInfractor(truncar(EnumCampoComparendo.OBSERVACIONES_INFRACTOR, observacionesInfractor));
         */
        // Sin importar el tipo de personas todas manejan una longitud maximo por q se almacenan en la misma tabla
        List<ProcesaComparendoPersonaDTO> personas = prComp.getProcesaComparendoPersonas();
        String numeroDocumento;
        String numeroLicencia;
        String apellido1;
        String apellido2;
        String nombre1;
        String nombre2;
        String razonSocial;
        String email;
        String telefono;
        for (ProcesaComparendoPersonaDTO persona : personas) {
            numeroDocumento = persona.getNumeroIdentificacion();
            numeroLicencia = persona.getNumeroLicencia();
            apellido1 = persona.getApellido1();
            apellido2 = persona.getApellido2();
            nombre1 = persona.getNombre1();
            nombre2 = persona.getNombre2();
            razonSocial = persona.getRazonSocial();
            email = persona.getEmail();
            telefono = persona.getTelefonoFijo();

            /*
             * persona.setNumeroIdentificacion(truncar(EnumCampoComparendo.NUMERO_DOCUMENTO_INFRACTOR, numeroDocumento));
             * persona.setNumeroLicencia(truncar(EnumCampoComparendo.NUMERO_LICENCIA_CONDUCCION_INFRACTOR, numeroLicencia));
             * persona.setApellido1(truncar(EnumCampoComparendo.APELLIDO_1_INFRACTOR, apellido1));
             * persona.setApellido2(truncar(EnumCampoComparendo.APELLIDO_2_INFRACTOR, apellido2));
             * persona.setNombre1(truncar(EnumCampoComparendo.NOMBRE_1_INFRACTOR, nombre1));
             * persona.setNombre2(truncar(EnumCampoComparendo.NOMBRE_2_INFRACTOR, nombre2));
             * persona.setRazonSocial(truncar(EnumCampoComparendo.RAZON_SOCIAL_INFRACTOR, razonSocial));
             * persona.setEmail(truncar(EnumCampoComparendo.EMAIL_INFRACTOR, email));
             * persona.setTelefonoFijo(truncar(EnumCampoComparendo.TELEFONO_INFRACTOR, telefono));
             */

        }
        return prComp;
    }

    /*
     * private static String truncar(EnumCampoComparendo campo, String valorCampo) { if (valorCampo != null && valorCampo.length() >
     * campo.getLongitud()) { valorCampo = StringUtils.substring(valorCampo, 0, campo.getLongitud()); } return valorCampo; }
     */
    /**
     * 
     * @param entidad
     * @param conRWS
     * @param conCampo
     * @return Lista de detalles con campos de respuesta
     */
    private static List<DetalleProcesamientoDTO> toLevel1DetallesDTO(ProcesaComparendo entidad, boolean conRWS,
            boolean conCampo) {
        List<DetalleProcesamientoDTO> lista = new ArrayList<DetalleProcesamientoDTO>();

        if (entidad.getDetalleProcesamientos() != null) {
            for (DetalleProcesamiento detalleProcesamiento : entidad.getDetalleProcesamientos()) {
                DetalleProcesamientoDTO detalleProcDTO = null;
                if (conRWS) {
                    // if ((detalleProcesamiento.getRespuestaWebService() != null)
                    // && (detalleProcesamiento.getRespuestaWebService().getId() != null)) {
                    // RespuestaWebServiceDTO rtaWSDTO = RespuestaWebServiceHelper.toLevel1DTO(detalleProcesamiento
                    // .getRespuestaWebService());
                    // detalleProcDTO = new DetalleProcesamientoDTO();
                    // detalleProcDTO.setRespuestaWebService(rtaWSDTO);
                    // lista.add(detalleProcDTO);
                    // }
                }

                if (conCampo) {
                    if ((detalleProcesamiento.getCampoEntidad() != null)
                            && (detalleProcesamiento.getCampoEntidad().getCodigo() != null)) {
                        CampoEntidadDTO campoDTO = CampoEntidadHelper
                                .toLevel0DTO(detalleProcesamiento.getCampoEntidad());
                        if (detalleProcDTO == null) {
                            detalleProcDTO = new DetalleProcesamientoDTO();
                            lista.add(detalleProcDTO);
                        }

                        detalleProcDTO.setCampoEntidad(campoDTO);
                    }

                }

                // if (detalleProcDTO != null) {
                // detalleProcDTO.setValorComparendo(detalleProcesamiento.getValorComparendo());
                // detalleProcDTO.setValorSistema(detalleProcesamiento.getValorSistema());
                // }

            }

        }

        return lista;
    }

    /**
     * Retorna el encabezado del procesa comparendo dto
     * 
     * @param entidad
     * @return Retorna el encabezado del procesa comparendo dto
     */
    private static ProcesaComparendoDTO toLevel0Encabezado(ProcesaComparendo entidad) {
        ProcesaComparendoDTO level0DTO = ProcesaComparendoHelper.toLevel0DTO(entidad);
        level0DTO.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return level0DTO;
    }

    // --- to DTO
    public static ProcesaComparendoDTO toLevel1ConteoDTO(ProcesaComparendo entidad) {
        ProcesaComparendoDTO level1DTO = ProcesaComparendoHelperExtend.toLevel0Encabezado(entidad);
        level1DTO.setDetalleProcesamientos(ProcesaComparendoHelperExtend.toLevel1DetallesDTO(entidad, true, false));
        return level1DTO;
    }

    public static ProcesaComparendoDTO toLevel2DTO(ProcesaComparendo entidad) {
        ProcesaComparendoDTO level2DTO = ProcesaComparendoHelperExtend.toLevel0Encabezado(entidad);
        level2DTO.setDetalleProcesamientos(ProcesaComparendoHelperExtend.toLevel1DetallesDTO(entidad, true, true));
        return level2DTO;
    }

    /**
     * Genera Archivo excel
     * 
     * @param contenido
     * @return byte[]
     */
    public static byte[] generaArchivo(List<UnificacionInconsistenciasComparendoDTO> contenido) {

        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();
        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);
        List<Object> encabezado = new ArrayList<>();
        if (contenido != null) {
            if (!contenido.isEmpty()) {
                encabezado.add(EnumCamposInconsistencias.NUMERO_COMPARENDO.getNombre());
                encabezado.add(EnumCamposInconsistencias.NOMBRE_ORIGEN_COMPARENDO.getNombre());
                encabezado.add(EnumCamposInconsistencias.CODIGO_INFRACCION.getNombre());
                encabezado.add(EnumCamposInconsistencias.FECHA_REGISTRO.getNombre());
                encabezado.add(EnumCamposInconsistencias.FECHA_HORA_IMPOSICION_COMPARENDO.getNombre());
                encabezado.add(EnumCamposInconsistencias.PLACA_VEHICULO.getNombre());
                encabezado.add(EnumCamposInconsistencias.TIPO_DOCUMENTO_INFRACTOR.getNombre());
                encabezado.add(EnumCamposInconsistencias.NUMERO_DOCUMENTO_INFRACTOR.getNombre());
                encabezado.add(EnumCamposInconsistencias.NOMBRE_INFRACTOR.getNombre());
                encabezado.add(EnumCamposInconsistencias.APELLIDO_INFRACTOR.getNombre());
                encabezado.add(EnumCamposInconsistencias.NOMBRE_CAMPO_CON_INCONSISTENCIAS.getNombre());
                encabezado.add(EnumCamposInconsistencias.ERROR_PROCESAMIENTO.getNombre());
                encabezado.add(EnumCamposInconsistencias.USUARIO.getNombre());
                filaArchivoExcel.setCeldas(encabezado);
                filaArchivoExcel.setEncabezado(true);
                filaArchivoList.add(filaArchivoExcel);
            }

            for (UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO : contenido) {

                FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
                filaArchivoExcel.setHoja(0);
                List<Object> registros = new ArrayList<>();
                // Numero comparendo
                if (unificacionInconsistenciasComparendoDTO.getNumeroComparendo() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNumeroComparendo());
                } else {
                    registros.add(null);
                }
                // Nombre de origen del comparendo
                if (unificacionInconsistenciasComparendoDTO.getNombreOrigen() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNombreOrigen());
                } else {
                    registros.add(null);
                }
                // Codigo de infraccion
                if (unificacionInconsistenciasComparendoDTO.getCodigoInfraccion() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getCodigoInfraccion());
                } else {
                    registros.add(null);
                }
                // Fecha de registro
                if (unificacionInconsistenciasComparendoDTO.getFechaRegistro() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getFechaRegistro());
                } else {
                    registros.add(null);
                }
                // Fecha y hora de imposicion comparendo
                if (unificacionInconsistenciasComparendoDTO.getFechaInfraccion() != null
                        && unificacionInconsistenciasComparendoDTO.getHoraInfraccion() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getFechaInfraccion().toString() + " "
                            + unificacionInconsistenciasComparendoDTO.getHoraInfraccion().toString());
                } else {
                    registros.add(null);
                }
                // Placa vehiculo
                if (unificacionInconsistenciasComparendoDTO.getPlacaVehiculo() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getPlacaVehiculo());
                } else {
                    registros.add(null);
                }
                // Tipo de documento del infractor
                if (unificacionInconsistenciasComparendoDTO.getNombreTipoIdentificacionInfractor() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNombreTipoIdentificacionInfractor());
                } else {
                    registros.add(null);
                }
                // Numero documento del infractor
                if (unificacionInconsistenciasComparendoDTO.getNumeroIdentificacionInfractor() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNumeroIdentificacionInfractor());
                } else {
                    registros.add(null);
                }
                // Nombre 1 del infractor
                if (unificacionInconsistenciasComparendoDTO.getNombre1Infractor() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNombre1Infractor());
                } else {
                    registros.add(null);
                }
                // Apellido 1 del infractor
                if (unificacionInconsistenciasComparendoDTO.getApellido1Infractor() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getApellido1Infractor());
                } else {
                    registros.add(null);
                }
                // Nombre de campo con inconsistencia
                if (unificacionInconsistenciasComparendoDTO.getNombreCampoInconsistencias() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getNombreCampoInconsistencias());
                } else {
                    registros.add(null);
                }
                // Error de procesamiento
                if (unificacionInconsistenciasComparendoDTO.getErrorProcesamiento() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getErrorProcesamiento());
                } else {
                    registros.add(null);
                }
                // Usuario
                if (unificacionInconsistenciasComparendoDTO.getLoginUsuario() != null) {
                    registros.add(unificacionInconsistenciasComparendoDTO.getLoginUsuario());
                } else {
                    registros.add(null);
                }
                filaArchivoExcelAux.setCeldas(registros);
                filaArchivoList.add(filaArchivoExcelAux);
            }
        }
        return escribirArchivo(filaArchivoList, EnumTipoArchivo.XLS);
    }

    /**
     * Escribe un archivo a partir de una lista de filas abstractas con el tipo indicado
     * 
     * @param filaArchivoList
     *            Lista de filas abstractas
     * @param tipoArchivo
     *            Tipo de archivo
     * @return Archivo en memoria
     */
    public static byte[] escribirArchivo(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenido = archivoFactory.obtenerContenido(tipoArchivo, filaArchivoList);
        return iArchivo.escribirArchivo(contenido, tipoArchivo);
    }
}