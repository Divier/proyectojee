package co.com.datatools.c2.negocio.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionSoporteDTO;
import co.com.datatools.c2.entidades.ConfiguracionSoporte;
import co.com.datatools.c2.entidades.TrazaEjecucionSoporte;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.ConfiguracionSoporteHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionProcedure;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionProcedure;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class AdministracionProcedureEJB
 */
@Stateless(mappedName = "AdministracionProcedureEJB")
@LocalBean
public class AdministracionProcedureEJB implements IRAdministracionProcedure, ILAdministracionProcedure {

    private final static Logger logger = Logger.getLogger(AdministracionProcedureEJB.class.getName());

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @PersistenceContext(unitName = "IntegracionTercerosC2JPA")
    private EntityManager emIntegracion;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager emCirculemos;

    @Override
    public List<ConfiguracionSoporteDTO> consultarConfiguracionProcedure(Integer idTipoSoporte) {
        logger.debug("AdministracionProcedureEJB::consultarConfiguracionProcedure(Integer)");

        Map<String, Object> filtros = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT cs FROM ConfiguracionSoporte cs ");
        jpql.append("WHERE 1=1 ");

        if (idTipoSoporte != null) {
            jpql.append("AND cs.soporte.id = :idTipoSoporte ");
            filtros.put("idTipoSoporte", idTipoSoporte);
        }

        GenericDao<ConfiguracionSoporte> resultado = new GenericDao<>(ConfiguracionSoporte.class, emCirculemos);
        List<ConfiguracionSoporte> results = resultado.buildAndExecuteQuery(jpql, filtros);
        List<ConfiguracionSoporteDTO> configSoporteDTO = null;
        if (results != null && !results.isEmpty()) {
            configSoporteDTO = ConfiguracionSoporteHelper.toListLevel1DTO(results);
        }
        return configSoporteDTO;
    }

    @Override
    public List<Object[]> ejecutarProcedure(Integer idTipoSoporte, String datosEntrada)
            throws CirculemosNegocioException {
        logger.debug("AdministracionProcedureEJB::ejecutarProcedure(Integer, String)");
        List<ConfiguracionSoporteDTO> configSoporteDTO = consultarConfiguracionProcedure(idTipoSoporte);
        List<Object[]> lsObjetos = null;
        try {
            if (configSoporteDTO != null && !configSoporteDTO.isEmpty()) {
                for (ConfiguracionSoporteDTO cSoporte : configSoporteDTO) {
                    Query query = emIntegracion.createNativeQuery(construirEjecucion(cSoporte, datosEntrada));
                    lsObjetos = Utilidades.safeList(query.getResultList());
                    TrazaEjecucionSoporte traza = new TrazaEjecucionSoporte();
                    traza.setConfigSoporte(ConfiguracionSoporteHelper.toLevel1Entity(cSoporte, null));
                    traza.setFechaFin(UtilFecha.buildCalendar().getTime());
                    traza.setUsuario(
                            UsuarioPersonaHelper.toLevel1Entity(iRSeguridadCirculemos.obtenerUsuarioDto(), null));
                    emCirculemos.persist(traza);
                }
            }
        } catch (PersistenceException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new CirculemosNegocioException(ErrorAdministracion.AdminisitracionProcedures.ADM_072001);
        }
        return lsObjetos;
    }

    private String construirEjecucion(ConfiguracionSoporteDTO configSoporteDTO, String datosEntrada) {
        logger.debug("AdministracionProcedureEJB::construirEjecucion(ConfiguracionSoporteDTO, String)");
        String ejecucion = null;
        if (configSoporteDTO != null) {
            if (configSoporteDTO.getConfiguracionEjecucion() != null) {
                ejecucion = configSoporteDTO.getConfiguracionEjecucion();
                String[] aDatosEntrada = null;
                if (datosEntrada != null) {
                    aDatosEntrada = datosEntrada.split(",");
                    for (String datoEntrada : aDatosEntrada) {
                        final String patron = Pattern.quote("?");
                        ejecucion = ejecucion.replaceFirst(patron, datoEntrada);
                    }
                }
            }
            if (configSoporteDTO.getRespuesta() != null) {
                StringBuilder sb = new StringBuilder(ejecucion);
                sb.append(" ");
                sb.append(configSoporteDTO.getRespuesta());
                ejecucion = sb.toString();
            }
        }
        return ejecucion;
    }
}