package co.com.datatools.fachadainetegracionweb.enumeracion;

/**
 * Enumeracion que corresponde a los tipos de identifiacación de las personas. Su contenido debe corresponder al que se encuentra en la tabla
 * tipo_identificacion_persona
 * 
 * @author giovanni.velandia
 */
public enum EnumTipoIdentificacion {
    CEDULA_DE_CIUDADANIA(1, "CED"), //
    NUMERO_TRIBUTARIO(2, "RUC"), //
    CEDULA_EXTRANJERIA(5, "MIG"), //
    TARJETA_IDENTIDAD(4, "CRE"), //
    PASAPORTE(3, "PAS"), //
    REGISTRO_CIVIL(6, "TAM"), //
    NUIP(7, ""), //
    CARNET_DIPLOMATICO(8, ""), //
    TARJETA_EXTRANJERIA(9, "");

    /**
     * Contiene el id del documento
     */
    private final int id;

    // Contenido del codigo de tipo de identificacion
    private final String codigo;

    /**
     * Constructor con el id del tipo de identificaci�n y el indicador de si dicho tipo de identificaci�n es usado con los funcionarios.
     * 
     * @param id
     *            del tipo de identificaci�n
     */
    private EnumTipoIdentificacion(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    /**
     * Retorna el valor del pk del documento
     * 
     * @return id
     */
    public int getValor() {
        return this.id;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
