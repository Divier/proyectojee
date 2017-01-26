package co.com.datatools.c2.negocio.interfaces.gestiondocs;

import javax.ejb.Remote;

import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.opciones.GestorArchivosOpciones;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Interface que se encarga de almacenar, consultar y actualizar un archivo binario.
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRRepositorioArchivo {

    /**
     * Almacena el archivo indicado retornando el identificador del archivo una vez almacenado.
     * 
     * @param archivo
     *            DTO del archivo
     * @return Identificador del archivo una vez almacenado
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     * <br>
     *             GESDOC_002: No se pudo guardar archivo en el repositorio
     */
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            GestorArchivosOpciones... opciones) throws CirculemosAlertaException;

    /**
     * Retorna el archivo con el id indicado
     * 
     * @param idDocumento
     *            Id del documento a consultar
     * @return DTO con los datos del dodumento
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     * <br>
     *             GESDOC_001: No se encontro el archivo
     */
    public ArchivoTransportableDTO consultarDocumento(String idDocumento) throws CirculemosAlertaException;

    /**
     * Actualiza el contenido del archivo con el identificador indicado.
     * 
     * @param idDocumento
     *            Id del documento a actualizar
     * @param archivo
     *            DTO con los datos del dodumento
     * @author julio.pinzon
     * @throws CirculemosNegocioException
     * <br>
     *             GESDOC_001: No se encontro el archivo <br>
     *             GESDOC_002: No se pudo guardar archivo en el repositorio
     */
    public void actualizarDocumento(String idDocumento, ArchivoTransportableDTO archivo)
            throws CirculemosAlertaException;

    /**
     * Almacena el archivo indicado retornando el identificador del archivo una vez almacenado en una ruta especificada.
     * 
     * @param categoriaDocumento
     * @param archivo
     * @param ruta
     * @return Identificador del archivo una vez almacenado
     * @author giovanni.velandia 2015-10-09
     * @throws CirculemosNegocioException
     * <br>
     *             GESDOC_002: No se pudo guardar archivo en el repositorio
     * @deprecated Debe utilizarce registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo) : String y enviarse
     *             la ruta dentro del ArchivoTransportableDTO
     */
    public String registrarDocumento(EnumCategoriaDocumento categoriaDocumento, ArchivoTransportableDTO archivo,
            String ruta) throws CirculemosAlertaException;
}
