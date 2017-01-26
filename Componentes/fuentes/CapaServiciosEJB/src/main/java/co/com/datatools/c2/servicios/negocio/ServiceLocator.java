package co.com.datatools.c2.servicios.negocio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import co.com.datatools.c2.servicios.utilidades.ConstantesCapaServicios;
/**
 * Esta clase esta basada en la implementación del patron Service Locator el cual es usado para la creación, búsqueda de interfaces complejas y operaciones de red
 * Esta capa de servicios es dinámica porque esta basada en un modelo relacional para obtener los servicios.
 * @see <a href="http://www.oracle.com/technetwork/java/servicelocator-137181.html">Service Locator</a>
 * @author luis.martinez
 *
 */
public class ServiceLocator {

	private static ServiceLocator instance;
	private HashMap<String, String> cache = null;
	private Context context;
	private DataSource ds;
	private static final Logger logger = Logger.getLogger(ServiceLocator.class.getName());

	public static final ServiceLocator getInstance() {
		if (instance == null) {
			try {
				instance = new ServiceLocator();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * Create una instancia de la clase y trae los servicios definidos dentro del modelo relacional de BD para el proyecto Circulemos2 y los almacena en un cache para 
	 * mejorar el desempeño de la consulta reduciendo el numero de invocaciones a la BD y mejorando el desempeño de red. 
	 */
	private ServiceLocator() {
		logger.info("ServiceLocator::constructor");
		try {
			if (cache == null) {
				cache = new HashMap<String, String>();
				List<ServicioDTO> servicioDTO = obtenerServicios();
				for (ServicioDTO serviciosC2 : servicioDTO) {
					String nombreJNDI = armarNombreJNDI(serviciosC2);
					cache.put(serviciosC2.getNombreServicio(), nombreJNDI);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			ds = (DataSource) context
					.lookup(nombreJNDI);
//			ds = (DataSource) context
//					.lookup("java:jboss/datasources/C2DesaOracleDS");
		} catch (NamingException | IOException ex) {
			ex.printStackTrace();
		}
		return ds.getConnection();
	}

	/**
	 * Este metodo dado un nombre de servicio asocia y busca el nombre del servicio JNDI 
	 * @param nombreServicio
	 * @return Objeto nombre JNDI
	 */
	public Object getLookupRemoteEJB(String nombreServicio) {
		logger.info("ServiceLocator::getLookupRemoteEJB");
		Object oJndiName = null;
		try {
			if (cache.containsKey(nombreServicio)) {
				System.out.println("Nombre Servicio " + nombreServicio);
				Object jndiNameService = (Object) cache.get(nombreServicio);
				context = new InitialContext();
				oJndiName = (Object) context.lookup(jndiNameService.toString());
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
	 * Este metodo se encargar de armar el nombre JNDI del servicio compuesto de una base de datos
	 * Por ejemplo. java:global/Ejemplo2EAR-1.0.0-SNAPSHOT/Ejemplo2EJB-1.0.0-SNAPSHOT/Ejemplo2Bean!co.com.datatools.c2.IEjemplo2
	 * @param servicioDTO
	 * @return String con el nombre del JNDI del servicio solicitado
	 */
	public static String armarNombreJNDI(ServicioDTO servicioDTO) {
		String nombreJNDI;
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
	
	/**
	 * Este metodo obtiene todos los servicios de comunicaicon para el proyecto Circulemos2
	 * @return List con los servicios del proyecto Circulemos2
	 */
	public List<ServicioDTO> obtenerServicios() {
		System.out.println("ServiceLocator::obtenerServicios");
		ArrayList<ServicioDTO> serviciosObtenidos = null;
		Connection conn = null;

		try {
			conn = getConnection();
//			String sql = "select pres.nombre, a.nombre as NombreAplicacion, lb.version, m.descripcion, b.nombre as NombreBean, "
//					+ "s.nombre_servicio, s.nombre_clase_interface_remota "
//					+ "from prefix_server pres, componente c,linea_base lb, aplicacion a, modulo m, com_bean b, servicio s "
//					+ "where "
//					+ "pres.id = c.id_prefix_server and "
//					+ "c.id = a.id_componente and "
//					+ "lb.id = a.id_linea_base and "
//					+ "a.id = m.id_aplicacion and "
//					+ "m.id = b.id_modulo and "
//					+ "b.id = s.id_bean ";
			
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
}
