package co.com.datatools.c2.dt.administracion;

import java.io.Serializable;

/**
 * 
 * @author diego.fonseca DTO que se utliza para consultar la dirección basado en el id de DireccionDTO
 */
public class ConsultaDireccionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1927881151048254404L;

    Long idDireccion;

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

}
