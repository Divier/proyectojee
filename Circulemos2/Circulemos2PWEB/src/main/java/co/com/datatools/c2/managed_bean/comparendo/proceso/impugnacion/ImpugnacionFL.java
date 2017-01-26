package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.OrigenImpugnacionDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.RadicarExpedienteDTO;
import co.com.datatools.c2.dto.TipoParticipanteDTO;
import co.com.datatools.c2.dto.TipoPeticionDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Objejo para manejo de la informacion de impugnacion en el flujo
 * 
 * @author giovanni.velandia
 * 
 */
public class ImpugnacionFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "impugnacionFL";
    private RadicarExpedienteDTO radicarExpedienteDTO;
    private boolean confirmarRadicar;
    private int tipoParticipanteDefault;
    private Boolean enviarAbogadoEspecialista;
    private Integer justificacionEnvio;
    private Boolean tienePruebas;
    private boolean evaluarExpedienteEspecialista;
    private boolean confCierrePruebas;
    private ParticipanteProcesoDTO participanteProcesoDTO;

    public ImpugnacionFL() {
        radicarExpedienteDTO = new RadicarExpedienteDTO();
        radicarExpedienteDTO.setMotivacionImpugnacionDTO(new MotivacionImpugnacionDTO());
        radicarExpedienteDTO.getMotivacionImpugnacionDTO().setTipoPeticion(new TipoPeticionDTO());
        radicarExpedienteDTO.getMotivacionImpugnacionDTO().setOrigenImpugnacion(new OrigenImpugnacionDTO());
        setTipoParticipanteDefault(EnumTipoParticipante.INFRACTOR.getValue());
        enviarAbogadoEspecialista = false;
        participanteProcesoDTO = new ParticipanteProcesoDTO();
        participanteProcesoDTO.setTipoParticipante(new TipoParticipanteDTO());
    }

    public RadicarExpedienteDTO getRadicarExpedienteDTO() {
        return radicarExpedienteDTO;
    }

    public void setRadicarExpedienteDTO(RadicarExpedienteDTO radicarExpedienteDTO) {
        this.radicarExpedienteDTO = radicarExpedienteDTO;
    }

    public boolean isConfirmarRadicar() {
        return confirmarRadicar;
    }

    public void setConfirmarRadicar(boolean confirmarRadicar) {
        this.confirmarRadicar = confirmarRadicar;
    }

    public int getTipoParticipanteDefault() {
        return tipoParticipanteDefault;
    }

    public void setTipoParticipanteDefault(int tipoParticipanteDefault) {
        this.tipoParticipanteDefault = tipoParticipanteDefault;
    }

    public Boolean getEnviarAbogadoEspecialista() {
        return enviarAbogadoEspecialista;
    }

    public void setEnviarAbogadoEspecialista(Boolean enviarAbogadoEspecialista) {
        this.enviarAbogadoEspecialista = enviarAbogadoEspecialista;
    }

    public Integer getJustificacionEnvio() {
        return justificacionEnvio;
    }

    public void setJustificacionEnvio(Integer justificacionEnvio) {
        this.justificacionEnvio = justificacionEnvio;
    }

    public Boolean getTienePruebas() {
        return tienePruebas;
    }

    public void setTienePruebas(Boolean tienePruebas) {
        this.tienePruebas = tienePruebas;
    }

    public boolean isEvaluarExpedienteEspecialista() {
        return evaluarExpedienteEspecialista;
    }

    public void setEvaluarExpedienteEspecialista(boolean evaluarExpedienteEspecialista) {
        this.evaluarExpedienteEspecialista = evaluarExpedienteEspecialista;
    }

    public boolean isConfCierrePruebas() {
        return confCierrePruebas;
    }

    public void setConfCierrePruebas(boolean confCierrePruebas) {
        this.confCierrePruebas = confCierrePruebas;
    }

    public ParticipanteProcesoDTO getParticipanteProcesoDTO() {
        return participanteProcesoDTO;
    }

    public void setParticipanteProcesoDTO(ParticipanteProcesoDTO participanteProcesoDTO) {
        this.participanteProcesoDTO = participanteProcesoDTO;
    }
}