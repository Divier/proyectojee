package co.com.datatools.c2.managed_bean.formularios.relacion_estados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;

/**
 * Objeto de flujo que permite controlar los atributos de filtro, los datos consultados y los datos a ingresar
 * 
 * @author luis.forero(2015-01-15)
 * 
 */
public class RelacionEstadoFormularioHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Atributos de filtro
     */    
	private Integer fId;
	private Integer fIdEstadoActual;
	private Integer fIdEstadoSiguiente;
    private Integer fIdTipoFormulario;
    private Integer fIdOrganismoTranisto;

    /**
     * listado de relaciones consultadas
     */
    private List<RelacionEstadosDTO> lstRelacionesEstados;
    /**
     * Relacion estados seleccionado
     */
    private RelacionEstadosDTO relacionEstadosSeleccionado;

    /**
     * Atributo que permite identificar una relacion nueva o por editar
     */
    private RelacionEstadosDTO relacionEstados;

    /**
     * 
     */
    public RelacionEstadoFormularioHolderFL() {
        lstRelacionesEstados = new ArrayList<>(1);
    }

    public void initRelacionEstados() {
        this.relacionEstados = new RelacionEstadosDTO();
        this.relacionEstados.setEstadoFormularioActual(new EstadoFormularioDTO());
        this.relacionEstados.setEstadoFormularioSiguiente(new EstadoFormularioDTO());
        this.relacionEstados.setTipoFormulario(new TipoFormularioDTO());
        this.relacionEstados.setOrganismoTranisto(new OrganismoTransitoDTO());
    }

    public void initRelacionEstados(RelacionEstadosDTO relacionEstados) {
        this.relacionEstados = relacionEstados;
    }

    public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getfIdEstadoActual() {
		return fIdEstadoActual;
	}

	public void setfIdEstadoActual(Integer fIdEstadoActual) {
		this.fIdEstadoActual = fIdEstadoActual;
	}

	public Integer getfIdEstadoSiguiente() {
		return fIdEstadoSiguiente;
	}

	public void setfIdEstadoSiguiente(Integer fIdEstadoSiguiente) {
		this.fIdEstadoSiguiente = fIdEstadoSiguiente;
	}

	public Integer getfIdTipoFormulario() {
		return fIdTipoFormulario;
	}

	public void setfIdTipoFormulario(Integer fIdTipoFormulario) {
		this.fIdTipoFormulario = fIdTipoFormulario;
	}

	public Integer getfIdOrganismoTranisto() {
		return fIdOrganismoTranisto;
	}

	public void setfIdOrganismoTranisto(Integer fIdOrganismoTranisto) {
		this.fIdOrganismoTranisto = fIdOrganismoTranisto;
	}

	public List<RelacionEstadosDTO> getLstRelacionesEstados() {
        return lstRelacionesEstados;
    }

    public void setLstRelacionesEstados(List<RelacionEstadosDTO> lstRelacionesEstados) {
        this.lstRelacionesEstados = lstRelacionesEstados;
    }

    public RelacionEstadosDTO getRelacionEstadosSeleccionado() {
        return relacionEstadosSeleccionado;
    }

    public void setRelacionEstadosSeleccionado(RelacionEstadosDTO relacionEstadosSeleccionado) {
        this.relacionEstadosSeleccionado = relacionEstadosSeleccionado;
    }

    public RelacionEstadosDTO getRelacionEstados() {
        return relacionEstados;
    }

    public void setRelacionEstados(RelacionEstadosDTO relacionEstados) {
        this.relacionEstados = relacionEstados;
    }
}