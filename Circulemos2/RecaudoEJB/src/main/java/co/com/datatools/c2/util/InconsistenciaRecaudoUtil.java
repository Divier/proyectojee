package co.com.datatools.c2.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoConciliacionErrorDTO;
import co.com.datatools.c2.dto.DetallePagoInconsistenciaResDTO;
import co.com.datatools.c2.dto.DetallePagoRespuestaDTO;
import co.com.datatools.c2.dto.PagoRespuestaDTO;
import co.com.datatools.c2.enumeraciones.EnumCampoCorreoInconsistenciaRecaudo;
import co.com.datatools.c2.negocio.opciones.ConstantesGestionDocumentos;
import co.com.datatools.util.file.archivo.ArchivoFactory;
import co.com.datatools.util.file.archivo.ContenidoArchivoDTO;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;
import co.com.datatools.util.file.archivo.IArchivo;

public class InconsistenciaRecaudoUtil {

    public static byte[] escribirArchivo(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenido = archivoFactory.obtenerContenido(tipoArchivo, filaArchivoList);
        return iArchivo.escribirArchivo(contenido, tipoArchivo);
    }

    public static Object[] generaArchivo(List<DetallePagoInconsistenciaResDTO> pagoInconsistenciaRespuestaDTOList) {
        Date fechaActual = new Date();
        Integer cantidadRegistros = 0;

        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();

        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);

        List<Object> encabezado = new ArrayList<>();

        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.ORGANISMO_TRANSITO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.FECHA_TRANSACCION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.HORA_TRANSACCION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NUMERO_RECAUDO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.CUENTA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_CUENTA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.BANCO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_EFECTIVO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_CHEQUE.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_RECAUDO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_DOCUMENTO_DEUDOR.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NUMERO_IDENTIFICACION_DEUDOR.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.OBLIGACION_PAGADA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.VALOR_OBLIGACION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_RECUADO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NRO_CUOTAS.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.FECHA_GENERACION.getTitulo());

        filaArchivoExcel.setCeldas(encabezado);
        filaArchivoExcel.setEncabezado(true);
        filaArchivoExcel.setHoja(0);
        filaArchivoList.add(filaArchivoExcel);

        for (DetallePagoInconsistenciaResDTO detallePagoInconsistenciaResDTO : pagoInconsistenciaRespuestaDTOList) {
            cantidadRegistros++;
            FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
            filaArchivoExcel.setHoja(0);
            List<Object> datos = new ArrayList<>();

            datos.add(String.valueOf(detallePagoInconsistenciaResDTO.getOrganismoTransito()));
            datos.add(detallePagoInconsistenciaResDTO.getFechaTransaccion());
            datos.add(detallePagoInconsistenciaResDTO.getHoraTransaccion());
            datos.add(detallePagoInconsistenciaResDTO.getNumeroRecaudo());
            datos.add(detallePagoInconsistenciaResDTO.getNumeroCuota());
            datos.add(detallePagoInconsistenciaResDTO.getCodigoTipoCuenta());
            datos.add(detallePagoInconsistenciaResDTO.getCodigoBanco());
            datos.add(detallePagoInconsistenciaResDTO.getTotalEfectivo());
            datos.add(detallePagoInconsistenciaResDTO.getTotalCheque());
            datos.add(detallePagoInconsistenciaResDTO.getTotalRecaudo());
            datos.add(detallePagoInconsistenciaResDTO.getCodigoTipoDocumento());
            datos.add(detallePagoInconsistenciaResDTO.getNumeroIdentificacion());

            datos.add(detallePagoInconsistenciaResDTO.getObligacionPagada());
            datos.add(detallePagoInconsistenciaResDTO.getValorObligacion());
            // datos.add(detallePagoInconsistenciaDTO.getIdConceptoCartera());
            datos.add(detallePagoInconsistenciaResDTO.getCodigoTipoRecaudo());
            datos.add(detallePagoInconsistenciaResDTO.getNumeroCuota());
            datos.add(Utilidades.dateToStringFormatApp(fechaActual, false));
            datos.add(detallePagoInconsistenciaResDTO.getInconsistencias());

            filaArchivoExcelAux.setCeldas(datos);
            filaArchivoList.add(filaArchivoExcelAux);

        }
        Object[] arreglo = { escribirArchivo(filaArchivoList, EnumTipoArchivo.XLSX), cantidadRegistros };
        return arreglo;

    }

    public static Object[] generarArchivoInconsistenciasConciliacion(List<PagoRespuestaDTO> pagoRespuestaDTOList) {
        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();

        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);

        List<Object> encabezado = new ArrayList<>();

        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.ORGANISMO_TRANSITO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.FECHA_TRANSACCION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.HORA_TRANSACCION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NUMERO_RECAUDO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.CUENTA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_CUENTA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.BANCO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_EFECTIVO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_CHEQUE.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TOTAL_RECAUDO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_DOCUMENTO_DEUDOR.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NUMERO_IDENTIFICACION_DEUDOR.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.OBLIGACION_PAGADA.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.VALOR_OBLIGACION.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.TIPO_RECUADO.getTitulo());
        encabezado.add(EnumCampoCorreoInconsistenciaRecaudo.NRO_CUOTAS.getTitulo());

        filaArchivoExcel.setCeldas(encabezado);
        filaArchivoExcel.setEncabezado(true);
        filaArchivoExcel.setHoja(0);
        filaArchivoList.add(filaArchivoExcel);

        int totalInconsistencias = 0;
        for (PagoRespuestaDTO pagoRespuestaDTO : pagoRespuestaDTOList) {
            for (DetallePagoRespuestaDTO detallePagoRespuestaDTO : pagoRespuestaDTO.getDetallePagoRespuestaDTOList()) {
                FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
                filaArchivoExcel.setHoja(0);
                List<Object> datos = new ArrayList<>();

                StringBuilder inconsistencias = new StringBuilder();
                datos.add(String.valueOf(pagoRespuestaDTO.getOrganismoTransito().getCodigoOrganismo()));
                datos.add(Utilidades.dateToStringFormatApp(pagoRespuestaDTO.getFechaTransaccion(), false));
                datos.add(Utilidades.hourToStringFormatApp(pagoRespuestaDTO.getFechaTransaccion()));
                datos.add(pagoRespuestaDTO.getNumeroPago());
                datos.add(pagoRespuestaDTO.getNumeroCuenta());
                datos.add(pagoRespuestaDTO.getCodigoTipoCuenta());
                datos.add(pagoRespuestaDTO.getCodigoBanco());
                datos.add(pagoRespuestaDTO.getTotalEfectivo());
                datos.add(pagoRespuestaDTO.getTotalCheque());
                datos.add(pagoRespuestaDTO.getTotalRecaudo());
                if (pagoRespuestaDTO.getTipoIdentificacionPersona() != null) {
                    datos.add(pagoRespuestaDTO.getTipoIdentificacionPersona().getCodigo());
                }
                datos.add(pagoRespuestaDTO.getNumeroDocumento());
                datos.add(detallePagoRespuestaDTO.getNumeroObligacion());
                datos.add(detallePagoRespuestaDTO.getValorObligacion());
                datos.add(detallePagoRespuestaDTO.getCodigoTipoRecaudo());
                datos.add(detallePagoRespuestaDTO.getNumeroCuota());

                StringBuilder sb = new StringBuilder();

                for (DetallePagoConciliacionErrorDTO detallePagoConciliacionError : detallePagoRespuestaDTO
                        .getDetallePagoConciliacionErrores()) {
                    sb.append(detallePagoConciliacionError.getErrorConciliacionPago().getDescripcion() + "|");
                }
                datos.add(sb.toString());
                filaArchivoExcelAux.setCeldas(datos);
                filaArchivoList.add(filaArchivoExcelAux);
                totalInconsistencias++;
            }
        }
        Object[] arreglo = { escribirArchivo(filaArchivoList, EnumTipoArchivo.XLSX), totalInconsistencias };
        return arreglo;

    }
}
