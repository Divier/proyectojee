package co.com.datatools.c2.negocio.ejb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.ItUbicabilidadAxisDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoTelefonoDTO;
import co.com.datatools.c2.entidades.ItUbicabilidadAxis;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoTelefono;
import co.com.datatools.c2.enumeracion.EnumTipoUbicabilidad;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoUbicaTercero;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminNegocio;
import co.com.datatools.c2.negocio.helpers.extension.ItUbicabilidadAxisHelperExtend;
import co.com.datatools.c2.negocio.interfaces.ILUbicabilidadTercero;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidadTercero;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.mail.EmailValidator;

/**
 * @see IRUbicabilidadTercero
 * @author divier.casas(2016-05-17)
 */
@Stateless(name = "UbicabilidadTerceroEJB")
@LocalBean
public class UbicabilidadTerceroEJB implements IRUbicabilidadTercero, ILUbicabilidadTercero {

    private final static Logger logger = Logger.getLogger(RecaudoIntegracionEJB.class.getName());

    @PersistenceContext(unitName = "IntegracionTercerosC2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminNegocio iRFachadaAdminNegocio;

    @EJB
    private IRParametro iRParametro;

    @EJB
    private ILUbicabilidadTercero iLUbicabilidadTercero;

    private static final Integer REGISTRO_INICIAL = 0;
    private static final Integer MAXIMO_REGISTROS = 1000;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaSolicitudUbicabilidadTerceroDTO solicitarUbicabilidadTerceros(int codigoOrganismo) {
        logger.debug(UbicabilidadTerceroEJB.class.getName().concat("solicitarUbicabilidadTerceros(int)"));
        RespuestaSolicitudUbicabilidadTerceroDTO rSolicitudUbicabilidadTercero = new RespuestaSolicitudUbicabilidadTerceroDTO();
        List<ItUbicabilidadAxisDTO> lItUbicabilidadAxis = null;
        rSolicitudUbicabilidadTercero.setTotalRegUbicLeidos(BigInteger.ZERO.intValue());
        rSolicitudUbicabilidadTercero.setTotalRegUbicRecibidos(BigInteger.ZERO.intValue());
        rSolicitudUbicabilidadTercero.setTotalRegUbicNoRecibidos(BigInteger.ZERO.intValue());
        do {
            lItUbicabilidadAxis = iLUbicabilidadTercero.consultarUbicabilidadTercero(EnumEstadoLectura.NUEVO);
            if (lItUbicabilidadAxis != null) {
                for (ItUbicabilidadAxisDTO itUbicabilidadAxisDTO : lItUbicabilidadAxis) {
                    rSolicitudUbicabilidadTercero = iLUbicabilidadTercero.procesarUbicabilidadTerceros(
                            rSolicitudUbicabilidadTercero, itUbicabilidadAxisDTO, codigoOrganismo);
                }
            }
        } while (lItUbicabilidadAxis != null);

        return rSolicitudUbicabilidadTercero;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaSolicitudUbicabilidadTerceroDTO procesarUbicabilidadTerceros(
            RespuestaSolicitudUbicabilidadTerceroDTO rSolicitudUbicabilidadTercero,
            ItUbicabilidadAxisDTO itUbicabilidadAxisDTO, int codigoOrganismo) {

        int totalLeidos = 0;
        int totalRecibidos = 0;
        int totalNoRecibidos = 0;

        try {
            PersonaDTO personaDTO = iRFachadaAdminNegocio.consultarPersona(codigoOrganismo,
                    obtenerIdTipoDocumento(itUbicabilidadAxisDTO.getIdTipoDocumentoAxis()),
                    itUbicabilidadAxisDTO.getNumeroDocumento());
            if (personaDTO == null) {
                personaDTO = enlazarDatosPersona(codigoOrganismo, itUbicabilidadAxisDTO);
                Long id = iRFachadaAdminNegocio.registrarPersona(personaDTO);
                if (id != null && id.compareTo(BigInteger.ZERO.longValue()) > 0) {
                    iLUbicabilidadTercero.actualizarEstadoUbicabilidad(itUbicabilidadAxisDTO.getId(),
                            EnumEstadoLectura.RECIBIDO);
                    totalRecibidos++;
                }
            } else {
                ValorParametroDTO valorParametroDTO = iRParametro.consultarParametro(
                        EnumParametro.PERMITIR_REGISTROS_REPETIDOS_UBICABILIDAD, codigoOrganismo, true);

                // consulta los correos asociados a la persona
                CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
                correoPersonaDTO.setPersona(personaDTO);

                personaDTO.setCorreoPersonaList(iRFachadaAdminNegocio.consultarCorreoPersona(correoPersonaDTO));

                // consulta los telefonos asociados a la persona
                TelefonoPersonaDTO telefonoPersonaDTO = new TelefonoPersonaDTO();
                telefonoPersonaDTO.setPersona(personaDTO);

                personaDTO.setTelefonoPersonaDTOs(iRFachadaAdminNegocio.consultarTelefonoPersona(telefonoPersonaDTO));

                boolean permitirRepetidos = valorParametroDTO.getValorParamBoolean();
                boolean registroExistente = compararDatosUbicabilidad(personaDTO, itUbicabilidadAxisDTO);

                if ((registroExistente && permitirRepetidos) || !registroExistente) {
                    enlazarDatosUbicabilidad(personaDTO, itUbicabilidadAxisDTO);
                    if (iRFachadaAdminNegocio.modificarPersona(personaDTO)) {
                        iLUbicabilidadTercero.actualizarEstadoUbicabilidad(itUbicabilidadAxisDTO.getId(),
                                EnumEstadoLectura.RECIBIDO);
                        totalRecibidos++;
                    }
                } else {
                    personaDTO.setFechaUltimaActualizacion(UtilFecha.buildCalendar().getTime());
                    if (iRFachadaAdminNegocio.modificarPersona(personaDTO)) {
                        iLUbicabilidadTercero.actualizarEstadoUbicabilidad(itUbicabilidadAxisDTO.getId(),
                                EnumEstadoLectura.NO_RECIBIDO);
                        totalNoRecibidos++;
                    }
                }
            }
        } catch (CirculemosNegocioException e) {
            iLUbicabilidadTercero.actualizarEstadoUbicabilidad(itUbicabilidadAxisDTO.getId(),
                    EnumEstadoLectura.NO_RECIBIDO);
            totalNoRecibidos++;
            logger.error(e + " - ID it_ubicabilidad_axis: " + itUbicabilidadAxisDTO.getId());
        } catch (CirculemosIllegalArgumentException e) {
            iLUbicabilidadTercero.actualizarEstadoUbicabilidad(itUbicabilidadAxisDTO.getId(),
                    EnumEstadoLectura.NO_RECIBIDO);
            totalNoRecibidos++;
            logger.error(e + " - ID it_ubicabilidad_axis: " + itUbicabilidadAxisDTO.getId());
        }
        totalLeidos++;
        rSolicitudUbicabilidadTercero
                .setTotalRegUbicLeidos(rSolicitudUbicabilidadTercero.getTotalRegUbicLeidos() + totalLeidos);
        rSolicitudUbicabilidadTercero
                .setTotalRegUbicRecibidos(rSolicitudUbicabilidadTercero.getTotalRegUbicRecibidos() + totalRecibidos);
        rSolicitudUbicabilidadTercero.setTotalRegUbicNoRecibidos(
                rSolicitudUbicabilidadTercero.getTotalRegUbicNoRecibidos() + totalNoRecibidos);
        return rSolicitudUbicabilidadTercero;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstadoUbicabilidad(long idUbicabilidad, EnumEstadoLectura estadoLectura) {
        ItUbicabilidadAxis itUbicabilidadAxis = em.find(ItUbicabilidadAxis.class, idUbicabilidad);
        if (itUbicabilidadAxis != null) {
            itUbicabilidadAxis.setIdEstadoLecturaAxis(estadoLectura.name());
            em.merge(itUbicabilidadAxis);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ItUbicabilidadAxisDTO> consultarUbicabilidadTercero(EnumEstadoLectura estadoLectura) {
        List<ItUbicabilidadAxisDTO> itUbicabilidadAxis = null;

        TypedQuery<ItUbicabilidadAxis> query = em.createNamedQuery(ItUbicabilidadAxis.SQ_FIND_BY_ESTADO_LECTURA,
                ItUbicabilidadAxis.class);
        query.setParameter("pIdEstadoLecturaAxis", estadoLectura.name());
        query.setFirstResult(REGISTRO_INICIAL).setMaxResults(MAXIMO_REGISTROS);

        List<ItUbicabilidadAxis> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            itUbicabilidadAxis = new ArrayList<ItUbicabilidadAxisDTO>(1);
            itUbicabilidadAxis = ItUbicabilidadAxisHelperExtend.toListLevel1DTOExtend(resultList);
        }
        return itUbicabilidadAxis;
    }

    private int obtenerIdTipoDocumento(String tipoDocumento) {
        int id = 0;
        if (tipoDocumento != null) {
            for (EnumTipoDocumentoUbicaTercero tiposDocumentos : EnumTipoDocumentoUbicaTercero.values()) {
                if (tiposDocumentos.name().equals(tipoDocumento)) {
                    id = tiposDocumentos.getValue();
                }
            }
        }
        return id;
    }

    private PersonaDTO enlazarDatosPersona(int codigoOrganismo, ItUbicabilidadAxisDTO itUbicabilidadAxisDTO) {
        PersonaDTO personaDTO = null;
        if (itUbicabilidadAxisDTO != null) {
            personaDTO = new PersonaDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO = new TipoIdentificacionPersonaDTO();
            tipoIdentificacionPersonaDTO.setId(obtenerIdTipoDocumento(itUbicabilidadAxisDTO.getIdTipoDocumentoAxis()));

            if (itUbicabilidadAxisDTO.getIdTipoDocumentoAxis().equals(EnumTipoDocumentoUbicaTercero.RUC.name())) {
                PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();
                personaJuridicaDTO.setNombreComercial(itUbicabilidadAxisDTO.getRazonSocial());
                personaDTO = personaJuridicaDTO;
            }

            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            organismoTransitoDTO.setCodigoOrganismo(codigoOrganismo);
            personaDTO.setOrganismoTransito(organismoTransitoDTO);
            personaDTO.setTipoIdentificacion(tipoIdentificacionPersonaDTO);
            personaDTO.setNumeroIdentificacion(itUbicabilidadAxisDTO.getNumeroDocumento());
            personaDTO.setNombre1(itUbicabilidadAxisDTO.getPrimerNombre());
            personaDTO.setNombre2(itUbicabilidadAxisDTO.getSegundoNombre());
            personaDTO.setApellido1(itUbicabilidadAxisDTO.getPrimerApellido());
            personaDTO.setApellido2(itUbicabilidadAxisDTO.getSegundoApellido());
            DireccionDTO direccionDTO = new DireccionDTO();
            TipoViaDTO tipoViaDTO = new TipoViaDTO();
            tipoViaDTO.setCodigo(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
            direccionDTO.setTipoViaPrincipal(tipoViaDTO);
            direccionDTO.setComplemento(itUbicabilidadAxisDTO.getDireccion());
            direccionDTO.setLatitud(itUbicabilidadAxisDTO.getLatitud());
            direccionDTO.setLongitud(itUbicabilidadAxisDTO.getLongitud());
            TipoUbicabilidadDTO tipoUbicabilidadDTO = new TipoUbicabilidadDTO();
            tipoUbicabilidadDTO.setId(EnumTipoUbicabilidad.RESIDENCIA.getPk());
            tipoUbicabilidadDTO.setNombre(EnumTipoUbicabilidad.RESIDENCIA.name());
            direccionDTO.setTipoUbicabilidad(tipoUbicabilidadDTO);
            TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
            tipoFuenteInformacionDTO.setId(EnumTipoFuenteInformacion.AXIS.getValue());
            tipoFuenteInformacionDTO.setNombre(EnumTipoFuenteInformacion.AXIS.name());
            tipoFuenteInformacionDTO.setEstado(true);
            DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
            direccionPersonaDTO.setDireccion(direccionDTO);
            direccionPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacionDTO);
            direccionPersonaDTO.setFechaRegistro(UtilFecha.buildCalendar().getTime());
            List<DireccionPersonaDTO> lsDireccionPersonaDTO = new ArrayList<>();
            lsDireccionPersonaDTO.add(direccionPersonaDTO);
            personaDTO.setDireccionPersonaList(lsDireccionPersonaDTO);

            // adición de telefonos a la persona
            List<TelefonoPersonaDTO> telefonoPersonaDTOs = new ArrayList<>();

            if (itUbicabilidadAxisDTO.getTelefonoFijo() != null && !itUbicabilidadAxisDTO.getTelefonoFijo().isEmpty()) {
                telefonoPersonaDTOs.add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_FIJO,
                        itUbicabilidadAxisDTO.getTelefonoFijo(), tipoFuenteInformacionDTO));
            }

            if (itUbicabilidadAxisDTO.getTelefonoMovil() != null
                    && !itUbicabilidadAxisDTO.getTelefonoMovil().isEmpty()) {
                telefonoPersonaDTOs.add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_CELULAR,
                        itUbicabilidadAxisDTO.getTelefonoMovil(), tipoFuenteInformacionDTO));
            }

            personaDTO.setTelefonoPersonaDTOs(telefonoPersonaDTOs);

            // adición de correos a la persona
            List<CorreoPersonaDTO> correoPersonaDTOs = new ArrayList<>();

            if (itUbicabilidadAxisDTO.getCorreoElectronico() != null
                    && !itUbicabilidadAxisDTO.getCorreoElectronico().isEmpty()) {
                correoPersonaDTOs.add(
                        crearCorreoPersonaDTO(itUbicabilidadAxisDTO.getCorreoElectronico(), tipoFuenteInformacionDTO));
            }

            personaDTO.setCorreoPersonaList(correoPersonaDTOs);

            personaDTO.setFuenteInformacion(tipoFuenteInformacionDTO);
            personaDTO.setFechaUltimaActualizacion(UtilFecha.buildCalendar().getTime());
        }
        return personaDTO;
    }

    private boolean compararDatosUbicabilidad(PersonaDTO personaDTO, ItUbicabilidadAxisDTO itUbicabilidadAxisDTO) {
        boolean registroIgual = true;
        List<DireccionPersonaDTO> lsDireccionPersonaDTO = personaDTO.getDireccionPersonaList();
        for (DireccionPersonaDTO direccionPersonaDTO : lsDireccionPersonaDTO) {
            if (direccionPersonaDTO.getDireccion().getComplemento() != null) {
                if (!direccionPersonaDTO.getDireccion().getComplemento().equals(itUbicabilidadAxisDTO.getDireccion())) {
                    registroIgual = false;
                }
            } else {
                if (direccionPersonaDTO.getDireccion().getComplemento() == null
                        && itUbicabilidadAxisDTO.getDireccion() != null) {
                    registroIgual = false;
                }
            }

            if (direccionPersonaDTO.getDireccion().getLatitud() != null && itUbicabilidadAxisDTO.getLatitud() != null) {
                if (!direccionPersonaDTO.getDireccion().getLatitud().equals(itUbicabilidadAxisDTO.getLatitud())) {
                    registroIgual = false;
                }
            } else {
                if (direccionPersonaDTO.getDireccion().getLatitud() == null
                        && itUbicabilidadAxisDTO.getLatitud() != null) {
                    registroIgual = false;
                }
                if (direccionPersonaDTO.getDireccion().getLatitud() != null
                        && itUbicabilidadAxisDTO.getLatitud() == null) {
                    registroIgual = false;
                }
            }

            if (direccionPersonaDTO.getDireccion().getLongitud() != null
                    && itUbicabilidadAxisDTO.getLongitud() != null) {
                if (!direccionPersonaDTO.getDireccion().getLongitud().equals(itUbicabilidadAxisDTO.getLongitud())) {
                    registroIgual = false;
                }
            } else {
                if (direccionPersonaDTO.getDireccion().getLongitud() == null
                        && itUbicabilidadAxisDTO.getLongitud() != null) {
                    registroIgual = false;
                }
                if (direccionPersonaDTO.getDireccion().getLongitud() != null
                        && itUbicabilidadAxisDTO.getLongitud() == null) {
                    registroIgual = false;
                }
            }
        }

        // validación de registro igual por telefono fijo
        if (personaDTO.getTelefonoPersonaDTOs() != null && !personaDTO.getTelefonoPersonaDTOs().isEmpty()) {
            if (itUbicabilidadAxisDTO.getTelefonoFijo() == null || itUbicabilidadAxisDTO.getTelefonoFijo().isEmpty()) {
                registroIgual = false;
            } else if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                    itUbicabilidadAxisDTO.getTelefonoFijo())) {
                registroIgual = false;
            }

        } else if (itUbicabilidadAxisDTO.getTelefonoFijo() != null
                && !itUbicabilidadAxisDTO.getTelefonoFijo().isEmpty()) {
            registroIgual = false;

        }

        // validación de registro igual por telefono celular
        if (personaDTO.getTelefonoPersonaDTOs() != null && !personaDTO.getTelefonoPersonaDTOs().isEmpty()) {
            if (itUbicabilidadAxisDTO.getTelefonoMovil() == null
                    || itUbicabilidadAxisDTO.getTelefonoMovil().isEmpty()) {
                registroIgual = false;
            } else if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                    itUbicabilidadAxisDTO.getTelefonoMovil())) {
                registroIgual = false;
            }

        } else if (itUbicabilidadAxisDTO.getTelefonoMovil() != null
                && !itUbicabilidadAxisDTO.getTelefonoMovil().isEmpty()) {
            registroIgual = false;

        }

        // validación de registro igual correo electronico
        if (personaDTO.getCorreoPersonaList() != null && !personaDTO.getCorreoPersonaList().isEmpty()) {
            if (itUbicabilidadAxisDTO.getCorreoElectronico() == null
                    || itUbicabilidadAxisDTO.getCorreoElectronico().isEmpty()) {
                registroIgual = false;
            } else if (!buscarCorreoPersona(personaDTO.getCorreoPersonaList(),
                    itUbicabilidadAxisDTO.getCorreoElectronico())) {
                registroIgual = false;
            }

        } else if (itUbicabilidadAxisDTO.getCorreoElectronico() != null
                && !itUbicabilidadAxisDTO.getCorreoElectronico().isEmpty()) {
            registroIgual = false;

        }

        return registroIgual;
    }

    private void enlazarDatosUbicabilidad(PersonaDTO personaDTO, ItUbicabilidadAxisDTO itUbicabilidadAxisDTO) {

        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
        tipoFuenteInformacionDTO.setId(EnumTipoFuenteInformacion.AXIS.getValue());
        tipoFuenteInformacionDTO.setNombre(EnumTipoFuenteInformacion.AXIS.name());
        tipoFuenteInformacionDTO.setEstado(true);

        // adición de direcciones en caso de no existir para esa persona
        if (itUbicabilidadAxisDTO.getDireccion() != null && !itUbicabilidadAxisDTO.getDireccion().isEmpty()) {
            if (personaDTO.getDireccionPersonaList() != null && !personaDTO.getDireccionPersonaList().isEmpty()) {
                if (!buscarDireccionPersona(personaDTO.getDireccionPersonaList(),
                        itUbicabilidadAxisDTO.getDireccion())) {
                    personaDTO.getDireccionPersonaList()
                            .add(crearDireccionPersonaDTO(itUbicabilidadAxisDTO.getDireccion(),
                                    itUbicabilidadAxisDTO.getLatitud(), itUbicabilidadAxisDTO.getLongitud(),
                                    tipoFuenteInformacionDTO));
                }
            } else {
                List<DireccionPersonaDTO> direccionPersonaDTOs = new ArrayList<>();
                personaDTO.setDireccionPersonaList(direccionPersonaDTOs);
                direccionPersonaDTOs.add(crearDireccionPersonaDTO(itUbicabilidadAxisDTO.getDireccion(),
                        itUbicabilidadAxisDTO.getLatitud(), itUbicabilidadAxisDTO.getLongitud(),
                        tipoFuenteInformacionDTO));
            }
        }

        // adición de telefonos en caso de no existir para esa persona
        if (personaDTO.getTelefonoPersonaDTOs() != null && !personaDTO.getTelefonoPersonaDTOs().isEmpty()) {

            // valida si el telefono fijo no existe para asi mismo adicionarlo
            if (itUbicabilidadAxisDTO.getTelefonoFijo() != null && !itUbicabilidadAxisDTO.getTelefonoFijo().isEmpty()) {
                if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                        itUbicabilidadAxisDTO.getTelefonoFijo())) {
                    personaDTO.getTelefonoPersonaDTOs().add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_FIJO,
                            itUbicabilidadAxisDTO.getTelefonoFijo(), tipoFuenteInformacionDTO));
                }
            }

            // valida si el telefono celular no existe para asi mismo adicionarlo
            if (itUbicabilidadAxisDTO.getTelefonoMovil() != null
                    && !itUbicabilidadAxisDTO.getTelefonoMovil().isEmpty()) {
                if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                        itUbicabilidadAxisDTO.getTelefonoMovil())) {
                    personaDTO.getTelefonoPersonaDTOs().add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_CELULAR,
                            itUbicabilidadAxisDTO.getTelefonoMovil(), tipoFuenteInformacionDTO));
                }
            }
        } else {

            List<TelefonoPersonaDTO> telefonoPersonaDTOs = new ArrayList<>();
            personaDTO.setTelefonoPersonaDTOs(telefonoPersonaDTOs);

            // valida si el telefono fijo no existe para asi mismo adicionarlo
            if (itUbicabilidadAxisDTO.getTelefonoFijo() != null && !itUbicabilidadAxisDTO.getTelefonoFijo().isEmpty()) {
                if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                        itUbicabilidadAxisDTO.getTelefonoFijo())) {
                    personaDTO.getTelefonoPersonaDTOs().add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_FIJO,
                            itUbicabilidadAxisDTO.getTelefonoFijo(), tipoFuenteInformacionDTO));
                }
            }

            // valida si el telefono celular no existe para asi mismo adicionarlo
            if (itUbicabilidadAxisDTO.getTelefonoMovil() != null
                    && !itUbicabilidadAxisDTO.getTelefonoMovil().isEmpty()) {
                if (!buscaTelefonoPersona(personaDTO.getTelefonoPersonaDTOs(),
                        itUbicabilidadAxisDTO.getTelefonoMovil())) {
                    personaDTO.getTelefonoPersonaDTOs().add(crearTelefonoPersonaDTO(EnumTipoTelefono.TELEFONO_CELULAR,
                            itUbicabilidadAxisDTO.getTelefonoMovil(), tipoFuenteInformacionDTO));
                }
            }
        }

        // adición de correos en caso de no existir para esa persona
        if (itUbicabilidadAxisDTO.getCorreoElectronico() != null
                && !itUbicabilidadAxisDTO.getCorreoElectronico().isEmpty()) {
            if (personaDTO.getCorreoPersonaList() != null && !personaDTO.getCorreoPersonaList().isEmpty()) {
                if (!buscarCorreoPersona(personaDTO.getCorreoPersonaList(),
                        itUbicabilidadAxisDTO.getCorreoElectronico())) {
                    personaDTO.getCorreoPersonaList().add(crearCorreoPersonaDTO(
                            itUbicabilidadAxisDTO.getCorreoElectronico(), tipoFuenteInformacionDTO));
                }
            } else {
                List<CorreoPersonaDTO> correoPersonaDTOs = new ArrayList<>();
                personaDTO.setCorreoPersonaList(correoPersonaDTOs);
                personaDTO.getCorreoPersonaList().add(
                        crearCorreoPersonaDTO(itUbicabilidadAxisDTO.getCorreoElectronico(), tipoFuenteInformacionDTO));
            }
        }

    }

    public TelefonoPersonaDTO crearTelefonoPersonaDTO(EnumTipoTelefono tipoTelefono, String numeroTelefono,
            TipoFuenteInformacionDTO tipoFuenteInformacion) {
        TelefonoPersonaDTO telefonoPersonaDTO = new TelefonoPersonaDTO();
        telefonoPersonaDTO.setNumeroTelefono(numeroTelefono);
        TipoTelefonoDTO tipoTelefonoDTO = new TipoTelefonoDTO();
        tipoTelefonoDTO.setId(tipoTelefono.getValue());
        telefonoPersonaDTO.setTipoTelefono(tipoTelefonoDTO);
        telefonoPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacion);
        return telefonoPersonaDTO;
    }

    public DireccionPersonaDTO crearDireccionPersonaDTO(String direccion, Double latitud, Double longitud,
            TipoFuenteInformacionDTO tipoFuenteInformacion) {

        DireccionDTO direccionDTO = new DireccionDTO();
        TipoViaDTO tipoViaDTO = new TipoViaDTO();
        tipoViaDTO.setCodigo(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
        direccionDTO.setTipoViaPrincipal(tipoViaDTO);
        direccionDTO.setComplemento(direccion);
        direccionDTO.setLatitud(latitud);
        direccionDTO.setLongitud(longitud);
        TipoUbicabilidadDTO tipoUbicabilidadDTO = new TipoUbicabilidadDTO();
        tipoUbicabilidadDTO.setId(EnumTipoUbicabilidad.RESIDENCIA.getPk());
        tipoUbicabilidadDTO.setNombre(EnumTipoUbicabilidad.RESIDENCIA.name());
        direccionDTO.setTipoUbicabilidad(tipoUbicabilidadDTO);
        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
        direccionPersonaDTO.setDireccion(direccionDTO);
        direccionPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacion);
        direccionPersonaDTO.setFechaRegistro(UtilFecha.buildCalendar().getTime());

        return direccionPersonaDTO;
    }

    public CorreoPersonaDTO crearCorreoPersonaDTO(String correo, TipoFuenteInformacionDTO tipoFuenteInformacion) {
        CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
        correoPersonaDTO.setCorreoElectronico(correo);
        correoPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacion);
        return correoPersonaDTO;
    }

    private boolean buscaTelefonoPersona(List<TelefonoPersonaDTO> telefonoPersonaDTOs, String numeroTelefono) {
        List<String> telefonos = new ArrayList<>();
        for (TelefonoPersonaDTO telefonoPersonaDTO : telefonoPersonaDTOs) {
            telefonos.add(telefonoPersonaDTO.getNumeroTelefono().toLowerCase());
        }

        return telefonos.contains(numeroTelefono.toLowerCase());
    }

    private boolean buscarCorreoPersona(List<CorreoPersonaDTO> correoPersonaDTOs, String correo) {
        List<String> correos = new ArrayList<>();
        for (CorreoPersonaDTO correoPersonaDTO : correoPersonaDTOs) {
            correos.add(correoPersonaDTO.getCorreoElectronico().toLowerCase());
        }
        return correos.contains(correo.toLowerCase());
    }

    private boolean buscarDireccionPersona(List<DireccionPersonaDTO> direccionPersonaDTOs, String direccion) {
        List<String> direcciones = new ArrayList<>();

        for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaDTOs) {
            direcciones.add(direccionPersonaDTO.getDireccion().getComplemento());
        }
        return direcciones.contains(direccion);
    }

    public <T extends PersonaDTO> String hola(T person) {
        return person.getApellido1();
    }
}
