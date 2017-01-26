package co.com.datatools.c2.negocio.helpers.extension;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.entidades.financiacion.ItFinanciacion;
import co.com.datatools.c2.negocio.helpers.ItDetalleCuotasFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ItDetalleFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ItFinanciacionHelper;

public class ItFinanciacionHelperExtend extends ItFinanciacionHelper {

    public static List<ItFinanciacionDTO> toListLevel1DTO(List<ItFinanciacion> listEntidad) {
        List<ItFinanciacionDTO> listDto = new ArrayList<ItFinanciacionDTO>();
        for (ItFinanciacion entidad : listEntidad) {
            ItFinanciacionDTO itFinanciacionDTO = toLevel0DTO(entidad);
            if (entidad.getDetalleCuotasFinanciacionList() != null) {
                itFinanciacionDTO.setDetalleCuotasFinanciacionList(ItDetalleCuotasFinanciacionHelper
                        .toListLevel1DTO(entidad.getDetalleCuotasFinanciacionList()));
            }
            if (entidad.getDetalleFinanciacionList() != null) {
                itFinanciacionDTO.setDetalleFinanciacionList(ItDetalleFinanciacionHelper.toListLevel1DTO(entidad
                        .getDetalleFinanciacionList()));
            }
            listDto.add(itFinanciacionDTO);
        }
        return listDto;
    }
}
