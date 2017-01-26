package co.com.datatools.util.ldap;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

import co.com.datatools.util.ldap.LdapContexto.EnumLdapParams;

/**
 * Fachada para conectarse a un Ldap y realizar diferentes validaciones
 * 
 * https://docs.oracle.com/javase/tutorial/jndi/ldap/sasl.html
 * 
 * @author felipe.martinez
 */
public class LDAP {
    /**
     * Realiza la autenticacion de un usuario-password contra un LDAP
     * 
     * @param ldapCtxt
     *            parametros de configuracion para conexion y credenciales a validar
     *            <ul>
     *            <li>{@link EnumLdapParams#INITIAL_CONTEXT_FACTORY}</li>
     *            <li>{@link EnumLdapParams#PROVIDER_URL}</li>
     *            <li>{@link EnumLdapParams#SECURITY_AUTHENTICATION}</li>
     *            <li>{@link EnumLdapParams#SECURITY_PRINCIPAL} - Login</li>
     *            <li>{@link EnumLdapParams#SECURITY_CREDENTIALS} - Password</li>
     *            </ul>
     * @return true el usuario fue autenticado correctamente, false error de autenticacion
     */
    public boolean autenticar(LdapContexto ldapCtxt) {
        // Realizar la conexion si no existe se va por el catch
        try {
            Context ctx = new InitialDirContext(ldapCtxt.getEnvironment());
            ctx.close();
            return true;
        } catch (NamingException e) {
            
            return false;
        }
    }

    /**
     * Consulta los atributos {@link LdapContexto#getAttributesToReturn()} de los elementos filtrados con {@link LdapContexto#getMatchingAttributes()}
     * en el path {@link LdapContexto#getSearchBasePath()} del Ldap
     * 
     * @param <T>
     *            tipo especifico del resultado de la consulta
     * @param ldapCtxt
     *            contexto Ldap con la configuracion de conexion, el path sobre el que se realiza la consulta y los atributos a extraer
     * @param tipoRespuesta
     *            Tipo de los objetos q se van a retonar como respuesta a la consulta, debe tener constructor por defecto
     * @return elementos encontrados con las condiciones de busqueda, vacio si no encuentra nada
     * @throws NamingException
     *             Error en la conexion con el Ldap
     * @throws InstantiationException
     *             Error instanciando un nuevo objeto de tipo tipoRespuesta
     * @throws IllegalAccessException
     *             Error accediendo al constructor
     */
    public <T extends LdapResultado> List<T> consultarElementos(LdapContexto ldapCtxt, Class<T> tipoRespuesta)
            throws NamingException, InstantiationException, IllegalAccessException {
        List<T> resultado = new ArrayList<>();
        final DirContext ctx = new InitialDirContext(ldapCtxt.getEnvironment());
        final NamingEnumeration<SearchResult> results = ctx.search(ldapCtxt.getSearchBasePath(),
                ldapCtxt.getMatchingAttributes(), ldapCtxt.getAttributesToReturn().toArray(new String[0]));
        while (results.hasMore()) {
            final SearchResult result = results.nextElement();
            @SuppressWarnings("rawtypes")
            final NamingEnumeration attrsResult = result.getAttributes().getAll();
            while (attrsResult.hasMore()) {
                Attribute attr = (Attribute) attrsResult.nextElement();
                String attrId = attr.getID();
                if (ldapCtxt.getAttributesToReturn().contains(attrId)) {
                    // Obtener valor del atributo
                    @SuppressWarnings("rawtypes")
                    Enumeration vals = attr.getAll();
                    final Object valorAttr = vals.nextElement();
                    final T newInstance = tipoRespuesta.newInstance();
                    resultado.add(newInstance);
                    newInstance.asignarAtributo(attrId, valorAttr);
                }
            }
        }
        return resultado;
    }
}
