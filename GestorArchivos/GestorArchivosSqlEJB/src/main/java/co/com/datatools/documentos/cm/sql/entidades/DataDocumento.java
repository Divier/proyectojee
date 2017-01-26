/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.datatools.documentos.cm.sql.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio.torres
 */
@Entity
@Table(name = "data_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataDocumento.findAll", query = "SELECT d FROM DataDocumento d"),
    @NamedQuery(name = "DataDocumento.findByCodigoDocumento", query = "SELECT d FROM DataDocumento d WHERE d.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "DataDocumento.findByDescripcion", query = "SELECT d FROM DataDocumento d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DataDocumento.findByNombre", query = "SELECT d FROM DataDocumento d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DataDocumento.findByFolder", query = "SELECT d FROM DataDocumento d WHERE d.folder = :folder"),
    @NamedQuery(name = "DataDocumento.findByExtension", query = "SELECT d FROM DataDocumento d WHERE d.extension = :extension")})
public class DataDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_documento")
    private Integer codigoDocumento;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "folder")
    private String folder;
    @Column(name = "extension")
    private String extension;
	@Column(name = "nombre_real")
    private String nombreReal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoDocumento", fetch = FetchType.LAZY)
    private List<VersionDataDocumento> versionDataDocumentoList;

    public DataDocumento() {
    }

    public DataDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public Integer getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

	public String getNombreReal() {
        return this.nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    @XmlTransient
    public List<VersionDataDocumento> getVersionDataDocumentoList() {
        return versionDataDocumentoList;
    }

    public void setVersionDataDocumentoList(List<VersionDataDocumento> versionDataDocumentoList) {
        this.versionDataDocumentoList = versionDataDocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDocumento != null ? codigoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataDocumento)) {
            return false;
        }
        DataDocumento other = (DataDocumento) object;
        if ((this.codigoDocumento == null && other.codigoDocumento != null) || (this.codigoDocumento != null && !this.codigoDocumento.equals(other.codigoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.DataDocumento[ codigoDocumento=" + codigoDocumento + " ]";
    }
    
}
