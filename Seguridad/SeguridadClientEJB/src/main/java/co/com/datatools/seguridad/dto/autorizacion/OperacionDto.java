package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;

/**
 * Informacion de una operacion del sistema
 * 
 * @author Felipe Martinez
 * 
 */
public class OperacionDto implements Serializable, Comparable<OperacionDto> {

    private static final long serialVersionUID = 1L;

    private Integer idOperacion;

    private String nombreOperacion;

    /**
     * Constructor sin argumentos, invoca a super
     */
    public OperacionDto() {
        super();
    }

    /**
     * Constructor que invoca a super y asigna los valores enviados como parametro a los atributos id y nombreOperacion
     * 
     * @param id
     *            valor para el atributo id
     * @param nombreOperacion
     *            valor para el atributo nombreOperacion
     */
    public OperacionDto(Integer id, String nombreOperacion) {
        super();
        this.idOperacion = id;
        this.nombreOperacion = nombreOperacion;
    }

    /**
     * Constructor que invoca a this y asigna el valor enviado como parametro al atributo nombreOperacion
     * 
     * @param nombreOperacion
     *            NOmbre a asignar para la operacion
     */
    public OperacionDto(String nombreOperacion) {
        this();
        this.nombreOperacion = nombreOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((nombreOperacion == null) ? 0 : nombreOperacion.toLowerCase().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OperacionDto other = (OperacionDto) obj;
        if (nombreOperacion == null) {
            if (other.nombreOperacion != null) {
                return false;
            }
        } else if (!nombreOperacion.equalsIgnoreCase(other.nombreOperacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OperacionDto {nombreOperacion=" + nombreOperacion + "}";
    }

    @Override
    public int compareTo(OperacionDto o) {
        return this.getNombreOperacion().compareToIgnoreCase(o.getNombreOperacion());
    }

}
