package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:01:25 COT 2016
 */
public class TipoPersonaIPATHelper { 
  // --- to DTO
  public static TipoPersonaIPATDTO toLevel0DTO(TipoPersonaIPAT entidad) {
      TipoPersonaIPATDTO dto = new TipoPersonaIPATDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static TipoPersonaIPATDTO toLevel1DTO(TipoPersonaIPAT entidad) {
      TipoPersonaIPATDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<TipoPersonaIPATDTO> toListLevel0DTO(List<TipoPersonaIPAT> listEntidad) {
      List<TipoPersonaIPATDTO> listDto = new ArrayList<TipoPersonaIPATDTO>();
      for (TipoPersonaIPAT entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<TipoPersonaIPATDTO> toListLevel1DTO(List<TipoPersonaIPAT> listEntidad) {
      List<TipoPersonaIPATDTO> listDto = new ArrayList<TipoPersonaIPATDTO>();
      for (TipoPersonaIPAT entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static TipoPersonaIPAT toLevel0Entity(TipoPersonaIPATDTO dto, TipoPersonaIPAT entidad) {
      if (null == entidad) {
          entidad = new TipoPersonaIPAT();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static TipoPersonaIPAT toLevel1Entity(TipoPersonaIPATDTO dto, TipoPersonaIPAT entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<TipoPersonaIPAT> toListLevel0Entity(List<TipoPersonaIPATDTO> listDto) {
      List<TipoPersonaIPAT> listEntidad = new ArrayList<TipoPersonaIPAT>();
      for (TipoPersonaIPATDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<TipoPersonaIPAT> toListLevel1Entity(List<TipoPersonaIPATDTO> listDto) {
      List<TipoPersonaIPAT> listEntidad = new ArrayList<TipoPersonaIPAT>();
      for (TipoPersonaIPATDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
