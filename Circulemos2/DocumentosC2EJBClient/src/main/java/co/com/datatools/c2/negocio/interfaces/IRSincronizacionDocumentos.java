package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.FuncionarioDocumentoDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;

/**
 * Interface que ofrece los servicios de comunicación con el módulo de documentos.
 * 
 * @author julio.pinzon
 * 
 */
@Remote
public interface IRSincronizacionDocumentos {
    /**
     * Metodo que crea agentes en Documentos
     * 
     * @param funcionarioDocumento
     * @throws CirculemosAlertaException
     */
    public void registrarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException;

    /**
     * Metodo que actualiza agentes en Documentos
     * 
     * @param funcionarioDocumento
     * @throws CirculemosAlertaException
     */
    public void actualizarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException;

}
