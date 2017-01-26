package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;

@Remote
public interface IRSac {

    /**
     * Registra las obligaciones de sac.
     * 
     * @param codigoOrganismo
     * @return RespuestaGestionCobroSacDTO
     * @author javier.fajardo
     */
    public RespuestaProcesoSacDTO replicarObligacionesSac(int codigoOrganismo);

    /**
     * Consulta los datos de ubicabilidad registrados en SOGIT
     * 
     * @param codigoOrganismo
     * @param numerosIdentificacion
     * @return Lista de {@link UbicabilidadDTO} con los datos de SOGIT
     * @author Rodrigo.Cruz
     */
    RespuestaUbicabilidadSacDTO consultarUbicabilidadSac(int codigoOrganismo/* , List<String> numerosIdentificacion */);

    /**
     * Permite registrar la ubicabilidad enviada a sac
     * 
     * @param codigoOrganismo
     * @param ubicabilidadSacDTO
     * @param idPersonaHistorico
     * @author rodrigo.cruz
     */
    void registrarUbicabilidadReplicada(int codigoOrganismo, UbicabilidadSacDTO ubicabilidadSacDTO,
            Long idPersonaHistorico);

    /**
     * Replica las novedades y pagos en la tabla intermedia de novedades con SAC
     * 
     * @param codigoOrganismo
     * @return Numero de novedades registradas
     * @author julio.pinzon 2016-05-11
     */
    public RespuestaProcesoSacDTO replicarNovedadesSac(int codigoOrganismo);

    /**
     * Actualiza las obligaciones que han sido financiadas
     * 
     * @param codigoOrganismo
     * @return Respuesta con numero de obligaciones actualizadas
     * @author julio.pinzon 2016-05-19
     */
    public RespuestaProcesoSacDTO actualizarObligacionesFinanciadas(int codigoOrganismo);

    /**
     * Recibe la gestion de cobro hecha por SAC
     * 
     * @param codigoOrganismo
     * @return Respuesta con numero de gestiones recibidas
     * @author Dixon.Alvarez
     */
    public RespuestaProcesoSacDTO recibirGestionCobroSac(int codigoOrganismo);

}
