package co.com.datatools.c2.negocio.interfaces;

import java.io.Serializable;

import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * Interface de elementos que se generan como una resolucion
 * 
 * @author julio.pinzon
 * 
 */
public interface Resoluble extends Serializable {

    /**
     * Retorna el Codigo de organismo
     * 
     * @return
     * @author julio.pinzon
     */
    public int getCodigoOrganismo();

    /**
     * Retorna la Enumeracion del consecutivo
     * 
     * @return
     * @author julio.pinzon
     */
    public EnumConsecutivo getEnumConsecutivo();

    /**
     * Retorna el Numero del consecutivo
     * 
     * @return
     * @author julio.pinzon
     */
    public String getNumeroConsecutivo();

    /**
     * Retorna el id del tipo de resolucion
     * 
     * @return
     * @author julio.pinzon
     */
    public Integer getIdTipoResolucion();

    /**
     * Retorna los datos del documento a generar
     * 
     * @return
     * @author julio.pinzon
     */
    public GeneraDocumentoDTO getGeneraDocumentoDTO();

    /**
     * cambia el valor del numero de consecutivo
     * 
     * @param numeroConsecutivo
     * @author julio.pinzon
     */
    public void setNumeroConsecutivo(String numeroConsecutivo);
}
