package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.NovedadImpugnacionSacDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;

@Local
public interface ILSac {

    /**
     * @see IRSac#replicarNovedadesSac(int)
     */
    public RespuestaProcesoSacDTO replicarNovedadesSac(int codigoOrganismo);

    /**
     * @see IRSac#consultarUbicabilidadSac(int)
     */
    RespuestaUbicabilidadSacDTO consultarUbicabilidadSac(int codigoOrganismo);

    /**
     * @see IRSac#registrarUbicabilidadReplicada(int, UbicabilidadSacDTO, Long)
     */
    void registrarUbicabilidadReplicada(int codigoOrganismo, UbicabilidadSacDTO ubicabilidadSacDTO,
            Long idPersonaHistorico);

    /**
     * @see IRSac#actualizarObligacionesFinanciadas(int)
     */
    public RespuestaProcesoSacDTO actualizarObligacionesFinanciadas(int codigoOrganismo);

    /**
     * Metodo que registra el pago en SAC
     * 
     * @param detalle
     *            del pago
     * @param ahora
     *            fecha del registro
     * @return true si la registró
     * @author julio.pinzon 2016-05-24
     */
    public boolean replicarPagoSac(DetallePagoDTO detallePago, Date ahora);

    /**
     * Replica una obligacion a SAC
     * 
     * @param obligacion
     * @param ahora
     * @return true si es exitoso
     * @author julio.pinzon 2016-05-24
     */
    boolean replicaObligacion(ObligacionSacDTO obligacion, Date ahora);

    /**
     * @see IRSac#recibirGestionCobroSac(int)
     */
    public RespuestaProcesoSacDTO recibirGestionCobroSac(int codigoOrganismo);

    /**
     * Actualiza la fecha de notificacion y tipo de notificacion de un comparendo
     * 
     * @param comparendoDTO
     *            Contiene la informacion del comparendo a actualizar
     * @author Dixon.Alvarez
     */
    public void actualizarFechaNotificacionComparendo(ComparendoDTO comparendoDTO);

    /**
     * Metodo que registra una anulacion en SAC
     * 
     * @param comparendoDTO
     * @param fechaActual
     * @return true si la registro
     * @author Dixon.Alvarez 2016-08-24
     */
    public boolean replicarAnulacionSac(ComparendoDTO comparendoDTO, Date fechaActual);

    /**
     * Registra las aperturas de impugnacion
     * 
     * @param novedadImpugnacionSacDTO
     * @param fechaActual
     * @return true si la registro correctamente
     * @author Dixon.Alvarez 2016-08-25
     */
    public boolean replicarAperturaImpugnacionSac(NovedadImpugnacionSacDTO novedadImpugnacionSacDTO, Date fechaActual);

    /**
     * Registra los fallos de impugnacion generados
     * 
     * @param novedadImpugnacionSacDTO
     * @param fechaActual
     * @return true si lo registra exitosamente
     * @author Dixon.Alvarez 2016-08-25
     */
    public boolean replicarFalloImpugnacionSac(NovedadImpugnacionSacDTO novedadImpugnacionSacDTO, Date fechaActual);
}
