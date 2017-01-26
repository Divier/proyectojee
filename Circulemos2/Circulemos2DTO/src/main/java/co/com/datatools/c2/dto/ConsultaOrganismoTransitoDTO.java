package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * Data Transfer Object utilizado para los datos de filtro posibles para la consulta:
 * IAdministracion.consultarOrganismoTransito(ConsultaOrganismoTransitoDTO).
 * 
 * @author luis.forero
 * 
 */
public class ConsultaOrganismoTransitoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Nombre del organismo de transito
     */
    private String nombreOrganismo;
    /**
     * Codigo del organismo de transito
     */
    private Integer codigoOrganismo;
    /**
     * Codigo RUNT
     */
    private String codigoRunt;
    /**
     * Codigo Ministerio
     */
    private String codigoMinisterio;
    /**
     * Nit del organismo de transito junto con el digito de verificacion concatenado
     */
    private String nitOrganismo;
    /**
     * Identificador unico del pais
     */
    private Integer idPais;
    /**
     * Estado del organismo
     */
    private Boolean estadoOrganismo;// 1 activo, 0 inactivo

    public ConsultaOrganismoTransitoDTO() {
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoRunt() {
        return codigoRunt;
    }

    public void setCodigoRunt(String codigoRunt) {
        this.codigoRunt = codigoRunt;
    }

    public String getCodigoMinisterio() {
        return codigoMinisterio;
    }

    public void setCodigoMinisterio(String codigoMinisterio) {
        this.codigoMinisterio = codigoMinisterio;
    }

    public String getNitOrganismo() {
        return nitOrganismo;
    }

    public void setNitOrganismo(String nitOrganismo) {
        this.nitOrganismo = nitOrganismo;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Boolean getEstadoOrganismo() {
        return estadoOrganismo;
    }

    public void setEstadoOrganismo(Boolean estadoOrganismo) {
        this.estadoOrganismo = estadoOrganismo;
    }

}
