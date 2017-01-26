package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 14 15:37:41 COT 2016
 */
public class TipoEventoHelper {
	// --- to DTO
	public static TipoEventoDTO toLevel0DTO(TipoEvento entidad) {
		TipoEventoDTO dto = new TipoEventoDTO();
		dto.setId(entidad.getId());
		dto.setCodigo(entidad.getCodigo());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setEstado(entidad.getActivo());
		dto.setNombre(entidad.getNombre());
		dto.setSigla(entidad.getSigla());

		return dto;
	}

	public static TipoEventoDTO toLevel1DTO(TipoEvento entidad) {
		TipoEventoDTO dto = toLevel0DTO(entidad);

		return dto;
	}

	public static List<TipoEventoDTO> toListLevel0DTO(List<TipoEvento> listEntidad) {
		List<TipoEventoDTO> listDto = new ArrayList<TipoEventoDTO>();
		for (TipoEvento entidad : listEntidad) {
			listDto.add(toLevel0DTO(entidad));
		}
		return listDto;
	}

	public static List<TipoEventoDTO> toListLevel1DTO(List<TipoEvento> listEntidad) {
		List<TipoEventoDTO> listDto = new ArrayList<TipoEventoDTO>();
		for (TipoEvento entidad : listEntidad) {
			listDto.add(toLevel1DTO(entidad));
		}
		return listDto;
	}

	// --- fin to DTO
	// --- to Entidad
	public static TipoEvento toLevel0Entity(TipoEventoDTO dto, TipoEvento entidad) {
		if (null == entidad) {
			entidad = new TipoEvento();
		}
		entidad.setId(dto.getId());
		entidad.setCodigo(dto.getCodigo());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setActivo(dto.getEstado());
		entidad.setNombre(dto.getNombre());
		entidad.setSigla(dto.getSigla());

		return entidad;
	}

	public static TipoEvento toLevel1Entity(TipoEventoDTO dto, TipoEvento entidad) {
		entidad = toLevel0Entity(dto, entidad);

		return entidad;
	}

	public static List<TipoEvento> toListLevel0Entity(List<TipoEventoDTO> listDto) {
		List<TipoEvento> listEntidad = new ArrayList<TipoEvento>();
		for (TipoEventoDTO dto : listDto) {
			listEntidad.add(toLevel0Entity(dto, null));
		}
		return listEntidad;
	}

	public static List<TipoEvento> toListLevel1Entity(List<TipoEventoDTO> listDto) {
		List<TipoEvento> listEntidad = new ArrayList<TipoEvento>();
		for (TipoEventoDTO dto : listDto) {
			listEntidad.add(toLevel1Entity(dto, null));
		}
		return listEntidad;
	}
	// --- fin to Entidad
}
