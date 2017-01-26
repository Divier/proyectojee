/**
 * 
 */
package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;

/**
 * Servicios de funcionario
 * 
 * @author julio.pinzon
 *
 */
@Remote
public interface IRFuncionario {

    /**
     * Consultar funcionario vigente en la fecha de corte por numero y tipo de identificacion de la persona asociada
     * 
     * @param funcionario
     * @param fechaCorte
     * @return Lista de FuncionarioDTO que cumplen con los filtros enviados
     * @author julio.pinzon 2016-10-03
     */
    public List<FuncionarioDTO> consultarFuncionarios(FuncionarioDTO funcionario, Date fechaCorte);

}
