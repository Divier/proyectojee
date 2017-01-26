package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ConfiguracionEmailDTO;
import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILConfiguracionEmail {
    /**
     * @see IRConfiguracionEmail#consultarConfiguracionEmail(Boolean)
     */
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(Boolean estadoConfiguracion);

    /**
     * @see IRConfiguracionEmail#consultarConfiguracionEmail(int, EnumTipoCorreo, Boolean)
     */
    public List<ConfiguracionEmailDTO> consultarConfiguracionEmail(int codigoOrganismo, EnumTipoCorreo enumTipoCorreo,
            Boolean estadoConfiguracion);

    /**
     * @see IRConfiguracionEmail#consultarTiposEmail(int)
     */
    public List<TipoEmailDTO> consultarTiposEmail(int codigoOrganismo);

    /**
     * @see IRConfiguracionEmail#eliminarConfiguracionEmail(ConfiguracionEmailDTO)
     */
    public void eliminarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRConfiguracionEmail#modificarConfiguracionEmail(ConfiguracionEmailDTO)
     */
    public void modificarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRConfiguracionEmail#registrarConfiguracionEmail(ConfiguracionEmailDTO)
     */
    public int registrarConfiguracionEmail(ConfiguracionEmailDTO configuracionEmailDTO)
            throws CirculemosNegocioException;

    /**
     * @see IRConfiguracionEmail#consultarEmails(String,int)
     */
    public List<CorreoEnvioDTO> consultarEmails(String email, int codOrganismo);

    /**
     * @see IRConfiguracionEmail#consultarEmailsConfigurados(int,Integer)
     */
    public CorreoEnvioDTO consultarEmailsConfigurados(int codigoOrganismo, Integer idConfiguracion);
}
