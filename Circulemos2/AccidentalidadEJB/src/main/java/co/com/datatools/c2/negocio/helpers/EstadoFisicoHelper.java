package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:59:53 COT 2016
 */
public class EstadoFisicoHelper { 
  // --- to DTO
  public static EstadoFisicoDTO toLevel0DTO(EstadoFisico entidad) {
      EstadoFisicoDTO dto = new EstadoFisicoDTO();
      dto.setId(entidad.getId());
dto.setNombre(entidad.getNombre());
dto.setEstado(entidad.getEstado());
dto.setSigla(entidad.getSigla());
dto.setCodigo(entidad.getCodigo());
dto.setDescripcion(entidad.getDescripcion());

      return dto;
  }
  public static EstadoFisicoDTO toLevel1DTO(EstadoFisico entidad) {
      EstadoFisicoDTO dto = toLevel0DTO(entidad);
      
      return dto;
  }
  public static List<EstadoFisicoDTO> toListLevel0DTO(List<EstadoFisico> listEntidad) {
      List<EstadoFisicoDTO> listDto = new ArrayList<EstadoFisicoDTO>();
      for (EstadoFisico entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<EstadoFisicoDTO> toListLevel1DTO(List<EstadoFisico> listEntidad) {
      List<EstadoFisicoDTO> listDto = new ArrayList<EstadoFisicoDTO>();
      for (EstadoFisico entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static EstadoFisico toLevel0Entity(EstadoFisicoDTO dto, EstadoFisico entidad) {
      if (null == entidad) {
          entidad = new EstadoFisico();
      }
      entidad.setId(dto.getId());
entidad.setNombre(dto.getNombre());
entidad.setEstado(dto.getEstado());
entidad.setSigla(dto.getSigla());
entidad.setCodigo(dto.getCodigo());
entidad.setDescripcion(dto.getDescripcion());

      return entidad;
  }
  public static EstadoFisico toLevel1Entity(EstadoFisicoDTO dto, EstadoFisico entidad) {
      entidad = toLevel0Entity(dto, entidad);
      
      return entidad;
  }
  public static List<EstadoFisico> toListLevel0Entity(List<EstadoFisicoDTO> listDto) {
      List<EstadoFisico> listEntidad = new ArrayList<EstadoFisico>();
      for (EstadoFisicoDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<EstadoFisico> toListLevel1Entity(List<EstadoFisicoDTO> listDto) {
      List<EstadoFisico> listEntidad = new ArrayList<EstadoFisico>();
      for (EstadoFisicoDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
