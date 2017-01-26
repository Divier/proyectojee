package co.com.datatools.c2.ws.comparendo.helper;

import co.com.datatools.c2.dto.comparendo.RespuestaSolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.comparendo.SolicitudNumeroComparendoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.dto.ws.PropietarioVehiculoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaSolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.SolicitudNumeroComparendoWSDTO;
import co.com.datatools.c2.dto.ws.VehiculoWSDTO;

public class SolicitarOCNHelper {

    public static SolicitudNumeroComparendoDTO toSolicitudNumeroComparendoDTO(
            SolicitudNumeroComparendoWSDTO solicitudNumeroComparendoWSDTO) {
        SolicitudNumeroComparendoDTO solicitudDTO = new SolicitudNumeroComparendoDTO();
        solicitudDTO.setCodigoOrganismo(solicitudNumeroComparendoWSDTO.getCodigoOrganismo());
        solicitudDTO.setFechaImposicion(solicitudNumeroComparendoWSDTO.getFechaImposicion().toGregorianCalendar()
                .getTime());
        solicitudDTO.setHoraImposicion(solicitudNumeroComparendoWSDTO.getHoraImposicion().toGregorianCalendar()
                .getTime());
        solicitudDTO.setIdentificadorVehiculo(solicitudNumeroComparendoWSDTO.getIdentificadorVehiculo());
        solicitudDTO.setNumeroDocumento(solicitudNumeroComparendoWSDTO.getNumeroDocumento());
        solicitudDTO.setPlacaAgente(solicitudNumeroComparendoWSDTO.getPlacaAgente());
        solicitudDTO.setPlacaVehiculo(solicitudNumeroComparendoWSDTO.getPlacaVehiculo());
        return solicitudDTO;
    }

    public static RespuestaSolicitudNumeroComparendoWSDTO tolevel1DTO(
            RespuestaSolicitudNumeroComparendoDTO respuestaSolicitudNumeroComparendoDTO, String idVehiculo) {
        RespuestaSolicitudNumeroComparendoWSDTO respuestaSolicitudNumeroComparendoWSDTO = new RespuestaSolicitudNumeroComparendoWSDTO();
        respuestaSolicitudNumeroComparendoWSDTO.setNumeroComparendo(respuestaSolicitudNumeroComparendoDTO
                .getNumeroComparendo());
        VehiculoWSDTO vehiculo = null;
        PropietarioVehiculoWSDTO propietario = null;
        if (respuestaSolicitudNumeroComparendoDTO.getVehiculo() != null) {
            vehiculo = toLevel1DTO(respuestaSolicitudNumeroComparendoDTO.getVehiculo());
            if (!respuestaSolicitudNumeroComparendoDTO.getVehiculo().getPropietarioVehiculoList().isEmpty())
                propietario = tolevel1(respuestaSolicitudNumeroComparendoDTO.getVehiculo().getPropietarioVehiculoList()
                        .get(0));
        } else {
            vehiculo = new VehiculoWSDTO();
            vehiculo.setIdentificadorVehiculo(idVehiculo);
        }

        respuestaSolicitudNumeroComparendoWSDTO.setVehiculoWSDTO(vehiculo);
        respuestaSolicitudNumeroComparendoWSDTO.setPropietarioVehiculoWSDTO(propietario);
        return respuestaSolicitudNumeroComparendoWSDTO;
    }

    public static VehiculoWSDTO toLevel0DTO(VehiculoDTO vehiculoDTO) {
        VehiculoWSDTO vehiculo = new VehiculoWSDTO();
        vehiculo.setPlacaVehiculo(vehiculoDTO.getPlaca());
        vehiculo.setTarjetaOperacion(vehiculoDTO.getNumeroTarjetaOpera());

        return vehiculo;
    }

    public static VehiculoWSDTO toLevel1DTO(VehiculoDTO vehiculoDTO) {

        VehiculoWSDTO vehiculo = null;

        vehiculo = toLevel0DTO(vehiculoDTO);

        if (vehiculoDTO.getTipoServicio() != null)
            vehiculo.setClaseServicio(vehiculoDTO.getTipoServicio().getCodigo());

        if (vehiculoDTO.getOrganismoTransito() != null)
            vehiculo.setCodigoOrganismoMatricula(vehiculoDTO.getOrganismoTransito().getCodigoOrganismo());

        if (vehiculoDTO.getModalidad() != null)
            vehiculo.setModalidad(vehiculoDTO.getModalidad().getCodigo());

        if (vehiculoDTO.getRadioAccion() != null)
            vehiculo.setRadioAccion(vehiculoDTO.getRadioAccion().getCodigo());

        if (vehiculoDTO.getEmpresaVehiculoList() != null && !vehiculoDTO.getEmpresaVehiculoList().isEmpty()
                && vehiculoDTO.getEmpresaVehiculoList().get(0).getPersonaJuridica() != null) {
            vehiculo.setRazonSocialEmpresa(vehiculoDTO.getEmpresaVehiculoList().get(0).getPersonaJuridica()
                    .getNombreComercial());
            if (vehiculoDTO.getEmpresaVehiculoList().get(0).getPersonaJuridica().getTipoIdentificacion() != null)
                vehiculo.setTipoDocumentoEmpresa(vehiculoDTO.getEmpresaVehiculoList().get(0).getPersonaJuridica()
                        .getTipoIdentificacion().getCodigo());
        }

        if (vehiculoDTO.getClaseVehiculo() != null)
            vehiculo.setTipoVehiculo(vehiculoDTO.getClaseVehiculo().getCodigo());

        if (vehiculoDTO.getTipoTransportePasajero() != null)
            vehiculo.setTipoTransportePasajeros(vehiculoDTO.getTipoTransportePasajero().getCodigo());
        return vehiculo;

    }

    public static PropietarioVehiculoWSDTO tolevel1(PropietarioVehiculoDTO propietario) {
        PropietarioVehiculoWSDTO propietarioWSDTO = new PropietarioVehiculoWSDTO();
        if (propietario.getPersona() != null) {

            propietarioWSDTO.setNumeroDocumento(propietario.getPersona().getNumeroIdentificacion());
            propietarioWSDTO.setRazonSocial(propietario.getPersona().getNombreEmpresaLabora());
            if (propietario.getPersona() instanceof PersonaJuridicaDTO)
                propietarioWSDTO.setRazonSocial((((PersonaJuridicaDTO) propietario.getPersona()).getNombreComercial()));
            else {
                propietarioWSDTO.setPrimerApellido(propietario.getPersona().getApellido1());
                propietarioWSDTO.setSegundoNombre(propietario.getPersona().getNombre2());
                propietarioWSDTO.setPrimerNombre(propietario.getPersona().getNombre1());
                propietarioWSDTO.setSegundoApellido(propietario.getPersona().getApellido2());
            }

            if (propietario.getVehiculo() != null)
                propietarioWSDTO.setNumeroLicenciaTransito(propietario.getVehiculo().getLicenciaTransito());

            if (propietario.getPersona().getTipoIdentificacion() != null)
                propietarioWSDTO.setTipoDocumento(propietario.getPersona().getTipoIdentificacion().getCodigo());
        }
        return propietarioWSDTO;
    }

}
