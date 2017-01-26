package co.com.datatools.c2.managed_bean.parametros.configuraciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

import com.google.common.collect.Collections2;

/**
 * Managed Bean Utilizado para crear logica dinamica de los formularios de Parametrizacion de Configuraciones ADM_066
 * 
 * @author Felipe.Martinez
 * 
 */
@ManagedBean
@SessionScoped
public class FormularioConfiguracionesMB extends AbstractC2ManagedBean {

    private static final String SELECCIONMULTIPLE = "SELECCIONMULTIPLE";
    private static final String SELECCIONUNICA = "SELECCIONUNICA";
    private static final long serialVersionUID = 5370590267297285300L;
    private final static Logger logger = Logger.getLogger(FormularioConfiguracionesMB.class.getName());

    private static final String NOMBRE_LABEL_lab_encab_entradas = "lab_encab_entradas";
    private static final String NOMBRE_LABEL_lab_encab_salidas = "lab_encab_salidas";
    private static final String NOMBRE_CONTROL_LABEL = "label";
    private static final String NOMBRE_CONTROL_SEPARADOR = "separador";

    private static final String SEPARADOR_ENTRADA_SALIDA = "<h3 style='margin-top:1;margin-bottom:0;'>%s</h3>";

    @EJB
    private IRConfiguracion configuracionEjb;

    @EJB
    private IRCatalogo catalogoEjb;

    public void construirFormularioActualizacion() {
        final ConfiguracionesFL fl = findFlowObject(ConfiguracionesFL.class, ConfiguracionesMB.CONFIGURACIONES_FL);
        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class,
                ConfiguracionesMB.CONFIGURACIONES_HOLDER_FL);
        // Inicializa el objeto donde se almacenan los valores del registro
        RegistroConfiguracionDTO registro = fl.getRegistro();
        if (registro == null) {
            if (holder.getSeleccion() != null) {
                fl.setRegistro(holder.getSeleccion());
                registro = fl.getRegistro();
            }
        }
        construirFormulario();
    }

    /**
     * Inicializa la configuracion del formulario dinamico de acuerdo al tipo de configuracion seleccionada por el usuario
     */
    public void construirFormulario() {
        final ConfiguracionesFL fl = findFlowObject(ConfiguracionesFL.class, ConfiguracionesMB.CONFIGURACIONES_FL);
        if (fl.getModel() != null) {
            // El modelo ya esta inicializado en el fl, termina
            return;
        }
        final ConfiguracionesHolderFL holder = findFlowObject(ConfiguracionesHolderFL.class,
                ConfiguracionesMB.CONFIGURACIONES_HOLDER_FL);

        RegistroConfiguracionDTO registro = fl.getRegistro();
        if (registro == null) {
            fl.setRegistro(registro = RegistroConfiguracionDTO.construirRegistroVacio(holder.getConfiguracion()));
        }

        final TipoConfiguracionDTO configuracion = holder.getConfiguracion();

        final Map<String, List<SelectItem>> catalogosWeb = holder.getCatalogosConfiguracion();

        final DynaFormModel fModel = new DynaFormModel();
        DynaFormRow row;

        // despliega la fila del formulario que dice que son campos de entrada
        row = fModel.createRegularRow();
        String entradas = getBundle(ConfiguracionesMB.NOMBRE_BUNDLE_CONFIGURACIONES).getString(
                NOMBRE_LABEL_lab_encab_entradas);
        row.addControl(String.format(SEPARADOR_ENTRADA_SALIDA, entradas), NOMBRE_CONTROL_SEPARADOR, 2, 0);
        int fila = 0;
        for (CampoConfiguracionDTO campo : configuracion.getEntradas()) {
            fila = generarFila(fila, registro, catalogosWeb, fModel, campo);
        }

        String salidas = getBundle(ConfiguracionesMB.NOMBRE_BUNDLE_CONFIGURACIONES).getString(
                NOMBRE_LABEL_lab_encab_salidas);
        row = fModel.createRegularRow();
        row.addControl(String.format(SEPARADOR_ENTRADA_SALIDA, salidas), NOMBRE_CONTROL_SEPARADOR, 2, 0);

        for (CampoConfiguracionDTO campo : configuracion.getSalidas()) {
            fila = generarFila(fila++, registro, catalogosWeb, fModel, campo);
        }

        fl.setModel(fModel);
    }

    @SuppressWarnings("unchecked")
    private int generarFila(int fila, final RegistroConfiguracionDTO registro,
            final Map<String, List<SelectItem>> catalogosWeb, final DynaFormModel fModel,
            final CampoConfiguracionDTO campo) {
        final ConfiguracionesFL fl = findFlowObject(ConfiguracionesFL.class, ConfiguracionesMB.CONFIGURACIONES_FL);
        DynaFormRow row;
        if (campo.esTipo(TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            // Si el catalogo es compuesto se generan mas filas de acuerdo al numero de dependencias
            final Map<String, List<String>> valorCampo = (Map<String, List<String>>) registro.getValorCampo(campo
                    .getCodigo());
            ItemCatalogoDTO filtroCatalogo = new ItemCatalogoDTO();
            filtroCatalogo.setActivo(true);
            for (Iterator<CampoConfiguracionDTO> it = campo.getDependenciaCatalogo().iterator(); it.hasNext();) {
                CampoConfiguracionDTO herencia = it.next();

                row = fModel.createRegularRow();
                CampoFormularioDinamico campoDerivado = new CampoFormularioDinamico(herencia, valorCampo.get(herencia
                        .getCodigo()), true, fila++);

                if (herencia.equals(campo.getDependenciaCatalogo().getFirst())) {
                    campoDerivado.setSeleccionables(new ArrayList<>(Collections2.transform(
                            catalogoEjb.consultarItemsCatalogo(herencia.getAttrCatOrigenDatos(), filtroCatalogo),
                            ConfiguracionesMB.TRANS_CATALOGO_TO_SELECTITEM)));
                }
                if (it.hasNext())
                    campoDerivado.setAjaxActivo(true);

                row.addControl(campoDerivado, NOMBRE_CONTROL_LABEL);
                row.addControl(campoDerivado, generarTipoControl(herencia));
                fl.getDataFormulario().add(campoDerivado);
            }
        } else {
            final CampoFormularioDinamico campoForm = new CampoFormularioDinamico(campo, registro.getValorCampo(campo
                    .getCodigo()), false, fila++);
            if (campo.esTipo(TipoCampoConfiguracion.CATALOGOSIMPLE, TipoCampoConfiguracion.CATALOGOINDEPENDIENTE)) {
                campoForm.setSeleccionables(catalogosWeb.get(campo.getCodigo()));
            }
            row = fModel.createRegularRow();
            row.addControl(campoForm, NOMBRE_CONTROL_LABEL);
            row.addControl(campoForm, generarTipoControl(campo));
            fl.getDataFormulario().add(campoForm);
        }
        return fila;
    }

    public static final String generarTipoControl(CampoConfiguracionDTO campoPlantilla) {
        if (campoPlantilla.esTipo(TipoCampoConfiguracion.CATALOGOSIMPLE, TipoCampoConfiguracion.CATALOGOINDEPENDIENTE,
                TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            if (campoPlantilla.getAttrCatSeleccion() == 1) {
                return SELECCIONUNICA;
            } else {
                return SELECCIONMULTIPLE;
            }
        }
        return campoPlantilla.getTipo().toString();
    }

    /**
     * Procesa la informacion ingresada por el usuario en el formulario dinamico
     * 
     * @return registro con la informacion ingresada por el usuario
     */
    @SuppressWarnings("unchecked")
    public RegistroConfiguracionDTO procesarFormularioDinamico() {
        ConfiguracionesFL fl = findFlowObject(ConfiguracionesFL.class, ConfiguracionesMB.CONFIGURACIONES_FL);
        DynaFormModel model = fl.getModel();

        RegistroConfiguracionDTO registro = fl.getRegistro();

        CampoFormularioDinamico campoForm = null;
        for (DynaFormControl formControl : model.getControls()) {
            if (!formControl.getType().equals(NOMBRE_CONTROL_LABEL)
                    && !formControl.getType().equals(NOMBRE_CONTROL_SEPARADOR)
                    && formControl.getData() instanceof CampoFormularioDinamico) {

                campoForm = ((CampoFormularioDinamico) formControl.getData());

                if (campoForm.isDerivado()) {
                    List<String> listas = (List<String>) campoForm.getValorFinal();
                    Collections.sort(listas);
                    registro.getValorCampoCompuesto(campoForm.getCampo().getDependenciaCatalogo().getFirst()).put(
                            campoForm.getCampo().getAttrCatOrigenDatos(), listas);

                } else {
                    registro.setValorCampo(campoForm.getCampo().getCodigo(), campoForm.getValorFinal());
                }
            }
        }

        logger.infov("Registro Generado del Formulario Dinamico {0}", registro);
        return registro;
    }

    public void procesarAjaxCatalogo(CampoFormularioDinamico campoCambio) {
        logger.debug("FormularioConfiguracionesMB.procesarAjaxCatalogo()");

        final ConfiguracionesFL fl = findFlowObject(ConfiguracionesFL.class, ConfiguracionesMB.CONFIGURACIONES_FL);
        final CampoFormularioDinamico dependiente = fl.getDataFormulario().get(campoCambio.getFila() + 1);
        logger.debugv(
                "Cambio fila: {0} nombreCatalogo: {1}, valorActual: {4} - actualizar fila: {2} nombreCatalogo: {3}",
                campoCambio.getFila(), campoCambio.getCampo().getAttrCatOrigenDatos(), dependiente.getFila(),
                dependiente.getCampo().getAttrCatOrigenDatos(), campoCambio.getValor());

        // ESTA SECCION DE CODIGO ES LA INTEGRACION CON EL CASO DE USO DE CATALOGOS COMPUESTOS
        // SUPONE QUE SE DEBE ENVIAR EL ID DEL ELEMENTO DE LA DEPENDENCIA PARA OBTENER LOS ELEMENTOS DEL CATALOGO DEPENDIENTE
        ItemCatalogoDTO filtro = new ItemCatalogoDTO();
        filtro.setActivo(true);
        filtro.setItemCatalogoDependenciaDTO(new ItemCatalogoDTO(Integer.valueOf(campoCambio.getValor().toString())));
        final List<ItemCatalogoDTO> itemsFiltrados = catalogoEjb.consultarItemsCatalogo(dependiente.getCampo()
                .getAttrCatOrigenDatos(), filtro);
        // /
        dependiente.setSeleccionables(new ArrayList<>(Collections2.transform(itemsFiltrados,
                ConfiguracionesMB.TRANS_CATALOGO_TO_SELECTITEM)));

    }
}
