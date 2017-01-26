package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.NovedadSacDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.entidades.EvidenciaObligacionSac;
import co.com.datatools.c2.entidades.GestionCobroSac;
import co.com.datatools.c2.entidades.NovedadSac;
import co.com.datatools.c2.entidades.ObligacionSac;
import co.com.datatools.c2.entidades.UbicabilidadSac;
import co.com.datatools.c2.negocio.helpers.GestionCobroSacHelper;
import co.com.datatools.c2.negocio.helpers.NovedadSacHelper;
import co.com.datatools.c2.negocio.helpers.ObligacionSacHelper;
import co.com.datatools.c2.negocio.helpers.UbicabilidadSacHelper;
import co.com.datatools.c2.negocio.interfaces.ILRegistroSac;
import co.com.datatools.c2.negocio.interfaces.IRRegistroSac;
import co.com.datatools.c2.util.Utilidades;

/**
 * Session Bean implementation class RegistroSacEJB
 */
@Stateless(mappedName = "RegistroSacEJB")
@LocalBean
public class RegistroSacEJB implements IRRegistroSac, ILRegistroSac {

    private final static Logger logger = Logger.getLogger(RegistroSacEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionSacC2JPA")
    private EntityManager em;

    @Override
    public NovedadSacDTO registarNovedadSac(NovedadSacDTO novedad) {
        logger.debug("RegistroSacEJB.registarNovedadSac(NovedadSacDTO)");
        checkNotNull(novedad, "Novedad es obligatorio");
        NovedadSac novedadSac = NovedadSacHelper.toLevel1Entity(novedad, null);
        em.persist(novedadSac);
        novedad.setIdNovedadSac(novedadSac.getIdNovedadSac());
        return novedad;
    }

    @Override
    public ObligacionSacDTO registrarObligacionSac(ObligacionSacDTO deuda) {
        logger.debug("RegistroSacEJB.registarNovedadSac(ObligacionSacDTO)");
        checkNotNull(deuda, "ObligacionSac es obligatorio");
        // if(!existeObligacion(deuda)) {
        ObligacionSac obligacionSac = ObligacionSacHelper.toLevel1Entity(deuda, null);
        em.persist(obligacionSac);
        List<EvidenciaObligacionSac> listaValores = obligacionSac.getEvidenciaObligacionSacs();
        if (listaValores != null && !listaValores.isEmpty()) {
            for (EvidenciaObligacionSac evidencia : listaValores) {
                em.persist(evidencia);
            }
        }
        deuda.setId(obligacionSac.getId());
        // }
        return deuda;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public UbicabilidadSacDTO replicarUbicabilidadSac(UbicabilidadSacDTO ubicabilidadSacDTO) {
        logger.debug("RegistroSacEJB.replicarUbicabilidadSac(UbicabilidadSacDTO)");
        try {
            UbicabilidadSac ubicabilidadSac = UbicabilidadSacHelper.toLevel1Entity(ubicabilidadSacDTO, null);
            em.persist(ubicabilidadSac);
            ubicabilidadSacDTO.setId(ubicabilidadSac.getId());
        } catch (Exception e) {
            ubicabilidadSacDTO = null;
            logger.error(e);
        }
        return ubicabilidadSacDTO;
    }

    @Override
    public ObligacionSacDTO consultarObligacionSac(ObligacionSacDTO obligacionSac) {
        logger.debug("RegistroSacEJB.consultarObligacionSac(ObligacionSacDTO)");
        checkNotNull(obligacionSac, "Obligacion es obligatorio");
        ObligacionSacDTO obligacion = null;

        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o");
        jpql.append(" FROM ObligacionSac o");
        jpql.append(" WHERE 1 = 1");
        if (obligacionSac.getIdCartera() != null) {
            jpql.append(" AND o.idCartera = :idCartera");
        }
        if (StringUtils.isNotBlank(obligacionSac.getNumeroFactura())) {
            jpql.append(" AND o.numeroFactura = :numeroFactura");
        }
        if (obligacionSac.getNumeroCuota() != null) {
            jpql.append(" AND o.numeroCuota = :numeroCuota");
        }
        final TypedQuery<ObligacionSac> query = em.createQuery(jpql.toString(), ObligacionSac.class);
        if (obligacionSac.getIdCartera() != null) {
            query.setParameter("idCartera", obligacionSac.getIdCartera());
        }
        if (StringUtils.isNotBlank(obligacionSac.getNumeroFactura())) {
            query.setParameter("numeroFactura", obligacionSac.getNumeroFactura());
        }
        if (obligacionSac.getNumeroCuota() != null) {
            query.setParameter("numeroCuota", obligacionSac.getNumeroCuota());
        }

        if (!query.getResultList().isEmpty()) {
            obligacion = ObligacionSacHelper.toLevel1DTO(query.getResultList().get(0));
        }

        return obligacion;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void modificarObligacionSac(ObligacionSacDTO obligacion) {
        logger.debug("RegistroSacEJB.modificarObligacionSac(ObligacionSacDTO)");
        checkNotNull(obligacion, "ObligacionSac es obligatorio");
        ObligacionSac obligacionSac = ObligacionSacHelper.toLevel1Entity(obligacion, null);
        em.merge(obligacionSac);
    }

    @Override
    public ObligacionSacDTO consultarObligacionSac(Long id) {
        logger.debug("RegistroSacEJB.consultarObligacionSac(Long)");
        ObligacionSacDTO obligacionDTO = null;
        ObligacionSac obligacion = em.find(ObligacionSac.class, id);
        if (obligacion != null) {
            obligacionDTO = ObligacionSacHelper.toLevel1DTO(obligacion);
        }

        return obligacionDTO;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void replicarEvidenciasSac() {
        Query query = em.createNativeQuery("EXEC [dbo].cargaEvidenciasObligacionesSac");
        query.executeUpdate();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public GestionCobroSacDTO registarGestionCobroSac(GestionCobroSacDTO gestionCobroSacDTO) {
        logger.debug("RegistroSacEJB.registarGestionCobroSac(GestionCobroSacDTO)");
        checkNotNull(gestionCobroSacDTO, "Gestion cobro sac es obligatorio");
        GestionCobroSac gestionCobroSac = GestionCobroSacHelper.toLevel1Entity(gestionCobroSacDTO, null);
        em.persist(gestionCobroSac);
        gestionCobroSacDTO.setId(gestionCobroSac.getId());
        return gestionCobroSacDTO;
    }

    @Override
    public List<GestionCobroSacDTO> consultarGestionCobroSac() {
        logger.debug("RegistroSacEJB.consultarGestionCobroSac()");
        List<GestionCobroSacDTO> gestionesSac = new ArrayList<GestionCobroSacDTO>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT vh.idHistoriaGestion, ");
        sql.append("vh.idDeudor, ");
        sql.append("vh.idCuenta, ");
        sql.append("vh.identificacion, ");
        sql.append("vh.multa, ");
        sql.append("vh.numeroCitacion, ");
        sql.append("vh.fechaGestion, ");
        sql.append("vh.fechaNotificado, ");
        sql.append("vh.tipoGestionSac, ");
        sql.append("vh.accion, ");
        sql.append("vh.respuesta, ");
        sql.append("vh.contacto, ");
        sql.append("vh.resultadoAccion, ");
        sql.append("vh.resultadoGestion, ");
        sql.append("vh.tipoEvidencia, ");
        sql.append("vh.datoUbicabilidad, ");
        sql.append("vh.observaciones, ");
        sql.append("vh.observacionesInternas  ");
        sql.append("FROM v_historialDetalladoDeGestiones vh ");
        sql.append("LEFT JOIN gestion_cobro_sac gs ON vh.idHistoriaGestion = gs.idHistoriaGestion ");
        sql.append("WHERE gs.idHistoriaGestion IS NULL");

        Query query = em.createNativeQuery(sql.toString());

        @SuppressWarnings({ "unchecked" })
        List<Object[]> detalles = Utilidades.safeList(query.getResultList());
        for (Object[] gestionSac : detalles) {
            int campo = 0;
            GestionCobroSacDTO gestionCobroSacDTO = new GestionCobroSacDTO();
            gestionCobroSacDTO.setIdHistoriaGestion((Integer) gestionSac[campo++]);
            gestionCobroSacDTO.setIdDeudor((Integer) gestionSac[campo++]);
            gestionCobroSacDTO.setIdCuenta((Integer) gestionSac[campo++]);
            gestionCobroSacDTO.setIdentificacion((String) gestionSac[campo++]);
            gestionCobroSacDTO.setMulta((String) gestionSac[campo++]);
            gestionCobroSacDTO.setNumeroCitacion((String) gestionSac[campo++]);
            gestionCobroSacDTO.setFechaGestion((Date) gestionSac[campo++]);
            gestionCobroSacDTO.setFechaNotificado((Date) gestionSac[campo++]);
            gestionCobroSacDTO.setTipoGestionSac((String) gestionSac[campo++]);
            gestionCobroSacDTO.setAccion((String) gestionSac[campo++]);
            gestionCobroSacDTO.setRespuesta((String) gestionSac[campo++]);
            gestionCobroSacDTO.setContacto((String) gestionSac[campo++]);
            gestionCobroSacDTO.setResultadoAccion((String) gestionSac[campo++]);
            gestionCobroSacDTO.setResultadoGestion((String) gestionSac[campo++]);
            gestionCobroSacDTO.setTipoEvidencia((String) gestionSac[campo++]);
            gestionCobroSacDTO.setDatoUbicabilidad((String) gestionSac[campo++]);
            gestionCobroSacDTO.setObservaciones((String) gestionSac[campo++]);
            gestionCobroSacDTO.setObservacionesInternas((String) gestionSac[campo++]);
            gestionesSac.add(gestionCobroSacDTO);
        }

        return gestionesSac;
    }
}
