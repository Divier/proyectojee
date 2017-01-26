package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ProrrogaPruebaDTO;
import co.com.datatools.c2.dto.RegistrarPruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRPruebas {

    /**
     * Registra una nueva solicitud de pruebas
     * 
     * @param solicitud
     * @throws CirculemosNegocioException
     */
    public byte[] registrarSolicitudPrueba(SolicitudPruebasBackDTO solicitud, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * Registra la prorroga de la prueba
     * 
     * @param prorroga
     * @param idProceso
     * @throws CirculemosNegocioException
     */
    public void registrarProrrogaPrueba(ProrrogaPruebaDTO prorroga, Long idProceso) throws CirculemosNegocioException;

    /**
     * Cierra la estapa de pruebas
     * 
     * @param idProceso
     * @param devolverDocumento
     * @throws CirculemosNegocioException
     */
    public byte[] cerrarPruebas(Long idProceso, Boolean devolverDocumento) throws CirculemosNegocioException;

    /**
     * Registra las pruebas del proceso
     * 
     * @param registro
     * @throws CirculemosNegocioException
     */
    public byte[] registrarPruebas(RegistrarPruebaDTO registro) throws CirculemosNegocioException;

    /**
     * Consulta las solicitudes de pruebas bajo unos criterios
     * 
     * @param criterios
     * @return
     */
    public List<SolicitudPruebasBackDTO> consultarSolicitudesProceso(SolicitudPruebasBackDTO criterios);

    /**
     * Cierra las etapas de pruebas a traves del Job
     */
    public void cerrarPruebasJob();
}