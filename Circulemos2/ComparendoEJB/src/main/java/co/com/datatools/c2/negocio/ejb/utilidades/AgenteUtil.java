package co.com.datatools.c2.negocio.ejb.utilidades;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.adaptador.dto.ValidarIncosistenciaAgenteDTO;
import co.com.datatools.c2.enumeraciones.EnumCampoInconsistenciaAgente;
import co.com.datatools.c2.negocio.opciones.ConstantesGestionDocumentos;
import co.com.datatools.util.file.archivo.ArchivoFactory;
import co.com.datatools.util.file.archivo.ContenidoArchivoDTO;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;
import co.com.datatools.util.file.archivo.IArchivo;

public class AgenteUtil {

    public static byte[] escribirArchivo(List<FilaArchivoDTO> filaArchivoList, EnumTipoArchivo tipoArchivo) {
        ArchivoFactory archivoFactory = new ArchivoFactory();
        IArchivo iArchivo = archivoFactory.obtenerArchivo(tipoArchivo,
                System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
        ContenidoArchivoDTO contenido = archivoFactory.obtenerContenido(tipoArchivo, filaArchivoList);
        return iArchivo.escribirArchivo(contenido, tipoArchivo);
    }

    public static byte[] generaArchivo(List<ValidarIncosistenciaAgenteDTO> validarInconsistencia) {

        List<FilaArchivoDTO> filaArchivoList = new ArrayList<>();

        FilaArchivoExcelDTO filaArchivoExcel = new FilaArchivoExcelDTO();
        filaArchivoExcel.setHoja(0);

        List<Object> encabezado = new ArrayList<>();

        encabezado.add(EnumCampoInconsistenciaAgente.NUMERO_FACTURA.getTitulo());
        encabezado.add(EnumCampoInconsistenciaAgente.FECHA_IMPOSICION.getTitulo());
        encabezado.add(EnumCampoInconsistenciaAgente.PLACA_AGENTE.getTitulo());
        encabezado.add(EnumCampoInconsistenciaAgente.FECHA_VALIDACION.getTitulo());
        encabezado.add(EnumCampoInconsistenciaAgente.INCOSISTENCIA.getTitulo());

        filaArchivoExcel.setCeldas(encabezado);
        filaArchivoExcel.setEncabezado(true);
        filaArchivoExcel.setHoja(0);
        filaArchivoList.add(filaArchivoExcel);

        for (ValidarIncosistenciaAgenteDTO detalleValidarInconsistencia : validarInconsistencia) {
            FilaArchivoExcelDTO filaArchivoExcelAux = new FilaArchivoExcelDTO();
            filaArchivoExcel.setHoja(0);
            List<Object> datos = new ArrayList<>();
            datos.add(String.valueOf(detalleValidarInconsistencia.getIdFacturaAxis()));
            datos.add(detalleValidarInconsistencia.getHoraImposicion());
            datos.add(detalleValidarInconsistencia.getPlacaAgenteTransito());
            datos.add(detalleValidarInconsistencia.getFechaValidacion());
            datos.add(detalleValidarInconsistencia.getInconsistencia());

            filaArchivoExcelAux.setCeldas(datos);
            filaArchivoList.add(filaArchivoExcelAux);
        }

        return escribirArchivo(filaArchivoList, EnumTipoArchivo.XLS);

    }

}
