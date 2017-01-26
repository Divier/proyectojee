package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ErrorCampoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaRecibirComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.helpers.comparendos.RecibirComparendoHelper;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.local.interfaces.IProcesarRecibirComparendo;
import co.com.datatools.c2.negocio.local.interfaces.IRecibirComparendoColombia;

/**
 * Logica asociada al proceso de recibir y procesar comparendo
 * 
 * @author giovanni.velandia
 * @version 28-Mar-2016 2.21
 */
@Stateless(name = "RecibirComparendoColombiaEJB")
@LocalBean
public class RecibirComparendoColombiaEJB implements IRecibirComparendoColombia {
    private final static Logger logger = Logger.getLogger(RecibirComparendoColombiaEJB.class.getName());

    @EJB
    private IProcesarRecibirComparendo iProcesarRecibirComparendo;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    private static int LONGITUD_NO_APLICA = -1;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaRecibirComparendoDTO procesarRecibirComparendo(ProcesarComparendoDTO procesarComparendoDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoColombiaEJB::procesarRecibirComparendo(ProcesarComparendoDTO)");
        // Objeto de respuesta
        RespuestaRecibirComparendoDTO respuestaValidacionDTO = new RespuestaRecibirComparendoDTO();
        // Valida que el pais al cual se esta ingresando el comparedno sea de colombia
        ValorParametroDTO valorParametroDTO = iProcesarRecibirComparendo.consultarValorParametro(
                EnumParametro.PAIS_INSTALACION,
                procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo());
        if (!iRSeguridadCirculemos.obtenerPais().getId().equals(valorParametroDTO.getValorParamInt())) {
            throw new CirculemosRuntimeException("El pais seleccionado no es colombia");
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
        ConfiguracionInfraccionDTO configuracionInfraccionDTO = iProcesarRecibirComparendo
                .consultarConfiguracionInfraccion(procesarComparendoDTO.getProcesaComparendoDTO());

        if (configuracionInfraccionDTO == null) {
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.INFRACCION,
                    EnumErrorProcesamiento.INFRACCION_NO_EXISTE, respuestaValidacionDTO);
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
         * Validacion propias de colombia
         */
        validacionesCamposProcesarComparendo(procesarComparendoDTO, configuracionInfraccionDTO, respuestaValidacionDTO);

        // PASO 3 FLUJO BASICO Origen validacion
        iProcesarRecibirComparendo.origenValidacion(procesarComparendoDTO, respuestaValidacionDTO);
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

        // Validar reglas comparendo propias de colombia
        respuestaValidacionDTO = validarReglasComparendo(procesarComparendoDTO, configuracionInfraccionDTO);

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
                    procesarComparendoDTO.getProcesaComparendoDTO(), EnumActividad.RECTIFICACION_COMPARENDO,
                    EnumActividad.IMPOSICION_DEL_COMPARENDO_RECTIFICADO);
        }

        // se genera el cambio de estado procesa comparendo si es una correccion de inconsistencia
        if (procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.CORREGIR_INCONSISTENCIA)) {
            // codigo del comparendo
            procesarComparendoDTO.getProcesaComparendoDTO().setComparendo(comparendoDTO);
            // Fecha correccion
            procesarComparendoDTO.getProcesaComparendoDTO().setFechaCorreccion(Calendar.getInstance().getTime());
            iProcesarRecibirComparendo.actualizarProcesaComparendo(procesarComparendoDTO.getProcesaComparendoDTO());
        }

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
        if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
            if (!procesarComparendoDTO.getProcesaComparendoDTO().getIdAgenteTransito()
                    .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                iProcesarRecibirComparendo.registroCambioEstadoformulario(
                        procesarComparendoDTO.getProcesaComparendoDTO(), configuracionInfraccionDTO);
            }
        }

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
     * Validacion de campos procesa comparendo obligatorios para ecuador
     */
    private void validacionesCamposProcesarComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO, RespuestaValidacionDTO respuestaValidacionDTO)
            throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEcuadorEJB::validacionesCamposProcesarComparendo(ProcesarComparendoDTO)");

        // Codigo del estado ESTA ES PROPIA DE GUAYAQUIL Y SE DESEA EN GENERAL PASAR AL NEGOCIO CENTRAR
        // Numero de comparendo
        if (procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo() == null
                || procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo().isEmpty()) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.FALTA_NUMERO_COMPARENDO, respuestaValidacionDTO);
        } else {
            // Validar si aplica longitud
            boolean longitud = false;
            if (EnumCamposDetalleComparendo.NUMERO_COMPARENDO.getLongitud() != LONGITUD_NO_APLICA) {
                longitud = RecibirComparendoHelper.validarLongitud(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                        procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo());
                if (longitud) {
                    // ERROR
                    iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                            EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                            EnumErrorProcesamiento.NUMERO_COMPARENDO_INVALIDO, respuestaValidacionDTO);
                } else {
                    if (!procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoAgenteImpositor()
                            .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                        try {
                            boolean valido = iProcesarRecibirComparendo.validarFormatoNumeroComparendo(
                                    procesarComparendoDTO.getProcesaComparendoDTO(), configuracionInfraccionDTO);

                            if (!valido) {
                                // ERROR
                                iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                                        EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                                        EnumErrorProcesamiento.NUMERO_COMPARENDO_INVALIDO, respuestaValidacionDTO);
                            }

                        } catch (CirculemosNegocioException e) {
                            // ERROR
                            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                                    EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                                    EnumErrorProcesamiento.NUMERO_COMPARENDO_INVALIDO, respuestaValidacionDTO);
                        }
                    }
                }
            }
        }

        // DATOS AGENTE
        // VALIDACION PROPIA de colombia si es general validar para pasar al negocio central
        iProcesarRecibirComparendo.validarCamposAgente(procesarComparendoDTO.getProcesaComparendoDTO(),
                configuracionInfraccionDTO, respuestaValidacionDTO);

        // Existe fuga del infractor
        if (procesarComparendoDTO.getProcesaComparendoDTO().getExisteFugaInfractor() == null) {
            // ERROR
            iProcesarRecibirComparendo.errorRechazoRespuestaValidacion(
                    EnumCamposDetalleComparendo.EXISTE_FUGA_INFRACTOR,
                    EnumErrorProcesamiento.FALTA_EXISTE_FUGA_INFRACTOR, respuestaValidacionDTO);
        }
    }

    /**
     * Validacion de reglas de comparendo propias de ecuador
     * 
     * @param procesarComparendoDTO
     * @param configuracionInfraccionDTO
     * @return
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     */
    private RespuestaRecibirComparendoDTO validarReglasComparendo(ProcesarComparendoDTO procesarComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) throws CirculemosNegocioException {
        logger.debug("RecibirComparendoEJB::validarReglasComparendo(ProcesarComparendoDTO,ConfiguracionInfraccionDTO)");
        RespuestaRecibirComparendoDTO respuestaValidacionDTO = new RespuestaRecibirComparendoDTO();
        respuestaValidacionDTO.setDetalleProcesamientoDTOs(new ArrayList<DetalleProcesamientoDTO>());

        // Valida que no sea una validaciond e origen rectificacion de comparendo
        if (!procesarComparendoDTO.getEnumProcesamiento().equals(EnumProcesamiento.RECTIFICAR_COMPARENDO)) {
            boolean existeFormulario = iProcesarRecibirComparendo.existeFormulario(
                    procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo(),
                    Integer.parseInt(iProcesarRecibirComparendo.consultarConfiguracion008(configuracionInfraccionDTO)
                            .getTipoFormulario().get(0)));
            if (existeFormulario) {
                if (procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoAgenteImpositor()
                        .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                    // ERROR
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(iProcesarRecibirComparendo.crearDetalleProcesamiento(
                                    EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                                    EnumErrorProcesamiento.COMPARENDO_POLCA_EXISTENTE));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                } else {

                    // Preguntamos si el numero de formulario este registrado con el estado asignado o pendiente por asignar y organismo de transito
                    EnumEstadoFomulario[] enumEstadoFomularios = { EnumEstadoFomulario.ASIGNADO,
                            EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR };

                    boolean existe = iProcesarRecibirComparendo.existeEstadoFormularioOrganismo(
                            procesarComparendoDTO.getProcesaComparendoDTO().getNumeroComparendo(),
                            Integer.parseInt(iProcesarRecibirComparendo
                                    .consultarConfiguracion008(configuracionInfraccionDTO).getTipoFormulario().get(0)),
                            procesarComparendoDTO.getProcesaComparendoDTO().getOrganismoTransito().getCodigoOrganismo(),
                            enumEstadoFomularios);
                    // si no existe generamos un error para la respuesta validacion
                    if (!existe) {
                        // ERROR
                        respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                                .add(iProcesarRecibirComparendo.crearDetalleProcesamiento(
                                        EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                                        EnumErrorProcesamiento.COMPARENDO_NO_ASIGNADO));
                        respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                    }
                }
            } else {
                if (!procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoAgenteImpositor()
                        .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                    // ERROR
                    respuestaValidacionDTO.getDetalleProcesamientoDTOs()
                            .add(iProcesarRecibirComparendo.crearDetalleProcesamiento(
                                    EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                                    EnumErrorProcesamiento.NUMERO_COMPARENDO_NO_EXISTE));
                    respuestaValidacionDTO.setCodigoResultado(EnumErrorProcesamiento.INCONSISTENTE.getCodigo());
                }
            }
        }

        // Validar existe agente si no es polca
        if (!procesarComparendoDTO.getProcesaComparendoDTO().getIdTipoAgenteImpositor()
                .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
            iProcesarRecibirComparendo.validarAgentePolca(procesarComparendoDTO.getProcesaComparendoDTO(),
                    respuestaValidacionDTO);
        }
        return respuestaValidacionDTO;
    }
}
