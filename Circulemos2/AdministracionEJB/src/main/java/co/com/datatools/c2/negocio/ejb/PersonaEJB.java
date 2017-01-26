package co.com.datatools.c2.negocio.ejb;

import static co.com.datatools.c2.util.Utilidades.safeList;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
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

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.dt.administracion.PersonaUbicabilidadDTO;
import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.dto.ConsultaAnalisisUbicResulDTO;
import co.com.datatools.c2.dto.ConsultarNotificacionesDTO;
import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.InconsistenciaAnalisisUbicDTO;
import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.TipoCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.TipoDocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.ArchivoPersonaDTO;
import co.com.datatools.c2.dto.personas.DatosUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.ScoreUbicabilidadDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoTelefonoDTO;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.c2.entidades.personas.ArchivoPersona;
import co.com.datatools.c2.entidades.personas.ParentescoPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaFuenteExterna;
import co.com.datatools.c2.entidades.personas.PersonaHistorico;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.RepresentanteLegal;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersonaHistorico;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoArchivoPersona;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoResulCargue;
import co.com.datatools.c2.enumeracion.EnumTipoNotificacion;
import co.com.datatools.c2.enumeracion.EnumTipoTelefono;
import co.com.datatools.c2.enumeracion.EnumTipoUbicabilidad;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoGenerado;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.AdministrarPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ArchivoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ParentescoPersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHistoricoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaJuridicaHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.RepresentanteLegalHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.CorreoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.CorreoPersonaHistoricoHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.CorreoPersonaHistoricoHelperExtend;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHistoricoHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHistoricoHelperExtend;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.TelefonoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.TelefonoPersonaHistoricoHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.TelefonoPersonaHistoricoHelperExtend;
import co.com.datatools.c2.negocio.helpers.v2.LogEnvioCorreoHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.ILFirma;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.negocio.interfaces.ILPersona;
import co.com.datatools.c2.negocio.interfaces.ILUbicabilidad;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILFachadaConfiguracion;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.negocio.opciones.GestorArchivosOpciones;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * 
 * @author robert.bautista
 */
@Stateless(name = "PersonaEJB")
@LocalBean
public class PersonaEJB implements ILPersona, IRPersona {

    private final static Logger logger = Logger.getLogger(PersonaEJB.class.getName());

    private static final int COD_ID_RUC = 2;

    private static final String STRING_VACIO = "";

    private final String NOMBRE_ARCHIVO_ACTUALIZACION_DATOS = "Actualizacion_datos.pdf";

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILAdministracion iLAdministracion;

    @EJB
    private ILDireccion iLDireccion;

    @EJB
    private ILParametro parametroEjb;

    @EJB
    private ILFachadaConfiguracion fachadaConfiguracionEJB;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private ILFirma iLfirma;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    @EJB
    private IRFachadaAdminGeneral iRFachadaAdminGeneral;

    @EJB
    private ILCargueMasivo iLCargueMasivo;

    @EJB
    private ILUbicabilidad iLUbicabilidad;

    @EJB
    private ILPersona iLPersona;

    @EJB
    private IRFachadaNotificacionTerceros iRFachadaNotificaciones;

    @Override
    public List<DireccionPersonaDTO> consultarDireccionPersona(PersonaDTO personaDTO) {
        List<DireccionPersonaDTO> listDireccionesPersona = new ArrayList<>();
        TypedQuery<DireccionPersona> query = em.createNamedQuery(DireccionPersona.SQ_FIND_DIRECCION_BY_PERSONA,
                DireccionPersona.class);
        query.setParameter("pIdPersona", personaDTO.getId());

        List<DireccionPersona> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            for (DireccionPersona direccionPersona : resultList) {
                DireccionPersonaDTO direccionPersonaDTO = DireccionPersonaHelper.toLevel1DTO(direccionPersona);

                direccionPersonaDTO.setDireccion(DireccionHelperExtend.toLevel1DTO(direccionPersona.getDireccion()));

                listDireccionesPersona.add(direccionPersonaDTO);
            }

        }

        return listDireccionesPersona;
    }

    @Override
    public PersonaDTO consultarPersona(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion) {
        checkNotNull(numeroIdentificacion, "Se esperaba numero de identificacion de entrada");

        PersonaDTO personaDTO = null;

        Map<String, Object> params = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT(p) FROM Persona p ");
        sb.append(" LEFT JOIN FETCH p.personaJuridica pj ");
        sb.append(" LEFT JOIN FETCH p.nivelEducativo ne ");
        sb.append(" LEFT JOIN FETCH p.tipoVivienda tv ");
        sb.append(" LEFT JOIN FETCH p.estadoCivil ec ");
        sb.append(" LEFT JOIN FETCH p.municipioExpedicionDocumento muni ");
        sb.append(" LEFT JOIN FETCH p.organismoTransito org ");
        sb.append(" LEFT JOIN FETCH p.fuenteInformacion fi ");
        sb.append(" LEFT JOIN FETCH p.tipoIdentificacion ti ");

        sb.append(" WHERE org.codigoOrganismo = :codOrganismo ");
        params.put("codOrganismo", codigoOrganismo);

        sb.append(" AND ti.id = :tipoId ");
        params.put("tipoId", tipoIdentificacion);

        sb.append(" AND p.numeroIdentificacion = :numeroIdentificacion ");
        params.put("numeroIdentificacion", numeroIdentificacion);

        GenericDao<Persona> personaDao = new GenericDao<>(Persona.class, this.em);
        final List<Persona> result = safeList(personaDao.buildAndExecuteQuery(sb, params));
        if (!result.isEmpty()) {
            personaDTO = ProcesarPersonasHelper.toPersonaLevel1DTO(result.get(0));

            // Consulto los telefonos asociados a la persona
            TelefonoPersonaDTO telefonoPersonaDTO = new TelefonoPersonaDTO();
            telefonoPersonaDTO.setPersona(personaDTO);
            List<TelefonoPersonaDTO> telefonoPersonaDTOs = iLUbicabilidad.consultarTelefonos(telefonoPersonaDTO);

            if (telefonoPersonaDTOs != null && !telefonoPersonaDTOs.isEmpty()) {
                for (TelefonoPersonaDTO telefonoPersonaDTOIterar : telefonoPersonaDTOs) {

                    // valida el tipo de teléfono para llenar sus respectivos campos en PersonaDTO
                    if (personaDTO.getNumeroTelefonico() == null && telefonoPersonaDTOIterar.getTipoTelefono().getId()
                            .equals(EnumTipoTelefono.TELEFONO_FIJO.getValue())) {
                        personaDTO.setNumeroTelefonico(telefonoPersonaDTOIterar.getNumeroTelefono());
                    } else if (personaDTO.getNumeroCelular() == null && telefonoPersonaDTOIterar.getTipoTelefono()
                            .getId().equals(EnumTipoTelefono.TELEFONO_CELULAR.getValue())) {
                        personaDTO.setNumeroCelular(telefonoPersonaDTOIterar.getNumeroTelefono());
                    } else if (personaDTO.getTelefonoEmpresaLabora() == null && telefonoPersonaDTOIterar
                            .getTipoTelefono().getId().equals(EnumTipoTelefono.TELEFONO_FIJO_EMPRESA.getValue())) {
                        personaDTO.setTelefonoEmpresaLabora(telefonoPersonaDTOIterar.getNumeroTelefono());
                    } else if (personaDTO.getFaxEmpresaLabora() == null && telefonoPersonaDTOIterar.getTipoTelefono()
                            .getId().equals(EnumTipoTelefono.FAX_EMPRESA.getValue())) {
                        personaDTO.setFaxEmpresaLabora(telefonoPersonaDTOIterar.getNumeroTelefono());
                    }
                }
            }

            // consulto los correos asociados a la persona
            CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
            correoPersonaDTO.setPersona(personaDTO);
            List<CorreoPersonaDTO> correoPersonaDTOs = iLUbicabilidad.consultarCorreos(correoPersonaDTO);

            if (correoPersonaDTOs != null && !correoPersonaDTOs.isEmpty()) {
                personaDTO.setCorreoElectronico(correoPersonaDTOs.get(0).getCorreoElectronico());
            }

            // consulto las direcciones asociadas a la persona
            DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
            direccionPersonaDTO.setPersona(personaDTO);
            personaDTO.setDireccionPersonaList(iLUbicabilidad.consultarDireccionPersona(direccionPersonaDTO));

        }
        return personaDTO;
    }

    @Override
    public PersonaDTO consultarPersona(long idPersona) {

        PersonaDTO dto = new PersonaDTO();
        dto.setId(idPersona);

        final List<PersonaDTO> result = consultarPersonas(dto);
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    @Override
    public PersonaDTO consultarPersona(Integer tipoIdentificacion, String numeroIdentificacion) {
        logger.debug("PersonaEJB::consultarPersona(Integer,String)");

        checkNotNull(tipoIdentificacion);
        checkNotNull(numeroIdentificacion);

        PersonaDTO personaDTO = null;
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Persona p");
        jpql.append(" WHERE p.tipoIdentificacion.id = :tipoIdentificacion");
        jpql.append(" AND p.numeroIdentificacion = :numeroIdentificacion");

        Query query = em.createQuery(jpql.toString());

        query.setParameter("tipoIdentificacion", tipoIdentificacion);
        query.setParameter("numeroIdentificacion", numeroIdentificacion);

        @SuppressWarnings("unchecked")
        List<Persona> personas = query.getResultList();

        if (personas != null && !personas.isEmpty()) {
            personaDTO = ProcesarPersonasHelper.toPersonaBasicaDTO(personas.get(0));
        }

        return personaDTO;
    }

    @Override
    public List<PersonaDTO> consultarPersonas(PersonaDTO dto) {
        checkNotNull(dto, "Se esperaba dto de entrada");
        Integer codOrganismo = null;
        if (dto.getId() == null) {
            codOrganismo = checkNotNull(
                    checkNotNull(dto.getOrganismoTransito(), "Organismo de transito es requerido").getCodigoOrganismo(),
                    "Codigo Organismo es requerido");
        }
        PersonaJuridicaDTO personaJuridicaDTO = null;

        Map<String, Object> params = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT(p) FROM Persona p ");
        sb.append(" LEFT JOIN FETCH p.personaJuridica pj ");
        sb.append(" LEFT JOIN FETCH p.nivelEducativo ne ");
        sb.append(" LEFT JOIN FETCH p.tipoVivienda tv ");
        sb.append(" LEFT JOIN FETCH p.estadoCivil ec ");
        sb.append(" LEFT JOIN FETCH p.municipioExpedicionDocumento muni ");
        sb.append(" LEFT JOIN FETCH p.organismoTransito org ");
        sb.append(" LEFT JOIN FETCH p.fuenteInformacion fi ");
        sb.append(" LEFT JOIN FETCH p.tipoIdentificacion ti ");
        sb.append(" WHERE 1=1  ");

        if (dto.getId() != null) {
            sb.append(" AND p.id = :id ");
            params.put("id", dto.getId());

        } else {
            sb.append(" AND org.codigoOrganismo = :codOrganismo ");
            params.put("codOrganismo", codOrganismo);

            if ((dto.getTipoIdentificacion() != null) && (dto.getTipoIdentificacion().getId() != null)) {
                sb.append(" AND ti.id = :tipoId ");
                params.put("tipoId", dto.getTipoIdentificacion().getId());
            }

            if (dto.getNumeroIdentificacion() != null) {
                sb.append(" AND p.numeroIdentificacion LIKE :numeroIdentificacion ");
                params.put("numeroIdentificacion", "%" + dto.getNumeroIdentificacion() + "%");
            }

            if ((dto.getFuenteInformacion() != null) && (dto.getFuenteInformacion().getCodigo() != null)) {
                sb.append(" AND fi.codigo = :fuenteInformacion ");
                params.put("fuenteInformacion", dto.getFuenteInformacion().getCodigo());
            }

            if (dto instanceof PersonaJuridicaDTO) {
                personaJuridicaDTO = (PersonaJuridicaDTO) dto;
                if (personaJuridicaDTO.getSigla() != null) {
                    sb.append(" AND LOWER ( pj.sigla ) LIKE LOWER( :sigla )");
                    params.put("sigla", "%" + personaJuridicaDTO.getSigla() + "%");
                }

                if (personaJuridicaDTO.getNombreComercial() != null) {
                    sb.append(" AND LOWER ( pj.nombreComercial ) LIKE LOWER( :nombreComercial )");
                    params.put("nombreComercial", "%" + personaJuridicaDTO.getNombreComercial() + "%");
                }

                if (personaJuridicaDTO.getDigitoVerificacion() != null) {
                    sb.append(" AND pj.digitoVerificacion = :digitoVerificacion ");
                    params.put("digitoVerificacion", personaJuridicaDTO.getDigitoVerificacion());
                }

            } else {
                if (dto.getNombre1() != null) {
                    sb.append(" AND LOWER( p.nombre1 ) LIKE LOWER( :nombre1 ) ");
                    params.put("nombre1", "%" + dto.getNombre1() + "%");
                }

                if (dto.getNombre2() != null) {
                    sb.append(" AND LOWER( p.nombre2 ) LIKE LOWER( :nombre2 ) ");
                    params.put("nombre2", "%" + dto.getNombre2() + "%");
                }

                if (dto.getApellido1() != null) {
                    sb.append(" AND LOWER( p.apellido1 ) LIKE LOWER( :apellido1 ) ");
                    params.put("apellido1", "%" + dto.getApellido1() + "%");
                }

                if (dto.getApellido2() != null) {
                    sb.append(" AND LOWER( p.apellido2 ) LIKE LOWER( :apellido2 ) ");
                    params.put("apellido2", "%" + dto.getApellido2() + "%");
                }

            }

            sb.append(" ORDER BY p.id ");
        }

        GenericDao<Persona> personaDao = new GenericDao<>(Persona.class, this.em);
        final List<Persona> result = safeList(personaDao.buildAndExecuteQuery(sb, params));
        return ProcesarPersonasHelper.toPersonaListLevel1DTO(result);
    }

    @Override
    public List<ParentescoPersonaDTO> consultarParentescoPersona(ParentescoPersonaDTO parentescoPersonaDTO) {
        final List<ParentescoPersona> parientes = consultarParentesco(parentescoPersonaDTO);

        return ParentescoPersonaHelperExtend.toListLevel1DTO(parientes);
    }

    /**
     * @see #consultarParentescoPersona(ParentescoPersonaDTO)
     */
    private List<ParentescoPersona> consultarParentesco(ParentescoPersonaDTO parentescoPersonaDTO) {
        checkNotNull(parentescoPersonaDTO, "Se esperaba dto de entrada");
        checkNotNull(checkNotNull(parentescoPersonaDTO.getPrincipal(), "Persona principal es requerida").getId(),
                "Id persona principal es requerido");

        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append(" SELECT pp FROM ParentescoPersona pp ");
        sb.append(" JOIN FETCH pp.principal pri ");
        sb.append(" JOIN FETCH pp.pariente par ");
        sb.append(" JOIN FETCH pp.tipoParentesco tp ");
        sb.append(" WHERE pri.id = :pId ");

        params.put("pId", parentescoPersonaDTO.getPrincipal().getId());

        if ((parentescoPersonaDTO.getTipoParentesco() != null)
                && (parentescoPersonaDTO.getTipoParentesco().getId() != null)) {
            sb.append(" AND tp.id = :tpId");
            params.put("tpId", parentescoPersonaDTO.getTipoParentesco().getId());
        }

        sb.append(" ORDER BY pp.fechaInicio DESC ");
        TypedQuery<ParentescoPersona> query = this.em.createQuery(sb.toString(), ParentescoPersona.class);

        for (Map.Entry<String, Object> filtros : params.entrySet()) {
            query.setParameter(filtros.getKey(), filtros.getValue());
        }

        return safeList(query.getResultList());
    }

    @Override
    public Long registrarPersona(PersonaDTO personaDTO, Boolean... sinHistorico) throws CirculemosNegocioException {
        PersonaEJB.logger.debug("PersonaEJB::ingresarPersona");
        Long idRegistro = null;

        checkNotNull(personaDTO, "Se esperaba dto de entrada");
        personaDTO.setId(null);

        if (this.existePersona(personaDTO)) {
            final Integer codOrganismo = personaDTO.getOrganismoTransito().getCodigoOrganismo();
            final Integer idTipoIdent = personaDTO.getTipoIdentificacion().getId();
            final String numeroId = personaDTO.getNumeroIdentificacion();
            PersonaEJB.logger.errorv(
                    "La persona a registrar ya existe [codigoOrganismo:{0}, idTipoIdentificacion:{1}, numeroIdentificacion:{2}]",
                    codOrganismo, idTipoIdent, numeroId);
            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008009);
        }
        personaDTO.setFechaUltimaActualizacion(new Date());
        if (personaDTO instanceof PersonaJuridicaDTO) {
            PersonaEJB.logger.debug("Registrando persona juridica");
            PersonaJuridicaDTO juridica = (PersonaJuridicaDTO) personaDTO;
            if (this.esPersonaJuridicaValida(juridica, !personaDTO.isValidarCamposMinimos())) {
                idRegistro = this.registrarPersonaJuridica(juridica, sinHistorico);
            }
        } else {
            PersonaEJB.logger.debug("Registrando persona natural");
            if (this.esPersonaNaturalValida(personaDTO, !personaDTO.isValidarCamposMinimos())) {
                this.validarConyugue(personaDTO, !personaDTO.isValidarCamposMinimos());
                idRegistro = this.registrarPersonaNatural(personaDTO, sinHistorico);
            }
        }

        return idRegistro;
    }

    @Override
    public boolean existePersona(int codigoOrganismo, int tipoIdentificacion, String numeroIdentificacion) {
        final TypedQuery<Long> quCount = this.em.createNamedQuery(Persona.SQ_COUNT_UNIQUE, Long.class);
        quCount.setParameter("codOrganismo", codigoOrganismo);
        quCount.setParameter("idTipoId", tipoIdentificacion);
        quCount.setParameter("numId", numeroIdentificacion);
        final Long count = quCount.getSingleResult();
        return count > 0;
    }

    /**
     * Indica si la persona indicada existe en el sistema
     * 
     * @param personaDTO
     *            la persona a consultar, por codigo de organismo, tipo identificacion y numero identificacion
     * 
     * @return true si la persona existe
     */
    private boolean existePersona(PersonaDTO personaDTO) {
        final Integer codOrganismo = checkNotNull(
                checkNotNull(personaDTO.getOrganismoTransito(), "Organismo de transito es requerido")
                        .getCodigoOrganismo(),
                "El codigo de organismo es requerido");

        final Integer idTipoIdent = checkNotNull(
                checkNotNull(personaDTO.getTipoIdentificacion(), "Tipo de identificacion es requerido").getId(),
                "El id tipo identificacion es requerido");

        final String numeroId = checkNotNull(personaDTO.getNumeroIdentificacion(),
                "El numero de identificacion es requerido");

        return existePersona(codOrganismo, idTipoIdent, numeroId);
    }

    /**
     * Retorna el conyugue de la personaDTO indicada con las modificaciones encontradas en el cónyugueDTO
     * 
     * @param personaDTO
     *            persona principal
     * 
     * @return Persona que es el conyugue de la personaDTO indicada
     */
    private Persona modificarConyugue(PersonaDTO conyugueDTO) {
        Persona conyugue;
        conyugueDTO.setFechaUltimaActualizacion(new Date());
        if (conyugueDTO.getId() != null) {
            conyugue = this.em.find(Persona.class, conyugueDTO.getId());
            // AdministrarPersonaHelper.compararCamposBasicosPersona(conyugue, conyugueDTO);
            PersonaHelperExtend.toLevel0Entity(conyugueDTO, conyugue);
            AdministrarPersonaHelper.compararCamposSegundoNivelPersona(conyugue, conyugueDTO, this.em);
        } else {
            conyugue = PersonaHelperExtend.toLevel1Entity(conyugueDTO, null);
        }

        return conyugue;
    }

    @Override
    public boolean modificarPersona(PersonaDTO personaDTO, DatosUbicabilidadDTO datosUbicabilidadDTO)
            throws CirculemosNegocioException {
        boolean respuesta;

        checkNotNull(personaDTO, "La persona a modificar no puede ser nula.");
        personaDTO.setFechaUltimaActualizacion(new Date());
        if (personaDTO instanceof PersonaJuridicaDTO) {
            respuesta = this.modificarPersonaJuridica((PersonaJuridicaDTO) personaDTO, true, datosUbicabilidadDTO);
        } else {
            respuesta = this.modificarPersonaNatural(personaDTO, true, datosUbicabilidadDTO);
        }

        return respuesta;
    }

    /**
     * Ingresa o actualiza el conyugue
     * 
     * @param personaDTO
     *            persona principal
     */
    private void persistirConyugue(PersonaDTO personaDTO) {
        Persona conyugue = null;
        PersonaHistorico personaHistorico;
        PersonaDTO conyugueDTO;

        if (!personaDTO.getParentescoPersonaList().isEmpty()) {
            conyugueDTO = personaDTO.getParentescoPersonaList().get(personaDTO.getParentescoPersonaList().size() - 1)
                    .getPariente();

            boolean conyugueNuevo = (conyugueDTO.getId() == null);
            if (conyugueNuevo) {
                conyugueDTO.setFechaUltimaActualizacion(new Date());
                for (DireccionPersonaDTO dirPersonaDTO : conyugueDTO.getDireccionPersonaList()) {
                    RespuestaIngresarDireccionDTO rtaDireccion = this.iLDireccion
                            .ingresarDireccion(dirPersonaDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACTOR);
                    dirPersonaDTO.getDireccion().setId(rtaDireccion.getIdDireccion());
                }

                conyugue = PersonaHelperExtend.toLevel1Entity(conyugueDTO, conyugue);
            } else {
                // El cónyugue ya existe
                conyugue = this.cargarDatosConyugueExistente(conyugueDTO);
            }

            personaHistorico = AdministrarPersonaHelper.adicionarHistorico(conyugueDTO, conyugue);
            if (conyugueNuevo) {
                this.em.persist(conyugue);
                for (ParentescoPersonaDTO parentescoPersonaDTO : personaDTO.getParentescoPersonaList()) {
                    if (parentescoPersonaDTO.getId() == null) {
                        parentescoPersonaDTO.getPariente().setId(conyugue.getId());
                    }

                }

            } else {
                this.em.merge(conyugue);
            }

            this.em.persist(personaHistorico);
        }

    }

    /**
     * Realiza el proceso de actualizacion de la persona natural indicada
     * 
     * @param personaDTO
     *            contiene la informacion de la persona natural a modificar
     * 
     * @return true si el proceso se realiza satisfactoriamente
     * 
     * @throws CirculemosNegocioException
     *             si la persona natural a ingresar tiene asociado un conyugue no valido
     */
    private boolean modificarPersonaNatural(PersonaDTO personaDTO, boolean validarCampos,
            DatosUbicabilidadDTO datosUbicabilidadDTO) throws CirculemosNegocioException {

        boolean modificada = false;

        // validación de campos de persona natural
        if (this.esPersonaNaturalValida(personaDTO, validarCampos)) {

            // consulta la persona por Id
            Persona persona = this.em.find(Persona.class, personaDTO.getId());

            // valida que la persona exista
            if (persona == null) {
                throw new CirculemosRuntimeException("No existe una persona natural con el id " + personaDTO.getId());
            }

            // valida que la persona todavia se encuentre viva
            if (persona.getFechaFallecimiento() != null) {
                throw new CirculemosRuntimeException("Esta persona no se puede modificar");
            }

            PersonaHelperExtend.toLevel1Entity(personaDTO, persona);
            AdministrarPersonaHelper.compararCamposSegundoNivelPersona(persona, personaDTO, this.em);
            Persona personaTemporal = PersonaHelperExtend.toLevel1Entity(personaDTO, null);

            // Se obtiene la fecha actual
            final Date fechaActual = UtilFecha.buildCalendar().getTime();

            List<CorreoPersonaDTO> correosNuevos = new ArrayList<>();
            List<CorreoPersonaDTO> correosActulizados = new ArrayList<>();
            List<Long> idCorreosNoActulizados = new ArrayList<>();

            // actualización de correos
            if (personaDTO.getCorreoPersonaList() != null) {

                // creación de correoPersona nuevos
                for (CorreoPersonaDTO correoPersonaDTO : personaDTO.getCorreoPersonaList()) {
                    if (correoPersonaDTO.getId() == null) {
                        this.guardarCorreoPersona(correoPersonaDTO, personaDTO, fechaActual);
                        correosNuevos.add(correoPersonaDTO);
                    }
                }

                // actualización de correoPersona existente
                correosActulizados = actualizarCorreos(personaDTO.getCorreoPersonaList(),
                        persona.getCorreoPersonaList(), fechaActual);

                // obtiene los id de correos que no fueron actulizados
                idCorreosNoActulizados = new ArrayList<>();

                // obtiene los ids de corros nuevos
                List<Long> idCorreosNuevos = new ArrayList<>();
                for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                    idCorreosNuevos.add(correoPersonaDTO.getId());
                }

                // obtiene los ids de correos actulizados
                List<Long> idCorreosActulizados = new ArrayList<>();
                for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                    idCorreosActulizados.add(correoPersonaDTO.getId());
                }

                // obtiene los ids de correos no actulizados
                for (CorreoPersonaDTO correoPersonaDTO : personaDTO.getCorreoPersonaList()) {
                    if (!(idCorreosNuevos.contains(correoPersonaDTO.getId())
                            || idCorreosActulizados.contains(correoPersonaDTO.getId()))) {
                        idCorreosNoActulizados.add(correoPersonaDTO.getId());
                    }
                }

            }

            List<TelefonoPersonaDTO> telefonosNuevos = new ArrayList<>();
            List<TelefonoPersonaDTO> telefonosActualizados = new ArrayList<>();
            List<Long> idTelefonosNoActulizados = new ArrayList<>();

            // actualización de telefonos
            if (personaDTO.getTelefonoPersonaDTOs() != null) {

                // creación de telefonoPersona nuevo
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaDTO.getTelefonoPersonaDTOs()) {
                    if (telefonoPersonaDTO.getId() == null) {
                        this.guardarTelefonoPersona(telefonoPersonaDTO, personaDTO, fechaActual);
                        telefonosNuevos.add(telefonoPersonaDTO);
                    }
                }

                // actualización de telefonoPersona existente
                telefonosActualizados = actualizarTelefonos(personaDTO.getTelefonoPersonaDTOs(),
                        persona.getTelefonoPersonaList(), fechaActual);

                // Obtiene los ids de telefonos nuevos
                List<Long> idTelefonosNuevos = new ArrayList<>();
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                    idTelefonosNuevos.add(telefonoPersonaDTO.getId());
                }

                // Obtiene los ids de telefonos actualizados
                List<Long> idTelefonosActualizados = new ArrayList<>();
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                    idTelefonosActualizados.add(telefonoPersonaDTO.getId());
                }

                // obtiene los ids de telefonos no actulizados
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaDTO.getTelefonoPersonaDTOs()) {
                    if (!(idTelefonosNuevos.contains(telefonoPersonaDTO.getId())
                            || idTelefonosActualizados.contains(telefonoPersonaDTO.getId()))) {
                        idTelefonosNoActulizados.add(telefonoPersonaDTO.getId());
                    }
                }
            }

            List<DireccionPersonaDTO> direccionesNuevas = new ArrayList<>();
            List<DireccionPersonaDTO> direccionesActualizadas = new ArrayList<>();
            List<Long> idDireccionesNoActualizadas = new ArrayList<>();

            // actualización de direcciones
            if (personaDTO.getDireccionPersonaList() != null) {

                // creación de direccionPersona nuevo
                for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
                    if (direccionPersonaDTO.getId() == null) {
                        this.guardarDireccionPersona(direccionPersonaDTO, personaDTO, fechaActual);
                        direccionesNuevas.add(direccionPersonaDTO);
                    }
                }

                // actualización de direccionPersona existente
                direccionesActualizadas = actualizarDireccionPersona(personaDTO.getDireccionPersonaList(),
                        persona.getDireccionPersonaList(), fechaActual);

                // obtiene los id de direccionPersona nuevas
                List<Long> idDireccionPersonaNuevos = new ArrayList<>();
                for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                    idDireccionPersonaNuevos.add(direccionPersonaDTO.getId());
                }

                // obtiene los id de direccionPersona actualizadas
                List<Long> idDireccionPersonaActualizados = new ArrayList<>();
                for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                    idDireccionPersonaActualizados.add(direccionPersonaDTO.getId());
                }

                for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
                    if (!(idDireccionPersonaNuevos.contains(direccionPersonaDTO.getId())
                            || idDireccionPersonaActualizados.contains(direccionPersonaDTO.getId()))) {
                        idDireccionesNoActualizadas.add(direccionPersonaDTO.getId());
                    }
                }
            }

            this.em.merge(persona);

            /*
             * generación de historíco
             * 
             */

            if (datosUbicabilidadDTO == null) {
                // Generación del historíco de persona
                PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaDTO, persona);

                /*
                 * generación de historico de correoPersona
                 */
                if (personaDTO.getCorreoPersonaList() != null && !personaDTO.getCorreoPersonaList().isEmpty()) {
                    personaHistorico.setCorreoPersonas(new ArrayList<CorreoPersonaHistorico>());

                    // genera historíco de correos nuevos
                    for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                        personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                    }

                    // genera historíco de correos actualizados
                    for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                        personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                    }

                    // consulta historico de los correos no actualizados
                    CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO = new CorreoPersonaHistoricoDTO();
                    correoPersonaHistoricoDTO.setCorreoPersona(new CorreoPersonaDTO());
                    for (Long idCorreos : idCorreosNoActulizados) {
                        correoPersonaHistoricoDTO.getCorreoPersona().setId(idCorreos);

                        // consulta historico de correo para el id de correo historico
                        List<CorreoPersonaHistoricoDTO> correoPersonaHistoricoDTOs = consultarCorreoPersonaHistorico(
                                correoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(correoPersonaHistoricoDTOs, new Comparator<CorreoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(CorreoPersonaHistoricoDTO o1, CorreoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!correoPersonaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getCorreoPersonas().add(CorreoPersonaHistoricoHelper.toLevel1Entity(
                                    correoPersonaHistoricoDTOs.get(correoPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                /*
                 * generación de historico de TelefonoPersona
                 * 
                 */

                if (personaDTO.getTelefonoPersonaDTOs() != null && !personaDTO.getTelefonoPersonaDTOs().isEmpty()) {

                    personaHistorico.setTelefonoPersonas(new ArrayList<TelefonoPersonaHistorico>());

                    // genera historíco de telefonos nuevos
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                        personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                    }

                    // genera historico de telefonos actualizados
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                        personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                    }

                    // consulta historico de los telefonos no actualizados
                    TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO = new TelefonoPersonaHistoricoDTO();
                    telefonoPersonaHistoricoDTO.setTelefonoPersona(new TelefonoPersonaDTO());

                    for (Long idTelefonos : idTelefonosNoActulizados) {
                        telefonoPersonaHistoricoDTO.getTelefonoPersona().setId(idTelefonos);

                        // consulta historico de telefono para el id de telefono historico
                        List<TelefonoPersonaHistoricoDTO> telefonoPersoaHistoricoDTOs = consultarTelefonoPersonaHistorico(
                                telefonoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(telefonoPersoaHistoricoDTOs, new Comparator<TelefonoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(TelefonoPersonaHistoricoDTO o1, TelefonoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!telefonoPersoaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getTelefonoPersonas().add(TelefonoPersonaHistoricoHelper.toLevel1Entity(
                                    telefonoPersoaHistoricoDTOs.get(telefonoPersoaHistoricoDTOs.size() - 1), null));
                        }

                    }

                }

                /*
                 * 
                 * generación de historico de DireccionPersona
                 * 
                 */
                if (personaDTO.getDireccionPersonaList() != null && !personaDTO.getDireccionPersonaList().isEmpty()) {

                    personaHistorico.setDireccionPersonas(new ArrayList<DireccionPersonaHistorico>());

                    // genera historíco de direccionPersona nueva
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                        personaHistorico.getDireccionPersonas()
                                .add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                    }

                    // genera historíco de direccionPersona actualizadas
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                        personaHistorico.getDireccionPersonas()
                                .add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                    }

                    // cosulta historico de las direcciones no actualizadas
                    DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = new DireccionPersonaHistoricoDTO();
                    direccionPersonaHistoricoDTO.setDireccionPersona(new DireccionPersonaDTO());

                    for (Long idDireccionPersonas : idDireccionesNoActualizadas) {
                        direccionPersonaHistoricoDTO.getDireccionPersona().setId(idDireccionPersonas);

                        // consulta historico de dirección para el id de dirección historico
                        List<DireccionPersonaHistoricoDTO> direccionPersonaHistoricoDTOs = consultarDireccionPersonaHistorico(
                                direccionPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(direccionPersonaHistoricoDTOs, new Comparator<DireccionPersonaHistoricoDTO>() {

                            @Override
                            public int compare(DireccionPersonaHistoricoDTO o1, DireccionPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!direccionPersonaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getDireccionPersonas().add(DireccionPersonaHistoricoHelper.toLevel1Entity(
                                    direccionPersonaHistoricoDTOs.get(direccionPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                personaHistorico.setUsuarioRegistro(
                        UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
                em.persist(personaHistorico);

            } else {

                if (!correosActulizados.isEmpty()) {
                    for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                        datosUbicabilidadDTO.getIdCorreoPersonas().add(correoPersonaDTO.getId());
                    }
                }

                if (!correosNuevos.isEmpty()) {
                    for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                        datosUbicabilidadDTO.getIdCorreoPersonas().add(correoPersonaDTO.getId());
                    }
                }

                if (!idCorreosNoActulizados.isEmpty()) {
                    for (Long idCorreo : idCorreosNoActulizados) {
                        datosUbicabilidadDTO.getIdCorreoPersonasNoActualizadas().add(idCorreo);
                    }
                }

                if (!telefonosActualizados.isEmpty()) {
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                        datosUbicabilidadDTO.getIdTelefonoPersonas().add(telefonoPersonaDTO.getId());
                    }
                }

                if (!telefonosNuevos.isEmpty()) {
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                        datosUbicabilidadDTO.getIdTelefonoPersonas().add(telefonoPersonaDTO.getId());
                    }
                }

                if (!idTelefonosNoActulizados.isEmpty()) {
                    for (Long idTelefono : idTelefonosNoActulizados) {
                        datosUbicabilidadDTO.getIdTelefonoPersonasNoActualizadas().add(idTelefono);
                    }
                }

                if (!direccionesActualizadas.isEmpty()) {
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                        datosUbicabilidadDTO.getIdDireccionPersonas().add(direccionPersonaDTO.getId());
                    }
                }

                if (!direccionesNuevas.isEmpty()) {
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                        datosUbicabilidadDTO.getIdDireccionPersonas().add(direccionPersonaDTO.getId());
                    }
                }

                if (!idDireccionesNoActualizadas.isEmpty()) {
                    for (Long idDireccionPersona : idDireccionesNoActualizadas) {
                        datosUbicabilidadDTO.getIdDireccionPersonasNoActualizadas().add(idDireccionPersona);
                    }
                }
            }

            modificada = true;
        }

        return modificada;
    }

    /**
     * Realiza el proceso de actualizacion de la persona Juridica indicada
     * 
     * @param personaJuridicaDTO
     *            contiene la informacion de la persona Juridica a modificar
     * 
     * @return true si el proceso se realiza satisfactoriamente
     * 
     * @throws CirculemosNegocioException
     *             si la persona Juridica a ingresar no es valida
     */
    private boolean modificarPersonaJuridica(PersonaJuridicaDTO personaJuridicaDTO, boolean validarCampos,
            DatosUbicabilidadDTO datosUbicabilidadDTO) throws CirculemosNegocioException {
        boolean modificada = false;

        if (this.esPersonaJuridicaValida(personaJuridicaDTO, validarCampos)) {
            PersonaJuridica persona = this.em.find(PersonaJuridica.class, personaJuridicaDTO.getId());

            // Si la persona no existe termina la ejecución
            if (persona == null) {
                throw new CirculemosRuntimeException(
                        "No existe una persona juridica con el id " + personaJuridicaDTO.getId());
            }

            // Se obtiene la fecha actual
            final Date fechaActual = UtilFecha.buildCalendar().getTime();

            // Comparo el DTO que recibo como parametro contra la persona en la base de datos y seteo los valores del dto a la entidad
            PersonaJuridicaHelperExtend.toLevel0EntityExtend(personaJuridicaDTO, persona);
            persona.getPersona().setFechaUltimaActualizacion(fechaActual);
            // Comparo el DTO que recibo como parametro contra la persona en la base de datos y seteo los valores del dto a la entidad
            UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
            AdministrarPersonaHelper.compararCamposSegundoNivelPersonaJuridica(persona, personaJuridicaDTO, this.em,
                    usuarioPersonaDTO);

            List<CorreoPersonaDTO> correosNuevos = new ArrayList<>();
            List<CorreoPersonaDTO> correosActulizados = new ArrayList<>();
            List<Long> idCorreosNoActulizados = new ArrayList<>();

            // actualización de correos
            if (personaJuridicaDTO.getCorreoPersonaList() != null) {

                // creación de correoPersona nuevos
                for (CorreoPersonaDTO correoPersonaDTO : personaJuridicaDTO.getCorreoPersonaList()) {
                    if (correoPersonaDTO.getId() == null) {
                        this.guardarCorreoPersona(correoPersonaDTO, personaJuridicaDTO, fechaActual);
                        correosNuevos.add(correoPersonaDTO);
                    }
                }

                // actualización de correoPersona existente
                correosActulizados = actualizarCorreos(personaJuridicaDTO.getCorreoPersonaList(),
                        persona.getPersona().getCorreoPersonaList(), fechaActual);

                // obtiene los id de correos que no fueron actulizados
                idCorreosNoActulizados = new ArrayList<>();

                // obtiene los ids de corros nuevos
                List<Long> idCorreosNuevos = new ArrayList<>();
                for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                    idCorreosNuevos.add(correoPersonaDTO.getId());
                }

                // obtiene los ids de correos actulizados
                List<Long> idCorreosActulizados = new ArrayList<>();
                for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                    idCorreosActulizados.add(correoPersonaDTO.getId());
                }

                // obtiene los ids de correos no actulizados
                for (CorreoPersonaDTO correoPersonaDTO : personaJuridicaDTO.getCorreoPersonaList()) {
                    if (!(idCorreosNuevos.contains(correoPersonaDTO.getId())
                            || idCorreosActulizados.contains(correoPersonaDTO.getId()))) {
                        idCorreosNoActulizados.add(correoPersonaDTO.getId());
                    }
                }

            }

            List<TelefonoPersonaDTO> telefonosNuevos = new ArrayList<>();
            List<TelefonoPersonaDTO> telefonosActualizados = new ArrayList<>();
            List<Long> idTelefonosNoActulizados = new ArrayList<>();

            // actualización de telefonos
            if (personaJuridicaDTO.getTelefonoPersonaDTOs() != null) {

                // creación de telefonoPersona nuevo
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaJuridicaDTO.getTelefonoPersonaDTOs()) {
                    if (telefonoPersonaDTO.getId() == null) {
                        this.guardarTelefonoPersona(telefonoPersonaDTO, personaJuridicaDTO, fechaActual);
                        telefonosNuevos.add(telefonoPersonaDTO);
                    }
                }

                // actualización de telefonoPersona existente
                telefonosActualizados = actualizarTelefonos(personaJuridicaDTO.getTelefonoPersonaDTOs(),
                        persona.getPersona().getTelefonoPersonaList(), fechaActual);

                // Obtiene los ids de telefonos nuevos
                List<Long> idTelefonosNuevos = new ArrayList<>();
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                    idTelefonosNuevos.add(telefonoPersonaDTO.getId());
                }

                // Obtiene los ids de telefonos actualizados
                List<Long> idTelefonosActualizados = new ArrayList<>();
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                    idTelefonosActualizados.add(telefonoPersonaDTO.getId());
                }

                // obtiene los ids de telefonos no actulizados
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaJuridicaDTO.getTelefonoPersonaDTOs()) {
                    if (!(idTelefonosNuevos.contains(telefonoPersonaDTO.getId())
                            || idTelefonosActualizados.contains(telefonoPersonaDTO.getId()))) {
                        idTelefonosNoActulizados.add(telefonoPersonaDTO.getId());
                    }
                }
            }

            List<DireccionPersonaDTO> direccionesNuevas = new ArrayList<>();
            List<DireccionPersonaDTO> direccionesActualizadas = new ArrayList<>();
            List<Long> idDireccionesNoActualizadas = new ArrayList<>();

            // actualización de direcciones
            if (personaJuridicaDTO.getDireccionPersonaList() != null) {

                // creación de direccionPersona nuevo
                for (DireccionPersonaDTO direccionPersonaDTO : personaJuridicaDTO.getDireccionPersonaList()) {
                    if (direccionPersonaDTO.getId() == null) {
                        this.guardarDireccionPersona(direccionPersonaDTO, personaJuridicaDTO, fechaActual);
                        direccionesNuevas.add(direccionPersonaDTO);
                    }
                }

                // actualización de direccionPersona existente
                direccionesActualizadas = actualizarDireccionPersona(personaJuridicaDTO.getDireccionPersonaList(),
                        persona.getPersona().getDireccionPersonaList(), fechaActual);

                // obtiene los id de direccionPersona nuevas
                List<Long> idDireccionPersonaNuevos = new ArrayList<>();
                for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                    idDireccionPersonaNuevos.add(direccionPersonaDTO.getId());
                }

                // obtiene los id de direccionPersona actualizadas
                List<Long> idDireccionPersonaActualizados = new ArrayList<>();
                for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                    idDireccionPersonaActualizados.add(direccionPersonaDTO.getId());
                }

                for (DireccionPersonaDTO direccionPersonaDTO : personaJuridicaDTO.getDireccionPersonaList()) {
                    if (!(idDireccionPersonaNuevos.contains(direccionPersonaDTO.getId())
                            || idDireccionPersonaActualizados.contains(direccionPersonaDTO.getId()))) {
                        idDireccionesNoActualizadas.add(direccionPersonaDTO.getId());
                    }
                }
            }

            this.em.merge(persona);
            // Si viene parametro no se genera historico

            /*
             * generación de historíco
             * 
             */

            if (datosUbicabilidadDTO == null) {

                // Generación del historíco de persona
                PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaJuridicaDTO,
                        persona.getPersona());

                /*
                 * generación de historico de correoPersona
                 */
                if (personaJuridicaDTO.getCorreoPersonaList() != null
                        && !personaJuridicaDTO.getCorreoPersonaList().isEmpty()) {
                    personaHistorico.setCorreoPersonas(new ArrayList<CorreoPersonaHistorico>());

                    // genera historíco de correos nuevos
                    for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                        personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                    }

                    // genera historíco de correos actualizados
                    for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                        personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                    }

                    // consulta historico de los correos no actualizados
                    CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO = new CorreoPersonaHistoricoDTO();
                    correoPersonaHistoricoDTO.setCorreoPersona(new CorreoPersonaDTO());
                    for (Long idCorreos : idCorreosNoActulizados) {
                        correoPersonaHistoricoDTO.getCorreoPersona().setId(idCorreos);

                        // consulta historico de correo para el id de correo historico
                        List<CorreoPersonaHistoricoDTO> correoPersonaHistoricoDTOs = consultarCorreoPersonaHistorico(
                                correoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(correoPersonaHistoricoDTOs, new Comparator<CorreoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(CorreoPersonaHistoricoDTO o1, CorreoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!correoPersonaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getCorreoPersonas().add(CorreoPersonaHistoricoHelper.toLevel1Entity(
                                    correoPersonaHistoricoDTOs.get(correoPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                /*
                 * generación de historico de TelefonoPersona
                 * 
                 */

                if (personaJuridicaDTO.getTelefonoPersonaDTOs() != null
                        && !personaJuridicaDTO.getTelefonoPersonaDTOs().isEmpty()) {

                    personaHistorico.setTelefonoPersonas(new ArrayList<TelefonoPersonaHistorico>());

                    // genera historíco de telefonos nuevos
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                        personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                    }

                    // genera historico de telefonos actualizados
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                        personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                    }

                    // consulta historico de los telefonos no actualizados
                    TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO = new TelefonoPersonaHistoricoDTO();
                    telefonoPersonaHistoricoDTO.setTelefonoPersona(new TelefonoPersonaDTO());

                    for (Long idTelefonos : idTelefonosNoActulizados) {
                        telefonoPersonaHistoricoDTO.getTelefonoPersona().setId(idTelefonos);

                        // consulta historico de telefono para el id de telefono historico
                        List<TelefonoPersonaHistoricoDTO> telefonoPersoaHistoricoDTOs = consultarTelefonoPersonaHistorico(
                                telefonoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(telefonoPersoaHistoricoDTOs, new Comparator<TelefonoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(TelefonoPersonaHistoricoDTO o1, TelefonoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!telefonoPersoaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getTelefonoPersonas().add(TelefonoPersonaHistoricoHelper.toLevel1Entity(
                                    telefonoPersoaHistoricoDTOs.get(telefonoPersoaHistoricoDTOs.size() - 1), null));
                        }
                    }

                }

                /*
                 * 
                 * generación de historico de DireccionPersona
                 * 
                 */
                if (personaJuridicaDTO.getDireccionPersonaList() != null
                        && !personaJuridicaDTO.getDireccionPersonaList().isEmpty()) {

                    personaHistorico.setDireccionPersonas(new ArrayList<DireccionPersonaHistorico>());

                    // genera historíco de direccionPersona nueva
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                        personaHistorico.getDireccionPersonas()
                                .add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                    }

                    // genera historíco de direccionPersona actualizadas
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                        personaHistorico.getDireccionPersonas()
                                .add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                    }

                    // cosulta historico de las direcciones no actualizadas
                    DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = new DireccionPersonaHistoricoDTO();
                    direccionPersonaHistoricoDTO.setDireccionPersona(new DireccionPersonaDTO());

                    for (Long idDireccionPersonas : idDireccionesNoActualizadas) {
                        direccionPersonaHistoricoDTO.getDireccionPersona().setId(idDireccionPersonas);

                        // consulta historico de dirección para el id de dirección historico
                        List<DireccionPersonaHistoricoDTO> direccionPersonaHistoricoDTOs = consultarDireccionPersonaHistorico(
                                direccionPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(direccionPersonaHistoricoDTOs, new Comparator<DireccionPersonaHistoricoDTO>() {

                            @Override
                            public int compare(DireccionPersonaHistoricoDTO o1, DireccionPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!direccionPersonaHistoricoDTOs.isEmpty()) {
                            personaHistorico.getDireccionPersonas().add(DireccionPersonaHistoricoHelper.toLevel1Entity(
                                    direccionPersonaHistoricoDTOs.get(direccionPersonaHistoricoDTOs.size() - 1), null));
                        }
                    }
                }

                personaHistorico.setUsuarioRegistro(
                        UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
                em.persist(personaHistorico);
            } else {

                if (!correosActulizados.isEmpty()) {
                    for (CorreoPersonaDTO correoPersonaDTO : correosActulizados) {
                        datosUbicabilidadDTO.getIdCorreoPersonas().add(correoPersonaDTO.getId());
                    }
                }

                if (!correosNuevos.isEmpty()) {
                    for (CorreoPersonaDTO correoPersonaDTO : correosNuevos) {
                        datosUbicabilidadDTO.getIdCorreoPersonas().add(correoPersonaDTO.getId());
                    }
                }

                if (!idCorreosNoActulizados.isEmpty()) {
                    for (Long idCorreo : idCorreosNoActulizados) {
                        datosUbicabilidadDTO.getIdCorreoPersonasNoActualizadas().add(idCorreo);
                    }
                }

                if (!telefonosActualizados.isEmpty()) {
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosActualizados) {
                        datosUbicabilidadDTO.getIdTelefonoPersonas().add(telefonoPersonaDTO.getId());
                    }
                }

                if (!telefonosNuevos.isEmpty()) {
                    for (TelefonoPersonaDTO telefonoPersonaDTO : telefonosNuevos) {
                        datosUbicabilidadDTO.getIdTelefonoPersonas().add(telefonoPersonaDTO.getId());
                    }
                }

                if (!idTelefonosNoActulizados.isEmpty()) {
                    for (Long idTelefono : idTelefonosNoActulizados) {
                        datosUbicabilidadDTO.getIdTelefonoPersonasNoActualizadas().add(idTelefono);
                    }
                }

                if (!direccionesActualizadas.isEmpty()) {
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesActualizadas) {
                        datosUbicabilidadDTO.getIdDireccionPersonas().add(direccionPersonaDTO.getId());
                    }
                }

                if (!direccionesNuevas.isEmpty()) {
                    for (DireccionPersonaDTO direccionPersonaDTO : direccionesNuevas) {
                        datosUbicabilidadDTO.getIdDireccionPersonas().add(direccionPersonaDTO.getId());
                    }
                }

                if (!idDireccionesNoActualizadas.isEmpty()) {
                    for (Long idDireccionPersona : idDireccionesNoActualizadas) {
                        datosUbicabilidadDTO.getIdDireccionPersonasNoActualizadas().add(idDireccionPersona);
                    }
                }
            }

            modificada = true;
        }
        return modificada;
    }

    /**
     * Retorna el id del tipo de identificación para la persona Jurídico
     * 
     * @param codigoOrganismo
     *            código del organismo para el país al cual se cargarán los tipos de documentos
     * @return id del tipo de documento jurídico
     */
    private int cargueIdTipoIdentificacionJuridica(int codigoOrganismo) {
        TypedQuery<Pais> quPais = em.createNamedQuery(OrganismoTransito.SQ_FIND_PAIS_ORG, Pais.class);
        quPais.setParameter("codigoOrganismo", codigoOrganismo);
        final Pais pais = quPais.getSingleResult();

        final TipoIdentificacionPersonaDTO tipoIdJuridica = this.iLAdministracion
                .obtenerTipoIdentificacionEmpresaJuridicaPais(pais.getId());

        return tipoIdJuridica.getId();
    }

    /**
     * Retorna true si la persona jurídica indicada cumple con las condiciones del negocio, de lo contrario lanza PersonaException con el mensaje de
     * la regla incumplida
     * 
     * @param personaJuridicaDTO
     *            contiene la información para validar las reglas
     * 
     * @param validarCampos
     *            indica si se deben validar los campos no basicos de la persona juridica
     * 
     * @return true si es válida
     * 
     * @throws CirculemosNegocioException
     *             si alguna regla no se cumple
     */
    private boolean esPersonaJuridicaValida(PersonaJuridicaDTO personaJuridicaDTO, boolean validarCampos)
            throws CirculemosNegocioException {

        if (AdministrarPersonaHelper.esPersonaConCamposBasicosValidos(personaJuridicaDTO, this.em)) {
            personaJuridicaDTO.setNumeroIdentificacion(personaJuridicaDTO.getNumeroIdentificacion().trim());
            if (!iLAdministracion.validarNumeroDocumento(personaJuridicaDTO.getNumeroIdentificacion(),
                    personaJuridicaDTO.getTipoIdentificacion().getId(), UtilFecha.buildCalendar().getTime())) {
                throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008015);
            }
            if (validarCampos) {

                if (AdministrarPersonaHelper.esPersonaJuridicaValida(personaJuridicaDTO, this.em)) {

                    // valida DireccionPersona
                    AdministrarPersonaHelper.validarDireccionPersona(personaJuridicaDTO.getDireccionPersonaList(), em);

                    for (DireccionPersonaDTO dirPersona : personaJuridicaDTO.getDireccionPersonaList()) {
                        if (dirPersona.getDireccion().getId() == null) {
                            try {
                                this.validarDireccion(dirPersona.getDireccion(), "Dirección residencia");
                            } catch (CirculemosRuntimeException cre) {
                                throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008002);
                            }
                        }
                    }

                    /* Representante legal */
                    if (!personaJuridicaDTO.getRepresentanteLegalList().isEmpty()) {
                        // Se busca el representante legal actual
                        RepresentanteLegalDTO representanteActual = personaJuridicaDTO.getRepresentanteLegalList()
                                .get(personaJuridicaDTO.getRepresentanteLegalList().size() - 1);
                        // Se valida la consistencia de la información del representante legal actual
                        if (representanteActual.getId() == null
                                || this.em.find(Persona.class, representanteActual.getId()) == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008016);
                        }
                        // Se valida que el representante tenga al menos fecha de inicio vigencia
                        if (representanteActual.getFechaInicio() == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015054);
                        }

                        OrganismoTransito org = (OrganismoTransito) this.em.find(OrganismoTransito.class,
                                personaJuridicaDTO.getOrganismoTransito().getCodigoOrganismo());
                        // El representante legal no puede tener tipo de id empresarial
                        int idTipoIdentJuri = this.cargueIdTipoIdentificacionJuridica(org.getCodigoOrganismo());
                        if (representanteActual.getTipoIdentificacion().getId() == idTipoIdentJuri) {
                            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008005);
                        }
                    }

                    // validación de correos
                    AdministrarPersonaHelper.validarCorreos(personaJuridicaDTO.getCorreoPersonaList(), em);

                    // validación de teléfonos
                    AdministrarPersonaHelper.validarTelefonos(personaJuridicaDTO.getTelefonoPersonaDTOs(), em);

                }

            }

        }

        return true;
    }

    /**
     * Retorna true si la persona indicada cumple con las condiciones del negocio, de lo contrario lanza PersonaException con el mensaje de la regla
     * incumplida
     * 
     * @param personaDTO
     *            contiene la información para validar las reglas
     * @param valideCampos
     *            indica si se deben validar camos adicionales a los básicos
     * @return true si es válida
     * @throws CirculemosNegocioException
     *             si hay errores validando la dirección de la persona
     * 
     */
    private boolean esPersonaNaturalValida(PersonaDTO personaDTO, boolean valideCampos)
            throws CirculemosNegocioException {

        if (AdministrarPersonaHelper.esPersonaConCamposBasicosValidos(personaDTO, this.em)) {
            personaDTO.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion().trim());
            if (!iLAdministracion.validarNumeroDocumento(personaDTO.getNumeroIdentificacion(),
                    personaDTO.getTipoIdentificacion().getId(), UtilFecha.buildCalendar().getTime())) {
                throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008015);
            }
            if (valideCampos) {
                if (AdministrarPersonaHelper.esPersonaNaturalConCamposNoBasicosValidos(personaDTO, this.em)) {

                    // validación DireccionPersona
                    AdministrarPersonaHelper.validarDireccionPersona(personaDTO.getDireccionPersonaList(), em);

                    for (DireccionPersonaDTO dirPersona : personaDTO.getDireccionPersonaList()) {
                        if (dirPersona.getDireccion().getId() == null) {
                            try {
                                this.validarDireccion(dirPersona.getDireccion(), "Dirección residencia");
                            } catch (CirculemosRuntimeException cre) {
                                throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008003);

                            }
                        }

                    }

                    // validación correos
                    AdministrarPersonaHelper.validarCorreos(personaDTO.getCorreoPersonaList(), em);

                    // validación telefonos
                    AdministrarPersonaHelper.validarTelefonos(personaDTO.getTelefonoPersonaDTOs(), em);

                }

            }

        }

        return true;
    }

    /**
     * Valida la direccion indicada, si no es valida, lanza runtime exception
     * 
     * @param direccion
     *            direccion a validar
     * 
     * @param nombreDireccion
     *            nombre de la direccion a validar para asociarla al mensaje de error en caso de no ser valida
     */
    private void validarDireccion(DireccionDTO direccion, String nombreDireccion) {
        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> respuesta;
        StringBuilder mensaje = new StringBuilder("");
        respuesta = this.iLDireccion.validarDireccion(direccion, EnumTipoValidacionDireccion.INFRACTOR);

        if (respuesta.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
            final Set<EnumCampoDireccion> campos = respuesta.getErroresCampo().keySet();
            for (EnumCampoDireccion enumCampo : campos) {
                final List<EnumErrorDireccion> errores = respuesta.getErroresCampo().get(enumCampo);
                mensaje.append(enumCampo + " {");
                for (EnumErrorDireccion enumError : errores) {
                    mensaje.append(enumError + ", ");
                }
                mensaje.append(" }\n");
            }

        }

        if (!mensaje.toString().trim().isEmpty()) {
            throw new CirculemosRuntimeException(nombreDireccion + " inválida " + mensaje);
        }
    }

    /**
     * Registra en el sistema la persona juridica indicada
     * 
     * @param personaJuridicaDTO
     *            la persona juridica a registrar
     * @return id de la persona registrada
     */
    private Long registrarPersonaJuridica(PersonaJuridicaDTO personaJuridicaDTO, Boolean... sinHistorico) {

        PersonaJuridica personaJuridica = PersonaJuridicaHelperExtend.toLevel1Entity(personaJuridicaDTO, null);

        // Se obtiene la fecha actual
        final Date fechaActual = UtilFecha.buildCalendar().getTime();

        /* Representante legal */
        final List<RepresentanteLegalDTO> representanteLegalList = personaJuridicaDTO.getRepresentanteLegalList();
        if (representanteLegalList != null) {
            List<RepresentanteLegal> lista = new ArrayList<RepresentanteLegal>(representanteLegalList.size());
            for (RepresentanteLegalDTO rlDTO : representanteLegalList) {
                Persona persona = this.em.find(Persona.class, rlDTO.getId());
                RepresentanteLegal representanteLegal = RepresentanteLegalHelper.toLevel0Entity(rlDTO, null);
                representanteLegal.setPersona(persona);
                representanteLegal.setPersonaJuridica(personaJuridica);
                representanteLegal.setFechaRegistro(fechaActual);
                representanteLegal.setUsuarioRegistro(
                        UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
                lista.add(representanteLegal);
            }

            personaJuridica.setRepresentanteLegalList(lista);
        }

        // Persistencia de la persona
        this.em.persist(personaJuridica.getPersona());
        personaJuridica.setIdPersonaJuridica(personaJuridica.getPersona().getId());

        // Persistencia de la persona juridica
        this.em.persist(personaJuridica);

        // Se obtiene el PersonaDTO
        PersonaDTO personaDTO = PersonaHelper.toLevel1DTO(personaJuridica.getPersona());

        // Persistencia de correo
        List<CorreoPersona> correoPersonas = new ArrayList<>();
        if (personaJuridicaDTO.getCorreoPersonaList() != null && !personaJuridicaDTO.getCorreoPersonaList().isEmpty()) {
            for (CorreoPersonaDTO correoPersonaDTO : personaJuridicaDTO.getCorreoPersonaList()) {
                guardarCorreoPersona(correoPersonaDTO, personaDTO, fechaActual);
            }
        }

        // Persistencia de teléfono
        List<TelefonoPersona> telefonoPersonas = new ArrayList<>();
        if (personaJuridicaDTO.getTelefonoPersonaDTOs() != null
                && !personaJuridicaDTO.getTelefonoPersonaDTOs().isEmpty()) {
            for (TelefonoPersonaDTO telefonoPersonaDTO : personaJuridicaDTO.getTelefonoPersonaDTOs()) {
                guardarTelefonoPersona(telefonoPersonaDTO, personaDTO, fechaActual);
            }
        }

        // Persistencia de la dirección
        List<DireccionPersona> direccionPersonas = new ArrayList<>();
        if (personaJuridicaDTO.getDireccionPersonaList() != null) {
            for (DireccionPersonaDTO direccionPersonaDTO : personaJuridicaDTO.getDireccionPersonaList()) {
                guardarDireccionPersona(direccionPersonaDTO, personaDTO, fechaActual);
            }
        }

        if (sinHistorico.length == 0) {
            // Generación del historíco de persona
            PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaJuridicaDTO,
                    personaJuridica.getPersona());

            // Persistencia del historíco de CorreoPersona
            if (personaJuridicaDTO.getCorreoPersonaList() != null) {
                personaHistorico.setCorreoPersonas(new ArrayList<CorreoPersonaHistorico>());
                for (CorreoPersonaDTO correoPersonaDTO : personaJuridicaDTO.getCorreoPersonaList()) {
                    personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                }
            }

            // Persistencia del historíco de TelefonoPersona
            if (personaJuridicaDTO.getTelefonoPersonaDTOs() != null) {
                personaHistorico.setTelefonoPersonas(new ArrayList<TelefonoPersonaHistorico>());
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaJuridicaDTO.getTelefonoPersonaDTOs()) {
                    personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                }
            }

            // Persistencia del historico de DireccionPersona
            if (personaJuridicaDTO.getDireccionPersonaList() != null) {
                personaHistorico.setDireccionPersonas(new ArrayList<DireccionPersonaHistorico>());
                for (DireccionPersonaDTO direccionPersonaDTO : personaJuridicaDTO.getDireccionPersonaList()) {
                    personaHistorico.getDireccionPersonas().add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                }
            }

            personaHistorico.setUsuarioRegistro(
                    UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
            // Persistencia de historíco de persona
            em.persist(personaHistorico);
        }

        return personaJuridica.getIdPersonaJuridica();
    }

    /**
     * Retorna la entidad correspondiente al conyugue indicado listo para ser actualizado en base de datos
     * 
     * @param conyugueDTO
     *            contiene la información del conyugue que sera retornado como entidad
     * 
     * @return Entidad Persona con la informacion del conyugue indicado
     */
    private Persona cargarDatosConyugueExistente(PersonaDTO conyugueDTO) {
        Persona conyugue;
        // cierro direcciones del DTO
        if (AdministrarPersonaHelper.contieneDireccionNueva(EnumTipoUbicabilidad.RESIDENCIA,
                conyugueDTO.getDireccionPersonaList())) {
            AdministrarPersonaHelper.cierreUltimaFecha(EnumTipoUbicabilidad.RESIDENCIA,
                    conyugueDTO.getDireccionPersonaList());
        }

        if (AdministrarPersonaHelper.contieneDireccionNueva(EnumTipoUbicabilidad.EMPRESA,
                conyugueDTO.getDireccionPersonaList())) {
            AdministrarPersonaHelper.cierreUltimaFecha(EnumTipoUbicabilidad.EMPRESA,
                    conyugueDTO.getDireccionPersonaList());
        }

        // inserto direcciones nuevas y asocio su id al DTO
        for (DireccionPersonaDTO dirPersonaDTO : conyugueDTO.getDireccionPersonaList()) {
            if (dirPersonaDTO.getDireccion().getId() == null) {
                RespuestaIngresarDireccionDTO rtaDireccion = this.iLDireccion
                        .ingresarDireccion(dirPersonaDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACTOR);
                dirPersonaDTO.getDireccion().setId(rtaDireccion.getIdDireccion());
            }

        }

        conyugue = this.modificarConyugue(conyugueDTO);
        Persona conyugueTemporal = PersonaHelperExtend.toLevel1Entity(conyugueDTO, null);
        // cierro direcciones de la entidad
        for (DireccionPersona direccionPersona : conyugue.getDireccionPersonaList()) {
            for (DireccionPersona direccionPersonaTemp : conyugueTemporal.getDireccionPersonaList()) {
                if (direccionPersona.getId().equals(direccionPersonaTemp.getId())) {
                    if ((direccionPersona.getFechaActualizacion() == null)
                            && (direccionPersonaTemp.getFechaActualizacion() != null)) {
                        direccionPersona.setFechaActualizacion(direccionPersonaTemp.getFechaActualizacion());
                    }
                    break;
                }

            }

        }

        // asocio direcciones nuevas a la entidad
        for (DireccionPersona direccionPersona : conyugueTemporal.getDireccionPersonaList()) {
            if (direccionPersona.getId() == null) {
                conyugue.getDireccionPersonaList().add(direccionPersona);
            }

        }

        return conyugue;
    }

    /**
     * Registra la persona natural indicada
     * 
     * @param personaDTO
     *            la persona a ingresar
     * 
     * @return id de la persona ingresada
     */
    private Long registrarPersonaNatural(PersonaDTO personaDTO, Boolean... sinHistorico) {

        // TO-DO revisar si persiste conyugue
        if (!personaDTO.getParentescoPersonaList().isEmpty()) {
            // Tiene conyugue
            this.persistirConyugue(personaDTO);
        }

        Persona persona = PersonaHelperExtend.toLevel1Entity(personaDTO, null);
        this.em.persist(persona);

        // Se setea el id de persona a personaDTO
        personaDTO.setId(persona.getId());

        // Se obtiene la fecha actual
        final Date fechaActual = UtilFecha.buildCalendar().getTime();

        // Persistencia de correo
        if (personaDTO.getCorreoPersonaList() != null && !personaDTO.getCorreoPersonaList().isEmpty()) {
            for (CorreoPersonaDTO correoPersonaDTO : personaDTO.getCorreoPersonaList()) {
                guardarCorreoPersona(correoPersonaDTO, personaDTO, fechaActual);
            }
        }

        // Persistencia de teléfono
        if (personaDTO.getTelefonoPersonaDTOs() != null && !personaDTO.getTelefonoPersonaDTOs().isEmpty()) {
            for (TelefonoPersonaDTO telefonoPersonaDTO : personaDTO.getTelefonoPersonaDTOs()) {
                guardarTelefonoPersona(telefonoPersonaDTO, personaDTO, fechaActual);
            }
        }

        // Persistencia de la dirección
        if (personaDTO.getDireccionPersonaList() != null) {
            for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
                guardarDireccionPersona(direccionPersonaDTO, personaDTO, fechaActual);
            }
        }

        if (sinHistorico.length == 0) {
            // Generación del historíco de persona
            PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaDTO, persona);

            // Persistencia del historíco de CorreoPersona
            if (personaDTO.getCorreoPersonaList() != null) {
                personaHistorico.setCorreoPersonas(new ArrayList<CorreoPersonaHistorico>());
                for (CorreoPersonaDTO correoPersonaDTO : personaDTO.getCorreoPersonaList()) {
                    personaHistorico.getCorreoPersonas().add(guardarCorreoPersonaHistorico(correoPersonaDTO));
                }
            }

            // Persistencia del historíco de TelefonoPersona
            if (personaDTO.getTelefonoPersonaDTOs() != null) {
                personaHistorico.setTelefonoPersonas(new ArrayList<TelefonoPersonaHistorico>());
                for (TelefonoPersonaDTO telefonoPersonaDTO : personaDTO.getTelefonoPersonaDTOs()) {
                    personaHistorico.getTelefonoPersonas().add(guardarTelefonoPersonaHistorico(telefonoPersonaDTO));
                }
            }

            // Persistencia del historico de DireccionPersona
            if (personaDTO.getDireccionPersonaList() != null) {
                personaHistorico.setDireccionPersonas(new ArrayList<DireccionPersonaHistorico>());
                for (DireccionPersonaDTO direccionPersonaDTO : personaDTO.getDireccionPersonaList()) {
                    personaHistorico.getDireccionPersonas().add(guardarDireccionPersonaHistorico(direccionPersonaDTO));
                }
            }

            // Persistencia de historíco de persona
            personaHistorico.setUsuarioRegistro(
                    UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
            em.persist(personaHistorico);
        }

        return persona.getId();
    }

    /**
     * Valida la información del cónyugue (si lo tiene)
     * 
     * @param personaDTO
     *            persona de la cual se validará la información del conyugue
     * @param validarCampos
     *            indica si se deben validar los campos del conyugue
     * 
     * @throws CirculemosNegocioException
     *             si el cónyugue no es válido
     */
    private void validarConyugue(PersonaDTO personaDTO, boolean validarCampos) throws CirculemosNegocioException {
        PersonaEJB.logger.debug("PersonaEJB::valideConyugue ");

        if (!personaDTO.getParentescoPersonaList().isEmpty()) {
            for (ParentescoPersonaDTO parentescoPersonaDTO : personaDTO.getParentescoPersonaList()) {
                // se valida la información del conyugue
                try {
                    if (parentescoPersonaDTO.getFechaFin() == null) {
                        this.esPersonaNaturalValida(parentescoPersonaDTO.getPariente(), validarCampos);
                        // si el conyugue es nuevo se valida que no exista otro con la misma identificacion
                        if (parentescoPersonaDTO.getPariente().getId() == null) {
                            if (this.existePersona(parentescoPersonaDTO.getPariente())) {
                                throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008013);
                            }
                        }
                    }
                } catch (CirculemosRuntimeException p) {
                    throw new CirculemosNegocioException(ErrorAdministracion.AdministrarPersona.ADM_008012);
                }
            }
        }
    }

    /**
     * Ingresa una nueva persona validando que posea tipo de identificación, número de identificación y código de organismo. Le asocia la fuente de
     * información indicada.
     * 
     * @param personaDTO
     *            la persona a ingresar
     * @param codigoOrganismo
     *            código del organismo asociado a la persona
     * @param idFuenteInformacion
     *            fuente de información de la persona
     * @return id asociado a la persona ingresada
     */
    private Long ingresarPersonaSinValidar(PersonaDTO personaDTO, int codigoOrganismo, int idFuenteInformacion) {
        Long id;

        personaDTO.setFechaUltimaActualizacion(new Date());
        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
        tipoFuenteInformacionDTO.setId(idFuenteInformacion);
        personaDTO.setFuenteInformacion(tipoFuenteInformacionDTO);

        OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
        organismoTransitoDTO.setCodigoOrganismo(codigoOrganismo);
        personaDTO.setOrganismoTransito(organismoTransitoDTO);

        if (personaDTO instanceof PersonaJuridicaDTO) {
            id = this.registrarPersonaJuridica((PersonaJuridicaDTO) personaDTO);
        } else {
            id = this.registrarPersonaNatural(personaDTO);
        }

        return id;
    }

    @Override
    public PersonaDTO consultarPersonaFuenteExterna(int idTipoIdentificacion, String numeroIdentificacion,
            int codigoOrganismo) {
        PersonaDTO personaDTO = null;

        final StringBuilder jpqlSELECT = new StringBuilder();

        final Map<String, Object> filtrosGen = new HashMap<>();
        jpqlSELECT.append("SELECT pfe FROM PersonaFuenteExterna AS pfe");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.tipoIdentificacion AS tip");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.fuenteInformacion AS tfi");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.estadoCivil AS ec");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.tipoVivienda AS tv");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.nivelEducativo AS ne");
        jpqlSELECT.append(" LEFT JOIN FETCH pfe.direccionFuenteExternaList AS dfe");

        jpqlSELECT.append(" WHERE tip.id = :pIdTipIde");
        filtrosGen.put("pIdTipIde", idTipoIdentificacion);
        jpqlSELECT.append(" AND pfe.numeroIdentificacion = :pNumIden");
        filtrosGen.put("pNumIden", numeroIdentificacion);

        final String orderBy = " ORDER BY pfe.fechaUltimaActualizacion DESC";

        final ValorParametroDTO prioridadBusquedaPersona = parametroEjb
                .consultarParametro(EnumParametro.PRIORIDAD_BUSQUEDA_PERSONA, codigoOrganismo, false);
        String[] valPrioBusPerSplit = prioridadBusquedaPersona.getValorParamSplit();

        final ValorParametroDTO cantDiasVencOrigenAct = parametroEjb
                .consultarParametro(EnumParametro.CONSULTAR_VENCIMIENTO_PER, codigoOrganismo, true);
        int valParamCantDias = cantDiasVencOrigenAct.getValorParamInt();

        if (valPrioBusPerSplit != null) {
            for (String idFuenteInformacion : valPrioBusPerSplit) {
                final int fuenteInformacion = Integer.valueOf(idFuenteInformacion);
                final StringBuilder jpqlFiltros = new StringBuilder();
                final Map<String, Object> filTipFuente = new HashMap<>();
                jpqlFiltros.append(" AND tfi.codigo = :pCodTipFueInf");
                filTipFuente.put("pCodTipFueInf", fuenteInformacion);

                filTipFuente.putAll(filtrosGen);

                jpqlFiltros.append(orderBy);

                final GenericDao<PersonaFuenteExterna> genericDao = new GenericDao<>(PersonaFuenteExterna.class, em);
                final List<PersonaFuenteExterna> result = genericDao
                        .buildAndExecuteQuery((jpqlSELECT.toString()).concat(jpqlFiltros.toString()), filTipFuente);
                if (!result.isEmpty()) {
                    for (PersonaFuenteExterna personaFuenteExterna : result) {

                        Date fecFinVigencia = Utilidades.sumarDias(personaFuenteExterna.getFechaUltimaActualizacion(),
                                valParamCantDias);
                        if (fecFinVigencia.compareTo(UtilFecha.currentZeroTimeDate()) >= 0) {
                            personaDTO = PersonaHelperExtend.toLevel1DTO(personaFuenteExterna);
                            break;
                        }
                    }
                    if (personaDTO != null) {
                        break;
                    }
                }
            }
        } else {
            jpqlSELECT.append(orderBy);
            final GenericDao<PersonaFuenteExterna> genericDao = new GenericDao<>(PersonaFuenteExterna.class, em);
            final List<PersonaFuenteExterna> result = genericDao.buildAndExecuteQuery(jpqlSELECT.toString(),
                    filtrosGen);
            if (!result.isEmpty()) {
                personaDTO = PersonaHelperExtend.toLevel1DTO(result.get(0));
            }
        }

        return personaDTO;
    }

    @Override
    public List<RepresentanteLegalDTO> consultarRepresentastesLegales(Long idPersonaJuridica) {
        List<RepresentanteLegalDTO> resultado = new ArrayList<RepresentanteLegalDTO>();
        GenericDao<RepresentanteLegal> representanteLegalDao = new GenericDao<>(RepresentanteLegal.class, em);
        Map<String, Object> filtros = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r FROM RepresentanteLegal r");
        sql.append(" LEFT JOIN FETCH r.persona p");
        sql.append(" LEFT JOIN FETCH r.personaJuridica pj");
        sql.append(" WHERE r.personaJuridica.id = :idPersonaJuridica");
        filtros.put("idPersonaJuridica", idPersonaJuridica);
        List<RepresentanteLegal> resultadoConsulta = representanteLegalDao.buildAndExecuteQuery(sql, filtros);
        for (RepresentanteLegal representanteLegal : resultadoConsulta) {
            PersonaDTO personaDTO = consultarPersona(representanteLegal.getPersona().getId());
            RepresentanteLegalDTO representanteLegalDTO = ProcesarPersonasHelper
                    .toRepresentanteLegalLevel1DTO(representanteLegal, personaDTO);
            representanteLegalDTO.setPersonaJuridica((PersonaJuridicaDTO) consultarPersona(
                    representanteLegal.getPersonaJuridica().getIdPersonaJuridica()));
            resultado.add(representanteLegalDTO);
        }
        return resultado;
    }

    @Override
    public PersonaDTO consultarPersonaConPrioridad(int codigoOrganismo, int tipoIdentificacion,
            String numeroIdentificacion) throws CirculemosNegocioException {
        PersonaDTO personaDTO = null;

        // OrdenPrioritarioPersFuenteInfoDTO ordenPrioritarioPersFuenteInfoDTO = new OrdenPrioritarioPersFuenteInfoDTO();
        // ordenPrioritarioPersFuenteInfoDTO.setCodigoOrganismo(new ArrayList<String>());
        // String codOrg = String.valueOf(codigoOrganismo);
        // ordenPrioritarioPersFuenteInfoDTO.getCodigoOrganismo().add(codOrg);
        //
        // ordenPrioritarioPersFuenteInfoDTO = fachadaConfiguracionEJB.consultarValorConfiguracion(
        // EnumConfiguracion.ORDEN_PRIORITARIO_PERS_FUENTE_INFORMACION.getCodigo(),
        // ordenPrioritarioPersFuenteInfoDTO);

        // TODO llevar a cabo consulta de fuentes segun sea la prioridad
        personaDTO = consultarPersona(codigoOrganismo, tipoIdentificacion, numeroIdentificacion);

        return personaDTO;
    }

    @Override
    public boolean modificarCabeceraPersona(PersonaDTO personaDTO) {
        PersonaEJB.logger.debug("PersonaEJB::modificarCabeceraPersona(PersonaDTO)");
        checkNotNull(personaDTO, "Se esperaba dto de entrada");
        if (personaDTO.getId() != null) {
            Persona persona = this.em.find(Persona.class, personaDTO.getId());
            if (persona != null) {
                Persona personaFromDTO = PersonaHelperExtend.toLevel1Entity(personaDTO, null);
                em.merge(personaFromDTO);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<PersonaHistoricoDTO> consultarHistoricoPersona(PersonaHistoricoDTO persona) {
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        jpql.append("SELECT ph FROM PersonaHistorico ph");
        jpql.append(" JOIN ph.organismoTransito ot");
        jpql.append(" JOIN ph.tipoIdentificacion ti");
        jpql.append(" WHERE 1=1");
        if (persona != null) {
            if (persona.getPersona() != null && persona.getPersona().getId() != null) {
                jpql.append(" AND ph.persona.id = :idPersona");
                params.put("idPersona", persona.getPersona().getId());
            } else {
                if (persona.getOrganismoTransito() != null
                        && persona.getOrganismoTransito().getCodigoOrganismo() != null) {
                    jpql.append(" AND ot.codigoOrganismo =:codOrganismo");
                    params.put("codOrganismo", persona.getOrganismoTransito().getCodigoOrganismo());
                }
                if (persona.getTipoIdentificacion() != null && persona.getTipoIdentificacion().getId() != null) {
                    jpql.append(" AND ti.id=:idTipoIdentificacion");
                    params.put("idTipoIdentificacion", persona.getTipoIdentificacion().getId());
                }
                if (StringUtils.isNotBlank(persona.getNumeroIdentificacion())) {
                    jpql.append(" AND ph.numeroIdentificacion=:identificacion");
                    params.put("identificacion", persona.getNumeroIdentificacion());
                }
            }
        }
        jpql.append(" ORDER BY ph.fechaUltimaActualizacion DESC");

        GenericDao<PersonaHistorico> personaHistoricoDao = new GenericDao<>(PersonaHistorico.class, this.em);
        List<PersonaHistorico> personaHistoricoList = personaHistoricoDao.buildAndExecuteQuery(jpql, params);

        List<PersonaHistoricoDTO> personaHistoricoDTOList = PersonaHistoricoHelper
                .toListLevel1DTO(personaHistoricoList);
        return personaHistoricoDTOList;
    }

    @Override
    public PersonaDTO guardarPersonaConDocumento(PersonaUbicabilidadDTO personaUbicabilidadDTO)
            throws CirculemosNegocioException {
        PersonaEJB.logger.debug("PersonaEJB::actualizacionPersonaConDocumento(PersonaUbicabilidadDTO)");

        try {
            DatosUbicabilidadDTO datosUbicabilidadDTO = null;
            if (personaUbicabilidadDTO.getPersonaDTO().getId() != null) {
                datosUbicabilidadDTO = new DatosUbicabilidadDTO();
                modificarPersona(personaUbicabilidadDTO.getPersonaDTO(), datosUbicabilidadDTO);
                em.flush();
            } else {
                Long idPersona = registrarPersona(personaUbicabilidadDTO.getPersonaDTO(), true);
                em.flush();
                personaUbicabilidadDTO.getPersonaDTO().setId(idPersona);
                personaUbicabilidadDTO.getPersonaDTO().setTipoIdentificacion(
                        TipoIdentificacionPersonaHelper.toLevel0DTO(em.find(TipoIdentificacionPersona.class,
                                personaUbicabilidadDTO.getPersonaDTO().getTipoIdentificacion().getId())));
            }

            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();

            Persona persona = em.find(Persona.class, personaUbicabilidadDTO.getPersonaDTO().getId());
            em.refresh(persona);
            for (DireccionPersona direccionPersona : persona.getDireccionPersonaList()) {
                em.refresh(direccionPersona);
            }
            PersonaDTO personaDTO = ProcesarPersonasHelper.toPersonaLevel1DTO(persona);
            Long id = null;
            if (!personaUbicabilidadDTO.isSoloGuardar()) {
                /* Guarda firma de la persona */
                if (personaUbicabilidadDTO.getNumeroFirma() != null) {

                    CapturaFirmaDTO capturaFirma = new CapturaFirmaDTO();
                    capturaFirma.setPersonaDTO(personaDTO);
                    capturaFirma.setFirma(personaUbicabilidadDTO.getNumeroFirma());
                    Long numeroFirma = iLfirma.registrarFirma(capturaFirma, false);

                    Map<String, Object> valoresVistaPreliminar = new HashMap<>();
                    valoresVistaPreliminar.put(ConstantesDocumentosC2.IMAGEN_FIRMA, numeroFirma.toString());
                    generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
                }

                if (personaUbicabilidadDTO.getPersonaDTO() instanceof PersonaJuridicaDTO) {
                    generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.ACT_PERSONA_JUR);
                } else {
                    generaDocumento.setIdTipoDocumentoGenerado(EnumTipoDocumentoGenerado.ACTUALIZAR_UBICABILIDAD);
                }
                Object[] valoresParametros = { personaUbicabilidadDTO.getPersonaDTO().getId() };
                generaDocumento.setValoresParametros(valoresParametros);
                generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
                id = iRDocumentosCirculemos.generarDocumento(generaDocumento);

                personaUbicabilidadDTO.getPersonaDTO().setIdDocumento(id);
                personaDTO.setIdDocumento(id);
            }

            PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaDTO, persona);

            if (datosUbicabilidadDTO != null) {
                List<CorreoPersonaHistorico> correoPersonaHistoricos = new ArrayList<>();
                if (datosUbicabilidadDTO.getIdCorreoPersonas() != null
                        && !datosUbicabilidadDTO.getIdCorreoPersonas().isEmpty()) {
                    for (CorreoPersona correoPersona : persona.getCorreoPersonaList()) {
                        if (datosUbicabilidadDTO.getIdCorreoPersonas().contains(correoPersona.getId())) {
                            correoPersonaHistoricos
                                    .add(guardarCorreoPersonaHistorico(CorreoPersonaHelper.toLevel1DTO(correoPersona)));
                        }
                    }
                }

                if (datosUbicabilidadDTO.getIdCorreoPersonasNoActualizadas() != null
                        && !datosUbicabilidadDTO.getIdCorreoPersonasNoActualizadas().isEmpty()) {
                    // consulta historico de los correos no actualizados
                    CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO = new CorreoPersonaHistoricoDTO();
                    correoPersonaHistoricoDTO.setCorreoPersona(new CorreoPersonaDTO());
                    for (Long idCorreos : datosUbicabilidadDTO.getIdCorreoPersonasNoActualizadas()) {
                        correoPersonaHistoricoDTO.getCorreoPersona().setId(idCorreos);

                        // consulta historico de correo para el id de correo historico
                        List<CorreoPersonaHistoricoDTO> correoPersonaHistoricoDTOs = consultarCorreoPersonaHistorico(
                                correoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(correoPersonaHistoricoDTOs, new Comparator<CorreoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(CorreoPersonaHistoricoDTO o1, CorreoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!correoPersonaHistoricoDTOs.isEmpty()) {
                            correoPersonaHistoricos.add(CorreoPersonaHistoricoHelper.toLevel1Entity(
                                    correoPersonaHistoricoDTOs.get(correoPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                if (!correoPersonaHistoricos.isEmpty()) {
                    personaHistorico.setCorreoPersonas(correoPersonaHistoricos);
                }

                List<TelefonoPersonaHistorico> telefonoPersonaHistoricos = new ArrayList<>();

                if (datosUbicabilidadDTO.getIdTelefonoPersonas() != null
                        && !datosUbicabilidadDTO.getIdTelefonoPersonas().isEmpty()) {
                    for (TelefonoPersona telefonoPersona : persona.getTelefonoPersonaList()) {
                        if (datosUbicabilidadDTO.getIdTelefonoPersonas().contains(telefonoPersona.getId())) {
                            telefonoPersonaHistoricos.add(guardarTelefonoPersonaHistorico(
                                    TelefonoPersonaHelper.toLevel1DTO(telefonoPersona)));
                        }
                    }

                }

                if (datosUbicabilidadDTO.getIdTelefonoPersonasNoActualizadas() != null
                        && !datosUbicabilidadDTO.getIdTelefonoPersonasNoActualizadas().isEmpty()) {
                    // consulta historico de los correos no actualizados
                    TelefonoPersonaHistoricoDTO TelefonoPersonaHistoricoDTO = new TelefonoPersonaHistoricoDTO();
                    TelefonoPersonaHistoricoDTO.setTelefonoPersona(new TelefonoPersonaDTO());
                    for (Long idTelefono : datosUbicabilidadDTO.getIdTelefonoPersonasNoActualizadas()) {
                        TelefonoPersonaHistoricoDTO.getTelefonoPersona().setId(idTelefono);

                        // consulta historico de correo para el id de correo historico
                        List<TelefonoPersonaHistoricoDTO> telefonoPersonaHistoricoDTOs = consultarTelefonoPersonaHistorico(
                                TelefonoPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(telefonoPersonaHistoricoDTOs, new Comparator<TelefonoPersonaHistoricoDTO>() {

                            @Override
                            public int compare(TelefonoPersonaHistoricoDTO o1, TelefonoPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!telefonoPersonaHistoricoDTOs.isEmpty()) {
                            telefonoPersonaHistoricos.add(TelefonoPersonaHistoricoHelper.toLevel1Entity(
                                    telefonoPersonaHistoricoDTOs.get(telefonoPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                if (!telefonoPersonaHistoricos.isEmpty()) {
                    personaHistorico.setTelefonoPersonas(telefonoPersonaHistoricos);
                }

                List<DireccionPersonaHistorico> direccionPersonaHistoricos = new ArrayList<>();

                if (datosUbicabilidadDTO.getIdDireccionPersonas() != null
                        && !datosUbicabilidadDTO.getIdDireccionPersonas().isEmpty()) {
                    for (DireccionPersona direccionPersona : persona.getDireccionPersonaList()) {
                        if (datosUbicabilidadDTO.getIdDireccionPersonas().contains(direccionPersona.getId())) {
                            direccionPersonaHistoricos.add(guardarDireccionPersonaHistorico(
                                    DireccionPersonaHelper.toLevel1DTO(direccionPersona)));
                        }
                    }

                }

                if (datosUbicabilidadDTO.getIdDireccionPersonasNoActualizadas() != null
                        && !datosUbicabilidadDTO.getIdDireccionPersonasNoActualizadas().isEmpty()) {
                    // consulta historico de los correos no actualizados
                    DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = new DireccionPersonaHistoricoDTO();
                    direccionPersonaHistoricoDTO.setDireccionPersona(new DireccionPersonaDTO());
                    for (Long idDireccion : datosUbicabilidadDTO.getIdDireccionPersonasNoActualizadas()) {
                        direccionPersonaHistoricoDTO.getDireccionPersona().setId(idDireccion);

                        // consulta historico de correo para el id de correo historico
                        List<DireccionPersonaHistoricoDTO> direccionPersonaHistoricoDTOs = consultarDireccionPersonaHistorico(
                                direccionPersonaHistoricoDTO);

                        // Dado que la consulta anterior se organiza por prioridad, se implementa un Comparator para dar orden natural por id
                        Collections.sort(direccionPersonaHistoricoDTOs, new Comparator<DireccionPersonaHistoricoDTO>() {

                            @Override
                            public int compare(DireccionPersonaHistoricoDTO o1, DireccionPersonaHistoricoDTO o2) {
                                return o1.getId().compareTo(o2.getId());
                            }

                        });

                        // se obtiene el ultimo item del historico
                        if (!direccionPersonaHistoricoDTOs.isEmpty()) {
                            direccionPersonaHistoricos.add(DireccionPersonaHistoricoHelper.toLevel1Entity(
                                    direccionPersonaHistoricoDTOs.get(direccionPersonaHistoricoDTOs.size() - 1), null));
                        }

                    }
                }

                if (!direccionPersonaHistoricos.isEmpty()) {
                    personaHistorico.setDireccionPersonas(direccionPersonaHistoricos);
                }

            } else {
                // Persistencia del historíco de CorreoPersona
                if (persona.getCorreoPersonaList() != null && !persona.getCorreoPersonaList().isEmpty()) {
                    List<CorreoPersonaHistorico> correoPersonaHistoricos = CorreoPersonaHistoricoHelperExtend
                            .toListCorreoPersonaHistorico(persona.getCorreoPersonaList());
                    personaHistorico.setCorreoPersonas(correoPersonaHistoricos);

                    // persiste correoPersonaHistorico
                    for (CorreoPersonaHistorico correoPersonaHistorico : personaHistorico.getCorreoPersonas()) {
                        em.persist(correoPersonaHistorico);
                    }
                }

                // Persistencia del historíco de TelefonoPersona
                if (persona.getTelefonoPersonaList() != null && !persona.getTelefonoPersonaList().isEmpty()) {
                    List<TelefonoPersonaHistorico> telefonoPersonaHistoricos = TelefonoPersonaHistoricoHelperExtend
                            .toListTelefonoPersonaHistorico(persona.getTelefonoPersonaList());

                    personaHistorico.setTelefonoPersonas(telefonoPersonaHistoricos);

                    // persiste telefonoPersonaHistorico
                    for (TelefonoPersonaHistorico telefonoPersonaHistorico : personaHistorico.getTelefonoPersonas()) {
                        em.persist(telefonoPersonaHistorico);
                    }
                }

                // Persistencia del historico de DireccionPersona
                if (persona.getDireccionPersonaList() != null && !persona.getDireccionPersonaList().isEmpty()) {
                    List<DireccionPersonaHistorico> direccionPersonaHistoricos = DireccionPersonaHistoricoHelperExtend
                            .toListDireccionPersonaHistorico(persona.getDireccionPersonaList());

                    personaHistorico.setDireccionPersonas(direccionPersonaHistoricos);

                    for (DireccionPersonaHistorico direccionPersonahistorico : personaHistorico
                            .getDireccionPersonas()) {
                        em.persist(direccionPersonahistorico);
                    }
                }
            }
            // Persistencia de historíco de persona
            personaHistorico.setUsuarioRegistro(
                    UsuarioPersonaHelper.toLevel0Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
            em.persist(personaHistorico);

            List<CorreoPersonaDTO> lsCorreoPersona = iLUbicabilidad.consultarCorreosNotificables(personaDTO.getId());

            if (lsCorreoPersona != null && !lsCorreoPersona.isEmpty()) {
                personaUbicabilidadDTO.getPersonaDTO().setCorreoPersonaList(lsCorreoPersona);
            }

            // Consulta las direcciones ordenadas
            DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
            direccionPersonaDTO.setPersona(personaDTO);
            List<DireccionPersonaDTO> lsDireccionPersonaDTO = iLUbicabilidad
                    .consultarDireccionPersona(direccionPersonaDTO);
            personaUbicabilidadDTO.getPersonaDTO().setDireccionPersonaList(lsDireccionPersonaDTO);

        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015001);
        }
        return personaUbicabilidadDTO.getPersonaDTO();
    }

    /**
     * Envia el correo de ubicabilidad
     * 
     * @throws CirculemosNegocioException
     * 
     * @autor diego.fonseca
     */
    @Override
    public void enviarCorreoUbicabilidad(PersonaDTO personaDTO) throws CirculemosNegocioException {

        OrganismoTransitoDTO organismo = iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario();
        Map<String, Object> variablesMensaje = new HashMap<>();
        variablesMensaje.put("organismo", escapeHtml4(organismo.getNombreOrganismo()));
        Date fechaSistema = UtilFecha.buildCalendar().getTime();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        variablesMensaje.put("anio", escapeHtml4(formateador.format(fechaSistema)));

        List<ArchivoTransportableDTO> lsArchivos = new ArrayList<ArchivoTransportableDTO>();
        ArchivoTransportableDTO archivo = null;
        if (personaDTO.getIdDocumento() != null) {
            try {
                // Consulta el documento para enviarlo en el correo
                List<Long> documentos = new ArrayList<Long>();
                documentos.add(personaDTO.getIdDocumento());
                archivo = new ArchivoTransportableDTO(NOMBRE_ARCHIVO_ACTUALIZACION_DATOS,
                        iRDocumentosCirculemos.consultarDocumentosPDF(documentos));
                archivo.setNumeroDocumento(String.valueOf(personaDTO.getIdDocumento()));
                lsArchivos.add(archivo);
            } catch (CirculemosAlertaException e) {
                logger.error("No se encontró documento para envio de correo de actualizacion", e);
            }
        }

        ValorParametroDTO parametro = iRFachadaNotificaciones.consultarParametroEnvioNotificaciones(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

        // Envio de correo
        if (personaDTO.getCorreoPersonaList() != null && !personaDTO.getCorreoPersonaList().isEmpty()) {
            List<String> correos = new ArrayList<>();
            for (CorreoPersonaDTO correoPersona : personaDTO.getCorreoPersonaList()) {
                correos.add(correoPersona.getCorreoElectronico());
            }
            String[] destinatarios = correos.toArray(new String[correos.size()]);
            PersonaHistoricoDTO pHistorico = new PersonaHistoricoDTO();
            pHistorico.setPersona(personaDTO);
            List<PersonaHistoricoDTO> lsPHistoricoDTO = consultarHistoricoPersona(pHistorico);

            if (parametro != null && parametro.getValorParamBoolean()) {

                EnvioNotificacionDTO envioNotificacion = new EnvioNotificacionDTO();
                List<ConsultarNotificacionesDTO> lsNotificacion = new ArrayList<>();
                ConsultarNotificacionesDTO notificacion = new ConsultarNotificacionesDTO();
                notificacion.setLsCorreoElectronico(Arrays.asList(destinatarios));
                notificacion.setCodSeguimientoInt(personaDTO.getId());
                notificacion.setExternalId(lsPHistoricoDTO.get(0).getId());
                notificacion.setLsArchivos(lsArchivos);
                lsNotificacion.add(notificacion);
                envioNotificacion.setLsNotificaciones(lsNotificacion);
                envioNotificacion.setTipoCorreo(EnumTipoCorreo.ENVIO_ACTUALIZACION_UBICABILIDAD_ENOTIFICA);
                envioNotificacion.setTipoNotificacion(EnumTipoNotificacion.NOTIFICACION_UBICABILIDAD);
                envioNotificacion.setVariablesMensaje(variablesMensaje);
                Integer[] aEstados = iRFachadaNotificaciones.enviaNotificaciones(envioNotificacion);
                if (aEstados[0] > 0) {
                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015079);
                }
            } else {
                LogEnvioCorreoDTO log = iRCirculemosMailSender.enviarCorreo(organismo.getCodigoOrganismo(),
                        EnumTipoCorreo.ENVIO_ACTUALIZACION_UBICABILIDAD, destinatarios, variablesMensaje, lsArchivos);
                // Actualizacion de auditoria de correo
                log.setTablaSolicitud("persona_historico");
                log.setIdTablaSolicitud(lsPHistoricoDTO.get(0).getId());
                em.merge(LogEnvioCorreoHelper.toLevel1Entity(log, null));
            }
        }
    }

    /**
     * Adiciona documento historico
     * 
     * @autor diego.fonseca mod julio.pinzon 2016-09-14
     */
    private void adicionarDocumentoHistorico(PersonaDTO personaDTO, Long idDocumento, Long... idDirecciones) {
        PersonaEJB.logger.debug("PersonaEJB::adicionarDocumentoHistorico(PersonaDTO)");

        Persona persona = PersonaHelperExtend.toLevel1Entity(personaDTO, null);
        PersonaHistorico personaHistorico = AdministrarPersonaHelper.adicionarHistorico(personaDTO, persona);
        personaHistorico.setIdDocumento(idDocumento);
        em.persist(personaHistorico);
        if (idDirecciones.length > 0) {
            for (DireccionPersona direccionPersona : persona.getDireccionPersonaList()) {
                for (Long id : idDirecciones) {
                    if (id != null && direccionPersona.getId() == id) {
                        // TODO: AJUSTAR UBICABILIDAD
                        // direccionPersona.setPersonaHistorico(personaHistorico);
                        em.merge(direccionPersona);
                    }
                }
            }
        } else {
            for (DireccionPersona direccionPersona : persona.getDireccionPersonaList()) {
                // TODO: AJUSTAR UBICABILIDAD
                // direccionPersona.setPersonaHistorico(personaHistorico);
                em.merge(direccionPersona);
            }
        }

    }

    @Override
    public CargueArchivoDTO validarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta)
            throws CirculemosNegocioException {
        logger.debug("PersonaEJB.consultarAnalisisUbicabilidad(ArchivoTransportableDTO)");
        try {
            DateFormat df = new SimpleDateFormat(ConstantesCargaArchivos.FORMATO_FECHA_ARCHIVO);
            String dateStr = df.format(Utilidades.getFechaActual());

            File csvFile = new File(ConstantesCargaArchivos.NOMBRE_ARCHIVO_ANALISIS_CARTERA + dateStr
                    + ConstantesCargaArchivos.EXTENSION_CSV);
            FileOutputStream fos = new FileOutputStream(csvFile);
            fos.write(archivoConsulta.getContenido());

            iLCargueMasivo.validarEstructuraArchivo(csvFile, EnumTipoCargueArchivo.CONSULTA_UBICABILIDAD.getValue());

            int totalRegistros = ConstantesCargaArchivos.getNumeroRegistrosCSV(csvFile);
            totalRegistros = totalRegistros - 1; // se resta el encabezado

            CargueArchivoDTO cargueArchivo = new CargueArchivoDTO();
            cargueArchivo.setTipoCargueArchivo(
                    new TipoCargueArchivoDTO(EnumTipoCargueArchivo.CONSULTA_UBICABILIDAD.getValue()));
            cargueArchivo.setFechaCargue(Utilidades.getFechaActual());
            cargueArchivo.setUsuarioCargue(iRSeguridadCirculemos.obtenerUsuarioDto());
            cargueArchivo.setTotalRegistros(totalRegistros);
            cargueArchivo
                    .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.EN_PROCESO.getValue()));
            cargueArchivo.setTotalLeidos(0);
            cargueArchivo.setTotalInconsistencias(0);

            String consecutivo = iRFachadaAdminGeneral.generarConsecutivo(
                    iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(),
                    EnumConsecutivo.NUMERO_CARGUE_ARCHIVO_ANALISIS_UBIC);
            cargueArchivo.setConsecutivo(consecutivo);
            try {
                GestorArchivosOpciones[] opciones = new GestorArchivosOpciones[0];
                String idDocumentoCargue = irRepositorioArchivo.registrarDocumento(
                        EnumCategoriaDocumento.ARCHIVO_CONSULTA_ANALISIS_UBIC, archivoConsulta, opciones);
                cargueArchivo.setIdDocumentoCargue(Long.parseLong(idDocumentoCargue));
            } catch (CirculemosAlertaException e1) {
                throw new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082005);
            }

            cargueArchivo = iLCargueMasivo.registrarCargueArchivo(cargueArchivo);
            return cargueArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082001);
        }
    }

    /**
     * Envia al gestor de archivos los reportes de ubicabilidad creados a partir de la consulta realizada
     * 
     * @param respuestaConsulta
     * @param cargueArchivo
     * @throws CirculemosAlertaException
     */
    private void cargarArchivosResultado(ConsultaAnalisisUbicResulDTO respuestaConsulta, CargueArchivoDTO cargueArchivo)
            throws CirculemosAlertaException {
        logger.debug("PersonaEJB.cargarArchivosResultado(ConsultaAnalisisUbicResulDTO, CargueArchivoDTO");
        ArchivoTransportableDTO archivoDirecciones = this
                .getArchivoUbicabilidadDirecciones(respuestaConsulta.getDireccionPersonas());
        ArchivoTransportableDTO archivoTelefonos = this
                .getArchivoUbicabilidadTelefonos(respuestaConsulta.getTelefonoPersonas());
        ArchivoTransportableDTO archivoCorreos = this
                .getArchivoUbicabilidadCorreos(respuestaConsulta.getCorreoPersonas());
        ArchivoTransportableDTO archivoInconsistencias = this
                .getArchivoUbicabilidadInconsistencias(respuestaConsulta.getInconsistencias());

        GestorArchivosOpciones[] opciones = new GestorArchivosOpciones[0];
        if (archivoDirecciones != null) {
            String idDocumentoCargueDir = irRepositorioArchivo.registrarDocumento(
                    EnumCategoriaDocumento.ARCHIVO_CONSULTA_ANALISIS_UBIC, archivoDirecciones, opciones);
            DocumentoResultadoCargueDTO docResulDTO = new DocumentoResultadoCargueDTO();
            docResulDTO.setCargueArchivo(cargueArchivo);
            docResulDTO.setIdDocumentoResultado(Long.parseLong(idDocumentoCargueDir));
            docResulDTO.setTipoDocumentoResultadoCargue(new TipoDocumentoResultadoCargueDTO(
                    EnumTipoDocumentoResulCargue.UBICABILIDAD_DIRECCION.getValue()));
            docResulDTO = iLCargueMasivo.registrarDocumentoResulCargue(docResulDTO);
            respuestaConsulta.setDocDireccionesPersonas(docResulDTO);
        }
        if (archivoTelefonos != null) {
            String idDocumentoCargueTel = irRepositorioArchivo.registrarDocumento(
                    EnumCategoriaDocumento.ARCHIVO_CONSULTA_ANALISIS_UBIC, archivoTelefonos, opciones);
            DocumentoResultadoCargueDTO docResulDTO = new DocumentoResultadoCargueDTO();
            docResulDTO.setCargueArchivo(cargueArchivo);
            docResulDTO.setIdDocumentoResultado(Long.parseLong(idDocumentoCargueTel));
            docResulDTO.setTipoDocumentoResultadoCargue(
                    new TipoDocumentoResultadoCargueDTO(EnumTipoDocumentoResulCargue.UBICABILIDAD_TELEFONO.getValue()));
            docResulDTO = iLCargueMasivo.registrarDocumentoResulCargue(docResulDTO);
            respuestaConsulta.setDocTelefonoPersonas(docResulDTO);
        }
        if (archivoCorreos != null) {
            String idDocumentoCargueCorr = irRepositorioArchivo.registrarDocumento(
                    EnumCategoriaDocumento.ARCHIVO_CONSULTA_ANALISIS_UBIC, archivoCorreos, opciones);
            DocumentoResultadoCargueDTO docResulDTO = new DocumentoResultadoCargueDTO();
            docResulDTO.setCargueArchivo(cargueArchivo);
            docResulDTO.setIdDocumentoResultado(Long.parseLong(idDocumentoCargueCorr));
            docResulDTO.setTipoDocumentoResultadoCargue(
                    new TipoDocumentoResultadoCargueDTO(EnumTipoDocumentoResulCargue.UBICABILIDAD_CORREO.getValue()));
            docResulDTO = iLCargueMasivo.registrarDocumentoResulCargue(docResulDTO);
            respuestaConsulta.setDocCorreoPersonas(docResulDTO);
        }
        if (archivoInconsistencias != null) {
            String idDocumentoCargueInconsistencias = irRepositorioArchivo.registrarDocumento(
                    EnumCategoriaDocumento.ARCHIVO_CONSULTA_ANALISIS_UBIC, archivoInconsistencias, opciones);
            DocumentoResultadoCargueDTO docResulDTO = new DocumentoResultadoCargueDTO();
            docResulDTO.setCargueArchivo(cargueArchivo);
            docResulDTO.setIdDocumentoResultado(Long.parseLong(idDocumentoCargueInconsistencias));
            docResulDTO.setTipoDocumentoResultadoCargue(new TipoDocumentoResultadoCargueDTO(
                    EnumTipoDocumentoResulCargue.UBICABILIDAD_INCONSISTENCIAS.getValue()));
            docResulDTO = iLCargueMasivo.registrarDocumentoResulCargue(docResulDTO);
            respuestaConsulta.setDocInconsistencias(docResulDTO);
        }
    }

    /**
     * Crea el archivo de inconsistencias del reporte de analisis de ubicabilidad ejecutado.
     * 
     * @param inconsistencias
     * @return
     */
    private ArchivoTransportableDTO getArchivoUbicabilidadInconsistencias(
            List<InconsistenciaAnalisisUbicDTO> inconsistencias) {
        ArchivoTransportableDTO respuesta = null;
        if (inconsistencias != null && inconsistencias.size() > 0) {
            respuesta = new ArchivoTransportableDTO();
            StringBuilder sb = new StringBuilder();
            String lineSep = ConstantesCargaArchivos.getSeparadorLineaSistema();
            String separadorColumna = ConstantesCargaArchivos.CSV_SEPARADOR;
            sb.append("Tipo de documento" + separadorColumna + "Número de documento" + separadorColumna
                    + "Inconsistencia" + lineSep);
            for (InconsistenciaAnalisisUbicDTO inconsistenciaDTO : inconsistencias) {
                String tipoId = inconsistenciaDTO.getTipoIdentificacion();
                String numeroId = inconsistenciaDTO.getNumeroIdentificacion();
                String inconsistencia = inconsistenciaDTO.getInconsistencia();
                sb.append(tipoId + separadorColumna + numeroId + separadorColumna + inconsistencia + lineSep);
            }
            respuesta.setContenido(sb.toString().getBytes(StandardCharsets.UTF_8));
            respuesta.setNombre(ConstantesCargaArchivos.NOMBRE_ARCHIVO_REPORTE_INCONSISTENCIAS);
        }
        return respuesta;
    }

    /**
     * Crea el archivo de los correos del reporte de analisis de ubicabilidad ejecutado.
     * 
     * @param correoPersonas
     * @return
     */
    private ArchivoTransportableDTO getArchivoUbicabilidadCorreos(List<PersonaDTO> correoPersonas) {
        ArchivoTransportableDTO respuesta = null;
        StringBuilder sb = new StringBuilder();
        if (correoPersonas != null && correoPersonas.size() > 0) {
            respuesta = new ArchivoTransportableDTO();
            for (PersonaDTO persona : correoPersonas) {
                String tipoId = persona.getTipoIdentificacion().getCodigo();
                String numeroId = persona.getNumeroIdentificacion().toString();
                String primerNombre = persona.getNombre1();
                String segundoNombre = persona.getNombre2();
                String primerApellido = persona.getApellido1();
                String segundoApellido = persona.getApellido2();
                String razonSocial = "";
                if (persona instanceof PersonaJuridicaDTO) {
                    razonSocial = ((PersonaJuridicaDTO) persona).getNombreComercial();
                }

                if (persona.getCorreoPersonaList() != null && persona.getCorreoPersonaList().size() > 0) {
                    for (CorreoPersonaDTO correo : persona.getCorreoPersonaList()) {
                        sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                        sb.append(correo.getCorreoElectronico() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(Utilidades.dateToStringFormatApp(correo.getFechaActualizacion(), false)
                                + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(
                                correo.getTipoFuenteInformacion().getNombre() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append((correo.getEstado() != null && correo.getEstado()
                                ? ConstantesCargaArchivos.DIRECCION_VALIDO_SI
                                : ConstantesCargaArchivos.DIRECCION_VALIDO_NO) + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(correo.getPrioridad() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                    }
                } else {
                    sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                }
            }
            respuesta.setContenido(sb.toString().getBytes());
            respuesta.setNombre(ConstantesCargaArchivos.NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_TEL);
        }
        return respuesta;
    }

    /**
     * Crea el archivo de telefonos del reporte de analisis de ubicabilidad ejecutado.
     * 
     * @param telefonoPersonas
     * @return
     */
    private ArchivoTransportableDTO getArchivoUbicabilidadTelefonos(List<PersonaDTO> telefonoPersonas) {
        ArchivoTransportableDTO respuesta = null;
        StringBuilder sb = new StringBuilder();
        if (telefonoPersonas != null && telefonoPersonas.size() > 0) {
            respuesta = new ArchivoTransportableDTO();
            for (PersonaDTO persona : telefonoPersonas) {
                // String idPersona = numFila+"";
                String tipoId = persona.getTipoIdentificacion().getCodigo();
                String numeroId = persona.getNumeroIdentificacion().toString();
                String primerNombre = persona.getNombre1();
                String segundoNombre = persona.getNombre2();
                String primerApellido = persona.getApellido1();
                String segundoApellido = persona.getApellido2();
                String razonSocial = "";
                if (persona instanceof PersonaJuridicaDTO) {
                    razonSocial = ((PersonaJuridicaDTO) persona).getNombreComercial();
                }

                if (persona.getTelefonoPersonaDTOs() != null && persona.getTelefonoPersonaDTOs().size() > 0) {
                    for (TelefonoPersonaDTO telefono : persona.getTelefonoPersonaDTOs()) {
                        sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                        sb.append(telefono.getTipoTelefono().getNombre() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(telefono.getNumeroTelefono() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(Utilidades.dateToStringFormatApp(telefono.getFechaActualizacion(), false)
                                + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(telefono.getTipoFuenteInformacion().getNombre()
                                + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append((telefono.getEstado() != null && telefono.getEstado()
                                ? ConstantesCargaArchivos.DIRECCION_VALIDO_SI
                                : ConstantesCargaArchivos.DIRECCION_VALIDO_NO) + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(telefono.getPrioridad() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                    }
                } else {
                    sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                }
            }
            respuesta.setContenido(sb.toString().getBytes());
            respuesta.setNombre(ConstantesCargaArchivos.NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_TEL);
        }
        return respuesta;
    }

    /**
     * Crea el archivo de direcciones del reporte de analisis de ubicabilidad ejecutado.
     * 
     * @param direccionPersonas
     * @return
     */
    private ArchivoTransportableDTO getArchivoUbicabilidadDirecciones(List<PersonaDTO> direccionPersonas) {
        ArchivoTransportableDTO respuesta = null;
        StringBuilder sb = new StringBuilder();
        if (direccionPersonas != null && direccionPersonas.size() > 0) {
            respuesta = new ArchivoTransportableDTO();
            for (PersonaDTO persona : direccionPersonas) {
                // String idPersona = numFila+"";
                String tipoId = persona.getTipoIdentificacion().getCodigo();
                String numeroId = persona.getNumeroIdentificacion().toString();
                String primerNombre = persona.getNombre1();
                String segundoNombre = persona.getNombre2();
                String primerApellido = persona.getApellido1();
                String segundoApellido = persona.getApellido2();
                String razonSocial = "";
                if (persona instanceof PersonaJuridicaDTO) {
                    razonSocial = ((PersonaJuridicaDTO) persona).getNombreComercial();
                }

                if (persona.getDireccionPersonaList() != null && persona.getDireccionPersonaList().size() > 0) {
                    for (DireccionPersonaDTO direccion : persona.getDireccionPersonaList()) {
                        sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                        String tipoUbicabilidad = (direccion.getDireccion().getTipoUbicabilidad() != null
                                ? direccion.getDireccion().getTipoUbicabilidad().getNombre() : "");
                        String dirComplemento = (direccion.getDireccion().getComplemento() != null
                                ? direccion.getDireccion().getComplemento() : "");
                        String dirDepartamento = (direccion.getDireccion().getDepartamento() != null
                                ? direccion.getDireccion().getDepartamento().getNombre() : "");
                        String dirMunicipio = (direccion.getDireccion().getMunicipio() != null
                                ? direccion.getDireccion().getMunicipio().getNombre() : "");
                        String dirLocalidad = (direccion.getDireccion().getLocalidad() != null
                                ? direccion.getDireccion().getLocalidad().getNombre() : "");
                        String fechaActualiza = (direccion.getFechaActualizacion() != null
                                ? Utilidades.dateToStringFormatApp(direccion.getFechaActualizacion(), false) : "");

                        sb.append(tipoUbicabilidad + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(dirComplemento + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(dirDepartamento + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(dirMunicipio + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(dirLocalidad + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(fechaActualiza + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(direccion.getTipoFuenteInformacion().getNombre()
                                + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append((direccion.getEstado() != null && direccion.getEstado()
                                ? ConstantesCargaArchivos.DIRECCION_VALIDO_SI
                                : ConstantesCargaArchivos.DIRECCION_VALIDO_NO) + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(direccion.getPrioridad() + ConstantesCargaArchivos.CSV_SEPARADOR);
                        sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                    }
                } else {
                    sb.append(tipoId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(numeroId + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoNombre + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(primerApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(segundoApellido + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(razonSocial + ConstantesCargaArchivos.CSV_SEPARADOR);

                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append("" + ConstantesCargaArchivos.CSV_SEPARADOR);
                    sb.append(ConstantesCargaArchivos.getSeparadorLineaSistema());
                }
            }
            respuesta.setContenido(sb.toString().getBytes());
            respuesta.setNombre(ConstantesCargaArchivos.NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC_DIR);
        }
        return respuesta;
    }

    /**
     * Ejecuta la consulta de cada uno de los documentos cargado para un reporte de analisis de ubicabilidad.
     * 
     * @param csvFile
     * @param respuestaConsulta
     * @param cargueArchivo
     * @param camposConsulta
     * @throws NumberFormatException
     * @throws IOException
     */
    private void consultarDocumentosCSV(File csvFile, ConsultaAnalisisUbicResulDTO respuestaConsulta,
            CargueArchivoDTO cargueArchivo, boolean[] camposConsulta) throws NumberFormatException, IOException {
        logger.debug(
                "PersonaEJB.consultarDocumentosCSV(File, ConsultaAnalisisUbicResulDTO, CargueArchivoDTO, boolean[])");

        boolean consultaDirecciones = camposConsulta[0];
        boolean consultaTelefonos = camposConsulta[1];
        boolean consultaCorreos = camposConsulta[2];

        int numFila = 0;
        int totalConsultados = 0;
        int totalInconsistencias = 0;
        List<String> docsExistentes = new ArrayList<String>();
        String linea = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((linea = br.readLine()) != null) {
                try {
                    switch (numFila) {
                    case 0:
                        break;
                    default:
                        String[] informacionDocumento = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
                        String tipoDocumento = informacionDocumento[0];
                        String numeroIdentificacion = informacionDocumento[1];
                        int codTipoId = 0;

                        if (tipoDocumento.trim().equals("") || numeroIdentificacion.trim().equals("")) {
                            InconsistenciaAnalisisUbicDTO inconsistencia = new InconsistenciaAnalisisUbicDTO();
                            inconsistencia.setTipoIdentificacion(tipoDocumento);
                            inconsistencia.setNumeroIdentificacion(numeroIdentificacion);
                            inconsistencia.setInconsistencia(
                                    ErrorAdministracion.ConsultaAnalisisCartera.ADM_082002.getDescError());
                            respuestaConsulta.getInconsistencias().add(inconsistencia);
                            totalInconsistencias++;
                            continue;
                        }

                        if (docsExistentes.contains(tipoDocumento + numeroIdentificacion)) {
                            totalConsultados++;
                            continue;
                        }

                        Query tipoIdQuery = em.createNamedQuery("TipoIdentificacionPersona.findByCodigo");
                        tipoIdQuery.setParameter("codigo", tipoDocumento);
                        @SuppressWarnings("unchecked")
                        List<Object> tipoIdLista = tipoIdQuery.getResultList();
                        if (tipoIdLista == null || tipoIdLista.size() == 0) {
                            InconsistenciaAnalisisUbicDTO inconsistencia = new InconsistenciaAnalisisUbicDTO();
                            inconsistencia.setTipoIdentificacion(tipoDocumento);
                            inconsistencia.setNumeroIdentificacion(numeroIdentificacion);
                            inconsistencia.setInconsistencia(
                                    ErrorAdministracion.ConsultaAnalisisCartera.ADM_082004.getDescError());
                            respuestaConsulta.getInconsistencias().add(inconsistencia);
                            totalInconsistencias++;
                            continue;
                        }

                        codTipoId = ((TipoIdentificacionPersona) tipoIdLista.get(0)).getId();

                        PersonaDTO personaDTO = consultarPersonaNQ(codTipoId, numeroIdentificacion);
                        if (personaDTO == null) {
                            InconsistenciaAnalisisUbicDTO inconsistencia = new InconsistenciaAnalisisUbicDTO();
                            inconsistencia.setTipoIdentificacion(tipoDocumento);
                            inconsistencia.setNumeroIdentificacion(numeroIdentificacion);
                            inconsistencia.setInconsistencia(
                                    ErrorAdministracion.ConsultaAnalisisCartera.ADM_082003.getDescError());
                            respuestaConsulta.getInconsistencias().add(inconsistencia);
                            totalInconsistencias++;
                            continue;
                        }

                        if (consultaDirecciones) {
                            personaDTO.setDireccionPersonaList(consultarDireccionPersonaNQ(personaDTO));
                            respuestaConsulta.getDireccionPersonas().add(personaDTO);
                        }
                        if (consultaTelefonos) {
                            personaDTO.setTelefonoPersonaDTOs(consultarTelefonosPersonaNQ(personaDTO));
                            respuestaConsulta.getTelefonoPersonas().add(personaDTO);
                        }
                        if (consultaCorreos) {
                            personaDTO.setCorreoPersonaList(consultarCorreosPersonaNQ(personaDTO));
                            respuestaConsulta.getCorreoPersonas().add(personaDTO);
                        }

                        docsExistentes.add(tipoDocumento + numeroIdentificacion);

                        totalConsultados++;
                        break;
                    }
                } finally {
                    numFila++;
                    // try {
                    // TimeUnit.SECONDS.sleep(10);
                    // } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // }
                    cargueArchivo.setTotalInconsistencias(totalInconsistencias);
                    cargueArchivo.setTotalLeidos(totalConsultados);
                    iLCargueMasivo.registrarRegistroProcesado(cargueArchivo);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void registrarRegistroProcesado(int totalConsultados, int totalInconsistencias,
            CargueArchivoDTO cargueArchivo) {

        final StringBuilder sql = new StringBuilder();
        sql.append("UPDATE cargue_archivo ");
        sql.append("SET total_actualizados = :totalActualizados, total_agregados = :totalAgregados");
        sql.append(", total_leidos = :totalLeidos, total_inconsistencias = :totalIncosistencias ");
        sql.append("WHERE id_cargue_archivo = :idCargue");

        final Map<String, Object> params = new HashMap<>();
        params.put("totalLeidos", totalConsultados);
        params.put("totalIncosistencias", totalInconsistencias);
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

    /**
     * Realiza la consulta de la información basica de una persona con un native query
     * 
     * @param codTipoId
     * @param numeroIdentificacion
     * @return
     */
    private PersonaDTO consultarPersonaNQ(int codTipoId, String numeroIdentificacion) {
        logger.debug("consultarProcesosImpugnacion(ImpugnacionConsultaDTO)");

        PersonaDTO respuesta = null;
        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append(
                "tip.codigo, p.nombre1, p.nombre2, p.apellido1, p.apellido2, pj.nombre_comercial, p.numero_identificacion, p.id_persona ");
        sql.append("FROM persona p ");
        sql.append(
                "LEFT JOIN tipo_identificacion_persona tip on p.id_tipo_identificacion = tip.id_tipo_identificacion ");
        sql.append("FULL OUTER JOIN persona_juridica pj on pj.id_persona_juridica = p.id_persona ");
        sql.append("WHERE tip.id_tipo_identificacion = :tipoId ");
        sql.append("AND p.numero_identificacion = :numId ");

        params.put("tipoId", codTipoId);
        params.put("numId", numeroIdentificacion);

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        List<Object[]> objects = query.getResultList();
        if (objects != null && objects.size() > 0) {
            respuesta = new PersonaDTO();
            if (codTipoId == COD_ID_RUC) {
                respuesta = new PersonaJuridicaDTO();
            }
            for (Object[] objects2 : objects) {
                TipoIdentificacionPersonaDTO tipoIdDTO = new TipoIdentificacionPersonaDTO();
                tipoIdDTO.setCodigo(objects2[0].toString());
                respuesta.setTipoIdentificacion(tipoIdDTO);
                if (codTipoId == COD_ID_RUC) {
                    ((PersonaJuridicaDTO) respuesta).setNombreComercial(objects2[5].toString());
                } else {
                    respuesta.setNombre1(objects2[1].toString());
                    respuesta.setNombre2((objects2[2] != null ? objects2[2].toString() : STRING_VACIO));
                    respuesta.setApellido1(objects2[3].toString());
                    respuesta.setApellido2((objects2[4] != null ? objects2[4].toString() : STRING_VACIO));
                }
                respuesta.setNumeroIdentificacion(objects2[6].toString());
                respuesta.setId(Long.parseLong(objects2[7].toString()));
            }
        }

        return respuesta;
    }

    /**
     * Realiza la consulta de la información basica de las direcciones de una persona con un native query
     * 
     * @param personaDTO
     * @return
     */
    private List<DireccionPersonaDTO> consultarDireccionPersonaNQ(PersonaDTO personaDTO) {
        List<DireccionPersonaDTO> respuesta = new ArrayList<DireccionPersonaDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append(
                "SELECT td.nombre as tipo_documento, d.complemento, dpto.nombre_departamento, mun.nombre as municipio, l.nombre as localidad, dp.fecha_actualizacion, tfi.nombre as tipo_fuente, dp.estado, dp.prioridad ");
        sql.append("FROM direccion_persona dp with(nolock) ");
        sql.append("LEFT JOIN direccion d with(nolock) ON dp.id_direccion = d.id_direccion ");
        sql.append(
                "LEFT JOIN tipo_ubicabilidad td with(nolock) ON d.codigo_tipo_ubicabilidad = td.codigo_tipo_ubicabilidad ");
        sql.append("LEFT JOIN departamento dpto with(nolock) ON dpto.id_departamento = d.id_departamento ");
        sql.append("LEFT JOIN municipio mun with(nolock) ON mun.id_municipio = d.id_municipio ");
        sql.append("LEFT JOIN localidad l with(nolock) ON l.id_localidad = d.id_localidad ");
        sql.append(
                "LEFT JOIN tipo_fuente_informacion tfi with(nolock) ON tfi.codigo_fuente_informacion = dp.codigo_fuente_informacion ");
        sql.append("WHERE dp.id_persona = :idPersona");

        params.put("idPersona", personaDTO.getId());

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                DireccionPersonaDTO direccionPersona = new DireccionPersonaDTO();

                DireccionDTO direccion = new DireccionDTO();

                TipoUbicabilidadDTO tipoDireccion = new TipoUbicabilidadDTO();
                tipoDireccion.setNombre((objects2[0] != null ? objects2[0].toString() : STRING_VACIO));

                // Cambio de Tipo dirección en DireccionPersona por TipoUbicabilidad en Direccion
                direccion.setTipoUbicabilidad(tipoDireccion);

                direccion.setComplemento(objects2[1].toString());

                DepartamentoDTO departamento = new DepartamentoDTO();
                departamento.setNombre((objects2[2] != null ? objects2[2].toString() : STRING_VACIO));
                direccion.setDepartamento(departamento);

                MunicipioDTO municipio = new MunicipioDTO();
                municipio.setNombre((objects2[3] != null ? objects2[3].toString() : STRING_VACIO));
                direccion.setMunicipio(municipio);

                LocalidadDTO localidad = new LocalidadDTO();
                localidad.setNombre((objects2[4] != null ? objects2[4].toString() : STRING_VACIO));
                direccion.setLocalidad(localidad);

                direccionPersona.setDireccion(direccion);
                direccionPersona.setFechaActualizacion((objects2[5] != null ? (Date) objects2[5] : null));

                TipoFuenteInformacionDTO fuenteInformacion = new TipoFuenteInformacionDTO();
                fuenteInformacion.setNombre(objects2[6].toString());
                direccionPersona.setTipoFuenteInformacion(fuenteInformacion);

                direccionPersona.setEstado((objects2[7] != null ? ((byte) objects2[7]) != 0 : null));
                direccionPersona.setPrioridad((objects2[8] != null ? Integer.parseInt(objects2[8].toString()) : null));

                respuesta.add(direccionPersona);
            }
        }
        return respuesta;
    }

    /**
     * Realiza la consulta de la información basica de los telefonos de una persona con un native query
     * 
     * @param personaDTO
     * @return
     */
    private List<TelefonoPersonaDTO> consultarTelefonosPersonaNQ(PersonaDTO personaDTO) {
        List<TelefonoPersonaDTO> respuesta = new ArrayList<TelefonoPersonaDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append(
                "SELECT  tt.nombre as tipo_telefono, tp.numero_telefono, tp.fecha_actualizacion, tfi.nombre as tipo_fuente, tp.estado, tp.prioridad ");
        sql.append("FROM telefono_persona tp with(nolock) ");
        sql.append("LEFT JOIN tipo_telefono tt with(nolock) ON tp.id_tipo_telefono = tt.id_tipo_telefono ");
        sql.append(
                "LEFT JOIN tipo_fuente_informacion tfi with(nolock) ON tfi.codigo_fuente_informacion = tp.codigo_fuente_informacion ");
        sql.append("WHERE tp.id_persona = :idPersona");

        params.put("idPersona", personaDTO.getId());

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        List<Object[]> objects = query.getResultList();
        if (objects != null && objects.size() > 0) {
            for (Object[] objects2 : objects) {
                TelefonoPersonaDTO telefono = new TelefonoPersonaDTO();

                TipoTelefonoDTO tipoTelefono = new TipoTelefonoDTO();
                tipoTelefono.setNombre(objects2[0].toString());
                telefono.setTipoTelefono(tipoTelefono);

                telefono.setNumeroTelefono(objects2[1].toString());
                telefono.setFechaActualizacion((Date) objects2[2]);

                TipoFuenteInformacionDTO fuenteInformacion = new TipoFuenteInformacionDTO();
                fuenteInformacion.setNombre(objects2[3].toString());
                telefono.setTipoFuenteInformacion(fuenteInformacion);

                telefono.setEstado((objects2[4] != null ? ((byte) objects2[4]) != 0 : null));
                telefono.setPrioridad((objects2[5] != null ? Integer.parseInt(objects2[5].toString()) : null));

                respuesta.add(telefono);
            }
        }
        return respuesta;
    }

    /**
     * Realiza la consulta de la información basica de los correos de una persona con un native query
     * 
     * @param personaDTO
     * @return
     */
    private List<CorreoPersonaDTO> consultarCorreosPersonaNQ(PersonaDTO personaDTO) {
        List<CorreoPersonaDTO> respuesta = new ArrayList<CorreoPersonaDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT cp.correo_electronico, cp.fecha_actualizacion, tfi.nombre, cp.estado, cp.prioridad ");
        sql.append("FROM correo_persona cp with(nolock) ");
        sql.append(
                "LEFT JOIN tipo_fuente_informacion tfi with(nolock) ON tfi.codigo_fuente_informacion = cp.codigo_fuente_informacion ");
        sql.append("WHERE cp.id_persona = :idPersona ");

        params.put("idPersona", personaDTO.getId());

        Query query = em.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        List<Object[]> objects = query.getResultList();
        if (objects != null && objects.size() > 0) {
            for (Object[] objects2 : objects) {
                CorreoPersonaDTO correo = new CorreoPersonaDTO();

                correo.setCorreoElectronico(objects2[0].toString());
                correo.setFechaActualizacion((Date) objects2[1]);

                TipoFuenteInformacionDTO fuenteInformacion = new TipoFuenteInformacionDTO();
                fuenteInformacion.setNombre(objects2[2].toString());
                correo.setTipoFuenteInformacion(fuenteInformacion);

                correo.setEstado((objects2[3] != null ? ((byte) objects2[3]) != 0 : null));
                correo.setPrioridad((objects2[4] != null ? Integer.parseInt(objects2[4].toString()) : null));

                respuesta.add(correo);
            }
        }
        return respuesta;
    }

    public ScoreUbicabilidadDTO consultarScoringUbicabilidad(TipoFuenteInformacionDTO tipoFuenteInformacionDTO) {
        logger.debug("UbicabilidadEJB.consultarScoringUbicabilidad(TipoFuenteInformacionDTO)");

        if (tipoFuenteInformacionDTO == null || tipoFuenteInformacionDTO.getId() == null) {
            throw new CirculemosRuntimeException("Tipo fuente de información es necesaria");
        }

        TipoFuenteInformacion tipoFuenteInformacion = em.find(TipoFuenteInformacion.class,
                tipoFuenteInformacionDTO.getId());

        ScoreUbicabilidadDTO scoreUbicabilidadDTO = null;

        if (tipoFuenteInformacion != null && tipoFuenteInformacion.getScoreUbicabilidad() != null) {

            scoreUbicabilidadDTO = ScoreUbicabilidadHelper.toLevel0DTO(tipoFuenteInformacion.getScoreUbicabilidad());
        }

        return scoreUbicabilidadDTO;
    }

    private void guardarCorreoPersona(CorreoPersonaDTO correoPersonaDTO, PersonaDTO personaDTO, Date fechaActual) {
        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        correoPersonaDTO.setFechaRegistro(fechaActual);
        correoPersonaDTO.setUsuarioRegistro(usuarioPersonaDTO);
        correoPersonaDTO.setUsuarioActualiza(usuarioPersonaDTO);
        correoPersonaDTO.setFechaActualizacion(fechaActual);
        correoPersonaDTO.setUsuarioValida(usuarioPersonaDTO);
        correoPersonaDTO.setFechaValidacion(fechaActual);
        correoPersonaDTO
                .setScoreUbicabilidad(consultarScoringUbicabilidad(correoPersonaDTO.getTipoFuenteInformacion()));
        correoPersonaDTO.setPersona(personaDTO);
        CorreoPersona correoPersona = CorreoPersonaHelper.toLevel1Entity(correoPersonaDTO, null);
        em.persist(correoPersona);
        correoPersonaDTO.setId(correoPersona.getId());
    }

    private void guardarTelefonoPersona(TelefonoPersonaDTO telefonoPersonaDTO, PersonaDTO personaDTO,
            Date fechaActual) {
        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        telefonoPersonaDTO.setFechaRegistro(fechaActual);
        telefonoPersonaDTO.setUsuarioRegistro(usuarioPersonaDTO);
        telefonoPersonaDTO.setUsuarioActualiza(usuarioPersonaDTO);
        telefonoPersonaDTO.setFechaActualizacion(fechaActual);
        telefonoPersonaDTO.setUsuarioValida(usuarioPersonaDTO);
        telefonoPersonaDTO.setFechaValidacion(fechaActual);
        telefonoPersonaDTO
                .setScoreUbicabilidad(consultarScoringUbicabilidad(telefonoPersonaDTO.getTipoFuenteInformacion()));
        telefonoPersonaDTO.setPersona(personaDTO);
        TelefonoPersona telefonoPersona = TelefonoPersonaHelper.toLevel1Entity(telefonoPersonaDTO, null);
        em.persist(telefonoPersona);
        telefonoPersonaDTO.setId(telefonoPersona.getId());
    }

    private void guardarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO, PersonaDTO personaDTO,
            Date fechaActual) {
        // Registro la dirección
        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        RespuestaIngresarDireccionDTO rtaDireccion = this.iLDireccion
                .ingresarDireccion(direccionPersonaDTO.getDireccion(), EnumTipoValidacionDireccion.INFRACTOR);
        direccionPersonaDTO.getDireccion().setId(rtaDireccion.getIdDireccion());
        direccionPersonaDTO.setDireccion(direccionPersonaDTO.getDireccion());
        direccionPersonaDTO.setFechaRegistro(fechaActual);
        direccionPersonaDTO.setUsuarioRegistro(usuarioPersonaDTO);
        direccionPersonaDTO.setUsuarioActualiza(usuarioPersonaDTO);
        direccionPersonaDTO.setFechaActualizacion(fechaActual);
        direccionPersonaDTO.setUsuarioValida(usuarioPersonaDTO);
        direccionPersonaDTO.setFechaValidacion(fechaActual);
        direccionPersonaDTO
                .setScoreUbicabilidad(consultarScoringUbicabilidad(direccionPersonaDTO.getTipoFuenteInformacion()));
        direccionPersonaDTO.setPersona(personaDTO);
        DireccionPersona direccionPersona = DireccionPersonaHelper.toLevel1Entity(direccionPersonaDTO, null);

        // Registro dirección persona
        em.persist(direccionPersona);
        direccionPersonaDTO.setId(direccionPersona.getId());
    }

    private List<CorreoPersonaDTO> actualizarCorreos(List<CorreoPersonaDTO> correoPersonaDTOs,
            List<CorreoPersona> correoPersonas, Date fechaActual) {
        List<CorreoPersonaDTO> correosActulizados = new ArrayList<>();
        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        if (correoPersonas != null && correoPersonaDTOs != null) {
            for (CorreoPersona correoPersona : correoPersonas) {
                for (CorreoPersonaDTO correoPersonaDTO : correoPersonaDTOs) {
                    if (correoPersona.getId().equals(correoPersonaDTO.getId())) {
                        boolean cambio = false;
                        if (correoPersonaDTO.getEstado() != null
                                && !correoPersonaDTO.getEstado().equals(correoPersona.getEstado())) {
                            correoPersona.setEstado(correoPersonaDTO.getEstado());
                            correoPersona.setFechaValidacion(fechaActual);
                            correoPersona
                                    .setUsuarioValida(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            cambio = true;
                        }

                        if ((correoPersonaDTO.getPrioridad() == null && correoPersona.getPrioridad() != null)
                                || (correoPersonaDTO.getPrioridad() != null
                                        && !correoPersonaDTO.getPrioridad().equals(correoPersona.getPrioridad()))) {
                            correoPersona.setPrioridad(correoPersonaDTO.getPrioridad());
                            cambio = true;
                        }

                        if (correoPersonaDTO.getTipoFuenteValidacion() != null
                                && (correoPersona.getTipoFuenteValidacion() == null
                                        || !correoPersonaDTO.getTipoFuenteValidacion().getId()
                                                .equals(correoPersona.getTipoFuenteValidacion().getId()))) {
                            correoPersona.setTipoFuenteValidacion(TipoFuenteInformacionHelper
                                    .toLevel0Entity(correoPersonaDTO.getTipoFuenteValidacion(), null));
                            cambio = true;
                        }

                        if (cambio) {
                            correoPersona.setFechaActualizacion(fechaActual);
                            correoPersona
                                    .setUsuarioActualiza(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            em.merge(correoPersona);
                            correosActulizados.add(CorreoPersonaHelper.toLevel1DTO(correoPersona));
                        }
                    }
                }
            }
        }

        return correosActulizados;
    }

    private List<TelefonoPersonaDTO> actualizarTelefonos(List<TelefonoPersonaDTO> telefonoPersonaDTOs,
            List<TelefonoPersona> telefonoPersonas, Date fechaActual) {
        List<TelefonoPersonaDTO> telefonosActualizados = new ArrayList<>();

        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        if (telefonoPersonas != null && telefonoPersonaDTOs != null) {
            for (TelefonoPersona telefonoPersona : telefonoPersonas) {
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonoPersonaDTOs) {
                    if (telefonoPersona.getId().equals(telefonoPersonaDTO.getId())) {
                        boolean cambio = false;
                        if (telefonoPersonaDTO.getEstado() != null
                                && !telefonoPersonaDTO.getEstado().equals(telefonoPersona.getEstado())) {
                            telefonoPersona.setEstado(telefonoPersonaDTO.getEstado());
                            telefonoPersona.setFechaValidacion(fechaActual);
                            telefonoPersona
                                    .setUsuarioValida(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            cambio = true;
                        }

                        if ((telefonoPersonaDTO.getPrioridad() == null && telefonoPersona.getPrioridad() != null)
                                || (telefonoPersonaDTO.getPrioridad() != null
                                        && !telefonoPersonaDTO.getPrioridad().equals(telefonoPersona.getPrioridad()))) {
                            telefonoPersona.setPrioridad(telefonoPersonaDTO.getPrioridad());
                            cambio = true;
                        }

                        if (telefonoPersonaDTO.getTipoFuenteValidacion() != null
                                && (telefonoPersona.getTipoFuenteValidacion() == null
                                        || !telefonoPersonaDTO.getTipoFuenteValidacion().getId()
                                                .equals(telefonoPersona.getTipoFuenteValidacion().getId()))) {
                            telefonoPersona.setTipoFuenteValidacion(TipoFuenteInformacionHelper
                                    .toLevel0Entity(telefonoPersonaDTO.getTipoFuenteValidacion(), null));
                            cambio = true;
                        }

                        if (cambio) {
                            telefonoPersona.setFechaActualizacion(fechaActual);
                            telefonoPersona
                                    .setUsuarioActualiza(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            em.merge(telefonoPersona);
                            telefonosActualizados.add(TelefonoPersonaHelper.toLevel1DTO(telefonoPersona));
                        }
                    }
                }
            }
        }

        return telefonosActualizados;
    }

    private List<DireccionPersonaDTO> actualizarDireccionPersona(List<DireccionPersonaDTO> direccionPersonaDTOs,
            List<DireccionPersona> direccionPersonas, Date fechaActual) {
        List<DireccionPersonaDTO> direccionPersonaActualizados = new ArrayList<>();

        UsuarioPersonaDTO usuarioPersonaDTO = iRSeguridadCirculemos.obtenerUsuarioDto();
        if (direccionPersonas != null && direccionPersonaDTOs != null) {
            for (DireccionPersona direccionPersona : direccionPersonas) {
                for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaDTOs) {
                    if (direccionPersona.getId().equals(direccionPersonaDTO.getId())) {
                        boolean cambio = false;
                        if (direccionPersonaDTO.getEstado() != null
                                && !direccionPersonaDTO.getEstado().equals(direccionPersona.getEstado())) {
                            direccionPersona.setEstado(direccionPersonaDTO.getEstado());
                            direccionPersona
                                    .setUsuarioValida(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            direccionPersona.setFechaValidacion(fechaActual);
                            cambio = true;
                        }

                        if ((direccionPersonaDTO.getPrioridad() == null && direccionPersona.getPrioridad() != null)
                                || (direccionPersonaDTO.getPrioridad() != null && !direccionPersonaDTO.getPrioridad()
                                        .equals(direccionPersona.getPrioridad()))) {
                            direccionPersona.setPrioridad(direccionPersonaDTO.getPrioridad());
                            cambio = true;
                        }

                        if (direccionPersonaDTO.getTipoFuenteValidacion() != null
                                && (direccionPersona.getTipoFuenteValidacion() == null
                                        || !direccionPersonaDTO.getTipoFuenteValidacion().getId()
                                                .equals(direccionPersona.getTipoFuenteValidacion().getId()))) {
                            direccionPersona.setTipoFuenteValidacion(TipoFuenteInformacionHelper
                                    .toLevel0Entity(direccionPersonaDTO.getTipoFuenteValidacion(), null));
                            cambio = true;
                        }

                        if (cambio) {
                            direccionPersona.setFechaActualizacion(fechaActual);
                            direccionPersona
                                    .setUsuarioActualiza(UsuarioPersonaHelper.toLevel0Entity(usuarioPersonaDTO, null));
                            em.merge(direccionPersona);
                            direccionPersonaActualizados.add(DireccionPersonaHelper.toLevel1DTO(direccionPersona));
                        }
                    }
                }
            }
        }

        return direccionPersonaActualizados;
    }

    private CorreoPersonaHistorico guardarCorreoPersonaHistorico(CorreoPersonaDTO correoPersonaDTO) {
        CorreoPersonaHistoricoDTO correoPersoaHistoricoDTO = CorreoPersonaHistoricoHelperExtend
                .toCorreoPersonaHistoricoDTO(correoPersonaDTO);
        CorreoPersonaHistorico correoPersonaHistorico = CorreoPersonaHistoricoHelper
                .toLevel1Entity(correoPersoaHistoricoDTO, null);
        em.persist(correoPersonaHistorico);
        return correoPersonaHistorico;
    }

    private TelefonoPersonaHistorico guardarTelefonoPersonaHistorico(TelefonoPersonaDTO telefonoPersonaDTO) {
        TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO = TelefonoPersonaHistoricoHelperExtend
                .toTelefonoPersonaHistoricoDTO(telefonoPersonaDTO);
        TelefonoPersonaHistorico telefonoPersonaHistorico = TelefonoPersonaHistoricoHelper
                .toLevel1Entity(telefonoPersonaHistoricoDTO, null);
        em.persist(telefonoPersonaHistorico);
        return telefonoPersonaHistorico;
    }

    private DireccionPersonaHistorico guardarDireccionPersonaHistorico(DireccionPersonaDTO direccionPersonaDTO) {
        DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = DireccionPersonaHistoricoHelperExtend
                .toDireccionPersonaHistoricoDTO(direccionPersonaDTO);

        DireccionPersonaHistorico direccionPersonaHistorico = DireccionPersonaHistoricoHelper
                .toLevel1Entity(direccionPersonaHistoricoDTO, null);
        em.persist(direccionPersonaHistorico);
        return direccionPersonaHistorico;
    }

    @Override
    public List<CorreoPersonaHistoricoDTO> consultarCorreoPersonaHistorico(
            CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO) {
        logger.debug("PersonaEJB.consultarCorreoPersonaHistorico(CorreoPersonaHistoricoDTO");
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        jpql.append("SELECT cph FROM CorreoPersonaHistorico cph");
        if (correoPersonaHistoricoDTO.getPersonaHistoricos() != null
                && !correoPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
            jpql.append(" JOIN cph.personaHistoricos ph");
        }
        jpql.append(" WHERE 1 = 1");
        if (correoPersonaHistoricoDTO != null) {
            if (correoPersonaHistoricoDTO.getId() != null) {
                jpql.append(" AND cph.idCorreoPersonaHistorico");
                params.put("idCorreoPersonaHistorico", correoPersonaHistoricoDTO.getId());
            } else {
                if (correoPersonaHistoricoDTO.getCorreoPersona() != null) {
                    if (correoPersonaHistoricoDTO.getCorreoPersona().getId() != null) {
                        jpql.append(" AND cph.correoPersona.id = :idCorreoPersona");
                        params.put("idCorreoPersona", correoPersonaHistoricoDTO.getCorreoPersona().getId());
                    }
                }
                if (correoPersonaHistoricoDTO.getPersonaHistoricos() != null
                        && !correoPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
                    if (correoPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId() != null) {
                        jpql.append(" AND ph.id = :idPersonaHistorico");
                        params.put("idPersonaHistorico",
                                correoPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId());
                    }
                }
            }
        }
        jpql.append(" ORDER BY cph.prioridad ASC");

        GenericDao<CorreoPersonaHistorico> correoPersonaHistoricoDao = new GenericDao<>(CorreoPersonaHistorico.class,
                this.em);
        List<CorreoPersonaHistorico> correoPersonaHistoricoList = correoPersonaHistoricoDao.buildAndExecuteQuery(jpql,
                params);

        List<CorreoPersonaHistoricoDTO> correoPersonaHistoricoDTOs = CorreoPersonaHistoricoHelper
                .toListLevel1DTO(correoPersonaHistoricoList);
        return correoPersonaHistoricoDTOs;
    }

    @Override
    public List<TelefonoPersonaHistoricoDTO> consultarTelefonoPersonaHistorico(
            TelefonoPersonaHistoricoDTO telefonoPersonaHistoricoDTO) {
        logger.debug("PersonaEJB.consultarTelefonoPersonaHistorico(TelefonoPersonaHistoricoDTO");
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        jpql.append("SELECT tph FROM TelefonoPersonaHistorico tph");
        if (telefonoPersonaHistoricoDTO.getPersonaHistoricos() != null
                && !telefonoPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
            jpql.append(" JOIN tph.personaHistoricos ph");
        }
        jpql.append(" WHERE 1 = 1");
        if (telefonoPersonaHistoricoDTO != null) {
            if (telefonoPersonaHistoricoDTO.getId() != null) {
                jpql.append(" AND tph.idTelefonoPersonaHistorico");
                params.put("idTelefonoPersonaHistorico", telefonoPersonaHistoricoDTO.getId());
            } else {
                if (telefonoPersonaHistoricoDTO.getTelefonoPersona() != null) {
                    if (telefonoPersonaHistoricoDTO.getTelefonoPersona().getId() != null) {
                        jpql.append(" AND tph.telefonoPersona.id = :idTelefonoPersona");
                        params.put("idTelefonoPersona", telefonoPersonaHistoricoDTO.getTelefonoPersona().getId());
                    }
                }
                if (telefonoPersonaHistoricoDTO.getPersonaHistoricos() != null
                        && !telefonoPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
                    if (telefonoPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId() != null) {
                        jpql.append(" AND ph.id = :idPersonaHistorico");
                        params.put("idPersonaHistorico",
                                telefonoPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId());
                    }
                }
            }
        }
        jpql.append(" ORDER BY tph.prioridad ASC");

        GenericDao<TelefonoPersonaHistorico> telefonoPersonaHistoricoDao = new GenericDao<>(
                TelefonoPersonaHistorico.class, this.em);
        List<TelefonoPersonaHistorico> telefonoPersonaHistoricoList = telefonoPersonaHistoricoDao
                .buildAndExecuteQuery(jpql, params);

        List<TelefonoPersonaHistoricoDTO> telefonoPersonaHistoricoDTOs = TelefonoPersonaHistoricoHelper
                .toListLevel1DTO(telefonoPersonaHistoricoList);
        return telefonoPersonaHistoricoDTOs;

    }

    @Override
    public List<DireccionPersonaHistoricoDTO> consultarDireccionPersonaHistorico(
            DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO) {
        logger.debug("PersonaEJB.consultarDireccionPersonaHistorico(DireccionPersonaHistoricoDTO");
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        jpql.append("SELECT dph FROM DireccionPersonaHistorico dph");
        if (direccionPersonaHistoricoDTO.getPersonaHistoricos() != null
                && !direccionPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
            jpql.append(" JOIN dph.personaHistoricos ph");
        }
        jpql.append(" WHERE 1 = 1");
        if (direccionPersonaHistoricoDTO != null) {
            if (direccionPersonaHistoricoDTO.getId() != null) {
                jpql.append(" AND dph.idDireccionPersonaHistorico");
                params.put("idDireccionPersonaHistorico", direccionPersonaHistoricoDTO.getId());
            } else {
                if (direccionPersonaHistoricoDTO.getDireccionPersona() != null) {
                    if (direccionPersonaHistoricoDTO.getDireccionPersona().getId() != null) {
                        jpql.append(" AND dph.direccionPersona.id = :idDireccionPersona");
                        params.put("idDireccionPersona", direccionPersonaHistoricoDTO.getDireccionPersona().getId());
                    }
                }
                if (direccionPersonaHistoricoDTO.getPersonaHistoricos() != null
                        && !direccionPersonaHistoricoDTO.getPersonaHistoricos().isEmpty()) {
                    if (direccionPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId() != null) {
                        jpql.append(" AND ph.id = :idPersonaHistorico");
                        params.put("idPersonaHistorico",
                                direccionPersonaHistoricoDTO.getPersonaHistoricos().get(0).getId());
                    }
                }
            }
        }
        jpql.append(" ORDER BY dph.prioridad ASC");

        GenericDao<DireccionPersonaHistorico> direccionPersonaHistoricoDao = new GenericDao<>(
                DireccionPersonaHistorico.class, this.em);
        List<DireccionPersonaHistorico> direccionPersonaHistoricoList = direccionPersonaHistoricoDao
                .buildAndExecuteQuery(jpql, params);

        List<DireccionPersonaHistoricoDTO> direccionPersonaHistoricoDTOs = DireccionPersonaHistoricoHelperExtend
                .toListLevel2DTO(direccionPersonaHistoricoList);
        return direccionPersonaHistoricoDTOs;

    }

    @SuppressWarnings("resource")
    @Override
    @Asynchronous
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Future<ConsultaAnalisisUbicResulDTO> consultarAnalisisUbicabilidad(ArchivoTransportableDTO archivoConsulta,
            CargueArchivoDTO cargueArchivo, boolean... camposConsulta) throws CirculemosNegocioException {

        ConsultaAnalisisUbicResulDTO respuestaConsulta = new ConsultaAnalisisUbicResulDTO();

        DateFormat df = new SimpleDateFormat(ConstantesCargaArchivos.FORMATO_FECHA_ARCHIVO);
        String dateStr = df.format(Utilidades.getFechaActual());

        File csvFile = new File(ConstantesCargaArchivos.NOMBRE_ARCHIVO_ANALISIS_CARTERA + dateStr
                + ConstantesCargaArchivos.EXTENSION_CSV);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(csvFile);
            fos.write(archivoConsulta.getContenido());

            consultarDocumentosCSV(csvFile, respuestaConsulta, cargueArchivo, camposConsulta);

            cargarArchivosResultado(respuestaConsulta, cargueArchivo);

            iLCargueMasivo.cerrarCargueArchivo(cargueArchivo);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082001);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082005);
        }

        return new AsyncResult<ConsultaAnalisisUbicResulDTO>(respuestaConsulta);
    }

    @Override
    public List<ArchivoPersonaDTO> consultarArchivosPersona(ArchivoPersonaDTO archivoPersonaDTO,
            boolean soloDocumentos) {
        logger.debug(PersonaEJB.class.getName().concat(":consultarArchivosPersona(ArchivoPersonaDTO, boolean)"));
        List<ArchivoPersonaDTO> archivosPersona = new ArrayList<ArchivoPersonaDTO>();
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<String, Object>();
        jpql.append("SELECT ap FROM ArchivoPersona ap");
        jpql.append(" WHERE 1=1");

        if (archivoPersonaDTO != null) {
            if (archivoPersonaDTO.getId() != null) {
                jpql.append(" AND ap.id = :idArchivo");
                filtros.put("idArchivo", archivoPersonaDTO.getId());
            } else {
                if (archivoPersonaDTO.getPersona() != null && archivoPersonaDTO.getPersona().getId() != null) {
                    jpql.append(" AND ap.persona.id = :idPersona");
                    filtros.put("idPersona", archivoPersonaDTO.getPersona().getId());
                }
                if (archivoPersonaDTO.getTipoArchivoPersona() != null
                        && archivoPersonaDTO.getTipoArchivoPersona().getId() != null) {
                    jpql.append(" AND ap.tipoArchivoPersona.id = :idTipoArchivo");
                    filtros.put("idTipoArchivo", archivoPersonaDTO.getTipoArchivoPersona().getId());
                }
            }
        }
        if (soloDocumentos) {
            jpql.append(" AND NOT ap.tipoArchivoPersona.id = :idTipoArchivoNoUbicabilidad");
            filtros.put("idTipoArchivoNoUbicabilidad", EnumTipoArchivoPersona.NO_UBICABILIDAD.getValue());
        }
        jpql.append(" ORDER BY ap.id DESC");

        GenericDao<ArchivoPersona> archivoPersonaDao = new GenericDao<>(ArchivoPersona.class, this.em);
        List<ArchivoPersona> archivoPersonaList = archivoPersonaDao.buildAndExecuteQuery(jpql, filtros);

        if (archivoPersonaList != null) {
            archivosPersona = ArchivoPersonaHelper.toListLevel1DTO(archivoPersonaList);
        }
        return archivosPersona;
    }

    @Override
    public ArchivoPersonaDTO registrarArchivoPersona(ArchivoPersonaDTO archivoPersonaDTO,
            ArchivoTransportableDTO archivoTransportableDTO, boolean noUbicabilidad) throws CirculemosNegocioException {
        logger.debug(PersonaEJB.class.getName()
                .concat(":registrarArchivoPersona(ArchivoPersonaDTO, ArchivoTransportableDTO, boolean)"));
        // Generar documento con archivo cargado
        try {
            // Si es un archivo de no ubicabilidad
            if (noUbicabilidad) {
                String idDocumento = irRepositorioArchivo
                        .registrarDocumento(EnumCategoriaDocumento.ARCHIVO_NO_UBICABILIDAD, archivoTransportableDTO);
                archivoPersonaDTO.setNumeroArchivo(idDocumento);

            } else { // Si es un documento de la persona

            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(e.getErrorInfo());
        }
        archivoPersonaDTO.setUsuarioRegistro(iRSeguridadCirculemos.obtenerUsuarioDto());
        ArchivoPersona archivoPersona = ArchivoPersonaHelper.toLevel1Entity(archivoPersonaDTO, null);
        em.persist(archivoPersona);
        archivoPersonaDTO.setId(archivoPersona.getId());

        return archivoPersonaDTO;
    }

}