package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.Serializable;

import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;

/**
 * Se encarga de mostrar la logica de las pruebas para un proceso
 * 
 * @author giovanni.velandia
 * 
 */
public class SolicitudPruebasBackVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private SolicitudPruebasBackDTO solicitudPruebasBackDTO;
    private boolean impulsoDefinitivo;
    private Long idProceso;

    public SolicitudPruebasBackVO() {
        solicitudPruebasBackDTO = new SolicitudPruebasBackDTO();
    }

    public SolicitudPruebasBackDTO getSolicitudPruebasBackDTO() {
        return solicitudPruebasBackDTO;
    }

    public void setSolicitudPruebasBackDTO(SolicitudPruebasBackDTO solicitudPruebasBackDTO) {
        this.solicitudPruebasBackDTO = solicitudPruebasBackDTO;
    }

    public boolean isImpulsoDefinitivo() {
        return impulsoDefinitivo;
    }

    public void setImpulsoDefinitivo(boolean impulsoDefinitivo) {
        this.impulsoDefinitivo = impulsoDefinitivo;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

}
