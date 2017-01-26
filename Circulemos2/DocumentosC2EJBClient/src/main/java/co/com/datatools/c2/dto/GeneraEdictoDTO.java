package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.Utilidades;

/**
 * DTO con los atributos o campos necesarios para generar un documento de Notificacion por Aviso de Comparendos.
 * 
 * @author luis.forero(2016-02-12)
 * 
 */
public class GeneraEdictoDTO extends GeneraDocumentoDTO {

    private static final long serialVersionUID = 1L;

    /**
     * Id de la notificacion aviso generada.
     */
    private Long idAviso;

    public GeneraEdictoDTO() {
        super();
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    @Override
    public String getUbicacion() {
        return String.format(super.getTipoDocumentoGenerado().getUbicacion(),
                String.valueOf(super.getCodigoOrganismo()),
                Utilidades.dateToStringFormatApp(super.getFechaGeneracion(), false));
    }

}
