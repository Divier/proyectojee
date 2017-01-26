package co.com.datatools.c2.negocio.helpers.extencion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import co.com.datatools.c2.dto.RutaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaCantidadComparendosNoNotificadosDTO;
import co.com.datatools.c2.dto.comparendo.ContenidoArchivoNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.EstadoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoAgenteImpositorDTO;
import co.com.datatools.c2.dto.comparendo.TipoComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.TipoOrigenComparendoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoAgente;
import co.com.datatools.c2.entidades.ComparendoCartera;
import co.com.datatools.c2.entidades.ComparendoPatio;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.entidades.ComparendoProceso;
import co.com.datatools.c2.entidades.ComparendoVehiculo;
import co.com.datatools.c2.entidades.EstadoComparendo;
import co.com.datatools.c2.entidades.Evidencia;
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
import co.com.datatools.c2.entidades.TipoPersonaComparendo;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumCampoArchivoAcuse;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoAgenteHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoCarteraHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoPatioHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoPersonaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoProcesoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.ComparendoVehiculoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.EstadoComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.EstadoComparendoSimitHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.EvidenciaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.GradoAlcoholemiaHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.InfraccionHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.MedioImposicionComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.OrdenComparendoNacionalHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoAgenteImpositorHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoInfractorHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoNotificacionComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TipoOrigenComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comparendos.TrazabilidadComparendoHelper;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.RutaHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoPersonaComparendoHelper;
import co.com.datatools.c2.negocio.opciones.ConstantesGestionDocumentos;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.archivo.ArchivoFactory;
import co.com.datatools.util.file.archivo.ContenidoArchivoDTO;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;
import co.com.datatools.util.file.archivo.IArchivo;

public class ComparendoHelperExtend extends ComparendoHelper {

    /**
     * Extiende la implementacion de {@link ComparendoHelper} agregando mas niveles de procesamiento a los dto.<br>
     * Definicion: este metodo se encarga de convertir toda la informacion de una entidad comparendo a un dto, la relaciones q no se encuentren seran
     * asignadas como null (de acuerdo a la definicion general de los Helper, no cambiar este comportamiento )
     * 
     * @param entidad
     *            entidad comparendo a convertir a dto
     * @return dto con la informacion del entidad comparendo, con valores null en las relaciones q no existan en la entidad
     * @author felipe.martinez
     */
    public static ComparendoDTO toLevel1DTO(Comparendo entidad) {
        final ComparendoDTO dto = toLevel0DTO(entidad);

        final OrdenComparendoNacional ordenComparendoNacional = entidad.getOrdenComparendoNacional();
        final TipoOrigenComparendo tipoOrigenComparendo = entidad.getTipoOrigen();
        final UsuarioPersona usuarioPersona = entidad.getUsuarioPersona();
        final MedioImposicionComparendo medioImposicion = entidad.getMedioImposicion();
        final TipoInfractor tipoInfractor = entidad.getTipoInfractor();
        final TipoNotificacionComparendo tipoNotificacion = entidad.getTipoNotificacion();
        final Direccion direccion = entidad.getDireccion();
        final Infraccion infraccion = entidad.getInfraccion();
        final Ruta ruta = entidad.getRuta();
        final TipoComparendo tipoComparendo = entidad.getTipoComparendo();
        final GradoAlcoholemia gradoAlcoholemia = entidad.getGradoAlcoholemia();
        final EstadoComparendo estadoComparendo = entidad.getEstadoComparendo();

        final ComparendoAgente comparendoAgente = entidad.getComparendoAgente();
        final ComparendoPatio comperendoPatio = entidad.getComparendoPatio();
        final ComparendoVehiculo comparendoVehiculo = entidad.getComparendoVehiculo();

        final List<ComparendoPersona> comparendoPersonas = entidad.getPersonaList();
        final List<Evidencia> evidencias = entidad.getEvidenciaList();
        final List<ComparendoCartera> lComparendoCartera = entidad.getComparendoCarteraList();
        final List<ComparendoProceso> lComparendoProceso = entidad.getComparendoProcesos();
        final TipoAgenteImpositor tipoAgenteImpositor = entidad.getTipoAgenteImpositor();

        if (ordenComparendoNacional != null) {
            final OrdenComparendoNacionalDTO ocnDto = OrdenComparendoNacionalHelper
                    .toLevel1DTO(ordenComparendoNacional);
            ocnDto.setOrganismoTransito(
                    OrganismoTransitoHelper.toLevel1DTO(ordenComparendoNacional.getOrganismoTransito()));
            dto.setOrdenComparendoNacional(ocnDto);
        }
        if (tipoOrigenComparendo != null) {
            dto.setTipoOrigen(TipoOrigenComparendoHelper.toLevel0DTO(tipoOrigenComparendo));
        }
        if (usuarioPersona != null) {
            dto.setUsuarioPersona(UsuarioPersonaHelper.toLevel1DTO(usuarioPersona));
        }
        if (medioImposicion != null) {
            dto.setMedioImposicion(MedioImposicionComparendoHelper.toLevel0DTO(medioImposicion));
        }
        if (tipoInfractor != null) {
            dto.setTipoInfractor(TipoInfractorHelper.toLevel0DTO(tipoInfractor));
        }
        if (tipoNotificacion != null) {
            dto.setTipoNotificacion(TipoNotificacionComparendoHelper.toLevel0DTO(tipoNotificacion));
        }

        if (direccion != null) {
            dto.setDireccion(DireccionHelperExtend.toLevel1DTO(direccion));
        }
        if (infraccion != null) {
            dto.setInfraccion(InfraccionHelper.toLevel1DTO(infraccion));
        }
        if (ruta != null) {
            dto.setRuta(RutaHelper.toLevel0DTO(ruta));
        }
        if (tipoComparendo != null) {
            dto.setTipoComparendo(TipoComparendoHelper.toLevel1DTO(tipoComparendo));
        }
        if (gradoAlcoholemia != null) {
            dto.setGradoAlcoholemia(GradoAlcoholemiaHelper.toLevel1DTO(gradoAlcoholemia));
        }
        if (estadoComparendo != null) {
            dto.setEstadoComparendo(EstadoComparendoHelper.toLevel1DTO(estadoComparendo));
        }

        if (comparendoAgente != null) {
            ComparendoAgenteDTO comparendoAgenteDTO = ComparendoAgenteHelper.toLevel1DTO(comparendoAgente);
            dto.setComparendoAgente(comparendoAgenteDTO);
        }

        if (comperendoPatio != null) {
            dto.setComparendoPatio(ComparendoPatioHelper.toLevel1DTO(comperendoPatio));
        }

        if (comparendoVehiculo != null) {
            dto.setComparendoVehiculo(ComparendoVehiculoHelperExtend.toLevel1DTOMunicipio(comparendoVehiculo));
        }

        if (entidad.getEstadoComparendoSimit() != null) {
            dto.setEstadoComparendoSimit(EstadoComparendoSimitHelper.toLevel0DTO(entidad.getEstadoComparendoSimit()));
        }
        if (comparendoPersonas != null) {
            List<ComparendoPersonaDTO> listComparendoPersonaDTO = new ArrayList<ComparendoPersonaDTO>();
            for (ComparendoPersona compPer : comparendoPersonas) {
                TipoPersonaComparendo tipPerComp = compPer.getTipoPersonaComparendo();
                ComparendoPersonaDTO compPerDTO = ComparendoPersonaHelperExtend.toLevel1DTO(compPer);

                if (compPer.getDireccion() != null) {
                    compPerDTO.setDireccion(DireccionHelperExtend.toLevel1DTO(compPer.getDireccion()));
                }

                compPerDTO.setTipoPersonaComparendo(TipoPersonaComparendoHelper.toLevel0DTO(tipPerComp));
                listComparendoPersonaDTO.add(compPerDTO);
                if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                    dto.setInfractor(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {
                    dto.setPropietario(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.TESTIGO.getCodigo())) {
                    dto.setTestigo(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo())) {
                    dto.setEmpresa(compPerDTO);
                }

            }
            dto.setPersonaList(listComparendoPersonaDTO);
        } else {
            dto.setPersonaList(new ArrayList<ComparendoPersonaDTO>(0));
        }
        if (evidencias != null) {
            dto.setEvidenciaList(EvidenciaHelperExtend.toListLevel0DTO(evidencias));
        }
        if (lComparendoCartera != null) {
            dto.setComparendoCarteraList(ComparendoCarteraHelper.toListLevel0DTO(lComparendoCartera));
        }
        if (lComparendoProceso != null) {
            dto.setComparendoProcesoList(ComparendoProcesoHelper.toListLevel0DTO(lComparendoProceso));
        }
        if (tipoAgenteImpositor != null) {
            dto.setTipoAgenteImpositorDTO(TipoAgenteImpositorHelper.toLevel0DTO(tipoAgenteImpositor));
        }
        return dto;
    }

    public static ComparendoPersona encontrarPersona(Comparendo comparendo, EnumTipoPersonaComparendo tipoBuscar) {
        List<ComparendoPersona> comparendoPersonas = comparendo.getPersonaList();
        ComparendoPersona temp = null;
        for (Iterator<ComparendoPersona> it = comparendoPersonas.iterator(); it.hasNext();) {
            ComparendoPersona next = it.next();
            Integer codTipoPersona = next.getTipoPersonaComparendo().getCodigo();
            if (codTipoPersona.equals(tipoBuscar.getCodigo())) {
                if (next.getFechaFin() == null) {
                    return next;
                }
            }
        }
        return temp;
    }

    /**
     * Este metodo setea los campos basicos y las relaciones con objetos catalogo o con entidades q no pueden existir aun
     * 
     * @param dto
     *            dto origen de datos
     * @param entidad
     *            entidad base sobre la q se realiza los cambios
     * @return comparendo
     * @author felipe.martinez
     */
    public static Comparendo toComparendo(ComparendoDTO dto, Comparendo entidad) {

        entidad = ComparendoHelper.toLevel0Entity(dto, entidad);

        if (dto.getOrdenComparendoNacional() != null) {
            entidad.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel1Entity(dto.getOrdenComparendoNacional(), null));
        }
        if (dto.getTipoOrigen() != null) {
            entidad.setTipoOrigen(new TipoOrigenComparendo());
            entidad.getTipoOrigen().setId(dto.getTipoOrigen().getId());
        }
        if (dto.getUsuarioPersona().getUsuario() != null) {
            entidad.setUsuarioPersona(new UsuarioPersona());
            entidad.getUsuarioPersona().setIdUsuario(dto.getUsuarioPersona().getUsuario().getId());
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

        if (dto.getDireccion() != null) {
            entidad.setDireccion(DireccionHelperExtend.toLevel1Entity(dto.getDireccion(), null));
        }
        if (dto.getComparendoAgente() != null) {
            entidad.setComparendoAgente(ComparendoAgenteHelper.toLevel1Entity(dto.getComparendoAgente(), null));
        }
        if (dto.getComparendoVehiculo() != null) {
            entidad.setComparendoVehiculo(ComparendoVehiculoHelper.toLevel1Entity(dto.getComparendoVehiculo(), null));
        }
        // Tipo Agente impositor
        if (dto.getTipoAgenteImpositorDTO() != null) {
            entidad.setTipoAgenteImpositor(new TipoAgenteImpositor());
            entidad.getTipoAgenteImpositor().setId(dto.getTipoAgenteImpositorDTO().getId());
        }
        if (dto.getComparendoPatio() != null) {
            entidad.setComparendoPatio(ComparendoPatioHelper.toLevel1Entity(dto.getComparendoPatio(), null));
        }
        if (dto.getEvidenciaList() != null && dto.getEvidenciaList() != null) {
            entidad.setEvidenciaList(EvidenciaHelper.toListLevel1Entity(dto.getEvidenciaList()));
        }
        if (dto.getPersonaList() != null && dto.getPersonaList() != null) {
            entidad.setPersonaList(ComparendoPersonaHelper.toListLevel1Entity(dto.getPersonaList()));
        }
        if (dto.getTrazabilidadComparendoList() != null && dto.getTrazabilidadComparendoList() != null) {
            entidad.setTrazabilidadComparendoList(
                    TrazabilidadComparendoHelper.toListLevel1Entity(dto.getTrazabilidadComparendoList()));
        }
        return entidad;
    }

    /**
     * Este metodo setea los campos basicos de una entidad a un objeto DTO comparendo
     * 
     * @param entidad
     *            entidad base sobre la q se realiza los cambios
     * @return comparendo
     * @author giovanni.velandia
     */
    public static ComparendoDTO toComparendo(Comparendo entidad) {

        ComparendoDTO comparendoDTO = ComparendoHelper.toLevel0DTO(entidad);

        // Orden comparendo Nacional
        if (entidad.getOrdenComparendoNacional() != null) {
            comparendoDTO.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel1DTO(entidad.getOrdenComparendoNacional()));
        }
        // TipoOrigen
        if (entidad.getTipoOrigen() != null) {
            TipoOrigenComparendoDTO tipoOrigenComparendoDTO = new TipoOrigenComparendoDTO();
            tipoOrigenComparendoDTO.setId(entidad.getTipoOrigen().getId());
            comparendoDTO.setTipoOrigen(tipoOrigenComparendoDTO);
        }

        // Usuario Persona // Persona
        if (entidad.getUsuarioPersona() != null) {
            comparendoDTO.setUsuarioPersona(UsuarioPersonaHelper.toLevel1DTO(entidad.getUsuarioPersona()));
        }

        // Medio Imposicion
        if (entidad.getMedioImposicion() != null) {
            MedioImposicionComparendoDTO medioImposicionComparendoDTO = new MedioImposicionComparendoDTO();
            medioImposicionComparendoDTO.setId(entidad.getMedioImposicion().getId());
            comparendoDTO.setMedioImposicion(medioImposicionComparendoDTO);
        }

        // Tipo Infractor
        if (entidad.getTipoInfractor() != null) {
            TipoInfractorDTO tipoInfractorDTO = new TipoInfractorDTO();
            tipoInfractorDTO.setId(entidad.getTipoInfractor().getId());
            comparendoDTO.setTipoInfractor(tipoInfractorDTO);
        }
        // Tipo Notificacion
        if (entidad.getTipoNotificacion() != null) {
            TipoNotificacionComparendoDTO tipoNotificacionComparendoDTO = new TipoNotificacionComparendoDTO();
            tipoNotificacionComparendoDTO.setId(entidad.getTipoNotificacion().getId());
            comparendoDTO.setTipoNotificacion(tipoNotificacionComparendoDTO);
        }
        // Infraccion
        if (entidad.getInfraccion() != null) {
            InfraccionDTO infraccionDTO = new InfraccionDTO();
            infraccionDTO.setId(entidad.getInfraccion().getId());
            comparendoDTO.setInfraccion(infraccionDTO);
        }
        // Ruta
        if (entidad.getRuta() != null) {
            RutaDTO rutaDTO = new RutaDTO();
            rutaDTO.setId(entidad.getRuta().getId());
        }
        // Tipo Comparendo
        if (entidad.getTipoComparendo() != null) {
            TipoComparendoDTO tipoComparendoDTO = new TipoComparendoDTO();
            tipoComparendoDTO.setId(entidad.getTipoComparendo().getId());
            comparendoDTO.setTipoComparendo(tipoComparendoDTO);
        }
        // Grado Alcoholemia
        if (entidad.getGradoAlcoholemia() != null) {
            GradoAlcoholemiaDTO gradoAlcoholemiaDTO = new GradoAlcoholemiaDTO();
            gradoAlcoholemiaDTO.setId(entidad.getGradoAlcoholemia().getId());
            comparendoDTO.setGradoAlcoholemia(gradoAlcoholemiaDTO);
        }
        // Estado Comparendo
        if (entidad.getEstadoComparendo() != null) {
            EstadoComparendoDTO estadoComparendoDTO = new EstadoComparendoDTO();
            estadoComparendoDTO.setId(entidad.getEstadoComparendo().getId());
            comparendoDTO.setEstadoComparendo(estadoComparendoDTO);
        }
        // Direccion
        if (entidad.getDireccion() != null) {
            comparendoDTO.setDireccion(DireccionHelperExtend.toLevel1DTO(entidad.getDireccion()));
        }
        // Comparendo Agente
        if (entidad.getComparendoAgente() != null) {
            comparendoDTO.setComparendoAgente(ComparendoAgenteHelper.toLevel1DTO(entidad.getComparendoAgente()));
        }
        // Comparendo Vehiculo
        if (entidad.getComparendoVehiculo() != null) {
            comparendoDTO.setComparendoVehiculo(ComparendoVehiculoHelper.toLevel1DTO(entidad.getComparendoVehiculo()));
        }
        // Comparendo patio
        if (entidad.getComparendoPatio() != null) {
            comparendoDTO.setComparendoPatio(ComparendoPatioHelper.toLevel1DTO(entidad.getComparendoPatio()));
        }
        // Tipo Agente Impositor
        if (entidad.getTipoAgenteImpositor() != null) {
            TipoAgenteImpositorDTO tipoAgenteImpositorDTO = new TipoAgenteImpositorDTO();
            tipoAgenteImpositorDTO.setId(entidad.getTipoAgenteImpositor().getId());
            comparendoDTO.setTipoAgenteImpositorDTO(tipoAgenteImpositorDTO);
        }
        // Lista de evidencias
        if (entidad.getEvidenciaList() != null && !entidad.getEvidenciaList().isEmpty()) {
            comparendoDTO.setEvidenciaList(EvidenciaHelper.toListLevel1DTO(entidad.getEvidenciaList()));
        }
        // Lista de personas
        if (entidad.getPersonaList() != null && !entidad.getPersonaList().isEmpty()) {
            comparendoDTO.setPersonaList(ComparendoPersonaHelper.toListLevel1DTO(entidad.getPersonaList()));
        }
        // Lista de trazabilidad
        if (entidad.getTrazabilidadComparendoList() != null && !entidad.getTrazabilidadComparendoList().isEmpty()) {
            comparendoDTO.setTrazabilidadComparendoList(
                    TrazabilidadComparendoHelper.toListLevel1DTO(entidad.getTrazabilidadComparendoList()));
        }
        return comparendoDTO;
    }

    public static List<ComparendoDTO> toListLevel1DTO(List<Comparendo> listComparendo) {
        List<ComparendoDTO> listComparendoDTO = new ArrayList<ComparendoDTO>();
        for (Comparendo comparendo : listComparendo) {
            listComparendoDTO.add(toLevel1DTO(comparendo));
        }
        return listComparendoDTO;
    }

    /**
     * Este metodo retorna una lista de comparendos con informacion basica de los mismos, incluidas las personas asociadas
     * 
     * @param listComparendo
     *            lista de entidades de tipo Comparendo
     * @return Lista de comparendos
     * @author divier.casas
     */
    public static List<ComparendoDTO> toListLevel1DTOExtTipoPersona(List<Comparendo> listComparendo) {
        List<ComparendoDTO> listComparendoDTO = new ArrayList<ComparendoDTO>();
        for (Comparendo comparendo : listComparendo) {
            listComparendoDTO.add(toLevel1DTOExtTipoPersona(comparendo));
        }
        return listComparendoDTO;
    }

    /**
     * Este metodo setea los campos con informacion basica del comparendo incluidas las personas asociadas al mismo
     * 
     * @param entidad
     *            entidad de tipo Comparendo
     * @return comparendo
     * @author divier.casas
     */
    public static ComparendoDTO toLevel1DTOExtTipoPersona(Comparendo entidad) {
        final ComparendoDTO dto = ComparendoHelper.toLevel1DTO(entidad);
        final List<ComparendoPersona> comparendoPersonas = entidad.getPersonaList();
        OrganismoTransitoDTO organismoTransito = OrganismoTransitoHelper
                .toLevel0DTO(entidad.getOrdenComparendoNacional().getOrganismoTransito());
        dto.getOrdenComparendoNacional().setOrganismoTransito(organismoTransito);
        if (comparendoPersonas != null) {
            List<ComparendoPersonaDTO> listComparendoPersonaDTO = new ArrayList<ComparendoPersonaDTO>();
            for (ComparendoPersona compPer : comparendoPersonas) {
                TipoPersonaComparendo tipPerComp = compPer.getTipoPersonaComparendo();
                ComparendoPersonaDTO compPerDTO = ComparendoPersonaHelper.toLevel1DTO(compPer);
                compPerDTO.setTipoPersonaComparendo(TipoPersonaComparendoHelper.toLevel0DTO(tipPerComp));
                listComparendoPersonaDTO.add(compPerDTO);

                if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                    dto.setInfractor(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.PROPIETARIO.getCodigo())) {
                    dto.setPropietario(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.TESTIGO.getCodigo())) {
                    dto.setTestigo(compPerDTO);
                } else if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getCodigo())) {
                    dto.setEmpresa(compPerDTO);
                }
            }
            dto.setPersonaList(listComparendoPersonaDTO);
        } else {
            dto.setPersonaList(new ArrayList<ComparendoPersonaDTO>(0));
        }

        return dto;
    }

    /**
     * Este metodo genera el resultado de notificacion a partir de un comparendo
     * 
     * @param comparendo
     *            entidad de tipo Comparendo
     * @return ResultadoConsultaNotificacionComparendoDTO
     * @author julio.pinzon
     */
    public static ResultadoConsultaNotificacionComparendoDTO toLevel1DTONotificacion(Comparendo comparendo) {
        final ResultadoConsultaNotificacionComparendoDTO dto = new ResultadoConsultaNotificacionComparendoDTO();

        dto.setCodigoInfraccion(comparendo.getInfraccion().getCodigo());
        dto.setEstadoComparendo(comparendo.getEstadoComparendo().getNombre());
        dto.setIdComparendo(comparendo.getCicomparendo());
        dto.setNumeroComparendo(comparendo.getOrdenComparendoNacional().getNumeroComparendo());
        if (comparendo.getTipoNotificacion() != null) {
            dto.setTipoNotificacion(comparendo.getTipoNotificacion().getNombre());
        }
        // Completa la fecha y hora de la infraccion en un solo atributo
        Calendar fechaInfraccionCompleta = Calendar.getInstance();
        fechaInfraccionCompleta.setTime(comparendo.getHoraInfraccion());

        Calendar fechaInfraccion = Calendar.getInstance();
        fechaInfraccion.setTime(comparendo.getFechaInfraccion());

        fechaInfraccionCompleta.set(fechaInfraccion.get(Calendar.YEAR), fechaInfraccion.get(Calendar.MONTH),
                fechaInfraccion.get(Calendar.DATE));

        dto.setFechaInfraccion(fechaInfraccionCompleta.getTime());

        final List<ComparendoPersona> comparendoPersonas = comparendo.getPersonaList();
        if (comparendoPersonas != null) {
            List<ComparendoPersonaDTO> listComparendoPersonaDTO = new ArrayList<ComparendoPersonaDTO>();
            for (ComparendoPersona compPer : comparendoPersonas) {
                TipoPersonaComparendo tipPerComp = compPer.getTipoPersonaComparendo();
                ComparendoPersonaDTO compPerDTO = ComparendoPersonaHelper.toLevel1DTO(compPer);
                compPerDTO.setTipoPersonaComparendo(TipoPersonaComparendoHelper.toLevel0DTO(tipPerComp));
                listComparendoPersonaDTO.add(compPerDTO);

                if (compPerDTO.getTipoPersonaComparendo().getCodigo()
                        .equals(EnumTipoPersonaComparendo.INFRACTOR.getCodigo())) {
                    dto.setNumeroDocumentoInfractor(compPerDTO.getNumeroIdentificacion());
                    dto.setTipoDocumentoInfractor(compPerDTO.getTipoIdentificacion().getNombre());
                    dto.setNombreInfractor(compPerDTO.getNombreCorto());
                    break;
                }
            }
        }
        return dto;
    }

    /**
     * Lee un archivo a partir de un arreglo de bytes con el tipo indicado
     * 
     * @param contenido
     *            Archivo en memoria
     * @param tipoArchivo
     *            Tipo de archivo
     * @return Lista de filas abstractas
     */
    public static List<FilaArchivoDTO> leerArchivo(byte[] contenido, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenidoArchivo = iArchivo.leerArchivo(contenido, tipoArchivo);
        List<FilaArchivoDTO> filasArchivo = new ArrayList<>();
        if (contenidoArchivo != null)
            filasArchivo = contenidoArchivo.getFilas();
        return filasArchivo;
    }

    /**
     * Escribe un archivo a partir de una lista de filas abstractas con el tipo indicado
     * 
     * @param archivo
     *            Lista de filas abstractas
     * @param tipoArchivo
     *            Tipo de archivo
     * @return Archivo en memoria
     */
    public static byte[] escribirArchivo(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenido = archivoFactory.obtenerContenido(tipoArchivo, filaArchivoList);
        return iArchivo.escribirArchivo(contenido, tipoArchivo);
    }

    /**
     * Genera Archivo excel para los comparendos a notificar vía correo certificado
     * 
     * @param archivos
     * @param consultarCantidadComparendosNoNotificadosDTO
     * @return
     */
    public static byte[] generaArchivo(List<ContenidoArchivoNotificacionDTO> archivos,
            ConsultaCantidadComparendosNoNotificadosDTO consultarCantidadComparendosNoNotificadosDTO) {
        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();

        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);

        List<Object> encabezado = new ArrayList<>();

        encabezado.add(EnumCampoArchivoAcuse.NUMERO_DE_COMPAREN.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.FECHA_DE_IMPOSICIO.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.TIPO_DOCUMENTO_INF.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.NUMERO_DOCUMENTO_I.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.NOMBRE_1_INFRACTOR.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.NOMBRE_2_INFRACTOR.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.APELLIDO_1_INFRACT.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.APELLIDO_2_INFRACT.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.DIRECCION_INFRACTO.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.NUMERO_CELULAR_INF.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.CODIGO_DE_INFRACCI.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.DESCRIPCION_INFRAC.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.VALOR_DE_COMPAREND.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.PLACA_DEL_VEHICULO.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.FECHA_NOTIFICACION.getNombre());
        encabezado.add(EnumCampoArchivoAcuse.OBSERVACIONES_NOTI.getNombre());
        filaArchivoExcel.setCeldas(encabezado);
        filaArchivoExcel.setEncabezado(true);

        for (ContenidoArchivoNotificacionDTO contenidoArchivo : archivos) {
            FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
            filaArchivoExcel.setHoja(0);

            List<Object> datos = new ArrayList<>();
            datos.add(contenidoArchivo.getNumeroComparendo());

            if (contenidoArchivo.getFechaImposicion() != null) {
                datos.add(UtilFecha.dateToString(contenidoArchivo.getFechaImposicion(), "dd/MM/yyyy-HH:mm:ss"));
            }
            datos.add(contenidoArchivo.getTipoDocumento());
            datos.add(contenidoArchivo.getNumeroDocumento());
            datos.add(contenidoArchivo.getPrimerNombre());
            datos.add(contenidoArchivo.getSegundoNombre());
            datos.add(contenidoArchivo.getPrimerApellido());
            datos.add(contenidoArchivo.getSegundoApellido());
            datos.add(contenidoArchivo.getDireccion());
            datos.add(contenidoArchivo.getNumeroCelular());
            datos.add(contenidoArchivo.getCodigoInfraccion());
            datos.add(contenidoArchivo.getDescripcionInfraccion());
            datos.add(contenidoArchivo.getValorComparendo());
            datos.add(contenidoArchivo.getPlaca());
            datos.add(contenidoArchivo.getFechaNotificacion());
            datos.add(contenidoArchivo.getObservaciones());
            filaArchivoExcelAux.setCeldas(datos);
            filaArchivoList.add(filaArchivoExcelAux);
        }

        return escribirArchivo(filaArchivoList, EnumTipoArchivo.XLS);

    }

    /**
     * Metodo utilizado para resoluciones de sancion, devolver solo los datos necesarios
     * 
     * @param entidad
     * @return DTO de comparendo
     */
    public static ComparendoDTO toLevel1DTOOrden(Comparendo entidad) {
        ComparendoDTO dto = new ComparendoDTO();
        dto.setCicomparendo(entidad.getCicomparendo());
        if (entidad.getOrdenComparendoNacional() != null)
            dto.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel0DTO(entidad.getOrdenComparendoNacional()));

        return dto;
    }

    /**
     * Convierte el comparendo con los datos necesarios a mostrar de trazabilidad.
     * 
     * @param entidad
     *            entidad con los campos de trazabilidad a ser ingresados
     * @return comparendo DTO con los datos necesarios a mostrar en la consulta.
     * @author luis.forero(2016-03-01)
     */
    public static ComparendoDTO toLevel1DTOSeguimientoTrazabilidad(Comparendo entidad) {
        ComparendoDTO dto = ComparendoHelper.toLevel1DTO(entidad);
        if (entidad.getOrdenComparendoNacional() != null) {
            dto.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel1DTO(entidad.getOrdenComparendoNacional()));
        }
        return dto;
    }

    /**
     * Este metodo retorna una lista de comparendos con informacion de la cartera asociada
     * 
     * @param listComparendo
     *            lista de entidades de tipo Comparendo
     * @return Lista de comparendos
     * @author divier.casas
     */
    public static List<ComparendoDTO> toListLevel1DTOExtComparendoCarteraProceso(List<Comparendo> listComparendo) {
        List<ComparendoDTO> listComparendoDTO = new ArrayList<ComparendoDTO>();
        ComparendoDTO comparendoDTO = null;
        for (Comparendo comparendo : listComparendo) {
            comparendoDTO = ComparendoHelper.toLevel1DTO(comparendo);
            comparendoDTO.setComparendoCarteraList(
                    ComparendoCarteraHelper.toListLevel1DTO(comparendo.getComparendoCarteraList()));
            comparendoDTO.setComparendoProcesoList(
                    ComparendoProcesoHelper.toListLevel1DTO(comparendo.getComparendoProcesos()));
            comparendoDTO.setIdFacturaAxis(comparendo.getIdFacturaAxis());
            comparendoDTO.setOrdenComparendoNacional(
                    OrdenComparendoNacionalHelper.toLevel1DTO(comparendo.getOrdenComparendoNacional()));
            comparendoDTO.setPersonaList(ComparendoPersonaHelper.toListLevel1DTO(comparendo.getPersonaList()));
            comparendoDTO.setInfraccion(InfraccionHelperExtend.toLevel1DTO(comparendo.getInfraccion()));
            listComparendoDTO.add(comparendoDTO);
            comparendoDTO = null;
        }
        return listComparendoDTO;
    }

    /**
     * Se encarga de adicionar la trazabilidad del comparendo
     * 
     * @param Comparendo
     *            comparendo
     * @return ComparendoDTO
     * @author divier.casas
     */
    public static ComparendoDTO toLevel1DTOTrazaExtend(Comparendo comparendo) {
        ComparendoDTO comparendoDTO = null;
        comparendoDTO = ComparendoHelper.toLevel1DTO(comparendo);
        comparendoDTO.setTrazabilidadComparendoList(
                TrazabilidadComparendoHelper.toListLevel1DTO(comparendo.getTrazabilidadComparendoList()));
        return comparendoDTO;
    }
}