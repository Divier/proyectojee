package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;

@Remote
public interface IRRegistroVehicularPropietario {

    /**
     * Se encarga de consultar los propietarios registrados de acuerdo a los filtros recibidos en el objeto
     * 
     * @param consultaRegistroVehicularDTO
     *            Objeto que contiene los filtros de consulta que son (codigoOrganismo, placa, idTipoIdentificacion, numeroIdentificacion)
     * @return Listado de propietarios de vehiculo encontrados, si no encuentra retorna lista vacia
     * @author dixon.alvarez
     */
    List<PropietarioVehiculoDTO> consultarPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO);

    /**
     * Consulta la informacion del propietario asociado al vehiculo consultado.
     * 
     * @param consultaRegistroVehicularDTO
     *            consultaRegistroVehicularDTO con informacion del vehiculo a consultar
     * @return informacion del propietario asociado al vehiculo
     * @author divier.casas(2015-10-08)
     */
    PropietarioVehiculoDTO consultarMayorPropietario(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO);

}