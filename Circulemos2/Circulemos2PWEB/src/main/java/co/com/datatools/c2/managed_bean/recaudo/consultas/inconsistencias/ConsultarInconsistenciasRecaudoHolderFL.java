package co.com.datatools.c2.managed_bean.recaudo.consultas.inconsistencias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.RechazoRecaudoResDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class ConsultarInconsistenciasRecaudoHolderFL extends AbstractSwfManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int codigoOrganismo;
    private String numeroRecaudo;
    private String numeroObligacion;
    private Date fechaInicialGeneracion;
    private Date fechaFinalGeneracion;
    private int tipoReporte;
    private List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaResDTO = new ArrayList<>();
    private List<RechazoRecaudoResDTO> recuadoRechazosDTOList = new ArrayList<>();

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNumeroRecaudo() {
        return numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public Date getFechaInicialGeneracion() {
        return fechaInicialGeneracion;
    }

    public void setFechaInicialGeneracion(Date fechaInicialGeneracion) {
        this.fechaInicialGeneracion = fechaInicialGeneracion;
    }

    public Date getFechaFinalGeneracion() {
        return fechaFinalGeneracion;
    }

    public void setFechaFinalGeneracion(Date fechaFinalGeneracion) {
        this.fechaFinalGeneracion = fechaFinalGeneracion;
    }

    public int getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public List<DetallePagoInconsistenciaResDTO> getDetallePagoInconsistenciaResDTO() {
        return detallePagoInconsistenciaResDTO;
    }

    public void setDetallePagoInconsistenciaResDTO(List<DetallePagoInconsistenciaResDTO> detallePagoInconsistenciaResDTO) {
        this.detallePagoInconsistenciaResDTO = detallePagoInconsistenciaResDTO;
    }

    public List<RechazoRecaudoResDTO> getRecuadoRechazosDTOList() {
        return recuadoRechazosDTOList;
    }

    public void setRecuadoRechazosDTOList(List<RechazoRecaudoResDTO> recuadoRechazosDTOList) {
        this.recuadoRechazosDTOList = recuadoRechazosDTOList;
    }

}
