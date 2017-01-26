package co.com.datatools.c2.managed_bean.coactivo.asignar_secretario;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.negocio.interfaces.IRFuncionario;
import co.com.datatools.c2.numeraciones.EnumCargo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

@ManagedBean
@SessionScoped
public class AsignarSecretarioMB extends AbstractC2ManagedBean {

    // Logger
    private static final Logger LOGGER = Logger.getLogger(AsignarSecretarioMB.class.getName());

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String BUNDLE_COACTIVO = "labelCoactivo";
    private String mensajeConfirmacionGuardado;

    @EJB
    private IRFuncionario iRFuncionario;

    /**
     * Consulta todos los funcionarios vigentes para cargarlos en la vista
     */
    public void consultarFuncionarios() {
        LOGGER.debug("AsignarSecretarioMB::consultarFuncionarios()");

        AsignarSecretarioFL asignarSecretarioFL = findFlowObject(AsignarSecretarioFL.class,
                AsignarSecretarioFL.NOMBRE_BEAN);
        asignarSecretarioFL.setFuncionario(null);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setIdCargo(EnumCargo.ABOGADO.getValue());
        List<FuncionarioDTO> lstFuncionarioDTO = iRFuncionario.consultarFuncionarios(funcionarioDTO, new Date());

        if (!lstFuncionarioDTO.isEmpty()) {
            Map<Integer, FuncionarioDTO> funcionariosMap = new HashMap<Integer, FuncionarioDTO>();
            List<SelectItem> funcionariosItem = new ArrayList<SelectItem>();
            for (FuncionarioDTO funcionario : lstFuncionarioDTO) {
                funcionariosMap.put(funcionario.getId(), funcionario);
                funcionariosItem.add(new SelectItem(funcionario.getId(), funcionario.getPersona().getNombreCompleto()));
            }
            asignarSecretarioFL.setFuncionarioDTOs(funcionariosMap);
            asignarSecretarioFL.setFuncionarios(funcionariosItem);
        } else {
            addErrorMessage(BUNDLE_COACTIVO, "msg_error_no_hay_funcionarios");
        }
    }

    /**
     * Actualiza los datos del funcionario seleccionado
     */
    public void actualizarFuncionario() {
        LOGGER.debug("AsignarSecretarioMB::actualizarFuncionario()");

        AsignarSecretarioFL asignarSecretarioFL = findFlowObject(AsignarSecretarioFL.class,
                AsignarSecretarioFL.NOMBRE_BEAN);

        asignarSecretarioFL.setFuncionario(null);

        if (asignarSecretarioFL.getIdFuncionario() != null) {
            FuncionarioDTO funcionario = asignarSecretarioFL.getFuncionarioDTOs()
                    .get(asignarSecretarioFL.getIdFuncionario());
            asignarSecretarioFL.setFuncionario(funcionario);
            mensajeConfirmacionGuardado = MessageFormat.format(
                    getBundle(BUNDLE_COACTIVO).getString("msg_confirmacion_guardar"),
                    funcionario.getPersona().getNombreCompleto());
        } else {
            addErrorMessage(BUNDLE_COACTIVO, "msg_error_secretario_seleccionado");
        }
    }

    public String getMensajeConfirmacionGuardado() {
        return mensajeConfirmacionGuardado;
    }

    public void setMensajeConfirmacionGuardado(String mensajeConfirmacionGuardado) {
        this.mensajeConfirmacionGuardado = mensajeConfirmacionGuardado;
    }
}
