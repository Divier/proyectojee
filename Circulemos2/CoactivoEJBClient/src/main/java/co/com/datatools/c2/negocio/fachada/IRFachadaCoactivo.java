package co.com.datatools.c2.negocio.fachada;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CoactivoDTO;

@Remote
public interface IRFachadaCoactivo {

    /**
     * Registra las obligaciones como Coactivo, provenientes del Job
     * 
     * @param Integer
     *            Codigo del organismo de transito
     * @return
     * @author julio.pinzon 2016-08-04
     */
    public Integer registrarCoactivoJob(Integer codigoOrganismo);

    /**
     * Consulta coactivo por numero de coactivo
     * 
     * @param numeroCoactivo
     * @return
     */
    public CoactivoDTO consultarCoactivo(String numeroCoactivo);
}