package co.com.datatools.c2.adaptador.proceso;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.adaptador.dto.ComparendoProcesoTrazabilidadDTO;
import co.com.datatools.c2.adaptador.dto.ProcesoComparendoDTO;

/**
 * Interfaz para acceder a los servicios del m�dulo de procesos desde el m�dulo de comparendos
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRProcesoComparendo {

    /**
     * Servicio que valida si existe un proceso asociado a un comparendo con los filtros indicados (tipo de proceso, estado de proceso, n�mero de
     * comparendo). En la implementaci�n debe realizar la conversi�n del par�metro ProcesoComparendoDTO a ProcesoDTO
     * 
     * @param procesoComparendoDTO
     * @return Retorna true si existe un proceso con las caracter�sticas del proceso indicado. false de lo contrario.
     */
    public boolean existeProceso(ProcesoComparendoDTO procesoComparendoDTO);

    /**
     * Metodo que se encarga de consultar todos los proceso relacionados con un comparendo en especifico
     * 
     * @author giovanni.velandia
     * @param cicomparendo
     * @return
     */
    public List<ComparendoProcesoTrazabilidadDTO> consultarTrazabilidadProcesos(Long cicomparendo);
}
