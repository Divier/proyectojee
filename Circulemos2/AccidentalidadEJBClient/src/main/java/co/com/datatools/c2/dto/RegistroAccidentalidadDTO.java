package co.com.datatools.c2.dto;

import java.io.Serializable;

public class RegistroAccidentalidadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreTab;
    private int idTab;
    private boolean conductor;
    private boolean pasajero;

    private DetalleAccidentalidadDTO detalleAccidentalidadDTO;

    public String getNombreTab() {
        return nombreTab;
    }

    public void setNombreTab(String nombreTab) {
        this.nombreTab = nombreTab;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public boolean isConductor() {
        return conductor;
    }

    public void setConductor(boolean conductor) {
        this.conductor = conductor;
    }

    public boolean isPasajero() {
        return pasajero;
    }

    public void setPasajero(boolean pasajero) {
        this.pasajero = pasajero;
    }

    public DetalleAccidentalidadDTO getDetalleAccidentalidadDTO() {
        return detalleAccidentalidadDTO;
    }

    public void setDetalleAccidentalidadDTO(DetalleAccidentalidadDTO detalleAccidentalidadDTO) {
        this.detalleAccidentalidadDTO = detalleAccidentalidadDTO;
    }

}