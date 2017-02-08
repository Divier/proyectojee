package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.comun.NotificacionProcesoDTO;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.interfaces.ILImpugnacion;
import co.com.datatools.c2.negocio.interfaces.ILNotificacion;
import co.com.datatools.c2.negocio.interfaces.ILPruebas;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRNotificacion;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;

@Stateless(name = "NotificacionEJB")
@LocalBean
public class NotificacionEJB implements ILNotificacion, IRNotificacion {

    private final static Logger logger = Logger.getLogger(NotificacionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminNegocio fachadaAdminNegocio;
    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;
    @EJB
    private IRFachadaProceso iRFachadaProceso;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;
    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;
    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;
    @EJB
    private IRFirma iRfirma;
    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;
    @EJB
    private IRRepositorioArchivo iRepositorioArchivoEjb;
    @EJB
    private ILImpugnacion iLImpugnacion;
    @EJB
    private ILPruebas iLPruebas;
    @EJB
    private ILNotificacion iLNotificacion;

    @Override
    public List<NotificacionProcesoDTO> consultaNotificacionProcesos(
            NotificacionProcesoDTO filtrosNotificacionProcesoDTO) {
        logger.debug("NotificacionEJB.consultaNotificacionProcesos(NotificacionProcesoDTO)");
        List<NotificacionProcesoDTO> notificacionProcesoDTOs = null;

        // Estados procesos que aplica notificacion
        List<Integer> estadosProcesos = new ArrayList<>();
        estadosProcesos.add(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO.getValue());
        estadosProcesos.add(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue());
        estadosProcesos.add(EnumEstadoProceso.ECUADOR_IMPUGNACION_CERRADO.getValue());

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT tp FROM TrazabilidadProceso tp");
        jpql.append(" INNER JOIN FETCH tp.proceso p");
        jpql.append(" INNER JOIN FETCH p.estadoProceso ep");
        jpql.append(" WHERE 1=1");
        jpql.append(" AND p.fechaInicio >= :fechaInicioAperturaProceso AND p.fechaInicio <= :fechaFinAperturaProceso ");
        jpql.append(" AND tp.estadoProceso.id IN (:estadosProcesos)");
        jpql.append(" AND p.tipoProceso.id = :tipoProceso");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("fechaInicioAperturaProceso", filtrosNotificacionProcesoDTO.getFechaInicio());
        query.setParameter("fechaFinAperturaProceso", filtrosNotificacionProcesoDTO.getFechaFin());
        query.setParameter("estadosProcesos", estadosProcesos);
        query.setParameter("tipoProceso", filtrosNotificacionProcesoDTO.getTipoProceso());

        @SuppressWarnings("unchecked")
        List<TrazabilidadProceso> trazabilidadProcesos = query.getResultList();
        if (trazabilidadProcesos != null && !trazabilidadProcesos.isEmpty()) {
            notificacionProcesoDTOs = new ArrayList<>();
            for (TrazabilidadProceso trazabilidadProceso : trazabilidadProcesos) {
                NotificacionProcesoDTO notificacionProcesoDTO = new NotificacionProcesoDTO();

                // Estado Proceso
                notificacionProcesoDTO.setEstadoProceso(trazabilidadProceso.getEstadoProceso().getNombre());
                // Fecha fin
                notificacionProcesoDTO.setFechaFin(trazabilidadProceso.getProceso().getFechaFin());
                // Fecha inicio
                notificacionProcesoDTO.setFechaInicio(trazabilidadProceso.getProceso().getFechaInicio());
                // Id proceso
                notificacionProcesoDTO.setIdProceso(trazabilidadProceso.getProceso().getId());
                // Numero de proceso
                notificacionProcesoDTO.setNumeroProceso(trazabilidadProceso.getProceso().getNumeroProceso());
                // ID Estado Proceso
                notificacionProcesoDTO.setIdEstadoProceso(trazabilidadProceso.getEstadoProceso().getId());
                // ID Tipo Proceso
                notificacionProcesoDTO.setTipoProceso(filtrosNotificacionProcesoDTO.getTipoProceso());

                notificacionProcesoDTOs.add(notificacionProcesoDTO);
            }
        }
        return notificacionProcesoDTOs;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void prepararNotificacion(List<NotificacionProcesoDTO> lsNotificaciones) {
        if (lsNotificaciones != null && !lsNotificaciones.isEmpty()) {
            for (NotificacionProcesoDTO notificacion : lsNotificaciones) {
                try {
                    iLNotificacion.notificar(notificacion);
                } catch (CirculemosNegocioException e) {
                    logger.error("Error al enviar la notificacion", e);
                }
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void notificar(NotificacionProcesoDTO notificacion) throws CirculemosNegocioException {

        TrazabilidadProcesoDTO traza = new TrazabilidadProcesoDTO();
        ProcesoDTO proceso = new ProcesoDTO();
        proceso.setId(notificacion.getIdProceso());

        EstadoProcesoDTO estadoProceso = new EstadoProcesoDTO();
        estadoProceso.setId(Integer.valueOf(notificacion.getIdEstadoProceso()));
        proceso.setEstadoProceso(estadoProceso);
        traza.setProceso(proceso);
        TrazabilidadProcesoDTO trazaCompleta = null;

        List<TrazabilidadProcesoDTO> lsTrazabilidad = iRFachadaProceso.consultarTrazabilidad(traza);
        if (lsTrazabilidad != null && !lsTrazabilidad.isEmpty()) {
            trazaCompleta = lsTrazabilidad.get(0);
        }

        Long idDocumento = null;
        if (notificacion.getTipoProceso().equals(EnumTipoProceso.IMPUGNACION_COMPARENDO.getValue())) {

            if (Integer.valueOf(notificacion.getIdEstadoProceso())
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO.getValue())
                    || notificacion.getIdEstadoProceso()
                            .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getValue())) {
                idDocumento = trazaCompleta.getDocumentos().get(0).getNumeroDocumento();
                iLImpugnacion.enviarCorreoEvaluarImpugnacion(notificacion.getIdProceso(), idDocumento,
                        trazaCompleta.getId());
            } else if (Integer.valueOf(notificacion.getIdEstadoProceso())
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_CERRADO.getValue())) {

                StringBuilder jpql = new StringBuilder();
                jpql.append("SELECT cp FROM ComparendoProceso cp");
                jpql.append(" WHERE cp.idProceso = :idProceso");

                Query query = em.createQuery(jpql.toString());
                query.setParameter("idProceso", notificacion.getIdProceso());
                List<ComparendoProceso> comparendoProceso = query.getResultList();

                if (comparendoProceso != null && !comparendoProceso.isEmpty()) {
                    iLImpugnacion.enviarCorreoFalloImpugnacion(notificacion.getIdProceso(),
                            comparendoProceso.get(0).getComparendo().getCicomparendo(), trazaCompleta);
                }
            }
        }
    }
}