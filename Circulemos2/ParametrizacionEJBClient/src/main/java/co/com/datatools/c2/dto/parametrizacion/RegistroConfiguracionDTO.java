package co.com.datatools.c2.dto.parametrizacion;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.NULL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

/**
 * Representa un registro de una configuracion de negocio
 * 
 * @author Felipe.Martinez
 */
public class RegistroConfiguracionDTO implements Serializable {

    private static final long serialVersionUID = -8855188116525162956L;

    /**
     * Nombres de los tags usados para estructurar un registro como XML
     * 
     * @author Felipe.Martinez
     *
     */
    public enum TagXmlRegistro {
        valor_configuracion_negocio, //
        campo, //
        codigo, //
        valor//
        ;
    }

    /**
     * Formato de un xsd:date
     */
    public static final SimpleDateFormat FORMATO_XSD_FECHA = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Formato de un xsd:datetime
     */
    public static final SimpleDateFormat FORMATO_XSD_FECHAHORA = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Formato de un xsd:time
     */
    public static final SimpleDateFormat FORMATO_XSD_HORA = new SimpleDateFormat("HH:mm:ss");

    // --- Atributos
    private final Integer id;

    /**
     * Informacion de los campos de un registro como se encuentra almacenada en el xml<br>
     * K: codigo del campo <br>
     * V: valor concreto del campo, el tipo del objeto depende del tipo del campo {@link TipoCampoConfiguracion}
     * 
     * <ul>
     * <li>{@link Boolean}: BOOLEANO</li>
     * <li>{@link String}: CADENA, URL, CORREO</li>
     * <li>{@link BigDecimal}: DECIMAL, NUMERO</li>
     * <li>{@link Date}: FECHA, FECHAHORA, HORA</li>
     * 
     * <li>Map<String, List<String>>: CATALOGOCOMPUESTO</li>
     * <li>List<String>: CATALOGOSIMPLE, CATALOGOINDEPENDIENTE - Ids de cada registro seleccionado</li>
     * </ul>
     * Si el valor del campo es nulo (no asignado) se utiliza el objeto {@link ObjectUtils#NULL}
     */
    private LinkedHashMap<String, Object> campos;

    /**
     * Constructor que inicializa el id del registro y el tipo de configuracion al que pertenece
     * 
     * @param id
     *            identificador del registro en la base de datos, null cuando el objeto es usado para un nuevo registro
     */
    private RegistroConfiguracionDTO(Integer id) {
        this.id = id;
    }

    public RegistroConfiguracionDTO(Integer id, LinkedHashMap<String, Object> campos) {
        this(id);
        this.campos = campos;
    }

    public Integer getId() {
        return id;
    }

    public LinkedHashMap<String, Object> getCampos() {
        return campos;
    }

    /**
     * Permite asignar un valor a un campo del registro
     * 
     * @param codigoCampo
     *            codigo del campo dentro del registro
     * @param valor
     *            valor del campo, si es null, es convertido por {@link ObjectUtils#NULL}
     * @throws CirculemosRuntimeException
     *             error en caso de que se intente asignar valor a un campo que no existe dentro del registro
     */
    public void setValorCampo(String codigoCampo, Object valor) throws CirculemosRuntimeException {
        if (valor == null) {
            valor = NULL;
        }
        if (campos.containsKey(codigoCampo)) {
            campos.put(codigoCampo, valor);
        } else {
            throw new CirculemosRuntimeException("El campo indicado \"{0}\" no existe en el registro", codigoCampo);
        }
    }

    /**
     * Consulta el valor para el campo solicitado
     * 
     * @param codigoCampo
     *            codigo del campo a buscar
     * @return objeto almacenado para el codigoCampo, null si no existe el campo
     */
    public Object getValorCampo(String codigoCampo) {
        return campos.get(codigoCampo);
    }

    /**
     * Sobre carga de metodo para facilitar la consulta cuando se tiene todo el campo
     * 
     * @param campo
     *            dto de donde obtener el codigo del campo a buscar
     * @return objeto almacenado para el codigoCampo, null si no existe el campo
     */
    public Object getValorCampo(CampoConfiguracionDTO campo) {
        checkNotNull(campo.getCodigo(), "El codigo del campo es requerido");
        return getValorCampo(campo.getCodigo());
    }

    /**
     * Si existe el campo dentro del registro, garantiza que se inicialice el mapa que mantiene los valores de los catalogos compuestos
     * 
     * @param campo
     *            identificador del campo, se valida que sea de tipo {@link TipoCampoConfiguracion#CATALOGOCOMPUESTO}
     * @return null si el campo no es de {@link TipoCampoConfiguracion#CATALOGOCOMPUESTO}, de lo contrario el mapa para mantener la referencia de los
     *         valores de un campo de catalogo compuesto
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<String>> getValorCampoCompuesto(CampoConfiguracionDTO campo) {
        if (campo.getTipo().equals(TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
            Object valorCampo = getValorCampo(campo);
            if (valorCampo == null || NULL.equals(valorCampo) || !(valorCampo instanceof Map)) {
                valorCampo = new HashMap<String, List<String>>(2);
                setValorCampo(campo.getCodigo(), valorCampo);
            }
            return (Map<String, List<String>>) valorCampo;
        } else {
            return null;
        }
    }

    /**
     * Permite construir un registro con la estructura de la planilla a la que pertenece
     * 
     * @param plantillaRegistro
     *            definicion de los campos que debe llevar el registro
     * @return registro con todos los campos definidos en plantillaRegistro, cada uno asignado al valor {@link ObjectUtils#NULL}
     */
    public static RegistroConfiguracionDTO construirRegistroVacio(TipoConfiguracionDTO plantillaRegistro) {
        RegistroConfiguracionDTO registro = new RegistroConfiguracionDTO(null);
        registro.campos = new LinkedHashMap<>(plantillaRegistro.getMapaCampos().size());
        for (CampoConfiguracionDTO campo : plantillaRegistro.listaCampos()) {
            if (campo.getTipo().equals(TipoCampoConfiguracion.CATALOGOCOMPUESTO))
                registro.campos.put(campo.getCodigo(), new HashMap<>(3));
            else
                registro.campos.put(campo.getCodigo(), NULL);

        }
        return registro;
    }

    @Override
    public String toString() {
        return "{id=" + id + " " + campos + "}";
    }
}
