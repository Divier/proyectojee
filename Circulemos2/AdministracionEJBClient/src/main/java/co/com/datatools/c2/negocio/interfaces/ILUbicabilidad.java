package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Local;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CargueMasivoUbicabilidadDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILUbicabilidad {

    /**
     * @see IRUbicabilidad#consultarCorreosNotificables(Long)
     */
    List<CorreoPersonaDTO> consultarCorreosNotificables(Long idPersona);

    /**
     * @see IRUbicabilidad#consultarCorreos(CorreoPersonaDTO)
     */
    public List<CorreoPersonaDTO> consultarCorreos(CorreoPersonaDTO correoPersonaDTO);

    /**
     * @see IRUbicabilidad#consultarTelefonos(TelefonoPersonaDTO)
     */
    public List<TelefonoPersonaDTO> consultarTelefonos(TelefonoPersonaDTO telefonoPersonaDTO);

    /**
     * @see IRUbicabilidad#consultarDireccionPersona(DireccionPersonaDTO)
     */
    public List<DireccionPersonaDTO> consultarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO);

    /**
     * @see IRUbicabilidad#procesarCargueMasivoUbic(CargueArchivoDTO, CargueMasivoUbicabilidadDTO)
     */
    public Future<CargueArchivoDTO> procesarCargueMasivoUbic(CargueArchivoDTO respuestaCargue,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) throws CirculemosNegocioException;

    /**
     * Realiza el cargue de una linea de ubicabilidad persona
     * 
     * @param linea
     * @param respuestaCargue
     * @param usuarioValida
     * @param cargueMasivoUbicabilidad
     * @return Texto de archivo de inconsistencias
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-11-22
     */
    public String cargaLineaUbicabilidadPersona(String linea, CargueArchivoDTO respuestaCargue,
            UsuarioPersonaDTO usuarioValida, CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad)
            throws CirculemosNegocioException;
}