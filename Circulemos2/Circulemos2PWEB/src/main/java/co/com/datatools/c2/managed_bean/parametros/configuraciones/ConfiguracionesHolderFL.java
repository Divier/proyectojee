package co.com.datatools.c2.managed_bean.parametros.configuraciones;

import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Objeto de flujo para almacenar las variables usadas en la navegacion del CU ADM_066
 * 
 * @author Felipe.Martinez
 */
public class ConfiguracionesHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    /**
     * Tipo de configuracion seleccionada por el usuario para consultar
     */
    private String filtroConfiguracion = null;

    // -- Configuracion
    private TipoConfiguracionDTO configuracion;

    // -- Registros
    private List<RegistroConfiguracionDTO> registrosConfiguracion = null;

    private RegistroConfiguracionDTO seleccion = null;

    // --
    private Map<String, List<SelectItem>> catalogosConfiguracion = null;

    private int numeroEntradas;
    private int numeroSalidas;
    private List<CampoConfiguracionDTO> listaCampos;

    /**
     * Tipo de configuracion seleccionada por el usuario para consultar
     * 
     * @return codigo de la configuracion seleccionada
     */
    public String getFiltroConfiguracion() {
        return filtroConfiguracion;
    }

    public void setFiltroConfiguracion(String filtroConfiguracion) {
        this.filtroConfiguracion = filtroConfiguracion;
    }

    /**
     * Informacion de la configuracion seleccionada con la estructura procesada de la plantilla XML
     * 
     * @return configuracion que fue seleccionada
     */
    public TipoConfiguracionDTO getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(TipoConfiguracionDTO configuracion) {
        this.configuracion = configuracion;
    }

    public List<RegistroConfiguracionDTO> getRegistrosConfiguracion() {
        return registrosConfiguracion;
    }

    public void setRegistrosConfiguracion(List<RegistroConfiguracionDTO> registrosConfiguracion) {
        this.registrosConfiguracion = registrosConfiguracion;
    }

    public RegistroConfiguracionDTO getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(RegistroConfiguracionDTO seleccion) {
        this.seleccion = seleccion;
    }

    public Map<String, List<SelectItem>> getCatalogosConfiguracion() {
        return catalogosConfiguracion;
    }

    public void setCatalogosConfiguracion(Map<String, List<SelectItem>> catalogosConfiguracion) {
        this.catalogosConfiguracion = catalogosConfiguracion;
    }

    public int getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(int numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public int getNumeroSalidas() {
        return numeroSalidas;
    }

    public void setNumeroSalidas(int numeroSalidas) {
        this.numeroSalidas = numeroSalidas;
    }

    public List<CampoConfiguracionDTO> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CampoConfiguracionDTO> listaCampos) {
        this.listaCampos = listaCampos;
    }

}
