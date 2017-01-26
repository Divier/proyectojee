package co.com.datatools.fachadaintegracionweb.entidades;

import java.io.Serializable;

/**
 * Interfaz para categorizar las clases que son entidades JPA con la estructura de catalogo
 * <p>
 * Una entidad catalogo debe tener los siguientes campos como minimo
 * <ul>
 * <li>@Column(name = "id_<nombre_tabla>") --> Atr: private Integer id;</li>
 * <li>@Column(name = "nombre") --> Atr: private String nombre;</li>
 * <li>@Column(name = "sigla") --> Atr: private String sigla;</li>
 * <li>@Column(name = "estado") --> Atr: private Boolean activo;</li>
 * <li>@Column(name = "codigo") --> Atr: private String codigo;</li>
 * <li>@Column(name = "descripcion") --> Atr: private String descripcion;</li>
 * </ul>
 * </p>
 * 
 * @author felipe.martinez
 */
public interface EntidadCatalogoC2 extends Serializable {

    Integer getId();

    void setId(Integer id);

    String getNombre();

    void setNombre(String nombre);

    String getSigla();

    void setSigla(String sigla);

    Boolean getActivo();

    void setActivo(Boolean activo);

    String getCodigo();

    void setCodigo(String codigo);

    String getDescripcion();

    void setDescripcion(String descripcion);
}
