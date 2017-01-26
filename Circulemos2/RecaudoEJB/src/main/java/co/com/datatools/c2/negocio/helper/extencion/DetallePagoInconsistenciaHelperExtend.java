package co.com.datatools.c2.negocio.helper.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaDTO;
import co.com.datatools.c2.entidades.DetallePagoInconsistencia;
import co.com.datatools.c2.entidades.PagoInconsistencia;
import co.com.datatools.c2.negocio.helpers.DetallePagoInconsistenciaHelper;
import co.com.datatools.c2.negocio.helpers.ErrorInconsistenciaPagoHelper;

public class DetallePagoInconsistenciaHelperExtend extends DetallePagoInconsistenciaHelper {

    public static List<DetallePagoInconsistencia> toListLevel1Entity(List<DetallePagoInconsistenciaDTO> listDto,
            PagoInconsistencia pagoInconsistencia) {
        List<DetallePagoInconsistencia> listEntidad = new ArrayList<DetallePagoInconsistencia>();
        for (DetallePagoInconsistenciaDTO dto : listDto) {
            DetallePagoInconsistencia detallePagoInconsistencia = new DetallePagoInconsistencia();
            if (!dto.getErrorInconsistenciaPagos().isEmpty()) {

                detallePagoInconsistencia.setErrorInconsistenciaPagos(ErrorInconsistenciaPagoHelper
                        .toListLevel0Entity(dto.getErrorInconsistenciaPagos()));

                detallePagoInconsistencia.setPagoInconsistencia(pagoInconsistencia);
            }
            detallePagoInconsistencia = DetallePagoInconsistenciaHelper.toLevel1Entity(dto, detallePagoInconsistencia);
            listEntidad.add(detallePagoInconsistencia);
        }
        return listEntidad;
    }

}
