package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ubicabilidad_axis database table.
 * 
 */
@Entity
@Table(name = "it_ubicabilidad_axis")
@NamedQueries({ @NamedQuery(name = "ItUbicabilidadAxis.findAll", query = "SELECT ua FROM ItUbicabilidadAxis ua"),
        @NamedQuery(
                name = "ItUbicabilidadAxis.findByEstadoLectura",
                query = "SELECT ua FROM ItUbicabilidadAxis ua WHERE ua.idEstadoLecturaAxis = :pIdEstadoLecturaAxis") })
public class ItUbicabilidadAxis implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Consulta los registros de ubicabilidad asociados al estado de lectura<br>
     * <p>
     * Parametros:<br>
     * <li><b>pIdEstadoLecturaAxis</b> estado de lectura del registro de ubicabilidad</li>
     * </p>
     * <br>
     * Consulta:<br>
     * SELECT ua FROM ItUbicabilidadAxis ua WHERE ua.idEstadoLecturaAxis = :pIdEstadoLecturaAxis
     * 
     * @author divier.casas(2016-05-17)
     */
    public static final String SQ_FIND_BY_ESTADO_LECTURA = "ItUbicabilidadAxis.findByEstadoLectura";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicabilidad_axis")
    private Long id;

    @Column(name = "id_tipo_documento_axis")
    private String idTipoDocumentoAxis;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "razon_social")
    private String razonSocial;

    private String direccion;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "id_fuente_informacion")
    private String idFuenteInformacion;

    @Column(name = "id_estado_lectura_axis")
    private String idEstadoLecturaAxis;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reporte")
    private Date fechaReporte;

    public ItUbicabilidadAxis() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTipoDocumentoAxis() {
        return idTipoDocumentoAxis;
    }

    public void setIdTipoDocumentoAxis(String idTipoDocumentoAxis) {
        this.idTipoDocumentoAxis = idTipoDocumentoAxis;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIdFuenteInformacion() {
        return idFuenteInformacion;
    }

    public void setIdFuenteInformacion(String idFuenteInformacion) {
        this.idFuenteInformacion = idFuenteInformacion;
    }

    public String getIdEstadoLecturaAxis() {
        return idEstadoLecturaAxis;
    }

    public void setIdEstadoLecturaAxis(String idEstadoLecturaAxis) {
        this.idEstadoLecturaAxis = idEstadoLecturaAxis;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}