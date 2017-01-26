package co.com.datatools.c2.dto;

public class DetallePagoRespuestaDTO extends DetallePagoDTO {
    private String codigoTipoRecaudo;
    private String anioObligacion;
    private String fechaDeCreacionObligacionPagada;

    public String getCodigoTipoRecaudo() {
        return codigoTipoRecaudo;
    }

    public void setCodigoTipoRecaudo(String codigoTipoRecaudo) {
        this.codigoTipoRecaudo = codigoTipoRecaudo;
    }

    public String getAnioObligacion() {
        return anioObligacion;
    }

    public void setAnioObligacion(String anioObligacion) {
        this.anioObligacion = anioObligacion;
    }

    public String getFechaDeCreacionObligacionPagada() {
        return fechaDeCreacionObligacionPagada;
    }

    public void setFechaDeCreacionObligacionPagada(String fechaDeCreacionObligacionPagada) {
        this.fechaDeCreacionObligacionPagada = fechaDeCreacionObligacionPagada;
    }

}
