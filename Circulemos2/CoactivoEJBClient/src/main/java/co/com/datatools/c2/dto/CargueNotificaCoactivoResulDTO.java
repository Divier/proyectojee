package co.com.datatools.c2.dto;

import java.util.Map;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

public class CargueNotificaCoactivoResulDTO  implements EntidadDtoC2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  Map<CoactivoDTO, DireccionPersonaDTO> coactivoDireccionNotif;
	private ArchivoTransportableDTO reporteInconsistencias;
	private int totalCoactivos;
	private int notificacionesExitosas;
	private int notificacionesNoExitosas;
	private int totalInconsistencias;
	
	public Map<CoactivoDTO, DireccionPersonaDTO> getCoactivoDireccionNotif() {
		return coactivoDireccionNotif;
	}
	public void setCoactivoDireccionNotif(Map<CoactivoDTO, DireccionPersonaDTO> coactivoDireccionNotif) {
		this.coactivoDireccionNotif = coactivoDireccionNotif;
	}
	public ArchivoTransportableDTO getReporteInconsistencias() {
		return reporteInconsistencias;
	}
	public void setReporteInconsistencias(ArchivoTransportableDTO reporteInconsistencias) {
		this.reporteInconsistencias = reporteInconsistencias;
	}
	public int getTotalCoactivos() {
		return totalCoactivos;
	}
	public void setTotalCoactivos(int totalCoactivos) {
		this.totalCoactivos = totalCoactivos;
	}
	public int getNotificacionesExitosas() {
		return notificacionesExitosas;
	}
	public void setNotificacionesExitosas(int notificacionesExitosas) {
		this.notificacionesExitosas = notificacionesExitosas;
	}
	public int getNotificacionesNoExitosas() {
		return notificacionesNoExitosas;
	}
	public void setNotificacionesNoExitosas(int notificacionesNoExitosas) {
		this.notificacionesNoExitosas = notificacionesNoExitosas;
	}
	public int getTotalInconsistencias() {
		return totalInconsistencias;
	}
	public void setTotalInconsistencias(int totalInconsistencias) {
		this.totalInconsistencias = totalInconsistencias;
	}
}
