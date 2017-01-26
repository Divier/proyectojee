package co.com.datatools.c2.negocio.ejb;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.FuncionarioDocumentoDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.negocio.error.ErrorDocumentos;
import co.com.datatools.c2.negocio.interfaces.ILSincronizacionDocumentos;
import co.com.datatools.c2.negocio.interfaces.IRSincronizacionDocumentos;
import co.com.datatools.documentos.constantes.ConstantesGeneral;
import co.com.datatools.documentos.negocio.interfaces.IDocumentosWS;
import co.com.datatools.documentos.ws.FuncionarioVO;
import co.com.datatools.documentos.ws.UsuarioVO;
import co.com.datatools.documentos.ws.exception.DocumentosWebException;

@Stateless(name = "SincronizacionDocumentosEJB")
@LocalBean
public class SincronizacionDocumentosEJB implements IRSincronizacionDocumentos, ILSincronizacionDocumentos {

    private final static Logger logger = Logger.getLogger(SincronizacionDocumentosEJB.class.getName());

    private static final String INTEGRACION_DOCUMENTOS_JNDI_NAME = "ejb:DocumentosEAR-"
            + ConstantesGeneral.VERSION_DOCUMENTOS
            + "/IntegracionEJB/DocumentosWSEJB!co.com.datatools.documentos.negocio.interfaces.IDocumentosWS";


    /**
     * Usuario para autenticarse en documentos
     */
    private static final String USUARIO_DOCUMENTOS = "admin";
    private static final String PWD_USUARIO_DOCUMENTOS = "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec";


    private IDocumentosWS iDocumentosWS;
    private final Integer idCargoAgente = 5;


    public SincronizacionDocumentosEJB() {
        try {
            final Hashtable<String, String> props = new Hashtable<String, String>();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);
            // lookup the bean
            iDocumentosWS = (IDocumentosWS) context.lookup(INTEGRACION_DOCUMENTOS_JNDI_NAME);
        } catch (NamingException e) {
            logger.error("Error localizando Jndi EJB", e);
        }
    }

    @Override
    public void registrarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException {

        if (funcionarioDocumento.getNumeroIdentificacion() == null || funcionarioDocumento.getNombre() == null
                || funcionarioDocumento.getFechaInicialFuncionario() == null
                || funcionarioDocumento.getTipoIdentificacion() == null
                || funcionarioDocumento.getFechaInicialCargo() == null || funcionarioDocumento.getFirma() == null) {
            throw new CirculemosAlertaException(ErrorDocumentos.Sincronizacion.ADM_000001);

        }

        // Usuario de servicio
        UsuarioVO usuario = new UsuarioVO();
        usuario.setUsuario(USUARIO_DOCUMENTOS);
        usuario.setClave(PWD_USUARIO_DOCUMENTOS);

        // Funcionario de servicio
        FuncionarioVO funcionario = construirFuncionarioVO(funcionarioDocumento);

        try {
            // Registro de funcionario
            iDocumentosWS.registrarFuncionario(funcionario, usuario);
        } catch (DocumentosWebException e) {
            logger.error("Error al generar documento", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Sincronizacion.ADM_000002);
        }
    }

    /**
     * Crea el VO del funcionario
     * 
     * @param funcionarioDocumento
     * @return
     */
    private FuncionarioVO construirFuncionarioVO(FuncionarioDocumentoDTO funcionarioDocumento) {
        // Formato de fechas
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantesGeneral.DATE_FORMAT);

        // Funcionario
        FuncionarioVO funcionario = new FuncionarioVO();
        funcionario.setFechaFinalFuncionario(funcionarioDocumento.getFechaFinalFuncionario() == null ? null
                : sdf.format(funcionarioDocumento.getFechaFinalFuncionario()));
        funcionario.setFechaFinCargo(funcionarioDocumento.getFechaFinalCargo() == null ? null
                : sdf.format(funcionarioDocumento.getFechaFinalCargo()));
        funcionario.setFechaInicialFuncionario(funcionarioDocumento.getFechaInicialFuncionario() == null ? null
                : sdf.format(funcionarioDocumento.getFechaInicialFuncionario()));
        funcionario.setFechaInicioCargo(funcionarioDocumento.getFechaInicialCargo() == null ? null
                : sdf.format(funcionarioDocumento.getFechaInicialCargo()));
        funcionario.setFirma(funcionarioDocumento.getFirma());
        funcionario.setIdCargo(idCargoAgente);
        funcionario.setNombreFuncionario(funcionarioDocumento.getNombre());
        funcionario.setNombreTipoIdentificacion(funcionarioDocumento.getTipoIdentificacion().getNombre());
        funcionario.setNumeroDocumIdent(funcionarioDocumento.getNumeroIdentificacion());
        funcionario.setSiglaTipoIdentificacion(funcionarioDocumento.getTipoIdentificacion().getSigla());

        return funcionario;
    }

    @Override
    public void actualizarAgente(FuncionarioDocumentoDTO funcionarioDocumento) throws CirculemosAlertaException {

        if (funcionarioDocumento.getNumeroIdentificacion() == null || funcionarioDocumento.getNombre() == null
                || funcionarioDocumento.getFechaInicialFuncionario() == null
                || funcionarioDocumento.getTipoIdentificacion() == null
                || funcionarioDocumento.getFechaInicialCargo() == null || funcionarioDocumento.getFirma() == null) {
            throw new CirculemosAlertaException(ErrorDocumentos.Sincronizacion.ADM_000001);

        }

        // Usuario de servicio
        UsuarioVO usuario = new UsuarioVO();
        usuario.setUsuario(USUARIO_DOCUMENTOS);
        usuario.setClave(PWD_USUARIO_DOCUMENTOS);

        // Funcionario de servicio
        FuncionarioVO funcionario = construirFuncionarioVO(funcionarioDocumento);

        try {
            // Registro de funcionario
            iDocumentosWS.actualizarFuncionario(funcionario, usuario);
        } catch (DocumentosWebException e) {
            logger.error("Error al generar documento", e);
            throw new CirculemosAlertaException(ErrorDocumentos.Sincronizacion.ADM_000003);
        }
    }

}
