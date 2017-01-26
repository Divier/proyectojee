package co.com.datatools.c2.managed_bean.accidentalidad;

import co.com.datatools.c2.dto.AccidentalidadDTO;
import co.com.datatools.c2.dto.AdjuntosAccidentalidadDTO;
import co.com.datatools.c2.dto.DatosAccidentalidadDTO;
import co.com.datatools.c2.dto.DelegacionDTO;
import co.com.datatools.c2.dto.PrevencionDTO;
import co.com.datatools.c2.dto.TipoAccidenteDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion asociada a informes de IPAT
 * 
 * @author divier.casas
 * 
 */
public class AdminAccidentalidadFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminAccidentalidadFL";

    private DatosAccidentalidadDTO datosAccidentalidadDTO;
    private AdjuntosAccidentalidadDTO adjuntosAccidentalidadSelDTO;

    private boolean confirmarIPAT;
    private String mensajeConfirmIPAT;

    private boolean capturarInforme;

    public AdminAccidentalidadFL() {
        datosAccidentalidadDTO = new DatosAccidentalidadDTO();
        datosAccidentalidadDTO.setAccidentalidad(new AccidentalidadDTO());
        datosAccidentalidadDTO.getAccidentalidad().setDelegacion(new DelegacionDTO());
        datosAccidentalidadDTO.getAccidentalidad().setTipoAccidente(new TipoAccidenteDTO());
        datosAccidentalidadDTO.getAccidentalidad().setPrevencion(new PrevencionDTO());
    }

    public DatosAccidentalidadDTO getDatosAccidentalidadDTO() {
        return datosAccidentalidadDTO;
    }

    public void setDatosAccidentalidadDTO(DatosAccidentalidadDTO datosAccidentalidadDTO) {
        this.datosAccidentalidadDTO = datosAccidentalidadDTO;
    }

    public boolean isConfirmarIPAT() {
        return confirmarIPAT;
    }

    public void setConfirmarIPAT(boolean confirmarIPAT) {
        this.confirmarIPAT = confirmarIPAT;
    }

    public String getMensajeConfirmIPAT() {
        return mensajeConfirmIPAT;
    }

    public void setMensajeConfirmIPAT(String mensajeConfirmIPAT) {
        this.mensajeConfirmIPAT = mensajeConfirmIPAT;
    }

    public AdjuntosAccidentalidadDTO getAdjuntosAccidentalidadSelDTO() {
        return adjuntosAccidentalidadSelDTO;
    }

    public void setAdjuntosAccidentalidadSelDTO(AdjuntosAccidentalidadDTO adjuntosAccidentalidadSelDTO) {
        this.adjuntosAccidentalidadSelDTO = adjuntosAccidentalidadSelDTO;
    }

    public boolean isCapturarInforme() {
        return capturarInforme;
    }

    public void setCapturarInforme(boolean capturarInforme) {
        this.capturarInforme = capturarInforme;
    }

}