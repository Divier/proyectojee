package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.TipoRechazoRecaudoDTO;
import co.com.datatools.c2.entidades.RecaudoRechazo;
import co.com.datatools.c2.entidades.TipoRechazoRecaudo;
import co.com.datatools.c2.enumeraciones.EnumCampoCorreoRechazoRecaudo;
import co.com.datatools.c2.negocio.opciones.ConstantesGestionDocumentos;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.file.archivo.ArchivoFactory;
import co.com.datatools.util.file.archivo.ContenidoArchivoDTO;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;
import co.com.datatools.util.file.archivo.IArchivo;

/**
 * @author Generated
 * @version 3.0 - Thu May 05 20:20:48 COT 2016
 */
public class RecaudoRechazoHelper {
    // --- to DTO
    public static RecaudoRechazoDTO toLevel0DTO(RecaudoRechazo entidad) {
        RecaudoRechazoDTO dto = new RecaudoRechazoDTO();
        dto.setId(entidad.getId());
        dto.setIdRecaudo(entidad.getIdRecaudo());
        dto.setCodigoBanco(entidad.getCodigoBanco());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setCodigoTipoCuenta(entidad.getCodigoTipoCuenta());
        dto.setCodigoTipoIdentificacion(entidad.getCodigoTipoIdentificacion());
        dto.setEstadoLectura(entidad.getEstadoLectura());
        dto.setFechaTransaccion(entidad.getFechaTransaccion());
        dto.setHoraTransaccion(entidad.getHoraTransaccion());
        dto.setNumeroCuenta(entidad.getNumeroCuenta());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setNumeroRecaudo(entidad.getNumeroRecaudo());
        dto.setTotalCheque(entidad.getTotalCheque());
        dto.setTotalEfectivo(entidad.getTotalEfectivo());
        dto.setTotalRecaudo(entidad.getTotalRecaudo());
        dto.setCodigoTipoRecaudo(entidad.getCodigoTipoRecaudo());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setUsuario(entidad.getUsuario());
        dto.setMotivoRechazoRecaudo(new ArrayList<TipoRechazoRecaudoDTO>());
        dto.setFechaRecibido(entidad.getFechaRecibido());
        dto.setFechaGeneracionReporte(entidad.getFechaGeneracionReporte());

        return dto;
    }

    public static RecaudoRechazoDTO toLevel1DTO(RecaudoRechazo entidad) {
        RecaudoRechazoDTO dto = toLevel0DTO(entidad);

        if (entidad.getMotivoRechazoRecaudo() != null && !entidad.getMotivoRechazoRecaudo().isEmpty()) {
            for (TipoRechazoRecaudo trr : entidad.getMotivoRechazoRecaudo()) {
                dto.getMotivoRechazoRecaudo().add(TipoRechazoRecaudoHelper.toLevel0DTO(trr));
            }
        }

        return dto;
    }

    public static List<RecaudoRechazoDTO> toListLevel0DTO(List<RecaudoRechazo> listEntidad) {
        List<RecaudoRechazoDTO> listDto = new ArrayList<RecaudoRechazoDTO>();
        for (RecaudoRechazo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RecaudoRechazoDTO> toListLevel1DTO(List<RecaudoRechazo> listEntidad) {
        List<RecaudoRechazoDTO> listDto = new ArrayList<RecaudoRechazoDTO>();
        for (RecaudoRechazo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RecaudoRechazo toLevel0Entity(RecaudoRechazoDTO dto, RecaudoRechazo entidad) {
        if (null == entidad) {
            entidad = new RecaudoRechazo();
        }
        entidad.setId(dto.getId());
        entidad.setIdRecaudo(dto.getIdRecaudo());
        entidad.setCodigoBanco(dto.getCodigoBanco());
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setCodigoTipoCuenta(dto.getCodigoTipoCuenta());
        entidad.setCodigoTipoIdentificacion(dto.getCodigoTipoIdentificacion());
        entidad.setEstadoLectura(dto.getEstadoLectura());
        entidad.setFechaTransaccion(dto.getFechaTransaccion());
        entidad.setHoraTransaccion(dto.getHoraTransaccion());
        entidad.setNumeroCuenta(dto.getNumeroCuenta());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setNumeroRecaudo(dto.getNumeroRecaudo());
        entidad.setTotalCheque(dto.getTotalCheque());
        entidad.setTotalEfectivo(dto.getTotalEfectivo());
        entidad.setTotalRecaudo(dto.getTotalRecaudo());
        entidad.setCodigoTipoRecaudo(dto.getCodigoTipoRecaudo());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setUsuario(dto.getUsuario());
        entidad.setFechaGeneracionReporte(dto.getFechaGeneracionReporte());
        entidad.setFechaRecibido(dto.getFechaRecibido());

        if (dto.getMotivoRechazoRecaudo() != null && !dto.getMotivoRechazoRecaudo().isEmpty()) {
            entidad.setMotivoRechazoRecaudo(TipoRechazoRecaudoHelper.toListLevel0Entity(dto.getMotivoRechazoRecaudo()));
        }

        return entidad;
    }

    public static RecaudoRechazo toLevel1Entity(RecaudoRechazoDTO dto, RecaudoRechazo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<RecaudoRechazo> toListLevel0Entity(List<RecaudoRechazoDTO> listDto) {
        List<RecaudoRechazo> listEntidad = new ArrayList<RecaudoRechazo>();
        for (RecaudoRechazoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RecaudoRechazo> toListLevel1Entity(List<RecaudoRechazoDTO> listDto) {
        List<RecaudoRechazo> listEntidad = new ArrayList<RecaudoRechazo>();
        for (RecaudoRechazoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad

    /**
     * Genera el archivo de excel
     * 
     * @param pagoInconsistenciaRespuestaDTOList
     * @return
     */
    public static byte[] generaArchivo(List<RecaudoRechazoDTO> pagoInconsistenciaRespuestaDTOList) {
        Integer cantidadRegistros = 0;

        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();

        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);

        // Lista de encabezados
        List<Object> encabezado = new ArrayList<>();
        encabezado.add(EnumCampoCorreoRechazoRecaudo.NUMERO_RECAUDO.getTitulo());
        encabezado.add(EnumCampoCorreoRechazoRecaudo.OBLIGACION_PAGADA.getTitulo());
        encabezado.add(EnumCampoCorreoRechazoRecaudo.FECHA_RECEPCION.getTitulo());
        encabezado.add(EnumCampoCorreoRechazoRecaudo.NOMBRE_USUARIO.getTitulo());
        encabezado.add(EnumCampoCorreoRechazoRecaudo.ERROR.getTitulo());

        filaArchivoExcel.setCeldas(encabezado);
        filaArchivoExcel.setEncabezado(true);
        filaArchivoExcel.setHoja(0);
        filaArchivoList.add(filaArchivoExcel);

        for (RecaudoRechazoDTO pagoInconsistenciaRespuestaDTO : pagoInconsistenciaRespuestaDTOList) {

            if (pagoInconsistenciaRespuestaDTO.getMotivoRechazoRecaudo() != null
                    && !pagoInconsistenciaRespuestaDTO.getMotivoRechazoRecaudo().isEmpty()) {

                cantidadRegistros++;

                FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
                filaArchivoExcel.setHoja(0);
                List<Object> datos = new ArrayList<>();

                datos.add(pagoInconsistenciaRespuestaDTO.getNumeroRecaudo());
                datos.add(pagoInconsistenciaRespuestaDTO.getNumeroObligacion());
                datos.add(Utilidades.dateToStringFormatApp(pagoInconsistenciaRespuestaDTO.getFechaRecibido(), true));
                datos.add(pagoInconsistenciaRespuestaDTO.getUsuario());

                // Errores
                StringBuilder inconsistencias = new StringBuilder();
                for (TipoRechazoRecaudoDTO tipoRechazoDTO : pagoInconsistenciaRespuestaDTO.getMotivoRechazoRecaudo()) {
                    if (inconsistencias.length() == 0) {
                        inconsistencias.append(tipoRechazoDTO.getDescripcion());
                    } else {
                        inconsistencias.append("|" + tipoRechazoDTO.getDescripcion());
                    }
                }
                datos.add(inconsistencias);

                filaArchivoExcelAux.setCeldas(datos);
                filaArchivoList.add(filaArchivoExcelAux);
            }
        }

        return escribirArchivo(filaArchivoList, EnumTipoArchivo.XLSX);
    }

    /**
     * Escribe el archivo con los datos enviados
     * 
     * @param filaArchivoList
     * @param tipoArchivo
     * @return
     */
    public static byte[] escribirArchivo(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenido = archivoFactory.obtenerContenido(tipoArchivo, filaArchivoList);
        return iArchivo.escribirArchivo(contenido, tipoArchivo);
    }
}
