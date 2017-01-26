package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;

@Remote
public interface IRRegistroVehicular {

    /**
     * Se encarga de consultar la información del vehículo de acuerdo a los filtros del objeto recibido
     * 
     * @param consultaVehiculoDTO
     *            Objeto que contiene los filtros de consulta que son (codigoOrganismo, placa, idTipoIdentificacion, numeroIdentificacion)
     * @return listado de vehiculos encontradas, si no encuentra retorna lista vacia
     * @author dixon.alvarez
     */
    List<VehiculoDTO> consultarInformacionVehiculo(ConsultaRegistroVehicularDTO consultaVehiculoDTO);

    /**
     * Consulta la informacion del vehiculo asociado a la placa recibida en el parametro.
     * 
     * @param placa
     *            placa del vehiculo a consultar
     * @return informacion del vehiculo
     * @author divier.casas(2015-10-07)
     */
    VehiculoDTO consultarVehiculo(String placa);
}
