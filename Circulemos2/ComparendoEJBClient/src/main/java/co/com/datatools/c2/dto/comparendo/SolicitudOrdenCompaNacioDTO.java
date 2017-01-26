package co.com.datatools.c2.dto.comparendo;
import java.util.List;
import java.util.Date;
import java.math.*;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.util.EntidadDtoC2;
/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 15:26:12 COT 2016
 */
public class SolicitudOrdenCompaNacioDTO implements EntidadDtoC2 { 
  private static final long serialVersionUID = 1L;
// --- Atributos
private Long id;
private Date fechaConsumo;
private Date fechaImposicion;
private Date fechaRegistro;
private Date horaImposicion;
private Integer idAgente;
private Integer idUsuario;
private String identificacionVehiculo;
private String numeroComparendo;
private String placaAgente;
private String placaVehiculo;

// --- Constructor
public SolicitudOrdenCompaNacioDTO () {
}

public SolicitudOrdenCompaNacioDTO (Long id ) {
  this.id=id;

}


// --- Getters-Setters
public Long getId () {
  return this.id;
}

public void setId (Long id) {
  this.id=id;
}

public Date getFechaConsumo () {
  return this.fechaConsumo;
}

public void setFechaConsumo (Date fechaConsumo) {
  this.fechaConsumo=fechaConsumo;
}

public Date getFechaImposicion () {
  return this.fechaImposicion;
}

public void setFechaImposicion (Date fechaImposicion) {
  this.fechaImposicion=fechaImposicion;
}

public Date getFechaRegistro () {
  return this.fechaRegistro;
}

public void setFechaRegistro (Date fechaRegistro) {
  this.fechaRegistro=fechaRegistro;
}

public Date getHoraImposicion () {
  return this.horaImposicion;
}

public void setHoraImposicion (Date horaImposicion) {
  this.horaImposicion=horaImposicion;
}

public Integer getIdAgente () {
  return this.idAgente;
}

public void setIdAgente (Integer idAgente) {
  this.idAgente=idAgente;
}

public Integer getIdUsuario () {
  return this.idUsuario;
}

public void setIdUsuario (Integer idUsuario) {
  this.idUsuario=idUsuario;
}

public String getIdentificacionVehiculo () {
  return this.identificacionVehiculo;
}

public void setIdentificacionVehiculo (String identificacionVehiculo) {
  this.identificacionVehiculo=identificacionVehiculo;
}

public String getNumeroComparendo () {
  return this.numeroComparendo;
}

public void setNumeroComparendo (String numeroComparendo) {
  this.numeroComparendo=numeroComparendo;
}

public String getPlacaAgente () {
  return this.placaAgente;
}

public void setPlacaAgente (String placaAgente) {
  this.placaAgente=placaAgente;
}

public String getPlacaVehiculo () {
  return this.placaVehiculo;
}

public void setPlacaVehiculo (String placaVehiculo) {
  this.placaVehiculo=placaVehiculo;
}


}
