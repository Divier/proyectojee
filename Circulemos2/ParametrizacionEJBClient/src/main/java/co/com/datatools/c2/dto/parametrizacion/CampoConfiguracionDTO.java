package co.com.datatools.c2.dto.parametrizacion;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.util.Utilidades;

import com.google.common.collect.Iterables;

/**
 * Permite representar en objeto un campo de la plantilla de una configuracion
 * 
 * @author Felipe.Martinez
 */
public class CampoConfiguracionDTO implements Serializable {

    private static final long serialVersionUID = 1116746715886377296L;

    /**
     * Patron para encontrar el nombre del metodo del origen de datos
     */
    private static Pattern PARTE_NOMBRE_METODO = Pattern.compile("\\{\\{(.*)\\}\\}");
    /**
     * Patron para encontrar el nombre de la interfaz del origen de datos
     */
    private static Pattern PARTE_NOMBRE_INTERFAZ = Pattern.compile("(.*)\\{\\{");

    private static String VERSION_REGEX = "\\{version\\}";

    // Referencia estatica para simplificar el import de los tipos de campos
    public static final TipoCampoConfiguracion _BOOLEANO = TipoCampoConfiguracion.BOOLEANO;
    public static final TipoCampoConfiguracion _CADENA = TipoCampoConfiguracion.CADENA;
    public static final TipoCampoConfiguracion _CATALOGOCOMPUESTO = TipoCampoConfiguracion.CATALOGOCOMPUESTO;
    public static final TipoCampoConfiguracion _CATALOGOINDEPENDIENTE = TipoCampoConfiguracion.CATALOGOINDEPENDIENTE;
    public static final TipoCampoConfiguracion _CATALOGOSIMPLE = TipoCampoConfiguracion.CATALOGOSIMPLE;
    public static final TipoCampoConfiguracion _CORREO = TipoCampoConfiguracion.CORREO;
    public static final TipoCampoConfiguracion _DECIMAL = TipoCampoConfiguracion.DECIMAL;
    public static final TipoCampoConfiguracion _FECHA = TipoCampoConfiguracion.FECHA;
    public static final TipoCampoConfiguracion _FECHAHORA = TipoCampoConfiguracion.FECHAHORA;
    public static final TipoCampoConfiguracion _HORA = TipoCampoConfiguracion.HORA;
    public static final TipoCampoConfiguracion _NUMERO = TipoCampoConfiguracion.NUMERO;
    public static final TipoCampoConfiguracion _URL = TipoCampoConfiguracion.URL;

    /**
     * Enumeracion definida para los posibles tipos de campos que se pueden presentar en un configuracion (regla de negocio)
     * 
     * @author Felipe.Martinez
     */
    public enum TipoCampoConfiguracion {
        BOOLEANO, //
        CADENA, //
        CATALOGOCOMPUESTO, //
        CATALOGOINDEPENDIENTE, //
        CATALOGOSIMPLE, //
        CORREO, //
        DECIMAL, //
        FECHA, //
        FECHAHORA, //
        HORA, //
        NUMERO, //
        RANGOFECHA, //
        URL, //
        ;

        public boolean seEncuentraEn(TipoCampoConfiguracion... tipos) {
            List<TipoCampoConfiguracion> asList = Arrays.asList(tipos);
            if (asList.contains(this))
                return true;
            return false;
        }
    }

    /**
     * Enumeracion definida para los posibles tipos de validaciones que se pueden realizar sobre un campo. Contienen un String que representa un
     * codigo del operador y un int que indica la cantidad de veces q se puede presentar esta validacion en un campo
     * 
     * @author Felipe.Martinez
     */
    public enum Operador {
        MENOR(-1), //
        MENOR_IGUAL(-1), //
        MAYOR(-1), //
        MAYOR_IGUAL(-1), //
        IGUAL(1), //
        DIFERENTE(-1), //
        LONGITUD(1), //
        ;
        /**
         * Cantidad maxima de operadores de este tipo q se pueden registrar, -1 indica ilimitados
         */
        int cantidad;

        Operador(int cantidad) {
            this.cantidad = cantidad;
        }
    }

    public enum OperandoEspecial {
        FECHA_ACTUAL;
    }

    private TipoCampoConfiguracion tipo;
    // --- Atributos generales
    private String codigo;
    private String nombre;
    private String descripcion;
    private final List<Entry<Operador, Object>> validaciones = new ArrayList<>(2);

    // --- Flags generales
    private boolean entrada = false;
    private boolean obligatorio = false;

    // --- Flags especificos
    private int attrDecPrecision = 0;

    private int attrCatSeleccion;
    private String attrCatOrigenDatos;

    private boolean attrRngTraslapar = false;
    private String attrRngCodigoRango;

    private final Deque<CampoConfiguracionDTO> dependenciaCatalogo = new ArrayDeque<>(0);

    public CampoConfiguracionDTO(TipoCampoConfiguracion tipoCampo, boolean entrada) {
        checkNotNull(tipoCampo, "Tipo de campo es requerido");
        this.tipo = tipoCampo;
        this.entrada = entrada;
        if (tipoCampo.seEncuentraEn(TipoCampoConfiguracion.CATALOGOSIMPLE,
                TipoCampoConfiguracion.CATALOGOINDEPENDIENTE, TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            attrCatSeleccion = 1;
        } else {
            attrCatSeleccion = 0;
        }
    }

    /**
     * @return tipo de campo del campo, despues de procesado desde el xml plantilla
     */
    public TipoCampoConfiguracion getTipo() {
        return tipo;
    }

    public void setTipo(TipoCampoConfiguracion tipo) {
        this.tipo = tipo;
    }

    /**
     * @return codigo unico del campo en la configuracion
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return nombre descriptivo del campo en la configuracion, valor extraido del xml plantilla
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return descripcion del campo en la configuracion, valor extraido del xml plantilla
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Agrega una validacion al campo actual
     * 
     * @param operador
     *            tipo de operacion
     * @param operando
     *            objeto contra el que se realiza la validacion
     * @throws IllegalStateException
     *             en caso de que se intente agegar mas validaciones de las permitidas para un tipo, ver {@link Operador}.cantidad
     */
    public void addValidacion(Operador operador, Object operando) {
        Iterable<Entry<Operador, Object>> filtro = encontrarValidaciones(operador);
        int size = Iterables.size(filtro);
        if (operador.cantidad == -1 || operador.cantidad > size)
            validaciones.add(new AbstractMap.SimpleEntry<Operador, Object>(operador, operando));
        else
            throw new IllegalStateException(MessageFormat.format(
                    "No es posible agregar mas de {0} validaciones para el tipo {1} ", operador.cantidad,
                    operador.toString()));
    }

    /**
     * @return indica si el campo esta parametrizado como entrada
     */
    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    /**
     * @return indica si el campo debe llevar un valor diferente de null en los valores de la configuracion
     */
    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    /**
     * @return almacena el origen de datos para los campos de tipo:
     *         <ul>
     *         <li>CATALOGOSIMPLE: nombreClaseCatalogo</li>
     *         <li>CATALOGOCOMPUESTO: nombreClaseCatalogo</li>
     *         <li>CATALOGOINDEPENDIENTE: nombreInterface{{nombreServicio}}</li>
     *         </ul>
     */
    public String getAttrCatOrigenDatos() {
        return attrCatOrigenDatos;
    }

    public void setAttrCatOrigenDatos(String attrCatOrigenDatos) {
        this.attrCatOrigenDatos = attrCatOrigenDatos;
    }

    public int getAttrDecPrecision() {
        return attrDecPrecision;
    }

    public void setAttrDecPrecision(int attrDecPrecision) {
        this.attrDecPrecision = attrDecPrecision;
    }

    public boolean isAttrRngTraslapar() {
        return attrRngTraslapar;
    }

    public void setAttrRngTraslapar(boolean attrRngTraslapar) {
        this.attrRngTraslapar = attrRngTraslapar;
    }

    public int getAttrCatSeleccion() {
        return attrCatSeleccion;
    }

    public void setAttrCatSeleccion(int attrCatSeleccion) {
        this.attrCatSeleccion = attrCatSeleccion;
    }

    public String getAttrRngCodigoRango() {
        return attrRngCodigoRango;
    }

    public void setAttrRngCodigoRango(String attrRngCodigoRango) {
        this.attrRngCodigoRango = attrRngCodigoRango;
    }

    public Deque<CampoConfiguracionDTO> getDependenciaCatalogo() {
        return dependenciaCatalogo;
    }

    @Override
    public String toString() {

        String valor = "";
        if (getTipo().equals(TipoCampoConfiguracion.DECIMAL)) {
            valor = String.format(", attrDecPrecision=%s", attrDecPrecision);
        } else if (attrRngCodigoRango != null) {
            valor = String.format(", attrRngTraslapar=%s, attrRngCodigoRango=%s", attrRngTraslapar, attrRngCodigoRango);
        } else if (attrCatOrigenDatos != null) {
            valor = String.format(", attrCatSeleccion=%s, attrCatOrigenDatos=%s", attrCatSeleccion, attrCatOrigenDatos);
        }

        return String
                .format("{tipo=%s, codigo=%s, nombre=%s, descripcion=%s, flags={entrada=%s, obligatorio=%s}, validaciones=%s %s}",
                        tipo, codigo, nombre, descripcion, entrada, obligatorio, validaciones, valor);
    }

    /**
     * Busca en la lista de operaciones las que coincidan con el operador indicado
     * 
     * @param operador
     *            operador por el cual filtrar, si es igual a null, retorna todas las validaciones
     * @return objeto iterable inmodificable con las validaciones encontradas
     */
    public List<Entry<Operador, Object>> encontrarValidaciones(final Operador... operador) {
        if (operador == null)
            return Collections.unmodifiableList(validaciones);
        else {
            List<Entry<Operador, Object>> filtro = new ArrayList<>(1);
            List<Operador> operadores = Arrays.asList(operador);
            for (Entry<Operador, Object> val : validaciones) {
                if (operadores.contains(val.getKey())) {
                    filtro.add(val);
                }
            }
            return filtro;
        }
    }

    /**
     * Descompone el origen de datos de un catalogo independiente para obtener el nombre JNDI del objeto que permite obtener el catalogo
     * 
     * @return nombre del objeto
     */
    public String nombreInterfaz() {
        if (!getTipo().equals(TipoCampoConfiguracion.CATALOGOINDEPENDIENTE))
            throw new CirculemosRuntimeException("El nombre de la interfaz no puede ser aplicado al campo de tipo {0}",
                    getTipo());
        Matcher matcher = PARTE_NOMBRE_INTERFAZ.matcher(getAttrCatOrigenDatos());
        matcher.find();
        return matcher.group(1).replaceAll(VERSION_REGEX, Utilidades.cargarVersionArtefacto());
    }

    /**
     * Descompone el origen de datos de un catalogo independiente para obtener el nombre del metodo del objeto JNDI a ejecutar para
     * 
     * @return nombre del metodo
     */
    public String nombreMetodo() {
        if (!getTipo().equals(TipoCampoConfiguracion.CATALOGOINDEPENDIENTE))
            throw new CirculemosRuntimeException("El nombre del metodo no puede ser aplicado al campo de tipo {0}",
                    getTipo());
        Matcher matcher = PARTE_NOMBRE_METODO.matcher(getAttrCatOrigenDatos());
        matcher.find();
        return matcher.group(1);
    }

    public boolean esTipo(TipoCampoConfiguracion... tipo){
        checkNotNull(tipo);
        checkState(tipo.length > 0);
        return this.getTipo().seEncuentraEn(tipo);
    }
}
