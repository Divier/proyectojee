/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.datatools.documentos.cm.sql.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergio.torres
 */
@Entity
@Table(name = "version_data_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VersionDataDocumento.findAll", query = "SELECT v FROM VersionDataDocumento v"),
    @NamedQuery(name = "VersionDataDocumento.findByIdVersion", query = "SELECT v FROM VersionDataDocumento v WHERE v.idVersion = :idVersion"),
    @NamedQuery(name = "VersionDataDocumento.findByFecha", query = "SELECT v FROM VersionDataDocumento v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VersionDataDocumento.findByVersion", query = "SELECT v FROM VersionDataDocumento v WHERE v.version = :version")})
public class VersionDataDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_version")
    private Integer idVersion;
    @Basic(optional = false)
    @Lob
    @Column(name = "contenido_binario")
    private byte[] contenidoBinario;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "version")
    private int version;
    @JoinColumn(name = "codigo_documento", referencedColumnName = "codigo_documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DataDocumento codigoDocumento;

    public VersionDataDocumento() {
    }

    public VersionDataDocumento(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public VersionDataDocumento(Integer idVersion, byte[] contenidoBinario, int version) {
        this.idVersion = idVersion;
        this.contenidoBinario = contenidoBinario;
        this.version = version;
    }

    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public byte[] getContenidoBinario() {
        return contenidoBinario;
    }

    public void setContenidoBinario(byte[] contenidoBinario) {
        this.contenidoBinario = contenidoBinario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataDocumento getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(DataDocumento codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVersion != null ? idVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionDataDocumento)) {
            return false;
        }
        VersionDataDocumento other = (VersionDataDocumento) object;
        if ((this.idVersion == null && other.idVersion != null) || (this.idVersion != null && !this.idVersion.equals(other.idVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.VersionDataDocumento[ idVersion=" + idVersion + " ]";
    }
    
}
