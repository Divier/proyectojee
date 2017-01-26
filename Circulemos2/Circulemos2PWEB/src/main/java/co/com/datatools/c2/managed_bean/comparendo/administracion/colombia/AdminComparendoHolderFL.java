package co.com.datatools.c2.managed_bean.comparendo.administracion.colombia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de comparendos colombia
 * 
 * @author julio.pinzon
 * 
 */
public class AdminComparendoHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminComparendoHolderFL";

    private ConfiguracionInfraccionDTO configuracionInfraccion;
    private TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa;
    private ComparendoDTO comparendo;
    private OrdenComparendoNacionalDTO ordenComparendoNacional;
    private Date fechaImposicionInicial;
    private Date fechaImposicionFinal;
    private String codigoPolca;
    private boolean polca;
    private String codOrganismoNumeroComparendo;
    private String numeroComparendo;
    private String numeroCitacion;
    private ProcesaComparendoPersonaDTO infractor;
    private ProcesaComparendoPersonaDTO propietario;
    private Long numComparendo;
    private Integer idTipoIdentificacionPersona;
    private String numeroDocumentoInfractor;
    private Integer anioCitacion;

    /**
     * listado de relaciones consultadas
     */
    private List<ComparendoDTO> lstComparendos;

    /**
     * Estado del seguimiento seleccionado
     */
    private ComparendoDTO comparendoSeleccionado;

    public AdminComparendoHolderFL() {
        comparendo = new ComparendoDTO();
        ordenComparendoNacional = new OrdenComparendoNacionalDTO();
        ordenComparendoNacional.setOrganismoTransito(new OrganismoTransitoDTO());
        comparendo.setOrdenComparendoNacional(ordenComparendoNacional);
        comparendo.setComparendoVehiculo(new ComparendoVehiculoDTO());
        comparendo.setInfractor(new ComparendoPersonaDTO());
        comparendo.getInfractor().setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        comparendo.setPropietario(new ComparendoPersonaDTO());
        comparendo.getPropietario().setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        comparendo.setComparendoAgente(new ComparendoAgenteDTO());
        comparendo.getComparendoAgente().setTipoIdentificacionPersona(new TipoIdentificacionPersonaDTO());
        comparendoSeleccionado = new ComparendoDTO();
        lstComparendos = new ArrayList<>();
    }

    public boolean isPolca() {
        return polca;
    }

    public void setPolca(boolean polca) {
        this.polca = polca;
    }

    public String getCodOrganismoNumeroComparendo() {
        return codOrganismoNumeroComparendo;
    }

    public void setCodOrganismoNumeroComparendo(String codOrganismoNumeroComparendo) {
        this.codOrganismoNumeroComparendo = codOrganismoNumeroComparendo;
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

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public List<ComparendoDTO> getLstComparendos() {
        return lstComparendos;
    }

    public void setLstComparendos(List<ComparendoDTO> lstComparendos) {
        this.lstComparendos = lstComparendos;
    }

    public ComparendoDTO getComparendoSeleccionado() {
        return comparendoSeleccionado;
    }

    public void setComparendoSeleccionado(ComparendoDTO comparendoSeleccionado) {
        this.comparendoSeleccionado = comparendoSeleccionado;
    }

    public Date getFechaImposicionInicial() {
        return fechaImposicionInicial;
    }

    public void setFechaImposicionInicial(Date fechaImposicionInicial) {
        this.fechaImposicionInicial = fechaImposicionInicial;
    }

    public Date getFechaImposicionFinal() {
        return fechaImposicionFinal;
    }

    public void setFechaImposicionFinal(Date fechaImposicionFinal) {
        this.fechaImposicionFinal = fechaImposicionFinal;
    }

    public ComparendoDTO getComparendo() {
        return comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public String getCodigoPolca() {
        return codigoPolca;
    }

    public void setCodigoPolca(String codigoPolca) {
        this.codigoPolca = codigoPolca;
    }

    public OrdenComparendoNacionalDTO getOrdenComparendoNacional() {
        return ordenComparendoNacional;
    }

    public void setOrdenComparendoNacional(OrdenComparendoNacionalDTO ordenComparendoNacional) {
        this.ordenComparendoNacional = ordenComparendoNacional;
    }

    public Long getNumComparendo() {
        return numComparendo;
    }

    public void setNumComparendo(Long numComparendo) {
        this.numComparendo = numComparendo;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Integer getIdTipoIdentificacionPersona() {
        return idTipoIdentificacionPersona;
    }

    public void setIdTipoIdentificacionPersona(Integer tipoIdentificacionPersona) {
        this.idTipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public Integer getAnioCitacion() {
        return anioCitacion;
    }

    public void setAnioCitacion(Integer anioCitacion) {
        this.anioCitacion = anioCitacion;
    }

}