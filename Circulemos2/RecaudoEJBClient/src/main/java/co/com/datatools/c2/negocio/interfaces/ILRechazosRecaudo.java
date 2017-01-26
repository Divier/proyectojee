package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.RechazoRecaudoResDTO;

@Local
public interface ILRechazosRecaudo {
    /**
     * @see IRRechazosRecaudo#enviarRechazosRecaudos(Integer codigoOrganismo)
     */
    public Integer enviarRechazosRecaudos(Integer codigoOrganismo);

    /**
     * @see IRRechazosRecaudo#registrarRechazosRecaudos(RecaudoRechazoDTO recaudoRechazoDTO)
     */
    public void registrarRechazosRecaudos(RecaudoRechazoDTO recaudoRechazoDTO);

    /**
     * @see IRRechazosRecaudo#consultarRechazosRecaudosEnviados(Integer codigoOrganismo, Date fechaInicial, Date fechaFinal)
     */
    public List<RechazoRecaudoResDTO> consultarRechazosRecaudosEnviados(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public void actualizarFechaRechazoRecaudo(Long id);

}
