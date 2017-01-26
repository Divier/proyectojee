package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the gestion_cobro_sac database table.
 * 
 */
@Entity
@Table(name = "gestion_cobro_sac")
@NamedQuery(name = "GestionCobroSac.findAll", query = "SELECT g FROM GestionCobroSac g")
public class GestionCobroSac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion_cobro_sac")
    private Long id;

    @Column(name = "idHistoriaGestion")
    private Integer idHistoriaGestion;

    @Column(name = "idDeudor")
    private Integer idDeudor;

    @Column(name = "idCuenta")
    private Integer idCuenta;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "multa")
    private String multa;

    @Column(name = "numeroCitacion")
    private String numeroCitacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaGestion")
    private Date fechaGestion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaNotificado")
    private Date fechaNotificado;

    @Column(name = "tipoGestionSac")
    private String tipoGestionSac;

    @Column(name = "accion")
    private String accion;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "resultadoAccion")
    private String resultadoAccion;

    @Column(name = "resultadoGestion")
    private String resultadoGestion;

    @Column(name = "tipoEvidencia")
    private String tipoEvidencia;

    @Column(name = "datoUbicabilidad")
    private String datoUbicabilidad;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "observacionesInternas")
    private String observacionesInternas;

    @Column(name = "id_cartera")
    private Long idCartera;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public GestionCobroSac() {
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