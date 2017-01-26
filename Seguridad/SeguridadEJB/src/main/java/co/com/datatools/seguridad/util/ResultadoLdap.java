package co.com.datatools.seguridad.util;

import co.com.datatools.util.ldap.LdapResultado;

/**
 * Clase para encapsular los atributos(nombre y valor) retornados del LDAP
 * 
 * @author claudia.rodriguez
 */
public class ResultadoLdap implements LdapResultado {

    private String valor;
    private String nombre;

    public ResultadoLdap() {

    }

    @Override
    public void asignarAtributo(String nombreAttr, Object valorAttr) {
        setNombre(nombreAttr);
        setValor((String) valorAttr);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}