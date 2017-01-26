package co.com.datatools.c2.enumeraciones;

/**
 * Contiene los códigos del estado de comparendo que se deben enviar a SIMIT
 * 
 * @author diego.fonseca
 * 
 */
public enum EnumCodigoEstadoComparendoSimit {

    PENDIENTE(1, 1), //
    PAGADO(2, 2), //
    CESE(3, 3), //
    RESOLUCION(4, 4), //
    PENDIENTE_DE_FALLO(5, 5), //
    ACUERDO_DE_PAGO(6, 6), //
    EXONERADO(7, 7), //
    ACTUALIZACION_COMPARENDO(8, 13), //
    ;
    private Integer id;
    private Integer codigo;

    EnumCodigoEstadoComparendoSimit(Integer id, Integer codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

}
