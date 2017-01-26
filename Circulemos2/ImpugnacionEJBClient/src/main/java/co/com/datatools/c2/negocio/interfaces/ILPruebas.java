package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProrrogaPruebaDTO;
import co.com.datatools.c2.dto.RegistrarPruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILPruebas {

    /**
     * @see ILPruebas#registrarSolicitudPrueba(SolicitudPruebasBackDTO)
     */
    public byte[] registrarSolicitudPrueba(SolicitudPruebasBackDTO solicitud, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * @see ILPruebas#registrarProrrogaPrueba(ProrrogaPruebaDTO)
     */
    public void registrarProrrogaPrueba(ProrrogaPruebaDTO prorroga, Long idProceso) throws CirculemosNegocioException;

    /**
     * @see ILPruebas#cerrarPruebas(Long)
     */
    public byte[] cerrarPruebas(Long idProceso, Boolean devolverDocumento) throws CirculemosNegocioException;

    /**
     * @see ILPruebas#registrarPruebas(RegistrarPruebaDTO)
     */
    public byte[] registrarPruebas(RegistrarPruebaDTO registro) throws CirculemosNegocioException;

    /**
     * @see ILPruebas#consultarSolicitudesProceso(SolicitudPruebasBackDTO)
     */
    public List<SolicitudPruebasBackDTO> consultarSolicitudesProceso(SolicitudPruebasBackDTO criterios);

    /**
     * @see ILPruebas#cerrarPruebasJob()
     */
    public void cerrarPruebasJob();

    /**
     * @author giovanni.velandia
     * @return
     */
    public List<ProcesoDTO> consultarSolicitudPruebasJob();

}
