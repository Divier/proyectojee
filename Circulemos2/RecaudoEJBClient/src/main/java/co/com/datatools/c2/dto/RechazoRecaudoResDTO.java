package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

public class RechazoRecaudoResDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int organismoTransito;
    private String numeroRecaudo;
    private Date fecha;
    private String nombreUsuario;
    private int estadoLectura;
    private String rechazos;

    public int getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(int organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getNumeroRecaudo() {
        return numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setEstadoLectura(int estadoDeLectura) {
        this.estadoLectura = estadoDeLectura;
    }

    public String getRechazos() {
        return rechazos;
    }

    public void setRechazos(String rechazos) {
        this.rechazos = rechazos;
    }

    public int getEstadoLectura() {
        return estadoLectura;
    }

}
