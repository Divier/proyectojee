package co.com.datatools.c2.servicios.negocio;

import java.io.Serializable;

/**
 * Esta clase se emplea para mapear los elementos que conforman el nombre JNDI de un servicio
 * @author Luis.martinez 
 */
public class ServicioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre del prefijo del servidor
	 */
	private String prefijoServidor;
	/**
	 * Nombre de la linea base generado para este artefacto
	 */
	private String lineaBaseVersion;
	/**
	 * Nombre de la aplicacion en este caso artefacto de software Empresarial JEE (EAR)
	 */
	private String nombreAplicacion;
	/**
	 * Nombre del modulo en este caso artefacto de software que puede ser un JAR o WAE
	 */
	private String nombreModulo;
	/**
	 * Nombre del EJB
	 */
	private String nombreBean;
	/**
	 * Nombre del servicio registrado que asocia el nombre del EJB con un nombre comun
	 */
	private String nombreServicio;
	/**
	 * <fully-qualified-bean-interface-name> 
	 */
	private String nombreClaseInterface;
	
	
	public String getPrefijoServidor() {
		return prefijoServidor;
	}
	public void setPrefijoServidor(String prefijoServidor) {
		this.prefijoServidor = prefijoServidor;
	}
	public String getLineaBaseVersion() {
		return lineaBaseVersion;
	}
	public void setLineaBaseVersion(String lineaBaseVersion) {
		this.lineaBaseVersion = lineaBaseVersion;
	}
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	public String getNombreModulo() {
		return nombreModulo;
	}
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}
	public String getNombreBean() {
		return nombreBean;
	}
	public void setNombreBean(String nombreBean) {
		this.nombreBean = nombreBean;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getNombreClaseInterface() {
		return nombreClaseInterface;
	}
	public void setNombreClaseInterface(String nombreClaseInterface) {
		this.nombreClaseInterface = nombreClaseInterface;
	}
	

	

}
