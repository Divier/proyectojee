package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Valores para consultar masivamente documentos
 * 
 * @author divier.casas
 * 
 */
public class ConsultaGeneracionMasivaDocumentosDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private Integer idTipoProceso;
    private Integer idPaso;
    private Integer idDocumento;
    private String numProc;
    private Date fechaProcIni;
    private Date fechaProcFin;
    private Date fechaPasoIni;
    private Date fechaPasoFin;
    private Integer cantMaxDocs = 500;

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public Integer getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Integer idPaso) {
        this.idPaso = idPaso;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNumProc() {
        return numProc;
    }

    public void setNumProc(String numProc) {
        this.numProc = numProc;
    }

    public Date getFechaProcIni() {
        return fechaProcIni;
    }

    public void setFechaProcIni(Date fechaProcIni) {
        this.fechaProcIni = fechaProcIni;
    }

    public Date getFechaProcFin() {
        return fechaProcFin;
    }

    public void setFechaProcFin(Date fechaProcFin) {
        this.fechaProcFin = fechaProcFin;
    }

    public Date getFechaPasoIni() {
        return fechaPasoIni;
    }

    public void setFechaPasoIni(Date fechaPasoIni) {
        this.fechaPasoIni = fechaPasoIni;
    }

    public Date getFechaPasoFin() {
        return fechaPasoFin;
    }

    public void setFechaPasoFin(Date fechaPasoFin) {
        this.fechaPasoFin = fechaPasoFin;
    }

    public Integer getCantMaxDocs() {
        return cantMaxDocs;
    }

    public void setCantMaxDocs(Integer cantMaxDocs) {
        this.cantMaxDocs = cantMaxDocs;
    }
}