package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.TipoUbicabilidad;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaHistorico;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.RepresentanteLegal;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoSociedad;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.entidades.ubicabilidad.TipoTelefono;
import co.com.datatools.c2.enumeracion.EnumGeneroPersona;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoParentescoPersona;
import co.com.datatools.c2.enumeracion.EnumTipoUbicabilidad;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHistoricoHelperExtend;
import co.com.datatools.c2.negocio.helpers.personas.RepresentanteLegalHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author robert.bautista
 * @since 2013-11-07
 */
public class AdministrarPersonaHelper {

    /**
     * Reglas de la persona:
     * <ol>
     * <li>No es null
     * <li>Esta asociada a un organismo de transito valido
     * <li>Esta asociada a un documento de identificacion valido
     * <li>Tiene un numero de identificacion valido. Esto aplica tanto para personas naturales como para personas juridicas
     * </ol>
     * 
     * @param personaDTO
     *            Contiene la informacion para validar las reglas.
     * @param em
     *            Entity Manager del EJB.
     * @return Retorna true si la persona indicada contiene los campos basicos validos.
     */
    public static boolean esPersonaConCamposBasicosValidos(PersonaDTO personaDTO, EntityManager em)
            throws CirculemosNegocioException {

        if (personaDTO == null) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015002);
        }

        if ((personaDTO.getOrganismoTransito() == null)
                || (personaDTO.getOrganismoTransito().getCodigoOrganismo() == null)) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015003);
        }

        if ((personaDTO.getTipoIdentificacion() == null) || (personaDTO.getTipoIdentificacion().getId() == null)) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015004);
        }

        if ((personaDTO.getNumeroIdentificacion() == null)
                || (personaDTO.getNumeroIdentificacion().trim().equals(""))) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015005);
        }

        if ((personaDTO.getFuenteInformacion() == null) || (personaDTO.getFuenteInformacion().getId() == null)) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015006);
        }

        if (em.find(OrganismoTransito.class, personaDTO.getOrganismoTransito().getCodigoOrganismo()) == null) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015007);
        }

        if (em.find(TipoIdentificacionPersona.class, personaDTO.getTipoIdentificacion().getId()) == null) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015008);
        }

        if (em.find(TipoFuenteInformacion.class, personaDTO.getFuenteInformacion().getId()) == null) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015009);
        }

        return true;
    }

    /**
     * Retorna true si los campos no basicos de la persona natural son validos
     * 
     * @param personaDTO
     *            la persona a la cual se le validaran los campos
     * @param em
     * @return true si los campos son validos
     * @throws CirculemosNegocioException
     */
    public static boolean esPersonaNaturalConCamposNoBasicosValidos(PersonaDTO personaDTO, EntityManager em)
            throws CirculemosNegocioException {

        if ((personaDTO.getMunicipioExpedicionDocumento() != null)
                && (personaDTO.getMunicipioExpedicionDocumento().getId() != null)) {
            if (em.find(Municipio.class, personaDTO.getMunicipioExpedicionDocumento().getId()) == null) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015059);
            }

        }

        // se podria tener que validar la fecha de nacimiento..
        if (personaDTO.getFechaFallecimiento() != null) {
            if (personaDTO.getFechaNacimiento() != null) {
                if (personaDTO.getFechaNacimiento().after(new Date())) {
                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015060);
                }

                if (!personaDTO.getFechaFallecimiento().after(personaDTO.getFechaNacimiento())) {
                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015061);
                }

            }

            if (personaDTO.getFechaFallecimiento().after(new Date())) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015062);
            }

        }

        // validación primer nombre
        if ((personaDTO.getNombre1() == null) || (StringUtils.isBlank(personaDTO.getNombre1()))) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015063);
        } else {
            personaDTO.setNombre1(personaDTO.getNombre1().trim());

            // validación de la expresion regular
            if (!personaDTO.getNombre1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015064);
            }

            // Validación de la longitud
            if (personaDTO.getNombre1().length() > 30) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015065);
            }

        }

        // validación segundo nombre
        if (personaDTO.getNombre2() != null && StringUtils.isNotBlank(personaDTO.getNombre2())) {
            personaDTO.setNombre2(personaDTO.getNombre2().trim());

            // validación de la expresion regular
            if (!personaDTO.getNombre2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015066);
            }
            // validación de longitud de segundo nombre
            if (personaDTO.getNombre2().length() > 30) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015067);
            }

        } else {
            personaDTO.setNombre2(null);
        }

        // validación primer apellido
        if ((personaDTO.getApellido1() == null) || (StringUtils.isBlank(personaDTO.getApellido1()))) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015068);
        } else {

            personaDTO.setApellido1(personaDTO.getApellido1().trim());
            // validación de expresion regular
            if (!personaDTO.getApellido1().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015069);
            }
            // validación de longitud de primer apellido
            if (personaDTO.getApellido1().length() > 30) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015070);
            }
        }

        // validación segundo apellido
        if (personaDTO.getApellido2() != null && StringUtils.isNotBlank(personaDTO.getApellido2())) {
            personaDTO.setApellido2(personaDTO.getApellido2().trim());

            // validación de la expresion regular
            if (!personaDTO.getApellido2().matches(ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS)) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015071);
            }
            // validación de longitud de segundo nombre
            if (personaDTO.getApellido2().length() > 30) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015072);
            }

        } else {
            personaDTO.setApellido2(null);
        }

        // validación de genero
        if (personaDTO.getGenero() != null) {
            String valor = personaDTO.getGenero().toString();
            if ((!valor.equals(EnumGeneroPersona.MASCULINO.getDiminutivo()))
                    && (!valor.equals(EnumGeneroPersona.FEMENINO.getDiminutivo()))) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015073);
            }

        }

        if ((personaDTO.getEstadoCivil() != null) && (personaDTO.getEstadoCivil().getId() != null)) {
            if (em.find(EstadoCivil.class, personaDTO.getEstadoCivil().getId()) == null) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015074);
            }

        }

        if ((personaDTO.getTipoVivienda() != null) && (personaDTO.getTipoVivienda().getCodigo() != null)) {
            if (em.find(TipoVivienda.class, personaDTO.getTipoVivienda().getCodigo()) == null) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015075);
            }

        }

        if ((personaDTO.getNivelEducativo() != null) && (personaDTO.getNivelEducativo().getCodigo() != null)) {
            if (em.find(NivelEducativo.class, personaDTO.getNivelEducativo().getCodigo()) == null) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015076);
            }

        }

        if (personaDTO.getNombreEmpresaLabora() != null) {
            personaDTO.setNombreEmpresaLabora(personaDTO.getNombreEmpresaLabora().trim());
            if (personaDTO.getNombreEmpresaLabora().length() > 60) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015077);
            }
        }

        if (personaDTO.getCargoEmpresaLabora() != null) {
            personaDTO.setCargoEmpresaLabora(personaDTO.getCargoEmpresaLabora().trim());
            if (personaDTO.getCargoEmpresaLabora().length() > 30) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015078);
            }
        }

        return true;
    }

    /**
     * Valida que los campos obligatorios posean informacion y los no obligatorios posean informacion consistente.
     * 
     * @param personaJuridicaDTO
     *            la persona juridica a validar
     * @param em
     * @return true si los campos poseen informacion valida
     * @throws CirculemosNegocioException
     */
    public static boolean esPersonaJuridicaValida(PersonaJuridicaDTO personaJuridicaDTO, EntityManager em)
            throws CirculemosNegocioException {

        // if (personaJuridicaDTO.getDigitoVerificacion() != null) {
        // if (Utilidades.digitoVerificacion(personaJuridicaDTO.getNumeroIdentificacion()) != personaJuridicaDTO
        // .getDigitoVerificacion()) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015049);
        // }
        //
        // }

        if ((personaJuridicaDTO.getNombreComercial() == null)
                || (personaJuridicaDTO.getNombreComercial().trim().equals(""))) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015050);
        }

        personaJuridicaDTO.setNombreComercial(personaJuridicaDTO.getNombreComercial().trim());
        if (personaJuridicaDTO.getNombreComercial().length() > 60) {
            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015051);
        }

        if (personaJuridicaDTO.getSigla() != null) {
            personaJuridicaDTO.setSigla(personaJuridicaDTO.getSigla().trim());
            if (personaJuridicaDTO.getSigla().length() > 10) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015052);
            }
        }

        if ((personaJuridicaDTO.getMunicipio() != null) && (personaJuridicaDTO.getMunicipio().getId() != null)) {
            if (em.find(Municipio.class, personaJuridicaDTO.getMunicipio().getId()) == null) {
                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015053);
            }
        }

        /* Representante legal */
        // if (!personaJuridicaDTO.getRepresentanteLegalList().isEmpty()) {
        // // Se busca el representante legal actual
        // RepresentanteLegalDTO representanteActualDTO = null;
        // for (RepresentanteLegalDTO rl : personaJuridicaDTO.getRepresentanteLegalList()) {
        // if (rl.getFechaFin() == null) {
        // representanteActualDTO = rl;
        // break;
        // }
        // }
        //
        // if (representanteActualDTO == null) {
        // representanteActualDTO = personaJuridicaDTO.getRepresentanteLegalList()
        // .get(personaJuridicaDTO.getRepresentanteLegalList().size() - 1);
        // if (!UtilFecha.betweenDate(representanteActualDTO.getFechaInicio(),
        // representanteActualDTO.getFechaFin(), UtilFecha.currentZeroTimeDate())) {
        // representanteActualDTO = null;
        // }
        // }
        //
        // // Se valida la informacion del representante legal actual
        // if (representanteActualDTO != null) {
        // if (representanteActualDTO.getFechaInicio() == null) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015054);
        // }
        //
        // if (representanteActualDTO.getCorreoElectronico() != null) {
        // if (!representanteActualDTO.getCorreoElectronico().trim().equals("")) {
        // if (!EmailValidator.validate(representanteActualDTO.getCorreoElectronico())) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015055);
        // }
        //
        // representanteActualDTO
        // .setCorreoElectronico(representanteActualDTO.getCorreoElectronico().trim());
        // if (representanteActualDTO.getCorreoElectronico().length() > 255) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015056);
        // }
        // } else {
        // representanteActualDTO.setCorreoElectronico(null);
        // }
        // }
        //
        // if ((representanteActualDTO.getNumeroIdentificacion() == null)
        // || (representanteActualDTO.getNumeroIdentificacion().isEmpty())) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015057);
        // }
        //
        // if (representanteActualDTO.getFechaFin() != null) {
        // if (!representanteActualDTO.getFechaFin().after(representanteActualDTO.getFechaInicio())) {
        // throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015058);
        // }
        //
        // }
        //
        // }
        //
        // }

        return true;

    }

    /**
     * Compara los campos basicos de la Persona Juridica y la Persona Juridica DTO y en caso de no ser iguales los modifica en la Persona Juridica
     * indicada
     * 
     * @param persona
     *            PersonaJuridica a la cual se le actualizaran los campos en caso de no ser iguales
     * 
     * @param personaDTO
     *            DTO de comparacion
     */
    public static void compararCamposBasicosPersonaJuridica(PersonaJuridica persona, PersonaJuridicaDTO personaDTO) {
        persona.setDigitoVerificacion(personaDTO.getDigitoVerificacion());
        persona.setSigla(personaDTO.getSigla());
        persona.setNombreComercial(personaDTO.getNombreComercial());

    }

    /**
     * Asocia una lista nueva a la persona indicada con un solo historico acorde a la informacion de la personaDTO indicada
     * 
     * @param personaDTO
     *            contiene la informacion para crear el historico
     * 
     * @param persona
     *            instancia a la cual se le asociara el historico creado
     * 
     * @return PersonaHistorico con el historico de la persona indicada
     */
    public static PersonaHistorico adicionarHistorico(PersonaDTO personaDTO, Persona persona) {
        PersonaHistoricoDTO perHistoricoDTO = PersonaHistoricoHelperExtend.toPersonaHistoricoDTO(personaDTO);
        perHistoricoDTO.setFechaRegistro(new Date());

        PersonaHistorico personaHistorico = PersonaHistoricoHelperExtend.toLevel1Entity(perHistoricoDTO, null);
        personaHistorico.setPersona(persona);

        return personaHistorico;
    }

    /**
     * Retorna la PersonaDTO (natural) correspondiente a la informacion de la persona indicada
     * 
     * @param persona
     *            la persona que se transformara en persona Natural
     * 
     * @return PersonaDTO con la informacion de la persona indicada
     */
    public static PersonaDTO cargarPersonaNatural(Persona persona) {
        return PersonaHelperExtend.toLevel1DTO(persona);
    }

    /**
     * Compara los campos de segundo nivel de la Persona y la Persona DTO y en caso de no ser iguales los modifica en la Persona indicada
     * 
     * @param persona
     *            Persona a la cual se le actualizaran los campos en caso de no ser iguales
     * 
     * @param personaDTO
     *            DTO de comparacion
     * @param em
     *            referencia al entity manager para realizar las validaciones de base de datos
     */
    public static void compararCamposSegundoNivelPersona(Persona persona, PersonaDTO personaDTO, EntityManager em) {
        EstadoCivil estadoCivil = null;
        if ((personaDTO.getEstadoCivil() != null) && (personaDTO.getEstadoCivil().getId() != null)) {
            estadoCivil = em.find(EstadoCivil.class, personaDTO.getEstadoCivil().getId());
        }

        persona.setEstadoCivil(estadoCivil);
        Municipio municipio = null;
        if ((personaDTO.getMunicipioExpedicionDocumento() != null)
                && (personaDTO.getMunicipioExpedicionDocumento().getId() != null)) {
            municipio = em.find(Municipio.class, personaDTO.getMunicipioExpedicionDocumento().getId());
        }

        persona.setMunicipioExpedicionDocumento(municipio);
        TipoVivienda tipoVivienda = null;
        if ((personaDTO.getTipoVivienda() != null) && (personaDTO.getTipoVivienda().getCodigo() != null)) {
            tipoVivienda = em.find(TipoVivienda.class, personaDTO.getTipoVivienda().getCodigo());
        }

        persona.setTipoVivienda(tipoVivienda);
        NivelEducativo nivelEducativo = null;
        if ((personaDTO.getNivelEducativo() != null) && (personaDTO.getNivelEducativo().getCodigo() != null)) {
            nivelEducativo = em.find(NivelEducativo.class, personaDTO.getNivelEducativo().getCodigo());
        }

        persona.setNivelEducativo(nivelEducativo);
    }

    /**
     * Compara los campos de persona (de la persona juridica) que se pueden modificar. Si cambiaron se asigna el cambio a la PersonaJuridica indicada
     * 
     * @param persona
     *            PersonaJuridica sobre la cual se haran los cambios en caso de ser necesario
     * @param personaDTO
     *            personaJuridicaDTO sobre la cual se comparan los atributos que se pueden modificar.
     * @param em
     * @param usuario
     */
    public static void compararCamposSegundoNivelPersonaJuridica(PersonaJuridica persona, PersonaJuridicaDTO personaDTO,
            EntityManager em, UsuarioPersonaDTO usuario) {
        // campos opcionales
        TipoSociedad tipoSociedad = null;
        if ((personaDTO.getTipoSociedad() != null) && (personaDTO.getTipoSociedad().getId() != null)) {
            if (personaDTO.getTipoSociedad().getId() != null) {
                tipoSociedad = em.find(TipoSociedad.class, personaDTO.getTipoSociedad().getId());
            }

        }

        persona.setTipoSociedad(tipoSociedad);
        ClaseActividadEconomica claseActividad = null;
        if ((personaDTO.getClaseActividadEconomica() != null)
                && (personaDTO.getClaseActividadEconomica().getId() != null)) {
            if (personaDTO.getClaseActividadEconomica().getId() != null) {
                claseActividad = em.find(ClaseActividadEconomica.class,
                        personaDTO.getClaseActividadEconomica().getId());
            }

        }

        persona.setClaseActividadEconomica(claseActividad);
        Municipio municipio = null;
        if ((personaDTO.getMunicipio() != null) && (personaDTO.getMunicipio().getId() != null)) {
            municipio = em.find(Municipio.class, personaDTO.getMunicipio().getId());
        }

        persona.setMunicipio(municipio);
        /* Representante legal */
        if (personaDTO.getRepresentanteLegalList() != null && !personaDTO.getRepresentanteLegalList().isEmpty()) {
            RepresentanteLegalDTO representanteLegalDTO = personaDTO.getRepresentanteLegalList()
                    .get(personaDTO.getRepresentanteLegalList().size() - 1);
            // Modificar valores de representante ya asignado
            if (representanteLegalDTO.getIdRepresentante() != null) {
                RepresentanteLegal representanteLegal = em.find(RepresentanteLegal.class,
                        representanteLegalDTO.getIdRepresentante());
                representanteLegal.setFechaActualizacion(personaDTO.getFechaUltimaActualizacion());
                if (usuario != null) {
                    representanteLegal.setUsuarioActualiza(new UsuarioPersona(usuario.getUsuario().getId()));
                }
                representanteLegal.setFechaFin(representanteLegalDTO.getFechaFin());
                representanteLegal.setFechaInicio(representanteLegalDTO.getFechaInicio());
                persona.getRepresentanteLegalList().set(persona.getRepresentanteLegalList().size() - 1,
                        representanteLegal);
            } else { // Asignar nuevo representante
                RepresentanteLegal representanteLegal = RepresentanteLegalHelper.toLevel1Entity(representanteLegalDTO,
                        null);
                representanteLegal.setFechaRegistro(personaDTO.getFechaUltimaActualizacion());
                if (usuario != null) {
                    representanteLegal.setUsuarioRegistro(new UsuarioPersona(usuario.getUsuario().getId()));
                }
                persona.getRepresentanteLegalList().add(representanteLegal);
            }
        }
        /* Tipo fuente informacion */
        if (personaDTO.getFuenteInformacion() != null) {
            persona.getPersona().setFuenteInformacion(
                    TipoFuenteInformacionHelper.toLevel0Entity(personaDTO.getFuenteInformacion(), null));
        }
    }

    /**
     * Valida los campos necesarios para ingresar una persona, id del tipo de identificacion y numero de identificacion. Si no son validos arroja
     * excepcion.
     * 
     * @param Id
     *            id del tipo de identificacion de la persona
     * 
     * @param numeroIdentificacion
     *            numero de identificacion de la persona
     */
    public static void validarCamposNecesariosPersona(Long Id, String numeroIdentificacion) {
        if ((Id == null) || (Id.equals(Long.valueOf(0)))) {
            throw new CirculemosRuntimeException("No se puede ingresar una persona sin tipo de identificación");
        }

        if ((numeroIdentificacion == null) || (numeroIdentificacion.equals(""))) {
            throw new CirculemosRuntimeException("No se puede ingresar una persona sin número de identificación");
        }
    }

    /**
     * Indica si existe una direccion en la lista indicada nueva con el tipo de ubicabilidad indicada
     * 
     * @param tipoUbicabilidad
     *            tipo de ubicabilidad a buscar
     * @param personaDireccion
     *            lista de direcciones sobre las cuales buscar
     * @return true si existe una direccion con el tipo de ubicabilidad indicado y id null
     */
    public static boolean contieneDireccionNueva(EnumTipoUbicabilidad tipoUbicabilidad,
            List<DireccionPersonaDTO> personaDireccion) {
        boolean contiene = false;

        for (DireccionPersonaDTO direccionPersonaDTO : personaDireccion) {
            if ((direccionPersonaDTO.getDireccion().getId() == null) && (direccionPersonaDTO.getDireccion()
                    .getTipoUbicabilidad().getId().intValue() == tipoUbicabilidad.getPk())) {
                contiene = true;
            }
        }
        return contiene;
    }

    /**
     * TODO: UBICABILIDAD ARREGLAR Le asigna la fecha actual a la fecha fin de la direccion persona que tenga la direccion con la ubicabilidad
     * indicada y fecha fin null
     * 
     * @param ubicabilidad
     *            contiene el tiop de ubicabilidad por el cual buscar
     * @param direccionPersonaList
     *            contiene el listado de direcciones sobre las cuales buscar
     */
    public static void cierreUltimaFecha(EnumTipoUbicabilidad ubicabilidad,
            List<DireccionPersonaDTO> direccionPersonaList) {
        for (DireccionPersonaDTO dirPersonaDTO : direccionPersonaList) {
            if (dirPersonaDTO.getDireccion().getTipoUbicabilidad().getId().intValue() == ubicabilidad.getPk()) {
                // if ((dirPersonaDTO.getDireccion().getId() != null) && (dirPersonaDTO.getFechaFin() == null)) {
                // dirPersonaDTO.setFechaFin(new Date());
                // break;
                // }

            }

        }
    }

    /**
     * Retorna true si existe un parentesco con id null
     * 
     * @param parentescoPersonaList
     *            contiene la lista de parentescos a revisar
     * @param tipoParentesco
     * @return true si existe un parentesco con id null
     */
    public static boolean contieneParentescoNuevo(List<ParentescoPersonaDTO> parentescoPersonaList,
            EnumTipoParentescoPersona tipoParentesco) {
        boolean hayNuevo = false;
        for (ParentescoPersonaDTO parentesco : parentescoPersonaList) {
            if ((parentesco.getId() == null)
                    && (parentesco.getTipoParentesco().getId().equals(tipoParentesco.getId()))) {
                hayNuevo = true;
                break;
            }

        }

        return hayNuevo;
    }

    /**
     * Cierra el parentesco cuya fecha fin sea igual a null
     * 
     * @param parentescoPersonaList
     * @param tipoParentesco
     */
    public static void cierreUltimaFecha(List<ParentescoPersonaDTO> parentescoPersonaList,
            EnumTipoParentescoPersona tipoParentesco) {
        for (ParentescoPersonaDTO parentesco : parentescoPersonaList) {
            if ((parentesco.getId() != null) && (parentesco.getTipoParentesco().getId().equals(tipoParentesco.getId()))
                    && (parentesco.getFechaFin() == null)) {
                parentesco.setFechaFin(new Date());
                break;
            }

        }

    }

    public static void validarCorreos(List<CorreoPersonaDTO> correoPersonaDTOs, EntityManager em)
            throws CirculemosNegocioException {
        if (correoPersonaDTOs != null) {
            if (!correoPersonaDTOs.isEmpty()) {
                List<String> correos = new ArrayList<>();
                for (CorreoPersonaDTO correoPersonaDTO : correoPersonaDTOs) {

                    // condición para que solo valide correos nuevos
                    if (correoPersonaDTO.getId() == null) {

                        // validación de fuente de información
                        if (correoPersonaDTO.getTipoFuenteInformacion() == null
                                || correoPersonaDTO.getTipoFuenteInformacion().getId() == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015023);
                        } else if (em.find(TipoFuenteInformacion.class,
                                correoPersonaDTO.getTipoFuenteInformacion().getId()) == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015024);
                        }

                        // validación de correo
                        if (StringUtils.isBlank(correoPersonaDTO.getCorreoElectronico())) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015025);
                        }

                        // validación de correo por expresion regular
                        if (!correoPersonaDTO.getCorreoElectronico().trim().matches(ExpresionesRegulares.REGEX_EMAIL)) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015026);
                        }

                        // validación datos válidos, segun fuente información
                        if (correoPersonaDTO.getTipoFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.CIUDADANO.getValue())) {
                            if (correoPersonaDTO.getEstado() == null) {
                                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015029);
                            } else {
                                if (correoPersonaDTO.getPrioridad() == null || correoPersonaDTO.getPrioridad() < 0) {
                                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015030);
                                }

                                if (correoPersonaDTO.getTipoFuenteValidacion() == null
                                        || correoPersonaDTO.getTipoFuenteValidacion().getId() == null) {
                                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015033);
                                }
                            }
                        }

                        // validaciones de correo repetidos
                        if (!correos.contains(correoPersonaDTO.getCorreoElectronico().trim().toLowerCase())) {
                            correos.add(correoPersonaDTO.getCorreoElectronico().trim().toLowerCase());
                        } else {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015034);
                        }

                    }

                }
            }
        }
    }

    public static void validarTelefonos(List<TelefonoPersonaDTO> telefonoPersonaDTOs, EntityManager em)
            throws CirculemosNegocioException {
        if (telefonoPersonaDTOs != null) {
            if (!telefonoPersonaDTOs.isEmpty()) {
                List<String> telefonos = new ArrayList<>();
                for (TelefonoPersonaDTO telefonoPersonaDTO : telefonoPersonaDTOs) {

                    // condición para que solo valide telefonos nuevos
                    if (telefonoPersonaDTO.getId() == null) {
                        // validación de fuente de información
                        if (telefonoPersonaDTO.getTipoFuenteInformacion() == null
                                || telefonoPersonaDTO.getTipoFuenteInformacion().getId() == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015035);
                        } else if (em.find(TipoFuenteInformacion.class,
                                telefonoPersonaDTO.getTipoFuenteInformacion().getId()) == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015036);
                        }

                        // valida si viene un numero telefonico
                        if (StringUtils.isBlank(telefonoPersonaDTO.getNumeroTelefono())) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015037);
                        }

                        // valida la longitud del número telefonico
                        if (telefonoPersonaDTO.getNumeroTelefono().trim().length() > 20) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015038);
                        }

                        // valida el tipo de teléfono
                        if (telefonoPersonaDTO.getTipoTelefono() == null
                                || telefonoPersonaDTO.getTipoTelefono().getId() == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015039);
                        } else if (em.find(TipoTelefono.class, telefonoPersonaDTO.getTipoTelefono().getId()) == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015040);
                        }

                        // validación datos válidos, segun fuente información
                        if (telefonoPersonaDTO.getTipoFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.CIUDADANO.getValue())) {
                            if (telefonoPersonaDTO.getEstado() == null) {
                                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015043);
                            } else {
                                if (telefonoPersonaDTO.getPrioridad() == null
                                        || telefonoPersonaDTO.getPrioridad() < 0) {
                                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015044);
                                }

                                if (telefonoPersonaDTO.getTipoFuenteValidacion() == null
                                        || telefonoPersonaDTO.getTipoFuenteValidacion().getId() == null) {
                                    throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015047);
                                }
                            }
                        }

                        // validaciones de telefonos repetidos
                        if (!telefonos.contains(telefonoPersonaDTO.getNumeroTelefono().trim().toLowerCase())) {
                            telefonos.add(telefonoPersonaDTO.getNumeroTelefono().trim().toLowerCase());
                        } else {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015048);
                        }

                    }
                }
            }
        }
    }

    public static void validarDireccionPersona(List<DireccionPersonaDTO> direccionPersonaDTOs, EntityManager em)
            throws CirculemosNegocioException {
        if (direccionPersonaDTOs != null) {
            for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaDTOs) {
                List<DireccionDTO> direcciones = new ArrayList<>();

                // condición para que solo valide dirección persona nueva
                if (direccionPersonaDTO.getId() == null) {
                    // validación de dirección
                    if (direccionPersonaDTO.getDireccion() == null) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015010);
                    }

                    // valida que el complemento de dirección no venga vacío o null
                    if (direccionPersonaDTO.getDireccion().getComplemento() == null
                            || direccionPersonaDTO.getDireccion().getComplemento().trim().isEmpty()) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015027);
                    }

                    // validación de tipo de ubicabilidad
                    if (direccionPersonaDTO.getDireccion().getTipoUbicabilidad() == null
                            || direccionPersonaDTO.getDireccion().getTipoUbicabilidad().getId() == null) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015015);
                    } else if (em.find(TipoUbicabilidad.class,
                            direccionPersonaDTO.getDireccion().getTipoUbicabilidad().getId()) == null) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015016);
                    }

                    // validación de fuente de información
                    if (direccionPersonaDTO.getTipoFuenteInformacion() == null
                            || direccionPersonaDTO.getTipoFuenteInformacion().getId() == null) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015011);
                    } else if (em.find(TipoFuenteInformacion.class,
                            direccionPersonaDTO.getTipoFuenteInformacion().getId()) == null) {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015012);
                    }

                    // validación datos válidos, segun fuente información
                    if (direccionPersonaDTO.getTipoFuenteInformacion().getId()
                            .equals(EnumTipoFuenteInformacion.CIUDADANO.getValue())) {
                        if (direccionPersonaDTO.getEstado() == null) {
                            throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015017);
                        } else {
                            if (direccionPersonaDTO.getPrioridad() == null || direccionPersonaDTO.getPrioridad() < 0) {
                                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015018);
                            }

                            if (direccionPersonaDTO.getTipoFuenteValidacion() == null
                                    || direccionPersonaDTO.getTipoFuenteValidacion().getId() == null) {
                                throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015021);
                            }
                        }
                    }

                    // validación de direcciones repetidas
                    if (!direcciones.contains(direccionPersonaDTO.getDireccion())) {
                        direcciones.add(direccionPersonaDTO.getDireccion());
                    } else {
                        throw new CirculemosNegocioException(ErrorAdministracion.Ubicabilidad.COB_015022);
                    }

                }

            }
        }

    }
}
