package co.com.datatools.c2.negocio.ejb.cartera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.common.ConsultaTasaInteresDTO;
import co.com.datatools.c2.entidades.Interes;
import co.com.datatools.c2.enumeracion.EnumPeriodoInteres;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorCartera.RegistrarTasaInteres;
import co.com.datatools.c2.negocio.helpers.cartera.InteresHelper;
import co.com.datatools.c2.negocio.interfaces.cartera.ILInteres;
import co.com.datatools.c2.negocio.interfaces.cartera.IRInteres;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class InteresEJB
 */
@Stateless(name = "InteresEJB")
@LocalBean
public class InteresEJB implements IRInteres, ILInteres {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(InteresEJB.class.getName());

    public InteresEJB() {

    }

    @Override
    public InteresDTO consultarTasasInteres(ConsultaTasaInteresDTO consultaTasaInteresDTO) {
        logger.debug("InteresEJB::consultarTasasInteres");
        InteresDTO resultado = null;
        GenericDao<Interes> interesDao = new GenericDao<>(Interes.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT i FROM Interes i");
        jpql.append(" WHERE 1=1");

        if (consultaTasaInteresDTO != null) {
            if (consultaTasaInteresDTO.getClaseInteres() != null) {
                jpql.append(" AND i.claseInteres.id = :claseInteres");
                filtros.put("claseInteres", consultaTasaInteresDTO.getClaseInteres());
            }

            if (consultaTasaInteresDTO.getPeriodoInteres() != null) {
                if (!EnumPeriodoInteres.ANUAL.getValue().equals(consultaTasaInteresDTO.getPeriodoInteres())
                        && !EnumPeriodoInteres.DIARIO.getValue().equals(consultaTasaInteresDTO.getPeriodoInteres())) {
                    jpql.append(" AND i.periodoInteres.id = :periodoInteres");
                    filtros.put("periodoInteres", consultaTasaInteresDTO.getPeriodoInteres());
                }
            }

            if (consultaTasaInteresDTO.getFechaAplicacion() != null) {
                jpql.append(" AND :fechaAplicacionInicial >= i.fechaInicial");
                filtros.put("fechaAplicacionInicial", consultaTasaInteresDTO.getFechaAplicacion());
                jpql.append(" AND :fechaAplicacionFinal <= i.fechaFinal");
                filtros.put("fechaAplicacionFinal", consultaTasaInteresDTO.getFechaAplicacion());
            }
        }

        List<Interes> resultadoConsulta = interesDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            resultado = InteresHelper.toLevel1DTO(resultadoConsulta.get(0));
        }
        return resultado;
    }

    @Override
    public InteresDTO registrarInteres(InteresDTO interesDTO) throws CirculemosNegocioException {
        logger.debug("InteresEJB::registrarIntereses(InteresDTO)");
        if (validarTasaInteresVigente(interesDTO)) {
            throw new CirculemosNegocioException(RegistrarTasaInteres.ADM_016001);
        }
        Interes interes = InteresHelper.toLevel1Entity(interesDTO, null);
        em.persist(interes);
        interesDTO.setId(interes.getId());
        return interesDTO;
    }

    @Override
    public void modificarInteres(InteresDTO interesDTO) throws CirculemosNegocioException {
        logger.debug("InteresEJB::modificarInteres(InteresDTO)");
        if (validarTasaInteresVigente(interesDTO)) {
            throw new CirculemosNegocioException(RegistrarTasaInteres.ADM_016001);
        }
        Interes interes = em.find(Interes.class, interesDTO.getId());
        interes = InteresHelper.toLevel1Entity(interesDTO, interes);
        em.merge(interes);
    }

    @Override
    public List<InteresDTO> consultarIntereses(InteresDTO interesDTO, boolean validarEstado) {
        logger.debug("InteresEJB::consultarIntereses(InteresDTO, boolean)");
        List<InteresDTO> resultado = new ArrayList<InteresDTO>();
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<String, Object>();

        jpql.append("SELECT i FROM Interes i ");
        jpql.append(" WHERE 1=1");

        if (interesDTO != null) {
            if (interesDTO.getId() != null) {
                jpql.append(" AND i.id = :idInteres");
                filtros.put("idInteres", interesDTO.getId());
            }
            if (interesDTO.getClaseInteres() != null && interesDTO.getClaseInteres().getId() != null) {
                jpql.append(" AND i.claseInteres.id = :idClaseInteres");
                filtros.put("idClaseInteres", interesDTO.getClaseInteres().getId());
            }
            if (interesDTO.getPeriodoInteres() != null && interesDTO.getPeriodoInteres().getId() != null) {
                jpql.append(" AND i.periodoInteres.id = :idPeriodoInteres");
                filtros.put("idPeriodoInteres", interesDTO.getPeriodoInteres().getId());
            }
            if (interesDTO.getFechaInicial() != null) {
                jpql.append(" AND i.fechaInicial = :fechaInicial");
                filtros.put("fechaInicial", interesDTO.getFechaInicial());
            }
            if (interesDTO.getFechaFinal() != null) {
                jpql.append(" AND i.fechaFinal = :fechaFinal");
                filtros.put("fechaFinal", interesDTO.getFechaFinal());
            }
        }
        // Validar estado del interes
        if (validarEstado) {
            if (interesDTO.getEstado() != null) {
                jpql.append(" AND i.estado = :estado");
                filtros.put("estado", interesDTO.getEstado());
            }
        }
        jpql.append(" ORDER BY i.fechaInicial DESC");

        GenericDao<Interes> dao = new GenericDao<>(Interes.class, em);
        List<Interes> resultadoConsulta = dao.buildAndExecuteQuery(jpql.toString(), filtros);

        for (Interes interes : resultadoConsulta) {
            InteresDTO interesDTODB = InteresHelper.toLevel1DTO(interes);
            resultado.add(interesDTODB);
        }
        return resultado;
    }

    /**
     * Valida si ya existe una configuracion de interes para el rango de fechas en el que queda la configuracion de interes que se va registrar
     * 
     * @param interesDTO
     * @return True, si existe una configuracion de tasa de interés que se cruce
     * @author Dixon.Alvarez 2016-10-12
     */
    private boolean validarTasaInteresVigente(InteresDTO interesDTO) {
        boolean existeTasaInteresVigente = false;
        if (interesDTO.getFechaInicial() != null) {
            InteresDTO filtro = new InteresDTO();
            filtro.setEstado(true);
            filtro.setClaseInteres(interesDTO.getClaseInteres());
            List<InteresDTO> intereses = consultarIntereses(filtro, true);
            for (InteresDTO interes : intereses) {
                if (interesDTO.getId() == null || interesDTO.getId().intValue() != interes.getId().intValue()) {
                    if (interes.getFechaFinal() != null) {
                        if (UtilFecha.betweenDate(interes.getFechaInicial(), interes.getFechaFinal(),
                                interesDTO.getFechaInicial())
                                || (interesDTO.getFechaFinal() == null
                                        && UtilFecha.betweenDate(interes.getFechaInicial(), interes.getFechaFinal(),
                                                interesDTO.getFechaInicial()))
                                || (interesDTO.getFechaFinal() != null && UtilFecha.betweenDate(
                                        interes.getFechaInicial(), interes.getFechaFinal(), interesDTO.getFechaFinal()))
                                || (interesDTO.getFechaFinal() != null
                                        && UtilFecha.betweenDate(interesDTO.getFechaInicial(),
                                                interesDTO.getFechaFinal(), interes.getFechaFinal()))) {
                            existeTasaInteresVigente = true;
                        }
                    } else {
                        existeTasaInteresVigente = true;
                        if (interesDTO.getFechaFinal() != null
                                && interesDTO.getFechaFinal().before(interes.getFechaInicial())) {
                            existeTasaInteresVigente = false;
                        }
                    }
                }
            }
        }
        return existeTasaInteresVigente;
    }
}
