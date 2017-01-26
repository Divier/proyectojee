package co.com.datatools.c2.managed_bean.formularios.consultas;

import java.io.Serializable;

import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;

public class SeguimientoFormularioFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private FormularioDTO formularioDTO;

    private DetalleCambioEstadoDTO seguimientoFormulario;

    private String nombreResponsable;

    public SeguimientoFormularioFL() {
    }

    public SeguimientoFormularioFL(DetalleCambioEstadoDTO seguimientoFormularioDTO) {
        this.seguimientoFormulario = seguimientoFormularioDTO;
    }

    public DetalleCambioEstadoDTO getSeguimientoFormulario() {
        return seguimientoFormulario;
    }

    public void setSeguimientoFormulario(DetalleCambioEstadoDTO seguimientoFormulario) {
        this.seguimientoFormulario = seguimientoFormulario;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreResponsable == null) ? 0 : nombreResponsable.hashCode());
        result = prime
                * result
                + ((seguimientoFormulario != null && seguimientoFormulario.getId() != null) ? seguimientoFormulario
                        .getId().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SeguimientoFormularioFL other = (SeguimientoFormularioFL) obj;
        if (nombreResponsable == null) {
            if (other.nombreResponsable != null) {
                return false;
            }
        } else if (!nombreResponsable.equals(other.nombreResponsable)) {
            return false;
        }
        if (seguimientoFormulario == null) {
            if (other.seguimientoFormulario != null) {
                return false;
            }
        } else if (!seguimientoFormulario.equals(other.seguimientoFormulario)) {
            return false;
        }
        return true;
    }

    public FormularioDTO getFormularioDTO() {
        return formularioDTO;
    }

    public void setFormularioDTO(FormularioDTO formularioDTO) {
        this.formularioDTO = formularioDTO;
    }

}
