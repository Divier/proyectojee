package co.com.datatools.c2.entidades.personas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.Municipio;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "persona_juridica")
public class PersonaJuridica implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_persona_juridica")
    private Long idPersonaJuridica;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "id_persona_juridica")
    private Persona persona;

    @Column(name = "digito_verificacion")
    private Short digitoVerificacion;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column(name = "sigla")
    private String sigla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_sociedad")
    private TipoSociedad tipoSociedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase_actividad_economica")
    private ClaseActividadEconomica claseActividadEconomica;

    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;

    @OneToMany(
            mappedBy = "personaJuridica",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<RepresentanteLegal> representanteLegalList;

    @Column(name = "codigo_empresa")
    private String codigoEmpresa;

    public PersonaJuridica() {

    }

    public Long getIdPersonaJuridica() {
        return idPersonaJuridica;
    }

    public void setIdPersonaJuridica(Long idPersonaJuridica) {
        this.idPersonaJuridica = idPersonaJuridica;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Short getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Short digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoSociedad getTipoSociedad() {
        return tipoSociedad;
    }

    public void setTipoSociedad(TipoSociedad tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public ClaseActividadEconomica getClaseActividadEconomica() {
        return claseActividadEconomica;
    }

    public void setClaseActividadEconomica(ClaseActividadEconomica claseActividadEconomica) {
        this.claseActividadEconomica = claseActividadEconomica;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<RepresentanteLegal> getRepresentanteLegalList() {
        return representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegal> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

}