package co.com.datatools.c2.managed_bean.accidentalidad;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.DetalleAccidentalidadDTO;
import co.com.datatools.c2.dto.FiltrosAccidentalidadDTO;
import co.com.datatools.c2.dto.RegistroAccidentalidadDTO;
import co.com.datatools.c2.dto.RespuestaAccidentalidadDTO;

/**
 * 
 * Objeto para el manejo de la informacion asociada a informes de IPAT
 * 
 * @author divier.casas
 * 
 */
public class AdminAccidentalidadHolderFL implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminAccidentalidadHolderFL";
    private RegistroAccidentalidadDTO regAccidDTO;

    private FiltrosAccidentalidadDTO filtrosAccidentalidadDTO;

    private boolean anexos;

    /**
     * Atributo que define el modelo para el manejo de los registros encontrados
     */
    private List<RespuestaAccidentalidadDTO> respuestaAccidentalidadDTOs;

    /**
     * Atributo para manejar un registro del modelo seleccionado.
     */
    private RespuestaAccidentalidadDTO respuestaAccidentalidadSelDTO;

    public AdminAccidentalidadHolderFL() {
        regAccidDTO = new RegistroAccidentalidadDTO();
        regAccidDTO.setDetalleAccidentalidadDTO(new DetalleAccidentalidadDTO());

        filtrosAccidentalidadDTO = new FiltrosAccidentalidadDTO();
    }

    public RegistroAccidentalidadDTO getRegAccidDTO() {
        return regAccidDTO;
    }

    public void setRegAccidDTO(RegistroAccidentalidadDTO regAccidDTO) {
        this.regAccidDTO = regAccidDTO;
    }

    public FiltrosAccidentalidadDTO getFiltrosAccidentalidadDTO() {
        return filtrosAccidentalidadDTO;
    }

    public void setFiltrosAccidentalidadDTO(FiltrosAccidentalidadDTO filtrosAccidentalidadDTO) {
        this.filtrosAccidentalidadDTO = filtrosAccidentalidadDTO;
    }

    public List<RespuestaAccidentalidadDTO> getRespuestaAccidentalidadDTOs() {
        return respuestaAccidentalidadDTOs;
    }

    public void setRespuestaAccidentalidadDTOs(List<RespuestaAccidentalidadDTO> respuestaAccidentalidadDTOs) {
        this.respuestaAccidentalidadDTOs = respuestaAccidentalidadDTOs;
    }

    public RespuestaAccidentalidadDTO getRespuestaAccidentalidadSelDTO() {
        return respuestaAccidentalidadSelDTO;
    }

    public void setRespuestaAccidentalidadSelDTO(RespuestaAccidentalidadDTO respuestaAccidentalidadSelDTO) {
        this.respuestaAccidentalidadSelDTO = respuestaAccidentalidadSelDTO;
    }

    public boolean isAnexos() {
        return anexos;
    }

    public void setAnexos(boolean anexos) {
        this.anexos = anexos;
    }
}