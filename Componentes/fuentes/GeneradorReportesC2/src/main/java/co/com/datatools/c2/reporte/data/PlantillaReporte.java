package co.com.datatools.c2.reporte.data;

import java.io.Serializable;

/**
 * Objeto que modela el contenido de una plantilla de reporte
 * 
 * @author felipe.martinez
 * 
 */
public class PlantillaReporte implements Serializable {

    private static final long serialVersionUID = 8231641759598695327L;

    /**
     * Titulo del reporte
     */
    private String titulo;

    /**
     * Contenido del encabezado para el reporte
     */
    private Encabezado encabezado;

    /**
     * Cabeceras del contenido del reporte (Titulos de columnas)
     */
    private CabeceraReporte cabecera;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Encabezado getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Encabezado encabezado) {
        this.encabezado = encabezado;
    }

    public CabeceraReporte getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraReporte cabecera) {
        this.cabecera = cabecera;
    }

}
