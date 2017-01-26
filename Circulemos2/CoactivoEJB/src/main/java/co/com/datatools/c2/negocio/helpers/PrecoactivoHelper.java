package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PrecoactivoDTO;
import co.com.datatools.c2.entidades.CargueCoactivo;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.EstadoPrecoactivo;
import co.com.datatools.c2.entidades.Precoactivo;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.cartera.CarteraHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.personas.FuncionarioHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:12 COT 2016
 */
public class PrecoactivoHelper {
    // --- to DTO
    public static PrecoactivoDTO toLevel0DTO(Precoactivo entidad) {
        PrecoactivoDTO dto = new PrecoactivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoObligacion(entidad.getCodigoTipoObligacion());
        dto.setFechaObligacion(entidad.getFechaObligacion());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorInteresMoratorios(entidad.getValorInteresMoratorios());
        dto.setValorObligacion(entidad.getValorObligacion());

        return dto;
    }

    public static PrecoactivoDTO toLevel1DTO(Precoactivo entidad) {
        PrecoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getCargueCoactivo() != null)
            dto.setCargueCoactivo(CargueCoactivoHelper.toLevel0DTO(entidad.getCargueCoactivo()));
        if (entidad.getEstadoPrecoactivo() != null)
            dto.setEstadoPrecoactivo(EstadoPrecoactivoHelper.toLevel0DTO(entidad.getEstadoPrecoactivo()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getFuncionario() != null)
            dto.setFuncionario(FuncionarioHelper.toLevel0DTO(entidad.getFuncionario()));
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<PrecoactivoDTO> toListLevel0DTO(List<Precoactivo> listEntidad) {
        List<PrecoactivoDTO> listDto = new ArrayList<PrecoactivoDTO>();
        for (Precoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PrecoactivoDTO> toListLevel1DTO(List<Precoactivo> listEntidad) {
        List<PrecoactivoDTO> listDto = new ArrayList<PrecoactivoDTO>();
        for (Precoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Precoactivo toLevel0Entity(PrecoactivoDTO dto, Precoactivo entidad) {
        if (null == entidad) {
            entidad = new Precoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoObligacion(dto.getCodigoTipoObligacion());
        entidad.setFechaObligacion(dto.getFechaObligacion());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorInteresMoratorios(dto.getValorInteresMoratorios());
        entidad.setValorObligacion(dto.getValorObligacion());

        return entidad;
    }

    public static Precoactivo toLevel1Entity(PrecoactivoDTO dto, Precoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCargueCoactivo() != null) {
            entidad.setCargueCoactivo(new CargueCoactivo());
            entidad.getCargueCoactivo().setId(dto.getCargueCoactivo().getId());
        }
        if (dto.getEstadoPrecoactivo() != null) {
            entidad.setEstadoPrecoactivo(new EstadoPrecoactivo());
            entidad.getEstadoPrecoactivo().setId(dto.getEstadoPrecoactivo().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getFuncionario() != null) {
            entidad.setFuncionario(new Funcionario());
            entidad.getFuncionario().setId(dto.getFuncionario().getId());
        }
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }

        return entidad;
    }

    public static List<Precoactivo> toListLevel0Entity(List<PrecoactivoDTO> listDto) {
        List<Precoactivo> listEntidad = new ArrayList<Precoactivo>();
        for (PrecoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Precoactivo> toListLevel1Entity(List<PrecoactivoDTO> listDto) {
        List<Precoactivo> listEntidad = new ArrayList<Precoactivo>();
        for (PrecoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
