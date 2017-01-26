package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;

@Local
public interface ILDescuentoComparendo {

    /**
     * @see IRDescuentoComparendo#consultarConfiguracionDescuento(Integer, Integer, Integer, Date)
     */
    public ConfiguracionDescuentoDTO consultarConfiguracionDescuento(Integer orgTransito, Integer codigoMedioImposicion,
            Integer idTipoNotificacion, Date fechaImposicion);
}