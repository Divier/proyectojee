package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class TipoLogReporteDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private int codigoTipoLogReporte;
    private String nombreTipoLogReporte;
    private List<LogReporteDTO> listLogReportesDTO;

    // Constructors Declaration

    public TipoLogReporteDTO() {

    }

    // Start Methods Declaration

    public int getCodigoTipoLogReporte() {
        return codigoTipoLogReporte;
    }

    public void setCodigoTipoLogReporte(int codigoTipoLogReporte) {
        this.codigoTipoLogReporte = codigoTipoLogReporte;
    }

    public String getNombreTipoLogReporte() {
        return nombreTipoLogReporte;
    }

    public void setNombreTipoLogReporte(String nombreTipoLogReporte) {
        this.nombreTipoLogReporte = nombreTipoLogReporte;
    }

    public List<LogReporteDTO> getListLogReportesDTO() {
        return listLogReportesDTO;
    }

    public void setListLogReportesDTO(List<LogReporteDTO> listLogReportesDTO) {
        this.listLogReportesDTO = listLogReportesDTO;
    }

    // Finish the class
}