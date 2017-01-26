package co.com.datatools.c2.enumeracion;

/**
 * Borrar enumeracion cuando deje de usarse en el codigo
 * 
 * @author robert.bautista
 * @since 2013-10-08
 */
@Deprecated
public enum EnumCodigosProcesos {

    REGISTRAR_PERSONA(1, "Registrar Persona"), ADMINISTRAR_CIA(2, "Administrar Cias");

    /**
     * Contiene el código del proceso
     */
    private int codigo;

    /**
     * Contiene el nombre del proceso
     */
    private String nombre;

    /**
     * Construye una enumeración de códigos de proceso con el código y nombre indicados
     * 
     * @param codigo
     *            el código del proceso
     * 
     * @param nombre
     *            nombre del proceso
     */
    private EnumCodigosProcesos(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Retorna el código del proceso
     * 
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre del proceso
     * 
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre del parámetro de redireccionamiento acorde al código del proceso invocador y el proceso invocado
     * 
     * @param origen
     *            indica el proceso invocador
     * 
     * @param destino
     *            indica el proceso invocado
     * 
     * @return redirect_ codigo proceso origen _ codigo proceso destino
     */
    public static String redireccionamientos(EnumCodigosProcesos origen, EnumCodigosProcesos destino) {
        return "redirect_" + origen.getCodigo() + "_" + destino.getCodigo();
    }

    /**
     * Retorna la enumeración de código de proceso acorde al código recibido
     * 
     * @param codigo
     *            código de la enumeración a retornar
     */
    public static EnumCodigosProcesos cargueCodigoProceso(int codigo) {
        EnumCodigosProcesos cod = null;

        switch (codigo) {
        case 1:
            cod = EnumCodigosProcesos.REGISTRAR_PERSONA;
            break;
        case 2:
            cod = EnumCodigosProcesos.REGISTRAR_PERSONA;
            break;
        }

        return cod;
    }

    /**
     * Retorna el nombre del parámetro del proceso que invoca otro caso de uso
     * 
     * @return nombre del parámetro
     */
    public static String parametroProceso() {
        return "proceso_cliente";
    }

    /**
     * Retorna el nombre del parámetro del proceso que responde a la invocación de un cliente
     * 
     * @param codigoProceso
     *            contiene el proceso que responde al cliente
     * @return nombre del parámetro
     */
    public static String resultadoProceso(EnumCodigosProcesos codigoProceso) {
        return "respuesta_proceso_" + codigoProceso.getCodigo();
    }

}
