package co.com.datatools.c2.managed_bean.comun;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoDireccion;
import co.com.datatools.c2.enumeracion.EnumTipoVia;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Este MB permite consultar los catalogos relacionados con direccion.
 * 
 * @author rodrigo.cruz
 */
@ManagedBean
@SessionScoped
public class CatalogoDireccionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(CatalogoDireccionMB.class.getName());

    private final static String BIS = "BIS";

    private final Character[] alfabeto = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private List<SelectItem> opcTipoDireccion;
    private List<SelectItem> opcTipoVia;
    private List<SelectItem> opcLetraVia;
    private List<SelectItem> opcBisVia;
    private List<SelectItem> opcCardinalidadDir;
    private List<SelectItem> opcPais;
    private List<SelectItem> opcDepartamento;
    private List<SelectItem> opcMunicipio;
    private List<SelectItem> opcLocalidad;
    private List<SelectItem> opcNombreViaPri;
    private List<SelectItem> opcNombreViaSec;

    @EJB
    private IRAdministracion iRAdministracion;

    public CatalogoDireccionMB() {
        logger.debug("CatalogoDireccionMB::CatalogoDireccionMB");
    }

    @PostConstruct
    public void iniciar() {
        // Se crean los catalogos y se ponen en sesion
        crearOpcionesTipoDireccion();
        crearOpcionesTipoVia(null);
        crearOpcionesNombreVia(null, null);
        crearOpcionesLetraVia();
        crearOpcionesBisVia();
        crearOpcionesCardinalidad(null);
        crearOpcionesPais();
        crearOpcionesDepartamento(null);
        crearOpcionesMunicipio(null);
        crearOpcionesLocalidad(null);
    }

    public void crearOpcionesTipoDireccion() {
        opcTipoDireccion = new ArrayList<SelectItem>();
        opcTipoDireccion.add(new SelectItem(EnumTipoDireccion.SIMPLE, EnumTipoDireccion.SIMPLE.getLabel()));
        opcTipoDireccion.add(new SelectItem(EnumTipoDireccion.VALIDADO, EnumTipoDireccion.VALIDADO.getLabel()));
    }

    /**
     * Crea el catalogo de Tipos de Via asociados a un pais.
     */
    public void crearOpcionesTipoVia(Integer idPais) {
        List<TipoViaDTO> lTipoViaDTO = iRAdministracion.consultarTipoVia(idPais);
        opcTipoVia = new ArrayList<>();
        for (TipoViaDTO tv : lTipoViaDTO)
            if (tv.getCodigo() != EnumTipoVia.SIN_IDENTIFICAR.getCodigo())
                opcTipoVia.add(new SelectItem(tv.getCodigo(), tv.getNombre()));
    }

    /**
     * Crea el catalogo de Nombres de Via asociados a un Municipio.
     */
    public List<NombreViaDTO> crearOpcionesNombreVia(Integer idMunicipio, Integer codigoTipoVia) {
        List<NombreViaDTO> lNombreViaDTO = iRAdministracion.consultarNombreVia(idMunicipio, codigoTipoVia);
        opcNombreViaPri = new ArrayList<>();
        opcNombreViaSec = new ArrayList<>();
        for (NombreViaDTO nv : lNombreViaDTO) {
            opcNombreViaPri.add(new SelectItem(nv.getCodigo(), nv.getNombre()));
            opcNombreViaSec.add(new SelectItem(nv.getCodigo(), nv.getNombre()));
        }
        return lNombreViaDTO;
    }

    public List<NombreViaDTO> crearOpcionesNombreViaPrincipal(Integer idMunicipio, Integer codigoTipoVia) {
        List<NombreViaDTO> lNombreViaDTO = iRAdministracion.consultarNombreVia(idMunicipio, codigoTipoVia);
        opcNombreViaPri = new ArrayList<>();
        for (NombreViaDTO nv : lNombreViaDTO)
            opcNombreViaPri.add(new SelectItem(nv.getCodigo(), nv.getNombre()));
        return lNombreViaDTO;
    }

    public List<NombreViaDTO> crearOpcionesNombreViaSecundaria(Integer idMunicipio, Integer codigoTipoVia) {
        List<NombreViaDTO> lNombreViaDTO = iRAdministracion.consultarNombreVia(idMunicipio, codigoTipoVia);
        opcNombreViaSec = new ArrayList<>();
        for (NombreViaDTO nv : lNombreViaDTO)
            opcNombreViaSec.add(new SelectItem(nv.getCodigo(), nv.getNombre()));
        return lNombreViaDTO;
    }

    public void crearOpcionesLetraVia() {
        opcLetraVia = new ArrayList<>();
        for (Character letra : alfabeto)
            opcLetraVia.add(new SelectItem(letra, String.valueOf(letra)));
    }

    public void crearOpcionesBisVia() {
        opcBisVia = new ArrayList<>();
        opcBisVia.add(new SelectItem(BIS, BIS));
    }

    /**
     * Crea el catalogo de Cardinalidades asociados a un Pais.
     */
    public void crearOpcionesCardinalidad(Integer idPais) {
        List<CardinalidadDireccionDTO> lCardinalidadDirDTO = iRAdministracion.consultarCardinalidadDireccion(idPais);
        opcCardinalidadDir = new ArrayList<>();
        for (CardinalidadDireccionDTO cd : lCardinalidadDirDTO)
            opcCardinalidadDir.add(new SelectItem(cd.getCodigo(), cd.getNombre()));
    }

    /**
     * Crea el catalogo de Paises.
     */
    public void crearOpcionesPais() {
        List<PaisDTO> lPaisDTO = iRAdministracion.consultarPais(null);
        opcPais = new ArrayList<>();
        for (PaisDTO p : lPaisDTO)
            opcPais.add(new SelectItem(p.getId(), p.getNombre()));
    }

    /**
     * Crea el catalogo de Departamentos asociados a un Pais.
     * 
     * @param idPais
     * 
     * @return List<SelectItem> con los departamentos disponibles
     */
    public List<SelectItem> crearOpcionesDepartamento(Integer idPais) {
        DepartamentoDTO departamentoDTO = new DepartamentoDTO();
        departamentoDTO.setPais(new PaisDTO(idPais));
        List<DepartamentoDTO> lDeptoDTO = iRAdministracion.consultarDepartamento(departamentoDTO);
        opcDepartamento = new ArrayList<>();
        for (DepartamentoDTO d : lDeptoDTO)
            opcDepartamento.add(new SelectItem(d.getId(), d.getNombre()));
        return opcDepartamento;
    }

    /**
     * Crea el catalogo de Municipios asociados a un Departamento.
     * 
     * @param idDepartamento
     * 
     * @return List<SelectItem> con los municipios disponibles para el departamento indicado
     */
    public List<SelectItem> crearOpcionesMunicipio(Integer idDepartamento) {
        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setDepartamento(new DepartamentoDTO(idDepartamento));
        List<MunicipioDTO> lMunicipioDTO = iRAdministracion.consultarMunicipio(municipioDTO);
        opcMunicipio = new ArrayList<>();
        for (MunicipioDTO m : lMunicipioDTO)
            opcMunicipio.add(new SelectItem(m.getId(), m.getNombre()));
        return opcMunicipio;
    }

    /**
     * Crea el catalogo de Localidades asociadas a un Municipio.
     * 
     * @param idMunicipio
     * 
     * @return List<SelectItem> con las localidades disponibles para el municipio indicado
     */
    public List<SelectItem> crearOpcionesLocalidad(Integer idMunicipio) {
        LocalidadDTO localidadDTO = new LocalidadDTO();
        localidadDTO.setMunicipio(new MunicipioDTO(idMunicipio));
        List<LocalidadDTO> lLocalidadDTO = iRAdministracion.consultarLocalidad(localidadDTO);
        opcLocalidad = new ArrayList<>();
        for (LocalidadDTO l : lLocalidadDTO)
            opcLocalidad.add(new SelectItem(l.getId(), l.getNombre()));
        return opcLocalidad;
    }

    /* Metodos GET y SET */

    public List<SelectItem> getOpcTipoVia() {
        return opcTipoVia;
    }

    public void setOpcTipoVia(List<SelectItem> opcTipoVia) {
        this.opcTipoVia = opcTipoVia;
    }

    public List<SelectItem> getOpcLetraVia() {
        return opcLetraVia;
    }

    public void setOpcLetraVia(List<SelectItem> opcLetraVia) {
        this.opcLetraVia = opcLetraVia;
    }

    public List<SelectItem> getOpcBisVia() {
        return opcBisVia;
    }

    public void setOpcBisVia(List<SelectItem> opcBisVia) {
        this.opcBisVia = opcBisVia;
    }

    public List<SelectItem> getOpcCardinalidadDir() {
        return opcCardinalidadDir;
    }

    public void setOpcCardinalidadDir(List<SelectItem> opcCardinalidadDir) {
        this.opcCardinalidadDir = opcCardinalidadDir;
    }

    public List<SelectItem> getOpcPais() {
        return opcPais;
    }

    public void setOpcPais(List<SelectItem> opcPais) {
        this.opcPais = opcPais;
    }

    public List<SelectItem> getOpcDepartamento() {
        return opcDepartamento;
    }

    public void setOpcDepartamento(List<SelectItem> opcDepartamento) {
        this.opcDepartamento = opcDepartamento;
    }

    public List<SelectItem> getOpcMunicipio() {
        return opcMunicipio;
    }

    public void setOpcMunicipio(List<SelectItem> opcMunicipio) {
        this.opcMunicipio = opcMunicipio;
    }

    public List<SelectItem> getOpcLocalidad() {
        return opcLocalidad;
    }

    public void setOpcLocalidad(List<SelectItem> opcLocalidad) {
        this.opcLocalidad = opcLocalidad;
    }

    public List<SelectItem> getOpcTipoDireccion() {
        return opcTipoDireccion;
    }

    public void setOpcTipoDireccion(List<SelectItem> opcTipoDireccion) {
        this.opcTipoDireccion = opcTipoDireccion;
    }

    public List<SelectItem> getOpcNombreViaPri() {
        return opcNombreViaPri;
    }

    public void setOpcNombreViaPri(List<SelectItem> opcNombreViaPri) {
        this.opcNombreViaPri = opcNombreViaPri;
    }

    public List<SelectItem> getOpcNombreViaSec() {
        return opcNombreViaSec;
    }

    public void setOpcNombreViaSec(List<SelectItem> opcNombreViaSec) {
        this.opcNombreViaSec = opcNombreViaSec;
    }

}
