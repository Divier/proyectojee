package co.com.datatools.c2.negocio.interfaces;

/**
 * @author luis.forero
 * 
 */
public interface ILReglaNegocio {

    /**
     * Metodo para ejecutar una regla de drools.<br>
     * El objeto que recibe el metodo es actualizado una vez se disparan las reglas pero no se debe usar por referencia por que no es confiable en un
     * sistema distribuido. Obtener la respuesta de drools a traves del retorno
     * 
     * @param objRegla
     *            dto de parametros y es actualizado con el valor de la regla
     * @return objecto con los valores parametrizados en drools para la regla ejecutada
     */
    Object consumirRegla(Object parametrosEntrada);
}
