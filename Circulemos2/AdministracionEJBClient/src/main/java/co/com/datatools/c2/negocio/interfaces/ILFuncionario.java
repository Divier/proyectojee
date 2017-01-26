/**
 * 
 */
package co.com.datatools.c2.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;

/**
 * Servicios de funcionario
 * 
 * @author julio.pinzon
 *
 */
@Local
public interface ILFuncionario {

    /**
     * @see IRFuncionario#consultarFuncionarios(FuncionarioDTO, Date)
     */
    public List<FuncionarioDTO> consultarFuncionarios(FuncionarioDTO funcionario, Date fechaCorte);

}
