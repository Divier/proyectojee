package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersonaHistorico;

public class TelefonoPersonaHistoricoHelperExtend extends TelefonoPersonaHistoricoHelper {

    public static TelefonoPersonaHistoricoDTO toTelefonoPersonaHistoricoDTO(TelefonoPersonaDTO telefonoPersonaDTO) {

        TelefonoPersonaHistoricoDTO TelefonoPersonaHistoricoDTO = null;

        if (telefonoPersonaDTO != null) {
            TelefonoPersonaHistoricoDTO = new TelefonoPersonaHistoricoDTO();
            TelefonoPersonaHistoricoDTO.setEstado(telefonoPersonaDTO.getEstado());
            TelefonoPersonaHistoricoDTO.setAutorizadoNotificacion(telefonoPersonaDTO.getAutorizadoNotificacion());
            TelefonoPersonaHistoricoDTO.setFechaRegistro(telefonoPersonaDTO.getFechaRegistro());
            TelefonoPersonaHistoricoDTO.setNumeroTelefono(telefonoPersonaDTO.getNumeroTelefono());
            TelefonoPersonaHistoricoDTO.setTipoFuenteValidacion(telefonoPersonaDTO.getTipoFuenteValidacion());
            TelefonoPersonaHistoricoDTO.setPrioridad(telefonoPersonaDTO.getPrioridad());
            TelefonoPersonaHistoricoDTO.setUsuarioValida(telefonoPersonaDTO.getUsuarioValida());
            TelefonoPersonaHistoricoDTO.setFechaValidacion(telefonoPersonaDTO.getFechaValidacion());
            TelefonoPersonaHistoricoDTO.setScoreUbicabilidad(telefonoPersonaDTO.getScoreUbicabilidad());
            TelefonoPersonaHistoricoDTO.setTipoTelefono(telefonoPersonaDTO.getTipoTelefono());
            TelefonoPersonaHistoricoDTO.setTipoFuenteInformacion(telefonoPersonaDTO.getTipoFuenteInformacion());
            TelefonoPersonaHistoricoDTO.setUsuarioRegistro(telefonoPersonaDTO.getUsuarioRegistro());
            TelefonoPersonaHistoricoDTO.setTelefonoPersona(telefonoPersonaDTO);

        }

        return TelefonoPersonaHistoricoDTO;
    }

    public static List<TelefonoPersonaHistoricoDTO> toListTelefonoPersonaHistoricoDTO(
            List<TelefonoPersonaDTO> telefonoPersonaDTOs) {

        List<TelefonoPersonaHistoricoDTO> telefonoPersonaHistoricoDTOs = null;

        if (telefonoPersonaDTOs != null) {

            telefonoPersonaHistoricoDTOs = new ArrayList<>();

            for (TelefonoPersonaDTO telefonoPersonaDTO : telefonoPersonaDTOs) {
                telefonoPersonaHistoricoDTOs
                        .add(TelefonoPersonaHistoricoHelperExtend.toTelefonoPersonaHistoricoDTO(telefonoPersonaDTO));
            }
        }

        return telefonoPersonaHistoricoDTOs;
    }

    public static TelefonoPersonaHistorico toTelefonoPersonaHsitorico(TelefonoPersona telefonoPersona) {
        TelefonoPersonaHistorico telefonoPersonaHistorico = null;

        if (telefonoPersona != null) {
            telefonoPersonaHistorico = new TelefonoPersonaHistorico();
            telefonoPersonaHistorico.setEstado(telefonoPersona.getEstado());
            telefonoPersonaHistorico.setAutorizadoNotificacion(telefonoPersona.getAutorizadoNotificacion());
            telefonoPersonaHistorico.setFechaRegistro(telefonoPersona.getFechaRegistro());
            telefonoPersonaHistorico.setNumeroTelefono(telefonoPersona.getNumeroTelefono());
            telefonoPersonaHistorico.setTipoFuenteValidacion(telefonoPersona.getTipoFuenteValidacion());
            telefonoPersonaHistorico.setPrioridad(telefonoPersona.getPrioridad());
            telefonoPersonaHistorico.setUsuarioValida(telefonoPersona.getUsuarioValida());
            telefonoPersonaHistorico.setFechaValidacion(telefonoPersona.getFechaValidacion());
            telefonoPersonaHistorico.setScoreUbicabilidad(telefonoPersona.getScoreUbicabilidad());
            telefonoPersonaHistorico.setTipoTelefono(telefonoPersona.getTipoTelefono());
            telefonoPersonaHistorico.setTipoFuenteInformacion(telefonoPersona.getTipoFuenteInformacion());
            telefonoPersonaHistorico.setUsuarioRegistro(telefonoPersona.getUsuarioRegistro());
            telefonoPersonaHistorico.setTelefonoPersona(telefonoPersona);
        }

        return telefonoPersonaHistorico;
    }

    public static List<TelefonoPersonaHistorico> toListTelefonoPersonaHistorico(
            List<TelefonoPersona> telefonoPersonas) {

        List<TelefonoPersonaHistorico> telefonoPersonaHistoricos = null;

        if (telefonoPersonas != null) {
            telefonoPersonaHistoricos = new ArrayList<>();

            for (TelefonoPersona telefonoPersona : telefonoPersonas) {
                telefonoPersonaHistoricos
                        .add(TelefonoPersonaHistoricoHelperExtend.toTelefonoPersonaHsitorico(telefonoPersona));
            }
        }

        return telefonoPersonaHistoricos;

    }
}
