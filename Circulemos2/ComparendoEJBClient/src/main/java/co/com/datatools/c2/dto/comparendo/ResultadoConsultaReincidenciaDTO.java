package co.com.datatools.c2.dto.comparendo;

import java.util.List;

/**
 * Contiene el resultado de consultar los comparendos para saber el número de reincidencias de una persona por un tipo de reincidencia.
 * 
 * @author rodrigo.cruz
 * 
 */
public class ResultadoConsultaReincidenciaDTO {

    private List<HistoricoComparendoDTO> comparendos;

    public List<HistoricoComparendoDTO> getComparendos() {
        return comparendos;
    }

    public void setComparendos(List<HistoricoComparendoDTO> comparendos) {
        this.comparendos = comparendos;
    }

}
