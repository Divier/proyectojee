package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.negocio.error.EnumProcesamiento;

/**
 * 
 * @author giovanni.velandia
 * 
 */
public class ProcesarComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private ProcesaComparendoDTO procesaComparendoDTO;
    private EnumProcesamiento enumProcesamiento;

    public ProcesaComparendoDTO getProcesaComparendoDTO() {
        return procesaComparendoDTO;
    }

    public void setProcesaComparendoDTO(ProcesaComparendoDTO procesaComparendoDTO) {
        this.procesaComparendoDTO = procesaComparendoDTO;
    }

    public EnumProcesamiento getEnumProcesamiento() {
        return enumProcesamiento;
    }

    public void setEnumProcesamiento(EnumProcesamiento enumProcesamiento) {
        this.enumProcesamiento = enumProcesamiento;
    }

}
