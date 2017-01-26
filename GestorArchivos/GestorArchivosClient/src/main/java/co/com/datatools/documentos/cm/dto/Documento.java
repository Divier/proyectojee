package co.com.datatools.documentos.cm.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO que permite realizar la creación, modificación y consulta de un documento del repositorio
 * 
 * @author sergio.torres
 * 
 */
public class Documento implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String idDocumento;
    private byte[] contenido;
    private Date fecha;
    private String carpeta;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String version;
    /**
     * @author diego.fonseca
     */
    private String nombreReal;

    public Documento() {
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @author diego.fonseca
     */
    public String getNombreReal() {
        return nombreReal;
    }

    /**
     * 
     * @author diego.fonseca
     */
    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

}
