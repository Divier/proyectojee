package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoReporteDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int idTipoReporte;
    private int codigoTipoReporte;
    private byte generaLog;
    private String nombreReporte;
    private List<LogReporteDTO> listLogReportesDTO;
    private List<TextoPredefinidoReporteDTO> listTextoPredefinidoReportesDTO;
    private OrganismoTransitoDTO organismoTransitoDTO;

    // Constructors Declaration

    public TipoReporteDTO() {

    }

    // Start Methods Declaration

    public int getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(int idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public int getCodigoTipoReporte() {
        return codigoTipoReporte;
    }

    public void setCodigoTipoReporte(int codigoTipoReporte) {
        this.codigoTipoReporte = codigoTipoReporte;
    }

    public byte getGeneraLog() {
        return generaLog;
    }

    public void setGeneraLog(byte generaLog) {
        this.generaLog = generaLog;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public List<LogReporteDTO> getListLogReportesDTO() {
        return listLogReportesDTO;
    }

    public void setListLogReportesDTO(List<LogReporteDTO> listLogReportesDTO) {
        this.listLogReportesDTO = listLogReportesDTO;
    }

    public List<TextoPredefinidoReporteDTO> getListTextoPredefinidoReportesDTO() {
        return listTextoPredefinidoReportesDTO;
    }

    public void setListTextoPredefinidoReportesDTO(List<TextoPredefinidoReporteDTO> listTextoPredefinidoReportesDTO) {
        this.listTextoPredefinidoReportesDTO = listTextoPredefinidoReportesDTO;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    // Finish the class
}