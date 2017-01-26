package co.com.datatools.c2.managed_bean.comun;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.datatools.c2.enumeracion.EnumCamposDetalleComparendo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase encargada de consultar informacion de las enumeraciones de negocio
 * 
 * @author giovanni.velandia
 */
@ManagedBean
@SessionScoped
public class EnumeracionMB extends AbstractC2ManagedBean {
    private static final long serialVersionUID = 1L;

    /**
     * Se encarga de buscar un atributo del enum campo detalle comparendo
     * 
     * @author giovanni.velandia
     * @param idCampoComparendo
     */
    public int consultarEnumCampoDetalleComparendo(int idCampoComparendo) {
        EnumCamposDetalleComparendo enumCamposDetalleComparendo = Utilidades.buscarElemEnum(
                EnumCamposDetalleComparendo.class, idCampoComparendo);
        return enumCamposDetalleComparendo.getLongitud();
    }

}
