package co.com.datatools.c2.negocio.ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.entidades.Consecutivo;
import co.com.datatools.c2.entidades.TipoConsecutivo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.interfaces.ILConsecutivo;
import co.com.datatools.c2.negocio.interfaces.IRConsecutivo;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;

/**
 * EJB que contiene la especificacion de la interfaz IConsecutivo
 * 
 * @author julio.pinzon
 */
@Stateless(name = "ConsecutivoEJB")
@LocalBean
public class ConsecutivoEJB implements ILConsecutivo, IRConsecutivo {

    private final static Logger logger = Logger.getLogger(ConsecutivoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    EntityManager em;

    @Override
    public String generarConsecutivo(int codigoOrganismo, EnumConsecutivo... tipoConsecutivo) {
        logger.debug("ConsecutivoEJB::generarConsecutivo(int, EnumConsecutivo..)");

        Calendar fecha = new GregorianCalendar();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c FROM Consecutivo AS c ");
        sql.append(" WHERE c.organismoTransito.codigoOrganismo = :pCodOrg");
        sql.append(" AND c.tipoConsecutivo.id = :pIdTipCon");
        sql.append(
                " AND ((c.tipoConsecutivo.aplicaAnio = true AND c.anio = :anio) or c.tipoConsecutivo.aplicaAnio = false) ");

        TypedQuery<Consecutivo> query = em.createQuery(sql.toString(), Consecutivo.class);
        EnumConsecutivo enumTipoConsecutivo = EnumConsecutivo.GENERAL;
        if (tipoConsecutivo != null && tipoConsecutivo.length > 0) {
            enumTipoConsecutivo = tipoConsecutivo[0];
        }
        query.setParameter("pIdTipCon", tipoConsecutivo[0].getValue());
        query.setParameter("pCodOrg", codigoOrganismo);
        query.setParameter("anio", fecha.get(Calendar.YEAR));

        List<Consecutivo> resultados = query.getResultList();
        Consecutivo consecutivo;
        if (resultados.isEmpty()) {
            consecutivo = new Consecutivo();
            consecutivo.setOrganismoTransito(new OrganismoTransito());
            consecutivo.getOrganismoTransito().setCodigoOrganismo(codigoOrganismo);
            consecutivo.setTipoConsecutivo(new TipoConsecutivo());
            consecutivo.getTipoConsecutivo().setId(enumTipoConsecutivo.getValue());
            consecutivo.setConsecutivo("1");

            TipoConsecutivo tipo = em.find(TipoConsecutivo.class, enumTipoConsecutivo.getValue());
            if (tipo.getAplicaAnio()) {
                consecutivo.setAnio(fecha.get(Calendar.YEAR));
            }

            em.persist(consecutivo);
        } else {
            consecutivo = resultados.get(0);
            Long numeroConsecutivo = Long.valueOf(consecutivo.getConsecutivo()) + 1L;
            consecutivo.setConsecutivo(String.valueOf(numeroConsecutivo));

            em.merge(consecutivo);
        }
        return consecutivo.getConsecutivo();
    }

}
