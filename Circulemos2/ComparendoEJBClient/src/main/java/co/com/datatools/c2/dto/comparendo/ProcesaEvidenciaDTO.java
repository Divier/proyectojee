package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * Tiene la logica para el regitro de documentos no regenerar
 * 
 * @author giovanni.velandia (2015-10-20)
 */
public class ProcesaEvidenciaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private ProcesaComparendoDTO procesaComparendo;
    private Integer codigoTipoEvidencia;
    private Date fechaEvidencia;
    private String idDocumento;
    private String url;
    private String nombreEvidencia;

    /**
     * Archivo transportable para los datos del archivo adjuntado
     */
    private ArchivoTransportableDTO archivoTransportable;

    /**
     * Campo solo informativo para tipo de evidencia
     */
    private String nombreTipoEvidencia;

    // --- Constructor
    public ProcesaEvidenciaDTO() {
    }

    public ProcesaEvidenciaDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcesaComparendoDTO getProcesaComparendo() {
        return this.procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendoDTO procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

    public Integer getCodigoTipoEvidencia() {
        return this.codigoTipoEvidencia;
    }

    public void setCodigoTipoEvidencia(Integer codigoTipoEvidencia) {
        this.codigoTipoEvidencia = codigoTipoEvidencia;
    }

    public Date getFechaEvidencia() {
        return this.fechaEvidencia;
    }

    public void setFechaEvidencia(Date fechaEvidencia) {
        this.fechaEvidencia = fechaEvidencia;
    }

    public String getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public ArchivoTransportableDTO getArchivoTransportable() {
        return archivoTransportable;
    }

    public void setArchivoTransportable(ArchivoTransportableDTO archivoTransportable) {
        this.archivoTransportable = archivoTransportable;
    }

    public String getNombreTipoEvidencia() {
        return nombreTipoEvidencia;
    }

    public void setNombreTipoEvidencia(String nombreTipoEvidencia) {
        this.nombreTipoEvidencia = nombreTipoEvidencia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombreEvidencia() {
        return nombreEvidencia;
    }

    public void setNombreEvidencia(String nombreEvidencia) {
        this.nombreEvidencia = nombreEvidencia;
    }

}
