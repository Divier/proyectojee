package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Clase que representa un rango de numeros asociados a un tipo de formulario
 * 
 * @author claudia.rodriguez
 * 
 */
public class RangoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private String numeroInicial;
    private String numeroFinal;

    public RangoDTO(String numeroInicial, String numeroFinal) {
        this.numeroFinal = numeroFinal;
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

}
