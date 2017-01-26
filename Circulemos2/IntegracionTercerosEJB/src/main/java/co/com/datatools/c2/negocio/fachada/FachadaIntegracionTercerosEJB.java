package co.com.datatools.c2.negocio.fachada;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.dto.ubicabilidad.RespuestaSolicitudUbicabilidadTerceroDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoLectura;
import co.com.datatools.c2.negocio.interfaces.ILComparendoIntegracionTercero;
import co.com.datatools.c2.negocio.interfaces.ILUbicabilidadTercero;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacionIntegracion;
import co.com.datatools.c2.negocio.interfaces.IRRecaudoIntegracion;

@Stateless(name = "FachadaIntegracionTercerosEJB")
@LocalBean
public class FachadaIntegracionTercerosEJB implements IRFachadaIntegracionTerceros {
    private final static Logger logger = Logger.getLogger(FachadaIntegracionTercerosEJB.class.getName());

    @EJB
    private IRRecaudoIntegracion recaudoIntegracionEJB;
    @EJB
    private ILComparendoIntegracionTercero comparendoTerceroEJB;
    @EJB
    private ILUbicabilidadTercero ubicabilidadTerceroEJB;
    @EJB
    private IRFinanciacionIntegracion financiacionIntegracionTerceroEJB;

    @Override
    public List<ItRecaudoDTO> consultarRecaudos(int codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug("FachadaIntegracionTercerosEJB.consultarRecaudos(int)");
        return recaudoIntegracionEJB.consultarRecaudos(codigoOrganismo, estadoLectura);
    }

    @Override
    public void actualizarEstadoRecaudo(Long idRecaudo, EnumEstadoLectura estadoLectura) {
        logger.debug("FachadaIntegracionTercerosEJB.actualizarEstadoRecaudos(List<RecaudoDTO>)");
        recaudoIntegracionEJB.actualizarEstadoRecaudo(idRecaudo, estadoLectura);
    }

    @Override
    public List<ItMultaDTO> consultarMultas(int codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat("::consultarMultas(int,EnumEstadoLectura)"));
        return comparendoTerceroEJB.consultarMultas(codigoOrganismo, estadoLectura);
    }

    @Override
    public void actualizarEstadoMulta(long idMulta, EnumEstadoLectura estadoLectura) {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat(
                "::actualizarEstadoMulta(long,EnumEstadoLectura)"));
        comparendoTerceroEJB.actualizarEstadoMulta(idMulta, estadoLectura);
    }

    public List<ItFinanciacionDTO> consultarFinanciaciones(Integer codigoOrganismo, EnumEstadoLectura estadoLectura) {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat(
                "::consultarFinanciaciones(Integer, EnumEstadoLectura)"));
        return financiacionIntegracionTerceroEJB.consultarFinanciaciones(codigoOrganismo, estadoLectura);
    }

    public void actualizarEstadoFinanciacion(Long numeroFinanciacion, EnumEstadoLectura estadoLectura) {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat(
                "::actualizarEstadoFinanciacion(Long,EnumEstadoLectura)"));
        financiacionIntegracionTerceroEJB.actualizarEstadoFinanciacion(numeroFinanciacion, estadoLectura);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public RespuestaSolicitudUbicabilidadTerceroDTO solicitarUbicabilidadTerceros(int codigoOrganismo) {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat("::solicitarUbicabilidadTerceros(int)"));
        return ubicabilidadTerceroEJB.solicitarUbicabilidadTerceros(codigoOrganismo);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void cargarEvidenciasComparendosTerceros() {
        logger.debug(FachadaIntegracionTercerosEJB.class.getName().concat("::cargarEvidenciasComparendosTerceros()"));
        comparendoTerceroEJB.cargarEvidenciasComparendosTerceros();
    }
}