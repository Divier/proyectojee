package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author robert.bautista
 * @since 2014-05-08
 */
public class ListaRespuestaEntradaDTO<T, Y extends Enum<Y>> extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = -4717774641930604112L;

    /**
     * Contiene el listado de errores
     */
    private List<RespuestaEntradaDTO<T, Y>> respuestas = null;

    /**
     * Constructor vacío
     */
    public ListaRespuestaEntradaDTO() {
        this.respuestas = new ArrayList<RespuestaEntradaDTO<T, Y>>();
    }

    /**
     * Indica si existen respuestas
     * 
     * @return true si la lista de respuestas es mayor a cero
     */
    public boolean hayRespuestas() {
        return (!this.respuestas.isEmpty());
    }

    /**
     * Retorna el listado de respuestas
     * 
     * @return the respuestas
     */
    public List<RespuestaEntradaDTO<T, Y>> getRespuestas() {
        return this.respuestas;
    }

    /**
     * Adiciona la respuesta indicada a la lista de respuestas
     * 
     * @param errorEntradaDto
     */
    public void addRespuestaEntradaDTO(RespuestaEntradaDTO<T, Y> errorEntradaDto) {
        this.respuestas.add(errorEntradaDto);
    }

    /**
     * Adiciona la respuesta entrada con el respectivo codigo y mensaje
     * 
     * @param codigo
     *            codigo de la respuesta a adicionar
     * 
     * @param mensaje
     *            mensaje asociado al codigo
     */
    public void addRespuestaEntradaDTO(T codigo, Y mensaje) {
        RespuestaEntradaDTO<T, Y> respuestaEntradaDTO = new RespuestaEntradaDTO<T, Y>(codigo, mensaje);
        this.respuestas.add(respuestaEntradaDTO);
    }

    /**
     * Vacía el listado de respuestas
     */
    public void limpieRespuestas() {
        this.respuestas.clear();
    }

    /**
     * Indica si no hay errores
     * 
     * @return true si no hay errores
     */
    public boolean esVacia() {
        return this.getRespuestas().isEmpty();
    }

}
