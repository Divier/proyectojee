package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.entidades.ClaseVehiculo;
import co.com.datatools.c2.entidades.Color;
import co.com.datatools.c2.entidades.LineaVehiculo;
import co.com.datatools.c2.entidades.Modalidad;
import co.com.datatools.c2.entidades.RadioAccion;
import co.com.datatools.c2.entidades.TipoServicio;
import co.com.datatools.c2.entidades.Vehiculo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.v2.ClaseVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoServicioHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Oct 08 09:29:31 COT 2015
 */
public class VehiculoHelper {
    // --- to DTO
    public static VehiculoDTO toLevel0DTO(Vehiculo entidad) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(entidad.getId());
        dto.setLicenciaTransito(entidad.getLicenciaTransito());
        dto.setNumeroMotor(entidad.getNumeroMotor());
        dto.setModelo(entidad.getModelo());
        dto.setPlaca(entidad.getPlaca());
        dto.setFechaExpedicionTarjeOpera(entidad.getFechaExpedicionTarjeOpera());
        dto.setFechaVencimientoTarjeOpera(entidad.getFechaVencimientoTarjeOpera());
        dto.setNumeroTarjetaOpera(entidad.getNumeroTarjetaOpera());

        return dto;
    }

    public static VehiculoDTO toLevel1DTO(Vehiculo entidad) {
        VehiculoDTO dto = toLevel0DTO(entidad);
        if (entidad.getColor() != null)
            dto.setColor(ColorHelper.toLevel0DTO(entidad.getColor()));
        if (entidad.getLinea() != null)
            // Se genera Helper nivel1 para obtener Marca de Linea
            dto.setLinea(LineaVehiculoHelper.toLevel1DTO(entidad.getLinea()));
        if (entidad.getModalidad() != null)
            dto.setModalidad(ModalidadHelper.toLevel0DTO(entidad.getModalidad()));
        if (entidad.getRadioAccion() != null)
            dto.setRadioAccion(RadioAccionHelper.toLevel0DTO(entidad.getRadioAccion()));
        if (entidad.getTipoServicio() != null)
            dto.setTipoServicio(TipoServicioHelper.toLevel0DTO(entidad.getTipoServicio()));
        if (entidad.getClaseVehiculo() != null)
            dto.setClaseVehiculo(ClaseVehiculoHelper.toLevel0DTO(entidad.getClaseVehiculo()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<VehiculoDTO> toListLevel0DTO(List<Vehiculo> listEntidad) {
        List<VehiculoDTO> listDto = new ArrayList<VehiculoDTO>();
        for (Vehiculo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<VehiculoDTO> toListLevel1DTO(List<Vehiculo> listEntidad) {
        List<VehiculoDTO> listDto = new ArrayList<VehiculoDTO>();
        for (Vehiculo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Vehiculo toLevel0Entity(VehiculoDTO dto, Vehiculo entidad) {
        if (null == entidad) {
            entidad = new Vehiculo();
        }
        entidad.setId(dto.getId());
        entidad.setLicenciaTransito(dto.getLicenciaTransito());
        entidad.setNumeroMotor(dto.getNumeroMotor());
        entidad.setModelo(dto.getModelo());
        entidad.setPlaca(dto.getPlaca());
        entidad.setFechaExpedicionTarjeOpera(dto.getFechaExpedicionTarjeOpera());
        entidad.setFechaVencimientoTarjeOpera(dto.getFechaVencimientoTarjeOpera());
        entidad.setNumeroTarjetaOpera(dto.getNumeroTarjetaOpera());

        return entidad;
    }

    public static Vehiculo toLevel1Entity(VehiculoDTO dto, Vehiculo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getColor() != null) {
            entidad.setColor(new Color());
            entidad.getColor().setId(dto.getColor().getId());
        }
        if (dto.getLinea() != null) {
            entidad.setLinea(new LineaVehiculo());
            entidad.getLinea().setId(dto.getLinea().getId());
        }
        if (dto.getModalidad() != null) {
            entidad.setModalidad(new Modalidad());
            entidad.getModalidad().setId(dto.getModalidad().getId());
        }
        if (dto.getRadioAccion() != null) {
            entidad.setRadioAccion(new RadioAccion());
            entidad.getRadioAccion().setId(dto.getRadioAccion().getId());
        }
        if (dto.getTipoServicio() != null) {
            entidad.setTipoServicio(new TipoServicio());
            entidad.getTipoServicio().setId(dto.getTipoServicio().getId());
        }
        if (dto.getClaseVehiculo() != null) {
            entidad.setClaseVehiculo(new ClaseVehiculo());
            entidad.getClaseVehiculo().setId(dto.getClaseVehiculo().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<Vehiculo> toListLevel0Entity(List<VehiculoDTO> listDto) {
        List<Vehiculo> listEntidad = new ArrayList<Vehiculo>();
        for (VehiculoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Vehiculo> toListLevel1Entity(List<VehiculoDTO> listDto) {
        List<Vehiculo> listEntidad = new ArrayList<Vehiculo>();
        for (VehiculoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}