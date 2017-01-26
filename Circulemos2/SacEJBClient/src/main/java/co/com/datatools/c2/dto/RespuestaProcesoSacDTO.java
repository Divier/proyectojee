/**
 * 
 */
package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * @author javier.fajardo
 *
 */
public class RespuestaProcesoSacDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int numeroExitosos;
    private int numeroFallidos;

    public int getNumeroExitosos() {
        return numeroExitosos;
    }

    public void setNumeroExitosos(int numeroExitosos) {
        this.numeroExitosos = numeroExitosos;
    }

    public int getNumeroFallidos() {
        return numeroFallidos;
    }

    public void setNumeroFallidos(int numeroFallidos) {
        this.numeroFallidos = numeroFallidos;
    }
}
