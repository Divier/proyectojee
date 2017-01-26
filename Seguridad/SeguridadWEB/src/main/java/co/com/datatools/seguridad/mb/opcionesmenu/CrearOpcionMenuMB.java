package co.com.datatools.seguridad.mb.opcionesmenu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.seguridad.dto.comun.LlaveValorDto;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean que gestiona la pagina de creacion de opciones de menu
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class CrearOpcionMenuMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    private String idParent;
    private String nombre;
    private String idRecurso;
    private String nombreRecurso;
    private List<LlaveValorDto> atributos = new ArrayList<LlaveValorDto>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public List<LlaveValorDto> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<LlaveValorDto> atributos) {
        this.atributos = atributos;
    }

    public void reset() {
        setNombre(null);
        setIdParent(null);
        setAtributos(new ArrayList<LlaveValorDto>());
        setIdRecurso(null);
        setNombreRecurso(null);
    }

    public void quitarRecurso() {
        setIdRecurso(null);
        setNombreRecurso(null);
    }

    public void quitarAtributo(LlaveValorDto dto) {
        atributos.remove(dto);
    }

    public void agregarAtributo() {
        atributos.add(new LlaveValorDto());
    }
}
