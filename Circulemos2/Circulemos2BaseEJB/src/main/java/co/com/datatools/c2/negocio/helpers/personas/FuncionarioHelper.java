package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class FuncionarioHelper {
    // --- to DTO
    public static FuncionarioDTO toLevel0DTO(Funcionario entidad) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(entidad.getId());
        dto.setCodigoProceso(entidad.getCodigoProceso());
        dto.setFechaFinalVigencia(entidad.getFechaFinalVigencia());
        dto.setFechaInicioVigencia(entidad.getFechaInicioVigencia());
        dto.setIdCargo(entidad.getIdCargo());
        dto.setMemoNombramiento(entidad.getMemoNombramiento());
        dto.setFechaNombramiento(entidad.getFechaNombramiento());

        return dto;
    }

    public static FuncionarioDTO toLevel1DTO(Funcionario entidad) {
        FuncionarioDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));

        return dto;
    }

    public static List<FuncionarioDTO> toListLevel0DTO(List<Funcionario> listEntidad) {
        List<FuncionarioDTO> listDto = new ArrayList<FuncionarioDTO>();
        for (Funcionario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FuncionarioDTO> toListLevel1DTO(List<Funcionario> listEntidad) {
        List<FuncionarioDTO> listDto = new ArrayList<FuncionarioDTO>();
        for (Funcionario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Funcionario toLevel0Entity(FuncionarioDTO dto, Funcionario entidad) {
        if (null == entidad) {
            entidad = new Funcionario();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoProceso(dto.getCodigoProceso());
        entidad.setFechaFinalVigencia(dto.getFechaFinalVigencia());
        entidad.setFechaInicioVigencia(dto.getFechaInicioVigencia());
        entidad.setIdCargo(dto.getIdCargo());
        entidad.setMemoNombramiento(dto.getMemoNombramiento());
        entidad.setFechaNombramiento(dto.getFechaNombramiento());

        return entidad;
    }

    public static Funcionario toLevel1Entity(FuncionarioDTO dto, Funcionario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }

        return entidad;
    }

    public static List<Funcionario> toListLevel0Entity(List<FuncionarioDTO> listDto) {
        List<Funcionario> listEntidad = new ArrayList<Funcionario>();
        for (FuncionarioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Funcionario> toListLevel1Entity(List<FuncionarioDTO> listDto) {
        List<Funcionario> listEntidad = new ArrayList<Funcionario>();
        for (FuncionarioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
