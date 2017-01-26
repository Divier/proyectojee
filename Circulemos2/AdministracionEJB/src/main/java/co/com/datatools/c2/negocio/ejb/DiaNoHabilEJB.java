package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.entidades.DiaNoHabil;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.helpers.v2.DiaNoHabilHelper;
import co.com.datatools.c2.negocio.interfaces.ILDiaNoHabil;
import co.com.datatools.c2.negocio.interfaces.IRDiaNoHabil;
import co.com.datatools.util.date.UtilFecha;

/**
 * @author luis.forero
 */
@Stateless(name = "DiaNoHabilEJB")
@LocalBean
public class DiaNoHabilEJB implements ILDiaNoHabil, IRDiaNoHabil {

    @PersistenceContext(unitName = "Circulemos2JPA")
    EntityManager em;

    @Override
    public List<DiaNoHabilDTO> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio, Date fechaFin) {
        List<DiaNoHabilDTO> diasNoHabiles = new ArrayList<DiaNoHabilDTO>();

        final Map<String, Object> filtros = new HashMap<String, Object>();

        /*
         * Construccion de consulta en base de datos.
         */
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT dnh FROM DiaNoHabil AS dnh");
        sql.append(" WHERE dnh.organismoTransito.codigoOrganismo = :codOrg");
        filtros.put("codOrg", codigoOrganismo);

        /*
         * Se valinan los datos a filtrar
         */
        if (fechaInicio != null) {
            sql.append(" AND dnh.fecha >= :fecInicio");
            filtros.put("fecInicio", fechaInicio);
        }

        if (fechaFin != null) {
            sql.append(" AND dnh.fecha <= :fecFin");
            filtros.put("fecFin", fechaFin);
        }

        final TypedQuery<DiaNoHabil> query = em.createQuery(sql.toString(), DiaNoHabil.class);
        /*
         * Se ingrsan los parametros de los filtros.
         */
        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        /*
         * Se extraen los datos filtrados.
         */
        List<DiaNoHabil> resultado = query.getResultList();

        diasNoHabiles = DiaNoHabilHelper.toListLevel0DTO(resultado);

        return diasNoHabiles;
    }

    @Override
    public boolean esDiaNoHabil(int codigoOrganismo, Date fecha) {

        final TypedQuery<Long> query = em.createNamedQuery(DiaNoHabil.SQ_COUNT_BETWEEN_FECHA, Long.class);
        query.setParameter("codOrg", codigoOrganismo);
        query.setParameter("fec", fecha);

        final long count = query.getSingleResult();

        return count > 0;
    }

    @Override
    public void modificarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException {

        if (diaNoHabilDTO.getFecha().compareTo(UtilFecha.currentZeroTimeDate()) < 0) {
            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarDiasNoHabiles.ADM_019003);
        }

        DiaNoHabil diaNoHabil = em.find(DiaNoHabil.class, diaNoHabilDTO.getId());
        diaNoHabil = DiaNoHabilHelper.toLevel1Entity(diaNoHabilDTO, diaNoHabil);

        em.merge(diaNoHabil);

    }

    @Override
    public List<DiaNoHabilDTO> registrarDiasNoHabiles(List<DiaNoHabilDTO> diasNoHabiles)
            throws CirculemosNegocioException {

        for (DiaNoHabilDTO diaNoHabilDTO : diasNoHabiles) {
            validarFechaDiaNoHabil(diaNoHabilDTO);
            DiaNoHabil diaNoHabil = DiaNoHabilHelper.toLevel1Entity(diaNoHabilDTO, null);
            em.persist(diaNoHabil);
            diaNoHabilDTO.setId(diaNoHabil.getId());

        }

        return diasNoHabiles;
    }

    @Override
    public Date sumarDiasHabiles(int codigoOrganismo, Date fechaInicial, int diasHabiles) {
        final List<Calendar> diasNoHabiles = consultarDiasNoHabiles(codigoOrganismo, fechaInicial);
        final Calendar calIni = UtilFecha.resetTime(fechaInicial);
        int dias = diasHabiles;
        boolean sumar = diasHabiles >= 0;

        if (!sumar) {
            dias *= -1;
        }

        for (int i = 0; i < dias;) {
            int incremento = 1;
            if (!sumar) {
                incremento = -1;
            }

            calIni.add(Calendar.DAY_OF_MONTH, incremento);
            if (!diasNoHabiles.contains(calIni)) {
                i++;
            }
        }
        return calIni.getTime();
    }

    /**
     * Valida si el dia no habil es mayor al dia actual del sistema.
     * 
     * @param diaNoHabilDTO
     *            dia a validar
     * @throws CirculemosNegocioException
     * @author luis.forero
     */
    private void validarFechaDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException {
        // Valida si la fecha es mayor o igual a la actual, si no retorna error.
        if (diaNoHabilDTO.getFecha().compareTo(UtilFecha.currentZeroTimeDate()) <= 0) {
            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarDiasNoHabiles.ADM_019001);
        }

        // Valida si ya existe el dia no habil
        if (diaNoHabilDTO.getId() == null
                && esDiaNoHabil(diaNoHabilDTO.getOrganismoTransito().getCodigoOrganismo(), diaNoHabilDTO.getFecha())) {
            throw new CirculemosNegocioException(ErrorAdministracion.AdministrarDiasNoHabiles.ADM_019002);
        }

    }

    @Override
    public void eliminarDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) throws CirculemosNegocioException {
        validarFechaDiaNoHabil(diaNoHabilDTO);
        DiaNoHabil diaNoHabil = em.find(DiaNoHabil.class, diaNoHabilDTO.getId());
        em.remove(diaNoHabil);
    }

    @Override
    public List<Calendar> consultarDiasNoHabiles(int codigoOrganismo, Date fechaInicio) {
        List<Calendar> listCalendars = new ArrayList<>(10);

        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT dnh.fecha FROM DiaNoHabil AS dnh");
        sql.append(" WHERE dnh.organismoTransito.codigoOrganismo = :codigoOrganismo");
        sql.append(" AND dnh.fecha >= :fechaInicio");
        sql.append(" ORDER BY dnh.fecha ASC ");

        final TypedQuery<Date> q = em.createQuery(sql.toString(), Date.class);
        q.setParameter("codigoOrganismo", codigoOrganismo);
        q.setParameter("fechaInicio", fechaInicio);

        final List<Date> result = q.getResultList();
        Calendar calNoHabil = null;
        for (Date noHabil : result) {
            calNoHabil = UtilFecha.resetTime(noHabil);
            listCalendars.add(calNoHabil);
        }

        return listCalendars;
    }
}
