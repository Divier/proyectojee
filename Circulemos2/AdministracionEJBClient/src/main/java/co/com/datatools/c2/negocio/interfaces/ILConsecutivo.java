package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * @author julio.pinzon
 */
@Local
public interface ILConsecutivo {

    /**
     * @see IRConsecutivo#generarConsecutivo(int, EnumConsecutivo...)
     */
    public String generarConsecutivo(int codigoOrganismo, EnumConsecutivo... tipoConsecutivo);
}
