package co.com.datatools.c2.negocio.helpers.extend;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.reglas.ValorConValidacionTipCompVarDTO;
import co.com.datatools.c2.dto.reglas.ValorCondValidacionTipIndividualDTO;
import co.com.datatools.c2.dto.reglas.ValorCondicionValidacionTipRangoDTO;
import co.com.datatools.c2.enumeracion.EnumFormatValues;
import co.com.datatools.c2.enumeracion.EnumTipoVariableCondicion;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

/**
 * @author luis.forero
 * 
 */
public class CondicionFinanciacionHelperExtend {

    /**
     * Constructor para recepcion de valores definidos.
     * 
     * <pre>
     * VariableCondicionFinanDTO no nula
     * </pre>
     * 
     * <pre>
     * VariableCondicionFinanDTO.TipoVariableFinanciacionDTO.CodigoTipoVariable no nula
     * </pre>
     * 
     * @param valorCondicionFinanciacionDTO
     * @return ValorCondicionValidacionTipRangoDTO
     * @author luis.forero
     */
    public static ValorCondicionValidacionTipRangoDTO inicializarTipoRango(
            ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO) {
        ValorCondicionValidacionTipRangoDTO valDTO = new ValorCondicionValidacionTipRangoDTO();
        valDTO.setIdVariableCondicionFinanciacion(valorCondicionFinanciacionDTO.getVariableCondicionFinan().getId());
        Integer codigoTipoVariable = valorCondicionFinanciacionDTO.getVariableCondicionFinan()
                .getTipoVariableFinanciacion().getCodigo();
        if (EnumTipoVariableCondicion.ENTERO.getCodigo() == codigoTipoVariable) {
            valDTO.setValorVariableCondicionInt(Integer.parseInt(valorCondicionFinanciacionDTO.getValor()));
            return valDTO;
        }
        if (EnumTipoVariableCondicion.REAL.getCodigo() == codigoTipoVariable) {
            valDTO.setValorVariableCondicionReal(new Double(valorCondicionFinanciacionDTO.getValor()));
            return valDTO;
        }
        if (EnumTipoVariableCondicion.FECHA.getCodigo() == codigoTipoVariable) {
            SimpleDateFormat formatter = new SimpleDateFormat(EnumFormatValues.FORMATO_FECHA.getValor());
            try {
                valDTO.setValorVariableCondicionFecha(formatter.parse(valorCondicionFinanciacionDTO.getValor()));
            } catch (ParseException e) {
                throw new CirculemosRuntimeException("ERROR " + ValorCondicionValidacionTipRangoDTO.class.getName()
                        + " - PARSING VALUE");
            }
            return valDTO;
        }
        return valDTO;
    }

    /**
     * Constructor para recepcion de valores definidos.
     * 
     * <pre>
     * VariableCondicionFinanDTO no nula
     * </pre>
     * 
     * <pre>
     * VariableCondicionFinanDTO.TipoVariableFinanciacionDTO.CodigoTipoVariable no nula
     * </pre>
     * 
     * @param valorCondicionFinanciacionDTO
     * @return ValorCondValidacionTipIndividualDTO
     */
    public static ValorCondValidacionTipIndividualDTO inicializarTipoIndividual(
            ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO) {
        ValorCondValidacionTipIndividualDTO valDTO = new ValorCondValidacionTipIndividualDTO();
        valDTO.setIdVarCondFinanciacion(valorCondicionFinanciacionDTO.getVariableCondicionFinan().getId());
        Integer codigoTipoVariable = valorCondicionFinanciacionDTO.getVariableCondicionFinan()
                .getTipoVariableFinanciacion().getCodigo();
        if (EnumTipoVariableCondicion.ENTERO.getCodigo() == codigoTipoVariable) {
            valDTO.setValorInt(Integer.parseInt(valorCondicionFinanciacionDTO.getValor()));
            return valDTO;
        }
        if (EnumTipoVariableCondicion.REAL.getCodigo() == codigoTipoVariable) {
            valDTO.setValorReal(new Double(valorCondicionFinanciacionDTO.getValor()));
            return valDTO;
        }
        if (EnumTipoVariableCondicion.FECHA.getCodigo() == codigoTipoVariable) {
            SimpleDateFormat formatter = new SimpleDateFormat(EnumFormatValues.FORMATO_FECHA.getValor());
            try {
                valDTO.setValorFecha(formatter.parse(valorCondicionFinanciacionDTO.getValor()));
            } catch (ParseException e) {
                throw new RuntimeException("ERROR - PARSING VALUE");
            }
            return valDTO;
        }
        if (EnumTipoVariableCondicion.CADENA.getCodigo() == codigoTipoVariable) {
            valDTO.setValorStr(valorCondicionFinanciacionDTO.getValor());
            return valDTO;
        }
        return valDTO;
    }

    /**
     * Constructor que permite asignar la informacion necesaria para llevar a cabo su respeciva validacion.
     * 
     * @param valorCondFinanDTO1
     *            Valor que contiene la variable origen de comparacion y su respectivo valor a comparar
     * @param valorCondFinanDTO2
     *            Valor contra el que se compara.
     * @return ValorConValidacionTipCompVarDTO
     */
    public static ValorConValidacionTipCompVarDTO inicializarTipoComparacion(
            ValorCondicionFinanciacionDTO valorCondFinanDTO1, ValorCondicionFinanciacionDTO valorCondFinanDTO2) {
        ValorConValidacionTipCompVarDTO valDTO = new ValorConValidacionTipCompVarDTO();
        valDTO.setIdVariableCondicionFinan(valorCondFinanDTO1.getVariableCondicionFinan().getId());
        // Asignacion del valor 1
        Integer codigoTipoVariable1 = valorCondFinanDTO1.getVariableCondicionFinan().getTipoVariableFinanciacion()
                .getCodigo();
        if (EnumTipoVariableCondicion.ENTERO.getCodigo() == codigoTipoVariable1) {
            valDTO.setValorVariable1Int(Integer.parseInt(valorCondFinanDTO1.getValor()));

        }
        if (EnumTipoVariableCondicion.REAL.getCodigo() == codigoTipoVariable1) {
            valDTO.setValorVariable1Real(new Double(valorCondFinanDTO1.getValor()));
        }
        if (EnumTipoVariableCondicion.FECHA.getCodigo() == codigoTipoVariable1) {
            SimpleDateFormat formatter = new SimpleDateFormat(EnumFormatValues.FORMATO_FECHA.getValor());
            try {
                valDTO.setValorVariable1Fecha(formatter.parse(valorCondFinanDTO1.getValor()));
            } catch (ParseException e) {
                throw new CirculemosRuntimeException("ERROR - PARSING VALUE");
            }
        }
        if (EnumTipoVariableCondicion.CADENA.getCodigo() == codigoTipoVariable1) {
            valDTO.setValorVariable1Cadena(valorCondFinanDTO1.getValor());
        }

        // Asignacion del valor 2
        Integer codigoTipoVariable2 = valorCondFinanDTO2.getVariableCondicionFinan().getTipoVariableFinanciacion()
                .getCodigo();
        if (EnumTipoVariableCondicion.ENTERO.getCodigo() == codigoTipoVariable2) {
            valDTO.setValorVariable2Int(Integer.parseInt(valorCondFinanDTO2.getValor()));
        }
        if (EnumTipoVariableCondicion.REAL.getCodigo() == codigoTipoVariable2) {
            valDTO.setValorVariable2Real(new Double(valorCondFinanDTO2.getValor()));
        }
        if (EnumTipoVariableCondicion.FECHA.getCodigo() == codigoTipoVariable2) {
            SimpleDateFormat formatter = new SimpleDateFormat(EnumFormatValues.FORMATO_FECHA.getValor());
            try {
                valDTO.setValorVariable2Fecha(formatter.parse(valorCondFinanDTO2.getValor()));
            } catch (ParseException e) {
                throw new CirculemosRuntimeException("ERROR - PARSING VALUE");
            }
        }
        if (EnumTipoVariableCondicion.CADENA.getCodigo() == codigoTipoVariable2) {
            valDTO.setValorVariable2Cadena(valorCondFinanDTO2.getValor());
        }
        return valDTO;
    }
}
