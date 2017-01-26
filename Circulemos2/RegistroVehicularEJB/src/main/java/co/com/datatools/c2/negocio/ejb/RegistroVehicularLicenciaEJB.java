package co.com.datatools.c2.negocio.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;
import co.com.datatools.c2.entidades.LicenciaConduccion;
import co.com.datatools.c2.negocio.helpers.regveh.LicenciaConduccionHelper;
import co.com.datatools.c2.negocio.interfaces.ILRegistroVehicularLicencia;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularLicencia;

@Stateless(name = "RegistroVehicularLicenciaEJB")
@LocalBean
public class RegistroVehicularLicenciaEJB implements ILRegistroVehicularLicencia, IRRegistroVehicularLicencia {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(RegistroVehicularLicenciaEJB.class.getName());

    @Override
    public LicenciaConduccionDTO consultarLicencia(String numeroLicencia, int codigoOrganismo) {
        logger.debug("RegistroVehicularLicenciaEJB::consultarLicencia(String, int)");
        LicenciaConduccionDTO licenciaConduccionDTO = null;

        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT lc FROM LicenciaConduccion lc");
        jpql.append(" WHERE lc.licenciaConduccion = :numeroLicencia");
        jpql.append(" AND lc.organismoTransito.codigoOrganismo = :codigoOrganismo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Numero de licencia
        query.setParameter("numeroLicencia", numeroLicencia);
        // Codigo organismo de transito
        query.setParameter("codigoOrganismo", codigoOrganismo);

        @SuppressWarnings("unchecked")
        List<LicenciaConduccion> licenciaConduccions = query.getResultList();
        if (licenciaConduccions != null && !licenciaConduccions.isEmpty()) {
            licenciaConduccionDTO = LicenciaConduccionHelper.toLevel1DTO(licenciaConduccions.get(0));
        }

        return licenciaConduccionDTO;
    }
}
