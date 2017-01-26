package co.com.datatools.c2.managed_bean.parametros.configuraciones;

import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._BOOLEANO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CADENA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOCOMPUESTO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOINDEPENDIENTE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOSIMPLE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CORREO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._DECIMAL;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHAHORA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._HORA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._NUMERO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._URL;
import static org.apache.commons.lang3.ObjectUtils.NULL;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.Operador;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Managed Bean Parametrizacion de Configuraciones (Reglas de negocio) ADM_066
 * 
 * @author Felipe.Martinez
 * 
 */
@ManagedBean
@SessionScoped
public class ConfiguracionesMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 507317189397665954L;

    static final String NOMBRE_BUNDLE_CONFIGURACIONES = "labelConfiguraciones";
    private static final String NOMBRE_MENSAJE_val_obligatorio = "msj_val_obligatorio";
    private static final String NOMBRE_MENSAJE_val_longitud = "msj_val_longitud";
    private static final String NOMBRE_MENSAJE_val_validacion = "msj_val_validacion";
    private static final String NOMBRE_MENSAJE_val_rango_fechas = "msj_val_rango_fechas";
    private static final String NOMBRE_MENSAJE_val_entradas_existen = "msj-val_entradas_existen";
    private static final String LAB_ITEM_CATALOGO_INVALIDO = "lab_item_catalogo_invalido";

    private final static Logger logger = Logger.getLogger(ConfiguracionesMB.class.getName());

    /**
     * Nombre de objeto del flujo para Holder
     */
    final static String CONFIGURACIONES_HOLDER_FL = "configuracionesHolderFL";
    /**
     * Nombre de objeto del flujo
     */
    final static String CONFIGURACIONES_FL = "configuracionesFL";

    @ManagedProperty(value = "#{formularioConfiguracionesMB}")
    public FormularioConfiguracionesMB formularioConfiguracionesMB;

    @EJB
    private IRConfiguracion configuracionEjb;

    @EJB
    private IRCatalogo catalogoEjb;

    public ConfiguracionesMB() {

    }

    public void consultar() {
        logger.debug("ConfiguracionesMB.consultar()");
        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class, CONFIGURACIONES_HOLDER_FL);
        holder.setSeleccion(null);
        final TipoConfiguracionDTO plantilla = configuracionEjb.consultarPlantillaConfiguracion(holder
                .getFiltroConfiguracion());
        holder.setConfiguracion(plantilla);
        logger.info(holder.getConfiguracion().getMapaCampos());

        holder.setRegistrosConfiguracion(configuracionEjb.consultarValorConfiguracion(holder.getFiltroConfiguracion()));
        if (holder.getRegistrosConfiguracion().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        }

        // --Procesar la plantilla para desagregar campos de catalogos compuestos
        if (plantilla.isContieneCatalogosCompuestos()) {
            int entradas = 0, salidas = 0;
            List<CampoConfiguracionDTO> copia = new ArrayList<>(plantilla.listaCampos().size() + 3);
            for (CampoConfiguracionDTO campo : plantilla.listaCampos()) {
                if (campo.isEntrada())
                    entradas += campo.getDependenciaCatalogo().isEmpty() ? 1 : campo.getDependenciaCatalogo().size();
                else
                    salidas += campo.getDependenciaCatalogo().isEmpty() ? 1 : campo.getDependenciaCatalogo().size();

                if (campo.esTipo(_CATALOGOCOMPUESTO)) {
                    copia.addAll(campo.getDependenciaCatalogo());
                } else {
                    copia.add(campo);
                }

            }
            holder.setNumeroEntradas(entradas);
            holder.setNumeroSalidas(salidas);
            holder.setListaCampos(copia);
        } else {
            holder.setNumeroEntradas(plantilla.getNumeroEntradas());
            holder.setNumeroSalidas(plantilla.getNumeroSalidas());
            holder.setListaCampos(plantilla.listaCampos());
        }

        if (plantilla.isContieneCatalogos()) {
            holder.setCatalogosConfiguracion(procesarCatalogos(holder.getConfiguracion()));
        }
    }

    private Map<String, List<SelectItem>> procesarCatalogos(final TipoConfiguracionDTO configuracion) {

        if (configuracion.isContieneCatalogos()) {
            final Map<String, List<ItemCatalogoDTO>> catalogosCampos = configuracionEjb
                    .consultarCatalogosConfiguracion(configuracion.getCodigo());

            final Map<String, List<SelectItem>> catalogosWeb = new HashMap<>(catalogosCampos.size());

            List<SelectItem> selectItems = null;
            for (Entry<String, List<ItemCatalogoDTO>> cat : catalogosCampos.entrySet()) {
                selectItems = new ArrayList<>(Collections2.transform(cat.getValue(), TRANS_CATALOGO_TO_SELECTITEM));
                Collections.sort(selectItems, FachadaCatalogosMB.SELECT_ITEM_COMPARATOR);
                catalogosWeb.put(cat.getKey(), selectItems);
            }
            return catalogosWeb;
        }
        return Collections.emptyMap();
    }

    static Function<ItemCatalogoDTO, SelectItem> TRANS_CATALOGO_TO_SELECTITEM = new Function<ItemCatalogoDTO, SelectItem>() {
        @Override
        public SelectItem apply(ItemCatalogoDTO item) {
            return new SelectItem(item.getId(), item.getNombre(), item.getDescripcion());
        }
    };

    /**
     * Retorna el valor que se debe presentar en la tabla de consulta para un campo de un registro
     * 
     * @param campo
     *            campo del registro a procesar
     * @param registro
     *            registro a procesar
     * @return objeto que se despliega al usuario para una columna de un registro
     */
    @SuppressWarnings("unchecked")
    public Object valorCampoConsulta(CampoConfiguracionDTO campo, RegistroConfiguracionDTO registro) {
        if (NULL.equals(registro.getValorCampo(campo)))
            return "";
        else if (campo.esTipo(_CATALOGOSIMPLE, _CATALOGOINDEPENDIENTE, _CATALOGOCOMPUESTO)) {
            List<String> valores = null;
            if (campo.esTipo(_CATALOGOCOMPUESTO)) {
                valores = registro.getValorCampoCompuesto(campo.getDependenciaCatalogo().getFirst()).get(
                        campo.getAttrCatOrigenDatos());
            } else {
                valores = (List<String>) registro.getValorCampo(campo);
            }

            if (campo.getAttrCatSeleccion() != 1)
                return valores;
            else
                return valores.isEmpty() ? "" : formatearCatalogo(campo, valores.get(0));
        } else
            return registro.getValorCampo(campo);
    }

    public String formatearCatalogo(CampoConfiguracionDTO campo, final String value) {
        if (campo.esTipo(_CATALOGOCOMPUESTO)) {
            // Si el catalogo es compuesto se consulta uno a uno cada id del registro
            final List<ItemCatalogoDTO> items = catalogoEjb.consultarItemsCatalogo(campo.getAttrCatOrigenDatos(),
                    new ItemCatalogoDTO(Integer.valueOf(value)));
            if (!items.isEmpty()) {
                return items.get(0).getNombre();
            }
            return value;
        } else {
            // Por desempeño se consultan los valores de los catalogos del cache temporal creado al inicio de la consulta
            // para los catalogos simples o independientes
            final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class,
                    CONFIGURACIONES_HOLDER_FL);
            List<SelectItem> list = holder.getCatalogosConfiguracion().get(campo.getCodigo());
            for (SelectItem item : list) {
                if (item.getValue().toString().equals(value)) {
                    return item.getLabel();
                }
            }
        }
        return super.getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(LAB_ITEM_CATALOGO_INVALIDO);
    }

    public boolean registrar() {
        logger.debug("ConfiguracionesMB.registrar()");
        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class, CONFIGURACIONES_HOLDER_FL);
        final RegistroConfiguracionDTO registro = formularioConfiguracionesMB.procesarFormularioDinamico();

        if (validarRegistro(registro)) {
            try {
                configuracionEjb.registrarValorConfiguracion(holder.getFiltroConfiguracion(), registro);
                CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
                return true;
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            } finally {
                consultar();
            }
        }
        return false;
    }

    public boolean modificar() {
        logger.debug("ConfiguracionesMB.modificar()");
        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class, CONFIGURACIONES_HOLDER_FL);
        final RegistroConfiguracionDTO registro = formularioConfiguracionesMB.procesarFormularioDinamico();

        if (validarRegistro(registro)) {
            try {
                configuracionEjb.actualizarValorConfiguracion(holder.getFiltroConfiguracion(), registro);
                CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
                return true;
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            } finally {
                consultar();
            }
        }
        return false;
    }

    private static final String PLANTILLA_ID_CLIENTE = "dynaForm:r%sc2reg:%s";

    private boolean validarRegistro(RegistroConfiguracionDTO registro) {

        logger.info("Atributos request: " + getFacesContext().getExternalContext().getRequestParameterMap());

        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class, CONFIGURACIONES_HOLDER_FL);
        List<String> idCampos = new ArrayList<>(holder.getConfiguracion().getMapaCampos().keySet());

        String msjtemp = null;

        List<CampoConfiguracionDTO> valObligatorios = configuracionEjb.validarCamposObligatorios(
                holder.getFiltroConfiguracion(), registro);
        if (!valObligatorios.isEmpty()) {
            msjtemp = getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(NOMBRE_MENSAJE_val_obligatorio);
            for (CampoConfiguracionDTO campo : valObligatorios) {

                getFacesContext().addMessage(procIdComponente(idCampos, campo),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msjtemp));
            }
            return false;
        }

        List<Triple<CampoConfiguracionDTO, Operador, Object>> validaciones = configuracionEjb.validarLongitudCampos(
                holder.getFiltroConfiguracion(), registro);
        if (!validaciones.isEmpty()) {
            msjtemp = getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(NOMBRE_MENSAJE_val_longitud);
            for (Triple<CampoConfiguracionDTO, Operador, Object> triple : validaciones) {
                getFacesContext().addMessage(
                        procIdComponente(idCampos, triple.getLeft()),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(msjtemp,
                                triple.getRight())));
            }
            return false;
        }

        validaciones = configuracionEjb.validarOperadoresLogicos(holder.getFiltroConfiguracion(), registro);
        if (!validaciones.isEmpty()) {
            msjtemp = getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(NOMBRE_MENSAJE_val_validacion);
            for (Triple<CampoConfiguracionDTO, Operador, Object> triple : validaciones) {
                getFacesContext().addMessage(
                        procIdComponente(idCampos, triple.getLeft()),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(msjtemp,
                                getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(triple.getMiddle().toString()),
                                triple.getRight())));
            }
            return false;
        }

        List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> inconsistenciaRangos = configuracionEjb
                .validarRangosFechas(holder.getFiltroConfiguracion(), registro);
        if (!inconsistenciaRangos.isEmpty()) {
            msjtemp = getBundle(NOMBRE_BUNDLE_CONFIGURACIONES).getString(NOMBRE_MENSAJE_val_rango_fechas);
            for (Pair<CampoConfiguracionDTO, CampoConfiguracionDTO> pair : inconsistenciaRangos) {
                getFacesContext().addMessage(
                        procIdComponente(idCampos, pair.getLeft()),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(msjtemp, pair.getLeft()
                                .getNombre(), pair.getRight().getNombre())));

                getFacesContext().addMessage(
                        procIdComponente(idCampos, pair.getRight()),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(msjtemp, pair.getLeft()
                                .getNombre(), pair.getRight().getNombre())));
            }
            return false;
        }

        if (!configuracionEjb.validarEntradaUnica(holder.getFiltroConfiguracion(), registro)) {
            addErrorMessage(NOMBRE_BUNDLE_CONFIGURACIONES, NOMBRE_MENSAJE_val_entradas_existen);
            return false;
        }
        return true;
    }

    public String procIdComponente(List<String> idCampos, CampoConfiguracionDTO campo) {
        return String.format(PLANTILLA_ID_CLIENTE, idCampos.indexOf(campo.getCodigo()) + (campo.isEntrada() ? 2 : 3),
                FormularioConfiguracionesMB.generarTipoControl(campo));
    }

    public void eliminar() {
        logger.debug("ConfiguracionesMB.eliminar()");
        ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class, CONFIGURACIONES_HOLDER_FL);
        configuracionEjb.eliminarValorconfiguracion(holder.getSeleccion().getId());
        CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
        consultar();
    }

    public FormularioConfiguracionesMB getFormularioConfiguracionesMB() {
        return formularioConfiguracionesMB;
    }

    public void setFormularioConfiguracionesMB(FormularioConfiguracionesMB formularioConfiguracionesMB) {
        this.formularioConfiguracionesMB = formularioConfiguracionesMB;
    }

    /**
     * Permite definir el tipo de componente estatico que se debe utilizar para desplegar un campo en la pantalla de consulta
     * 
     * @param campo
     *            Informacion del campo(columna) que se esta procesando
     * @param tipoComponente
     *            {CADENA,BOOLEANO,NUMERO,FECHA,LISTA}
     * @return true en el caso en que el campo puede ser renderizado con el tipoComponente indicado
     */
    public boolean esTipoComponente(CampoConfiguracionDTO campo, String tipoComponente) {
        String tipo = _CADENA.toString();

        if (campo.esTipo(_BOOLEANO))
            tipo = _BOOLEANO.toString();
        else if (campo.esTipo(_DECIMAL, _NUMERO, _HORA))
            tipo = _NUMERO.toString();
        else if (campo.esTipo(_CADENA, _CORREO, _URL))
            tipo = _CADENA.toString();
        else if (campo.esTipo(_FECHA))
            tipo = _FECHA.toString();
        else if (campo.esTipo(_FECHAHORA))
            tipo = _FECHAHORA.toString();
        else if (campo.esTipo(_CATALOGOSIMPLE, _CATALOGOCOMPUESTO, _CATALOGOINDEPENDIENTE))
            tipo = campo.getAttrCatSeleccion() != 1 ? "LISTA" : _CADENA.toString();
        else
            throw new CirculemosRuntimeException("Valor no esperado en switch {0}", campo.getTipo());

        return tipo.equalsIgnoreCase(tipoComponente);
    }
}
