package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.CaracteristicaPruebaDTO;
import co.com.datatools.c2.dto.OrigenPruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.dto.TipoDestinoPruebaImpugDTO;
import co.com.datatools.c2.dto.TipoPruebaDTO;

/**
 * Objejo para manejo de la informacion de gestionar preubas en el flujo
 * 
 * @author giovanni.velandia
 * 
 */
public class GestionarPruebasFL implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "gestionarPruebasFL";

    private boolean adicionarPrueba;
    private boolean confirmAdicPrueba;
    private boolean generarProrroga;

    private SolicitudPruebasBackVO soliciPruebBackSeleccVO;
    private List<SolicitudPruebasBackVO> solicitudPruebasBackVOs;

    private SolicitudPruebasBackDTO solicitudPruebasBackDTO;

    private Integer diasProrroga;

    // Fecha del sistema
    private Date fechaSistema;

    public GestionarPruebasFL() {
        solicitudPruebasBackDTO = new SolicitudPruebasBackDTO();
        solicitudPruebasBackDTO.setCaracteristicaPrueba(new CaracteristicaPruebaDTO());
        solicitudPruebasBackDTO.setOrigenPrueba(new OrigenPruebaDTO());
        solicitudPruebasBackDTO.setTipoPrueba(new TipoPruebaDTO());
        solicitudPruebasBackDTO.setTipoDestinoPruebaImpug(new TipoDestinoPruebaImpugDTO());
        // Fecha del sistema
        fechaSistema = Calendar.getInstance().getTime();
    }

    public boolean isAdicionarPrueba() {
        return adicionarPrueba;
    }

    public void setAdicionarPrueba(boolean adicionarPrueba) {
        this.adicionarPrueba = adicionarPrueba;
    }

    public boolean isConfirmAdicPrueba() {
        return confirmAdicPrueba;
    }

    public void setConfirmAdicPrueba(boolean confirmAdicPrueba) {
        this.confirmAdicPrueba = confirmAdicPrueba;
    }

    public boolean isGenerarProrroga() {
        return generarProrroga;
    }

    public void setGenerarProrroga(boolean generarProrroga) {
        this.generarProrroga = generarProrroga;
    }

    public SolicitudPruebasBackVO getSoliciPruebBackSeleccVO() {
        return soliciPruebBackSeleccVO;
    }

    public void setSoliciPruebBackSeleccVO(SolicitudPruebasBackVO soliciPruebBackSeleccVO) {
        this.soliciPruebBackSeleccVO = soliciPruebBackSeleccVO;
    }

    public List<SolicitudPruebasBackVO> getSolicitudPruebasBackVOs() {
        return solicitudPruebasBackVOs;
    }

    public void setSolicitudPruebasBackVOs(List<SolicitudPruebasBackVO> solicitudPruebasBackVOs) {
        this.solicitudPruebasBackVOs = solicitudPruebasBackVOs;
    }

    public SolicitudPruebasBackDTO getSolicitudPruebasBackDTO() {
        return solicitudPruebasBackDTO;
    }

    public void setSolicitudPruebasBackDTO(SolicitudPruebasBackDTO solicitudPruebasBackDTO) {
        this.solicitudPruebasBackDTO = solicitudPruebasBackDTO;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Integer getDiasProrroga() {
        return diasProrroga;
    }

    public void setDiasProrroga(Integer diasProrroga) {
        this.diasProrroga = diasProrroga;
    }

}
