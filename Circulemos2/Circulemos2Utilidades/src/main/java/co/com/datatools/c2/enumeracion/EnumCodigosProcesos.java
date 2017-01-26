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
     * Contiene el c�digo del proceso
     */
    private int codigo;

    /**
     * Contiene el nombre del proceso
     */
    private String nombre;

    /**
     * Construye una enumeraci�n de c�digos de proceso con el c�digo y nombre indicados
     * 
     * @param codigo
     *            el c�digo del proceso
     * 
     * @param nombre
     *            nombre del proceso
     */
    private EnumCodigosProcesos(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Retorna el c�digo del proceso
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
     * Retorna el nombre del par�metro de redireccionamiento acorde al c�digo del proceso invocador y el proceso invocado
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
     * Retorna la enumeraci�n de c�digo de proceso acorde al c�digo recibido
     * 
     * @param codigo
     *            c�digo de la enumeraci�n a retornar
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
     * Retorna el nombre del par�metro del proceso que invoca otro caso de uso
     * 
     * @return nombre del par�metro
     */
    public static String parametroProceso() {
        return "proceso_cliente";
    }

    /**
     * Retorna el nombre del par�metro del proceso que responde a la invocaci�n de un cliente
     * 
     * @param codigoProceso
     *            contiene el proceso que responde al cliente
     * @return nombre del par�metro
     */
    public static String resultadoProceso(EnumCodigosProcesos codigoProceso) {
        return "respuesta_proceso_" + codigoProceso.getCodigo();
    }

}
