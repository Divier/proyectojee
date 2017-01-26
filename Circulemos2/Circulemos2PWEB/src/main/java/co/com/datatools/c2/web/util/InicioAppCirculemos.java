package co.com.datatools.c2.web.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.reporte.data.ConectorArchivosReporte;
import co.com.datatools.c2.reporte.jsf.ReportHandlerComponent;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.bundle.MultipleBundlesLoader;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Managed bean de utilidades que se instancia a nivel de aplicacion al desplegar el artefacto
 * <ul>
 * <li>Mantiene la referencia a los bundle internacionalizados de negocio</li>
 * <li>Cache de catalogos de aplicacion</li>
 * </ul>
 * 
 * @author felipe.martinez<br/>
 *         giovanni.velandia (mod 2015-05-22)
 */
@ApplicationScoped
@ManagedBean(eager = true)
public class InicioAppCirculemos extends AbstractManagedBean {

    private static final long serialVersionUID = -4655980432495863188L;

    private static final String PATH_BUNDLE_NEGOCIO = "co.com.datatools.c2.negocio.bundle";

    private final static Logger logger = Logger.getLogger(InicioAppCirculemos.class.getName());

    /**
     * Clase que mantiene las referencias a los bundle internacionalizados de negocio
     */
    private transient MultipleBundlesLoader bundleLoader = null;

    /**
     * K:Catalogo, V:Valores Catalogo
     */
    private final Map<EnumCatalogo, List<SelectItem>> catalogos = new HashMap<>();

    private final String versionArtefacto = Utilidades.cargarVersionArtefacto();

    @PostConstruct
    public void init() {
        inicializarGeneradorReportes();
        bundleLoader = new MultipleBundlesLoader(PATH_BUNDLE_NEGOCIO);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        bundleLoader = new MultipleBundlesLoader(PATH_BUNDLE_NEGOCIO);
    }

    private void inicializarGeneradorReportes() {
        final ConectorArchivosReporte conectorArchivosReporte = new ConectorArchivosReporte(
                "resource:co/com/datatools/c2/reportes");
        addApplicationObject(ReportHandlerComponent.NOMBRE_OBJ_CONECTOR_PLANTILLA, conectorArchivosReporte);
    }

    public String getVersionArtefacto() {
        return versionArtefacto;
    }

    /**
     * Permite obtener el bundle de negocio para el locale especificado
     * 
     * @param locale
     *            locale del bundle seleccionado
     * @return bundle encontrado, null si no existe
     */
    public ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return bundleLoader.getResourceBundle(locale);
    }

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
        if (valores != null && !valores.isEmpty()) {
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
