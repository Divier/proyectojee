package co.com.datatools.c2.managed_bean.administracionnegocio.configuracion.interes;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.dto.ClaseInteresDTO;
import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.PeriodoInteresDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo interes-flow CU_CIR20_DAT_ADM_016
 * 
 * @author Dixon.Alvarez
 * 
 */
public class InteresFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "interesFL";

    private boolean actualizar;
    private InteresDTO interesRegistrar;
    private Date minFechaFinal;
    private Date fechaActual;
    private boolean visiblePopupRegistro;
    private String porcentajeGuardado;

    public InteresFL() {
        super();
        interesRegistrar = new InteresDTO();
        interesRegistrar.setClaseInteres(new ClaseInteresDTO());
        interesRegistrar.setPeriodoInteres(new PeriodoInteresDTO());
        interesRegistrar.setPorcentajeInteresDiario(BigDecimal.ZERO);
        interesRegistrar.setPorcentajeTasaInteres(BigDecimal.ZERO);
        fechaActual = new Date();
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public InteresDTO getInteresRegistrar() {
        return interesRegistrar;
    }

    public void setInteresRegistrar(InteresDTO interesRegistrar) {
        this.interesRegistrar = interesRegistrar;
    }

    public Date getMinFechaFinal() {
        return minFechaFinal;
    }

    public void setMinFechaFinal(Date minFechaFinal) {
        this.minFechaFinal = minFechaFinal;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public boolean isVisiblePopupRegistro() {
        return visiblePopupRegistro;
    }

    public void setVisiblePopupRegistro(boolean visiblePopupRegistro) {
        this.visiblePopupRegistro = visiblePopupRegistro;
    }

    public String getPorcentajeGuardado() {
        return porcentajeGuardado;
    }

    public void setPorcentajeGuardado(String porcentajeGuardado) {
        this.porcentajeGuardado = porcentajeGuardado;
    }
}
