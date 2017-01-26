package co.com.datatools.c2.managed_bean.comun;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.time.DateUtils;

import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.util.date.UtilFecha;

/**
 * MB a utilizar para implementar el cambio dinamico de los formatos de fecha y moneda en el aplicativo
 * 
 * @author Jeison.Rodriguez
 *
 */
@ManagedBean
@SessionScoped
public class UtilidadMB extends AbstractC2ManagedBean {
    private static final long serialVersionUID = 1L;
    private String formatoFecha;
    private String formatoFechaCompleta;
    private String formatoMonedaCompleta;
    private String formatoMoneda;

    @EJB
    private IRParametro iRParametro;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;
    
    private int HORA = 23;
    private int MINUTOS = 59;
    private int SEGUNDOS = 59;

    /**
     * retorna el formato de fecha corta
     * 
     * @return
     */
    public String modificarFormatoFechaCorta() {
        String[] valorParametroDTO = iRParametro.consultarValorParametro(EnumParametro.FORMATO_FECHA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo()).split(" ");
        return valorParametroDTO[0];
    }

    /**
     * retorna el formato de fecha larga
     * 
     * @return
     */
    public String modificarFormatoFechaCompleta() {
        String valorParametroDTO = iRParametro.consultarValorParametro(EnumParametro.FORMATO_FECHA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
        return valorParametroDTO;
    }

    /**
     * retorna el formato de mondeda completo
     * 
     * @return
     */
    public String modificarFormatoMonedaCompleta() {
        String valorParametroDTO = iRParametro.consultarValorParametro(EnumParametro.FORMATO_MONEDA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
        return valorParametroDTO;
    }

    /**
     * retorna el formato de moneda sin el simbolo solo al inicial
     * 
     * @return
     */
    public String modificaFormatoModenaCorta() {
        String valorParametroDTO = iRParametro.consultarValorParametro(EnumParametro.FORMATO_MONEDA,
                iRSeguridadCirculemos.obtenerOrganismoTransitoUsuario().getCodigoOrganismo());
        return valorParametroDTO.substring(1);
    }

    public String getFormatoFecha() {

        if (formatoFecha == null) {
            formatoFecha = modificarFormatoFechaCorta();
        }
        return formatoFecha;
    }

    public String getFormatoFechaCompleta() {
        if (formatoFechaCompleta == null) {
            formatoFechaCompleta = modificarFormatoFechaCompleta();
        }
        return formatoFechaCompleta;
    }

    public String getFormatoMonedaCompleta() {
        if (formatoMonedaCompleta == null) {
            formatoMonedaCompleta = modificarFormatoMonedaCompleta();
        }
        return formatoMonedaCompleta;
    }

    public String getFormatoMoneda() {
        if (formatoMoneda == null) {
            formatoMoneda = modificaFormatoModenaCorta();
        }
        return formatoMoneda;
    }
    
    public Date getFechaActual() {
        Date fechaActual = UtilFecha.sumarDias(UtilFecha.currentZeroTimeDate(), -1);
        fechaActual = DateUtils.addHours(fechaActual, HORA);
        fechaActual = DateUtils.addMinutes(fechaActual, MINUTOS);
        fechaActual = DateUtils.addSeconds(fechaActual, SEGUNDOS);
        return fechaActual;
    }

}
