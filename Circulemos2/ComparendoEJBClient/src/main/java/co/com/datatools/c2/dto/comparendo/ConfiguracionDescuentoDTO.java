package co.com.datatools.c2.dto.comparendo;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.TipoDescuentoDTO;
import co.com.datatools.c2.dto.TipoFechaOrigenDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Se agrego atributo de negocio NO autogenerar
 * 
 * @author Generated
 * @version 3.0 - Tue Jan 26 11:44:21 COT 2016
 */
public class ConfiguracionDescuentoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer cantidadDiasCalendario;
    private Integer cantidadDiasHabiles;
    private String descripcion;
    private Integer diasDesdeFechaImposicion;
    private Date fechaVigenciaFin;
    private Date fechaVigenciaInicio;
    private boolean interesComparendo;
    private String limite;
    private Boolean resolucionEspecial;
    private OrganismoTransitoDTO organismoTransito;
    private TipoDescuentoDTO tipoDescuento;
    private TipoFechaOrigenDTO tipoFechaOrigen;
    private List<DetalleDescuentoDTO> detalleDescuentoList;
    private List<MedioImposicionComparendoDTO> medioImposicionList;
    private List<TipoNotificacionComparendoDTO> tipoNotificacionList;
    // De negocio
    private Date fechaConsulta;

    // --- Constructor
    public ConfiguracionDescuentoDTO() {
    }

    public ConfiguracionDescuentoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadDiasCalendario() {
        return this.cantidadDiasCalendario;
    }

    public void setCantidadDiasCalendario(Integer cantidadDiasCalendario) {
        this.cantidadDiasCalendario = cantidadDiasCalendario;
    }

    public Integer getCantidadDiasHabiles() {
        return this.cantidadDiasHabiles;
    }

    public void setCantidadDiasHabiles(Integer cantidadDiasHabiles) {
        this.cantidadDiasHabiles = cantidadDiasHabiles;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiasDesdeFechaImposicion() {
        return this.diasDesdeFechaImposicion;
    }

    public void setDiasDesdeFechaImposicion(Integer diasDesdeFechaImposicion) {
        this.diasDesdeFechaImposicion = diasDesdeFechaImposicion;
    }

    public Date getFechaVigenciaFin() {
        return this.fechaVigenciaFin;
    }

    public void setFechaVigenciaFin(Date fechaVigenciaFin) {
        this.fechaVigenciaFin = fechaVigenciaFin;
    }

    public Date getFechaVigenciaInicio() {
        return this.fechaVigenciaInicio;
    }

    public void setFechaVigenciaInicio(Date fechaVigenciaInicio) {
        this.fechaVigenciaInicio = fechaVigenciaInicio;
    }

    public boolean getInteresComparendo() {
        return this.interesComparendo;
    }

    public void setInteresComparendo(boolean interesComparendo) {
        this.interesComparendo = interesComparendo;
    }

    public String getLimite() {
        return this.limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public Boolean getResolucionEspecial() {
        return this.resolucionEspecial;
    }

    public void setResolucionEspecial(Boolean resolucionEspecial) {
        this.resolucionEspecial = resolucionEspecial;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoDescuentoDTO getTipoDescuento() {
        return this.tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuentoDTO tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public TipoFechaOrigenDTO getTipoFechaOrigen() {
        return this.tipoFechaOrigen;
    }

    public void setTipoFechaOrigen(TipoFechaOrigenDTO tipoFechaOrigen) {
        this.tipoFechaOrigen = tipoFechaOrigen;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleDescuentoDTO> getDetalleDescuentoList() {
        if (this.detalleDescuentoList == null) {
            this.detalleDescuentoList = new java.util.ArrayList<>(1);
        }
        return this.detalleDescuentoList;
    }

    public void setDetalleDescuentoList(List<DetalleDescuentoDTO> detalleDescuentoList) {
        this.detalleDescuentoList = detalleDescuentoList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<MedioImposicionComparendoDTO> getMedioImposicionList() {
        if (this.medioImposicionList == null) {
            this.medioImposicionList = new java.util.ArrayList<>(1);
        }
        return this.medioImposicionList;
    }

    public void setMedioImposicionList(List<MedioImposicionComparendoDTO> medioImposicionList) {
        this.medioImposicionList = medioImposicionList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<TipoNotificacionComparendoDTO> getTipoNotificacionList() {
        if (this.tipoNotificacionList == null) {
            this.tipoNotificacionList = new java.util.ArrayList<>(1);
        }
        return this.tipoNotificacionList;
    }

    public void setTipoNotificacionList(List<TipoNotificacionComparendoDTO> tipoNotificacionList) {
        this.tipoNotificacionList = tipoNotificacionList;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
}