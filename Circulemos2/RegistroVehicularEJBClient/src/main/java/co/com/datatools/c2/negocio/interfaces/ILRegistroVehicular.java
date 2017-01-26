package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;

@Local
public interface ILRegistroVehicular {

    /**
     * @see IRRegistroVehicular#consultarInformacionVehiculo(ConsultaRegistroVehicularDTO)
     */
    List<VehiculoDTO> consultarInformacionVehiculo(ConsultaRegistroVehicularDTO consultaVehiculoDTO);

    /**
     * @see IRRegistroVehicular#consultarVehiculo(String)
     */
    VehiculoDTO consultarVehiculo(String placa);
}
