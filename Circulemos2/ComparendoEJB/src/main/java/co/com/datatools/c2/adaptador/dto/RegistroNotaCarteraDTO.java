package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.enumeraciones.EnumCarteraComparendo;

/**
 * Registro nota cartera comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class RegistroNotaCarteraDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long cicomparendo;
    private EnumCarteraComparendo enumCarteraComparendo;
    private Date fechaObligacion;
    private int idTipoDocumento;
    private String numeroDocumento;
    private BigDecimal valorConcepto;
    private int codigoOrganismoTransito;

    public long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public EnumCarteraComparendo getEnumCarteraComparendo() {
        return enumCarteraComparendo;
    }

    public void setEnumCarteraComparendo(EnumCarteraComparendo enumCarteraComparendo) {
        this.enumCarteraComparendo = enumCarteraComparendo;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getValorConcepto() {
        return valorConcepto;
    }

    public void setValorConcepto(BigDecimal valorConcepto) {
        this.valorConcepto = valorConcepto;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(int codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

}
