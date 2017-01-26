package co.com.datatools.c2.managed_bean.comparendo.administracion.colombia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.SeguimientoComparendoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;

/**
 * Objeto del flujo que permite visualizar el detalle de un comparendo registrado en el sistema
 * 
 * @author luis.forero(2015-10-19)
 * 
 */
public class DetalleComparendoFL implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "detalleComparendoFL";

    private SeguimientoComparendoDTO seguimientoComparendoDTO;

    private ConfiguracionInfraccionDTO configuracionInfraccion;
    private TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa;
    private ComparendoDTO comparendo;
    private String nomOrganismoTransito;

    // Encabezado
    private String descripcionInfraccion;

    private String direccionInfraccionTexto;
    private String paisDirInfraccion;
    private String departamentoDirInfraccion;
    private String municipioDirInfraccion;
    private String localidadDirInfraccion;
    private Date fechaHoraInfraccion;

    private String numeroComparendo;

    // Vehiculo
    private ComparendoVehiculoDTO empresaVehiculo;
    private ComparendoPersonaDTO empresaPersonaVehiculo;

    // Infractor
    private ComparendoPersonaDTO infractor;
    private String direccionInfractorTexto;
    private String nomOrgLicenciaInfractor;

    // Propietario
    private ComparendoPersonaDTO propietario;
    private String nomOrgLicenciaTrans;

    // AgenteTransito
    private ComparendoAgenteDTO agente;
    private String entidadAgenteTransito;

    // Inmovilizacion
    private ComparendoPatioDTO comparendoPatio;
    private String direccionInmovilizacionTexto;

    // Testigo
    private ComparendoPersonaDTO testigo;
    private String direccionTestigoTexto;

    // Embriaguez
    private Integer numeroPruebaAlcoholemia;

    // Evidencias
    private List<EvidenciaDTO> evidencias = new ArrayList<>();

    public DetalleComparendoFL() {
    }

    public DetalleComparendoFL(ComparendoDTO comparendoDTO) {
        this.comparendo = comparendoDTO;
    }

    public ConfiguracionInfraccionDTO getConfiguracionInfraccion() {
        return configuracionInfraccion;
    }

    public void setConfiguracionInfraccion(ConfiguracionInfraccionDTO configuracionInfraccion) {
        this.configuracionInfraccion = configuracionInfraccion;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionEmpresa() {
        return tipoIdentificacionEmpresa;
    }

    public void setTipoIdentificacionEmpresa(TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa) {
        this.tipoIdentificacionEmpresa = tipoIdentificacionEmpresa;
    }

    public ComparendoDTO getComparendo() {
        return comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public String getNomOrganismoTransito() {
        return nomOrganismoTransito;
    }

    public void setNomOrganismoTransito(String nomOrganismoTransito) {
        this.nomOrganismoTransito = nomOrganismoTransito;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public String getDireccionInfraccionTexto() {
        return direccionInfraccionTexto;
    }

    public void setDireccionInfraccionTexto(String direccionInfraccionTexto) {
        this.direccionInfraccionTexto = direccionInfraccionTexto;
    }

    public String getPaisDirInfraccion() {
        return paisDirInfraccion;
    }

    public void setPaisDirInfraccion(String paisDirInfraccion) {
        this.paisDirInfraccion = paisDirInfraccion;
    }

    public String getDepartamentoDirInfraccion() {
        return departamentoDirInfraccion;
    }

    public void setDepartamentoDirInfraccion(String departamentoDirInfraccion) {
        this.departamentoDirInfraccion = departamentoDirInfraccion;
    }

    public String getMunicipioDirInfraccion() {
        return municipioDirInfraccion;
    }

    public void setMunicipioDirInfraccion(String municipioDirInfraccion) {
        this.municipioDirInfraccion = municipioDirInfraccion;
    }

    public String getLocalidadDirInfraccion() {
        return localidadDirInfraccion;
    }

    public void setLocalidadDirInfraccion(String localidadDirInfraccion) {
        this.localidadDirInfraccion = localidadDirInfraccion;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public ComparendoVehiculoDTO getEmpresaVehiculo() {
        return empresaVehiculo;
    }

    public void setEmpresaVehiculo(ComparendoVehiculoDTO empresaVehiculo) {
        this.empresaVehiculo = empresaVehiculo;
    }

    public ComparendoPersonaDTO getEmpresaPersonaVehiculo() {
        return empresaPersonaVehiculo;
    }

    public void setEmpresaPersonaVehiculo(ComparendoPersonaDTO empresaPersonaVehiculo) {
        this.empresaPersonaVehiculo = empresaPersonaVehiculo;
    }

    public ComparendoPersonaDTO getInfractor() {
        return infractor;
    }

    public void setInfractor(ComparendoPersonaDTO infractor) {
        this.infractor = infractor;
    }

    public String getDireccionInfractorTexto() {
        return direccionInfractorTexto;
    }

    public void setDireccionInfractorTexto(String direccionInfractorTexto) {
        this.direccionInfractorTexto = direccionInfractorTexto;
    }

    public String getNomOrgLicenciaInfractor() {
        return nomOrgLicenciaInfractor;
    }

    public void setNomOrgLicenciaInfractor(String nomOrgLicenciaInfractor) {
        this.nomOrgLicenciaInfractor = nomOrgLicenciaInfractor;
    }

    public ComparendoPersonaDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(ComparendoPersonaDTO propietario) {
        this.propietario = propietario;
    }

    public String getNomOrgLicenciaTrans() {
        return nomOrgLicenciaTrans;
    }

    public void setNomOrgLicenciaTrans(String nomOrgLicenciaTrans) {
        this.nomOrgLicenciaTrans = nomOrgLicenciaTrans;
    }

    public ComparendoAgenteDTO getAgente() {
        return agente;
    }

    public void setAgente(ComparendoAgenteDTO agente) {
        this.agente = agente;
    }

    public String getEntidadAgenteTransito() {
        return entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(String entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public ComparendoPatioDTO getComparendoPatio() {
        return comparendoPatio;
    }

    public void setComparendoPatio(ComparendoPatioDTO comparendoPatio) {
        this.comparendoPatio = comparendoPatio;
    }

    public String getDireccionInmovilizacionTexto() {
        return direccionInmovilizacionTexto;
    }

    public void setDireccionInmovilizacionTexto(String direccionInmovilizacionTexto) {
        this.direccionInmovilizacionTexto = direccionInmovilizacionTexto;
    }

    public ComparendoPersonaDTO getTestigo() {
        return testigo;
    }

    public void setTestigo(ComparendoPersonaDTO testigo) {
        this.testigo = testigo;
    }

    public String getDireccionTestigoTexto() {
        return direccionTestigoTexto;
    }

    public void setDireccionTestigoTexto(String direccionTestigoTexto) {
        this.direccionTestigoTexto = direccionTestigoTexto;
    }

    public Integer getNumeroPruebaAlcoholemia() {
        return numeroPruebaAlcoholemia;
    }

    public void setNumeroPruebaAlcoholemia(Integer numeroPruebaAlcoholemia) {
        this.numeroPruebaAlcoholemia = numeroPruebaAlcoholemia;
    }

    public List<EvidenciaDTO> getEvidencias() {
        return evidencias;
    }

    public void setEvidencias(List<EvidenciaDTO> evidencias) {
        this.evidencias = evidencias;
    }

    public Date getFechaHoraInfraccion() {
        return fechaHoraInfraccion;
    }

    public void setFechaHoraInfraccion(Date fechaHoraInfraccion) {
        this.fechaHoraInfraccion = fechaHoraInfraccion;
    }

    public SeguimientoComparendoDTO getSeguimientoComparendoDTO() {
        return seguimientoComparendoDTO;
    }

    public void setSeguimientoComparendoDTO(SeguimientoComparendoDTO seguimientoComparendoDTO) {
        this.seguimientoComparendoDTO = seguimientoComparendoDTO;
    }

}
