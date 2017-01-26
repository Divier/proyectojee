package co.com.datatools.c2.managed_bean.administracion.direccion;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoDireccion;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.managed_bean.comun.CatalogoDireccionMB;
import co.com.datatools.c2.negocio.interfaces.IRDireccion;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * DireccionMB gestiona las acciones para la insercion de una direccion al sistema.
 * 
 * @author rodrigo.cruz <br>
 *         (modificado: 2014-04-01) - SWF <br>
 *         (modificado: 2015-10-02) - PoPuP <br>
 *         (modificado: 2015-11-20) - Se eliminan reglas de negocio quemadas en el MB, validaciones se hacen en capa de negocio
 * @version 2.0
 */
@ManagedBean
@SessionScoped
public class DireccionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(DireccionMB.class.getName());
    private static final String AGREGAR_DIRECCION = "AGREGAR_DIRECCION";

    private DireccionFL dirFL;
    private DireccionHolderFL dirHolderFL;

    @ManagedProperty(value = "#{catalogoDireccionMB}")
    private CatalogoDireccionMB catalogosDir;

    @EJB
    private IRDireccion iRDireccion;

    public DireccionMB() {
        dirHolderFL = new DireccionHolderFL();
        dirFL = new DireccionFL();
    }

    /**
     * REGISTRAR DIRECCION NUEVO
     */

    private void reset() {
        RequestContext.getCurrentInstance().reset("form-ingreso-direccion:txtDir");
        RequestContext.getCurrentInstance().reset("form-ingreso-direccion:pnlMun");
        RequestContext.getCurrentInstance().reset("form-ingreso-direccion:panel-formulario");
        RequestContext.getCurrentInstance().reset("form-ingreso-direccion:panel-formulario-complemento");
    }

    public void limpiarDatos() {
        dirHolderFL.limpiarDatos();
        dirFL.limpiarDireccion();
        if (!dirHolderFL.isSeleccionaPais())
            dirFL.getDireccionDTO().setPais(getPais());
        reset();
    }

    public void volver() {
        limpiarDatos();
        dirHolderFL.setPopup(false);
    }

    public String consultarValorCatalogo(List<SelectItem> catalogo, Integer valor) {
        String key = null;
        for (SelectItem entry : catalogo) {
            Integer value = (Integer) entry.getValue();
            if (valor.intValue() == value.intValue()) {
                key = entry.getLabel();
                break;
            }
        }
        return key;
    }

    /*
     * 1 - DIRECCION SIMPLE/VALIDADA
     */

    public void inicializarDireccion(DireccionDTO direccionDTO) {
        logger.debug("DireccionMB::inicializarDireccion(DireccionDTO)");
        dirHolderFL.setPopup(true);
        dirHolderFL.setValidaTipoDireccion(true);
        if (direccionDTO == null) {
            dirFL = new DireccionFL();
        } else {
            dirFL.setDireccionDTO(direccionDTO);
        }
        if (!dirHolderFL.isSeleccionaPais())
            dirFL.getDireccionDTO().setPais(getPais());
        if (dirFL.getDireccionDTO().getPais() != null && dirFL.getDireccionDTO().getPais().getId() != null) {
            catalogosDir.crearOpcionesDepartamento(dirFL.getDireccionDTO().getPais().getId());
            catalogosDir.crearOpcionesTipoVia(dirFL.getDireccionDTO().getPais().getId());
            catalogosDir.crearOpcionesCardinalidad(dirFL.getDireccionDTO().getPais().getId());
        }
        if (dirFL.getDireccionDTO().getDepartamento() != null
                && dirFL.getDireccionDTO().getDepartamento().getId() != null) {
            catalogosDir.crearOpcionesMunicipio(dirFL.getDireccionDTO().getDepartamento().getId());
        }
        if (dirFL.getDireccionDTO().getMunicipio() != null && dirFL.getDireccionDTO().getMunicipio().getId() != null) {
            catalogosDir.crearOpcionesLocalidad(dirFL.getDireccionDTO().getMunicipio().getId());
            catalogosDir.crearOpcionesNombreViaPrincipal(dirFL.getDireccionDTO().getMunicipio().getId(), null);
            catalogosDir.crearOpcionesNombreViaSecundaria(dirFL.getDireccionDTO().getMunicipio().getId(), null);
        }
    }

    public void validarTipoDireccion() {
        /*
         * if (dirHolderFL.isValidaTipoDireccion()) { cambiarTipoDireccion(); } else {
         */
        dirHolderFL.setValidaDireccion(false);
        dirHolderFL.setTipoDireccion(EnumTipoDireccion.SIMPLE);
        /* } */
    }

    public void cambiarTipoDireccion() {
        switch (dirHolderFL.getTipoDireccion()) {
        case SIMPLE:
            dirHolderFL.setValidaDireccion(false);
            break;
        case VALIDADO:
            dirHolderFL.setValidaDireccion(true);
            break;
        default:
            break;
        }
    }

    /*
     * 2 - UBICACION: ACTUALIZA PAIS/DEPTO/MUNICIPIO/LOCALIDAD
     */

    // PAIS: ACTUALIZA CARDINALIDAD/TIPOVIA

    public void actPais(Integer idPais) {
        catalogosDir.crearOpcionesDepartamento(idPais);
        catalogosDir.crearOpcionesTipoVia(idPais);
        catalogosDir.crearOpcionesCardinalidad(idPais);
        dirFL.getDireccionDTO().setDepartamento(new DepartamentoDTO());
        dirFL.getDireccionDTO().setMunicipio(new MunicipioDTO());
        dirFL.getDireccionDTO().setLocalidad(new LocalidadDTO());
        dirFL.limpiarVias();
        dirFL.getDireccionDTO().setComplemento(null);
        actualizarTextoDir();
    }

    // DEPTO

    public void actDepartamento(Integer idDepartamento) {
        catalogosDir.crearOpcionesMunicipio(idDepartamento);
        dirFL.getDireccionDTO().setMunicipio(new MunicipioDTO());
        dirFL.getDireccionDTO().setLocalidad(new LocalidadDTO());
        dirFL.limpiarVias();
        dirFL.getDireccionDTO().setComplemento(null);
        actualizarTextoDir();
    }

    // MUNICIPIO: ACTUALIZA CON TIPOVIA A NOMBREVIA

    public void actMunicipio(Integer idMunicipio) {
        catalogosDir.crearOpcionesLocalidad(idMunicipio);
        catalogosDir.crearOpcionesNombreViaPrincipal(idMunicipio, null);
        catalogosDir.crearOpcionesNombreViaSecundaria(idMunicipio, null);
        dirFL.getDireccionDTO().setLocalidad(new LocalidadDTO());
        dirFL.limpiarVias();
        dirFL.getDireccionDTO().setComplemento(null);
        actualizarTextoDir();
    }

    /*
     * 3 - ACTUALIZA VIAS
     */

    // TIPOVIA: ACTUALIZA NUMERICO/CATALOGO

    public void actTipoViaPrincipal(Integer codTipoVia, Integer idMunicipio) {
        // NUMERICO: ACTUALIZA NUMEROVIA/LETRAVIA/BISVIA/CARDINA
        dirHolderFL.setTipoViaPriNumerico(EnumTipoVia.obtenerTiposViaNumericos().contains(codTipoVia));
        if (EnumTipoVia.obtenerTiposViaCatalogo().contains(codTipoVia))
            // CATALOGO: ACTUALIZA NOMBREVIA CON MUNICIPIO
            catalogosDir.crearOpcionesNombreViaPrincipal(idMunicipio, codTipoVia);
        dirFL.limpiarViaPrincipal(false);
        actualizarTextoDir();
    }

    public void actTipoViaSecundaria(Integer codTipoVia, Integer idMunicipio) {
        // NUMERICO: ACTUALIZA NUMEROVIA/LETRAVIA/BISVIA/CARDINA
        dirHolderFL.setTipoViaSecNumerico(EnumTipoVia.obtenerTiposViaNumericos().contains(codTipoVia));
        if (EnumTipoVia.obtenerTiposViaCatalogo().contains(codTipoVia))
            // CATALOGO: ACTUALIZA NOMBREVIA CON MUNICIPIO
            catalogosDir.crearOpcionesNombreViaSecundaria(idMunicipio, codTipoVia);
        dirFL.limpiarViaSecundaria(false);
        actualizarTextoDir();
    }

    // BIS: ACTUALIZA LETRABISVIA

    public void actLetraViaPrincipal(String letraVia, String bis) {
        if (letraVia == null)
            dirFL.getDireccionDTO().setBisViaPrincipal(null);
        dirFL.getDireccionDTO().setLetraBisViaPrincipal(null);
    }

    public void actLetraViaSecundaria(String letraVia, String bis) {
        if (letraVia == null)
            dirFL.getDireccionDTO().setBisViaSecundaria(null);
        dirFL.getDireccionDTO().setLetraBisViaSecundaria(null);
    }

    /*
     * 4 - TEXTO DIRECCION: SE ACTUALIZA POR (3)
     */

    public void actualizarTextoDir() {
        // [TVS]
        if (dirFL.getDireccionDTO().getTipoViaPrincipal().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcTipoVia(),
                    dirFL.getDireccionDTO().getTipoViaPrincipal().getCodigo());
            dirFL.getDireccionDTO().getTipoViaPrincipal().setNombre(etiqueta);
        }
        // [NVP]
        if (dirFL.getDireccionDTO().getNombreViaPrincipal().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcNombreViaPri(),
                    dirFL.getDireccionDTO().getNombreViaPrincipal().getCodigo());
            dirFL.getDireccionDTO().getNombreViaPrincipal().setNombre(etiqueta);
        }
        // [CVP]
        if (dirFL.getDireccionDTO().getCardinalidadViaPrincipal().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcCardinalidadDir(),
                    dirFL.getDireccionDTO().getCardinalidadViaPrincipal().getCodigo());
            dirFL.getDireccionDTO().getCardinalidadViaPrincipal().setNombre(etiqueta);
        }
        // [TVS]
        if (dirFL.getDireccionDTO().getTipoViaSecundaria().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcTipoVia(),
                    dirFL.getDireccionDTO().getTipoViaSecundaria().getCodigo());
            dirFL.getDireccionDTO().getTipoViaSecundaria().setNombre(etiqueta);
        }
        // [NVS]
        if (dirFL.getDireccionDTO().getNombreViaSecundaria().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcNombreViaSec(),
                    dirFL.getDireccionDTO().getNombreViaSecundaria().getCodigo());
            dirFL.getDireccionDTO().getNombreViaSecundaria().setNombre(etiqueta);
        }
        // [CVS]
        if (dirFL.getDireccionDTO().getCardinalidadViaSecundaria().getCodigo() != null) {
            String etiqueta = consultarValorCatalogo(catalogosDir.getOpcCardinalidadDir(),
                    dirFL.getDireccionDTO().getCardinalidadViaSecundaria().getCodigo());
            dirFL.getDireccionDTO().getCardinalidadViaSecundaria().setNombre(etiqueta);
        }
    }

    /*
     * REGISTRAR DIRECCION
     */

    public String registrarDireccion() {
        boolean direccionValida = true;

        if (!dirHolderFL.isValidaDireccion())
            dirFL.getDireccionDTO().setTipoViaPrincipal(new TipoViaDTO(EnumTipoVia.SIN_IDENTIFICAR.getCodigo()));

        // Se valida la direccion completa
        RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> rta = iRDireccion.validarDireccion(dirFL.getDireccionDTO(),
                EnumTipoValidacionDireccion.INFRACTOR);

        // Si la direccion es incorrecta, no realiza insercion y retorna la lista de errores
        if (rta.getRespuesta().equals(EnumRespuestaSistema.ERRORES))
            for (Map.Entry<EnumCampoDireccion, List<EnumErrorDireccion>> error : rta.getErroresCampo().entrySet())
                for (EnumErrorDireccion errorDir : error.getValue()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR", error.getKey() + " " + errorDir.getDesc()));
                    direccionValida = false;
                }

        if (direccionValida) {
            RequestContext.getCurrentInstance().execute("PF('dialogoDireccion').hide()");
            return AGREGAR_DIRECCION;
        } else {
            return null;
        }
    }

    /* GET - SET */

    public DireccionFL getDirFL() {
        return dirFL;
    }

    public void setDirFL(DireccionFL dirFL) {
        this.dirFL = dirFL;
    }

    public DireccionHolderFL getDirHolderFL() {
        return dirHolderFL;
    }

    public void setDirHolderFL(DireccionHolderFL dirHolderFL) {
        this.dirHolderFL = dirHolderFL;
    }

    public CatalogoDireccionMB getCatalogosDir() {
        return catalogosDir;
    }

    public void setCatalogosDir(CatalogoDireccionMB catalogosDir) {
        this.catalogosDir = catalogosDir;
    }

}
