package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * @author robert.bautista
 * @since 2014-05-08
 */
public class RespuestaEntradaDTO<T, Y extends Enum<Y>> extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Contiene el codigo o llave de la respuesta
     */
    private T codigo;

    /**
     * Contiene el mensaje de enumeracion asociado al codigo o llave
     */
    private Y errorEntrada;

    /**
     * Constructor vacio
     */
    public RespuestaEntradaDTO() {
        super();
    }

    /**
     * Constructor con codigo y error
     * 
     * @param codigo
     *            codigo o llave del error
     * 
     * @param errorEntrada
     *            error encontrado
     */
    public RespuestaEntradaDTO(T codigo, Y errorEntrada) {
        super();
        this.codigo = codigo;
        this.errorEntrada = errorEntrada;
    }

    public T getCodigo() {
        return this.codigo;
    }

    public void setCodigo(T codigo) {
        this.codigo = codigo;
    }

    public Y getErrorEntrada() {
        return this.errorEntrada;
    }

    public void setErrorEntrada(Y errorEntrada) {
        this.errorEntrada = errorEntrada;
    }

}
