package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.dto.FirmaPersonaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILFirma {

    /**
     * @see IRFirma#registrarFirma(FirmaPersonaDTO)
     */
    public Long registrarFirma(CapturaFirmaDTO firmaDTO, boolean actPersona) throws CirculemosNegocioException;

    /**
     * @see IRFirma#consultarNumeroFirma(long idPersona)
     */
    public Long consultarNumeroFirma(long idPersona);

    /**
     * @see IRFirma#registrarFirmaFuncionario(CapturaFirmaDTO firmaDTO)
     */
    public String registrarFirmaFuncionario(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException;

    /**
     * @see IRFirma#registrarFirmaAgente(CapturaFirmaDTO firmaDTO)
     */
    public String registrarFirmaAgente(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException;

}
