package co.com.datatools.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Clase de funciones utilitarias para manejo de Date y Calendar<br>
 * Antes de agregar metodos, verificar que no existen en {@link DateUtils}
 * 
 * @author Felipe Martinez
 */
public class UtilFecha {

    /**
     * Retorna un nuevo calendario con los campos de hora, minuto, segundo, milisegundos a ceros
     * 
     * @param cal
     *            fecha a resetear
     * @return calendario con las campos reseteados
     * @author Felipe Martinez
     */
    public static final Calendar resetTime(Calendar cal) {
        return DateUtils.truncate(cal, Calendar.DAY_OF_MONTH);
    }

    /**
     * Retorna un nuevo calendario con los campos de hora, minuto, segundo y milisegundo en el maximo valor del dia
     * 
     * 
     * @param cal
     * @return calendario con los campos modificados
     * @author sergio.torres (11-feb-2016)
     */
    public static final Calendar upsetTime(Calendar cal) {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal = DateUtils.truncate(cal, Calendar.DAY_OF_MONTH);
        cal.add(Calendar.MILLISECOND, -1);
        return cal;
    }

    /**
     * Retorna un nuevo calendario con los campos de hora, minuto, segundo y milisegundo en el maximo valor del dia
     * 
     * 
     * @param cal
     * @return calendario con los campos modificados
     * @author sergio.torres (11-feb-2016)
     */
    public static final Calendar upsetTime(Date date) {
        return upsetTime(buildCalendar(date));
    }

    /**
     * Retorna un calendario con los campos de hora, minuto, segundo, milisegundos a ceros
     * 
     * @param base
     *            fecha a resetear
     * @return calendario con las campos reseteados
     * @author Felipe Martinez
     */
    public static final Calendar resetTime(Date base) {
        return buildCalendar(DateUtils.truncate(base, Calendar.DAY_OF_MONTH));
    }

    /**
     * Obtiene la instancia de calendar con la fecha actual
     * 
     * @return instancia de calendar con la fecha inicializada en la actual
     * @author Felipe Martinez
     */
    public static final Calendar buildCalendar() {
        return buildCalendar(null);
    }

    /**
     * Obtiene la instancia de calendar y le asigna la fecha
     * 
     * @param fecha
     *            fecha/hora a asignar al calendar, si null se retorna calendar con fecha actual
     * @return instancia de calendar con la fecha inicializada
     * @author Felipe Martinez
     */
    public static final Calendar buildCalendar(final Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        if (fecha != null) {
            calendar.setTime(fecha);
        }
        return calendar;
    }

    /**
     * Verifica si inferior <= operador && operador <= superior en cuanto a las fechas, sin comparar respecto a los campos de hora de los 3 argumentos
     * 
     * @param inferior
     *            limite inferior del rango
     * @param superior
     *            limite superior del rango
     * @param comparar
     *            fecha a validar dentro del rango
     * @return true, si inferior <= operador && operador <= superior, si no false
     */
    public static final boolean betweenDate(Date inferior, Date superior, Date comparar) {
        final Calendar calInferior = resetTime(inferior);
        final Calendar calSuperior = resetTime(superior);
        final Calendar calComparar = resetTime(comparar);
        if (calInferior.compareTo(calComparar) <= 0 && calComparar.compareTo(calSuperior) <= 0)
            return true;
        else
            return false;
    }

    /**
     * Retorna el ultimo dia del mes de la fecha del argumento
     * 
     * @param fecha
     * @return El numero de dia final del mes de la fecha del argumento
     */
    public static final int lastDayFromMonth(Date fecha) {
        Calendar calendar = buildCalendar(fecha);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Retorna la fecha actual con la hora reseteada a ceros
     * 
     * @return fecha actual con campos menores a hora en ceros
     */
    public static Date currentZeroTimeDate() {
        return resetTime(buildCalendar(null)).getTime();
    }

    /**
     * Convierte un String que representa una Fecha en un objeto Date
     * 
     * @param patron
     *            formato de entrada, ver SimpleDateFormat para los patrones soportados
     * @param fechaString
     *            cadena con el formato definido
     * @return objeto Date que representa el string <b>fechaString</b>
     * @see SimpleDateFormat
     * @author Felipe Martinez
     */
    public static Date stringToDate(String patron, String fechaString) {
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        try {
            return sdf.parse(fechaString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte una cadena con el formato yyyy/MM/dd en Date
     * 
     * @param fechaString
     *            cadena con el formato definido
     * @return objeto Date que representa el string <b>fechaString</b>
     * @author Felipe Martinez
     */
    public static Date stringYYYMMDDToDate(String fechaString) {
        return stringToDate("yyyy/MM/dd", fechaString);
    }

    /**
     * Obtiene el anio de una fecha
     * 
     * @param fecha
     *            fecha a obtener el anio
     * @return anio de la fecha ingresada
     * @author Felipe Martinez
     */
    public static int yearFromDate(Date fecha) {
        return buildCalendar(fecha).get(Calendar.YEAR);
    }

    /**
     * Obtiene el dia del mes de un fecha
     * 
     * @param fecha
     *            fecha a obtener el dia del mes
     * @return dia del mes de la fecha ingresada [1,31]
     * @author Felipe Martinez
     */
    public static int dayFromDate(Date fecha) {
        return buildCalendar(fecha).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Obtiene el mes de un fecha<br>
     * <b>NOTA</b> los valores del mes para el objeto calendar se encuentran definidos entre [0,11], para mantener la deficion del objecto
     * {@link Calendar} se retornan de la misma manera
     * 
     * @param fecha
     *            fecha a obtener el mes
     * @return mes de la fecha ingresada [0,11]
     * @see java.util.Calendar#MONTH
     * @author Felipe Martinez
     */
    public static int monthFromDate(Date fecha) {
        return buildCalendar(fecha).get(Calendar.MONTH);
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
        return (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 24 * 60 * 60 * 1000);
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
     * Sumar el tiempo (horas, minutos, segundos, milisegundos) de una fecha a otra fecha.
     * 
     * @param fecha
     *            La fecha a la que se suma el tiempo de <b>fechaTiempo</b>
     * @param fechaTiempo
     *            La fecha que tiene el tiempo a sumar
     * @return La fecha con el tiempo sumado
     * @author rodrigo.cruz
     */
    public static Date sumarTiempo(Date fecha, Date fechaTiempo) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fechaTiempo);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);
        int segundo = calendar.get(Calendar.SECOND);
        int miliseg = calendar.get(Calendar.MILLISECOND);
        fecha = DateUtils.addHours(fecha, hora);
        fecha = DateUtils.addMinutes(fecha, minuto);
        fecha = DateUtils.addSeconds(fecha, segundo);
        fecha = DateUtils.addMilliseconds(fecha, miliseg);
        return fecha;
    }

    /**
     * Se encarga de igualar la hora dentro de una fecha determinada.<br>
     * ejm:<br>
     * fecha = 2016-01-01 00:00:00.000<br>
     * hora = 1907-01-01 12:35:22.254<br>
     * Salida= 2016-01-01 12:35:22.254
     * 
     * @param fecha
     *            fecha a la cual se concatena la hora.
     * @param hora
     *            hora a concatenar
     * @return fecha con la hora concatenada
     * @author luis.forero(2016-03-10)
     */
    public static Date setHoraFecha(Date fecha, Date hora) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        Calendar horaCal = Calendar.getInstance();
        horaCal.setTime(hora);
        calendar.set(Calendar.HOUR, horaCal.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, horaCal.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, horaCal.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, horaCal.get(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * Convierte la fecha a su cadena en un formato en especifico
     * 
     * @param fecha
     *            fecha a convertir
     * @param formato
     *            formato de la fecha en la que se quiere
     * @return retorna cadena con la fecha en el formato establecido
     * @author luis.forero (2016-02-03)
     */
    public static String dateToString(Date fecha, String formato) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        return format.format(fecha);
    }

    /**
     * Valida si la fecha tiene alguno de los formatos enviados
     * 
     * @param textoFecha
     *            Cadena con contenido en formato fecha
     * @param patterns
     *            Conjunto de cadenas con patrones de fecha, ejemplo <b>dd/MM/yyyy HH:mm:ss</b>
     * @return {@link Date} si <code>textoFecha</code> correponde a alguno de los patrones, nulo en caso contrario
     * @author rodrigo.cruz
     */
    public static Date validarFormatoFecha(String textoFecha, String... patterns) {
        try {
            return DateUtils.parseDate(textoFecha, patterns);
        } catch (ParseException e) {
            return null;
        }
    }

}
