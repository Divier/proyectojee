package co.com.datatools.fachadaintegracionweb.comparendos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.ComparendoConsultaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDTO;
import co.com.datatools.fachadainetegracionweb.dto.ComparendoReplicaDetalleDTO;
import co.com.datatools.fachadainetegracionweb.dto.EvidenciasDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadainetegracionweb.interfaces.ILCatalogo;
import co.com.datatools.fachadainetegracionweb.interfaces.IRFachadaComparendos;
import co.com.datatools.fachadaintegracionweb.entidades.MedioImposicionComparendo;
import co.com.datatools.fachadaintegracionweb.entidades.TipoIdentificacionPersona;

@Stateless
public class FachadaComparendosEJB implements IRFachadaComparendos {

    private final static Logger LOGGER = Logger.getLogger(FachadaComparendosEJB.class.getName());

    @PersistenceContext(unitName = "AXISOracleJPA")
    private EntityManager emIt;
    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILCatalogo iLCatalogo;

    private static String ESTADO_COMPARENDO_PAGADO = "PAGADA";

    @Override
    public List<ComparendoReplicaDTO> consultarComparendos(ComparendoConsultaDTO comparendoConsultaDto) {
        LOGGER.debug("consultarComparendos(ComparendoConsultaDTO)");
        final List<ComparendoReplicaDTO> comparendos = new ArrayList<ComparendoReplicaDTO>();

        final StringBuilder sql = new StringBuilder();
        final Map<String, Object> params = new HashMap<>();

        sql.append("SELECT ");
        sql.append("TIPO_CITACION, ");
        sql.append("ESTADO_CITACION, ");
        sql.append("NUMERO_CITACION, ");
        sql.append("PLACA, ");
        sql.append("FECHA_IMPOSICION, ");
        sql.append("VALOR_CONCEPTO, ");
        sql.append("isf_calcula_recargo(NUMERO_FACTURA) INTERESES, ");
        sql.append("VALOR_CONCEPTO + isf_calcula_recargo(NUMERO_FACTURA) VALOR_TOTAL, ");
        sql.append("NUMERO_FACTURA ");
        sql.append("FROM v_infracciones ");
        sql.append("WHERE ESTADO_CITACION != 'ANULADA'");

        if (comparendoConsultaDto.getCodigoIdentificacion() != null
                && comparendoConsultaDto.getIdentificacion() != null) {
            sql.append("AND ID_IDENTIFICACION = :codigoIdentificacion ");
            params.put("codigoIdentificacion", comparendoConsultaDto.getCodigoIdentificacion());
            sql.append("AND IDENTIFICACION = :identificacion ");
            params.put("identificacion", comparendoConsultaDto.getIdentificacion());
        }

        if (comparendoConsultaDto.getNumeroCitacion() != null) {
            sql.append("AND NUMERO_CITACION = :numeroCitacion ");
            params.put("numeroCitacion", comparendoConsultaDto.getNumeroCitacion());
        }

        if (comparendoConsultaDto.getAnioInfraccion() != null) {
            sql.append("AND EXTRACT(YEAR FROM FECHA_IMPOSICION) = :anioInfraccion ");
            params.put("anioInfraccion", comparendoConsultaDto.getAnioInfraccion());
        }

        if (comparendoConsultaDto.getPlacaVehiculo() != null) {
            sql.append("AND UPPER(PLACA) = :placaVehiculo ");
            params.put("placaVehiculo", comparendoConsultaDto.getPlacaVehiculo().toUpperCase());
        }

        sql.append("ORDER BY NUMERO_CITACION ASC");

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
                ComparendoReplicaDTO comparendoReplicaDTO = new ComparendoReplicaDTO();
                comparendoReplicaDTO.setTipoCitacion(Integer.parseInt(objects2[0].toString()));

                ItemCatalogoDTO itemCatalogoDTO = consultarItemCatalogo(MedioImposicionComparendo.class.getName(),
                        comparendoReplicaDTO.getTipoCitacion());
                comparendoReplicaDTO.setNombreTipoCitacion(itemCatalogoDTO.getNombre());

                comparendoReplicaDTO.setEstadoCitacion(objects2[1].toString());
                comparendoReplicaDTO.setNumeroCitacion((objects2[2] == null ? null : objects2[2].toString()));
                comparendoReplicaDTO.setPlacaVehiculo((objects2[3] == null ? null : objects2[3].toString()));
                comparendoReplicaDTO.setFechaImposicion((Date) objects2[4]);
                comparendoReplicaDTO.setValorIntereses(new BigDecimal(objects2[6].toString()));
                comparendoReplicaDTO.setSaldoComparendo(new BigDecimal(objects2[7].toString()));
                comparendoReplicaDTO.setValorComparendo(
                        comparendoReplicaDTO.getSaldoComparendo().add(comparendoReplicaDTO.getValorIntereses()));
                comparendoReplicaDTO.setNumeroFactura(objects2[8].toString());
                comparendos.add(comparendoReplicaDTO);
            }
        }

        return comparendos;
    }

    @Override
    public ComparendoReplicaDetalleDTO consultarDetalleComparendo(String numeroComparendo) {
        LOGGER.debug("consultarDetalleComparendo(String)");
        ComparendoReplicaDetalleDTO respuesta = new ComparendoReplicaDetalleDTO();

        final StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append("i.TIPO_CITACION, ");
        sql.append("i.NUMERO_CITACION, ");
        sql.append("i.FECHA_IMPOSICION, ");
        sql.append("i.PLACA, ");
        sql.append("i.DIRECCION_INFRACCION, ");
        sql.append("i.ESTADO_CITACION, ");
        sql.append("i.ID_IDENTIFICACION, ");
        sql.append("i.IDENTIFICACION, ");
        sql.append("i.NOMBRES||' '||i.APELLIDO1||' '||i.APELLIDO2, ");
        sql.append("i.CODIGO_INFRACCION, ");
        sql.append("i.VALOR_CONCEPTO,	 ");
        sql.append("isf_calcula_recargo(NUMERO_FACTURA) INTERESES, ");
        sql.append("VALOR_CONCEPTO + isf_calcula_recargo(NUMERO_FACTURA) VALOR_TOTAL ");
        sql.append("FROM v_infracciones i ");
        sql.append("WHERE i.NUMERO_FACTURA = :numeroComparendo ");

        Query query = emIt.createNativeQuery(sql.toString());
        query.setParameter("numeroComparendo", numeroComparendo);

        @SuppressWarnings("unchecked")
        List<Object[]> objects = query.getResultList();
        if (objects != null) {
            for (Object[] objects2 : objects) {

                // Tipo citacion (Medio de imposicion)
                respuesta.setTipoCitacion(Integer.parseInt(objects2[0].toString()));
                ItemCatalogoDTO itemCatalogoTCDTO = consultarItemCatalogo(MedioImposicionComparendo.class.getName(),
                        respuesta.getTipoCitacion());
                respuesta.setNombreTipoCitacion(itemCatalogoTCDTO.getNombre());

                respuesta.setNumeroCitacion((objects2[1] == null ? null : objects2[1].toString()));
                respuesta.setFechaImposicion((Date) objects2[2]);
                respuesta.setPlacaVehiculo((objects2[3] == null ? null : objects2[3].toString()));
                respuesta.setDireccionInfraccion((objects2[4] == null ? null : objects2[4].toString()));
                respuesta.setEstadoCitacion(objects2[5].toString());

                // Tipo Identificacion
                respuesta.setIdIdentificacion(objects2[6].toString());
                ItemCatalogoDTO itemCatalogoTIPDTO = consultarItemCatalogo(TipoIdentificacionPersona.class.getName(),
                        respuesta.getIdIdentificacion());
                respuesta.setNombreTipoIdentificacion(itemCatalogoTIPDTO.getNombre());

                respuesta.setIdentificacion(objects2[7].toString());
                respuesta.setNombreInfractor(objects2[8].toString());
                respuesta.setDescripcionInfraccion(objects2[9].toString());
                respuesta.setSaldoComparendo(new BigDecimal(objects2[10].toString()));
                respuesta.setValorIntereses(new BigDecimal(objects2[11].toString()));
                respuesta.setTotalSaldo(new BigDecimal(objects2[12].toString()));

                // Fecha pago
                if (respuesta.getEstadoCitacion().equals(ESTADO_COMPARENDO_PAGADO)) {
                    respuesta.setFechaPago(consultarFechaFinalDetallePago(numeroComparendo));
                }
            }
        }

        return respuesta;
    }

    /**
     * Se encarga de consultar la fecha final de pago
     * 
     * @author giovanni.velandia
     * @return
     */
    private Date consultarFechaFinalDetallePago(String numeroFactura) {
        LOGGER.debug("consultarFechaFinalDetallePago(String)");

        if (numeroFactura == null) {
            return null;
        }

        Date fechaFinalPago = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT");
        sql.append(" dp.FECHA_HORA_PAGO");
        sql.append(" FROM V_DETALLES_PAGOS dp");
        sql.append(" WHERE dp.NUMERO_DOCUMENTO = :numeroFactura");

        Query query = emIt.createNativeQuery(sql.toString());
        query.setParameter("numeroFactura", numeroFactura);

        @SuppressWarnings("unchecked")
        List<Timestamp> objects = query.getResultList();
        if (objects != null) {
            for (Timestamp object : objects) {
                Calendar calFechaFinalPago = Calendar.getInstance();
                calFechaFinalPago.setTime(object);
                fechaFinalPago = calFechaFinalPago.getTime();
            }
        }
        return fechaFinalPago;
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

    @Override
    public List<EvidenciasDTO> rutaEvidencias(Long facturaAxis) {
        LOGGER.debug("rutaEvidencias()");
        List<EvidenciasDTO> evidencias = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT url FROM evidencia e ");
        sql.append("INNER JOIN comparendo c ON c.cicomparendo = e.cicomparendo ");
        sql.append("WHERE c.id_factura_axis = :facturaAxis");

        Query query = em.createNativeQuery(sql.toString());

        query.setParameter("facturaAxis", facturaAxis);

        @SuppressWarnings("unchecked")
        List<String> objects = query.getResultList();
        if (objects != null && !objects.isEmpty()) {
            evidencias = new ArrayList<>();
            for (String objeto : objects) {
                EvidenciasDTO evidenciasDTO = new EvidenciasDTO();
                evidenciasDTO.setRuta(objeto);
                evidencias.add(evidenciasDTO);
            }
        }

        return evidencias;
    }

}
