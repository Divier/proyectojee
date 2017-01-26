package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.PlantillaDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Interface que ofrece los servicios de comunicación con el módulo de documentos.
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRDocumentosCirculemos {

    /**
     * Genera el documento acorde a la información recibida, teniendo en cuenta la configuración realizada para el tipo de documento y el organismo de
     * tránsito.
     * 
     * @param generaDocumento
     * @return Retorna el identificador del documento generado.
     * @author julio.pinzon
     * @throws CirculemosAlertaException
     *             ADM_073001: Se presento un error al generar documento ADM_073003:Se presento un error al consultar los documentos pdf ADM_073004:El
     *             comparendo no tiene infractor registrado ADM_073005: Tipo de documento generado invalido
     */
    public Long generarDocumento(GeneraDocumentoDTO generaDocumento) throws CirculemosAlertaException;

    /**
     * Carga los archivos binarios asociados a los identificadores indicados.
     * 
     * @param idDocumento
     * @return Retorna los archivos en uno solo con formato PDF.
     * @author julio.pinzon
     * @throws CirculemosAlertaException
     *             ADM_073002:Se presento un error al consultar los documentos pdf
     */
    public byte[] consultarDocumentosPDF(List<Long> idDocumento) throws CirculemosAlertaException;

    /**
     * Genera vista preliminar del documento acorde a la información recibida, teniendo en cuenta la configuración realizada para el tipo de documento
     * y el organismo de tránsito.
     * 
     * @param generaDocumento
     * @return Retorna el archivo transportable del documento generado.
     * @author julio.pinzon 2016-08-03
     * @throws CirculemosAlertaException
     *             ADM_073001: Se presento un error al generar documento ADM_073003:Se presento un error al consultar los documentos pdf ADM_073004:El
     *             comparendo no tiene infractor registrado ADM_073005: Tipo de documento generado invalido
     */
    public ArchivoTransportableDTO generarDocumentoPreliminar(GeneraDocumentoDTO generaDocumento)
            throws CirculemosAlertaException;

    /**
     * Consulta las plantillas de un proceso
     * 
     * @param codigoProceso
     * @return plantillas de un proceso
     * @author julio.pinzon 2016-09-06
     * @throws CirculemosAlertaException
     */
    public List<PlantillaDTO> consultarPlantillasPorProceso(String codigoProceso) throws CirculemosAlertaException;
}
