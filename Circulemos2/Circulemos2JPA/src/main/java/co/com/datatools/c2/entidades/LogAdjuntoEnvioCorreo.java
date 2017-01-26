package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "log_adjunto_envio_correo")
public class LogAdjuntoEnvioCorreo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_log_adjunto_envio_correo")
    private Long id;

    @JoinColumn(name = "id_log_envio_correo", referencedColumnName = "id_log_envio_correo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LogEnvioCorreo logEnvioCorreo;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "archivo")
    private byte[] archivo;

    public LogAdjuntoEnvioCorreo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogEnvioCorreo getLogEnvioCorreo() {
        return logEnvioCorreo;
    }

    public void setLogEnvioCorreo(LogEnvioCorreo logEnvioCorreo) {
        this.logEnvioCorreo = logEnvioCorreo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

}