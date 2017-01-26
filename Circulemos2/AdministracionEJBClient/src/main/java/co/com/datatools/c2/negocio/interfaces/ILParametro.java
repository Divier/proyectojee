package co.com.datatools.c2.negocio.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ParametroDTO;
import co.com.datatools.c2.dto.ParametroOrganismoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;

@Local
public interface ILParametro {

    /**
     * @see IRParametro#consultarParametroOrganismo(ParametroDTO, Integer)
     */
    @Deprecated
    public List<ParametroOrganismoDTO> consultarParametroOrganismo(ParametroDTO parametroDTO, Integer codigoOrganismo);

    /**
     * @see IRParametro#existeParametroOrganismo(ParametroOrganismoDTO, Integer)
     */
    public boolean existeParametroOrganismo(ParametroOrganismoDTO paramOrg, Integer codigoOrganismo);

    /**
     * @see IRParametro#consultarParametroSistema(ParametroDTO)
     */
    List<ParametroDTO> consultarParametroSistema(ParametroDTO parametro);

    /**
     * @see IRParametro#consultarValorParametro(EnumParametro, int)
     */
    String consultarValorParametro(EnumParametro codigoParametro, int codigoOrganismo);

    /**
     * @see IRParametro#consultarParametro(ParametroDTO, int)
     */
    Map<Integer, List<ParametroDTO>> consultarParametro(ParametroDTO parametro, int codigoOrganismo);

    /**
     * @see IRParametro#consultarParametro(EnumParametro, int, boolean)
     */
    ValorParametroDTO consultarParametro(EnumParametro parametro, int codigoOrganismo, boolean requerido);

}
