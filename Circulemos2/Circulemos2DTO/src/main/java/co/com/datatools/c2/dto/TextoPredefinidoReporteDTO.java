package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TextoPredefinidoReporteDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int idTextoPredefinidoReporte;
    private Date fechaFin;
    private Date fechaIncio;
    private String textoEncabezado;
    private String textoPiePagina;
    private TipoReporteDTO tipoReporteDTO;

    // Constructors Declaration

    public TextoPredefinidoReporteDTO() {

    }

    // Start Methods Declaration

    public int getIdTextoPredefinidoReporte() {
        return idTextoPredefinidoReporte;
    }

    public void setIdTextoPredefinidoReporte(int idTextoPredefinidoReporte) {
        this.idTextoPredefinidoReporte = idTextoPredefinidoReporte;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(Date fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public String getTextoEncabezado() {
        return textoEncabezado;
    }

    public void setTextoEncabezado(String textoEncabezado) {
        this.textoEncabezado = textoEncabezado;
    }

    public String getTextoPiePagina() {
        return textoPiePagina;
    }

    public void setTextoPiePagina(String textoPiePagina) {
        this.textoPiePagina = textoPiePagina;
    }

    public TipoReporteDTO getTipoReporteDTO() {
        return tipoReporteDTO;
    }

    public void setTipoReporteDTO(TipoReporteDTO tipoReporteDTO) {
        this.tipoReporteDTO = tipoReporteDTO;
    }

    // Finish the class
}