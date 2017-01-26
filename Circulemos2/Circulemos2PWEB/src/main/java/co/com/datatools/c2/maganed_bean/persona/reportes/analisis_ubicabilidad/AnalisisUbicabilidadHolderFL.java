package co.com.datatools.c2.maganed_bean.persona.reportes.analisis_ubicabilidad;

import co.com.datatools.c2.dto.ConsultaAnalisisUbicResulDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo analisis ubicabilidad CU_CIR20_DAT_UBI_001
 * 
 * @author ricardo.chavarro
 * 
 */
public class AnalisisUbicabilidadHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    
    public static final String NOMBRE_BEAN = "analisisUbicabilidadHolderFL";
    
    private ConsultaAnalisisUbicResulDTO resultadoConsulta;
    
    private ArchivoTransportableDTO archivoCSVConsulta;
    
    private CargueArchivoDTO cargueArchivo;
    
    private boolean consultaDireccion;
    
	private boolean consultaTelefono;
	
	private boolean consultaCorreo;

	private boolean popUpVisible;

    public AnalisisUbicabilidadHolderFL() {
        super();
    }

	public void setArchivoCSVConsulta(ArchivoTransportableDTO archivo) {
		this.archivoCSVConsulta = archivo;
	}

	public ArchivoTransportableDTO getArchivoCSVConsulta() {
		return archivoCSVConsulta;
	}

	public void setResultadoConsulta(ConsultaAnalisisUbicResulDTO resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}

	public ConsultaAnalisisUbicResulDTO getResultadoConsulta() {
		return resultadoConsulta;
	}

	public CargueArchivoDTO getCargueArchivo() {
		return cargueArchivo;
	}

	public void setCargueArchivo(CargueArchivoDTO cargueArchivo) {
		this.cargueArchivo = cargueArchivo;
	}
	
	public boolean isCargueActivo(){
		return this.cargueArchivo != null;
	}

	public boolean isConsultaDireccion() {
		return consultaDireccion;
	}

	public void setConsultaDireccion(boolean consultaDireccion) {
		this.consultaDireccion = consultaDireccion;
	}

	public boolean isConsultaTelefono() {
		return consultaTelefono;
	}

	public void setConsultaTelefono(boolean consultaTelefono) {
		this.consultaTelefono = consultaTelefono;
	}

	public boolean isConsultaCorreo() {
		return consultaCorreo;
	}

	public void setConsultaCorreo(boolean consultaCorreo) {
		this.consultaCorreo = consultaCorreo;
	}

	public boolean isPopUpVisible() {
		return popUpVisible;
	}

	public void setPopUpVisible(boolean popUpVisible) {
		this.popUpVisible = popUpVisible;
	}

}
