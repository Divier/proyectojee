package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoResolucionSimitDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int idTipoResolucionSimit;
    private String nombreResolucion;

    // Constructors Declaration

    public TipoResolucionSimitDTO() {

    }

    // Start Methods Declaration

    public int getIdTipoResolucionSimit() {
        return idTipoResolucionSimit;
    }

    public void setIdTipoResolucionSimit(int idTipoResolucionSimit) {
        this.idTipoResolucionSimit = idTipoResolucionSimit;
    }

    public String getNombreResolucion() {
        return nombreResolucion;
    }

    public void setNombreResolucion(String nombreResolucion) {
        this.nombreResolucion = nombreResolucion;
    }

    // Finish the class
}