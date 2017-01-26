package co.com.datatools.fachadaintegracionweb.gaceta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.DetalleGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FechaPublicacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.FiltrosGacetaDTO;
import co.com.datatools.fachadainetegracionweb.dto.GacetaDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaGaceta;
import co.com.datatools.fachadaintegracionweb.entidades.DetalleGaceta;
import co.com.datatools.fachadaintegracionweb.entidades.Gaceta;
import co.com.datatools.fachadaintegracionweb.helpers.DetalleGacetaHelper;
import co.com.datatools.fachadaintegracionweb.helpers.GacetaHelper;

@Stateless
public class FachadaGacetaEJB implements IRFachadaGaceta {

    private final static Logger LOGGER = Logger.getLogger(FachadaGacetaEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public GacetaDTO consultarGaceta(Date fechaPublicacion) {
        LOGGER.debug("consultarGaceta(Date)");
        GacetaDTO gacetaDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT g FROM Gaceta g ");
        jpql.append("WHERE CONVERT(VARCHAR(10),g.fechaPublicacion,111) = :fechaPublicacion");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("fechaPublicacion", fechaPublicacion);

        @SuppressWarnings("unchecked")
        List<Gaceta> gacetas = query.getResultList();
        if (gacetas != null && !gacetas.isEmpty()) {
            gacetaDTO = GacetaHelper.toLevel1DTO(gacetas.get(0));
        }
        return gacetaDTO;
    }

    @Override
    public List<DetalleGacetaDTO> consultarDetalleGaceta(Date fechaPublicacion) {
        LOGGER.debug("consultarGaceta(Date)");
        List<DetalleGacetaDTO> detalleGacetaDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT dg FROM DetalleGaceta dg ");
        jpql.append("INNER JOIN dg.gaceta g ");
        jpql.append("WHERE CONVERT(VARCHAR(10),g.fechaPublicacion,111) = :fechaPublicacion");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("fechaPublicacion", fechaPublicacion);

        @SuppressWarnings("unchecked")
        List<DetalleGaceta> detalleGacetas = query.getResultList();
        if (detalleGacetas != null && !detalleGacetas.isEmpty()) {
            detalleGacetaDTOs = new ArrayList<>();
            for (DetalleGaceta detalleGaceta : detalleGacetas) {
                detalleGacetaDTOs.add(DetalleGacetaHelper.toLevel1DTO(detalleGaceta));
            }

        }
        return detalleGacetaDTOs;
    }

    @Override
    public List<DetalleGacetaDTO> consultarDetalleGaceta(FiltrosGacetaDTO filtrosGacetaDTO) {
        LOGGER.debug("consultarGaceta(Date)");
        List<DetalleGacetaDTO> detalleGacetaDTOs = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT dg FROM DetalleGaceta dg");
        jpql.append(" INNER JOIN dg.gaceta g ");
        jpql.append(" WHERE 1=1");

        // Fecha publicacion
        if (filtrosGacetaDTO.getFechaPublicacion() != null) {
            jpql.append(" AND CONVERT(VARCHAR(10),g.fechaPublicacion,111) = :fechaPublicacion");
        }

        // Placa
        if (filtrosGacetaDTO.getPlacaVehiculo() != null) {
            jpql.append(" AND dg.placaVehiculo LIKE :placa");
        }
        // Propietario
        if (filtrosGacetaDTO.getPropietario() != null) {
            jpql.append(" AND dg.propietario LIKE :propietario");
        }
        // Fecha imposicion
        if (filtrosGacetaDTO.getFechaImposicion() != null) {
            jpql.append(" AND dg.fechaImposicion = :fechaImposicion");
        }

        Query query = em.createQuery(jpql.toString());

        // Fecha publicacion
        if (filtrosGacetaDTO.getFechaPublicacion() != null) {
            query.setParameter("fechaPublicacion", filtrosGacetaDTO.getFechaPublicacion());
        }
        // Placa
        if (filtrosGacetaDTO.getPlacaVehiculo() != null) {
            query.setParameter("placa", "%" + filtrosGacetaDTO.getPlacaVehiculo() + "%");
        }
        // Propietario
        if (filtrosGacetaDTO.getPropietario() != null) {
            query.setParameter("propietario", "%" + filtrosGacetaDTO.getPropietario() + "%");
        }
        // Fecha imposicion
        if (filtrosGacetaDTO.getFechaImposicion() != null) {
            query.setParameter("fechaImposicion", filtrosGacetaDTO.getFechaImposicion());
        }

        @SuppressWarnings("unchecked")
        List<DetalleGaceta> detalleGacetas = query.getResultList();
        if (detalleGacetas != null && !detalleGacetas.isEmpty()) {
            detalleGacetaDTOs = new ArrayList<>();
            for (DetalleGaceta detalleGaceta : detalleGacetas) {
                detalleGacetaDTOs.add(DetalleGacetaHelper.toLevel1DTO(detalleGaceta));
            }

        }
        return detalleGacetaDTOs;
    }

    @Override
    public List<FechaPublicacionDTO> consultarFechasPublicacion() {
        LOGGER.debug("consultarFechasPublicacion()");
        List<FechaPublicacionDTO> fechasPublicacionDTO = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT g FROM Gaceta g ");
        jpql.append(" ORDER BY g.fechaPublicacion DESC");

        Query query = em.createQuery(jpql.toString());

        @SuppressWarnings("unchecked")
        List<Gaceta> gacetas = query.getResultList();
        if (gacetas != null && !gacetas.isEmpty()) {
            fechasPublicacionDTO = new ArrayList<>();
            for (Gaceta gaceta : gacetas) {
                FechaPublicacionDTO fechaPublicacionDTO = new FechaPublicacionDTO();
                fechaPublicacionDTO.setFechaPublicacion(gaceta.getFechaPublicacion());
                fechasPublicacionDTO.add(fechaPublicacionDTO);
            }
        }
        return fechasPublicacionDTO;
    }

}
