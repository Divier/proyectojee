package co.com.datatools.c2.entidades.personas;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 3 - valida
 */
@Entity
@Table(name = "persona_fuente_externa")
public class PersonaFuenteExterna implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_fuente_exter")
    private Long id;

    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoIdentificacionPersona tipoIdentificacion;

    @Basic(optional = false)
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @JoinColumn(name = "codigo_fuente_informacion", referencedColumnName = "codigo_fuente_informacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFuenteInformacion fuenteInformacion;

    @Column(name = "fecha_ultima_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaActualizacion;

    @Column(name = "fecha_expedicion_documento")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionDocumento;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "fecha_fallecimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaFallecimiento;

    @Column(name = "telefono")
    private String numeroTelefonico;

    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoCivil estadoCivil;

    // TODO FM convertir a una enumeracion
    @Column(name = "genero")
    private Character genero;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nombre1")
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "celular")
    private String numeroCelular;

    @Column(name = "nombre_empresa_labora")
    private String nombreEmpresaLabora;

    @Column(name = "notif_direc_empre_labor")
    private Boolean notificaDirEmpresa;

    @Column(name = "cargo_empresa_labora")
    private String cargoEmpresaLabora;

    @Column(name = "fax_empresa_labora")
    private String faxEmpresaLabora;

    @Lob
    @Column(name = "huella_digital")
    private byte[] huellaDigital;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @JoinColumn(name = "codigo_tipo_vivienda", referencedColumnName = "codigo_tipo_vivienda")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoVivienda tipoVivienda;

    @JoinColumn(name = "codigo_nivel_educativo", referencedColumnName = "codigo_nivel_educativo")
    @ManyToOne(fetch = FetchType.LAZY)
    private NivelEducativo nivelEducativo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaFuenteExterna", fetch = FetchType.LAZY)
    private List<DireccionPersonaFuenteExterna> direccionFuenteExternaList;

    public PersonaFuenteExterna() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoIdentificacionPersona getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionPersona tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public TipoFuenteInformacion getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(TipoFuenteInformacion fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Date getFechaExpedicionDocumento() {
        return fechaExpedicionDocumento;
    }

    public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
        this.fechaExpedicionDocumento = fechaExpedicionDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNombreEmpresaLabora() {
        return nombreEmpresaLabora;
    }

    public void setNombreEmpresaLabora(String nombreEmpresaLabora) {
        this.nombreEmpresaLabora = nombreEmpresaLabora;
    }

    public Boolean getNotificaDirEmpresa() {
        return notificaDirEmpresa;
    }

    public void setNotificaDirEmpresa(Boolean notificaDirEmpresa) {
        this.notificaDirEmpresa = notificaDirEmpresa;
    }

    public String getCargoEmpresaLabora() {
        return cargoEmpresaLabora;
    }

    public void setCargoEmpresaLabora(String cargoEmpresaLabora) {
        this.cargoEmpresaLabora = cargoEmpresaLabora;
    }

    public String getFaxEmpresaLabora() {
        return faxEmpresaLabora;
    }

    public void setFaxEmpresaLabora(String faxEmpresaLabora) {
        this.faxEmpresaLabora = faxEmpresaLabora;
    }

    public byte[] getHuellaDigital() {
        return huellaDigital;
    }

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public List<DireccionPersonaFuenteExterna> getDireccionFuenteExternaList() {
        return direccionFuenteExternaList;
    }

    public void setDireccionFuenteExternaList(List<DireccionPersonaFuenteExterna> direccionFuenteExternaList) {
        this.direccionFuenteExternaList = direccionFuenteExternaList;
    }

}
