package co.com.datatools.c2.managed_bean.coactivo.autorizacion;

import java.util.List;

import co.com.datatools.c2.dto.AprobacionPrecoactivoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de autorizar coactivos
 * 
 * @author julio.pinzon 2016-09-29
 * 
 */
public class AutorizaCoactivoFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "autorizaCoactivoFL";
    private List<AprobacionPrecoactivoDTO> precoactivos;
    private boolean descargaArchivo;
    private String multasAutorizar;

    public List<AprobacionPrecoactivoDTO> getPrecoactivos() {
        return precoactivos;
    }

    public void setPrecoactivos(List<AprobacionPrecoactivoDTO> precoactivos) {
        this.precoactivos = precoactivos;
    }

    public boolean isDescargaArchivo() {
        return descargaArchivo;
    }

    public void setDescargaArchivo(boolean descargaArchivo) {
        this.descargaArchivo = descargaArchivo;
    }

    public String getMultasAutorizar() {
        return multasAutorizar;
    }

    public void setMultasAutorizar(String multasAutorizar) {
        this.multasAutorizar = multasAutorizar;
    }

}