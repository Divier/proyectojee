package co.com.datatools.seguridad.dto.comun;

import java.io.Serializable;

/**
 * Clase utilizada para encapsular un objeto que contiene un par de atributos: llave(id) y valor
 * 
 * @author claudia.rodriguez
 * 
 */
public class LlaveValorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String valor;

    public LlaveValorDto() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor con los atributos id y valor
     * 
     * @param id
     *            LLave o identificador
     * @param valor
     *            Valor correspondiente a la llave enviada
     */
    public LlaveValorDto(String id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
