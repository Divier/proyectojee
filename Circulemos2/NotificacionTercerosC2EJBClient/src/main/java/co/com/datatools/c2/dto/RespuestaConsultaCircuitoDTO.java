package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class RespuestaConsultaCircuitoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer smtpCodigo;
    private Integer codigoEstado;
    private Date smtpDate;
    private Date closeDate;
    private Date createDate;

    public Integer getSmtpCodigo() {
        return smtpCodigo;
    }

    public void setSmtpCodigo(Integer smtpCodigo) {
        this.smtpCodigo = smtpCodigo;
    }

    public Integer getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Integer codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Date getSmtpDate() {
        return smtpDate;
    }

    public void setSmtpDate(Date smtpDate) {
        this.smtpDate = smtpDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}