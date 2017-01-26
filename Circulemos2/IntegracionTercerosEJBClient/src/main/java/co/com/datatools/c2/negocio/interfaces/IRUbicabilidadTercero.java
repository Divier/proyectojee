package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ubicabilidad.ItUbicabilidadAxisDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Interface que permite llevar a cabo la logica relacionada con la solicitud de ubicabilidad a un tercero
 * 
 * @author divier.casas(2016-05-17)
 */
@Remote
public interface IRUbicabilidadTercero {

    /**
     * Consulta de registros de ubicabilidad de un tercero.
     * 
     * @param codigoOrganismo
     * 
     * @return respuesta con las cantidades de registros leidos, recibidos y no recibidos.
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple
     * @author divier.casas(2016-05-17)
     */
    public RespuestaSolicitudUbicabilidadTerceroDTO solicitarUbicabilidadTerceros(int codigoOrganismo);

    /**
     * Procesa los registros de ubicabilidad de un tercero.
     * 
     * @param ItUbicabilidadAxisDTO
     * @param codigoOrganismo
     * @param RespuestaSolicitudUbicabilidadTerceroDTO
     * 
     * @return respuesta con las cantidades de registros leidos, recibidos y no recibidos.
     * @throws CirculemosNegocioException
     *             si alguna regla del negocio no se cumple
     * @author divier.casas(2016-05-24)
     */
    public RespuestaSolicitudUbicabilidadTerceroDTO procesarUbicabilidadTerceros(
            RespuestaSolicitudUbicabilidadTerceroDTO respuestaSolicitudUbicabilidadTerceroDTO,
            ItUbicabilidadAxisDTO itUbicabilidadAxisDTO, int codigoOrganismo);

    /**
     * Actualizar el estado del registro en la tabla de ubicabilidad de acuerdo al procesamiento
     * 
     * @param long
     * @param EnumEstadoLectura
     * 
     * @author divier.casas(2016-05-24)
     */
    public void actualizarEstadoUbicabilidad(long idUbicabilidad, EnumEstadoLectura estadoLectura);

    /**
     * Consultar los registros de ubicabilidad del tercero
     * 
     * @param EnumEstadoLectura
     * 
     * @author divier.casas(2016-05-24)
     */
    public List<ItUbicabilidadAxisDTO> consultarUbicabilidadTercero(EnumEstadoLectura estadoLectura);
}