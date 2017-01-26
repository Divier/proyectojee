package co.com.datatools.c2.enumeracion;

/**
 * Enumeración que corresponde a los tipos de identifiacación de las personas. Su contenido debe corresponder al que se encuentra en la tabla
 * tipo_identificacion_persona
 * 
 * @author robert.bautista
 */
public enum EnumTipoIdentificacion {
    CEDULA_DE_CIUDADANIA(1, true, true, true), //
    NUMERO_TRIBUTARIO(2, false, true, false), //
    CEDULA_EXTRANJERIA(3, true, true, true), //
    TARJETA_IDENTIDAD(4, true, true, false), //
    PASAPORTE(3, true, true, true), //
    REGISTRO_CIVIL(6, false, false, false), //
    NUIP(7, false, false, false), //
    CARNET_DIPLOMATICO(8, false, false, false), //
    TARJETA_EXTRANJERIA(9, true, true, true);

    /**
     * Contiene el pk del documento
     */
    private final int pk;

    /**
     * Indica si el documento puede ser registrado para una persona
     */
    private boolean dePersona;

    /**
     * Indica si el documento puede ser registrado para un representante legal
     */
    private boolean deRepresentanteLegal;

    /**
     * Indica si el documento puede ser de un funcionario
     */
    private boolean deFuncionario;

    /**
     * Constructor con el pk del tipo de identificación y el indicador de si dicho tipo de identificación es usado con los funcionarios.
     * 
     * @param pk
     *            pk del tipo de identificación
     * 
     * @param deFuncionario
     *            indica si la puede usar un funcionario.
     * @param dePersona
     *            indica si el documento puede ser usado en el registro de personas
     * @param deRepresentanteLegal
     *            indica si el documento puede ser usado para un representante legal
     */
    private EnumTipoIdentificacion(int pk, boolean deFuncionario, boolean dePersona, boolean deRepresentanteLegal) {
        this.pk = pk;
        this.deFuncionario = deFuncionario;
        this.dePersona = dePersona;
        this.deRepresentanteLegal = deRepresentanteLegal;
    }

    /**
     * Retorna el valor del pk del documento
     * 
     * @return pk
     */
    public int getValor() {
        return this.pk;
    }

    public boolean isDeFuncionario() {
        return this.deFuncionario;
    }

    public boolean isDePersona() {
        return this.dePersona;
    }

    public boolean isDeRepresentanteLegal() {
        return this.deRepresentanteLegal;
    }

}
