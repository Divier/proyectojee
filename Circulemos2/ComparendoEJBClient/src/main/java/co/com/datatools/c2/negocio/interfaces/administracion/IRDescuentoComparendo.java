package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.Date;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.ConfiguracionDescuentoDTO;

@Remote
public interface IRDescuentoComparendo {

    /**
     * Consulta una configuracion de descuentos segun los parametros enviados (todos son obligatorios).
     * 
     * @param codigoOrganismo
     *            Codigo de organismo de transito.
     * @param codigoMedioImposicion
     *            Codigo de medio de imposicion.
     * @param codigoTipoNotificacion
     *            ID de tipo de notificacion.
     * @param fechaImposicionComparendo
     *            Fecha de imposicion del comparendo que valida la vigencia de la configuracion de descuento.
     * @return Una configuracion de descuentos que coincida con los parametros enviados. {@code null} si no se envia alguno de los parametros o no
     *         existen resultados de la busqueda.
     * @author divier.casas
     */
    public ConfiguracionDescuentoDTO consultarConfiguracionDescuento(Integer codigoOrganismo,
            Integer codigoMedioImposicion, Integer codigoTipoNotificacion, Date fechaImposicionComparendo);
}