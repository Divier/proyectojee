package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * *
 * 
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:56:29 COT 2016
 */
public class PrecoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private CargueCoactivoDTO cargueCoactivo;
    private EstadoPrecoactivoDTO estadoPrecoactivo;
    private PersonaDTO persona;
    private FuncionarioDTO funcionario;
    private List<PrecoactivoErrorDTO> precoactivoErrors;
    private Integer codigoTipoObligacion;
    private Date fechaObligacion;
    private CarteraDTO cartera;
    private String numeroObligacion;
    private BigDecimal valorInteresMoratorios;
    private BigDecimal valorObligacion;
    private DireccionDTO direccion;

    // --- Constructor
    public PrecoactivoDTO() {
    }

    public PrecoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CargueCoactivoDTO getCargueCoactivo() {
        return this.cargueCoactivo;
    }

    public void setCargueCoactivo(CargueCoactivoDTO cargueCoactivo) {
        this.cargueCoactivo = cargueCoactivo;
    }

    public EstadoPrecoactivoDTO getEstadoPrecoactivo() {
        return this.estadoPrecoactivo;
    }

    public void setEstadoPrecoactivo(EstadoPrecoactivoDTO estadoPrecoactivo) {
        this.estadoPrecoactivo = estadoPrecoactivo;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public FuncionarioDTO getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PrecoactivoErrorDTO> getPrecoactivoErrors() {
        if (this.precoactivoErrors == null) {
            this.precoactivoErrors = new java.util.ArrayList<>(1);
        }
        return this.precoactivoErrors;
    }

    public void setPrecoactivoErrors(List<PrecoactivoErrorDTO> precoactivoErrors) {
        this.precoactivoErrors = precoactivoErrors;
    }

    public Integer getCodigoTipoObligacion() {
        return this.codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public Date getFechaObligacion() {
        return this.fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public CarteraDTO getCartera() {
        return this.cartera;
    }

    public void setCartera(CarteraDTO cartera) {
        this.cartera = cartera;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorInteresMoratorios() {
        return this.valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

}
