package co.com.datatools.c2.servicios.negocio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.infinispan.CacheImpl;
import org.jboss.logging.Logger;

import co.com.datatools.c2.servicios.interfaces.IServiceLocator;
import co.com.datatools.c2.servicios.utilidades.ConstantesCapaServicios;

/**
 * Session Bean implementation class ServiceLocatorEJB
 */
@Stateless(name = "ServiceLocatorEJB")
@LocalBean
public class ServiceLocatorEJB implements IServiceLocator {

	/**
	 * Inyeccion (o JNDI lookup) de objeto de DataSource que realiza el mapeo
	 * para obtnener los servicios almacenados dentro de la BD del modelo de la
	 * capa de servicios
	 */
	//@Resource(name = "java:jboss/datasources/C2DesaOracleDS")
	private DataSource dataSource;

	/**
	 * Inyeccion (o JNDI lookup) de objeto CacheContainer Infinispan
	 */
	@Resource(lookup = "java:jboss/infinispan/container/capaServicios")
	private org.infinispan.manager.CacheContainer serviciosContainer;

	private org.infinispan.Cache<Object, Object> cacheServicios;

	private static final Logger logger = Logger
			.getLogger(ServiceLocatorEJB.class.getName());

	private Context context;

	/**
	 * Este metodo obtiene la conexion de Base de Datos a traves de un servicio definido en el servidor de aplicaciones
	 * @return Connection Objeto de conexion de la base de datos
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		try {
			context = new InitialContext();
			Properties propiedades = new Properties();
			propiedades.load(CapaServiciosBean.class
					.getResourceAsStream("/config.properties"));

			String nombreJNDI = propiedades.getProperty("name.jndi.datasource");
			dataSource = (DataSource) context.lookup(nombreJNDI);
		} catch (NamingException | IOException ex) {
			ex.printStackTrace();
		}
		return dataSource.getConnection();
	}

	@PostConstruct
	private void init() {
		logger.info("init");
		List<ServicioDTO> servicioDTO = obtenerServiciosC2();
		if (serviciosContainer != null) {
			this.cacheServicios = this.serviciosContainer
					.getCache(ConstantesCapaServicios.NOMBRE_CACHE_SERVICIOS);
		}
		if (this.cacheServicios == null) {
			cacheServicios = new CacheImpl<Object, Object>(
					ConstantesCapaServicios.NOMBRE_CACHE_SERVICIOS);
		} else {
			System.out.println("--> Se encuentra instancia de Cache: "
					+ ConstantesCapaServicios.NOMBRE_CACHE_SERVICIOS
					+ " administrada por el contenedor");
		}

		for (ServicioDTO serviciosC2 : servicioDTO) {
			String nombreJNDI = armarNombreJNDI(serviciosC2);
			cacheServicios.put(serviciosC2.getNombreClaseInterface(), nombreJNDI);
		}
	}

	@Override
	public Object obtenerNombreJNDI(String nombreServicio) {
		logger.debug("ServiceLocatorEJB::ObtenerNombreJNDI ");
		Object oJndiName = null;
		try {
			if (cacheServicios.containsKey(nombreServicio)) {
				context = new InitialContext();
				Object jndiNameService = cacheServicios.get(nombreServicio);
				oJndiName = (Object) context.lookup(jndiNameService.toString());
			} else {
				throw new IllegalStateException("El nombre del servicio "
						+ nombreServicio
						+ " no ha sido definido dentro del sistema");
			}
		} catch (NamingException ex) {
			throw new IllegalStateException("Cannot connect to bean: "
					+ nombreServicio + " Reason: " + ex, ex.getCause());
		} finally {
			try {
				context.close();
			} catch (NamingException ex) {
				throw new IllegalStateException(
						"Cannot close InitialContext. Reason: " + ex,
						ex.getCause());
			}
		}
		return oJndiName;
	}

	/**
	 * Este metodo permite obtener los servicios definidos para el proyecto Circulemos2
	 * @return Lista con los servicios definidos para el proyecto Circulemos2
	 */
	public List<ServicioDTO> obtenerServiciosC2() {
		logger.info("ServiceLocatorEJB:::obtenerServicios");
		ArrayList<ServicioDTO> serviciosObtenidos = null;
		Connection conn = null;

		try {
			//conn = dataSource.getConnection();
			conn = getConnection();

			String sql = "SELECT "
					+ "ps.nombre, "
					+ "padre.nombre as NombreAplicacion, "
					+ "lb.version, "
					+ "modulo.nombre as NombreModulo, "
					+ "bean.nombre as NombreBean, "
					+ "servicio.nombre_servicio, "
					+ "servicio.nombre_clase_interface_remota "
					+ "FROM ae_componente modulo "
					+ " left join ae_componente padre on modulo.id_padre = padre.id "
					+ " inner join ae_linea_base lb on lb.id = modulo.id_linea_base "
					+ " inner join ae_bean bean on bean.id_componente = modulo.id "
					+ " inner join ae_servicio servicio on servicio.id_servicio_bean = bean.id "
					+ " inner join ae_prefijo_servidor ps on ps.id = modulo.id_prefijo_servidor "
					+ "inner join ae_aplicacion aplicacion on aplicacion.id = modulo.id_aplicacion "
					+ " WHERE " + " modulo.modulo = "
					+ ConstantesCapaServicios.VER_MODULO + " AND "
					+ " modulo.id_aplicacion = "
					+ ConstantesCapaServicios.APP_CIRCULEMOS2;

			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			serviciosObtenidos = new ArrayList<ServicioDTO>();
			while (rs.next()) {
				String prefijoServidor = rs.getString(1);
				String nombreAplicacion = rs.getString(2);
				String lineaBaseVersion = rs.getString(3);
				String nombreModulo = rs.getString(4);
				String nombreBean = rs.getString(5);
				String servicio = rs.getString(6);
				String nombreClaseInterface = rs.getString(7);

				ServicioDTO servicioDTO = new ServicioDTO();
				servicioDTO.setPrefijoServidor(prefijoServidor);
				servicioDTO.setNombreAplicacion(nombreAplicacion);
				servicioDTO.setLineaBaseVersion(lineaBaseVersion);
				servicioDTO.setNombreModulo(nombreModulo);
				servicioDTO.setNombreBean(nombreBean);
				servicioDTO.setNombreServicio(servicio);
				servicioDTO.setNombreClaseInterface(nombreClaseInterface);

				serviciosObtenidos.add(servicioDTO);
			}
			rs.close();
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return serviciosObtenidos;
	}

	/**
	 * Este metodo se encargar de armar el nombre JNDI del servicio de acuerdo a
	 * la especificacion dada por el servidor de aplicaciones Jboss Por ejemplo.
	 * java:global/Ejemplo2EAR-1.0.0-SNAPSHOT/Ejemplo2EJB-1.0.0-SNAPSHOT/
	 * Ejemplo2Bean!co.com.datatools.c2.IEjemplo2
	 * 
	 * @param servicioDTO
	 * @return String con el nombre del JNDI del servicio solicitado
	 */
	public static String armarNombreJNDI(ServicioDTO servicioDTO) {
		String nombreJNDI = null;
		/**
		 * valida la forma de construir el nombre JNDI acuerdo a si el servicio
		 * parte de una aplicacion o de un modulo
		 */
		if (servicioDTO.getNombreAplicacion() == null) {
			nombreJNDI = servicioDTO.getPrefijoServidor()
					+ servicioDTO.getNombreModulo() + "-"
					+ servicioDTO.getLineaBaseVersion() + "/"
					+ servicioDTO.getNombreBean() + "!"
					+ servicioDTO.getNombreClaseInterface();
		} else {
			nombreJNDI = servicioDTO.getPrefijoServidor()
					+ servicioDTO.getNombreAplicacion() + "-"
					+ servicioDTO.getLineaBaseVersion() + "/"
					+ servicioDTO.getNombreModulo() + "-"
					+ servicioDTO.getLineaBaseVersion() + "/"
					+ servicioDTO.getNombreBean() + "!"
					+ servicioDTO.getNombreClaseInterface();
		}
		return nombreJNDI;
	}
}
