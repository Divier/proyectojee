package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.regveh.ConsultaEmpresaDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;

@Remote
public interface IRRegistroVehicularEmpresa {

    /**
     * Se encarga de consultar la empresa de un vehiculo de servicio público en una fecha determinada
     * 
     * @param consultaEmpresaDTO
     *            Objeto que contiene los filtros de consulta
     * @return Listado de empresas de vehiculos encontradas, si no encuentra retorna lista vacia
     * 
     * @author dixon.alvarez
     */
    List<EmpresaVehiculoDTO> consultarEmpresa(ConsultaEmpresaDTO consultaEmpresaDTO);

    /**
     * Consulta la informacion del vehiculo asociado a la placa recibida en el parametro.
     * 
     * @param placa
     *            placa del vehiculo a consultar
     * @param codigoOrganismo
     *            codigoOrganismo
     * @param fechaInfraccion
     *            fecha del comparendo
     * @return informacion de la empresa asociada al vehiculo
     * @author divier.casas(2015-10-08)
     */
    EmpresaVehiculoDTO consultarEmpresa(String placa, int codigoOrganismo, Date fechaInfraccion);

}
