package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:45 COT 2016
 */
public class DetalleCargueCourierDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroProceso;
    private Date fechaProceso;
    private String respuesta;
    private CargueCourierDTO cargueCourier;
    private CoactivoDTO coactivo;
    private DireccionDTO direccion;
    private List<DetalleCargueCourierErrorDTO> detalleCargueCourierErrors;

    // --- Constructor
    public DetalleCargueCourierDTO() {
    }

    public DetalleCargueCourierDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProceso() {
        return this.numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public CargueCourierDTO getCargueCourier() {
        return this.cargueCourier;
    }

    public void setCargueCourier(CargueCourierDTO cargueCourier) {
        this.cargueCourier = cargueCourier;
    }

    public CoactivoDTO getCoactivo() {
        return this.coactivo;
    }

    public void setCoactivo(CoactivoDTO coactivo) {
        this.coactivo = coactivo;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCargueCourierErrorDTO> getDetalleCargueCourierErrors() {
        if (this.detalleCargueCourierErrors == null) {
            this.detalleCargueCourierErrors = new java.util.ArrayList<>(1);
        }
        return this.detalleCargueCourierErrors;
    }

    public void setDetalleCargueCourierErrors(List<DetalleCargueCourierErrorDTO> detalleCargueCourierErrors) {
        this.detalleCargueCourierErrors = detalleCargueCourierErrors;
    }

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

}
