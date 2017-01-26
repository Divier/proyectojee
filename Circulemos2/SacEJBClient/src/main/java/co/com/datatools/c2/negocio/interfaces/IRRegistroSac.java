package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.NovedadSacDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;

@Remote
public interface IRRegistroSac {

    /**
     * Guarda la informacion de novedades y pagos en las tablas intermedias
     * 
     * @param novedad
     * @return Novedad registrada
     * @author julio.pinzon 2016-05-11
     */
    public NovedadSacDTO registarNovedadSac(NovedadSacDTO novedad);

    /**
     * Guarda la informacion de deundas en la tablas intermedias.
     * 
     * @param novedad
     * @return Novedad registrada
     * @author javier.fajardo 2016-05-12
     */
    public ObligacionSacDTO registrarObligacionSac(ObligacionSacDTO deuda);

    /**
     * Registra una ubicabilidad nueva en SAC a partir de los datos en SOGIT
     * 
     * @param ubicabilidadSacDTO
     * @return ubicabilidadSacDTO registrado
     * @author rodrigo.cruz
     */
    public UbicabilidadSacDTO replicarUbicabilidadSac(UbicabilidadSacDTO ubicabilidadSacDTO);

    /**
     * Consulta una obligacion por sus filtros
     * 
     * @param obligacionSac
     * @return obligacion encontrada o null en caso de no encontrarla
     * @author julio.pinzon 2016-05-12
     */
    public ObligacionSacDTO consultarObligacionSac(ObligacionSacDTO obligacionSac);

    /**
     * Actualiza la obligacion
     * 
     * @param obligacionSac
     * @author julio.pinzon 2016-05-19
     */
    public void modificarObligacionSac(ObligacionSacDTO obligacion);

    /**
     * Consulta una obligacion por su id
     * 
     * @param id
     * @return obligacion encontrada o null en caso de no encontrarla
     * @author julio.pinzon 2016-05-31
     */
    public ObligacionSacDTO consultarObligacionSac(Long id);

    /**
     * Replica las evidencias de C2 a SAC
     */
    public void replicarEvidenciasSac();

    /**
     * Consulta las gestiones realizadas por SAC y que no han sido replicadas en C2
     * 
     * @return Lista de gestiones de cobro de SAC
     * @author Dixon.Alvarez 2016-08-17
     */
    public List<GestionCobroSacDTO> consultarGestionCobroSac();

    /**
     * Periste una GestionCobroSac
     * 
     * @param gestionCobroSacDTO
     *            Contiene la informacion a persistir
     * @return GestionCobroSac persistida
     */
    public GestionCobroSacDTO registarGestionCobroSac(GestionCobroSacDTO gestionCobroSacDTO);

}
