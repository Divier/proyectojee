package co.com.datatools.c2.negocio.ejb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.entidades.ErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaDetalleCuotasFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaDetalleFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacionDetalleCuotaFinanciacion;
import co.com.datatools.c2.enumeracion.financiacion.EnumErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.excepciones.CirculemosIllegalArgumentException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion;
import co.com.datatools.c2.negocio.helpers.InconsistenciaDetalleCuotasFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.InconsistenciaDetalleFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.InconsistenciaFinanciacionHelper;
import co.com.datatools.c2.negocio.interfaces.ILInconsistenciaFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRInconsistenciaFinanciacion;

@Stateless(mappedName = "InconsistenciaFinanciacionEJB")
@LocalBean
public class InconsistenciaFinanciacionEJB implements ILInconsistenciaFinanciacion, IRInconsistenciaFinanciacion {

    private static final Logger logger = Logger.getLogger(FinanciacionEJB.class);

    @PersistenceContext(name = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public InconsistenciaFinanciacionDTO registrarInconsistenciaFinanciacion(
            InconsistenciaFinanciacionDTO financiacionInconsistente,
            List<EnumErrorInconsistenciaFinanciacion> inconsistenciasFinanciacion) {
        logger.debug("InconsistenciaFinanciacionEJB.registrarInconsistenciaFinanciacion(InconsistenciaFinanciacionDTO,List<ErrorFinanciacionDTO>)");

        if (financiacionInconsistente == null || inconsistenciasFinanciacion == null) {
            throw new CirculemosIllegalArgumentException("Argumentos insuficientes { financiacionInconsistente:"
                    + financiacionInconsistente + ",inconsistencias:" + inconsistenciasFinanciacion + "}");
        } else {

            InconsistenciaFinanciacion inconsistenciaFinanciacion = InconsistenciaFinanciacionHelper.toLevel0Entity(
                    financiacionInconsistente, null);

            em.persist(inconsistenciaFinanciacion);

            for (EnumErrorInconsistenciaFinanciacion error : inconsistenciasFinanciacion) {
                ErrorInconsistenciaFinanciacion errorInconsistenciaFinanciacion = new ErrorInconsistenciaFinanciacion();
                errorInconsistenciaFinanciacion.setId(error.getValue());

                InconsistenciaFinanciacionDetalleCuotaFinanciacion inconsistencia = new InconsistenciaFinanciacionDetalleCuotaFinanciacion();
                inconsistencia.setInconsistenciaFinanciacion(inconsistenciaFinanciacion);
                inconsistencia.setErrorInconsistenciaFinanciacion(errorInconsistenciaFinanciacion);

                em.persist(inconsistencia);
            }

            return InconsistenciaFinanciacionHelper.toLevel0DTO(inconsistenciaFinanciacion);
        }
    }

    @Override
    public void registrarInconsistenciaDetalleFinanciacion(
            Map<InconsistenciaDetalleFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalles,
            InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO) throws CirculemosNegocioException {
        logger.debug("InconsistenciaFinanciacionEJB.registrarInconsistenciaDetalleFinanciacion(InconsistenciaDetalleFinanciacionDTO,List<ErrorFinanciacionDTO>)");

        if (inconsistenciasDetalles == null) {
            throw new CirculemosIllegalArgumentException("Argumentos insuficientes { inconsistenciasDetalles:"
                    + inconsistenciasDetalles + "}");
        } else if (inconsistenciasDetalles.isEmpty()) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroresInconsistenciaFinanciacion.FIN_01000002);
        } else if (inconsistenciaFinanciacionDTO == null || inconsistenciaFinanciacionDTO.getId() == null) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroresInconsistenciaFinanciacion.FIN_01000001);
        } else {
            for (Entry<InconsistenciaDetalleFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> e : inconsistenciasDetalles
                    .entrySet()) {
                e.getKey().setInconsistenciaFinanciacion(inconsistenciaFinanciacionDTO);
                InconsistenciaDetalleFinanciacion inconsistenciaDetalleFinanciacion = InconsistenciaDetalleFinanciacionHelper
                        .toLevel1Entity(e.getKey(), null);

                em.persist(inconsistenciaDetalleFinanciacion);

                for (EnumErrorInconsistenciaFinanciacion error : e.getValue()) {
                    ErrorInconsistenciaFinanciacion errorInconsistenciaFinanciacion = new ErrorInconsistenciaFinanciacion();
                    errorInconsistenciaFinanciacion.setId(error.getValue());

                    InconsistenciaFinanciacionDetalleCuotaFinanciacion inconsistencia = new InconsistenciaFinanciacionDetalleCuotaFinanciacion();
                    inconsistencia.setInconsistenciaDetalleFinanciacion(inconsistenciaDetalleFinanciacion);
                    inconsistencia.setErrorInconsistenciaFinanciacion(errorInconsistenciaFinanciacion);
                    em.persist(inconsistencia);
                }
            }
        }

    }

    @Override
    public void registrarInconsistenciaDetalleCuotasFinanciacion(
            Map<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> inconsistenciasDetalleCuotas,
            InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO) throws CirculemosNegocioException {
        logger.debug("InconsistenciaFinanciacionEJB.registrarInconsistenciaDetalleFinanciacion(Map<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>>,InconsistenciaFinanciacionDTO)");

        if (inconsistenciasDetalleCuotas == null) {
            throw new CirculemosIllegalArgumentException("Argumentos insuficientes { inconsistenciasDetalleCuotas:"
                    + inconsistenciasDetalleCuotas + "}");
        } else if (inconsistenciasDetalleCuotas.isEmpty()) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroresInconsistenciaFinanciacion.FIN_01000002);
        } else if (inconsistenciaFinanciacionDTO == null || inconsistenciaFinanciacionDTO.getId() == null) {
            throw new CirculemosNegocioException(ErrorFinanciacion.EnumErroresInconsistenciaFinanciacion.FIN_01000001);
        } else {
            for (Entry<InconsistenciaDetalleCuotasFinanciacionDTO, List<EnumErrorInconsistenciaFinanciacion>> e : inconsistenciasDetalleCuotas
                    .entrySet()) {
                e.getKey().setInconsistenciaFinanciacion(inconsistenciaFinanciacionDTO);
                InconsistenciaDetalleCuotasFinanciacion inconsistenciaDetalleCuotasFinanciacion = InconsistenciaDetalleCuotasFinanciacionHelper
                        .toLevel1Entity(e.getKey(), null);

                em.persist(inconsistenciaDetalleCuotasFinanciacion);

                for (EnumErrorInconsistenciaFinanciacion error : e.getValue()) {
                    ErrorInconsistenciaFinanciacion errorInconsistenciaFinanciacion = new ErrorInconsistenciaFinanciacion();
                    errorInconsistenciaFinanciacion.setId(error.getValue());

                    InconsistenciaFinanciacionDetalleCuotaFinanciacion inconsistencia = new InconsistenciaFinanciacionDetalleCuotaFinanciacion();
                    inconsistencia.setInconsistenciaDetalleCuotasFinanciacion(inconsistenciaDetalleCuotasFinanciacion);
                    inconsistencia.setErrorInconsistenciaFinanciacion(errorInconsistenciaFinanciacion);
                    em.persist(inconsistencia);
                }
            }
        }
    }

}
