package co.com.datatools.c2.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.bundle.comun.EnumParametrosWeb;
import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException.ErrorParametro;
import co.com.datatools.util.date.UtilFecha;
import sun.misc.BASE64Decoder;

public final class Utilidades {

    /**
     * Bundle de parametros web para lectura de fechas configuradas y otros formatos.
     */
    private static final String BUNDLE_PARAMETROS_WEB = "co.com.datatools.c2.bundle.comun.ParametrosWeb";
    private static final String ARCHIVO_CONFIG_EXTERNO = "Config_WS.properties";
    private static Properties propiedades = null;

    private static final Logger LOGGER = Logger.getLogger(Utilidades.class.getName());

    /**
     * Contiene el divisor de milisegundos para convertir a dias
     */
    private static final long MILISEGUNDOS_DIA = 24 * 60 * 60 * 1000;
    private static final int BYTES_IN_ONE_MEGABYTE = 1048576;

    private Utilidades() {
    }

    /**
     * 
     */
    public static Long convertBigIntegerToLong(Object valor) {
        BigInteger valorLong;
        if (valor != null) {
            valorLong = (BigInteger) valor;
            return valorLong.longValue();
        }
        return null;
    }

    public static Date getFechaActual() {
        return GregorianCalendar.getInstance().getTime();
    }

    /**
     * Retorna el digito de verificacion correspondiente al nit indicado (Solo aplica para Colombia)
     * 
     * @param numeroNit
     *            contiene el nit del cual se calculara el digito de verificacion
     * 
     * @return entero de un digito correspondiente al digito de verificacion del nit indicado o -1 si hay algun error
     */
    public static int digitoVerificacion(String numeroNit) {
        int moduloPrimos = 11;
        int minimoModulo = 1;
        int digitoVerificacion = -1;
        int suma = 0;

        // longitud 15
        int numerosPrimos[] = new int[] { 71, 67, 59, 53, 47, 43, 41, 37, 29, 23, 19, 17, 13, 7, 3 };

        if ((numeroNit != null) && (!numeroNit.trim().isEmpty())) {
            numeroNit = String.format("%0" + numerosPrimos.length + "d", Integer.parseInt(numeroNit));

            try {
                for (int i = 0; i < numerosPrimos.length; i++) {
                    suma += (Integer.parseInt(numeroNit.substring(i, i + 1))) * numerosPrimos[i];
                }

                digitoVerificacion = suma % moduloPrimos;
                if (digitoVerificacion > minimoModulo) {
                    digitoVerificacion = moduloPrimos - digitoVerificacion;
                }

            } catch (NumberFormatException e) {
                LOGGER.info("Datos no numericos ", e);
            }
        } else {
            LOGGER.info("Nit vacio ");
        }

        return digitoVerificacion;
    }

    /**
     * Retorna los dias diferencia entre dos fechas.
     * 
     * @param fechaInicial
     *            La fecha inicial
     * @param fechaFinal
     *            La fecha final
     * @return El numero de dias
     * @author rodrigo.cruz
     */
    public static int diasDiferencia(Date fechaInicial, Date fechaFinal) {
        return (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / Utilidades.MILISEGUNDOS_DIA);
    }

    /**
     * Obtiene el anio de una fecha.
     * 
     * @param fecha
     *            La fecha de la cual se quiere obtener el anio
     * @return El anio de la fecha enviada
     * @author rodrigo.cruz
     */
    public static int getYearFromDate(Date fecha) {
        Calendar fechaCal = Calendar.getInstance();
        fechaCal.setTime(fecha);

        return fechaCal.get(Calendar.YEAR);
    }

    /**
     * Retorna una fecha con los dias adicionales a la fecha enviada.
     * 
     * @param fechaInicial
     *            La fecha a la que se adicionan los dias.
     * @param dias
     *            Los dias a adicionar.
     * @return Una fecha con los dias adicionales a la fecha enviada.
     * @author rodrigo.cruz
     */
    public static Date sumarDias(Date fechaInicial, int dias) {
        Calendar fechaInicialCal = Calendar.getInstance();
        fechaInicialCal.setTime(fechaInicial);
        fechaInicialCal.add(Calendar.DAY_OF_MONTH, dias);
        return fechaInicialCal.getTime();
    }

    /**
     * Valida si la cadena indicada es numerica
     * 
     * @author camilo.sierra
     * @param cadena
     *            la cadena a validar
     * @return Verdadero cadena es numerica
     * @deprecated {@link StringUtils#isNumeric(CharSequence)}
     */
    @Deprecated
    public static boolean isNumeric(String cadena) {
        return cadena.matches("[0-9]*");
    }

    /**
     * Retorna el valor con el formato y el separador indicados
     * 
     * @author rodrigo.cruz
     * @param valor
     *            valor a formatear
     * @param formato
     *            formato del numero a retornar
     * @param separador
     *            separador a utilizar
     * @return Numero en formato indicado por formato y con el separador establecido
     */
    public static BigDecimal formatearDouble(BigDecimal valor, String formato, char separador) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator(separador);

        DecimalFormat df = new DecimalFormat(formato, symbols);
        String sValor = df.format(valor);

        return new BigDecimal(sValor);
    }

    /**
     * Permite convertir los parametros configurados en la BD como S o N como true o false
     * 
     * @param booleano
     *            S = true, N = false
     * @return S = true, N = false
     * @deprecated {@link #toBooleanParametro(EnumParametro, String)}
     */
    @Deprecated
    public static boolean toBooleanParametro(String booleano) {
        if ((booleano == null) || ((!"N".equals(booleano)) && (!"S".equals(booleano)))) {
            throw new CirculemosRuntimeException(
                    "Error en la configuracion parametro, se esperaba S o N, se obtuvo " + booleano);
        }

        return "S".equals(booleano);
    }

    public static boolean toBooleanParametro(EnumParametro parametro, String booleano) {
        if ((booleano == null) || ((!"N".equals(booleano)) && (!"S".equals(booleano)))) {
            throw new ConfiguracionParametroException(ErrorParametro.PARAMETRO_NO_BOOLEAN, parametro);
        }

        return "S".equals(booleano);
    }

    /**
     * obtiene la fecha para la generacion de la referencia de pago
     * 
     * @author pedro.moncada
     * @return Fecha en formato cadena para referencias de pago
     */
    public static String obtenerFechaReferenciaPago() {
        DateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * Ajusta con ceros a la izquierda la entrada indicada para que tenga el tamaño indicado
     * 
     * @author pedro.moncada
     * @param entrada
     *            entrada a ajustar
     * @param tamanio
     *            tamaño resultante de la entrada
     */
    public static String ajustarCerosString(String entrada, int tamanio) {
        int valor = 0;
        String resultado = "";
        if (entrada.length() < tamanio) {
            resultado = String.format("%0" + (tamanio - entrada.length()) + "d", valor);
        }

        return resultado + entrada;
    }

    /**
     * 
     * @param numero
     *            numero del comparendo sin completar
     * @param codigoOrganismo
     *            codigo del organismo para completar el numero del comparendo
     * @return Retorna el numero del comparendo acorde al numero y codigo de organismo indicados
     */
    public static String completarNumeroComparendo(String numero, Integer codigoOrganismo) {
        if ((!numero.isEmpty())
                && (numero.length() <= ConstantesComparendo.LENGHT_NUM_COMPARENDO_CONSECUTIVO_COMPARENDO)) {
            // 1. Validar digitos
            if (!StringUtils.isNumeric(numero)) {
                throw new CirculemosRuntimeException("El numero de comparendo tiene caracteres invalidos");
            }
            StringBuilder sCodigoOrganismo = null;
            // 2. Completar codigo de organismo de transito
            if (codigoOrganismo != null) {
                sCodigoOrganismo = new StringBuilder(codigoOrganismo.toString());
                // Si longitud es menor que 8, concatena ceros a la derecha hasta que la longitud sea 8
                while (sCodigoOrganismo.length() < ConstantesComparendo.LENGHT_NUM_COMPARENDO_COD_ORGANISMO) {
                    sCodigoOrganismo.append('0');
                }
            } else {
                throw new CirculemosRuntimeException("El codigo de organismo es nulo");
            }
            // 3. Completar consecutivo de comparendo
            StringBuilder numeroComparendo = new StringBuilder(numero);
            // Si la longitud es menor que 12, concatena ceros a la izquierda hasta que la longitud sea 12
            while (numeroComparendo.length() < ConstantesComparendo.LENGHT_NUM_COMPARENDO_CONSECUTIVO_COMPARENDO) {
                numeroComparendo.insert(0, "0");
            }
            // 4. Concatenar codigo de organismo con consecutivo de comparendo
            numeroComparendo.insert(0, sCodigoOrganismo.toString());
            numero = numeroComparendo.toString();

        }

        return numero;
    }

    /**
     * Permite buscar un elemento de una enumeracion q implementa la interfaz {@link SearchableEnumeration}
     * 
     * @param searchEnumClass
     *            tipo de la enumeracion en la q se busca el elemento
     * @param value
     *            valor q se compara contra los valores de los elementos
     * @return enumeracion encontrada, null si no se encuentra
     * @throws CirculemosRuntimeException
     *             si <b>searchEnumClass</b> no implementa {@link SearchableEnumeration}
     * @author felipe.martinez
     */
    @SuppressWarnings("rawtypes")
    public static <T extends Enum> T buscarElemEnum(Class<T> searchEnumClass, Object value)
            throws CirculemosRuntimeException {
        if (!SearchableEnumeration.class.isAssignableFrom(searchEnumClass)) {
            throw new CirculemosRuntimeException("Tipo invalido de enumeracion {0}, debe implementar {1}",
                    searchEnumClass.getName(), SearchableEnumeration.class.getName());
        }
        for (T elem : searchEnumClass.getEnumConstants()) {
            if (elem instanceof SearchableEnumeration) {
                SearchableEnumeration srchElem = (SearchableEnumeration) elem;
                if (srchElem.getValue().equals(value)) {
                    return elem;
                }
            }
        }
        return null;
    }

    /**
     * Prevalida q la lista de entrada no sea null, si es null returna una lista vacia
     * 
     * @param lista
     *            objeto a validar
     * @return <b>lista</b> si es diferente de null, de lo contrario {@link Collections#emptyList()}
     * @author felipe.martinez
     */
    public static <T> List<T> safeList(List<T> lista) {
        List<T> resultado = lista;
        if (lista == null) {
            resultado = Collections.emptyList();
        }

        return resultado;
    }

    /**
     * Permite reemplazar el caracter ~ al inicio de la cadena por la ruta del usuario del systema
     * 
     * @param ruta
     *            cadena a procesar
     * @return cadena precedida por la ruta del usuario si aplica
     */
    public static String procesarRutaUsuario(String ruta) {
        final String rutaUsuario = System.getProperty("user.home");
        if ((ruta != null) && (!ruta.isEmpty()) && (ruta.charAt(0) == '~')) {
            ruta = rutaUsuario + ruta.substring(1);
        }

        return ruta;
    }

    /**
     * Calcula la edad apartir de una fecha de nacimiento de una persona
     * 
     * <pre>
     * Fecha de nacimiento no puede ser nula
     * </pre>
     * 
     * @param fechaNacimiento
     *            fecha de nacimiento de la persona
     * @return edad de la persona
     * @author luis.forero (2014-11-19)
     */
    public static int calcularEdad(Date fechaNacimiento) {
        Calendar cal = Calendar.getInstance();
        int actMes = cal.get(Calendar.MONTH) + 1;
        int actAnio = cal.get(Calendar.YEAR);
        int actDia = cal.get(Calendar.DAY_OF_MONTH);

        Calendar nacimi = Calendar.getInstance();
        nacimi.setTime(fechaNacimiento);
        int naMes = nacimi.get(Calendar.MONTH) + 1;
        int naAnio = nacimi.get(Calendar.YEAR);
        int naDia = nacimi.get(Calendar.DAY_OF_MONTH);

        int anios = actAnio - naAnio;
        int dias = actDia - naDia;
        int meses = actMes - naMes;
        if (dias < 0) {
            meses--;
        }
        if (meses < 0) {
            anios--;
        }

        return anios;
    }

    public static String cargarVersionArtefacto() {
        Properties propiedades = new Properties();
        try {
            propiedades.load(BeanLocatorC2.class.getResourceAsStream("/c2-artefacto.properties"));
            return propiedades.getProperty("version");
        } catch (IOException e) {
            LOGGER.error("Problemas cargando propiedades. ", e);
            throw new CirculemosRuntimeException(
                    "No se encuentra el archivo de propiedades de versiones c2-artefacto.properties");
        }
    }

    /**
     * Convierte y formatea la fecha al formato actual definido para la aplciacion.
     * 
     * @param fecha
     *            fecha de entrada
     * @param fullFormat
     *            indica si es o no en formato completo
     * @return fecha con el formato de definido para la aplicacion
     * @author luis.forero(2016-02-15)
     */
    public static String dateToStringFormatApp(Date fecha, boolean fullFormat) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PARAMETROS_WEB, Locale.getDefault());
        String formato = null;
        if (fullFormat) {
            formato = bundle.getString(EnumParametrosWeb.lab_calendar_pattern_full.toString());
        } else {
            formato = bundle.getString(EnumParametrosWeb.lab_calendar_pattern.toString());
        }
        return UtilFecha.dateToString(fecha, formato);
    }

    /**
     * Convierte y formatea la fecha del formato actual definido para la aplicacion.
     * 
     * @param fecha
     *            fecha de entrada en formato texto
     * @param fullFormat
     *            indica si es o no en formato completo
     * @return fecha con el formato de definido para la aplicacion
     * @author luis.forero(2016-02-15)
     */
    public static Date stringToDateFormatApp(String fecha, boolean fullFormat) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PARAMETROS_WEB, Locale.getDefault());
        String formato = null;
        if (fullFormat) {
            formato = bundle.getString(EnumParametrosWeb.lab_calendar_pattern_full.toString());
        } else {
            formato = bundle.getString(EnumParametrosWeb.lab_calendar_pattern.toString());
        }
        return UtilFecha.stringToDate(fecha, formato);
    }

    /**
     * Convierte y formatea la hora al formato actual definido para la aplciacion.
     * 
     * @param fecha
     *            fecha de entrada
     * @return hora con el formato de definido para la aplicacion
     * @author luis.forero(2016-02-15)
     */
    public static String hourToStringFormatApp(Date fecha) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PARAMETROS_WEB, Locale.getDefault());
        String formato = null;
        formato = bundle.getString(EnumParametrosWeb.lab_calendar_hour_pattern.toString());
        return UtilFecha.dateToString(fecha, formato);
    }

    public static byte[] getBase64ImgBytes(String imageString) throws IOException {
        BufferedImage image = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(imageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        return imageByte;
    }

    /**
     * Permite adjuntar varios archivos en un archivo .zip
     * 
     * @param ZipOutputStream
     * @param String
     * @param byte[]
     * @throws IOException
     * @author divier.casas(2016-08-29)
     */
    public static void adjuntarArchivos(ZipOutputStream zos, String nombreArchivo, byte[] contenido)
            throws IOException {
        ZipEntry entrada = new ZipEntry(nombreArchivo);
        entrada.setSize(contenido.length);
        zos.putNextEntry(entrada);
        zos.write(contenido);
        zos.closeEntry();
    }

    /**
     * Permite cargar un archivo de propiedades externo
     * 
     * @throws CirculemosRuntimeException
     * @author divier.casas(2016-09-26)
     */
    public static void loadPropiedadesArchivoExterno() throws CirculemosRuntimeException {
        propiedades = new Properties();
        try {
            propiedades
                    .load(Thread.currentThread().getContextClassLoader().getResourceAsStream(ARCHIVO_CONFIG_EXTERNO));
        } catch (IOException e) {
            throw new CirculemosRuntimeException(
                    "No ha sido posible cargar el archivo de propiedades externo: " + ARCHIVO_CONFIG_EXTERNO);
        }
    }

    /**
     * Permite obtener el valor de la clave pasada como argumento de un archivo de propiedades externo
     * 
     * @param clave
     * @return
     * @author divier.casas(2016-09-26)
     */
    public static String getPropiedad(String clave) {
        if (propiedades == null) {
            return null;
        } else {
            return propiedades.getProperty(clave);
        }
    }

    public static int convertirBytesToMegabytes(int bytes) {
        return bytes / BYTES_IN_ONE_MEGABYTE;
    }

    // public static void main(String[] args) {
    // System.out.println(Utilidades.digitoVerificacion("333777100219"));
    // }
}