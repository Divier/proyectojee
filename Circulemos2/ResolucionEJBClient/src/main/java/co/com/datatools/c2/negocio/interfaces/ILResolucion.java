package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ResolucionDTO;

@Local
public interface ILResolucion {

    /**
     * @see IRResolucion#actualizarResolucion(ResolucionDTO)
     */
    public void actualizarResolucion(ResolucionDTO resolucion);

    /**
     * @see IRResolucion#consultarResolucion(ResolucionDTO)
     */
    public List<ResolucionDTO> consultarResolucion(ResolucionDTO resolucion);

    /**
     * @see IRResolucion#generarResolucion(Resoluble)
     */
    public long generarResolucion(Resoluble resoluble);

    /**
     * Genera el documento de resolucion
     * 
     * @param resolucionDTO
     * @param resoluble
     * @return retorna la resolucion con los datos de acuerdo a la generacion del documento
     * @author julio.pinzon 17/03/2016
     */
    public ResolucionDTO generaDocumentoResolucion(ResolucionDTO resolucionDTO, Resoluble resoluble);
}
