package co.com.datatools.c2.dto.comparendo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.util.EntidadDtoC2;
import co.com.datatools.util.date.UtilFecha;

/**
 * DTO de Comparendo No autogenerar, tiene datos adicionales
 * 
 * @author Generated
 * @version 3.0 - Wed Oct 07 10:48:52 COT 2015
 */
public class ComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long cicomparendo;
    private OrdenComparendoNacionalDTO ordenComparendoNacional;
    private TipoOrigenComparendoDTO tipoOrigen;
    private UsuarioPersonaDTO usuarioPersona;
    private MedioImposicionComparendoDTO medioImposicion;
    private TipoInfractorDTO tipoInfractor;
    private TipoNotificacionComparendoDTO tipoNotificacion;
    private Date fechaInfraccion;
    private Date horaInfraccion;
    private Date fechaModificacion;
    private DireccionDTO direccion;
    private Boolean retieneLicencia;
    private Boolean existeFugaInfractor;
    private InfraccionDTO infraccion;
    private RutaDTO ruta;
    private TipoComparendoDTO tipoComparendo;
    private GradoAlcoholemiaDTO gradoAlcoholemia;
    private Integer valorGradoAlcoholemia;
    private EstadoComparendoDTO estadoComparendo;
    private EstadoComparendoSimitDTO estadoComparendoSimit;
    private ResponsableFormularioDTO responsableFormulario;
    private Date fechaNotificacion;
    private Date fechaPruebaAlcoholemia;
    private Date fechaRegistro;
    private String observacionesInfractor;
    private Boolean niegaPruebaAlcoholemia;
    private String numeroPruebaAlcoholemia;
    private Integer numeroReincidencia;
    private Boolean carteraGenerada;
    private Date fechaGeneraCartera;
    private ComparendoAgenteDTO comparendoAgente;
    private ComparendoPatioDTO comparendoPatio;
    private ComparendoVehiculoDTO comparendoVehiculo;
    private List<ComparendoPersonaDTO> personaList;
    private List<EvidenciaDTO> evidenciaList;
    private List<ProcesaComparendoDTO> procesaComparendos;
    private List<TrazabilidadComparendoDTO> trazabilidadComparendoList;
    private List<ComparendoCarteraDTO> comparendoCarteraList;
    private List<ComparendoProcesoDTO> comparendoProcesoList;
    private Long idDocumentoNotificacion;
    private BigDecimal velocidadVehiculo;
    // ATRIBUTOS ADICIONALES
    private ComparendoPersonaDTO infractor;
    private ComparendoPersonaDTO propietario;
    private ComparendoPersonaDTO testigo;
    private ComparendoPersonaDTO empresa;
    // private Date fechaRecepcion;
    // private Date fechaDigitacion;
    private Boolean validaFechas;
    private TarifaInfraccionDTO tarifaInfraccion;
    private Long idResolucionSancionGenerada;
    private TipoAgenteImpositorDTO tipoAgenteImpositorDTO;
    private String numeroCitacion;
    private Long idFacturaAxis;
    private String placaAgenteTransito;
    private String secuenciaUnica;

    // --- Constructor
    public ComparendoDTO() {
    }

    public ComparendoDTO(Long cicomparendo) {
        this.cicomparendo = cicomparendo;

    }

    // --- Getters-Setters
    public Long getCicomparendo() {
        return this.cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public OrdenComparendoNacionalDTO getOrdenComparendoNacional() {
        return this.ordenComparendoNacional;
    }

    public void setOrdenComparendoNacional(OrdenComparendoNacionalDTO ordenComparendoNacional) {
        this.ordenComparendoNacional = ordenComparendoNacional;
    }

    public TipoOrigenComparendoDTO getTipoOrigen() {
        return this.tipoOrigen;
    }

    public void setTipoOrigen(TipoOrigenComparendoDTO tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public UsuarioPersonaDTO getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersonaDTO usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public MedioImposicionComparendoDTO getMedioImposicion() {
        return this.medioImposicion;
    }

    public void setMedioImposicion(MedioImposicionComparendoDTO medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public TipoInfractorDTO getTipoInfractor() {
        return this.tipoInfractor;
    }

    public void setTipoInfractor(TipoInfractorDTO tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public TipoNotificacionComparendoDTO getTipoNotificacion() {
        return this.tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacionComparendoDTO tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
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

    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public Boolean getRetieneLicencia() {
        return this.retieneLicencia;
    }

    public void setRetieneLicencia(Boolean retieneLicencia) {
        this.retieneLicencia = retieneLicencia;
    }

    public Boolean getExisteFugaInfractor() {
        return this.existeFugaInfractor;
    }

    public void setExisteFugaInfractor(Boolean existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
    }

    public InfraccionDTO getInfraccion() {
        return this.infraccion;
    }

    public void setInfraccion(InfraccionDTO infraccion) {
        this.infraccion = infraccion;
    }

    public RutaDTO getRuta() {
        return this.ruta;
    }

    public void setRuta(RutaDTO ruta) {
        this.ruta = ruta;
    }

    public TipoComparendoDTO getTipoComparendo() {
        return this.tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendoDTO tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public GradoAlcoholemiaDTO getGradoAlcoholemia() {
        return this.gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(GradoAlcoholemiaDTO gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Integer getValorGradoAlcoholemia() {
        return this.valorGradoAlcoholemia;
    }

    public void setValorGradoAlcoholemia(Integer valorGradoAlcoholemia) {
        this.valorGradoAlcoholemia = valorGradoAlcoholemia;
    }

    public EstadoComparendoDTO getEstadoComparendo() {
        return this.estadoComparendo;
    }

    public void setEstadoComparendo(EstadoComparendoDTO estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public ResponsableFormularioDTO getResponsableFormulario() {
        return this.responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaPruebaAlcoholemia() {
        return this.fechaPruebaAlcoholemia;
    }

    public void setFechaPruebaAlcoholemia(Date fechaPruebaAlcoholemia) {
        this.fechaPruebaAlcoholemia = fechaPruebaAlcoholemia;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacionesInfractor() {
        return this.observacionesInfractor;
    }

    public void setObservacionesInfractor(String observacionesInfractor) {
        this.observacionesInfractor = observacionesInfractor;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return this.niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public String getNumeroPruebaAlcoholemia() {
        return this.numeroPruebaAlcoholemia;
    }

    public void setNumeroPruebaAlcoholemia(String numeroPruebaAlcoholemia) {
        this.numeroPruebaAlcoholemia = numeroPruebaAlcoholemia;
    }

    public Integer getNumeroReincidencia() {
        return this.numeroReincidencia;
    }

    public void setNumeroReincidencia(Integer numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public ComparendoAgenteDTO getComparendoAgente() {
        return this.comparendoAgente;
    }

    public void setComparendoAgente(ComparendoAgenteDTO comparendoAgente) {
        this.comparendoAgente = comparendoAgente;
    }

    public ComparendoPatioDTO getComparendoPatio() {
        return this.comparendoPatio;
    }

    public void setComparendoPatio(ComparendoPatioDTO comparendoPatio) {
        this.comparendoPatio = comparendoPatio;
        if (this.comparendoPatio != null) {
            this.comparendoPatio.setId(this.getCicomparendo());
        }
    }

    public ComparendoVehiculoDTO getComparendoVehiculo() {
        return this.comparendoVehiculo;
    }

    public void setComparendoVehiculo(ComparendoVehiculoDTO comparendoVehiculo) {
        this.comparendoVehiculo = comparendoVehiculo;
        if (this.comparendoVehiculo != null) {
            this.comparendoVehiculo.setId(this.getCicomparendo());
        }
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ComparendoPersonaDTO> getPersonaList() {
        if (this.personaList == null) {
            this.personaList = new java.util.ArrayList<>(1);
        }
        return this.personaList;
    }

    public void setPersonaList(List<ComparendoPersonaDTO> personaList) {
        this.personaList = personaList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EvidenciaDTO> getEvidenciaList() {
        if (this.evidenciaList == null) {
            this.evidenciaList = new java.util.ArrayList<>(1);
        }
        return this.evidenciaList;
    }

    public void setEvidenciaList(List<EvidenciaDTO> evidenciaList) {
        this.evidenciaList = evidenciaList;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ProcesaComparendoDTO> getProcesaComparendos() {
        if (this.procesaComparendos == null) {
            this.procesaComparendos = new java.util.ArrayList<>(1);
        }
        return this.procesaComparendos;
    }

    public void setProcesaComparendos(List<ProcesaComparendoDTO> procesaComparendos) {
        this.procesaComparendos = procesaComparendos;
    }

    public Boolean getValidaFechas() {
        return validaFechas;
    }

    public void setValidaFechas(Boolean validaFechas) {
        this.validaFechas = validaFechas;
    }

    public ComparendoPersonaDTO getInfractor() {
        return infractor;
    }

    public void setInfractor(ComparendoPersonaDTO infractor) {
        this.infractor = infractor;
    }

    public ComparendoPersonaDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(ComparendoPersonaDTO propietario) {
        this.propietario = propietario;
    }

    public ComparendoPersonaDTO getTestigo() {
        return testigo;
    }

    public void setTestigo(ComparendoPersonaDTO testigo) {
        this.testigo = testigo;
    }

    public ComparendoPersonaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ComparendoPersonaDTO empresa) {
        this.empresa = empresa;
    }

    public List<TrazabilidadComparendoDTO> getTrazabilidadComparendoList() {
        return trazabilidadComparendoList;
    }

    public void setTrazabilidadComparendoList(List<TrazabilidadComparendoDTO> trazabilidadComparendoList) {
        this.trazabilidadComparendoList = trazabilidadComparendoList;
    }

    public Boolean getCarteraGenerada() {
        return carteraGenerada;
    }

    public void setCarteraGenerada(Boolean carteraGenerada) {
        this.carteraGenerada = carteraGenerada;
    }

    public Date getFechaGeneraCartera() {
        return fechaGeneraCartera;
    }

    public void setFechaGeneraCartera(Date fechaGeneraCartera) {
        this.fechaGeneraCartera = fechaGeneraCartera;
    }

    public EstadoComparendoSimitDTO getEstadoComparendoSimit() {
        return estadoComparendoSimit;
    }

    public void setEstadoComparendoSimit(EstadoComparendoSimitDTO estadoComparendoSimit) {
        this.estadoComparendoSimit = estadoComparendoSimit;
    }

    public List<ComparendoCarteraDTO> getComparendoCarteraList() {
        return comparendoCarteraList;
    }

    public void setComparendoCarteraList(List<ComparendoCarteraDTO> comparendoCarteraList) {
        this.comparendoCarteraList = comparendoCarteraList;
    }

    public TarifaInfraccionDTO getTarifaInfraccion() {
        return tarifaInfraccion;
    }

    public void setTarifaInfraccion(TarifaInfraccionDTO tarifaInfraccion) {
        this.tarifaInfraccion = tarifaInfraccion;
    }

    public Long getIdDocumentoNotificacion() {
        return idDocumentoNotificacion;
    }

    public void setIdDocumentoNotificacion(Long idDocumentoNotificacion) {
        this.idDocumentoNotificacion = idDocumentoNotificacion;
    }

    public Long getIdResolucionSancionGenerada() {
        return idResolucionSancionGenerada;
    }

    public void setIdResolucionSancionGenerada(Long idResolucionSancionGenerada) {
        this.idResolucionSancionGenerada = idResolucionSancionGenerada;
    }

    public TipoAgenteImpositorDTO getTipoAgenteImpositorDTO() {
        return tipoAgenteImpositorDTO;
    }

    public void setTipoAgenteImpositorDTO(TipoAgenteImpositorDTO tipoAgenteImpositorDTO) {
        this.tipoAgenteImpositorDTO = tipoAgenteImpositorDTO;
    }

    public BigDecimal getVelocidadVehiculo() {
        return velocidadVehiculo;
    }

    public void setVelocidadVehiculo(BigDecimal velocidadVehiculo) {
        this.velocidadVehiculo = velocidadVehiculo;
    }

    public Long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public List<ComparendoProcesoDTO> getComparendoProcesoList() {
        return comparendoProcesoList;
    }

    public void setComparendoProcesoList(List<ComparendoProcesoDTO> comparendoProcesoList) {
        this.comparendoProcesoList = comparendoProcesoList;
    }

    public Date getFechaHoraImposicion() {
        return UtilFecha.setHoraFecha(fechaInfraccion, horaInfraccion);
    }

    public String getPlacaAgenteTransito() {
        return placaAgenteTransito;
    }

    public void setPlacaAgenteTransito(String placaAgenteTransito) {
        this.placaAgenteTransito = placaAgenteTransito;
    }

    public String getSecuenciaUnica() {
        return secuenciaUnica;
    }

    public void setSecuenciaUnica(String secuenciaUnica) {
        this.secuenciaUnica = secuenciaUnica;
    }
}