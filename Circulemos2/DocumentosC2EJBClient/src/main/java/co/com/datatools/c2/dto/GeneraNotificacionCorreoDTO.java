package co.com.datatools.c2.dto;

import java.util.Date;

/**
 * 
 * @author diego.fonseca DTO con la información necesaria pera generar documentos de notificación por correo certificado
 */
public class GeneraNotificacionCorreoDTO extends GeneraDocumentoDTO {
    Date fecha;
    String numeroComparendo;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    @Override
    public String getUbicacion() {
        return String.format(super.getTipoDocumentoGenerado().getUbicacion(),
                String.valueOf(this.getNumeroComparendo()));
    }

}
