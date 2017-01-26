package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:00:19 COT 2016
 */
public class PrevencionHelper { 
  // --- to DTO
  public static PrevencionDTO toLevel0DTO(Prevencion entidad) {
      PrevencionDTO dto = new PrevencionDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static PrevencionDTO toLevel1DTO(Prevencion entidad) {
      PrevencionDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<PrevencionDTO> toListLevel0DTO(List<Prevencion> listEntidad) {
      List<PrevencionDTO> listDto = new ArrayList<PrevencionDTO>();
      for (Prevencion entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<PrevencionDTO> toListLevel1DTO(List<Prevencion> listEntidad) {
      List<PrevencionDTO> listDto = new ArrayList<PrevencionDTO>();
      for (Prevencion entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static Prevencion toLevel0Entity(PrevencionDTO dto, Prevencion entidad) {
      if (null == entidad) {
          entidad = new Prevencion();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static Prevencion toLevel1Entity(PrevencionDTO dto, Prevencion entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<Prevencion> toListLevel0Entity(List<PrevencionDTO> listDto) {
      List<Prevencion> listEntidad = new ArrayList<Prevencion>();
      for (PrevencionDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<Prevencion> toListLevel1Entity(List<PrevencionDTO> listDto) {
      List<Prevencion> listEntidad = new ArrayList<Prevencion>();
      for (PrevencionDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
