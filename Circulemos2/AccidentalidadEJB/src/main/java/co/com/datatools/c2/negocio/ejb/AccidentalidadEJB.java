package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.AccidentalidadDTO;
import co.com.datatools.c2.dto.AdjuntosAccidentalidadDTO;
import co.com.datatools.c2.dto.ArchivoAccidentalidadDTO;
import co.com.datatools.c2.dto.DatosAccidentalidadDTO;
import co.com.datatools.c2.dto.DetalleAccidentalidadDTO;
import co.com.datatools.c2.dto.FiltrosAccidentalidadDTO;
import co.com.datatools.c2.dto.RespuestaAccidentalidadDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.entidades.Accidentalidad;
import co.com.datatools.c2.entidades.ArchivoAccidentalidad;
import co.com.datatools.c2.entidades.DetalleAccidentalidad;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAccidentalidad;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.AccidentalidadHelper;
import co.com.datatools.c2.negocio.helpers.ArchivoAccidentalidadHelper;
import co.com.datatools.c2.negocio.helpers.DetalleAccidentalidadHelper;
import co.com.datatools.c2.negocio.interfaces.ILAccidentalidad;
import co.com.datatools.c2.negocio.interfaces.IRAccidentalidad;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class AccidentalidadEJB
 */
@Stateless(name = "AccidentalidadEJB")
@LocalBean
public class AccidentalidadEJB implements ILAccidentalidad, IRAccidentalidad {

    private final static Logger logger = Logger.getLogger(AccidentalidadEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRFachadaAdminGeneral iRFachadaAdminGeneral;

    @EJB
    private IRRepositorioArchivo iRepositorioArchivoEjb;

    /**
     * Guarda el informe de accidentalidad recibido
     * 
     * @throws CirculemosNegocioException
     */
    @Override
    public String registrarAccidentalidad(DatosAccidentalidadDTO accidentalidad) throws CirculemosNegocioException {
        logger.debug("AccidentalidadEJB::registrarAccidentalidad(IngresoAccidentalidadDTO)");
        String numeroConsecutivo = null;
        // Genera consecutivo de accidentalidad
        accidentalidad.getAccidentalidad()
                .setConsecutivo(iRFachadaAdminGeneral.generarConsecutivo(
                        iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                        EnumConsecutivo.NUMERO_ACCIDENTALIDAD));
        accidentalidad.getAccidentalidad().setFechaRegistro(Calendar.getInstance().getTime());
        accidentalidad.getAccidentalidad().setFechaActualizacion(Calendar.getInstance().getTime());
        // Obtener usuario en sesion
        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();
        accidentalidad.getAccidentalidad().setUsuario(usuario);

        try {
            // Persiste la entidad de Accidentalidad
            Accidentalidad accident = AccidentalidadHelper.toLevel1Entity(accidentalidad.getAccidentalidad(), null);
            registrarAccidentalidad(accident);

            // Persiste el detalle de la accidentalidad
            if (accidentalidad.getLsDetalleAccidentalidad() != null
                    && !accidentalidad.getLsDetalleAccidentalidad().isEmpty()) {
                for (DetalleAccidentalidadDTO detalleAccidenteDTO : accidentalidad.getLsDetalleAccidentalidad()) {
                    detalleAccidenteDTO.setAccidentalidad(AccidentalidadHelper.toLevel1DTO(accident));
                    registrarDetalleAccidentalidad(
                            DetalleAccidentalidadHelper.toLevel1Entity(detalleAccidenteDTO, null));
                }
            }

            if (accidentalidad.getAdjuntos() != null && !accidentalidad.getAdjuntos().isEmpty()) {
                // Persiste los adjuntos del informe
                for (AdjuntosAccidentalidadDTO adjuntos : accidentalidad.getAdjuntos()) {
                    adjuntos.getAdjuntos().setAccidentalidad(AccidentalidadHelper.toLevel1DTO(accident));
                    adjuntos.getAdjuntos().setNumeroArchivo(registrarArchivos(adjuntos));
                    adjuntos.getAdjuntos().setUsuario(usuario);
                    adjuntos.getAdjuntos().setFechaRegistro(Calendar.getInstance().getTime());
                    registrarArchivoAccidentalidad(adjuntos.getAdjuntos());
                }
            }
            numeroConsecutivo = accidentalidad.getAccidentalidad().getConsecutivo();

        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAccidentalidad.AdministrarAccidentalidad.ACC_00201);
        }
        return numeroConsecutivo;
    }

    /**
     * 
     * @param accidentalidad
     */
    private void registrarAccidentalidad(Accidentalidad accidentalidad) {
        // Persiste la entidad de Accidentalidad
        em.persist(accidentalidad);
    }

    /**
     * 
     * @param detalleAccidentalidad
     */
    private void registrarDetalleAccidentalidad(DetalleAccidentalidad detalleAccidentalidad) {
        // Persiste el detalle de la accidentalidad
        em.persist(detalleAccidentalidad);
    }

    /**
     * 
     * @param archivos
     */
    private void registrarArchivoAccidentalidad(ArchivoAccidentalidadDTO archivos) {
        // Persiste los adjuntos del informe
        ArchivoAccidentalidad archivo = ArchivoAccidentalidadHelper.toLevel1Entity(archivos, null);
        em.persist(archivo);
    }

    /**
     * Realiza la consulta de informe de accidentalidad por el consecutivo de informe
     * 
     */
    @Override
    public List<AccidentalidadDTO> consultarAccidentalidad(AccidentalidadDTO accidentalidad) {
        logger.debug("AccidentalidadEJB::consultarAccidentalidad(FiltrosAccidentalidadDTO)");
        List<AccidentalidadDTO> lsResultado = null;
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT a FROM Accidentalidad a");
        jpql.append(" WHERE 1=1 ");

        if (accidentalidad.getConsecutivo() != null) {
            jpql.append("AND a.consecutivo = :idInforme ");
            filtros.put("idInforme", accidentalidad.getConsecutivo());
        }

        GenericDao<Accidentalidad> dao = new GenericDao<>(Accidentalidad.class, em);
        List<Accidentalidad> resultado = dao.buildAndExecuteQuery(jpql, filtros);

        AccidentalidadDTO accidentalidadDTO = null;

        if (resultado != null && !resultado.isEmpty()) {
            lsResultado = new ArrayList<>();
            for (Accidentalidad acciAux : resultado) {
                accidentalidadDTO = AccidentalidadHelper.toLevel1DTO(acciAux);
                lsResultado.add(accidentalidadDTO);
            }
        }
        return lsResultado;
    }

    /**
     * 
     */
    @Override
    public List<RespuestaAccidentalidadDTO> consultarAccidentalidadInforme(
            FiltrosAccidentalidadDTO filtrosAccidentalidad) {

        List<RespuestaAccidentalidadDTO> lsResultado = new ArrayList<>();
        // Cargo el dto con los filtros para la consulta
        AccidentalidadDTO accidentalidadDTO = new AccidentalidadDTO();
        accidentalidadDTO.setConsecutivo(filtrosAccidentalidad.getConsecutivo());

        // Consulta el informe de accidentalidad
        List<AccidentalidadDTO> respuesta = consultarAccidentalidad(accidentalidadDTO);
        if (respuesta != null && !respuesta.isEmpty()) {
            boolean doc = false;
            for (AccidentalidadDTO datos : respuesta) {
                // Carga los datos necesarios en la pantalla
                RespuestaAccidentalidadDTO resultado = new RespuestaAccidentalidadDTO();
                resultado.setIdAccidentalidad(datos.getId());
                resultado.setConsecutivo(datos.getConsecutivo());
                resultado.setFechaActulizacion(datos.getFechaActualizacion());
                resultado.setUsuario(datos.getUsuario().getLogin());

                // Verificar documentos
                doc = consultarExistenciaDocumentos(resultado.getIdAccidentalidad());

                resultado.setDocumentos(doc);

                lsResultado.add(resultado);
            }
        }

        return lsResultado;
    }

    /**
     * Guarda los adjuntos ingresados en el informe de accidentalidad, devuelve el numero de documento
     * 
     * @param adjunto
     * @return
     * @throws CirculemosAlertaException
     */
    private String registrarArchivos(AdjuntosAccidentalidadDTO adjunto) throws CirculemosAlertaException {

        ArchivoTransportableDTO archivoDTO = new ArchivoTransportableDTO();
        archivoDTO.setContenido(adjunto.getArchivo());
        archivoDTO.setNombre(adjunto.getAdjuntos().getNombreArchivo());
        String numeroArchivo = iRepositorioArchivoEjb
                .registrarDocumento(EnumCategoriaDocumento.ACCIDENTALIDAD_EVIDENCIA, archivoDTO);

        return numeroArchivo;
    }

    @Override
    public DatosAccidentalidadDTO consultarAccidentalidad(Long idInforme) {
        logger.debug("AccidentalidadEJB::consultarAccidentalidad(String)");
        DatosAccidentalidadDTO resultadoDTO = null;
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT a FROM Accidentalidad a ");
        jpql.append("WHERE a.id = :idInforme ");
        filtros.put("idInforme", idInforme);

        GenericDao<Accidentalidad> dao = new GenericDao<>(Accidentalidad.class, em);
        List<Accidentalidad> resultado = dao.buildAndExecuteQuery(jpql, filtros);

        if (resultado != null && !resultado.isEmpty()) {
            resultadoDTO = new DatosAccidentalidadDTO();
            // Se llena el DTO de respuesta
            for (Accidentalidad datos : resultado) {
                AccidentalidadDTO accidentalidadDTO = new AccidentalidadDTO();
                accidentalidadDTO = AccidentalidadHelper.toLevel1DTO(datos);
                resultadoDTO.setAccidentalidad(accidentalidadDTO);

                // Documentos accidentalidad
                List<AdjuntosAccidentalidadDTO> adjuntosAccidentalidadDTOs = null;
                if (datos.getArchivoAccidentalidad() != null && !datos.getArchivoAccidentalidad().isEmpty()) {
                    adjuntosAccidentalidadDTOs = new ArrayList<>();
                    for (ArchivoAccidentalidad archivoAccidentalidad : datos.getArchivoAccidentalidad()) {
                        AdjuntosAccidentalidadDTO adjuntosAccidentalidadDTO = new AdjuntosAccidentalidadDTO();
                        adjuntosAccidentalidadDTO
                                .setAdjuntos(ArchivoAccidentalidadHelper.toLevel1DTO(archivoAccidentalidad));
                        adjuntosAccidentalidadDTOs.add(adjuntosAccidentalidadDTO);
                    }
                    resultadoDTO.setAdjuntos(adjuntosAccidentalidadDTOs);
                }

                // Detalle accidentalidad
                resultadoDTO.setLsDetalleAccidentalidad(
                        DetalleAccidentalidadHelper.toListLevel1DTO(datos.getDetalleAccidentalidad()));
            }
        }

        return resultadoDTO;

    }

    /**
     * Se encarga de verificar si el informe tiene documentos adjuntos
     * 
     * @param idInforme
     * @return
     */
    private boolean consultarExistenciaDocumentos(Long idInforme) {
        logger.debug("AccidentalidadEJB::consultarExistenciaDocumentos(Long)");
        boolean existe = false;

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();

        jpql.append("SELECT a FROM Accidentalidad a ");
        jpql.append("WHERE a.id = :idInforme ");
        filtros.put("idInforme", idInforme);

        GenericDao<Accidentalidad> dao = new GenericDao<>(Accidentalidad.class, em);
        List<Accidentalidad> resultado = dao.buildAndExecuteQuery(jpql, filtros);

        if (resultado != null && !resultado.isEmpty()) {
            // Se llena el DTO de respuesta
            for (Accidentalidad datos : resultado) {
                if (datos.getArchivoAccidentalidad() != null && !datos.getArchivoAccidentalidad().isEmpty()) {
                    existe = true;
                }
            }
        }

        return existe;

    }

    @Override
    public void modificarAccidentalidad(DatosAccidentalidadDTO accidentalidad) throws CirculemosAlertaException {
        logger.debug("AccidentalidadEJB::modificarAccidentalidad(IngresoAccidentalidadDTO)");

        // Obtener usuario en sesion
        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();
        accidentalidad.getAccidentalidad().setUsuario(usuario);

        // Se ingresa la fecha de actualizacion
        accidentalidad.getAccidentalidad().setFechaActualizacion(Calendar.getInstance().getTime());

        // Se actualiza la informacion del informe
        Accidentalidad acci = AccidentalidadHelper.toLevel1Entity(accidentalidad.getAccidentalidad(), null);
        em.merge(acci);

        // Se actualiza la informacion de los detalles del informe
        for (DetalleAccidentalidadDTO detalleAccidenteDTO : accidentalidad.getLsDetalleAccidentalidad()) {
            DetalleAccidentalidad detalles = DetalleAccidentalidadHelper.toLevel1Entity(detalleAccidenteDTO, null);
            em.merge(detalles);
        }

        // Valida si envian archivos
        if (accidentalidad.getAdjuntos() != null && !accidentalidad.getAdjuntos().isEmpty()) {
            // Persiste los archivos del informe
            for (AdjuntosAccidentalidadDTO adjuntos : accidentalidad.getAdjuntos()) {

                if (adjuntos.getAdjuntos().getId() == null) {
                    adjuntos.getAdjuntos().setAccidentalidad(AccidentalidadHelper.toLevel1DTO(acci));
                    adjuntos.getAdjuntos().setNumeroArchivo(registrarArchivos(adjuntos));
                    adjuntos.getAdjuntos().setUsuario(usuario);
                    adjuntos.getAdjuntos().setFechaRegistro(Calendar.getInstance().getTime());
                    registrarArchivoAccidentalidad(adjuntos.getAdjuntos());
                }
            }
        }

    }

    @Override
    public void guardarCapturaInforme(String consecutivo, byte[] capturaInforme) throws CirculemosNegocioException {
        logger.debug("AccidentalidadEJB::guardarCapturaInforme(String consecutivo, byte[] capturaInforme)");

        // Obtener usuario en sesion
        UsuarioPersonaDTO usuario = iRSeguridadCirculemos.obtenerUsuarioDto();

        // Cargo dto para consultar informe IPAT
        AccidentalidadDTO accidentalidadDTO = new AccidentalidadDTO();
        accidentalidadDTO.setConsecutivo(consecutivo);
        List<AccidentalidadDTO> respuesta = consultarAccidentalidad(accidentalidadDTO);

        try {
            ArchivoAccidentalidadDTO captura = new ArchivoAccidentalidadDTO();
            captura.setAccidentalidad(respuesta.get(0));
            captura.setNombreArchivo("Captura Informe");
            captura.setUsuario(usuario);
            captura.setFechaRegistro(Calendar.getInstance().getTime());

            // Se carga DTO para generar numero de documento
            AdjuntosAccidentalidadDTO adjuntos = new AdjuntosAccidentalidadDTO();
            adjuntos.setAdjuntos(captura);
            adjuntos.setArchivo(capturaInforme);
            captura.setNumeroArchivo(registrarArchivos(adjuntos));

            // Guardar el archivo
            registrarArchivoAccidentalidad(captura);

        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAccidentalidad.AdministrarAccidentalidad.ACC_00202);
        }

    }

}
