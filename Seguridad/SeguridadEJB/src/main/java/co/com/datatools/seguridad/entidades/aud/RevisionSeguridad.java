//package co.com.datatools.seguridad.entidades.aud;
//
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.envers.RevisionNumber;
//import org.hibernate.envers.RevisionTimestamp;
//
//@Entity
//@Table(name = "revision", schema = "seguridad_aud")
//@org.hibernate.envers.RevisionEntity(RevisionSeguridadListener.class)
//public class RevisionSeguridad {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @RevisionNumber
//    private long id;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @RevisionTimestamp
//    private Date fechaAud;
//
//    private String usuario;
//
//    private String ip;
//
//    public String getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(String usuario) {
//        this.usuario = usuario;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Date getFechaAud() {
//        return fechaAud;
//    }
//
//    public void setFechaAud(Date fechaAud) {
//        this.fechaAud = fechaAud;
//    }
//
// }
