package co.com.datatools.fachadaintegracionweb.financiaciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.DetalleAmortizacionDTO;
import co.com.datatools.fachadainetegracionweb.dto.DetalleFinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.FinanciacionReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadainetegracionweb.dto.ObligacionFinanciacionDTO;
import co.com.datatools.fachadainetegracionweb.enumeracion.EnumTipoIdentificacion;
import co.com.datatools.fachadainetegracionweb.interfaces.ILCatalogo;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaFinanciaciones;
import co.com.datatools.fachadaintegracionweb.entidades.TipoIdentificacionPersona;
import co.com.datatools.fachadaintegracionweb.entidades.TipoObligacion;

@Stateless
public class FachadaFinanciacionesEJB implements IRFachadaFinanciaciones {

    private final static Logger LOGGER = Logger.getLogger(FachadaFinanciacionesEJB.class.getName());

    @PersistenceContext(unitName = "AXISOracleJPA")
    private EntityManager emIt;

    @EJB
    private ILCatalogo iLCatalogo;

    @Override
    public List<FinanciacionReplicaDTO> consultarFinanciaciones(FinanciacionConsultaDTO financiacionConsultaDTO) {
        LOGGER.debug("consultarFinanciaciones(FinanciacionConsultaDTO)");
        final List<FinanciacionReplicaDTO> financiaciones = new ArrayList<FinanciacionReplicaDTO>();
        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("NUMERO_FINANCIACION, ");
        sql.append("FECHA_INICIO_DEUDA, ");
        sql.append("VALOR_TOTAL_FINANCIADO, ");
        sql.append("SALDO_FINANCIACION ");
        sql.append("FROM V_FINANCIACIONES ");
        sql.append("WHERE SALDO_FINANCIACION > 0 ");

        if (financiacionConsultaDTO.getCodigoIdentificacion() != null
                && financiacionConsultaDTO.getIdentificacion() != null) {
            sql.append("AND ID_IDENTIFICACION = :codigoIdentificacion ");
            params.put("codigoIdentificacion", financiacionConsultaDTO.getCodigoIdentificacion());
            sql.append("AND IDENTIFICACION = :identificacion ");
            params.put("identificacion", financiacionConsultaDTO.getIdentificacion());
        }

        if (financiacionConsultaDTO.getNumeroFinanciacion() != null) {
            sql.append("AND NUMERO_FINANCIACION = :numeroFinanciacion ");
            params.put("numeroFinanciacion", financiacionConsultaDTO.getNumeroFinanciacion());
        }

        sql.append("ORDER BY numero_financiacion ASC");

        Query query = emIt.createNativeQuery(sql.toString());

        if (!params.isEmpty()) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                FinanciacionReplicaDTO finan = new FinanciacionReplicaDTO();
                finan.setNumeroFinanciacion(new BigDecimal(objects2[0].toString()));
                finan.setFechaFinanciacion((Date) objects2[1]);
                finan.setValorTotalFinanciado(new BigDecimal(objects2[2].toString()));
                finan.setSaldoFinanciacion(new BigDecimal(objects2[3].toString()));
                financiaciones.add(finan);
            }
        }

        return financiaciones;
    }

    @Override
    public DetalleFinanciacionReplicaDTO consultarDetalleFinanciacion(BigDecimal numeroFinanciacion) {
        LOGGER.debug("consultarDetalleFinanciacion(BigDecimal)");
        DetalleFinanciacionReplicaDTO respuesta = new DetalleFinanciacionReplicaDTO();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("NUMERO_FINANCIACION, ");
        sql.append("FECHA_INICIO_DEUDA, ");
        sql.append("VALOR_TOTAL_FINANCIADO, ");
        sql.append("NRO_CUOTAS, ");
        sql.append("ID_IDENTIFICACION, ");
        sql.append("IDENTIFICACION, ");
        sql.append("NOMBRES, ");
        sql.append("APELLIDO1, ");
        sql.append("APELLIDO2 ");
        sql.append("FROM V_FINANCIACIONES ");
        sql.append("WHERE NUMERO_FINANCIACION = :numeroFinanciacion");

        Query query = emIt.createNativeQuery(sql.toString());
        query.setParameter("numeroFinanciacion", numeroFinanciacion);

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                respuesta.setNumeroFinanciacion(new BigDecimal(objects2[0].toString()));
                respuesta.setFechaFinanciacion((Date) objects2[1]);
                respuesta.setValorTotalFinanciado(new BigDecimal(objects2[2].toString()));
                respuesta.setNumeroCuotas(Integer.parseInt(objects2[3].toString()));

                // Tipo Identificacion
                respuesta.setTipoIdentificacionDeudor(objects2[4].toString());
                ItemCatalogoDTO itemCatalogoTIPDTO = consultarItemCatalogo(TipoIdentificacionPersona.class.getName(),
                        respuesta.getTipoIdentificacionDeudor());
                respuesta.setNombreTipoIdentificacion(itemCatalogoTIPDTO.getNombre());

                respuesta.setNumeroIdentificacionDeudor(objects2[5].toString());
                respuesta.setNombresDeudor(objects2[6].toString());

                // Apellido 1
                String apellido1 = null;
                if (objects2[7] != null) {
                    apellido1 = objects2[7].toString();
                }
                // Apellido 2
                String apellido2 = null;
                if (objects2[8] != null) {
                    apellido2 = objects2[8].toString();
                }

                if (!respuesta.getTipoIdentificacionDeudor()
                        .equals(EnumTipoIdentificacion.NUMERO_TRIBUTARIO.getCodigo())) {
                    String nombreDeudor = respuesta.getNombresDeudor();
                    if (apellido1 != null) {
                        nombreDeudor = nombreDeudor + " " + apellido1;
                    }
                    if (apellido2 != null) {
                        nombreDeudor = nombreDeudor + " " + apellido2;
                    }
                    respuesta.setNombresDeudor(nombreDeudor);
                }

                respuesta.setDetalleAmortizacion(consultarDetalleAmortizacion(numeroFinanciacion));
                respuesta.setOblicacionesFinanciacion(consultarObligacionesFinanciacion(numeroFinanciacion));
            }
        }

        return respuesta;
    }

    private List<DetalleAmortizacionDTO> consultarDetalleAmortizacion(BigDecimal numeroFinanciacion) {
        LOGGER.debug("consultarDetalleAmortizacion(BigDecimal)");
        List<DetalleAmortizacionDTO> respuesta = new ArrayList<DetalleAmortizacionDTO>();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("C.NRO_CUOTA, ");
        sql.append("C.VENCIMIENTO_CUOTA, ");
        sql.append("C.VALOR_CUOTA, ");
        sql.append("P.FECHA_HORA_PAGO ");
        sql.append("FROM V_CUOTASFINANCIACION C ");
        sql.append(
                "FULL OUTER JOIN V_DETALLES_PAGOS P ON C.NRO_CUOTA = P.NUMERO_CUOTA AND C.NUMERO_FINANCIACION = P.NUMERO_DOCUMENTO ");
        sql.append("WHERE C.NUMERO_FINANCIACION = :numeroFinanciacion ");
        sql.append("ORDER BY C.NRO_CUOTA ");

        Query query = emIt.createNativeQuery(sql.toString());
        query.setParameter("numeroFinanciacion", numeroFinanciacion);

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                DetalleAmortizacionDTO detalleDTO = new DetalleAmortizacionDTO();
                detalleDTO.setNumeroCuota(Integer.parseInt(objects2[0].toString()));
                detalleDTO.setFechaPagoOportuno((Date) objects2[1]);
                detalleDTO.setValorCuota(new BigDecimal(objects2[2].toString()));
                detalleDTO.setFechaTransaccion((Date) objects2[3]);
                respuesta.add(detalleDTO);
            }
        }

        return respuesta;
    }

    private List<ObligacionFinanciacionDTO> consultarObligacionesFinanciacion(BigDecimal numeroFinanciacion) {
        LOGGER.debug("consultarObligacionesFinanciacion(BigDecimal)");
        List<ObligacionFinanciacionDTO> respuesta = new ArrayList<ObligacionFinanciacionDTO>();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("I.TIPO_CITACION, ");
        sql.append("I.NUMERO_CITACION, ");
        sql.append("I.FECHA_IMPOSICION, ");
        sql.append("F.VALOR_OBLIGACION ");
        sql.append("FROM V_DETALLEFINANCIACION F, V_INFRACCIONES I ");
        sql.append("WHERE F.OBLIGACION = I.NUMERO_FACTURA ");
        sql.append("AND F.NUMERO_FINANCIACION = :numeroFinanciacion ");

        Query query = emIt.createNativeQuery(sql.toString());
        query.setParameter("numeroFinanciacion", numeroFinanciacion);

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {
                ObligacionFinanciacionDTO obligacionDTO = new ObligacionFinanciacionDTO();

                // Tipo Obligacion
                obligacionDTO.setTipoObligacion(objects2[0].toString());
                ItemCatalogoDTO itemCatalogoDTO = consultarItemCatalogo(TipoObligacion.class.getName(),
                        StringUtils.leftPad(String.valueOf(obligacionDTO.getTipoObligacion()), 2, "0"));
                obligacionDTO.setNombreObligacion(itemCatalogoDTO.getNombre());

                obligacionDTO.setObligacion(objects2[1].toString());
                obligacionDTO.setFechaObligacion((Date) objects2[2]);
                obligacionDTO.setValorObligacion(new BigDecimal(objects2[3].toString()));
                respuesta.add(obligacionDTO);
            }
        }

        return respuesta;
    }

    /**
     * Se encarga de consultar un catalogo por id
     * 
     * @param catalogo
     * @param id
     * @return
     * @author giovanni.velandia
     */
    private ItemCatalogoDTO consultarItemCatalogo(String catalogo, Integer id) {
        List<ItemCatalogoDTO> itemCatalogoDTOs = iLCatalogo.consultarItemsCatalogo(catalogo, new ItemCatalogoDTO(id));
        return itemCatalogoDTOs.get(0);
    }

    /**
     * Se encarga de consultar un catalogo por codigo
     * 
     * @param catalogo
     * @param codigo
     * @return
     * @author giovanni.velandia
     */
    private ItemCatalogoDTO consultarItemCatalogo(String catalogo, String codigo) {
        List<ItemCatalogoDTO> itemCatalogoDTOs = iLCatalogo.consultarItemsCatalogo(catalogo,
                new ItemCatalogoDTO(codigo));
        return itemCatalogoDTOs.get(0);
    }
}
