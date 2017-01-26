package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Utilitario: consulta ubicabilidad para SAC
 * 
 * @author rodrigo.cruz
 */
public class RespuestaUbicabilidadSacDTO extends RespuestaProcesoSacDTO {

    private static final long serialVersionUID = 1L;
    private List<UbicabilidadSacDTO> ubicabilidadSacList;
    private List<Long> lsIdPersonaHistorico;

    public List<UbicabilidadSacDTO> getUbicabilidadSacList() {
        if (ubicabilidadSacList == null) {
            ubicabilidadSacList = new ArrayList<UbicabilidadSacDTO>();
        }
        return ubicabilidadSacList;
    }

    public void setUbicabilidadSacList(List<UbicabilidadSacDTO> ubicabilidadSacList) {
        this.ubicabilidadSacList = ubicabilidadSacList;
    }

    public List<Long> getLsIdPersonaHistorico() {
        if (lsIdPersonaHistorico == null) {
            lsIdPersonaHistorico = new ArrayList<Long>();
        }
        return lsIdPersonaHistorico;
    }

    public void setLsIdPersonaHistorico(List<Long> lsIdPersonaHistorico) {
        this.lsIdPersonaHistorico = lsIdPersonaHistorico;
    }
}