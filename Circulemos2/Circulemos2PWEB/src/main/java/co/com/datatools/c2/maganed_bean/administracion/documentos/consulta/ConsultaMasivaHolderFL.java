package co.com.datatools.c2.maganed_bean.administracion.documentos.consulta;

import java.util.List;

import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta de documentos generados
 * 
 * @author divier.casas
 * 
 */
public class ConsultaMasivaHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "consultaMasivaHolderFL";

    /**
     * Listado de documentos generados
     */
    private List<DocumentoMasivoDTO> lsRespuesta;
    private Long idArchivo;
    private Integer codigoOrganismo;

    public List<DocumentoMasivoDTO> getLsRespuesta() {
        return lsRespuesta;
    }

    public void setLsRespuesta(List<DocumentoMasivoDTO> lsRespuesta) {
        this.lsRespuesta = lsRespuesta;
    }

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }
}