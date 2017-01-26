package co.com.datatools.c2.negocio.fachada;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.FalloImpugnacionDTO;

@Remote
public interface IRFachadaImpugnacion {
    /**
     * Cierra las etapas de pruebas a traves del Job
     */
    public void cerrarPruebasJob();

    /**
     * Consulta ultimo fallo de impugnacion de un proceso
     * 
     * @param idProceso
     * @return
     * @author Dixon.Alvarez 2016-08-25
     */
    FalloImpugnacionDTO consultarUltimoFalloImpugnacion(Long idProceso);
}