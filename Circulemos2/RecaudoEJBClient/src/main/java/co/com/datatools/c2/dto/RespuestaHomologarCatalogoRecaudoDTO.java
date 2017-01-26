package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.enumeraciones.EnumTipoRechazoRecaudo;

/**
 * DTO para respuestas de homologacion de catalogos en un recaudo
 * 
 * @author julio.pinzon 2016-05-06
 * 
 */
public class RespuestaHomologarCatalogoRecaudoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private List<EnumTipoRechazoRecaudo> errores;
    private PagoDTO pagoDTO;

    // --- Constructor
    public RespuestaHomologarCatalogoRecaudoDTO() {
    }

    // --- Getters-Setters

    public List<EnumTipoRechazoRecaudo> getErrores() {
        return errores;
    }

    public void setErrores(List<EnumTipoRechazoRecaudo> errores) {
        this.errores = errores;
    }

    public PagoDTO getPagoDTO() {
        return pagoDTO;
    }

    public void setPagoDTO(PagoDTO pagoDTO) {
        this.pagoDTO = pagoDTO;
    }
}
