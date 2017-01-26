package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ErrorCampoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCampoEntidad;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.error.ErrorComparendo;
import co.com.datatools.c2.negocio.helpers.comparendos.RecibirComparendoHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ProcesaComparendoPersonaHelperExtend;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.local.interfaces.IProcesarRecibirComparendo;
import co.com.datatools.c2.negocio.local.interfaces.IRecibirComparendoEcuador;

/**
 * Logica asociada al proceso de recibir y procesar comparendo
 * 
 * @author giovanni.velandia
 * @version 28-Mar-2016 2.21
 */
@Stateless(name = "RecibirComparendoEcuadorEJB")
@LocalBean
public class RecibirComparendoEcuadorEJB implements IRecibirComparendoEcuador {
    private final static Logger logger = Logger.getLogger(RecibirComparendoEcuadorEJB.class.getName());

    @EJB
    private IProcesarRecibirComparendo iProcesarRecibirComparendo;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaRecibirComparendoDTO procesarRecibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEcuadorEJB::procesarRecibirComparendo(ProcesarComparendoDTO)");

        if (procesarComparendoDTO.getProcesaComparendoDTO().getIdFacturaAxis() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002002);
        }

        if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroCitacion() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002007);
        }

        if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrigen() == null) {
            throw new CirculemosNegocioException(ErrorComparendo.RecibirComparendo.COM_002008);
        }

        procesarComparendoDTO.getProcesaComparendoDTO()
                .setNumeroComparendo(procesarComparendoDTO.getProcesaComparendoDTO().getIdFacturaAxis().toString());

        // Validaciones de negocio

        // Validamos la integridad del infractor
        validarInfractor(procesarComparendoDTO.getProcesaComparendoDTO());

        // Validar Agente
        validarAgente(procesarComparendoDTO.getProcesaComparendoDTO());

        // Objeto de respuesta
        RespuestaRecibirComparendoDTO respuestaValidacionDTO = new RespuestaRecibirComparendoDTO();
        // Valida que el pais al cual se esta ingresando el comparedno sea de colombia
        ValorParametroDTO valorParametroDTO = iProcesarRecibirComparendo.consultarValorParametro(
                EnumParametro.PAIS_INSTALACION,
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
        if (!iRSeguridadCirculemos.obtenerPais().getId().equals(valorParametroDTO.getValorParamInt())) {
            throw new CirculemosRuntimeException("El pais seleccionado no es ecuador");
        }

        // PASO 1 FLUJO BASICO Recibe los datos del comparendo descritos en la sección de datos de entrada y salida.
        // Valida los datos en su estructura de cada uno de los campos del procesa comparendo
        List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs = RecibirComparendoHelper
                .validarDatosProcesaComparendo(procesarComparendoDTO.getProcesaComparendoDTO());
        if (errorCampoComparendoDTOs != null && !errorCampoComparendoDTOs.isEmpty()) {
            for (ErrorCampoComparendoDTO errorCampoComparendoDTO : errorCampoComparendoDTOs) {
                // ERROR
                iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                        errorCampoComparendoDTO.getEnumCamposDetalleComparendo(),
                        errorCampoComparendoDTO.getEnumErrorProcesamiento(), respuestaValidacionDTO);
            }
        }

        // PASO 2 FLUJO BASICO Realiza la validación de datos recibidos en cuanto a longitud, reglas establecidas y formato de los datos recibidos.
        // Validacion campos para consulta de la configuracion
        // **** Validaciones necesarias para la consulta de la configuracion de infraccion
        iProcesarRecibirComparendo.validacionesCamposProcesarComparendo(procesarComparendoDTO.getProcesaComparendoDTO(),
                respuestaValidacionDTO);

        /*
         * Informacion de la configuracion de infraccion
         */

        // Verificamos que la infraccion sea correcta
        boolean validarConfiguracion = true;
        if (!iProcesarRecibirComparendo
                .existeInfraccion(procesarComparendoDTO.getProcesaComparendoDTO().getCodigoInfraccion())) {
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.INFRACCION,
                    EnumErrorProcesamiento.INFRACCION_NO_EXISTE, respuestaValidacionDTO);
            validarConfiguracion = false;
        }

        ConfiguracionInfraccionDTO configuracionInfraccionDTO = iProcesarRecibirComparendo
                .consultarConfiguracionInfraccion(procesarComparendoDTO.getProcesaComparendoDTO());

        if (configuracionInfraccionDTO == null) {
            if (validarConfiguracion) {
                iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.INFRACCION,
                        EnumErrorProcesamiento.INFRACCION_NO_SE_ENCUENTRA_VIGENTE, respuestaValidacionDTO);
            }
        } else {

            /*
             * Agregamos el tipo comparendo inferido en la configuracion
             */
            procesarComparendoDTO.getProcesaComparendoDTO()
                    .setIdTipoComparendo(configuracionInfraccionDTO.getInfraccion().getTipoComparendo().getId());

            /*
             * VALIDACIONES DATOS COMPARENDO BLOQUEANTES: Recibe los datos del comparendo descritos en la sección de datos de entrada y salida.
             * Realiza la validación de datos recibidos en cuanto a longitud, reglas establecidas y formato de los datos recibidos. Realiza las
             * validaciones de origen de validaciones
             */
            iProcesarRecibirComparendo.validacionesCamposProcesarComparendo(procesarComparendoDTO,
                    configuracionInfraccionDTO, respuestaValidacionDTO);
        }

        /*
         * Validacion propias de ecuador
         */
        validacionesCamposProcesarComparendo(procesarComparendoDTO, configuracionInfraccionDTO, respuestaValidacionDTO);

        // PASO 3 FLUJO BASICO Origen validacion
        origenValidacion(procesarComparendoDTO, respuestaValidacionDTO);
        if (respuestaValidacionDTO != null && respuestaValidacionDTO.getCodigoResultado() != null) {
            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getValue())) {

                // Se debe ingresar a la base de datos los errores de rechazo
                iProcesarRecibirComparendo.registroDetalleBloqueo(respuestaValidacionDTO,
                        procesarComparendoDTO.getProcesaComparendoDTO());
                // Log
                iProcesarRecibirComparendo.crearLogC2(EnumErrorProcesamiento.RECHAZADO, procesarComparendoDTO,
                        respuestaValidacionDTO);
                return respuestaValidacionDTO;
            }
        }

        /*
         * PASO 4 FLUJO BASICO al PASO 20 FLUJO BASICO Validaciones de reglas de negocio desde el paso 4 hasta el 20 en adelante
         */
        respuestaValidacionDTO = iProcesarRecibirComparendo.validarReglasComparendo(procesarComparendoDTO,
                configuracionInfraccionDTO);

        if (respuestaValidacionDTO.getCodigoResultado() != null) {
            if (respuestaValidacionDTO.getCodigoResultado().equals(EnumErrorProcesamiento.INCONSISTENTE.getCodigo())) {

                // **** **** FLUJO ALTERNO 4 PASO 1 **** ****
                // El “origen validación” NO es IGUAL A Corrección de inconsistencia y este comparendo NO se encuentra almacenado
                // como comparendo inconsistente
                boolean existeComparendo = false;
                if (iProcesarRecibirComparendo
                        .consultarProcesaComparendo(procesarComparendoDTO.getProcesaComparendoDTO().getId()) != null) {
                    existeComparendo = true;
                }
                if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)
                        && !existeComparendo) {
                    // Verificamos que no sea rectificacion de inconsistencias
                    if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
                        // **** **** FLUJO ALTERNO 4 PASO 2 **** ****
                        // Llamamos el metodo de registrar procesar comparendo
                        iProcesarRecibirComparendo.registrarProcesaComparendo(procesarComparendoDTO,
                                respuestaValidacionDTO);
                    }
                }

                // Log
                iProcesarRecibirComparendo.crearLogC2(EnumErrorProcesamiento.INCONSISTENTE, procesarComparendoDTO,
                        respuestaValidacionDTO);
                return respuestaValidacionDTO;
            }
        }

        /*
         * **** **** PASO 21 FLUJO BASICO **** **** Verificamos que no exista ninguna inconsistencia para el registro del comparendo
         */
        // **** **** PASO 22 FLUJO BASICO **** ****
        // Llamamos el metodo registrar comparendo
        ComparendoDTO comparendoDTO = null;
        if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
            comparendoDTO = iProcesarRecibirComparendo.registrarComparendo(procesarComparendoDTO,
                    respuestaValidacionDTO, configuracionInfraccionDTO);

            // Verificamos si el comparendo fue reservado solo cuando el origen sea diferente de null
            if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoOrigen() != null) {
                boolean existe = iProcesarRecibirComparendo.verificarComparendoResevado(
                        procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo());
                if (existe) {
                    iProcesarRecibirComparendo.consumirReservaOCN(procesarComparendoDTO.getProcesaComparendoDTO());
                }
            }
        } else {
            comparendoDTO = iProcesarRecibirComparendo.rectificacionesComparendo(
                    procesarComparendoDTO.getProcesaComparendoDTO(), EnumActividad.SUSTITUCION,
                    EnumActividad.IMPOSICION_SUSTITUCION);
            if (comparendoDTO == null) {
                respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.REGISTRADO.getCodigo());
                return respuestaValidacionDTO;
            }
        }

        // Se retira la correcion de inconsistencias si en algun momento se requiere de nuevo tomar del principal
        // SI SE REQUIERE SACAR DEL NEGOCIO PRINCIPAL
        // se genera el cambio de estado procesa comparendo si es una correccion de inconsistencia

        /*
         * **** **** PASO 24 FLUJO BASICO **** **** Verificamos si se genera cartera ya con la configuracion de la infraccion
         */
        boolean registroNotificacionesConfiguracion = false;
        if (configuracionInfraccionDTO.getGeneraCartera()) {
            iProcesarRecibirComparendo.generarCartera(procesarComparendoDTO, configuracionInfraccionDTO, comparendoDTO);
        } else {
            // Flujo alterno FA17
            // Validar el tipo de comparendo PASO 1
            if (procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                    .equals(EnumMedioImposicion.ELECTRONICO_DEAP.getPk())
                    || procesarComparendoDTO.getProcesaComparendoDTO().getCodigoMedioImposicion()
                            .equals(EnumMedioImposicion.MANUAL.getPk())) {

                // Registramos la notificacion actualizando el comparendo PASO 2
                registroNotificacionesConfiguracion = true;
            } else {

                // **** **** FLUJO BASICO 18 **** ****
                // Medio de Imposición” del comparendo recibido es DIFERENTE a MANUAL o DEAP
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoNotificacionComparendo() != null
                        && procesarComparendoDTO.getProcesaComparendoDTO().getFechaNotificacion() != null) {
                    // Los campos “Tipo de Notificación del Comparendo” y “Fecha Notificación” traen información asociada
                    iProcesarRecibirComparendo.registroNotificaciones(comparendoDTO,
                            procesarComparendoDTO.getProcesaComparendoDTO(), configuracionInfraccionDTO);
                } else {
                    registroNotificacionesConfiguracion = false;
                }
            }
        }

        // Cambiar estado formulario **** **** PASO 34 FLUJO BASICO **** ****
        // Para la instancia de ecuador no aplica el cambio estado del formulario

        /*
         * **** Registro notificaciones ****
         */
        if (registroNotificacionesConfiguracion) {
            iProcesarRecibirComparendo.registroNotificacionesConfiguracion(comparendoDTO, procesarComparendoDTO,
                    configuracionInfraccionDTO);
        }

        // Registra comparendo con todas las modificaciones
        if (comparendoDTO != null) {
            iProcesarRecibirComparendo.actualizarComparendo(comparendoDTO);
        }
        // RESPUESTA EXITO **** **** FLUJO BASICO 35 **** ****
        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.REGISTRADO.getCodigo());

        // Log
        iProcesarRecibirComparendo.crearLogC2(EnumErrorProcesamiento.REGISTRADO, procesarComparendoDTO,
                respuestaValidacionDTO);

        // Respuesta Informacion notificacion
        respuestaValidacionDTO.setCicompareno(comparendoDTO.getCicomparendo());
        respuestaValidacionDTO.setCodigoOrganismoTransito(
                comparendoDTO.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        return respuestaValidacionDTO;
    }

    /**
     * Se encarga de validar el agente buscando datos en circulemos 2
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     */
    private void validarAgente(ProcesaComparendoDTO procesaComparendoDTO) {
        logger.debug("RecibirComparendoEcuadorEJB::validarAgente(ProcesarComparendoDTO)");
        // Verificamos la existencia de la placa
        if (procesaComparendoDTO.getPlacaAgente() != null) {
            AgenteDTO agenteDTO = new AgenteDTO();
            agenteDTO.setPlaca(procesaComparendoDTO.getPlacaAgente());
            agenteDTO = iProcesarRecibirComparendo.consultarAgente(agenteDTO);
            // Verificamos si existe el agente en circulemos 2
            if (agenteDTO != null) {
                // Nombre 1 agente
                procesaComparendoDTO.setNombre1Agente(agenteDTO.getPersona().getNombre1());
                // Nombre 2 agente
                procesaComparendoDTO.setNombre2Agente(agenteDTO.getPersona().getNombre2());
                // Apellido 1 agente
                procesaComparendoDTO.setApellido1Agente(agenteDTO.getPersona().getApellido1());
                // Apellido 2 agente
                procesaComparendoDTO.setApellido2Agente(agenteDTO.getPersona().getApellido2());
                // Id agente
                procesaComparendoDTO.setIdAgenteTransito(agenteDTO.getId());
            }
        }
    }

    /**
     * Se encarga de validar el infractor buscando datos en prsonas de circulemos 2
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     */
    private void validarInfractor(ProcesaComparendoDTO procesaComparendoDTO) {
        logger.debug("RecibirComparendoEcuadorEJB::validarInfractor(ProcesarComparendoDTO)");
        if (procesaComparendoDTO.getProcesaComparendoPersonas() != null
                && !procesaComparendoDTO.getProcesaComparendoPersonas().isEmpty()) {
            ProcesaComparendoPersonaDTO personaComPerValidarDTO = null;
            for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                    .getProcesaComparendoPersonas()) {
                if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {

                    /*
                     * Revisamos la informacion y verificamos que si es RUC debemos tratar los datos de la razon social
                     */
                    if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null
                            && procesaComparendoPersonaDTO.getNumeroIdentificacion() != null) {
                        if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null
                                && procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                                        .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                            if (procesaComparendoPersonaDTO.getRazonSocial() == null) {
                                if (procesaComparendoPersonaDTO.getNombre1() != null) {
                                    procesaComparendoPersonaDTO
                                            .setRazonSocial(procesaComparendoPersonaDTO.getNombre1());
                                } else if (procesaComparendoPersonaDTO.getNombre2() != null) {
                                    procesaComparendoPersonaDTO
                                            .setRazonSocial(procesaComparendoPersonaDTO.getNombre2());
                                } else if (procesaComparendoPersonaDTO.getApellido1() != null) {
                                    procesaComparendoPersonaDTO
                                            .setRazonSocial(procesaComparendoPersonaDTO.getApellido1());
                                } else if (procesaComparendoPersonaDTO.getApellido2() != null) {
                                    procesaComparendoPersonaDTO
                                            .setRazonSocial(procesaComparendoPersonaDTO.getApellido2());
                                }
                            }

                            // Limpiamos los campos de persona natrual
                            procesaComparendoPersonaDTO.setApellido1(null);
                            procesaComparendoPersonaDTO.setApellido2(null);
                            procesaComparendoPersonaDTO.setNombre1(null);
                            procesaComparendoPersonaDTO.setNombre2(null);

                            personaComPerValidarDTO = procesaComparendoPersonaDTO;
                        } else {
                            procesaComparendoPersonaDTO.setRazonSocial(null);
                            procesaComparendoPersonaDTO.setDigitoVerificacionNit(null);
                            personaComPerValidarDTO = procesaComparendoPersonaDTO;
                        }
                    }
                    break;
                }
            }

            // Validacion de dirrecion del infractor
            validarDireccionInfractor(procesaComparendoDTO, personaComPerValidarDTO);

            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs = new ArrayList<ErrorCampoComparendoDTO>();
            RecibirComparendoHelper.validarDatosProcesaComparendoInfractor(procesaComparendoDTO,
                    errorCampoComparendoDTOs);
            if (errorCampoComparendoDTOs != null && !errorCampoComparendoDTOs.isEmpty()) {
                // Validaciones que se retiran para este procesamiento}
                List<ErrorCampoComparendoDTO> errorCampoComparendoTempDTOs = new ArrayList<ErrorCampoComparendoDTO>();
                for (ErrorCampoComparendoDTO errorCampoComparendoDTO : errorCampoComparendoDTOs) {
                    // Correo electronico
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.EMAIL_INFRACTOR_PCC.getValue().intValue()) {
                        personaComPerValidarDTO.setEmail(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                    // Direccion
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.DIRECCION_INFRACTOR_PCC.getValue().intValue()) {
                        personaComPerValidarDTO.setProcesaDireccion(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                    // Telefono fijo
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.TELEFONO_FIJO_INFRACTOR_PCC.getValue().intValue()) {
                        personaComPerValidarDTO.setTelefonoFijo(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                    // Telefono Movil
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.TELEFONO_MOVIL_INFRACTOR_PCC.getValue().intValue()) {
                        personaComPerValidarDTO.setTelefonoMovil(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                    // Segundo nombre
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.SEGUNDO_NOMBRE_INFRACTOR_PCC.getValue().intValue()) {
                        personaComPerValidarDTO.setNombre2(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                    // Segundo apellido
                    if (errorCampoComparendoDTO.getEnumCamposDetalleComparendo()
                            .getCodigoCampo() == EnumCampoEntidad.SEGUNDO_APELLIDO_INFRACTOR_PCC.getValue()
                                    .intValue()) {
                        personaComPerValidarDTO.setApellido2(null);
                        errorCampoComparendoTempDTOs.add(errorCampoComparendoDTO);
                    }
                }

                // Recorremos os errores si son de los validados a retirar los retiramos de la lista general
                for (ErrorCampoComparendoDTO errorCampoComparendoDTO : errorCampoComparendoTempDTOs) {
                    errorCampoComparendoDTOs.remove(errorCampoComparendoDTO);
                }

                if (errorCampoComparendoDTOs != null && !errorCampoComparendoDTOs.isEmpty()) {
                    PersonaDTO personaDTO = iProcesarRecibirComparendo.consultarPersona(
                            personaComPerValidarDTO.getIdTipoIdentificacion(),
                            personaComPerValidarDTO.getNumeroIdentificacion());
                    if (personaDTO != null) {
                        procesaComparendoDTO.getProcesaComparendoPersonas().set(
                                procesaComparendoDTO.getProcesaComparendoPersonas().indexOf(personaComPerValidarDTO),
                                ProcesaComparendoPersonaHelperExtend.toProcesaComparendoPersonaUbicabiliad(personaDTO,
                                        personaComPerValidarDTO));
                    }
                }
            } else {
                boolean consultarPersona = false;

                if (personaComPerValidarDTO != null) {
                    // Razon social
                    if (personaComPerValidarDTO.getIdTipoIdentificacion()
                            .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                        if (personaComPerValidarDTO.getRazonSocial() == null
                                || personaComPerValidarDTO.getRazonSocial().isEmpty()) {
                            consultarPersona = true;
                        }
                    } else {
                        // Falta apellido 1 infractor
                        if (personaComPerValidarDTO.getApellido1() == null) {
                            consultarPersona = true;
                        }
                        // Falta nombre 1 infractor
                        if (personaComPerValidarDTO.getNombre1() == null) {
                            consultarPersona = true;
                        }
                    }
                }
                if (consultarPersona) {
                    PersonaDTO personaDTO = iProcesarRecibirComparendo.consultarPersona(
                            personaComPerValidarDTO.getIdTipoIdentificacion(),
                            personaComPerValidarDTO.getNumeroIdentificacion());
                    if (personaDTO != null) {
                        procesaComparendoDTO.getProcesaComparendoPersonas().set(
                                procesaComparendoDTO.getProcesaComparendoPersonas().indexOf(personaComPerValidarDTO),
                                ProcesaComparendoPersonaHelperExtend.toProcesaComparendoPersonaUbicabiliad(personaDTO,
                                        personaComPerValidarDTO));
                    }
                }
            }
        }
    }

    /**
     * Se encarga de validar la dirreccion del infractor
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @param personaComPerValidarDTO
     */
    private void validarDireccionInfractor(ProcesaComparendoDTO procesaComparendoDTO,
            ProcesaComparendoPersonaDTO personaComPerValidarDTO) {
        RespuestaValidacionDTO respuestaValidacionDTO = new RespuestaValidacionDTO();
        iProcesarRecibirComparendo.validarDirecciones(procesaComparendoDTO, respuestaValidacionDTO);
        if (respuestaValidacionDTO.getDetalleProcesamientoDTOs() != null
                && !respuestaValidacionDTO.getDetalleProcesamientoDTOs().isEmpty()) {
            for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacionDTO
                    .getDetalleProcesamientoDTOs()) {
                if (detalleProcesamientoDTO.getErrorProcesamiento().getCodigo()
                        .equals(EnumErrorProcesamiento.DIRECCION_INFRACTOR_INVALIDA.getCodigo())) {
                    personaComPerValidarDTO.setProcesaDireccion(null);
                    break;
                }
            }
        }
    }

    /**
     * Metodo que se encarga de validar el origen de validacion con al nueva especificacion de guayaquil ************ESTE METODO SI SE LLEGA A
     * COMPORTAR IGUAL QUE EN COLOMBIA REGRESARA AL NEGOCIO PRINCIPAL**************************
     * 
     * @param procesarComparendoDTO
     * @param respuestaValidacionDTO
     */
    private void origenValidacion(ProcesarComparendoDTO procesarComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEcuadorEJB::origenValidacion(ProcesarComparendoDTO)");

        // Origen validacion
        if (procesarComparendoDTO.getEnumProcesamiento() == null) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(null,
                    EnumErrorProcesamiento.FALTA_CAMPO_ORIGEN_VALIDACION, respuestaValidacionDTO);
        } else {

            // **** PASO 3 ****
            // Para el caso de recibir comparendo verificamos que no se encuentre almacenado en procesar comparendo
            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECIBIR_COMPARENDO)) {
                validarExistenciaComparendo(procesarComparendoDTO.getProcesaComparendoDTO(), respuestaValidacionDTO);
            }

            if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
                iProcesarRecibirComparendo.validarRectificaComparendo(
                        procesarComparendoDTO.getProcesaComparendoDTO().getComparendo().getCicomparendo());
            }
        }
    }

    /**
     * Validacion de campos procesa comparendo obligatorios para ecuador
     */
    private void validacionesCamposProcesarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEcuadorEJB::validacionesCamposProcesarComparendo(ProcesarComparendoDTO)");

        // Codigo del estado ESTA ES PROPIA DE GUAYAQUIL Y SE DESEA EN GENERAL PASAR AL NEGOCIO CENTRAR
        if (procesarComparendoDTO.getProcesaComparendoDTO().getIdEstadoComparendo() == null) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.ESTADO_COMPARENDO,
                    EnumErrorProcesamiento.FALTA_ESTADO_ORIGEN_COMPARENDO, respuestaValidacionDTO);
        } else {
            // Estado comparendo
            if (procesarComparendoDTO.getProcesaComparendoDTO().getIdEstadoComparendo()
                    .equals(EnumEstadoComparendo.ANULADO.getCodigo())) {
                // ERROR
                iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                        EnumCamposDetalleComparendo.ESTADO_COMPARENDO,
                        EnumErrorProcesamiento.ESTADO_COMPARENDO_INVALIDO, respuestaValidacionDTO);
            }
        }

        // Validamos la actividad ESTA ES PROPIA DE GUAYAQUIL Y SE DESEA EN GENERAL PASAR AL NEGOCIO CENTRAR
        if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
            if (procesarComparendoDTO.getProcesaComparendoDTO().getIdActividad() == null) {
                // ERROR
                iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NO_ES_CAMPO_PCC,
                        EnumErrorProcesamiento.FALTA_ACTIVIDAD_RECTIFICACION_COMPARENDO, respuestaValidacionDTO);
            }
        }

        // Numero de comparendo
        if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo() == null
                || procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo().isEmpty()) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.FALTA_NUMERO_COMPARENDO, respuestaValidacionDTO);
        }

        // Validacion correo electronico de persona
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesarComparendoDTO.getProcesaComparendoDTO()
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                if (procesaComparendoPersonaDTO.getEmail() != null
                        && !procesaComparendoPersonaDTO.getEmail().isEmpty()) {
                    if (procesaComparendoPersonaDTO.getEmail().length() > 50) {
                        iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                                EnumCamposDetalleComparendo.EMAIL_DEL_INFRACTOR,
                                EnumErrorProcesamiento.EMAIL_INFRACTOR_INVALIDO, respuestaValidacionDTO);
                    }
                }
                break;
            }
        }
    }

    /**
     * Se validara la existencia del comparedo COMPORTAR IGUAL QUE EN COLOMBIA REGRESARA AL NEGOCIO PRINCIPAL**************************
     * 
     * @param procesaComparendoDTO
     * @param respuestaValidacionDTO
     */
    private void validarExistenciaComparendo(ProcesaComparendoDTO procesaComparendoDTO,
            RespuestaValidacionDTO respuestaValidacionDTO) {
        logger.debug("RecibirComparendoEJB::validarExistenciaComparendo(ProcesarComparendoDTO,RespuestaValidacionDTO)");
        // Verificamos la existencia del comparendo
        boolean existe = iProcesarRecibirComparendo.existeComparendo(procesaComparendoDTO);

        if (existe) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.COMPARENDO_YA_EXISTE, respuestaValidacionDTO);
        }
    }
}
