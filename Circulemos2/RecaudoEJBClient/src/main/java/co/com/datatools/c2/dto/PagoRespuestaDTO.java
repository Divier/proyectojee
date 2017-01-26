package co.com.datatools.c2.dto;

import java.util.List;

public class PagoRespuestaDTO extends PagoDTO {
    private String codigoBanco;
    private String codigoTipoCuenta;
    private String horaTransaccion;
    private List<DetallePagoRespuestaDTO> detallePagoRespuestaDTOList;

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public List<DetallePagoRespuestaDTO> getDetallePagoRespuestaDTOList() {

        if (this.detallePagoRespuestaDTOList == null) {
            this.detallePagoRespuestaDTOList = new java.util.ArrayList<>(1);
        }
        return this.detallePagoRespuestaDTOList;
    }

    public void setDetallePagoRespuestaDTOList(List<DetallePagoRespuestaDTO> detallePagoRespuestaDTOList) {
        this.detallePagoRespuestaDTOList = detallePagoRespuestaDTOList;
    }

}
