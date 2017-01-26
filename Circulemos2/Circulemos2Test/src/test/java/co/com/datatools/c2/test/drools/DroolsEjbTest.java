package co.com.datatools.c2.test.drools;

import static org.junit.Assert.assertTrue;

import javax.ejb.EJBException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.reglas.ConsConcCreaCartCompOrgaTranDTO;
import co.com.datatools.c2.excepciones.ReglaNegocioInvalidaException;
import co.com.datatools.c2.test.BaseTest;

/**
 * Prueba de acceso a drools con servicio EJB
 * 
 * @author felipe.martinez
 */
@RunWith(Arquillian.class)
@Ignore
public class DroolsEjbTest extends BaseTest {

    // @EJB
    // ILReglaNegocio reglaNegocioEjb;

    /**
     * Valida que consuma la regla correctamente
     */
    @Test
    public void test1() {
        ConsConcCreaCartCompOrgaTranDTO dtoRegla = new ConsConcCreaCartCompOrgaTranDTO();
        dtoRegla.setCodigoOrganismo(11001);
        // dtoRegla = (ConsConcCreaCartCompOrgaTranDTO) reglaNegocioEjb.consumirRegla(dtoRegla);
        assertTrue(dtoRegla.getCodigoConcepto() != null);
    }

    /**
     * Valida que arroje excepcion si el campo marcado como NotNull es igual a null dentro del objeto
     */
    @Test
    public void test2() {
        ConsConcCreaCartCompOrgaTranDTO dtoRegla = new ConsConcCreaCartCompOrgaTranDTO();
        try {
            // dtoRegla = (ConsConcCreaCartCompOrgaTranDTO) reglaNegocioEjb.consumirRegla(dtoRegla);
            assertTrue("Se esperaba q lanzara excepcion en la validacion de campo null", false);
        } catch (EJBException e) {
            assertTrue("La casua de la excepcion no corresponde",
                    e.getCausedByException() instanceof ReglaNegocioInvalidaException);
        }
    }

}
