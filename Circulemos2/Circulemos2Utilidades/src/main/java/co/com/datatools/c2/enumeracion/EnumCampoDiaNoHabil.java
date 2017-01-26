package co.com.datatools.c2.enumeracion;

/**
 * Listado de campos asociados a la entidad Dia No Habil
 * 
 * @author luis.forero
 * 
 */
public enum EnumCampoDiaNoHabil {

    DIA_NO_HABIL(0), //
    FECHA(100), //
    OBSERVACION(101), //
    ;

    private int codigo;

    private EnumCampoDiaNoHabil(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
