package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 10 13:59:31 COT 2016
 */
public class GestionCobroSacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos

    private Long id;
    private Integer idHistoriaGestion;
    private Integer idDeudor;
    private Integer idCuenta;
    private String identificacion;
    private String multa;
    private String numeroCitacion;
    private Date fechaGestion;
    private Date fechaNotificado;
    private String tipoGestionSac;
    private String accion;
    private String respuesta;
    private String contacto;
    private String resultadoAccion;
    private String resultadoGestion;
    private String tipoEvidencia;
    private String datoUbicabilidad;
    private String observaciones;
    private String observacionesInternas;
    private Long idCartera;
    private Date fechaRegistro;

    public GestionCobroSacDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdHistoriaGestion() {
        return idHistoriaGestion;
    }

    public void setIdHistoriaGestion(Integer idHistoriaGestion) {
        this.idHistoriaGestion = idHistoriaGestion;
    }

    public Integer getIdDeudor() {
        return idDeudor;
    }

    public void setIdDeudor(Integer idDeudor) {
        this.idDeudor = idDeudor;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public Date getFechaNotificado() {
        return fechaNotificado;
    }

    public void setFechaNotificado(Date fechaNotificado) {
        this.fechaNotificado = fechaNotificado;
    }

    public String getTipoGestionSac() {
        return tipoGestionSac;
    }

    public void setTipoGestionSac(String tipoGestionSac) {
        this.tipoGestionSac = tipoGestionSac;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getResultadoAccion() {
        return resultadoAccion;
    }

    public void setResultadoAccion(String resultadoAccion) {
        this.resultadoAccion = resultadoAccion;
    }

    public String getResultadoGestion() {
        return resultadoGestion;
    }

    public void setResultadoGestion(String resultadoGestion) {
        this.resultadoGestion = resultadoGestion;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(String tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public String getDatoUbicabilidad() {
        return datoUbicabilidad;
    }

    public void setDatoUbicabilidad(String datoUbicabilidad) {
        this.datoUbicabilidad = datoUbicabilidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservacionesInternas() {
        return observacionesInternas;
    }

    public void setObservacionesInternas(String observacionesInternas) {
        this.observacionesInternas = observacionesInternas;
    }

    public Long getIdCartera() {
        return idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
