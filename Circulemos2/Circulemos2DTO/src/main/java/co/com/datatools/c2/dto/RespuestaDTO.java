package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;

/**
 * Clase generica q respresenta la informacion de respuesta de un proceso
 * 
 * @author felipe.martinez
 * @param <T>
 *            Enumeracion de los campos de una entidad q generaron error.
 * @param <Y>
 *            Enumeracion de los multiples tipos de error asociados al campo de la entidad
 */
public class RespuestaDTO<T extends Enum<T>, Y extends Enum<Y>> extends AbstractDTO {

    private static final long serialVersionUID = -884294162376445635L;
    /**
     * Descripcion adicional de la respuesta
     */
    private String descripcionRespuesta = null;
    /**
     * Mensaje principal de la respuesta
     */
    private String nombreRespuesta = null;
    /**
     * Estado Global de la respuesta
     */
    private EnumRespuestaSistema respuesta = null;

    private Y errorRespuesta;

    /**
     * Mapa q almacena una lista de errores asociados a un campo
     */
    private Map<T, List<Y>> erroresCampo = new HashMap<T, List<Y>>(0, 1.0f);

    public RespuestaDTO() {
        super();
    }

    /**
     * Constructor alterno que inicializa el atributo {@link #respuesta}
     * 
     * @param estado
     *            indica el valor para inicializar {@link #respuesta},
     *            <ul>
     *            <li>true: {@link EnumRespuestaSistema#OK}</li>
     *            <li>false: {@link EnumRespuestaSistema#ERRORES}</li>
     *            </ul>
     */
    public RespuestaDTO(boolean estado) {
        if (estado) {
            setRespuesta(EnumRespuestaSistema.OK);
        } else {
            setRespuesta(EnumRespuestaSistema.ERRORES);
        }
    }

    /**
     * Crea el respuesta de dto y agrega un <b>errorCampo</b> para <b>campo</b>
     * 
     * @param campo
     *            campo al q se agrega el primer error
     * @param errorCampo
     *            error del campo indicado
     */
    public RespuestaDTO(T campo, Y errorCampo) {
        this(false);
        addErrorCampo(campo, errorCampo);
    }

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public String getNombreRespuesta() {
        return nombreRespuesta;
    }

    public void setNombreRespuesta(String nombreRespuesta) {
        this.nombreRespuesta = nombreRespuesta;
    }

    public EnumRespuestaSistema getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(EnumRespuestaSistema respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Agrega un error a un campo
     * 
     * @param campo
     *            variable q indica el campo al q se agrega el error
     * @param errorCampo
     *            error asociado al campo
     */
    public void addErrorCampo(T campo, Y errorCampo) {
        List<Y> list = erroresCampo.get(campo);
        if (list == null) {
            list = new ArrayList<>(1);
            erroresCampo.put(campo, list);
        }
        list.add(errorCampo);
    }

    /**
     * Permite obtener la variable que almacena la relacion entre campos y errores. Usar metodos {@link Map#keySet()} o {@link Map#entrySet()} para
     * recorrer los errores almacenados
     * 
     * @return mapa de errores por campo
     */
    public Map<T, List<Y>> getErroresCampo() {
        return erroresCampo;
    }

    public void setErroresCampo(Map<T, List<Y>> erroresCampo) {
        this.erroresCampo = erroresCampo;
    }

    public Y getErrorRespuesta() {
        return errorRespuesta;
    }

    public void setErrorRespuesta(Y errorRespuesta) {
        this.errorRespuesta = errorRespuesta;
    }

}
