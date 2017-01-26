package co.com.datatools.util.ldap;

/**
 * Interface para los objetos q se quieren consultar del Ldap
 * 
 * @author felipe.martinez
 * */
public interface LdapResultado {
    /**
     * Metodo utilizado para setear los atributos obtenidas del Ldap dentro del objeto de respuesta
     * 
     * @param nombreAttr
     *            nombre del atributo consultado del Ldap
     * @param valorAttr
     *            valor del atributo consultado del Ldap
     */
    void asignarAtributo(String nombreAttr, Object valorAttr);
}
