package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.entidades.EstadoComparendo;
import co.com.datatools.c2.entidades.EstadoComparendoSimit;
import co.com.datatools.c2.entidades.GradoAlcoholemia;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.entidades.MedioImposicionComparendo;
import co.com.datatools.c2.entidades.OrdenComparendoNacional;
import co.com.datatools.c2.entidades.Ruta;
import co.com.datatools.c2.entidades.TipoAgenteImpositor;
import co.com.datatools.c2.entidades.TipoComparendo;
import co.com.datatools.c2.entidades.TipoInfractor;
import co.com.datatools.c2.entidades.TipoNotificacionComparendo;
import co.com.datatools.c2.entidades.TipoOrigenComparendo;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.RutaHelper;

/**
 * Cambios en la estructura en el manejo de entidades no se puede volver a restaurar
 * 
 * @author Generated
 * @version 3.0 - Wed Oct 07 10:54:55 COT 2015
 * @author giovanni.velandia
 */
public class ComparendoHelper {
    // --- to DTO
    public static ComparendoDTO toLevel0DTO(Comparendo entidad) {
        ComparendoDTO dto = new ComparendoDTO();
        dto.setCicomparendo(entidad.getCicomparendo());
        dto.setFechaInfraccion(entidad.getFechaInfraccion());
        dto.setHoraInfraccion(entidad.getHoraInfraccion());
        dto.setFechaModificacion(entidad.getFechaModificacion());
        dto.setRetieneLicencia(entidad.getRetieneLicencia());
        dto.setExisteFugaInfractor(entidad.getExisteFugaInfractor());
        dto.setValorGradoAlcoholemia(entidad.getValorGradoAlcoholemia());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());
        dto.setFechaPruebaAlcoholemia(entidad.getFechaPruebaAlcoholemia());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setObservacionesInfractor(entidad.getObservacionesInfractor());
        dto.setNiegaPruebaAlcoholemia(entidad.getNiegaPruebaAlcoholemia());
        dto.setNumeroPruebaAlcoholemia(entidad.getNumeroPruebaAlcoholemia());
        dto.setNumeroReincidencia(entidad.getNumeroReincidencia());
        dto.setCarteraGenerada(entidad.getCarteraGenerada());
        dto.setFechaGeneraCartera(entidad.getFechaGeneraCartera());
        dto.setVelocidadVehiculo(entidad.getVelocidadVehiculo());
        dto.setIdDocumentoNotificacion(entidad.getIdDocumentoNotificacion());
        dto.setNumeroCitacion(entidad.getNumeroCitacion());
        dto.setIdFacturaAxis(entidad.getIdFacturaAxis());
        dto.setPlacaAgenteTransito(entidad.getPlacaAgenteTransito());
        dto.setSecuenciaUnica(entidad.getSecuenciaUnica());

        return dto;
    }

    public static ComparendoDTO toLevel1DTO(Comparendo entidad) {
        ComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrdenComparendoNacional() != null)
            dto.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel0DTO(entidad.getOrdenComparendoNacional()));
        if (entidad.getTipoOrigen() != null)
            dto.setTipoOrigen(TipoOrigenComparendoHelper.toLevel0DTO(entidad.getTipoOrigen()));
        if (entidad.getUsuarioPersona() != null)
            dto.setUsuarioPersona(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioPersona()));
        if (entidad.getMedioImposicion() != null)
            dto.setMedioImposicion(MedioImposicionComparendoHelper.toLevel0DTO(entidad.getMedioImposicion()));
        if (entidad.getTipoInfractor() != null)
            dto.setTipoInfractor(TipoInfractorHelper.toLevel0DTO(entidad.getTipoInfractor()));
        if (entidad.getTipoNotificacion() != null)
            dto.setTipoNotificacion(TipoNotificacionComparendoHelper.toLevel0DTO(entidad.getTipoNotificacion()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));
        if (entidad.getInfraccion() != null)
            dto.setInfraccion(InfraccionHelper.toLevel0DTO(entidad.getInfraccion()));
        if (entidad.getRuta() != null)
            dto.setRuta(RutaHelper.toLevel0DTO(entidad.getRuta()));
        if (entidad.getTipoComparendo() != null)
            dto.setTipoComparendo(TipoComparendoHelper.toLevel0DTO(entidad.getTipoComparendo()));
        if (entidad.getGradoAlcoholemia() != null)
            dto.setGradoAlcoholemia(GradoAlcoholemiaHelper.toLevel0DTO(entidad.getGradoAlcoholemia()));
        if (entidad.getEstadoComparendo() != null)
            dto.setEstadoComparendo(EstadoComparendoHelper.toLevel0DTO(entidad.getEstadoComparendo()));
        if (entidad.getComparendoAgente() != null)
            dto.setComparendoAgente(ComparendoAgenteHelper.toLevel0DTO(entidad.getComparendoAgente()));
        if (entidad.getComparendoPatio() != null)
            dto.setComparendoPatio(ComparendoPatioHelper.toLevel0DTO(entidad.getComparendoPatio()));
        if (entidad.getComparendoVehiculo() != null)
            dto.setComparendoVehiculo(ComparendoVehiculoHelper.toLevel0DTO(entidad.getComparendoVehiculo()));
        if (entidad.getEstadoComparendoSimit() != null)
            dto.setEstadoComparendoSimit(EstadoComparendoSimitHelper.toLevel0DTO(entidad.getEstadoComparendoSimit()));
        if (entidad.getTipoAgenteImpositor() != null)
            dto.setTipoAgenteImpositorDTO(TipoAgenteImpositorHelper.toLevel0DTO(entidad.getTipoAgenteImpositor()));

        return dto;
    }

    public static List<ComparendoDTO> toListLevel0DTO(List<Comparendo> listEntidad) {
        List<ComparendoDTO> listDto = new ArrayList<ComparendoDTO>();
        for (Comparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoDTO> toListLevel1DTO(List<Comparendo> listEntidad) {
        List<ComparendoDTO> listDto = new ArrayList<ComparendoDTO>();
        for (Comparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Comparendo toLevel0Entity(ComparendoDTO dto, Comparendo entidad) {
        if (null == entidad) {
            entidad = new Comparendo();
        }
        entidad.setCicomparendo(dto.getCicomparendo());
        entidad.setFechaInfraccion(dto.getFechaInfraccion());
        entidad.setHoraInfraccion(dto.getHoraInfraccion());
        entidad.setFechaModificacion(dto.getFechaModificacion());
        entidad.setRetieneLicencia(dto.getRetieneLicencia());
        entidad.setExisteFugaInfractor(dto.getExisteFugaInfractor());
        entidad.setValorGradoAlcoholemia(dto.getValorGradoAlcoholemia());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setFechaPruebaAlcoholemia(dto.getFechaPruebaAlcoholemia());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setObservacionesInfractor(dto.getObservacionesInfractor());
        entidad.setNiegaPruebaAlcoholemia(dto.getNiegaPruebaAlcoholemia());
        entidad.setNumeroPruebaAlcoholemia(dto.getNumeroPruebaAlcoholemia());
        entidad.setNumeroReincidencia(dto.getNumeroReincidencia());
        entidad.setCarteraGenerada(dto.getCarteraGenerada());
        entidad.setFechaGeneraCartera(dto.getFechaGeneraCartera());
        entidad.setIdDocumentoNotificacion(dto.getIdDocumentoNotificacion());
        entidad.setVelocidadVehiculo(dto.getVelocidadVehiculo());
        entidad.setNumeroCitacion(dto.getNumeroCitacion());
        entidad.setIdFacturaAxis(dto.getIdFacturaAxis());
        entidad.setPlacaAgenteTransito(dto.getPlacaAgenteTransito());
        entidad.setSecuenciaUnica(dto.getSecuenciaUnica());

        return entidad;
    }

    public static Comparendo toLevel1Entity(ComparendoDTO dto, Comparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrdenComparendoNacional() != null) {
            entidad.setOrdenComparendoNacional(new OrdenComparendoNacional());
            entidad.getOrdenComparendoNacional().setCicomparendo(dto.getOrdenComparendoNacional().getCicomparendo());
        }
        if (dto.getTipoOrigen() != null) {
            entidad.setTipoOrigen(new TipoOrigenComparendo());
            entidad.getTipoOrigen().setId(dto.getTipoOrigen().getId());
        }
        if (dto.getUsuarioPersona() != null) {
            if (dto.getUsuarioPersona().getUsuario() != null) {
                entidad.setUsuarioPersona(new UsuarioPersona());
                entidad.getUsuarioPersona().setIdUsuario(dto.getUsuarioPersona().getUsuario().getId());
            }
        }
        if (dto.getMedioImposicion() != null) {
            entidad.setMedioImposicion(new MedioImposicionComparendo());
            entidad.getMedioImposicion().setId(dto.getMedioImposicion().getId());
        }
        if (dto.getTipoInfractor() != null) {
            entidad.setTipoInfractor(new TipoInfractor());
            entidad.getTipoInfractor().setId(dto.getTipoInfractor().getId());
        }
        if (dto.getTipoNotificacion() != null) {
            entidad.setTipoNotificacion(new TipoNotificacionComparendo());
            entidad.getTipoNotificacion().setId(dto.getTipoNotificacion().getId());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }
        if (dto.getInfraccion() != null) {
            entidad.setInfraccion(new Infraccion());
            entidad.getInfraccion().setId(dto.getInfraccion().getId());
        }
        if (dto.getRuta() != null) {
            entidad.setRuta(new Ruta());
            entidad.getRuta().setId(dto.getRuta().getId());
        }
        if (dto.getTipoComparendo() != null) {
            entidad.setTipoComparendo(new TipoComparendo());
            entidad.getTipoComparendo().setId(dto.getTipoComparendo().getId());
        }
        if (dto.getGradoAlcoholemia() != null) {
            entidad.setGradoAlcoholemia(new GradoAlcoholemia());
            entidad.getGradoAlcoholemia().setId(dto.getGradoAlcoholemia().getId());
        }
        if (dto.getEstadoComparendo() != null) {
            entidad.setEstadoComparendo(new EstadoComparendo());
            entidad.getEstadoComparendo().setId(dto.getEstadoComparendo().getId());
        }
        if (dto.getComparendoAgente() != null) {// TODO cambiado para que lo encuentre Giovanni
            entidad.setComparendoAgente(new ComparendoAgente());

            entidad.getComparendoAgente().setComparendo(new Comparendo());
            entidad.getComparendoAgente().getComparendo()
                    .setCicomparendo(dto.getComparendoAgente().getComparendo().getCicomparendo());

        }
        if (dto.getComparendoVehiculo() != null) {
            entidad.setComparendoVehiculo(new ComparendoVehiculo());
            entidad.getComparendoVehiculo().setId(dto.getComparendoVehiculo().getId());
        }
        if (dto.getEstadoComparendoSimit() != null) {
            entidad.setEstadoComparendoSimit(new EstadoComparendoSimit());
            entidad.getEstadoComparendoSimit().setId(dto.getEstadoComparendoSimit().getId());
        }
        if (dto.getTipoAgenteImpositorDTO() != null) {
            entidad.setTipoAgenteImpositor(new TipoAgenteImpositor());
            entidad.getTipoAgenteImpositor().setId(dto.getTipoAgenteImpositorDTO().getId());
        }

        return entidad;
    }

    public static List<Comparendo> toListLevel0Entity(List<ComparendoDTO> listDto) {
        List<Comparendo> listEntidad = new ArrayList<Comparendo>();
        for (ComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Comparendo> toListLevel1Entity(List<ComparendoDTO> listDto) {
        List<Comparendo> listEntidad = new ArrayList<Comparendo>();
        for (ComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad

}
