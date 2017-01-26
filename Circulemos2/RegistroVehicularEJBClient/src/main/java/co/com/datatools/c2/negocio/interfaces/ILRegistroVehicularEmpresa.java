package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;

@Local
public interface ILRegistroVehicularEmpresa {

    EmpresaVehiculoDTO consultarEmpresa(String placa, int codigoOrganismo, Date fechaInfraccion);

}
