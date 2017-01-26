package co.com.datatools.c2.negocio.ejb.parametrizacion;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILCatalogo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILFachadaConfiguracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.ILHomologacion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRFachadaConfiguracion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.Mapeable;

/**
 * 
 * @author giovanni.velandia 2015-10-06
 * 
 */
@Stateless(name = "FachadaConfiguracionEJB")
@LocalBean
public class FachadaConfiguracionEJB implements IRFachadaConfiguracion, ILFachadaConfiguracion {

    @EJB
    private IRConfiguracion iRConfiguracion;

    @EJB
    private ILHomologacion iLHomologacion;

    @EJB
    private ILCatalogo catalogoEJB;

    @Override
    public <T> T consultarValorConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException {
        return iRConfiguracion.consultarDatoConfiguracion(codigoTipoConfiguracion, dto);
    }

    @Override
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable) {
        return iLHomologacion.obtenerValor(origenHomologacion, valorHomologable);
    }

    @Override
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo) {
        return catalogoEJB.consultarItemsCatalogo(entidadCatalogo, itemCatalogo);
    }

}
