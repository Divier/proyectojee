package co.com.datatools.documentos.cm.sql.implementation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.com.datatools.documentos.cm.dto.Documento;
import co.com.datatools.documentos.cm.interfaces.ILGestorArchivos;
import co.com.datatools.documentos.cm.interfaces.IRGestorArchivos;
import co.com.datatools.documentos.cm.sql.entidades.DataDocumento;
import co.com.datatools.documentos.cm.sql.entidades.VersionDataDocumento;
import co.com.datatools.documentos.cm.sql.helper.DataDocumentoHelper;

/**
 * Session Bean implementation class ContentManagerEJB
 */
@Stateless
@LocalBean
public class GestorArchivosSqlEJB implements ILGestorArchivos, IRGestorArchivos {

    @PersistenceContext(unitName = "ContentManagerJPA")
    private EntityManager em;

    /**
     * Default constructor.
     */
    public GestorArchivosSqlEJB() {
        // TODO Auto-generated constructor stub
    }

    public Documento guardarDocumento(Documento documentoCM) {
        // verificar si ya existe un documento creado con el mismo nombre en la
        // misma carpeta
        DataDocumento dataDocumento = findDocumentByNameFolder(documentoCM.getNombre() + documentoCM.getTipo(),
                documentoCM.getCarpeta());
        VersionDataDocumento versionDataDocumento = null;
        if (dataDocumento == null) {
            // No existe otro documento con el mismo nombre en el mismo folder
            dataDocumento = DataDocumentoHelper.toDataDocumento(null, documentoCM);
            em.persist(dataDocumento);
            versionDataDocumento = DataDocumentoHelper.toVersionDataDocumento(documentoCM);
            versionDataDocumento.setCodigoDocumento(dataDocumento);
            em.persist(versionDataDocumento);
        } else {
            // Existe otro documento dl mismo nombre ebn el folder por lo que se
            // procede a generar una nueva versi�n
            return null;
        }
        return DataDocumentoHelper.toDocumentCM(dataDocumento, versionDataDocumento);
    }

    public Documento obtenerDocumento(String idDocumento, String version) {
        Documento documento = null;
        DataDocumento dataDocumento = em.find(DataDocumento.class, Integer.parseInt(idDocumento));
        if (dataDocumento != null) {
            VersionDataDocumento ultimaVersion = findVersionDocument(dataDocumento.getCodigoDocumento(),
                    version != null && !version.equals("") ? Integer.parseInt(version) : null);
            documento = DataDocumentoHelper.toDocumentCM(dataDocumento, ultimaVersion);
        }
        return documento;
    }

    public Documento actualizarDocumento(Documento documento) {
        DataDocumento dataDocumento = findDocumentByNameFolder(documento.getNombre() + documento.getTipo(),
                documento.getCarpeta());
        VersionDataDocumento versionDataDocumento = null;
        if (dataDocumento != null) {
            versionDataDocumento = generateNewVersionDocument(dataDocumento, documento);
            return DataDocumentoHelper.toDocumentCM(dataDocumento, versionDataDocumento);
        }
        return null;
    }

    /**
     * Permite consultar si existe un documento con ese nombre en la misma ubicaci�n, si la ubicaci�n es nula se deja en el root del mismo
     * 
     * @param name
     * @param folder
     * @return
     */
    private DataDocumento findDocumentByNameFolder(String name, String folder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d FROM DataDocumento d");
        sql.append(" WHERE d.nombre LIKE :nombre");
        if (null != folder) {
            sql.append(" AND d.folder LIKE :folder");
        } else {
            sql.append(" AND d.folder IS NULL");
        }

        TypedQuery<DataDocumento> query = em.createQuery(sql.toString(), DataDocumento.class);

        query.setParameter("nombre", name);
        if (null != folder) {
            query.setParameter("folder", folder);
        }

        List<DataDocumento> resultados = query.getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    /**
     * Se encarga de generar la nueva version del documento recibido que ya existe en el sistema
     * 
     * @param dataDocumento
     * @param documentCM
     * @return
     */
    private VersionDataDocumento generateNewVersionDocument(DataDocumento dataDocumento, Documento documentCM) {
        VersionDataDocumento versionNueva = new VersionDataDocumento();
        VersionDataDocumento maxVersion = findVersionDocument(dataDocumento.getCodigoDocumento(), null);
        versionNueva.setVersion(maxVersion.getVersion() + 1);
        versionNueva.setFecha(documentCM.getFecha());
        versionNueva.setCodigoDocumento(dataDocumento);
        versionNueva.setContenidoBinario(documentCM.getContenido());
        em.persist(versionNueva);
        return versionNueva;
    }

    /**
     * M�todo que se encarga de consultar en la base de datos la �ltima versi�n del documento
     * 
     * @param codigoDocumento
     * @param version
     *            - @null si es la <strong>�ltima</strong> versi�n
     * @return
     */
    private VersionDataDocumento findVersionDocument(int codigoDocumento, Integer version) {
        Map<String, Object> paramQuery = new HashMap<String, Object>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT v FROM VersionDataDocumento v");
        sql.append(" WHERE v.codigoDocumento.codigoDocumento = :idDocumento");
        if (null != version) {
            sql.append(" AND v.version = :version");
            paramQuery.put("version", version);
        }
        sql.append(" ORDER BY v.version DESC");

        TypedQuery<VersionDataDocumento> query = em.createQuery(sql.toString(), VersionDataDocumento.class);
        paramQuery.put("idDocumento", codigoDocumento);
        for (Iterator<Entry<String, Object>> it = paramQuery.entrySet().iterator(); it.hasNext();) {
            Entry<String, Object> param = it.next();
            query.setParameter(param.getKey(), param.getValue());
        }
        return query.getResultList().get(0);
    }
}
