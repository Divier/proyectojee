package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.RespuestaGestionCobroSacDTO;

@Remote
public interface IRGestionCobro {

    /**
     * Registra el cobro de la gestion de SAC.
     * 
     * @param GestionCobroSac
     * @return RespuestaGestionCobroSacDTO
     * @author javier.fajardo
     */
    RespuestaGestionCobroSacDTO registrarGestionCobro(GestionCobroSacDTO GestionCobroSac);

    /**
     * Actualiza los estados de TODAS las carteras en SAC de acuerdo a las validaciones
     * 
     * @param codigoOrganismo
     */
    void actualizarGestionCobroTimer(Integer codigoOrganismo);

}
