package co.com.datatools.c2.managed_bean.coactivo.administracion.seguimiento;

import java.util.List;

import co.com.datatools.c2.dto.ArchivoExcepcionDTO;
import co.com.datatools.c2.dto.ConsultaSeguimientoCoactivoDTO;
import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RespuestaSeguimientoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta del seguimiento de coactivos
 * 
 * @author divier.casas
 * 
 */
public class ConsultaSeguimientoHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "consultaSeguimientoHolderFL";
    private ConsultaSeguimientoCoactivoDTO consultaSegCoactivo;

    /**
     * listado de seguimientos consultados
     */
    private List<RespuestaSeguimientoDTO> lstSeguimientos;

    /**
     * Seguimiento seleccionado
     */
    private RespuestaSeguimientoDTO segSeleccionado;

    /**
     * Excepciones
     */
    private boolean excepciones;
    private RadicarExcepcionDTO radicarExcepcionDTO;
    private ArchivoExcepcionDTO archivoExcepcionRadSelDTO;
    private ArchivoExcepcionDTO archivoExcepcionFalloSelDTO;
    private boolean falloAFavor;
    private boolean fallo;
    private List<ArchivoExcepcionDTO> archivoExcepcionRadDTOs;
    private List<ArchivoExcepcionDTO> archivoExcepcionFalloDTOs;
    private boolean anexo;

    public ConsultaSeguimientoHolderFL() {
        consultaSegCoactivo = new ConsultaSeguimientoCoactivoDTO();
    }

    public ConsultaSeguimientoCoactivoDTO getConsultaSegCoactivo() {
        return consultaSegCoactivo;
    }

    public void setConsultaSegCoactivo(ConsultaSeguimientoCoactivoDTO consultaSegCoactivo) {
        this.consultaSegCoactivo = consultaSegCoactivo;
    }

    public List<RespuestaSeguimientoDTO> getLstSeguimientos() {
        return lstSeguimientos;
    }

    public void setLstSeguimientos(List<RespuestaSeguimientoDTO> lstSeguimientos) {
        this.lstSeguimientos = lstSeguimientos;
    }

    public RespuestaSeguimientoDTO getSegSeleccionado() {
        return segSeleccionado;
    }

    public void setSegSeleccionado(RespuestaSeguimientoDTO segSeleccionado) {
        this.segSeleccionado = segSeleccionado;
    }

    public RadicarExcepcionDTO getRadicarExcepcionDTO() {
        return radicarExcepcionDTO;
    }

    public void setRadicarExcepcionDTO(RadicarExcepcionDTO radicarExcepcionDTO) {
        this.radicarExcepcionDTO = radicarExcepcionDTO;
    }

    public boolean isExcepciones() {
        return excepciones;
    }

    public void setExcepciones(boolean excepciones) {
        this.excepciones = excepciones;
    }

    public boolean isFalloAFavor() {
        return falloAFavor;
    }

    public void setFalloAFavor(boolean falloAFavor) {
        this.falloAFavor = falloAFavor;
    }

    public boolean isFallo() {
        return fallo;
    }

    public void setFallo(boolean fallo) {
        this.fallo = fallo;
    }

    public List<ArchivoExcepcionDTO> getArchivoExcepcionRadDTOs() {
        return archivoExcepcionRadDTOs;
    }

    public void setArchivoExcepcionRadDTOs(List<ArchivoExcepcionDTO> archivoExcepcionRadDTOs) {
        this.archivoExcepcionRadDTOs = archivoExcepcionRadDTOs;
    }

    public List<ArchivoExcepcionDTO> getArchivoExcepcionFalloDTOs() {
        return archivoExcepcionFalloDTOs;
    }

    public void setArchivoExcepcionFalloDTOs(List<ArchivoExcepcionDTO> archivoExcepcionFalloDTOs) {
        this.archivoExcepcionFalloDTOs = archivoExcepcionFalloDTOs;
    }

    public boolean isAnexo() {
        return anexo;
    }

    public void setAnexo(boolean anexo) {
        this.anexo = anexo;
    }

    public ArchivoExcepcionDTO getArchivoExcepcionRadSelDTO() {
        return archivoExcepcionRadSelDTO;
    }

    public void setArchivoExcepcionRadSelDTO(ArchivoExcepcionDTO archivoExcepcionRadSelDTO) {
        this.archivoExcepcionRadSelDTO = archivoExcepcionRadSelDTO;
    }

    public ArchivoExcepcionDTO getArchivoExcepcionFalloSelDTO() {
        return archivoExcepcionFalloSelDTO;
    }

    public void setArchivoExcepcionFalloSelDTO(ArchivoExcepcionDTO archivoExcepcionFalloSelDTO) {
        this.archivoExcepcionFalloSelDTO = archivoExcepcionFalloSelDTO;
    }
}