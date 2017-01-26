/**
 * 
 */
package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo;

import java.io.Serializable;
import java.util.Random;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Utilizada para registros en tablas que pueden no tener un rowKey
 * 
 * @author julio.pinzon 2016-07-08
 *
 */
public class RegistroTablaVO<T extends EntidadDtoC2> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RegistroTablaVO(T dto) {
        super();
        this.dto = dto;
    }

    private Long rowKey;

    private T dto;

    private Random rnd = new Random();

    public Long getRowKey() {
        if (rowKey == null) {
            rowKey = rnd.nextLong();
        }
        return rowKey;
    }

    public void setRowKey(Long rowKey) {
        this.rowKey = rowKey;
    }

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }

}
