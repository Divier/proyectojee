package co.com.datatools.c2.dto.personas;

import java.util.List;

import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Se crea herencia de PersonaDTO para mantener la informacion relativa a la entidad persona
 * 
 * @author felipe.martinez
 * @version 3.0 - Tue May 27 18:03:44 COT 2014 - editado
 */
public class PersonaJuridicaDTO extends PersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Short digitoVerificacion;
    private String nombreComercial;
    private String sigla;
    private TipoSociedadDTO tipoSociedad;
    private ClaseActividadEconomicaDTO claseActividadEconomica;
    private MunicipioDTO municipio;
    private List<RepresentanteLegalDTO> representanteLegalList;
    private String codigoEmpresa;

    // --- Constructor
    public PersonaJuridicaDTO() {
    }

    public PersonaJuridicaDTO(Long id) {
        super(id);
    }

    // --- Getters-Setters

    public Short getDigitoVerificacion() {
        return this.digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return this.nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoSociedadDTO getTipoSociedad() {
        return this.tipoSociedad;
    }

    public void setTipoSociedad(TipoSociedadDTO tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public ClaseActividadEconomicaDTO getClaseActividadEconomica() {
        return this.claseActividadEconomica;
    }

    public void setClaseActividadEconomica(ClaseActividadEconomicaDTO claseActividadEconomica) {
        this.claseActividadEconomica = claseActividadEconomica;
    }

    public MunicipioDTO getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RepresentanteLegalDTO> getRepresentanteLegalList() {
        if (this.representanteLegalList == null) {
            this.representanteLegalList = new java.util.ArrayList<>(1);
        }
        return this.representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegalDTO> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getId().intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonaJuridicaDTO other = (PersonaJuridicaDTO) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

}