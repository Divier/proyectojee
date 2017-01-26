package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de registro de impulso HU_CIR20_DAT_JUR_012
 * 
 * @author dixon.alvarez 2016-07-05
 * 
 */
public class RegistrarImpulsoPruebaFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "registrarImpulsoPruebaFL";

    private ImpulsoPruebaDTO impulsoPruebaDTO;
    private boolean confirRegistrarImpulso;

    public RegistrarImpulsoPruebaFL() {
        impulsoPruebaDTO = new ImpulsoPruebaDTO();
        impulsoPruebaDTO.setDefinitivo(false);
    }

    public ImpulsoPruebaDTO getImpulsoPruebaDTO() {
        return impulsoPruebaDTO;
    }

    public void setImpulsoPruebaDTO(ImpulsoPruebaDTO impulsoPruebaDTO) {
        this.impulsoPruebaDTO = impulsoPruebaDTO;
    }

    public boolean isConfirRegistrarImpulso() {
        return confirRegistrarImpulso;
    }

    public void setConfirRegistrarImpulso(boolean confirRegistrarImpulso) {
        this.confirRegistrarImpulso = confirRegistrarImpulso;
    }
}
