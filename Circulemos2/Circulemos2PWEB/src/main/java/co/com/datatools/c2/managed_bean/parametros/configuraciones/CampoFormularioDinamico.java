package co.com.datatools.c2.managed_bean.parametros.configuraciones;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.NULL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Objeto para representar un campo de un formulario dinamico (una fila del formulario) ADM_066
 * 
 * @author Felipe.Martinez
 */
public class CampoFormularioDinamico implements Serializable {

    private static final long serialVersionUID = -6403093089337637162L;

    private static final Long DEFAULT_MAX_NUMERO = Long.MAX_VALUE;

    private static final Long DEFAULT_LONG_CADENA = 1000l;

    private static final Long DEFAULT_LONG_CORREO = 50l;

    /**
     * numero de la fila del formulario, empieza en 0
     */
    private final int fila;

    /**
     * Referencia al campo de la configuracion sobre el que esta basado este campo del formulario dinamico
     */
    private CampoConfiguracionDTO campo;

    /**
     * Nombre con el que se despliega en el formulario
     */
    private String nombre;

    /**
     * Estilo CSS que se le aplica al label usado para obligatorios
     */
    private String estiloLabel = "";

    /**
     * Almacena el valor que se le asigne al campo en el componente de entrada
     */
    private Object valor;

    /**
     * Almacena el valor que se le asigne al campo en el componente de entrada para los campos numericos
     */
    private BigDecimal valorNumerico;

    /**
     * Listado de elementos cuando el campo es un catalogo
     */
    private List<SelectItem> seleccionables;

    private final int precision;

    private long min = 0;

    private long max = 0;

    /**
     * Controla cuantos elementos se pueden seleccionar cuando el campo es catalogo
     */
    private int numeroSeleccion = 1;

    /**
     * Usado en los catalogos compuestos dependientes para activar el componente de ajax
     */
    private boolean ajaxActivo;

    /**
     * Indica que este campo fue derivado y que no existe realmente en el registro
     */
    private boolean derivado;

    public CampoFormularioDinamico(CampoConfiguracionDTO campo, Object valorCampo, boolean derivado, int fila) {
        checkNotNull(campo, "Se requiere la configuracion base del campo para la inicializacion del componente grafico");
        this.campo = campo;
        this.fila = fila;
        nombre = campo.getNombre();
        precision = campo.getAttrDecPrecision();
        if (campo.isObligatorio()) {
            estiloLabel = ConstantesManagedBean.ESTILO_CSS_CAMPO_OBLIGATORIO;
        }

        if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.NUMERO, TipoCampoConfiguracion.DECIMAL)) {
            max = DEFAULT_MAX_NUMERO;
        } else if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CADENA, TipoCampoConfiguracion.URL)) {
            max = DEFAULT_LONG_CADENA;
        } else if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CORREO)) {
            max = DEFAULT_LONG_CORREO;
        }

        if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CATALOGOSIMPLE,
                TipoCampoConfiguracion.CATALOGOINDEPENDIENTE, TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            numeroSeleccion = campo.getAttrCatSeleccion();
        }
        this.setDerivado(derivado);
        initValor(valorCampo);
    }

    @SuppressWarnings("unchecked")
    private void initValor(Object valorCampo) {
        if (!NULL.equals(valorCampo)) {

            if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.NUMERO, TipoCampoConfiguracion.DECIMAL))
                setValorNumerico((BigDecimal) valorCampo);
            else if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CATALOGOSIMPLE,
                    TipoCampoConfiguracion.CATALOGOINDEPENDIENTE)) {
                if (numeroSeleccion == 1) {
                    setValor(((List<String>) valorCampo).get(0));
                } else if (numeroSeleccion > 1) {
                    setValor(((List<String>) valorCampo).toArray());
                }
            } else
                setValor(valorCampo);
        }
    }

    public CampoConfiguracionDTO getCampo() {
        return campo;
    }

    public String getEstiloLabel() {
        return estiloLabel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecision() {
        return precision;
    }

    public List<SelectItem> getSeleccionables() {
        return seleccionables;
    }

    /**
     * Permite obtener el valor del campo cuando no es un numerico
     * 
     * @see #getValorNumerico()
     * @return valor asignado por el componente grafico a este campo, null si no ha sido inicializado o es numerico
     */
    public Object getValor() {
        return valor;
    }

    /**
     * Permite obtener de manera generica el valor que se asigno al campo, sin importar el tipo
     * 
     * @return si es numerico {@link #getValorNumerico()}, si no {@link #getValor()}
     */
    public Object getValorFinal() {
        if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.NUMERO, TipoCampoConfiguracion.DECIMAL))
            return getValorNumerico();
        if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CATALOGOSIMPLE,
                TipoCampoConfiguracion.CATALOGOINDEPENDIENTE, TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            if (getValor() != null) {
                if (getValor() instanceof String) {
                    return Arrays.asList(getValor());
                } else {
                    return Arrays.asList((String[]) getValor());
                }
            }
        }
        return getValor();
    }

    /**
     * Cuando el campo es de tipo numerico es necesario almacenar el valor en un BigDecimal, por eso se usa un campo especializado
     * 
     * @return valor del campo asignado por el componente grafico cuando es numerico, null si no ha sido asignado o es otro tipo de campo
     */
    public BigDecimal getValorNumerico() {
        return valorNumerico;
    }

    public void setCampo(CampoConfiguracionDTO campo) {
        this.campo = campo;
    }

    public void setEstiloLabel(String estiloLabel) {
        this.estiloLabel = estiloLabel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSeleccionables(List<SelectItem> seleccionables) {
        this.seleccionables = seleccionables;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setValorNumerico(BigDecimal valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public int getNumeroSeleccion() {
        return numeroSeleccion;
    }

    public boolean isAjaxActivo() {
        return ajaxActivo;
    }

    public void setAjaxActivo(boolean ajaxActivo) {
        this.ajaxActivo = ajaxActivo;
    }

    public boolean isDerivado() {
        return derivado;
    }

    public void setDerivado(boolean derivado) {
        this.derivado = derivado;
    }

    public int getFila() {
        return fila;
    }

}
