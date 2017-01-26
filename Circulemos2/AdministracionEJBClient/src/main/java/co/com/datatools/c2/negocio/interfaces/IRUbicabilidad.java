package co.com.datatools.c2.negocio.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.ubicabilidad.CargueMasivoUbicabilidadDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRUbicabilidad {

    /**
     * Permite consultar todos los correos asociados a una persona que son notificables
     * 
     * @param idPersona
     * @return
     * @author divier.casas mod(23-11-2016 @author diego.fonseca)
     */
    List<CorreoPersonaDTO> consultarCorreosNotificables(Long idPersona);

    /**
     * Permite consultar correos según filtros recibidos
     * 
     * @param correoPersonaDTO
     * @return
     */
    public List<CorreoPersonaDTO> consultarCorreos(CorreoPersonaDTO correoPersonaDTO);

    /**
     * Permite consultar telefonos según filtros recibidos
     * 
     * @param correoPersonaDTO
     * @return
     */
    public List<TelefonoPersonaDTO> consultarTelefonos(TelefonoPersonaDTO telefonoPersonaDTO);

    /**
     * Permite consultar direccionPersona según filtros recibidos
     * 
     * @param correoPersonaDTO
     * @return
     */
    public List<DireccionPersonaDTO> consultarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO);

    /**
     * Realiza el cargue masivo de ubicabilidad (HU_CIR20_DAT_UBI_006)
     * 
     * @param csvFile
     * @param respuestaCargue
     * @param cargueMasivoUbicabilidad
     * @throws FileNotFoundException
     * @throws IOException
     * @author julio.pinzon 2016-11-22
     * @throws CirculemosNegocioException
     */
    public Future<CargueArchivoDTO> procesarCargueMasivoUbic(CargueArchivoDTO respuestaCargue,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) throws CirculemosNegocioException;
}