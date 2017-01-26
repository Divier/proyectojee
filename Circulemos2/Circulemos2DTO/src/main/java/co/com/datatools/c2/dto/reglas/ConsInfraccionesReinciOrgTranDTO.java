package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dto para regla q consulta el listado de codigos alfa numericos de las infraccion que aplican para reincidencia de un organismo de transito en una
 * fecha especifica
 * 
 * @author felipe.martinez
 */
public class ConsInfraccionesReinciOrgTranDTO implements Serializable {

    private static final long serialVersionUID = 2538428259180652064L;

    /**
     * Parametro Entrada
     */
    private Integer codigoOrganismo;

    /**
     * Parametro Entrada
     */
    private Date inicioVigencia;

    /**
     * Parametro Entrada
     */
    private Date finVigencia;

    /**
     * Elemento de salida de drools listado de tuplas [nombre_tipo_infraccion+""+codigo_tipo_infraccion]
     */
    private List<String> codigoAlfaNumInfraccion;

    public ConsInfraccionesReinciOrgTranDTO() {
        super();
        codigoAlfaNumInfraccion = new ArrayList<>();
    }

    public ConsInfraccionesReinciOrgTranDTO(Integer codigoOrganismo, Date vigente) {
        this();
        this.codigoOrganismo = codigoOrganismo;
        this.setInicioVigencia(vigente);
        this.setFinVigencia(vigente);
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<String> getCodigoAlfaNumInfraccion() {
        return codigoAlfaNumInfraccion;
    }

    public void setCodigoAlfaNumInfraccion(List<String> codigoAlfaNumInfraccion) {
        this.codigoAlfaNumInfraccion = codigoAlfaNumInfraccion;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

}
