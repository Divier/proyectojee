package co.com.datatools.c2.negocio.fachada;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicular;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularEmpresa;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularLicencia;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularPropietario;

@Stateless(name = "FachadaRegistroVehicularEJB")
@LocalBean
public class FachadaRegistroVehicularEJB implements IRFachadaRegistroVehicular {
    private final static Logger logger = Logger.getLogger(FachadaRegistroVehicularEJB.class.getName());

    @EJB
    private ILRegistroVehicularLicencia iLRegistroVehicularLicencia;

    @EJB
    private ILRegistroVehicular iLRegistroVehicular;

    @EJB
    private ILRegistroVehicularEmpresa iLRegistroVehicularEmpresa;

    @EJB
    private ILRegistroVehicularPropietario iLRegistroVehicularPropietario;

    @Override
    public LicenciaConduccionDTO consultarLicencia(String numeroLicencia, int codigoOrganismo) {
        logger.debug("FachadaRegistroVehicularEJB::consultarLicencia(String,int)");
        LicenciaConduccionDTO licenciaConduccionDTO = iLRegistroVehicularLicencia.consultarLicencia(numeroLicencia,
                codigoOrganismo);
        return licenciaConduccionDTO;
    }

    @Override
    public VehiculoDTO consultarVehiculo(String placa) {
        logger.debug("FachadaRegistroVehicularEJB::consultarVehiculo(String)");
        VehiculoDTO vehiculo = null;
        if (StringUtils.isNotBlank(placa))
            vehiculo = iLRegistroVehicular.consultarVehiculo(placa);
        return vehiculo;
    }

    @Override
    public EmpresaVehiculoDTO consultarEmpresa(String placa, int codigoOrganismo, Date fecha) {
        logger.debug("FachadaRegistroVehicularEJB::consultarVehiculo(String,int,Date)");
        EmpresaVehiculoDTO empresaVehiculoDTO = null;
        if (StringUtils.isNotBlank(placa) && fecha != null)
            empresaVehiculoDTO = iLRegistroVehicularEmpresa.consultarEmpresa(placa, codigoOrganismo, fecha);
        return empresaVehiculoDTO;
    }

    @Override
    public PropietarioVehiculoDTO consultarPropietarioVehiculo(ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO) {
        logger.debug("FachadaRegistroVehicularEJB::consultarPropietarioVehiculo(ConsultaRegistroVehicularDTO)");
        PropietarioVehiculoDTO propietario = null;
        if (consultaRegistroVehicularDTO != null)
            propietario = iLRegistroVehicularPropietario.consultarMayorPropietario(consultaRegistroVehicularDTO);
        return propietario;
    }

}
