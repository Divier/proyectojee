package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.seguridad.BaseSeguridadTest;
import co.com.datatools.seguridad.dto.autorizacion.EdicionMenuDto;
import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.interfaces.IRMenu;

@RunWith(Arquillian.class)
public class MenuEJBTest extends BaseSeguridadTest {

    private final static Logger logger = Logger.getLogger(MenuEJBTest.class.getName());

    @EJB
    IRMenu menuEjb;

    @Test
    public void consultarMenu() {
        logger.debug("MenuEJBTest::consultarMenu");
        Assert.assertTrue(!menuEjb.consultarOpcionesMenu(1).isEmpty());

    }

    @Test
    public void registrarMenu() {
        logger.debug("MenuEJBTest::registrarMenu");
        List<MenuDto> opcionesMenuModificadas = new ArrayList<MenuDto>();
        // Consultar menu actual y modificarlo
        List<MenuDto> opcionesMenu = menuEjb.consultarOpcionesMenu(1);
        for (MenuDto menuDto : opcionesMenu.get(0).getSubmenu()) {
            if (menuDto.getIdMenu().equals(2)) {
                menuDto.setMenuPadre(null);
                menuDto.setModificado(true);
                opcionesMenuModificadas.add(menuDto);
            }
        }
        opcionesMenu.get(0).getSubmenu().clear();
        opcionesMenuModificadas.add(opcionesMenu.get(0));

        EdicionMenuDto opcionesMenuDto = new EdicionMenuDto();
        opcionesMenuDto.setOpcionesMenu(opcionesMenuModificadas);
        // Eliminar una opcion del menu actual
        List<MenuDto> opcionesMenuEliminadas = new ArrayList<MenuDto>();
        MenuDto opcionEliminada = new MenuDto();
        opcionEliminada.setIdMenu(3);
        opcionesMenuEliminadas.add(opcionEliminada);
        opcionesMenuDto.setOpcionesMenuEliminadas(opcionesMenuEliminadas);
        menuEjb.registrarOpcionesMenu(opcionesMenuDto);
    }

}
