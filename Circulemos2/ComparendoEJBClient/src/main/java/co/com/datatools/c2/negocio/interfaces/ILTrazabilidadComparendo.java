package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

@Local
public interface ILTrazabilidadComparendo {

    /**
     * @see IRTrazabilidadComparendo#existeProceso(long, Integer)
     */
    public boolean existeProceso(long cicomparendo, Integer codigoProceso);
}