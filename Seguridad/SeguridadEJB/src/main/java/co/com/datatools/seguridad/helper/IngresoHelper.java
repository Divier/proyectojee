package co.com.datatools.seguridad.helper;

import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.entidades.IngresoUsuario;

public class IngresoHelper {

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples
     * 
     * @param entidad
     *            Contiene los datos de la entidad de HistoricoUsuario
     * @return Dto de Usuario con los correspodientes datos de la entidad
     */
    public IngresoDto toDto(IngresoUsuario entidad) {
        IngresoDto dto = new IngresoDto();
        dto.setEstadoIngreso(entidad.getEstadoIngreso().getNombre());
        dto.setFechaCierre(entidad.getFechaCierre());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setId(entidad.getIdIngresoUsuario());
        dto.setIpIngreso(entidad.getIpEquipo());
        dto.setXmlActividadIngreso(entidad.getXmlActividadIngreso());
        dto.setLogin(entidad.getUsuario().getLogin());
        if (entidad.getXsdHistorico() != null) {
            dto.setIdXsdHistorico(entidad.getXsdHistorico().getIdXsdHistorico());
            dto.setContenidoXsd(entidad.getXsdHistorico().getContenidoXsd());
        }
        dto.setIdAplicacion(entidad.getAplicacion().getIdAplicacion());
        dto.setNombreAplicacion(entidad.getAplicacion().getNombreAplicacion());

        return dto;
    }

}
