package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.util.ArchivoTransportableDTO;

/**
 * CU_CIR20_DAT_COM_054: DTO utilitario
 * 
 * @author rodrigo.cruz
 * 
 */
public class ResultadoCargueArchivoNotificacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int cantidadRegistros;
    private int comparendosInvalidos;
    private ArchivoTransportableDTO archivoRespuesta;

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public int getComparendosInvalidos() {
        return comparendosInvalidos;
    }

    public void setComparendosInvalidos(int comparendosInvalidos) {
        this.comparendosInvalidos = comparendosInvalidos;
    }

    public ArchivoTransportableDTO getArchivoRespuesta() {
        return archivoRespuesta;
    }

    public void setArchivoRespuesta(ArchivoTransportableDTO archivoRespuesta) {
        this.archivoRespuesta = archivoRespuesta;
    }

}
