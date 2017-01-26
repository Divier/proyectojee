package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import javax.ejb.Local;

@Local
public interface ILHomologacion {

    /**
     * @see IRHomologacion#obtenerValor(Mapeable, String)
     */
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable);
}
