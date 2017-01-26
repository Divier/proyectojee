package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.entidades.ProcesaDireccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.enumeraciones.EnumTipoOrigenComparendo;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Apr 26 13:37:20 COT 2016
 */
public class ProcesaComparendoHelper {
    // --- to DTO
    public static ProcesaComparendoDTO toLevel0DTO(ProcesaComparendo entidad) {
        ProcesaComparendoDTO dto = new ProcesaComparendoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroComparendo(entidad.getNumeroComparendo());
        dto.setIdTipoComparendo(entidad.getIdTipoComparendo());
        dto.setFechaOperacion(entidad.getFechaOperacion());
        dto.setCodigoOrigen(entidad.getCodigoOrigen());
        dto.setNombreOrigen(EnumTipoOrigenComparendo.getValue(entidad.getCodigoOrigen()).name());
        dto.setCodigoMedioImposicion(entidad.getCodigoMedioImposicion());
        dto.setCodigoTipoInfractor(entidad.getCodigoTipoInfractor());
        dto.setIdTipoNotificacionComparendo(entidad.getIdTipoNotificacionComparendo());
        dto.setFechaInfraccion(entidad.getFechaInfraccion());
        dto.setHoraInfraccion(entidad.getHoraInfraccion());
        dto.setCodigoInfraccion(entidad.getCodigoInfraccion());
        dto.setRetieneLicencia(entidad.getRetieneLicencia());
        dto.setExisteFugaInfractor(entidad.getExisteFugaInfractor());
        dto.setNiegaPruebaAlcoholemia(entidad.getNiegaPruebaAlcoholemia());
        dto.setNumeroPruebaAlcoholemia(entidad.getNumeroPruebaAlcoholemia());
        dto.setGradoAlcoholemia(entidad.getGradoAlcoholemia());
        dto.setValorGradoAlcoholemia(entidad.getValorGradoAlcoholemia());
        dto.setFechaPruebaAlcoholemia(entidad.getFechaPruebaAlcoholemia());
        dto.setNumeroReincidencia(entidad.getNumeroReincidencia());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());
        dto.setObservacionesInfractor(entidad.getObservacionesInfractor());
        dto.setInmovilizado(entidad.getInmovilizado());
        dto.setIdRuta(entidad.getIdRuta());
        dto.setIdTipoServicio(entidad.getIdTipoServicio());
        dto.setIdRadioAccion(entidad.getIdRadioAccion());
        dto.setIdModalidad(entidad.getIdModalidad());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());
        dto.setIdentificacionVehiculo(entidad.getIdentificacionVehiculo());
        dto.setNumeroTarjetaOperacion(entidad.getNumeroTarjetaOperacion());
        dto.setIdClaseVehiculo(entidad.getIdClaseVehiculo());
        dto.setIdNivelServicio(entidad.getIdNivelServicio());
        dto.setCodigoOrganismoMatriculaVehiculo(entidad.getCodigoOrganismoMatriculaVehiculo());
        dto.setCodigoOrganismoLicenciaTransito(entidad.getCodigoOrganismoLicenciaTransito());
        dto.setNumeroLicenciaTransito(entidad.getNumeroLicenciaTransito());
        dto.setIdMarcaVehiculo(entidad.getIdMarcaVehiculo());
        dto.setIdLineaVehiculo(entidad.getIdLineaVehiculo());
        dto.setIdColor(entidad.getIdColor());
        dto.setNumeroMotor(entidad.getNumeroMotor());
        dto.setModelo(entidad.getModelo());
        dto.setPlacaAgente(entidad.getPlacaAgente());
        dto.setPesoNeto(entidad.getPesoNeto());
        dto.setPesoSeco(entidad.getPesoSeco());
        dto.setCodigoTipoTransPasajero(entidad.getCodigoTipoTransPasajero());
        dto.setIdAgenteTransito(entidad.getIdAgenteTransito());
        dto.setObservacionesAgente(entidad.getObservacionesAgente());
        dto.setIdTipoIdentificacionAgente(entidad.getIdTipoIdentificacionAgente());
        dto.setNumeroIdentificacionAgente(entidad.getNumeroIdentificacionAgente());
        dto.setIdUnificacionResponsable(entidad.getIdUnificacionResponsable());
        dto.setNombreResponsable(entidad.getNombreResponsable());
        dto.setApellido1Agente(entidad.getApellido1Agente());
        dto.setApellido2Agente(entidad.getApellido2Agente());
        dto.setNombre1Agente(entidad.getNombre1Agente());
        dto.setNombre2Agente(entidad.getNombre2Agente());
        dto.setIdPatio(entidad.getIdPatio());
        dto.setNumeroGrua(entidad.getNumeroGrua());
        dto.setPlacaGrua(entidad.getPlacaGrua());
        dto.setConsecutivoInmovilizacion(entidad.getConsecutivoInmovilizacion());
        dto.setNumeroPatio(entidad.getNumeroPatio());
        dto.setNombrePatio(entidad.getNombrePatio());
        dto.setNumeroInforme(entidad.getNumeroInforme());
        dto.setFechaRecepcion(entidad.getFechaRecepcion());
        dto.setFechaCorreccion(entidad.getFechaCorreccion());
        dto.setFechaReporte(entidad.getFechaReporte());
        dto.setIdFacturaAxis(entidad.getIdFacturaAxis());
        dto.setNumeroCitacion(entidad.getNumeroCitacion());

        return dto;
    }

    public static ProcesaComparendoDTO toLevel1DTO(ProcesaComparendo entidad) {
        ProcesaComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getProcesaDireccionComparendo() != null)

            dto.setProcesaDireccionComparendo(
                    ProcesaDireccionHelper.toLevel0DTO(entidad.getProcesaDireccionComparendo()));
        if (entidad.getUsuarioPersona() != null)
            dto.setUsuarioPersona(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioPersona()));
        if (entidad.getProcesaDireccionPatio() != null)
            dto.setProcesaDireccionPatio(ProcesaDireccionHelper.toLevel0DTO(entidad.getProcesaDireccionPatio()));

        return dto;
    }

    public static List<ProcesaComparendoDTO> toListLevel0DTO(List<ProcesaComparendo> listEntidad) {
        List<ProcesaComparendoDTO> listDto = new ArrayList<ProcesaComparendoDTO>();
        for (ProcesaComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesaComparendoDTO> toListLevel1DTO(List<ProcesaComparendo> listEntidad) {
        List<ProcesaComparendoDTO> listDto = new ArrayList<ProcesaComparendoDTO>();
        for (ProcesaComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProcesaComparendo toLevel0Entity(ProcesaComparendoDTO dto, ProcesaComparendo entidad) {
        if (null == entidad) {
            entidad = new ProcesaComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroComparendo(dto.getNumeroComparendo());
        entidad.setIdTipoComparendo(dto.getIdTipoComparendo());
        entidad.setFechaOperacion(dto.getFechaOperacion());
        entidad.setCodigoOrigen(dto.getCodigoOrigen());
        entidad.setCodigoMedioImposicion(dto.getCodigoMedioImposicion());
        entidad.setCodigoTipoInfractor(dto.getCodigoTipoInfractor());
        entidad.setIdTipoNotificacionComparendo(dto.getIdTipoNotificacionComparendo());
        entidad.setFechaInfraccion(dto.getFechaInfraccion());
        entidad.setHoraInfraccion(dto.getHoraInfraccion());
        entidad.setCodigoInfraccion(dto.getCodigoInfraccion());
        entidad.setRetieneLicencia(dto.getRetieneLicencia());
        entidad.setExisteFugaInfractor(dto.getExisteFugaInfractor());
        entidad.setNiegaPruebaAlcoholemia(dto.getNiegaPruebaAlcoholemia());
        entidad.setNumeroPruebaAlcoholemia(dto.getNumeroPruebaAlcoholemia());
        entidad.setGradoAlcoholemia(dto.getGradoAlcoholemia());
        entidad.setValorGradoAlcoholemia(dto.getValorGradoAlcoholemia());
        entidad.setFechaPruebaAlcoholemia(dto.getFechaPruebaAlcoholemia());
        entidad.setNumeroReincidencia(dto.getNumeroReincidencia());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setObservacionesInfractor(dto.getObservacionesInfractor());
        entidad.setInmovilizado(dto.getInmovilizado());
        entidad.setIdRuta(dto.getIdRuta());
        entidad.setIdTipoServicio(dto.getIdTipoServicio());
        entidad.setIdRadioAccion(dto.getIdRadioAccion());
        entidad.setIdModalidad(dto.getIdModalidad());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        entidad.setIdentificacionVehiculo(dto.getIdentificacionVehiculo());
        entidad.setNumeroTarjetaOperacion(dto.getNumeroTarjetaOperacion());
        entidad.setIdClaseVehiculo(dto.getIdClaseVehiculo());
        entidad.setIdNivelServicio(dto.getIdNivelServicio());
        entidad.setCodigoOrganismoMatriculaVehiculo(dto.getCodigoOrganismoMatriculaVehiculo());
        entidad.setCodigoOrganismoLicenciaTransito(dto.getCodigoOrganismoLicenciaTransito());
        entidad.setNumeroLicenciaTransito(dto.getNumeroLicenciaTransito());
        entidad.setIdMarcaVehiculo(dto.getIdMarcaVehiculo());
        entidad.setIdLineaVehiculo(dto.getIdLineaVehiculo());
        entidad.setIdColor(dto.getIdColor());
        entidad.setNumeroMotor(dto.getNumeroMotor());
        entidad.setModelo(dto.getModelo());
        entidad.setPlacaAgente(dto.getPlacaAgente());
        entidad.setPesoNeto(dto.getPesoNeto());
        entidad.setPesoSeco(dto.getPesoSeco());
        entidad.setCodigoTipoTransPasajero(dto.getCodigoTipoTransPasajero());
        entidad.setIdAgenteTransito(dto.getIdAgenteTransito());
        entidad.setObservacionesAgente(dto.getObservacionesAgente());
        entidad.setIdTipoIdentificacionAgente(dto.getIdTipoIdentificacionAgente());
        entidad.setNumeroIdentificacionAgente(dto.getNumeroIdentificacionAgente());
        entidad.setIdUnificacionResponsable(dto.getIdUnificacionResponsable());
        entidad.setNombreResponsable(dto.getNombreResponsable());
        entidad.setApellido1Agente(dto.getApellido1Agente());
        entidad.setApellido2Agente(dto.getApellido2Agente());
        entidad.setNombre1Agente(dto.getNombre1Agente());
        entidad.setNombre2Agente(dto.getNombre2Agente());
        entidad.setIdPatio(dto.getIdPatio());
        entidad.setNumeroGrua(dto.getNumeroGrua());
        entidad.setPlacaGrua(dto.getPlacaGrua());
        entidad.setConsecutivoInmovilizacion(dto.getConsecutivoInmovilizacion());
        entidad.setNumeroPatio(dto.getNumeroPatio());
        entidad.setNombrePatio(dto.getNombrePatio());
        entidad.setNumeroInforme(dto.getNumeroInforme());
        entidad.setFechaRecepcion(dto.getFechaRecepcion());
        entidad.setFechaCorreccion(dto.getFechaCorreccion());
        entidad.setFechaReporte(dto.getFechaReporte());
        entidad.setIdFacturaAxis(dto.getIdFacturaAxis());
        entidad.setNumeroCitacion(dto.getNumeroCitacion());

        return entidad;
    }

    public static ProcesaComparendo toLevel1Entity(ProcesaComparendoDTO dto, ProcesaComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getProcesaDireccionComparendo() != null) {
            entidad.setProcesaDireccionComparendo(new ProcesaDireccion());
            entidad.getProcesaDireccionComparendo().setId(dto.getProcesaDireccionComparendo().getId());
        }
        if (dto.getUsuarioPersona() != null) {
            entidad.setUsuarioPersona(new UsuarioPersona());
            entidad.getUsuarioPersona().setIdUsuario(dto.getUsuarioPersona().getUsuario().getId());
        }
        if (dto.getProcesaDireccionPatio() != null) {
            entidad.setProcesaDireccionPatio(new ProcesaDireccion());
            entidad.getProcesaDireccionPatio().setId(dto.getProcesaDireccionPatio().getId());
        }

        return entidad;
    }

    public static List<ProcesaComparendo> toListLevel0Entity(List<ProcesaComparendoDTO> listDto) {
        List<ProcesaComparendo> listEntidad = new ArrayList<ProcesaComparendo>();
        for (ProcesaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProcesaComparendo> toListLevel1Entity(List<ProcesaComparendoDTO> listDto) {
        List<ProcesaComparendo> listEntidad = new ArrayList<ProcesaComparendo>();
        for (ProcesaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}