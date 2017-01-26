package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Catalogo tipo inconsistencia curso <br>
 * CU_CIR20_DAT_CAR_015 :: Aplicar curso pedagogico
 * 
 * @author rodrigo.cruz
 */
public enum EnumTipoInconsisCurso implements SearchableEnumeration<String> {

    CP_APLICADO_EXITO(1, "CAR_015001", "El curso fue aplicado exitosamente"), //
    CP_NO_EXISTE(2, "CAR_015002", "No existe curso pedagógico para los datos enviados"), //
    CP_YA_APLICADO(3, "CAR_015003", "El curso pedagógico ya fue aplicado"), //
    CP_NO_OBLIG_ASOCIADAS(4, "CAR_015004", "No existe obligaciones asociadas dentro de las fechas permitidas"), //
    CP_NO_EXISTE_NUM_OBLIG(5, "CAR_015005", "El número de la obligación no se encuentra registrada en el sistema"), //
    CP_NUM_OBLIG_ANULADA(6, "CAR_015006", "El número de obligación se encuentra anulada"), //
    CP_NO_TERMINOS_AMNISTIA(7, "CAR_015007", "El curso no se encuentra dentro de los términos de amnistía"), //
    CP_TIPO_NUM_DOC_DIFERENTE_INFRACTOR(8, "CAR_015008", "El tipo y número de documento no es igual al del infractor"), //
    CP_PAGO_INCOMPLETO_APLICACION(9, "CAR_015009",
            "No se ha registrado el pago completo para la aplicación del curso pedagógico"), //
    CP_ENTIDAD_NO_APTA(10, "CAR_015010", "La entidad reportadora del curso pedagógico no es apta"), //
    CP_TIPO_NUM_DOC_DIFERENTE_CARTERA(11, "CAR_015011",
            "El tipo y número de documento no es igual a registrado en cartera"), //
    CP_NUM_OBLIG_NO_BENEFICIO(12, "CAR_015012", "El número de obligación no tiene beneficio"), //
    ;

    private int codigo;
    private String codigoError;
    private String nombre;

    private EnumTipoInconsisCurso(int codigo, String codigoError, String nombre) {
        this.codigo = codigo;
        this.codigoError = codigoError;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getValue() {
        return codigoError;
    }

}
