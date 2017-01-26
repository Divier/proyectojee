package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EvidenciaObligacionSacDTO;
import co.com.datatools.c2.dto.ObligacionSacDTO;
import co.com.datatools.c2.entidades.EvidenciaObligacionSac;
import co.com.datatools.c2.entidades.ObligacionSac;
/**
 * @author Generated
 * @version 3.0 - Wed May 18 18:16:50 COT 2016
 */
public class ObligacionSacHelper { 
	// --- to DTO
	public static ObligacionSacDTO toLevel0DTO(ObligacionSac entidad) {
		ObligacionSacDTO dto = new ObligacionSacDTO();
		dto.setId(entidad.getId());
		dto.setAnioFactura(entidad.getAnioFactura());
		dto.setFechaFactura(entidad.getFechaFactura());
		dto.setFechaNotificacion(entidad.getFechaNotificacion());
		dto.setFechaPagoOportuna(entidad.getFechaPagoOportuna());
		dto.setFechaRegistro(entidad.getFechaRegistro());
		dto.setHoraFactura(entidad.getHoraFactura());
		dto.setIdCartera(entidad.getIdCartera());
		dto.setIdEstadoProcesoMulta(entidad.getIdEstadoProcesoMulta());
		dto.setIdEstadoTransaccionSac(entidad.getIdEstadoTransaccionSac());
		dto.setIdOrigenFacturaSac(entidad.getIdOrigenFacturaSac());
		dto.setIdTipoDocumentoSac(entidad.getIdTipoDocumentoSac());
		dto.setIdTipoGestionSac(entidad.getIdTipoGestionSac());
		dto.setIdTipoNotificacionSac(entidad.getIdTipoNotificacionSac());
		dto.setNumeroCuota(entidad.getNumeroCuota());
		dto.setNumeroDocumentoDeudor(entidad.getNumeroDocumentoDeudor());
		dto.setNumeroFactura(entidad.getNumeroFactura());
		dto.setPrimerApellidoDeudor(entidad.getPrimerApellidoDeudor());
		dto.setPrimerNombreDeudor(entidad.getPrimerNombreDeudor());
		dto.setRazonSocial(entidad.getRazonSocial());
		dto.setSaldoTotalDeuda(entidad.getSaldoTotalDeuda());
		dto.setSegundoApellidoDeudor(entidad.getSegundoApellidoDeudor());
		dto.setSegundoNombreDeudor(entidad.getSegundoNombreDeudor());
		dto.setValorCobrar(entidad.getValorCobrar());
		dto.setValorInteresFinanciacion(entidad.getValorInteresFinanciacion());
		dto.setValorRecargoGenerado(entidad.getValorRecargoGenerado());
		dto.setValorTasaGenerada(entidad.getValorTasaGenerada());
		dto.setFechaCambio(entidad.getFechaCambio());
		dto.setNumeroCitacion(entidad.getNumeroCitacion()); 
		return dto;
	}
	public static ObligacionSacDTO toLevel1DTO(ObligacionSac entidad) {
		ObligacionSacDTO dto = toLevel0DTO(entidad);

		return dto;
	}
	public static List<ObligacionSacDTO> toListLevel0DTO(List<ObligacionSac> listEntidad) {
		List<ObligacionSacDTO> listDto = new ArrayList<ObligacionSacDTO>();
		for (ObligacionSac entidad : listEntidad) {
			listDto.add(toLevel0DTO(entidad));
		}
		return listDto;
	}
	public static List<ObligacionSacDTO> toListLevel1DTO(List<ObligacionSac> listEntidad) {
		List<ObligacionSacDTO> listDto = new ArrayList<ObligacionSacDTO>();
		for (ObligacionSac entidad : listEntidad) {
			listDto.add(toLevel1DTO(entidad));
		}
		return listDto;
	}
	// --- fin to DTO
	// --- to Entidad
	public static ObligacionSac toLevel0Entity(ObligacionSacDTO dto, ObligacionSac entidad) {
		if (null == entidad) {
			entidad = new ObligacionSac();
		}
		entidad.setId(dto.getId());
		entidad.setAnioFactura(dto.getAnioFactura());
		entidad.setFechaFactura(dto.getFechaFactura());
		entidad.setFechaNotificacion(dto.getFechaNotificacion());
		entidad.setFechaPagoOportuna(dto.getFechaPagoOportuna());
		entidad.setFechaRegistro(dto.getFechaRegistro());
		entidad.setHoraFactura(dto.getHoraFactura());
		entidad.setIdCartera(dto.getIdCartera());
		entidad.setIdEstadoProcesoMulta(dto.getIdEstadoProcesoMulta());
		entidad.setIdEstadoTransaccionSac(dto.getIdEstadoTransaccionSac());
		entidad.setIdOrigenFacturaSac(dto.getIdOrigenFacturaSac());
		entidad.setIdTipoDocumentoSac(dto.getIdTipoDocumentoSac());
		entidad.setIdTipoGestionSac(dto.getIdTipoGestionSac());
		entidad.setIdTipoNotificacionSac(dto.getIdTipoNotificacionSac());
		entidad.setNumeroCuota(dto.getNumeroCuota());
		entidad.setNumeroDocumentoDeudor(dto.getNumeroDocumentoDeudor());
		entidad.setNumeroFactura(dto.getNumeroFactura());
		entidad.setPrimerApellidoDeudor(dto.getPrimerApellidoDeudor());
		entidad.setPrimerNombreDeudor(dto.getPrimerNombreDeudor());
		entidad.setRazonSocial(dto.getRazonSocial());
		entidad.setSaldoTotalDeuda(dto.getSaldoTotalDeuda());
		entidad.setSegundoApellidoDeudor(dto.getSegundoApellidoDeudor());
		entidad.setSegundoNombreDeudor(dto.getSegundoNombreDeudor());
		entidad.setValorCobrar(dto.getValorCobrar());
		entidad.setValorInteresFinanciacion(dto.getValorInteresFinanciacion());
		entidad.setValorRecargoGenerado(dto.getValorRecargoGenerado());
		entidad.setValorTasaGenerada(dto.getValorTasaGenerada());
		entidad.setFechaCambio(dto.getFechaCambio());
		entidad.setNumeroCitacion(dto.getNumeroCitacion()); 
		return entidad;
	}
	public static ObligacionSac toLevel1Entity(ObligacionSacDTO dto, ObligacionSac entidad) {
		entidad = toLevel0Entity(dto, entidad);
		EvidenciaObligacionSac evidenciaObligacionSac = null;
		List<EvidenciaObligacionSac> listaEvidencias = new ArrayList<EvidenciaObligacionSac>();
		List<EvidenciaObligacionSacDTO> listaValores = dto.getEvidenciaObligacionSacs();
		if(listaValores != null && !listaValores.isEmpty()) {
			for(EvidenciaObligacionSacDTO evidenciaSac: dto.getEvidenciaObligacionSacs()) {
				evidenciaObligacionSac = new EvidenciaObligacionSac();
				evidenciaObligacionSac.setNombre(evidenciaSac.getNombre());
				evidenciaObligacionSac.setUrl(evidenciaSac.getUrl());
				evidenciaObligacionSac.setNumeroCitacion(evidenciaSac.getNumeroCitacion());
				evidenciaObligacionSac.setNumeroFactura(evidenciaSac.getNumeroFactura());
				evidenciaObligacionSac.setObligacionSac(entidad); 			
				listaEvidencias.add(evidenciaObligacionSac);
			}
			entidad.setEvidenciaObligacionSacs(listaEvidencias); 	
		}		
		return entidad;
	}
	public static List<ObligacionSac> toListLevel0Entity(List<ObligacionSacDTO> listDto) {
		List<ObligacionSac> listEntidad = new ArrayList<ObligacionSac>();
		for (ObligacionSacDTO dto : listDto) {
			listEntidad.add(toLevel0Entity(dto, null));
		}
		return listEntidad;
	}
	public static List<ObligacionSac> toListLevel1Entity(List<ObligacionSacDTO> listDto) {
		List<ObligacionSac> listEntidad = new ArrayList<ObligacionSac>();
		for (ObligacionSacDTO dto : listDto) {
			listEntidad.add(toLevel1Entity(dto, null));
		}
		return listEntidad;
	}
	// --- fin to Entidad
}
