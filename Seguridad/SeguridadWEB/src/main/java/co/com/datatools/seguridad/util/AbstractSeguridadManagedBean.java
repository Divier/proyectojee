package co.com.datatools.seguridad.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Definicion abstracta de un managed bean para Seguridad
 * 
 * @author giovanni.velandia
 */
public abstract class AbstractSeguridadManagedBean extends AbstractSwfManagedBean {

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
