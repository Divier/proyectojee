package co.com.datatools.c2.dto.reglas;

public class TipoViaSecundariaIgualDTO {

    private Integer codigoTipoViaPrincipal;
    private String codigoMunicipio;
    private Integer tipoViaSecundariaIgual;

    public TipoViaSecundariaIgualDTO() {
        super();
    }

    public Integer getCodigoTipoViaPrincipal() {
        return codigoTipoViaPrincipal;
    }

    public void setCodigoTipoViaPrincipal(Integer codigoTipoViaPrincipal) {
        this.codigoTipoViaPrincipal = codigoTipoViaPrincipal;
    }

    public Integer getTipoViaSecundariaIgual() {
        return tipoViaSecundariaIgual;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setTipoViaSecundariaIgual(Integer tipoViaSecundariaIgual) {
        this.tipoViaSecundariaIgual = tipoViaSecundariaIgual;
    }

}
