package co.com.datatools.c2.negocio.helpers.gestiondocs;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 09 09:35:31 COT 2015
 */
public class ArchivoTransportableHelper {
    // --- to DTO
    public static co.com.datatools.documentos.cm.dto.Documento toDocumento(ArchivoTransportableDTO archivo,
            co.com.datatools.documentos.cm.dto.Documento documentCM) {
        if (documentCM == null) {
            documentCM = new co.com.datatools.documentos.cm.dto.Documento();
        }
        documentCM.setContenido(archivo.getContenido());
        documentCM.setFecha(new Date());
        documentCM.setDescripcion(archivo.getNombre());
        documentCM.setTipo("." + FilenameUtils.getExtension(archivo.getNombre()));
        documentCM.setNombreReal(archivo.getNombre());
        return documentCM;
    }

}
