package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;
import co.com.datatools.c2.negocio.helpers.ImpulsoPruebaHelper;
import co.com.datatools.c2.negocio.helpers.PruebaHelper;
import co.com.datatools.c2.negocio.helpers.SolicitudPruebasBackHelper;

public class SolicitudPruebasBackHelperExtend extends SolicitudPruebasBackHelper {

    public static SolicitudPruebasBackDTO toLevel2DTO(SolicitudPruebasBack entidad) {
        SolicitudPruebasBackDTO dto = toLevel1DTO(entidad);
        if (entidad.getImpulsoPruebas() != null) {
            dto.setImpulsoPruebas(ImpulsoPruebaHelper.toListLevel0DTO(entidad.getImpulsoPruebas()));
        }
        if (entidad.getPruebas() != null) {
            dto.setPruebas(PruebaHelper.toListLevel0DTO(entidad.getPruebas()));
        }
        return dto;
    }

    public static List<SolicitudPruebasBackDTO> toListLevel2DTO(List<SolicitudPruebasBack> listEntidad) {
        List<SolicitudPruebasBackDTO> listDto = new ArrayList<SolicitudPruebasBackDTO>();
        for (SolicitudPruebasBack entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }

}