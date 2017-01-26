package co.com.datatools.c2.enumeracion;

/**
 * Enumeracion para los registros de la tabla metodos_web_service
 * 
 * @author felipe.martinez
 */
public enum EnumMetodoWebService {
    RECEPCION_COMPARENDO(1), //
    RECIBIR_CAMBIO_ESTADO_COMP(2), //
    ENVIAR_PAGO(3); //
    private int codigo;

    private EnumMetodoWebService(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
