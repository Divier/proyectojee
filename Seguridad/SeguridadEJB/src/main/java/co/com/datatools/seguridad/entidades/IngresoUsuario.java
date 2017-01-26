package co.com.datatools.seguridad.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Registro de autenticaciones realizadas por un usuario y trazabilidad de las actividades ejecutadas
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "ingreso_usuario")
@NamedQueries({
        @NamedQuery(name = "IngresoUsuario.findAll", query = "SELECT i FROM IngresoUsuario i"),
        @NamedQuery(
                name = "IngresoUsuario.ingresoByUsuario",
                query = "SELECT i FROM IngresoUsuario i WHERE i.usuario.idUsuario = :idUsuario"),
        @NamedQuery(
                name = "IngresoUsuario.conteoIngresosByUsuario",
                query = "SELECT COUNT(i.idIngresoUsuario) FROM IngresoUsuario i WHERE i.usuario.login = :login AND i.estadoIngreso.idEstadoIngreso=:idEstadoIngreso AND i.fechaInicio BETWEEN :fechaInicio AND :fechaFin"),
        @NamedQuery(
                name = "IngresoUsuario.ingresoExitosoByUsuario",
                query = "SELECT i FROM IngresoUsuario i WHERE i.idIngresoUsuario=(SELECT MAX(imax.idIngresoUsuario) FROM IngresoUsuario imax  WHERE imax.usuario.login = :login AND imax.estadoIngreso.idEstadoIngreso=:idEstadoIngreso AND imax.fechaInicio BETWEEN :fechaInicio AND :fechaFin)"),
        @NamedQuery(
                name = "IngresoUsuario.ingresosAbiertosByUsuario",
                query = "SELECT i FROM IngresoUsuario i WHERE i.usuario.login = :login AND i.estadoIngreso.idEstadoIngreso=:idEstadoIngreso AND i.aplicacion.idAplicacion= :idApp AND i.fechaCierre IS NULL"),
        @NamedQuery(
                name = "IngresoUsuario.ultimoIngresoAbiertoByUsuario",
                query = "SELECT i FROM IngresoUsuario i WHERE i.idIngresoUsuario=(SELECT MAX(imax.idIngresoUsuario) FROM IngresoUsuario imax  WHERE imax.usuario.login = :login AND imax.estadoIngreso.idEstadoIngreso=:idEstadoIngreso)"), })
public class IngresoUsuario implements EntidadC2 {

    private static final long serialVersionUID = 1L;
    public static final String SQ_INGRESO_BY_USUARIO = "IngresoUsuario.ingresoByUsuario";
    public static final String SQ_CONTEO_INGRESOS_BY_USUARIO_FECHAS = "IngresoUsuario.conteoIngresosByUsuario";
    public static final String SQ_CONTEO_ULTIMO_INGRESO_BY_USUARIO_FECHAS = "IngresoUsuario.ingresoExitosoByUsuario";
    public static final String SQ_INGRESOS_ABIERTOS_BY_USUARIO = "IngresoUsuario.ingresosAbiertosByUsuario";
    public static final String SQ_ULTIMO_INGRESO_BY_USUARIO = "IngresoUsuario.ultimoIngresoAbiertoByUsuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso_usuario")
    private Integer idIngresoUsuario;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ip_equipo")
    private String ipEquipo;

    @Lob
    // @Type(type = "org.hibernate.type.TextType")
    @Size(max = 65535)
    @Column(name = "xml_actividad_ingreso")
    private String xmlActividadIngreso;

    @JoinColumn(name = "id_xsd", referencedColumnName = "id_xsd_historico")
    @ManyToOne(fetch = FetchType.LAZY)
    private XsdHistorico xsdHistorico;

    @JoinColumn(name = "id_estado_ingreso", referencedColumnName = "id_estado_ingreso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoIngreso estadoIngreso;

    @JoinColumn(name = "id_aplicacion", referencedColumnName = "id_aplicacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aplicacion aplicacion;

    @JoinTable(name = "ingreso_usuario_rol", joinColumns = { @JoinColumn(
            name = "id_ingreso_usuario",
            referencedColumnName = "id_ingreso_usuario") }, inverseJoinColumns = { @JoinColumn(
            name = "id_rol",
            referencedColumnName = "id_rol") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rol> rolList;

    public IngresoUsuario() {
    }

    public Integer getIdIngresoUsuario() {
        return idIngresoUsuario;
    }

    public void setIdIngresoUsuario(Integer idIngresoUsuario) {
        this.idIngresoUsuario = idIngresoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getIpEquipo() {
        return ipEquipo;
    }

    public void setIpEquipo(String ipEquipo) {
        this.ipEquipo = ipEquipo;
    }

    public String getXmlActividadIngreso() {
        return xmlActividadIngreso;
    }

    public void setXmlActividadIngreso(String xmlActividadIngreso) {
        this.xmlActividadIngreso = xmlActividadIngreso;
    }

    public XsdHistorico getXsdHistorico() {
        return xsdHistorico;
    }

    public void setXsdHistorico(XsdHistorico xsdHistorico) {
        this.xsdHistorico = xsdHistorico;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public EstadoIngreso getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(EstadoIngreso estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

}
