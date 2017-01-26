package co.com.datatools.c2.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase para generar los helper automaticos de conversion entre entidad y dto
 * 
 * @author felipe.martinez
 */
public class HelperGenerator {

    // -- Constantes
    private static final String PAQUETE_ENTIDADES = "co.com.datatools.c2.entidades";
    private static final String PAQUETE_HELPER = "co.com.datatools.c2.negocio.helpers";
    private static final String _PKG = "{pkg_name}";
    private static final String _CLASS = "{class_name}";
    private static final String MARK_Level0DTO = "{toLevel0DTO}";
    private static final String MARK_Level0Entity = "{toLevel0Entity}";
    private static final String MARK_Level1DTO = "{toLevel1DTO}";
    private static final String MARK_Level1Entity = "{toLevel1Entity}";
    private static final String POSTFIJO_HELPER = "Helper";

    // -- Buffers con el codigo generado
    private final StringBuilder base = new StringBuilder(5000);
    private final StringBuilder lv0Dto = new StringBuilder(100);
    private final StringBuilder lv0Ent = new StringBuilder(100);
    private final StringBuilder lv1Dto = new StringBuilder(100);
    private final StringBuilder lv1Ent = new StringBuilder(100);

    private String nombreClase;
    private String nombrePaquete;

    /**
     * Inicializa el template de un helper generado
     * 
     * @param nombreClase
     *            nombre del entidad base a leer
     */
    private HelperGenerator(String nombrePaquete, String nombreClase) {
        this.nombreClase = nombreClase;
        this.nombrePaquete = nombrePaquete;
        initTemplate();
    }

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

                String[] entidades = nombreEntidad.split(",");
                for (String entidad : entidades) {
                    try {
                        crearHelper(entidad, paqueteOrigen, proyectoDestino, paqueteDestino);
                        nombreEntidad = null;
                    } catch (ClassNotFoundException | IOException e) {
                        System.err.println("Error con la clase " + entidad + " " + e.getMessage());
                    }
                }
            }
        } else {
            while (true) {
                nombreEntidad = JOptionPane.showInputDialog("Ingrese el nombre del Helper que desea generar");
                paqueteOrigen = JOptionPane.showInputDialog("Paquete entidad", PAQUETE_ENTIDADES);
                proyectoDestino = JOptionPane.showInputDialog("Nombre proyecto destino");
                paqueteDestino = JOptionPane.showInputDialog("Nombre paquete destino", PAQUETE_HELPER);

                String[] entidades = nombreEntidad.split(",");
                for (String entidad : entidades) {
                    try {
                        crearHelper(entidad, paqueteOrigen, proyectoDestino, paqueteDestino);
                        JOptionPane.showMessageDialog(null, "La helper fue creado con exito: " + entidad, "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "La entidad base no existe: " + entidad, "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error escritura/lectura: " + entidad,
                                "Error escritura/lectura", JOptionPane.ERROR_MESSAGE);
                    }
                }
                int opcion = JOptionPane.showConfirmDialog(null, "Desea generar otro Helper?");
                if (opcion > 0) {
                    break;
                }
            }
        }
    }

    /**
     * Genera un helper basado en la entidad que recibe por parametro
     * 
     * @param nombreClase
     *            nombre simple de la entidad sobre la q se genera el helper
     */
    public static void crearHelper(String nombreClase, String paqueteOrigen, String proyectoDestino,
            String paqueteDestino) throws IOException, ClassNotFoundException {
        final Class<?> entidad = Class.forName(paqueteOrigen + "." + nombreClase);
        String workingDir = System.getProperty("user.dir");
        workingDir = workingDir.replace("Circulemos2Test", proyectoDestino);
        String rutaPaqueteDestino = paqueteDestino.replace(".", "/");
        File myFile = new File(
                workingDir + "/src/main/java/" + rutaPaqueteDestino + "/" + nombreClase + POSTFIJO_HELPER + ".java");
        myFile.createNewFile();
        PrintWriter pw = new PrintWriter(myFile);

        final HelperGenerator helperGenerator = new HelperGenerator(paqueteDestino, nombreClase);
        helperGenerator.procesarAtributos(entidad);

        final String classSource = helperGenerator.contenidoClase();
        pw.write(classSource);
        pw.close();
        System.out.println("Archivo Creado: " + myFile.getAbsolutePath());
    }

    /**
     * Procesa cada uno de los atributos de la entidad para generar el helper
     * 
     * @param claseOrigen
     *            clase de la entidad
     */
    private void procesarAtributos(Class<?> claseOrigen) {
        final List<Field> camposOrigen = Arrays.asList(claseOrigen.getDeclaredFields());
        for (Field campo : camposOrigen) {
            if (!Modifier.isStatic(campo.getModifiers())) {
                String capNombre = StringUtils.capitalize(campo.getName());
                String tipoCampo = campo.getType().getSimpleName();

                if (!isCollection(campo) && isLevel0(campo)) {
                    appendLn(lv0Dto, "dto.set" + capNombre + "(entidad.get" + capNombre + "());");
                    appendLn(lv0Ent, "entidad.set" + capNombre + "(dto.get" + capNombre + "());");
                } else if (!isCollection(campo)) {
                    String nombreIdRelacion = "";
                    final List<Field> camposRelacion = Arrays.asList(campo.getType().getDeclaredFields());
                    for (Field campoRelacion : camposRelacion) {
                        if (campoRelacion.getAnnotation(javax.persistence.Id.class) != null) {
                            nombreIdRelacion = StringUtils.capitalize(campoRelacion.getName());
                            break;
                        }
                    }

                    appendLn(lv1Dto, "if(entidad.get" + capNombre + "() != null)");
                    appendLn(lv1Dto, "  dto.set" + capNombre + "(" + tipoCampo + "Helper.toLevel0DTO(entidad.get"
                            + capNombre + "()));");
                    // --
                    appendLn(lv1Ent, "if(dto.get" + capNombre + "() != null){");
                    appendLn(lv1Ent, " entidad.set" + capNombre + "(new " + tipoCampo + "());");

                    appendLn(lv1Ent, " entidad.get" + capNombre + "().set" + nombreIdRelacion + "(dto.get" + capNombre
                            + "().get" + nombreIdRelacion + "());");
                    appendLn(lv1Ent, "}");

                }
            }
        }
    }

    private boolean isLevel0(Field field) {
        return field.getType().getName().startsWith("java") || field.getType().equals(byte[].class);
    }

    private boolean isCollection(Field field) {
        return field.getType().equals(List.class);
    }

    /**
     * Parsea la plantilla de helper con el contenido especifico para la entidad procesada
     * 
     * @return contenido de la clase final
     */
    private String contenidoClase() {
        return base.toString().replace(_PKG, nombrePaquete).replace(_CLASS, nombreClase)
                .replace(MARK_Level0DTO, lv0Dto.toString()).replace(MARK_Level1DTO, lv1Dto.toString())
                .replace(MARK_Level0Entity, lv0Ent.toString()).replace(MARK_Level1Entity, lv1Ent.toString());
    }

    private void appendLn(StringBuilder builder, String line) {
        builder.append(line + "\n");
    }

    /**
     * Inicializa el template basico de cualquier Helper
     */
    private void initTemplate() {
        appBase("package " + _PKG + ";\n");

        appBase("import java.util.List;");
        appBase("import java.util.ArrayList;");
        appBase("import java.util.Date;");
        appBase("import co.com.datatools.c2.dto.*;");
        appBase("import co.com.datatools.c2.entidades.*;");
        appBase("import java.math.*;");

        appBase("/**");
        appBase(" * @author Generated");
        appBase(" * @version 3.0 - " + new Date(System.currentTimeMillis()));
        appBase(" */");

        appBase("public class " + _CLASS + "Helper { ");

        appBase("  // --- to DTO");
        appBase("  public static " + _CLASS + "DTO toLevel0DTO(" + _CLASS + " entidad) {");
        appBase("      " + _CLASS + "DTO dto = new " + _CLASS + "DTO();");
        appBase("      " + MARK_Level0DTO);
        appBase("      return dto;");
        appBase("  }");

        appBase("  public static " + _CLASS + "DTO toLevel1DTO(" + _CLASS + " entidad) {");
        appBase("      " + _CLASS + "DTO dto = toLevel0DTO(entidad);");
        appBase("      " + MARK_Level1DTO);
        appBase("      return dto;");
        appBase("  }");

        appBase("  public static List<" + _CLASS + "DTO> toListLevel0DTO(List<" + _CLASS + "> listEntidad) {");
        appBase("      List<" + _CLASS + "DTO> listDto = new ArrayList<" + _CLASS + "DTO>();");
        appBase("      for (" + _CLASS + " entidad : listEntidad) {");
        appBase("          listDto.add(toLevel0DTO(entidad));");
        appBase("      }");
        appBase("      return listDto;");
        appBase("  }");

        appBase("  public static List<" + _CLASS + "DTO> toListLevel1DTO(List<" + _CLASS + "> listEntidad) {");
        appBase("      List<" + _CLASS + "DTO> listDto = new ArrayList<" + _CLASS + "DTO>();");
        appBase("      for (" + _CLASS + " entidad : listEntidad) {");
        appBase("          listDto.add(toLevel1DTO(entidad));");
        appBase("      }");
        appBase("      return listDto;");
        appBase("  }");

        appBase("  // --- fin to DTO");

        appBase("  // --- to Entidad");
        appBase("  public static " + _CLASS + " toLevel0Entity(" + _CLASS + "DTO dto, " + _CLASS + " entidad) {");
        appBase("      if (null == entidad) {");
        appBase("          entidad = new " + _CLASS + "();");
        appBase("      }");
        appBase("      " + MARK_Level0Entity);
        appBase("      return entidad;");
        appBase("  }");

        appBase("  public static " + _CLASS + " toLevel1Entity(" + _CLASS + "DTO dto, " + _CLASS + " entidad) {");
        appBase("      entidad = toLevel0Entity(dto, entidad);");
        appBase("      " + MARK_Level1Entity);
        appBase("      return entidad;");
        appBase("  }");

        appBase("  public static List<" + _CLASS + "> toListLevel0Entity(List<" + _CLASS + "DTO> listDto) {");
        appBase("      List<" + _CLASS + "> listEntidad = new ArrayList<" + _CLASS + ">();");
        appBase("      for (" + _CLASS + "DTO dto : listDto) {");
        appBase("          listEntidad.add(toLevel0Entity(dto, null));");
        appBase("      }");
        appBase("      return listEntidad;");
        appBase("  }");

        appBase("  public static List<" + _CLASS + "> toListLevel1Entity(List<" + _CLASS + "DTO> listDto) {");
        appBase("      List<" + _CLASS + "> listEntidad = new ArrayList<" + _CLASS + ">();");
        appBase("      for (" + _CLASS + "DTO dto : listDto) {");
        appBase("          listEntidad.add(toLevel1Entity(dto, null));");
        appBase("      }");
        appBase("      return listEntidad;");
        appBase("  }");

        appBase("  // --- fin to Entidad");

        appBase("}");
    }

    private void appBase(String line) {
        base.append(line + "\n");
    }
}
