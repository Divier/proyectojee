package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import co.com.datatools.c2.adaptador.dto.RegistroNotaCarteraDTO;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.ActividadDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ErrorCampoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.LiquidarTarifaInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.TrazabilidadComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumCarteraComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.mail.EmailValidator;

/**
 * Clase auxiliar para procesar objetos RecibirComparendoEJB
 * 
 * @author giovanni.velandia
 */
public class RecibirComparendoHelper {

    /**
     * Transporta un propietario a un infractor cuando se requiera en el negocio
     * 
     * @param procesaComparendoPersonaInfractorDTO
     * @return
     */
    public static ProcesaComparendoPersonaDTO toInfractor(
            ProcesaComparendoPersonaDTO procesaComparendoPersonaPropietarioDTO) {
        ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO = new ProcesaComparendoPersonaDTO();
        procesaComparendoPersonaDTO = procesaComparendoPersonaPropietarioDTO.clone();

        // Se Ingresa el nuevo tipo
        procesaComparendoPersonaDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getCodigo());
        return procesaComparendoPersonaDTO;
    }

    /**
     * Convierte una ProcesaComparendoPersonaDTO en un personaDTO
     * 
     * @param procesaComparendoPersonaDTO
     * @return
     * @author giovanni.velandia
     */
    public static PersonaDTO convertirProcesaComparendoPersonaDTO(
            ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO) {
        PersonaDTO personaDTO = new PersonaDTO();
        if (procesaComparendoPersonaDTO.getIdTipoIdentificacion() != null) {
            if (procesaComparendoPersonaDTO.getIdTipoIdentificacion()
                    .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();
                // Razon Social
                personaJuridicaDTO.setNombreComercial(procesaComparendoPersonaDTO.getRazonSocial());
                personaDTO = personaJuridicaDTO;
            }
        }
        // Apellido 1
        personaDTO.setApellido1(procesaComparendoPersonaDTO.getApellido1());
        // Apellido 2
        personaDTO.setApellido2(procesaComparendoPersonaDTO.getApellido2());
        // Codigo organismo de transito
        OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
        organismoTransitoDTO.setCodigoOrganismo(procesaComparendoPersonaDTO.getCodigoOrganismoLicencia());
        personaDTO.setOrganismoTransito(organismoTransitoDTO);
        // Digito Verificacion Nit
        personaDTO.setNumeroIdentificacion(procesaComparendoPersonaDTO.getNumeroIdentificacion());
        // Email
        // TODO: CAMBIO DISENIO UBICABILIDAD
        // personaDTO.setCorreoElectronico(procesaComparendoPersonaDTO.getEmail());
        // Tipo identificacion
        TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
        tipoIdentificacionPersonaDTO.setId(procesaComparendoPersonaDTO.getIdTipoIdentificacion());
        personaDTO.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
        // Nombre 1
        personaDTO.setNombre1(procesaComparendoPersonaDTO.getNombre1());
        // Nombre 2
        personaDTO.setNombre2(procesaComparendoPersonaDTO.getNombre2());
        return personaDTO;
    }

    /**
     * Metodo que se encarga de armar la liquidacion Tarifa infracion con un procesaComparendoDTO
     * 
     * @param procesaComparendoDTO
     * @param configuracionInfraccionDTO
     * @return
     */
    public static LiquidarTarifaInfraccionDTO toLiquidarTarifa(ProcesaComparendoDTO procesaComparendoDTO,
            ConfiguracionInfraccionDTO configuracionInfraccionDTO) {

        // Datos para liquidar la tarifa de la infraccion
        LiquidarTarifaInfraccionDTO liquidarTarifaInfraccionDTO = new LiquidarTarifaInfraccionDTO();
        liquidarTarifaInfraccionDTO.setFechaLiquidacion(procesaComparendoDTO.getFechaInfraccion());
        liquidarTarifaInfraccionDTO.setGradoAlcoholemia(procesaComparendoDTO.getGradoAlcoholemia());
        liquidarTarifaInfraccionDTO.setIdClaseServicio(procesaComparendoDTO.getIdTipoServicio());
        liquidarTarifaInfraccionDTO.setCodigoInfraccion(configuracionInfraccionDTO.getInfraccion().getCodigo());
        liquidarTarifaInfraccionDTO.setNiegaPruebaAlcoholemia(procesaComparendoDTO.getNiegaPruebaAlcoholemia());
        liquidarTarifaInfraccionDTO.setNumeroReincidencias(procesaComparendoDTO.getNumeroReincidencia());

        return liquidarTarifaInfraccionDTO;
    }

    /**
     * Se encarga de validar la logitud de un campo si es mayor el valorCmapo devuelve true
     * 
     * @param campo
     * @param valorCampo
     * @return boolean
     * @author giovanni.velandia
     */
    public static boolean validarLongitud(EnumCamposDetalleComparendo campo, String valorCampo) {
        if (!StringUtils.isBlank(valorCampo)) {
            if (valorCampo.length() > campo.getLongitud()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que se encarga de registrar la trazabilidad del comparendo
     * 
     * @param comparendoDTO
     * @author julio.pinzon
     * @param comparendosNotificacion
     */
    public static TrazabilidadComparendoDTO generarTrazabilidadComparendoDTO(ComparendoDTO comparendoDTO,
            EnumActividad comparendosNotificacion, Date fechaEjecucion, UsuarioPersonaDTO usuarioPersonaDTO) {

        /*
         * Registramos la trazabilidad del comparendo PASO 25
         */
        TrazabilidadComparendoDTO trazabilidadComparendoDTO = new TrazabilidadComparendoDTO();

        // Actividad
        ActividadDTO actividadDTO = new ActividadDTO();
        actividadDTO.setId(comparendosNotificacion.getValue());
        trazabilidadComparendoDTO.setActividad(actividadDTO);

        // Estado del comparendo
        trazabilidadComparendoDTO.setEstadoComparendo(comparendoDTO.getEstadoComparendo());
        // Fecha ejecucion
        trazabilidadComparendoDTO.setFechaEjecucion(fechaEjecucion);
        // Fecha y hora de registro
        trazabilidadComparendoDTO.setFechaRegistro(Calendar.getInstance().getTime());

        // Comparendo
        trazabilidadComparendoDTO.setComparendo(comparendoDTO);

        // Usuario
        trazabilidadComparendoDTO.setUsuario(usuarioPersonaDTO);
        return trazabilidadComparendoDTO;
    }

    /**
     * Se encarga de procesar la ruta del comparendo y la estructura del nombre del archivo
     * 
     * @param archivoTransportableDTO
     * @return
     * @author giovanni.velandia
     * @author MOD-sergio.torres (09-feb-2016) - Cambio en el manejo de rutas utilizando rutas din�micas
     */
    public static ArchivoTransportableDTO procesarArchivoTransportable(String numeroComparendo, String codigoEvidencia,
            ArchivoTransportableDTO archivoTransportableDTO, String rutaInconsistencia) {

        // Escenario cuando corresponmde a una inconsistencia
        if (rutaInconsistencia != null) {
            archivoTransportableDTO.setRuta(rutaInconsistencia);
            archivoTransportableDTO.appendRuta(String
                    .format(EnumCategoriaDocumento.EVIDENCIA_COMPARENDO_INCONSISTENTE.getFormato(), numeroComparendo));
        }
        // Escenario cuando corresponde ala evidencia de un comparendo (NO incinsistencia)
        else {
            archivoTransportableDTO
                    .setRuta(String.format(EnumCategoriaDocumento.EVIDENCIA_COMPARENDO.getFormato(), numeroComparendo));
        }
        archivoTransportableDTO.setNombre(codigoEvidencia + "-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmm")
                + "." + FilenameUtils.getExtension(archivoTransportableDTO.getNombre()));
        return archivoTransportableDTO;
    }

    /**
     * Crear nueva instancia de generacion de documento para resolucion de rectificacion de un comparendo se o no anulada la de sancion
     * 
     * @param rectificacion
     *            dto con los datos a cargar en generacion de documentoDTO.
     * @param enumTipoDocumentoGenerado
     *            tipo de documento a ser generado
     * @return generaDocumentoDTO con sus respectivos datos necesarios para la carga del documento
     * @author luis.forero(2016-02-02)
     */
    public static GeneraDocumentoDTO newInstanceGeneraDocumentoDTO(final ProcesaComparendoRectificadoDTO rectificacion,
            EnumTipoDocumentoGenerado enumTipoDocumentoGenerado) {
        final GeneraDocumentoDTO generaDocumentoDTO = new GeneraDocumentoDTO();
        generaDocumentoDTO.setCodigoOrganismo(
                rectificacion.getComparendo().getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
        generaDocumentoDTO.setFechaGeneracion(new java.util.Date());
        generaDocumentoDTO.setIdTipoDocumentoGenerado(enumTipoDocumentoGenerado);
        generaDocumentoDTO.setCicomparendo(rectificacion.getComparendo().getCicomparendo());
        return generaDocumentoDTO;
    }

    /**
     * Metodo que se encarga de crear el objeto registro cartera
     * 
     * @param comparendo
     * @param procesaComparendoDTO
     * @author giovanni.velandia
     * @return
     */
    public static RegistroNotaCarteraDTO toRegistroNotaCarteraDTO(ComparendoDTO comparendoDTO,
            ProcesaComparendoDTO procesaComparendoDTO) {
        RegistroNotaCarteraDTO registroNotaCarteraDTO = new RegistroNotaCarteraDTO();
        // Cicomparendo
        registroNotaCarteraDTO.setCicomparendo(comparendoDTO.getCicomparendo());
        // Codigo organismo de transito
        registroNotaCarteraDTO
                .setCodigoOrganismoTransito(procesaComparendoDTO.getOrganismoTransito().getCodigoOrganismo());
        // Enum cartera comparendo
        registroNotaCarteraDTO.setEnumCarteraComparendo(EnumCarteraComparendo.CONC_CART_NC_NOTA_RECT);
        // Fecha obligacion

        registroNotaCarteraDTO.setFechaObligacion(procesaComparendoDTO.getFechaInfraccion());
        // Buscar en la lista de personas el infractor para saber el tipo de documento
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                // Tipo documento
                registroNotaCarteraDTO.setIdTipoDocumento(procesaComparendoPersonaDTO.getIdTipoIdentificacion());
                // Numero documento
                registroNotaCarteraDTO.setNumeroDocumento(procesaComparendoPersonaDTO.getNumeroIdentificacion());
                break;
            }
        }
        return registroNotaCarteraDTO;
    }

    /**
     * Metodo que se encarga de validar la integridad (Longitud y contenido) de los datos de un ProcesaComparendo.
     * 
     * @param procesaComparendoDTO
     * @author giovanni.velandia
     */
    public static List<ErrorCampoComparendoDTO> validarDatosProcesaComparendo(
            ProcesaComparendoDTO procesaComparendoDTO) {
        List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs = new ArrayList<ErrorCampoComparendoDTO>();

        // Encabezado
        validarDatosProcesaComparendoEncabezado(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Vehiculo
        validarDatosProcesaComparendoVehiculo(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Infractor
        validarDatosProcesaComparendoInfractor(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Priopietario
        validarDatosProcesaComparendoPropietario(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Agente Transito
        validarDatosProcesaComparendoAgenteTransito(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Inmovilizacion
        validarDatosProcesaComparendoInmovilizacion(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Testigo
        validarDatosProcesaComparendoTestigo(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Embriaguez
        validarDatosProcesaComparendoEmbriaguez(procesaComparendoDTO, errorCampoComparendoDTOs);
        // Evidencias Fisisas
        validarDatosProcesaComparendoEvidenciasFisicas(procesaComparendoDTO, errorCampoComparendoDTOs);
        return errorCampoComparendoDTOs;
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo encabezado
     * 
     * @author javier.fajardo
     * @author giovanni.velandia (mod 25-05-2016)
     */
    public static void validarDatosProcesaComparendoEncabezado(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        // longitud numero citacion
        if (procesaComparendoDTO.getNumeroCitacion() != null && validarLongitud(
                EnumCamposDetalleComparendo.NUMERO_CITACION, procesaComparendoDTO.getNumeroCitacion())) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.NUMERO_CITACION_INVALIDO));
        }
        // tipo dato numero citacion
        if (procesaComparendoDTO.getNumeroComparendo() != null && !procesaComparendoDTO.getNumeroComparendo()
                .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_CITACION,
                    EnumErrorProcesamiento.NUMERO_CITACION_INVALIDO));
        }
        // tipo dato numero factura
        if (procesaComparendoDTO.getNumeroComparendo() != null && !procesaComparendoDTO.getNumeroComparendo()
                .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_FACTURA,
                    EnumErrorProcesamiento.NUMERO_FACTURA_INVALIDO));
        }
        // longitud numero comparendo
        if (procesaComparendoDTO.getNumeroComparendo() != null && validarLongitud(
                EnumCamposDetalleComparendo.NUMERO_COMPARENDO, procesaComparendoDTO.getNumeroComparendo())) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.NUMERO_COMPARENDO_INVALIDO));
        }
        // tipo dato numero comparendo
        if (procesaComparendoDTO.getNumeroComparendo() != null && !procesaComparendoDTO.getNumeroComparendo()
                .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_COMPARENDO,
                    EnumErrorProcesamiento.NUMERO_COMPARENDO_INVALIDO));
        }
        // longitud codigo infraccion
        if (procesaComparendoDTO.getCodigoInfraccion() != null && validarLongitud(
                EnumCamposDetalleComparendo.INFRACCION, procesaComparendoDTO.getCodigoInfraccion())) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.INFRACCION,
                    EnumErrorProcesamiento.LONGITUD_INFRACCION_INVALIDA));
        }
        // tipo de dato del codigo de la infraccion
        String codigoInfraccion = procesaComparendoDTO.getCodigoInfraccion();
        if (codigoInfraccion != null
                && !codigoInfraccion.matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.INFRACCION,
                    EnumErrorProcesamiento.INFRACCION_INVALIDA));
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo vehiculo
     * 
     * @author giovanni.velandia
     */
    public static void validarDatosProcesaComparendoVehiculo(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        // numero tarjeta de operacion
        if (procesaComparendoDTO.getNumeroTarjetaOperacion() != null
                && validarLongitud(EnumCamposDetalleComparendo.NUMERO_TARJETA_OPERACION,
                        procesaComparendoDTO.getNumeroTarjetaOperacion())) {
            errorCampoComparendoDTOs
                    .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_TARJETA_OPERACION,
                            EnumErrorProcesamiento.LONGITUD_NUMERO_TARJETA_OPERACION_INVALIDO));
        }
        // tipo de dato de la placa vehiculo
        if (procesaComparendoDTO.getNumeroTarjetaOperacion() != null && !procesaComparendoDTO
                .getNumeroTarjetaOperacion().matches(ExpresionesRegulares.REGEX_ALFANUMERICO_OBLIGATORIO)) {
            errorCampoComparendoDTOs
                    .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_TARJETA_OPERACION,
                            EnumErrorProcesamiento.FORMATO_TARJETA_OPERACION_INVALIDO));
        }

        // longitud de placa
        if (procesaComparendoDTO.getPlacaVehiculo() != null && validarLongitud(
                EnumCamposDetalleComparendo.PLACA_VEHICULO, procesaComparendoDTO.getPlacaVehiculo())) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PLACA_VEHICULO,
                    EnumErrorProcesamiento.PLACA_VEHICULO_INVALIDA));
        }
        // tipo de dato de la placa vehiculo
        if (procesaComparendoDTO.getPlacaVehiculo() != null && !procesaComparendoDTO.getPlacaVehiculo()
                .matches(ExpresionesRegulares.REGEX_ALFANUMERICO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PLACA_VEHICULO,
                    EnumErrorProcesamiento.PLACA_VEHICULO_INVALIDA));
        }
        // identificacion vehiculo
        if (procesaComparendoDTO.getIdentificacionVehiculo() != null
                && validarLongitud(EnumCamposDetalleComparendo.IDENTIFICACION_VEHICULO,
                        procesaComparendoDTO.getIdentificacionVehiculo())) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PLACA_VEHICULO,
                    EnumErrorProcesamiento.FALTA_IDENTIFICACION_VEHICULO));
        }
        // tipo de dato de la identificacion del vehiculo
        String identificacionVehiculo = procesaComparendoDTO.getIdentificacionVehiculo();
        if (identificacionVehiculo != null
                && !identificacionVehiculo.matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs
                    .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.IDENTIFICACION_VEHICULO,
                            EnumErrorProcesamiento.FORMATO_IDENTIFICACION_VEHICULO_INVALIDO));
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo Infractor
     * 
     * @author giovanni.velandia
     */
    public static void validarDatosProcesaComparendoInfractor(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        ProcesaComparendoPersonaDTO personaDTO = null;
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.INFRACTOR.getValue())) {
                personaDTO = procesaComparendoPersonaDTO;
                break;
            }
        }
        if (personaDTO != null) {
            // tipo de dato numero de identificacion
            if (personaDTO.getNumeroIdentificacion() != null && !personaDTO.getNumeroIdentificacion()
                    .matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_DEL_INFRACTOR,
                                EnumErrorProcesamiento.FORMATO_DOCUMENTO_INFRACTOR_INVALIDO));
            }

            // validar lo siguiente: Para Colombia, se valida como numérico, excepto cuando es Pasaporte.
            if (personaDTO.getIdTipoIdentificacion() != null
                    && !personaDTO.getIdTipoIdentificacion().equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {

                boolean valido = personaDTO.getNumeroIdentificacion()
                        .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO);
                if (!valido) {
                    // ERROR
                    errorCampoComparendoDTOs.add(
                            crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_DOCUMENTO_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.FORMATO_DOCUMENTO_INFRACTOR_INVALIDO));
                }
            }

            // Validacmos los nombres y apellido
            if (personaDTO.getIdTipoIdentificacion() != null && !personaDTO.getIdTipoIdentificacion()
                    .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getValor())) {
                // Primer nombre infractor
                if (personaDTO.getNombre1() != null) {
                    if (validarLongitud(EnumCamposDetalleComparendo.PRIMER_NOMBRE_INFRACTOR, personaDTO.getNombre1())) {
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_INFRACTOR,
                                        EnumErrorProcesamiento.PRIMER_NOMBRE_INFRACTOR_INVALIDO_FORMATO));
                    }
                    // Tipo de dato del primer nombre infractor
                    if (!personaDTO.getNombre1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) { //
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_INFRACTOR,
                                        EnumErrorProcesamiento.PRIMER_NOMBRE_INFRACTOR_INVALIDO_FORMATO));
                    }
                }

                // Segundo nombre infractor
                if (personaDTO.getNombre2() != null) {
                    // Longitud
                    if (validarLongitud(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_INFRACTOR,
                            personaDTO.getNombre2())) {
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_INFRACTOR,
                                        EnumErrorProcesamiento.SEGUNDO_NOMBRE_INFRACTOR_INVALIDO));
                    }
                    // Tipo de dato del segundo nombre
                    if (!personaDTO.getNombre2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_INFRACTOR,
                                        EnumErrorProcesamiento.SEGUNDO_NOMBRE_INFRACTOR_INVALIDO));
                    }
                }
                // primer apellido
                if (personaDTO.getApellido1() != null) {
                    if (validarLongitud(EnumCamposDetalleComparendo.PRIMER_APELLIDO_INFRACTOR,
                            personaDTO.getApellido1())) {
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_INFRACTOR,
                                        EnumErrorProcesamiento.PRIMER_APELLIDO_INFRACTOR_INVALIDO));
                    }
                    // tipo de dato del primer apellido
                    if (!personaDTO.getApellido1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                        errorCampoComparendoDTOs
                                .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_INFRACTOR,
                                        EnumErrorProcesamiento.PRIMER_APELLIDO_INFRACTOR_INVALIDO_FORMATO));
                    }
                }

                // Apellido 2
                if (personaDTO.getApellido2() != null) {
                    // Longitud
                    if (validarLongitud(EnumCamposDetalleComparendo.SEGUNDO_APELLIDO_INFRACTOR,
                            personaDTO.getApellido1())) {
                        errorCampoComparendoDTOs.add(
                                crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_APELLIDO_INFRACTOR,
                                        EnumErrorProcesamiento.SEGUNDO_APELLIDO_INFRACTOR_INVALIDO));
                    }
                    // tipo de dato del segundo apellido
                    if (!personaDTO.getApellido2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                        errorCampoComparendoDTOs.add(
                                crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_APELLIDO_INFRACTOR,
                                        EnumErrorProcesamiento.SEGUNDO_APELLIDO_INFRACTOR_INVALIDO));
                    }
                }
            }

            // tipo de dato del correo infractor
            if (personaDTO.getEmail() != null && !personaDTO.getEmail().isEmpty()) {
                if (!EmailValidator.validate(personaDTO.getEmail())) {
                    errorCampoComparendoDTOs
                            .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.EMAIL_DEL_INFRACTOR,
                                    EnumErrorProcesamiento.EMAIL_INFRACTOR_INVALIDO));
                }
            }
            // tipo de dato telefono fijo infractor
            if (personaDTO.getTelefonoFijo() != null
                    && !personaDTO.getTelefonoFijo().matches(ExpresionesRegulares.REGEX_TELEFONO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.TELEFONO_FIJO_INFRACTOR,
                                EnumErrorProcesamiento.FORMATO_TELEFONO_FIJO_INFRACTOR_INVALIDO));
            }
            // tipo de dato telefono movil infractor
            if (personaDTO.getTelefonoMovil() != null
                    && !personaDTO.getTelefonoMovil().matches(ExpresionesRegulares.REGEX_TELEFONO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.TELEFONO_MOVIL_INFRACTOR,
                                EnumErrorProcesamiento.FORMATO_TELEFONO_MOVIL_INFRACTOR_INVALIDO));
            }
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo propietario
     * 
     * @author giovanni.velandia
     */
    public static void validarDatosProcesaComparendoPropietario(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        ProcesaComparendoPersonaDTO personaDTO = null;
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.PROPIETARIO.getValue())) {
                personaDTO = procesaComparendoPersonaDTO;
                break;
            }
        }
        if (personaDTO != null) {
            // longitud numero de identificacion
            if (personaDTO.getNumeroIdentificacion() != null && !personaDTO.getNumeroIdentificacion()
                    .matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs.add(
                        crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_PROPIETARIO,
                                EnumErrorProcesamiento.FORMATO_NUMERO_DOCUMENTO_TESTIGO_INVALIDO));
            }
            // tipo dato apellido 1 propietario
            if (personaDTO.getApellido1() != null
                    && !personaDTO.getApellido1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_PROPIETARIO,
                                EnumErrorProcesamiento.PRIMER_APELLIDO_PROPIETARIO_INVALIDO_FORMATO));
            }
            // tipo dato apellido 2 propietario
            if (personaDTO.getApellido2() != null
                    && !personaDTO.getApellido2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_PROPIETARIO,
                                EnumErrorProcesamiento.SEGUNDO_APELLIDO_PROPIETARIO_INVALIDO_FORMATO));
            }
            // tipo dato nombre 1 propietario
            if (personaDTO.getNombre1() != null
                    && !personaDTO.getNombre1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_PROPIETARIO,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_PROPIETARIO_INVALIDO_FORMATO));
            }
            // tipo dato nombre 2 propietario
            if (personaDTO.getNombre2() != null
                    && !personaDTO.getNombre2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_PROPIETARIO,
                                EnumErrorProcesamiento.SEGUNDO_NOMBRE_PROPIETARIO_INVALIDO_FORMATO));
            }
            // numero licencia transito vehiculo
            if (procesaComparendoDTO.getNumeroLicenciaTransito() != null && !procesaComparendoDTO
                    .getNumeroLicenciaTransito().matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.LICENCIA_DE_TRANSITO,
                                EnumErrorProcesamiento.FORMATO_LICENCIA_TRANSITO_INVALIDO));
            }
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo Agente de transito
     * 
     * @author giovanni.velandia
     */
    public static void validarDatosProcesaComparendoAgenteTransito(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        // numero documento agente
        if (procesaComparendoDTO.getNumeroIdentificacionAgente() != null) {
            if (!procesaComparendoDTO.getNumeroIdentificacionAgente()
                    .matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_AGENTE_INVALIDO_FORMATO));
            }

            // Validar si aplica longitud
            boolean longitud = false;
            longitud = RecibirComparendoHelper.validarLongitud(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                    procesaComparendoDTO.getNumeroIdentificacionAgente());
            if (longitud) {
                // ERROR
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_IDENTIFICACION_AGENTE,
                                EnumErrorProcesamiento.FORMATO_DOCUMENTO_AGENTE_INVALIDO));
            }
        }

        if (procesaComparendoDTO.getApellido1Agente() != null) {
            // longitud primer apellido agente
            if (validarLongitud(EnumCamposDetalleComparendo.PRIMER_APELLIDO_AGENTE,
                    procesaComparendoDTO.getApellido1Agente())) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_AGENTE,
                                EnumErrorProcesamiento.FALTA_PRIMER_APELLIDO_AGENTE));
            }
            // tipo dato primer apellido agente
            if (!procesaComparendoDTO.getApellido1Agente()
                    .matches(ExpresionesRegulares.REGEX_ALFANUMERICO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_AGENTE,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_AGENTE_INVALIDO_FORMATO));
            }
        }
        // tipo dato segundo apellido agente
        String apellido2 = procesaComparendoDTO.getApellido2Agente();
        if (apellido2 != null && !apellido2.matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
            errorCampoComparendoDTOs
                    .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_APELLIDO_AGENTE,
                            EnumErrorProcesamiento.SEGUNDO_APELLIDO_AGENTE_INVALIDO_FORMATO));
        }
        if (procesaComparendoDTO.getNombre1Agente() != null) {
            // longitud primer nombre agente
            if (validarLongitud(EnumCamposDetalleComparendo.PRIMER_NOMBRE_AGENTE,
                    procesaComparendoDTO.getNombre1Agente())) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_AGENTE,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_AGENTE_INVALIDO));
            }
            // tipo dato primer nombre agente
            if (!procesaComparendoDTO.getNombre1Agente().matches(ExpresionesRegulares.REGEX_ALFANUMERICO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_AGENTE,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_AGENTE_INVALIDO_FORMATO));
            }
        }
        // tipo dato segundo nombre agente
        String nombre2 = procesaComparendoDTO.getNombre2Agente();
        if (nombre2 != null && !nombre2.matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_AGENTE,
                    EnumErrorProcesamiento.SEGUNDO_APELLIDO_AGENTE_INVALIDO_FORMATO));
        }
        if (procesaComparendoDTO.getNombreResponsable() != null) {
            // longitud nombre responsable
            if (validarLongitud(EnumCamposDetalleComparendo.NOMBRE_RESPONSABLE,
                    procesaComparendoDTO.getNombreResponsable())) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NOMBRE_RESPONSABLE,
                                EnumErrorProcesamiento.LONGITUD_RESPONSABLE_INVALIDO));
            }
            // tipo dato nombre responsable
            if (!procesaComparendoDTO.getNombreResponsable().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NOMBRE_RESPONSABLE,
                                EnumErrorProcesamiento.FORMATO_RESPONSABLE_INVALIDO));
            }
        }
        // tipo de dato observaciones agente
        String observacionAgente = procesaComparendoDTO.getObservacionesAgente();
        if (observacionAgente != null
                && !observacionAgente.matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) { //
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.OBSERVACIONES_AGENTE,
                    EnumErrorProcesamiento.FORMATO_OBSERVACIONES_INVALIDO));
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo inmovilizacion
     * 
     * @author giovanni.velandia
     */
    public static void validarDatosProcesaComparendoInmovilizacion(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        // Campo consecutivo inmovilizacion
        if (procesaComparendoDTO.getConsecutivoInmovilizacion() != null) {
            // Tipo de dato consecutivo inmovilizacion
            if (!procesaComparendoDTO.getConsecutivoInmovilizacion()
                    .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.CONSECUTIVO_INMOVILIZACION,
                                EnumErrorProcesamiento.CONSECUTIVO_INMOVILIZACION_INVALIDO));
            }
        }
        // tipo de dato nombre patio
        String nombrePatio = procesaComparendoDTO.getNombrePatio();
        if (nombrePatio != null && !nombrePatio.matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
            errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NOMBRE_PATIO,
                    EnumErrorProcesamiento.FORMATO_NOMBRE_PATIO_INVALIDO));
        }
        // tipo de dato numero grua
        if (procesaComparendoDTO.getPlacaGrua() != null) {
            String placa = procesaComparendoDTO.getPlacaGrua();
            if (placa != null && !placa.matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PLACA_GRUA,
                        EnumErrorProcesamiento.PLACA_GRUA_INVALIDO));
            }

            // Validar si aplica longitud
            boolean longitud = false;
            longitud = validarLongitud(EnumCamposDetalleComparendo.PLACA_GRUA, procesaComparendoDTO.getPlacaGrua());
            if (longitud) {
                // ERROR
                errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PLACA_GRUA,
                        EnumErrorProcesamiento.PLACA_GRUA_INVALIDO));
            }
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo testigo
     * 
     * @author javier.fajardo
     */
    public static void validarDatosProcesaComparendoTestigo(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
        ProcesaComparendoPersonaDTO personaDTO = null;
        for (ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO : procesaComparendoDTO
                .getProcesaComparendoPersonas()) {
            if (procesaComparendoPersonaDTO.getCodigoTipoPersonaComparendo()
                    .equals(EnumTipoPersonaComparendo.TESTIGO.getValue())) {
                personaDTO = procesaComparendoPersonaDTO;
                break;
            }
        }
        if (personaDTO != null) {
            // numero de documento testigo
            if (personaDTO.getNumeroIdentificacion() != null
                    && validarLongitud(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_TESTIGO,
                            personaDTO.getNumeroIdentificacion())) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_TESTIGO,
                                EnumErrorProcesamiento.FALTA_NUMERO_DOCUMENTO_TESTIGO));
            }

            // validar lo siguiente: Para Colombia, se valida como numérico, excepto cuando es Pasaporte.
            if (personaDTO.getIdTipoIdentificacion() != null) {
                if (!personaDTO.getIdTipoIdentificacion().equals(EnumTipoIdentificacion.PASAPORTE.getValor())) {

                    boolean valido = personaDTO.getNumeroIdentificacion()
                            .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO);
                    if (!valido) {
                        // ERROR
                        errorCampoComparendoDTOs.add(crearErrorCampoComparendoDTO(
                                EnumCamposDetalleComparendo.NUMERO_DE_DOCUMENTO_DEL_TESTIGO,
                                EnumErrorProcesamiento.FORMATO_NUMERO_DOCUMENTO_TESTIGO_INVALIDO));
                    }
                }
            }
            // tipo de dato apellido 1 del testigo
            if (personaDTO.getApellido1() != null
                    && !personaDTO.getApellido1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_APELLIDO_TESTIGO,
                                EnumErrorProcesamiento.PRIMER_APELLIDO_TESTIGO_INVALIDO));
            }
            // tipo de dato apellido 2 del testigo
            if (personaDTO.getApellido2() != null
                    && !personaDTO.getApellido2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_APELLIDO_TESTIGO,
                                EnumErrorProcesamiento.SEGUNDO_APELLIDO_TESTIGO_INVALIDO));
            }
            // tipo de dato nombre 1 del testigo
            if (personaDTO.getNombre1() != null
                    && !personaDTO.getNombre1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.PRIMER_NOMBRE_TESTIGO,
                                EnumErrorProcesamiento.PRIMER_NOMBRE_TESTIGO_INVALIDO_FORMATO));
            }
            // tipo de dato nombre 2 del testigo
            if (personaDTO.getNombre2() != null
                    && !personaDTO.getNombre2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.SEGUNDO_NOMBRE_TESTIGO,
                                EnumErrorProcesamiento.SEGUNDO_NOMBRE_TESTIGO_INVALIDO_FORMATO));
            }
            // tipo de campo numero movil testigo
            if (personaDTO.getTelefonoMovil() != null
                    && !personaDTO.getTelefonoMovil().matches(ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO)) {
                errorCampoComparendoDTOs
                        .add(crearErrorCampoComparendoDTO(EnumCamposDetalleComparendo.TELEFONO_MOVIL_TESTIGO,
                                EnumErrorProcesamiento.FORMATO_MOVIL_TESTIGO_INVALIDO));
            }
        }
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo embriaguez
     * 
     * @author javier.fajardo
     */
    public static void validarDatosProcesaComparendoEmbriaguez(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
    }

    /**
     * Metodo que se encarga de validar la integridad de los datos de un ProcesaComparendo evidencias fisicas
     * 
     * @author javier.fajardo
     */
    public static void validarDatosProcesaComparendoEvidenciasFisicas(ProcesaComparendoDTO procesaComparendoDTO,
            List<ErrorCampoComparendoDTO> errorCampoComparendoDTOs) {
    }

    /**
     * Metodo que se encarga de crear un erro Campo comparendo
     * 
     * @param enumCamposDetalleComparendo
     * @param enumErrorProcesamiento
     * @return
     */
    private static ErrorCampoComparendoDTO crearErrorCampoComparendoDTO(
            EnumCamposDetalleComparendo enumCamposDetalleComparendo, EnumErrorProcesamiento enumErrorProcesamiento) {
        ErrorCampoComparendoDTO errorCampoComparendoDTO = new ErrorCampoComparendoDTO();
        // Enum detalle campo comparendo
        errorCampoComparendoDTO.setEnumCamposDetalleComparendo(enumCamposDetalleComparendo);
        // Enum error procesamiento
        errorCampoComparendoDTO.setEnumErrorProcesamiento(enumErrorProcesamiento);
        return errorCampoComparendoDTO;
    }
}
