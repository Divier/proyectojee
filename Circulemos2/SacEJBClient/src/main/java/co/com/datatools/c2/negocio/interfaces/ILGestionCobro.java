package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.RespuestaGestionCobroSacDTO;

@Local
public interface ILGestionCobro {

    /**
     * @see IRGestionCobro#registrarGestionCobro(GestionCobroSacDTO)
     */
    RespuestaGestionCobroSacDTO registrarGestionCobro(GestionCobroSacDTO GestionCobroSac);

    /**
     * @see IRGestionCobro#actualizarGestionCobroTimer(Integer)
     */
    void actualizarGestionCobroTimer(Integer codigoOrganismo);
}
