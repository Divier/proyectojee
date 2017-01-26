package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * 
 * @author diego.fonseca
 * 
 *         DTO que contiene el identificador del aviso generado, la cantidad de comparendos del mismo y el contenido del documento asociado al aviso
 */
public class DocumentoAvisoGeneradoDTO extends AvisoGeneradoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5768937746910945756L;

    private ArchivoTransportableDTO archivoTransportableDTO;
    private Long idDocumento;

    public ArchivoTransportableDTO getArchivoTransportableDTO() {
        return archivoTransportableDTO;
    }

    public void setArchivoTransportableDTO(ArchivoTransportableDTO archivoTransportableDTO) {
        this.archivoTransportableDTO = archivoTransportableDTO;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

}
