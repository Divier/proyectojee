package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersonaHistorico;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;

public class DireccionPersonaHistoricoHelperExtend extends DireccionPersonaHistoricoHelper {

    public static DireccionPersonaHistoricoDTO toDireccionPersonaHistoricoDTO(DireccionPersonaDTO direccionPersonaDTO) {

        DireccionPersonaHistoricoDTO direccionPersonaHistoricoDTO = null;
        if (direccionPersonaDTO != null) {
            direccionPersonaHistoricoDTO = new DireccionPersonaHistoricoDTO();
            direccionPersonaHistoricoDTO.setEstado(direccionPersonaDTO.getEstado());
            direccionPersonaHistoricoDTO.setFechaRegistro(direccionPersonaDTO.getFechaRegistro());
            direccionPersonaHistoricoDTO.setDireccion(direccionPersonaDTO.getDireccion());
            direccionPersonaHistoricoDTO.setTipoFuenteInformacion(direccionPersonaDTO.getTipoFuenteInformacion());
            direccionPersonaHistoricoDTO.setScoreUbicabilidad(direccionPersonaDTO.getScoreUbicabilidad());
            direccionPersonaHistoricoDTO.setAutorizadoNotificacion(direccionPersonaDTO.getAutorizadoNotificacion());
            direccionPersonaHistoricoDTO.setTipoFuenteValidacion(direccionPersonaDTO.getTipoFuenteValidacion());
            direccionPersonaHistoricoDTO.setPrioridad(direccionPersonaDTO.getPrioridad());
            direccionPersonaHistoricoDTO.setUsuarioValida(direccionPersonaDTO.getUsuarioValida());
            direccionPersonaHistoricoDTO.setFechaValidacion(direccionPersonaDTO.getFechaValidacion());
            direccionPersonaHistoricoDTO.setUsuarioRegistro(direccionPersonaDTO.getUsuarioRegistro());
            direccionPersonaHistoricoDTO.setDireccionPersona(direccionPersonaDTO);

        }

        return direccionPersonaHistoricoDTO;
    }

    public static List<DireccionPersonaHistoricoDTO> toListDireccionPersonaHistoricoDTO(
            List<DireccionPersonaDTO> direccionPersonaDTOs) {

        List<DireccionPersonaHistoricoDTO> direccionPersonaHistoricoDTOs = null;

        if (direccionPersonaDTOs != null) {

            direccionPersonaHistoricoDTOs = new ArrayList<>();

            for (DireccionPersonaDTO direccionPersonaDTO : direccionPersonaDTOs) {
                direccionPersonaHistoricoDTOs
                        .add(DireccionPersonaHistoricoHelperExtend.toDireccionPersonaHistoricoDTO(direccionPersonaDTO));
            }
        }

        return direccionPersonaHistoricoDTOs;
    }

    public static DireccionPersonaHistoricoDTO toLevel2DTO(DireccionPersonaHistorico entidad) {
        DireccionPersonaHistoricoDTO dto = toLevel1DTO(entidad);
        if (entidad.getDireccion() != null) {
            dto.setDireccion(DireccionHelper.toLevel1DTO(entidad.getDireccion()));
        }

        return dto;
    }

    public static List<DireccionPersonaHistoricoDTO> toListLevel2DTO(List<DireccionPersonaHistorico> listEntidad) {
        List<DireccionPersonaHistoricoDTO> listDto = new ArrayList<DireccionPersonaHistoricoDTO>();
        for (DireccionPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }

    public static DireccionPersonaHistorico toDireccionPersonaHistorico(DireccionPersona direccionPersona) {

        DireccionPersonaHistorico direccionPersonaHistorico = null;

        if (direccionPersona != null) {
            direccionPersonaHistorico = new DireccionPersonaHistorico();
            direccionPersonaHistorico.setEstado(direccionPersona.getEstado());
            direccionPersonaHistorico.setFechaRegistro(direccionPersona.getFechaRegistro());
            direccionPersonaHistorico.setDireccion(direccionPersona.getDireccion());
            direccionPersonaHistorico.setTipoFuenteInformacion(direccionPersona.getTipoFuenteInformacion());
            direccionPersonaHistorico.setScoreUbicabilidad(direccionPersona.getScoreUbicabilidad());
            direccionPersonaHistorico.setAutorizadoNotificacion(direccionPersona.getAutorizadoNotificacion());
            direccionPersonaHistorico.setTipoFuenteValidacion(direccionPersona.getTipoFuenteValidacion());
            direccionPersonaHistorico.setPrioridad(direccionPersona.getPrioridad());
            direccionPersonaHistorico.setUsuarioValida(direccionPersona.getUsuarioValida());
            direccionPersonaHistorico.setFechaValidacion(direccionPersona.getFechaValidacion());
            direccionPersonaHistorico.setUsuarioRegistro(direccionPersona.getUsuarioRegistro());
            direccionPersonaHistorico.setDireccionPersona(direccionPersona);
        }

        return direccionPersonaHistorico;
    }

    public static List<DireccionPersonaHistorico> toListDireccionPersonaHistorico(
            List<DireccionPersona> direccionPersonas) {

        List<DireccionPersonaHistorico> direccionPersonaHistoricos = null;

        if (direccionPersonas != null) {

            direccionPersonaHistoricos = new ArrayList<>();

            for (DireccionPersona direccionPersona : direccionPersonas) {
                direccionPersonaHistoricos
                        .add(DireccionPersonaHistoricoHelperExtend.toDireccionPersonaHistorico(direccionPersona));
            }
        }

        return direccionPersonaHistoricos;
    }

    public static DireccionPersonaHistorico toLevel2Entity(DireccionPersonaHistoricoDTO dto,
            DireccionPersonaHistorico entidad) {
        entidad = toLevel1Entity(dto, entidad);
        if (dto.getDireccion() != null) {
            entidad.setDireccion(DireccionHelper.toLevel1Entity(dto.getDireccion(), null));
        }
        return entidad;
    }

    public static List<DireccionPersonaHistorico> toListLevel2Entity(List<DireccionPersonaHistoricoDTO> listDto) {
        List<DireccionPersonaHistorico> listEntidad = new ArrayList<DireccionPersonaHistorico>();
        for (DireccionPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel2Entity(dto, null));
        }
        return listEntidad;
    }

}
