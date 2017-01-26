package co.com.datatools.seguridad.entidades;

import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.Size;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Registros de historicos/cambios realizados a un usuario
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "historico_usuario")
@NamedQueries({
        @NamedQuery(name = "HistoricoUsuario.findAll", query = "SELECT h FROM HistoricoUsuario h"),
        @NamedQuery(
                name = "HistoricoUsuario.findUltimoByUsuario",
                query = "SELECT h FROM HistoricoUsuario h WHERE h.idHistoricoUsuario =(SELECT MAX(h1.idHistoricoUsuario) FROM HistoricoUsuario h1 WHERE h1.usuario.idUsuario =:idUsuario)"),
        @NamedQuery(
                name = "HistoricoUsuario.findByRol",
                query = "SELECT h FROM HistoricoUsuario h JOIN h.rolList rh WHERE rh.idRol=:idRol"),
        @NamedQuery(
                name = "HistoricoUsuario.findAllByUsuario",
                query = "SELECT h FROM HistoricoUsuario h WHERE h.usuario.idUsuario=:idUsuario ORDER BY h.fechaInicio DESC"),
        @NamedQuery(
                name = "HistoricoUsuario.findPwByUsuario",
                query = "SELECT DISTINCT(h.password), h.fechaInicio  FROM HistoricoUsuario h WHERE h.usuario.idUsuario=:idUsuario ORDER BY h.fechaInicio DESC"),
        @NamedQuery(
                name = "HistoricoUsuario.findPrimerHistoricoByUsuario",
                query = "SELECT h FROM HistoricoUsuario h WHERE h.idHistoricoUsuario=(SELECT MIN(hist.idHistoricoUsuario) FROM HistoricoUsuario hist WHERE hist.usuario.idUsuario=:idUsuario)") })
public class HistoricoUsuario implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_HISTORICO_ULTIMO_BY_USUARIO = "HistoricoUsuario.findUltimoByUsuario";
    public static final String SQ_HISTORICO_BY_ROL = "HistoricoUsuario.findByRol";
    public static final String SQ_HISTORICO_PASSWORD_BY_USUARIO = "HistoricoUsuario.findPwByUsuario";
    public static final String SQ_HISTORICO_ALL_BY_USUARIO = "HistoricoUsuario.findAllByUsuario";
    public static final String SQ_HISTORICO_CREACION_BY_USUARIO = "HistoricoUsuario.findPrimerHistoricoByUsuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_usuario")
    private Long idHistoricoUsuario;

    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_final")
    private Date fechaFinal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifica_password")
    private Date fechaModificaPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "historico_rol_usuario",
            joinColumns = { @JoinColumn(name = "id_historico_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_rol") })
    private List<Rol> rolList;

    @Lob
    // @Type(type = "org.hibernate.type.TextType")
    @Size(min = 1, max = 65535)
    @Column(name = "xml_historico")
    private String xmlHistorico;

    @JoinColumn(name = "id_xsd_historico", referencedColumnName = "id_xsd_historico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private XsdHistorico xsdHistorico;

    @Column(name = "descripcion_cambio")
    private String descripcionCambio;

    @Column(name = "usuario_cambio")
    private String usuarioCambio;

    public HistoricoUsuario() {
    }

    public Long getIdHistoricoUsuario() {
        return idHistoricoUsuario;
    }

    public void setIdHistoricoUsuario(Long idHistoricoUsuario) {
        this.idHistoricoUsuario = idHistoricoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaModificaPassword() {
        return fechaModificaPassword;
    }

    public void setFechaModificaPassword(Date fechaModificaPassword) {
        this.fechaModificaPassword = fechaModificaPassword;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public String getXmlHistorico() {
        return xmlHistorico;
    }

    public void setXmlHistorico(String xmlHistorico) {
        this.xmlHistorico = xmlHistorico;
    }

    public XsdHistorico getXsdHistorico() {
        return xsdHistorico;
    }

    public void setXsdHistorico(XsdHistorico xsdHistorico) {
        this.xsdHistorico = xsdHistorico;
    }

    public String getDescripcionCambio() {
        return descripcionCambio;
    }

    public void setDescripcionCambio(String descripcionCambio) {
        this.descripcionCambio = descripcionCambio;
    }

    public String getUsuarioCambio() {
        return usuarioCambio;
    }

    public void setUsuarioCambio(String usuarioCambio) {
        this.usuarioCambio = usuarioCambio;
    }

}
