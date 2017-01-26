/**
 * 
 */
package co.com.datatools.c2.managed_bean.financiacion.configuracion;

import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;

/**
 * @author luis.forero
 * 
 */
public class DetalleCantidadCuotaVO extends DetalleCantidadCuotaDTO {

    private static final long serialVersionUID = 1L;

    private int index;

    public DetalleCantidadCuotaVO() {
        super();
    }

    /**
     * @param id
     */
    public DetalleCantidadCuotaVO(Integer id) {
        super(id);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
