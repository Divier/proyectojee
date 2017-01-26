package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 15:00:37 COT 2016
 */
public class SentidoHelper { 
  // --- to DTO
  public static SentidoDTO toLevel0DTO(Sentido entidad) {
      SentidoDTO dto = new SentidoDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static SentidoDTO toLevel1DTO(Sentido entidad) {
      SentidoDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<SentidoDTO> toListLevel0DTO(List<Sentido> listEntidad) {
      List<SentidoDTO> listDto = new ArrayList<SentidoDTO>();
      for (Sentido entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<SentidoDTO> toListLevel1DTO(List<Sentido> listEntidad) {
      List<SentidoDTO> listDto = new ArrayList<SentidoDTO>();
      for (Sentido entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static Sentido toLevel0Entity(SentidoDTO dto, Sentido entidad) {
      if (null == entidad) {
          entidad = new Sentido();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static Sentido toLevel1Entity(SentidoDTO dto, Sentido entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<Sentido> toListLevel0Entity(List<SentidoDTO> listDto) {
      List<Sentido> listEntidad = new ArrayList<Sentido>();
      for (SentidoDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<Sentido> toListLevel1Entity(List<SentidoDTO> listDto) {
      List<Sentido> listEntidad = new ArrayList<Sentido>();
      for (SentidoDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
