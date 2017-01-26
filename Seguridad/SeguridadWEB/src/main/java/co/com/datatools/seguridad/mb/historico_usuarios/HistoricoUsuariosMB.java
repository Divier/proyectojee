package co.com.datatools.seguridad.mb.historico_usuarios;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.bundle.comun.EnumParametrosWeb;
import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * MangedBean de sesion que administra la pagina de consulta de historico de cambios de un usuario
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class HistoricoUsuariosMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoUsuariosMB.class);

    private static final String NOMBRE_BUNDLE_USUARIOS = "mensajesUsuario";

    @EJB
    private IRUsuario usuarioEjb;

    @EJB
    private IRParametrosSeguridad parametrosEjb;

    public HistoricoUsuariosMB() {
        logger.debug("HistoricoUsuariosMB::HistoricoUsuariosMB");
    }

    @PostConstruct
    public void init() {
        logger.debug("HistoricoUsuariosMB::init");
        consultarHistorico();
    }

    /**
     * Realiza la consulta del historico de cambios del usuario utilizando las fechas y el id de usuario contenidos en el Dto: HistoricoUsuariosFL,
     * asigna a este dto los resultados encontrados para su visualizacion en la interfaz
     */
    public void consultarHistorico() {
        logger.debug("HistoricoUsuariosMB::consultarHistorico");
        HistoricoUsuariosFL historicoUsuariosFL = findFlowObject(HistoricoUsuariosFL.class,
                HistoricoUsuariosFL.NOMBRE_BEAN);
        if (historicoUsuariosFL.getFechaCreacionUsuario() == null) {
            historicoUsuariosFL.setFechaCreacionUsuario(usuarioEjb.consultarFechaCreacionUsuario(historicoUsuariosFL
                    .getIdUsuario()));
        }
        if (historicoUsuariosFL.getFechaActualMaxima() == null) {

            Calendar fechaMax = UtilFecha.buildCalendar(historicoUsuariosFL.getFechaFinal());
            fechaMax.set(Calendar.HOUR_OF_DAY, 23);
            fechaMax.set(Calendar.MINUTE, 59);
            fechaMax.set(Calendar.SECOND, 59);
            historicoUsuariosFL.setFechaActualMaxima(fechaMax.getTime());
        }
        if (historicoUsuariosFL.getFechaInicial().compareTo(historicoUsuariosFL.getFechaFinal()) >= 0) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIOS, "msg_fechas_invalidas");
            historicoUsuariosFL.setResultadoConsulta(new ArrayList<HistoricoUsuarioDto>());
            historicoUsuariosFL.setConsultaRealizada(false);
            return;
        }
        String diasMax = parametrosEjb.consultarValorParametroSeguridad(EnumParametro.CANTIDAD_DIAS_CONSULTA_USUARIOS);
        long milisegundosDia = (24 * 60 * 60 * 1000);
        int diasDiferenciaDesdeHasta = (int) ((historicoUsuariosFL.getFechaFinal().getTime() - historicoUsuariosFL
                .getFechaInicial().getTime()) / milisegundosDia);
        if (diasDiferenciaDesdeHasta > Integer.valueOf(diasMax)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(
                            (getBundle(NOMBRE_BUNDLE_USUARIOS)).getString("msg_cantidad_dias_invalidos"), diasMax)));
            historicoUsuariosFL.setResultadoConsulta(new ArrayList<HistoricoUsuarioDto>());
            historicoUsuariosFL.setConsultaRealizada(false);
            return;
        }

        List<HistoricoUsuarioDto> resultados = usuarioEjb.consultarHistoricoUsuario(historicoUsuariosFL.getIdUsuario(),
                historicoUsuariosFL.getFechaInicial(), historicoUsuariosFL.getFechaFinal());

        for (HistoricoUsuarioDto historicoUsuarioDto : resultados) {
            if (StringUtils.isNotBlank(historicoUsuarioDto.getDescripcionCambio())) {
                String[] descripcionesCambios = historicoUsuarioDto.getDescripcionCambio().split(",");
                StringBuilder descripcion = new StringBuilder();
                for (String cambio : descripcionesCambios) {
                    if (descripcion.length() > 0) {
                        descripcion.append(",");
                    }
                    descripcion.append(getBundle(NOMBRE_BUNDLE_USUARIOS).getString(cambio));
                }
                historicoUsuarioDto.setDescripcionCambio(descripcion.toString());
            }

        }

        SimpleDateFormat formatoFechaHora = new SimpleDateFormat(getBundle(EnumParametrosWeb.getBundleName())
                .getString(EnumParametrosWeb.lab_calendar_pattern_full.toString()));
        historicoUsuariosFL.setMsgNoResultados(MessageFormat.format(
                (getBundle(NOMBRE_BUNDLE_USUARIOS)).getString("msgNoResultadosHistorico"),
                formatoFechaHora.format(historicoUsuariosFL.getFechaInicial()),
                formatoFechaHora.format(historicoUsuariosFL.getFechaFinal())));
        historicoUsuariosFL.setResultadoConsulta(resultados);
        historicoUsuariosFL.setConsultaRealizada(true);

    }
}
