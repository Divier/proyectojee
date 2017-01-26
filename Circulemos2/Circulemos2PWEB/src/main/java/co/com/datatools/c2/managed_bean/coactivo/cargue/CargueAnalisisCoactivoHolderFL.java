package co.com.datatools.c2.managed_bean.coactivo.cargue;

import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * permite manipular el archivo que se adjunte en pantalla
 * 
 * @author Dixon.Alvarez
 */
public class CargueAnalisisCoactivoHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "cargueAnalisisCoactivoHolderFL";

    private ConfiguracionCoactivoDTO configuracionCoactivoDTO;
    private ArchivoTransportableDTO archivoCargadoAnalisis;
    private boolean archivoCargado;
    private boolean archivoProcesado;
    private boolean archivoEnProceso;
    private boolean sinCargueActivo;
    private CargueArchivoDTO cargue;
    private ArchivoTransportableDTO archivoInconsistencias;
    private ArchivoTransportableDTO archivoValidas;
    private boolean archivoEnProcesoOtroUsuario;
    private boolean progresoIniciado;
    private String cantidadMultasArchivo;
    private String multasNoValidasCoactivo;
    private String multasValidasCoactivo;

    public ConfiguracionCoactivoDTO getConfiguracionCoactivoDTO() {
        return configuracionCoactivoDTO;
    }

    public void setConfiguracionCoactivoDTO(ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        this.configuracionCoactivoDTO = configuracionCoactivoDTO;
    }

    public ArchivoTransportableDTO getArchivoCargadoAnalisis() {
        return archivoCargadoAnalisis;
    }

    public void setArchivoCargadoAnalisis(ArchivoTransportableDTO archivoCargadoAnalisis) {
        this.archivoCargadoAnalisis = archivoCargadoAnalisis;
    }

    public boolean isArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(boolean archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public boolean isArchivoProcesado() {
        return archivoProcesado;
    }

    public void setArchivoProcesado(boolean archivoProcesado) {
        this.archivoProcesado = archivoProcesado;
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

    public String getCantidadMultasArchivo() {
        return cantidadMultasArchivo;
    }

    public void setCantidadMultasArchivo(String cantidadMultasArchivo) {
        this.cantidadMultasArchivo = cantidadMultasArchivo;
    }

    public String getMultasNoValidasCoactivo() {
        return multasNoValidasCoactivo;
    }

    public void setMultasNoValidasCoactivo(String multasNoValidasCoactivo) {
        this.multasNoValidasCoactivo = multasNoValidasCoactivo;
    }

    public String getMultasValidasCoactivo() {
        return multasValidasCoactivo;
    }

    public void setMultasValidasCoactivo(String multasValidasCoactivo) {
        this.multasValidasCoactivo = multasValidasCoactivo;
    }

    public ArchivoTransportableDTO getArchivoValidas() {
        return archivoValidas;
    }

    public void setArchivoValidas(ArchivoTransportableDTO archivoValidas) {
        this.archivoValidas = archivoValidas;
    }

}
