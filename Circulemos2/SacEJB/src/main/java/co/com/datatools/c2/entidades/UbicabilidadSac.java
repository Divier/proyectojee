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
 * The persistent class for the ubicabilidad_sac database table.
 * 
 */
@Entity
@Table(name = "ubicabilidad_sac")
@NamedQuery(name = "UbicabilidadSac.findAll", query = "SELECT u FROM UbicabilidadSac u")
public class UbicabilidadSac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicabilidad_sac")
    private Long id;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "pais")
    private String pais;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "localidad")
    private String localidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "id_estado_transaccion_sac")
    private Integer idEstadoTransaccionSac;

    @Column(name = "id_origen_ubicabilidad_sac")
    private Integer idOrigenUbicabilidadSac;

    @Column(name = "id_tipo_documento_sac")
    private Integer idTipoDocumentoSac;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    public UbicabilidadSac() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdEstadoTransaccionSac() {
        return this.idEstadoTransaccionSac;
    }

    public void setIdEstadoTransaccionSac(int idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

    public Integer getIdOrigenUbicabilidadSac() {
        return this.idOrigenUbicabilidadSac;
    }

    public void setIdOrigenUbicabilidadSac(Integer idOrigenUbicabilidadSac) {
        this.idOrigenUbicabilidadSac = idOrigenUbicabilidadSac;
    }

    public Integer getIdTipoDocumentoSac() {
        return this.idTipoDocumentoSac;
    }

    public void setIdTipoDocumentoSac(Integer idTipoDocumentoSac) {
        this.idTipoDocumentoSac = idTipoDocumentoSac;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return this.primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return this.telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setIdEstadoTransaccionSac(Integer idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

}