package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

@Remote
public interface IRTrazabilidadComparendo {

    /**
     * Retorna true si existe alguna actividad en la trazabilidad del comparendo indicado se encuentra asociada al proceso con el c�digo indicado o
     * alg�n proceso ancestro del mismo tiene dicho c�digo. Valida la existencia del comparendo y el proceso.
     * 
     * @param cicomparendo
     * @param codigoProceso
     * @return
     * @author giovanni.velandia
     */
    public boolean existeProceso(long cicomparendo, Integer codigoProceso);

}