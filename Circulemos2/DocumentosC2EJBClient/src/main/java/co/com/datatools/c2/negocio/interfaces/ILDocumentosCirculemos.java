package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.PlantillaDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Local
public interface ILDocumentosCirculemos {

    /**
     * @see IRDocumentosCirculemos#generarDocumento(GeneraDocumentoDTO)
     */
    public Long generarDocumento(GeneraDocumentoDTO generaDocumento) throws CirculemosAlertaException;

    /**
     * @see IRDocumentosCirculemos#consultarDocumentosPDF(List<Long>)
     */
    public byte[] consultarDocumentosPDF(List<Long> idDocumento) throws CirculemosAlertaException;

    /**
     * @see IRDocumentosCirculemos#generarDocumentoPreliminar(GeneraDocumentoDTO)
     */
    public ArchivoTransportableDTO generarDocumentoPreliminar(GeneraDocumentoDTO generaDocumento)
            throws CirculemosAlertaException;

    /**
     * @see IRDocumentosCirculemos#consultarPlantillasPorProceso(String)
     */
    public List<PlantillaDTO> consultarPlantillasPorProceso(String codigoProceso) throws CirculemosAlertaException;
}
