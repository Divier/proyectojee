package co.com.datatools.c2.negocio.fachada.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.RespuestaProcesoSacDTO;
import co.com.datatools.c2.dto.RespuestaUbicabilidadSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;

@Remote
public interface IRFachadaSac {

    /**
     * Consulta los datos de ubicabilidad registrados en SOGIT
     * 
     * @param codigoOrganismo
     * @param numerosIdentificacion
     * @return
     * @author rodrigo.cruz
     */
    RespuestaUbicabilidadSacDTO consultarUbicabilidadSac(int codigoOrganismo/* , List<String> numerosIdentificacion */);

    /**
     * Registra una ubicabilidad nueva en SAC a partir de los datos en SOGIT
     * 
     * @param ubicabilidadSacDTO
     * @return ubicabilidadSacDTO registrado
     * @author rodrigo.cruz
     */
    public UbicabilidadSacDTO replicarUbicabilidadSac(UbicabilidadSacDTO ubicabilidadSacDTO);

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
     * Replica las obligaciones a Sac
     * 
     * @param codigoOrganismo
     * @return
     * @author julio.pinzon 2016-05-11
     * 
     */
    public RespuestaProcesoSacDTO replicarObligacionesSac(int codigoOrganismo);

    /**
     * Actualiza los estados de TODAS las carteras en SAC de acuerdo a las validaciones
     */
    public void actualizarGestionCobroTimer(Integer codigoOrganismo);

    /**
     * Actualiza las obligaciones que han sido financiadas
     * 
     * @param codigoOrganismo
     * @return Respuesta con numero de obligaciones actualizadas
     * @author julio.pinzon 2016-05-19
     */
    public RespuestaProcesoSacDTO actualizarObligacionesFinanciadas(int codigoOrganismo);

    /**
     * Replica las evidencias de C2 a SAC
     */
    public void replicarEvidenciasSac();

    /**
     * Recibe la gestion de cobro hecha por SAC
     * 
     * @param codigoOrganismo
     * @return Respuesta con numero de gestiones recibidas
     * @author Dixon.Alvarez
     */
    public RespuestaProcesoSacDTO recibirGestionCobroSac(int codigoOrganismo);
}
