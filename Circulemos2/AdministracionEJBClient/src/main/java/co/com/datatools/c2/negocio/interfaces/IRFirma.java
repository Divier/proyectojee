package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRFirma {

    /**
     * Registra la firma de la persona
     * 
     * @param firma
     *            FirmaDTO
     * 
     * @return Respuesta del proceso de registro de firma
     * @author oscar.leon
     */
    public Long registrarFirma(CapturaFirmaDTO firmaDTO, boolean actPersona) throws CirculemosNegocioException;

    /**
     * Consultar la firma de la persona
     * 
     * @param Id
     *            persona idPersona
     * 
     * @return DTO con información de la firma de la persona
     * @author oscar.leon
     */
    public Long consultarNumeroFirma(long idPersona);

    /**
     * Registra la firma en el gestor para funcionarios
     * 
     * @param firmaDTO
     * @return
     * @throws CirculemosNegocioException
     */
    public String registrarFirmaFuncionario(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException;

    /**
     * Registra la firma en el gestor para agentes
     * 
     * @param firmaDTO
     * @return
     * @throws CirculemosNegocioException
     */
    public String registrarFirmaAgente(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException;
}
