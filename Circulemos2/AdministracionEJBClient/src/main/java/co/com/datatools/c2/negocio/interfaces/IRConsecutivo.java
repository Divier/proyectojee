package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * Permite obtener los consecutivos de diversas funcionalides en el sistema
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRConsecutivo {

    /**
     * Genera un consecutivo para el organismo indicado de un tipo determinado. El tipo de consecutivo es var-args en caso de no enviar tipo se
     * utilizar el tipo EnumConsecutivo.GENERAL
     * 
     * @param codigoOrganismo
     * @param tipoConsecutivo
     * @return Devuelve el consecutivo
     * @author julio.pinzon
     */
    public String generarConsecutivo(int codigoOrganismo, EnumConsecutivo... tipoConsecutivo);
}
