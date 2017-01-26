package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 19 11:25:06 COT 2016
 */
public class MedioNotificacionHelper { 
  // --- to DTO
  public static MedioNotificacionDTO toLevel0DTO(MedioNotificacion entidad) {
      MedioNotificacionDTO dto = new MedioNotificacionDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static MedioNotificacionDTO toLevel1DTO(MedioNotificacion entidad) {
      MedioNotificacionDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<MedioNotificacionDTO> toListLevel0DTO(List<MedioNotificacion> listEntidad) {
      List<MedioNotificacionDTO> listDto = new ArrayList<MedioNotificacionDTO>();
      for (MedioNotificacion entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<MedioNotificacionDTO> toListLevel1DTO(List<MedioNotificacion> listEntidad) {
      List<MedioNotificacionDTO> listDto = new ArrayList<MedioNotificacionDTO>();
      for (MedioNotificacion entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static MedioNotificacion toLevel0Entity(MedioNotificacionDTO dto, MedioNotificacion entidad) {
      if (null == entidad) {
          entidad = new MedioNotificacion();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static MedioNotificacion toLevel1Entity(MedioNotificacionDTO dto, MedioNotificacion entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<MedioNotificacion> toListLevel0Entity(List<MedioNotificacionDTO> listDto) {
      List<MedioNotificacion> listEntidad = new ArrayList<MedioNotificacion>();
      for (MedioNotificacionDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<MedioNotificacion> toListLevel1Entity(List<MedioNotificacionDTO> listDto) {
      List<MedioNotificacion> listEntidad = new ArrayList<MedioNotificacion>();
      for (MedioNotificacionDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
