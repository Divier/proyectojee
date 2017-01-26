package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.EmpresaTransporteDTO;
import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;

@Remote
public interface IRAdministracionComparendo {

    /**
     * Retorna el listado de grados de alcoholemia existentes para el organismo de tránsito indicado.
     * 
     * @param codigoOrganismo
     *            Codigo de organismo
     * @return Catalogo de {@code GradoAlcoholemiaDTO} o un grado de alcoholemia segun el ID enviado. Una lista vacia si no encuentra resultados.
     * @author rodrigo.cruz
     */
    List<GradoAlcoholemiaDTO> consultarGradoAlcoholemia(int codigoOrganismo);

    /**
     * Consulta el catalogo de rutas.
     * 
     * @param pIdOrganismoTransito
     *            Obligatorio con el codigo de origanismo de transito.
     * @param pRuta
     *            En caso de no ser nulo, consulta una ruta por ID.
     * @return Catalogo de {@code RutaDTO} o una ruta segun el ID enviado. Una lista vacia si no encuentra resultados.
     * @author Maria.Torres
     */
    List<RutaDTO> consultarRuta(Integer pIdOrganismoTransito, RutaDTO pRuta);

    /**
     * Consulta las empresas de transporte acorde al filtro y el organismo de transito
     * 
     * @param codigoOrganismo
     *            codigo del organismo de transito
     * @param empresaTransporteDTO
     *            empresa de transporte con los datos a filtrar como el tipo y numero de documento de la persona
     * @return Retorna el listado de empresas de transporte acorde al filtro y el organismo de transito indicado.
     * @author luis.forero(2015-10-30)
     */
    List<EmpresaTransporteDTO> consultarEmpresaTransporte(int codigoOrganismo, EmpresaTransporteDTO empresaTransporteDTO);

    /**
     * Consulta la resolución de sanción más reciente de un comparendo
     * 
     * @param cicomparendo
     * @return Retorna null si no existe resolución
     * @author julio.pinzon
     */
    public ComparendoResolucionDTO consultarResolucionSancionActual(long cicomparendo);

    /**
     * Consulta el listado de resoluciones que estan relacionadas al comparendo indicado. Valida que el comparendo exista.
     * 
     * @param comparendoResolucion
     *            objeto con los filtros de entrada de la resolucion, contiene (cicomparendo,tipoResolucion,estado resolucion)
     * @return Retorna el listado de resoluciones que cumplen con el filtro indicado que estan relacionadas al comparendo indicado. Valida que el
     *         comparendo exista.
     * @author luis.forero(2016-02-02)
     */
    public List<ComparendoResolucionDTO> consultarResoluciones(ComparendoResolucionDTO comparendoResolucion);
}
