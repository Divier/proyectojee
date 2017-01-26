package co.com.datatools.c2.dto.ubicabilidad;

import java.io.Serializable;
import java.util.Map;

import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * Objeto que permite manipular objetos para el flujo analisis ubicabilidad CU_CIR20_DAT_UBI_001
 * 
 * @author ricardo.chavarro
 * 
 */
public class CargueMasivoUbicabilidadDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTipoFuenteInformacion;
    private Integer idTipoCargueArchivo;
    private ArchivoTransportableDTO archivoCSVCargue;
    // Cache de tipos de identificacion
    private Map<String, Integer> codigosTipoId;
    // Cache de tipos de ubicabilidad
    private Map<String, Integer> codigosTipoDir;
    // Cache de provincias
    private Map<String, Integer> codigosProvincia;
    // Cache de canton
    private Map<String, MunicipioDTO> codigosCanton;
    // Cache de parroquia
    private Map<String, LocalidadDTO> codigosParroquia;
    // Cache de tipos de telefono
    private Map<String, Integer> codigosTipoTel;
    // Cache de errores ubicabilidad
    private Map<Integer, String> erroresCargueUbicabilidad;

    public CargueMasivoUbicabilidadDTO() {
        super();
    }

    public void setArchivoCSVCargue(ArchivoTransportableDTO archivo) {
        this.archivoCSVCargue = archivo;
    }

    public ArchivoTransportableDTO getArchivoCSVCargue() {
        return archivoCSVCargue;
    }

    public Integer getIdTipoFuenteInformacion() {
        return idTipoFuenteInformacion;
    }

    public void setIdTipoFuenteInformacion(Integer idTipoFuenteInformacion) {
        this.idTipoFuenteInformacion = idTipoFuenteInformacion;
    }

    public Integer getIdTipoCargueArchivo() {
        return idTipoCargueArchivo;
    }

    public void setIdTipoCargueArchivo(Integer idTipoCargueArchivo) {
        this.idTipoCargueArchivo = idTipoCargueArchivo;
    }

    public Map<String, Integer> getCodigosTipoId() {
        return codigosTipoId;
    }

    public void setCodigosTipoId(Map<String, Integer> codigosTipoId) {
        this.codigosTipoId = codigosTipoId;
    }

    public Map<String, Integer> getCodigosTipoDir() {
        return codigosTipoDir;
    }

    public void setCodigosTipoDir(Map<String, Integer> codigosTipoDir) {
        this.codigosTipoDir = codigosTipoDir;
    }

    public Map<String, Integer> getCodigosProvincia() {
        return codigosProvincia;
    }

    public void setCodigosProvincia(Map<String, Integer> codigosProvincia) {
        this.codigosProvincia = codigosProvincia;
    }

    public Map<String, MunicipioDTO> getCodigosCanton() {
        return codigosCanton;
    }

    public void setCodigosCanton(Map<String, MunicipioDTO> codigosCanton) {
        this.codigosCanton = codigosCanton;
    }

    public Map<String, LocalidadDTO> getCodigosParroquia() {
        return codigosParroquia;
    }

    public void setCodigosParroquia(Map<String, LocalidadDTO> codigosParroquia) {
        this.codigosParroquia = codigosParroquia;
    }

    public Map<String, Integer> getCodigosTipoTel() {
        return codigosTipoTel;
    }

    public void setCodigosTipoTel(Map<String, Integer> codigosTipoTel) {
        this.codigosTipoTel = codigosTipoTel;
    }

    public Map<Integer, String> getErroresCargueUbicabilidad() {
        return erroresCargueUbicabilidad;
    }

    public void setErroresCargueUbicabilidad(Map<Integer, String> erroresCargueUbicabilidad) {
        this.erroresCargueUbicabilidad = erroresCargueUbicabilidad;
    }

}
