package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.FuncionarioDocumentoDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;

@Local
public interface ILSincronizacionDocumentos {

    /**
     * @see IRSincronizacionDocumentos#registrarAgente(FuncionarioDocumentoDTO)
     */
    public void registrarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException;

    /**
     * @see IRSincronizacionDocumentos#actualizarAgente(FuncionarioDocumentoDTO)
     */
    public void actualizarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException;

}
