package co.com.datatools.c2.negocio.helpers.extension;

import java.util.ArrayList;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.SaldoCartera;
import co.com.datatools.c2.negocio.helpers.cartera.CarteraHelper;
import co.com.datatools.c2.negocio.helpers.cartera.SaldoCarteraHelper;

/**
 * @author julio.pinzon
 * @version 3.0 - Wed Jan 27 10:10:00 COT 2016
 */
public class CarteraHelperExtend extends CarteraHelper {
    // --- to DTO
    public static CarteraDTO toLevel1DTOSaldoCartera(Cartera entidad) {
        CarteraDTO dto = toLevel1DTO(entidad);
        if (entidad.getSaldoCarteras() != null) {
            dto.setSaldoCarteras(new ArrayList<SaldoCarteraDTO>());
            for (SaldoCartera saldo : entidad.getSaldoCarteras()) {
                dto.getSaldoCarteras().add(SaldoCarteraHelper.toLevel1DTO(saldo));
            }
        }

        return dto;
    }
}
