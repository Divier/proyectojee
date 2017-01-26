package co.com.datatools.c2.entidades.cargue;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the cargue_archivo database table.
 * 
 */
@Entity
@Table(name = "documento_resultado_cargue")
@NamedQuery(name = "DocumentoResultadoCargue.findAll", query = "SELECT d FROM DocumentoResultadoCargue d")
public class DocumentoResultadoCargue implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_resultado_cargue")
    private Long id;

    @Column(name = "id_documento_resultado")
    private Long idDocumentoResultado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento_resul_cargue")
    private TipoDocumentoResultadoCargue tipoDocumentoResultadoCargue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargue_archivo")
    private CargueArchivo cargueArchivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDocumentoResultado() {
        return idDocumentoResultado;
    }

    public void setIdDocumentoResultado(Long idDocumentoResultado) {
        this.idDocumentoResultado = idDocumentoResultado;
    }

    public TipoDocumentoResultadoCargue getTipoDocumentoResultadoCargue() {
        return tipoDocumentoResultadoCargue;
    }

    public void setTipoDocumentoResultadoCargue(TipoDocumentoResultadoCargue tipoDocumentoResultadoCargue) {
        this.tipoDocumentoResultadoCargue = tipoDocumentoResultadoCargue;
    }

    public CargueArchivo getCargueArchivo() {
        return cargueArchivo;
    }

    public void setCargueArchivo(CargueArchivo cargueArchivo) {
        this.cargueArchivo = cargueArchivo;
    }

}