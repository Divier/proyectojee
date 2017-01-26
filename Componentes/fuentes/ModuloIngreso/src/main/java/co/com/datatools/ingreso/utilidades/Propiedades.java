package co.com.datatools.ingreso.utilidades;

import java.io.IOException;
import java.util.Properties;

import org.jboss.logging.Logger;

import co.com.datatools.ingreso.ConexionAutenticacion;
import co.com.datatools.ingreso.ModuloIngreso;
import co.com.datatools.ingreso.excepcion.AutenticacionExcepcion;
import co.com.datatools.ingreso.excepcion.ErrorAutenticacion;

/**
 * Clase que se encarga de leer el archivo de propiedades donde se encuentra la configuracion de la autenticacion, y provee los metodos para leer las
 * propiedaes definidas
 * 
 * @author sergio.torres (20/05/2015)
 * 
 */
public class Propiedades {

    private static final Logger LOGGER = Logger.getLogger(ModuloIngreso.class);

    private static Propiedades instancia;
    private Properties propiedades;

    private Propiedades() throws AutenticacionExcepcion {
        cargarPropiedades();
    }

    /**
     * Obtiene la instancia del sibgleton que maneja el archivo de configuración de la autenticación personalizada
     * 
     * @return Propiedades - Instancia de la clase que ya tiene el archivo de propiedades cargado
     * @throws AutenticacionExcepcion
     */
    public static Propiedades obtenerInstancia() throws AutenticacionExcepcion {
        LOGGER.info("Propiedades::obtenerInstancia");
        if (instancia == null) {
            instancia = new Propiedades();
        }
        return instancia;
    }

    /**
     * Carga el archivo de propiedades de configuración para la autenticación
     * 
     * @throws AutenticacionExcepcion
     */
    private void cargarPropiedades() throws AutenticacionExcepcion {
        LOGGER.info("Propiedades::cargarPropiedades");
        propiedades = new Properties();
        try {
            propiedades.load(ConexionAutenticacion.class.getResourceAsStream("/configuracion.properties"));
        } catch (IOException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_003);
            aex.initCause(e);
            throw aex;
        }
    }

    /**
     * Obtiene la propiedad recibida en formato cadena
     * 
     * @param clave
     *            de identificacion en el archivo
     * @return valor asignada a la clave
     */
    public String obtenerPropiedadString(String clave) {
        LOGGER.info("Propiedades::obtenerPropiedadString");
        return propiedades.getProperty(clave);
    }

}
