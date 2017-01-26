package co.com.datatools.c2.managed_bean.comparendo.administracion.agente;

import java.util.Date;

import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author ricardo.chavarro
 * 
 */
public class AgenteFL extends AbstractC2ManagedBean {

    /**
     * 
     * Variables que almacena los datos para guardar la anulación
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "agenteFL";
    private String placaAgente;
    private AgenteDTO agenteDTO;
    private String nombreAgente;
	public String getPlacaAgente() {
		return placaAgente;
	}
	public void setPlacaAgente(String placaAgente) {
		this.placaAgente = placaAgente;
	}
	public AgenteDTO getAgenteDTO() {
		return agenteDTO;
	}
	public void setAgenteDTO(AgenteDTO agenteDTO) {
		this.agenteDTO = agenteDTO;
	}
	public String getNombreAgente() {
		return nombreAgente;
	}
	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

}
