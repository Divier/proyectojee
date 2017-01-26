package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class NombreViaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigo;
    private String nombre;
    private MunicipioDTO municipio;
    private TipoViaDTO tipoVia;

    // --- Constructor
    public NombreViaDTO() {
    }

    public NombreViaDTO(Integer codigo) {
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

    public MunicipioDTO getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public TipoViaDTO getTipoVia() {
        return this.tipoVia;
    }

    public void setTipoVia(TipoViaDTO tipoVia) {
        this.tipoVia = tipoVia;
    }

}