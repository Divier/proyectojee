package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;

@Remote
public interface IRFachadaNotificacionTerceros {

    public Integer[] enviaNotificaciones(EnvioNotificacionDTO notificaciones);

    /**
     * Permite consultar el estado de notificacion de un circuito
     * 
     * @author divier.casas
     * @return
     */
    public Integer[] consultarNotificaciones();

    /**
     * Permite consultar el parametro de configuracion para determinar si una notificacion se envia por un tercero o no.
     * 
     * @param codigoOrganismo
     * @return
     * @author divier.casas
     */
    public ValorParametroDTO consultarParametroEnvioNotificaciones(int codigoOrganismo);
}