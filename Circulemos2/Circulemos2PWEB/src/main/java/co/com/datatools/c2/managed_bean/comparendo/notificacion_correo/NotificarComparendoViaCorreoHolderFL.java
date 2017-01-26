package co.com.datatools.c2.managed_bean.comparendo.notificacion_correo;

import java.util.Date;

import co.com.datatools.c2.dto.comparendo.ConsultaCantidadComparendosNoNotificadosDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class NotificarComparendoViaCorreoHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    ConsultaCantidadComparendosNoNotificadosDTO consultarComparendosNoNotificadosDTO;

    Integer cantidadDeComparendos;

    boolean visible;

    private String codOrganismoNumeroComparendo;

    private String numeroComparendo;

    private boolean polca;

    private String codigoPolca;

    private boolean requiereFecha;

    private boolean requiereFechaRegistro;

    private boolean generaDocumento;

    private Date fecha;

    private Date fechaActual = new Date();

    public NotificarComparendoViaCorreoHolderFL() {
        this.consultarComparendosNoNotificadosDTO = new ConsultaCantidadComparendosNoNotificadosDTO();
    }

    public ConsultaCantidadComparendosNoNotificadosDTO getConsultarComparendosNoNotificadosDTO() {
        return consultarComparendosNoNotificadosDTO;
    }

    public void setConsultarComparendosNoNotificadosDTO(
            ConsultaCantidadComparendosNoNotificadosDTO consultarComparendosNoNotificadosDTO) {
        this.consultarComparendosNoNotificadosDTO = consultarComparendosNoNotificadosDTO;
    }

    public Integer getCantidadDeComparendos() {
        return cantidadDeComparendos;
    }

    public void setCantidadDeComparendos(Integer cantidadDeComparendos) {
        this.cantidadDeComparendos = cantidadDeComparendos;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getCodOrganismoNumeroComparendo() {
        return codOrganismoNumeroComparendo;
    }

    public void setCodOrganismoNumeroComparendo(String codOrganismoNumeroComparendo) {
        this.codOrganismoNumeroComparendo = codOrganismoNumeroComparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public boolean isPolca() {
        return polca;
    }

    public void setPolca(boolean polca) {
        this.polca = polca;
    }

    public String getCodigoPolca() {
        return codigoPolca;
    }

    public void setCodigoPolca(String codigoPolca) {
        this.codigoPolca = codigoPolca;
    }

    public boolean isRequiereFecha() {
        return requiereFecha;
    }

    public void setRequiereFecha(boolean requiereFecha) {
        this.requiereFecha = requiereFecha;
    }

    public boolean isRequiereFechaRegistro() {
        return requiereFechaRegistro;
    }

    public void setRequiereFechaRegistro(boolean requiereFechaRegistro) {
        this.requiereFechaRegistro = requiereFechaRegistro;
    }

    public boolean isGeneraDocumento() {
        return generaDocumento;
    }

    public void setGeneraDocumento(boolean generaDocumento) {
        this.generaDocumento = generaDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

}
