package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.FiltroConsultaInconsistenciasDTO;
import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.RechazoRecaudoResDTO;

@Remote
public interface IRRechazosRecaudo {

    /**
     * Metodo que consulta los recaudos rechazados y notifica via email
     * 
     * @param organismoDTO
     * @return
     */
    public Integer enviarRechazosRecaudos(Integer codigoOrganismo);

    /**
     * Registra un rechazo de un recaudo
     * 
     * @param ItRecaudoRechazoDTO
     */
    public void registrarRechazosRecaudos(RecaudoRechazoDTO recaudoRechazoDTO);

    /**
     * Consulta los rechazos de recaudo de acuerdo a los filtros
     * 
     * @param codigoOrganismo
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<RechazoRecaudoResDTO> consultarRechazosRecaudosEnviados(
            FiltroConsultaInconsistenciasDTO filtroConsultaInconsistenciasDTO);

    public void actualizarFechaRechazoRecaudo(Long id);

}
