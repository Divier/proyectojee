package co.com.datatools.c2.negocio.helpers.extencion;

import co.com.datatools.c2.dto.comparendo.BloqueoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleBloqueoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.UnificacionInconsistenciasComparendoDTO;

/**
 * Clase utilitaria que se encarga de transformar objetos de errores de procesamiento comparendo a una unifacion de errores
 * 
 * @author giovanni.velandia
 * 
 */
public class UnificacionInconCompHelperExtend {

    /**
     * Metodo que se encarga de convertir de procesa comparendo a unificacion
     * 
     * @author giovanni.velandia
     * @param procesaComparendoDTO
     * @param detalleProcesamientoDTO
     * @return
     */
    public static UnificacionInconsistenciasComparendoDTO toUnificacionInconsistenciasComparendoDTO(
            ProcesaComparendoDTO procesaComparendoDTO, DetalleProcesamientoDTO detalleProcesamientoDTO,
            ProcesaComparendoPersonaDTO procesaComparendoPersonaDTO) {
        UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO = new UnificacionInconsistenciasComparendoDTO();

        // Apellido infractor 1
        unificacionInconsistenciasComparendoDTO.setApellido1Infractor(procesaComparendoPersonaDTO.getApellido1());
        // Apelliso infractor 2
        unificacionInconsistenciasComparendoDTO.setApellido2Infractor(procesaComparendoPersonaDTO.getApellido2());
        // Tipo Identificacion Infractor
        unificacionInconsistenciasComparendoDTO.setIdTipoIdentificacionInfractor(procesaComparendoPersonaDTO
                .getIdTipoIdentificacion());
        // Nombre infractor 1
        unificacionInconsistenciasComparendoDTO.setNombre1Infractor(procesaComparendoPersonaDTO.getNombre1());
        // Nombre infractor 2
        unificacionInconsistenciasComparendoDTO.setApellido2Infractor(procesaComparendoPersonaDTO.getNombre2());
        // Numero identificacion infractor
        unificacionInconsistenciasComparendoDTO.setNumeroIdentificacionInfractor(procesaComparendoPersonaDTO
                .getNumeroIdentificacion());
        // id
        unificacionInconsistenciasComparendoDTO.setId(procesaComparendoDTO.getId());
        // Codigo Infraccion
        unificacionInconsistenciasComparendoDTO.setCodigoInfraccion(procesaComparendoDTO.getCodigoInfraccion());
        // Codigo de Origen
        unificacionInconsistenciasComparendoDTO.setCodigoOrigen(procesaComparendoDTO.getCodigoOrigen());
        unificacionInconsistenciasComparendoDTO.setNombreOrigen(procesaComparendoDTO.getNombreOrigen());
        // Error procesamiento
        unificacionInconsistenciasComparendoDTO.setErrorProcesamiento(detalleProcesamientoDTO.getErrorProcesamiento()
                .getDescripcion());
        // Fecha Infraccion
        unificacionInconsistenciasComparendoDTO.setFechaInfraccion(procesaComparendoDTO.getFechaInfraccion());
        // Fecha registro
        unificacionInconsistenciasComparendoDTO.setFechaRegistro(procesaComparendoDTO.getFechaRecepcion());
        // Fecha Reporte
        unificacionInconsistenciasComparendoDTO.setFechaReporte(procesaComparendoDTO.getFechaReporte());
        // Hora Infraccion
        unificacionInconsistenciasComparendoDTO.setHoraInfraccion(procesaComparendoDTO.getHoraInfraccion());
        // Campo Inconsistencias
        unificacionInconsistenciasComparendoDTO.setNombreCampoInconsistencias(detalleProcesamientoDTO.getCampoEntidad()
                .getNombre());
        // Numero del comparendo
        unificacionInconsistenciasComparendoDTO.setNumeroComparendo(procesaComparendoDTO.getNumeroComparendo());
        // Usuario persona
        unificacionInconsistenciasComparendoDTO.setUsuarioPersona(procesaComparendoDTO.getUsuarioPersona());
        // Placa
        unificacionInconsistenciasComparendoDTO.setPlacaVehiculo(procesaComparendoDTO.getPlacaVehiculo());
        return unificacionInconsistenciasComparendoDTO;
    }

    /**
     * Metodo que se encarga de convertir de Bloqueo comparendo a unificacion
     * 
     * @author giovanni.velandia
     * @param bloqueoComparendoDTO
     * @param detalleBloqueoDTO
     * @return
     */
    public static UnificacionInconsistenciasComparendoDTO toUnificacionInconsistenciasComparendoDTO(
            BloqueoComparendoDTO bloqueoComparendoDTO, DetalleBloqueoDTO detalleBloqueoDTO) {
        UnificacionInconsistenciasComparendoDTO unificacionInconsistenciasComparendoDTO = new UnificacionInconsistenciasComparendoDTO();
        // id
        unificacionInconsistenciasComparendoDTO.setId(bloqueoComparendoDTO.getId());
        // Apellido infractor 1
        unificacionInconsistenciasComparendoDTO.setApellido1Infractor(bloqueoComparendoDTO.getApellido1Infractor());
        // Apelliso infractor 2
        unificacionInconsistenciasComparendoDTO.setApellido2Infractor(bloqueoComparendoDTO.getApellido2Infractor());
        // Tipo Identificacion Infractor
        unificacionInconsistenciasComparendoDTO.setIdTipoIdentificacionInfractor(bloqueoComparendoDTO
                .getIdTipoIdentificacionInfractor());
        // Nombre infractor 1
        unificacionInconsistenciasComparendoDTO.setNombre1Infractor(bloqueoComparendoDTO.getNombre1Infractor());
        // Nombre infractor 2
        unificacionInconsistenciasComparendoDTO.setApellido2Infractor(bloqueoComparendoDTO.getNombre2Infractor());
        // Numero identificacion infractor
        unificacionInconsistenciasComparendoDTO.setNumeroIdentificacionInfractor(bloqueoComparendoDTO
                .getNumeroIdentificacionInfractor());
        // Codigo Infraccion
        unificacionInconsistenciasComparendoDTO.setCodigoInfraccion(bloqueoComparendoDTO.getCodigoInfraccion());
        // Codigo de Origen
        unificacionInconsistenciasComparendoDTO.setCodigoOrigen(bloqueoComparendoDTO.getCodigoOrigen());
        unificacionInconsistenciasComparendoDTO.setNombreOrigen(bloqueoComparendoDTO.getNombreOrigen());
        // Error procesamiento
        unificacionInconsistenciasComparendoDTO.setErrorProcesamiento(detalleBloqueoDTO.getErrorProcesamiento()
                .getDescripcion());
        // Fecha Infraccion
        unificacionInconsistenciasComparendoDTO.setFechaInfraccion(bloqueoComparendoDTO.getFechaInfraccion());
        // Fecha registro
        unificacionInconsistenciasComparendoDTO.setFechaRegistro(bloqueoComparendoDTO.getFechaRegistro());
        // Fecha Reporte
        unificacionInconsistenciasComparendoDTO.setFechaReporte(bloqueoComparendoDTO.getFechaReporte());
        // Hora Infraccion
        unificacionInconsistenciasComparendoDTO.setHoraInfraccion(bloqueoComparendoDTO.getHoraInfraccion());
        // Campo Inconsistencias
        unificacionInconsistenciasComparendoDTO.setNombreCampoInconsistencias(detalleBloqueoDTO.getCampoEntidad()
                .getNombre());
        // Numero del comparendo
        unificacionInconsistenciasComparendoDTO.setNumeroComparendo(bloqueoComparendoDTO.getNumeroComparendo());
        // Usuario persona
        unificacionInconsistenciasComparendoDTO.setUsuarioPersona(bloqueoComparendoDTO.getUsuarioPersona());
        // PLaca vehiculo
        unificacionInconsistenciasComparendoDTO.setPlacaVehiculo(bloqueoComparendoDTO.getPlacaVehiculo());
        return unificacionInconsistenciasComparendoDTO;
    }

}
