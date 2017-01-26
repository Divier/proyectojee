package co.com.datatools.c2.dto.common.cartera;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO Utilitario para registrar cartera - CU_CIR20_DAT_CAR_003
 * 
 * @author rodrigo.cruz
 * 
 */
public class RegistroCarteraDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date fechaObligacion;
    private Integer idOrigen;
    private Long idPersona;
    private Integer idTipoObligacion;
    private String referenciaObligacion;
    private BigDecimal valor;

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdTipoObligacion() {
        return idTipoObligacion;
    }

    public void setIdTipoObligacion(Integer idTipoObligacion) {
        this.idTipoObligacion = idTipoObligacion;
    }

    public String getReferenciaObligacion() {
        return referenciaObligacion;
    }

    public void setReferenciaObligacion(String referenciaObligacion) {
        this.referenciaObligacion = referenciaObligacion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
