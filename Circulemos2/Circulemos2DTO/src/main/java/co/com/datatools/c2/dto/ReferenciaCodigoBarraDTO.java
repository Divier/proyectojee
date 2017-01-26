package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class ReferenciaCodigoBarraDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer idReferencia;
    private Integer codigo;
    private Date fechaVigenciaFinal;
    private Date fechaVigenciaInicial;
    private String referencia;
    private EntidadRecaudadoraDTO entidadRecaudadoraDTO;

    // Constructors Declaration

    public ReferenciaCodigoBarraDTO() {

    }

    // Start Methods Declaration

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVigenciaFinal() {
        return fechaVigenciaFinal;
    }

    public void setFechaVigenciaFinal(Date fechaVigenciaFinal) {
        this.fechaVigenciaFinal = fechaVigenciaFinal;
    }

    public Date getFechaVigenciaInicial() {
        return fechaVigenciaInicial;
    }

    public void setFechaVigenciaInicial(Date fechaVigenciaInicial) {
        this.fechaVigenciaInicial = fechaVigenciaInicial;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public EntidadRecaudadoraDTO getEntidadRecaudadoraDTO() {
        return entidadRecaudadoraDTO;
    }

    public void setEntidadRecaudadoraDTO(EntidadRecaudadoraDTO entidadRecaudadoraDTO) {
        this.entidadRecaudadoraDTO = entidadRecaudadoraDTO;
    }

    // Finish the class
}