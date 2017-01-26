package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionProvisionalDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoViaDTO tipoViaPrincipal;
    private Integer numeroViaPrincipal;
    private NombreViaDTO nombreViaPrincipal;
    private Character letraViaPrincipal;
    private String bisViaPrincipal;
    private Character letraBisViaPrincipal;
    private CardinalidadDireccionDTO cardinalidadViaPrincipal;
    private TipoViaDTO tipoViaSecundaria;
    private Integer numeroViaSecundaria;
    private NombreViaDTO nombreViaSecundaria;
    private Character letraViaSecundaria;
    private String bisViaSecundaria;
    private Character letraBisViaSecundaria;
    private CardinalidadDireccionDTO cardinalidadViaSecundaria;
    private Integer numeroPlacaDomiciliaria;
    private String complemento;
    private String barrio;
    private MunicipioDTO municipio;
    private LocalidadDTO localidad;
    private TipoUbicabilidadDTO tipoUbicabilidad;

    // --- Constructor
    public DireccionProvisionalDTO() {
    }

    public DireccionProvisionalDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoViaDTO getTipoViaPrincipal() {
        return this.tipoViaPrincipal;
    }

    public void setTipoViaPrincipal(TipoViaDTO tipoViaPrincipal) {
        this.tipoViaPrincipal = tipoViaPrincipal;
    }

    public Integer getNumeroViaPrincipal() {
        return this.numeroViaPrincipal;
    }

    public void setNumeroViaPrincipal(Integer numeroViaPrincipal) {
        this.numeroViaPrincipal = numeroViaPrincipal;
    }

    public NombreViaDTO getNombreViaPrincipal() {
        return this.nombreViaPrincipal;
    }

    public void setNombreViaPrincipal(NombreViaDTO nombreViaPrincipal) {
        this.nombreViaPrincipal = nombreViaPrincipal;
    }

    public Character getLetraViaPrincipal() {
        return this.letraViaPrincipal;
    }

    public void setLetraViaPrincipal(Character letraViaPrincipal) {
        this.letraViaPrincipal = letraViaPrincipal;
    }

    public String getBisViaPrincipal() {
        return this.bisViaPrincipal;
    }

    public void setBisViaPrincipal(String bisViaPrincipal) {
        this.bisViaPrincipal = bisViaPrincipal;
    }

    public Character getLetraBisViaPrincipal() {
        return this.letraBisViaPrincipal;
    }

    public void setLetraBisViaPrincipal(Character letraBisViaPrincipal) {
        this.letraBisViaPrincipal = letraBisViaPrincipal;
    }

    public CardinalidadDireccionDTO getCardinalidadViaPrincipal() {
        return this.cardinalidadViaPrincipal;
    }

    public void setCardinalidadViaPrincipal(CardinalidadDireccionDTO cardinalidadViaPrincipal) {
        this.cardinalidadViaPrincipal = cardinalidadViaPrincipal;
    }

    public TipoViaDTO getTipoViaSecundaria() {
        return this.tipoViaSecundaria;
    }

    public void setTipoViaSecundaria(TipoViaDTO tipoViaSecundaria) {
        this.tipoViaSecundaria = tipoViaSecundaria;
    }

    public Integer getNumeroViaSecundaria() {
        return this.numeroViaSecundaria;
    }

    public void setNumeroViaSecundaria(Integer numeroViaSecundaria) {
        this.numeroViaSecundaria = numeroViaSecundaria;
    }

    public NombreViaDTO getNombreViaSecundaria() {
        return this.nombreViaSecundaria;
    }

    public void setNombreViaSecundaria(NombreViaDTO nombreViaSecundaria) {
        this.nombreViaSecundaria = nombreViaSecundaria;
    }

    public Character getLetraViaSecundaria() {
        return this.letraViaSecundaria;
    }

    public void setLetraViaSecundaria(Character letraViaSecundaria) {
        this.letraViaSecundaria = letraViaSecundaria;
    }

    public String getBisViaSecundaria() {
        return this.bisViaSecundaria;
    }

    public void setBisViaSecundaria(String bisViaSecundaria) {
        this.bisViaSecundaria = bisViaSecundaria;
    }

    public Character getLetraBisViaSecundaria() {
        return this.letraBisViaSecundaria;
    }

    public void setLetraBisViaSecundaria(Character letraBisViaSecundaria) {
        this.letraBisViaSecundaria = letraBisViaSecundaria;
    }

    public CardinalidadDireccionDTO getCardinalidadViaSecundaria() {
        return this.cardinalidadViaSecundaria;
    }

    public void setCardinalidadViaSecundaria(CardinalidadDireccionDTO cardinalidadViaSecundaria) {
        this.cardinalidadViaSecundaria = cardinalidadViaSecundaria;
    }

    public Integer getNumeroPlacaDomiciliaria() {
        return this.numeroPlacaDomiciliaria;
    }

    public void setNumeroPlacaDomiciliaria(Integer numeroPlacaDomiciliaria) {
        this.numeroPlacaDomiciliaria = numeroPlacaDomiciliaria;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBarrio() {
        return this.barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public MunicipioDTO getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public LocalidadDTO getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }

    public TipoUbicabilidadDTO getTipoUbicabilidad() {
        return this.tipoUbicabilidad;
    }

    public void setTipoUbicabilidad(TipoUbicabilidadDTO tipoUbicabilidad) {
        this.tipoUbicabilidad = tipoUbicabilidad;
    }

}