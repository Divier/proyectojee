package co.com.datatools.c2.dto;
import java.util.List;
import java.util.Date;
import java.math.*;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.util.EntidadDtoC2;
/**
 * @author Generated
 * @version 3.0 - Mon Oct 10 15:46:45 COT 2016
 */
public class ConfiguracionSoporteDTO implements EntidadDtoC2 { 
  private static final long serialVersionUID = 1L;
// --- Atributos
private Long id;
private SoporteProcedimientoDTO soporte;
private String configuracionEjecucion;
private String respuesta;

// --- Constructor
public ConfiguracionSoporteDTO () {
}

public ConfiguracionSoporteDTO (Long id ) {
  this.id=id;

}


// --- Getters-Setters
public Long getId () {
  return this.id;
}

public void setId (Long id) {
  this.id=id;
}

public SoporteProcedimientoDTO getSoporte () {
  return this.soporte;
}

public void setSoporte (SoporteProcedimientoDTO soporte) {
  this.soporte=soporte;
}

public String getConfiguracionEjecucion () {
  return this.configuracionEjecucion;
}

public void setConfiguracionEjecucion (String configuracionEjecucion) {
  this.configuracionEjecucion=configuracionEjecucion;
}

public String getRespuesta () {
  return this.respuesta;
}

public void setRespuesta (String respuesta) {
  this.respuesta=respuesta;
}


}
