package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface de servicios de integracion a terceros
 * 
 * @author julio.pinzon (2016-05-04)
 * 
 */
@Local
public interface ILRecaudoIntegracion {

    /**
     * @see IRRecaudoIntegracion#consultarRecaudos(int)
     */
    public List<ItRecaudoDTO> consultarRecaudos(int codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * @see IRRecaudoIntegracion#actualizarEstadoRecaudos(Long, EnumEstadoLectura)
     */
    public void actualizarEstadoRecaudo(Long idRecaudo, EnumEstadoLectura estadoLectura);

}
