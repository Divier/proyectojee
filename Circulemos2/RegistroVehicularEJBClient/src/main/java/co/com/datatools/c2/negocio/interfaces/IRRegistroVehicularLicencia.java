package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;

@Remote
public interface IRRegistroVehicularLicencia {

    /**
     * Se encarga de consultar la licencia de transito
     * 
     * @param numeroLicencia
     * @param codigoOrganismo
     * @return
     * @author giovanni.velandia
     */
    public LicenciaConduccionDTO consultarLicencia(String numeroLicencia, int codigoOrganismo);
}
