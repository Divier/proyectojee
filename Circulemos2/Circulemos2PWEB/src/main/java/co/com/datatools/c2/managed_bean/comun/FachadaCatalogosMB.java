package co.com.datatools.c2.managed_bean.comun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.comparendo.CausalInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.patios.PatioDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumEventoFomulario;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRConfiguracionEmail;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionInfraccion;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.negocio.interfaces.patios.IRPatio;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.InicioAppCirculemos;

/**
 * Clase de fachada para acceder a los catalogos alojados en el contexto aplicacion
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class FachadaCatalogosMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 5238142975714924657L;

    @ManagedProperty(value = "#{inicioAppCirculemos}")
    private InicioAppCirculemos inicioAppCirculemos;

    @EJB
    private IRAdministracion administracionEJB;

    @EJB
    private IRAdministracionInfraccion administracionInfraccionEJB;

    @EJB
    private IRAdministracionComparendo administracionComparendoEJB;

    @EJB
    private IRConfiguracionEmail configuracionEmailEJB;

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;

    @EJB
    private IRConfiguracion configuracionesEJB;

    @EJB
    private IRCatalogo iRCatalogo;

    @EJB
    private IRPatio patioEJB;

    public InicioAppCirculemos getInicioAppCirculemos() {
        return inicioAppCirculemos;
    }

    public void setInicioAppCirculemos(InicioAppCirculemos inicioAppCirculemos) {
        this.inicioAppCirculemos = inicioAppCirculemos;
    }

    public static final SelectItemComparator SELECT_ITEM_COMPARATOR = new SelectItemComparator();

    public static class SelectItemComparator implements Comparator<SelectItem>, Serializable {

        private static final long serialVersionUID = 1740776134445747568L;

        @Override
        public int compare(SelectItem selectItem1, SelectItem selectItem2) {
            return selectItem1.getLabel().compareTo(selectItem2.getLabel());
        }
    }

    /**
     * Consulta los itemes de un catalogo
     * 
     * @param EnumCatalogo
     * @author giovanni.velandia
     * @return catalogo listado de selectItems dependiendo el tipo de catalogo
     */
    public List<SelectItem> consultarCatalogos(EnumCatalogo EnumCatalogo, Integer idDependencia) {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo);
        itemsCatalogos = new ArrayList<SelectItem>();
        List<ItemCatalogoDTO> itemCatalogoDTOs = null;

        /*
         * Validamos si es un catalogo simple o compuesto
         */
        if (idDependencia != null) {
            ItemCatalogoDTO itemCatalogoDTO = new ItemCatalogoDTO();
            itemCatalogoDTO.setItemCatalogoDependenciaDTO(new ItemCatalogoDTO());
            itemCatalogoDTO.getItemCatalogoDependenciaDTO().setId(idDependencia);
            itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(EnumCatalogo.toString(), itemCatalogoDTO);
        } else {
            itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(EnumCatalogo.toString(), null);
        }

        for (ItemCatalogoDTO itemCatalogoDTO : itemCatalogoDTOs) {
            itemsCatalogos.add(new SelectItem(itemCatalogoDTO.getId(), itemCatalogoDTO.getNombre(),
                    itemCatalogoDTO.getDescripcion()));
        }
        Collections.sort(itemsCatalogos, SELECT_ITEM_COMPARATOR);
        inicioAppCirculemos.addCatalogo(EnumCatalogo, itemsCatalogos);

        return itemsCatalogos;
    }

    /*
     * ******************************************************************************
     * 
     * GENERAL
     * 
     * ******************************************************************************
     */

    /**
     * Listado de opciones de tipo de identificacion para el organismo
     * 
     * @return SelectItem tipo identificacion
     * @author giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catTipoIdentificacion() {
        // Esencial
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoIdentificacionPersona);
        if (catalogo == null) {
            catalogo = new ArrayList<>(8);
            List<TipoIdentificacionPersonaDTO> listTipoIdPais = this.administracionEJB
                    .consultarTipoIdentificacionPersona(getPais(), null);
            for (TipoIdentificacionPersonaDTO tipDTO : listTipoIdPais) {
                catalogo.add(new SelectItem(tipDTO.getId(), tipDTO.getNombre()));
            }
            // Agregar si no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoIdentificacionPersona, catalogo);
        }
        return catalogo;
    }

    /**
     * Listado de opciones de tipo de identificacion para el organismo sin incluir tipo de juridica
     * 
     * @return SelectItem Tipo Identificacion No juridica
     * @author giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catTipoIdentificacionNoJuridica() {
        // Esencial
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TIPO_IDENTIFICACION_NO_JURIDICA);
        if (catalogo == null) {
            catalogo = new ArrayList<>(8);
            final TipoIdentificacionPersonaDTO tipoIdJuridica = this.administracionEJB
                    .obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId());
            List<TipoIdentificacionPersonaDTO> listTipoIdPais = this.administracionEJB
                    .consultarTipoIdentificacionPersona(getPais(), null);
            for (TipoIdentificacionPersonaDTO tipDTO : listTipoIdPais) {
                if (!tipoIdJuridica.getId().equals(tipDTO.getId())) {
                    catalogo.add(new SelectItem(tipDTO.getId(), tipDTO.getNombre()));
                }
            }
            // Agregar, no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TIPO_IDENTIFICACION_NO_JURIDICA, catalogo);
        }
        return catalogo;
    }

    /**
     * Listado de los organismos de transito disponible esta consulta se debe tratar de diferente manera, ya que siempre tenemos que tener el
     * organismo de transito del usuario en session, por lo tanto no debemos tenerlo en cache si no el usuario en consulta.
     * 
     * @return SelectItem Organismo Transito
     * @author divier.casas (mod 2015-10-07)
     */
    public List<SelectItem> catOrganismoTransito() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.organismo_transito);
        if (catalogo == null) {
            catalogo = new ArrayList<>(1);
            List<OrganismoTransitoDTO> lstOrganismoTransitoDTO = this.administracionEJB
                    .consultarOrganismosAsociados(getOrganismoTransito().getCodigoOrganismo());
            for (OrganismoTransitoDTO organismoTransitoDTO : lstOrganismoTransitoDTO) {
                catalogo.add(new SelectItem(organismoTransitoDTO.getCodigoOrganismo(),
                        organismoTransitoDTO.getCodigoOrganismo() + " - " + organismoTransitoDTO.getNombreOrganismo()));
            }
            inicioAppCirculemos.addCatalogo(EnumCatalogo.organismo_transito, catalogo);
        }
        return catalogo;
    }

    /*
     * ******************************************************************************
     * 
     * INFRACCIONES
     * 
     * ******************************************************************************
     */

    /**
     * @author giovanni.velandia (mod 2015-05-22)
     * @return SelectItem Causal Infraciones
     */
    public List<SelectItem> catCausalInfraccion() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.CAUSAL_INFRACCION);
        if (catalogo == null) {
            catalogo = new ArrayList<>(10);
            List<CausalInfraccionDTO> lstCausalesInfr = administracionInfraccionEJB
                    .consultarCausalesInfraccion(getCodigoOrganismoTransito());
            for (CausalInfraccionDTO causalInfraccionDTO : lstCausalesInfr) {
                catalogo.add(new SelectItem(causalInfraccionDTO.getId(), causalInfraccionDTO.getNombre(),
                        causalInfraccionDTO.getDescripcion()));
            }
            // Agregar si no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.CAUSAL_INFRACCION, catalogo);
        }
        return catalogo;
    }

    /**
     * @author giovanni.velandia (mod 2015-05-22)
     * @return SelectItem Normatividad
     */
    public List<SelectItem> catNormatividad() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.NORMATIVIDAD);
        if (catalogo == null) {
            catalogo = new ArrayList<>(10);
            /*
             * List<NormatividadDTO> lstNormatividades = administracionEJB .consultarNormatividades(getCodigoOrganismoTransito());
             * 
             * for (NormatividadDTO normatividadDTO : lstNormatividades) { catalogo.add(new SelectItem(normatividadDTO.getId(),
             * normatividadDTO.getNombre(), normatividadDTO .getDescripcion())); }
             */
            // Agregar si no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.NORMATIVIDAD, catalogo);
        }
        return catalogo;
    }

    /**
     * @author giovanni.velandia (mod 2015-05-22)
     * @return SelectItem Tipo Sancion
     */
    public List<SelectItem> catTipoSancion() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TIPO_SANCION);
        if (catalogo == null) {
            catalogo = new ArrayList<>(10);
            List<TipoSancionDTO> lstTiposSancion = administracionInfraccionEJB.consultarTiposSancion(getPais().getId());
            for (TipoSancionDTO tipoSancionDTO : lstTiposSancion) {
                catalogo.add(new SelectItem(tipoSancionDTO.getId(), tipoSancionDTO.getNombre()));
            }
            // Agregar si no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TIPO_SANCION, catalogo);
        }
        return catalogo;
    }

    public List<SelectItem> catTipoReincidencia() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoReincidencia);
        if (catalogo == null) {
            catalogo = consultarCatalogos(EnumCatalogo.TipoReincidencia, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoReincidencia, catalogo);
        }
        return catalogo;
    }

    /*
     * ******************************************************************************
     * 
     * OTROS
     * 
     * ******************************************************************************
     */

    /**
     * Listado de opciones de Bancos para el organismo
     * 
     * @author giovanni.velandia (mod 2015-05-22)
     * @return SelectItem Clase interes
     */
    public List<SelectItem> catClaseInteres() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.ClaseInteres, null);
        return itemsCatalogos;
    }

    public List<SelectItem> catPeriodoInteres() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.PeriodoInteres, null);
        return itemsCatalogos;
    }

    /**
     * Listado de opciones de tipos de comunicacion de correo para el organismo
     * 
     * @author giovanni.velandia (mod 2015-05-22)
     * @return SelectItem Tipo comunicaciones correo
     */
    public List<SelectItem> catTipoComCorreo() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TIPO_COM_CORREO);
        if (catalogo == null) {
            catalogo = new ArrayList<>();
            List<TipoEmailDTO> lstTiposComunicacionesCorreo = configuracionEmailEJB
                    .consultarTiposEmail(getCodigoOrganismoTransito());
            for (TipoEmailDTO tipoEmailDTO : lstTiposComunicacionesCorreo) {
                catalogo.add(new SelectItem(tipoEmailDTO.getCodigo(), tipoEmailDTO.getNombre()));
            }
            // Agregar si no existe
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TIPO_COM_CORREO, catalogo);
        }
        return catalogo;
    }

    /**
     * Permite obtener y/o cargar el catalogo de las areas fisicas del organismo de transito
     * 
     * @return SelectItems con las posibles areas fisicas del organismo de transito
     * @author luis.forero giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catAreaFisica() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.AREA_FISICA);

        if (catalogo == null) {
            catalogo = new ArrayList<>(1);
            /*
             * List<AreaFisicaDTO> consultaAreasFisicas = administracionEJB.consultarArea(null, getCodigoOrganismoTransito()); for (AreaFisicaDTO
             * areaFisicaDTO : consultaAreasFisicas) { catalogo.add(new SelectItem(areaFisicaDTO.getId(), areaFisicaDTO.getNombre(), areaFisicaDTO
             * .getDescripcion())); }
             */
            inicioAppCirculemos.addCatalogo(EnumCatalogo.AREA_FISICA, catalogo);
        }

        return catalogo;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los tipos de entidades de agente de transito para el pais sobre el cual se esta autenticando
     * 
     * @return SelectItems con los tipos de entidades de agente de transito
     * @author luis.forero giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catTipoEntidadAgenteTransito() {
        // TODO SE DEBE HACER REFACTOR POR CAMBIO EN FORMULARIOS
        /*
         * 
         * List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TIPO_ENTIDAD_AGENTE_TRANSITO);
         * 
         * if (catalogo == null) { catalogo = new ArrayList<>(1);
         * 
         * List<TipoEntidadAgenteTransitoDTO> response = administracionFormulariosEJB .consultarTipoEntidadAgenteTransito(null); for
         * (TipoEntidadAgenteTransitoDTO tipoEntidadAgenteTransitoDTO : response) { catalogo.add(new SelectItem(tipoEntidadAgenteTransitoDTO.getId(),
         * tipoEntidadAgenteTransitoDTO .getNombre(), tipoEntidadAgenteTransitoDTO.getDescripcion())); }
         * 
         * inicioAppCirculemos.addCatalogo(EnumCatalogo.TIPO_ENTIDAD_AGENTE_TRANSITO, catalogo); }
         * 
         * return catalogo;
         */
        return null;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles paises.
     * 
     * @return SelectItems con los posibles paises
     * @author luis.forero (2015-01-20) giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catPaises() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.PAIS);

        if (catalogo == null) {
            catalogo = new ArrayList<>(1);

            List<PaisDTO> consultarPais = administracionEJB.consultarPais(null);
            for (PaisDTO paisDTO : consultarPais) {
                catalogo.add(new SelectItem(paisDTO.getId(), paisDTO.getNombre()));
            }

            inicioAppCirculemos.addCatalogo(EnumCatalogo.PAIS, catalogo);
        }

        return catalogo;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles tipos de infracciones en el sistema sin la opcion 'SIN TIPO'.
     * 
     * @return SelectItems con los posibles tipos de infracciones sin el la opcion 'SIN TIPO'
     * @author luis.forero (2015-01-19) giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catTipoInfraccionMenosSinTipo() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TIPO_INFRACCION);

        if (catalogo == null) {
            List<TipoInfraccionDTO> listTipoInfraccionDTOs = this.administracionInfraccionEJB
                    .consultarTiposInfraccion();
            catalogo = new ArrayList<SelectItem>(1);
            for (TipoInfraccionDTO tipoInfraccionDTO : listTipoInfraccionDTOs) {
                catalogo.add(new SelectItem(tipoInfraccionDTO.getId(), tipoInfraccionDTO.getNombre(),
                        tipoInfraccionDTO.getDescripcion()));
            }

            inicioAppCirculemos.addCatalogo(EnumCatalogo.TIPO_INFRACCION, catalogo);
        }

        return catalogo;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles tipos de formularios registrados en el sistema.
     * 
     * @return SelectItems con los posibles Estados Formulario
     * @author luis.forero (2015-01-15) giovanni.velandia (mod 2015-05-21)
     */
    public List<SelectItem> catEstadoFormulario() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoFormulario, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles estados de comparendo registrados en el sistema.
     * 
     * @author giovanni.velandia
     * @return SelectItems con los posibles Estados del comparendo
     */
    public List<SelectItem> catEstadoComparendo() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoComparendo, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener los estados de licencia registrados en el sistema.
     * 
     * @return SelectItem Esatados Licencia
     * @author giovanni.velandia
     */
    public List<SelectItem> catEstadoLicencia() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoLicencia, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener los colores registrados en el sistema.
     * 
     * @return SelectItem color
     * @author giovanni.velandia
     */
    public List<SelectItem> catColor() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.Color, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de las areas de los responsables
     * 
     * @return SelectItem Area Responsable
     * @author luis.forero (2015-02-09) giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catAreaResponsable() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.AreaResponsable, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles Estados Rango formuelarios registrados en el sistema.
     * 
     * @return SelectItem Estado Rango
     * @author luis.forero (2015-01-15) giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catEstadoRango() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoRangoFormulario, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles estados de responsable de formulario
     * 
     * @return SelectItem estado responsable formulario
     * @author luis.forero giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catEstadoResponsableFormulario() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoResponsable, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles Origen Prueba
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catOrigenPrueba() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.OrigenPrueba, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de las posibles Tipo Prueba
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catTipoPrueba() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoPrueba, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de las posibles Tipo Participante
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catTipoParticipante() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoParticipante, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de lss posibles caracteristicas prueba
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catCaracteristicaPrueba() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.CaracteristicaPrueba, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles mandos.
     * 
     * @return SelectItem mando
     * @author luis.forero giovanni.velandia (mod 2015-05-22)
     */
    public List<SelectItem> catMando() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.Mando, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener los Tipo Responsable Formulario registrados en el sistema.
     * 
     * @return SelectItem Tipo Responsable Formulario
     * @author claudia.rodriguez giovanni.velandia (mod 2015-05-25)
     */
    public List<SelectItem> catTiposResponsableFormulario() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoResponsableFormulario, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles tipos de formularios registrados en el sistema.
     * 
     * @return SelectItems con los posibles Tipo formulario
     * @author luis.forero (2015-01-15) giovanni.velandia (mod 2015-08-20)
     */
    public List<SelectItem> catTipoFormulario() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoFormulario);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoFormulario, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoFormulario, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Permite obtener los Tipo Propietario registrados en el sistema.
     * 
     * @return SelectItem Tipo Propietario
     * @author giovanni.velandia
     */
    public List<SelectItem> catTipoPropietario() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoPropietario, null);
        return itemsCatalogos;
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles eventos para los formularios.
     * 
     * @return SelectItems con los posibles Eventos
     * @author julio.pinzon
     */
    public List<SelectItem> catEvento() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoFormulario, null);
        List<SelectItem> itemsEventos = new ArrayList<SelectItem>();
        // Se filtran los estados utilizando la enumeracion de eventos
        for (SelectItem estados : itemsCatalogos) {
            for (EnumEventoFomulario evento : EnumEventoFomulario.values()) {
                if (evento.getValue().equals(estados.getValue())) {
                    itemsEventos.add(estados);
                    break;
                }
            }
        }
        return itemsEventos;
    }

    public List<SelectItem> catClaseServicio() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoServicio);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoServicio, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoServicio, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catTipoVehiculo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.ClaseVehiculo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.ClaseVehiculo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.ClaseVehiculo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catRadioAccion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.RadioAccion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.RadioAccion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.RadioAccion, itemsCatalogos);

        }
        return itemsCatalogos;
    }

    public List<SelectItem> catModalidad() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Modalidad);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Modalidad, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Modalidad, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catTransportePasajero() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoTransportePasajero);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoTransportePasajero, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoTransportePasajero, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catCategoriaLicencia() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoCategLicenciaConduccion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoCategLicenciaConduccion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoCategLicenciaConduccion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catTipoInfractor() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoInfractor);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoInfractor, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoInfractor, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catGradoAlcoholemia() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.GradoAlcoholemia);
        if (catalogo == null) {
            catalogo = new ArrayList<>(1);
            List<GradoAlcoholemiaDTO> lstGradoAlcoholemiaDTO = administracionComparendoEJB
                    .consultarGradoAlcoholemia(getOrganismoTransito().getCodigoOrganismo());
            for (GradoAlcoholemiaDTO gradoAlcoholemiaDTO : lstGradoAlcoholemiaDTO) {
                catalogo.add(new SelectItem(gradoAlcoholemiaDTO.getValor(),
                        gradoAlcoholemiaDTO.getValor() + " - " + gradoAlcoholemiaDTO.getNombre()));
            }
            inicioAppCirculemos.addCatalogo(EnumCatalogo.GradoAlcoholemia, catalogo);
        }
        return catalogo;
    }

    public List<SelectItem> catTipoEvidencia() {
        List<SelectItem> catalogo = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoEvidencia);
        if (catalogo == null) {
            catalogo = consultarCatalogos(EnumCatalogo.TipoEvidencia, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoEvidencia, catalogo);
        }
        return catalogo;
    }

    public List<SelectItem> catMedioImposicionComparendo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.MedioImposicionComparendo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.MedioImposicionComparendo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.MedioImposicionComparendo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catMarcaVehiculo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.MarcaVehiculo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.MarcaVehiculo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.MarcaVehiculo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    public List<SelectItem> catPatio() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Patio);
        if (itemsCatalogos == null) {
            itemsCatalogos = new ArrayList<>(0);
            PatioDTO patioDTO = new PatioDTO();
            patioDTO.setOrganismoTransito(getOrganismoTransito());
            List<PatioDTO> patios = patioEJB.consultarPatios(patioDTO);
            for (PatioDTO patio : patios) {
                itemsCatalogos.add(new SelectItem(patio.getId(), patio.getNombre()));
            }
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Patio, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de fallos
     * 
     * @return catalogo de tipos de fallos
     * @author julio.pinzon
     */
    public List<SelectItem> catTipoFallo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoFallo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoFallo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoFallo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de entidad
     * 
     * @return catalogo de tipos de entidad
     * @author giovanni.velandia
     */
    public List<SelectItem> catTipoEntidad() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoEntidad);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoEntidad, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoEntidad, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de peticion
     * 
     * @return catalogo de tipos de fallos
     * @author giovanni.velandia
     */
    public List<SelectItem> catTipoPeticion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoPeticion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoPeticion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoPeticion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de origen de impugnacion
     * 
     * @return catalogo de tipos de fallos
     * @author giovanni.velandia
     */
    public List<SelectItem> catOrigenImpugnacion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.OrigenImpugnacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.OrigenImpugnacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.OrigenImpugnacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de rechazos
     * 
     * @return catalogo de tipos de rechazos
     * @author divier.casas
     */
    public List<SelectItem> catTipoRechazo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoRechazo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoRechazo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoRechazo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de motivos para cerrar vigencia de agentes de transito
     * 
     * @return catalogo de motivos
     * @author divier.casas
     */
    public List<SelectItem> catMotivosVigenciaAgente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.MotivoVigenciaAgente);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.MotivoVigenciaAgente, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.MotivoVigenciaAgente, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de notificacion de comparendos
     * 
     * @return catalogo de tipos de notificacion de comparendos
     * @author julio.pinzon
     */
    public List<SelectItem> catTipoNotificacionComparendo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoNotificacionComparendo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoNotificacionComparendo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoNotificacionComparendo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de notificacion presencial de comparendos
     * 
     * @return catalogo de tipos de notificacion presencial de comparendos
     * @author julio.pinzon
     */
    public List<SelectItem> catTipoNotificacionComparendoPresencial() {
        List<SelectItem> itemsCatalogos = catTipoNotificacionComparendo();
        List<SelectItem> itemsTipoNotificacion = new ArrayList<SelectItem>();
        for (SelectItem selectItem : itemsCatalogos) {
            if (EnumTipoNotificacionComparendo.NOTIFICACION_PERSONAL.getValue().equals(selectItem.getValue())
                    || EnumTipoNotificacionComparendo.NOTIFICACION_POR_CONDUCTA_CONCLUYENTE.getValue()
                            .equals(selectItem.getValue())) {
                itemsTipoNotificacion.add(selectItem);
            }
        }
        return itemsTipoNotificacion;
    }

    /**
     * Consulta el catalogo de tipos de justificacion del proceso de impugnacion
     * 
     * @return catalogo de tipos de justificacion del proceso de impugnacion
     * @author dixon.alvarez
     */
    public List<SelectItem> catJustificacionImpugnacion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.JustificacionImpugnacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.JustificacionImpugnacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.JustificacionImpugnacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de accion back
     * 
     * @return catalogo de tipos de accion back
     * @author dixon.alvarez
     */
    public List<SelectItem> catTipoAccionBack() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoAccionBack);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoAccionBack, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoAccionBack, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de obligacion
     * 
     * @return catalogo de tipos de obligacion
     * @author julio.pinzon
     */
    public List<SelectItem> catTipoObligacion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoObligacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoObligacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoObligacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de estados de obligacion
     * 
     * @return catalogo de estado de obligacion
     * @author julio.pinzon
     */
    public List<SelectItem> catEstadoObligacion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.EstadoObligacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoObligacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.EstadoObligacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de cargue
     * 
     * @return catalogo de tipo de cargue
     * @author julio.pinzon
     */
    public List<SelectItem> catTipoCargueArchivo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoCargueArchivo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoCargueArchivo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoCargueArchivo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de teléfono
     * 
     * @return catalogo de tipos de teléfono
     * @author dixon.alvarez
     */
    public List<SelectItem> catTipoTelefono() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoTelefono);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoTelefono, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoTelefono, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de direccion
     * 
     * @return catalogo de tipos de direccion
     * @author dixon.alvarez
     */
    public List<SelectItem> catTipoDireccion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoUbicabilidad);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoUbicabilidad, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoUbicabilidad, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /*
     * ******************************************************************************
     * 
     * CATALOGOS COMPUESTOS
     * 
     * ******************************************************************************
     */
    /**
     * Se encarga de consultar el estado proceso por un tipo de proceso
     * 
     * @author giovanni.velandia
     * @param idTipoProceso
     * @return
     */
    public List<SelectItem> catEstadoProceso(Integer idTipoProceso) {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoProceso, idTipoProceso);
        return itemsCatalogos;
    }

    public List<SelectItem> catTipoProceso() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoProceso);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoProceso, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoProceso, itemsCatalogos);
        }
        return itemsCatalogos;

    }

    public List<SelectItem> catCausalCambioEstado(Integer idDependencia) {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.CausalCambioEstado, idDependencia);
        return itemsCatalogos;
    }

    public List<SelectItem> catLineaVehiculo(Integer idDependencia) {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.LineaVehiculo, idDependencia);
        return itemsCatalogos;
    }

    public List<SelectItem> catRuta(Long idEmpresa) {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Ruta);
        if (itemsCatalogos == null) {
            itemsCatalogos = new ArrayList<>(0);
            RutaDTO rutaDTOTemp = new RutaDTO();
            // EmpresaTransporteDTO empresaTransporte = new EmpresaTransporteDTO(idEmpresa);
            // rutaDTOTemp.setEmpresaTransporte(empresaTransporte);
            List<RutaDTO> rutas = administracionComparendoEJB.consultarRuta(getCodigoOrganismoTransito(), rutaDTOTemp);
            for (RutaDTO rutaDTO : rutas) {
                itemsCatalogos.add(new SelectItem(rutaDTO.getId(), rutaDTO.getNombre(), rutaDTO.getDescripcion()));
            }
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Ruta, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /*
     * ******************************************************************************
     * 
     * Parametrizacion de Configuraciones
     * 
     * ******************************************************************************
     */
    /**
     * Permite obtener los tipos de configuraciones registradas en el sistema (tambien conocidas como REGLAS de NEGOCIO)
     * 
     * @return Listado de tipos de configuraciones, lista vacia si no existe ninguno.
     * @author Felipe.Martinez
     */
    public List<SelectItem> catTipoConfiguraciones() {
        List<ItemCatalogoDTO> tiposConfiguracion = configuracionesEJB.consultarTiposConfiguracion();
        List<SelectItem> itemCatalogos = new ArrayList<>(tiposConfiguracion.size());
        for (ItemCatalogoDTO itemCatalogoDTO : tiposConfiguracion) {
            itemCatalogos.add(new SelectItem(itemCatalogoDTO.getCodigo(), itemCatalogoDTO.getNombre()));
        }
        return itemCatalogos;
    }

    /**
     * Obtiene el SelectItem correspondiente al Id seleccionado en la lista de catalogo
     * 
     * @param catalogo
     *            Enumeracion del catalogo
     * @param idCatalogo
     *            Id del elemento de catalogo seleccionado
     * @return SelectItem correspondiente al Id seleccionado en la lista de catalogo
     */
    public SelectItem getItemCatalogo(EnumCatalogo catalogo, Object idCatalogo) {
        return inicioAppCirculemos.getItemCatalogo(catalogo, idCatalogo);

    }

    /**
     * Obtiene el DTO de tipo de identificacion correspondiente a una persona juridica para el pais enviado
     * 
     * @param idPais
     *            Id del pais cuyo tipo de identificacion para persona juridica debe ser consultado
     * @return DTO de tipo de identificacion correspondiente a una persona juridica para el pais enviado
     */
    public TipoIdentificacionPersonaDTO getTipoIdentificacionEmpresa(int idPais) {
        return administracionEJB.obtenerTipoIdentificacionEmpresaJuridicaPais(idPais);
    }

    /**
     * Retorna un item catalogo de un <strong>id</strong> existente en el sistema
     * 
     * @param catalogo
     *            - correspondiente al catalogo del cual se va a consultar el item
     * @param id
     *            - Id asignado en el sistema
     * @return <strong>ItemCatalogoDTO</strong> con el objeto correspondiente al <strong>id</strong>
     * @throws <strong>IndexOutOfBounsException</strong>
     *             cuando el <strong>id</strong> del catalogo enviado no existe en el sistema
     */
    public ItemCatalogoDTO getItemCatalogoId(EnumCatalogo catalogo, Integer id) {
        if (id != null) {
            List<ItemCatalogoDTO> items = iRCatalogo.consultarItemsCatalogo(catalogo.toString(),
                    new ItemCatalogoDTO(id));
            return items.get(0);
        } else {
            return null;
        }
    }

    /**
     * Consulta los items activos de un catalogo en el sistema
     * 
     * @param catalogo
     *            - Nombre del catalogo del sistema
     * @return Listado de los items del catalogo activos en el sistema
     */
    public List<ItemCatalogoDTO> getItemsCatalogo(EnumCatalogo catalogo) {
        ItemCatalogoDTO filtro = new ItemCatalogoDTO();
        filtro.setActivo(true);
        return iRCatalogo.consultarItemsCatalogo(catalogo.toString(), filtro);
    }

    /**
     * Consulta el catalogo de tipos de justificación de envío a back
     * 
     * @return catalogo de tipos de justificación de envío a back
     * @author diego.fonseca
     */
    public List<SelectItem> catJustificacionImpugnacionBack() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.JustificacionImpugnacionBack);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.JustificacionImpugnacionBack, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.JustificacionImpugnacionBack, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de documentos
     * 
     * @return catalogo de tipos de documentos
     * @author divier.casas
     */
    public List<SelectItem> catTipoDocumento() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoDocumentoProceso);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoDocumentoProceso, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoDocumentoProceso, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el cartalogo de inconsistencias de agente en comparendos.
     * 
     * @return Lista con el catalogo de inconsistencias.
     * @author ricardo.chavarro
     */
    public List<SelectItem> catTipoInconsistenciaAgente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoInconsistenciaAgente);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoInconsistenciaAgente, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoInconsistenciaAgente, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de accidentes
     * 
     * @return catalogo de tipos de accidentes
     * @author divier.casas
     */
    public List<SelectItem> catTiposAccidentes() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoAccidente);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoAccidente, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoAccidente, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de unidades judiciales
     * 
     * @return catalogo de unidades judiciales
     * @author divier.casas
     */
    public List<SelectItem> catUnidadesJudiciales() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Prevencion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Prevencion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Prevencion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de Delegaciones del Agente
     * 
     * @return catalogo de Delegaciones del Agente
     * @author divier.casas
     */
    public List<SelectItem> catDelegacionAgente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Delegacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Delegacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Delegacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de persona accidente
     * 
     * @return catalogo de tipos de persona accidente
     * @author divier.casas
     */
    public List<SelectItem> catTipoPersonaAccidente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoPersonaIPAT);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoPersonaIPAT, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoPersonaIPAT, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de estado fisico de implicado en accidente
     * 
     * @return catalogo de estado fisico de implicado en accidente
     * @author divier.casas
     */
    public List<SelectItem> catEstadoFisicoImpliAccid() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.EstadoFisico);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.EstadoFisico, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.EstadoFisico, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de sentido circulacion al momento del accidente
     * 
     * @return catalogo de sentido circulacion al momento del accidente
     * @author divier.casas
     */
    public List<SelectItem> catSentidoCirculacionAccidente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Sentido);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Sentido, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Sentido, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de carril del vehiculo al momento del accidente
     * 
     * @return catalogo de carril del vehiculo al momento del accidente
     * @author divier.casas
     */
    public List<SelectItem> catCarrilAccidente() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Carril);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Carril, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Carril, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de procedimientos
     * 
     * @return catalogo de procedimientos
     * @author divier.casas
     */
    public List<SelectItem> catSoporteProcedimiento() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.SoporteProcedimiento);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.SoporteProcedimiento, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.SoporteProcedimiento, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de fuentes de información
     * 
     * @return catalogo de fuentes de información
     * @author ricardo.chavarro
     */
    public List<SelectItem> catTipoFuenteInformacion() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoFuenteInformacion);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoFuenteInformacion, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoFuenteInformacion, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de entidad oficio
     * 
     * @return catalogo de fuentes de información
     * @author giovanni.velandia
     */
    public List<SelectItem> catEntidadOficio() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.EntidadOficio);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.EntidadOficio, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.EntidadOficio, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de fuentes de información
     * 
     * @return catalogo de fuentes de información
     * @author ricardo.chavarro
     */
    public List<SelectItem> catTipoDestinoPruebaImpug() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoDestinoPruebaImpug);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoDestinoPruebaImpug, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoDestinoPruebaImpug, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de fuentes de información
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catCargo() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Cargo);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Cargo, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Cargo, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de fuentes de información
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<SelectItem> catDia() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.Dia);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.Dia, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.Dia, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * Consulta el catalogo de tipos de eventos
     * 
     * @return catalogo de tipos de eventos
     * @author ricardo.chavarro
     */
    public List<SelectItem> catTipoEvento() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoEvento);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoEvento, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoEvento, itemsCatalogos);
        }
        return itemsCatalogos;
    }

    /**
     * 
     * @return
     */
    public List<SelectItem> catTipoProcesoEvento() {
        List<SelectItem> itemsCatalogos = inicioAppCirculemos.getCatalogo(EnumCatalogo.TipoProcesoEvento);
        if (itemsCatalogos == null) {
            itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoProcesoEvento, null);
            inicioAppCirculemos.addCatalogo(EnumCatalogo.TipoProcesoEvento, itemsCatalogos);
        }
        return itemsCatalogos;

    }

}