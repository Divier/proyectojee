package co.com.datatools.c2.negocio.helpers.v2;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;
/**
 * @author Generated
 * @version 3.0 - Mon Oct 10 15:50:57 COT 2016
 */
public class ConfiguracionSoporteHelper { 
  // --- to DTO
  public static ConfiguracionSoporteDTO toLevel0DTO(ConfiguracionSoporte entidad) {
      ConfiguracionSoporteDTO dto = new ConfiguracionSoporteDTO();
      dto.setId(entidad.getId());
dto.setConfiguracionEjecucion(entidad.getConfiguracionEjecucion());
dto.setRespuesta(entidad.getRespuesta());

      return dto;
  }
  public static ConfiguracionSoporteDTO toLevel1DTO(ConfiguracionSoporte entidad) {
      ConfiguracionSoporteDTO dto = toLevel0DTO(entidad);
      if(entidad.getSoporte() != null)
  dto.setSoporte(SoporteProcedimientoHelper.toLevel0DTO(entidad.getSoporte()));

      return dto;
  }
  public static List<ConfiguracionSoporteDTO> toListLevel0DTO(List<ConfiguracionSoporte> listEntidad) {
      List<ConfiguracionSoporteDTO> listDto = new ArrayList<ConfiguracionSoporteDTO>();
      for (ConfiguracionSoporte entidad : listEntidad) {
          listDto.add(toLevel0DTO(entidad));
      }
      return listDto;
  }
  public static List<ConfiguracionSoporteDTO> toListLevel1DTO(List<ConfiguracionSoporte> listEntidad) {
      List<ConfiguracionSoporteDTO> listDto = new ArrayList<ConfiguracionSoporteDTO>();
      for (ConfiguracionSoporte entidad : listEntidad) {
          listDto.add(toLevel1DTO(entidad));
      }
      return listDto;
  }
  // --- fin to DTO
  // --- to Entidad
  public static ConfiguracionSoporte toLevel0Entity(ConfiguracionSoporteDTO dto, ConfiguracionSoporte entidad) {
      if (null == entidad) {
          entidad = new ConfiguracionSoporte();
      }
      entidad.setId(dto.getId());
entidad.setConfiguracionEjecucion(dto.getConfiguracionEjecucion());
entidad.setRespuesta(dto.getRespuesta());

      return entidad;
  }
  public static ConfiguracionSoporte toLevel1Entity(ConfiguracionSoporteDTO dto, ConfiguracionSoporte entidad) {
      entidad = toLevel0Entity(dto, entidad);
      if(dto.getSoporte() != null){
 entidad.setSoporte(new SoporteProcedimiento());
 entidad.getSoporte().setId(dto.getSoporte().getId());
}

      return entidad;
  }
  public static List<ConfiguracionSoporte> toListLevel0Entity(List<ConfiguracionSoporteDTO> listDto) {
      List<ConfiguracionSoporte> listEntidad = new ArrayList<ConfiguracionSoporte>();
      for (ConfiguracionSoporteDTO dto : listDto) {
          listEntidad.add(toLevel0Entity(dto, null));
      }
      return listEntidad;
  }
  public static List<ConfiguracionSoporte> toListLevel1Entity(List<ConfiguracionSoporteDTO> listDto) {
      List<ConfiguracionSoporte> listEntidad = new ArrayList<ConfiguracionSoporte>();
      for (ConfiguracionSoporteDTO dto : listDto) {
          listEntidad.add(toLevel1Entity(dto, null));
      }
      return listEntidad;
  }
  // --- fin to Entidad
}
