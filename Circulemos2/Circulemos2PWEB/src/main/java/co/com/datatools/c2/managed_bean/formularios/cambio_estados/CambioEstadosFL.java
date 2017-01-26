package co.com.datatools.c2.managed_bean.formularios.cambio_estados;

import java.util.ArrayList;
import java.util.Date;

import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author giovanni.velandia
 * 
 */
public class CambioEstadosFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private CambioEstadoFormularioDTO cambioEstadoFormularioDTO;
    private EstadoFormularioDTO estadoFormularioDTO;
    private String nombreArchivo;
    private Date fechamaxima;

    public static final String NOMBRE_BEAN = "cambioEstadosFL";

    public CambioEstadosFL() {
        cambioEstadoFormularioDTO = new CambioEstadoFormularioDTO();
        cambioEstadoFormularioDTO.setListRangoDTO(new ArrayList<RangoDTO>());
        estadoFormularioDTO = new EstadoFormularioDTO();
    }

    public CambioEstadoFormularioDTO getCambioEstadoFormularioDTO() {
        return cambioEstadoFormularioDTO;
    }

    public void setCambioEstadoFormularioDTO(CambioEstadoFormularioDTO cambioEstadoFormularioDTO) {
        this.cambioEstadoFormularioDTO = cambioEstadoFormularioDTO;
    }

    public EstadoFormularioDTO getEstadoFormularioDTO() {
        return estadoFormularioDTO;
    }

    public void setEstadoFormularioDTO(EstadoFormularioDTO estadoFormularioDTO) {
        this.estadoFormularioDTO = estadoFormularioDTO;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechamaxima() {
        return fechamaxima;
    }

    public void setFechamaxima(Date fechamaxima) {
        this.fechamaxima = fechamaxima;
    }

}
