package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "procesa_direccion")
@NamedQueries({ @NamedQuery(name = "ProcesaDireccion.findAll", query = "SELECT p FROM ProcesaDireccion p") })
public class ProcesaDireccion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procesa_direccion")
    private Long id;

    @Column(name = "codigo_tipo_via_principal")
    private Integer codigoTipoViaPrincipal;

    @Column(name = "numero_via_principal")
    private Integer numeroViaPrincipal;

    @Column(name = "codigo_nombre_via_principal")
    private Integer codigoNombreViaPrincipal;

    @Column(name = "letra_via_principal")
    private Character letraViaPrincipal;

    @Column(name = "bis_via_principal")
    private String bisViaPrincipal;

    @Column(name = "letra_bis_via_principal")
    private Character letraBisViaPrincipal;

    @Column(name = "codigo_cardinalidad_via_princ")
    private Integer codigoCardinalidadViaPrincipal;

    @Column(name = "codigo_tipo_via_secundaria")
    private Integer codigoTipoViaSecundaria;

    @Column(name = "numero_via_secundaria")
    private Integer numeroViaSecundaria;

    @Column(name = "codigo_nombre_via_secundaria")
    private Integer codigoNombreViaSecundaria;

    @Column(name = "letra_via_secundaria")
    private Character letraViaSecundaria;

    @Column(name = "bis_via_secundaria")
    private String bisViaSecundaria;

    @Column(name = "letra_bis_via_secundaria")
    private Character letraBisViaSecundaria;

    @Column(name = "codigo_cardinalidad_via_secun")
    private Integer codigoCardinalidadViaSecundario;

    @Column(name = "numero_placa_domiciliaria")
    private Integer numeroPlacaDomiciliaria;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "id_pais")
    private Integer idPais;

    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @Column(name = "id_municipio")
    private Integer idMunicipio;

    @Column(name = "id_localidad")
    private Integer idLocalidad;

    @Column(name = "codigo_tipo_ubicabilidad")
    private Integer codigoTipoUbicabilidad;

    @Column(name = "barrio")
    private String barrio;

    @Column(name = "codigo_tipo_coordenada")
    private Integer codigoTipoCoordenada;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    public ProcesaDireccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoViaPrincipal() {
        return codigoTipoViaPrincipal;
    }

    public void setCodigoTipoViaPrincipal(Integer codigoTipoViaPrincipal) {
        this.codigoTipoViaPrincipal = codigoTipoViaPrincipal;
    }

    public Integer getNumeroViaPrincipal() {
        return numeroViaPrincipal;
    }

    public void setNumeroViaPrincipal(Integer numeroViaPrincipal) {
        this.numeroViaPrincipal = numeroViaPrincipal;
    }

    public Integer getCodigoNombreViaPrincipal() {
        return codigoNombreViaPrincipal;
    }

    public void setCodigoNombreViaPrincipal(Integer codigoNombreViaPrincipal) {
        this.codigoNombreViaPrincipal = codigoNombreViaPrincipal;
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

    public Integer getCodigoCardinalidadViaPrincipal() {
        return codigoCardinalidadViaPrincipal;
    }

    public void setCodigoCardinalidadViaPrincipal(Integer codigoCardinalidadViaPrincipal) {
        this.codigoCardinalidadViaPrincipal = codigoCardinalidadViaPrincipal;
    }

    public Integer getCodigoTipoViaSecundaria() {
        return codigoTipoViaSecundaria;
    }

    public void setCodigoTipoViaSecundaria(Integer codigoTipoViaSecundaria) {
        this.codigoTipoViaSecundaria = codigoTipoViaSecundaria;
    }

    public Integer getNumeroViaSecundaria() {
        return numeroViaSecundaria;
    }

    public void setNumeroViaSecundaria(Integer numeroViaSecundaria) {
        this.numeroViaSecundaria = numeroViaSecundaria;
    }

    public Integer getCodigoNombreViaSecundaria() {
        return codigoNombreViaSecundaria;
    }

    public void setCodigoNombreViaSecundaria(Integer codigoNombreViaSecundaria) {
        this.codigoNombreViaSecundaria = codigoNombreViaSecundaria;
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

    public Integer getCodigoCardinalidadViaSecundario() {
        return codigoCardinalidadViaSecundario;
    }

    public void setCodigoCardinalidadViaSecundario(Integer codigoCardinalidadViaSecundario) {
        this.codigoCardinalidadViaSecundario = codigoCardinalidadViaSecundario;
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

    public Integer getIdPais() {
        return this.idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdDepartamento() {
        return this.idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Integer getCodigoTipoUbicabilidad() {
        return codigoTipoUbicabilidad;
    }

    public void setCodigoTipoUbicabilidad(Integer codigoTipoUbicabilidad) {
        this.codigoTipoUbicabilidad = codigoTipoUbicabilidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Integer getCodigoTipoCoordenada() {
        return codigoTipoCoordenada;
    }

    public void setCodigoTipoCoordenada(Integer codigoTipoCoordenada) {
        this.codigoTipoCoordenada = codigoTipoCoordenada;
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
