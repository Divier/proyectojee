package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.entidades.CargueCoactivo;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.ConfiguracionCoactivo;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Funcionario;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.FuncionarioHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:38 COT 2016
 */
public class CoactivoHelper {
    // --- to DTO
    public static CoactivoDTO toLevel0DTO(Coactivo entidad) {
        CoactivoDTO dto = new CoactivoDTO();
        dto.setId(entidad.getId());
        dto.setAnio(entidad.getAnio());
        dto.setCantidadObligaciones(entidad.getCantidadObligaciones());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());
        dto.setIdTramite(entidad.getIdTramite());
        dto.setNumeroCoactivo(entidad.getNumeroCoactivo());
        dto.setValorTotalCostasProcesales(entidad.getValorTotalCostasProcesales());
        dto.setValorTotalObligaciones(entidad.getValorTotalObligaciones());

        return dto;
    }

    public static CoactivoDTO toLevel1DTO(Coactivo entidad) {
        CoactivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel0DTO(entidad.getProceso()));
        if (entidad.getCargueCoactivo() != null)
            dto.setCargueCoactivo(CargueCoactivoHelper.toLevel0DTO(entidad.getCargueCoactivo()));
        if (entidad.getConfiguracionCoactivo() != null)
            dto.setConfiguracionCoactivo(ConfiguracionCoactivoHelper.toLevel0DTO(entidad.getConfiguracionCoactivo()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getFuncionario() != null)
            dto.setFuncionario(FuncionarioHelper.toLevel0DTO(entidad.getFuncionario()));

        return dto;
    }

    public static List<CoactivoDTO> toListLevel0DTO(List<Coactivo> listEntidad) {
        List<CoactivoDTO> listDto = new ArrayList<CoactivoDTO>();
        for (Coactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CoactivoDTO> toListLevel1DTO(List<Coactivo> listEntidad) {
        List<CoactivoDTO> listDto = new ArrayList<CoactivoDTO>();
        for (Coactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Coactivo toLevel0Entity(CoactivoDTO dto, Coactivo entidad) {
        if (null == entidad) {
            entidad = new Coactivo();
        }
        entidad.setId(dto.getId());
        entidad.setAnio(dto.getAnio());
        entidad.setCantidadObligaciones(dto.getCantidadObligaciones());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setIdTramite(dto.getIdTramite());
        entidad.setNumeroCoactivo(dto.getNumeroCoactivo());
        entidad.setValorTotalCostasProcesales(dto.getValorTotalCostasProcesales());
        entidad.setValorTotalObligaciones(dto.getValorTotalObligaciones());

        return entidad;
    }

    public static Coactivo toLevel1Entity(CoactivoDTO dto, Coactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getProceso() != null) {
            entidad.setProceso(new Proceso());
            entidad.getProceso().setId(dto.getProceso().getId());
        }
        if (dto.getCargueCoactivo() != null) {
            entidad.setCargueCoactivo(new CargueCoactivo());
            entidad.getCargueCoactivo().setId(dto.getCargueCoactivo().getId());
        }
        if (dto.getConfiguracionCoactivo() != null) {
            entidad.setConfiguracionCoactivo(new ConfiguracionCoactivo());
            entidad.getConfiguracionCoactivo().setId(dto.getConfiguracionCoactivo().getId());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getFuncionario() != null) {
            entidad.setFuncionario(new Funcionario());
            entidad.getFuncionario().setId(dto.getFuncionario().getId());
        }

        return entidad;
    }

    public static List<Coactivo> toListLevel0Entity(List<CoactivoDTO> listDto) {
        List<Coactivo> listEntidad = new ArrayList<Coactivo>();
        for (CoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Coactivo> toListLevel1Entity(List<CoactivoDTO> listDto) {
        List<Coactivo> listEntidad = new ArrayList<Coactivo>();
        for (CoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
