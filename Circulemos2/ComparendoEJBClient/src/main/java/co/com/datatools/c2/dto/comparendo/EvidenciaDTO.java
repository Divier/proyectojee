package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.dto.TipoEvidenciaDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Tiene la logica para el registro de documentos no regenerar
 * 
 * @author julio.pinzon (2015-10-21)
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:02:14 COT 2015
 */
public class EvidenciaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Date fechaEvidencia;
    private String idDocumento;
    private ComparendoDTO comparendo;
    private TipoEvidenciaDTO tipoEvidencia;
    private String url;
    private String nombreEvidencia;

    /**
     * Archivo transportable para los datos del archivo adjuntado
     */
    private ArchivoTransportableDTO archivoTransportable;

    // --- Constructor
    public EvidenciaDTO() {
    }

    public EvidenciaDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public TipoEvidenciaDTO getTipoEvidencia() {
        return this.tipoEvidencia;
    }

    public void setTipoEvidencia(TipoEvidenciaDTO tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public ArchivoTransportableDTO getArchivoTransportable() {
        return archivoTransportable;
    }

    public void setArchivoTransportable(ArchivoTransportableDTO archivoTransportable) {
        this.archivoTransportable = archivoTransportable;
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
