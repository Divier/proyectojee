package co.com.datatools.c2.dto.comparendo;

import java.util.List;

/**
 * Clase que representa a los comparendos rectificados, contiene el listado de rectificaciones realizadas.
 * 
 * @author luis.forero(2016-01-28)
 */
public class ProcesaComparendoRectificadoDTO extends ProcesaComparendoDTO {

    private static final long serialVersionUID = 1L;

    private List<CampoRectificaComparendoDTO> campoRectificaComparendoDTOs;

    private List<RectificaEvidenciaDTO> listRectificaEvidencias;

    public ProcesaComparendoRectificadoDTO() {
    }

    public ProcesaComparendoRectificadoDTO(Long id) {
        super(id);
    }

    public List<CampoRectificaComparendoDTO> getCampoRectificaComparendoDTOs() {
        return campoRectificaComparendoDTOs;
    }

    public void setCampoRectificaComparendoDTOs(List<CampoRectificaComparendoDTO> campoRectificaComparendoDTOs) {
        this.campoRectificaComparendoDTOs = campoRectificaComparendoDTOs;
    }

    public List<RectificaEvidenciaDTO> getListRectificaEvidencias() {
        return listRectificaEvidencias;
    }

    public void setListRectificaEvidencias(List<RectificaEvidenciaDTO> listRectificaEvidencias) {
        this.listRectificaEvidencias = listRectificaEvidencias;
    }

}
