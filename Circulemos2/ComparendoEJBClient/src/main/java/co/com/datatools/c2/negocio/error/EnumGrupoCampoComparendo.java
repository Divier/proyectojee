package co.com.datatools.c2.negocio.error;


/**
 * Enum que se encarga de devolver el id correspondiente a cada grupo (pestaña) de la interfaz de la correccion de inconsistencias (COM_003) y
 * rectificacion (COM_014)
 * 
 * @author giovanni.velandia
 * 
 */
public enum EnumGrupoCampoComparendo {

    VEHICULO(0), //
    INFRACTOR(1), //
    PROPIETARIO(2), //
    AGENTE_TRANSITO(3), //
    OBSERVACIONES(4), //
    INMOVILIZACION(5), //
    TESTIGO(6), //
    EMBRIAGUEZ(7), //
    EVIDENCIAS_FISICAS(8), //
    ENCABEZADO(10), //
    OTROS_CAMPOS(11) //
    ;

    /**
     * Identifica el codigo del campo en la base de datos.
     */
    int id;

    private EnumGrupoCampoComparendo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
