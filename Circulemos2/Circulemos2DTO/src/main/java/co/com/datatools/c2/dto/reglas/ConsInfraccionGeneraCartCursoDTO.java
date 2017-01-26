package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

/**
 * Consulta si un tipo de infraccion genera automaticamente cartera de un comparendo cuando el comparendo tiene asociado un curso
 * 
 * @author felipe.martinez
 * 
 */
public class ConsInfraccionGeneraCartCursoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Parametro Entrada
     */
    private Integer codigoOrganismo;
    /**
     * Parametro Entrada nombre_tipo_infraccion+""+codigo_tipo_infraccion
     */
    private String codigoAlfaNumInfraccion;
    /**
     * Elemento de salida
     */
    private boolean genCarteraCuandoCurso;

    public ConsInfraccionGeneraCartCursoDTO() {
        super();
        setGenCarteraCuandoCurso(false);
    }

    public ConsInfraccionGeneraCartCursoDTO(Integer codigoOrganismo, String codigoAlfaNumInfraccion) {
        this();
        this.codigoOrganismo = codigoOrganismo;
        this.setCodigoAlfaNumInfraccion(codigoAlfaNumInfraccion);
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public boolean isGenCarteraCuandoCurso() {
        return genCarteraCuandoCurso;
    }

    public void setGenCarteraCuandoCurso(boolean genCarteraCuandoCurso) {
        this.genCarteraCuandoCurso = genCarteraCuandoCurso;
    }

    public String getCodigoAlfaNumInfraccion() {
        return codigoAlfaNumInfraccion;
    }

    public void setCodigoAlfaNumInfraccion(String codigoAlfaNumInfraccion) {
        this.codigoAlfaNumInfraccion = codigoAlfaNumInfraccion;
    }

}
