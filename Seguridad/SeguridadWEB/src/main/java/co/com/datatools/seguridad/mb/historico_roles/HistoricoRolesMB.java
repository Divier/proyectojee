package co.com.datatools.seguridad.mb.historico_roles;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.bundle.comun.EnumParametrosWeb;
import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.seguridad.mb.roles.RecursoDetallePermisos;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean de sesion que administra la pagina de consulta de historico de cambios de un rol
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class HistoricoRolesMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HistoricoRolesFL.class);

    private static final String NOMBRE_BUNDLE_ROLES = "mensajesRol";

    @EJB
    private IRRol rolEjb;

    @EJB
    private IRParametrosSeguridad parametrosEjb;

    public HistoricoRolesMB() {
        logger.debug("HistoricoRolesMB::HistoricoRolesMB");
    }

    @PostConstruct
    public void init() {
        logger.debug("HistoricoRolesMB::init");
        consultarHistorico();
    }

    /**
     * Realiza la consulta del historico de cambios del rol utilizando las fechas y el id de rol contenidos en el Dto de flujo:HistoricoRolesFL,
     * asigna a este dto los resultados encontrados
     */
    public void consultarHistorico() {
        logger.debug("HistoricoRolesMB::consultarHistorico");
        HistoricoRolesFL historicoRolesFL = findFlowObject(HistoricoRolesFL.class, HistoricoRolesFL.NOMBRE_BEAN);
        historicoRolesFL.setMuestraHistoricoVigente(false);

        if (historicoRolesFL.getFechaActual() == null) {

            Calendar fechaMax = UtilFecha.buildCalendar(historicoRolesFL.getFechaFinal());
            fechaMax.set(Calendar.HOUR_OF_DAY, 23);
            fechaMax.set(Calendar.MINUTE, 59);
            fechaMax.set(Calendar.SECOND, 59);

            historicoRolesFL.setFechaActual(fechaMax.getTime());
        }

        if (historicoRolesFL.isValidacionFechas()) {
            if (historicoRolesFL.getFechaInicial().compareTo(historicoRolesFL.getFechaFinal()) >= 0) {
                addErrorMessage(NOMBRE_BUNDLE_ROLES, "msg_fechas_invalidas");
                return;
            }
            int diasMax = Integer.valueOf(parametrosEjb
                    .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_DIAS_CONSULTA_ROLES));
            long milisegundosDia = (24 * 60 * 60 * 1000);
            int diasDiferencia = (int) ((historicoRolesFL.getFechaFinal().getTime() - historicoRolesFL
                    .getFechaInicial().getTime()) / milisegundosDia);
            if (diasDiferencia > diasMax) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageFormat.format(
                                (getBundle(NOMBRE_BUNDLE_ROLES)).getString("msg_cantidad_dias_invalidos"), diasMax)));
                return;
            }
        }
        List<HistoricoRolDto> resultados = rolEjb.consultarDetalleHistoricoRol(historicoRolesFL.getIdRol(),
                historicoRolesFL.getFechaInicial(), historicoRolesFL.getFechaFinal());

        if (StringUtils.isBlank(historicoRolesFL.getNombreRol())) {
            historicoRolesFL.setNombreRol(rolEjb.consultarRol(historicoRolesFL.getIdRol()).getNombre());
        }

        if (resultados.isEmpty()) {
            // Si esta deshabilitado el ingreso de fechas es porq las fechas son fijas y fueron enviadas desde el flujo de historico de usuarios, si
            // no se encontraron resultados es porque el rol no ha cambiado con respecto a las fechas del cambio del usuarios, por tanto debe mostrar
            // la configuración vigente del rol
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat(getBundle(EnumParametrosWeb.getBundleName())
                    .getString(EnumParametrosWeb.lab_calendar_pattern_full.toString()));
            historicoRolesFL.setMsgNoResultados(MessageFormat.format(
                    (getBundle(NOMBRE_BUNDLE_ROLES)).getString("msgNoResultadosHistorico"),
                    formatoFechaHora.format(historicoRolesFL.getFechaInicial()),
                    formatoFechaHora.format(historicoRolesFL.getFechaFinal())));
            HistoricoRolDto historicoVigente = rolEjb.consultarUltimoHistoricoRol(historicoRolesFL.getIdRol(),
                    historicoRolesFL.getFechaInicial());
            if (historicoVigente != null) {
                resultados.add(historicoVigente);
                historicoRolesFL.setMuestraHistoricoVigente(true);
            }

        }
        historicoRolesFL.setResultadoConsulta(resultados);
        historicoRolesFL.setConsultaRealizada(true);
    }

    /**
     * Obtiene los listados de los permisos directamente asignados y de los heredados para el historico de rol seleccionado y poder mostrar esta
     * informacion en el detalle de dicho historico
     */
    public void cargarDetallePermisos() {
        logger.debug("HistoricoRolesMB::cargarDetallePermisos");
        HistoricoRolesFL historicoRolesFL = findFlowObject(HistoricoRolesFL.class, HistoricoRolesFL.NOMBRE_BEAN);

        List<RecursoDetallePermisos> permisosAsignados = new ArrayList<RecursoDetallePermisos>();
        historicoRolesFL.setPermisosAsignados(obtenerPermisos(permisosAsignados, historicoRolesFL
                .getHistoricoSeleccionado().getRolDetalleDto(), false));

        List<RecursoDetallePermisos> permisosHeredados = new ArrayList<RecursoDetallePermisos>();
        obtenerPermisosHeredados(permisosHeredados, (RolDetalleDto) historicoRolesFL.getHistoricoSeleccionado()
                .getRolDetalleDto().getRolPadre());

        historicoRolesFL.setPermisosHeredados(permisosHeredados);
    }

    /**
     * Carga en el listado recibido en el primer parametro los diferentes permisos que son heredables a un rol a partir del rol padre que se envia
     * como segundo parametro, si dicho rolPadre enviado a su vez tiene padres se ejecuta el metodo de manera recursiva para obtener todos los
     * recursos que son heredables
     * 
     * @param permisosHeredados
     *            Listado pase sobre el cual se van agregando los permisos
     * @param rolPadre
     *            Dto padre que tiene los permisos
     */
    private void obtenerPermisosHeredados(List<RecursoDetallePermisos> permisosHeredados, RolDetalleDto rolPadre) {
        if (rolPadre != null) {
            if (rolPadre.getRecursosAplicacion() != null && !rolPadre.getRecursosAplicacion().isEmpty()) {
                obtenerPermisos(permisosHeredados, rolPadre, true);
                obtenerPermisosHeredados(permisosHeredados, (RolDetalleDto) rolPadre.getRolPadre());
            }
        }

    }

    /**
     * Obtiene un listado con los permisos de un rol, utilizando el mapa de RecursosAplicacion contenido en su Dto, esto para cargar la informacion en
     * objetos de tipo RecursoDetallePermisos y poderlos visualizar y filtrar en una tabla con columnas por aplicacion, nombre de recurso, operaciones
     * y nombre del rol padre en caso de que el permiso hubiese sido heredado
     * 
     * @param permisosAsignados
     *            Lista base sobre la cual se van agregando los RecursoDetallePermisos obtenidos
     * @param rolDetalleDto
     *            Dto del cual se esta obteniendo el listado de permisos
     * @param asignarRolHerencia
     *            Indica si a cada RecursoDetallePermisos le debe asignar el Rol dueño de los permisos
     * @return listado con los permisos del rol
     */
    private List<RecursoDetallePermisos> obtenerPermisos(List<RecursoDetallePermisos> permisosAsignados,
            RolDetalleDto rolDetalleDto, boolean asignarRolHerencia) {

        Map<String, List<RecursoDetalleDto>> permisosPorApp = rolDetalleDto.getRecursosAplicacion();
        for (Map.Entry<String, List<RecursoDetalleDto>> entry : permisosPorApp.entrySet()) {
            String app = entry.getKey();
            List<RecursoDetalleDto> recursos = entry.getValue();
            for (RecursoDetalleDto recursoDetalleDto : recursos) {
                RecursoDetallePermisos detallePermiso = new RecursoDetallePermisos();
                detallePermiso.setRecursoDetalleDto(recursoDetalleDto);
                detallePermiso.setOperaciones(obtenerNombresOperaciones(recursoDetalleDto.getOperaciones()));
                detallePermiso.setAplicacion(app);
                if (asignarRolHerencia)
                    detallePermiso.setRolHerencia(rolDetalleDto);

                permisosAsignados.add(detallePermiso);
            }
        }
        return permisosAsignados;
    }

    /**
     * Obtiene un string con los nombres de las operaciones enviadas en la lista del parametro y separa dichos nombres por una coma(,)
     * 
     * @param operacionesDto
     *            Listado con los Dto de operaciones de las cuales se obtendra el nombre
     * @return Nombres de las operaciones separadas por coma
     */
    private String obtenerNombresOperaciones(List<OperacionDto> operacionesDto) {
        StringBuilder operaciones = new StringBuilder();
        for (OperacionDto operacionDto : operacionesDto) {
            if (operaciones.length() > 0) {
                operaciones.append(",");
            }
            operaciones.append(operacionDto.getNombreOperacion());
        }
        return operaciones.toString();
    }

}
