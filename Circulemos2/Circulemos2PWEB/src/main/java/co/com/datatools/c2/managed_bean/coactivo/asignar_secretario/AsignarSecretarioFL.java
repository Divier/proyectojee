package co.com.datatools.c2.managed_bean.coactivo.asignar_secretario;

import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class AsignarSecretarioFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "asignarSecretarioFL";
    private FuncionarioDTO funcionario;
    private List<SelectItem> funcionarios;
    private Integer idFuncionario;
    private Map<Integer, FuncionarioDTO> funcionarioDTOs;

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    public List<SelectItem> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<SelectItem> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Map<Integer, FuncionarioDTO> getFuncionarioDTOs() {
        return funcionarioDTOs;
    }

    public void setFuncionarioDTOs(Map<Integer, FuncionarioDTO> funcionarioDTOs) {
        this.funcionarioDTOs = funcionarioDTOs;
    }

}
