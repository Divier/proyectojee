package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.entidades.ConfiguracionFinanciacion;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Fri Jun 17 11:33:16 COT 2016
 */
public class FinanciacionHelper {
    // --- to DTO
    public static FinanciacionDTO toLevel0DTO(Financiacion entidad) {
        FinanciacionDTO dto = new FinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setNumeroFinanciacion(entidad.getNumeroFinanciacion());
        dto.setNumeroReferenciaTerceros(entidad.getNumeroReferenciaTerceros());
        dto.setNumeroCuotas(entidad.getNumeroCuotas());
        dto.setAnio(entidad.getAnio());
        dto.setValorTotalCostasProcesales(entidad.getValorTotalCostasProcesales());
        dto.setJustificacion(entidad.getJustificacion());
        dto.setCantidadObligaciones(entidad.getCantidadObligaciones());
        dto.setTasaInteres(entidad.getTasaInteres());
        dto.setValorMora(entidad.getValorMora());
        dto.setValorTotalSaldoCapitalObligaciones(entidad.getValorTotalSaldoCapitalObligaciones());
        dto.setValorTotalInteresesMoratorios(entidad.getValorTotalInteresesMoratorios());
        dto.setValorTotal(entidad.getValorTotal());
        dto.setValorTotalFinanciarInteres(entidad.getValorTotalFinanciarInteres());
        dto.setIdTramite(entidad.getIdTramite());

        return dto;
    }

    public static FinanciacionDTO toLevel1DTO(Financiacion entidad) {
        FinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getDeudor() != null)
            dto.setDeudor(PersonaHelper.toLevel0DTO(entidad.getDeudor()));
        if (entidad.getCodeudor() != null)
            dto.setCodeudor(PersonaHelper.toLevel0DTO(entidad.getCodeudor()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel0DTO(entidad.getProceso()));
        if (entidad.getConfiguracionFinanciacion() != null)
            dto.setConfiguracionFinanciacion(
                    ConfiguracionFinanciacionHelper.toLevel0DTO(entidad.getConfiguracionFinanciacion()));
        if (entidad.getFuenteInformacion() != null)
            dto.setFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));

        return dto;
    }

    public static List<FinanciacionDTO> toListLevel0DTO(List<Financiacion> listEntidad) {
        List<FinanciacionDTO> listDto = new ArrayList<FinanciacionDTO>();
        for (Financiacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FinanciacionDTO> toListLevel1DTO(List<Financiacion> listEntidad) {
        List<FinanciacionDTO> listDto = new ArrayList<FinanciacionDTO>();
        for (Financiacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Financiacion toLevel0Entity(FinanciacionDTO dto, Financiacion entidad) {
        if (null == entidad) {
            entidad = new Financiacion();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroFinanciacion(dto.getNumeroFinanciacion());
        entidad.setNumeroReferenciaTerceros(dto.getNumeroReferenciaTerceros());
        entidad.setNumeroCuotas(dto.getNumeroCuotas());
        entidad.setAnio(dto.getAnio());
        entidad.setValorTotalCostasProcesales(dto.getValorTotalCostasProcesales());
        entidad.setJustificacion(dto.getJustificacion());
        entidad.setCantidadObligaciones(dto.getCantidadObligaciones());
        entidad.setTasaInteres(dto.getTasaInteres());
        entidad.setValorMora(dto.getValorMora());
        entidad.setValorTotalSaldoCapitalObligaciones(dto.getValorTotalSaldoCapitalObligaciones());
        entidad.setValorTotalInteresesMoratorios(dto.getValorTotalInteresesMoratorios());
        entidad.setValorTotal(dto.getValorTotal());
        entidad.setValorTotalFinanciarInteres(dto.getValorTotalFinanciarInteres());
        entidad.setIdTramite(dto.getIdTramite());

        return entidad;
    }

    public static Financiacion toLevel1Entity(FinanciacionDTO dto, Financiacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDeudor() != null) {
            entidad.setDeudor(new Persona());
            entidad.getDeudor().setId(dto.getDeudor().getId());
        }
        if (dto.getCodeudor() != null) {
            entidad.setCodeudor(new Persona());
            entidad.getCodeudor().setId(dto.getCodeudor().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getProceso() != null) {
            entidad.setProceso(new Proceso());
            entidad.getProceso().setId(dto.getProceso().getId());
        }
        if (dto.getConfiguracionFinanciacion() != null) {
            entidad.setConfiguracionFinanciacion(new ConfiguracionFinanciacion());
            entidad.getConfiguracionFinanciacion().setId(dto.getConfiguracionFinanciacion().getId());
        }

        if (dto.getFuenteInformacion() != null) {
            entidad.setFuenteInformacion(new TipoFuenteInformacion());
            entidad.getFuenteInformacion().setId(dto.getFuenteInformacion().getId());
        }

        return entidad;
    }

    public static List<Financiacion> toListLevel0Entity(List<FinanciacionDTO> listDto) {
        List<Financiacion> listEntidad = new ArrayList<Financiacion>();
        for (FinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Financiacion> toListLevel1Entity(List<FinanciacionDTO> listDto) {
        List<Financiacion> listEntidad = new ArrayList<Financiacion>();
        for (FinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
