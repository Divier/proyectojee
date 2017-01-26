package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "empresa_transporte")
@NamedQuery(name = "EmpresaTransporte.findAll", query = "SELECT e FROM EmpresaTransporte e")
public class EmpresaTransporte implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa_transporte")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa_transporte")
    private PersonaJuridica personaJuridica;

    @OneToMany(mappedBy = "empresaTransporte")
    private List<Ruta> rutas;

    public EmpresaTransporte() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaJuridica getPersonaJuridica() {
        return this.personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public List<Ruta> getRutas() {
        return this.rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

}