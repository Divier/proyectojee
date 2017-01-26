package co.com.datatools.c2.managed_bean.administracion.eventos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.dto.TipoEventoDTO;
import co.com.datatools.c2.dto.TipoProcesoDTO;
import co.com.datatools.c2.dto.TipoProcesoEventoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo administracion-persona-flow CU_CIR20_DAT_UBI_002
 * 
 * @author Dixon.Alvarez
 * 
 */
public class AdministracionEventosHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "administracionEventosHolderFL";

    private EventoDTO eventoConsulta;
    private List<EventoDTO> resultadoConsulta;
    private EventoDTO eventoCrea;

    public AdministracionEventosHolderFL() {
        inicializar();
    }

    public void inicializar() {
        eventoConsulta = new EventoDTO();
        eventoConsulta.setTipoEvento(new TipoEventoDTO());
        eventoConsulta.setTipoProcesoEvento(new TipoProcesoEventoDTO());
        resultadoConsulta = new ArrayList<EventoDTO>();

        eventoCrea = new EventoDTO();
        eventoCrea.setTipoEvento(new TipoEventoDTO());
        eventoCrea.setTipoProcesoEvento(new TipoProcesoEventoDTO());
    }

    public EventoDTO getEventoConsulta() {
        return eventoConsulta;
    }

    public void setEventoConsulta(EventoDTO eventoConsulta) {
        this.eventoConsulta = eventoConsulta;
    }

    public List<EventoDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<EventoDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public EventoDTO getEventoCrea() {
        return eventoCrea;
    }

    public void setEventoCrea(EventoDTO eventoCrea) {
        this.eventoCrea = eventoCrea;
    }
}
