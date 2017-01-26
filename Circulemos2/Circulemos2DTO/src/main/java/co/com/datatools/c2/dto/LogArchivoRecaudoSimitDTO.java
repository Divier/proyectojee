package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class LogArchivoRecaudoSimitDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer totalRegistrosExitosos;
    private ArchivoPlanoDTO archivoPlano;
    private TipoRespuestaProcesamientoDTO tipoRespuestaProcesamiento;

    // --- Constructor
    public LogArchivoRecaudoSimitDTO() {
    }

    public LogArchivoRecaudoSimitDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalRegistrosExitosos() {
        return this.totalRegistrosExitosos;
    }

    public void setTotalRegistrosExitosos(Integer totalRegistrosExitosos) {
        this.totalRegistrosExitosos = totalRegistrosExitosos;
    }

    public ArchivoPlanoDTO getArchivoPlano() {
        return this.archivoPlano;
    }

    public void setArchivoPlano(ArchivoPlanoDTO archivoPlano) {
        this.archivoPlano = archivoPlano;
    }

    public TipoRespuestaProcesamientoDTO getTipoRespuestaProcesamiento() {
        return this.tipoRespuestaProcesamiento;
    }

    public void setTipoRespuestaProcesamiento(TipoRespuestaProcesamientoDTO tipoRespuestaProcesamiento) {
        this.tipoRespuestaProcesamiento = tipoRespuestaProcesamiento;
    }

}