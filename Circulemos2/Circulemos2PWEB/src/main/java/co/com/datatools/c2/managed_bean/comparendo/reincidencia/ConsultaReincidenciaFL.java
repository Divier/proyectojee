package co.com.datatools.c2.managed_bean.comparendo.reincidencia;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.HistoricoComparendoDTO;

public class ConsultaReincidenciaFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private Integer idTipoReincidencia;
    private Integer idTipoDocInfractor;
    private String numDocInfractor;

    private List<HistoricoComparendoDTO> resultadoConsulta;
    private HistoricoComparendoDTO historicoSeleccion;

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdTipoReincidencia() {
        return idTipoReincidencia;
    }

    public void setIdTipoReincidencia(Integer idTipoReincidencia) {
        this.idTipoReincidencia = idTipoReincidencia;
    }

    public Integer getIdTipoDocInfractor() {
        return idTipoDocInfractor;
    }

    public void setIdTipoDocInfractor(Integer idTipoDocInfractor) {
        this.idTipoDocInfractor = idTipoDocInfractor;
    }

    public String getNumDocInfractor() {
        return numDocInfractor;
    }

    public void setNumDocInfractor(String numDocInfractor) {
        this.numDocInfractor = numDocInfractor;
    }

    public List<HistoricoComparendoDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<HistoricoComparendoDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public HistoricoComparendoDTO getHistoricoSeleccion() {
        return historicoSeleccion;
    }

    public void setHistoricoSeleccion(HistoricoComparendoDTO historicoSeleccion) {
        this.historicoSeleccion = historicoSeleccion;
    }

}
