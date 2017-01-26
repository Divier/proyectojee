package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.dto.ErrorGestionPagoSacDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoTransaccionSac;

public class RespuestaGestionCobroSacDTO {

	 // --- Atributos
    private List<ErrorGestionPagoSacDTO> errores;
    private EnumEstadoTransaccionSac estadoGestionCobro;

    // --- Constructor
    public RespuestaGestionCobroSacDTO() {
    }

    // --- Getters-Setters
	public List<ErrorGestionPagoSacDTO> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorGestionPagoSacDTO> errores) {
		this.errores = errores;
	}

	public EnumEstadoTransaccionSac getEstadoGestionCobro() {
		return estadoGestionCobro;
	}

	public void setEstadoGestionCobro(EnumEstadoTransaccionSac estadoGestionCobro) {
		this.estadoGestionCobro = estadoGestionCobro;
	}
}
