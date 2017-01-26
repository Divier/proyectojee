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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.dto.ValidarIncosistenciaAgenteDTO;
import co.com.datatools.c2.dto.FuncionarioDocumentoDTO;
import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.DetalleHistoricoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.HistoricoAgenteDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.Agente;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.HistoricoAgente;
import co.com.datatools.c2.entidades.MotivoVigenciaAgente;
import co.com.datatools.c2.entidades.TipoInconsistenciaAgente;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.enumeracion.EnumMedioImposicion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.enumeraciones.EnumTipoInconsistenciaAgente;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.utilidades.AgenteUtil;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.error.ErrorAgente;
import co.com.datatools.c2.negocio.error.ErrorComparendo.ActualizarAgenteComparendo;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.AgenteHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.HistoricoAgenteHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.MotivoVigenciaAgenteHelper;
import co.com.datatools.c2.negocio.interfaces.ILAgente;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRSincronizacionDocumentos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.util.IRCirculemosMailSender;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * @author luis.forero (2015-10-13)
 * 
 */
@Stateless(name = "AgenteEJB")
@LocalBean
public class AgenteEJB implements IRAgente, ILAgente {

    private final static Logger logger = Logger.getLogger(AgenteEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCirculemosMailSender iRCirculemosMailSender;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;

    @EJB
    private ILAgente iLAgente;

    @EJB
    private IRRepositorioArchivo iReposArchivoEjb;

    @EJB
    private IRSincronizacionDocumentos iRSincDocumentos;

    @EJB
    private IRPersona iRPersona;

    @EJB
    private IRParametro iRParametro;

    @EJB
    private IRCatalogo iRCatalogo;

    private final String NOMBRE_ARCHIVO = "Reporte inconsistencia agentes.xls";

    @Override
    public List<AgenteDTO> consultarAgente(AgenteDTO agenteDTO) {
        logger.debug("AgenteEJB::consultarAgente(AgenteDTO)");
        List<AgenteDTO> resultaAgenteList = new ArrayList<>();

        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT a FROM Agente AS a");
        jpql.append(" JOIN FETCH a.persona AS p");
        jpql.append(" JOIN FETCH a.organismoTransito AS ot");
        jpql.append(" LEFT JOIN FETCH p.personaJuridica AS pj");
        jpql.append(" LEFT JOIN FETCH p.tipoIdentificacion ti");

        jpql.append(" WHERE 1=1");
        if (agenteDTO.getOrganismoTransito() != null && agenteDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
            jpql.append(" AND ot.codigoOrganismo = :pCodOrg");
            filtros.put("pCodOrg", agenteDTO.getOrganismoTransito().getCodigoOrganismo());
        }

        PersonaDTO persona = agenteDTO.getPersona();
        if (persona != null) {
            if (persona.getTipoIdentificacion() != null && persona.getTipoIdentificacion().getId() != null) {
                jpql.append(" AND ti.id = :pIdTipIde");
                filtros.put("pIdTipIde", persona.getTipoIdentificacion().getId());
            }
            if (StringUtils.isNotBlank(persona.getNumeroIdentificacion())) {
                jpql.append(" AND p.numeroIdentificacion = :pNumIden");
                filtros.put("pNumIden", persona.getNumeroIdentificacion());
            }
        }
        if (StringUtils.isNotBlank(agenteDTO.getPlaca())) {
            jpql.append(" AND a.placa = :pPlaca");
            filtros.put("pPlaca", agenteDTO.getPlaca());
        }

        GenericDao<Agente> dao = new GenericDao<>(Agente.class, em);
        List<Agente> result = dao.buildAndExecuteQuery(jpql, filtros);

        AgenteDTO agenteDTOAux = null;

        for (Agente agente : result) {
            agenteDTOAux = AgenteHelper.toLevel1DTO(agente);
            agenteDTOAux.setPersona(ProcesarPersonasHelper.toPersonaBasicaDTO(agente.getPersona()));
            resultaAgenteList.add(agenteDTOAux);
        }

        return resultaAgenteList;
    }

    @Override
    public List<ConsultaComparendoAgenteDTO> consultarComparendoAgenteComparendo() {
        List<ConsultaComparendoAgenteDTO> comparendoDTOS = new ArrayList<ConsultaComparendoAgenteDTO>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT co.cicomparendo, co.fecha_infraccion , co.hora_infraccion, ");
        sql.append("co.id_factura_axis, co.placa_agente_transito, a.id_agente, a.id_persona, ");
        sql.append("p.id_tipo_identificacion, p.numero_identificacion, p.nombre1, ");
        sql.append("p.nombre2, p.apellido1, p.apellido2 ");
        sql.append("FROM comparendo co ");
        sql.append("LEFT JOIN agente a on co.placa_agente_transito = a.placa_agente_transito ");
        sql.append("LEFT JOIN persona p on a.id_persona = p.id_persona ");
        sql.append("WHERE co.codigo_medio_imposicion = :medioImposicion ");
        sql.append("AND NOT EXISTS (SELECT 1 FROM comparendo_agente cp ");
        sql.append("WHERE cp.cicomparendo = co.cicomparendo )  ");

        Query query = em.createNativeQuery(sql.toString());
        query.setParameter("medioImposicion", EnumMedioImposicion.FOTOMULTA.getValue());

        List<Object[]> datos = Utilidades.safeList(query.getResultList());
        if (!datos.isEmpty()) {
            for (Object[] dato : datos) {
                ConsultaComparendoAgenteDTO compaDTO = new ConsultaComparendoAgenteDTO();
                compaDTO.setCicomparendo(Long.parseLong(dato[0].toString()));
                compaDTO.setFechaInfraccion((Date) dato[1]);
                compaDTO.setHoraInfraccion((Date) dato[2]);
                compaDTO.setIdFacturaAxis(Long.parseLong(dato[3].toString()));
                compaDTO.setPlacaAgenteTransito((String) dato[4]);
                compaDTO.setIdAgente((Integer) dato[5]);
                if (dato[6] != null) {
                    compaDTO.setIdPersona(Long.parseLong(dato[6].toString()));
                }

                compaDTO.setIdTipoIdentificacion((Integer) dato[8]);
                compaDTO.setNumeroIdentificacion((String) dato[9]);
                compaDTO.setNombre1((String) dato[10]);
                compaDTO.setNombre2((String) dato[11]);
                compaDTO.setApellido1((String) dato[12]);
                compaDTO.setApellido2((String) dato[13]);

                comparendoDTOS.add(compaDTO);

            }
        }
        return comparendoDTOS;
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void insertarComparendoAgente(ConsultaComparendoAgenteDTO consultaComparendoAgenteDTO) {

        // Valida obligatoriedad de datos
        checkNotNull(consultaComparendoAgenteDTO.getPlacaAgenteTransito(),
                ErrorAgente.AgregarComparendoAgente.COM_089006);
        checkNotNull(consultaComparendoAgenteDTO.getCicomparendo(), ErrorAgente.AgregarComparendoAgente.COM_089001);

        // Crea objeto de agente y comparendo
        Agente agente = new Agente();
        Comparendo comparendo = new Comparendo();
        agente.setId(consultaComparendoAgenteDTO.getIdAgente());
        comparendo.setCicomparendo(consultaComparendoAgenteDTO.getCicomparendo());
        TipoIdentificacionPersona tipoIdentificacionPersona = new TipoIdentificacionPersona();
        tipoIdentificacionPersona.setId(consultaComparendoAgenteDTO.getIdTipoIdentificacion());

        // Se cargan los datos para comparendoAgente
        ComparendoAgente comparendoAgente = new ComparendoAgente();
        comparendoAgente.setComparendo(comparendo);
        comparendoAgente.setPlaca(consultaComparendoAgenteDTO.getPlacaAgenteTransito());
        comparendoAgente.setTipoIdentificacionPersona(tipoIdentificacionPersona);
        comparendoAgente.setNumeroIdentificacion(consultaComparendoAgenteDTO.getNumeroIdentificacion());
        comparendoAgente.setNombre1(consultaComparendoAgenteDTO.getNombre1());
        comparendoAgente.setNombre2(consultaComparendoAgenteDTO.getNombre2());
        comparendoAgente.setApellido1(consultaComparendoAgenteDTO.getApellido1());
        comparendoAgente.setApellido2(consultaComparendoAgenteDTO.getApellido2());
        comparendoAgente.setAgente(agente);

        // Crea el Comparendo Agente para el comparendo
        em.persist(comparendoAgente);

    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public ArrayList<Integer> validarAgente(OrganismoTransitoDTO organismo) {
        ArrayList<Integer> resultadoJob = null;
        // Consulta los comparendos que se validaran
        List<ConsultaComparendoAgenteDTO> comparendos = iLAgente.consultarComparendoAgenteComparendo();
        if (!comparendos.isEmpty()) {
            resultadoJob = new ArrayList<Integer>();
            int sinAgente = 0;
            int agenteNoEncontrado = 0;
            int agenteNoVigente = 0;
            // Formato fecha
            String formato = iRParametro.consultarValorParametro(EnumParametro.FORMATO_FECHA,
                    iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());

            // Parametro de registro en comparendo agente
            ValorParametroDTO agregarComparendoAgente = fachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.INSERTAR_COMPARENDO_AGENTE, organismo.getCodigoOrganismo(), true);

            Date fecha = UtilFecha.buildCalendar().getTime();
            List<ValidarIncosistenciaAgenteDTO> inconsistencias = new ArrayList<ValidarIncosistenciaAgenteDTO>();
            HashMap<Integer, String> tipoIA = consultarTipoInconsistencia();

            for (ConsultaComparendoAgenteDTO compa : comparendos) {

                // Valida si la placa agente es nula
                if (compa.getPlacaAgenteTransito() != null) {

                    // Valida si existe el agente
                    if (compa.getPlacaAgenteTransito() != null) {
                        // Valida el estado del agente
                        if (compa.getEstado()) {

                            // Valida el parametro para insertar comparendoAgente
                            if (agregarComparendoAgente.getValorParamBoolean()) {
                                try {
                                    // Inserta el comparendo agente
                                    iLAgente.insertarComparendoAgente(compa);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        } else {
                            // Si el agente no esta vigente
                            inconsistencias.add(crearObjetoInconsistencia(compa, formato,
                                    tipoIA.get(EnumTipoInconsistenciaAgente.AGENTE_NO_VIGENTE.getCodigo()), fecha));
                            agenteNoVigente++;
                        }
                    } else {
                        // Si el agente no existe
                        inconsistencias.add(crearObjetoInconsistencia(compa, formato,
                                tipoIA.get(EnumTipoInconsistenciaAgente.AGENTE_NO_REGISTRADO.getCodigo()), fecha));
                        agenteNoEncontrado++;
                    }

                } else {
                    // Si la placa es nula
                    inconsistencias.add(crearObjetoInconsistencia(compa, formato,
                            tipoIA.get(EnumTipoInconsistenciaAgente.MULTA_NO_TIENE_AGENTE.getCodigo()), fecha));
                    sinAgente++;
                }
            }

            resultadoJob.add(agenteNoEncontrado);
            resultadoJob.add(agenteNoVigente);
            resultadoJob.add(sinAgente);
            // Valida el parametro para enviar el correo de inconsistencias
            ValorParametroDTO enviarCorreoInconsistencia = fachadaAdminGeneral.consultarValorParametro(
                    EnumParametro.REGISTRAR_ENVIO_CORREO_VALIDAR_AGENTE, organismo.getCodigoOrganismo(), true);

            // Valida si debe enviar el correo
            if (enviarCorreoInconsistencia.getValorParamBoolean() && !inconsistencias.isEmpty()) {

                byte[] archivo = AgenteUtil.generaArchivo(inconsistencias);
                ArrayList<ArchivoTransportableDTO> adjuntos = new ArrayList<ArchivoTransportableDTO>();
                ArchivoTransportableDTO archivos = new ArchivoTransportableDTO();
                if (archivo != null) {
                    archivos.setContenido(archivo);
                }
                archivos.setNombre(NOMBRE_ARCHIVO);
                adjuntos.add(archivos);
                // Envio de correo
                iRCirculemosMailSender.enviarCorreo(organismo.getCodigoOrganismo(),
                        EnumTipoCorreo.ENVIO_INCONSISTENCIA_AGENTE, null, null, adjuntos);
            }
        }

        return resultadoJob;

    }

    /**
     * Crea el objeto de inconsistencia
     * 
     * @param compa
     * @param formato
     * @param descripcionInconsistencia
     * @return
     */
    private ValidarIncosistenciaAgenteDTO crearObjetoInconsistencia(ConsultaComparendoAgenteDTO compa, String formato,
            String descripcionInconsistencia, Date fecha) {
        ValidarIncosistenciaAgenteDTO validar = new ValidarIncosistenciaAgenteDTO();
        validar.setIdFacturaAxis(compa.getIdFacturaAxis());
        validar.setFechaImposicion(UtilFecha.dateToString(compa.getFechaInfraccion(), formato));
        validar.setHoraImposicion(UtilFecha.dateToString(compa.getHoraInfraccion(), formato));
        validar.setInconsistencia(descripcionInconsistencia);
        validar.setPlacaAgenteTransito(compa.getPlacaAgenteTransito());
        validar.setFechaValidacion(UtilFecha.dateToString(fecha, formato));

        return validar;
    }

    @Override
    public void registrarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException {
        logger.debug("AgenteEJB::consultarAgente(AgenteDTO)");
        try {
            if (agenteDTO != null) {
                Agente agente = AgenteHelper.toLevel1Entity(agenteDTO, null);
                em.persist(agente);
                HistoricoAgente histoAgente = new HistoricoAgente();
                histoAgente.setAgente(agente);
                histoAgente.setEntidadAgenteTransito(agente.getEntidadAgenteTransito().getId());
                // histoAgente.setEstado(agente.getEstado());
                histoAgente.setFechaActualizacion(agente.getFechaActualizacion());
                histoAgente.setFechaInicioVigencia(agente.getFechaInicioVigencia());
                histoAgente.setOrganismoTransito(agente.getOrganismoTransito().getCodigoOrganismo());
                histoAgente.setPersona(agente.getPersona().getId());
                histoAgente.setPlaca(agente.getPlaca());
                histoAgente.setUsuarioActualizacion(agente.getUsuarioActualizacion().getIdUsuario());
                em.persist(histoAgente);

                FuncionarioDocumentoDTO funciDocumento = new FuncionarioDocumentoDTO();
                funciDocumento.setFechaFinalCargo(null);
                funciDocumento.setFechaFinalFuncionario(null);
                funciDocumento.setFechaInicialCargo(agenteDTO.getFechaInicioVigencia());
                funciDocumento.setFechaInicialFuncionario(agenteDTO.getFechaInicioVigencia());

                ArchivoTransportableDTO archivoFirma = iReposArchivoEjb
                        .consultarDocumento(agenteDTO.getNumeroFirma().toString());
                funciDocumento.setFirma(archivoFirma.getContenido());
                funciDocumento.setNombre(agenteDTO.getPersona().getNombreCompleto());
                funciDocumento.setTipoIdentificacion(agenteDTO.getPersona().getTipoIdentificacion());
                funciDocumento.setNumeroIdentificacion(agenteDTO.getPersona().getNumeroIdentificacion());
                iRSincDocumentos.registrarAgente(funciDocumento);
            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.AdminisitracionAgentes.ADM_071001);
        }
    }

    @Override
    public void actualizarAgente(AgenteDTO agenteDTO) throws CirculemosNegocioException {
        logger.debug("AgenteEJB::actualizarAgente(AgenteDTO)");
        try {
            if (agenteDTO != null) {
                Agente agente = em.find(Agente.class, agenteDTO.getId());
                agente = AgenteHelper.toLevel1Entity(agenteDTO, agente);

                if (agenteDTO.getMotivoVigenciaAgente() == null) {
                    agente.setMotivoVigenciaAgente(null);
                }
                em.merge(agente);

                HistoricoAgenteDTO histoAgenteDTO = new HistoricoAgenteDTO();
                histoAgenteDTO.setAgente(agenteDTO);
                histoAgenteDTO.setEntidadAgenteTransito(agenteDTO.getEntidadAgenteTransito().getId());
                // histoAgenteDTO.setEstado(agenteDTO.getEstado());
                histoAgenteDTO.setFechaActualizacion(agenteDTO.getFechaActualizacion());
                histoAgenteDTO.setFechaInicioVigencia(agenteDTO.getFechaInicioVigencia());
                histoAgenteDTO.setFechaFinVigencia(agenteDTO.getFechaFinVigencia());
                histoAgenteDTO.setOrganismoTransito(agenteDTO.getOrganismoTransito().getCodigoOrganismo());
                histoAgenteDTO.setPersona(agenteDTO.getPersona().getId());
                histoAgenteDTO.setPlaca(agenteDTO.getPlaca());
                histoAgenteDTO.setUsuarioActualizacion(agenteDTO.getUsuarioActualizacion().getUsuario().getId());

                if (agenteDTO.getMotivoVigenciaAgente() != null) {
                    histoAgenteDTO.setMotivoVigenciaAgente(agenteDTO.getMotivoVigenciaAgente().getId());
                } else {
                    histoAgenteDTO.setMotivoVigenciaAgente(null);
                }

                HistoricoAgente histoAgente = HistoricoAgenteHelper.toLevel1Entity(histoAgenteDTO, null);
                em.persist(histoAgente);

                FuncionarioDocumentoDTO funciDocumento = new FuncionarioDocumentoDTO();
                funciDocumento.setFechaFinalCargo(agenteDTO.getFechaFinVigencia());
                funciDocumento.setFechaFinalFuncionario(agenteDTO.getFechaFinVigencia());
                funciDocumento.setFechaInicialCargo(agenteDTO.getFechaInicioVigencia());
                funciDocumento.setFechaInicialFuncionario(agenteDTO.getFechaInicioVigencia());

                ArchivoTransportableDTO archivoFirma = iReposArchivoEjb
                        .consultarDocumento(agenteDTO.getNumeroFirma().toString());
                funciDocumento.setFirma(archivoFirma.getContenido());
                funciDocumento.setNombre(agenteDTO.getPersona().getNombreCompleto());
                funciDocumento.setTipoIdentificacion(agenteDTO.getPersona().getTipoIdentificacion());
                funciDocumento.setNumeroIdentificacion(agenteDTO.getPersona().getNumeroIdentificacion());
                iRSincDocumentos.actualizarAgente(funciDocumento);
            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorAdministracion.AdminisitracionAgentes.ADM_071001);
        }
    }

    /**
     * Consulta los tipos de inconsistencia
     * 
     * @return
     */
    private HashMap<Integer, String> consultarTipoInconsistencia() {
        HashMap<Integer, String> inconsistenciaAgente = new HashMap<Integer, String>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t from TipoInconsistenciaAgente t");

        GenericDao<TipoInconsistenciaAgente> dao = new GenericDao<>(TipoInconsistenciaAgente.class, em);
        List<TipoInconsistenciaAgente> result = dao.buildAndExecuteQuery(sql, null);

        for (TipoInconsistenciaAgente tipoIncoAgen : result) {
            inconsistenciaAgente.put(tipoIncoAgen.getId(), tipoIncoAgen.getDescripcion());
        }

        return inconsistenciaAgente;

    }

    @Override
    public List<DetalleHistoricoAgenteDTO> consultarHistoricoAgente(Integer organismoTransito, String placa) {
        logger.debug("consultarHistoricoAgente(Integer,String)");
        List<DetalleHistoricoAgenteDTO> detalleHistoricoAgenteDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT ha FROM HistoricoAgente ha ");
        jpql.append("WHERE 1=1 ");

        if (organismoTransito != null) {
            jpql.append("AND ha.organismoTransito = :organismoTransito ");
        }

        if (placa != null) {
            jpql.append("AND ha.placa = :placa ");
        }

        jpql.append("ORDER BY ha.fechaActualizacion DESC");

        Query query = em.createQuery(jpql.toString());
        if (organismoTransito != null) {
            query.setParameter("organismoTransito", organismoTransito);
        }

        if (placa != null) {
            query.setParameter("placa", placa);
        }

        @SuppressWarnings("unchecked")
        List<HistoricoAgente> historicoAgentes = query.getResultList();
        if (historicoAgentes != null && !historicoAgentes.isEmpty()) {
            detalleHistoricoAgenteDTOs = new ArrayList<>();
            for (HistoricoAgente historicoAgente : historicoAgentes) {
                DetalleHistoricoAgenteDTO detalleHistoricoAgenteDTO = new DetalleHistoricoAgenteDTO();
                detalleHistoricoAgenteDTO.setHistoricoAgenteDTO(HistoricoAgenteHelper.toLevel1DTO(historicoAgente));

                // Persona
                detalleHistoricoAgenteDTO.setPersonaDTO(iRPersona.consultarPersona(historicoAgente.getPersona()));

                // Motivo vigencia agente
                if (historicoAgente.getMotivoVigenciaAgente() != null) {
                    detalleHistoricoAgenteDTO.setMotivoVigenciaAgenteDTO(
                            consultarMotivoVigenciaAgente(historicoAgente.getMotivoVigenciaAgente()));
                }

                detalleHistoricoAgenteDTOs.add(detalleHistoricoAgenteDTO);
            }
        }

        return detalleHistoricoAgenteDTOs;
    }

    /**
     * Consulta motivoVigenciaAgente por id
     * 
     * @param id
     * @return
     * @author giovanni.velandia
     */
    private MotivoVigenciaAgenteDTO consultarMotivoVigenciaAgente(Integer id) {
        logger.debug("consultarMotivoVigenciaAgente(Integer)");
        MotivoVigenciaAgente motivoVigenciaAgente = em.find(MotivoVigenciaAgente.class, id);
        return MotivoVigenciaAgenteHelper.toLevel1DTO(motivoVigenciaAgente);
    }

    @Override
    public AgenteDTO consultarAgenteValido(AgenteDTO agenteDTO) throws CirculemosNegocioException {
        logger.debug("AgenteEJB::consultarAgenteValido(AgenteDTO)");

        List<AgenteDTO> agentes = this.consultarAgente(agenteDTO);
        if (agentes.isEmpty()) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091005);
        } else if (agentes.size() > 1) {
            throw new CirculemosNegocioException(ActualizarAgenteComparendo.COM_091007);
        } else {
            return agentes.get(0);
        }
    }

    @Override
    public boolean vigenciaAgente(AgenteDTO agenteDTO) {
        boolean estado = true;
        Date fechaAcTual = Calendar.getInstance().getTime();

        if (agenteDTO.getFechaInicioVigencia().before(fechaAcTual)) {
            if (agenteDTO.getFechaFinVigencia() == null) {
                estado = true;
            } else {
                if (agenteDTO.getFechaFinVigencia().after(fechaAcTual)) {
                    estado = true;
                } else {
                    estado = false;
                }
            }
        } else {
            estado = false;
        }

        return estado;
    }

}