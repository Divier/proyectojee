package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EstadoEventoDTO;
import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.entidades.Evento;
import co.com.datatools.c2.enumeraciones.EnumEstadoEvento;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.helpers.EventoHelper;
import co.com.datatools.c2.negocio.interfaces.ILEvento;
import co.com.datatools.c2.negocio.interfaces.IREvento;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.util.GenericDao;

@Stateless(name = "EventoEJB")
@LocalBean
public class EventoEJB implements ILEvento, IREvento {

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRPersona iRPersona;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(EventoEJB.class.getName());

    @Override
    public List<EventoDTO> consultarEvento(EventoDTO evento) {
        logger.debug("EventoEJB::consultarEvento(EventoDTO)");
        List<EventoDTO> lsEventoDTO = new ArrayList<>();
        if (evento != null) {
            GenericDao<Evento> eventoDao = new GenericDao<>(Evento.class, em);

            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT DISTINCT e FROM Evento e");
            jpql.append(" WHERE 1=1");

            if (evento.getId() != null) {
                jpql.append(" AND e.id = :idEvento");
                filtros.put("idEvento", evento.getId());
            }

            if (evento.getTipoEvento() != null) {
                jpql.append(" AND e.tipoEvento.id = :idTipoEvento");
                filtros.put("idTipoEvento", evento.getTipoEvento().getId());
            }

            if (evento.getTipoEvento() != null) {
                jpql.append(" AND e.tipoProcesoEvento.id = :idTipoProcesoEvento");
                filtros.put("idTipoProcesoEvento", evento.getTipoProcesoEvento().getId());
            }

            if (evento.getNombreEvento() != null) {
                jpql.append(" AND e.nombreEvento = :nombreEvento");
                filtros.put("nombreEvento", evento.getNombreEvento());
            }

            jpql.append(" ORDER BY e.fecha DESC");
            List<Evento> resultadoConsulta = eventoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                for (Evento eventoResult : resultadoConsulta) {
                    EventoDTO eventoDTO = EventoHelper.toLevel1DTO(eventoResult);
                    List<UsuarioPersonaDTO> usuariosList = iRSeguridadCirculemos
                            .consultarUsuarioPersona(eventoDTO.getUsuario());
                    if (usuariosList != null && !usuariosList.isEmpty()) {
                        eventoDTO.setUsuario(usuariosList.get(0));
                    }
                    lsEventoDTO.add(eventoDTO);
                }
            }

        }
        return lsEventoDTO;
    }

    @Override
    public void registrarEvento(EventoDTO evento) throws CirculemosNegocioException {
        evento.setUsuario(iRSeguridadCirculemos.obtenerUsuarioDto());
        evento.setFechaRegistro(new Date());
        evento.setEstadoEvento(new EstadoEventoDTO());
        evento.getEstadoEvento().setId(EnumEstadoEvento.SIN_REGISTRO_ASISTENCIA.getValue());
        Evento eventoEntity = EventoHelper.toLevel1Entity(evento, null);
        em.persist(eventoEntity);
    }

}
