package co.com.datatools.c2.negocio.interfaces;

import java.io.File;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.RespuestaValidaCargueArchivoDTO;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILCargueMasivo {

    /**
     * @see IRCargueMasivo#consultarCargueArchivo(CargueArchivoDTO)
     */
    public List<CargueArchivoDTO> consultarCargueArchivo(CargueArchivoDTO cargueArchivo);

    /**
     * @see IRCargueMasivo#registrarCargueArchivo(CargueArchivoDTO)
     */
    public CargueArchivoDTO registrarCargueArchivo(CargueArchivoDTO cargueArchivo);

    /**
     * @see IRCargueMasivo#validarEstructuraArchivo(File, Integer)
     */
    public void validarEstructuraArchivo(File csvFile, Integer tipoCargue) throws CirculemosNegocioException;

    /**
     * @see IRCargueMasivo#notificarCargueArchivo(CargueArchivoDTO)
     */
    public void notificarCargueArchivo(CargueArchivoDTO cargueArchivoDTO);

    /**
     * @see IRCargueMasivo#registrarDocumentoResulCargue(DocumentoResultadoCargueDTO)
     */
    public DocumentoResultadoCargueDTO registrarDocumentoResulCargue(DocumentoResultadoCargueDTO documentoResulDTO);

    /**
     * @see IRCargueMasivo#consultarDocumentosResultadoCargue(CargueArchivoDTO)
     */
    public List<DocumentoResultadoCargueDTO> consultarDocumentosResultadoCargue(CargueArchivoDTO cargueArchivoDTO);

    /**
     * @see IRCargueMasivo#cerrarCargueArchivo(CargueArchivoDTO)
     */
    public void cerrarCargueArchivo(CargueArchivoDTO cargueArchivoDTO);

    /**
     * @see IRCargueMasivo#registrarCargueMasivo(ArchivoTransportableDTO, EnumTipoCargueArchivo, EnumCategoriaDocumento, EnumConsecutivo)
     */
    public CargueArchivoDTO registrarCargueMasivo(ArchivoTransportableDTO archivoCargado,
            EnumTipoCargueArchivo tipoCargue, EnumCategoriaDocumento categoriaDocumento,
            EnumConsecutivo tipoConsecutivo) throws CirculemosNegocioException;

    /**
     * @see IRCargueMasivo#registrarRegistroProcesado(CargueArchivoDTO)
     */
    public void registrarRegistroProcesado(CargueArchivoDTO cargueArchivo);

    /**
     * @see IRCargueMasivo#validarCargueActivo(List<EnumTipoCargueArchivo>)
     */
    public RespuestaValidaCargueArchivoDTO validarCargueActivo(List<EnumTipoCargueArchivo> tiposCargue);
}