package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;

@Local
public interface ILRegistroVehicularPropietario {

    /**
     * @see IRRegistroVehicularPropietario#consultarPropietario(ConsultaPropietarioDTO)
     */
    List<PropietarioVehiculoDTO> consultarPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO);

    /**
     * @see IRRegistroVehicularPropietario#consultarMayorPropietario(ConsultaRegistroVehicularDTO)
     */
    PropietarioVehiculoDTO consultarMayorPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO);

}