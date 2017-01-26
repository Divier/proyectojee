package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;

/**
 * Dto que encapsula los datos de un parametro de seguridad: ParametroSeguridad
 * 
 * @author claudia.rodriguez
 * 
 */
public class ParametroSeguridadDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String descripcion;
    private String valor;
    private Integer idTipoParametro;
    private String nombreTipoParametro;
    private boolean editable;

    public ParametroSeguridadDto() {

    }

    public ParametroSeguridadDto(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getIdTipoParametro() {
        return idTipoParametro;
    }

    public void setIdTipoParametro(Integer idTipoParametro) {
        this.idTipoParametro = idTipoParametro;
    }

    public String getNombreTipoParametro() {
        return nombreTipoParametro;
    }

    public void setNombreTipoParametro(String nombreTipoParametro) {
        this.nombreTipoParametro = nombreTipoParametro;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
