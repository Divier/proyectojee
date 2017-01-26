package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ubicabilidad.ItUbicabilidadAxisDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface que permite llevar a cabo la logica relacionada con la solicitud de ubicabilidad a un tercero
 * 
 * @author divier.casas(2016-05-17)
 */
@Local
public interface ILUbicabilidadTercero {

    /**
     * @see IRUbicabilidadTercero#solicitarUbicabilidadTerceros(int)
     */
    public RespuestaSolicitudUbicabilidadTerceroDTO solicitarUbicabilidadTerceros(int codigoOrganismo);

    /**
     * @see IRUbicabilidadTercero#procesarUbicabilidadTerceros(RespuestaSolicitudUbicabilidadTerceroDTO, ItUbicabilidadAxisDTO, int)
     */
    public RespuestaSolicitudUbicabilidadTerceroDTO procesarUbicabilidadTerceros(
            RespuestaSolicitudUbicabilidadTerceroDTO rSolicitudUbicabilidadTercero,
            ItUbicabilidadAxisDTO itUbicabilidadAxisDTO, int codigoOrganismo);

    /**
     * @see IRUbicabilidadTercero#actualizarEstadoUbicabilidad(long, EnumEstadoLectura)
     */
    public void actualizarEstadoUbicabilidad(long idUbicabilidad, EnumEstadoLectura estadoLectura);

    /**
     * @see IRUbicabilidadTercero#consultarUbicabilidadTercero(EnumEstadoLectura)
     */
    public List<ItUbicabilidadAxisDTO> consultarUbicabilidadTercero(EnumEstadoLectura estadoLectura);
}