package co.com.datatools.util.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Clase que permite generar un comprimido de archivos
 * 
 * @author sergio.torres
 * 
 */
public class CompresorArchivos {

    private List<Entrada> entradas;

    /**
     * Objeto para almacenar cada una de las entradas del .zip
     * 
     * @author sergio.torres
     * 
     */
    private class Entrada {
        private String nombre;
        private byte[] contenido;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public byte[] getContenido() {
            return contenido;
        }

        public void setContenido(byte[] contenido) {
            this.contenido = contenido;
        }
    }

    public CompresorArchivos() {
        entradas = new ArrayList<>();
    }

    /**
     * Permite agregar una nueva entrada al comprimido que se está creando
     * 
     * @param nombre
     * @param contenido
     */
    public void agregarEntrada(String nombre, byte[] contenido) {
        Entrada entrada = new Entrada();
        entrada.setNombre(nombre);
        entrada.setContenido(contenido);
        entradas.add(entrada);
    }

    /**
     * Se encarga de cerrar el archivo y entregar el archivo .zip en arreglo de bytes
     * 
     * @return
     * @throws IOException
     */
    public byte[] comprimir() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (Entrada entrada : entradas) {
                ZipEntry zipEntry = new ZipEntry(entrada.getNombre());
                zos.putNextEntry(zipEntry);
                zos.write(entrada.getContenido());
            }
            zos.closeEntry();
            zos.close();
            return baos.toByteArray();
        } finally {

        }
    }

}
