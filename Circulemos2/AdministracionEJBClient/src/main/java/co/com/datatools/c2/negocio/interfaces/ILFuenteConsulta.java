package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;

@Local
public interface ILFuenteConsulta {

    /**
     * @see IRFuenteConsulta#consultarDetallesFuenteConsulta(int, int)
     */
    List<DetalleFuenteConsultaDTO> consultarDetallesFuenteConsulta(int codigoOrganismo, int idTipoFuenteConsulta);

}
