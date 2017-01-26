package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

public class RadicarExpedienteDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private List<ParticipanteProcesoDTO> participanteProcesoDTOs;
    private MotivacionImpugnacionDTO motivacionImpugnacionDTO;
    private Long cicomparendo;
    private byte[] documento;
    private String numeroFirma;
    private AdjuntosMotivacionImpugnacionDTO adjunto;

    /**
     * Listado de participantes seleccionados
     */
    private List<ParticipanteProcesoDTO> participantesSeleccionados;

    public List<ParticipanteProcesoDTO> getParticipanteProcesoDTOs() {
        return participanteProcesoDTOs;
    }

    public void setParticipanteProcesoDTOs(List<ParticipanteProcesoDTO> participanteProcesoDTOs) {
        this.participanteProcesoDTOs = participanteProcesoDTOs;
    }

    public MotivacionImpugnacionDTO getMotivacionImpugnacionDTO() {
        return motivacionImpugnacionDTO;
    }

    public void setMotivacionImpugnacionDTO(MotivacionImpugnacionDTO motivacionImpugnacionDTO) {
        this.motivacionImpugnacionDTO = motivacionImpugnacionDTO;
    }

    public String getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(String numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public List<ParticipanteProcesoDTO> getParticipantesSeleccionados() {
        return participantesSeleccionados;
    }

    public void setParticipantesSeleccionados(List<ParticipanteProcesoDTO> participantesSeleccionados) {
        this.participantesSeleccionados = participantesSeleccionados;
    }

    public AdjuntosMotivacionImpugnacionDTO getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(AdjuntosMotivacionImpugnacionDTO adjunto) {
        this.adjunto = adjunto;
    }
}