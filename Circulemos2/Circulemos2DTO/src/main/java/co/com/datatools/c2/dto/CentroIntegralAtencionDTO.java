package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class CentroIntegralAtencionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private Boolean reportaRecaudo;
    private BigDecimal porcentajeRecaudo;
    private Date fechaInicioOperacion;
    private Date fechaFinOperacion;
    private String cuentaRecaudadora;
    private PersonaJuridicaDTO personaJuridica;
    private List<SedesCentroIntegralAtencionDTO> sedeList;

    // --- Atributos de manejo de interfaz
    private boolean soloVigentes;

    // --- Constructor
    public CentroIntegralAtencionDTO() {
    }

    public CentroIntegralAtencionDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getReportaRecaudo() {
        return this.reportaRecaudo;
    }

    public void setReportaRecaudo(Boolean reportaRecaudo) {
        this.reportaRecaudo = reportaRecaudo;
    }

    public BigDecimal getPorcentajeRecaudo() {
        return this.porcentajeRecaudo;
    }

    public void setPorcentajeRecaudo(BigDecimal porcentajeRecaudo) {
        this.porcentajeRecaudo = porcentajeRecaudo;
    }

    public Date getFechaInicioOperacion() {
        return this.fechaInicioOperacion;
    }

    public void setFechaInicioOperacion(Date fechaInicioOperacion) {
        this.fechaInicioOperacion = fechaInicioOperacion;
    }

    public Date getFechaFinOperacion() {
        return this.fechaFinOperacion;
    }

    public void setFechaFinOperacion(Date fechaFinOperacion) {
        this.fechaFinOperacion = fechaFinOperacion;
    }

    public String getCuentaRecaudadora() {
        return this.cuentaRecaudadora;
    }

    public void setCuentaRecaudadora(String cuentaRecaudadora) {
        this.cuentaRecaudadora = cuentaRecaudadora;
    }

    public PersonaJuridicaDTO getPersonaJuridica() {
        return this.personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<SedesCentroIntegralAtencionDTO> getSedeList() {
        if (this.sedeList == null) {
            this.sedeList = new java.util.ArrayList<>(1);
        }
        return this.sedeList;
    }

    public void setSedeList(List<SedesCentroIntegralAtencionDTO> sedeList) {
        this.sedeList = sedeList;
    }

    public boolean isSoloVigentes() {
        return soloVigentes;
    }

    public void setSoloVigentes(boolean soloVigentes) {
        this.soloVigentes = soloVigentes;
    }

}