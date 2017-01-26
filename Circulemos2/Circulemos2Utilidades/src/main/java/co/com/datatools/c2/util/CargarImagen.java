package co.com.datatools.c2.util;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jboss.logging.Logger;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;

/**
 * Esta clase se encarga de realizar el proceso de conversion y carga de imagenes
 * 
 * @author luis.martinez
 * 
 */
public class CargarImagen {

    public final static Logger logger = Logger.getLogger(CargarImagen.class.getName());

    public final static String TIFF_EXTENSION = "tiff";
    public final static String PNG_EXTENSION = "png";

    /**
     * Este metodo se encarga de realizar la conversion de una imagen de formato TIFF a JPG
     * 
     * @param path
     *            donde se encuentra la imagen TIFF
     * @return String con el path donde va almacenar la imagen
     * @throws Exception
     */
    public static String cargar(String path, String ruta) {

        SeekableStream s;
        String otPath = null; // directorio donde se va almacenar la imagen
        String nombreImg = null;

        try {
            s = new FileSeekableStream(path);
            TIFFDecodeParam param = null;
            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
            RenderedImage op = dec.decodeAsRenderedImage(0);
            nombreImg = "prueba" + Math.random() * 10 + ".PNG";
            otPath = ruta + "\\" + nombreImg; // path donde se va almacenar la imagen JPEG

            try (FileOutputStream fos = new FileOutputStream(otPath)) {
                PNGEncodeParam paramPng = new PNGEncodeParam.Palette();
                ImageEncoder encoderPng = ImageCodec.createImageEncoder("PNG", fos, paramPng);
                encoderPng.encode(op);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return nombreImg;
    }

    /**
     * Permite convertir un arreglo de byte con el contenido de una imagen TIFF al contenido de una imagen PNG
     * 
     * @param tiffData
     *            datos de una imagen TIFF
     * @return datos de la imagen en formato PNG
     * @throws IOException
     *             error en el procesamiento de bytes
     * @author felipe.martinez
     */
    public static byte[] procesarTiffToPng(byte[] tiffData) throws IOException {
        SeekableStream s = new ByteArraySeekableStream(tiffData);
        TIFFDecodeParam param = null;
        ImageDecoder dec = ImageCodec.createImageDecoder(TIFF_EXTENSION, s, param);
        RenderedImage op = dec.decodeAsRenderedImage(0);

        ByteArrayOutputStream bot = new ByteArrayOutputStream();
        PNGEncodeParam paramPng = new PNGEncodeParam.Palette();
        ImageEncoder en = ImageCodec.createImageEncoder(PNG_EXTENSION, bot, paramPng);
        en.encode(op);
        return bot.toByteArray();
    }

}
