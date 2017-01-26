package co.com.datatools.ingreso.objetos;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

public class Rol implements Serializable, Principal {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int idRol;
    private String nombre;
    private List<String> grupos;
    private List<String> aplicaciones;

    public Rol() {
    }

    @Override
    public String getName() {
        return nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    public List<String> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(List<String> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

}
