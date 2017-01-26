package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConsultaGeneracionMasivaDocumentosDTO;
import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaMasivaDocumentosDTO;
import co.com.datatools.c2.entidades.DetalleDocumentoMasivo;
import co.com.datatools.c2.entidades.DocumentoMasivo;
import co.com.datatools.c2.entidades.DocumentoProceso;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.DocumentoMasivoHelper;
import co.com.datatools.c2.negocio.helpers.DocumentoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.TrazabilidadProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.extencion.ParticipanteProcesoHelperExtend;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILDocumentoProceso;
import co.com.datatools.c2.negocio.interfaces.IRDocumentoProceso;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.opciones.OpcionGestorFileSystem;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "DocumentoProcesoEJB")
@LocalBean
public class DocumentoProcesoEJB implements IRDocumentoProceso, ILDocumentoProceso {

    private final static Logger logger = Logger.getLogger(DocumentoProcesoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRSeguridadCirculemos iRSegCirculemos;

    @EJB
    private IRDocumentosCirculemos iRDocCirculemos;

    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;

    private static final String GUION = "-";
    private static final String EX_ZIP = ".zip";
    private static final String RUTA_GENERACION_MASIVA = "c2/documentos_masivos/";
    private static final String EX_PDF = ".pdf";

    @Override
    public List<RespuestaConsultaMasivaDocumentosDTO> consultarDocumentos(
            ConsultaGeneracionMasivaDocumentosDTO consulta) {
        logger.debug("DocumentoProcesoEJB::consultarDocumentos(ConsultaGeneracionMasivaDocumentosDTO)");
        checkNotNull(consulta, "Debe ingresar algun criterio de busqueda");

        GenericDao<TrazabilidadProceso> trzProcesoDao = new GenericDao<>(TrazabilidadProceso.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT tp FROM TrazabilidadProceso tp");
        jpql.append(" JOIN tp.proceso p");
        jpql.append(" JOIN tp.documentos dp");
        jpql.append(" JOIN p.participantesProceso pp");
        jpql.append(" JOIN pp.persona pe");
        jpql.append(" LEFT JOIN pe.direccionPersonaList dpl");
        jpql.append(" WHERE pp.tipoParticipante.id = :idTipoPart");
        filtros.put("idTipoPart", EnumTipoParticipante.INFRACTOR.getValue());
        jpql.append(" AND (dpl.fechaFin IS NULL OR dpl.fechaFin >= :fechaActual)");
        filtros.put("fechaActual", UtilFecha.buildCalendar().getTime());

        if (consulta.getIdTipoProceso() != null) {
            jpql.append(" AND p.tipoProceso.id = :idTipoProceso");
            filtros.put("idTipoProceso", consulta.getIdTipoProceso());
        }

        if (consulta.getIdPaso() != null) {
            jpql.append(" AND tp.estadoProceso.id = :idEstadoProceso");
            filtros.put("idEstadoProceso", consulta.getIdPaso());
        }

        if (consulta.getIdDocumento() != null) {
            jpql.append(" AND dp.tipoDocumento.id = :idDocumento");
            filtros.put("idDocumento", consulta.getIdDocumento());
        }

        if (consulta.getNumProc() != null) {
            jpql.append(" AND p.numeroProceso = :numProc");
            filtros.put("numProc", consulta.getNumProc());
        }

        if (consulta.getFechaProcIni() != null && consulta.getFechaProcFin() != null) {
            jpql.append(" AND p.fechaInicio >= :fechaProcIni AND p.fechaInicio <= :fechaProcFin ");
            Calendar fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(consulta.getFechaProcIni());
            fechaInicio.set(Calendar.HOUR, 0);
            fechaInicio.set(Calendar.MINUTE, 0);
            fechaInicio.set(Calendar.SECOND, 0);
            filtros.put("fechaProcIni", fechaInicio.getTime());
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.setTime(consulta.getFechaProcFin());
            fechaFinal.set(Calendar.HOUR, 23);
            fechaFinal.set(Calendar.MINUTE, 59);
            fechaFinal.set(Calendar.SECOND, 59);
            filtros.put("fechaProcFin", fechaFinal.getTime());
        }

        if (consulta.getFechaPasoIni() != null && consulta.getFechaPasoFin() != null) {
            jpql.append(" AND tp.fechaInicio >= :fechaPasoIni AND tp.fechaInicio <= :fechaPasoFin ");
            Calendar fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(consulta.getFechaPasoIni());
            fechaInicio.set(Calendar.HOUR, 0);
            fechaInicio.set(Calendar.MINUTE, 0);
            fechaInicio.set(Calendar.SECOND, 0);
            filtros.put("fechaPasoIni", fechaInicio.getTime());
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.setTime(consulta.getFechaPasoFin());
            fechaFinal.set(Calendar.HOUR, 23);
            fechaFinal.set(Calendar.MINUTE, 59);
            fechaFinal.set(Calendar.SECOND, 59);
            filtros.put("fechaPasoFin", fechaFinal.getTime());
        }

        List<TrazabilidadProceso> resultadoConsulta = trzProcesoDao.buildAndExecuteQuery(jpql, filtros);

        Collections.sort(resultadoConsulta, new Comparator<TrazabilidadProceso>() {
            @Override
            public int compare(TrazabilidadProceso obj1, TrazabilidadProceso obj2) {
                int valor1 = obj1.getProceso().getFechaInicio().compareTo(obj2.getProceso().getFechaInicio());
                if (valor1 == 0) {
                    int valor2 = obj1.getProceso().getNumeroProceso().compareTo(obj2.getProceso().getNumeroProceso());
                    if (valor2 == 0) {
                        return obj1.getFechaInicio().compareTo(obj2.getFechaInicio());
                    } else {
                        return valor2;
                    }
                }
                return valor1;
            }
        });

        List<RespuestaConsultaMasivaDocumentosDTO> lsRespuestaDTO = new ArrayList<>();
        RespuestaConsultaMasivaDocumentosDTO respuestaDTO = null;
        if (!resultadoConsulta.isEmpty()) {
            for (TrazabilidadProceso trzProceso : resultadoConsulta) {
                for (DocumentoProceso docProceso : trzProceso.getDocumentos()) {
                    respuestaDTO = new RespuestaConsultaMasivaDocumentosDTO();
                    respuestaDTO.setProcesoDTO(ProcesoHelper.toLevel1DTO(trzProceso.getProceso()));
                    respuestaDTO.setTrazaDTO(TrazabilidadProcesoHelper.toLevel1DTO(trzProceso));
                    respuestaDTO.setDocumentoDTO(DocumentoProcesoHelper.toLevel1DTO(docProceso));
                    respuestaDTO.setPartProcesoDTO(ParticipanteProcesoHelperExtend
                            .toLevel2DTO(trzProceso.getProceso().getParticipantesProceso() != null
                                    && !trzProceso.getProceso().getParticipantesProceso().isEmpty()
                                            ? trzProceso.getProceso().getParticipantesProceso().get(0) : null));

                    DireccionPersona dp = trzProceso.getProceso().getParticipantesProceso() != null
                            && !trzProceso.getProceso().getParticipantesProceso()
                                    .isEmpty()
                                            ? (trzProceso.getProceso().getParticipantesProceso().get(0).getPersona()
                                                    .getDireccionPersonaList() != null
                                                    && !trzProceso.getProceso().getParticipantesProceso().get(0)
                                                            .getPersona()
                                                            .getDireccionPersonaList().isEmpty()
                                                                    ? trzProceso.getProceso().getParticipantesProceso()
                                                                            .get(0)
                                                                            .getPersona().getDireccionPersonaList()
                                                                            .get(0)
                                                                    : null)
                                            : null;
                    respuestaDTO.setDireccionPersonaDTO(dp != null ? DireccionPersonaHelper.toLevel1DTO(dp) : null);
                    lsRespuestaDTO.add(respuestaDTO);
                }
            }
        }
        return lsRespuestaDTO;
    }

    @Override
    public Long registrarDocumentoMasivo(DocumentoMasivoDTO documento,
            List<RespuestaConsultaMasivaDocumentosDTO> lsResConMasDoc) {
        logger.debug(
                "DocumentoProcesoEJB::registrarDocumentoMasivo(DocumentoMasivoDTO, List<RespuestaConsultaMasivaDocumentosDTO>)");
        DocumentoMasivo documentoMasivo = new DocumentoMasivo();
        documentoMasivo.setFechaSolicitud(documento.getFechaSolicitud());
        documentoMasivo.setLimiteDocumentos(documento.getLimiteDocumentos());
        documentoMasivo.setUsuario(UsuarioPersonaHelper.toLevel1Entity(iRSegCirculemos.obtenerUsuarioDto(), null));
        documentoMasivo.setCantidadDocumentos(documento.getCantidadDocumentos());
        em.persist(documentoMasivo);

        for (RespuestaConsultaMasivaDocumentosDTO resConMasDoc : lsResConMasDoc) {
            DetalleDocumentoMasivo detDocMasivo = new DetalleDocumentoMasivo();
            detDocMasivo.setDocumentoMasivo(documentoMasivo);
            detDocMasivo.setIdDocumento(resConMasDoc.getDocumentoDTO().getId());
            em.persist(detDocMasivo);
        }
        return documentoMasivo.getId();
    }

    @Override
    @Asynchronous
    public void procesarDocumentoMasivo(Long idArchivo) throws CirculemosNegocioException {
        logger.debug("DocumentoProcesoEJB::procesarDocumentoMasivo(Long)");
        checkNotNull(idArchivo, "Debe ingresar un identificador de archivo solicitado");

        try {
            GenericDao<DocumentoMasivo> docMasivoDao = new GenericDao<>(DocumentoMasivo.class, em);
            StringBuilder jpql = new StringBuilder();
            Map<String, Object> filtros = new HashMap<>();

            jpql.append("SELECT dm FROM DocumentoMasivo dm");
            jpql.append(" WHERE dm.id = :idArchivo");
            filtros.put("idArchivo", idArchivo);

            DocumentoMasivo docMas = docMasivoDao.buildAndExecuteQuery(jpql, filtros).get(0);
            List<DetalleDocumentoMasivo> lsDetDocMas = docMas.getDetalles();
            List<Long> lsIdsDocumentos = new ArrayList<>();
            String nomTipoProc = null;
            // Traer todos los ids de los documentos asociados
            for (DetalleDocumentoMasivo detDocumento : lsDetDocMas) {
                jpql = new StringBuilder();
                filtros = new HashMap<>();
                GenericDao<DocumentoProceso> docProcesoDao = new GenericDao<>(DocumentoProceso.class, em);
                jpql.append("SELECT dp FROM DocumentoProceso dp");
                jpql.append(" WHERE dp.id = :idDocumentoProc");
                filtros.put("idDocumentoProc", detDocumento.getIdDocumento());
                DocumentoProceso docProc = docProcesoDao.buildAndExecuteQuery(jpql, filtros).get(0);
                nomTipoProc = docProc.getTrazabilidadProceso().getProceso().getTipoProceso().getNombre();
                lsIdsDocumentos.add(docProc.getNumeroDocumento());
            }

            Calendar calendario = Calendar.getInstance();
            String sDia = new SimpleDateFormat("dd").format(calendario.getTime());
            String sMes = new SimpleDateFormat("MM").format(calendario.getTime());
            String sAnio = new SimpleDateFormat("yyyy").format(calendario.getTime());

            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append(nomTipoProc);
            nombreArchivo.append(GUION);
            nombreArchivo.append(sAnio);
            nombreArchivo.append(sMes);
            nombreArchivo.append(sDia);
            nombreArchivo.append(GUION);

            // Agrupar los ids de los documentos
            int control = 1;
            int grupo = 1;
            List<Long> lsIdsAgrupDocs = new ArrayList<>();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);
            for (int indice = 0;;) {
                if (control <= docMas.getLimiteDocumentos() && indice < lsIdsDocumentos.size()) {
                    lsIdsAgrupDocs.add(lsIdsDocumentos.get(indice));
                    ++control;
                    indice++;
                } else {
                    byte[] grupoDocumentos = iRDocCirculemos.consultarDocumentosPDF(lsIdsAgrupDocs);
                    Utilidades.adjuntarArchivos(zos, nombreArchivo.toString() + grupo + EX_PDF, grupoDocumentos);
                    control = 1;
                    ++grupo;
                    lsIdsAgrupDocs = new ArrayList<>();
                    grupoDocumentos = null;
                    if (indice == lsIdsDocumentos.size()) {
                        break;
                    }
                }
            }
            zos.close();
            // Guarda archivo
            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO();
            archivo.setContenido(baos.toByteArray());
            archivo.setNombre(nombreArchivo.append(idArchivo).append(EX_ZIP).toString());
            archivo.setRuta(RUTA_GENERACION_MASIVA);
            OpcionGestorFileSystem ogfs = new OpcionGestorFileSystem();
            ogfs.setUbicacion(RUTA_GENERACION_MASIVA);
            docMas.setRutaGeneracion(RUTA_GENERACION_MASIVA + nombreArchivo.toString());
            String idZipArchGenerado = iRRepositorioArchivo.registrarDocumento(null, archivo, ogfs);
            docMas.setFechaProcesado(UtilFecha.buildCalendar().getTime());
            docMas.setIdArchivoGenerado(Long.valueOf(idZipArchGenerado));
            em.merge(docMas);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.GeneracionMasivaDocumentos.ADM_069001);
        } catch (IOException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.GeneracionMasivaDocumentos.ADM_069002);
        }
    }

    @Override
    public List<DocumentoMasivoDTO> consultarDocumentoMasivo(Long idArchivo) {
        logger.debug("DocumentoProcesoEJB::consultarDocumentoMasivo()");

        GenericDao<DocumentoMasivo> detDocMasivoDao = new GenericDao<>(DocumentoMasivo.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT dm FROM DocumentoMasivo dm");
        jpql.append(" WHERE 1 = 1");

        if (idArchivo != null) {
            jpql.append(" AND dm.id = :idArchivo");
            filtros.put("idArchivo", idArchivo);
        }
        jpql.append(" AND dm.fechaProcesado IS NOT NULL");
        jpql.append(" ORDER BY dm.fechaSolicitud DESC");

        List<DocumentoMasivo> resultadoConsulta = detDocMasivoDao.buildAndExecuteQuery(jpql, filtros);
        List<DocumentoMasivoDTO> lsDetDocMasivoDTO = new ArrayList<>();
        if (!resultadoConsulta.isEmpty()) {
            lsDetDocMasivoDTO = DocumentoMasivoHelper.toListLevel1DTO(resultadoConsulta);
        }
        return lsDetDocMasivoDTO;
    }
}