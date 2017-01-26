package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;

@Local
public interface ILRegistroVehicularLicencia {

    /**
     * 
     * @see ILRegistroVehicularLicencia#consultarLicencia(String, int)
     */
    public LicenciaConduccionDTO consultarLicencia(String numeroLicencia, int codigoOrganismo);
}
