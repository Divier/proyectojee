package co.com.datatools.c2.negocio.ejb.parametrizacion;

import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOCOMPUESTO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOINDEPENDIENTE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOSIMPLE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHAHORA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._HORA;
import static co.com.datatools.c2.util.Utilidades.safeList;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.NULL;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.Operador;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.OperandoEspecial;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;
import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.entidades.Configuracion;
import co.com.datatools.c2.entidades.ValorConfiguracion;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.error.parametrizacion.ErrorParametrizacion;
import co.com.datatools.c2.negocio.error.parametrizacion.ErrorParametrizacion.ParametrizacionConfiguraciones;
import co.com.datatools.c2.negocio.helpers.parametrizacion.ProcesadorXmlConfiguracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.util.BeanLocatorC2;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * @author Felipe.Martinez
 */
@Stateless(name = "ConfiguracionEJB")
@LocalBean
public class ConfiguracionEJB implements IRConfiguracion {

    private final static Logger logger = Logger.getLogger(ConfiguracionEJB.class.getName());

    private static final String PARAM_SQL_COD_CONF = "pCodigo";
    private static final String PARAM_SQL_ID_REGISTRO = "pIdExistente";
    private static final String SQL_ENCONTRAR_DATOS_CONFIG = "SELECT v FROM ValorConfiguracion v WHERE v.configuracion.codigo = :"
            + PARAM_SQL_COD_CONF + " ";
    private static final String CONDICIONES_REGISTRO_EXISTE = " AND v.id != :" + PARAM_SQL_ID_REGISTRO;
    private static final String CONDICIONES_ESTRUCTURA_CAMPO = " AND v.valor LIKE :p{0} ESCAPE ''!''";
    private static final String VALOR_LIKE = "%<campo><codigo>{0}</codigo><valor>{1}</valor></campo>%";

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRCatalogo catalogoEjb;

    @Override
    public List<ItemCatalogoDTO> consultarTiposConfiguracion() {
        final GenericDao<Configuracion> configDao = new GenericDao<Configuracion>(Configuracion.class, em);

        List<Configuracion> resultList = configDao.findByAttributes(null);
        List<ItemCatalogoDTO> respuesta = new ArrayList<>(resultList.size());
        for (Configuracion conf : resultList) {
            ItemCatalogoDTO item = new ItemCatalogoDTO();
            item.setId(conf.getId());
            item.setCodigo(conf.getCodigo());
            item.setNombre(conf.getNombre());
            respuesta.add(item);
        }

        return respuesta;
    }

    @Override
    public TipoConfiguracionDTO consultarPlantillaConfiguracion(String codigoTipoConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");

        TypedQuery<Configuracion> query = em.createNamedQuery(Configuracion.SQ_FIND_BY_CODIGO, Configuracion.class);
        query.setParameter(PARAM_SQL_COD_CONF, codigoTipoConfiguracion);

        Configuracion result = query.getSingleResult();

        TipoConfiguracionDTO tipoConfiguracion = new TipoConfiguracionDTO(result.getId(), result.getCodigo(),
                result.getDescripcion(), new ProcesadorXmlConfiguracion().parseXmlPlantilla(result.getPlantilla()));

        if (tipoConfiguracion.isContieneCatalogosCompuestos()) {
            for (CampoConfiguracionDTO campo : tipoConfiguracion.listaCampos()) {
                if (campo.esTipo(_CATALOGOCOMPUESTO)) {
                    CatalogoDTO ini = new CatalogoDTO();
                    ini.setNombreEntidad(campo.getAttrCatOrigenDatos());
                    ini.setIdCatalogoDependencia(true);
                    ini = catalogoEjb.consultarCatalogos(ini).get(0);

                    while (ini != null) {
                        CampoConfiguracionDTO derivado = new CampoConfiguracionDTO(_CATALOGOCOMPUESTO, false);

                        derivado.setNombre(ini.getNombre());
                        derivado.setCodigo(campo.getCodigo() + "." + ini.getId());
                        derivado.setObligatorio(campo.isObligatorio());
                        derivado.setAttrCatSeleccion(1);
                        derivado.setAttrCatOrigenDatos(ini.getNombreEntidad());
                        derivado.getDependenciaCatalogo().push(campo);
                        derivado.setEntrada(campo.isEntrada());

                        campo.getDependenciaCatalogo().push(derivado);
                        ini = ini.getCatalogoDependenciaDTO();
                    }
                    campo.getDependenciaCatalogo().getLast().setAttrCatSeleccion(campo.getAttrCatSeleccion());
                    campo.getDependenciaCatalogo().getLast().setNombre(campo.getNombre());
                }
            }
        }
        return tipoConfiguracion;
    }

    @Override
    public List<RegistroConfiguracionDTO> consultarValorConfiguracion(String codigoTipoConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");

        TypedQuery<ValorConfiguracion> query = em.createNamedQuery(ValorConfiguracion.SQ_FIND_BY_CODIGO_CONF,
                ValorConfiguracion.class);
        query.setParameter(PARAM_SQL_COD_CONF, codigoTipoConfiguracion);

        List<ValorConfiguracion> result = safeList(query.getResultList());

        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        List<RegistroConfiguracionDTO> response = new ArrayList<>(result.size());

        for (ValorConfiguracion valor : result) {
            response.add(ProcesadorXmlConfiguracion.construirRegistro(plantilla, valor.getId(), valor.getValor()));
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<ItemCatalogoDTO>> consultarCatalogosConfiguracion(String codigoTipoConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");

        Map<String, List<ItemCatalogoDTO>> valoresCatalogos = new HashMap<>();
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        for (CampoConfiguracionDTO campo : plantilla.listaCampos()) {
            if (campo.esTipo(_CATALOGOSIMPLE)) {
                ItemCatalogoDTO item = new ItemCatalogoDTO();
                item.setActivo(true);
                valoresCatalogos.put(campo.getCodigo(),
                        catalogoEjb.consultarItemsCatalogo(campo.getAttrCatOrigenDatos(), item));

            } else if (campo.esTipo(_CATALOGOINDEPENDIENTE)) {
                Object bean = BeanLocatorC2.lookup(campo.nombreInterfaz());
                try {

                    Object result = bean.getClass().getMethod(campo.nombreMetodo()).invoke(bean);
                    if (result instanceof List) {
                        valoresCatalogos.put(campo.getCodigo(), (List<ItemCatalogoDTO>) result);
                    }

                } catch (NoSuchMethodException | SecurityException e1) {
                    throw new CirculemosRuntimeException(
                            "No es posible encontrar el objeto para el catalogo independiente {0}, objeto: {1}",
                            campo.getCodigo(), campo.nombreInterfaz());
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new CirculemosRuntimeException(
                            "No es posible ejecutar el metodo para el catalogo independiente {0}, objeto: {1}, metodo:{2}",
                            campo.getCodigo(), campo.nombreInterfaz(), campo.nombreMetodo());
                }
            }
        }
        return valoresCatalogos;
    }

    @Override
    public List<CampoConfiguracionDTO> validarCamposObligatorios(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        return validarCamposObligatorios(plantilla, registroConfiguracion);
    }

    @Override
    public List<Triple<CampoConfiguracionDTO, Operador, Object>> validarOperadoresLogicos(
            String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        return validarOperadoresLogicos(plantilla, registroConfiguracion);
    }

    @Override
    public List<Triple<CampoConfiguracionDTO, Operador, Object>> validarLongitudCampos(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        return validarLongitudCampos(plantilla, registroConfiguracion);
    }

    @Override
    public List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> validarRangosFechas(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        return validarRangosFechas(plantilla, registroConfiguracion);
    }

    @Override
    public boolean validarEntradaUnica(String codigoTipoConfiguracion, RegistroConfiguracionDTO registroConfiguracion) {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        return validarEntradaUnica(plantilla, registroConfiguracion);
    }

    @Override
    public void registrarValorConfiguracion(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion) throws CirculemosNegocioException {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion, "Registro de configuracion es requerido");

        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);

        agruparValidaciones(plantilla, registroConfiguracion);

        ValorConfiguracion entidad = new ValorConfiguracion();
        entidad.setConfiguracion(new Configuracion(plantilla.getId()));
        entidad.setValor(ProcesadorXmlConfiguracion.generarXML(plantilla, registroConfiguracion));
        em.persist(entidad);
    }

    @Override
    public void actualizarValorConfiguracion(String codigoTipoConfiguracion,
            RegistroConfiguracionDTO registroConfiguracion) throws CirculemosNegocioException {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(registroConfiguracion);
        checkNotNull(registroConfiguracion.getId());

        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);

        agruparValidaciones(plantilla, registroConfiguracion);

        ValorConfiguracion entidad = em.find(ValorConfiguracion.class, registroConfiguracion.getId());

        entidad.setValor(ProcesadorXmlConfiguracion.generarXML(plantilla, registroConfiguracion));
        em.merge(entidad);
    }

    @Override
    public void eliminarValorconfiguracion(int idRegistro) {
        em.remove(em.getReference(ValorConfiguracion.class, idRegistro));
    }

    private List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> validarRangosFechas(
            TipoConfiguracionDTO plantilla, RegistroConfiguracionDTO registroConfiguracion) {

        if (plantilla.getRangos().isEmpty()) {
            return Collections.emptyList();
        }

        List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> inconsistencias = new ArrayList<>(plantilla
                .getRangos().size());
        Object objIni, objFin;

        for (Pair<CampoConfiguracionDTO, CampoConfiguracionDTO> rango : plantilla.getRangos()) {

            objIni = registroConfiguracion.getValorCampo(rango.getLeft().getCodigo());
            objFin = registroConfiguracion.getValorCampo(rango.getRight().getCodigo());

            // Error, si se ingresa la final y no la inicial
            if (NULL.equals(objIni)) {
                if (!NULL.equals(objFin))
                    inconsistencias.add(rango);
            } else {
                // Error, inicial mayor a final
                if (!NULL.equals(objFin))
                    if (((Date) objIni).compareTo((Date) objFin) > 0)
                        inconsistencias.add(rango);
            }

        }

        return inconsistencias;
    }

    private List<CampoConfiguracionDTO> validarCamposObligatorios(TipoConfiguracionDTO plantilla,
            RegistroConfiguracionDTO registroConfiguracion) {
        List<CampoConfiguracionDTO> inconsistencias = new ArrayList<>(1);
        Object valor;
        for (CampoConfiguracionDTO campo : plantilla.listaCampos()) {
            if (campo.isObligatorio()) {
                if ((valor = registroConfiguracion.getValorCampo(campo.getCodigo())).equals(NULL)) {
                    inconsistencias.add(campo);
                } else if (valor instanceof String && StringUtils.isBlank((String) valor)) {
                    inconsistencias.add(campo);
                }
            }
        }
        return inconsistencias;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<Triple<CampoConfiguracionDTO, Operador, Object>> validarLongitudCampos(TipoConfiguracionDTO plantilla,
            RegistroConfiguracionDTO registroConfiguracion) {
        final List<Triple<CampoConfiguracionDTO, Operador, Object>> inconsistencias = new ArrayList<>(1);
        List<Entry<Operador, Object>> vals = null;
        Integer longitud = null;
        String cadena = null;
        // recorer cada campo de la plantilla
        for (CampoConfiguracionDTO campo : plantilla.listaCampos()) {
            // No ejecuta validacion cuando no hay valores en el campo
            if (registroConfiguracion.getValorCampo(campo).equals(ObjectUtils.NULL))
                continue;

            vals = campo.encontrarValidaciones(Operador.LONGITUD);
            if (!vals.isEmpty()) {
                // El objeto asociado al operador debe ser un Long
                longitud = (Integer) vals.get(0).getValue();
                // El tipo del objeto que se va a validar deber ser String
                cadena = (String) registroConfiguracion.getValorCampo(campo);
                if (cadena.length() > longitud)
                    inconsistencias.add(new ImmutableTriple<>(campo, Operador.LONGITUD, vals.get(0).getValue()));
            } else if (campo.getAttrCatSeleccion() > 0) {
                List<String> elementos = null;
                if (campo.esTipo(_CATALOGOCOMPUESTO)) {
                    elementos = registroConfiguracion.getValorCampoCompuesto(campo).get(campo.getAttrCatOrigenDatos());
                } else {
                    elementos = (List<String>) registroConfiguracion.getValorCampo(campo);
                }
                if (elementos.size() > campo.getAttrCatSeleccion()) {
                    inconsistencias.add(new ImmutableTriple(campo, Operador.LONGITUD, Integer.valueOf(campo
                            .getAttrCatSeleccion())));
                }
            }
        }
        return inconsistencias;
    }

    private boolean validarEntradaUnica(TipoConfiguracionDTO plantilla, RegistroConfiguracionDTO registroConfiguracion) {

        final GenericDao<ValorConfiguracion> dao = new GenericDao<>(ValorConfiguracion.class, em);
        final StringBuilder sql = new StringBuilder(SQL_ENCONTRAR_DATOS_CONFIG);
        final Map<String, Object> params = GenericDao.buildMap(PARAM_SQL_COD_CONF, plantilla.getCodigo());

        if (registroConfiguracion.getId() != null) {
            sql.append(CONDICIONES_REGISTRO_EXISTE);
            params.put(PARAM_SQL_ID_REGISTRO, registroConfiguracion.getId());

        }

        String valor = null;
        boolean incluirEntrada = false;
        boolean validarRangos = false;
        for (CampoConfiguracionDTO entrada : plantilla.getEntradas()) {
            incluirEntrada = true;
            if (entrada.esTipo(_FECHA)) {
                if (entrada.getAttrRngCodigoRango() != null && !entrada.isAttrRngTraslapar()) {
                    // No se verifica si este campo se encuentra en el registro de
                    // entrada debido a que es un rango que no se puede traslapar
                    incluirEntrada = false;
                    validarRangos = true;
                }
            }

            if (incluirEntrada) {
                valor = ProcesadorXmlConfiguracion.marshalValorCampo(entrada.getTipo(),
                        registroConfiguracion.getValorCampo(entrada.getCodigo()));
                sql.append(MessageFormat.format(CONDICIONES_ESTRUCTURA_CAMPO, entrada.getCodigo()));
                params.put("p" + entrada.getCodigo(), MessageFormat.format(VALOR_LIKE, entrada.getCodigo(), valor));
            }
        }

        final List<ValorConfiguracion> result = dao.buildAndExecuteQuery(sql, params);

        if (result.isEmpty()) {
            // Si no hay coincidencia con el resto de campos, no se necesita validar rangos
            return true;
        } else if (validarRangos) {
            final List<RegistroConfiguracionDTO> registros = new ArrayList<>(0);

            for (Pair<CampoConfiguracionDTO, CampoConfiguracionDTO> rango : plantilla.getRangos()) {
                if (rango.getLeft().isEntrada() && !rango.getLeft().isAttrRngTraslapar()) {
                    // Por cada rango de entrada que no se pueda traslapar,
                    // realiza la validacion contra los registros que coincidieron parcialmente
                    for (Iterator<ValorConfiguracion> it = result.iterator(); it.hasNext();) {
                        registros.clear();
                        ValorConfiguracion valConf = it.next();
                        registros.add(ProcesadorXmlConfiguracion.construirRegistro(plantilla, valConf.getId(),
                                valConf.getValor()));
                        if (rangoTraslapado(rango.getLeft().getCodigo(),
                                registroConfiguracion.getValorCampo(rango.getLeft()), rango.getRight().getCodigo(),
                                registroConfiguracion.getValorCampo(rango.getRight()), registros)) {
                            return false;
                        }
                    }
                }
            }
            // Si no fallo con ninguno de los registros que coincidia parcialmente, es por que cumple la validacion de llave unica
            return true;
        }
        return false;
    }

    @Override
    public <T> T consultarDatoConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException {
        try {
            return procesarDatoConfiguracion(codigoTipoConfiguracion, dto);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CirculemosRuntimeException(e);
        }
    }

    private <T> T procesarDatoConfiguracion(String codigoTipoConfiguracion, T dtoEntradas)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, CirculemosNegocioException {
        checkNotNull(codigoTipoConfiguracion, "Codigo de tipo de configuracion es requerido");
        checkNotNull(dtoEntradas, "Dto con informacion de entrada es requerido");
        // Init
        final GenericDao<ValorConfiguracion> dao = new GenericDao<>(ValorConfiguracion.class, em);
        final TipoConfiguracionDTO plantilla = consultarPlantillaConfiguracion(codigoTipoConfiguracion);
        final StringBuilder sql = new StringBuilder(SQL_ENCONTRAR_DATOS_CONFIG);
        final Map<String, Object> params = GenericDao.buildMap(PARAM_SQL_COD_CONF, codigoTipoConfiguracion);

        // Valor en string de cada campo de entrada enviado en el dto, que no pertenece a rango
        String valorStrDto = null;
        for (CampoConfiguracionDTO in : plantilla.getEntradas()) {
            if (in.getAttrRngCodigoRango() == null) {
                valorStrDto = ProcesadorXmlConfiguracion.marshalValorCampo(in.getTipo(),
                        PropertyUtils.getProperty(dtoEntradas, in.getCodigo()));
                sql.append(MessageFormat.format(CONDICIONES_ESTRUCTURA_CAMPO, in.getCodigo()));

                String param = MessageFormat.format(VALOR_LIKE, in.getCodigo(), valorStrDto);

                System.out.println(param);
                param = param.replace("[", "![").replace("]", "!]");

                params.put("p" + in.getCodigo(), param);
            }
        }
        sql.append(" ORDER BY v.id");
        // Ejecuta la consulta con los filtros que existan de acuerdo a campos de entrada
        final List<ValorConfiguracion> result = dao.buildAndExecuteQuery(sql, params);
        if (result.isEmpty()) {
            // final anticipado, si no hay resultados de la consulta
            logger.infov("No se ha encontrado datos para la configuracion {0}, usando los parametros {1}",
                    codigoTipoConfiguracion, params);
            throw new CirculemosNegocioException(ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066007,
                    " codigoTipoConfiguracion: " + codigoTipoConfiguracion + ", parametros: " + params + ", clase: "
                            + dtoEntradas.getClass().getName());
        }

        RegistroConfiguracionDTO match = null;
        if (!plantilla.isContieneRangosEntrada()) {
            if (result.size() > 1) {
                throw new CirculemosNegocioException(ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066008,
                        " codigoTipoConfiguracion: " + codigoTipoConfiguracion + ", parametros: " + params
                                + ", clase: " + dtoEntradas.getClass().getName());
            }
            match = ProcesadorXmlConfiguracion.construirRegistro(plantilla, result.get(0).getId(), result.get(0)
                    .getValor());
        } else {
            // Hay rangos en los campos de entrada, se deben filtrar los registros encontrados
            final List<RegistroConfiguracionDTO> registros = new ArrayList<>(result.size());
            for (ValorConfiguracion reg : result) {
                registros.add(ProcesadorXmlConfiguracion.construirRegistro(plantilla, reg.getId(), reg.getValor()));
            }

            long regIni, regFin;
            Object propiedadDto;
            for (Pair<CampoConfiguracionDTO, CampoConfiguracionDTO> rango : plantilla.getRangos()) {
                if (!rango.getLeft().isEntrada())
                    break;
                propiedadDto = PropertyUtils.getProperty(dtoEntradas, rango.getLeft().getCodigo());
                checkNotNull(propiedadDto, "se requiere valor para el campo: " + rango.getLeft().getCodigo()
                        + ", de la clase: " + dtoEntradas.getClass().getName());

                final Date valMatchRango = (Date) propiedadDto;
                for (Iterator<RegistroConfiguracionDTO> it = registros.iterator(); it.hasNext();) {
                    RegistroConfiguracionDTO registro = it.next();

                    regIni = NULL.equals(registro.getValorCampo(rango.getLeft())) ? 0L : ((Date) registro
                            .getValorCampo(rango.getLeft())).getTime();
                    regFin = NULL.equals(registro.getValorCampo(rango.getRight())) ? Long.MAX_VALUE : ((Date) registro
                            .getValorCampo(rango.getRight())).getTime();

                    if (!longEntre(regIni, regFin, valMatchRango.getTime())) {
                        it.remove();
                    }
                }
            }
            if (registros.isEmpty()) {
                throw new CirculemosNegocioException(ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066007,
                        " codigoTipoConfiguracion: " + codigoTipoConfiguracion + ", parametros: " + params
                                + ", clase: " + dtoEntradas.getClass().getName());
            } else if (registros.size() > 1) {
                throw new CirculemosNegocioException(ErrorParametrizacion.ParametrizacionConfiguraciones.ADM_066008,
                        " codigoTipoConfiguracion: " + codigoTipoConfiguracion + ", parametros: " + params
                                + ", clase: " + dtoEntradas.getClass().getName());
            } else {
                match = registros.get(0);
            }
        }
        // Pasar los valores de cada campo de salida del registro al dto recibido por parametro
        for (CampoConfiguracionDTO out : plantilla.getSalidas()) {
            Object valorCampo = match.getValorCampo(out);
            PropertyUtils.setProperty(dtoEntradas, out.getCodigo(), NULL.equals(valorCampo) ? null : valorCampo);
        }
        return dtoEntradas;
    }

    private List<Triple<CampoConfiguracionDTO, Operador, Object>> validarOperadoresLogicos(
            final TipoConfiguracionDTO plantilla, RegistroConfiguracionDTO registroConfiguracion) {
        List<Triple<CampoConfiguracionDTO, Operador, Object>> inconsistencias = new ArrayList<>(1);

        List<Entry<Operador, Object>> vals = null;
        // Valor del objeto contra el que realiza la validacion
        Object operando = null;
        Object valor = null;

        boolean valido = false;
        // recorer cada campo de la plantilla
        for (CampoConfiguracionDTO campo : plantilla.listaCampos()) {
            vals = campo.encontrarValidaciones(Operador.MENOR, Operador.MENOR_IGUAL, Operador.MAYOR,
                    Operador.MAYOR_IGUAL, Operador.IGUAL, Operador.DIFERENTE);
            if (vals.isEmpty())
                continue;

            valor = registroConfiguracion.getValorCampo(campo.getCodigo());
            for (Entry<Operador, Object> validacion : vals) {

                operando = validacion.getValue();

                if (operando instanceof CampoConfiguracionDTO) {
                    operando = registroConfiguracion.getValorCampo(((CampoConfiguracionDTO) operando).getCodigo());
                }

                if (ObjectUtils.NULL.equals(valor) || ObjectUtils.NULL.equals(operando)) {
                    if (Operador.IGUAL.equals(validacion.getKey())) {
                        valido = valor.equals(operando);
                    } else if (Operador.DIFERENTE.equals(validacion.getKey())) {
                        valido = !valor.equals(operando);
                    } else {
                        logger.infov("No se aplica la validacion para el campo {0}, {1}{2}{3}", campo.getCodigo(),
                                valor, validacion.getKey(), operando);
                    }
                } else {
                    int resComparacion = comparar(campo.getCodigo(), valor, operando, registroConfiguracion, plantilla);
                    switch (validacion.getKey()) {
                    case MENOR:
                        valido = resComparacion < 0;
                        break;
                    case MENOR_IGUAL:
                        valido = resComparacion <= 0;
                        break;
                    case MAYOR:
                        valido = resComparacion > 0;
                        break;
                    case MAYOR_IGUAL:
                        valido = resComparacion >= 0;
                        break;
                    case IGUAL:
                        valido = resComparacion == 0;
                        break;
                    case DIFERENTE:
                        valido = resComparacion != 0;
                        break;
                    default:
                        throw new CirculemosRuntimeException("Valor no esperado en switch {0}", validacion.getKey());
                    }
                }

                if (!valido) {
                    inconsistencias.add(new ImmutableTriple<>(campo, validacion.getKey(), operando));
                }
            }
        }
        return inconsistencias;
    }

    /**
     * Compara el valorCampo con operando
     * 
     * @param codigoCampo
     *            codigo del campo que se esta comparando
     * @param valorCampo
     *            valor del campo a comparar
     * @param operando
     *            valor contra el que se compara
     * @param registroConfiguracion
     *            permite acceder a toda la informacion del registro en caso de que la comparacion se deba hacer con el valor de otro campo
     * @param plantilla
     *            definicion de la configuracion que se esta procesando, utilizada para condicionar el tipo de campo que se esta validando
     * @return un entero negativo, cero, o un entero positivo si valorCampo es menor, igual o mayor que operando
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private int comparar(final String codigoCampo, final Object valorCampo, Object operando,
            final RegistroConfiguracionDTO registroConfiguracion, final TipoConfiguracionDTO plantilla) {
        int comparacion;
        if (operando == OperandoEspecial.FECHA_ACTUAL) {
            // Cuando el campo tiene un operando que trae la variable especial FECHA_ACTUAL
            TipoCampoConfiguracion tipo = plantilla.getMapaCampos().get(codigoCampo).getTipo();
            if (tipo == _FECHA) {
                comparacion = UtilFecha.resetTime(((Date) valorCampo)).getTime()
                        .compareTo(UtilFecha.currentZeroTimeDate());
            } else if (tipo == _FECHAHORA) {
                comparacion = ((Date) valorCampo).compareTo(UtilFecha.buildCalendar().getTime());
            } else if (tipo == _HORA) {
                // uso cualquier año como referencia, la validacion la define la hora
                Calendar calValor = UtilFecha.buildCalendar((Date) valorCampo);
                Calendar calActual = UtilFecha.buildCalendar();
                calValor.set(2000, 0, 1);
                calActual.set(2000, 0, 1);
                comparacion = calValor.compareTo(calActual);
            } else {
                throw new CirculemosRuntimeException(
                        "Error de parametrizacion, se esta validando el campo {0} con la variable {1} pero el campo no es del tipo fecha/hora",
                        codigoCampo, OperandoEspecial.FECHA_ACTUAL);
            }
        } else {
            if (operando instanceof CampoConfiguracionDTO) {
                // Cuando el operando es validado contra otro campo del registro
                operando = registroConfiguracion.getValorCampo(((CampoConfiguracionDTO) operando).getCodigo());
            }
            comparacion = ((Comparable) valorCampo).compareTo(operando);
        }
        return comparacion;
    }

    private void agruparValidaciones(TipoConfiguracionDTO plantilla, RegistroConfiguracionDTO registroConfiguracion)
            throws CirculemosNegocioException {
        final List<CampoConfiguracionDTO> valObligatorios = validarCamposObligatorios(plantilla, registroConfiguracion);
        if (!valObligatorios.isEmpty()) {
            logger.infov("No se cumple la validacion campos obligatorios, para los campos {0}", valObligatorios);
            throw new CirculemosNegocioException(ParametrizacionConfiguraciones.ADM_066002);
        }

        List<Triple<CampoConfiguracionDTO, Operador, Object>> validaciones = validarLongitudCampos(plantilla,
                registroConfiguracion);
        if (!validaciones.isEmpty()) {
            logger.infov("No se cumplen las validaciones de longitud de campos: {0}", validaciones);
            throw new CirculemosNegocioException(ParametrizacionConfiguraciones.ADM_066003);
        }

        validaciones = validarOperadoresLogicos(plantilla, registroConfiguracion);
        if (!validaciones.isEmpty()) {
            logger.infov("No se cumplen las validaciones de campos: {0}", validaciones);
            throw new CirculemosNegocioException(ParametrizacionConfiguraciones.ADM_066004);
        }

        List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> inconsistenciaRangos = validarRangosFechas(plantilla,
                registroConfiguracion);
        if (!inconsistenciaRangos.isEmpty()) {
            logger.infov("Rangos inconsistentes del registro: {0}", inconsistenciaRangos);
            throw new CirculemosNegocioException(ParametrizacionConfiguraciones.ADM_066005);
        }

        if (!validarEntradaUnica(plantilla, registroConfiguracion)) {
            throw new CirculemosNegocioException(ParametrizacionConfiguraciones.ADM_066001);
        }

    }

    private boolean rangoTraslapado(String codFechaIni, Object valFechaIni, String codFechaFin, Object valFechaFin,
            List<RegistroConfiguracionDTO> registros) {

        long valIni = NULL.equals(valFechaIni) ? 0L : ((Date) valFechaIni).getTime();
        long valFin = NULL.equals(valFechaFin) ? Long.MAX_VALUE : ((Date) valFechaFin).getTime();

        long regIni, regFin;
        for (RegistroConfiguracionDTO registro : registros) {
            regIni = NULL.equals(registro.getValorCampo(codFechaIni)) ? 0L : ((Date) registro
                    .getValorCampo(codFechaIni)).getTime();
            regFin = NULL.equals(registro.getValorCampo(codFechaFin)) ? Long.MAX_VALUE : ((Date) registro
                    .getValorCampo(codFechaFin)).getTime();

            if (longEntre(valIni, valFin, regIni) || longEntre(valIni, valFin, regFin)
                    || longEntre(regIni, regFin, valIni) || longEntre(regIni, regFin, valFin)) {
                return true;
            }
        }
        return false;
    }

    private boolean longEntre(long inferior, long superior, long valor) {
        return inferior <= valor && valor <= superior;
    }
}