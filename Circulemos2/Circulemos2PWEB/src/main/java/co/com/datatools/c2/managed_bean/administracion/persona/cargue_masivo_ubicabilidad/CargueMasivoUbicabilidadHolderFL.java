package co.com.datatools.c2.managed_bean.administracion.persona.cargue_masivo_ubicabilidad;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo analisis ubicabilidad CU_CIR20_DAT_UBI_001
 * 
 * @author ricardo.chavarro
 * 
 */
public class CargueMasivoUbicabilidadHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "cargueMasivoUbicabilidadHolderFL";
    private Integer idTipoFuenteInformacion;
    private Integer idTipoCargueArchivo;
    private ArchivoTransportableDTO archivoCSVCargue;
    private boolean archivoCargado;
    private boolean archivoProcesado;
    private boolean archivoEnProceso;
    private boolean sinCargueActivo;
    private CargueArchivoDTO cargue;
    private ArchivoTransportableDTO archivoInconsistencias;
    private boolean archivoEnProcesoOtroUsuario;
    private boolean progresoIniciado;

    public CargueMasivoUbicabilidadHolderFL() {
        super();
    }

    public void setArchivoCSVCargue(ArchivoTransportableDTO archivo) {
        this.archivoCSVCargue = archivo;
    }

    public ArchivoTransportableDTO getArchivoCSVCargue() {
        return archivoCSVCargue;
    }

    public boolean isArchivoProcesado() {
        return archivoProcesado;
    }

    public void setArchivoProcesado(boolean archivoProcesado) {
        this.archivoProcesado = archivoProcesado;
    }

    public boolean isArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(boolean archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public Integer getIdTipoFuenteInformacion() {
        return idTipoFuenteInformacion;
    }

    public void setIdTipoFuenteInformacion(Integer idTipoFuenteInformacion) {
        this.idTipoFuenteInformacion = idTipoFuenteInformacion;
    }

    public Integer getIdTipoCargueArchivo() {
        return idTipoCargueArchivo;
    }

    public void setIdTipoCargueArchivo(Integer idTipoCargueArchivo) {
        this.idTipoCargueArchivo = idTipoCargueArchivo;
    }

    public boolean isArchivoEnProceso() {
        return archivoEnProceso;
    }

    public void setArchivoEnProceso(boolean archivoEnProceso) {
        this.archivoEnProceso = archivoEnProceso;
    }

    public boolean isSinCargueActivo() {
        return sinCargueActivo;
    }

    public void setSinCargueActivo(boolean sinCargueActivo) {
        this.sinCargueActivo = sinCargueActivo;
    }

    public CargueArchivoDTO getCargue() {
        return cargue;
    }

    public void setCargue(CargueArchivoDTO cargue) {
        this.cargue = cargue;
    }

    public ArchivoTransportableDTO getArchivoInconsistencias() {
        return archivoInconsistencias;
    }

    public void setArchivoInconsistencias(ArchivoTransportableDTO archivoInconsistencias) {
        this.archivoInconsistencias = archivoInconsistencias;
    }

    public boolean isArchivoEnProcesoOtroUsuario() {
        return archivoEnProcesoOtroUsuario;
    }

    public void setArchivoEnProcesoOtroUsuario(boolean archivoEnProcesoOtroUsuario) {
        this.archivoEnProcesoOtroUsuario = archivoEnProcesoOtroUsuario;
    }

    public boolean isProgresoIniciado() {
        return progresoIniciado;
    }

    public void setProgresoIniciado(boolean progresoIniciado) {
        this.progresoIniciado = progresoIniciado;
    }

}
