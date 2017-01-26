package co.com.datatools.c2.managed_bean.comparendo.administracion.peru;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author julio.pinzon
 * 
 */
public class AdminComparendoPeruFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminComparendoPeruFL";

    private ConfiguracionInfraccionDTO configuracionInfraccion;
    private TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa;
    private ProcesaComparendoDTO procesaComparendo;
    private Integer idTipoFormulario;
    // Errores del procesamiento
    private List<String> detallesProcesamiento;
    private String errorProcesamiento;
    private boolean existeError;
    private Integer tipoComparendoPeru;

    private boolean mostrarWizard;
    private String pasoActual;
    private Integer maximoAnio;
    private boolean papeleta;
    private String sufijoComparendo;

    private boolean editaDireccionInfraccion;
    private boolean editaDireccionInfractor;
    private boolean editaDireccionEmpresa;
    private boolean editaDireccionPropietario;

    // Encabezado
    private String descripcionInfraccion;
    private DireccionDTO direccionInfraccion;
    private String direccionInfraccionTexto;

    // Vehiculo
    private boolean placaRequerida;

    // Infractor
    private ProcesaComparendoPersonaDTO infractor;
    private boolean requiereInfractor;
    private boolean requiereTipoDocInfractor;
    private boolean esPropietario;
    private DireccionDTO direccionInfractor;
    private String direccionInfractorTexto;

    // Propietario
    private ProcesaComparendoPersonaDTO propietario;
    private boolean requierePropietario;
    private DireccionDTO direccionPropietario;
    private String direccionPropietarioTexto;
    private boolean tipoIdJuridicoPropietario;

    // AgenteTransito
    private Date fechaInicioVigenciaAgente;
    private Date fechaFinVigenciaAgente;

    // Empresa
    private ProcesaComparendoPersonaDTO empresaVehiculo;
    private boolean tipoDocEmpresaRequerido;
    private DireccionDTO direccionEmpresa;
    private String direccionEmpresaTexto;
    private Long idEmpresaTransporte;

    // Evidencias
    private Integer idTipoEvidencia;
    private ProcesaEvidenciaDTO evidenciaSeleccionada;
    private List<ProcesaEvidenciaDTO> procesaEvidencias;

    public AdminComparendoPeruFL() {
        // TODO Auto-generated constructor stub
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public boolean isMostrarWizard() {
        return mostrarWizard;
    }

    public void setMostrarWizard(boolean mostrarWizard) {
        this.mostrarWizard = mostrarWizard;
    }

    public ConfiguracionInfraccionDTO getConfiguracionInfraccion() {
        return configuracionInfraccion;
    }

    public void setConfiguracionInfraccion(ConfiguracionInfraccionDTO configuracionInfraccion) {
        this.configuracionInfraccion = configuracionInfraccion;
    }

    public boolean isPlacaRequerida() {
        return placaRequerida;
    }

    public void setPlacaRequerida(boolean placaRequerida) {
        this.placaRequerida = placaRequerida;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionEmpresa() {
        return tipoIdentificacionEmpresa;
    }

    public void setTipoIdentificacionEmpresa(TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa) {
        this.tipoIdentificacionEmpresa = tipoIdentificacionEmpresa;
    }

    public boolean isTipoDocEmpresaRequerido() {
        return tipoDocEmpresaRequerido;
    }

    public void setTipoDocEmpresaRequerido(boolean tipoDocEmpresaRequerido) {
        this.tipoDocEmpresaRequerido = tipoDocEmpresaRequerido;
    }

    public boolean isRequiereInfractor() {
        return requiereInfractor;
    }

    public void setRequiereInfractor(boolean requiereInfractor) {
        this.requiereInfractor = requiereInfractor;
    }

    public boolean isEsPropietario() {
        return esPropietario;
    }

    public void setEsPropietario(boolean esPropietario) {
        this.esPropietario = esPropietario;
    }

    public boolean isRequierePropietario() {
        return requierePropietario;
    }

    public void setRequierePropietario(boolean requierePropietario) {
        this.requierePropietario = requierePropietario;
    }

    public ProcesaComparendoPersonaDTO getInfractor() {
        return infractor;
    }

    public void setInfractor(ProcesaComparendoPersonaDTO infractor) {
        this.infractor = infractor;
    }

    public ProcesaComparendoPersonaDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(ProcesaComparendoPersonaDTO propietario) {
        this.propietario = propietario;
    }

    public ProcesaComparendoDTO getProcesaComparendo() {
        return procesaComparendo;
    }

    public void setProcesaComparendo(ProcesaComparendoDTO procesaComparendo) {
        this.procesaComparendo = procesaComparendo;
    }

    public Integer getIdTipoEvidencia() {
        return idTipoEvidencia;
    }

    public void setIdTipoEvidencia(Integer idTipoEvidencia) {
        this.idTipoEvidencia = idTipoEvidencia;
    }

    public ProcesaComparendoPersonaDTO getEmpresaVehiculo() {
        return empresaVehiculo;
    }

    public void setEmpresaVehiculo(ProcesaComparendoPersonaDTO empresaVehiculo) {
        this.empresaVehiculo = empresaVehiculo;
    }

    public List<ProcesaEvidenciaDTO> getProcesaEvidencias() {
        return procesaEvidencias;
    }

    public void setProcesaEvidencias(List<ProcesaEvidenciaDTO> procesaEvidencias) {
        this.procesaEvidencias = procesaEvidencias;
    }

    public DireccionDTO getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(DireccionDTO direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public DireccionDTO getDireccionInfractor() {
        return direccionInfractor;
    }

    public void setDireccionInfractor(DireccionDTO direccionInfractor) {
        this.direccionInfractor = direccionInfractor;
    }

    public DireccionDTO getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(DireccionDTO direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public boolean isRequiereTipoDocInfractor() {
        return requiereTipoDocInfractor;
    }

    public void setRequiereTipoDocInfractor(boolean requiereTipoDocInfractor) {
        this.requiereTipoDocInfractor = requiereTipoDocInfractor;
    }

    public String getDireccionInfraccionTexto() {
        if (this.direccionInfraccion != null) {
            direccionInfraccionTexto = this.direccionInfraccion.toString();
        }
        return direccionInfraccionTexto;
    }

    public void setDireccionInfraccionTexto(String direccionInfraccionTexto) {
        this.direccionInfraccionTexto = direccionInfraccionTexto;
    }

    public String getDireccionInfractorTexto() {
        if (this.direccionInfractor != null) {
            direccionInfractorTexto = this.direccionInfractor.toString();
        }
        return direccionInfractorTexto;
    }

    public void setDireccionInfractorTexto(String direccionInfractorTexto) {
        this.direccionInfractorTexto = direccionInfractorTexto;
    }

    public String getDireccionEmpresaTexto() {
        if (this.direccionEmpresa != null) {
            direccionEmpresaTexto = this.direccionEmpresa.toString();
        }
        return direccionEmpresaTexto;
    }

    public void setDireccionEmpresaTexto(String direccionEmpresaTexto) {
        this.direccionEmpresaTexto = direccionEmpresaTexto;
    }

    public boolean isEditaDireccionInfraccion() {
        return editaDireccionInfraccion;
    }

    public void setEditaDireccionInfraccion(boolean editaDireccionInfraccion) {
        this.editaDireccionInfraccion = editaDireccionInfraccion;
    }

    public boolean isEditaDireccionInfractor() {
        return editaDireccionInfractor;
    }

    public void setEditaDireccionInfractor(boolean editaDireccionInfractor) {
        this.editaDireccionInfractor = editaDireccionInfractor;
    }

    public boolean isEditaDireccionEmpresa() {
        return editaDireccionEmpresa;
    }

    public void setEditaDireccionEmpresa(boolean editaDireccionEmpresa) {
        this.editaDireccionEmpresa = editaDireccionEmpresa;
    }

    public ProcesaEvidenciaDTO getEvidenciaSeleccionada() {
        return evidenciaSeleccionada;
    }

    public void setEvidenciaSeleccionada(ProcesaEvidenciaDTO evidenciaSeleccionada) {
        this.evidenciaSeleccionada = evidenciaSeleccionada;
    }

    public String getPasoActual() {
        return pasoActual;
    }

    public void setPasoActual(String pasoActual) {
        this.pasoActual = pasoActual;
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public List<String> getDetallesProcesamiento() {
        return detallesProcesamiento;
    }

    public void setDetallesProcesamiento(List<String> detallesProcesamiento) {
        this.detallesProcesamiento = detallesProcesamiento;
    }

    public String getErrorProcesamiento() {
        return errorProcesamiento;
    }

    public void setErrorProcesamiento(String errorProcesamiento) {
        this.errorProcesamiento = errorProcesamiento;
    }

    public boolean isExisteError() {
        return existeError;
    }

    public void setExisteError(boolean existeError) {
        this.existeError = existeError;
    }

    public Integer getTipoComparendoPeru() {
        return tipoComparendoPeru;
    }

    public void setTipoComparendoPeru(Integer tipoComparendoPeru) {
        this.tipoComparendoPeru = tipoComparendoPeru;
    }

    public DireccionDTO getDireccionPropietario() {
        return direccionPropietario;
    }

    public void setDireccionPropietario(DireccionDTO direccionPropietario) {
        this.direccionPropietario = direccionPropietario;
    }

    public String getDireccionPropietarioTexto() {
        if (this.direccionPropietario != null) {
            direccionPropietarioTexto = this.direccionPropietario.toString();
        }
        return direccionPropietarioTexto;
    }

    public void setDireccionPropietarioTexto(String direccionPropietarioTexto) {
        this.direccionPropietarioTexto = direccionPropietarioTexto;
    }

    public boolean isEditaDireccionPropietario() {
        return editaDireccionPropietario;
    }

    public void setEditaDireccionPropietario(boolean editaDireccionPropietario) {
        this.editaDireccionPropietario = editaDireccionPropietario;
    }

    public Integer getMaximoAnio() {
        return maximoAnio;
    }

    public void setMaximoAnio(Integer maximoAnio) {
        this.maximoAnio = maximoAnio;
    }

    public boolean isPapeleta() {
        return papeleta;
    }

    public void setPapeleta(boolean papeleta) {
        this.papeleta = papeleta;
    }

    public Date getFechaInicioVigenciaAgente() {
        return fechaInicioVigenciaAgente;
    }

    public void setFechaInicioVigenciaAgente(Date fechaInicioVigenciaAgente) {
        this.fechaInicioVigenciaAgente = fechaInicioVigenciaAgente;
    }

    public Date getFechaFinVigenciaAgente() {
        return fechaFinVigenciaAgente;
    }

    public void setFechaFinVigenciaAgente(Date fechaFinVigenciaAgente) {
        this.fechaFinVigenciaAgente = fechaFinVigenciaAgente;
    }

    public String getSufijoComparendo() {
        return sufijoComparendo;
    }

    public void setSufijoComparendo(String sufijoComparendo) {
        this.sufijoComparendo = sufijoComparendo;
    }

    public Long getIdEmpresaTransporte() {
        return idEmpresaTransporte;
    }

    public void setIdEmpresaTransporte(Long idEmpresaTransporte) {
        this.idEmpresaTransporte = idEmpresaTransporte;
    }

    public boolean isTipoIdJuridicoPropietario() {
        return tipoIdJuridicoPropietario;
    }

    public void setTipoIdJuridicoPropietario(boolean tipoIdJuridicoPropietario) {
        this.tipoIdJuridicoPropietario = tipoIdJuridicoPropietario;
    }

}
