package co.com.datatools.c2.negocio.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.RechazoRecaudoResDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.entidades.RecaudoRechazo;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.fachada.IRFachadaIntegracionTerceros;
import co.com.datatools.c2.negocio.helpers.RecaudoRechazoHelper;
import co.com.datatools.c2.negocio.interfaces.ILRechazosRecaudo;
import co.com.datatools.c2.negocio.interfaces.IRRechazosRecaudo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;

/**
 * Session Bean implementation class RechazosRecaudoEJB
 */
@Stateless(mappedName = "RechazosRecaudoEJB")
@LocalBean
public class RechazosRecaudoEJB implements IRRechazosRecaudo, ILRechazosRecaudo {

    private final static Logger logger = Logger.getLogger(RechazosRecaudoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;
    @EJB
    private IRFachadaIntegracionTerceros iRFachadaIntegracionTerceros;
    @EJB
    private IRFachadaAdminNegocio iRFachadaAdminNegocio;
    @EJB
    private ILRechazosRecaudo rechazosRecaudoEJB;

    @SuppressWarnings("unchecked")
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer enviarRechazosRecaudos(Integer codigoOrganismo) {
        logger.debug("RechazosRecaudoEJB.enviarRechazosRecaudos(Integer)");
        Integer totalRechazos = 0;
        Date fechaSistema = UtilFecha.buildCalendar().getTime();

        // Consultar inconsistencias
        List<ItRecaudoDTO> recaudos = iRFachadaIntegracionTerceros.consultarRecaudos(codigoOrganismo,
                EnumEstadoLectura.NO_RECIBIDO);

        if (recaudos != null && !recaudos.isEmpty()) {
            List<RecaudoRechazoDTO> recaudosRechazo = new ArrayList<RecaudoRechazoDTO>();
            List<RecaudoRechazo> rechazosTotales = new ArrayList<RecaudoRechazo>();

            for (ItRecaudoDTO recaudo : recaudos) {

                StringBuilder jpql = new StringBuilder();

                jpql.append("SELECT rr FROM RecaudoRechazo rr");
                jpql.append(" WHERE rr.idRecaudo = :idRecaudo");
                jpql.append(" AND rr.fechaGeneracionReporte is null ");

                Query query = em.createQuery(jpql.toString());
                query.setParameter("idRecaudo", recaudo.getIdRecaudo());

                List<RecaudoRechazo> rechazos = query.getResultList();

                if (rechazos != null && !rechazos.isEmpty()) {
                    rechazosTotales.addAll(rechazos);
                    for (RecaudoRechazo recaudoRechazo : rechazos) {
                        recaudosRechazo.add(RecaudoRechazoHelper.toLevel1DTO(recaudoRechazo));
                    }
                    rechazosTotales.addAll(rechazos);
                }

            }

            totalRechazos = recaudosRechazo.size();

            if (totalRechazos > 0) {
                // Generacion de archivo
                byte[] archivo = RecaudoRechazoHelper.generaArchivo(recaudosRechazo);

                OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
                organismo.setCodigoOrganismo(codigoOrganismo);
                List<OrganismoTransitoDTO> resultadoOrganismos = iRFachadaAdminNegocio
                        .consultarOrganismoTransito(organismo);

                ArrayList<ArchivoTransportableDTO> archivosTransportableDTO = new ArrayList<ArchivoTransportableDTO>();
                ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();
                archivoTransportableDTO.setContenido(archivo);
                archivoTransportableDTO.setNombre("Reporte_rechazos_recaudo" + "." + EnumTipoArchivo.XLSX.name());
                archivosTransportableDTO.add(archivoTransportableDTO);

                // Generacion de correo
                Map<String, Object> variablesMensaje = new HashMap<>();
                SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
                variablesMensaje.put("fecha_reporte_rechazos_recaudo", formateador.format(fechaSistema));
                variablesMensaje.put("total_rechazos", totalRechazos);
                variablesMensaje.put("organismo", resultadoOrganismos.get(0).getNombreOrganismo());

                // Enviar correo
                iRCirculemosMailSender.enviarCorreo(codigoOrganismo, EnumTipoCorreo.ENVIO_RECHAZOS_RECAUDO, null,
                        variablesMensaje, archivosTransportableDTO);

                // Persistencia de datos
                for (RecaudoRechazo recaudoRechazo : rechazosTotales) {
                    rechazosRecaudoEJB.actualizarFechaRechazoRecaudo(recaudoRechazo.getId());
                }
            }

        }
        return totalRechazos;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarFechaRechazoRecaudo(Long id) {
        RecaudoRechazo recaudoRechazo = em.find(RecaudoRechazo.class, id);
        recaudoRechazo.setFechaGeneracionReporte(new Date());
        em.merge(recaudoRechazo);
    }

    @Override
    public List<RechazoRecaudoResDTO> consultarRechazosRecaudosEnviados(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO) {
        logger.debug("RechazosRecaudoEJB.consultarRechazosRecaudos(Integer,Date,Date)");
        List<RechazoRecaudoResDTO> recaudosRechazoResDTOList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT rr.codigo_organismo, rr.numero_recaudo,rr.usuario, rr.fecha_recibido,rr.estado_lectura, ");
        sql.append("CONVERT(VARCHAR(MAX),STUFF((SELECT '|'+trr.descripcion FROM detalle_recaudo_rechazo drr ");
        sql.append(
                "    INNER JOIN tipo_rechazo_recaudo trr ON drr.id_tipo_rechazo_recaudo = trr.id_tipo_rechazo_recaudo ");
        sql.append("    WHERE drr.id_recaudo_rechazo = rr.id_recaudo_rechazo FOR XML PATH('')),1,1,'')) rechazos ");

        sql.append("FROM recaudo_rechazo rr ");

        sql.append("WHERE rr.codigo_organismo = :idOrganismo ");
        sql.append("AND rr.fecha_generacion_reporte BETWEEN :fechaIni AND :fechaFin ");

        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            sql.append("AND rr.numero_recaudo = :numPago ");
        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            sql.append("AND rr.numero_obligacion = :numObligacion ");
        }

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("idOrganismo", filtroConsultaInconsistenciasDTO.getOrganismoTransito());
        query.setParameter("fechaIni", filtroConsultaInconsistenciasDTO.getFechaInicial());
        query.setParameter("fechaFin", filtroConsultaInconsistenciasDTO.getFechaFinal());

        if (filtroConsultaInconsistenciasDTO.getNumeroRecaudo() != null) {
            query.setParameter("numPago", filtroConsultaInconsistenciasDTO.getNumeroRecaudo());
        }
        if (filtroConsultaInconsistenciasDTO.getObligacionPagada() != null) {
            query.setParameter("numObligacion", filtroConsultaInconsistenciasDTO.getObligacionPagada());
        }

        @SuppressWarnings("unchecked")
        List<Object[]> rechazos = query.getResultList();
        RechazoRecaudoResDTO rechazoRecaudoResDTO;
        if (rechazos != null) {
            for (Object[] recaudoRechazo : rechazos) {
                rechazoRecaudoResDTO = new RechazoRecaudoResDTO();
                int i = 0;

                rechazoRecaudoResDTO.setOrganismoTransito((Integer) recaudoRechazo[i++]);
                rechazoRecaudoResDTO.setNumeroRecaudo((String) recaudoRechazo[i++]);
                rechazoRecaudoResDTO.setNombreUsuario((String) recaudoRechazo[i++]);
                rechazoRecaudoResDTO.setFecha((Date) recaudoRechazo[i++]);
                rechazoRecaudoResDTO.setEstadoLectura(new Integer((Short) recaudoRechazo[i++]));
                rechazoRecaudoResDTO.setRechazos((String) recaudoRechazo[i]);
                recaudosRechazoResDTOList.add(rechazoRecaudoResDTO);
            }
        }

        return recaudosRechazoResDTOList;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void registrarRechazosRecaudos(RecaudoRechazoDTO recaudoRechazoDTO) {
        logger.debug("RechazosRecaudoEJB.registrarRechazosRecaudos(recaudoRechazoDTO)");

        // Persistencia de los datos de rechazo
        RecaudoRechazo recaudoRechazo = new RecaudoRechazo();
        recaudoRechazo = RecaudoRechazoHelper.toLevel0Entity(recaudoRechazoDTO, recaudoRechazo);
        recaudoRechazo.setFechaRecibido(new Date());

        em.persist(recaudoRechazo);

    }

}
