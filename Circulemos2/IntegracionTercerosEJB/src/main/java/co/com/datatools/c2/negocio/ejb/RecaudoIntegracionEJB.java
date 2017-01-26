package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.entidades.ItRecaudo;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.negocio.helpers.ItRecaudoHelper;
import co.com.datatools.c2.negocio.interfaces.ILRecaudoIntegracion;
import co.com.datatools.c2.negocio.interfaces.IRRecaudoIntegracion;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class RecaudoEJB
 */
@Stateless(mappedName = "RecaudoIntegracionEJB")
@LocalBean
public class RecaudoIntegracionEJB implements IRRecaudoIntegracion, ILRecaudoIntegracion {

    private final static Logger logger = Logger.getLogger(RecaudoIntegracionEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionTercerosC2JPA")
    private EntityManager em;

    @Override
    public List<ItRecaudoDTO> consultarRecaudos(int codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug("RecaudoIntegracionEJB.consultarRecaudos(int, EnumEstadoLectura)");
        List<ItRecaudoDTO> lsResultado = new ArrayList<ItRecaudoDTO>();

        GenericDao<ItRecaudo> comparendoDao = new GenericDao<>(ItRecaudo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT r FROM ItRecaudo r");
        jpql.append(" WHERE ");
        jpql.append(" r.codigoOrganismo = :codigoOrganismo");
        filtros.put("codigoOrganismo", codigoOrganismo);
        if (estadoLectura != null) {
            jpql.append(" AND r.estadoLectura = :estadoLectura");
            filtros.put("estadoLectura", estadoLectura.getValue());
        }
        jpql.append(" ORDER BY r.codigoTipoRecaudo, r.numeroObligacion, r.numeroCuota");

        List<ItRecaudo> resultadoConsulta = comparendoDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lsResultado = ItRecaudoHelper.toListLevel1DTO(resultadoConsulta);
        }
        return lsResultado;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstadoRecaudo(Long idRecaudo, EnumEstadoLectura estadoLectura) {
        logger.debug("RecaudoIntegracionEJB.actualizarEstadoRecaudos(Long , EnumEstadoLectura)");
        checkNotNull(estadoLectura, "Estado de lectura es obligatorio");
        checkNotNull(idRecaudo, "Identificador del recaudo es obligatorio");
        ItRecaudo recaudo = em.find(ItRecaudo.class, idRecaudo);
        recaudo.setEstadoLectura(estadoLectura.getValue());
        em.merge(recaudo);
    }

}
