package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Thu Dec 01 09:36:47 COT 2016
 */
public class TipoDestinoPruebaImpugHelper { 
  // --- to DTO
  public static TipoDestinoPruebaImpugDTO toLevel0DTO(TipoDestinoPruebaImpug entidad) {
      TipoDestinoPruebaImpugDTO dto = new TipoDestinoPruebaImpugDTO();
      dto.setId(entidad.getId());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());
dto.setEstado(entidad.getEstado());
dto.setNombre(entidad.getNombre());
dto.setSigla(entidad.getSigla());

      return dto;
  }
  public static TipoDestinoPruebaImpugDTO toLevel1DTO(TipoDestinoPruebaImpug entidad) {
      TipoDestinoPruebaImpugDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<TipoDestinoPruebaImpugDTO> toListLevel0DTO(List<TipoDestinoPruebaImpug> listEntidad) {
      List<TipoDestinoPruebaImpugDTO> listDto = new ArrayList<TipoDestinoPruebaImpugDTO>();
      for (TipoDestinoPruebaImpug entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<TipoDestinoPruebaImpugDTO> toListLevel1DTO(List<TipoDestinoPruebaImpug> listEntidad) {
      List<TipoDestinoPruebaImpugDTO> listDto = new ArrayList<TipoDestinoPruebaImpugDTO>();
      for (TipoDestinoPruebaImpug entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static TipoDestinoPruebaImpug toLevel0Entity(TipoDestinoPruebaImpugDTO dto, TipoDestinoPruebaImpug entidad) {
      if (null == entidad) {
          entidad = new TipoDestinoPruebaImpug();
      }
      entidad.setId(dto.getId());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());
entidad.setEstado(dto.getEstado());
entidad.setNombre(dto.getNombre());
entidad.setSigla(dto.getSigla());

      return entidad;
  }
  public static TipoDestinoPruebaImpug toLevel1Entity(TipoDestinoPruebaImpugDTO dto, TipoDestinoPruebaImpug entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<TipoDestinoPruebaImpug> toListLevel0Entity(List<TipoDestinoPruebaImpugDTO> listDto) {
      List<TipoDestinoPruebaImpug> listEntidad = new ArrayList<TipoDestinoPruebaImpug>();
      for (TipoDestinoPruebaImpugDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<TipoDestinoPruebaImpug> toListLevel1Entity(List<TipoDestinoPruebaImpugDTO> listDto) {
      List<TipoDestinoPruebaImpug> listEntidad = new ArrayList<TipoDestinoPruebaImpug>();
      for (TipoDestinoPruebaImpugDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
