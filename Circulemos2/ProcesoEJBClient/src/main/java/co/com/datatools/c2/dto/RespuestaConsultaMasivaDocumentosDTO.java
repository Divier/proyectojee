package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores de respuesta tras consultar masivamente documentos
 * 
 * @author divier.casas
 * 
 */
public class RespuestaConsultaMasivaDocumentosDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ProcesoDTO procesoDTO;
    private TrazabilidadProcesoDTO trazaDTO;
    private DocumentoProcesoDTO documentoDTO;
    private ParticipanteProcesoDTO partProcesoDTO;
    private DireccionPersonaDTO direccionPersonaDTO;

    public ProcesoDTO getProcesoDTO() {
        return procesoDTO;
    }

    public void setProcesoDTO(ProcesoDTO procesoDTO) {
        this.procesoDTO = procesoDTO;
    }

    public TrazabilidadProcesoDTO getTrazaDTO() {
        return trazaDTO;
    }

    public void setTrazaDTO(TrazabilidadProcesoDTO trazaDTO) {
        this.trazaDTO = trazaDTO;
    }

    public DocumentoProcesoDTO getDocumentoDTO() {
        return documentoDTO;
    }

    public void setDocumentoDTO(DocumentoProcesoDTO documentoDTO) {
        this.documentoDTO = documentoDTO;
    }

    public ParticipanteProcesoDTO getPartProcesoDTO() {
        return partProcesoDTO;
    }

    public void setPartProcesoDTO(ParticipanteProcesoDTO partProcesoDTO) {
        this.partProcesoDTO = partProcesoDTO;
    }

    public DireccionPersonaDTO getDireccionPersonaDTO() {
        return direccionPersonaDTO;
    }

    public void setDireccionPersonaDTO(DireccionPersonaDTO direccionPersonaDTO) {
        this.direccionPersonaDTO = direccionPersonaDTO;
    }
}