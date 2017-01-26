package co.com.datatools.c2.axis.ws.cliente.vo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class DataVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @SerializedName("id_convenio")
    private String idConvenio;

    @SerializedName("nueva_factura")
    private Long nuevaFactura;

    @SerializedName("id_tramite")
    private Long idTramite;

    public String getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(String idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Long getNuevaFactura() {
        return nuevaFactura;
    }

    public void setNuevaFactura(Long nuevaFactura) {
        this.nuevaFactura = nuevaFactura;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }
}
