package co.com.datatools.c2.managed_bean.comparendo.administracion.anulacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoConsultaAnulacionDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author diego.fonseca
 * 
 */
public class AnulacionHolderFL extends AbstractC2ManagedBean {

    /**
     * variables que almacenan datos de la consulta
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    private Date fechaInicialImposicion;
    private Date fechaFinalImposicion;
    private String direccionInfraccion;
    private List<ComparendoConsultaAnulacionDTO> comparendoConsultaAnulacionSeleccionados = new ArrayList<>();
    private List<ComparendoConsultaAnulacionDTO> comparendoConsultaAnulacionDTOList = new ArrayList<>();
    public static final String NOMBRE_BEAN = "anulacionHolderFL";

    public AnulacionHolderFL() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaInicialImposicion() {
        return fechaInicialImposicion;
    }

    public void setFechaInicialImposicion(Date fechaInicialImposicion) {
        this.fechaInicialImposicion = fechaInicialImposicion;
    }

    public Date getFechaFinalImposicion() {
        return fechaFinalImposicion;
    }

    public void setFechaFinalImposicion(Date fechaFinalImposicion) {
        this.fechaFinalImposicion = fechaFinalImposicion;
    }

    public String getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(String direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public List<ComparendoConsultaAnulacionDTO> getComparendoConsultaAnulacionDTOList() {
        return comparendoConsultaAnulacionDTOList;
    }

    public void setComparendoConsultaAnulacionDTOList(
            List<ComparendoConsultaAnulacionDTO> comparendoConsultaAnulacionDTOList) {
        this.comparendoConsultaAnulacionDTOList = comparendoConsultaAnulacionDTOList;
    }

    public List<ComparendoConsultaAnulacionDTO> getComparendoConsultaAnulacionSeleccionados() {
        return comparendoConsultaAnulacionSeleccionados;
    }

    public void setComparendoConsultaAnulacionSeleccionados(
            List<ComparendoConsultaAnulacionDTO> comparendoConsultaAnulacionSeleccionados) {
        this.comparendoConsultaAnulacionSeleccionados = comparendoConsultaAnulacionSeleccionados;
    }

}
