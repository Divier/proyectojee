package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:40:36 COT 2016
 */
public class CoactivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer anio;
    private Integer cantidadObligaciones;
    private Date fechaNotificacion;
    private Long idTramite;
    private String numeroCoactivo;
    private BigDecimal valorTotalCostasProcesales;
    private BigDecimal valorTotalObligaciones;
    private OrganismoTransitoDTO organismoTransito;
    private ProcesoDTO proceso;
    private CargueCoactivoDTO cargueCoactivo;
    private ConfiguracionCoactivoDTO configuracionCoactivo;
    private DireccionDTO direccion;
    private PersonaDTO persona;
    private List<DetalleCargueCourierDTO> detalleCargueCouriers;
    private List<ObligacionCoactivoDTO> obligacionCoactivos;
    private FuncionarioDTO funcionario;

    // --- Constructor
    public CoactivoDTO() {
    }

    public CoactivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCantidadObligaciones() {
        return this.cantidadObligaciones;
    }

    public void setCantidadObligaciones(Integer cantidadObligaciones) {
        this.cantidadObligaciones = cantidadObligaciones;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Long getIdTramite() {
        return this.idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public String getNumeroCoactivo() {
        return this.numeroCoactivo;
    }

    public void setNumeroCoactivo(String numeroCoactivo) {
        this.numeroCoactivo = numeroCoactivo;
    }

    public BigDecimal getValorTotalCostasProcesales() {
        return this.valorTotalCostasProcesales;
    }

    public void setValorTotalCostasProcesales(BigDecimal valorTotalCostasProcesales) {
        this.valorTotalCostasProcesales = valorTotalCostasProcesales;
    }

    public BigDecimal getValorTotalObligaciones() {
        return this.valorTotalObligaciones;
    }

    public void setValorTotalObligaciones(BigDecimal valorTotalObligaciones) {
        this.valorTotalObligaciones = valorTotalObligaciones;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ProcesoDTO getProceso() {
        return this.proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public CargueCoactivoDTO getCargueCoactivo() {
        return this.cargueCoactivo;
    }

    public void setCargueCoactivo(CargueCoactivoDTO cargueCoactivo) {
        this.cargueCoactivo = cargueCoactivo;
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivo() {
        return this.configuracionCoactivo;
    }

    public void setConfiguracionCoactivo(ConfiguracionCoactivoDTO configuracionCoactivo) {
        this.configuracionCoactivo = configuracionCoactivo;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCargueCourierDTO> getDetalleCargueCouriers() {
        if (this.detalleCargueCouriers == null) {
            this.detalleCargueCouriers = new java.util.ArrayList<>(1);
        }
        return this.detalleCargueCouriers;
    }

    public void setDetalleCargueCouriers(List<DetalleCargueCourierDTO> detalleCargueCouriers) {
        this.detalleCargueCouriers = detalleCargueCouriers;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ObligacionCoactivoDTO> getObligacionCoactivos() {
        if (this.obligacionCoactivos == null) {
            this.obligacionCoactivos = new java.util.ArrayList<>(1);
        }
        return this.obligacionCoactivos;
    }

    public void setObligacionCoactivos(List<ObligacionCoactivoDTO> obligacionCoactivos) {
        this.obligacionCoactivos = obligacionCoactivos;
    }

    public FuncionarioDTO getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

}
