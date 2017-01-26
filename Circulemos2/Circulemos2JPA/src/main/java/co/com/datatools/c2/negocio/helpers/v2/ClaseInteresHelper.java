package co.com.datatools.c2.negocio.helpers.v2;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 11:51:23 COT 2015
 */
public class ClaseInteresHelper { 
  // --- to DTO
  public static ClaseInteresDTO toLevel0DTO(ClaseInteres entidad) {
      ClaseInteresDTO dto = new ClaseInteresDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setActivo(entidad.getActivo());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static ClaseInteresDTO toLevel1DTO(ClaseInteres entidad) {
      ClaseInteresDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<ClaseInteresDTO> toListLevel0DTO(List<ClaseInteres> listEntidad) {
      List<ClaseInteresDTO> listDto = new ArrayList<ClaseInteresDTO>();
      for (ClaseInteres entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<ClaseInteresDTO> toListLevel1DTO(List<ClaseInteres> listEntidad) {
      List<ClaseInteresDTO> listDto = new ArrayList<ClaseInteresDTO>();
      for (ClaseInteres entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static ClaseInteres toLevel0Entity(ClaseInteresDTO dto, ClaseInteres entidad) {
      if (null == entidad) {
          entidad = new ClaseInteres();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setActivo(dto.getActivo());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static ClaseInteres toLevel1Entity(ClaseInteresDTO dto, ClaseInteres entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<ClaseInteres> toListLevel0Entity(List<ClaseInteresDTO> listDto) {
      List<ClaseInteres> listEntidad = new ArrayList<ClaseInteres>();
      for (ClaseInteresDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<ClaseInteres> toListLevel1Entity(List<ClaseInteresDTO> listDto) {
      List<ClaseInteres> listEntidad = new ArrayList<ClaseInteres>();
      for (ClaseInteresDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
