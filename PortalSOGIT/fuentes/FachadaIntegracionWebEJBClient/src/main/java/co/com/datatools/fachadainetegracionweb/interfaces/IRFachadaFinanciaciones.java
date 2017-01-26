package co.com.datatools.fachadainetegracionweb.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.DetalleFinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionReplicaDTO;

@Remote
public interface IRFachadaFinanciaciones {

    /**
     * 
     * @param financiacionConsulta
     * @return
     */
    public List<FinanciacionReplicaDTO> consultarFinanciaciones(FinanciacionConsultaDTO financiacionConsulta);

    /**
     * 
     * @param numeroFinanciacion
     * @return
     */
    public DetalleFinanciacionReplicaDTO consultarDetalleFinanciacion(BigDecimal numeroFinanciacion);
}
