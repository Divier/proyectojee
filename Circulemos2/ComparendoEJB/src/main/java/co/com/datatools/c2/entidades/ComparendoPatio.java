package co.com.datatools.c2.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "comparendo_patio")
@NamedQuery(name = "ComparendoPatio.findAll", query = "SELECT c FROM ComparendoPatio c")
public class ComparendoPatio implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_comparendo_patio")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "id_comparendo_patio")
    private Comparendo comparendo;

    @Column(name = "consecutivo_asignado_grua")
    private String consecutivoAsignadoGrua;

    private String nombre;

    @Column(name = "numero_grua")
    private Integer numeroGrua;

    @Column(name = "numero_informe")
    private String numeroInforme;

    @Column(name = "numero_patio")
    private Integer numeroPatio;

    @Column(name = "placa_grua")
    private String placaGrua;

    @Column(name = "id_patio")
    private Integer idPatio;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direccion direccion;

    public ComparendoPatio() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivoAsignadoGrua() {
        return this.consecutivoAsignadoGrua;
    }

    public void setConsecutivoAsignadoGrua(String consecutivoAsignadoGrua) {
        this.consecutivoAsignadoGrua = consecutivoAsignadoGrua;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroGrua() {
        return this.numeroGrua;
    }

    public void setNumeroGrua(Integer numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getNumeroInforme() {
        return this.numeroInforme;
    }

    public void setNumeroInforme(String numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    public Integer getNumeroPatio() {
        return this.numeroPatio;
    }

    public void setNumeroPatio(Integer numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public String getPlacaGrua() {
        return this.placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public Integer getIdPatio() {
        return this.idPatio;
    }

    public void setIdPatio(Integer idPatio) {
        this.idPatio = idPatio;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}