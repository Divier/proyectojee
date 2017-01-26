package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.PagoInconsistenciaDTO;

@Remote
public interface IRInconsistenciaRecaudo {

    /**
     * Envia inconsistencias basicas de negocio vía correo electronico, y retorna la cantidad de registros inconsistentes
     * 
     * @author diego.fonseca MOD(2016-05-17)
     * @return
     */
    public Integer enviarInconsistencias(Integer codigoOrganismo);

    /**
     * Registra inconsistencias basicas de negocio
     * 
     * @author diego.fonseca MOD(2016-05-17)
     * @param inconsistenciaPago
     */
    public void registrarInconsistencia(PagoInconsistenciaDTO inconsistenciaPago);

    /**
     * Consulta las inconsistencias de conciliación por organismo de transito, y por fecha de generación de reporte, en caso que se envie las fechas
     * de generación de reporte en null, se consulta las inconsistencias que no se han enviado
     * 
     * @param organismoTransito
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<PagoDTO> consultarInconsistenciasConciliacion(int organismoTransito, Date fechaInicial, Date fechaFinal);

    /**
     * Envia inconsistencias de conciliación de recaudo vía correo electronico, y retorna la cantidad de registros inconsistentes
     * 
     * @return
     */
    public Integer enviarInconsistenciasConciliacion(Integer organismoTransito);

    public List<DetallePagoInconsistenciaResDTO> consultarPagoInconsistenciasEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public List<DetallePagoInconsistenciaResDTO> consultarPagosInconsistenciasConciliacionEnviadas(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public void actualizarFechaRegistroInconsistenciaRecaudo(Long idPagoInconsistencia);

    public void actualizarFechaRegistroInconsistenciaConciliacionRecaudo(Long idDetallePago);
}
