package co.com.datatools.c2.managed_bean.comparendo.reincidencia;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import co.com.datatools.c2.dto.comparendo.ConsultaReincidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaReincidenciaDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoReincidencia;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILReincidencia;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

@ManagedBean
public class ConsultaReincidenciaMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private ConsultaReincidenciaFL consultaReincidenciaFL;

    @EJB
    private ILReincidencia iReincidencia;

    public void consultarReincidencia() {
        consultaReincidenciaFL = findFlowObject(ConsultaReincidenciaFL.class, "consultaReincidenciaFL");

        ConsultaReincidenciaDTO consulta = new ConsultaReincidenciaDTO();
        consulta.setCodigoOrganismo(consultaReincidenciaFL.getCodigoOrganismo());
        consulta.setIdTipoDocumento(consultaReincidenciaFL.getIdTipoDocInfractor());
        consulta.setNumeroDocumento(consultaReincidenciaFL.getNumDocInfractor());
        consulta.setTipoReincidencia(Utilidades.buscarElemEnum(EnumTipoReincidencia.class,
                consultaReincidenciaFL.getIdTipoReincidencia()));

        try {
            ResultadoConsultaReincidenciaDTO resultado = iReincidencia.consultarReincidencias(consulta);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

}
