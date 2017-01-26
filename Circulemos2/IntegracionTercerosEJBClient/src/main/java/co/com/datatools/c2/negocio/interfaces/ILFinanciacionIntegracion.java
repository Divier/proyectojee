package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;

/**
 * Interface de servicios de integracion a terceros para financiaciones
 * 
 * @author dixon.alvarez
 * 
 */
@Local
public interface ILFinanciacionIntegracion {

    /**
     * @see IRFinanciacionIntegracion#consultarFinanciaciones(Integer, EnumEstadoLectura)
     */
    List<ItFinanciacionDTO> consultarFinanciaciones(Integer codigoOrganismo, EnumEstadoLectura estadoLectura);

    /**
     * @see IRFinanciacionIntegracion#actualizarEstadoFinanciacion(Long, EnumEstadoLectura)
     */
    void actualizarEstadoFinanciacion(Long numeroFinanciacion, EnumEstadoLectura estadoLectura);
}
