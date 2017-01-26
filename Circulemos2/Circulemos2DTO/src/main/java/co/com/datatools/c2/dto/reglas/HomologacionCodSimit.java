package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

/**
 * Obtiene codigos de simit homologados por circulemos 2
 * 
 * @author giovanny.rey
 */
public class HomologacionCodSimit implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoModalidadCir2;
    private String codigoNivelServicioCir2;
    private String codigoRadioAccionCir2;
    private int tipoIdentificacionCir2;
    private String idTipoIdentificacionCir2;
    private int codTipoInfractorCir2;
    private String codTipoServicioCir2;

    private String codigoModalidadSimit = "2";
    private String codigoNivelServicioSimit = "2";
    private String codigoRadioAccionSimit = "2";
    private int tipoIdentificacionSimit = 1;
    private String idTipoIdentificacionSimit = "1";
    private int codTipoInfractorSimit = 1;
    private String codTipoServicioSimit = "2";

    public HomologacionCodSimit() {
    }

    public HomologacionCodSimit(String codigoModalidadCir2, String codigoNivelServicioCir2,
            String codigoRadioAccionCir2, int tipoIdentificacionCir2, String idTipoIdentificacionCir2,
            int codTipoInfractorCir2, String codTipoServicioCir2) {
        this.codigoModalidadCir2 = codigoModalidadCir2;
        this.codigoNivelServicioCir2 = codigoNivelServicioCir2;
        this.codigoRadioAccionCir2 = codigoRadioAccionCir2;
        this.tipoIdentificacionCir2 = tipoIdentificacionCir2;
        this.idTipoIdentificacionCir2 = idTipoIdentificacionCir2;
        this.codTipoInfractorCir2 = codTipoInfractorCir2;
        this.codTipoServicioCir2 = codTipoServicioCir2;
    }

    public String getIdTipoIdentificacionCir2() {
        return idTipoIdentificacionCir2;
    }

    public void setIdTipoIdentificacionCir2(String idTipoIdentificacionCir2) {
        this.idTipoIdentificacionCir2 = idTipoIdentificacionCir2;
    }

    public String getIdTipoIdentificacionSimit() {
        return idTipoIdentificacionSimit;
    }

    public void setIdTipoIdentificacionSimit(String idTipoIdentificacionSimit) {
        this.idTipoIdentificacionSimit = idTipoIdentificacionSimit;
    }

    public String getCodigoModalidadCir2() {
        return codigoModalidadCir2;
    }

    public void setCodigoModalidadCir2(String codigoModalidadCir2) {
        this.codigoModalidadCir2 = codigoModalidadCir2;
    }

    public String getCodigoNivelServicioCir2() {
        return codigoNivelServicioCir2;
    }

    public void setCodigoNivelServicioCir2(String codigoNivelServicioCir2) {
        this.codigoNivelServicioCir2 = codigoNivelServicioCir2;
    }

    public String getCodigoRadioAccionCir2() {
        return codigoRadioAccionCir2;
    }

    public void setCodigoRadioAccionCir2(String codigoRadioAccionCir2) {
        this.codigoRadioAccionCir2 = codigoRadioAccionCir2;
    }

    public int getTipoIdentificacionCir2() {
        return tipoIdentificacionCir2;
    }

    public void setTipoIdentificacionCir2(int tipoIdentificacionCir2) {
        this.tipoIdentificacionCir2 = tipoIdentificacionCir2;
    }

    public int getCodTipoInfractorCir2() {
        return codTipoInfractorCir2;
    }

    public void setCodTipoInfractorCir2(int codTipoInfractorCir2) {
        this.codTipoInfractorCir2 = codTipoInfractorCir2;
    }

    public String getCodTipoServicioCir2() {
        return codTipoServicioCir2;
    }

    public void setCodTipoServicioCir2(String codTipoServicioCir2) {
        this.codTipoServicioCir2 = codTipoServicioCir2;
    }

    public String getCodigoModalidadSimit() {
        return codigoModalidadSimit;
    }

    public void setCodigoModalidadSimit(String codigoModalidadSimit) {
        this.codigoModalidadSimit = codigoModalidadSimit;
    }

    public String getCodigoNivelServicioSimit() {
        return codigoNivelServicioSimit;
    }

    public void setCodigoNivelServicioSimit(String codigoNivelServicioSimit) {
        this.codigoNivelServicioSimit = codigoNivelServicioSimit;
    }

    public String getCodigoRadioAccionSimit() {
        return codigoRadioAccionSimit;
    }

    public void setCodigoRadioAccionSimit(String codigoRadioAccionSimit) {
        this.codigoRadioAccionSimit = codigoRadioAccionSimit;
    }

    public int getTipoIdentificacionSimit() {
        return tipoIdentificacionSimit;
    }

    public void setTipoIdentificacionSimit(int tipoIdentificacionSimit) {
        this.tipoIdentificacionSimit = tipoIdentificacionSimit;
    }

    public int getCodTipoInfractorSimit() {
        return codTipoInfractorSimit;
    }

    public void setCodTipoInfractorSimit(int codTipoInfractorSimit) {
        this.codTipoInfractorSimit = codTipoInfractorSimit;
    }

    public String getCodTipoServicioSimit() {
        return codTipoServicioSimit;
    }

    public void setCodTipoServicioSimit(String codTipoServicioSimit) {
        this.codTipoServicioSimit = codTipoServicioSimit;
    }

}
