package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCircuitoENotifica;
import co.com.datatools.c2.enumeracion.EnumTipoOperacionENotifica;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

public class CreacionCircuitoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String tituloCircuito;
    private EnumTipoCircuitoENotifica tipoCircuito;
    private List<ArchivoTransportableDTO> lDocumentos;
    private boolean publicacionCerti;
    private boolean oculCreadorCircuito;
    private boolean recibirAviso;
    private List<PersonaDTO> lDestinatarios;
    private ConfiguracionEmailDTO configMail;
    private List<ConsultarNotificacionesDTO> lMetadata;
    private EnumTipoOperacionENotifica tipoOperacion;
    private String externalAppId;
    private String externalId;

    public String getTituloCircuito() {
        return tituloCircuito;
    }

    public void setTituloCircuito(String tituloCircuito) {
        this.tituloCircuito = tituloCircuito;
    }

    public EnumTipoCircuitoENotifica getTipoCircuito() {
        return tipoCircuito;
    }

    public void setTipoCircuito(EnumTipoCircuitoENotifica tipoCircuito) {
        this.tipoCircuito = tipoCircuito;
    }

    public List<ArchivoTransportableDTO> getlDocumentos() {
        return lDocumentos;
    }

    public void setlDocumentos(List<ArchivoTransportableDTO> lDocumentos) {
        this.lDocumentos = lDocumentos;
    }

    public boolean isPublicacionCerti() {
        return publicacionCerti;
    }

    public void setPublicacionCerti(boolean publicacionCerti) {
        this.publicacionCerti = publicacionCerti;
    }

    public boolean isOculCreadorCircuito() {
        return oculCreadorCircuito;
    }

    public void setOculCreadorCircuito(boolean oculCreadorCircuito) {
        this.oculCreadorCircuito = oculCreadorCircuito;
    }

    public boolean isRecibirAviso() {
        return recibirAviso;
    }

    public void setRecibirAviso(boolean recibirAviso) {
        this.recibirAviso = recibirAviso;
    }

    public List<PersonaDTO> getlDestinatarios() {
        return lDestinatarios;
    }

    public void setlDestinatarios(List<PersonaDTO> lDestinatarios) {
        this.lDestinatarios = lDestinatarios;
    }

    public ConfiguracionEmailDTO getConfigMail() {
        return configMail;
    }

    public void setConfigMail(ConfiguracionEmailDTO configMail) {
        this.configMail = configMail;
    }

    public List<ConsultarNotificacionesDTO> getlMetadata() {
        return lMetadata;
    }

    public void setlMetadata(List<ConsultarNotificacionesDTO> lMetadata) {
        this.lMetadata = lMetadata;
    }

    public EnumTipoOperacionENotifica getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(EnumTipoOperacionENotifica tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getExternalAppId() {
        return externalAppId;
    }

    public void setExternalAppId(String externalAppId) {
        this.externalAppId = externalAppId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}