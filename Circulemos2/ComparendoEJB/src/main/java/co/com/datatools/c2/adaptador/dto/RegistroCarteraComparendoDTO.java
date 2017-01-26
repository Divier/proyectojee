package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.dto.comparendo.TarifaLiquidacionDTO;

/**
 * Registro cartera comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class RegistroCarteraComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaImposicion;
    private int idTipoDocumento;
    private String numeroComparendo;
    private String numeroDocumento;
    private int origenObligacion;
    private int codigoOrganismoTransito;
    private Long idComparendo;
    private TarifaLiquidacionDTO tarifaLiquidacion;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getOrigenObligacion() {
        return origenObligacion;
    }

    public void setOrigenObligacion(int origenObligacion) {
        this.origenObligacion = origenObligacion;
    }

    public int getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(int codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    public Long getIdComparendo() {
        return idComparendo;
    }

    public void setIdComparendo(Long idComparendo) {
        this.idComparendo = idComparendo;
    }

    public TarifaLiquidacionDTO getTarifaLiquidacion() {
        return tarifaLiquidacion;
    }

    public void setTarifaLiquidacion(TarifaLiquidacionDTO tarifaLiquidacion) {
        this.tarifaLiquidacion = tarifaLiquidacion;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }
}
