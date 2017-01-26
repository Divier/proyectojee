package co.com.datatools.c2.negocio.helpers.parametrizacion;

import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOCOMPUESTO;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOINDEPENDIENTE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._CATALOGOSIMPLE;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._DECIMAL;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._FECHAHORA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._HORA;
import static co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO._NUMERO;

import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.Operador;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.OperandoEspecial;
import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO;
import co.com.datatools.c2.dto.parametrizacion.RegistroConfiguracionDTO.TagXmlRegistro;
import co.com.datatools.c2.dto.parametrizacion.TipoConfiguracionDTO;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * Clase de procesamiento de los XML de plantilla y valores de los registros de una configuracion(regla) de negocio
 * 
 * @author Felipe.Martinez
 */
public class ProcesadorXmlConfiguracion {

    private static final String TAG_VAL_CAMPO = "campo";
    private static final String TAG_VAL_CONSTANTE = "constante";
    private static final String TAG_VALIDACION = "validacion";
    private static final String TAG_NOMBRE_SERVICIO = "nombreServicio";
    private static final String TAG_NOMBRE_INTERFACE = "nombreInterface";
    private static final String TAG_NOMBRE_CLASE_CATALOGO = "nombreClaseCatalogo";
    private static final String TAG_DESCRIPCION = "descripcion";
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_CODIGO = "codigo";
    private static final String ATTR_OBLIGATORIO = "obligatorio";
    private static final String ATTR_NO_DECIMALES = "nodecimales";
    private static final String ATTR_SELECCION = "seleccion";
    private static final String ATTR_TRASLAPAR_FECHAS = "traslaparFechas";
    private static final String ATTR_LONGITUD = "longitud";
    private static final String TAG_SALIDA = "salida";
    private static final String TAG_ENTRADA = "entrada";
    private static final String TAG_CONFIGURACION_NEGOCIO = "configuracion_negocio";

    /**
     * Valor con el que se representa un campo nulo en el xml almacenado en la BD
     */
    private static final String VALOR_NULL = "##NULL##";

    /**
     * Filtro que permite ignorar los elementos de cadena vacia dentro de un nodo de un xml
     */
    private static EventFilter filtroElems = new EventFilter() {
        @Override
        public boolean accept(XMLEvent event) {
            if (event.isCharacters()) {
                return StringUtils.isNotBlank(event.asCharacters().getData())
                        && !event.asCharacters().getData().equals("\n");
            }
            return true;
        }
    };

    private LinkedHashMap<String, CampoConfiguracionDTO> camposCreados;

    private List<Triple<CampoConfiguracionDTO, Operador, String>> validacionesSobreCampos;

    private XMLEventReader xmlEventReader;

    public ProcesadorXmlConfiguracion() {

    }

    /**
     * Realiza el procesamiento del XML de una plantilla de una configuracion
     * 
     * @param xmlPlantilla
     *            String que contiene la estructura de la configuracion
     * @return estructura de datos que representa la configuracion
     * @author Felipe.Martinez
     */
    public synchronized LinkedHashMap<String, CampoConfiguracionDTO> parseXmlPlantilla(String xmlPlantilla) {
        camposCreados = new LinkedHashMap<>(3);
        validacionesSobreCampos = new ArrayList<>(1);

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            xmlEventReader = xmlInputFactory.createFilteredReader(
                    xmlInputFactory.createXMLEventReader(new StringReader(xmlPlantilla)), filtroElems);
            // --
            boolean procesaEntradas = false;
            CampoConfiguracionDTO campo = null;
            Attribute attribute = null;

            if (xmlEventReader.peek().isStartDocument())
                xmlEventReader.nextEvent();

            nextEvent: while (xmlEventReader.hasNext()) {

                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    String elemName = startElement.getName().getLocalPart();
                    switch (elemName) {
                    case TAG_CONFIGURACION_NEGOCIO:
                        continue nextEvent;
                    case TAG_ENTRADA:
                        procesaEntradas = true;
                        continue nextEvent;
                    case TAG_SALIDA:
                        procesaEntradas = false;
                        continue nextEvent;
                    }

                    TipoCampoConfiguracion tipoCampo = null;
                    if ((tipoCampo = TipoCampoConfiguracion.valueOf(elemName)) != null) {
                        // -- El elemento que inicia es un campo de la configuracion

                        if (tipoCampo.equals(TipoCampoConfiguracion.RANGOFECHA)) {
                            attribute = startElement.getAttributeByName(new QName(ATTR_TRASLAPAR_FECHAS));
                            boolean traslapar = false;
                            if (attribute != null) {
                                traslapar = parseXsdBoolean(attribute.getValue());
                            }
                            xmlEventReader.nextEvent();// -- codigo
                            String codigoRango = xmlEventReader.getElementText();

                            startElement = xmlEventReader.nextEvent().asStartElement();// -- INICIAL
                            campo = procesarCampoPlantilla(procesaEntradas, startElement, _FECHA);
                            campo.setAttrRngTraslapar(traslapar);
                            campo.setAttrRngCodigoRango(codigoRango);
                            camposCreados.put(campo.getCodigo(), campo);
                            xmlEventReader.nextEvent();// -- INICIAL fin

                            startElement = xmlEventReader.nextEvent().asStartElement();// -- FINAL
                            campo = procesarCampoPlantilla(procesaEntradas, startElement, _FECHA);
                            campo.setAttrRngTraslapar(traslapar);
                            campo.setAttrRngCodigoRango(codigoRango);
                            camposCreados.put(campo.getCodigo(), campo);
                            xmlEventReader.nextEvent();// -- FINAL fin
                        } else {
                            campo = procesarCampoPlantilla(procesaEntradas, startElement, tipoCampo);
                            camposCreados.put(campo.getCodigo(), campo);
                        }
                        campo = null;
                    }

                }
            }

        } catch (XMLStreamException e) {
            throw new CirculemosRuntimeException(e);
        }

        for (Triple<CampoConfiguracionDTO, Operador, String> triple : validacionesSobreCampos) {
            CampoConfiguracionDTO campoOperando = camposCreados.get(triple.getRight());
            if (campoOperando == null) {
                throw new CirculemosRuntimeException(
                        "Error de definicion de la validacion para el campo {0}, el campo con codigo {1} no existe",
                        triple.getLeft().getCodigo(), triple.getRight());
            } else {
                verificarTipoComparable(triple.getLeft(), campoOperando);
                triple.getLeft().addValidacion(triple.getMiddle(), campoOperando);
            }
        }
        return camposCreados;
    }

    private CampoConfiguracionDTO procesarCampoPlantilla(boolean procesaEntradas, StartElement startElement,
            TipoCampoConfiguracion tipoCampo) throws XMLStreamException {

        CampoConfiguracionDTO campo = new CampoConfiguracionDTO(tipoCampo, procesaEntradas);

        Attribute attribute = startElement.getAttributeByName(new QName(ATTR_OBLIGATORIO));
        if (attribute != null) {
            campo.setObligatorio(parseXsdBoolean(attribute.getValue()));
        }
        attribute = startElement.getAttributeByName(new QName(ATTR_NO_DECIMALES));
        if (attribute != null) {
            campo.setAttrDecPrecision(Integer.valueOf(attribute.getValue()));
        }
        attribute = startElement.getAttributeByName(new QName(ATTR_SELECCION));
        if (attribute != null) {
            campo.setAttrCatSeleccion(Integer.valueOf(attribute.getValue()));
        }
        attribute = startElement.getAttributeByName(new QName(ATTR_LONGITUD));
        if (attribute != null) {
            campo.addValidacion(Operador.LONGITUD, Integer.valueOf(attribute.getValue()));
        }

        while ((xmlEventReader.peek()).isStartElement()) {
            String nombrePropiedadCampo = xmlEventReader.nextEvent().asStartElement().getName().getLocalPart();

            switch (nombrePropiedadCampo) {
            case TAG_CODIGO:
                campo.setCodigo(xmlEventReader.getElementText());
                break;
            case TAG_NOMBRE:
                campo.setNombre(xmlEventReader.getElementText());
                break;
            case TAG_DESCRIPCION:
                campo.setDescripcion(xmlEventReader.getElementText());
                break;
            case TAG_NOMBRE_CLASE_CATALOGO:
                if (tipoCampo.seEncuentraEn(_CATALOGOCOMPUESTO, _CATALOGOSIMPLE))
                    campo.setAttrCatOrigenDatos(xmlEventReader.getElementText());
                break;
            case TAG_NOMBRE_INTERFACE:
                if (tipoCampo.equals(_CATALOGOINDEPENDIENTE))
                    campo.setAttrCatOrigenDatos(xmlEventReader.getElementText());
                break;
            case TAG_NOMBRE_SERVICIO:
                if (tipoCampo.equals(_CATALOGOINDEPENDIENTE))
                    campo.setAttrCatOrigenDatos(campo.getAttrCatOrigenDatos() + "{{" + xmlEventReader.getElementText()
                            + "}}");
                break;
            case TAG_VALIDACION:
                procesarTagValidacion(campo, tipoCampo);
                break;
            }
        }
        return campo;
    }

    private void procesarTagValidacion(CampoConfiguracionDTO campo, TipoCampoConfiguracion tipoCampo)
            throws XMLStreamException {

        xmlEventReader.nextEvent(); // -- operador
        Operador operador = Operador.valueOf(xmlEventReader.getElementText());

        xmlEventReader.nextEvent();// -- operando
        String tipoOperando = xmlEventReader.nextEvent().asStartElement().getName().getLocalPart();
        String valorOperando = xmlEventReader.getElementText();

        if (StringUtils.equals(tipoOperando, TAG_VAL_CONSTANTE)) {
            if (tipoCampo.seEncuentraEn(_NUMERO, _DECIMAL, _FECHA, _FECHAHORA, _HORA)) {
                campo.addValidacion(operador, unmarshalValorCampo(tipoCampo, valorOperando));
            }
        } else if (StringUtils.equals(tipoOperando, TAG_VAL_CAMPO)) {

            if (StringUtils.equals(valorOperando, OperandoEspecial.FECHA_ACTUAL.toString())
                    && tipoCampo.seEncuentraEn(_FECHA, _FECHAHORA)) {
                campo.addValidacion(operador, OperandoEspecial.FECHA_ACTUAL);
            } else {
                CampoConfiguracionDTO campoOperando = camposCreados.get(valorOperando);
                if (campoOperando == null) {
                    // si el campo no esta puede ser que todavia no ha sido leido
                    // almacenar para posterior procesamiento
                    validacionesSobreCampos.add(new ImmutableTriple<>(campo, operador, valorOperando));
                } else {
                    // Valida contra otro campo de la configuracion
                    verificarTipoComparable(campo, campoOperando);
                    campo.addValidacion(operador, campoOperando);
                }
            }

        }
        xmlEventReader.nextEvent();// -- fin operando
        xmlEventReader.nextEvent();// -- fin validacion
    }

    private void verificarTipoComparable(CampoConfiguracionDTO campo, CampoConfiguracionDTO campoOperando) {
        if (!campo.getTipo().equals(campoOperando.getTipo()))
            throw new CirculemosRuntimeException("El campo={0} no puede ser validado contra el campo={1}",
                    campo.getCodigo(), campoOperando.getCodigo());
    }

    /**
     * Genera una cadena con la representacion en XML de un registro
     * 
     * @param plantillaRegistro
     *            plantilla a la que pertenece el registro
     * @param registro
     *            objeto con los valores del campo del registro
     * @return cadena con estructura XML que representa al registro
     */
    public static String generarXML(TipoConfiguracionDTO plantillaRegistro, RegistroConfiguracionDTO registro) {
        return MapCamposConfiguracionConverter.getXstream(plantillaRegistro).toXML(registro.getCampos());
    }

    /**
     * Permite construir un registro a partir del xml que lo representa
     * 
     * @param plantillaRegistro
     *            plantilla a la que pertenece el registro
     * @param id
     *            identificador de base de datos del registro
     * @param xmlRegistro
     *            cadena con estructura XML que representa al registro
     * 
     * @return registro con los campos inicializados
     */
    @SuppressWarnings("unchecked")
    public static RegistroConfiguracionDTO construirRegistro(TipoConfiguracionDTO plantillaRegistro, Integer id,
            String xmlRegistro) {
        LinkedHashMap<String, Object> mapeo = (LinkedHashMap<String, Object>) MapCamposConfiguracionConverter
                .getXstream(plantillaRegistro).fromXML(xmlRegistro);
        return new RegistroConfiguracionDTO(id, mapeo);
    }

    /**
     * Funcion encargada de convertir un Objeto a una representacion en cadena de caracteres de acuerdo al tipo del campo asociado
     * 
     * @param tipoCampo
     *            define el tipo de campo que esta representado en el objeto _valorCampo
     * @param _valorCampo
     *            objeto a procesar de acuerdo a tipoCampo ingresado
     * @return representacion en cadena de caracteres del objeto ingresado
     */
    public static String marshalValorCampo(TipoCampoConfiguracion tipoCampo, Object _valorCampo) {
        if (_valorCampo == null || _valorCampo.equals(ObjectUtils.NULL)) {
            return VALOR_NULL;
        }

        Gson gson = new Gson();
        switch (tipoCampo) {
        case BOOLEANO:
        case CADENA:
        case CORREO:
        case URL:
        case DECIMAL:
        case NUMERO:
            return _valorCampo.toString();
        case FECHA:
            return RegistroConfiguracionDTO.FORMATO_XSD_FECHA.format(_valorCampo);
        case FECHAHORA:
            return RegistroConfiguracionDTO.FORMATO_XSD_FECHAHORA.format(_valorCampo);
        case HORA:
            return RegistroConfiguracionDTO.FORMATO_XSD_HORA.format(_valorCampo);

        case CATALOGOCOMPUESTO:
        case CATALOGOSIMPLE:
        case CATALOGOINDEPENDIENTE:
            return gson.toJson(_valorCampo);

        default:
            throw new CirculemosRuntimeException("Valor no esperado en switch {0}", tipoCampo);
        }
    }

    private static final Type TYPE_CATALOGO_SIMPLE = new TypeToken<List<String>>() {
    }.getType();
    private static final Type TYPE_CATALOGO_COMPUESTO = new TypeToken<Map<String, List<String>>>() {
    }.getType();

    /**
     * Funcion encargada de convertir de una cadena a un objeto dependiendo del tipo del campo
     * 
     * @param tipoCampo
     *            define el tipo de campo que esta representado en la cadena _valorCampo
     * @param _valorCampo
     *            cadena con una estructura interpretable de acuerdo al tipo del campo
     * @return el Objeto concreto asociado al tipoCampo, si _valorCampo es null, retorna {@link ObjectUtils#NULL}
     */
    public static Object unmarshalValorCampo(TipoCampoConfiguracion tipoCampo, String _valorCampo) {
        if (_valorCampo == null || _valorCampo.equals(VALOR_NULL)) {
            return ObjectUtils.NULL;
        }
        Gson gson = new Gson();
        try {
            switch (tipoCampo) {
            case BOOLEANO:
                return Boolean.valueOf(parseXsdBoolean(_valorCampo));
            case CADENA:
            case CORREO:
            case URL:
                return _valorCampo;
            case DECIMAL:
            case NUMERO:
                return new BigDecimal(_valorCampo);
            case FECHA:
                return RegistroConfiguracionDTO.FORMATO_XSD_FECHA.parse(_valorCampo);
            case FECHAHORA:
                return RegistroConfiguracionDTO.FORMATO_XSD_FECHAHORA.parse(_valorCampo);
            case HORA:
                return RegistroConfiguracionDTO.FORMATO_XSD_HORA.parse(_valorCampo);
            case CATALOGOCOMPUESTO:
                return gson.fromJson(_valorCampo, TYPE_CATALOGO_COMPUESTO);
            case CATALOGOSIMPLE:
            case CATALOGOINDEPENDIENTE:
                return gson.fromJson(_valorCampo, TYPE_CATALOGO_SIMPLE);

            default:
                throw new CirculemosRuntimeException("Valor no esperado en switch {0}", tipoCampo);
            }
        } catch (ParseException e) {
            throw new CirculemosRuntimeException("No es posible convertir la cadena " + _valorCampo
                    + " para el tipo de campo " + tipoCampo, e);
        }

    }

    private static boolean parseXsdBoolean(String xsdBooleanVal) {
        if (StringUtils.isNotBlank(xsdBooleanVal)) {
            if (xsdBooleanVal.equals("true") || xsdBooleanVal.equals("1"))
                return true;
            else if (xsdBooleanVal.equals("false") || xsdBooleanVal.equals("0")) {
                return false;
            }
        }
        throw new CirculemosRuntimeException("Valor inesperado para xsd:boolean:" + xsdBooleanVal
                + "se esperaba true, 1, false, 0");
    }

    /**
     * Esta clase permite convertir desde y hacia XML los valores de un registro de un {@link TipoConfiguracionDTO}
     * 
     * @author Felipe.Martinez
     */
    @SuppressWarnings("rawtypes")
    static class MapCamposConfiguracionConverter implements Converter, Serializable {
        private static final long serialVersionUID = 1146461063666741339L;

        private final TipoConfiguracionDTO plantillaRegistro;

        public MapCamposConfiguracionConverter(TipoConfiguracionDTO plantillaRegistro) {
            this.plantillaRegistro = plantillaRegistro;
        }

        /**
         * Permite obtener el procesador que genera registros para este Tipo de Configuracion
         * 
         * @return procesador de XML configurada para este tipo de configuracion
         */
        public static final XStream getXstream(TipoConfiguracionDTO plantillaRegistro) {
            XStream xstream = new XStream(new StaxDriver(new XmlFriendlyNameCoder("d-", "_")));
            xstream.processAnnotations(RegistroConfiguracionDTO.class);
            xstream.registerConverter(new MapCamposConfiguracionConverter(plantillaRegistro));
            xstream.alias(TagXmlRegistro.valor_configuracion_negocio.toString(), LinkedHashMap.class);
            return xstream;
        }

        @Override
        public boolean canConvert(Class clazz) {
            return LinkedHashMap.class.isAssignableFrom(clazz);
        }

        @Override
        @SuppressWarnings({ "unchecked" })
        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
            Map map = (Map) value;
            for (Entry entry : (Set<Entry>) map.entrySet()) {
                writer.startNode(TagXmlRegistro.campo.toString());
                {
                    writer.startNode(TagXmlRegistro.codigo.toString());
                    String codigo = entry.getKey().toString();
                    writer.setValue(codigo);
                    writer.endNode();
                    writer.startNode(TagXmlRegistro.valor.toString());
                    Object val = entry.getValue();
                    if (null != val) {
                        writer.setValue(marshalValorCampo(plantillaRegistro.getMapaCampos().get(codigo).getTipo(), val));
                    }
                    writer.endNode();
                }
                writer.endNode();
            }

        }

        @Override
        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();

            while (reader.hasMoreChildren()) {
                reader.moveDown(); // campo
                // codigo
                reader.moveDown();
                String codigo = reader.getValue();
                reader.moveUp();
                // valor
                reader.moveDown();
                String valor = reader.getValue();
                reader.moveUp();
                map.put(codigo, unmarshalValorCampo(plantillaRegistro.getMapaCampos().get(codigo).getTipo(), valor));

                reader.moveUp();
            }

            return map;
        }

    }

}
