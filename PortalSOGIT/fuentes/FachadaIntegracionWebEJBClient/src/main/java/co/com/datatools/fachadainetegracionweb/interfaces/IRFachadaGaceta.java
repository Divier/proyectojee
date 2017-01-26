package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.DetalleGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FechaPublicacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.FiltrosGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.GacetaDTO;

@Remote
public interface IRFachadaGaceta {

    /**
     * Metodo que se encarga de consultar la publicacion de la gaceta por fecha de publicacion
     * 
     * @author giovanni.velandia
     * @param fechaPublicacion
     * @return
     */
    public GacetaDTO consultarGaceta(Date fechaPublicacion);

    /**
     * Metodo que se encarga de consultar la publicacion del detalle gaceta por fecha de publicacion
     * 
     * @author giovanni.velandia
     * @param fechaPublicacion
     * @return
     */
    public List<DetalleGacetaDTO> consultarDetalleGaceta(Date fechaPublicacion);

    /**
     * Metodo que se encarga de consultar la publicacion del detalle gaceta por filtros y fecha de publicacion
     * 
     * @author giovanni.velandia
     * @param filtrosGacetaDTO
     * @return
     */
    public List<DetalleGacetaDTO> consultarDetalleGaceta(FiltrosGacetaDTO filtrosGacetaDTO);

    /**
     * Metodo que se encarga de llenar la lista con las posibles fechas de publicacion
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<FechaPublicacionDTO> consultarFechasPublicacion();

}
