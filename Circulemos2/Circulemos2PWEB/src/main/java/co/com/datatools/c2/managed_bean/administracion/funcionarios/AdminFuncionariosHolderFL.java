package co.com.datatools.c2.managed_bean.administracion.funcionarios;

import co.com.datatools.c2.dto.personas.FuncionarioDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class AdminFuncionariosHolderFL extends AbstractC2ManagedBean {
	private static final long serialVersionUID = 1L;
	public static final String NOMBRE_BEAN = "adminFuncionarioHolderFL";
	
	private FuncionarioDTO funcionario;
	
	public AdminFuncionariosHolderFL(){
		funcionario = new FuncionarioDTO();
		PersonaDTO persona = new PersonaDTO();
		persona.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
		funcionario.setPersona(persona);
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
}
