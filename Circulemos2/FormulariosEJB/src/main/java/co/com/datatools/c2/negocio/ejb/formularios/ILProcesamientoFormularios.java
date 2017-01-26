package co.com.datatools.c2.negocio.ejb.formularios;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Local;

import co.com.datatools.c2.dto.formularios.SeguimientoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.entidades.SeguimientoFormulario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILProcesamientoFormularios {

    Future<List<SeguimientoFormulario>> validarConfiguracionEstados(
            List<SeguimientoFormularioDTO> seguimientoFormularios, TipoFormularioDTO tipoFormularioDTO, int i,
            boolean validarResponsable, Integer idEstadoSiguiente, boolean validarConfiguracionEstados)
            throws CirculemosNegocioException;

}
