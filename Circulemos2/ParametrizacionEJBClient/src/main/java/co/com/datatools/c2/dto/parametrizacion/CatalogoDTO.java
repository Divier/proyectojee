package co.com.datatools.c2.dto.parametrizacion;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Entidad que permite mantener la referencia de las tablas que representan catalogos en el sistema
 * 
 * @author edier.sua
 */
public class CatalogoDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    // Atributos
    private Integer id;
    private String nombre;
    private String codigo;
    private String sigla;
    private Boolean editable;
    private String descripcion;
    private String nombreEntidad;
    private String paqueteEntidad;

    private CatalogoDTO catalogoDependenciaDTO;
    private Boolean idCatalogoDependencia;

    // Variable de negocio
    private boolean esDependeciaFinal;
    private boolean esDependeciaInicial;
    private boolean esDependenciaNormal;

    // Accesors
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getPaqueteEntidad() {
        return paqueteEntidad;
    }

    public void setPaqueteEntidad(String paqueteEntidad) {
        this.paqueteEntidad = paqueteEntidad;
    }

    public Boolean getIdCatalogoDependencia() {
        return idCatalogoDependencia;
    }

    public void setIdCatalogoDependencia(Boolean idCatalogoDependencia) {
        this.idCatalogoDependencia = idCatalogoDependencia;
    }

    public CatalogoDTO getCatalogoDependenciaDTO() {
        return catalogoDependenciaDTO;
    }

    public void setCatalogoDependenciaDTO(CatalogoDTO catalogoDependenciaDTO) {
        this.catalogoDependenciaDTO = catalogoDependenciaDTO;
    }

    public boolean isEsDependeciaFinal() {
        return esDependeciaFinal;
    }

    public void setEsDependeciaFinal(boolean esDependeciaFinal) {
        this.esDependeciaFinal = esDependeciaFinal;
    }

    public boolean isEsDependeciaInicial() {
        return esDependeciaInicial;
    }

    public void setEsDependeciaInicial(boolean esDependeciaInicial) {
        this.esDependeciaInicial = esDependeciaInicial;
    }

    public boolean isEsDependenciaNormal() {
        return esDependenciaNormal;
    }

    public void setEsDependenciaNormal(boolean esDependenciaNormal) {
        this.esDependenciaNormal = esDependenciaNormal;
    }
}
