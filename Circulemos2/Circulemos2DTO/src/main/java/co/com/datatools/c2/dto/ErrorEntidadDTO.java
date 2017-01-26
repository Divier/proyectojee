package co.com.datatools.c2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO generico q almacena la informacion de error de un proceso
 * 
 * @author felipe.martinez
 * 
 * @param <T>
 *            Enumeracion de los campos de una entidad q generaron error. Cuando este objeto se utiliza para almacenar errores no relacionados con
 *            entidades, este tipo define los posible valores del error principal ocurrido en un metodo {@link #enumCampoEntidad}
 * @param <Y>
 *            Enumeracion de los multiples tipos de error asociados al campo de la entidad {@link #listaEnumsErrores}
 */
public class ErrorEntidadDTO<T extends Enum<T>, Y extends Enum<Y>> extends AbstractDTO {

    private static final long serialVersionUID = 1266149059903745368L;

    /**
     * Definicion del error principal
     */
    private T enumCampoEntidad;

    /**
     * Definicion de los errores adicionales asociados a {@link #enumCampoEntidad}
     */
    private List<Y> listaEnumsErrores;

    public ErrorEntidadDTO() {
        super();
    }

    public ErrorEntidadDTO(T enumCampoEntidad) {
        this.enumCampoEntidad = enumCampoEntidad;
    }

    public T getEnumCampoEntidad() {
        return enumCampoEntidad;
    }

    public void setEnumCampoEntidad(T enumCampoEntidad) {
        this.enumCampoEntidad = enumCampoEntidad;
    }

    public List<Y> getListaEnumsErrores() {
        if (listaEnumsErrores == null) {
            listaEnumsErrores = new ArrayList<>();
        }
        return listaEnumsErrores;
    }

    public void addListaEnumsErrores(Y enumError) {
        getListaEnumsErrores().add(enumError);
    }
}
