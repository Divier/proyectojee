/**
 * 
 */
package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Consulta de formularios
 * 
 * @author luis.forero (2015-05-11)
 */
public class ConsultaFormularioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idTipoFormulario;
    private List<Integer> lstIdEstadoFormulario;
    private Date fechaInicial;
    private Date fechaFinal;
    private Integer idTipoDocumento;
    private String numeroDocumentoIdentidad;
    private String numeroPlacaResponsable;

    private Integer filaInicial;
    private Integer maxResultados;

    public ConsultaFormularioDTO() {
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public List<Integer> getLstIdEstadoFormulario() {
        return lstIdEstadoFormulario;
    }

    public void setLstIdEstadoFormulario(List<Integer> lstIdEstadoFormulario) {
        this.lstIdEstadoFormulario = lstIdEstadoFormulario;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getNumeroPlacaResponsable() {
        return numeroPlacaResponsable;
    }

    public void setNumeroPlacaResponsable(String numeroPlacaResponsable) {
        this.numeroPlacaResponsable = numeroPlacaResponsable;
    }

    public Integer getFilaInicial() {
        return filaInicial;
    }

    public void setFilaInicial(Integer filaInicial) {
        this.filaInicial = filaInicial;
    }

    public Integer getMaxResultados() {
        return maxResultados;
    }

    public void setMaxResultados(Integer maxResultados) {
        this.maxResultados = maxResultados;
    }
}