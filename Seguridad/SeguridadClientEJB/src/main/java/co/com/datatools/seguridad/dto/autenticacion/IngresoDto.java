package co.com.datatools.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;

/**
 * Almacena la trazabilidad de un intento de autenticacion
 * 
 * @author Felipe Martinez
 */
public class IngresoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date fechaInicio;
    private Date fechaCierre;
    private String ipIngreso;
    private String estadoIngreso;
    private String xmlActividadIngreso;
    private Integer idAplicacion;
    private String nombreAplicacion;
    private String login;

    private Integer idXsdHistorico;
    private String contenidoXsd;

    private List<InfoAutorizacion> actividadIngreso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getIpIngreso() {
        return ipIngreso;
    }

    public void setIpIngreso(String ipIngreso) {
        this.ipIngreso = ipIngreso;
    }

    public String getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(String estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public String getXmlActividadIngreso() {
        return xmlActividadIngreso;
    }

    public void setXmlActividadIngreso(String xmlActividadIngreso) {
        this.xmlActividadIngreso = xmlActividadIngreso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdXsdHistorico() {
        return idXsdHistorico;
    }

    public void setIdXsdHistorico(Integer idXsdHistorico) {
        this.idXsdHistorico = idXsdHistorico;
    }

    public String getContenidoXsd() {
        return contenidoXsd;
    }

    public void setContenidoXsd(String contenidoXsd) {
        this.contenidoXsd = contenidoXsd;
    }

    public List<InfoAutorizacion> getActividadIngreso() {
        return actividadIngreso;
    }

    public void setActividadIngreso(List<InfoAutorizacion> actividadIngreso) {
        this.actividadIngreso = actividadIngreso;
    }

    public Integer getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

}
