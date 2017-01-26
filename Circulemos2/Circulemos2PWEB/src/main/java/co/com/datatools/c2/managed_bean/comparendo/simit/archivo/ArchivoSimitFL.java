package co.com.datatools.c2.managed_bean.comparendo.simit.archivo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.util.date.UtilFecha;

/**
 * 
 */
public class ArchivoSimitFL implements Serializable {

    private static final long serialVersionUID = 1L;

    // Filtros consulta
    private Integer codigoOrganismo;
    private Date fechaInicial;
    private Date fechaInicialMax;
    private Date fechaFinal;
    private Date fechaFinalMin;

    // Resultado consulta
    private int id;
    private int candadRegistros;
    private String numeroOficio;
    private String usuario;
    private String idDocumento;
    private NotificacionSimitDTO notificacionSimit;

    // Resumen generar archivo
    private int cantidadRegistrosTotal;
    private boolean mostrarGenerarArchivo;
    private String nombreTipoDocumentoSimit;

    public ArchivoSimitFL() {
        Date ahora = DateUtils.addDays(UtilFecha.buildCalendar().getTime(), 0);
        fechaInicialMax = ahora;
        fechaFinal = ahora;
        mostrarGenerarArchivo = false;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicialMax() {
        return fechaInicialMax;
    }

    public void setFechaInicialMax(Date fechaInicialMax) {
        this.fechaInicialMax = fechaInicialMax;
    }

    public Date getFechaFinalMin() {
        return fechaFinalMin;
    }

    public void setFechaFinalMin(Date fechaFinalMin) {
        this.fechaFinalMin = fechaFinalMin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandadRegistros() {
        return candadRegistros;
    }

    public void setCandadRegistros(int candadRegistros) {
        this.candadRegistros = candadRegistros;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public NotificacionSimitDTO getNotificacionSimit() {
        return notificacionSimit;
    }

    public void setNotificacionSimit(NotificacionSimitDTO notificacionSimit) {
        this.notificacionSimit = notificacionSimit;
    }

    public int getCantidadRegistrosTotal() {
        return cantidadRegistrosTotal;
    }

    public void setCantidadRegistrosTotal(int cantidadRegistrosTotal) {
        this.cantidadRegistrosTotal = cantidadRegistrosTotal;
    }

    public boolean isMostrarGenerarArchivo() {
        return mostrarGenerarArchivo;
    }

    public void setMostrarGenerarArchivo(boolean mostrarGenerarArchivo) {
        this.mostrarGenerarArchivo = mostrarGenerarArchivo;
    }

    public String getNombreTipoDocumentoSimit() {
        return nombreTipoDocumentoSimit;
    }

    public void setNombreTipoDocumentoSimit(String nombreTipoDocumentoSimit) {
        this.nombreTipoDocumentoSimit = nombreTipoDocumentoSimit;
    }

}
