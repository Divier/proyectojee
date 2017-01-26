package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

public class RegistrarPruebaDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idProceso;
    private SolicitudPruebasBackDTO solicitud;
    private List<PruebaDTO> pruebas;
    private List<ImpulsoPruebaDTO> impulsos;
    private byte[] documentoGenerado;

    public RegistrarPruebaDTO() {
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public SolicitudPruebasBackDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudPruebasBackDTO solicitud) {
        this.solicitud = solicitud;
    }

    public List<PruebaDTO> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<PruebaDTO> pruebas) {
        this.pruebas = pruebas;
    }

    public List<ImpulsoPruebaDTO> getImpulsos() {
        return impulsos;
    }

    public void setImpulsos(List<ImpulsoPruebaDTO> impulsos) {
        this.impulsos = impulsos;
    }

    public byte[] getDocumentoGenerado() {
        return documentoGenerado;
    }

    public void setDocumentoGenerado(byte[] documentoGenerado) {
        this.documentoGenerado = documentoGenerado;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

}
