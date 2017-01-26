package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.PagoInconsistenciaDTO;

@Local
public interface ILInconsistenciaRecaudo {

    /**
     * @see IRInconsistenciaRecaudo#enviarInconsistencias()
     */
    public Integer enviarInconsistencias(Integer codigoOrganismo);

    /**
     * @see IRInconsistenciaRecaudo#registrarInconsistencia(PagoInconsistenciaDTO)
     */
    public void registrarInconsistencia(PagoInconsistenciaDTO inconsistenciaPago);

    /**
     * @see IRInconsistenciaRecaudo#consultarInconsistenciasConciliacion(int, Date, Date)
     */
    public List<PagoDTO> consultarInconsistenciasConciliacion(int organismoTransito, Date fechaInicial, Date fechaFinal);

    /**
     * @see IRInconsistenciaRecaudo#enviarInconsistenciasConciliacion()
     */
    public Integer enviarInconsistenciasConciliacion(Integer organismoTransito);

    /**
     * @see
     */
    public List<DetallePagoInconsistenciaResDTO> consultarPagoInconsistenciasEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public List<DetallePagoInconsistenciaResDTO> consultarPagosInconsistenciasConciliacionEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public void actualizarFechaRegistroInconsistenciaRecaudo(Long idPagoInconsistencia);

    public void actualizarFechaRegistroInconsistenciaConciliacionRecaudo(Long idDetallePago);

}
