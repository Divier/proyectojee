package co.com.datatools.c2.managed_bean.comparendo.notificacion_correo;

import java.io.Serializable;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * CU_CIR20_DAT_COM_054
 * 
 * @author rodrigo.cruz
 */
public class CargueAcuseReciboHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean mostrarGenerarArchivo;
    private boolean mostrarDescargarArchivo;
    private int codigoOrganismo;
    private ArchivoTransportableDTO archivo;

    public CargueAcuseReciboHolderFL() {
        archivo = new ArchivoTransportableDTO();
        mostrarGenerarArchivo = false;
    }

    public boolean isMostrarGenerarArchivo() {
        return mostrarGenerarArchivo;
    }

    public void setMostrarGenerarArchivo(boolean mostrarGenerarArchivo) {
        this.mostrarGenerarArchivo = mostrarGenerarArchivo;
    }

    public boolean isMostrarDescargarArchivo() {
        return mostrarDescargarArchivo;
    }

    public void setMostrarDescargarArchivo(boolean mostrarDescargarArchivo) {
        this.mostrarDescargarArchivo = mostrarDescargarArchivo;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public ArchivoTransportableDTO getArchivo() {
        return archivo;
    }

    public void setArchivo(ArchivoTransportableDTO archivo) {
        this.archivo = archivo;
    }

}