package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RegistraProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.entidades.DocumentoProceso;
import co.com.datatools.c2.entidades.EstadoProceso;
import co.com.datatools.c2.entidades.ParticipanteProceso;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.TipoProceso;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.DocumentoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.EstadoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.TrazabilidadProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ParticipanteProcesoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.ProcesoHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.TrazabilidadProcesoHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILProceso;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.util.GenericDao;

@Stateless(name = "ProcesoEJB")
@LocalBean
public class ProcesoEJB implements IRProceso, ILProceso {

    private final static Logger logger = Logger.getLogger(ProcesoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRFachadaAdminGeneral iRFachadaAdminGeneral;

    @Override
    public boolean existeProceso(ProcesoDTO proceso) {
        logger.debug("ProcesoEJB::existeProceso(ProcesoDTO)");
        final StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT COUNT(p)");
        jpql.append("  FROM Proceso p");
        jpql.append(" WHERE 1 = 1 ");
        if (proceso.getTipoProceso() != null) {
            jpql.append(" AND p.tipoProceso.id = :tipoProceso");
        }

        final Query query = em.createQuery(jpql.toString());

        if (proceso.getTipoProceso() != null) {
            query.setParameter("tipoProceso", proceso.getTipoProceso().getId());
        }

        final Long cantidad = (Long) query.getSingleResult();

        return cantidad > 0;
    }

    @Override
    public ProcesoDTO crearProceso(RegistraProcesoDTO registra) {
        logger.debug("ProcesoEJB::crearProceso(ProcesoDTO, EnumEstadoProceso,EnumConsecutivo)");

        EstadoProceso estadoEnt = em.find(EstadoProceso.class, registra.getEstado().getId());

        // Proceso
        Proceso procesoEntidad = new Proceso();
        procesoEntidad.setTipoProceso(em.find(TipoProceso.class, registra.getTipoProceso().getValue()));
        procesoEntidad.setFechaInicio(registra.getFechaInicio() == null ? new Date() : registra.getFechaInicio());
        procesoEntidad.setNumeroProceso(iRFachadaAdminGeneral.generarConsecutivo(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                registra.getConsecutivo()));
        procesoEntidad.setObservacion(registra.getObservacion());
        procesoEntidad.setEstadoProceso(estadoEnt);
        procesoEntidad.setTrazabilidadProceso(new ArrayList<TrazabilidadProceso>());
        em.persist(procesoEntidad);

        // Traza
        TrazabilidadProceso traza = new TrazabilidadProceso();
        traza.setFechaInicio(new Date());
        traza.setProceso(procesoEntidad);
        traza.setUsuario(UsuarioPersonaHelper.toLevel1Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
        traza.setEstadoProceso(estadoEnt);
        em.persist(traza);
        return ProcesoHelper.toLevel0DTO(procesoEntidad);

    }

    @Override
    public TrazabilidadProcesoDTO actualizarEstadoProceso(Long idProceso, EnumEstadoProceso estado,
            Boolean cerrarProceso) {
        logger.debug("ProcesoEJB::actualizarEstadoProceso(Long, EnumEstadoProceso, Boolean)");
        Proceso proceso = em.find(Proceso.class, idProceso);
        em.refresh(proceso);
        if (proceso != null && proceso.getFechaFin() == null) {
            for (TrazabilidadProceso traza : proceso.getTrazabilidadProceso()) {
                if (traza.getFechaFin() == null) {
                    traza.setFechaFin(new Date());
                    em.merge(traza);
                }
            }

            // Actualiza el estado en el proceso
            EstadoProceso estadoEnt = em.find(EstadoProceso.class, estado.getId());
            proceso.setEstadoProceso(estadoEnt);
            em.merge(proceso);

            // Crear traza
            UsuarioPersona usuarioEntidad = null;
            TrazabilidadProceso traza = new TrazabilidadProceso();
            traza.setProceso(proceso);
            traza.setUsuario(
                    UsuarioPersonaHelper.toLevel1Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), usuarioEntidad));
            traza.setEstadoProceso(estadoEnt);
            traza.setFechaInicio(new Date());
            em.persist(traza);

            // Cierre de proceso
            if (cerrarProceso != null && cerrarProceso) {
                proceso.setFechaFin(new Date());
                em.merge(proceso);
            }
            em.flush();
            return TrazabilidadProcesoHelper.toLevel0DTO(traza);
        }
        return null;

    }

    @Override
    public EstadoProcesoDTO consultarEstadoProceso(Long idProceso) {
        logger.debug("ProcesoEJB::consultarEstadoProceso(Long)");
        Proceso proceso = em.find(Proceso.class, idProceso);
        if (proceso != null) {
            return EstadoProcesoHelper.toLevel1DTO(proceso.getEstadoProceso());
        }
        return null;
    }

    @Override
    public List<ProcesoDTO> consultarProceso(ProcesoDTO proceso) {
        logger.debug("ProcesoEJB::consultarProceso(ProcesoDTO)");

        List<ProcesoDTO> lsProcesoDTO = new ArrayList<>();

        if (proceso != null) {
            GenericDao<Proceso> procesoDao = new GenericDao<>(Proceso.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT DISTINCT p FROM Proceso p");
            jpql.append(" WHERE 1=1");

            if (proceso.getId() != null) {
                jpql.append(" AND p.id = :idProceso");
                filtros.put("idProceso", proceso.getId());
            }

            if (proceso.getTipoProceso() != null) {
                jpql.append(" AND p.tipoProceso.id = :idTipoProceso");
                filtros.put("idTipoProceso", proceso.getTipoProceso().getId());
            }

            if (proceso.getNumeroProceso() != null) {
                jpql.append(" AND p.numeroProceso = :numeroProceso");
                filtros.put("numeroProceso", proceso.getNumeroProceso());
            }

            if (proceso.getFechaInicio() != null && proceso.getFechaFin() != null) {
                jpql.append(
                        " AND p.fechaInicio >= :fechaInicioAperturaProceso AND p.fechaInicio <= :fechaFinAperturaProceso ");
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(proceso.getFechaInicio());
                fechaInicio.set(Calendar.HOUR, 0);
                fechaInicio.set(Calendar.MINUTE, 0);
                fechaInicio.set(Calendar.SECOND, 0);
                filtros.put("fechaInicioAperturaProceso", fechaInicio.getTime());
                Calendar fechaFinal = Calendar.getInstance();
                fechaFinal.setTime(proceso.getFechaFin());
                fechaFinal.set(Calendar.HOUR, 23);
                fechaFinal.set(Calendar.MINUTE, 59);
                fechaFinal.set(Calendar.SECOND, 59);
                filtros.put("fechaFinAperturaProceso", fechaFinal.getTime());
            }

            if (proceso.getFechaInicio() != null && proceso.getFechaFin() == null) {
                jpql.append(" AND p.fechaInicio = :fechaInicioAperturaProceso ");
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(proceso.getFechaInicio());
                filtros.put("fechaInicioAperturaProceso", fechaInicio.getTime());
            }

            if (proceso.getTrazabilidadProcesoList() != null && !proceso.getTrazabilidadProcesoList().isEmpty()) {
                jpql.append(" AND p.trazabilidadProceso.estadoProceso.id = :idEstadoProceso");
                TrazabilidadProcesoDTO trazabilidadProcesoDTO = proceso.getTrazabilidadProcesoList().get(0);
                filtros.put("idEstadoProceso", trazabilidadProcesoDTO.getEstadoProceso().getId());
            }
            jpql.append(" ORDER BY p.fechaInicio DESC");
            List<Proceso> resultadoConsulta = procesoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {

                lsProcesoDTO = ProcesoHelperExtend.toListLevel1DTOExtTrazabilidad(resultadoConsulta);
            }
        }
        return lsProcesoDTO;
    }

    @Override
    public DocumentoProcesoDTO registrarDocumento(DocumentoProcesoDTO documento) {
        logger.debug("ProcesoEJB::registrarDocumento(DocumentoProcesoDTO)");
        DocumentoProceso documentoEntity = DocumentoProcesoHelper.toLevel1Entity(documento, null);
        em.persist(documentoEntity);

        documento.setId(documentoEntity.getId());

        return documento;
    }

    @Override
    public List<TrazabilidadProcesoDTO> consultarTrazabilidad(TrazabilidadProcesoDTO trazabilidad) {
        logger.debug("ProcesoEJB::consultarProceso(ProcesoDTO)");

        checkNotNull(trazabilidad, "Trazabilidad es obligatoria");
        checkNotNull(trazabilidad.getProceso(), "Proceso es obligatorio");

        List<TrazabilidadProcesoDTO> lsTrazabilidadDTO = new ArrayList<>();

        if (trazabilidad != null) {
            GenericDao<TrazabilidadProceso> trazabilidadDao = new GenericDao<>(TrazabilidadProceso.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT DISTINCT tp FROM TrazabilidadProceso tp");
            jpql.append(" JOIN FETCH tp.usuario d");
            jpql.append(" LEFT JOIN FETCH tp.documentos d");
            jpql.append(" WHERE 1=1");

            if (trazabilidad.getId() != null) {
                jpql.append(" AND tp.id = :idTrazabilidadProceso");
                filtros.put("idTrazabilidadProceso", trazabilidad.getId());
            }

            if (trazabilidad.getEstadoProceso() != null) {
                jpql.append(" AND tp.estadoProceso.id = :idEstadoProceso");
                filtros.put("idEstadoProceso", trazabilidad.getEstadoProceso().getId());
            }

            if (trazabilidad.getProceso() != null && trazabilidad.getProceso().getNumeroProceso() != null) {
                jpql.append(" AND tp.proceso.numeroProceso = :numeroProceso");
                filtros.put("numeroProceso", trazabilidad.getProceso().getNumeroProceso());
            }

            if (trazabilidad.getProceso() != null && trazabilidad.getProceso().getId() != null) {
                jpql.append(" AND tp.proceso.id = :idProceso");
                filtros.put("idProceso", trazabilidad.getProceso().getId());
            }

            jpql.append(" ORDER BY tp.fechaInicio DESC");

            List<TrazabilidadProceso> resultadoConsulta = trazabilidadDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsTrazabilidadDTO = TrazabilidadProcesoHelperExtend.toListLevel1DTOExtDocumentos(resultadoConsulta);
            }
        }
        return lsTrazabilidadDTO;
    }

    @Override
    public List<ParticipanteProcesoDTO> consultarParticipantesProceso(ParticipanteProcesoDTO criterios) {
        logger.debug("ProcesoEJB::consultarParticipantesProceso(ParticipanteProcesoDTO)");

        List<ParticipanteProcesoDTO> lsParticipanteDTO = new ArrayList<>();

        if (criterios != null) {
            GenericDao<ParticipanteProceso> participanteProcesoDao = new GenericDao<>(ParticipanteProceso.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();
            jpql.append("SELECT p FROM ParticipanteProceso p");
            jpql.append(" LEFT JOIN FETCH p.persona pe");
            jpql.append(" LEFT JOIN FETCH p.tipoParticipante tp");
            jpql.append(" WHERE 1=1");

            if (criterios.getId() != null) {
                jpql.append(" AND p.id = :idParticipanteProceso");
                filtros.put("idParticipanteProceso", criterios.getId());
            }

            if (criterios.getTipoParticipante() != null) {
                jpql.append(" AND tp.id = :idTipoParticipante");
                filtros.put("idTipoParticipante", criterios.getTipoParticipante().getId());
            }

            if (criterios.getProceso() != null) {
                jpql.append(" AND p.proceso.id = :idProceso");
                filtros.put("idProceso", criterios.getProceso().getId());
            }

            List<ParticipanteProceso> resultadoConsulta = participanteProcesoDao.buildAndExecuteQuery(jpql, filtros);
            if (!resultadoConsulta.isEmpty()) {
                lsParticipanteDTO = ParticipanteProcesoHelperExtend.toListLevel2DTO(resultadoConsulta);
            }
        }
        return lsParticipanteDTO;
    }

    @Override
    public ProcesoDTO consultarProceso(Long idProceso) {
        logger.debug("ProcesoEJB::consultarProceso(Long)");
        ProcesoDTO procesoDTO = null;
        if (idProceso != null) {
            Proceso proceso = em.find(Proceso.class, idProceso);
            if (proceso != null) {
                procesoDTO = ProcesoHelperExtend.toLevel1DTOExt(proceso);
            }
        }
        return procesoDTO;
    }
}