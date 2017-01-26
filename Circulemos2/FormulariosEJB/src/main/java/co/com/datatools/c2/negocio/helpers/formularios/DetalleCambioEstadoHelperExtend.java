package co.com.datatools.c2.negocio.helpers.formularios;

import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.entidades.CausalCambioEstado;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.DocumentoFormulario;

public class DetalleCambioEstadoHelperExtend extends DetalleCambioEstadoHelper {

    /**
     * Pertmite llevar a cabo la carga del responsable a nivel uno de su entidad a DTO desde los datos consultados.
     * 
     * @param entidad
     *            con los datos correspondientes a ser transformado
     * @return detalle cambio estado dto con los datos del responsable.
     */
    public static DetalleCambioEstadoDTO toLevel1DTO(DetalleCambioEstado entidad) {
        DetalleCambioEstadoDTO dto = DetalleCambioEstadoHelper.toLevel1DTO(entidad);
        if (entidad.getResponsableFormulario() != null)
            dto.setResponsableFormulario(ResponsableFormularioHelper.toLevel1DTO(entidad.getResponsableFormulario()));

        return dto;
    }

    /**
     * Permite devolver la entidad DetalleCambioEstado con los valores a ser actualizados de documento y causal.
     * 
     * @param dto
     *            con los datos correspondientes a ser transformados
     * @param entidad
     *            con los datos vigentes
     * @return detalle cambio estado dto con los datos del Documento y la Causal de cambio de estado.
     */
    public static DetalleCambioEstado toLevel1Entity(DetalleCambioEstadoDTO dto, DetalleCambioEstado entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDocumentoFormulario() != null) {
            entidad.setDocumentoFormulario(new DocumentoFormulario());
            entidad.getDocumentoFormulario().setId(dto.getDocumentoFormulario().getId());
            entidad.getDocumentoFormulario().setNumeroDocumento(dto.getDocumentoFormulario().getNumeroDocumento());
            entidad.getDocumentoFormulario().setIdDocumento(dto.getDocumentoFormulario().getIdDocumento());
        }
        if (dto.getCausalCambioEstado() != null) {
            entidad.setCausalCambioEstado(new CausalCambioEstado());
            entidad.getCausalCambioEstado().setId(dto.getCausalCambioEstado().getId());
        }
        return entidad;
    }
}
