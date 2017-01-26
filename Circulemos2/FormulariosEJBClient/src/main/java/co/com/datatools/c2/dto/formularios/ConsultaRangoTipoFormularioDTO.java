package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.List;

public class ConsultaRangoTipoFormularioDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int codigoOrganismo;
    private int idTipoFormulario;
    private List<RangoDTO> rangoDTOs;
    private Long idDetalleCambioEstado;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public int getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(int idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public List<RangoDTO> getRangoDTOs() {
        return rangoDTOs;
    }

    public void setRangoDTOs(List<RangoDTO> rangoDTOs) {
        this.rangoDTOs = rangoDTOs;
    }

    public Long getIdDetalleCambioEstado() {
        return idDetalleCambioEstado;
    }

    public void setIdDetalleCambioEstado(Long idDetalleCambioEstado) {
        this.idDetalleCambioEstado = idDetalleCambioEstado;
    }

}
