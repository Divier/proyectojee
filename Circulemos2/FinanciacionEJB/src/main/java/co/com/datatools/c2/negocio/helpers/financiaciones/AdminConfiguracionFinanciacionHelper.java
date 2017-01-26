package co.com.datatools.c2.negocio.helpers.financiaciones;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.reglas.ValorConValidacionTipCompVarDTO;
import co.com.datatools.c2.dto.reglas.ValorCondValidacionTipIndividualDTO;
import co.com.datatools.c2.dto.reglas.ValorCondVariablesComparacionDTO;
import co.com.datatools.c2.dto.reglas.ValorCondicionTiposValidacionDTO;
import co.com.datatools.c2.dto.reglas.ValorCondicionValidacionTipRangoDTO;
import co.com.datatools.c2.enumeracion.EnumCampoConfFinanciacion;
import co.com.datatools.c2.enumeracion.EnumErrorConfiguracionFinanciacion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.negocio.helpers.extend.CondicionFinanciacionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILReglaNegocio;

/**
 * Clase que permite llevar a cabo las reglas de negocio necesarias para el funcionamiento de los servicios relacionados con la Configuracion de una
 * Financiacion
 * 
 * @author luis.forero
 * 
 */
public class AdminConfiguracionFinanciacionHelper {

    /**
     * Valida que el campo de Fecha inicial de vigencia de la Configuracion
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : Configuracion con la fecha inicio a validar.
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarFecIniConfFinMenIgualActual(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        if (configuracionFinanciacionDTO.getFechaInicial().compareTo(
                formatoFechaSinHora(Calendar.getInstance().getTime())) <= 0) {
            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.FECHA_INICIAL_VIGENCIA));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1).getListaEnumsErrores()
            // .add(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_INI_IGUAL_MENOR);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_INI_IGUAL_MENOR
                    .getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_INI_IGUAL_MENOR
                    .getDescripcion());
            return validacion;
        }
        validacion = new RespuestaDTO<>(true);
        validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        return validacion;
    }

    /**
     * Valida que el campo de Fecha final de vigencia de la Configuracion no sea menor a la fecha inicial, siempre y cuando la fecha final no sea
     * nula, en este caso retornara el campo validado correctacmente.
     * 
     * @author luis.forero
     * @param configuracionFinanciacionDTO
     *            : Configuracion con la fecha inicio y fecha fin.
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarFecFinConfMenorFecIni(
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        if (configuracionFinanciacionDTO.getFechaFinal() != null
                && configuracionFinanciacionDTO.getFechaFinal().compareTo(
                        configuracionFinanciacionDTO.getFechaInicial()) < 0) {
            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.FECHA_FINAL_VIGENCIA));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1).getListaEnumsErrores()
            // .add(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_FIN_MENOR_FECHA_INI);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_FIN_MENOR_FECHA_INI
                    .getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_FECHA_FIN_MENOR_FECHA_INI
                    .getDescripcion());
            return validacion;
        }
        validacion = new RespuestaDTO<>(true);
        validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        return validacion;
    }

    /**
     * Permite identificar si un determinado detalle Cantidad cuota esta correctamente validado.
     * 
     * 1) Validacion de CAMPO_CANTIDAD_MAXIMA_CUOTAS > CAMPO_CANTIDAD_MINIMA_CUOTAS
     * 
     * 2) Validacion de campos VALOR_MINIMO_FINANCIAR, VALOR_MAXIMO_FINANCIAR, CAMPO_CANTIDAD_MINIMA_CUOTAS, CAMPO_CANTIDAD_MAXIMA_CUOTAS sean valores
     * positivos
     * 
     * @param detalleCantidadCuotaDTO
     *            : Detalle cantidad Cuota con los campos a llevar a cabo validacion.
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarCampoDetalleCantCuotas(
            DetalleCantidadCuotaDTO detalleCantidadCuotaDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        boolean validado = true;
        // TODO FIX cambio en el objeto respuesta de DTO
        validacion = new RespuestaDTO<>(validado);
        if (detalleCantidadCuotaDTO.getCantidadMaximaCouta() < detalleCantidadCuotaDTO.getCantidadMinimaCoutas()) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CANTIDAD_MAXIMA_CUOTAS_DCC));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1).getListaEnumsErrores()
            // .add(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_DCC_CANT_CUOTAS);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detalleCantidadCuotaDTO.getValorMinimoFinanciar().compareTo(BigDecimal.ZERO) < 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_VALOR_MINIMO_FINANCIAR_DCC));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detalleCantidadCuotaDTO.getValorMaximoFinanciar().compareTo(BigDecimal.ZERO) < 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_VALOR_MAXIMO_FINANCIAR_DCC));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detalleCantidadCuotaDTO.getCantidadMinimaCoutas().intValue() <= 1) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CANTIDAD_MINIMA_CUOTAS_DCC));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detalleCantidadCuotaDTO.getCantidadMaximaCouta() < 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CANTIDAD_MAXIMA_CUOTAS_DCC));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (validado) {
            validacion = new RespuestaDTO<>(true);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.DETALLE_CANTIDAD_CUOTAS_VALIDADO
                    .getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.DETALLE_CANTIDAD_CUOTAS_VALIDADO
                    .getDescripcion());
        } else {
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_DETALLE_CANTIDAD_CUOTAS_NO_VALIDADO
                    .getDescripcion());
            validacion
                    .setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_DETALLE_PORCENTAJE_CUOTA_INI_NO_VALIDADO
                            .getDescripcion());
        }
        return validacion;
    }

    /**
     * Permite identificar si un determinado Detalle Porcentaje Cuota Inicial esta correctamente validado.
     * 
     * 1) Validacion de PORCENTAJE_FINANCIACION_CUOTA_INICIAL entre 0 y 100
     * 
     * 2) Validacion de campos VALOR_MINIMO_FINANCIAR, VALOR_MAXIMO_FINANCIAR sean valores positivos
     * 
     * @param detallePorcentajeCuotaIniciDTO
     *            : Detalle cantidad Cuota con los campos a llevar a cabo validacion.
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    public RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validarCampoDetallePorcCuotaIni(
            DetallePorcentajeCuotaIniciDTO detallePorcentajeCuotaIniciDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        boolean validado = true;
        validacion = new RespuestaDTO<>(validado);
        // TODO FIX cambio en el objeto respuesta de DTO
        if (detallePorcentajeCuotaIniciDTO.getValorMinimoFinanciar().compareTo(BigDecimal.ZERO) < 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_VALOR_MINIMO_FINANCIAR_DPCI));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detallePorcentajeCuotaIniciDTO.getValorMaximoFinanciar().compareTo(BigDecimal.ZERO) < 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_VALOR_MAXIMO_FINANCIAR_DPCI));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_ENTERO_POSITIVO);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (detallePorcentajeCuotaIniciDTO.getPorcentajeCuotaInicial().compareTo(BigDecimal.ZERO) < 0
                || detallePorcentajeCuotaIniciDTO.getPorcentajeCuotaInicial().compareTo(new BigDecimal(100)) > 0) {
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_PORCENTAJE_FINANCIACION_CUOTA_INICIAL_DPCI));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_DPCI_PORC_FIN_CI);
            validacion.setRespuesta(EnumRespuestaSistema.ERRORES);
            validado = false;
        }
        if (validado) {
            validacion = new RespuestaDTO<>(true);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.DETALLE_CANTIDAD_CUOTAS_VALIDADO
                    .getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.DETALLE_CANTIDAD_CUOTAS_VALIDADO
                    .getDescripcion());
        } else {
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_DETALLE_CANTIDAD_CUOTAS_NO_VALIDADO
                    .getDescripcion());
            validacion
                    .setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_DETALLE_PORCENTAJE_CUOTA_INI_NO_VALIDADO
                            .getDescripcion());
        }
        return validacion;
    }

    /**
     * Retorna un la validacion de los valores de las variables de una configuracion de financiacion.
     * 
     * @author luis.forero
     * @param lstValorCondicionFinanciacionDTOs
     * @return Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>>
     */
    public Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validacionValoresConfFinanciacion(
            List<ValorCondicionFinanciacionDTO> lstValorCondicionFinanciacionDTOs, ILReglaNegocio reglaNegocioEjb) {
        Hashtable<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> validaciones = new Hashtable<>();

        /**
         * Se lleva a cabo la consuta de todos los posibles tipos de validacion para la variable a la cual pertenece el valor y llevar a cabo la
         * validacion respectiva del valor.
         */
        for (ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO : lstValorCondicionFinanciacionDTOs) {
            ValorCondicionTiposValidacionDTO tiposValidacionDTO = new ValorCondicionTiposValidacionDTO();
            tiposValidacionDTO.setIdVariableCondicionValor(valorCondicionFinanciacionDTO.getVariableCondicionFinan()
                    .getId());
            tiposValidacionDTO = (ValorCondicionTiposValidacionDTO) reglaNegocioEjb.consumirRegla(tiposValidacionDTO);

            for (Integer tipValidacion : tiposValidacionDTO.getListTipoValidaciones()) {
                RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaValidacion = null;
                switch (tipValidacion) {
                // tipo uno "1" para validaciones de campos individualmente
                case 1: {
                    ValorCondValidacionTipIndividualDTO valorCondValidacionTipIndividualDTO = CondicionFinanciacionHelperExtend
                            .inicializarTipoIndividual(valorCondicionFinanciacionDTO);
                    valorCondValidacionTipIndividualDTO = (ValorCondValidacionTipIndividualDTO) reglaNegocioEjb
                            .consumirRegla(valorCondValidacionTipIndividualDTO);
                    respuestaValidacion = construccionRespuestaDTO(valorCondValidacionTipIndividualDTO);
                    break;
                }
                // tipo dos "2" para validaciones de campos entre rangos
                case 2: {
                    ValorCondicionValidacionTipRangoDTO condicionValidacionTipRangoDTO = CondicionFinanciacionHelperExtend
                            .inicializarTipoRango(valorCondicionFinanciacionDTO);
                    condicionValidacionTipRangoDTO = (ValorCondicionValidacionTipRangoDTO) reglaNegocioEjb
                            .consumirRegla(condicionValidacionTipRangoDTO);
                    respuestaValidacion = construccionRespuestaDTO(condicionValidacionTipRangoDTO);
                    break;
                }
                // tipo tres "3" para validaciones de campos con respecto a otros existentes en el formulario.
                case 3: {
                    /*
                     * Para este caso en especial se consultan primero que todo las variables contra las cuales se va a llevar a cabo la comparacion.
                     */
                    ValorCondVariablesComparacionDTO variablesComparacionDTO = new ValorCondVariablesComparacionDTO(
                            valorCondicionFinanciacionDTO);
                    variablesComparacionDTO = (ValorCondVariablesComparacionDTO) reglaNegocioEjb
                            .consumirRegla(variablesComparacionDTO);
                    /*
                     * Se recorre el listado de los ids de variables obteniendo el valor con el cual se va a comparar.
                     */

                    for (Integer idVarComp : variablesComparacionDTO.getLstIdsVariablesCondFinan()) {
                        ValorCondicionFinanciacionDTO valCondFinanDTOComp = null;
                        for (ValorCondicionFinanciacionDTO valCondFinanDTO : lstValorCondicionFinanciacionDTOs) {
                            if (valCondFinanDTO.getVariableCondicionFinan().getId().equals(idVarComp)) {
                                valCondFinanDTOComp = valCondFinanDTO;
                                break;
                            }
                        }
                        /**
                         * Si se encuentra la variable junto con su valor a comparar dentro de la lista se lleva a cabo su respectiva validacion.
                         */
                        if (valCondFinanDTOComp != null) {
                            ValorConValidacionTipCompVarDTO condValidacionTipCompVarDTO = CondicionFinanciacionHelperExtend
                                    .inicializarTipoComparacion(valorCondicionFinanciacionDTO, valCondFinanDTOComp);
                            condValidacionTipCompVarDTO = (ValorConValidacionTipCompVarDTO) reglaNegocioEjb
                                    .consumirRegla(condValidacionTipCompVarDTO);
                            respuestaValidacion = construccionRespuestaDTO(condValidacionTipCompVarDTO);
                            break;
                        }
                    }
                    break;
                }
                default: {
                    break;
                }
                }
                if (respuestaValidacion != null
                        && respuestaValidacion.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                    validaciones.put(valorCondicionFinanciacionDTO, respuestaValidacion);
                }
            }

        }
        return validaciones;
    }

    /**
     * Borra la hora de la fecha ingresada
     * 
     * <pre>
     * Fecha no puede ser nula
     * </pre>
     * 
     * @author luis.forero
     * @param fecha
     * @return Date
     */
    @Deprecated
    public Date formatoFechaSinHora(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Construye una respuesta en base a una regla de negocio. Si el campo de respuesta del DTO es nulo o vacio la respuesta o validacion quedara como
     * resultado validada correctamente de lo contrario su respuesta de validacion sera un error.
     * 
     * @author luis.forero
     * @param condValidacionTipIndividualDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    private RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> construccionRespuestaDTO(
            ValorCondValidacionTipIndividualDTO condValidacionTipIndividualDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        if (condValidacionTipIndividualDTO.getRespuesta() != null
                && !condValidacionTipIndividualDTO.getRespuesta().isEmpty()) {
            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CONDICION));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_INDIVIDUAL);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_INDIVIDUAL
                    .getDescripcion());
            validacion.setDescripcionRespuesta(condValidacionTipIndividualDTO.getRespuesta());

        } else {
            validacion = new RespuestaDTO<>(true);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        }
        return validacion;
    }

    /**
     * Construye una respuesta en base a una regla de negocio. Si el campo de respuesta del DTO es nulo o vacio la respuesta o validacion quedara como
     * resultado validada correctamente de lo contrario su respuesta de validacion sera un error.
     * 
     * @author luis.forero
     * @param valorCondicionValidacionTipRangoDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    private RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> construccionRespuestaDTO(
            ValorCondicionValidacionTipRangoDTO valorCondicionValidacionTipRangoDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        if (valorCondicionValidacionTipRangoDTO.getRespuesta() != null
                && !valorCondicionValidacionTipRangoDTO.getRespuesta().isEmpty()) {
            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CONDICION));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_RANGO);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_RANGO
                    .getDescripcion());
            validacion.setDescripcionRespuesta(valorCondicionValidacionTipRangoDTO.getRespuesta());

        } else {
            validacion = new RespuestaDTO<>(true);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        }
        return validacion;
    }

    /**
     * Construye una respuesta en base a una regla de negocio. Si el campo de respuesta del DTO es nulo o vacio la respuesta o validacion quedara como
     * resultado validada correctamente de lo contrario su respuesta de validacion sera un error.
     * 
     * @author luis.forero
     * @param valorCondValidacionTipCompVarDTO
     * @return RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>
     */
    private RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> construccionRespuestaDTO(
            ValorConValidacionTipCompVarDTO valorCondValidacionTipCompVarDTO) {
        RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> validacion;
        if (valorCondValidacionTipCompVarDTO.getRespuesta() != null
                && !valorCondValidacionTipCompVarDTO.getRespuesta().isEmpty()) {
            validacion = new RespuestaDTO<>(false);
            // TODO FIX cambio en el objeto respuesta de DTO
            // validacion.getErrorEntidad().add(
            // new ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>(
            // EnumCampoConfFinanciacion.CAMPO_CONDICION));
            // validacion.getErrorEntidad().get(validacion.getErrorEntidad().size() - 1)
            // .addListaEnumsErrores(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_COMPARACION);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.ERROR_VALIDACION_CAMPO_TIP_COMPARACION
                    .getDescripcion());
            validacion.setDescripcionRespuesta(valorCondValidacionTipCompVarDTO.getRespuesta());

        } else {
            validacion = new RespuestaDTO<>(true);
            validacion.setNombreRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
            validacion.setDescripcionRespuesta(EnumErrorConfiguracionFinanciacion.CAMPO_VALIDO.getDescripcion());
        }
        return validacion;
    }

}
