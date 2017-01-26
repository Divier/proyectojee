package co.com.datatools.seguridad.ejb;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;

@RunWith(Arquillian.class)
public class ParametroEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(RolEJBTest.class.getName());

    @EJB
    IRParametrosSeguridad parametrosSeguridadEjb;

    @Test
    public void consultarParametroSeguridads() {
        logger.debug("ParametroEJBTest::consultarParametroSeguridad");
        List<ParametroSeguridadDto> resultado = parametrosSeguridadEjb.consultarParametroSeguridad("Prueba_Decimal");
        Assert.assertTrue(!resultado.isEmpty());
    }

    @Test
    public void consultarValorParametroSeguridad() {
        logger.debug("ParametroEJBTest::consultarValorParametroSeguridad");
        String resultado = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NIVELES_HERENCIA_ROLES);
        Assert.assertTrue(!resultado.equals(null));
    }

    @Test
    public void actualizarParametroSeguridad() {
        logger.debug("ParametroEJBTest::actualizarParametroSeguridad");
        ParametroSeguridadDto parametroDto = new ParametroSeguridadDto();
        parametroDto.setId(1003);
        parametroDto.setNombre("Prueba_Entero");
        parametroDto.setValor("4");
        parametrosSeguridadEjb.actualizarParametroSeguridad(parametroDto);
    }

}
