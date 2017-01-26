package co.com.datatools.c2.managed_bean.comparendo.rectificacion.ecuador;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.CampoEntidadDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
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
public class RectificarComparendoEcuadorUtil {

    private RectificarComparendoEcuadorUtil() {
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
        procesaInfractor.setCodigoOrganismoLicencia(comparendoPersona.getOrganismoTransito() != null
                ? comparendoPersona.getOrganismoTransito().getCodigoOrganismo() : null);
        procesaInfractor.setNumeroLicencia(comparendoPersona.getNumeroLicencia());
        procesaInfractor.setIdCategoriaLicenciaCondu(comparendoPersona.getCategoriaLicencia() != null
                ? comparendoPersona.getCategoriaLicencia().getId() : null);
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
            ProcesaComparendoPersonaDTO personaNueva, List<CampoRectificaComparendoDTO> lstCambios,
            String formatoFecha) {
        EnumTipoPersonaComparendo tipoPersonaComparendo = null;
        if (personaAntigua != null && personaNueva != null) {
            tipoPersonaComparendo = Utilidades.buscarElemEnum(EnumTipoPersonaComparendo.class,
                    personaAntigua.getTipoPersonaComparendo().getCodigo());
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
                lstCambios.add(
                        crearCambioCampo(campoEntidad, null, String.valueOf(personaNueva.getIdTipoIdentificacion())));
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
            identificarCambioCampoString(personaAntigua.getTelefonoMovil(), personaNueva.getTelefonoMovil(), lstCambios,
                    campoEntidad);
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
            if (personaAntigua.getCategoriaLicencia() != null
                    && personaAntigua.getCategoriaLicencia().getId() != null) {
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
                lstCambios.add(
                        crearCambioCampo(campoEntidad, null, String.valueOf(personaNueva.getIdTipoIdentificacion())));
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
            RectificaComparendoEcuadorFL rectificaComparendoFL, String formatoFecha) {
        ComparendoDTO datosAntiguos = procesaComparendo.getComparendo();
        List<CampoRectificaComparendoDTO> lstCambios = new ArrayList<CampoRectificaComparendoDTO>();

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

        procesaComparendo.setCampoRectificaComparendoDTOs(lstCambios);

        return procesaComparendo;
    }

}
