package co.com.datatools.c2.negocio.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dt.administracion.ConsultaDireccionDTO;
import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.RespuestaIngresarDireccionDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.enumeracion.EnumCampoDireccion;
import co.com.datatools.c2.enumeracion.EnumErrorDireccion;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILAdministracion;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionWebService;
import co.com.datatools.c2.negocio.interfaces.ILConsecutivo;
import co.com.datatools.c2.negocio.interfaces.ILDiaNoHabil;
import co.com.datatools.c2.negocio.interfaces.ILDireccion;
import co.com.datatools.c2.negocio.interfaces.ILFuenteConsulta;
import co.com.datatools.c2.negocio.interfaces.ILParametro;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.numeraciones.EnumTipoValidacionDireccion;
import co.com.datatools.util.date.UtilFecha;

@Stateless(name = "FachadaAdminGeneralEJB")
@LocalBean
public class FachadaAdminGeneralEJB implements IRFachadaAdminGeneral {
    private final static Logger logger = Logger.getLogger(FachadaAdminGeneralEJB.class.getName());

    @EJB
    private ILDiaNoHabil iLDiaNoHabil;
    @EJB
    private ILParametro iLParametro;
    @EJB
    private ILAdministracion iLAdministracion;
    @EJB
    private ILDireccion iLDireccion;
    @EJB
    private ILConsecutivo iLConsecutivo;
    @EJB
    private ILAdministracionWebService administracionWebServiceEJB;
    @EJB
    private ILFuenteConsulta iFuenteConsulta;

    @Override
    public ValorParametroDTO consultarValorParametro(EnumParametro enumParametro, int codigoOrganismo,
            boolean requerido) {
        logger.debug("FachadaAdminGeneralEJB::consultarValorParametro(EnumParametro,int,boolean)");
        return iLParametro.consultarParametro(enumParametro, codigoOrganismo, requerido);
    }

    @Override
    public Date sumarDias(int codigoOrganismo, Date fecha, int cantidadDias, boolean diaHabil) {
        logger.debug("FachadaAdminGeneralEJB::sumarDias(int,Date,int,boolean)");
        Date date = null;
        // Verificamos si son con dias habiles o todos los dias del calendario
        if (diaHabil) {
            date = iLDiaNoHabil.sumarDiasHabiles(codigoOrganismo, fecha, cantidadDias);
        } else {
            date = UtilFecha.sumarDias(fecha, cantidadDias);
        }
        return date;
    }

    @Override
    public boolean validarNumeroDocumento(String numeroDocumento, int idTipoDocumento, Date fechaValidacion)
            throws CirculemosNegocioException {
        logger.debug("FachadaAdminGeneralEJB::validarNumeroDocumento(String,int)");
        // Convertir el id formato documento en una enumeracion
        // EnumFormatoDocumentoIdentidad enumFormatoDocumentoIdentidad = Utilidades.buscarElemEnum(
        // EnumFormatoDocumentoIdentidad.class, idFormatoDocumento);
        // return iLAdministracion.validarNumeroDocumento(numeroDocumento, enumFormatoDocumentoIdentidad);
        return iLAdministracion.validarNumeroDocumento(numeroDocumento, idTipoDocumento, fechaValidacion);
    }

    @Override
    public RespuestaDTO<EnumCampoDireccion, EnumErrorDireccion> validarDireccion(DireccionDTO direccion,
            EnumTipoValidacionDireccion enumTipoValidacionDireccion) {
        logger.debug("FachadaAdminGeneralEJB::validarDireccion(DireccionDTO)");
        return iLDireccion.validarDireccion(direccion, enumTipoValidacionDireccion);
    }

    @Override
    public RespuestaIngresarDireccionDTO registrarDireccion(DireccionDTO direccionDTO,
            EnumTipoValidacionDireccion enumTipoValidacionDireccion) {
        logger.debug("FachadaAdminGeneralEJB::registrarDireccion(DireccionDTO)");
        return iLDireccion.ingresarDireccion(direccionDTO, enumTipoValidacionDireccion);
    }

    @Override
    public String generarConsecutivo(int codigoOrganismo, EnumConsecutivo... tipoConsecutivo) {
        logger.debug("FachadaAdminGeneralEJB::generarConsecutivo(int,EnumConsecutivo)");
        return iLConsecutivo.generarConsecutivo(codigoOrganismo, tipoConsecutivo);
    }

    @Override
    public List<DireccionDTO> consultarDireccion(ConsultaDireccionDTO consultaDireccionDTO) {
        logger.debug("FachadaAdminGeneralEJB::consultarDireccion(ConsultaDireccionDTO)");
        DireccionDTO direccionDTO;
        List<DireccionDTO> direcciones = new ArrayList<>();
        if (consultaDireccionDTO.getIdDireccion() != null) {
            direccionDTO = iLDireccion.consultarDireccion(consultaDireccionDTO.getIdDireccion());
            if (direccionDTO != null) {
                direcciones.add(direccionDTO);
            }
        }
        return direcciones;
    }

    @Override
    public WebServiceDTO consultarWebService(Integer idWebService) {
        logger.debug(FachadaAdminGeneralEJB.class.getName().concat("::consultarWebService(Integer)"));
        return administracionWebServiceEJB.consultarWebServices(idWebService);
    }

    @Override
    public List<RespuestaWebServiceDTO> consultarRespuestasWebService(Integer idWebService) {
        logger.debug(FachadaAdminGeneralEJB.class.getName().concat("::consultarRespuestasWebService(Integer)"));
        return administracionWebServiceEJB.consultarRespuestasWebService(idWebService);
    }

    @Override
    public List<DetalleFuenteConsultaDTO> consultarDetalleFuenteConsulta(int codigoOrganismo,
            int idTipoFuenteConsulta) {
        logger.debug(
                FachadaAdminGeneralEJB.class.getName().concat("::consultarDetalleFuenteConsulta(Integer, Integer)"));
        return iFuenteConsulta.consultarDetallesFuenteConsulta(codigoOrganismo, idTipoFuenteConsulta);
    }

    @Override
    public boolean esDiaNoHabil(int codigoOrganismo, Date fecha) {
        logger.debug(FachadaAdminGeneralEJB.class.getName().concat("::esDiaNoHabil(int, Date)"));
        return iLDiaNoHabil.esDiaNoHabil(codigoOrganismo, fecha);
    }

    @Override
    public LogEjecucionWSDTO registrarLogWS(LogEjecucionWSDTO log) {
        logger.debug(FachadaAdminGeneralEJB.class.getName().concat("::registrarLogWS(LogEjecucionWSDTO)"));
        return administracionWebServiceEJB.registrarLogWS(log);
    }

}
