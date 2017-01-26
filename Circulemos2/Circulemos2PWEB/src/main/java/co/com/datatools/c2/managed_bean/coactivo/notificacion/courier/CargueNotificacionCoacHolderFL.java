package co.com.datatools.c2.managed_bean.coactivo.notificacion.courier;

import co.com.datatools.c2.dto.CargueNotificaCoactivoResulDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class CargueNotificacionCoacHolderFL extends AbstractC2ManagedBean{
	
	private static final long serialVersionUID = 1L;
	public static final String NOMBRE_BEAN = "cargueNotificacionCoacHolderFL";
	
	private boolean archivoCargado = false;
	
	private ArchivoTransportableDTO archivo;
	
	private CargueNotificaCoactivoResulDTO respuestaCargue;

	public boolean isArchivoCargado() {
		return archivoCargado;
	}

	public void setArchivoCargado(boolean archivoCargado) {
		this.archivoCargado = archivoCargado;
	}

	public ArchivoTransportableDTO getArchivo() {
		return archivo;
	}

	public void setArchivo(ArchivoTransportableDTO archivo) {
		this.archivo = archivo;
	}

	public void setRespuestaCargue(CargueNotificaCoactivoResulDTO respuestaCargue) {
		this.respuestaCargue = respuestaCargue;
	}
	
	public CargueNotificaCoactivoResulDTO getRespuestaCargue(){
		return this.respuestaCargue;
	}
}
