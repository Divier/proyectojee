package co.com.datatools.c2.dto.comparendo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Metodo que se encarga de unificar los tipos de inconsistencias para unproceso de recepcion de comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class UnificacionInconsistenciasComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String numeroComparendo;
    private Integer codigoOrigen;
    private String codigoInfraccion;
    private Date fechaRegistro;
    private Date fechaReporte;
    private Date fechaInfraccion;
    private Date horaInfraccion;
    private Integer idTipoIdentificacionInfractor;
    private String nombreTipoIdentificacionInfractor;
    private String numeroIdentificacionInfractor;
    private String nombre1Infractor;
    private String nombre2Infractor;
    private String apellido1Infractor;
    private String apellido2Infractor;
    private UsuarioPersonaDTO usuarioPersona;
    private String nombreCampoInconsistencias;
    private String errorProcesamiento;
    private String placaVehiculo;
    private String nombreOrigen;
    private String loginUsuario;
    private String tabla;
    private Date fechaHoraInfraccion;

    // --- Constructor
    public UnificacionInconsistenciasComparendoDTO() {
    }

    public UnificacionInconsistenciasComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Integer getCodigoOrigen() {
        return this.codigoOrigen;
    }

    public void setCodigoOrigen(Integer codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoInfraccion() {
        return this.codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaReporte() {
        return this.fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaInfraccion() {
        return this.fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public Date getHoraInfraccion() {
        return this.horaInfraccion;
    }

    public void setHoraInfraccion(Date horaInfraccion) {
        this.horaInfraccion = horaInfraccion;
    }

    public Integer getIdTipoIdentificacionInfractor() {
        return this.idTipoIdentificacionInfractor;
    }

    public void setIdTipoIdentificacionInfractor(Integer idTipoIdentificacionInfractor) {
        this.idTipoIdentificacionInfractor = idTipoIdentificacionInfractor;
    }

    public String getNumeroIdentificacionInfractor() {
        return this.numeroIdentificacionInfractor;
    }

    public void setNumeroIdentificacionInfractor(String numeroIdentificacionInfractor) {
        this.numeroIdentificacionInfractor = numeroIdentificacionInfractor;
    }

    public String getNombre1Infractor() {
        return this.nombre1Infractor;
    }

    public void setNombre1Infractor(String nombre1Infractor) {
        this.nombre1Infractor = nombre1Infractor;
    }

    public String getNombre2Infractor() {
        return this.nombre2Infractor;
    }

    public void setNombre2Infractor(String nombre2Infractor) {
        this.nombre2Infractor = nombre2Infractor;
    }

    public String getApellido1Infractor() {
        return this.apellido1Infractor;
    }

    public void setApellido1Infractor(String apellido1Infractor) {
        this.apellido1Infractor = apellido1Infractor;
    }

    public String getApellido2Infractor() {
        return this.apellido2Infractor;
    }

    public void setApellido2Infractor(String apellido2Infractor) {
        this.apellido2Infractor = apellido2Infractor;
    }

    public UsuarioPersonaDTO getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersonaDTO usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public String getNombreCampoInconsistencias() {
        return nombreCampoInconsistencias;
    }

    public void setNombreCampoInconsistencias(String nombreCampoInconsistencias) {
        this.nombreCampoInconsistencias = nombreCampoInconsistencias;
    }

    public String getErrorProcesamiento() {
        return errorProcesamiento;
    }

    public void setErrorProcesamiento(String errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getNombreTipoIdentificacionInfractor() {
        return nombreTipoIdentificacionInfractor;
    }

    public void setNombreTipoIdentificacionInfractor(String nombreTipoIdentificacionInfractor) {
        this.nombreTipoIdentificacionInfractor = nombreTipoIdentificacionInfractor;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public Date getFechaHoraInfraccion() {
        return fechaHoraInfraccion;
    }

    public void setFechaHoraInfraccion(String fechaHoraInfraccion) {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        fechaHoraInfraccion = fechaHoraInfraccion.replace("-", "/");
        Date fechaHoraInfra = null;
        try {
            fechaHoraInfra = formatoDelTexto.parse(fechaHoraInfraccion);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fechaHoraInfraccion = fechaHoraInfra;
    }

}
