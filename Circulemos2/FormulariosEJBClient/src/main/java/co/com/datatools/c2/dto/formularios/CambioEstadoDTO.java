package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.enumeraciones.EnumCausalCambioEstado;

/**
 * 
 * @author giovanni.velandia
 * 
 */
public class CambioEstadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private EnumCausalCambioEstado enumCausalCambioEstado;
    private Date fechaCambio;
    private String numeroFormulario;
    private int tipoFormulario;

    public EnumCausalCambioEstado getEnumCausalCambioEstado() {
        return enumCausalCambioEstado;
    }

    public void setEnumCausalCambioEstado(EnumCausalCambioEstado enumCausalCambioEstado) {
        this.enumCausalCambioEstado = enumCausalCambioEstado;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public int getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(int tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

}
