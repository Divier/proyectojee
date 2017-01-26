package co.com.datatools.fachadainetegracionweb.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.DetalleProcesoImpugnacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.ImpugnacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ProcesoImpugnacionReplicaDTO;

@Remote
public interface IRFachadaImpugnaciones {

    /**
     * 
     * @param impugnacionConsultaDTO
     * @return
     */
    public List<ProcesoImpugnacionReplicaDTO> consultarProcesosImpugnacion(
            ImpugnacionConsultaDTO impugnacionConsultaDTO);

    /**
     * 
     * @param idProceso
     * @return
     */
    public DetalleProcesoImpugnacionDTO consultarDetalleImpugnacion(BigDecimal idProceso);
}
