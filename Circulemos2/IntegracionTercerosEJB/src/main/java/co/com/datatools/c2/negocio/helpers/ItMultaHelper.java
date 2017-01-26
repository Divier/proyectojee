package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.multas.ItMultaDTO;
import co.com.datatools.c2.entidades.multas.ItMulta;

/**
 * @author Generated
 * @version 3.0 - Thu May 19 13:52:18 COT 2016
 */
public class ItMultaHelper {
    // --- to DTO
    public static ItMultaDTO toLevel0DTO(ItMulta entidad) {
        ItMultaDTO dto = new ItMultaDTO();
        dto.setIdMulta(entidad.getIdMulta());
        dto.setApellido1Agente(entidad.getApellido1Agente());
        dto.setApellido1Infractor(entidad.getApellido1Infractor());
        dto.setApellido1Propietario(entidad.getApellido1Propietario());
        dto.setApellido1Testigo(entidad.getApellido1Testigo());
        dto.setApellido2Agente(entidad.getApellido2Agente());
        dto.setApellido2Infractor(entidad.getApellido2Infractor());
        dto.setApellido2Propietario(entidad.getApellido2Propietario());
        dto.setApellido2Testigo(entidad.getApellido2Testigo());
        dto.setCodigoCategLicenConduInfr(entidad.getCodigoCategLicenConduInfr());
        dto.setCodigoClaseVehiculo(entidad.getCodigoClaseVehiculo());
        dto.setCodigoDepartamentoInfraccion(entidad.getCodigoDepartamentoInfraccion());
        dto.setCodigoDepartamentoInfractor(entidad.getCodigoDepartamentoInfractor());
        dto.setCodigoDepartamentoPatio(entidad.getCodigoDepartamentoPatio());
        dto.setCodigoDepartamentoTestigo(entidad.getCodigoDepartamentoTestigo());
        dto.setCodigoInfraccion(entidad.getCodigoInfraccion());
        dto.setCodigoLocalidadInfraccion(entidad.getCodigoLocalidadInfraccion());
        dto.setCodigoLocalidadInfractor(entidad.getCodigoLocalidadInfractor());
        dto.setCodigoLocalidadPatio(entidad.getCodigoLocalidadPatio());
        dto.setCodigoLocalidadTestigo(entidad.getCodigoLocalidadTestigo());
        dto.setCodigoMedioImposicion(entidad.getCodigoMedioImposicion());
        dto.setCodigoModalidad(entidad.getCodigoModalidad());
        dto.setCodigoMunicipioInfraccion(entidad.getCodigoMunicipioInfraccion());
        dto.setCodigoMunicipioInfractor(entidad.getCodigoMunicipioInfractor());
        dto.setCodigoMunicipioPatio(entidad.getCodigoMunicipioPatio());
        dto.setCodigoMunicipioTestigo(entidad.getCodigoMunicipioTestigo());
        dto.setCodigoOrganLicenConduInfr(entidad.getCodigoOrganLicenConduInfr());
        dto.setCodigoOrganismoLicenTrans(entidad.getCodigoOrganismoLicenTrans());
        dto.setCodigoOrganismoMatriVehic(entidad.getCodigoOrganismoMatriVehic());
        dto.setCodigoOrganismoTransito(entidad.getCodigoOrganismoTransito());
        dto.setCodigoOrigenComparendo(entidad.getCodigoOrigenComparendo());
        dto.setCodigoPaisDirecInfractor(entidad.getCodigoPaisDirecInfractor());
        dto.setCodigoPaisDirecPatio(entidad.getCodigoPaisDirecPatio());
        dto.setCodigoPaisDirecTestigo(entidad.getCodigoPaisDirecTestigo());
        dto.setCodigoPaisInfraccion(entidad.getCodigoPaisInfraccion());
        dto.setCodigoRadioAccion(entidad.getCodigoRadioAccion());
        dto.setCodigoTipoAgenteImpositor(entidad.getCodigoTipoAgenteImpositor());
        dto.setCodigoTipoIdentAgente(entidad.getCodigoTipoIdentAgente());
        dto.setCodigoTipoIdentEmpresa(entidad.getCodigoTipoIdentEmpresa());
        dto.setCodigoTipoIdentInfractor(entidad.getCodigoTipoIdentInfractor());
        dto.setCodigoTipoIdentPropietario(entidad.getCodigoTipoIdentPropietario());
        dto.setCodigoTipoIdentTestigo(entidad.getCodigoTipoIdentTestigo());
        dto.setCodigoTipoInfractor(entidad.getCodigoTipoInfractor());
        dto.setCodigoTipoNotifCompa(entidad.getCodigoTipoNotifCompa());
        dto.setCodigoTipoServicio(entidad.getCodigoTipoServicio());
        dto.setCodigoTipoTransPasajero(entidad.getCodigoTipoTransPasajero());
        dto.setConsecutivoInmovilizacion(entidad.getConsecutivoInmovilizacion());
        dto.setDireccionBasicaInfraccion(entidad.getDireccionBasicaInfraccion());
        dto.setDireccionBasicaInfractor(entidad.getDireccionBasicaInfractor());
        dto.setDireccionBasicaPatio(entidad.getDireccionBasicaPatio());
        dto.setDireccionBasicaTestigo(entidad.getDireccionBasicaTestigo());
        dto.setEdadInfractor(entidad.getEdadInfractor());
        dto.setEmail(entidad.getEmail());
        dto.setCodigoEstadoComparendo(entidad.getCodigoEstadoComparendo());
        dto.setEstadoLectura(entidad.getEstadoLectura());
        dto.setEvidencia1CodigoTipo(entidad.getEvidencia1CodigoTipo());
        dto.setEvidencia1Nombre(entidad.getEvidencia1Nombre());
        dto.setEvidencia1Url(entidad.getEvidencia1Url());
        dto.setEvidencia2CodigoTipo(entidad.getEvidencia2CodigoTipo());
        dto.setEvidencia2Nombre(entidad.getEvidencia2Nombre());
        dto.setEvidencia2Url(entidad.getEvidencia2Url());
        dto.setExisteFugaInfractor(entidad.getExisteFugaInfractor());
        dto.setFechaExpedLicenConduInfra(entidad.getFechaExpedLicenConduInfra());
        dto.setFechaHoraRecibido(entidad.getFechaHoraRecibido());
        dto.setFechaInfraccion(entidad.getFechaInfraccion());
        dto.setFechaNotificacion(entidad.getFechaNotificacion());
        dto.setFechaPruebaAlcoholemia(entidad.getFechaPruebaAlcoholemia());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaVenciLicenConduInfra(entidad.getFechaVenciLicenConduInfra());
        dto.setGradoAlcoholemia(entidad.getGradoAlcoholemia());
        dto.setHoraInfraccion(entidad.getHoraInfraccion());
        dto.setIdentificacionVehiculo(entidad.getIdentificacionVehiculo());
        dto.setInmoviliza(entidad.getInmoviliza());
        dto.setNiegaPruebaAlcoholemia(entidad.getNiegaPruebaAlcoholemia());
        dto.setNombrePatio(entidad.getNombrePatio());
        dto.setNombre1Agente(entidad.getNombre1Agente());
        dto.setNombre1Infractor(entidad.getNombre1Infractor());
        dto.setNombre1Propietario(entidad.getNombre1Propietario());
        dto.setNombre1Testigo(entidad.getNombre1Testigo());
        dto.setNombre2Agente(entidad.getNombre2Agente());
        dto.setNombre2Infractor(entidad.getNombre2Infractor());
        dto.setNombre2Propietario(entidad.getNombre2Propietario());
        dto.setNombre2Testigo(entidad.getNombre2Testigo());
        dto.setNumeroCitacion(entidad.getNumeroCitacion());
        dto.setNumeroGrua(entidad.getNumeroGrua());
        dto.setNumeroIdentPropietario(entidad.getNumeroIdentPropietario());
        dto.setNumeroIdentTestigo(entidad.getNumeroIdentTestigo());
        dto.setNumeroIdentificacionAgente(entidad.getNumeroIdentificacionAgente());
        dto.setNumeroIdentificacionEmpresa(entidad.getNumeroIdentificacionEmpresa());
        dto.setNumeroIdentificacionInfractor(entidad.getNumeroIdentificacionInfractor());
        dto.setNumeroLicencia(entidad.getNumeroLicencia());
        dto.setNumeroLicenciaTransito(entidad.getNumeroLicenciaTransito());
        dto.setNumeroPatio(entidad.getNumeroPatio());
        dto.setNumeroPruebaAlcoholemia(entidad.getNumeroPruebaAlcoholemia());
        dto.setNumeroReincidencia(entidad.getNumeroReincidencia());
        dto.setNumeroTarjetaOperacion(entidad.getNumeroTarjetaOperacion());
        dto.setObservacionesAgente(entidad.getObservacionesAgente());
        dto.setPlacaAgente(entidad.getPlacaAgente());
        dto.setPlacaGrua(entidad.getPlacaGrua());
        dto.setPlacaVehiculo(entidad.getPlacaVehiculo());
        dto.setRazonSocialEmpresa(entidad.getRazonSocialEmpresa());
        dto.setRazonSocialInfractor(entidad.getRazonSocialInfractor());
        dto.setRazonSocialPropietario(entidad.getRazonSocialPropietario());
        dto.setTelefonoFijoInfractor(entidad.getTelefonoFijoInfractor());
        dto.setTelefonoMovilInfractor(entidad.getTelefonoMovilInfractor());
        dto.setTelefonoMovilTestigo(entidad.getTelefonoMovilTestigo());
        dto.setValorConcepto(entidad.getValorConcepto());
        dto.setValorGradoAlcoholemia(entidad.getValorGradoAlcoholemia());
        dto.setVelocidadVehiculo(entidad.getVelocidadVehiculo());
        dto.setIdFacturaAxis(entidad.getIdFacturaAxis());
        dto.setLongitudDireccionInfraccion(entidad.getLongitudDireccionInfraccion());
        dto.setLatitudDireccionInfraccion(entidad.getLatitudDireccionInfraccion());
        dto.setNombreLocalidadInfraccion(entidad.getNombreLocalidadInfraccion());
        dto.setSecuenciaUnica(entidad.getSecuenciaUnica());

        return dto;
    }

    public static ItMultaDTO toLevel1DTO(ItMulta entidad) {
        ItMultaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ItMultaDTO> toListLevel0DTO(List<ItMulta> listEntidad) {
        List<ItMultaDTO> listDto = new ArrayList<ItMultaDTO>();
        for (ItMulta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItMultaDTO> toListLevel1DTO(List<ItMulta> listEntidad) {
        List<ItMultaDTO> listDto = new ArrayList<ItMultaDTO>();
        for (ItMulta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItMulta toLevel0Entity(ItMultaDTO dto, ItMulta entidad) {
        if (null == entidad) {
            entidad = new ItMulta();
        }
        entidad.setIdMulta(dto.getIdMulta());
        entidad.setApellido1Agente(dto.getApellido1Agente());
        entidad.setApellido1Infractor(dto.getApellido1Infractor());
        entidad.setApellido1Propietario(dto.getApellido1Propietario());
        entidad.setApellido1Testigo(dto.getApellido1Testigo());
        entidad.setApellido2Agente(dto.getApellido2Agente());
        entidad.setApellido2Infractor(dto.getApellido2Infractor());
        entidad.setApellido2Propietario(dto.getApellido2Propietario());
        entidad.setApellido2Testigo(dto.getApellido2Testigo());
        entidad.setCodigoCategLicenConduInfr(dto.getCodigoCategLicenConduInfr());
        entidad.setCodigoClaseVehiculo(dto.getCodigoClaseVehiculo());
        entidad.setCodigoDepartamentoInfraccion(dto.getCodigoDepartamentoInfraccion());
        entidad.setCodigoDepartamentoInfractor(dto.getCodigoDepartamentoInfractor());
        entidad.setCodigoDepartamentoPatio(dto.getCodigoDepartamentoPatio());
        entidad.setCodigoDepartamentoTestigo(dto.getCodigoDepartamentoTestigo());
        entidad.setCodigoInfraccion(dto.getCodigoInfraccion());
        entidad.setCodigoLocalidadInfraccion(dto.getCodigoLocalidadInfraccion());
        entidad.setCodigoLocalidadInfractor(dto.getCodigoLocalidadInfractor());
        entidad.setCodigoLocalidadPatio(dto.getCodigoLocalidadPatio());
        entidad.setCodigoLocalidadTestigo(dto.getCodigoLocalidadTestigo());
        entidad.setCodigoMedioImposicion(dto.getCodigoMedioImposicion());
        entidad.setCodigoModalidad(dto.getCodigoModalidad());
        entidad.setCodigoMunicipioInfraccion(dto.getCodigoMunicipioInfraccion());
        entidad.setCodigoMunicipioInfractor(dto.getCodigoMunicipioInfractor());
        entidad.setCodigoMunicipioPatio(dto.getCodigoMunicipioPatio());
        entidad.setCodigoMunicipioTestigo(dto.getCodigoMunicipioTestigo());
        entidad.setCodigoOrganLicenConduInfr(dto.getCodigoOrganLicenConduInfr());
        entidad.setCodigoOrganismoLicenTrans(dto.getCodigoOrganismoLicenTrans());
        entidad.setCodigoOrganismoMatriVehic(dto.getCodigoOrganismoMatriVehic());
        entidad.setCodigoOrganismoTransito(dto.getCodigoOrganismoTransito());
        entidad.setCodigoOrigenComparendo(dto.getCodigoOrigenComparendo());
        entidad.setCodigoPaisDirecInfractor(dto.getCodigoPaisDirecInfractor());
        entidad.setCodigoPaisDirecPatio(dto.getCodigoPaisDirecPatio());
        entidad.setCodigoPaisDirecTestigo(dto.getCodigoPaisDirecTestigo());
        entidad.setCodigoPaisInfraccion(dto.getCodigoPaisInfraccion());
        entidad.setCodigoRadioAccion(dto.getCodigoRadioAccion());
        entidad.setCodigoTipoAgenteImpositor(dto.getCodigoTipoAgenteImpositor());
        entidad.setCodigoTipoIdentAgente(dto.getCodigoTipoIdentAgente());
        entidad.setCodigoTipoIdentEmpresa(dto.getCodigoTipoIdentEmpresa());
        entidad.setCodigoTipoIdentInfractor(dto.getCodigoTipoIdentInfractor());
        entidad.setCodigoTipoIdentPropietario(dto.getCodigoTipoIdentPropietario());
        entidad.setCodigoTipoIdentTestigo(dto.getCodigoTipoIdentTestigo());
        entidad.setCodigoTipoInfractor(dto.getCodigoTipoInfractor());
        entidad.setCodigoTipoNotifCompa(dto.getCodigoTipoNotifCompa());
        entidad.setCodigoTipoServicio(dto.getCodigoTipoServicio());
        entidad.setCodigoTipoTransPasajero(dto.getCodigoTipoTransPasajero());
        entidad.setConsecutivoInmovilizacion(dto.getConsecutivoInmovilizacion());
        entidad.setDireccionBasicaInfraccion(dto.getDireccionBasicaInfraccion());
        entidad.setDireccionBasicaInfractor(dto.getDireccionBasicaInfractor());
        entidad.setDireccionBasicaPatio(dto.getDireccionBasicaPatio());
        entidad.setDireccionBasicaTestigo(dto.getDireccionBasicaTestigo());
        entidad.setEdadInfractor(dto.getEdadInfractor());
        entidad.setEmail(dto.getEmail());
        entidad.setCodigoEstadoComparendo(dto.getCodigoEstadoComparendo());
        entidad.setEstadoLectura(dto.getEstadoLectura());
        entidad.setEvidencia1CodigoTipo(dto.getEvidencia1CodigoTipo());
        entidad.setEvidencia1Nombre(dto.getEvidencia1Nombre());
        entidad.setEvidencia1Url(dto.getEvidencia1Url());
        entidad.setEvidencia2CodigoTipo(dto.getEvidencia2CodigoTipo());
        entidad.setEvidencia2Nombre(dto.getEvidencia2Nombre());
        entidad.setEvidencia2Url(dto.getEvidencia2Url());
        entidad.setExisteFugaInfractor(dto.getExisteFugaInfractor());
        entidad.setFechaExpedLicenConduInfra(dto.getFechaExpedLicenConduInfra());
        entidad.setFechaHoraRecibido(dto.getFechaHoraRecibido());
        entidad.setFechaInfraccion(dto.getFechaInfraccion());
        entidad.setFechaNotificacion(dto.getFechaNotificacion());
        entidad.setFechaPruebaAlcoholemia(dto.getFechaPruebaAlcoholemia());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaVenciLicenConduInfra(dto.getFechaVenciLicenConduInfra());
        entidad.setGradoAlcoholemia(dto.getGradoAlcoholemia());
        entidad.setHoraInfraccion(dto.getHoraInfraccion());
        entidad.setIdentificacionVehiculo(dto.getIdentificacionVehiculo());
        entidad.setInmoviliza(dto.getInmoviliza());
        entidad.setNiegaPruebaAlcoholemia(dto.getNiegaPruebaAlcoholemia());
        entidad.setNombrePatio(dto.getNombrePatio());
        entidad.setNombre1Agente(dto.getNombre1Agente());
        entidad.setNombre1Infractor(dto.getNombre1Infractor());
        entidad.setNombre1Propietario(dto.getNombre1Propietario());
        entidad.setNombre1Testigo(dto.getNombre1Testigo());
        entidad.setNombre2Agente(dto.getNombre2Agente());
        entidad.setNombre2Infractor(dto.getNombre2Infractor());
        entidad.setNombre2Propietario(dto.getNombre2Propietario());
        entidad.setNombre2Testigo(dto.getNombre2Testigo());
        entidad.setNumeroCitacion(dto.getNumeroCitacion());
        entidad.setNumeroGrua(dto.getNumeroGrua());
        entidad.setNumeroIdentPropietario(dto.getNumeroIdentPropietario());
        entidad.setNumeroIdentTestigo(dto.getNumeroIdentTestigo());
        entidad.setNumeroIdentificacionAgente(dto.getNumeroIdentificacionAgente());
        entidad.setNumeroIdentificacionEmpresa(dto.getNumeroIdentificacionEmpresa());
        entidad.setNumeroIdentificacionInfractor(dto.getNumeroIdentificacionInfractor());
        entidad.setNumeroLicencia(dto.getNumeroLicencia());
        entidad.setNumeroLicenciaTransito(dto.getNumeroLicenciaTransito());
        entidad.setNumeroPatio(dto.getNumeroPatio());
        entidad.setNumeroPruebaAlcoholemia(dto.getNumeroPruebaAlcoholemia());
        entidad.setNumeroReincidencia(dto.getNumeroReincidencia());
        entidad.setNumeroTarjetaOperacion(dto.getNumeroTarjetaOperacion());
        entidad.setObservacionesAgente(dto.getObservacionesAgente());
        entidad.setPlacaAgente(dto.getPlacaAgente());
        entidad.setPlacaGrua(dto.getPlacaGrua());
        entidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        entidad.setRazonSocialEmpresa(dto.getRazonSocialEmpresa());
        entidad.setRazonSocialInfractor(dto.getRazonSocialInfractor());
        entidad.setRazonSocialPropietario(dto.getRazonSocialPropietario());
        entidad.setTelefonoFijoInfractor(dto.getTelefonoFijoInfractor());
        entidad.setTelefonoMovilInfractor(dto.getTelefonoMovilInfractor());
        entidad.setTelefonoMovilTestigo(dto.getTelefonoMovilTestigo());
        entidad.setValorConcepto(dto.getValorConcepto());
        entidad.setValorGradoAlcoholemia(dto.getValorGradoAlcoholemia());
        entidad.setVelocidadVehiculo(dto.getVelocidadVehiculo());
        entidad.setIdFacturaAxis(dto.getIdFacturaAxis());
        entidad.setLongitudDireccionInfraccion(dto.getLongitudDireccionInfraccion());
        entidad.setLatitudDireccionInfraccion(dto.getLatitudDireccionInfraccion());
        entidad.setNombreLocalidadInfraccion(dto.getNombreLocalidadInfraccion());
        entidad.setSecuenciaUnica(dto.getSecuenciaUnica());

        return entidad;
    }

    public static ItMulta toLevel1Entity(ItMultaDTO dto, ItMulta entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ItMulta> toListLevel0Entity(List<ItMultaDTO> listDto) {
        List<ItMulta> listEntidad = new ArrayList<ItMulta>();
        for (ItMultaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItMulta> toListLevel1Entity(List<ItMultaDTO> listDto) {
        List<ItMulta> listEntidad = new ArrayList<ItMulta>();
        for (ItMultaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
