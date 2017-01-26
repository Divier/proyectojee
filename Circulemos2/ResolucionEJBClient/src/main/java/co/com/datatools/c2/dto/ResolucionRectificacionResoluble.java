/**
 * 
 */
package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.negocio.interfaces.Resoluble;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * @author luis.forero
 * 
 */
public class ResolucionRectificacionResoluble implements Resoluble {

    private GeneraDocumentoDTO generaDocumentoDTO;

    private Integer codigoOrganismoTransito;

    public ResolucionRectificacionResoluble() {
    }

    public ResolucionRectificacionResoluble(GeneraDocumentoDTO generaDocumentoDTO, Integer codigoOrganismoTransito) {
        this.generaDocumentoDTO = generaDocumentoDTO;
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    private static final long serialVersionUID = 1L;

    private String numeroConsecutivo = null;

    @Override
    public String getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    @Override
    public void setNumeroConsecutivo(String numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

    @Override
    public Integer getIdTipoResolucion() {
        return EnumTipoResolucion.RESOLUCION_RECTIFICACION.getValue();
    }

    @Override
    public GeneraDocumentoDTO getGeneraDocumentoDTO() {
        return generaDocumentoDTO;
    }

    @Override
    public EnumConsecutivo getEnumConsecutivo() {
        return EnumConsecutivo.RESOL_RECTIFICA;
    }

    @Override
    public int getCodigoOrganismo() {
        return codigoOrganismoTransito;
    }

}
