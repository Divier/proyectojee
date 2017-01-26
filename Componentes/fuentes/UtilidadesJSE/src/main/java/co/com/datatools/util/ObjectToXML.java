package co.com.datatools.util;

import com.thoughtworks.xstream.XStream;

/**
 * Clase utilitaria para serializar un objeto a XML, por optimizacion contiene una unica instancia global de procesador de xml con su configuracion
 * por defecto. Si se quiere personalizar la configuracion de {@link XStream}, usar {@link Customized}
 * 
 * @author Felipe Martinez
 */
public class ObjectToXML {

    private static final XStream globalXstream = new XStream();

    private ObjectToXML() {

    }

    /**
     * Serializa a XML el objeto y cada uno los atributos q contenga
     * 
     * @param obj
     *            objeto a representar como XML
     * @return XML q representa la estructura del objeto
     */
    public static String objectToXml(Object obj) {
        return globalXstream.toXML(obj);
    }

    /**
     * Obtiene un objeto apartir del contenido almacenado en un xml
     * 
     * @param resultType
     *            tipo de objeto esperado
     * @param xml
     *            cadena con formato xml a parsear
     * @return objeto parseado
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(Class<T> resultType, CharSequence xml) {
        return (T) globalXstream.fromXML(xml.toString());
    }

    /**
     * Clase a utilizar cuando se necesita realizar una configuracion personalizada sobre el procesador de XStream
     * 
     * @author Felipe Martinez
     */
    public static class Customized {
        /**
         * Instancia del serializador xstream para este objeto
         */
        private final XStream instanceXstream;

        /**
         * Permite construir una fachada para parsear objetos a xml y viceversa, apartir de una instancia configurada del serializador XStream
         * 
         * @param serializer
         *            instancia del serializador personalizada, si es <b>null</b> inicia uno instancia con la configuracion por defecto
         */
        public Customized(XStream serializer) {
            if (serializer != null) {
                instanceXstream = serializer;
            } else {
                instanceXstream = new XStream();
            }
        }

        /**
         * Serializa a XML el objeto y cada uno los atributos q contenga
         * 
         * @param obj
         *            objeto a representar como XML
         * @return XML q representa la estructura del objeto
         */
        public String objectToXml(Object obj) {
            return instanceXstream.toXML(obj);
        }

        /**
         * Obtiene un objeto apartir del contenido almacenado en un xml
         * 
         * @param resultType
         *            tipo de objeto esperado
         * @param xml
         *            cadena con formato xml a parsear
         * @return objeto parseado
         */
        @SuppressWarnings("unchecked")
        public <T> T xmlToObject(Class<T> resultType, CharSequence xml) {
            return (T) instanceXstream.fromXML(xml.toString());
        }

        /**
         * Obtiene la instancia configurada de XStream
         * 
         * @return instancia inicializada de XStream para este objeto
         */
        public XStream getInstanceXstream() {
            return instanceXstream;
        }
    }
}
