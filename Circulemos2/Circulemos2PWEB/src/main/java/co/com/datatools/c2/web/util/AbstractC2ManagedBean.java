package co.com.datatools.c2.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.com.datatools.c2.dto.common.EncabezadoReporteC2DTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Definicion abstracta de un managed bean para C2
 * 
 * @author felipe.martinez
 */
public abstract class AbstractC2ManagedBean extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 9148377567088023852L;

    /**
     * Permite obtener el usuario que ha ingresado en el sistema
     * 
     * @return Usuario que corresponde al login utilizado en el login de la aplicacion
     */
    public String getUsuarioSesion() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String usuario = null;
        if (context != null && context.getUserPrincipal() != null) {
            usuario = context.getUserPrincipal().getName();
            usuario = usuario.split("#")[1];
        }
        return usuario;
    }

    /**
     * Obtiene el codigo del organismo del transito del usuario autenticado
     * 
     * @return codigo de organismo
     */
    protected int getCodigoOrganismoTransito() {
        return super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO).getCodigoOrganismo();
    }

    /**
     * Obtiene el detalle del usuario autenticado
     * 
     * @author giovanni.velandia
     * @return
     */
    protected UsuarioDetalleDto getDetalleUsuarioSesion() {
        return super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_DETALLE_USU,
                ConstantesManagedBean.NOMBRE_OBJ_DETALLE_USU);
    }

    /**
     * Obtiene el organismo del transito del usuario autenticado
     * 
     * @return organismo de transito
     */
    protected OrganismoTransitoDTO getOrganismoTransito() {
        return super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
    }

    /**
     * Obtiene el pais se la sesion
     * 
     * @return pais
     */
    protected PaisDTO getPais() {
        return super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_PAIS, ConstantesManagedBean.NOMBRE_OBJ_PAIS);
    }

    /**
     * Obtiene el managed bean que centraliza el acceso a los catalogos de la aplicacion
     * 
     * @return objeto fachada de acceso
     */
    public FachadaCatalogosMB getCatalogosApp() {
        return findSessionObject(ConstantesManagedBean.CLASE_OBJ_FACHADA_CATALOGOS,
                ConstantesManagedBean.NOMBRE_FACHADA_CATALOGOS);
    }

    /**
     * Obtiene la información correspondiente al encabezado de reportes
     * 
     * @return EncabezadoReporteC2DTO
     */
    public EncabezadoReporteC2DTO obtenerEncabezadoReporte(ContenidoReporte contenido) {
        EncabezadoReporteC2DTO encabezadoReporteC2DTO = new EncabezadoReporteC2DTO();
        UsuarioPersonaDTO usuario = super.findSessionObject(
                ConstantesManagedBean.CLASE_OBJ_USUARIO_PERSONA_AUTENTICADA,
                ConstantesManagedBean.OBJ_USUARIO_PERSONA_AUTENTICA);
        encabezadoReporteC2DTO.setNombreOrganismo(super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO).getNombreOrganismo());
        encabezadoReporteC2DTO.setLoginUsuario(usuario.getLogin());
        encabezadoReporteC2DTO.setNombreUsuario(usuario.getPersona().getNombreCompleto());
        contenido.getParametrosEncabezado().put(0, new Object[] { encabezadoReporteC2DTO.getNombreOrganismo() });
        contenido.getParametrosEncabezado().put(2,
                new Object[] { encabezadoReporteC2DTO.getLoginUsuario(), encabezadoReporteC2DTO.getNombreUsuario() });

        return encabezadoReporteC2DTO;
    }

    /**
     * Retorna la fecha minima configurada en el sistema. Manejada especialmente en la seleccion de fechas en los calendarios.
     * 
     * @return Fecha minima manejada en el sistema o null si existen problemas al generarla.
     */
    public Date obtenerFechaMinima() {
        String patron, valorFecha;
        DateFormat dFormat;
        Date fecha = null;

        patron = this.getBundle("webPrm").getString("lab_calendar_pattern");
        if (patron != null) {
            valorFecha = this.getBundle("webPrm").getString("lab_fecha_minima");
            if (valorFecha != null) {
                dFormat = new SimpleDateFormat(patron);
                try {
                    fecha = dFormat.parse(valorFecha);
                } catch (ParseException e) {
                }
            }

        }

        return fecha;
    }

}
