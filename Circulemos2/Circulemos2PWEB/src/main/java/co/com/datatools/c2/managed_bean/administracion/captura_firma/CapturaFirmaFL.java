package co.com.datatools.c2.managed_bean.administracion.captura_firma;

import java.io.Serializable;

import co.com.datatools.c2.dto.CapturaFirmaDTO;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class CapturaFirmaFL implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "capturaFirmaFL";

    private boolean capturarFirmaDigital;
    private CapturaFirmaDTO capturaFirmaDTO;

    public CapturaFirmaFL() {
        capturaFirmaDTO = new CapturaFirmaDTO();
    }

    public boolean isCapturarFirmaDigital() {
        return capturarFirmaDigital;
    }

    public void setCapturarFirmaDigital(boolean capturarFirmaDigital) {
        this.capturarFirmaDigital = capturarFirmaDigital;
    }

    public CapturaFirmaDTO getCapturaFirmaDTO() {
        return capturaFirmaDTO;
    }

    public void setCapturaFirmaDTO(CapturaFirmaDTO capturaFirmaDTO) {
        this.capturaFirmaDTO = capturaFirmaDTO;
    }

}
