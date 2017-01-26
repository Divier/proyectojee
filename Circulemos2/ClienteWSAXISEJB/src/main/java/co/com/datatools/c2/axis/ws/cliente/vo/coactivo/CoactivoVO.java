/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo.coactivo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto que se enviará como parametro en registro del coactivo
 * 
 * @author Jeison.Rodriguez (2016-09-21)
 *
 */
public class CoactivoVO implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;


    @SerializedName("tipo_identificacion")
    private String tipoIdentificacion;
    private String identificacion;

    @SerializedName("fecha_coactiva")
    private Date fechaCoactiva;

    @SerializedName("monto_coactiva")
    private BigDecimal montoCoactiva;

    @SerializedName("costo_judicial")
    private BigDecimal costoJudicial;

    @SerializedName("gasto_admin")
    private BigDecimal gastoAdmin;
    private String observacion;
    private String usuario;
    private String empresa;
    private List<InfraccionCoactivoVO> infracciones;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Date getFechaCoactiva() {
        return fechaCoactiva;
    }

    public void setFechaCoactiva(Date fechaCoactiva) {
        this.fechaCoactiva = fechaCoactiva;
    }

    public BigDecimal getMontoCoactiva() {
        return montoCoactiva;
    }

    public void setMontoCoactiva(BigDecimal montoCoactiva) {
        this.montoCoactiva = montoCoactiva;
    }

    public BigDecimal getCostoJudicial() {
        return costoJudicial;
    }

    public void setCostoJudicial(BigDecimal costoJudicial) {
        this.costoJudicial = costoJudicial;
    }

    public BigDecimal getGastoAdmin() {
        return gastoAdmin;
    }

    public void setGastoAdmin(BigDecimal gastoAdmin) {
        this.gastoAdmin = gastoAdmin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<InfraccionCoactivoVO> getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(List<InfraccionCoactivoVO> infracciones) {
        this.infracciones = infracciones;
    }

}
