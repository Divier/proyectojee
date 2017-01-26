package co.com.datatools.seguridad.helper;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.entidades.HistoricoRol;

public class HistoricoRolHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de HistoricoRol
     * @return Dto del Rol con los correspodientes datos de la entidad
     */
    public HistoricoRolDto toDto(HistoricoRol entidad) {
        HistoricoRolDto dto = new HistoricoRolDto();
        dto.setFechaFinRol(entidad.getFechaFinRol());
        dto.setFechaInicioRol(entidad.getFechaInicioRol());
        dto.setIdHistoricoRol(entidad.getIdHistoricoRol());
        dto.setXmlHistorico(entidad.getXmlHistorico());
        dto.setUsuarioModifica(entidad.getUsuarioCambio());
        if (entidad.getXsdHistorico() != null) {
            dto.setIdXsdHistorico(entidad.getXsdHistorico().getIdXsdHistorico());
            dto.setContenidoXsd(entidad.getXsdHistorico().getContenidoXsd());
        }
        return dto;
    }

}
