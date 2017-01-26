package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

public class RespuestaFinanciacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    /**
     * Indica el codigo de respuesta a la validacion de una financiacion
     */
    private Integer codigoError;

    /**
     * Contiene una lista de errores correspondientes a las validaciones de una financiacion
     */
    private List<ErrorFinanciacionDTO> errores;

    /**
     * Contiene la informacion de la financiacion que se valido, incluyendo su id en caso de que haya pasado todas las validaciones
     */
    private FinanciacionDTO financiacion;

    private Long idDocumento;

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public List<ErrorFinanciacionDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorFinanciacionDTO> errores) {
        this.errores = errores;
    }

    public FinanciacionDTO getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(FinanciacionDTO financiacion) {
        this.financiacion = financiacion;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

}
