package co.com.datatools.c2.dto.formularios;

public class RangoDisponibleDTO extends RangoFormularioDTO {

    private static final long serialVersionUID = 1L;

    private Integer cantidadIngresada;

    // --- Constructor
    public RangoDisponibleDTO() {
    }

    public Integer getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Integer cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

}
