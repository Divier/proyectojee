package co.com.datatools.c2.enumeraciones;

/**
 * C�digos de respuesta de proceso de generaci�n de resoluciones de sanci�n para comparendos (COM_009)
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumRespuestaResolucionSancion {

    RESOLUCION_GENERADA(1), //
    NO_COMPARENDOS_RESOLUCION(0), //
    ;
    private int id;

    EnumRespuestaResolucionSancion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
