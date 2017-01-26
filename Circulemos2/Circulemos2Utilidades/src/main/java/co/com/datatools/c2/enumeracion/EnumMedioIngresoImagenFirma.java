package co.com.datatools.c2.enumeracion;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeración que contempla los medios de ingreso de las imágenes de las firmas de los funcionarios. Su contenido debe corresponder con la tabla
 * medio_ingreso_firma
 * 
 * @author robert.bautista
 * @since 2013-11-06
 */
public enum EnumMedioIngresoImagenFirma {

    LAPIZ_OPTICO("Lápiz Óptico", 1), //
    IMAGEN_LOCAL("Imagen Local", 2);

    /**
     * Contiene el nombre del medio
     */
    private String nombre;

    /**
     * Contiene el código del medio de ingreso
     */
    private int codigo;

    /**
     * Construye la enumeración con el código indicado
     * 
     * @param codigo
     *            código asociado a la enumeración
     */
    private EnumMedioIngresoImagenFirma(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna una lista con los medios de ingreso de las imágenes de las firmas, ordenados por código.
     * 
     * @return List<EnumMedioIngresoImagenFirma> ordenada por código
     */
    public static List<EnumMedioIngresoImagenFirma> getMediosIngresoOrdenados() {
        List<EnumMedioIngresoImagenFirma> lista = new ArrayList<EnumMedioIngresoImagenFirma>();

        lista.add(LAPIZ_OPTICO);
        lista.add(IMAGEN_LOCAL);
        return lista;
    }
}
