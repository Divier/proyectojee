package co.com.datatools.c2.negocio.interfaces;

import java.io.File;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.RespuestaValidaCargueArchivoDTO;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Remote
public interface IRCargueMasivo {

    /**
     * Consultar cargues masivos
     * 
     * @param cargueArchivo
     * @return Lista de cargues de acuerdo a los filtros
     * @author julio.pinzon 2016-11-18
     */
    public List<CargueArchivoDTO> consultarCargueArchivo(CargueArchivoDTO cargueArchivo);

    /**
     * Registra el cargue en la base de datos
     * 
     * @param cargueArchivo
     * @return Dto del cargue con el identificador
     * @author julio.pinzon 2016-11-18
     */
    public CargueArchivoDTO registrarCargueArchivo(CargueArchivoDTO cargueArchivo);

    /**
     * Valida estructura basica del archivo a cargar
     * 
     * @param csvFile
     * @param tipoCargue
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-11-18
     */
    public void validarEstructuraArchivo(File csvFile, Integer tipoCargue) throws CirculemosNegocioException;

    /**
     * Actualiza el estado de un cargue a notificado
     * 
     * @param cargueArchivoDTO
     */
    public void notificarCargueArchivo(CargueArchivoDTO cargueArchivoDTO);

    /**
     * Registra un documento resultado del cargue de un archivo
     * 
     * @param documentoResulDTO
     */
    public DocumentoResultadoCargueDTO registrarDocumentoResulCargue(DocumentoResultadoCargueDTO documentoResulDTO);

    /**
     * Consulta los documentos de resultado para un cargue de un archivo
     * 
     * @param cargueArchivoDTO
     * @return List<DocumentoResultadoCargueDTO>
     */
    public List<DocumentoResultadoCargueDTO> consultarDocumentosResultadoCargue(CargueArchivoDTO cargueArchivoDTO);

    /**
     * Cierra el cargue activo de un archivo al sistema
     * 
     * @param cargueArchivoDTO
     */
    public void cerrarCargueArchivo(CargueArchivoDTO cargueArchivoDTO);

    /**
     * Registra el cargue masivo para un tipo de cargue
     * 
     * @param archivoCargado
     * @param tipoCargue
     * @param categoriaDocumento
     * @param tipoConsecutivo
     * @return
     * @throws CirculemosNegocioException
     * @author julio.pinzon 2016-12-01
     */
    public CargueArchivoDTO registrarCargueMasivo(ArchivoTransportableDTO archivoCargado,
            EnumTipoCargueArchivo tipoCargue, EnumCategoriaDocumento categoriaDocumento,
            EnumConsecutivo tipoConsecutivo) throws CirculemosNegocioException;

    /**
     * Registra avance de cargue masivo
     * 
     * @param cargueArchivo
     * @author manuel.chavarro 2016-11-25
     */
    public void registrarRegistroProcesado(CargueArchivoDTO cargueArchivo);
    
    /**
     * Actualiza los cargues activos al estado "Con error" (4). El servicio es ejecutado cuando se inicia el servidor con el fin de cerrar cargues que hayan quedado incompletos por el reinicio del servidor.
     */
    public void cerrarCarguesErroneos();
    
    /**
     * Valida si existe un cargue activo
     * 
     * @param tiposCargue
     * @return
     * @author julio.pinzon 2016-12-01
     */
    public RespuestaValidaCargueArchivoDTO validarCargueActivo(List<EnumTipoCargueArchivo> tiposCargue);
}