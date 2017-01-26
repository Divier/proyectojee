package co.com.datatools.seguridad.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Clase utilitaria para validar un xml a partir de un xsd
 * 
 * @author claudia.rodriguez
 */
public class XmlSchemaValidatorUtil {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    /**
     * Valida un documento XML con un esquema XML (XSD).
     * 
     * @param xml
     *            Contenido del archivo que contiene el documento xml a validar
     * @param xmlSchema
     *             Contenido del archivo que contiene el esquema que define el formato valido.
     * 
     * @throws ParserConfigurationException
     *             En caso de error de configuracion
     * @throws SAXException
     *             En caso de detectar un error de validacion
     * @throws IOException
     *             en caso de error al crear los InputSource
     */
    public void validarXml(String contenidoXml, String contenidoXsd) throws ParserConfigurationException, SAXException,
            IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        InputSource inputSourceXsd = new InputSource(new StringReader(contenidoXsd));
        factory.setAttribute(JAXP_SCHEMA_SOURCE, inputSourceXsd);

        InputSource inputSourceXml = new InputSource(new StringReader(contenidoXml));
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException ex) throws SAXException {
                throw ex;
            }

            public void error(SAXParseException ex) throws SAXException {
                throw ex;
            }

            public void fatalError(SAXParseException ex) throws SAXException {
                throw ex;
            }
        });
        documentBuilder.parse(inputSourceXml);

    }

}
