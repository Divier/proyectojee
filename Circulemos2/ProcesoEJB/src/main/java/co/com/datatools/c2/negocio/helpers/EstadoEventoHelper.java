package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 14 15:37:25 COT 2016
 */
public class EstadoEventoHelper {
	// --- to DTO
	public static EstadoEventoDTO toLevel0DTO(EstadoEvento entidad) {
		EstadoEventoDTO dto = new EstadoEventoDTO();
		dto.setId(entidad.getId());
		dto.setCodigo(entidad.getCodigo());
		dto.setDescripcion(entidad.getDescripcion());
		dto.setEstado(entidad.getActivo());
		dto.setNombre(entidad.getNombre());
		dto.setSigla(entidad.getSigla());

		return dto;
	}

	public static EstadoEventoDTO toLevel1DTO(EstadoEvento entidad) {
		EstadoEventoDTO dto = toLevel0DTO(entidad);

		return dto;
	}

	public static List<EstadoEventoDTO> toListLevel0DTO(List<EstadoEvento> listEntidad) {
		List<EstadoEventoDTO> listDto = new ArrayList<EstadoEventoDTO>();
		for (EstadoEvento entidad : listEntidad) {
			listDto.add(toLevel0DTO(entidad));
		}
		return listDto;
	}

	public static List<EstadoEventoDTO> toListLevel1DTO(List<EstadoEvento> listEntidad) {
		List<EstadoEventoDTO> listDto = new ArrayList<EstadoEventoDTO>();
		for (EstadoEvento entidad : listEntidad) {
			listDto.add(toLevel1DTO(entidad));
		}
		return listDto;
	}

	// --- fin to DTO
	// --- to Entidad
	public static EstadoEvento toLevel0Entity(EstadoEventoDTO dto, EstadoEvento entidad) {
		if (null == entidad) {
			entidad = new EstadoEvento();
		}
		entidad.setId(dto.getId());
		entidad.setCodigo(dto.getCodigo());
		entidad.setDescripcion(dto.getDescripcion());
		entidad.setActivo(dto.getEstado());
		entidad.setNombre(dto.getNombre());
		entidad.setSigla(dto.getSigla());

		return entidad;
	}

	public static EstadoEvento toLevel1Entity(EstadoEventoDTO dto, EstadoEvento entidad) {
		entidad = toLevel0Entity(dto, entidad);

		return entidad;
	}

	public static List<EstadoEvento> toListLevel0Entity(List<EstadoEventoDTO> listDto) {
		List<EstadoEvento> listEntidad = new ArrayList<EstadoEvento>();
		for (EstadoEventoDTO dto : listDto) {
			listEntidad.add(toLevel0Entity(dto, null));
		}
		return listEntidad;
	}

	public static List<EstadoEvento> toListLevel1Entity(List<EstadoEventoDTO> listDto) {
		List<EstadoEvento> listEntidad = new ArrayList<EstadoEvento>();
		for (EstadoEventoDTO dto : listDto) {
			listEntidad.add(toLevel1Entity(dto, null));
		}
		return listEntidad;
	}
	// --- fin to Entidad
}
