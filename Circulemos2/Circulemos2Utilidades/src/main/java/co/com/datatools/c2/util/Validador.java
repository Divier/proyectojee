package co.com.datatools.c2.util;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.constantes.ConstantesComparendo;

/**
 * Clase con metodos estaticos para validar datos asociados con la logica de negocio<br>
 * Otros metodos de validacion pueden hallarse en {@link StringUtils} (numerico, alfanumerico ...)
 * 
 * @author felipe.martinez
 */
public class Validador {

    private Validador() {

    }

    /**
     * Verifica que sea una cadena valida para la placa de cualquier tipo de vehiculo
     * 
     * @param entrada
     *            Cadena a validar
     * @return Verdadero entrada corresponde a patron placa (motocicleta o automovil)
     */
    public static boolean esPlacaValida(String entrada) {
        return esPlacaAutomovilValida(entrada) || esPlacaMotoValida(entrada);
    }

    /**
     * Valida si es una placa valida para un automovil particular
     * 
     * @param entrada
     *            Cadena a validar
     * @return Verdadero entrada corresponde a patron placa automovil
     */
    public static boolean esPlacaAutomovilValida(String entrada) {
        return entrada.matches(ConstantesComparendo.REGEX_PLACA_VEHICULO);
    }

    /**
     * Valida si es una placa valida para una Motocicleta
     * 
     * @param entrada
     *            Cadena a validar
     * @return Verdadero entrada corresponde a patron placa motocicleta
     */
    public static boolean esPlacaMotoValida(String entrada) {
        return entrada.matches(ConstantesComparendo.REGEX_PLACA_MOTO);
    }

    /**
     * Valida si es un complemento valido para una direccion
     * 
     * @param entrada
     *            Cadena a validar
     * @return Verdadero entrada corresponde a patron complemento direccion
     */
    public static boolean esComplementoDireccionValido(String entrada) {
        return entrada.matches(ConstantesComparendo.REGEX_DIRECCION_COMPLEMENTO);
    }
}
