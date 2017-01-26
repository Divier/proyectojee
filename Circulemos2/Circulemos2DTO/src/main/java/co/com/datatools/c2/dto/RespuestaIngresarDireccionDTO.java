package co.com.datatools.c2.dto;

import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;

public class RespuestaIngresarDireccionDTO extends RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> {

    private static final long serialVersionUID = 1L;

    private Long idDireccion;

    public RespuestaIngresarDireccionDTO() {
        super();
    }

    public RespuestaIngresarDireccionDTO(boolean estado) {
        super(estado);
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

}
