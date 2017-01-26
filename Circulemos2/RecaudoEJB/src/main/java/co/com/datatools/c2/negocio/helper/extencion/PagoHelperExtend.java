package co.com.datatools.c2.negocio.helper.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.entidades.Pago;
import co.com.datatools.c2.negocio.helpers.DetallePagoHelper;
import co.com.datatools.c2.negocio.helpers.PagoHelper;

public class PagoHelperExtend extends PagoHelper {
    // --- to DTO
    public static PagoDTO toLevel1DTO(Pago entidad) {
        PagoDTO dto = PagoHelper.toLevel1DTO(entidad);
        dto.setDetallesPago(DetallePagoHelper.toListLevel1DTO(entidad.getDetallesPago()));

        return dto;
    }

    public static List<PagoDTO> toListLevel1DTO(List<Pago> listEntidad) {
        List<PagoDTO> listDto = new ArrayList<PagoDTO>();
        for (Pago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }
    // --- fin to DTO

    // --- to Entidad
    /**
     * Convierte un dto de pago en una entidad agregandole los detalles de pago
     * 
     * @param dto
     * @param entidad
     * @return
     * @author julio.pinzon 2016-08-01
     */
    public static Pago toLevel2Entity(PagoDTO dto, Pago entidad) {
        Pago entity = PagoHelper.toLevel1Entity(dto, entidad);
        entity.setDetallesPago(DetallePagoHelper.toListLevel1Entity(dto.getDetallesPago()));

        return entity;
    }
    // --- fin to Entidad
}
