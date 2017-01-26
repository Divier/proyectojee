package co.com.datatools.util.file;

import java.io.InputStream;

/**
 * Allows using Apache Tika library validate the contents of the files
 * 
 * @author luis.forero
 * 
 */
public class FileValidator {

    /**
     * Allows validate that the archive content has a content of the input type.
     * 
     * @param inputStream
     *            input stream of the archive.
     * @param nameFile
     *            name file with extension
     * @param contentTypes
     *            content types that the extension file is valid. (To day the content types are extensions of archive )
     * @return true if archive content are into the contents type params
     * @author luis.forero
     */
    public static boolean checkFileType(InputStream inputStream, String nameFile, String... contentTypes) {

        // try {
        // ContentHandler contenthandler = new BodyContentHandler();
        // Metadata metadata = new Metadata();
        // metadata.set(Metadata.RESOURCE_NAME_KEY, nameFile);
        // Parser parser = new AutoDetectParser();
        // try {
        // parser.parse(inputStream, contenthandler, metadata, new ParseContext());
        // } catch (SAXException | TikaException e) {
        // return false;
        // }

        boolean valid = false;
        for (String contentType : contentTypes) {
            // TODO FM implementacion de contenttype de el input stream recibido
            // if (metadata.get(Metadata.CONTENT_TYPE).equalsIgnoreCase(contentType)) {
            // valid = true;
            // break;
            // }
            if (nameFile.endsWith(contentType)) {
                valid = true;
                break;
            }
        }

        return valid;
        // } catch (IOException e) {
        // return false;
        // }
    }

}
