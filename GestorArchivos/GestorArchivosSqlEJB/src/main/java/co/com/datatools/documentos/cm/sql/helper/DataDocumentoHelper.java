package co.com.datatools.documentos.cm.sql.helper;

import java.nio.file.Path;
import java.nio.file.Paths;

import co.com.datatools.documentos.cm.dto.ConstantesGestorArchivos;
import co.com.datatools.documentos.cm.dto.Documento;
import co.com.datatools.documentos.cm.sql.entidades.DataDocumento;
import co.com.datatools.documentos.cm.sql.entidades.VersionDataDocumento;

public class DataDocumentoHelper {

    /**
     * Permite convertir del DTO de comunicaci�n a la entidad para ser persistida en la base de datos
     * 
     * @param documentCM
     * @return
     */
    public static DataDocumento toDataDocumento(DataDocumento data, Documento documentCM) {
        if (data == null)
            data = new DataDocumento();
        if (null != documentCM.getIdDocumento() && !documentCM.getIdDocumento().equals(""))
            data.setCodigoDocumento(Integer.parseInt(documentCM.getIdDocumento()));
        data.setDescripcion(documentCM.getDescripcion());
        data.setFolder(documentCM.getCarpeta());
        data.setNombre(documentCM.getNombre() + documentCM.getTipo());
        data.setExtension(documentCM.getTipo());
        data.setNombreReal(documentCM.getNombreReal());
        return data;
    }

    /**
     * Se encarga de normalizar el folder asociado a un documento
     * 
     * @param path
     * @return
     */
    private static String normalizeFolder(String path) {
        try {
            Path folder = Paths.get(path);
            return folder.normalize().toString();
        } catch (Exception e) {
            return ConstantesGestorArchivos.SEPARADOR;
        }
    }

    /**
     * se encarga de convertir la entidad en el documento de comunicaci�n
     * 
     * @param dataDocumento
     * @return
     */
    public static Documento toDocumentCM(DataDocumento dataDocumento, VersionDataDocumento version) {
        Documento documentCM = new Documento();
        documentCM.setIdDocumento(dataDocumento.getCodigoDocumento().toString());
        documentCM.setContenido(version.getContenidoBinario());
        documentCM.setDescripcion(dataDocumento.getDescripcion());
        documentCM.setFecha(version.getFecha());
        documentCM.setNombre(dataDocumento.getNombre());
        documentCM.setTipo(dataDocumento.getExtension());
        documentCM.setCarpeta(dataDocumento.getFolder());
        documentCM.setVersion(Integer.toString(version.getVersion()));
        documentCM.setNombreReal(dataDocumento.getNombreReal());
        return documentCM;
    }

    /**
     * Se encarga de extraer los datos propios de la version del DocumentcM recibidos
     * 
     * @param documentCM
     * @return
     */
    public static VersionDataDocumento toVersionDataDocumento(Documento documentCM) {
        VersionDataDocumento versionNew = new VersionDataDocumento();
        versionNew.setContenidoBinario(documentCM.getContenido());
        versionNew.setFecha(documentCM.getFecha());
        versionNew.setVersion(1);
        return versionNew;
    }

}
