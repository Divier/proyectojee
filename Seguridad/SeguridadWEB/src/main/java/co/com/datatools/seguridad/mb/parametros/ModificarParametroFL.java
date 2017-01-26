package co.com.datatools.seguridad.mb.parametros;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.bundle.comun.EnumParametrosWeb;
import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumTipoDato;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase que actua como Dto que provee los datos para la pagina de modificacion de parametros
 * 
 * @author claudia.rodriguez
 * 
 */
public class ModificarParametroFL extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ModificarParametroFL.class.getName());

    public static final String NOMBRE_BEAN = "modificarParametroFL";

    private Date fecha;
    private String texto;
    private String numero;
    private boolean valorSiNo;

    private List<SelectItem> lSiNo;

    private ParametroSeguridadDto parametroSeleccionado;

    private boolean valorTexto;
    private boolean valorNumeroDecimal;
    private boolean valorNumeroEntero;
    private boolean valorBooleano;
    private boolean valorHora;
    private boolean valorFechaHora;
    private boolean valorFecha;
    private SimpleDateFormat formatoFecha;
    private SimpleDateFormat formatoFechaHora;
    private SimpleDateFormat formatoHora;

    public ModificarParametroFL() {
        logger.debug("ModificarParametroFL::ModificarParametroFL");

    }

    public void cargarListaSiNo() {
        logger.debug("ModificarParametroFL::cargarListaSiNo");
        lSiNo = new ArrayList<>();
        lSiNo.add(new SelectItem(true, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_si")));
        lSiNo.add(new SelectItem(false, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("label_no")));
    }

    public void validarTipoDato() {
        logger.debug("ModificarParametroFL::validarTipoDato");
        valorTexto = false;
        valorNumeroEntero = false;
        valorNumeroDecimal = false;
        valorBooleano = false;
        valorHora = false;
        valorFechaHora = false;
        valorFecha = false;

        valorSiNo = false;

        ResourceBundle bundle = getBundle(EnumParametrosWeb.getBundleName());

        formatoFecha = new SimpleDateFormat(bundle.getString(EnumParametrosWeb.lab_calendar_pattern.toString()));
        formatoFechaHora = new SimpleDateFormat(
                bundle.getString(EnumParametrosWeb.lab_calendar_pattern_full.toString()));
        formatoHora = new SimpleDateFormat(bundle.getString(EnumParametrosWeb.lab_calendar_hour_pattern.toString()));

        int idTipoParSeleccionado = parametroSeleccionado.getIdTipoParametro().intValue();
        try {
            if (idTipoParSeleccionado == EnumTipoDato.DECIMAL.getId()) {
                valorNumeroDecimal = true;
                numero = parametroSeleccionado.getValor();
            } else if (idTipoParSeleccionado == EnumTipoDato.ENTERO.getId()) {
                valorNumeroEntero = true;
                numero = parametroSeleccionado.getValor();
            } else if (idTipoParSeleccionado == EnumTipoDato.SI_NO.getId()
                    || idTipoParSeleccionado == EnumTipoDato.SI_NO.getId()) {
                valorBooleano = true;
                cargarListaSiNo();
                if (parametroSeleccionado.getValor().equalsIgnoreCase(ConstantesSeguridad.VALOR_SI))
                    valorSiNo = true;
            } else if (idTipoParSeleccionado == EnumTipoDato.HORA.getId()
                    || idTipoParSeleccionado == EnumTipoDato.HORA.getId()) {
                valorHora = true;
                fecha = formatoHora.parse(parametroSeleccionado.getValor());
            } else if (idTipoParSeleccionado == EnumTipoDato.FECHA_HORA.getId()
                    || idTipoParSeleccionado == EnumTipoDato.FECHA_HORA.getId()) {
                valorFechaHora = true;
                fecha = formatoFechaHora.parse(parametroSeleccionado.getValor());
            } else if (idTipoParSeleccionado == EnumTipoDato.FECHA.getId()
                    || idTipoParSeleccionado == EnumTipoDato.FECHA.getId()) {
                valorFecha = true;
                fecha = formatoFecha.parse(parametroSeleccionado.getValor());
            } else {
                valorTexto = true;
                texto = parametroSeleccionado.getValor();
            }
        } catch (ParseException e) {
            logger.error("Error convirtiendo el valor del parametro: " + parametroSeleccionado.getValor() + " - "
                    + e.getMessage());
        }
    }

    public void transformarValorParametro() {
        if (valorFecha) {
            parametroSeleccionado.setValor(formatoFecha.format(fecha));
        } else if (valorFechaHora) {
            parametroSeleccionado.setValor(formatoFechaHora.format(fecha));
        } else if (valorHora) {
            parametroSeleccionado.setValor(formatoHora.format(fecha));
        } else if (valorBooleano) {
            if (valorSiNo) {
                parametroSeleccionado.setValor(ConstantesSeguridad.VALOR_SI);
            } else {
                parametroSeleccionado.setValor(ConstantesSeguridad.VALOR_NO);
            }
        } else if (valorNumeroDecimal || valorNumeroEntero) {
            parametroSeleccionado.setValor(numero);
        } else {
            parametroSeleccionado.setValor(texto);
        }
    }

    public ParametroSeguridadDto getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(ParametroSeguridadDto parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isValorSiNo() {
        return valorSiNo;
    }

    public void setValorSiNo(boolean valorSiNo) {
        this.valorSiNo = valorSiNo;
    }

    public List<SelectItem> getlSiNo() {
        return lSiNo;
    }

    public void setlSiNo(List<SelectItem> lSiNo) {
        this.lSiNo = lSiNo;
    }

    public boolean isValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(boolean valorTexto) {
        this.valorTexto = valorTexto;
    }

    public boolean isValorBooleano() {
        return valorBooleano;
    }

    public void setValorBooleano(boolean valorBooleano) {
        this.valorBooleano = valorBooleano;
    }

    public boolean isValorHora() {
        return valorHora;
    }

    public void setValorHora(boolean valorHora) {
        this.valorHora = valorHora;
    }

    public boolean isValorFechaHora() {
        return valorFechaHora;
    }

    public void setValorFechaHora(boolean valorFechaHora) {
        this.valorFechaHora = valorFechaHora;
    }

    public boolean isValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(boolean valorFecha) {
        this.valorFecha = valorFecha;
    }

    public boolean isValorNumeroDecimal() {
        return valorNumeroDecimal;
    }

    public void setValorNumeroDecimal(boolean valorNumeroDecimal) {
        this.valorNumeroDecimal = valorNumeroDecimal;
    }

    public boolean isValorNumeroEntero() {
        return valorNumeroEntero;
    }

    public void setValorNumeroEntero(boolean valorNumeroEntero) {
        this.valorNumeroEntero = valorNumeroEntero;
    }

}
