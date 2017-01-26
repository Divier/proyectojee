package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.NovedadSacDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.dto.UbicabilidadSacDTO;

@Local
public interface ILRegistroSac {

    /**
     * @see ILRegistroSac#registarNovedadSac(NovedadSacDTO)
     */
    public NovedadSacDTO registarNovedadSac(NovedadSacDTO novedad);

    /**
     * @see ILRegistroSac#registrarObligacionSac(ObligacionSacDTO)
     */
    public ObligacionSacDTO registrarObligacionSac(ObligacionSacDTO deuda);

    /**
     * @see ILRegistroSac#replicarUbicabilidadSac(UbicabilidadSacDTO)
     */
    public UbicabilidadSacDTO replicarUbicabilidadSac(UbicabilidadSacDTO ubicabilidadSacDTO);

    /**
     * @see ILRegistroSac#consultarObligacionSac(ObligacionSacDTO)
     */
    public ObligacionSacDTO consultarObligacionSac(ObligacionSacDTO obligacionSac);

    /**
     * @see ILRegistroSac#modificarObligacionSac(ObligacionSacDTO)
     */
    public void modificarObligacionSac(ObligacionSacDTO obligacion);

    /**
     * @see ILRegistroSac#consultarObligacionSac(Long)
     */
    public ObligacionSacDTO consultarObligacionSac(Long id);

    /**
     * replica evidencias de C2 a SAC
     */
    public void replicarEvidenciasSac();

    /**
     * @see IRRegistroSac#consultarGestionCobroSac()
     */
    public List<GestionCobroSacDTO> consultarGestionCobroSac();

    /**
     * @see IRRegistroSac#registarGestionCobroSac(GestionCobroSacDTO)
     */
    public GestionCobroSacDTO registarGestionCobroSac(GestionCobroSacDTO gestionCobroSacDTO);

}
