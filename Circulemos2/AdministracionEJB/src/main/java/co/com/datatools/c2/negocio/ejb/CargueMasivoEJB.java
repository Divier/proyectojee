
package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.RespuestaValidaCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.TipoCargueArchivoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.cargue.DocumentoResultadoCargue;
import co.com.datatools.c2.entidades.cargue.EstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.cargue.CargueArchivoHelper;
import co.com.datatools.c2.negocio.helpers.cargue.DocumentoResultadoCargueHelper;
import co.com.datatools.c2.negocio.interfaces.ILCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.ILConsecutivo;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.IRCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class CargueMasivoEJB
 */
@Stateless(name = "CargueMasivoEJB")
@LocalBean
public class CargueMasivoEJB implements IRCargueMasivo, ILCargueMasivo {

    private final static Logger logger = Logger.getLogger(CargueMasivoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILParametro parametroEjb;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    @EJB
    private ILConsecutivo iConsecutivo;

    @Override
    public List<CargueArchivoDTO> consultarCargueArchivo(CargueArchivoDTO cargueArchivo) {
        logger.debug("CargueMasivoEJB.consultarCargueArchivo(CargueArchivoDTO)");
        List<CargueArchivoDTO> lsCargueArchivoDTO = null;
        checkNotNull(cargueArchivo, "Debe recibirse el cargue de filtro");
        Map<String, Object> filtros = new HashMap<String, Object>();
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM CargueArchivo c");
        jpql.append(" WHERE 1=1");
        if (cargueArchivo.getId() != null) {
            jpql.append(" AND c.id = :idCargue");
            filtros.put("idCargue", cargueArchivo.getId());
        }
        if (cargueArchivo.getEstadoCargueArchivo() != null) {
            jpql.append(" AND c.estadoCargueArchivo.id = :estadoCargue");
            filtros.put("estadoCargue", cargueArchivo.getEstadoCargueArchivo().getId());
        }
        if (cargueArchivo.getTipoCargueArchivo() != null) {
            jpql.append(" AND c.tipoCargueArchivo.id = :tipoCargue");
            filtros.put("tipoCargue", cargueArchivo.getTipoCargueArchivo().getId());
        }
        if (cargueArchivo.getUsuarioCargue() != null) {
            jpql.append(" AND c.usuarioCargue.login = :loginUsuario");
            filtros.put("loginUsuario", cargueArchivo.getUsuarioCargue().getLogin());
        }

        GenericDao<CargueArchivo> dao = new GenericDao<>(CargueArchivo.class, em);
        List<CargueArchivo> resultadoConsulta = dao.buildAndExecuteQuery(jpql.toString(), filtros);
        lsCargueArchivoDTO = CargueArchivoHelper.toListLevel1DTO(resultadoConsulta);

        return lsCargueArchivoDTO;
    }

    @Override
    public CargueArchivoDTO registrarCargueArchivo(CargueArchivoDTO cargueArchivoDTO) {
        logger.debug("CargueMasivoEJB.registrarCargueArchivo(CargueArchivoDTO)");
        CargueArchivo cargueArchivo = CargueArchivoHelper.toLevel1Entity(cargueArchivoDTO, null);
        em.persist(cargueArchivo);
        cargueArchivoDTO.setId(cargueArchivo.getId());
        return cargueArchivoDTO;
    }

    @Override
    public void validarEstructuraArchivo(File csvFile, Integer idTipoCargue) throws CirculemosNegocioException {
        logger.debug("CargueMasivoEJB.validarEstructuraArchivo(File, Integer)");

        int numColumnas = 0;
        int numFilas = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
                LineNumberReader lnr = new LineNumberReader(new FileReader(csvFile))) {
            lnr.skip(Long.MAX_VALUE);
            while ((line = br.readLine()) != null) {
                numFilas++;
                if (numFilas == 1) {
                    String[] informacion = line.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
                    numColumnas = informacion.length;
                    break;
                }
            }
            numFilas = lnr.getLineNumber();
        } catch (IOException e) {
            logger.error("Error al procesar el cargue masivo", e);
            throw new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082001);
        }

        EnumTipoCargueArchivo tipoCargue = EnumTipoCargueArchivo.obtenerPorValor(idTipoCargue);

        ValorParametroDTO numMaxLineasParam = parametroEjb.consultarParametro(tipoCargue.getParametroValidacion(),
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), true);
        int numMaxLineas = numMaxLineasParam.getValorParamInt();
        if (numFilas > numMaxLineas) {
            throw new CirculemosNegocioException(ErrorAdministracion.CargueMasivoUbicabilidad.UBIC_006003);
        }

        if (tipoCargue != null && numColumnas != tipoCargue.getNumeroColumnas()) {
            throw new CirculemosNegocioException(ErrorAdministracion.CargueMasivoUbicabilidad.UBIC_006001);
        }

    }

    @Override
    public void notificarCargueArchivo(CargueArchivoDTO cargueArchivoDTO) {
        if (cargueArchivoDTO != null && cargueArchivoDTO.getId() != null) {
            CargueArchivo cargueArchivo = em.find(CargueArchivo.class, cargueArchivoDTO.getId());
            cargueArchivo
                    .setEstadoCargueArchivo(new EstadoCargueArchivo(EnumEstadoCargueArchivo.NOTIFICADO.getValue()));
            em.merge(cargueArchivo);
        }
    }

    @Override
    public DocumentoResultadoCargueDTO registrarDocumentoResulCargue(DocumentoResultadoCargueDTO documentoResulDTO) {
        logger.debug("CargueMasivoEJB.registrarCargueArchivo(CargueArchivoDTO)");
        DocumentoResultadoCargue documentoResultado = DocumentoResultadoCargueHelper.toLevel1Entity(documentoResulDTO,
                null);
        em.persist(documentoResultado);
        documentoResulDTO.setId(documentoResultado.getId());
        return documentoResulDTO;
    }

    @Override
    public List<DocumentoResultadoCargueDTO> consultarDocumentosResultadoCargue(CargueArchivoDTO cargueArchivoDTO) {
        logger.debug("CargueMasivoEJB.consultarDocumentosResultadoCargue(CargueArchivoDTO)");
        List<DocumentoResultadoCargueDTO> lsDocsCargueArchivoDTO = null;
        checkNotNull(cargueArchivoDTO, "Debe recibirse el cargue de filtro");
        Map<String, Object> filtros = new HashMap<String, Object>();
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM DocumentoResultadoCargue d");
        jpql.append(" WHERE 1=1");
        if (cargueArchivoDTO.getId() != null) {
            jpql.append(" AND d.cargueArchivo.id = :idCargue");
            filtros.put("idCargue", cargueArchivoDTO.getId());
        }

        GenericDao<DocumentoResultadoCargue> dao = new GenericDao<>(DocumentoResultadoCargue.class, em);
        List<DocumentoResultadoCargue> resultadoConsulta = dao.buildAndExecuteQuery(jpql.toString(), filtros);
        lsDocsCargueArchivoDTO = DocumentoResultadoCargueHelper.toListLevel1DTO(resultadoConsulta);

        return lsDocsCargueArchivoDTO;
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void cerrarCargueArchivo(CargueArchivoDTO cargueArchivo) {
        logger.debug("CargueMasivoEJB.cerrarCargueArchivo(CargueArchivoDTO)");
        cargueArchivo.setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.PROCESADO.getValue()));
        cargueArchivo.setFechaFinaliza(Utilidades.getFechaActual());
        CargueArchivo cargue = CargueArchivoHelper.toLevel1Entity(cargueArchivo, null);
        em.merge(cargue);
        em.flush();
    }

    @Override
    public CargueArchivoDTO registrarCargueMasivo(ArchivoTransportableDTO archivoCargado,
            EnumTipoCargueArchivo tipoCargue, EnumCategoriaDocumento categoriaDocumento,
            EnumConsecutivo tipoConsecutivo) throws CirculemosNegocioException {
        logger.debug("CargueMasivoEJB.registrarCargueMasivo(ArchivoTransportableDTO, EnumTipoCargueArchivo)");
        try {
            DateFormat df = new SimpleDateFormat(ConstantesCargaArchivos.FORMATO_FECHA_ARCHIVO);
            String dateStr = df.format(Utilidades.getFechaActual());

            File csvFile = new File(ConstantesCargaArchivos.NOMBRE_ARCHIVO_CARGUE_MASIVO_UBIC + dateStr
                    + ConstantesCargaArchivos.EXTENSION_CSV);
            FileUtils.writeByteArrayToFile(csvFile, archivoCargado.getContenido());
            validarEstructuraArchivo(csvFile, tipoCargue.getValue());

            CargueArchivoDTO cargueArchivo = new CargueArchivoDTO();
            cargueArchivo
                    .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.EN_PROCESO.getValue()));
            cargueArchivo.setTipoCargueArchivo(new TipoCargueArchivoDTO(tipoCargue.getValue()));
            cargueArchivo.setFechaCargue(Utilidades.getFechaActual());
            cargueArchivo.setUsuarioCargue(iRSeguridadCirculemos.obtenerUsuarioDto());
            String codigoDoc = irRepositorioArchivo.registrarDocumento(categoriaDocumento, archivoCargado);
            cargueArchivo.setIdDocumentoCargue(Long.valueOf(codigoDoc));
            String consecutivo = iConsecutivo.generarConsecutivo(
                    iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), tipoConsecutivo);
            cargueArchivo.setConsecutivo(consecutivo);
            cargueArchivo.setTotalLeidos(0);
            cargueArchivo.setTotalInconsistencias(0);
            cargueArchivo.setTotalActualizados(0);
            cargueArchivo.setTotalAgregados(0);
            LineNumberReader lnr = new LineNumberReader(new FileReader(csvFile));
            lnr.skip(Long.MAX_VALUE);
            cargueArchivo.setTotalRegistros(lnr.getLineNumber());
            lnr.close();

            cargueArchivo = registrarCargueArchivo(cargueArchivo);

            return cargueArchivo;

        } catch (IOException | CirculemosAlertaException e) {
            logger.error("Error al procesar el cargue masivo", e);
            throw new CirculemosNegocioException(ErrorAdministracion.CargueMasivoUbicabilidad.UBIC_006002);
        }
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void registrarRegistroProcesado(CargueArchivoDTO cargueArchivo) {

        final StringBuilder sql = new StringBuilder();
        sql.append("UPDATE cargue_archivo ");
        sql.append("SET total_actualizados = :totalActualizados, total_agregados = :totalAgregados");
        sql.append(", total_leidos = :totalLeidos, total_inconsistencias = :totalIncosistencias ");
        sql.append("WHERE id_cargue_archivo = :idCargue");

        final Map<String, Object> params = new HashMap<>();
        params.put("totalLeidos", cargueArchivo.getTotalLeidos());
        params.put("totalIncosistencias", cargueArchivo.getTotalInconsistencias());
        params.put("totalActualizados", cargueArchivo.getTotalActualizados());
        params.put("totalAgregados", cargueArchivo.getTotalAgregados());
        params.put("idCargue", cargueArchivo.getId());

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        query.executeUpdate();
    }

    @Override
    public void cerrarCarguesErroneos() {
        logger.debug("CargueMasivoEJB.cerrarCarguesErroneos()");
        final StringBuilder sql = new StringBuilder();
        sql.append("UPDATE cargue_archivo ");
        sql.append("SET id_estado_cargue_archivo = :estadoConError ");
        sql.append("WHERE id_estado_cargue_archivo = :estadoEnProceso ");

        final Map<String, Object> params = new HashMap<>();
        params.put("estadoConError", EnumEstadoCargueArchivo.CON_ERROR.getValue());
        params.put("estadoEnProceso", EnumEstadoCargueArchivo.EN_PROCESO.getValue());

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        query.executeUpdate();
    }

    @Override
    public synchronized RespuestaValidaCargueArchivoDTO validarCargueActivo(List<EnumTipoCargueArchivo> tiposCargue) {
        logger.debug("CargueMasivoEJB.validarCargueActivo(List<EnumTipoCargueArchivo>)");
        RespuestaValidaCargueArchivoDTO respuesta = new RespuestaValidaCargueArchivoDTO();
        respuesta.setSinCargueActivo(true);
        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();
        List<Integer> estados = new ArrayList<>();
        estados.add(EnumEstadoCargueArchivo.EN_PROCESO.getValue());
        estados.add(EnumEstadoCargueArchivo.PROCESADO.getValue());

        Map<String, Object> filtros = new HashMap<String, Object>();
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM CargueArchivo c");
        jpql.append(" WHERE ");
        jpql.append(" c.estadoCargueArchivo.id IN (:estadoCargue)");
        filtros.put("estadoCargue", estados);

        if (tiposCargue != null) {
            jpql.append(" AND c.tipoCargueArchivo.id IN (:tiposCargue)");
            List<Integer> tipos = new ArrayList<>();
            for (EnumTipoCargueArchivo enumTipoCargueArchivo : tiposCargue) {
                tipos.add(enumTipoCargueArchivo.getValue());
            }
            filtros.put("tiposCargue", tipos);
        }

        GenericDao<CargueArchivo> dao = new GenericDao<>(CargueArchivo.class, em);
        List<CargueArchivo> resultadoConsulta = dao.buildAndExecuteQuery(jpql.toString(), filtros);
        if (resultadoConsulta != null) {
            for (CargueArchivo cargueActivo : resultadoConsulta) {
                if (cargueActivo.getEstadoCargueArchivo().getId()
                        .equals(EnumEstadoCargueArchivo.EN_PROCESO.getValue())) {
                    respuesta.setArchivoEnProcesoOtroUsuario(
                            !cargueActivo.getUsuarioCargue().getLogin().equals(usuario.getLogin()));
                    respuesta.setArchivoEnProceso(true);
                    respuesta.setSinCargueActivo(false);
                } else if (cargueActivo.getEstadoCargueArchivo().getId()
                        .equals(EnumEstadoCargueArchivo.PROCESADO.getValue())
                        && cargueActivo.getUsuarioCargue().getLogin().equals(usuario.getLogin())) {
                    respuesta.setArchivoProcesado(true);
                    respuesta.setSinCargueActivo(false);
                }
                respuesta.setCargue(CargueArchivoHelper.toLevel1DTO(cargueActivo));
                break;
            }
        }
        return respuesta;
    }
}