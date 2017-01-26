package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class LogReporteDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int idLogReporte;
    private Date fecha;
    private String login;
    private TipoReporteDTO tipoReporteDTO;
    private TipoLogReporteDTO tipoLogReporteDTO;

    // Constructors Declaration

    public LogReporteDTO() {

    }

    // Start Methods Declaration

    public int getIdLogReporte() {
        return idLogReporte;
    }

    public void setIdLogReporte(int idLogReporte) {
        this.idLogReporte = idLogReporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TipoReporteDTO getTipoReporteDTO() {
        return tipoReporteDTO;
    }

    public void setTipoReporteDTO(TipoReporteDTO tipoReporteDTO) {
        this.tipoReporteDTO = tipoReporteDTO;
    }

    public TipoLogReporteDTO getTipoLogReporteDTO() {
        return tipoLogReporteDTO;
    }

    public void setTipoLogReporteDTO(TipoLogReporteDTO tipoLogReporteDTO) {
        this.tipoLogReporteDTO = tipoLogReporteDTO;
    }

    // Finish the class
}