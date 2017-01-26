package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.ComparendoConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDetalleDTO;
import co.com.datatools.fachadainetegracionweb.dto.EvidenciasDTO;

@Remote
public interface IRFachadaComparendos {

    /**
     * 
     * @param comparendoConsultaDto
     * @return
     */
    public List<ComparendoReplicaDTO> consultarComparendos(ComparendoConsultaDTO comparendoConsultaDto);

    /**
     * 
     * @param numeroComparendo
     * @return
     */
    public ComparendoReplicaDetalleDTO consultarDetalleComparendo(String numeroComparendo);

    /**
     * Se encarga de traer las rutas de las evidencias para cada factura
     * 
     * @author giovanni.velandia
     * @param facturaAxis
     * @return
     */
    public List<EvidenciasDTO> rutaEvidencias(Long facturaAxis);
}
