package co.com.datatools.seguridad.helper;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.entidades.HistoricoUsuario;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para HistoricoUsuario
 * 
 * @author claudia.rodriguez
 * 
 */
public class HistoricoUsuarioHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de HistoricoUsuario
     * @return Dto de Usuario con los correspodientes datos de la entidad
     */
    public HistoricoUsuarioDto toDto(HistoricoUsuario entidad) {
        HistoricoUsuarioDto dto = new HistoricoUsuarioDto();
        dto.setId(entidad.getIdHistoricoUsuario());
        dto.setPassword(entidad.getPassword());
        dto.setFechaModPass(entidad.getFechaModificaPassword());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFinal());
        dto.setXmlHistorico(entidad.getXmlHistorico());
        dto.setUsuarioRealizaCambio(entidad.getUsuarioCambio());
        dto.setDescripcionCambio(entidad.getDescripcionCambio());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de HistoricoUsuario
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de HistoricoUsuario con los correspodientes datos del Dto
     */
    public HistoricoUsuario toEntity(HistoricoUsuarioDto dto, HistoricoUsuario entidad) {
        if (entidad == null)
            entidad = new HistoricoUsuario();
        entidad.setIdHistoricoUsuario(entidad.getIdHistoricoUsuario());
        entidad.setFechaModificaPassword(dto.getFechaModPass());
        entidad.setPassword(dto.getPassword());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFinal(dto.getFechaFin());
        entidad.setUsuarioCambio(dto.getUsuarioRealizaCambio());
        entidad.setDescripcionCambio(dto.getDescripcionCambio());
        return entidad;
    }

}
