package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.dto.PruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo.RegistroTablaVO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de registro de pruebas
 * 
 * @author divier.casas 2016-06-29
 * 
 */
public class RegistrarPruebaFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "registrarPruebaFL";

    private SolicitudPruebasBackDTO solicitud;
    private List<RegistroTablaVO<ImpulsoPruebaDTO>> listaImpulsos;
    private RegistroTablaVO<ImpulsoPruebaDTO> impulsoSeleccionado;
    private List<RegistroTablaVO<PruebaDTO>> listaPruebas;
    private RegistroTablaVO<PruebaDTO> pruebaSeleccionada;
    private Long idProceso;
    private Long cicomparendo;
    private boolean modoConsulta;
    private List<SolicitudPruebasBackDTO> solicitudes;
    private SolicitudPruebasBackDTO solicitudSeleccionada;

    public RegistrarPruebaFL() {
    }

    public boolean isModoConsulta() {
        return modoConsulta;
    }

    public void setModoConsulta(boolean modoConsulta) {
        this.modoConsulta = modoConsulta;
    }

    public SolicitudPruebasBackDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudPruebasBackDTO solicitud) {
        this.solicitud = solicitud;
    }

    public List<RegistroTablaVO<ImpulsoPruebaDTO>> getListaImpulsos() {
        return listaImpulsos;
    }

    public void setListaImpulsos(List<RegistroTablaVO<ImpulsoPruebaDTO>> listaImpulsos) {
        this.listaImpulsos = listaImpulsos;
    }

    public RegistroTablaVO<ImpulsoPruebaDTO> getImpulsoSeleccionado() {
        return impulsoSeleccionado;
    }

    public void setImpulsoSeleccionado(RegistroTablaVO<ImpulsoPruebaDTO> impulsoSeleccionado) {
        this.impulsoSeleccionado = impulsoSeleccionado;
    }

    public List<RegistroTablaVO<PruebaDTO>> getListaPruebas() {
        return listaPruebas;
    }

    public void setListaPruebas(List<RegistroTablaVO<PruebaDTO>> listaPruebas) {
        this.listaPruebas = listaPruebas;
    }

    public RegistroTablaVO<PruebaDTO> getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setPruebaSeleccionada(RegistroTablaVO<PruebaDTO> pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public List<SolicitudPruebasBackDTO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudPruebasBackDTO> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public SolicitudPruebasBackDTO getSolicitudSeleccionada() {
        return solicitudSeleccionada;
    }

    public void setSolicitudSeleccionada(SolicitudPruebasBackDTO solicitudSeleccionada) {
        this.solicitudSeleccionada = solicitudSeleccionada;
    }

}