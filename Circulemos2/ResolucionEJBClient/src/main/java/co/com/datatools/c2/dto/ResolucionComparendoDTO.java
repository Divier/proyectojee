package co.com.datatools.c2.dto;

import co.com.datatools.c2.negocio.interfaces.Resoluble;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * Contiene los datos adicionales del documento para generar una resolución.
 * 
 * @author julio.pinzon
 */
public class ResolucionComparendoDTO extends GeneraResolucionComparendoDTO implements Resoluble {

    private static final long serialVersionUID = 1L;

    /**
     * Tipo de consecutivo que se debe utilizar
     */
    private EnumConsecutivo enumConsecutivo;

    /**
     * Id del tipo de resolucion
     */
    private Integer idTipoResolucion;

    public EnumConsecutivo getEnumConsecutivo() {
        return enumConsecutivo;
    }

    public void setEnumConsecutivo(EnumConsecutivo enumConsecutivo) {
        this.enumConsecutivo = enumConsecutivo;
    }

    public Integer getIdTipoResolucion() {
        return idTipoResolucion;
    }

    public void setIdTipoResolucion(Integer idTipoResolucion) {
        this.idTipoResolucion = idTipoResolucion;
    }

    public GeneraDocumentoDTO getGeneraDocumentoDTO() {
        return this;
    }

}
