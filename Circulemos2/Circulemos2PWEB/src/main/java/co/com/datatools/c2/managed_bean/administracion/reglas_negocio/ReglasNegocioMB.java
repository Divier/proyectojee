package co.com.datatools.c2.managed_bean.administracion.reglas_negocio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

@ManagedBean
@SessionScoped
public class ReglasNegocioMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 5175801065217767130L;

    private List<SelectItem> reglas = new ArrayList<>(0);
    private String reglaSeleccionada;

    private List<Object> registrosRegla = new ArrayList<>(0);
    private Object registroReglaSeleccionado;

    @PostConstruct
    public void init() {
        reglas.add(new SelectItem("REGLA_1", "Regla #1"));
    }

    public void consultar() {
    }

    public void agregarRegistro() {
        registrosRegla.add(new Object());

    }

    public void modificarRegistro() {

    }

    public void eliminarRegistro() {

    }

    public List<SelectItem> getReglas() {
        return reglas;
    }

    public void setReglas(List<SelectItem> reglas) {
        this.reglas = reglas;
    }

    public List<?> getRegistrosRegla() {
        return registrosRegla;
    }

    public void setRegistrosRegla(List<Object> registrosRegla) {
        this.registrosRegla = registrosRegla;
    }

    public Object getRegistroReglaSeleccionado() {
        return registroReglaSeleccionado;
    }

    public void setRegistroReglaSeleccionado(Object registroReglaSeleccionado) {
        this.registroReglaSeleccionado = registroReglaSeleccionado;
    }

    public String getReglaSeleccionada() {
        return reglaSeleccionada;
    }

    public void setReglaSeleccionada(String reglaSeleccionada) {
        this.reglaSeleccionada = reglaSeleccionada;
    }

}
