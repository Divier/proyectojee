package co.com.datatools.c2.dto.cargue;

import java.util.List;
import java.util.Date;
import java.math.*;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 22 17:55:10 COT 2016
 */
public class DocumentoResultadoCargueDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Long idDocumentoResultado;
    private TipoDocumentoResultadoCargueDTO tipoDocumentoResultadoCargue;
    private CargueArchivoDTO cargueArchivo;

    // --- Constructor
    public DocumentoResultadoCargueDTO() {
    }

    public DocumentoResultadoCargueDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDocumentoResultado() {
        return this.idDocumentoResultado;
    }

    public void setIdDocumentoResultado(Long idDocumentoResultado) {
        this.idDocumentoResultado = idDocumentoResultado;
    }

    public TipoDocumentoResultadoCargueDTO getTipoDocumentoResultadoCargue() {
        return this.tipoDocumentoResultadoCargue;
    }

    public void setTipoDocumentoResultadoCargue(TipoDocumentoResultadoCargueDTO tipoDocumentoResultadoCargue) {
        this.tipoDocumentoResultadoCargue = tipoDocumentoResultadoCargue;
    }

    public CargueArchivoDTO getCargueArchivo() {
        return this.cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivoDTO cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

}
