package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.AccidentalidadDTO;
import co.com.datatools.c2.dto.DatosAccidentalidadDTO;
import co.com.datatools.c2.dto.FiltrosAccidentalidadDTO;
import co.com.datatools.c2.dto.RespuestaAccidentalidadDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRAccidentalidad {

    public String registrarAccidentalidad(DatosAccidentalidadDTO accidentalidad) throws CirculemosNegocioException;

    public List<AccidentalidadDTO> consultarAccidentalidad(AccidentalidadDTO accidentalidad);

    public DatosAccidentalidadDTO consultarAccidentalidad(Long idInforme);

    public void modificarAccidentalidad(DatosAccidentalidadDTO accidentalidad) throws CirculemosAlertaException;

    public List<RespuestaAccidentalidadDTO> consultarAccidentalidadInforme(
            FiltrosAccidentalidadDTO filtrosAccidentalidad);

    public void guardarCapturaInforme(String consecutivo, byte[] capturaInforme) throws CirculemosNegocioException;

}
