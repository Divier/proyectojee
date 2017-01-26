package co.com.datatools.c2.dto.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la respuesta de los comparendos obtenidos de la consulta
 * 
 * @author luis.forero
 * @version 1.0
 * @created 14-abr-2016 02:32:31 p.m.
 */
public class RespuestaConsultaComparendoDTO extends RespuestaNotificacionDTO {

    private static final long serialVersionUID = 1L;
    /**
     * Contiene el listado de las comparendos recibidos
     */
    private List<HistoricoComparendoDTO> reincidencias;

    public RespuestaConsultaComparendoDTO() {

    }

    public List<HistoricoComparendoDTO> getReincidencias() {
        if (reincidencias == null) {
            reincidencias = new ArrayList<HistoricoComparendoDTO>(0);
        }
        return reincidencias;
    }

    public void setReincidencias(List<HistoricoComparendoDTO> reincidencias) {
        this.reincidencias = reincidencias;
    }

}