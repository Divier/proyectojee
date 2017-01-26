package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los tipos de agente impositor existentes.
 * 
 * @author luis.forero(2016-05-06)
 * 
 */
public enum EnumTipoAgenteImpositor implements SearchableEnumeration<Integer> {
    // Revisar el codigo 01 corresponde para colombia como Polca si se habilita este campo en ecuador genera error
    // POLCA(1, "01"), //
    // Este id 6 es temporal hasta que se revise el tema de los enum
    POLCA(6, "06"), //
    PONAL(2, "02"), //
    SIETT(3, "03"), //
    MUNICIPAL(4, "04"), //
    NACIONAL(5, "05"), //
    ACT(1, "01") // Ecuador
    ;
    private Integer id;
    private String codigo;

    private EnumTipoAgenteImpositor(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
