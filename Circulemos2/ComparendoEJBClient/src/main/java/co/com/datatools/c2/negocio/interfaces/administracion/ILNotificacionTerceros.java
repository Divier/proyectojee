package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;

@Local
public interface ILNotificacionTerceros {
    /**
     * Metodo que consulta y genera el doc de las citaciones que se pueden notificar por correo electronico
     * 
     * @return
     */
    public EnvioNotificacionDTO consultarCitacionesNotifica(int codOrganismo);

    public Integer[] enviarNotificaciones(int codOrganismo);

    public List<EvidenciaDTO> obtenerEvidencia(Long cicomparendo, int codOrganismo);
}