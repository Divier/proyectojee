package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;

@Local
public interface ILDireccion {

    /**
     * @see IRDireccion#ingresarDireccion(DireccionDTO, EnumTipoValidacionDireccion)
     */
    RespuestaIngresarDireccionDTO ingresarDireccion(DireccionDTO direccionDTO,
            EnumTipoValidacionDireccion tipoValidacion);

    /**
     * @see IRDireccion#validarDireccion(DireccionDTO,EnumTipoValidacionDireccion)
     */
    RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> validarDireccion(DireccionDTO direccion,
            EnumTipoValidacionDireccion tipoValidacion);

    DireccionDTO consultarDireccion(long idDireccion);

}
