package co.com.datatools.util.ldap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

/**
 * Objeto q almacena la configuracion para conectarse a un LDAP y filtrar los resultados
 * 
 * @author felipe.martinez
 */
public class LdapContexto implements Serializable {

    private static final long serialVersionUID = 2508554519882705515L;

    /**
     * Valores minimos para la conexion a un LDAP
     * 
     * @author felipe.martinez
     */
    public enum EnumLdapParams {
        INITIAL_CONTEXT_FACTORY(Context.INITIAL_CONTEXT_FACTORY), //
        PROVIDER_URL(Context.PROVIDER_URL), //
        SECURITY_AUTHENTICATION(Context.SECURITY_AUTHENTICATION), //
        SECURITY_PRINCIPAL(Context.SECURITY_PRINCIPAL), //
        SECURITY_CREDENTIALS(Context.SECURITY_CREDENTIALS), //
        SASL_REALM("java.naming.security.sasl.realm");

        protected String propiedad;

        EnumLdapParams(String propiedad) {
            this.propiedad = propiedad;
        }
    }

    /**
     * Variables de entorno de la conexion con LDAP
     */
    private Hashtable<String, String> environment;
    /**
     * Atributos y valores contra los q se validan los resultados
     */
    private Attributes matchingAttributes;
    /**
     * Campos q se retornan de los elementos seleccionados de la respuesta del LDAP
     */
    private List<String> attributesToReturn;
    /**
     * Ubicacion del contexto desde donde se van a filtrar los resultados del LDAP
     */
    private String searchBasePath;

    /**
     * Constructor por defecto
     */
    public LdapContexto() {
        this.environment = new Hashtable<String, String>(6);
        this.matchingAttributes = new BasicAttributes(true);
        this.attributesToReturn = new ArrayList<>(6);
        this.setSearchBasePath("");
    }

    /**
     * Agrega los valores minimos para conexion con el LDAP
     * 
     * @param paramType
     *            catalogo de propiedades minimas
     * @param paramValue
     *            valor para la propiedad
     */
    public void putParam(EnumLdapParams paramType, String paramValue) {
        environment.put(paramType.propiedad, paramValue);
    }

    /**
     * Campos contra los q se validan los resultados del LDAP
     * 
     * @param id
     *            identificador del campo para hacer match
     * @param value
     *            valor con el q se hace el match
     */
    public void putMatchingAttribute(String id, Object value) {
        matchingAttributes.put(new BasicAttribute(id, value));
    }

    /**
     * Agrega multiples campos para retornar del resultado del LDAP
     * 
     * @param attributes
     *            nombres de los campos a retornar de los resultados del LDAP
     */
    public void addAttributeToReturn(String... attributes) {
        attributesToReturn.addAll(Arrays.asList(attributes));
    }

    public Hashtable<String, String> getEnvironment() {
        return environment;
    }

    public void setEnvironment(Hashtable<String, String> environment) {
        this.environment = environment;
    }

    public Attributes getMatchingAttributes() {
        return matchingAttributes;
    }

    public void setMatchingAttributes(Attributes matchingAttributes) {
        this.matchingAttributes = matchingAttributes;
    }

    public List<String> getAttributesToReturn() {
        return attributesToReturn;
    }

    public void setAttributesToReturn(List<String> attributesToReturn) {
        this.attributesToReturn = attributesToReturn;
    }

    public String getSearchBasePath() {
        return searchBasePath;
    }

    public void setSearchBasePath(String searchBasePath) {
        this.searchBasePath = searchBasePath;
    }
}
