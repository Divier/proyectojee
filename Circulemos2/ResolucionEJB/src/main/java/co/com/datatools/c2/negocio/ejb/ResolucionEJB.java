package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EstadoResolucionDTO;
import co.com.datatools.c2.dto.ResolucionDTO;
import co.com.datatools.c2.dto.TipoResolucionDTO;
import co.com.datatools.c2.entidades.Resolucion;
import co.com.datatools.c2.enumeraciones.EnumEstadoResolucion;
import co.com.datatools.c2.enumeraciones.EnumTipoResolucion;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.fachada.IRFachadaAdminGeneral;
import co.com.datatools.c2.negocio.helpers.ResolucionHelper;
import co.com.datatools.c2.negocio.interfaces.ILResolucion;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRResolucion;
import co.com.datatools.c2.negocio.interfaces.Resoluble;
import co.com.datatools.util.GenericDao;

@Stateless(name = "ResolucionEJB")
@LocalBean
public class ResolucionEJB implements IRResolucion, ILResolucion {

    private final static Logger logger = Logger.getLogger(ResolucionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRFachadaAdminGeneral fachadaAdminGeneral;
    @EJB
    private ILResolucion iLResolucion;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @Override
    public void actualizarResolucion(ResolucionDTO resolucion) {
        logger.debug("ResolucionEJB::actualizarResolucion(ResolucionDTO)");
        checkNotNull(resolucion, "Resolucion nula");
        checkNotNull(resolucion.getId(), "Resolucion sin identificador");

        Resolucion resolAntigua = em.find(Resolucion.class, resolucion.getId());
        checkNotNull(resolAntigua, "Resolucion no existe");
        Resolucion resolucionActualizar = ResolucionHelper.toLevel1Entity(resolucion, resolAntigua);
        em.merge(resolucionActualizar);
    }

    @Override
    public List<ResolucionDTO> consultarResolucion(ResolucionDTO resolucion) {
        logger.debug("ResolucionEJB.consultarResolucion(ResolucionDTO)");
        List<ResolucionDTO> lsResultado = null;

        GenericDao<Resolucion> resolucionDao = new GenericDao<>(Resolucion.class, em);
        StringBuilder jpql = new StringBuilder();
        Map<String, Object> filtros = new HashMap<>();
        jpql.append("SELECT DISTINCT r FROM Resolucion r");
        jpql.append(" WHERE 1 = 1");

        if (resolucion.getId() != null) {
            jpql.append(" AND r.id = :idResolucion");
            filtros.put("idResolucion", resolucion.getId());
        }

        List<Resolucion> resultadoConsulta = resolucionDao.buildAndExecuteQuery(jpql, filtros);
        if (!resultadoConsulta.isEmpty()) {
            lsResultado = ResolucionHelper.toListLevel1DTO(resultadoConsulta);
        }
        return lsResultado;
    }

    @Override
    public long generarResolucion(Resoluble resoluble) {
        logger.debug("ResolucionEJB::registrarResolucion(Resoluble, DatosDocumentoDTO)");
        Calendar fechaResolucion = Calendar.getInstance();
        fechaResolucion.setTime(resoluble.getGeneraDocumentoDTO().getFechaGeneracion());
        // Arma objeto de resolucion
        ResolucionDTO resolucionDTO = new ResolucionDTO();
        resolucionDTO.setCodigoOrganismo(resoluble.getCodigoOrganismo());
        resolucionDTO.setAnoResolucion(fechaResolucion.get(Calendar.YEAR));
        resolucionDTO.setFechaResolucion(fechaResolucion.getTime());
        resolucionDTO.setTipoResolucion(new TipoResolucionDTO(EnumTipoResolucion.SANCION.getValue()));

        if (resoluble.getNumeroConsecutivo() == null) {
            /*
             * Se obtiene un 'Número del consecutivo' de resolucion de sanción mediante la inclusión del caso de uso 'Obtener consecutivos'
             */
            resoluble.setNumeroConsecutivo(fachadaAdminGeneral.generarConsecutivo(resolucionDTO.getCodigoOrganismo(),
                    resoluble.getEnumConsecutivo()));
        }
        resolucionDTO.setNumeroResolucion(resoluble.getNumeroConsecutivo());
        resolucionDTO = iLResolucion.generaDocumentoResolucion(resolucionDTO, resoluble);

        Resolucion resolucion = ResolucionHelper.toLevel1Entity(resolucionDTO, null);
        em.persist(resolucion);
        return resolucion.getId();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ResolucionDTO generaDocumentoResolucion(ResolucionDTO resolucionDTO, Resoluble resoluble) {

        try {
            // Por cada comparendo genera la resolución de sanción bajo la inclusión del caso de uso 'Generar Documento circulemos 2.0'
            Long idDocumento = iRDocumentosCirculemos.generarDocumento(resoluble.getGeneraDocumentoDTO());

            // Resolucion exitosa guarda toda la informacion
            resolucionDTO.setIdDocumento(idDocumento);
            resolucionDTO.setEstadoResolucion(new EstadoResolucionDTO(EnumEstadoResolucion.VIGENTE.getValue()));
            resolucionDTO.setResolucionExitosa(true);

        } catch (CirculemosAlertaException e) {
            resolucionDTO.setResolucionExitosa(false);
        }
        return resolucionDTO;
    }
}
