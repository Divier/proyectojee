package co.com.datatools.c2.dto;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.enumeraciones.EnumCampoArchivoSimit;
import co.com.datatools.c2.util.ArchivoSimitUtil;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Contiene una linea archivo de notificacion a Simit
 * 
 * @author julio.pinzon
 */
public class LineaArchivoNotCompSimitDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos

    private String separador;
    private String consecutivo;
    private String numeroComparendo;
    private String fechaImposicionComparendo;
    private String horaImposicionComparendo;
    private String direccionLugarInfraccion;
    private String municipio;
    private String localidad;
    private String placaVehiculo;
    private String matriculadoEn;
    private String tipoVehiculo;
    private String claseServicio;
    private String radioAccion;
    private String modalidadTransporte;
    private String transportePasajeros;
    private String numeroDocumentoInfractor;
    private String tipoDocumentoInfractor;
    private String nombresInfractor;
    private String apellidosInfractor;
    private String edadInfractor;
    private String direccionInfractor;
    private String correoElectronicoInfractor;
    private String telefonoInfractor;
    private String ciudadResidenciaInfractor;
    private String numeroLicenciaConduccion;
    private String categoriaLicenciaConduccion;
    private String organismoExpideLicencia;
    private String fechaVencimientoLicenciaConduccionInfractor;
    private String tipoInfractor;
    private String numeroLicenciaTransito;
    private String organismoLicenciaTransito;
    private String numeroDocumentoPropietario;
    private String tipoDocumentoPropietario;
    private String nombresApellidosPropietario;
    private String razonSocial;
    private String numeroDocumentoEmpresa;
    private String numeroTarjetaOperacion;
    private String placaAgenteTransito;
    private String observacionesAgente;
    private String existeFugaInfractor;
    private String reportaAccidente;
    private String reportaInmovilizacion;
    private String nombrePatio;
    private String direccionPatioInmovilizacion;
    private String numeroGrua;
    private String placaGrua;
    private String consecutivoInmovilizacion;
    private String numeroDocumentoTestigo;
    private String nombresApellidosTestigo;
    private String direccionTestigo;
    private String numeroCelularTestigo;
    private String valorComparendo;
    private String valoresAdicionales;
    private String organismoReporta;
    private String estadoComparendo;
    private String polca;
    private String codigoInfraccion;
    private String valorTarifaInfraccion;
    private String gradoAlcoholemia;
    private String tipoComparendo;
    private String fechaNotificacion;

    // --- Constructor
    public LineaArchivoNotCompSimitDTO() {
    }

    public String getSeparador() {
        return separador;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getFechaImposicionComparendo() {
        return fechaImposicionComparendo;
    }

    public void setFechaImposicionComparendo(String fechaImposicionComparendo) {
        this.fechaImposicionComparendo = fechaImposicionComparendo;
    }

    public String getHoraImposicionComparendo() {
        return horaImposicionComparendo;
    }

    public void setHoraImposicionComparendo(String horaImposicionComparendo) {
        this.horaImposicionComparendo = horaImposicionComparendo;
    }

    public String getDireccionLugarInfraccion() {
        return direccionLugarInfraccion;
    }

    public void setDireccionLugarInfraccion(String direccionLugarInfraccion) {
        this.direccionLugarInfraccion = direccionLugarInfraccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMatriculadoEn() {
        return matriculadoEn;
    }

    public void setMatriculadoEn(String matriculadoEn) {
        this.matriculadoEn = matriculadoEn;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getClaseServicio() {
        return claseServicio;
    }

    public void setClaseServicio(String claseServicio) {
        this.claseServicio = claseServicio;
    }

    public String getRadioAccion() {
        return radioAccion;
    }

    public void setRadioAccion(String radioAccion) {
        this.radioAccion = radioAccion;
    }

    public String getModalidadTransporte() {
        return modalidadTransporte;
    }

    public void setModalidadTransporte(String modalidadTransporte) {
        this.modalidadTransporte = modalidadTransporte;
    }

    public String getTransportePasajeros() {
        return transportePasajeros;
    }

    public void setTransportePasajeros(String transportePasajeros) {
        this.transportePasajeros = transportePasajeros;
    }

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public String getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(String tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public String getNombresInfractor() {
        return nombresInfractor;
    }

    public void setNombresInfractor(String nombresInfractor) {
        this.nombresInfractor = nombresInfractor;
    }

    public String getApellidosInfractor() {
        return apellidosInfractor;
    }

    public void setApellidosInfractor(String apellidosInfractor) {
        this.apellidosInfractor = apellidosInfractor;
    }

    public String getEdadInfractor() {
        return edadInfractor;
    }

    public void setEdadInfractor(String edadInfractor) {
        this.edadInfractor = edadInfractor;
    }

    public String getDireccionInfractor() {
        return direccionInfractor;
    }

    public void setDireccionInfractor(String direccionInfractor) {
        this.direccionInfractor = direccionInfractor;
    }

    public String getCorreoElectronicoInfractor() {
        return correoElectronicoInfractor;
    }

    public void setCorreoElectronicoInfractor(String correoElectronicoInfractor) {
        this.correoElectronicoInfractor = correoElectronicoInfractor;
    }

    public String getTelefonoInfractor() {
        return telefonoInfractor;
    }

    public void setTelefonoInfractor(String telefonoInfractor) {
        this.telefonoInfractor = telefonoInfractor;
    }

    public String getCiudadResidenciaInfractor() {
        return ciudadResidenciaInfractor;
    }

    public void setCiudadResidenciaInfractor(String ciudadResidenciaInfractor) {
        this.ciudadResidenciaInfractor = ciudadResidenciaInfractor;
    }

    public String getNumeroLicenciaConduccion() {
        return numeroLicenciaConduccion;
    }

    public void setNumeroLicenciaConduccion(String numeroLicenciaConduccion) {
        this.numeroLicenciaConduccion = numeroLicenciaConduccion;
    }

    public String getCategoriaLicenciaConduccion() {
        return categoriaLicenciaConduccion;
    }

    public void setCategoriaLicenciaConduccion(String categoriaLicenciaConduccion) {
        this.categoriaLicenciaConduccion = categoriaLicenciaConduccion;
    }

    public String getOrganismoExpideLicencia() {
        return organismoExpideLicencia;
    }

    public void setOrganismoExpideLicencia(String organismoExpideLicencia) {
        this.organismoExpideLicencia = organismoExpideLicencia;
    }

    public String getFechaVencimientoLicenciaConduccionInfractor() {
        return fechaVencimientoLicenciaConduccionInfractor;
    }

    public void setFechaVencimientoLicenciaConduccionInfractor(String fechaVencimientoLicenciaConduccionInfractor) {
        this.fechaVencimientoLicenciaConduccionInfractor = fechaVencimientoLicenciaConduccionInfractor;
    }

    public String getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(String tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public String getNumeroLicenciaTransito() {
        return numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public String getOrganismoLicenciaTransito() {
        return organismoLicenciaTransito;
    }

    public void setOrganismoLicenciaTransito(String organismoLicenciaTransito) {
        this.organismoLicenciaTransito = organismoLicenciaTransito;
    }

    public String getNumeroDocumentoPropietario() {
        return numeroDocumentoPropietario;
    }

    public void setNumeroDocumentoPropietario(String numeroDocumentoPropietario) {
        this.numeroDocumentoPropietario = numeroDocumentoPropietario;
    }

    public String getTipoDocumentoPropietario() {
        return tipoDocumentoPropietario;
    }

    public void setTipoDocumentoPropietario(String tipoDocumentoPropietario) {
        this.tipoDocumentoPropietario = tipoDocumentoPropietario;
    }

    public String getNombresApellidosPropietario() {
        return nombresApellidosPropietario;
    }

    public void setNombresApellidosPropietario(String nombresApellidosPropietario) {
        this.nombresApellidosPropietario = nombresApellidosPropietario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroDocumentoEmpresa() {
        return numeroDocumentoEmpresa;
    }

    public void setNumeroDocumentoEmpresa(String numeroDocumentoEmpresa) {
        this.numeroDocumentoEmpresa = numeroDocumentoEmpresa;
    }

    public String getNumeroTarjetaOperacion() {
        return numeroTarjetaOperacion;
    }

    public void setNumeroTarjetaOperacion(String numeroTarjetaOperacion) {
        this.numeroTarjetaOperacion = numeroTarjetaOperacion;
    }

    public String getPlacaAgenteTransito() {
        return placaAgenteTransito;
    }

    public void setPlacaAgenteTransito(String placaAgenteTransito) {
        this.placaAgenteTransito = placaAgenteTransito;
    }

    public String getObservacionesAgente() {
        return observacionesAgente;
    }

    public void setObservacionesAgente(String observacionesAgente) {
        this.observacionesAgente = observacionesAgente;
    }

    public String getExisteFugaInfractor() {
        return existeFugaInfractor;
    }

    public void setExisteFugaInfractor(String existeFugaInfractor) {
        this.existeFugaInfractor = existeFugaInfractor;
    }

    public String getReportaAccidente() {
        return reportaAccidente;
    }

    public void setReportaAccidente(String reportaAccidente) {
        this.reportaAccidente = reportaAccidente;
    }

    public String getReportaInmovilizacion() {
        return reportaInmovilizacion;
    }

    public void setReportaInmovilizacion(String reportaInmovilizacion) {
        this.reportaInmovilizacion = reportaInmovilizacion;
    }

    public String getNombrePatio() {
        return nombrePatio;
    }

    public void setNombrePatio(String nombrePatio) {
        this.nombrePatio = nombrePatio;
    }

    public String getDireccionPatioInmovilizacion() {
        return direccionPatioInmovilizacion;
    }

    public void setDireccionPatioInmovilizacion(String direccionPatioInmovilizacion) {
        this.direccionPatioInmovilizacion = direccionPatioInmovilizacion;
    }

    public String getNumeroGrua() {
        return numeroGrua;
    }

    public void setNumeroGrua(String numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getPlacaGrua() {
        return placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public String getConsecutivoInmovilizacion() {
        return consecutivoInmovilizacion;
    }

    public void setConsecutivoInmovilizacion(String consecutivoInmovilizacion) {
        this.consecutivoInmovilizacion = consecutivoInmovilizacion;
    }

    public String getNumeroDocumentoTestigo() {
        return numeroDocumentoTestigo;
    }

    public void setNumeroDocumentoTestigo(String numeroDocumentoTestigo) {
        this.numeroDocumentoTestigo = numeroDocumentoTestigo;
    }

    public String getNombresApellidosTestigo() {
        return nombresApellidosTestigo;
    }

    public void setNombresApellidosTestigo(String nombresApellidosTestigo) {
        this.nombresApellidosTestigo = nombresApellidosTestigo;
    }

    public String getDireccionTestigo() {
        return direccionTestigo;
    }

    public void setDireccionTestigo(String direccionTestigo) {
        this.direccionTestigo = direccionTestigo;
    }

    public String getNumeroCelularTestigo() {
        return numeroCelularTestigo;
    }

    public void setNumeroCelularTestigo(String numeroCelularTestigo) {
        this.numeroCelularTestigo = numeroCelularTestigo;
    }

    public String getValorComparendo() {
        return valorComparendo;
    }

    public void setValorComparendo(String valorComparendo) {
        this.valorComparendo = valorComparendo;
    }

    public String getValoresAdicionales() {
        return valoresAdicionales;
    }

    public void setValoresAdicionales(String valoresAdicionales) {
        this.valoresAdicionales = valoresAdicionales;
    }

    public String getOrganismoReporta() {
        return organismoReporta;
    }

    public void setOrganismoReporta(String organismoReporta) {
        this.organismoReporta = organismoReporta;
    }

    public String getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(String estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public String getPolca() {
        return polca;
    }

    public void setPolca(String polca) {
        this.polca = polca;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getValorTarifaInfraccion() {
        return valorTarifaInfraccion;
    }

    public void setValorTarifaInfraccion(String valorTarifaInfraccion) {
        this.valorTarifaInfraccion = valorTarifaInfraccion;
    }

    public String getGradoAlcoholemia() {
        return gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(String gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public String getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(String tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    @Override
    public String toString() {

        /*
         * Arma linea de archivo y reemplaza cada valor para que no pueda contener el separador
         */
        StringBuilder sb = new StringBuilder();

        sb.append(consecutivo)
                .append(separador)
                .append(ArchivoSimitUtil.validarValor(numeroComparendo, separador,
                        EnumCampoArchivoSimit.NUMERO_COMPARENDO))
                .append(ArchivoSimitUtil.validarValor(fechaImposicionComparendo, separador,
                        EnumCampoArchivoSimit.FECHA_IMPOSICION_COMPARENDO))
                .append(ArchivoSimitUtil.validarValor(horaImposicionComparendo, separador,
                        EnumCampoArchivoSimit.HORA_IMPOSICION_COMPARENDO))
                .append(ArchivoSimitUtil.validarValor(direccionLugarInfraccion, separador,
                        EnumCampoArchivoSimit.DIRECCION_LUGAR_INFRACCION))
                .append(ArchivoSimitUtil.validarValor(municipio, separador, EnumCampoArchivoSimit.MUNICIPIO))
                .append(ArchivoSimitUtil.validarValor(localidad, separador, EnumCampoArchivoSimit.LOCALIDAD))
                .append(ArchivoSimitUtil.validarValor(placaVehiculo, separador, EnumCampoArchivoSimit.PLACA_VEHICULO))
                .append(ArchivoSimitUtil.validarValor(matriculadoEn, separador, EnumCampoArchivoSimit.MATRICULADO_EN))
                .append(ArchivoSimitUtil.validarValor(tipoVehiculo, separador, EnumCampoArchivoSimit.TIPO_VEHICULO))
                .append(ArchivoSimitUtil.validarValor(claseServicio, separador, EnumCampoArchivoSimit.CLASE_SERVICIO))
                .append(ArchivoSimitUtil.validarValor(radioAccion, separador, EnumCampoArchivoSimit.RADIO_ACCION))
                .append(ArchivoSimitUtil.validarValor(modalidadTransporte, separador,
                        EnumCampoArchivoSimit.MODALIDAD_TRANSPORTE))
                .append(ArchivoSimitUtil.validarValor(transportePasajeros, separador,
                        EnumCampoArchivoSimit.TRANSPORTE_PASAJEROS))
                .append(ArchivoSimitUtil.validarValor(numeroDocumentoInfractor, separador,
                        EnumCampoArchivoSimit.NUMERO_DOCUMENTO_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(tipoDocumentoInfractor, separador,
                        EnumCampoArchivoSimit.TIPO_DOCUMENTO_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(nombresInfractor, separador,
                        EnumCampoArchivoSimit.NOMBRES_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(apellidosInfractor, separador,
                        EnumCampoArchivoSimit.APELLIDOS_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(edadInfractor, separador, EnumCampoArchivoSimit.EDAD_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(direccionInfractor, separador,
                        EnumCampoArchivoSimit.DIRECCION_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(correoElectronicoInfractor, separador,
                        EnumCampoArchivoSimit.CORREO_ELECTRONICO_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(telefonoInfractor, separador,
                        EnumCampoArchivoSimit.TELEFONO_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(ciudadResidenciaInfractor, separador,
                        EnumCampoArchivoSimit.CIUDAD_RESIDENCIA_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(numeroLicenciaConduccion, separador,
                        EnumCampoArchivoSimit.NUMERO_LICENCIA_CONDUCCION))
                .append(ArchivoSimitUtil.validarValor(categoriaLicenciaConduccion, separador,
                        EnumCampoArchivoSimit.CATEGORIA_LICENCIA_CONDUCCION))
                .append(ArchivoSimitUtil.validarValor(organismoExpideLicencia, separador,
                        EnumCampoArchivoSimit.ORGANISMO_EXPIDE_LICENCIA))
                .append(ArchivoSimitUtil.validarValor(fechaVencimientoLicenciaConduccionInfractor, separador,
                        EnumCampoArchivoSimit.FECHA_VENCIMIENTO_LICENCIA_CONDUCCION_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(tipoInfractor, separador, EnumCampoArchivoSimit.TIPO_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(numeroLicenciaTransito, separador,
                        EnumCampoArchivoSimit.NUMERO_LICENCIA_TRANSITO))
                .append(ArchivoSimitUtil.validarValor(organismoLicenciaTransito, separador,
                        EnumCampoArchivoSimit.ORGANISMOLICENCIA_TRANSITO))
                .append(ArchivoSimitUtil.validarValor(numeroDocumentoPropietario, separador,
                        EnumCampoArchivoSimit.NUMERO_DOCUMENTO_PROPIETARIO))
                .append(ArchivoSimitUtil.validarValor(tipoDocumentoPropietario, separador,
                        EnumCampoArchivoSimit.TIPO_DOCUMENTO_PROPIETARIO))
                .append(ArchivoSimitUtil.validarValor(nombresApellidosPropietario, separador,
                        EnumCampoArchivoSimit.NOMBRES_APELLIDOS_PROPIETARIO))
                .append(ArchivoSimitUtil.validarValor(razonSocial, separador, EnumCampoArchivoSimit.RAZON_SOCIAL))
                .append(ArchivoSimitUtil.validarValor(numeroDocumentoEmpresa, separador,
                        EnumCampoArchivoSimit.NUMERO_DOCUMENTO_EMPRESA))
                .append(ArchivoSimitUtil.validarValor(numeroTarjetaOperacion, separador,
                        EnumCampoArchivoSimit.NUMERO_TARJETA_OPERACION))
                .append(ArchivoSimitUtil.validarValor(placaAgenteTransito, separador,
                        EnumCampoArchivoSimit.PLACA_AGENTE_TRANSITO))
                .append(ArchivoSimitUtil.validarValor(observacionesAgente, separador,
                        EnumCampoArchivoSimit.OBSERVACIONES_AGENTE))
                .append(ArchivoSimitUtil.validarValor(existeFugaInfractor, separador,
                        EnumCampoArchivoSimit.EXISTE_FUGA_INFRACTOR))
                .append(ArchivoSimitUtil.validarValor(reportaAccidente, separador,
                        EnumCampoArchivoSimit.REPORTA_ACCIDENTE))
                .append(ArchivoSimitUtil.validarValor(reportaInmovilizacion, separador,
                        EnumCampoArchivoSimit.REPORTA_INMOVILIZACION))
                .append(ArchivoSimitUtil.validarValor(nombrePatio, separador, EnumCampoArchivoSimit.NOMBRE_PATIO))
                .append(ArchivoSimitUtil.validarValor(direccionPatioInmovilizacion, separador,
                        EnumCampoArchivoSimit.DIRECCION_PATIO_INMOVILIZACION))
                .append(ArchivoSimitUtil.validarValor(numeroGrua, separador, EnumCampoArchivoSimit.NUMERO_GRUA))
                .append(ArchivoSimitUtil.validarValor(placaGrua, separador, EnumCampoArchivoSimit.PLACA_GRUA))
                .append(ArchivoSimitUtil.validarValor(consecutivoInmovilizacion, separador,
                        EnumCampoArchivoSimit.CONSECUTIVO_INMOVILIZACION))
                .append(ArchivoSimitUtil.validarValor(numeroDocumentoTestigo, separador,
                        EnumCampoArchivoSimit.NUMERO_DOCUMENTO_TESTIGO))
                .append(ArchivoSimitUtil.validarValor(nombresApellidosTestigo, separador,
                        EnumCampoArchivoSimit.NOMBRES_APELLIDOS_TESTIGO))
                .append(ArchivoSimitUtil.validarValor(direccionTestigo, separador,
                        EnumCampoArchivoSimit.DIRECCION_TESTIGO))
                .append(ArchivoSimitUtil.validarValor(numeroCelularTestigo, separador,
                        EnumCampoArchivoSimit.NUMERO_CELULAR_TESTIGO))
                .append(ArchivoSimitUtil.validarValor(valorComparendo, separador,
                        EnumCampoArchivoSimit.VALOR_COMPARENDO))
                .append(ArchivoSimitUtil.validarValor(valoresAdicionales, separador,
                        EnumCampoArchivoSimit.VALORES_ADICIONALES))
                .append(ArchivoSimitUtil.validarValor(organismoReporta, separador,
                        EnumCampoArchivoSimit.ORGANISMO_REPORTA))
                .append(ArchivoSimitUtil.validarValor(estadoComparendo, separador,
                        EnumCampoArchivoSimit.ESTADO_COMPARENDO))
                .append(ArchivoSimitUtil.validarValor(polca, separador, EnumCampoArchivoSimit.POLCA))
                .append(ArchivoSimitUtil.validarValor(codigoInfraccion, separador,
                        EnumCampoArchivoSimit.CODIGO_INFRACCION))
                .append(ArchivoSimitUtil.validarValor(valorTarifaInfraccion, separador,
                        EnumCampoArchivoSimit.VALOR_TARIFA_INFRACCION))
                .append(ArchivoSimitUtil.validarValor(gradoAlcoholemia, separador,
                        EnumCampoArchivoSimit.GRADO_ALCOHOLEMIA))
                .append(ArchivoSimitUtil.validarValor(tipoComparendo, separador, EnumCampoArchivoSimit.TIPO_COMPARENDO))
                .append(StringUtils.replace(ArchivoSimitUtil.validarValor(fechaNotificacion, separador,
                        EnumCampoArchivoSimit.FECHA_NOTIFICACION), separador, "")).append(ArchivoSimitUtil.FIN_LINEA);
        return sb.toString();
    }
}
