package co.com.datatools.c2.web.util.comparendo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;

public class RespuestaValidacionVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Listado de errores encontrados en la validacion
     */
    private List<DetalleProcesamientoDTO> detalleProcesamientoVehiculoDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoInfractorDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoPropietarioDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoAgtTransitoDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoObservacionesDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoInmovilizacionDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoTestigoDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoEmbriaguezDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoEvidenciasFisicasDTOs;
    private List<DetalleProcesamientoDTO> detalleProcesamientoEncabezadoDTOs;

    private String codigoResultado;
    private boolean existeAlerta;

    public RespuestaValidacionVO() {
        detalleProcesamientoVehiculoDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoInfractorDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoPropietarioDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoAgtTransitoDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoObservacionesDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoInmovilizacionDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoTestigoDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoEmbriaguezDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoEvidenciasFisicasDTOs = new ArrayList<DetalleProcesamientoDTO>();
        detalleProcesamientoEncabezadoDTOs = new ArrayList<DetalleProcesamientoDTO>();
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoVehiculoDTOs() {
        return detalleProcesamientoVehiculoDTOs;
    }

    public void setDetalleProcesamientoVehiculoDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoVehiculoDTOs) {
        this.detalleProcesamientoVehiculoDTOs = detalleProcesamientoVehiculoDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoInfractorDTOs() {
        return detalleProcesamientoInfractorDTOs;
    }

    public void setDetalleProcesamientoInfractorDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoInfractorDTOs) {
        this.detalleProcesamientoInfractorDTOs = detalleProcesamientoInfractorDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoPropietarioDTOs() {
        return detalleProcesamientoPropietarioDTOs;
    }

    public void setDetalleProcesamientoPropietarioDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoPropietarioDTOs) {
        this.detalleProcesamientoPropietarioDTOs = detalleProcesamientoPropietarioDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoAgtTransitoDTOs() {
        return detalleProcesamientoAgtTransitoDTOs;
    }

    public void setDetalleProcesamientoAgtTransitoDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoAgtTransitoDTOs) {
        this.detalleProcesamientoAgtTransitoDTOs = detalleProcesamientoAgtTransitoDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoObservacionesDTOs() {
        return detalleProcesamientoObservacionesDTOs;
    }

    public void setDetalleProcesamientoObservacionesDTOs(
            List<DetalleProcesamientoDTO> detalleProcesamientoObservacionesDTOs) {
        this.detalleProcesamientoObservacionesDTOs = detalleProcesamientoObservacionesDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoInmovilizacionDTOs() {
        return detalleProcesamientoInmovilizacionDTOs;
    }

    public void setDetalleProcesamientoInmovilizacionDTOs(
            List<DetalleProcesamientoDTO> detalleProcesamientoInmovilizacionDTOs) {
        this.detalleProcesamientoInmovilizacionDTOs = detalleProcesamientoInmovilizacionDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoTestigoDTOs() {
        return detalleProcesamientoTestigoDTOs;
    }

    public void setDetalleProcesamientoTestigoDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoTestigoDTOs) {
        this.detalleProcesamientoTestigoDTOs = detalleProcesamientoTestigoDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoEmbriaguezDTOs() {
        return detalleProcesamientoEmbriaguezDTOs;
    }

    public void setDetalleProcesamientoEmbriaguezDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoEmbriaguezDTOs) {
        this.detalleProcesamientoEmbriaguezDTOs = detalleProcesamientoEmbriaguezDTOs;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoEvidenciasFisicasDTOs() {
        return detalleProcesamientoEvidenciasFisicasDTOs;
    }

    public void setDetalleProcesamientoEvidenciasFisicasDTOs(
            List<DetalleProcesamientoDTO> detalleProcesamientoEvidenciasFisicasDTOs) {
        this.detalleProcesamientoEvidenciasFisicasDTOs = detalleProcesamientoEvidenciasFisicasDTOs;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public boolean isExisteAlerta() {
        return existeAlerta;
    }

    public void setExisteAlerta(boolean existeAlerta) {
        this.existeAlerta = existeAlerta;
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientoEncabezadoDTOs() {
        return detalleProcesamientoEncabezadoDTOs;
    }

    public void setDetalleProcesamientoEncabezadoDTOs(List<DetalleProcesamientoDTO> detalleProcesamientoEncabezadoDTOs) {
        this.detalleProcesamientoEncabezadoDTOs = detalleProcesamientoEncabezadoDTOs;
    }

}
