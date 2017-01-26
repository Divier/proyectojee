package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Representa las evidencias del comparendo
 * 
 * @author luis.forero (2015-11-06)
 * 
 */
public class EvidenciaWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
    private String codigoTipoEvidencia;
    private String nombre;
    private byte[] archivo;

    public String getCodigoTipoEvidencia() {
        return codigoTipoEvidencia;
    }

    public void setCodigoTipoEvidencia(String codigoTipoEvidencia) {
        this.codigoTipoEvidencia = codigoTipoEvidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

}