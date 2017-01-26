package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class ParametroDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private String valorDefecto;
    private Integer codigoUnidad;
    private String formato;
    private Boolean editableOrganismo;
    private TipoVariableDTO tipoVariable;
    private ModuloDTO modulo;

    // --- Constructor
    public ParametroDTO() {
    }

    public ParametroDTO(Integer codigo) {
        this.codigo = codigo;
    }

    // --- Getters-Setters
    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValorDefecto() {
        return this.valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public Integer getCodigoUnidad() {
        return this.codigoUnidad;
    }

    public void setCodigoUnidad(Integer codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getFormato() {
        return this.formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Boolean getEditableOrganismo() {
        return this.editableOrganismo;
    }

    public void setEditableOrganismo(Boolean editableOrganismo) {
        this.editableOrganismo = editableOrganismo;
    }

    public TipoVariableDTO getTipoVariable() {
        return this.tipoVariable;
    }

    public void setTipoVariable(TipoVariableDTO tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public ModuloDTO getModulo() {
        return modulo;
    }

    public void setModulo(ModuloDTO modulo) {
        this.modulo = modulo;
    }

}