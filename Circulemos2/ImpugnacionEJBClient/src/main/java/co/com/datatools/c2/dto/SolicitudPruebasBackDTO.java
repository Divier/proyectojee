package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:52:12 COT 2016
 */
public class SolicitudPruebasBackDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String descripcion;
    private Date fechaSolicitud;
    private TipoPruebaDTO tipoPrueba;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private List<PruebaDTO> pruebas;
    private List<ImpulsoPruebaDTO> impulsoPruebas;
    private List<ProrrogaPruebaDTO> prorrogaPruebas;
    private CaracteristicaPruebaDTO caracteristicaPrueba;
    private OrigenPruebaDTO origenPrueba;
    private Date fechaLimite;
    // private String para; // se reemplaza por catalogo (id_tipo_destino_prueba)
    private TipoDestinoPruebaImpugDTO tipoDestinoPruebaImpug;
    private String destinoPruebaOtro;
    private String consecutivoDocumento;

    // --- Constructor
    public SolicitudPruebasBackDTO() {
    }

    public SolicitudPruebasBackDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public TipoPruebaDTO getTipoPrueba() {
        return this.tipoPrueba;
    }

    public void setTipoPrueba(TipoPruebaDTO tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<PruebaDTO> getPruebas() {
        if (this.pruebas == null) {
            this.pruebas = new java.util.ArrayList<>(1);
        }
        return this.pruebas;
    }

    public void setPruebas(List<PruebaDTO> pruebas) {
        this.pruebas = pruebas;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ImpulsoPruebaDTO> getImpulsoPruebas() {
        if (this.impulsoPruebas == null) {
            this.impulsoPruebas = new java.util.ArrayList<>(1);
        }
        return this.impulsoPruebas;
    }

    public void setImpulsoPruebas(List<ImpulsoPruebaDTO> impulsoPruebas) {
        this.impulsoPruebas = impulsoPruebas;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProrrogaPruebaDTO> getProrrogaPruebas() {
        if (this.prorrogaPruebas == null) {
            this.prorrogaPruebas = new java.util.ArrayList<>(1);
        }
        return this.prorrogaPruebas;
    }

    public void setProrrogaPruebas(List<ProrrogaPruebaDTO> prorrogaPruebas) {
        this.prorrogaPruebas = prorrogaPruebas;
    }

    public CaracteristicaPruebaDTO getCaracteristicaPrueba() {
        return this.caracteristicaPrueba;
    }

    public void setCaracteristicaPrueba(CaracteristicaPruebaDTO caracteristicaPrueba) {
        this.caracteristicaPrueba = caracteristicaPrueba;
    }

    public OrigenPruebaDTO getOrigenPrueba() {
        return this.origenPrueba;
    }

    public void setOrigenPrueba(OrigenPruebaDTO origenPrueba) {
        this.origenPrueba = origenPrueba;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public TipoDestinoPruebaImpugDTO getTipoDestinoPruebaImpug() {
        return tipoDestinoPruebaImpug;
    }

    public void setTipoDestinoPruebaImpug(TipoDestinoPruebaImpugDTO tipoDestinoPruebaImpug) {
        this.tipoDestinoPruebaImpug = tipoDestinoPruebaImpug;
    }

    public String getDestinoPruebaOtro() {
        return destinoPruebaOtro;
    }

    public void setDestinoPruebaOtro(String destinoPruebaOtro) {
        this.destinoPruebaOtro = destinoPruebaOtro;
    }

    public String getConsecutivoDocumento() {
        return consecutivoDocumento;
    }

    public void setConsecutivoDocumento(String consecutivoDocumento) {
        this.consecutivoDocumento = consecutivoDocumento;
    }

    // public String getPara() {
    // return para;
    // }
    //
    // public void setPara(String para) {
    // this.para = para;
    // }

}