package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.negocio.helpers.DocumentoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.EstadoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesoHelper;
import co.com.datatools.c2.negocio.helpers.TrazabilidadProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * Extension para obtener datos adicionales de la trazabilidad
 * 
 * @author julio.pinzon
 *
 */
public class TrazabilidadProcesoHelperExtend extends TrazabilidadProcesoHelper {

    /**
     * Este metodo retorna la trazabilidad asociada a un proceso
     * 
     * @param proceso
     * @return dto de trazabilidad
     * @author julio.pinzon
     */
    public static TrazabilidadProcesoDTO toLevel1DTOExtDocumentos(TrazabilidadProceso trazabilidadProceso) {
        TrazabilidadProcesoDTO trazabilidadProcesoDTO = toLevel1DTO(trazabilidadProceso);
        if (trazabilidadProceso.getDocumentos() != null) {
            trazabilidadProcesoDTO
                    .setDocumentos(DocumentoProcesoHelper.toListLevel1DTO(trazabilidadProceso.getDocumentos()));
        }
        return trazabilidadProcesoDTO;
    }

    /**
     * Este metodo retorna la trazabilidad asociada a un proceso
     * 
     * @param listProceso
     *            lista de entidades de tipo TrazabilidadProceso
     * @return Lista de trazabilidades
     * @author julio.pinzon
     */
    public static List<TrazabilidadProcesoDTO> toListLevel1DTOExtDocumentos(
            List<TrazabilidadProceso> listTrazabilidadProceso) {
        List<TrazabilidadProcesoDTO> lsTrazabilidadProcesoDTO = new ArrayList<TrazabilidadProcesoDTO>();
        for (TrazabilidadProceso trazabilidadProceso : listTrazabilidadProceso) {
            lsTrazabilidadProcesoDTO.add(toLevel1DTOExtDocumentos(trazabilidadProceso));
        }
        return lsTrazabilidadProcesoDTO;
    }

    /**
     * Este metodo retorna la trazabilidad a un proceso por usuario
     * 
     * @param listProceso
     *            lista de entidades de tipo TrazabilidadProceso
     * @return Lista de trazabilidades
     * @author jeison.rodriguez
     */
    public static TrazabilidadProcesoDTO toLevel1DTO(TrazabilidadProceso entidad) {
        TrazabilidadProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel1DTO(entidad.getProceso()));
        if (entidad.getEstadoProceso() != null)
            dto.setEstadoProceso(EstadoProcesoHelper.toLevel1DTO(entidad.getEstadoProceso()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel1DTO(entidad.getUsuario()));

        return dto;
    }
}
