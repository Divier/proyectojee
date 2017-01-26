package co.com.datatools.util.file.archivo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigo.cruz
 * 
 */
public class FilaArchivoExcelDTO extends FilaArchivoDTO {

    private int hoja;
    private List<Object> celdas;

    public FilaArchivoExcelDTO() {
        super();
        hoja = 0;
        celdas = new ArrayList<>();
    }

    public int getHoja() {
        return hoja;
    }

    public void setHoja(int hoja) {
        this.hoja = hoja;
    }

    public boolean isEncabezado() {
        return encabezado;
    }

    public void setEncabezado(boolean encabezado) {
        this.encabezado = encabezado;
    }

    public List<Object> getCeldas() {
        return celdas;
    }

    public void setCeldas(List<Object> celdas) {
        this.celdas = celdas;
    }

}
