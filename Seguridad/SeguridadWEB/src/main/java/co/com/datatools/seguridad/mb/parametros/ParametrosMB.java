package co.com.datatools.seguridad.mb.parametros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Session Bean para gestion del flujo de la administración de parámetros
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class ParametrosMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ParametrosMB.class.getName());

    private static final String NOMBRE_BUNDLE_PARAMETROS = "parametrosBase";

    private Pattern patronNumericoEntero = Pattern.compile("[0-9]+");
    private Pattern patronNumericoDecimal = Pattern.compile("\\d+(\\.\\d{1,2})?");

    @EJB
    private IRParametrosSeguridad parametrosSeguridadEjb;

    @EJB
    private IRCatalogosSeguridad catalogosSeguridadEjb;

    private Map<String, String> tiposDato;

    public ParametrosMB() {
        logger.debug("ParametrosMB::ParametrosMB");
        tiposDato = new HashMap<String, String>();
    }

    public void cargarListaTiposParametro() {
        logger.debug("ParametrosMB::cargarListaTiposParametro");
        tiposDato = catalogosSeguridadEjb.consultarTiposDato();
    }

    public void consultarParametros() {
        logger.debug("ParametrosMB::consultarParametros");
        ParametrosFL parametrosFl = findFlowObject(ParametrosFL.class, ParametrosFL.NOMBRE_BEAN);
        List<ParametroSeguridadDto> resultados = parametrosSeguridadEjb.consultarParametroSeguridad(parametrosFl
                .getFiltroNombre());

        for (ParametroSeguridadDto parametroSeguridadDto : resultados) {
            try {
                parametroSeguridadDto.setDescripcion(getBundle(NOMBRE_BUNDLE_PARAMETROS).getString(
                        parametroSeguridadDto.getNombre()));
            } catch (MissingResourceException e) {
                logger.debug("Falta descipcion del parametro= " + parametroSeguridadDto.getNombre()
                        + " en el archivo bundle: " + NOMBRE_BUNDLE_PARAMETROS);
            }
        }

        parametrosFl.setResultadoConsulta(resultados);
        parametrosFl.setConsultaRealizada(true);
    }

    public void validarTipoDato() {
        logger.debug("ParametrosMB::validarTipoDato");
        ModificarParametroFL parametrosFl = findFlowObject(ModificarParametroFL.class, ModificarParametroFL.NOMBRE_BEAN);
        parametrosFl.validarTipoDato();
    }

    public boolean validarModificacion() {
        ModificarParametroFL modificarParametrosFl = findFlowObject(ModificarParametroFL.class,
                ModificarParametroFL.NOMBRE_BEAN);
        if (!modificarParametrosFl.getParametroSeleccionado().isEditable()) {
            addErrorMessage(NOMBRE_BUNDLE_PARAMETROS, "msg_error_parametro_no_editable");
            return false;
        }
        return true;
    }

    public boolean actualizarParametro() {
        logger.debug("ParametrosMB::actualizarParametro");
        ModificarParametroFL parametrosFl = findFlowObject(ModificarParametroFL.class, ModificarParametroFL.NOMBRE_BEAN);

        // Validar tipo de dato si el parametro es numerico
        if (parametrosFl.isValorNumeroEntero()) {
            Matcher matcher = patronNumericoEntero.matcher(parametrosFl.getNumero());
            if (!matcher.matches()) {
                addErrorMessage(NOMBRE_BUNDLE_PARAMETROS, "msg_error_tipo_dato");
                return false;
            }
        } else if (parametrosFl.isValorNumeroDecimal()) {
            Matcher matcher = patronNumericoDecimal.matcher(parametrosFl.getNumero());
            if (!matcher.matches()) {
                addErrorMessage(NOMBRE_BUNDLE_PARAMETROS, "msg_error_tipo_dato");
                return false;
            }
        }
        // Transformar el valor del parametro a string
        parametrosFl.transformarValorParametro();
        // Actualizar el parametro
        parametrosSeguridadEjb.actualizarParametroSeguridad(parametrosFl.getParametroSeleccionado());
        addInfoMessage(NOMBRE_BUNDLE_PARAMETROS, "msg_parametro_guardado");
        return true;

    }

    public Map<String, String> getTiposDato() {
        return tiposDato;
    }

    public void setTiposDato(Map<String, String> tiposDato) {
        this.tiposDato = tiposDato;
    }

}
