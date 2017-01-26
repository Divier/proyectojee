package co.com.datatools.c2.entidades.id;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1
 */

@Embeddable
public class MotivoEstadoObligacionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "codigo_estado_obligacion")
    private Integer codigoEstadoObligacion;

    @Basic(optional = false)
    @Column(name = "codigo_motivo_estado")
    private Integer codigoMotivoEstado;

    public MotivoEstadoObligacionId() {
    }

    public Integer getCodigoEstadoObligacion() {
        return codigoEstadoObligacion;
    }

    public void setCodigoEstadoObligacion(Integer codigoEstadoObligacion) {
        this.codigoEstadoObligacion = codigoEstadoObligacion;
    }

    public Integer getCodigoMotivoEstado() {
        return codigoMotivoEstado;
    }

    public void setCodigoMotivoEstado(Integer codigoMotivoEstado) {
        this.codigoMotivoEstado = codigoMotivoEstado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCodigoEstadoObligacion() == null) ? 0 : getCodigoEstadoObligacion().hashCode());
        result = prime * result + ((getCodigoMotivoEstado() == null) ? 0 : getCodigoMotivoEstado().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MotivoEstadoObligacionId other = (MotivoEstadoObligacionId) obj;
        if (getCodigoEstadoObligacion() == null) {
            if (other.getCodigoEstadoObligacion() != null)
                return false;
        } else if (!getCodigoEstadoObligacion().equals(other.getCodigoEstadoObligacion()))
            return false;
        if (getCodigoMotivoEstado() == null) {
            if (other.getCodigoMotivoEstado() != null)
                return false;
        } else if (!getCodigoMotivoEstado().equals(other.getCodigoMotivoEstado()))
            return false;
        return true;
    }

}
