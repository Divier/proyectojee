package co.com.datatools.c2.managed_bean.administracion.parametros;

import java.util.List;

import co.com.datatools.c2.dto.AdminParametrosDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta de jobs
 * 
 * @author divier.casas
 * 
 */
public class AdminParametrosHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminParametrosHolderFL";

    /**
     * Listado de Parametros
     */
    private List<AdminParametrosDTO> lsParametrosDTO;

    /**
     * Estado del parametro seleccionado
     */
    private AdminParametrosDTO paraSeleccionado;

    public List<AdminParametrosDTO> getLsParametrosDTO() {
        return lsParametrosDTO;
    }

    public void setLsParametrosDTO(List<AdminParametrosDTO> lsParametrosDTO) {
        this.lsParametrosDTO = lsParametrosDTO;
    }

    public AdminParametrosDTO getParaSeleccionado() {
        return paraSeleccionado;
    }

    public void setParaSeleccionado(AdminParametrosDTO paraSeleccionado) {
        this.paraSeleccionado = paraSeleccionado;
    }

}