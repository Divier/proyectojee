package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de evaluar expediente HU_CIR20_DAT_JUR_004
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
public class EvaluarExpedienteFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "evaluarExpedienteFL";

    private boolean solucionInmediata;
    private Integer idJustificacion;
    private Long idProceso;
    private Long cicomparendo;
    private boolean justificacionObligatoria;
    private List<SelectItem> lstPlantillas;
    private String codigoPlantilla;
    private String consideracionJuridica;
    private String resultadoGuardar;
    private String mensajeAdv;
    private boolean verMensajeAdv;

    public boolean isSolucionInmediata() {
        return solucionInmediata;
    }

    public void setSolucionInmediata(boolean solucionInmediata) {
        this.solucionInmediata = solucionInmediata;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(Integer idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public boolean isJustificacionObligatoria() {
        return justificacionObligatoria;
    }

    public void setJustificacionObligatoria(boolean justificacionObligatoria) {
        this.justificacionObligatoria = justificacionObligatoria;
    }

    public List<SelectItem> getLstPlantillas() {
        return lstPlantillas;
    }

    public void setLstPlantillas(List<SelectItem> lstPlantillas) {
        this.lstPlantillas = lstPlantillas;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public String getConsideracionJuridica() {
        return consideracionJuridica;
    }

    public void setConsideracionJuridica(String consideracionJuridica) {
        this.consideracionJuridica = consideracionJuridica;
    }

    public String getResultadoGuardar() {
        return resultadoGuardar;
    }

    public void setResultadoGuardar(String resultadoGuardar) {
        this.resultadoGuardar = resultadoGuardar;
    }

    public String getMensajeAdv() {
        return mensajeAdv;
    }

    public void setMensajeAdv(String mensajeAdv) {
        this.mensajeAdv = mensajeAdv;
    }

    public boolean isVerMensajeAdv() {
        return verMensajeAdv;
    }

    public void setVerMensajeAdv(boolean verMensajeAdv) {
        this.verMensajeAdv = verMensajeAdv;
    }
}