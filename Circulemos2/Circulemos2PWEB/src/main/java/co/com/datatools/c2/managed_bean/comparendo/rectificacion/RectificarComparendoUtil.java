package co.com.datatools.c2.managed_bean.comparendo.rectificacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.RectificaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumCampoEntidad;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;

/**
 * Clase utilitaria utilizada en RectificarComparendoMB
 * 
 * @author luis.forero (2016-02-10)
 * 
 */
public class RectificarComparendoUtil {

    private RectificarComparendoUtil() {
    }

    /**
     * Transforma un ComparendoPersonaDTO a un ProcesaComparendoPersona
     * 
     * @param comparendoPersona
     *            datos del comparendo persona
     * @return retorna una persona procesa comparendo con sus datos correspondientes.
     * @author luis.forero(mod 2016-01-29)
     */
    public static ProcesaComparendoPersonaDTO getProcesaComparendoPersonaDTO(ComparendoPersonaDTO comparendoPersona) {
        ProcesaComparendoPersonaDTO procesaInfractor = new ProcesaComparendoPersonaDTO();
        procesaInfractor.setIdTipoIdentificacion(comparendoPersona.getTipoIdentificacion().getId());
        procesaInfractor.setNumeroIdentificacion(comparendoPersona.getNumeroIdentificacion());
        procesaInfractor.setNombre1(comparendoPersona.getNombre1());
        procesaInfractor.setNombre2(comparendoPersona.getNombre2());
        procesaInfractor.setApellido1(comparendoPersona.getApellido1());
        procesaInfractor.setApellido2(comparendoPersona.getApellido2());
        procesaInfractor.setRazonSocial(comparendoPersona.getRazonSocial());

        procesaInfractor.setEdad(comparendoPersona.getEdad());
        procesaInfractor.setEmail(comparendoPersona.getEmail());
        procesaInfractor.setTelefonoFijo(comparendoPersona.getTelefonoFijo());
        procesaInfractor.setTelefonoMovil(comparendoPersona.getTelefonoMovil());

        // Datos licencia del infractor
        procesaInfractor
                .setCodigoOrganismoLicencia(comparendoPersona.getOrganismoTransito() != null ? comparendoPersona
                        .getOrganismoTransito().getCodigoOrganismo() : null);
        procesaInfractor.setNumeroLicencia(comparendoPersona.getNumeroLicencia());
        procesaInfractor
                .setIdCategoriaLicenciaCondu(comparendoPersona.getCategoriaLicencia() != null ? comparendoPersona
                        .getCategoriaLicencia().getId() : null);
        procesaInfractor.setFechaExpedicionLicenCondu(comparendoPersona.getFechaExpedicionLicenCondu());
        procesaInfractor.setFechaVencimientoLicenCondu(comparendoPersona.getFechaVencimientoLicenCondu());
        return procesaInfractor;
    }

    /**
     * Crea una isntancia nueva de un campo rectificado del comparendo
     * 
     * @param campoEntidad
     *            campo sobre el cual se efectua la rectificacion
     * @param valorOriginal
     *            valor original del campo
     * @param valorNuevo
     *            valor nuevo que toma el campo
     * @return Retorna una instancia nueva del campo correspondiente.
     * @author luis.forero(2016-02-03)
     */
    private static CampoRectificaComparendoDTO crearCambioCampo(EnumCampoEntidad campoEntidad, String valorOriginal,
            String valorNuevo) {
        CampoRectificaComparendoDTO campo = new CampoRectificaComparendoDTO();
        campo.setCampoEntidad(new CampoEntidadDTO(campoEntidad.getValue()));
        campo.setValorOriginal(valorOriginal);
        campo.setValorNuevo(valorNuevo);
        return campo;
    }

    /**
     * identifica si se efectua o no un cambio en el campo de la entidad para los tipos de datos String.
     * 
     * @param campoAntiguo
     *            campo antiguo de la entidad
     * @param campoNuevo
     *            campo nuevo de la entidad
     * @param lstCambios
     *            listado de cambios sobre el que se adicionan los cambios de existir
     * @param campoEntidad
     *            campo de la entidad que se está verificando.
     * @author luis.forero(2016-02-05)
     */
    private static void identificarCambioCampoString(String campoAntiguo, String campoNuevo,
            List<CampoRectificaComparendoDTO> lstCambios, EnumCampoEntidad campoEntidad) {
        if (StringUtils.isNotBlank(campoAntiguo)) {
            if (!campoAntiguo.equals(campoNuevo)) {
                lstCambios.add(crearCambioCampo(campoEntidad, campoAntiguo, campoNuevo));
            }
        } else if (StringUtils.isNotBlank(campoNuevo)) {
            lstCambios.add(crearCambioCampo(campoEntidad, null, campoNuevo));
        }
    }

    /**
     * Permite identificar si existe un cambio en campos de tipo Fecha (Date)
     * 
     * @param fechaAntigua
     *            valor de la fecha antigua
     * @param fechaNueva
     *            valor de la fecha nueva
     * @param lstCambios
     *            lista de cambios efectuados en caso de que existan
     * @param campoEntidad
     *            campo que se esta evaluando
     * @param formatoFecha
     *            Formato en el cual se registrara el cambio del campo efectuado
     * @author luis.forero(2016-02-08)
     */
    private static void identificarCambioCampoFecha(java.util.Date fechaAntigua, java.util.Date fechaNueva,
            List<CampoRectificaComparendoDTO> lstCambios, EnumCampoEntidad campoEntidad, String formatoFecha) {
        if (fechaAntigua != null) {
            if (fechaAntigua.compareTo(fechaNueva) != 0) {
                lstCambios.add(crearCambioCampo(campoEntidad, UtilFecha.dateToString(fechaAntigua, formatoFecha),
                        UtilFecha.dateToString(fechaNueva, formatoFecha)));
            }
        } else if (fechaNueva != null) {
            lstCambios.add(crearCambioCampo(campoEntidad, null, UtilFecha.dateToString(fechaNueva, formatoFecha)));
        }

    }

    /**
     * Permite identificar el cambio de un campo de tipo direccion de una entidad
     * 
     * @param lstCambios
     *            listado de cambios al cual se agregan modificaciones en caso de existir
     * @param direccionAntigua
     *            direccion antigua a verificar si cambio
     * @param direccionNueva
     *            direccion nueva que recibe si cambio
     * @param campoEntidad
     *            campo de la entidad que esta evaluando el campo
     * @author luis.forero(2016-02-05)
     */
    private static void identificarCambioCampoDireccion(List<CampoRectificaComparendoDTO> lstCambios,
            DireccionDTO direccionAntigua, DireccionDTO direccionNueva, EnumCampoEntidad campoEntidad) {
        if (direccionAntigua != null) {
            if (direccionNueva == null || !direccionAntigua.toString().equals(direccionNueva.toString())) {
                lstCambios.add(crearCambioCampo(campoEntidad, String.valueOf(direccionAntigua),
                        String.valueOf(direccionNueva)));
            }
        } else if (direccionNueva != null) {
            lstCambios.add(crearCambioCampo(campoEntidad, null, String.valueOf(direccionNueva)));
        }
    }

    /**
     * Identifica si existe un cambio para tipos de datos enteros en un determinado campo
     * 
     * @param campoAntiguo
     *            valor antiguo
     * @param campoNuevo
     *            valor nuevo
     * @param lstCambios
     *            listado de cambios donde se registran
     * @param campoEntidad
     *            campo de la entidad que se esta evaluando
     * 
     * @author luis.forero(2016-02-08)
     */
    private static void identificarCambioCampoInteger(Integer campoAntiguo, Integer campoNuevo,
            List<CampoRectificaComparendoDTO> lstCambios, EnumCampoEntidad campoEntidad) {
        if (campoAntiguo != null) {
            if (!campoAntiguo.equals(campoNuevo)) {
                lstCambios
                        .add(crearCambioCampo(campoEntidad, String.valueOf(campoAntiguo), String.valueOf(campoNuevo)));
            }
        } else if (campoNuevo != null) {
            lstCambios.add(crearCambioCampo(campoEntidad, null, String.valueOf(campoNuevo)));
        }
    }

    /**
     * Identifica los cambios efectuados en los datos de una persona
     * 
     * @param personaAntigua
     *            datos de la persona antiguos a ser comparados
     * @param personaNueva
     *            datos de la persona nueva a ser comparados
     * @param lstCambios
     *            listado al cual se le agregaran los cambios efectuados en la persona
     * @param formatoFecha
     *            Formato de fecha en la cual se quiere registrar los cambios de una fecha
     * @author luis.forero(2016-02-05)
     */
    private static void identificarCambiosComparendoPersona(ComparendoPersonaDTO personaAntigua,
            ProcesaComparendoPersonaDTO personaNueva, List<CampoRectificaComparendoDTO> lstCambios, String formatoFecha) {
        EnumTipoPersonaComparendo tipoPersonaComparendo = null;
        if (personaAntigua != null && personaNueva != null) {
            tipoPersonaComparendo = Utilidades.buscarElemEnum(EnumTipoPersonaComparendo.class, personaAntigua
                    .getTipoPersonaComparendo().getCodigo());
            // TIPO DOCUMENTO
            EnumCampoEntidad campoEntidad = null;
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            if (personaAntigua.getTipoIdentificacion() != null
                    && personaAntigua.getTipoIdentificacion().getId() != null) {
                if (!personaAntigua.getTipoIdentificacion().getId().equals(personaNueva.getIdTipoIdentificacion())) {
                    lstCambios.add(crearCambioCampo(campoEntidad,
                            String.valueOf(personaAntigua.getTipoIdentificacion().getId()),
                            String.valueOf(personaNueva.getIdTipoIdentificacion())));
                }
            } else if (personaNueva.getIdTipoIdentificacion() != null) {
                lstCambios.add(crearCambioCampo(campoEntidad, null,
                        String.valueOf(personaNueva.getIdTipoIdentificacion())));
            }
            // NUMERO IDENTIFICACION
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getNumeroIdentificacion(),
                    personaNueva.getNumeroIdentificacion(), lstCambios, campoEntidad);
            // NOMBRE 1
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getNombre1(), personaNueva.getNombre1(), lstCambios,
                    campoEntidad);
            // NOMBRE 2
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getNombre2(), personaNueva.getNombre2(), lstCambios,
                    campoEntidad);

            // APELLIDO 1
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getApellido1(), personaNueva.getApellido1(), lstCambios,
                    campoEntidad);

            // APELLIDO 2
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getApellido2(), personaNueva.getApellido2(), lstCambios,
                    campoEntidad);

            // RAZON SOCIAL
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.RAZON_SOCIAL_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.RAZON_SOCIAL_EMPRESA;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getRazonSocial(), personaNueva.getRazonSocial(), lstCambios,
                    campoEntidad);

            // EDAD
            if (personaAntigua.getEdad() != null) {
                if (!personaAntigua.getEdad().equals(personaNueva.getEdad())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.EDAD_INFRACTOR,
                            String.valueOf(personaAntigua.getEdad()), String.valueOf(personaNueva.getEdad())));
                }
            } else if (personaNueva.getEdad() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.EDAD_INFRACTOR, null,
                        String.valueOf(personaNueva.getEdad())));
            }

            // EMAIL
            identificarCambioCampoString(personaAntigua.getEmail(), personaNueva.getEmail(), lstCambios,
                    EnumCampoEntidad.EMAIL_INFRACTOR);

            // TELEFONO FIJO
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TELEFONO_FIJO_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.TELEFONO_FIJO_EMPRESA;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getTelefonoFijo(), personaNueva.getTelefonoFijo(), lstCambios,
                    campoEntidad);
            // TELEFONO MOVIL
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_EMPRESA;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(personaAntigua.getTelefonoMovil(), personaNueva.getTelefonoMovil(),
                    lstCambios, campoEntidad);
            // Datos licencia del infractor
            // ORGANISMO LICENCIA
            if (personaAntigua.getOrganismoTransito() != null
                    && personaAntigua.getOrganismoTransito().getCodigoOrganismo() != null) {
                if (!personaAntigua.getOrganismoTransito().getCodigoOrganismo()
                        .equals(personaNueva.getCodigoOrganismoLicencia())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.CODIGO_ORG_TRANS_LICENCIA_INFRACTOR,
                            String.valueOf(personaAntigua.getOrganismoTransito().getCodigoOrganismo()),
                            String.valueOf(personaNueva.getCodigoOrganismoLicencia())));
                }
            } else if (personaNueva.getCodigoOrganismoLicencia() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CODIGO_ORG_TRANS_LICENCIA_INFRACTOR, null,
                        String.valueOf(personaNueva.getCodigoOrganismoLicencia())));
            }
            // NUMERO DE LICENCIA
            identificarCambioCampoString(personaAntigua.getNumeroLicencia(), personaNueva.getNumeroLicencia(),
                    lstCambios, EnumCampoEntidad.NUMERO_LICENCIA_CONDUCCION_INFRACTOR);
            // CATEGORIA LICENCIA CONDUCCION
            if (personaAntigua.getCategoriaLicencia() != null && personaAntigua.getCategoriaLicencia().getId() != null) {
                if (!personaAntigua.getCategoriaLicencia().getId().equals(personaNueva.getIdCategoriaLicenciaCondu())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.CATEGORIA_LICENCIA_CONDUCCIÓN_INFRACTOR,
                            String.valueOf(personaAntigua.getCategoriaLicencia().getCodigo()),
                            String.valueOf(personaNueva.getIdCategoriaLicenciaCondu())));
                }
            } else if (personaNueva.getIdCategoriaLicenciaCondu() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CATEGORIA_LICENCIA_CONDUCCIÓN_INFRACTOR, null,
                        String.valueOf(personaNueva.getIdCategoriaLicenciaCondu())));
            }
            // FECHA DE EXPEDICION LICENCIA DE CONDUCCION
            identificarCambioCampoFecha(personaAntigua.getFechaExpedicionLicenCondu(),
                    personaNueva.getFechaExpedicionLicenCondu(), lstCambios,
                    EnumCampoEntidad.FECHA_EXPEDICION_LICENCIA_CONDUC_INFRAC, formatoFecha);
            // FECHA VENCIMIENTO DE LICENCIA DE CONDUCCION
            identificarCambioCampoFecha(personaAntigua.getFechaVencimientoLicenCondu(),
                    personaAntigua.getFechaVencimientoLicenCondu(), lstCambios,
                    EnumCampoEntidad.FECHA_VENCIMIENTO_LICENCIA_CONDUC_INFRAC, formatoFecha);
        } else if (personaNueva != null) {
            tipoPersonaComparendo = Utilidades.buscarElemEnum(EnumTipoPersonaComparendo.class,
                    personaNueva.getCodigoTipoPersonaComparendo());
            // TIPO DOCUMENTO
            EnumCampoEntidad campoEntidad = null;
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.TIPO_DOCUMENTO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            if (personaNueva.getIdTipoIdentificacion() != null) {
                lstCambios.add(crearCambioCampo(campoEntidad, null,
                        String.valueOf(personaNueva.getIdTipoIdentificacion())));
            }
            // NUMERO IDENTIFICACION
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.NUMERO_DOCUMENTO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getNumeroIdentificacion(), lstCambios, campoEntidad);
            // NOMBRE 1
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.PRIMER_NOMBRE_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getNombre1(), lstCambios, campoEntidad);
            // NOMBRE 2
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_NOMBRE_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getNombre2(), lstCambios, campoEntidad);

            // APELLIDO 1
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.PRIMER_APELLIDO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getApellido1(), lstCambios, campoEntidad);

            // APELLIDO 2
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_INFRACTOR;
                break;
            case PROPIETARIO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_PROPIETARIO;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.SEGUNDO_APELLIDO_TESTIGO;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getApellido2(), lstCambios, campoEntidad);

            // RAZON SOCIAL
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.RAZON_SOCIAL_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.RAZON_SOCIAL_EMPRESA;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getRazonSocial(), lstCambios, campoEntidad);

            // EDAD
            if (personaNueva.getEdad() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.EDAD_INFRACTOR, null,
                        String.valueOf(personaNueva.getEdad())));
            }

            // EMAIL
            identificarCambioCampoString(null, personaNueva.getEmail(), lstCambios, EnumCampoEntidad.EMAIL_INFRACTOR);

            // TELEFONO FIJO
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TELEFONO_FIJO_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.TELEFONO_FIJO_EMPRESA;
                break;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getTelefonoFijo(), lstCambios, campoEntidad);
            // TELEFONO MOVIL
            switch (tipoPersonaComparendo) {
            case INFRACTOR:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_INFRACTOR;
                break;
            case EMPRESA_TRANSPORTE:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_EMPRESA;
                break;
            case TESTIGO:
                campoEntidad = EnumCampoEntidad.TELEFONO_MOVIL_TESTIGO;
            default:
                campoEntidad = null;
                break;
            }
            identificarCambioCampoString(null, personaNueva.getTelefonoMovil(), lstCambios, campoEntidad);
            // Datos licencia del infractor
            // ORGANISMO LICENCIA
            if (personaNueva.getCodigoOrganismoLicencia() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CODIGO_ORG_TRANS_LICENCIA_INFRACTOR, null,
                        String.valueOf(personaNueva.getCodigoOrganismoLicencia())));
            }
            // NUMERO DE LICENCIA
            identificarCambioCampoString(null, personaNueva.getNumeroLicencia(), lstCambios,
                    EnumCampoEntidad.NUMERO_LICENCIA_CONDUCCION_INFRACTOR);
            // CATEGORIA LICENCIA CONDUCCION
            if (personaNueva.getIdCategoriaLicenciaCondu() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CATEGORIA_LICENCIA_CONDUCCIÓN_INFRACTOR, null,
                        String.valueOf(personaNueva.getIdCategoriaLicenciaCondu())));
            }
            // FECHA DE EXPEDICION LICENCIA DE CONDUCCION
            identificarCambioCampoFecha(null, personaNueva.getFechaExpedicionLicenCondu(), lstCambios,
                    EnumCampoEntidad.FECHA_EXPEDICION_LICENCIA_CONDUC_INFRAC, formatoFecha);
            // FECHA VENCIMIENTO DE LICENCIA DE CONDUCCION
            identificarCambioCampoFecha(null, personaNueva.getFechaVencimientoLicenCondu(), lstCambios,
                    EnumCampoEntidad.FECHA_VENCIMIENTO_LICENCIA_CONDUC_INFRAC, formatoFecha);
        }

    }

    /**
     * Permite procesar y obtener el listado de cambios efectuados en el comarendo.
     * 
     * @param procesaComparendo
     *            comparendo que va a ser rectificado
     * @param rectificaComparendoFL
     *            utilizado para obtener datos modificados del comparendo
     * @param formatoFecha
     *            Formato de la fecha en el cual se quiere que se registren los cambios de los valores de campos tipo fecha.
     * @return retorna el comparendo procesado con sus respectivos campos cambiados
     * @author luis.forero (2016-02-03)
     */
    static ProcesaComparendoRectificadoDTO procesarCamposCambiados(ProcesaComparendoRectificadoDTO procesaComparendo,
            RectificaComparendoFL rectificaComparendoFL, String formatoFecha) {
        ComparendoDTO datosAntiguos = procesaComparendo.getComparendo();
        List<CampoRectificaComparendoDTO> lstCambios = new ArrayList<CampoRectificaComparendoDTO>();

        // ********************************************************************************
        // ******************************* ENCABEZADO *************************************
        // ********************************************************************************
        if (!procesaComparendo.getCodigoMedioImposicion().equals(datosAntiguos.getMedioImposicion().getId())) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.MEDIO_IMPOSICION,
                    String.valueOf(datosAntiguos.getMedioImposicion().getId()),
                    String.valueOf(procesaComparendo.getCodigoMedioImposicion())));
        }
        if (!procesaComparendo.getFechaInfraccion().equals(datosAntiguos.getFechaInfraccion())) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.FECHA_INFRACCION,
                    UtilFecha.dateToString(datosAntiguos.getFechaInfraccion(), formatoFecha),
                    UtilFecha.dateToString(procesaComparendo.getFechaInfraccion(), formatoFecha)));
        }

        if (!procesaComparendo.getExisteFugaInfractor().equals(datosAntiguos.getExisteFugaInfractor())) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.EXISTE_FUGA_INFRACTOR,
                    String.valueOf(datosAntiguos.getExisteFugaInfractor()),
                    String.valueOf(procesaComparendo.getExisteFugaInfractor())));
        }

        if (!procesaComparendo.getCodigoInfraccion().equals(datosAntiguos.getInfraccion().getCodigo())) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.INFRACCION, datosAntiguos.getInfraccion().getCodigo(),
                    procesaComparendo.getCodigoInfraccion()));
        }

        identificarCambioCampoDireccion(lstCambios, datosAntiguos.getDireccion(),
                rectificaComparendoFL.getDireccionInfraccion(), EnumCampoEntidad.DIRECCION_COMPARENDO);

        // ********************************************************************************
        // ******************************* VEHICULO ***************************************
        // ********************************************************************************
        ComparendoVehiculoDTO comparendoVehiculo = datosAntiguos.getComparendoVehiculo();
        if (comparendoVehiculo != null) {

            identificarCambioCampoString(comparendoVehiculo.getPlacaVehiculo(), procesaComparendo.getPlacaVehiculo(),
                    lstCambios, EnumCampoEntidad.PLACA_VEHICULO);

            identificarCambioCampoString(comparendoVehiculo.getIdentificacionVehiculo(),
                    procesaComparendo.getIdentificacionVehiculo(), lstCambios, EnumCampoEntidad.IDENTIFICACION_VEHICULO);

            OrganismoTransitoDTO organismoMatriVehic = comparendoVehiculo.getOrganismoMatriVehic();
            if (organismoMatriVehic != null && organismoMatriVehic.getCodigoOrganismo() != null) {
                if (!organismoMatriVehic.getCodigoOrganismo().equals(
                        procesaComparendo.getCodigoOrganismoMatriculaVehiculo())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.ORGANISMO_MATRICULA_VEHICULO,
                            String.valueOf(organismoMatriVehic.getCodigoOrganismo()),
                            String.valueOf(procesaComparendo.getCodigoOrganismoMatriculaVehiculo())));
                }
            } else if (procesaComparendo.getCodigoOrganismoMatriculaVehiculo() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.ORGANISMO_MATRICULA_VEHICULO, null,
                        String.valueOf(procesaComparendo.getCodigoOrganismoMatriculaVehiculo())));
            }

            if (comparendoVehiculo.getTipoServicio() != null && comparendoVehiculo.getTipoServicio().getId() != null) {
                if (!comparendoVehiculo.getTipoServicio().getId().equals(procesaComparendo.getIdTipoServicio())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_SERVICIO,
                            String.valueOf(comparendoVehiculo.getTipoServicio().getId()),
                            String.valueOf(procesaComparendo.getIdTipoServicio())));
                }
            } else if (procesaComparendo.getIdTipoServicio() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_SERVICIO, null,
                        String.valueOf(procesaComparendo.getIdTipoServicio())));
            }

            if (comparendoVehiculo.getClaseVehiculo() != null && comparendoVehiculo.getClaseVehiculo().getId() != null) {
                if (!comparendoVehiculo.getClaseVehiculo().getId().equals(procesaComparendo.getIdClaseVehiculo())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.CLASE_VEHICULO,
                            String.valueOf(comparendoVehiculo.getClaseVehiculo().getId()),
                            String.valueOf(procesaComparendo.getIdClaseVehiculo())));
                }
            } else if (procesaComparendo.getIdClaseVehiculo() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CLASE_VEHICULO, null,
                        String.valueOf(procesaComparendo.getIdClaseVehiculo())));
            }

            if (comparendoVehiculo.getRadioAccion() != null && comparendoVehiculo.getRadioAccion().getId() != null) {
                if (!comparendoVehiculo.getRadioAccion().getId().equals(procesaComparendo.getIdRadioAccion())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.RADIO_ACCION,
                            String.valueOf(comparendoVehiculo.getRadioAccion().getId()),
                            String.valueOf(procesaComparendo.getIdRadioAccion())));
                }
            } else if (procesaComparendo.getIdRadioAccion() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.RADIO_ACCION, null,
                        String.valueOf(procesaComparendo.getIdRadioAccion())));
            }

            if (comparendoVehiculo.getModalidad() != null && comparendoVehiculo.getModalidad().getId() != null) {
                if (!comparendoVehiculo.getModalidad().getId().equals(procesaComparendo.getIdModalidad())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.MODALIDAD,
                            String.valueOf(comparendoVehiculo.getModalidad().getId()),
                            String.valueOf(procesaComparendo.getIdModalidad())));
                }
            } else if (procesaComparendo.getIdModalidad() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.MODALIDAD, null,
                        String.valueOf(procesaComparendo.getIdModalidad())));
            }

            if (comparendoVehiculo.getTipoTransPasajero() != null
                    && comparendoVehiculo.getTipoTransPasajero().getId() != null) {
                if (!comparendoVehiculo.getTipoTransPasajero().getId()
                        .equals(procesaComparendo.getCodigoTipoTransPasajero())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_TRANSPORTE_PASAJEROS,
                            String.valueOf(comparendoVehiculo.getTipoTransPasajero().getId()),
                            String.valueOf(procesaComparendo.getCodigoTipoTransPasajero())));
                }
            } else if (procesaComparendo.getCodigoTipoTransPasajero() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_TRANSPORTE_PASAJEROS, null,
                        String.valueOf(procesaComparendo.getCodigoTipoTransPasajero())));
            }

            identificarCambioCampoString(comparendoVehiculo.getNumeroTarjetaOperacion(),
                    procesaComparendo.getNumeroTarjetaOperacion(), lstCambios,
                    EnumCampoEntidad.NUMERO_TARJETA_OPERACION);

        } else {
            if (StringUtils.isNotBlank(procesaComparendo.getPlacaVehiculo())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.PLACA_VEHICULO, null,
                        procesaComparendo.getPlacaVehiculo()));
            }
            if (StringUtils.isNotBlank(procesaComparendo.getIdentificacionVehiculo())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.IDENTIFICACION_VEHICULO, null,
                        procesaComparendo.getIdentificacionVehiculo()));
            }
            if (procesaComparendo.getCodigoOrganismoMatriculaVehiculo() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.ORGANISMO_MATRICULA_VEHICULO, null,
                        String.valueOf(procesaComparendo.getCodigoOrganismoMatriculaVehiculo())));
            }
            if (procesaComparendo.getIdTipoServicio() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_SERVICIO, null,
                        String.valueOf(procesaComparendo.getIdTipoServicio())));
            }
            if (procesaComparendo.getIdClaseVehiculo() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.CLASE_VEHICULO, null,
                        String.valueOf(procesaComparendo.getIdClaseVehiculo())));
            }
            if (procesaComparendo.getIdRadioAccion() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.RADIO_ACCION, null,
                        String.valueOf(procesaComparendo.getIdRadioAccion())));
            }
            if (procesaComparendo.getIdModalidad() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.MODALIDAD, null,
                        String.valueOf(procesaComparendo.getIdModalidad())));
            }
            if (procesaComparendo.getCodigoTipoTransPasajero() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_TRANSPORTE_PASAJEROS, null,
                        String.valueOf(procesaComparendo.getCodigoTipoTransPasajero())));
            }
            if (StringUtils.isNotBlank(procesaComparendo.getNumeroTarjetaOperacion())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.NUMERO_TARJETA_OPERACION, null,
                        procesaComparendo.getNumeroTarjetaOperacion()));
            }
        }

        ComparendoPersonaDTO empresa = datosAntiguos.getEmpresa();
        ProcesaComparendoPersonaDTO empresaVehiculo = rectificaComparendoFL.getEmpresaVehiculo();
        if (empresa != null) {
            if (empresa.getTipoIdentificacion() != null && empresa.getTipoIdentificacion().getId() != null) {
                if (!empresa.getTipoIdentificacion().getId().equals(empresaVehiculo.getIdTipoIdentificacion())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_DOCUMENTO_EMPRESA,
                            String.valueOf(empresa.getTipoIdentificacion().getId()),
                            String.valueOf(empresaVehiculo.getIdTipoIdentificacion())));
                }
            } else if (empresaVehiculo.getIdTipoIdentificacion() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_DOCUMENTO_EMPRESA, null,
                        String.valueOf(empresaVehiculo.getIdTipoIdentificacion())));
            }

            identificarCambioCampoString(empresa.getNumeroIdentificacion(), empresaVehiculo.getNumeroIdentificacion(),
                    lstCambios, EnumCampoEntidad.NUMERO_DOCUMENTO_EMPRESA);

            identificarCambioCampoString(empresa.getRazonSocial(), empresaVehiculo.getRazonSocial(), lstCambios,
                    EnumCampoEntidad.RAZON_SOCIAL_EMPRESA);
        } else {
            if (empresaVehiculo.getIdTipoIdentificacion() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_DOCUMENTO_EMPRESA, null,
                        String.valueOf(empresaVehiculo.getIdTipoIdentificacion())));
            }
            if (StringUtils.isNotBlank(empresaVehiculo.getNumeroIdentificacion())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.NUMERO_DOCUMENTO_EMPRESA, null,
                        empresaVehiculo.getNumeroIdentificacion()));
            }
            if (StringUtils.isNotBlank(empresaVehiculo.getRazonSocial())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.RAZON_SOCIAL_EMPRESA, null,
                        empresaVehiculo.getRazonSocial()));
            }
        }

        // ********************************************************************************
        // ******************************* INFRACTOR **************************************
        // ********************************************************************************
        TipoInfractorDTO tipoInfractor = datosAntiguos.getTipoInfractor();
        if (tipoInfractor != null && tipoInfractor.getId() != null) {
            if (!tipoInfractor.getId().equals(procesaComparendo.getCodigoTipoInfractor())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_INFRACTOR, String.valueOf(tipoInfractor.getId()),
                        String.valueOf(procesaComparendo.getCodigoTipoInfractor())));
            }
        } else if (procesaComparendo.getCodigoTipoInfractor() != null) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_INFRACTOR, null,
                    String.valueOf(procesaComparendo.getCodigoTipoInfractor())));
        }

        ComparendoPersonaDTO infractorAntiguo = datosAntiguos.getInfractor();
        ProcesaComparendoPersonaDTO procesaInfractor = rectificaComparendoFL.getInfractor();
        identificarCambiosComparendoPersona(infractorAntiguo, procesaInfractor, lstCambios, formatoFecha);

        // Datos direccion infractor
        DireccionDTO dirInfractor = null;
        if (infractorAntiguo != null) {
            dirInfractor = infractorAntiguo.getDireccion();
        }
        DireccionDTO direccionInfractor = rectificaComparendoFL.getDireccionInfractor();

        identificarCambioCampoDireccion(lstCambios, dirInfractor, direccionInfractor,
                EnumCampoEntidad.DIRECCION_INFRACTOR);

        // ********************************************************************************
        // ****************************** PROPIETARIO *************************************
        // ********************************************************************************
        ComparendoPersonaDTO propietarioAntiguo = datosAntiguos.getPropietario();
        ProcesaComparendoPersonaDTO procesaPropietarioDTO = rectificaComparendoFL.getPropietario();
        identificarCambiosComparendoPersona(propietarioAntiguo, procesaPropietarioDTO, lstCambios, formatoFecha);

        // ORGANISMO LICENCIA TRANSITO
        if (comparendoVehiculo != null && comparendoVehiculo.getOrganismoLicenciaTransito() != null
                && comparendoVehiculo.getOrganismoLicenciaTransito().getCodigoOrganismo() != null) {
            if (!comparendoVehiculo.getOrganismoLicenciaTransito().getCodigoOrganismo()
                    .equals(procesaComparendo.getCodigoOrganismoLicenciaTransito())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.ORGANISMO_LICENCIA_TRANSITO,
                        String.valueOf(comparendoVehiculo.getOrganismoLicenciaTransito().getCodigoOrganismo()),
                        String.valueOf(procesaComparendo.getCodigoOrganismoLicenciaTransito())));
            }
        } else if (procesaComparendo.getCodigoOrganismoLicenciaTransito() != null) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.ORGANISMO_LICENCIA_TRANSITO, null,
                    String.valueOf(procesaComparendo.getCodigoOrganismoLicenciaTransito())));
        }
        // NUMERO DE LICENCIA DE TRANSITO
        if (comparendoVehiculo != null && comparendoVehiculo.getNumeroLicenciaTransito() != null) {
            if (!comparendoVehiculo.getNumeroLicenciaTransito().equals(procesaComparendo.getNumeroLicenciaTransito())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.NUMERO_LICENCIA_TRANSITO,
                        comparendoVehiculo.getNumeroLicenciaTransito(), procesaComparendo.getNumeroLicenciaTransito()));
            }
        } else if (procesaComparendo.getNumeroLicenciaTransito() != null) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.NUMERO_LICENCIA_TRANSITO, null,
                    procesaComparendo.getNumeroLicenciaTransito()));
        }

        // ********************************************************************************
        // ****************************** AGENTE DE TRANSITO ******************************
        // ********************************************************************************
        ComparendoAgenteDTO compAgenteAntiguo = datosAntiguos.getComparendoAgente();

        if (compAgenteAntiguo != null) {
            if (compAgenteAntiguo.getAgente() != null && compAgenteAntiguo.getAgente().getId() != null) {
                if (!compAgenteAntiguo.getAgente().getId().equals(procesaComparendo.getIdAgenteTransito())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.AGENTE_TRANSITO,
                            String.valueOf(compAgenteAntiguo.getAgente().getId()),
                            String.valueOf(procesaComparendo.getIdAgenteTransito())));
                }
            } else if (procesaComparendo.getIdAgenteTransito() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.AGENTE_TRANSITO, null,
                        String.valueOf(procesaComparendo.getIdAgenteTransito())));
            }

            // PLACA AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getPlaca(), procesaComparendo.getPlacaAgente(), lstCambios,
                    EnumCampoEntidad.PLACA_AGENTE);
            // TIPO IDENTIFICACION AGENTE
            if (compAgenteAntiguo.getTipoIdentificacionPersona() != null
                    && compAgenteAntiguo.getTipoIdentificacionPersona().getId() != null) {
                if (!compAgenteAntiguo.getTipoIdentificacionPersona().getId()
                        .equals(procesaComparendo.getIdTipoIdentificacionAgente())) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_IDENTIFICACION_AGENTE,
                            String.valueOf(compAgenteAntiguo.getTipoIdentificacionPersona().getId()),
                            String.valueOf(procesaComparendo.getIdTipoIdentificacionAgente())));
                }
            } else if (procesaComparendo.getIdTipoIdentificacionAgente() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_IDENTIFICACION_AGENTE, null,
                        String.valueOf(procesaComparendo.getIdTipoIdentificacionAgente())));
            }
            // NUMERO IDENTIFICACION AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getNumeroIdentificacion(),
                    procesaComparendo.getNumeroIdentificacionAgente(), lstCambios,
                    EnumCampoEntidad.NUMERO_IDENTIFICACION_AGENTE);
            // NOMBRE 1 AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getNombre1(), procesaComparendo.getNombre1Agente(),
                    lstCambios, EnumCampoEntidad.PRIMER_NOMBRE_AGENTE);
            // NOMBRE 2 AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getNombre2(), procesaComparendo.getNombre2Agente(),
                    lstCambios, EnumCampoEntidad.SEGUNDO_NOMBRE_AGENTE);
            // APELLIDO 1 AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getApellido1(), procesaComparendo.getApellido1Agente(),
                    lstCambios, EnumCampoEntidad.PRIMER_APELLIDO_AGENTE);
            // APELLIDO 2 AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getApellido2(), procesaComparendo.getApellido2Agente(),
                    lstCambios, EnumCampoEntidad.SEGUNDO_APELLIDO_AGENTE);
            // NOMBRE RESPONSABLE
            // TODO
            // procesaComparendo.getEsPolca()
            if (true) {
                identificarCambioCampoString(compAgenteAntiguo.getNombreResponsable(),
                        procesaComparendo.getNombreResponsable(), lstCambios, EnumCampoEntidad.NOMBRE_RESPONSABLE);
            } else {
                if (compAgenteAntiguo.getUnificacionResponsable() != null
                        && compAgenteAntiguo.getUnificacionResponsable().getId() != null) {
                    if (!compAgenteAntiguo.getUnificacionResponsable().getId()
                            .equals(procesaComparendo.getIdUnificacionResponsable())) {
                        lstCambios.add(crearCambioCampo(EnumCampoEntidad.NOMBRE_RESPONSABLE,
                                String.valueOf(compAgenteAntiguo.getUnificacionResponsable().getId()),
                                String.valueOf(procesaComparendo.getIdUnificacionResponsable())));
                    }

                } else if (procesaComparendo.getIdUnificacionResponsable() != null) {
                    lstCambios.add(crearCambioCampo(EnumCampoEntidad.NOMBRE_RESPONSABLE, null,
                            String.valueOf(procesaComparendo.getIdUnificacionResponsable())));
                }
            }

            // OBSERVACIONES AGENTE
            identificarCambioCampoString(compAgenteAntiguo.getObservacionesAgente(),
                    procesaComparendo.getObservacionesAgente(), lstCambios, EnumCampoEntidad.OBSERVACIONES_AGENTE);
        } else {
            // PLACA AGENTE
            identificarCambioCampoString(null, procesaComparendo.getPlacaAgente(), lstCambios,
                    EnumCampoEntidad.PLACA_AGENTE);
            // TIPO IDENTIFICACION AGENTE
            if (procesaComparendo.getIdTipoIdentificacionAgente() != null) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.TIPO_IDENTIFICACION_AGENTE, null,
                        String.valueOf(procesaComparendo.getIdTipoIdentificacionAgente())));
            }
            // NUMERO IDENTIFICACION AGENTE
            identificarCambioCampoString(null, procesaComparendo.getNumeroIdentificacionAgente(), lstCambios,
                    EnumCampoEntidad.NUMERO_IDENTIFICACION_AGENTE);
            // NOMBRE 1 AGENTE
            identificarCambioCampoString(null, procesaComparendo.getNombre1Agente(), lstCambios,
                    EnumCampoEntidad.PRIMER_NOMBRE_AGENTE);
            // NOMBRE 2 AGENTE
            identificarCambioCampoString(null, procesaComparendo.getNombre2Agente(), lstCambios,
                    EnumCampoEntidad.SEGUNDO_NOMBRE_AGENTE);
            // APELLIDO 1 AGENTE
            identificarCambioCampoString(null, procesaComparendo.getApellido1Agente(), lstCambios,
                    EnumCampoEntidad.PRIMER_APELLIDO_AGENTE);
            // APELLIDO 2 AGENTE
            identificarCambioCampoString(null, procesaComparendo.getApellido2Agente(), lstCambios,
                    EnumCampoEntidad.SEGUNDO_APELLIDO_AGENTE);
            // NOMBRE RESPONSABLE
            identificarCambioCampoString(null, procesaComparendo.getNombreResponsable(), lstCambios,
                    EnumCampoEntidad.NOMBRE_RESPONSABLE);
            // OBSERVACIONES AGENTE
            identificarCambioCampoString(null, procesaComparendo.getObservacionesAgente(), lstCambios,
                    EnumCampoEntidad.OBSERVACIONES_AGENTE);
        }

        // ********************************************************************************
        // ********************************* INMOVILIZACION *******************************
        // ********************************************************************************
        ComparendoPatioDTO compPatioAntiguo = datosAntiguos.getComparendoPatio();
        if (compPatioAntiguo != null) {

            // NUMERO DEL PATIO
            identificarCambioCampoInteger(compPatioAntiguo.getNumeroPatio(), procesaComparendo.getNumeroPatio(),
                    lstCambios, EnumCampoEntidad.NUMERO_PATIO);
            // NOMBRE PATIO
            identificarCambioCampoString(compPatioAntiguo.getNombre(), procesaComparendo.getNombrePatio(), lstCambios,
                    EnumCampoEntidad.NOMBRE_PATIO);
            // NUMERO GRUA
            identificarCambioCampoInteger(compPatioAntiguo.getNumeroGrua(), procesaComparendo.getNumeroGrua(),
                    lstCambios, EnumCampoEntidad.NUMERO_GRUA);
            // PLACA GRUA
            identificarCambioCampoString(compPatioAntiguo.getPlacaGrua(), procesaComparendo.getPlacaGrua(), lstCambios,
                    EnumCampoEntidad.PLACA_GRUA);
            // CONSECUTIVO INMOVILIZACION
            identificarCambioCampoString(compPatioAntiguo.getConsecutivoAsignadoGrua(),
                    procesaComparendo.getConsecutivoInmovilizacion(), lstCambios,
                    EnumCampoEntidad.CONSECUTIVO_INMOVILIZACION);

            DireccionDTO dirPatioInmovili = compPatioAntiguo.getDireccion();
            DireccionDTO direccionInmovilizacion = rectificaComparendoFL.getDireccionInmovilizacion();
            identificarCambioCampoDireccion(lstCambios, dirPatioInmovili, direccionInmovilizacion,
                    EnumCampoEntidad.DIRECCION_PATIO);
        } else {
            // NUMERO DEL PATIO
            identificarCambioCampoInteger(null, procesaComparendo.getNumeroPatio(), lstCambios,
                    EnumCampoEntidad.NUMERO_PATIO);
            // NOMBRE PATIO
            identificarCambioCampoString(null, procesaComparendo.getNombrePatio(), lstCambios,
                    EnumCampoEntidad.NOMBRE_PATIO);
            // NUMERO GRUA
            identificarCambioCampoInteger(null, procesaComparendo.getNumeroGrua(), lstCambios,
                    EnumCampoEntidad.NUMERO_GRUA);
            // PLACA GRUA
            identificarCambioCampoString(null, procesaComparendo.getPlacaGrua(), lstCambios,
                    EnumCampoEntidad.PLACA_GRUA);
            // CONSECUTIVO INMOVILIZACION
            identificarCambioCampoString(null, procesaComparendo.getConsecutivoInmovilizacion(), lstCambios,
                    EnumCampoEntidad.CONSECUTIVO_INMOVILIZACION);

            DireccionDTO direccionInmovilizacion = rectificaComparendoFL.getDireccionInmovilizacion();
            identificarCambioCampoDireccion(lstCambios, null, direccionInmovilizacion, EnumCampoEntidad.DIRECCION_PATIO);
        }

        // ********************************************************************************
        // ********************************* TESTIGO **************************************
        // ********************************************************************************
        ComparendoPersonaDTO testigo = datosAntiguos.getTestigo();
        ProcesaComparendoPersonaDTO procesaTestigo = rectificaComparendoFL.getTestigo();
        identificarCambiosComparendoPersona(testigo, procesaTestigo, lstCambios, formatoFecha);
        DireccionDTO direccionTestigo = rectificaComparendoFL.getDireccionTestigo();
        DireccionDTO dirTestigo = null;
        if (testigo != null) {
            dirTestigo = testigo.getDireccion();
        }
        identificarCambioCampoDireccion(lstCambios, dirTestigo, direccionTestigo, EnumCampoEntidad.DIRECCION_TESTIGO);

        // ********************************************************************************
        // ********************************* EMBRIAGUEZ ***********************************
        // ********************************************************************************
        // NIEGA PRUEBA DE ALCOHOLEMIA
        if (datosAntiguos.getNiegaPruebaAlcoholemia() != null) {
            if (!datosAntiguos.getNiegaPruebaAlcoholemia().equals(procesaComparendo.getNiegaPruebaAlcoholemia())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.NIEGA_PRUEBA_ALCOHOLEMIA,
                        String.valueOf(datosAntiguos.getNiegaPruebaAlcoholemia()),
                        String.valueOf(procesaComparendo.getNiegaPruebaAlcoholemia())));
            }
        } else if (procesaComparendo.getNiegaPruebaAlcoholemia() != null) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.NIEGA_PRUEBA_ALCOHOLEMIA, null,
                    String.valueOf(procesaComparendo.getNiegaPruebaAlcoholemia())));
        }

        // GRADO ALCOHOLEMIA
        if (datosAntiguos.getGradoAlcoholemia() != null && datosAntiguos.getGradoAlcoholemia().getValor() != null) {
            if (!datosAntiguos.getGradoAlcoholemia().getValor().equals(procesaComparendo.getGradoAlcoholemia())) {
                lstCambios.add(crearCambioCampo(EnumCampoEntidad.GRADO_ALCOHOLEMIA,
                        String.valueOf(datosAntiguos.getGradoAlcoholemia().getValor()),
                        String.valueOf(procesaComparendo.getGradoAlcoholemia())));
            }
        } else if (procesaComparendo.getGradoAlcoholemia() != null) {
            lstCambios.add(crearCambioCampo(EnumCampoEntidad.GRADO_ALCOHOLEMIA, null,
                    String.valueOf(procesaComparendo.getGradoAlcoholemia())));
        }
        // FECHA PRUEBA ALCOHOLEMIA
        identificarCambioCampoFecha(datosAntiguos.getFechaPruebaAlcoholemia(),
                procesaComparendo.getFechaPruebaAlcoholemia(), lstCambios, EnumCampoEntidad.FECHA_PRUEBA_ALCOHOLEMIA,
                formatoFecha);

        // NUMERO PRUEBA ALCOHOLEMIA
        identificarCambioCampoString(datosAntiguos.getNumeroPruebaAlcoholemia(),
                procesaComparendo.getNumeroPruebaAlcoholemia(), lstCambios, EnumCampoEntidad.NUMERO_PRUEBA_ALCOHOLEMIA);
        // VALOR GRADO ALCOHOLEMIA
        identificarCambioCampoInteger(datosAntiguos.getValorGradoAlcoholemia(),
                procesaComparendo.getValorGradoAlcoholemia(), lstCambios, EnumCampoEntidad.VALOR_GRADO_ALCOHOLEMIA);
        // NUMERO DE REINCIDENCIA
        identificarCambioCampoInteger(datosAntiguos.getNumeroReincidencia(), procesaComparendo.getNumeroReincidencia(),
                lstCambios, EnumCampoEntidad.NUMERO_REINCIDENCIAS);

        // ********************************************************************************
        // ********************************* EVIDENCIAS ***********************************
        // ********************************************************************************
        List<ProcesaEvidenciaDTO> procesaEvidencias = rectificaComparendoFL.getProcesaEvidencias();
        if (procesaEvidencias != null) {
            List<RectificaEvidenciaDTO> lstEvidenciasRectificadas = new ArrayList<RectificaEvidenciaDTO>(
                    procesaEvidencias.size());
            for (ProcesaEvidenciaDTO procesaEvidenciaDTO : procesaEvidencias) {
                RectificaEvidenciaDTO rectificaEvidenciaDTO = new RectificaEvidenciaDTO();
                rectificaEvidenciaDTO.setCodigoTipoEvidencia(procesaEvidenciaDTO.getCodigoTipoEvidencia());
                lstEvidenciasRectificadas.add(rectificaEvidenciaDTO);
            }
            procesaComparendo.setListRectificaEvidencias(lstEvidenciasRectificadas);
        }

        procesaComparendo.setCampoRectificaComparendoDTOs(lstCambios);

        return procesaComparendo;
    }

}
