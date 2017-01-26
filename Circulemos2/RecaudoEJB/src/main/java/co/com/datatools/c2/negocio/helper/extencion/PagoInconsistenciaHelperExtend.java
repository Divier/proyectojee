package co.com.datatools.c2.negocio.helper.extencion;

import java.util.List;

import co.com.datatools.c2.dto.PagoInconsistenciaDTO;
import co.com.datatools.c2.entidades.DetallePagoInconsistencia;
import co.com.datatools.c2.entidades.ErrorInconsistenciaPago;
import co.com.datatools.c2.entidades.PagoInconsistencia;
import co.com.datatools.c2.negocio.helpers.ErrorInconsistenciaPagoHelper;
import co.com.datatools.c2.negocio.helpers.PagoInconsistenciaHelper;

public class PagoInconsistenciaHelperExtend extends PagoInconsistenciaHelper {

    public static PagoInconsistencia toLevel1Entity(PagoInconsistenciaDTO dto, PagoInconsistencia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (!dto.getDetallePagoInconsistencias().isEmpty()) {
            List<DetallePagoInconsistencia> detallePagoInconsistenciaList = null;
            detallePagoInconsistenciaList = DetallePagoInconsistenciaHelperExtend.toListLevel1Entity(
                    dto.getDetallePagoInconsistencias(), entidad);
            entidad.setDetallePagoInconsistencias(detallePagoInconsistenciaList);
        }

        if (!dto.getErrorInconsistenciaPagos().isEmpty()) {
            List<ErrorInconsistenciaPago> errorInconsistenciaPagoList = null;
            errorInconsistenciaPagoList = ErrorInconsistenciaPagoHelper.toListLevel1Entity(dto
                    .getErrorInconsistenciaPagos());
            entidad.setErrorInconsistenciaPagos(errorInconsistenciaPagoList);
        }

        return entidad;
    }

}
