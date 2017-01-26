package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.entidades.ClaseVehiculo;
import co.com.datatools.c2.entidades.Color;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.entidades.LineaVehiculo;
import co.com.datatools.c2.entidades.MarcaVehiculo;
import co.com.datatools.c2.entidades.Modalidad;
import co.com.datatools.c2.entidades.NivelServicio;
import co.com.datatools.c2.entidades.RadioAccion;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.entidades.TipoTransportePasajero;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.ColorHelper;
import co.com.datatools.c2.negocio.helpers.regveh.LineaVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.MarcaVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.regveh.ModalidadHelper;
import co.com.datatools.c2.negocio.helpers.regveh.RadioAccionHelper;
import co.com.datatools.c2.negocio.helpers.regveh.TipoTransportePasajeroHelper;
import co.com.datatools.c2.negocio.helpers.v2.ClaseVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoServicioHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 20 15:24:44 COT 2015
 */
public class ComparendoVehiculoHelper {
    // --- to DTO
    public static ComparendoVehiculoDTO toLevel0DTO(ComparendoVehiculo entidad) {
        ComparendoVehiculoDTO dto = new ComparendoVehiculoDTO();
        dto.setId(entidad.getId());
        dto.setIdentificacionVehiculo(entidad.getIdentificacionVehiculo());
        dto.setModelo(entidad.getModelo());
        dto.setNumeroLicenciaTransito(entidad.getNumeroLicenciaTransito());
        dto.setNumeroMotor(entidad.getNumeroMotor());
        dto.setNumeroTarjetaOperacion(entidad.getNumeroTarjetaOperacion());
        dto.setPesoNeto(entidad.getPesoNeto());
        dto.setPesoSeco(entidad.getPesoSeco());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());

        return dto;
    }

    public static ComparendoVehiculoDTO toLevel1DTO(ComparendoVehiculo entidad) {
        ComparendoVehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getClaseVehiculo() != null)
            dto.setClaseVehiculo(ClaseVehiculoHelper.toLevel0DTO(entidad.getClaseVehiculo()));
        if (entidad.getColor() != null)
            dto.setColor(ColorHelper.toLevel0DTO(entidad.getColor()));
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getLineaVehiculo() != null)
            dto.setLineaVehiculo(LineaVehiculoHelper.toLevel0DTO(entidad.getLineaVehiculo()));
        if (entidad.getMarcaVehiculo() != null)
            dto.setMarcaVehiculo(MarcaVehiculoHelper.toLevel0DTO(entidad.getMarcaVehiculo()));
        if (entidad.getModalidad() != null)
            dto.setModalidad(ModalidadHelper.toLevel0DTO(entidad.getModalidad()));
        if (entidad.getNivelServicio() != null)
            dto.setNivelServicio(NivelServicioHelper.toLevel0DTO(entidad.getNivelServicio()));
        if (entidad.getRadioAccion() != null)
            dto.setRadioAccion(RadioAccionHelper.toLevel0DTO(entidad.getRadioAccion()));
        if (entidad.getTipoServicio() != null)
            dto.setTipoServicio(TipoServicioHelper.toLevel0DTO(entidad.getTipoServicio()));
        if (entidad.getTipoTransPasajero() != null)
            dto.setTipoTransPasajero(TipoTransportePasajeroHelper.toLevel0DTO(entidad.getTipoTransPasajero()));
        if (entidad.getOrganismoMatriVehic() != null)
            dto.setOrganismoMatriVehic(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoMatriVehic()));
        if (entidad.getOrganismoLicenciaTransito() != null)
            dto.setOrganismoLicenciaTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoLicenciaTransito()));

        return dto;
    }

    public static List<ComparendoVehiculoDTO> toListLevel0DTO(List<ComparendoVehiculo> listEntidad) {
        List<ComparendoVehiculoDTO> listDto = new ArrayList<ComparendoVehiculoDTO>();
        for (ComparendoVehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoVehiculoDTO> toListLevel1DTO(List<ComparendoVehiculo> listEntidad) {
        List<ComparendoVehiculoDTO> listDto = new ArrayList<ComparendoVehiculoDTO>();
        for (ComparendoVehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoVehiculo toLevel0Entity(ComparendoVehiculoDTO dto, ComparendoVehiculo entidad) {
        if (null == entidad) {
            entidad = new ComparendoVehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setIdentificacionVehiculo(dto.getIdentificacionVehiculo());
        entidad.setModelo(dto.getModelo());
        entidad.setNumeroLicenciaTransito(dto.getNumeroLicenciaTransito());
        entidad.setNumeroMotor(dto.getNumeroMotor());
        entidad.setNumeroTarjetaOperacion(dto.getNumeroTarjetaOperacion());
        entidad.setPesoNeto(dto.getPesoNeto());
        entidad.setPesoSeco(dto.getPesoSeco());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());

        return entidad;
    }

    public static ComparendoVehiculo toLevel1Entity(ComparendoVehiculoDTO dto, ComparendoVehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getClaseVehiculo() != null) {
            entidad.setClaseVehiculo(new ClaseVehiculo());
            entidad.getClaseVehiculo().setId(dto.getClaseVehiculo().getId());
        }
        if (dto.getColor() != null) {
            entidad.setColor(new Color());
            entidad.getColor().setId(dto.getColor().getId());
        }
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getLineaVehiculo() != null) {
            entidad.setLineaVehiculo(new LineaVehiculo());
            entidad.getLineaVehiculo().setId(dto.getLineaVehiculo().getId());
        }
        if (dto.getMarcaVehiculo() != null) {
            entidad.setMarcaVehiculo(new MarcaVehiculo());
            entidad.getMarcaVehiculo().setId(dto.getMarcaVehiculo().getId());
        }
        if (dto.getModalidad() != null) {
            entidad.setModalidad(new Modalidad());
            entidad.getModalidad().setId(dto.getModalidad().getId());
        }
        if (dto.getNivelServicio() != null) {
            entidad.setNivelServicio(new NivelServicio());
            entidad.getNivelServicio().setId(dto.getNivelServicio().getId());
        }
        if (dto.getRadioAccion() != null) {
            entidad.setRadioAccion(new RadioAccion());
            entidad.getRadioAccion().setId(dto.getRadioAccion().getId());
        }
        if (dto.getTipoServicio() != null) {
            entidad.setTipoServicio(new TipoServicio());
            entidad.getTipoServicio().setId(dto.getTipoServicio().getId());
        }
        if (dto.getTipoTransPasajero() != null) {
            entidad.setTipoTransPasajero(new TipoTransportePasajero());
            entidad.getTipoTransPasajero().setId(dto.getTipoTransPasajero().getId());
        }
        if (dto.getTipoTransPasajero() != null) {
            entidad.setTipoTransPasajero(new TipoTransportePasajero());
            entidad.getTipoTransPasajero().setId(dto.getTipoTransPasajero().getId());
        }
        if (dto.getOrganismoMatriVehic() != null) {
            entidad.setOrganismoMatriVehic(new OrganismoTransito());
            entidad.getOrganismoMatriVehic().setCodigoOrganismo(dto.getOrganismoMatriVehic().getCodigoOrganismo());
        }
        if (dto.getOrganismoLicenciaTransito() != null) {
            entidad.setOrganismoLicenciaTransito(new OrganismoTransito());
            entidad.getOrganismoLicenciaTransito().setCodigoOrganismo(
                    dto.getOrganismoLicenciaTransito().getCodigoOrganismo());
        }
        return entidad;
    }

    public static List<ComparendoVehiculo> toListLevel0Entity(List<ComparendoVehiculoDTO> listDto) {
        List<ComparendoVehiculo> listEntidad = new ArrayList<ComparendoVehiculo>();
        for (ComparendoVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoVehiculo> toListLevel1Entity(List<ComparendoVehiculoDTO> listDto) {
        List<ComparendoVehiculo> listEntidad = new ArrayList<ComparendoVehiculo>();
        for (ComparendoVehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}