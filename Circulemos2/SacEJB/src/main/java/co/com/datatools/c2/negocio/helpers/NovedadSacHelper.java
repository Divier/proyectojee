package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.NovedadSacDTO;
import co.com.datatools.c2.entidades.NovedadSac;
import co.com.datatools.c2.entidades.ObligacionSac;

/**
 * @author Generated
 * @version 3.0 - Tue May 17 17:13:32 COT 2016
 */
public class NovedadSacHelper {
    // --- to DTO
    public static NovedadSacDTO toLevel0DTO(NovedadSac entidad) {
        NovedadSacDTO dto = new NovedadSacDTO();
        dto.setIdNovedadSac(entidad.getIdNovedadSac());
        dto.setFechaNovedad(entidad.getFechaNovedad());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setIdEstadoTransaccionSac(entidad.getIdEstadoTransaccionSac());
        dto.setIdTipoNovedadSac(entidad.getIdTipoNovedadSac());
        dto.setSoporteNovedad(entidad.getSoporteNovedad());
        dto.setValorNovedad(entidad.getValorNovedad());

        return dto;
    }

    public static NovedadSacDTO toLevel1DTO(NovedadSac entidad) {
        NovedadSacDTO dto = toLevel0DTO(entidad);
        if (entidad.getObligacionSac() != null)
            dto.setObligacionSac(ObligacionSacHelper.toLevel0DTO(entidad.getObligacionSac()));

        return dto;
    }

    public static List<NovedadSacDTO> toListLevel0DTO(List<NovedadSac> listEntidad) {
        List<NovedadSacDTO> listDto = new ArrayList<NovedadSacDTO>();
        for (NovedadSac entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<NovedadSacDTO> toListLevel1DTO(List<NovedadSac> listEntidad) {
        List<NovedadSacDTO> listDto = new ArrayList<NovedadSacDTO>();
        for (NovedadSac entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static NovedadSac toLevel0Entity(NovedadSacDTO dto, NovedadSac entidad) {
        if (null == entidad) {
            entidad = new NovedadSac();
        }
        entidad.setIdNovedadSac(dto.getIdNovedadSac());
        entidad.setFechaNovedad(dto.getFechaNovedad());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setIdEstadoTransaccionSac(dto.getIdEstadoTransaccionSac());
        entidad.setIdTipoNovedadSac(dto.getIdTipoNovedadSac());
        entidad.setSoporteNovedad(dto.getSoporteNovedad());
        entidad.setValorNovedad(dto.getValorNovedad());

        return entidad;
    }

    public static NovedadSac toLevel1Entity(NovedadSacDTO dto, NovedadSac entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getObligacionSac() != null) {
            entidad.setObligacionSac(new ObligacionSac());
            entidad.getObligacionSac().setId(dto.getObligacionSac().getId());
        }

        return entidad;
    }

    public static List<NovedadSac> toListLevel0Entity(List<NovedadSacDTO> listDto) {
        List<NovedadSac> listEntidad = new ArrayList<NovedadSac>();
        for (NovedadSacDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<NovedadSac> toListLevel1Entity(List<NovedadSacDTO> listDto) {
        List<NovedadSac> listEntidad = new ArrayList<NovedadSac>();
        for (NovedadSacDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
