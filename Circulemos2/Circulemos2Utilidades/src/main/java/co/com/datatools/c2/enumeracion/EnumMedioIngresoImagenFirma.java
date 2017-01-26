package co.com.datatools.c2.enumeracion;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeraci�n que contempla los medios de ingreso de las im�genes de las firmas de los funcionarios. Su contenido debe corresponder con la tabla
 * medio_ingreso_firma
 * 
 * @author robert.bautista
 * @since 2013-11-06
 */
public enum EnumMedioIngresoImagenFirma {

    LAPIZ_OPTICO("L�piz �ptico", 1), //
    IMAGEN_LOCAL("Imagen Local", 2);

    /**
     * Contiene el nombre del medio
     */
    private String nombre;

    /**
     * Contiene el c�digo del medio de ingreso
     */
    private int codigo;

    /**
     * Construye la enumeraci�n con el c�digo indicado
     * 
     * @param codigo
     *            c�digo asociado a la enumeraci�n
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
     * Retorna una lista con los medios de ingreso de las im�genes de las firmas, ordenados por c�digo.
     * 
     * @return List<EnumMedioIngresoImagenFirma> ordenada por c�digo
     */
    public static List<EnumMedioIngresoImagenFirma> getMediosIngresoOrdenados() {
        List<EnumMedioIngresoImagenFirma> lista = new ArrayList<EnumMedioIngresoImagenFirma>();

        lista.add(LAPIZ_OPTICO);
        lista.add(IMAGEN_LOCAL);
        return lista;
    }
}
