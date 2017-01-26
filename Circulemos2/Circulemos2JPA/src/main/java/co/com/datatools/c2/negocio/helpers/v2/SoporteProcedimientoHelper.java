package co.com.datatools.c2.negocio.helpers.v2;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Mon Oct 10 15:50:34 COT 2016
 */
public class SoporteProcedimientoHelper { 
  // --- to DTO
  public static SoporteProcedimientoDTO toLevel0DTO(SoporteProcedimiento entidad) {
      SoporteProcedimientoDTO dto = new SoporteProcedimientoDTO();
      dto.setId(entidad.getId());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());
dto.setEstado(entidad.getEstado());
dto.setNombre(entidad.getNombre());
dto.setSigla(entidad.getSigla());

      return dto;
  }
  public static SoporteProcedimientoDTO toLevel1DTO(SoporteProcedimiento entidad) {
      SoporteProcedimientoDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<SoporteProcedimientoDTO> toListLevel0DTO(List<SoporteProcedimiento> listEntidad) {
      List<SoporteProcedimientoDTO> listDto = new ArrayList<SoporteProcedimientoDTO>();
      for (SoporteProcedimiento entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<SoporteProcedimientoDTO> toListLevel1DTO(List<SoporteProcedimiento> listEntidad) {
      List<SoporteProcedimientoDTO> listDto = new ArrayList<SoporteProcedimientoDTO>();
      for (SoporteProcedimiento entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static SoporteProcedimiento toLevel0Entity(SoporteProcedimientoDTO dto, SoporteProcedimiento entidad) {
      if (null == entidad) {
          entidad = new SoporteProcedimiento();
      }
      entidad.setId(dto.getId());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());
entidad.setEstado(dto.getEstado());
entidad.setNombre(dto.getNombre());
entidad.setSigla(dto.getSigla());

      return entidad;
  }
  public static SoporteProcedimiento toLevel1Entity(SoporteProcedimientoDTO dto, SoporteProcedimiento entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<SoporteProcedimiento> toListLevel0Entity(List<SoporteProcedimientoDTO> listDto) {
      List<SoporteProcedimiento> listEntidad = new ArrayList<SoporteProcedimiento>();
      for (SoporteProcedimientoDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<SoporteProcedimiento> toListLevel1Entity(List<SoporteProcedimientoDTO> listDto) {
      List<SoporteProcedimiento> listEntidad = new ArrayList<SoporteProcedimiento>();
      for (SoporteProcedimientoDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
