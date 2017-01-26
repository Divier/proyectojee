package co.com.datatools.c2.dto.cartera;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contiene la información de la nota de cartera a generar. Si es necesario se actualiza la información de la obligación.
 * 
 * @author giovanni.velandia
 * 
 */
public class IngresoNotaCarteraDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaObligacion;
    private long idCartera;
    private int idConceptoNota;
    private long idDeudor;
    private BigDecimal valor;

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }

    public int getIdConceptoNota() {
        return idConceptoNota;
    }

    public void setIdConceptoNota(int idConceptoNota) {
        this.idConceptoNota = idConceptoNota;
    }

    public long getIdDeudor() {
        return idDeudor;
    }

    public void setIdDeudor(long idDeudor) {
        this.idDeudor = idDeudor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
