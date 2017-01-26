package co.com.datatools.seguridad.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Registros de historicos/cambios realizados a un rol
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "historico_rol")
@NamedQueries({
        @NamedQuery(name = "HistoricoRol.findAll", query = "SELECT h FROM HistoricoRol h"),
        @NamedQuery(
                name = "HistoricoRol.findUltimoByRol",
                query = "SELECT h FROM HistoricoRol h WHERE h.idHistoricoRol =(SELECT MAX(h1.idHistoricoRol) FROM HistoricoRol h1 WHERE h1.rol.idRol =:idRol)"),
        @NamedQuery(
                name = "HistoricoRol.findUltimoByRolFecha",
                query = "SELECT h FROM HistoricoRol h WHERE h.idHistoricoRol =(SELECT MAX(h1.idHistoricoRol) FROM HistoricoRol h1 WHERE h1.rol.idRol =:idRol AND h1.fechaInicioRol <= :fechaReferencia)"),
        @NamedQuery(
                name = "HistoricoRol.findByRolPadre",
                query = "SELECT h FROM HistoricoRol h WHERE h.rolPadre.idRol =:idRol"),
        @NamedQuery(
                name = "HistoricoRol.findAllByRol",
                query = "SELECT h FROM HistoricoRol h WHERE h.rol.idRol =:idRol"),
        @NamedQuery(
                name = "HistoricoRol.findPrimerHistoricoByRol",
                query = "SELECT h FROM HistoricoRol h WHERE h.idHistoricoRol=(SELECT MIN(hist.idHistoricoRol) FROM HistoricoRol hist WHERE hist.rol.idRol=:idRol)") })
public class HistoricoRol implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    public static final String SQ_HISTORICO_ULTIMO_BY_ROL = "HistoricoRol.findUltimoByRol";
    public static final String SQ_HISTORICO_ULTIMO_BY_ROL_FECHA = "HistoricoRol.findUltimoByRolFecha";
    public static final String SQ_HISTORICO_BY_ROL_PADRE = "HistoricoRol.findByRolPadre";
    public static final String SQ_HISTORICO_ALL_BY_USUARIO = "HistoricoRol.findAllByRol";

    public static final String SQ_HISTORICO_CREACION_BY_ROL = "HistoricoRol.findPrimerHistoricoByRol";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_rol")
    private Long idHistoricoRol;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio_rol")
    private Date fechaInicioRol;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin_rol")
    private Date fechaFinRol;

    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "xml_historico")
    private String xmlHistorico;

    @JoinColumn(name = "id_xsd_historico", referencedColumnName = "id_xsd_historico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private XsdHistorico xsdHistorico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol_padre")
    private Rol rolPadre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Column(name = "usuario_cambio")
    private String usuarioCambio;

    public HistoricoRol() {
    }

    public Long getIdHistoricoRol() {
        return idHistoricoRol;
    }

    public void setIdHistoricoRol(Long idHistoricoRol) {
        this.idHistoricoRol = idHistoricoRol;
    }

    public Date getFechaFinRol() {
        return fechaFinRol;
    }

    public void setFechaFinRol(Date fechaFinRol) {
        this.fechaFinRol = fechaFinRol;
    }

    public Date getFechaInicioRol() {
        return fechaInicioRol;
    }

    public void setFechaInicioRol(Date fechaInicioRol) {
        this.fechaInicioRol = fechaInicioRol;
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

    public Rol getRolPadre() {
        return rolPadre;
    }

    public void setRolPadre(Rol rolPadre) {
        this.rolPadre = rolPadre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsuarioCambio() {
        return usuarioCambio;
    }

    public void setUsuarioCambio(String usuarioCambio) {
        this.usuarioCambio = usuarioCambio;
    }

}
