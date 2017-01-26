package co.com.datatools.seguridad.dto.comun;

import java.io.Serializable;
import java.util.Date;

public class ConsultaRolDto implements Serializable {

    private static final long serialVersionUID = 7735345045590103641L;

    private Integer idRol;
    private Date fechaFiltroInicial;
    private Date fechaFiltroFinal;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Date getFechaFiltroInicial() {
        return fechaFiltroInicial;
    }

    public void setFechaFiltroInicial(Date fechaFiltroInicial) {
        this.fechaFiltroInicial = fechaFiltroInicial;
    }

    public Date getFechaFiltroFinal() {
        return fechaFiltroFinal;
    }

    public void setFechaFiltroFinal(Date fechaFiltroFinal) {
        this.fechaFiltroFinal = fechaFiltroFinal;
    }

}
