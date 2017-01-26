package co.com.datatools.c2.negocio.helpers.v2;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 11:52:05 COT 2015
 */
public class PeriodoInteresHelper { 
  // --- to DTO
  public static PeriodoInteresDTO toLevel0DTO(PeriodoInteres entidad) {
      PeriodoInteresDTO dto = new PeriodoInteresDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setActivo(entidad.getActivo());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static PeriodoInteresDTO toLevel1DTO(PeriodoInteres entidad) {
      PeriodoInteresDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<PeriodoInteresDTO> toListLevel0DTO(List<PeriodoInteres> listEntidad) {
      List<PeriodoInteresDTO> listDto = new ArrayList<PeriodoInteresDTO>();
      for (PeriodoInteres entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<PeriodoInteresDTO> toListLevel1DTO(List<PeriodoInteres> listEntidad) {
      List<PeriodoInteresDTO> listDto = new ArrayList<PeriodoInteresDTO>();
      for (PeriodoInteres entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static PeriodoInteres toLevel0Entity(PeriodoInteresDTO dto, PeriodoInteres entidad) {
      if (null == entidad) {
          entidad = new PeriodoInteres();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setActivo(dto.getActivo());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static PeriodoInteres toLevel1Entity(PeriodoInteresDTO dto, PeriodoInteres entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<PeriodoInteres> toListLevel0Entity(List<PeriodoInteresDTO> listDto) {
      List<PeriodoInteres> listEntidad = new ArrayList<PeriodoInteres>();
      for (PeriodoInteresDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<PeriodoInteres> toListLevel1Entity(List<PeriodoInteresDTO> listDto) {
      List<PeriodoInteres> listEntidad = new ArrayList<PeriodoInteres>();
      for (PeriodoInteresDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
