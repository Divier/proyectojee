package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.eventos.BloqueHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.entidades.eventos.BloqueHorario;
import co.com.datatools.c2.entidades.eventos.ConfiguracionHorario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorConfiguracionHorario;
import co.com.datatools.c2.negocio.helpers.eventos.BloqueHorarioHelper;
import co.com.datatools.c2.negocio.helpers.eventos.ConfiguracionHorarioHelper;
import co.com.datatools.c2.negocio.interfaces.ILCargo;
import co.com.datatools.c2.negocio.interfaces.IRCargo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;

/**
 * Session Bean implementation class CargoEJB
 */
@Stateless(name = "CargoEJB")
@LocalBean
public class CargoEJB implements ILCargo, IRCargo {

    private final static Logger logger = Logger.getLogger(CargoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    public CargoEJB() {
        logger.info("CargoEJB::Constructor");
    }

    @Override
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHoraioDia(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO) {
        logger.info("CargoEJB::consultarConfiguracionHoraioDia(ConfiguracionHorarioFiltrosDTO)");
        List<ConfiguracionHorarioRespuestaDTO> configuracionHorarioRespuestaDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT DISTINCT ch.dia.id FROM ConfiguracionHorario ch");
        jpql.append(" WHERE ch.cargo.id = :idCargo");

        Query query = em.createQuery(jpql.toString());

        // Cargo
        if (configuracionHorarioFiltrosDTO.getIdCargo() != null) {
            query.setParameter("idCargo", configuracionHorarioFiltrosDTO.getIdCargo());
        }

        @SuppressWarnings("unchecked")
        List<ConfiguracionHorario> configuracionHorarios = query.getResultList();
        if (configuracionHorarios != null && !configuracionHorarios.isEmpty()) {
            configuracionHorarioRespuestaDTOs = new ArrayList<>();
            for (ConfiguracionHorario configuracionHorario : configuracionHorarios) {
                ConfiguracionHorarioRespuestaDTO configuracionHorarioRespuestaDTO = new ConfiguracionHorarioRespuestaDTO();
                // Id
                configuracionHorarioRespuestaDTO
                        .setIdConfiguracionHorario(configuracionHorario.getIdConfiguracionHorario());
                // Nombre cargo
                configuracionHorarioRespuestaDTO.setNombreCargo(configuracionHorario.getCargo().getNombre());
                // Nombre dia
                configuracionHorarioRespuestaDTO.setNombreDia(configuracionHorario.getDia().getNombre());
                // Fecha inicio vigencia
                configuracionHorarioRespuestaDTO.setFechaInicioVigencia(configuracionHorario.getFechaInicio());
                // Fecha fin vigencia
                configuracionHorarioRespuestaDTO.setFechaFinVigencia(configuracionHorario.getFechaFin());
                configuracionHorarioRespuestaDTOs.add(configuracionHorarioRespuestaDTO);
            }
        }

        return configuracionHorarioRespuestaDTOs;
    }

    @Override
    public List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHorario(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO) {
        logger.info("CargoEJB::consultarConfiguracionHorario(ConfiguracionHorarioFiltrosDTO)");
        List<ConfiguracionHorarioRespuestaDTO> configuracionHorarioRespuestaDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT ch FROM ConfiguracionHorario ch");
        jpql.append(" WHERE 1=1");

        // Cargo
        if (configuracionHorarioFiltrosDTO.getIdCargo() != null) {
            jpql.append("AND ch.cargo.id = :idCargo");
        }

        // Dias
        if (configuracionHorarioFiltrosDTO.getDias() != null && !configuracionHorarioFiltrosDTO.getDias().isEmpty()) {
            jpql.append(" AND ch.dia.id IN (:idDias)");
        }

        Query query = em.createQuery(jpql.toString());

        // Cargo
        if (configuracionHorarioFiltrosDTO.getIdCargo() != null) {
            query.setParameter("idCargo", configuracionHorarioFiltrosDTO.getIdCargo());
        }

        // Dias
        if (configuracionHorarioFiltrosDTO.getDias() != null && !configuracionHorarioFiltrosDTO.getDias().isEmpty()) {
            query.setParameter("idDias", configuracionHorarioFiltrosDTO.getDias());
        }

        @SuppressWarnings("unchecked")
        List<ConfiguracionHorario> configuracionHorarios = query.getResultList();
        if (configuracionHorarios != null && !configuracionHorarios.isEmpty()) {
            configuracionHorarioRespuestaDTOs = new ArrayList<>();
            for (ConfiguracionHorario configuracionHorario : configuracionHorarios) {
                ConfiguracionHorarioRespuestaDTO configuracionHorarioRespuestaDTO = new ConfiguracionHorarioRespuestaDTO();
                // Id
                configuracionHorarioRespuestaDTO
                        .setIdConfiguracionHorario(configuracionHorario.getIdConfiguracionHorario());
                // Nombre cargo
                configuracionHorarioRespuestaDTO.setNombreCargo(configuracionHorario.getCargo().getNombre());
                // Nombre dia
                configuracionHorarioRespuestaDTO.setNombreDia(configuracionHorario.getDia().getNombre());
                // Fecha inicio vigencia
                configuracionHorarioRespuestaDTO.setFechaInicioVigencia(configuracionHorario.getFechaInicio());
                // Fecha fin vigencia
                configuracionHorarioRespuestaDTO.setFechaFinVigencia(configuracionHorario.getFechaFin());
                // Bloque horario
                configuracionHorarioRespuestaDTO.setBloqueHorarioDTOs(
                        BloqueHorarioHelper.toListLevel1DTO(configuracionHorario.getBloqueHorarios()));
                configuracionHorarioRespuestaDTOs.add(configuracionHorarioRespuestaDTO);
            }
        }

        return configuracionHorarioRespuestaDTOs;
    }

    @Override
    public List<ConfiguracionHorarioDTO> consultarConfiguracionHorario(
            ConfiguracionHorarioDTO configuracionHorarioDTO) {
        logger.info("CargoEJB::consultarConfiguracionHorario(ConfiguracionHorarioDTO)");
        List<ConfiguracionHorarioDTO> configuracionHorarioDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT ch FROM ConfiguracionHorario ch");
        jpql.append(" WHERE 1=1");

        if (configuracionHorarioDTO.getCargoDTO() != null && configuracionHorarioDTO.getCargoDTO().getId() != null) {
            jpql.append("AND ch.cargo.id = :idCargo");
        }

        Query query = em.createQuery(jpql.toString());

        if (configuracionHorarioDTO.getCargoDTO() != null && configuracionHorarioDTO.getCargoDTO().getId() != null) {
            query.setParameter("idCargo", configuracionHorarioDTO.getCargoDTO().getId());
        }

        @SuppressWarnings("unchecked")
        List<ConfiguracionHorario> configuracionHorarios = query.getResultList();
        if (configuracionHorarios != null && !configuracionHorarios.isEmpty()) {
            configuracionHorarioDTOs = ConfiguracionHorarioHelper.toListLevel0DTO(configuracionHorarios);
        }

        return configuracionHorarioDTOs;
    }

    @Override
    public void registrarConfiguracionHorario(List<ConfiguracionHorarioDTO> configuracionHorarioDTOs)
            throws CirculemosNegocioException {
        logger.info("CargoEJB::registrarConfiguracionHorario(List<ConfiguracionHorarioDTO>)");
        for (ConfiguracionHorarioDTO configuracionHorarioDTO : configuracionHorarioDTOs) {
            registrarConfiguracionHorario(configuracionHorarioDTO);
        }
    }

    /**
     * Metodo que se encarga de registrar la configuracion horario de un cargo
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioDTO
     */
    private void registrarConfiguracionHorario(ConfiguracionHorarioDTO configuracionHorarioDTO)
            throws CirculemosNegocioException {
        logger.info("CargoEJB::registrarConfiguracionHorario(ConfiguracionHorarioDTO)");

        boolean existeIntervalo = validarIntervaloTiempo(configuracionHorarioDTO);

        if (existeIntervalo) {
            // Fecha de registro
            configuracionHorarioDTO.setUsuarioActualizaDTO(iRSeguridadCirculemos.obtenerUsuarioDto());
            configuracionHorarioDTO.setUsuarioRegistraDTO(iRSeguridadCirculemos.obtenerUsuarioDto());

            // Fecha de la ultima actualizacon
            configuracionHorarioDTO.setFechaActualizacion(Calendar.getInstance().getTime());

            em.persist(ConfiguracionHorarioHelper.toLevel1Entity(configuracionHorarioDTO, null));
        } else {
            throw new CirculemosNegocioException(ErrorConfiguracionHorario.ConfiguracionHorario.ADM_074001);
        }
    }

    /**
     * Se encarga de validar el intevalo de tiempo para el registro
     * 
     * @author giovanni.velandia
     * @param configuracionHorarioDTO
     * @return
     */
    public boolean validarIntervaloTiempo(ConfiguracionHorarioDTO configuracionHorarioDTO) {
        logger.info("CargoEJB::validarIntervaloTiempo(ConfiguracionHorarioDTO)");

        boolean existeIntervalo = true;

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT ch FROM ConfiguracionHorario ch");
        jpql.append(" WHERE ch.dia.id = :idDia");
        jpql.append(" AND ch.cargo.id = :idCargo");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("idDia", configuracionHorarioDTO.getDiaDTO().getId());
        query.setParameter("idCargo", configuracionHorarioDTO.getCargoDTO().getId());

        @SuppressWarnings("unchecked")
        List<ConfiguracionHorario> configuracionHorarios = query.getResultList();
        if (configuracionHorarios != null && !configuracionHorarios.isEmpty()) {
            for (ConfiguracionHorario configuracionHorario : configuracionHorarios) {

                for (BloqueHorarioDTO bloqueHorarioDTO : configuracionHorarioDTO.getBloqueHorarioDTOs()) {
                    for (BloqueHorario bloqueHorario : configuracionHorario.getBloqueHorarios()) {
                        if ((
                        // Hora inicio ingresada esta entre una configuracion registrada
                        (bloqueHorarioDTO.getHoraInicio().getTime() >= bloqueHorario.getHoraInicio().getTime()
                                && bloqueHorarioDTO.getHoraInicio().getTime() < bloqueHorario.getHoraFin().getTime()) ||
                        // Hora fin ingresada esta entre una configuracion registrada
                                (bloqueHorarioDTO.getHoraFin().getTime() > bloqueHorario.getHoraInicio().getTime()
                                        && bloqueHorarioDTO.getHoraFin().getTime() <= bloqueHorario.getHoraFin()
                                                .getTime()))
                                ||
                        // Configuracion ingresada contiene una configuracion registrada
                                (bloqueHorarioDTO.getHoraInicio().getTime() <= bloqueHorario.getHoraInicio().getTime()
                                        && bloqueHorarioDTO.getHoraFin().getTime() >= bloqueHorario.getHoraFin()
                                                .getTime())) {
                            existeIntervalo = false;
                            break;
                        }
                    }
                }
            }
        }

        return existeIntervalo;
    }
}
