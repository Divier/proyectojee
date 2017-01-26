package co.com.datatools.c2.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase para generar los DTO basados en las Entidades
 * 
 * @author felipe.martinez
 */
public class DtoGenerator {
    // -- Constantes
    private static final String PAQUETE_ENTIDADES = "co.com.datatools.c2.entidades";
    private static final String PAQUETE_DTO = "co.com.datatools.c2.dto";
    private static final String CLASES_INTERNAS = "co.com.datatools";
    private static final String MARK_GET_SET = "{class_get-set}";
    private static final String MARK_CONSTRUCTOR = "{class_constructor}";
    private static final String MARK_CAMPOS = "{class_fields}";
    private static final String MARK_CLASE = "{class_name}";
    private static final String MARK_PAQUETE = "{pkg_name}";
    private static final String POSTFIJO_DTO = "DTO";

    // -- Buffers con el codigo generado
    private final StringBuilder tmptClase = new StringBuilder(5000);
    private final StringBuilder tmptGet = new StringBuilder(200);
    private final StringBuilder tmptGetList = new StringBuilder(200);
    private final StringBuilder tmptSet = new StringBuilder(200);
    private final StringBuilder tmptConstrDefecto = new StringBuilder(200);
    private final StringBuilder tmptConstrId = new StringBuilder(200);

    private final StringBuilder campos = new StringBuilder(50);
    private final StringBuilder constructores = new StringBuilder(50);
    private final StringBuilder getSet = new StringBuilder(50);

    private List<Entry> idsEntidad = new ArrayList<>(0);

    private String nombreClase;
    private String nombrePaquete;

    /**
     * Inicializa los templates para el dto a generar
     * 
     * @param nombreClase
     *            nombre final del Helper a crear
     */
    private DtoGenerator(String nombrePaquete, String nombreClase) {
        this.nombreClase = nombreClase;
        this.nombrePaquete = nombrePaquete;
        initTemplate();
    }

    /**
     * Crea DTO apartir de entidades
     * 
     * @param args
     *            <ul>
     *            <li>args[0] = nombre del proyecto destino donde se crean los dto</li>
     *            <li>args[1] = paquete donde se crearan los DTO generados dentro del proyecto: co.com.datatools.c2.dto</li>
     *            <li>args[2] = paquete donde se encuentra la/s entidades: co.com.datatools.c2.entidades</li>
     *            <li>args[N] = nombres de n entidades que se encuentran dentro del paquete de entidades</li>
     *            </ul>
     */
    public static void main(String[] args) {
        String paqueteOrigen;
        String proyectoDestino;
        String paqueteDestino;
        String nombreEntidad;

        if (args.length >= 4) {
            paqueteOrigen = args[2];
            proyectoDestino = args[0];
            paqueteDestino = args[1];

            for (int i = 3; i < args.length; i++) {
                nombreEntidad = args[i];
                try {
                    crearClaseDto(nombreEntidad, paqueteOrigen, proyectoDestino, paqueteDestino);
                    HelperGenerator.crearHelper(nombreEntidad, paqueteOrigen, proyectoDestino, paqueteDestino);
                    nombreEntidad = null;
                } catch (ClassNotFoundException | IOException e) {
                    System.err.println("Error con la clase " + nombreEntidad + " " + e.getMessage());
                }
            }
        } else {
            while (true) {
                nombreEntidad = JOptionPane.showInputDialog("Ingrese el nombre de la entidad que desea generar");
                paqueteOrigen = JOptionPane.showInputDialog("Paquete entidad", PAQUETE_ENTIDADES);
                proyectoDestino = JOptionPane.showInputDialog("Nombre proyecto destino");
                paqueteDestino = JOptionPane.showInputDialog("Nombre paquete destino", PAQUETE_DTO);
                String[] entidades = nombreEntidad.split(",");
                for (String entidad : entidades) {
                    try {
                        crearClaseDto(entidad, paqueteOrigen, proyectoDestino, paqueteDestino);
                        JOptionPane.showMessageDialog(null, "El DTO fue creado con exito: " + entidad, "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "La entidad no existe: " + entidad, "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error escritura/lectura: " + entidad,
                                "Error escritura/lectura", JOptionPane.ERROR_MESSAGE);
                    }
                }
                int opcion = JOptionPane.showConfirmDialog(null, "Desea generar otro DTO?");
                if (opcion > 0) {
                    break;
                }
            }
        }
    }

    public static void crearClaseDto(String nombreClase, String paqueteOrigen, String proyectoDestino,
            String paqueteDestino) throws IOException, ClassNotFoundException {
        final Class<?> entidad = Class.forName(paqueteOrigen + "." + nombreClase);
        String workingDir = System.getProperty("user.dir");
        workingDir = workingDir.replace("Circulemos2Test", proyectoDestino);
        String rutaPaqueteDestino = paqueteDestino.replace(".", "/");
        File myFile = new File(
                workingDir + "/src/main/java/" + rutaPaqueteDestino + "/" + nombreClase + POSTFIJO_DTO + ".java");
        myFile.createNewFile();
        PrintWriter pw = new PrintWriter(myFile);

        final DtoGenerator dtoGenerator = new DtoGenerator(paqueteDestino, nombreClase + POSTFIJO_DTO);
        dtoGenerator.procesarAtributos(entidad);

        final String classSource = dtoGenerator.contenidoClase();
        pw.write(classSource);
        pw.close();
        System.out.println("Archivo Creado: " + myFile.getAbsolutePath());
    }

    /**
     * Parsea la plantilla de helper con el contenido especifico para la entidad procesada
     * 
     * @return contenido de la clase final
     */
    private String contenidoClase() {
        return tmptClase.toString().replace(MARK_PAQUETE, nombrePaquete).replace(MARK_CLASE, nombreClase)
                .replace(MARK_CAMPOS, campos.toString()).replace(MARK_CONSTRUCTOR, constructores.toString())
                .replace(MARK_GET_SET, getSet.toString());
    }

    private void procesarAtributos(Class<?> claseOrigen) {
        final List<Field> fields = Arrays.asList(claseOrigen.getDeclaredFields());
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                crearCampo(field);
            }
        }
        constructores.append(crearConstructores());
    }

    /**
     * Procesa uno de los campos de la clase base de la q se creara el DTO
     * 
     * @param field
     *            atributo a procesar
     */
    private void crearCampo(Field field) {
        StringBuilder generic = new StringBuilder(0);
        try {
            final Type type = field.getGenericType();
            ParameterizedType genericType = (ParameterizedType) type;
            final Type[] actualTypeArguments = genericType.getActualTypeArguments();
            for (Type tipoGenerico : actualTypeArguments) {
                generic.append(determinarTipo((Class<?>) tipoGenerico) + ",");
            }
            generic.insert(0, '<').append(">").deleteCharAt(generic.lastIndexOf(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String tipoFinal = determinarTipo(field.getType()) + generic;
        String nombreFinal = field.getName();
        campos.append("private " + tipoFinal + " " + nombreFinal + ";\n");
        if (field.getType().getName().startsWith("java.util.List")) {
            getSet.append(crearGetList(tipoFinal, nombreFinal));
        } else {
            getSet.append(crearGet(tipoFinal, nombreFinal));
        }
        getSet.append(crearSet(tipoFinal, nombreFinal));

        if (field.getAnnotation(javax.persistence.Id.class) != null) {
            idsEntidad.add(new Entry(nombreFinal, tipoFinal));
        }
    }

    private String determinarTipo(Class<?> tipo) {
        if (tipo.getName().startsWith(CLASES_INTERNAS)) {
            return tipo.getSimpleName() + POSTFIJO_DTO;
        }
        return tipo.getSimpleName();
    }

    private String crearGet(String tipoFinal, String nombreFinal) {
        return tmptGet.toString().replace("{retorno}", tipoFinal)
                .replace("{cNombre}", StringUtils.capitalize(nombreFinal)).replace("{nombre}", nombreFinal);
    }

    private String crearGetList(String tipoFinal, String nombreFinal) {
        return StringUtils.replace(tmptGetList.toString().replace("{retorno}", tipoFinal).replace("{cNombre}",
                StringUtils.capitalize(nombreFinal)), "{nombre}", nombreFinal);
    }

    private String crearSet(String tipoFinal, String nombreFinal) {
        return StringUtils.replace(tmptSet.toString().replace("{tipo}", tipoFinal).replace("{cNombre}",
                StringUtils.capitalize(nombreFinal)), "{nombre}", nombreFinal);
    }

    private String crearConstructores() {
        StringBuilder constructorId = new StringBuilder(0);
        if (!idsEntidad.isEmpty()) {
            StringBuilder asignaciones = new StringBuilder(0);
            StringBuilder parametros = new StringBuilder(0);
            for (Entry campo : idsEntidad) {
                parametros.append(campo.getValue() + " " + campo.getKey() + ",");
                asignaciones.append("this." + campo.getKey() + "=" + campo.getKey() + ";\n");
            }
            parametros.setCharAt(parametros.length() - 1, ' ');
            constructorId = new StringBuilder(
                    StringUtils.replace(tmptConstrId.toString(), "{parametros}", parametros.toString())
                            .replace(MARK_CLASE, this.nombreClase).replace("{asignacion}", asignaciones.toString()));
        }

        return StringUtils.replace(tmptConstrDefecto.toString(), MARK_CLASE, nombreClase) + constructorId.toString();
    }

    private void appendLn(StringBuilder builder, String line) {
        builder.append(line + "\n");
    }

    /**
     * Inicializa los templates para las diferentes secciones del dto
     */
    private void initTemplate() {
        appendLn(tmptClase, "package " + MARK_PAQUETE + ";");

        appendLn(tmptClase, "import java.util.List;");
        appendLn(tmptClase, "import java.util.Date;");
        appendLn(tmptClase, "import java.math.*;");
        appendLn(tmptClase, "import co.com.datatools.c2.dto.*;");
        appendLn(tmptClase, "import co.com.datatools.c2.util.EntidadDtoC2;");

        appendLn(tmptClase, "/**");
        appendLn(tmptClase, " * @author Generated");
        appendLn(tmptClase, " * @version 3.0 - " + new Date(System.currentTimeMillis()) + "");
        appendLn(tmptClase, " */");

        appendLn(tmptClase, "public class " + MARK_CLASE + " implements EntidadDtoC2 { ");
        appendLn(tmptClase, "  private static final long serialVersionUID = 1L;");

        appendLn(tmptClase, "// --- Atributos");
        appendLn(tmptClase, MARK_CAMPOS);
        appendLn(tmptClase, "// --- Constructor");
        appendLn(tmptClase, MARK_CONSTRUCTOR);
        appendLn(tmptClase, "// --- Getters-Setters");
        appendLn(tmptClase, MARK_GET_SET);
        appendLn(tmptClase, "}");

        appendLn(tmptGet, "public {retorno} get{cNombre} () {");
        appendLn(tmptGet, "  return this.{nombre};");
        appendLn(tmptGet, "}\n");

        appendLn(tmptGetList, "/**");
        appendLn(tmptGetList, " * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna");
        appendLn(tmptGetList, " */");
        appendLn(tmptGetList, "public {retorno} get{cNombre} () {");
        appendLn(tmptGetList, "  if(this.{nombre}==null){");
        appendLn(tmptGetList, "    this.{nombre}=new java.util.ArrayList<>(1);");
        appendLn(tmptGetList, "  }");
        appendLn(tmptGetList, "  return this.{nombre};");
        appendLn(tmptGetList, "}\n");

        appendLn(tmptSet, "public void set{cNombre} ({tipo} {nombre}) {");
        appendLn(tmptSet, "  this.{nombre}={nombre};");
        appendLn(tmptSet, "}\n");

        appendLn(tmptConstrDefecto, "public " + MARK_CLASE + " () {");
        appendLn(tmptConstrDefecto, "}\n");

        appendLn(tmptConstrId, "public " + MARK_CLASE + " ({parametros}) {");
        appendLn(tmptConstrId, "  {asignacion}");
        appendLn(tmptConstrId, "}\n");
    }

    static class Entry implements java.util.Map.Entry<String, String> {

        String key;
        String value;

        public Entry(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            String temp = this.value;
            this.value = value;
            return temp;
        }

    }
}
