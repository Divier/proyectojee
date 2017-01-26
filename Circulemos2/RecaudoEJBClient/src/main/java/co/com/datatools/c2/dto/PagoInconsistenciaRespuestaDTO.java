package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

public class PagoInconsistenciaRespuestaDTO extends PagoInconsistenciaDTO implements Serializable {

    private String tipoDocumento;
    private String codigoTipoCuenta;
    private String codigoBanco;
    private String horaTransaccion;
    private List<DetallePagoInconsistenciaRespuestaDTO> detallePagoInconsistenciaRespuestaDTOList;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String idTipoCuenta) {
        this.codigoTipoCuenta = idTipoCuenta;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public List<DetallePagoInconsistenciaRespuestaDTO> getDetallePagoInconsistenciaRespuestaDTOList() {
        if (this.detallePagoInconsistenciaRespuestaDTOList == null) {
            this.detallePagoInconsistenciaRespuestaDTOList = new java.util.ArrayList<>(1);
        }
        return this.detallePagoInconsistenciaRespuestaDTOList;
    }

    public void setDetallePagoInconsistenciaRespuestaDTOList(
            List<DetallePagoInconsistenciaRespuestaDTO> detallePagoInconsistenciaRespuestaDTOList) {
        this.detallePagoInconsistenciaRespuestaDTOList = detallePagoInconsistenciaRespuestaDTOList;
    }

}
