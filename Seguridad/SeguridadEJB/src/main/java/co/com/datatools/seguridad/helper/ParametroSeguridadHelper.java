package co.com.datatools.seguridad.helper;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.entidades.ParametroSeguridad;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para los Parametros de seguridad
 * 
 * @author claudia.rodriguez
 * 
 */
public class ParametroSeguridadHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples y tambien el tipo de dato
     * 
     * @param entidad
     *            Contiene los datos de la entidad ParametroSeguridad
     * @return Dto de ParametroSeguridad con los correspodientes datos de la entidad
     */
    public ParametroSeguridadDto toDto(ParametroSeguridad entidad) {
        ParametroSeguridadDto dto = new ParametroSeguridadDto();
        dto.setId(entidad.getIdParametroSeguridad());
        dto.setNombre(entidad.getNombreParametroSeguridad());
        dto.setValor(entidad.getValorParametroSeguridad());
        dto.setIdTipoParametro(entidad.getTipoDato().getIdTipoDato());
        dto.setNombreTipoParametro(entidad.getTipoDato().getNombreTipoDato());
        dto.setEditable(entidad.isEditable());
        return dto;
    }

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de ParametroSeguridad
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de ParametroSeguridad con los correspodientes datos del Dto
     */
    public ParametroSeguridad toEntity(ParametroSeguridadDto dto, ParametroSeguridad entidadBase) {

        if (entidadBase == null) {
            entidadBase = new ParametroSeguridad();
        }
        entidadBase.setNombreParametroSeguridad(dto.getNombre());
        entidadBase.setValorParametroSeguridad(dto.getValor());
        entidadBase.setEditable(dto.isEditable());
        return entidadBase;

    }
}
