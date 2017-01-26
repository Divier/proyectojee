package co.com.datatools.c2.negocio.fachada;

import java.util.Date;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;

@Remote
public interface IRFachadaRegistroVehicular {

    /**
     * Se encarga de consultar la licencia de transito
     * 
     * @param numeroLicencia
     * @param codigoOrganismo
     * @return
     * @author giovanni.velandia 2015-12-15
     */
    public LicenciaConduccionDTO consultarLicencia(String numeroLicencia, int codigoOrganismo);

    /**
     * Retorna el vehículo asociado a la placa indicada. Valida que la placa tenga algún valor
     * 
     * @param placa
     * @return
     * @author diego.fonseca
     */
    public VehiculoDTO consultarVehiculo(String placa);

    /**
     * Retorna la información de la empresa asociada al vehículo con la placa indicada cuya vigencia se encuentre en la fecha indicada. Verifica que
     * los campos tengan información
     * 
     * @param placa
     * @param codigoOrganismo
     * @param fecha
     * @return
     * @author diego.fonseca
     */
    public EmpresaVehiculoDTO consultarEmpresa(String placa, int codigoOrganismo, Date fecha);

    /**
     * Retorna el propietario con mayor porcentaje sobre el vehículo acorde a los filtros indicados. Verifica que el parámetro no sea nulo
     * 
     * @param consultarRegistroVehiculoarDTO
     * @author diego.fonseca
     * @return
     */
    public PropietarioVehiculoDTO consultarPropietarioVehiculo(
            ConsultaRegistroVehicularDTO consultarRegistroVehiculoarDTO);

}
