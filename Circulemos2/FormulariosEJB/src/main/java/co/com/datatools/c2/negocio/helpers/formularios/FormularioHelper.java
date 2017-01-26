package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.entidades.Formulario;
import co.com.datatools.c2.entidades.RangoFormulario;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Fri Sep 04 16:13:18 COT 2015
 */
public class FormularioHelper {
	// --- to DTO
	public static FormularioDTO toLevel0DTO(Formulario entidad) {
		FormularioDTO dto = new FormularioDTO();
		dto.setId(entidad.getId());
		dto.setNumeroFormulario(entidad.getNumeroFormulario());

		return dto;
	}

	public static FormularioDTO toLevel1DTO(Formulario entidad) {
		FormularioDTO dto = toLevel0DTO(entidad);
		if (entidad.getEstadoFormulario() != null)
			dto.setEstadoFormulario(EstadoFormularioHelper.toLevel0DTO(entidad
					.getEstadoFormulario()));
		if (entidad.getTipoFormulario() != null)
			dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad
					.getTipoFormulario()));
		if (entidad.getResponsableFormulario() != null)
			dto.setResponsableFormulario(ResponsableFormularioHelper
					.toLevel0DTO(entidad.getResponsableFormulario()));
		if (entidad.getRangoFormulario() != null)
			dto.setRangoFormulario(RangoFormularioHelper.toLevel0DTO(entidad
					.getRangoFormulario()));
		if (entidad.getCodigoOrganismo() != null)
			dto.setCodigoOrganismo(OrganismoTransitoHelper.toLevel0DTO(entidad
					.getCodigoOrganismo()));
		if (entidad.getDetalleCambioEstado() != null)
			dto.setDetalleCambioEstado(DetalleCambioEstadoHelper
					.toLevel0DTO(entidad.getDetalleCambioEstado()));

		return dto;
	}

	public static List<FormularioDTO> toListLevel0DTO(
			List<Formulario> listEntidad) {
		List<FormularioDTO> listDto = new ArrayList<FormularioDTO>();
		for (Formulario entidad : listEntidad) {
			listDto.add(toLevel0DTO(entidad));
		}
		return listDto;
	}

	public static List<FormularioDTO> toListLevel1DTO(
			List<Formulario> listEntidad) {
		List<FormularioDTO> listDto = new ArrayList<FormularioDTO>();
		for (Formulario entidad : listEntidad) {
			listDto.add(toLevel1DTO(entidad));
		}
		return listDto;
	}

	// --- fin to DTO
	// --- to Entidad
	public static Formulario toLevel0Entity(FormularioDTO dto,
			Formulario entidad) {
		if (null == entidad) {
			entidad = new Formulario();
		}
		entidad.setId(dto.getId());
		entidad.setNumeroFormulario(dto.getNumeroFormulario());

		return entidad;
	}

	public static Formulario toLevel1Entity(FormularioDTO dto,
			Formulario entidad) {
		entidad = toLevel0Entity(dto, entidad);
		if (dto.getEstadoFormulario() != null) {
			entidad.setEstadoFormulario(new EstadoFormulario());
			entidad.getEstadoFormulario().setId(
					dto.getEstadoFormulario().getId());
		}
		if (dto.getTipoFormulario() != null) {
			entidad.setTipoFormulario(new TipoFormulario());
			entidad.getTipoFormulario().setId(dto.getTipoFormulario().getId());
		}
		if (dto.getResponsableFormulario() != null) {
			entidad.setResponsableFormulario(new ResponsableFormulario());
			entidad.getResponsableFormulario().setId(
					dto.getResponsableFormulario().getId());
		}
		if (dto.getRangoFormulario() != null) {
			entidad.setRangoFormulario(new RangoFormulario());
			entidad.getRangoFormulario()
					.setId(dto.getRangoFormulario().getId());
		}
		if (dto.getCodigoOrganismo() != null) {
			entidad.setCodigoOrganismo(new OrganismoTransito());
			entidad.getCodigoOrganismo().setCodigoOrganismo(
					dto.getCodigoOrganismo().getCodigoOrganismo());
		}
		if (dto.getDetalleCambioEstado() != null) {
			entidad.setDetalleCambioEstado(new DetalleCambioEstado());
			entidad.getDetalleCambioEstado().setId(
					dto.getDetalleCambioEstado().getId());
		}

		return entidad;
	}

	public static List<Formulario> toListLevel0Entity(
			List<FormularioDTO> listDto) {
		List<Formulario> listEntidad = new ArrayList<Formulario>();
		for (FormularioDTO dto : listDto) {
			listEntidad.add(toLevel0Entity(dto, null));
		}
		return listEntidad;
	}

	public static List<Formulario> toListLevel1Entity(
			List<FormularioDTO> listDto) {
		List<Formulario> listEntidad = new ArrayList<Formulario>();
		for (FormularioDTO dto : listDto) {
			listEntidad.add(toLevel1Entity(dto, null));
		}
		return listEntidad;
	}
	// --- fin to Entidad
}
