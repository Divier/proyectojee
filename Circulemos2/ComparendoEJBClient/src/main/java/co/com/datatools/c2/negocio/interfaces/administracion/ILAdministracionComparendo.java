package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.EmpresaTransporteDTO;
import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;

@Local
public interface ILAdministracionComparendo {

    /**
     * @see IRAdministracionComparendo#consultarGradoAlcoholemia(PaisDTO, GradoAlcoholemiaDTO)
     */
    List<GradoAlcoholemiaDTO> consultarGradoAlcoholemia(int codigoOrganismo);

    /**
     * @see IRAdministracionComparendo#consultarRuta(Integer, RutaDTO)
     */
    public List<RutaDTO> consultarRuta(Integer pIdOrganismoTransito, RutaDTO pRuta);

    /**
     * @see IRAdministracionComparendo#consultarEmpresaTransporte(int, EmpresaTransporteDTO)
     */
    List<EmpresaTransporteDTO> consultarEmpresaTransporte(int codigoOrganismo, EmpresaTransporteDTO empresaTransporteDTO);

    /**
     * @see IRAdministracionComparendo#consultarResolucionSancionActual(long)
     */
    public ComparendoResolucionDTO consultarResolucionSancionActual(long cicomparendo);

    /**
     * @see IRAdministracionComparendo#consultarResoluciones(ComparendoResolucionDTO)
     */
    public List<ComparendoResolucionDTO> consultarResoluciones(ComparendoResolucionDTO comparendoResolucion);
}
