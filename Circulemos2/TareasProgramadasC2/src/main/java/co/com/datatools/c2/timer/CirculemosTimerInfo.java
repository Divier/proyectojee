package co.com.datatools.c2.timer;

import java.io.Serializable;

import javax.ejb.ScheduleExpression;

/**
 * Interfaz general para todos los objetos q se usen como informacion de los Timers q se configuran para las tareas programadas
 * 
 * @author felipe.martinez
 */
public interface CirculemosTimerInfo extends Serializable {

    /**
     * Compara si obj y this hacen referencia a la misma tarea en el sistema
     * 
     * @param obj
     *            objeto contra el q se realiza la comparacion
     * @return true si tienen llaves iguales, false si no tienen llaves iguales
     */
    boolean equalsKey(CirculemosTimerInfo obj);

    /**
     * Este metodo compara si un TimerInfo tiene la misma configuracion q this
     * 
     * @param obj
     *            objeto a comparar
     * @return true en caso q todos los campos sean iguales, tanto llave como elementos de informacion
     */
    boolean equalsContent(CirculemosTimerInfo obj);

    /**
     * Verifica si el contenido del objeto implementador esta consistente y permite crear un {@link ScheduleExpression}
     * 
     * @return true si el contenido del objeto es una configuracion valida para programar tarea
     */
    boolean isValid();

    /**
     * Convierte la informacion de negocio en una {@link javax.ejb.ScheduleExpression} programable en el sistema
     * 
     * @return null si la configuracion no pudo ser parseada a una ScheduleExpression
     */
    ScheduleExpression asScheduleExpression();
}
