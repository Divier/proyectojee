package co.com.datatools.c2.managed_bean.parametros.configuraciones;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.extensions.model.dynaform.DynaFormModel;

import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Objeto de flujo para alamacenar las variables usadas durante el registro o actualizacion de un registro del ADM_066
 * 
 * @author Felipe.Martinez
 */
public class ConfiguracionesFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    /**
     * Mantiene el modelo dinamico del formulario de una configuracion
     */
    private DynaFormModel model = null;

    /**
     * Informacion del registro que esta siendo creado/actualizado
     */
    private RegistroConfiguracionDTO registro = null;

    /**
     * Este mapa sirve para referenciar los diferentes catalogos compuestos del formulario, manteniendo una referencia centralizada de las listas
     * dinamicas de elementos
     */
    private List<CampoFormularioDinamico> dataFormulario = new ArrayList<>(3);

    public DynaFormModel getModel() {
        return model;
    }

    public void setModel(DynaFormModel model) {
        this.model = model;
    }

    public RegistroConfiguracionDTO getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroConfiguracionDTO registro) {
        this.registro = registro;
    }

    public List<CampoFormularioDinamico> getDataFormulario() {
        return dataFormulario;
    }

    public void setDataFormulario(List<CampoFormularioDinamico> dataFormulario) {
        this.dataFormulario = dataFormulario;
    }
}
