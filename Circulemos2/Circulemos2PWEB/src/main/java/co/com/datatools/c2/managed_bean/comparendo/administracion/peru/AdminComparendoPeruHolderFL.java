package co.com.datatools.c2.managed_bean.comparendo.administracion.peru;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de comparendos colombia
 * 
 * @author julio.pinzon
 * 
 */
public class AdminComparendoPeruHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminComparendoPeruHolderFL";

    private ComparendoDTO comparendo;
    private OrdenComparendoNacionalDTO ordenComparendoNacional;
    private Date fechaImposicionInicial;
    private Date fechaImposicionFinal;
    private String codigoPolca;
    private Integer tipoComparendoPeru;
    private String sufijoComparendo;
    private boolean polca;
    private String numeroComparendo;
    private ProcesaComparendoPersonaDTO infractor;
    private ProcesaComparendoPersonaDTO propietario;
    private Integer tipoActa;
    private Integer tipoPapeleta;

    /**
     * listado de relaciones consultadas
     */
    private List<ComparendoDTO> lstComparendos;

    /**
     * Estado del seguimiento seleccionado
     */
    private ComparendoDTO comparendoSeleccionado;

    public AdminComparendoPeruHolderFL() {
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
        setTipoActa(EnumTipoComparendo.ACTA_CONTROL.getCodigo());
        setTipoPapeleta(EnumTipoComparendo.PAPELETA.getCodigo());
    }

    public boolean isPolca() {
        return polca;
    }

    public void setPolca(boolean polca) {
        this.polca = polca;
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

    public Integer getTipoComparendoPeru() {
        return tipoComparendoPeru;
    }

    public void setTipoComparendoPeru(Integer tipoComparendoPeru) {
        this.tipoComparendoPeru = tipoComparendoPeru;
    }

    public Integer getTipoActa() {
        return tipoActa;
    }

    public void setTipoActa(Integer tipoActa) {
        this.tipoActa = tipoActa;
    }

    public Integer getTipoPapeleta() {
        return tipoPapeleta;
    }

    public void setTipoPapeleta(Integer tipoPapeleta) {
        this.tipoPapeleta = tipoPapeleta;
    }

    public String getSufijoComparendo() {
        return sufijoComparendo;
    }

    public void setSufijoComparendo(String sufijoComparendo) {
        this.sufijoComparendo = sufijoComparendo;
    }
}