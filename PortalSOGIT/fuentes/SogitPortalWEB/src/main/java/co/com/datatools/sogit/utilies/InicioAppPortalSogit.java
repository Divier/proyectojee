package co.com.datatools.sogit.utilies;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

/**
 * Managed bean de utilidades que se instancia a nivel de aplicacion al desplegar el artefacto
 * <ul>
 * <li>Mantiene la referencia a los bundle internacionalizados de negocio</li>
 * <li>Cache de catalogos de aplicacion</li>
 * </ul>
 * 
 * @author felipe.martinez<br/>
 *         giovanni.velandia (mod 2016-08-23)
 */
@ApplicationScoped
@ManagedBean(eager = true)
public class InicioAppPortalSogit implements Serializable {

    private static final long serialVersionUID = -4655980432495863188L;
    private final static Logger logger = Logger.getLogger(InicioAppPortalSogit.class.getName());

    /**
     * K:Catalogo, V:Valores Catalogo
     */
    private final Map<EnumCatalogo, List<SelectItem>> catalogos = new HashMap<>();

    /*-----------------------------------
     * Catalogos - INI
     * ----------------------------------
     */
    public List<SelectItem> getCatalogo(EnumCatalogo catalogo) {
        return catalogos.get(catalogo);
    }

    public void removerCatalogo(EnumCatalogo catalogo) {
        catalogos.remove(catalogo);
    }

    public void addCatalogo(EnumCatalogo catalogo, List<SelectItem> valores) {
        if (valores != null) {
            logger.debugv("Cargado Catalogo Web [codigoOrganismo: {0}, catalogo: {1}]", catalogo);
            catalogos.put(catalogo, valores);
        }
    }

    /**
     * Permite obtener el SelectItem con el identificador o valor dentro de un catalogo determinado
     * 
     * @param catalogo
     *            catalogo sobre el cual se lleva a cabo la consulta
     * @param idCatalogo
     *            identificador del registro o item a ser buscado
     * 
     * @return SelectItem del catalogo con sus respectivos valores, null en caso de no ser encontrado
     * @author luis.forero (2015-09-02)
     */
    public SelectItem getItemCatalogo(EnumCatalogo catalogo, Object idCatalogo) {
        List<SelectItem> listItems = catalogos.get(catalogo);
        SelectItem item = null;
        if (listItems != null) {
            for (SelectItem sItem : listItems) {
                if (sItem.getValue().equals(idCatalogo)) {
                    item = sItem;
                    break;
                }
            }
        }
        return item;
    }

    /*-----------------------------------
     * Catalogos - FIN
     * ----------------------------------
     */
}
