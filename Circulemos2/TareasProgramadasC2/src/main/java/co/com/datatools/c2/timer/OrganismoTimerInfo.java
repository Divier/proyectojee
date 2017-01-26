package co.com.datatools.c2.timer;

import javax.ejb.ScheduleExpression;

import org.apache.commons.lang3.StringUtils;

/**
 * Almacena la informacion parametrizada para una tarea programada de un organismo de transito
 * 
 * @author felipe.martinez
 */
public class OrganismoTimerInfo implements CirculemosTimerInfo {

    private static final long serialVersionUID = 8347831532628614892L;

    /**
     * Codigo de organismo que tiene configurada una expresion para una tarea programada
     */
    private Integer codigoOrganismo;

    /**
     * Expresion CRON que define la hora de ejecucion de una tarea programada
     */
    private String expresion;

    public OrganismoTimerInfo(Integer codigoOrganismo, String expresion) {
        super();
        this.expresion = StringUtils.trim(expresion);
        this.codigoOrganismo = codigoOrganismo;
    }

    @Override
    public boolean equalsKey(CirculemosTimerInfo obj) {
        if (obj != null && obj instanceof OrganismoTimerInfo) {
            OrganismoTimerInfo otro = (OrganismoTimerInfo) obj;
            if (this.codigoOrganismo.equals(otro.codigoOrganismo))
                return true;
        }
        return false;
    }

    @Override
    public boolean equalsContent(CirculemosTimerInfo obj) {
        if (this.equalsKey(obj)) {
            OrganismoTimerInfo otro = (OrganismoTimerInfo) obj;
            if (this.expresion.equals(otro.expresion))
                return true;
        }
        return false;
    }

    @Override
    public boolean isValid() {
        return asScheduleExpression() != null;
    }

    @Override
    public ScheduleExpression asScheduleExpression() {
        if (StringUtils.isBlank(expresion) || StringUtils.startsWith(expresion, "-1")) {
            return null;
        }

        final String[] split = expresion.split(" ");
        ScheduleExpression expr = new ScheduleExpression();
        for (int i = 0; i < split.length; i++) {
            switch (i) {
            case 0:
                expr.second(split[i]);
                break;
            case 1:
                expr.minute(split[i]);
                break;
            case 2:
                expr.hour(split[i]);
                break;
            case 3:
                expr.dayOfMonth(split[i]);
                break;
            case 4:
                expr.month(split[i]);
                break;
            case 5:
                expr.dayOfWeek(split[i]);
                break;
            case 6:
                expr.year(split[i]);
                break;
            }
        }
        return expr;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public String getExpresion() {
        return expresion;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " {codigoOrganismo: " + codigoOrganismo + ", expresion: " + expresion + "}";
    }

}
