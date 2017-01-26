package co.com.datatools.c2.mail.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UtilidadesCorreo {

    /**
     * Este metodo convierte un objeto de tipo File a un byte[] para el envio de archivos adjuntos
     * 
     * @param file
     *            Envia archivo
     * @return byte[]
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        /**
         * Obtiene el tamaño del archivo
         */
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            /**
             * Archivo es muy grande
             */
        }

        /**
         * Crea el arreglo de bytes para almacenar los datos
         */
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        /**
         * Asegura que todos los bytes han sido leidos
         */
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }

}
