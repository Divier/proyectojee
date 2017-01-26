package co.com.datatools.c2.negocio.interfaces.formularios;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILAdministracionFormularios {

    /**
     * @see IRAdministracionFormularios#consultarNumeracionFormulario(Integer, Boolean)
     */
    List<NumeracionFormularioDTO> consultarNumeracionFormulario(Integer idTipoFormulario, Boolean estadoNumeracion);

    /**
     * @see IRAdministracionFormularios#registrarNumeracionFormulario(NumeracionFormularioDTO)
     */
    void registrarNumeracionFormulario(NumeracionFormularioDTO NumeracionFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#consultarResponsablesFormularios(UnificacionResponsableDTO)
     */
    List<UnificacionResponsableDTO> consultarResponsablesFormularios(UnificacionResponsableDTO responsableConsulta);

    /**
     * @see IRAdministracionFormularios#consultarResponsableFormularios(UnificacionResponsableDTO)
     */
    UnificacionResponsableDTO consultarResponsableFormularios(UnificacionResponsableDTO unificacionResponsable)
            throws CirculemosNegocioException;

    // Commented unused Sergio Torres 17-dic-2015
    // /**
    // * @see IRAdministracionFormularios#consultarResponsableFormularios(Integer, String)
    // */
    // @Deprecated
    // ResponsableFormularioDTO consultarResponsableFormularios(Integer tipoDocumento, String numeroDocumento);

    // Commented unused Sergio Torres 17-dic-2015
    // /**
    // * @see IRAdministracionFormularios#consultarResponsablePorPlaca(String)
    // */
    // @Deprecated
    // ResponsableFormularioDTO consultarResponsablePorPlaca(String numPlaca);

    /**
     * @see IRAdministracionFormularios#registrarResponsableFormularios(UnificacionResponsableDTO)
     */
    Long registrarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#actualizarResponsableFormularios(UnificacionResponsableDTO)
     */
    void actualizarResponsableFormularios(UnificacionResponsableDTO unificacionResponsableDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#eliminarResponsableFormularios(Long)
     */
    void eliminarResponsableFormularios(Long idResponsable) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#validarDetalleNumeracion(DetalleNumeracionDTO)
     */
    void validarDetalleNumeracion(DetalleNumeracionDTO detalleNumeracionDTO) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#consultarDetalleNumeracionFormulario(int)
     */
    List<DetalleNumeracionDTO> consultarDetalleNumeracionFormulario(int idNumeracionFormulario);

    /**
     * 
     * @see IRAdministracionFormularios#eliminarNumeracionFormulario(int)
     */
    void eliminarNumeracionFormulario(int idNumeracionFormulario) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#actualizarNumeracionFormulario(NumeracionFormularioDTO)
     */
    void actualizarNumeracionFormulario(NumeracionFormularioDTO numeracionFormularioDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#validarNumeracionEditable(int)
     */
    boolean validarNumeracionEditable(int idNumeracionFormulario);

    /**
     * @see IRAdministracionFormularios#consultarRelacionesEstados(RelacionEstadosDTO)
     */
    List<RelacionEstadosDTO> consultarRelacionesEstados(RelacionEstadosDTO relacionEstadoConsulta);

    /**
     * @see IRAdministracionFormularios#registrarRelacionEstados(RelacionEstadosDTO)
     */
    Long registrarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#actualizarRelacionEstados(RelacionEstadosDTO)
     */
    void actualizarRelacionEstados(RelacionEstadosDTO relacionEstadosDTO) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#eliminarRelacionEstados(Long)
     */
    void eliminarRelacionEstados(Long idRelacionEstados);

    /**
     * @see IRAdministracionFormularios#consultarStockTipoFormulario(StockTipoFormularioDTO)
     */
    List<StockTipoFormularioDTO> consultarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario);

    /**
     * @see IRAdministracionFormularios#registrarStockTipoFormulario(StockTipoFormularioDTO)
     */
    void registrarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#actualizarStockTipoFormulario(StockTipoFormularioDTO)
     */
    void actualizarStockTipoFormulario(StockTipoFormularioDTO stockTipoFormulario) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#eliminarStockTipoFormulario(Integer)
     */
    void eliminarStockTipoFormulario(Integer idTipoFormulario) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionFormularios#consultarResponsableFormulario(String, int)
     */
    public UnificacionResponsableDTO consultarResponsableFormulario(String numeroFormulario, int idTipoFormulario);

    /**
     * @see IRAdministracionFOrmularios#consultarResponsablesOrganismo(int,Integer)
     */
    public List<UnificacionResponsableDTO> consultarResponsablesOrganismo(int codigoOrganismo, Integer tipoFormulario);
}
