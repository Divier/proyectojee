package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;

@Remote
public interface IRDireccion {

    /**
     * Ingresa una direccion al sistema y realiza un llamado a validar para determinar si la direccion es valida. Retorna null si la direccion fue
     * correctamente ingresada en caso contrario retorna los codigo de error que aparecen al momento de ingresar la direccion. Llama al metodo
     * validarDireccion.
     * 
     * @param direccionDTO
     *            Un objeto con datos de direccion
     * @param tipoValidacion
     *            Indica que validaciones se van a tener en cuenta para verificar la direccion
     * @return Respuesta del proceso de insercion indicando si ocurren errores
     * @author rodrigo.cruz
     */
    RespuestaIngresarDireccionDTO ingresarDireccion(DireccionDTO direccionDTO,
            EnumTipoValidacionDireccion tipoValidacion);

    /**
     * Valida el contenido de una direccion.
     * 
     * @param direccion
     *            Objeto a validar
     * @param tipoValidacion
     *            Indica que validaciones se van a tener en cuenta para verificar la direccion
     * @return Respuesta del proceso de validacion indicando si ocurren errores
     * @author rodrigo.cruz
     */
    RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> validarDireccion(DireccionDTO direccion,
            EnumTipoValidacionDireccion tipoValidacion);

    /**
     * Consulta la direccion segun el id
     * 
     * @param idDireccion
     * @return
     */
    DireccionDTO consultarDireccion(long idDireccion);

}
