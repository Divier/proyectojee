package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import javax.ejb.Remote;

@Remote
public interface IRHomologacion {

    /**
     * Consulta el valor asociado al valor homologable recibido, de acuerdo al origen de homologación.
     * 
     * @param origenHomologacion
     * @param valorHomologable
     * @return Retorna el valor asociado al valor homologable recibido
     * @author julio.pinzon
     */
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable);
}
