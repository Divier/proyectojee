package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.dto.InconsistenciaGeneralDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.ubicabilidad.CargueMasivoUbicabilidadDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoTelefonoDTO;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumErrorCargueUbicabilidad;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.CorreoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.DireccionPersonaHelperExtend;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.TelefonoPersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.ILConsecutivo;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.ILPersona;
import co.com.datatools.c2.negocio.interfaces.ILUbicabilidad;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidad;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class UbicabilidadEJB
 */
@Stateless(name = "UbicabilidadEJB")
@LocalBean
public class UbicabilidadEJB implements IRUbicabilidad, ILUbicabilidad {

    private final static Logger logger = Logger.getLogger(UbicabilidadEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    @EJB
    private ILCargueMasivo iLCargueMasivo;

    @EJB
    private ILUbicabilidad iLUbicabilidad;

    @EJB
    private ILPersona iLPersona;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRCatalogo catalogoEjb;

    @EJB
    private ILAdministracion iLAdministracion;

    @EJB
    private ILDireccion iLDireccion;

    @EJB
    private ILConsecutivo iLConsecutivo;

    @Override
    public List<CorreoPersonaDTO> consultarCorreosNotificables(Long idPersona) {
        List<CorreoPersonaDTO> lsCorreoPersonaDTO = new ArrayList<>();
        checkNotNull(idPersona, "Debe recibirse el identificador de la persona");

        // se setean datos nesecarios para llamar consultarCorreos
        CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(idPersona);
        correoPersonaDTO.setPersona(personaDTO);

        // consulto los correos
        List<CorreoPersonaDTO> correoPersonaDTOs = consultarCorreos(correoPersonaDTO);

        // inicializo la lista de respuesta
        if (correoPersonaDTOs != null && !correoPersonaDTOs.isEmpty()) {
            for (CorreoPersonaDTO correoPersona : correoPersonaDTOs) {
                if (correoPersona.getEstado() == null || correoPersona.getEstado()) {
                    lsCorreoPersonaDTO.add(correoPersona);
                }
            }
        }
        return lsCorreoPersonaDTO;
    }

    public List<CorreoPersonaDTO> consultarCorreos(CorreoPersonaDTO correoPersonaDTO) {

        if (correoPersonaDTO == null || correoPersonaDTO.getPersona() == null
                || correoPersonaDTO.getPersona().getId() == null) {
            throw new CirculemosRuntimeException("Se requiere idPersona para realizar la consulta");
        }

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT c FROM CorreoPersona c");
        jpql.append(" JOIN c.persona p");

        Map<String, Object> filtros = new HashMap<String, Object>();

        jpql.append(" WHERE p.id=:idPersona");
        filtros.put("idPersona", correoPersonaDTO.getPersona().getId());

        if (correoPersonaDTO.getId() != null) {
            jpql.append(" AND c.id=:idCorreoPersona");
            filtros.put("idCorreoPersona", correoPersonaDTO.getId());
        }

        if (correoPersonaDTO.getEstado() != null) {
            jpql.append(" AND c.estado=:idEstado");
            filtros.put("idEstado", correoPersonaDTO.getEstado());
        }

        if (correoPersonaDTO.getPrioridad() != null) {
            jpql.append(" AND c.prioridad=:idPrioridad");
            filtros.put("idPrioridad", correoPersonaDTO.getPrioridad());
        }

        jpql.append(" ORDER BY c.fechaActualizacion DESC, c.id DESC");

        GenericDao<CorreoPersona> correoPersonaDao = new GenericDao<>(CorreoPersona.class, em);
        List<CorreoPersona> resultadoConsulta = correoPersonaDao.buildAndExecuteQuery(jpql.toString(), filtros);
        List<CorreoPersonaDTO> lsCorreoPersonaDTO = CorreoPersonaHelper.toListLevel1DTO(resultadoConsulta);

        return lsCorreoPersonaDTO;
    }

    public List<TelefonoPersonaDTO> consultarTelefonos(TelefonoPersonaDTO telefonoPersonaDTO) {

        if (telefonoPersonaDTO == null || telefonoPersonaDTO.getPersona() == null
                || telefonoPersonaDTO.getPersona().getId() == null) {
            throw new CirculemosRuntimeException("Se requiere idPersona para realizar la consulta");
        }

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT t FROM TelefonoPersona t");
        jpql.append(" JOIN t.persona p");

        Map<String, Object> filtros = new HashMap<String, Object>();

        jpql.append(" WHERE p.id=:idPersona");
        filtros.put("idPersona", telefonoPersonaDTO.getPersona().getId());

        if (telefonoPersonaDTO.getId() != null) {
            jpql.append(" AND t.id=:idTelefonoPersona");
            filtros.put("idTelefonoPersona", telefonoPersonaDTO.getId());
        }

        if (telefonoPersonaDTO.getEstado() != null) {
            jpql.append(" AND t.estado=:idEstado");
            filtros.put("idEstado", telefonoPersonaDTO.getEstado());
        }

        if (telefonoPersonaDTO.getPrioridad() != null) {
            jpql.append(" AND t.prioridad=:idPrioridad");
            filtros.put("idPrioridad", telefonoPersonaDTO.getPrioridad());
        }

        jpql.append(" ORDER BY t.fechaActualizacion DESC,t.id DESC");

        GenericDao<TelefonoPersona> telefonoPersonaDao = new GenericDao<>(TelefonoPersona.class, em);
        List<TelefonoPersona> resultadoConsulta = telefonoPersonaDao.buildAndExecuteQuery(jpql.toString(), filtros);
        List<TelefonoPersonaDTO> lsTelefonoPersonaDTO = TelefonoPersonaHelper.toListLevel1DTO(resultadoConsulta);

        return lsTelefonoPersonaDTO;
    }

    public List<DireccionPersonaDTO> consultarDireccionPersona(DireccionPersonaDTO direccionPersonaDTO) {

        if (direccionPersonaDTO == null || direccionPersonaDTO.getPersona() == null
                || direccionPersonaDTO.getPersona().getId() == null) {
            throw new CirculemosRuntimeException("Se requiere idPersona para realizar la consulta");
        }

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM DireccionPersona d");
        jpql.append(" JOIN FETCH d.direccion dir");
        jpql.append(" JOIN d.persona p");

        Map<String, Object> filtros = new HashMap<String, Object>();

        jpql.append(" WHERE p.id=:idPersona");
        filtros.put("idPersona", direccionPersonaDTO.getPersona().getId());

        if (direccionPersonaDTO.getId() != null) {
            jpql.append(" AND d.id=:idDireccionPersona");
            filtros.put("idDireccionPersona", direccionPersonaDTO.getId());
        }

        if (direccionPersonaDTO.getEstado() != null) {
            jpql.append(" AND d.estado=:idEstado");
            filtros.put("idEstado", direccionPersonaDTO.getEstado());
        }

        if (direccionPersonaDTO.getPrioridad() != null) {
            jpql.append(" AND d.prioridad=:idPrioridad");
            filtros.put("idPrioridad", direccionPersonaDTO.getPrioridad());
        }

        jpql.append(" ORDER BY d.fechaActualizacion DESC, d.id DESC");

        GenericDao<DireccionPersona> direccionPersonaDao = new GenericDao<>(DireccionPersona.class, em);
        List<DireccionPersona> resultadoConsulta = direccionPersonaDao.buildAndExecuteQuery(jpql.toString(), filtros);
        List<DireccionPersonaDTO> lsDireccionPersonaDTO = DireccionPersonaHelperExtend
                .toListLevel2DTO(resultadoConsulta);

        return lsDireccionPersonaDTO;
    }

    @Asynchronous
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Future<CargueArchivoDTO> procesarCargueMasivoUbic(CargueArchivoDTO respuestaCargue,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) throws CirculemosNegocioException {
        logger.debug("UbicabilidadEJB.procesarCargueMasivoUbic(File, CargueArchivoDTO, CargueMasivoUbicabilidadDTO)");
        try {
            DateFormat df = new SimpleDateFormat(ConstantesCargaArchivos.FORMATO_FECHA_ARCHIVO);
            String dateStr = df.format(Utilidades.getFechaActual());
            File csvFile = new File(ConstantesCargaArchivos.NOMBRE_ARCHIVO_CARGUE_MASIVO_UBIC + dateStr
                    + ConstantesCargaArchivos.EXTENSION_CSV);
            FileUtils.writeByteArrayToFile(csvFile, cargueMasivoUbicabilidad.getArchivoCSVCargue().getContenido());

            int numFila = 0;
            StringBuilder sbInconsistencias = new StringBuilder();
            String linea = null;

            // Usuario actual
            UsuarioPersonaDTO usuarioValida = iRSeguridadCirculemos.obtenerUsuarioDto();

            // Cargar catalogos para validacion
            cargarCatalogosCargueMasivo(cargueMasivoUbicabilidad);

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((linea = br.readLine()) != null) {
                    try {
                        if (numFila == 0) {
                            sbInconsistencias.append(linea + ConstantesCargaArchivos.getSeparadorLineaSistema());
                        } else {
                            String inconsistencias = validaLineaUbicabilidad(linea, cargueMasivoUbicabilidad);
                            if (inconsistencias != null) {
                                respuestaCargue.setTotalInconsistencias(respuestaCargue.getTotalInconsistencias() + 1);
                                sbInconsistencias.append(inconsistencias);
                            } else {
                                sbInconsistencias.append(iLUbicabilidad.cargaLineaUbicabilidadPersona(linea,
                                        respuestaCargue, usuarioValida, cargueMasivoUbicabilidad));
                            }
                        }
                    } catch (Exception e) {
                        List<InconsistenciaGeneralDTO> inconsistenciasRegistro = new ArrayList<>();
                        InconsistenciaGeneralDTO inconsistencia = new InconsistenciaGeneralDTO();
                        inconsistencia.setInconsistencia(e.getMessage());
                        inconsistenciasRegistro.add(inconsistencia);
                        sbInconsistencias.append(colocarInconsistenciasRegistroCargueMasUbic(linea,
                                inconsistenciasRegistro, EnumTipoCargueArchivo
                                        .obtenerPorValor(cargueMasivoUbicabilidad.getIdTipoCargueArchivo())));
                        respuestaCargue.setTotalInconsistencias(respuestaCargue.getTotalInconsistencias() + 1);
                        logger.error("Error en cargue masivo ubicabilidad", e);
                    }
                    numFila++;
                    // Actualiza el cargue cada 10 registros
                    if (numFila % 10 == 0) {
                        respuestaCargue.setTotalLeidos(
                                respuestaCargue.getTotalActualizados() + respuestaCargue.getTotalAgregados());
                        iLCargueMasivo.registrarRegistroProcesado(respuestaCargue);
                    }
                }
            }
            // Genera archivo de inconsistencias, si las hay
            if (respuestaCargue.getTotalInconsistencias() > 0) {
                ArchivoTransportableDTO archivoInconsistencias = new ArchivoTransportableDTO();
                archivoInconsistencias.setNombre(ConstantesCargaArchivos.NOMBRE_INCONSISTENCIAS + csvFile.getName());
                archivoInconsistencias.setContenido(sbInconsistencias.toString().getBytes(StandardCharsets.UTF_8));

                String codigoDoc = irRepositorioArchivo.registrarDocumento(
                        EnumCategoriaDocumento.CARGUE_MASIVO_UBICABILIDAD_INCONSISTENCIAS, archivoInconsistencias);
                respuestaCargue.setIdDocumentoInconsistencias(Long.valueOf(codigoDoc));
            }
            respuestaCargue
                    .setTotalLeidos(respuestaCargue.getTotalActualizados() + respuestaCargue.getTotalAgregados());
            respuestaCargue
                    .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.PROCESADO.getValue()));
            respuestaCargue
                    .setTotalRegistros(respuestaCargue.getTotalLeidos() + respuestaCargue.getTotalInconsistencias());
            iLCargueMasivo.cerrarCargueArchivo(respuestaCargue);
            return new AsyncResult<CargueArchivoDTO>(respuestaCargue);

        } catch (IOException | CirculemosAlertaException e) {
            logger.error("Error al procesar el cargue masivo", e);
            throw new CirculemosNegocioException(ErrorAdministracion.CargueMasivoUbicabilidad.UBIC_006002);
        }
    }

    /**
     * Carga los catalogos para validacion
     * 
     * @param cargueMasivoUbicabilidad
     * @author julio.pinzon 2016-11-22
     */
    private void cargarCatalogosCargueMasivo(CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) {
        logger.debug("UbicabilidadEJB.cargarCatalogosCargueMasivo(CargueMasivoUbicabilidadDTO)");

        // Cache de tipos de identificacion
        Map<String, Integer> codigosTipoId = new HashMap<>();
        List<ItemCatalogoDTO> tiposId = catalogoEjb
                .consultarItemsCatalogo(EnumCatalogo.TipoIdentificacionPersona.name(), null);
        for (ItemCatalogoDTO tipoIdentificacionPersonaDTO : tiposId) {
            codigosTipoId.put(tipoIdentificacionPersonaDTO.getCodigo(), tipoIdentificacionPersonaDTO.getId());
        }
        cargueMasivoUbicabilidad.setCodigosTipoId(codigosTipoId);

        if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                .equals(EnumTipoCargueArchivo.MASIVO_DIRECCION.getValue())) {
            PaisDTO pais = iRSeguridadCirculemos.obtenerPais();
            // Cache de tipos de ubicabilidad
            Map<String, Integer> codigosTipoDir = new HashMap<>();
            List<ItemCatalogoDTO> tiposDir = catalogoEjb.consultarItemsCatalogo(EnumCatalogo.TipoUbicabilidad.name(),
                    null);
            for (ItemCatalogoDTO tipoDireccion : tiposDir) {
                codigosTipoDir.put(tipoDireccion.getCodigo(), tipoDireccion.getId());
            }
            cargueMasivoUbicabilidad.setCodigosTipoDir(codigosTipoDir);

            // Cache de provincias
            Map<String, Integer> codigosProvincia = new HashMap<>();
            List<DepartamentoDTO> provincias = iLAdministracion.consultarDepartamentos(pais.getId());
            for (DepartamentoDTO provincia : provincias) {
                codigosProvincia.put(provincia.getCodigo(), provincia.getId());
            }
            cargueMasivoUbicabilidad.setCodigosProvincia(codigosProvincia);

            // Cache de canton
            Map<String, MunicipioDTO> codigosCanton = new HashMap<>();
            List<MunicipioDTO> cantones = iLAdministracion.consultarMunicipio(null);
            for (MunicipioDTO municipio : cantones) {
                codigosCanton.put(municipio.getCodigo(), municipio);
            }
            cargueMasivoUbicabilidad.setCodigosCanton(codigosCanton);

            // Cache de parroquia
            Map<String, LocalidadDTO> codigosParroquia = new HashMap<>();
            List<LocalidadDTO> parroquias = iLAdministracion.consultarLocalidad(null);
            for (LocalidadDTO localidad : parroquias) {
                codigosParroquia.put(localidad.getCodigo(), localidad);
            }
            cargueMasivoUbicabilidad.setCodigosParroquia(codigosParroquia);
        } else if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                .equals(EnumTipoCargueArchivo.MASIVO_TELEFONO.getValue())) {
            // Cache de tipos de telefono
            Map<String, Integer> codigosTipoTel = new HashMap<>();
            List<ItemCatalogoDTO> tiposTel = catalogoEjb.consultarItemsCatalogo(EnumCatalogo.TipoTelefono.name(), null);
            for (ItemCatalogoDTO tipoTelefono : tiposTel) {
                codigosTipoTel.put(tipoTelefono.getCodigo(), tipoTelefono.getId());
            }
            cargueMasivoUbicabilidad.setCodigosTipoTel(codigosTipoTel);

        }

        // Cache de tipos de telefono
        Map<Integer, String> erroresCargueUbicabilidad = new HashMap<>();
        List<ItemCatalogoDTO> errores = catalogoEjb.consultarItemsCatalogo(EnumCatalogo.ErrorCargueUbicabilidad.name(),
                null);
        for (ItemCatalogoDTO error : errores) {
            erroresCargueUbicabilidad.put(error.getId(), error.getDescripcion());
        }
        cargueMasivoUbicabilidad.setErroresCargueUbicabilidad(erroresCargueUbicabilidad);
    }

    /**
     * Valida las lineas de direcciones
     * 
     * @param linea
     * @param cargueMasivoUbicabilidad
     * @return
     * @author julio.pinzon 2016-11-22
     */
    private String validaLineaUbicabilidad(String linea, CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) {
        logger.debug("UbicabilidadEJB.validaLineaUbicabilidad(String, CargueMasivoUbicabilidadDTO)");
        String lineaInconsistencias = null;
        List<InconsistenciaGeneralDTO> inconsistenciasRegistro = new ArrayList<InconsistenciaGeneralDTO>();

        // Valida obligatoriedad de campos
        if (StringUtils.isBlank(linea)) {
            agregarInconsistencia(EnumErrorCargueUbicabilidad.CAMPOS_VACIOS, inconsistenciasRegistro,
                    cargueMasivoUbicabilidad);
        } else {
            String[] informacionRegistro = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
            String tipoDocumento = informacionRegistro[0];
            String numeroIdentificacion = informacionRegistro[1];
            String valido = null;
            String calificacion = null;

            if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_DIRECCION.getValue())) {
                String tipoDireccion = informacionRegistro[2];
                String direccion = informacionRegistro[3];
                // Departamento
                String codProvincia = informacionRegistro[4];
                // Ciudad
                String codCanton = informacionRegistro[5];
                // Localidad
                String codParroquia = informacionRegistro[6];
                valido = informacionRegistro[7];
                calificacion = informacionRegistro[8];

                // Valida obligatoriedad de campos
                if (StringUtils.isBlank(tipoDocumento) || StringUtils.isBlank(numeroIdentificacion)
                        || StringUtils.isBlank(tipoDireccion) || StringUtils.isBlank(direccion)
                        || StringUtils.isBlank(valido) || StringUtils.isBlank(calificacion)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.CAMPOS_VACIOS, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                }

                // Valida que exista el tipo de direccion
                if (StringUtils.isNotBlank(tipoDireccion)
                        && !cargueMasivoUbicabilidad.getCodigosTipoDir().containsKey(tipoDireccion)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.TIPO_DIRECCION_NO_EXISTE, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                }

                // Valida que exista la provincia
                DepartamentoDTO provincia = null;
                if (StringUtils.isNotBlank(codProvincia)
                        && !cargueMasivoUbicabilidad.getCodigosProvincia().containsKey(codProvincia)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.PROVINCIA_NO_EXISTE, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                } else if (StringUtils.isNotBlank(codProvincia)) {
                    provincia = new DepartamentoDTO(cargueMasivoUbicabilidad.getCodigosProvincia().get(codProvincia));
                }

                // Valida que exista el canton
                MunicipioDTO canton = null;
                if (StringUtils.isNotBlank(codCanton)
                        && !cargueMasivoUbicabilidad.getCodigosCanton().containsKey(codCanton)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.CANTON_NO_EXISTE, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                } else if (StringUtils.isNotBlank(codCanton)) {
                    canton = cargueMasivoUbicabilidad.getCodigosCanton().get(codCanton);
                    // Valida que si pertenezca a la provincia
                    if (provincia != null && !canton.getDepartamento().getId().equals(provincia.getId())) {
                        agregarInconsistencia(EnumErrorCargueUbicabilidad.CANTON_NO_EXISTE, inconsistenciasRegistro,
                                cargueMasivoUbicabilidad);
                    }
                }

                // Valida que exista la parroquia
                LocalidadDTO localidad = null;
                if (StringUtils.isNotBlank(codParroquia)
                        && !cargueMasivoUbicabilidad.getCodigosParroquia().containsKey(codParroquia)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.PARROQUIA_NO_EXISTE, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                } else if (StringUtils.isNotBlank(codParroquia)) {
                    localidad = cargueMasivoUbicabilidad.getCodigosParroquia().get(codParroquia);
                    // Valida que si pertenezca al canton
                    if (canton != null && !localidad.getMunicipio().getId().equals(canton.getId())) {
                        agregarInconsistencia(EnumErrorCargueUbicabilidad.PARROQUIA_NO_EXISTE, inconsistenciasRegistro,
                                cargueMasivoUbicabilidad);
                    }
                }
            } else if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_TELEFONO.getValue())) {
                String tipoTelefono = informacionRegistro[2];
                String telefono = informacionRegistro[3];
                valido = informacionRegistro[4];
                calificacion = informacionRegistro[5];

                // Valida obligatoriedad de campos
                if (StringUtils.isBlank(tipoDocumento) || StringUtils.isBlank(numeroIdentificacion)
                        || StringUtils.isBlank(tipoTelefono) || StringUtils.isBlank(telefono)
                        || StringUtils.isBlank(valido) || StringUtils.isBlank(calificacion)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.CAMPOS_VACIOS, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                }

                // Valida que exista el tipo de direccion
                if (StringUtils.isNotBlank(tipoTelefono)
                        && !cargueMasivoUbicabilidad.getCodigosTipoTel().containsKey(tipoTelefono)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.TIPO_TELEFONO_NO_EXISTE, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                }
            } else if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_CORREO.getValue())) {
                valido = informacionRegistro[3];
                calificacion = informacionRegistro[4];

                // Valida obligatoriedad de campos
                if (StringUtils.isBlank(tipoDocumento) || StringUtils.isBlank(numeroIdentificacion)
                        || StringUtils.isBlank(valido) || StringUtils.isBlank(calificacion)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.CAMPOS_VACIOS, inconsistenciasRegistro,
                            cargueMasivoUbicabilidad);
                }
            }

            // Valida que sea numerica la calificacion
            if (StringUtils.isNotBlank(calificacion) && !StringUtils.isNumeric(calificacion)) {
                agregarInconsistencia(EnumErrorCargueUbicabilidad.VALOR_CALIFICACION_INVALIDO, inconsistenciasRegistro,
                        cargueMasivoUbicabilidad);
            }

            // Valida que tenga un valor valido
            if (StringUtils.isNotBlank(valido)) {
                valido = valido.toUpperCase();
                if (!valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_SI)
                        && !valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_NO)) {
                    agregarInconsistencia(EnumErrorCargueUbicabilidad.VALOR_VALIDACION_INVALIDO,
                            inconsistenciasRegistro, cargueMasivoUbicabilidad);
                }
            }

            // Valida que exista el tipo de identificacion
            if (StringUtils.isNotBlank(tipoDocumento)
                    && !cargueMasivoUbicabilidad.getCodigosTipoId().containsKey(tipoDocumento)) {
                agregarInconsistencia(EnumErrorCargueUbicabilidad.TIPO_IDENTIFICACION_NO_EXISTE,
                        inconsistenciasRegistro, cargueMasivoUbicabilidad);
            }
        }

        // SI tiene inconsistencias las agrega y continua con el siguiente registro
        if (inconsistenciasRegistro.size() > 0) {
            lineaInconsistencias = colocarInconsistenciasRegistroCargueMasUbic(linea, inconsistenciasRegistro,
                    EnumTipoCargueArchivo.obtenerPorValor(cargueMasivoUbicabilidad.getIdTipoCargueArchivo()));
        }
        return lineaInconsistencias;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String cargaLineaUbicabilidadPersona(String linea, CargueArchivoDTO cargue, UsuarioPersonaDTO usuarioValida,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) throws CirculemosNegocioException {
        logger.debug(
                "UbicabilidadEJB.cargaLineaUbicabilidadPersona(String, CargueArchivoDTO, UsuarioPersonaDTO, CargueMasivoUbicabilidadDTO)");
        String lineaInconsistencias = "";
        Date ahora = new Date();
        List<InconsistenciaGeneralDTO> inconsistenciasRegistro = new ArrayList<InconsistenciaGeneralDTO>();
        String[] informacionRegistro = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
        String tipoDocumento = informacionRegistro[0];
        String numeroIdentificacion = informacionRegistro[1];

        int codTipoId = cargueMasivoUbicabilidad.getCodigosTipoId().get(tipoDocumento);
        EnumTipoCargueArchivo tipoCargue = EnumTipoCargueArchivo
                .obtenerPorValor(cargueMasivoUbicabilidad.getIdTipoCargueArchivo());

        PersonaDTO personaDTO = iLPersona.consultarPersona(
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo(), codTipoId,
                numeroIdentificacion);
        if (personaDTO == null) {
            agregarInconsistencia(EnumErrorCargueUbicabilidad.PERSONA_NO_EXISTE, inconsistenciasRegistro,
                    cargueMasivoUbicabilidad);
        } else {
            // Consulta direcciones
            DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
            direccionPersonaDTO.setPersona(personaDTO);
            personaDTO.setDireccionPersonaList(iLUbicabilidad.consultarDireccionPersona(direccionPersonaDTO));
            // Consulta correos
            CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
            correoPersonaDTO.setPersona(personaDTO);
            personaDTO.setCorreoPersonaList(iLUbicabilidad.consultarCorreos(correoPersonaDTO));
            // Consulta telefonos
            TelefonoPersonaDTO telefonoPersonaDTO = new TelefonoPersonaDTO();
            telefonoPersonaDTO.setPersona(personaDTO);
            personaDTO.setTelefonoPersonaDTOs(iLUbicabilidad.consultarTelefonos(telefonoPersonaDTO));

            boolean actualiza = false;

            if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_DIRECCION.getValue())) {
                actualiza = completarDireccionCargue(informacionRegistro, direccionPersonaDTO, personaDTO,
                        cargueMasivoUbicabilidad, ahora, usuarioValida);
            } else if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_TELEFONO.getValue())) {
                actualiza = completarTelefonoCargue(informacionRegistro, personaDTO, cargueMasivoUbicabilidad, ahora,
                        usuarioValida, telefonoPersonaDTO);
            } else if (cargueMasivoUbicabilidad.getIdTipoCargueArchivo()
                    .equals(EnumTipoCargueArchivo.MASIVO_CORREO.getValue())) {
                actualiza = completarCorreoCargue(informacionRegistro, personaDTO, cargueMasivoUbicabilidad, ahora,
                        usuarioValida, correoPersonaDTO);
            }
            personaDTO.setFuenteInformacion(
                    new TipoFuenteInformacionDTO(cargueMasivoUbicabilidad.getIdTipoFuenteInformacion()));
            iLPersona.modificarPersona(personaDTO, null);

            if (!actualiza) {
                cargue.setTotalAgregados(cargue.getTotalAgregados() + 1);
            } else {
                cargue.setTotalActualizados(cargue.getTotalActualizados() + 1);
            }
        }
        if (inconsistenciasRegistro.size() > 0) {
            cargue.setTotalInconsistencias(cargue.getTotalInconsistencias() + 1);
            lineaInconsistencias = colocarInconsistenciasRegistroCargueMasUbic(linea, inconsistenciasRegistro,
                    tipoCargue);
        }
        return lineaInconsistencias;
    }

    /**
     * Completa los datos de direccion persona para su registro o actualizacion
     * 
     * @param informacionRegistro
     * @param direccionPersonaDTO
     * @param personaDTO
     * @param cargueMasivoUbicabilidad
     * @param ahora
     * @param usuarioValida
     * @return True Si es una actualizacion o un registro
     * @author julio.pinzon 2016-11-22
     */
    private boolean completarDireccionCargue(String[] informacionRegistro, DireccionPersonaDTO direccionPersonaDTO,
            PersonaDTO personaDTO, CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad, Date ahora,
            UsuarioPersonaDTO usuarioValida) {

        boolean actualiza = false;

        TipoFuenteInformacionDTO tipoFuente = new TipoFuenteInformacionDTO(
                cargueMasivoUbicabilidad.getIdTipoFuenteInformacion());

        String tipoDireccion = informacionRegistro[2];
        String direccion = informacionRegistro[3];
        // Departamento
        String codProvincia = informacionRegistro[4];
        // Ciudad
        String codCanton = informacionRegistro[5];
        // Localidad
        String codParroquia = informacionRegistro[6];
        String valido = informacionRegistro[7].toUpperCase();
        String calificacion = informacionRegistro[8];
        TipoUbicabilidadDTO tipoDir = new TipoUbicabilidadDTO();
        tipoDir.setId(cargueMasivoUbicabilidad.getCodigosTipoDir().get(tipoDireccion));
        // Valida que tenga un valor valido
        Boolean esValido = null;
        if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_SI)) {
            esValido = true;
        } else if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_NO)) {
            esValido = false;
        }

        // Valida que exista la provincia
        DepartamentoDTO provincia = null;
        if (StringUtils.isNotBlank(codProvincia)) {
            provincia = new DepartamentoDTO(cargueMasivoUbicabilidad.getCodigosProvincia().get(codProvincia));
        }

        // Valida que exista el canton
        MunicipioDTO canton = null;
        if (StringUtils.isNotBlank(codCanton)) {
            canton = cargueMasivoUbicabilidad.getCodigosCanton().get(codCanton);
        }

        // Valida que exista la parroquia
        LocalidadDTO localidad = null;
        if (StringUtils.isNotBlank(codParroquia)) {
            localidad = cargueMasivoUbicabilidad.getCodigosParroquia().get(codParroquia);
        }
        for (DireccionPersonaDTO direccionPer : personaDTO.getDireccionPersonaList()) {
            if (StringUtils.equalsIgnoreCase(direccionPer.getDireccion().getComplemento(), direccion)) {
                direccionPer.setEstado(esValido);
                direccionPer.setPrioridad(Integer.parseInt(calificacion));
                direccionPer.setFechaActualizacion(ahora);
                direccionPer.setUsuarioActualiza(usuarioValida);
                if (esValido) {
                    direccionPer.setFechaValidacion(ahora);
                    direccionPer.setUsuarioValida(usuarioValida);
                    direccionPer.setTipoFuenteValidacion(tipoFuente);
                }
                actualiza = true;
                break;
            }
        }
        if (!actualiza) {
            DireccionDTO direccionDTO = new DireccionDTO();
            direccionDTO.setPais(iRSeguridadCirculemos.obtenerPais());
            direccionDTO.setComplemento(direccion);
            // Tipo via
            TipoViaDTO tipoViaPrincipalDTO = new TipoViaDTO();
            tipoViaPrincipalDTO.setCodigo(EnumTipoVia.SIN_IDENTIFICAR.getCodigo());
            direccionDTO.setTipoViaPrincipal(tipoViaPrincipalDTO);

            // Tipo ubicabilidad
            direccionDTO.setTipoUbicabilidad(tipoDir);

            direccionDTO.setDepartamento(provincia);
            direccionDTO.setMunicipio(canton);
            direccionDTO.setLocalidad(localidad);

            direccionPersonaDTO.setDireccion(direccionDTO);
            direccionPersonaDTO.setEstado(esValido);
            direccionPersonaDTO.setPrioridad(Integer.parseInt(calificacion));
            direccionPersonaDTO.setFechaActualizacion(ahora);
            direccionPersonaDTO.setFechaRegistro(ahora);
            direccionPersonaDTO.setUsuarioRegistro(usuarioValida);
            direccionPersonaDTO.setUsuarioActualiza(usuarioValida);
            direccionPersonaDTO.setTipoFuenteInformacion(tipoFuente);
            if (esValido) {
                direccionPersonaDTO.setFechaValidacion(new Date());
                direccionPersonaDTO.setUsuarioValida(usuarioValida);
                direccionPersonaDTO.setTipoFuenteValidacion(tipoFuente);
            }
            personaDTO.getDireccionPersonaList().add(direccionPersonaDTO);
        }
        return actualiza;
    }

    /**
     * Completa los datos de telefono persona para su registro o actualizacion
     * 
     * @param informacionRegistro
     * @param telefonoPersonaDTO
     * @param personaDTO
     * @param cargueMasivoUbicabilidad
     * @param ahora
     * @param usuarioValida
     * @return True Si es una actualizacion o un registro
     * @author julio.pinzon 2016-11-22
     */
    private boolean completarTelefonoCargue(String[] informacionRegistro, PersonaDTO personaDTO,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad, Date ahora, UsuarioPersonaDTO usuarioValida,
            TelefonoPersonaDTO telefonoPersonaDTO) {

        boolean actualiza = false;

        TipoFuenteInformacionDTO tipoFuente = new TipoFuenteInformacionDTO(
                cargueMasivoUbicabilidad.getIdTipoFuenteInformacion());

        String tipoTelefono = informacionRegistro[2];
        String telefono = informacionRegistro[3];
        String valido = informacionRegistro[4].toUpperCase();
        String calificacion = informacionRegistro[5];

        TipoTelefonoDTO tipoTel = new TipoTelefonoDTO(cargueMasivoUbicabilidad.getCodigosTipoTel().get(tipoTelefono));

        // Valida que tenga un valor valido
        Boolean esValido = null;
        if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_SI)) {
            esValido = true;
        } else if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_NO)) {
            esValido = false;
        }
        for (TelefonoPersonaDTO telefonoPer : personaDTO.getTelefonoPersonaDTOs()) {
            if (StringUtils.equalsIgnoreCase(telefonoPer.getNumeroTelefono(), telefono)) {
                telefonoPer.setEstado(esValido);
                telefonoPer.setPrioridad(Integer.parseInt(calificacion));
                telefonoPer.setFechaActualizacion(ahora);
                telefonoPer.setUsuarioActualiza(usuarioValida);
                if (esValido) {
                    telefonoPer.setFechaValidacion(ahora);
                    telefonoPer.setUsuarioValida(usuarioValida);
                    telefonoPer.setTipoFuenteValidacion(tipoFuente);
                }
                actualiza = true;
                break;
            }
        }
        if (!actualiza) {
            telefonoPersonaDTO.setNumeroTelefono(telefono);
            telefonoPersonaDTO.setTipoTelefono(tipoTel);
            telefonoPersonaDTO.setEstado(esValido);
            telefonoPersonaDTO.setPrioridad(Integer.parseInt(calificacion));
            telefonoPersonaDTO.setFechaActualizacion(ahora);
            telefonoPersonaDTO.setFechaRegistro(ahora);
            telefonoPersonaDTO.setUsuarioRegistro(usuarioValida);
            telefonoPersonaDTO.setUsuarioActualiza(usuarioValida);
            telefonoPersonaDTO.setTipoFuenteInformacion(tipoFuente);
            if (esValido) {
                telefonoPersonaDTO.setFechaValidacion(new Date());
                telefonoPersonaDTO.setUsuarioValida(usuarioValida);
                telefonoPersonaDTO.setTipoFuenteValidacion(tipoFuente);
            }
            personaDTO.getTelefonoPersonaDTOs().add(telefonoPersonaDTO);
        }
        return actualiza;
    }

    /**
     * Completa los datos de telefono persona para su registro o actualizacion
     * 
     * @param informacionRegistro
     * @param personaDTO
     * @param cargueMasivoUbicabilidad
     * @param ahora
     * @param usuarioValida
     * @return True Si es una actualizacion o un registro
     * @author julio.pinzon 2016-11-22
     * @param correoPersonaDTO
     */
    private boolean completarCorreoCargue(String[] informacionRegistro, PersonaDTO personaDTO,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad, Date ahora, UsuarioPersonaDTO usuarioValida,
            CorreoPersonaDTO correoPersonaDTO) {

        boolean actualiza = false;

        TipoFuenteInformacionDTO tipoFuente = new TipoFuenteInformacionDTO(
                cargueMasivoUbicabilidad.getIdTipoFuenteInformacion());
        String correo = informacionRegistro[2];
        String valido = informacionRegistro[3].toUpperCase();
        String calificacion = informacionRegistro[4];

        // Valida que tenga un valor valido
        Boolean esValido = null;
        if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_SI)) {
            esValido = true;
        } else if (valido.equals(ConstantesCargaArchivos.DIRECCION_VALIDO_NO)) {
            esValido = false;
        }
        for (CorreoPersonaDTO correoPer : personaDTO.getCorreoPersonaList()) {
            if (StringUtils.equalsIgnoreCase(correoPer.getCorreoElectronico(), correo)) {
                correoPer.setEstado(esValido);
                correoPer.setPrioridad(Integer.parseInt(calificacion));
                correoPer.setFechaActualizacion(ahora);
                correoPer.setUsuarioActualiza(usuarioValida);
                if (esValido) {
                    correoPer.setFechaValidacion(ahora);
                    correoPer.setUsuarioValida(usuarioValida);
                    correoPer.setTipoFuenteValidacion(tipoFuente);
                }
                actualiza = true;
                break;
            }
        }
        if (!actualiza) {
            correoPersonaDTO.setCorreoElectronico(correo);
            correoPersonaDTO.setEstado(esValido);
            correoPersonaDTO.setPrioridad(Integer.parseInt(calificacion));
            correoPersonaDTO.setFechaActualizacion(ahora);
            correoPersonaDTO.setFechaRegistro(ahora);
            correoPersonaDTO.setUsuarioRegistro(usuarioValida);
            correoPersonaDTO.setUsuarioActualiza(usuarioValida);
            correoPersonaDTO.setTipoFuenteInformacion(tipoFuente);
            if (esValido) {
                correoPersonaDTO.setFechaValidacion(new Date());
                correoPersonaDTO.setUsuarioValida(usuarioValida);
                correoPersonaDTO.setTipoFuenteValidacion(tipoFuente);
            }
            personaDTO.getCorreoPersonaList().add(correoPersonaDTO);
        }
        return actualiza;
    }

    /**
     * Agrega una inconsistencia a la lista
     * 
     * @param error
     * @param inconsistenciasRegistro
     * @author julio.pinzon 2016-11-22
     */
    private void agregarInconsistencia(EnumErrorCargueUbicabilidad error,
            List<InconsistenciaGeneralDTO> inconsistenciasRegistro,
            CargueMasivoUbicabilidadDTO cargueMasivoUbicabilidad) {
        logger.debug(
                "UbicabilidadEJB.agregarInconsistencia(EnumErrorCargueUbicabilidad, List<InconsistenciaGeneralDTO>,CargueMasivoUbicabilidadDTO)");

        InconsistenciaGeneralDTO inconsistencia = new InconsistenciaGeneralDTO();
        inconsistencia.setInconsistencia(cargueMasivoUbicabilidad.getErroresCargueUbicabilidad().get(error.getValue()));
        inconsistenciasRegistro.add(inconsistencia);
    }

    /**
     * Concatena la la inconsistencia
     * 
     * @param linea
     * @param inconsistenciasRegistro
     * @return Texto de inconsistencia
     * @author julio.pinzon 2016-11-22
     */
    private String colocarInconsistenciasRegistroCargueMasUbic(String linea,
            List<InconsistenciaGeneralDTO> inconsistenciasRegistro, EnumTipoCargueArchivo tipoCargueArchivo) {
        logger.debug(
                "UbicabilidadEJB.colocarInconsistenciasRegistroCargueMasUbic(String, List<InconsistenciaGeneralDTO>)");
        String inconsistenciasCol = "";
        if (inconsistenciasRegistro != null && !inconsistenciasRegistro.isEmpty()) {
            String[] arrStrInonsistencias = new String[inconsistenciasRegistro.size()];
            int i = 0;
            for (InconsistenciaGeneralDTO error : inconsistenciasRegistro) {
                arrStrInonsistencias[i] = error.getInconsistencia();
                i++;
            }
            for (int j = 0; j < arrStrInonsistencias.length; j++) {
                inconsistenciasCol += (j == arrStrInonsistencias.length - 1 ? arrStrInonsistencias[j]
                        : arrStrInonsistencias[j] + ConstantesCargaArchivos.SEPARADOR_INCONSISTENCIAS);
            }
        }
        String[] informacionRegistro = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
        for (int i = informacionRegistro.length; i < tipoCargueArchivo.getNumeroColumnas(); i++) {
            linea = linea + ConstantesCargaArchivos.CSV_SEPARADOR;
        }
        linea = linea + ConstantesCargaArchivos.CSV_SEPARADOR + inconsistenciasCol
                + ConstantesCargaArchivos.getSeparadorLineaSistema();
        return linea;
    }
}