package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersonaHistorico;

public class CorreoPersonaHistoricoHelperExtend extends CorreoPersonaHistoricoHelper {

    public static CorreoPersonaHistoricoDTO toCorreoPersonaHistoricoDTO(CorreoPersonaDTO correoPersonaDTO) {

        CorreoPersonaHistoricoDTO correoPersonaHistoricoDTO = null;

        if (correoPersonaDTO != null) {
            correoPersonaHistoricoDTO = new CorreoPersonaHistoricoDTO();
            correoPersonaHistoricoDTO.setEstado(correoPersonaDTO.getEstado());
            correoPersonaHistoricoDTO.setAutorizadoNotificacion(correoPersonaDTO.getAutorizadoNotificacion());
            correoPersonaHistoricoDTO.setFechaRegistro(correoPersonaDTO.getFechaRegistro());
            correoPersonaHistoricoDTO.setCorreoElectronico(correoPersonaDTO.getCorreoElectronico());
            correoPersonaHistoricoDTO.setTipoFuenteValidacion(correoPersonaDTO.getTipoFuenteValidacion());
            correoPersonaHistoricoDTO.setPrioridad(correoPersonaDTO.getPrioridad());
            correoPersonaHistoricoDTO.setUsuarioValida(correoPersonaDTO.getUsuarioValida());
            correoPersonaHistoricoDTO.setFechaValidacion(correoPersonaDTO.getFechaValidacion());
            correoPersonaHistoricoDTO.setScoreUbicabilidad(correoPersonaDTO.getScoreUbicabilidad());
            correoPersonaHistoricoDTO.setTipoFuenteInformacion(correoPersonaDTO.getTipoFuenteInformacion());
            correoPersonaHistoricoDTO.setUsuarioRegistro(correoPersonaDTO.getUsuarioRegistro());
            correoPersonaHistoricoDTO.setCorreoPersona(correoPersonaDTO);
        }

        return correoPersonaHistoricoDTO;
    }

    public static List<CorreoPersonaHistoricoDTO> toListCorreoPersonaHistoricoDTO(
            List<CorreoPersonaDTO> correoPersonaDTOs) {

        List<CorreoPersonaHistoricoDTO> correoPersonaHistoricoDTOs = null;

        if (correoPersonaDTOs != null) {

            correoPersonaHistoricoDTOs = new ArrayList<>();

            for (CorreoPersonaDTO correoPersonaDTO : correoPersonaDTOs) {
                correoPersonaHistoricoDTOs
                        .add(CorreoPersonaHistoricoHelperExtend.toCorreoPersonaHistoricoDTO(correoPersonaDTO));
            }
        }

        return correoPersonaHistoricoDTOs;
    }

    public static CorreoPersonaHistorico toCorreoPersonaHistorico(CorreoPersona correoPersona) {

        CorreoPersonaHistorico correoPersonaHistorico = null;

        if (correoPersona != null) {
            correoPersonaHistorico = new CorreoPersonaHistorico();
            correoPersonaHistorico.setEstado(correoPersona.getEstado());
            correoPersonaHistorico.setAutorizadoNotificacion(correoPersona.getAutorizadoNotificacion());
            correoPersonaHistorico.setFechaRegistro(correoPersona.getFechaRegistro());
            correoPersonaHistorico.setCorreoElectronico(correoPersona.getCorreoElectronico());
            correoPersonaHistorico.setTipoFuenteValidacion(correoPersona.getTipoFuenteValidacion());
            correoPersonaHistorico.setPrioridad(correoPersona.getPrioridad());
            correoPersonaHistorico.setUsuarioValida(correoPersona.getUsuarioValida());
            correoPersonaHistorico.setFechaValidacion(correoPersona.getFechaValidacion());
            correoPersonaHistorico.setScoreUbicabilidad(correoPersona.getScoreUbicabilidad());
            correoPersonaHistorico.setTipoFuenteInformacion(correoPersona.getTipoFuenteInformacion());
            correoPersonaHistorico.setUsuarioRegistro(correoPersona.getUsuarioRegistro());
            correoPersonaHistorico.setCorreoPersona(correoPersona);
        }

        return correoPersonaHistorico;
    }

    public static List<CorreoPersonaHistorico> toListCorreoPersonaHistorico(List<CorreoPersona> correoPersonas) {
        List<CorreoPersonaHistorico> correoPersonaHistoricos = null;

        if (correoPersonas != null) {
            correoPersonaHistoricos = new ArrayList<>();

            for (CorreoPersona correoPersona : correoPersonas) {
                correoPersonaHistoricos.add(CorreoPersonaHistoricoHelperExtend.toCorreoPersonaHistorico(correoPersona));
            }
        }

        return correoPersonaHistoricos;
    }
}
