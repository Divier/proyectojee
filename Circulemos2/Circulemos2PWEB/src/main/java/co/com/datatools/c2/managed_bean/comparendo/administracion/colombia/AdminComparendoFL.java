package co.com.datatools.c2.managed_bean.comparendo.administracion.colombia;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.comparendo.RespuestaValidacionVO;

/**
 * 
 * @author julio.pinzon
 * 
 */
public class AdminComparendoFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminComparendoFL";

    private ConfiguracionInfraccionDTO configuracionInfraccion;
    private TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa;
    private ProcesaComparendoDTO procesaComparendo;
    private String codigoPolca;
    private String nombreEntidadPolca;
    private Integer idTipoFormulario;
    // Errores del procesamiento
    private List<String> detallesProcesamiento;
    private String errorProcesamiento;
    private boolean existeError;

    private boolean mostrarWizard;
    private String pasoActual;

    private boolean editaDireccionInfraccion;
    private boolean editaDireccionInfractor;
    private boolean editaDireccionInmovilizacion;
    private boolean editaDireccionTestigo;

    // Encabezado
    private boolean polca;
    private String descripcionInfraccion;
    private String codOrganismoNumeroComparendo;
    private DireccionDTO direccionInfraccion;
    private String direccionInfraccionTexto;
    private String numeroComparendo;

    // Vehiculo
    private ProcesaComparendoPersonaDTO empresaVehiculo;
    private boolean placaRequerida;
    private boolean tipoDocEmpresaRequerido;
    private boolean placaVehiculoDeshabilitado;
    private boolean idVehiculoDeshabilitado;

    // Infractor
    private ProcesaComparendoPersonaDTO infractor;
    private boolean requiereInfractor;
    private boolean requiereTipoDocInfractor;
    private boolean esPropietario;
    private DireccionDTO direccionInfractor;
    private String direccionInfractorTexto;
    private boolean tipoIdJuridicoInfractor;

    // Propietario
    private ProcesaComparendoPersonaDTO propietario;
    private boolean requierePropietario;
    private boolean tipoIdJuridicoPropietario;

    // AgenteTransito
    private boolean requiereAgenteTransito;
    private boolean placaAgenteRequerida;
    private String nombreResponsable;

    // Inmovilizacion
    private boolean requiereInmovilizacion;
    private DireccionDTO direccionInmovilizacion;
    private String direccionInmovilizacionTexto;

    // Testigo
    private ProcesaComparendoPersonaDTO testigo;
    private boolean requiereTestigo;
    private DireccionDTO direccionTestigo;
    private String direccionTestigoTexto;

    // Embriaguez
    private boolean requiereEmbriaguez;

    // Evidencias
    private Integer idTipoEvidencia;
    private ProcesaEvidenciaDTO evidenciaSeleccionada;
    private List<ProcesaEvidenciaDTO> procesaEvidencias;
    private List<Integer> idEvidenciasEliminadas;

    private RespuestaValidacionVO respuestaValidacionVO;

    public AdminComparendoFL() {
        respuestaValidacionVO = new RespuestaValidacionVO();
    }

    public boolean isPolca() {
        return polca;
    }

    public void setPolca(boolean polca) {
        this.polca = polca;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public String getCodOrganismoNumeroComparendo() {
        return codOrganismoNumeroComparendo;
    }

    public void setCodOrganismoNumeroComparendo(String codOrganismoNumeroComparendo) {
        this.codOrganismoNumeroComparendo = codOrganismoNumeroComparendo;
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

    public boolean isRequiereAgenteTransito() {
        return requiereAgenteTransito;
    }

    public void setRequiereAgenteTransito(boolean requiereAgenteTransito) {
        this.requiereAgenteTransito = requiereAgenteTransito;
    }

    public boolean isRequiereInmovilizacion() {
        return requiereInmovilizacion;
    }

    public void setRequiereInmovilizacion(boolean requiereInmovilizacion) {
        this.requiereInmovilizacion = requiereInmovilizacion;
    }

    public boolean isRequiereEmbriaguez() {
        return requiereEmbriaguez;
    }

    public void setRequiereEmbriaguez(boolean requiereEmbriaguez) {
        this.requiereEmbriaguez = requiereEmbriaguez;
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

    public ProcesaComparendoPersonaDTO getTestigo() {
        return testigo;
    }

    public void setTestigo(ProcesaComparendoPersonaDTO testigo) {
        this.testigo = testigo;
    }

    public boolean isRequiereTestigo() {
        return requiereTestigo;
    }

    public void setRequiereTestigo(boolean requiereTestigo) {
        this.requiereTestigo = requiereTestigo;
    }

    public boolean isPlacaAgenteRequerida() {
        return placaAgenteRequerida;
    }

    public void setPlacaAgenteRequerida(boolean placaAgenteRequerida) {
        this.placaAgenteRequerida = placaAgenteRequerida;
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

    public List<Integer> getIdEvidenciasEliminadas() {
        if (idEvidenciasEliminadas == null)
            idEvidenciasEliminadas = new ArrayList<>();
        return idEvidenciasEliminadas;
    }

    public void setIdEvidenciasEliminadas(List<Integer> idEvidenciasEliminadas) {
        this.idEvidenciasEliminadas = idEvidenciasEliminadas;
    }

    public boolean isPlacaVehiculoDeshabilitado() {
        return placaVehiculoDeshabilitado;
    }

    public void setPlacaVehiculoDeshabilitado(boolean placaVehiculoDeshabilitado) {
        this.placaVehiculoDeshabilitado = placaVehiculoDeshabilitado;
    }

    public boolean isIdVehiculoDeshabilitado() {
        return idVehiculoDeshabilitado;
    }

    public void setIdVehiculoDeshabilitado(boolean idVehiculoDeshabilitado) {
        this.idVehiculoDeshabilitado = idVehiculoDeshabilitado;
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

    public DireccionDTO getDireccionInmovilizacion() {
        return direccionInmovilizacion;
    }

    public void setDireccionInmovilizacion(DireccionDTO direccionInmovilizacion) {
        this.direccionInmovilizacion = direccionInmovilizacion;
    }

    public DireccionDTO getDireccionTestigo() {
        return direccionTestigo;
    }

    public void setDireccionTestigo(DireccionDTO direccionTestigo) {
        this.direccionTestigo = direccionTestigo;
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

    public String getDireccionInmovilizacionTexto() {
        if (this.direccionInmovilizacion != null) {
            direccionInmovilizacionTexto = this.direccionInmovilizacion.toString();
        }
        return direccionInmovilizacionTexto;
    }

    public void setDireccionInmovilizacionTexto(String direccionInmovilizacionTexto) {
        this.direccionInmovilizacionTexto = direccionInmovilizacionTexto;
    }

    public String getDireccionTestigoTexto() {
        if (this.direccionTestigo != null) {
            direccionTestigoTexto = this.direccionTestigo.toString();
        }
        return direccionTestigoTexto;
    }

    public void setDireccionTestigoTexto(String direccionTestigoTexto) {
        this.direccionTestigoTexto = direccionTestigoTexto;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
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

    public boolean isEditaDireccionInmovilizacion() {
        return editaDireccionInmovilizacion;
    }

    public void setEditaDireccionInmovilizacion(boolean editaDireccionInmovilizacion) {
        this.editaDireccionInmovilizacion = editaDireccionInmovilizacion;
    }

    public boolean isEditaDireccionTestigo() {
        return editaDireccionTestigo;
    }

    public void setEditaDireccionTestigo(boolean editaDireccionTestigo) {
        this.editaDireccionTestigo = editaDireccionTestigo;
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

    public String getCodigoPolca() {
        return codigoPolca;
    }

    public void setCodigoPolca(String codigoPolca) {
        this.codigoPolca = codigoPolca;
    }

    public String getNombreEntidadPolca() {
        return nombreEntidadPolca;
    }

    public void setNombreEntidadPolca(String nombreEntidadPolca) {
        this.nombreEntidadPolca = nombreEntidadPolca;
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

    public boolean isTipoIdJuridicoInfractor() {
        return tipoIdJuridicoInfractor;
    }

    public void setTipoIdJuridicoInfractor(boolean tipoIdJuridicoInfractor) {
        this.tipoIdJuridicoInfractor = tipoIdJuridicoInfractor;
    }

    public boolean isTipoIdJuridicoPropietario() {
        return tipoIdJuridicoPropietario;
    }

    public void setTipoIdJuridicoPropietario(boolean tipoIdJuridicoPropietario) {
        this.tipoIdJuridicoPropietario = tipoIdJuridicoPropietario;
    }

    public RespuestaValidacionVO getRespuestaValidacionVO() {
        return respuestaValidacionVO;
    }

    public void setRespuestaValidacionVO(RespuestaValidacionVO respuestaValidacionVO) {
        this.respuestaValidacionVO = respuestaValidacionVO;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

}
