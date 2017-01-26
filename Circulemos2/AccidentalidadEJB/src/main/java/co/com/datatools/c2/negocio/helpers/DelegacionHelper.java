package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:55:07 COT 2016
 */
public class DelegacionHelper { 
  // --- to DTO
  public static DelegacionDTO toLevel0DTO(Delegacion entidad) {
      DelegacionDTO dto = new DelegacionDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static DelegacionDTO toLevel1DTO(Delegacion entidad) {
      DelegacionDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<DelegacionDTO> toListLevel0DTO(List<Delegacion> listEntidad) {
      List<DelegacionDTO> listDto = new ArrayList<DelegacionDTO>();
      for (Delegacion entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<DelegacionDTO> toListLevel1DTO(List<Delegacion> listEntidad) {
      List<DelegacionDTO> listDto = new ArrayList<DelegacionDTO>();
      for (Delegacion entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static Delegacion toLevel0Entity(DelegacionDTO dto, Delegacion entidad) {
      if (null == entidad) {
          entidad = new Delegacion();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static Delegacion toLevel1Entity(DelegacionDTO dto, Delegacion entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<Delegacion> toListLevel0Entity(List<DelegacionDTO> listDto) {
      List<Delegacion> listEntidad = new ArrayList<Delegacion>();
      for (DelegacionDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<Delegacion> toListLevel1Entity(List<DelegacionDTO> listDto) {
      List<Delegacion> listEntidad = new ArrayList<Delegacion>();
      for (DelegacionDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
