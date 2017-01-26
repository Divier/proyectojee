package co.com.datatools.c2.negocio.fachada;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

@Stateless(name = "FachadaAccidentalidadEJB")
@LocalBean
public class FachadaAccidentalidadEJB implements IRFachadaAccidentalidad {
    private final static Logger logger = Logger.getLogger(FachadaAccidentalidadEJB.class.getName());


}
