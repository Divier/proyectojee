package co.com.datatools.c2.util;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

/**
 * Metodo generado con logica propia no regenerar automaticamente
 * 
 * @author robert.bautista giovanni.velandia(mod 11-11-2015)
 * @since 2013-11-22
 */
public class ArchivoTransportableDTO implements Serializable {

    /**
     * Contiene el serial UID por defecto
     */
    private static final long serialVersionUID = 1L;

    public static final String SEPARADOR = "/";

    /**
     * Contiene el nombre del archivo
     */
    private String nombre;

    /**
     * Contenido del archivo como un array de bytes
     */
    private byte[] contenido;

    /**
     * Contenido de la ruta del archivo
     */
    private String ruta;

    private String numeroDocumento;

    /**
     * Constructor por defecto, crea un ArchivoTransportableDTO con atributos nulos
     */
    public ArchivoTransportableDTO() {
        super();
        this.nombre = null;
        this.contenido = null;
        this.ruta = null;
    }

    /**
     * Crea un ArchivoTransportableDTO con el nombre y contenido indicados.
     * 
     * @param nombre
     *            nombre del archivo
     * 
     * @param contenido
     *            Contenido del archivo
     */
    public ArchivoTransportableDTO(String nombre, byte[] contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }

    /**
     * Metodo que se encarga de devolver la extension del archivo
     * 
     * @return String valor extension
     * @author giovanni.velandia
     */
    public String getExtension() {
        return FilenameUtils.getExtension(this.nombre);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return this.contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta.replace("\\", SEPARADOR);
    }

    /**
     * Permite agregar a la ruta existente otra porción del path
     * 
     * @param ruta
     *            complemento de la ruta ya existente
     */
    public void appendRuta(String ruta) {
        ruta.replace("\\", SEPARADOR);
        if (!ruta.startsWith(SEPARADOR)) {
            ruta = SEPARADOR + ruta;
        }
        this.ruta += ruta;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}
