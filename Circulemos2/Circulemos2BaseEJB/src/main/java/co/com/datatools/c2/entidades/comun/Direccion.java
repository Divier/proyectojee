package co.com.datatools.c2.entidades.comun;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "direccion")
public class Direccion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion")
    private Long id;

    // {-- Via principal

    @JoinColumn(name = "codigo_tipo_via_principal", referencedColumnName = "codigo_tipo_via")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoVia tipoViaPrincipal;

    @Column(name = "numero_via_principal")
    private Integer numeroViaPrincipal;

    @JoinColumn(name = "codigo_nombre_via_principal", referencedColumnName = "codigo_nombre_via")
    @ManyToOne(fetch = FetchType.LAZY)
    private NombreVia nombreViaPrincipal;

    @Column(name = "letra_via_principal")
    private Character letraViaPrincipal;

    @Column(name = "bis_via_principal")
    private String bisViaPrincipal;

    @Column(name = "letra_bis_via_principal")
    private Character letraBisViaPrincipal;

    @JoinColumn(name = "codigo_cardinalidad_via_princ", referencedColumnName = "codigo_cardinalidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private CardinalidadDireccion cardinalidadViaPrincipal;

    // }-- Via Principal

    // {-- Via Secundaria

    @JoinColumn(name = "codigo_tipo_via_secundaria", referencedColumnName = "codigo_tipo_via")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVia tipoViaSecundaria;

    @Column(name = "numero_via_secundaria")
    private Integer numeroViaSecundaria;

    @JoinColumn(name = "codigo_nombre_via_secundaria", referencedColumnName = "codigo_nombre_via")
    @ManyToOne(fetch = FetchType.LAZY)
    private NombreVia nombreViaSecundaria;

    @Column(name = "letra_via_secundaria")
    private Character letraViaSecundaria;

    @Column(name = "bis_via_secundaria")
    private String bisViaSecundaria;

    @Column(name = "letra_bis_via_secundaria")
    private Character letraBisViaSecundaria;

    @JoinColumn(name = "codigo_cardinalidad_via_secun", referencedColumnName = "codigo_cardinalidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private CardinalidadDireccion cardinalidadViaSecundaria;

    // }-- Via Secundaria

    @Column(name = "numero_placa_domiciliaria")
    private Integer numeroPlacaDomiciliaria;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "barrio")
    private String barrio;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais pais;

    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;

    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Localidad localidad;

    @JoinColumn(name = "codigo_tipo_ubicabilidad", referencedColumnName = "codigo_tipo_ubicabilidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoUbicabilidad tipoUbicabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_coordenada")
    private TipoCoordenada tipoCoordenada;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    public Direccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVia getTipoViaPrincipal() {
        return tipoViaPrincipal;
    }

    public void setTipoViaPrincipal(TipoVia tipoViaPrincipal) {
        this.tipoViaPrincipal = tipoViaPrincipal;
    }

    public Integer getNumeroViaPrincipal() {
        return numeroViaPrincipal;
    }

    public void setNumeroViaPrincipal(Integer numeroViaPrincipal) {
        this.numeroViaPrincipal = numeroViaPrincipal;
    }

    public NombreVia getNombreViaPrincipal() {
        return nombreViaPrincipal;
    }

    public void setNombreViaPrincipal(NombreVia nombreViaPrincipal) {
        this.nombreViaPrincipal = nombreViaPrincipal;
    }

    public Character getLetraViaPrincipal() {
        return letraViaPrincipal;
    }

    public void setLetraViaPrincipal(Character letraViaPrincipal) {
        this.letraViaPrincipal = letraViaPrincipal;
    }

    public String getBisViaPrincipal() {
        return bisViaPrincipal;
    }

    public void setBisViaPrincipal(String bisViaPrincipal) {
        this.bisViaPrincipal = bisViaPrincipal;
    }

    public Character getLetraBisViaPrincipal() {
        return letraBisViaPrincipal;
    }

    public void setLetraBisViaPrincipal(Character letraBisViaPrincipal) {
        this.letraBisViaPrincipal = letraBisViaPrincipal;
    }

    public CardinalidadDireccion getCardinalidadViaPrincipal() {
        return cardinalidadViaPrincipal;
    }

    public void setCardinalidadViaPrincipal(CardinalidadDireccion cardinalidadViaPrincipal) {
        this.cardinalidadViaPrincipal = cardinalidadViaPrincipal;
    }

    public TipoVia getTipoViaSecundaria() {
        return tipoViaSecundaria;
    }

    public void setTipoViaSecundaria(TipoVia tipoViaSecundaria) {
        this.tipoViaSecundaria = tipoViaSecundaria;
    }

    public Integer getNumeroViaSecundaria() {
        return numeroViaSecundaria;
    }

    public void setNumeroViaSecundaria(Integer numeroViaSecundaria) {
        this.numeroViaSecundaria = numeroViaSecundaria;
    }

    public NombreVia getNombreViaSecundaria() {
        return nombreViaSecundaria;
    }

    public void setNombreViaSecundaria(NombreVia nombreViaSecundaria) {
        this.nombreViaSecundaria = nombreViaSecundaria;
    }

    public Character getLetraViaSecundaria() {
        return letraViaSecundaria;
    }

    public void setLetraViaSecundaria(Character letraViaSecundaria) {
        this.letraViaSecundaria = letraViaSecundaria;
    }

    public String getBisViaSecundaria() {
        return bisViaSecundaria;
    }

    public void setBisViaSecundaria(String bisViaSecundaria) {
        this.bisViaSecundaria = bisViaSecundaria;
    }

    public Character getLetraBisViaSecundaria() {
        return letraBisViaSecundaria;
    }

    public void setLetraBisViaSecundaria(Character letraBisViaSecundaria) {
        this.letraBisViaSecundaria = letraBisViaSecundaria;
    }

    public CardinalidadDireccion getCardinalidadViaSecundaria() {
        return cardinalidadViaSecundaria;
    }

    public void setCardinalidadViaSecundaria(CardinalidadDireccion cardinalidadViaSecundaria) {
        this.cardinalidadViaSecundaria = cardinalidadViaSecundaria;
    }

    public Integer getNumeroPlacaDomiciliaria() {
        return numeroPlacaDomiciliaria;
    }

    public void setNumeroPlacaDomiciliaria(Integer numeroPlacaDomiciliaria) {
        this.numeroPlacaDomiciliaria = numeroPlacaDomiciliaria;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Pais getPais() {
        return this.pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public TipoUbicabilidad getTipoUbicabilidad() {
        return tipoUbicabilidad;
    }

    public void setTipoUbicabilidad(TipoUbicabilidad tipoUbicabilidad) {
        this.tipoUbicabilidad = tipoUbicabilidad;
    }

    public TipoCoordenada getTipoCoordenada() {
        return tipoCoordenada;
    }

    public void setTipoCoordenada(TipoCoordenada tipoCoordenada) {
        this.tipoCoordenada = tipoCoordenada;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

}