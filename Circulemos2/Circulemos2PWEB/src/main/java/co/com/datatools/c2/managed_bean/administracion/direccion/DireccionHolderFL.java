package co.com.datatools.c2.managed_bean.administracion.direccion;

import java.io.Serializable;

import co.com.datatools.c2.enumeracion.EnumTipoDireccion;

public class DireccionHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DIR_HOLDER_FL = "dirHolderFL";

    private boolean popup;
    private boolean validaTipoDireccion;
    private boolean validaDireccion;
    private boolean seleccionaPais;
    private Boolean tipoViaPriNumerico;
    private Boolean tipoViaSecNumerico;
    private EnumTipoDireccion tipoDireccion;

    public DireccionHolderFL() {
        popup = false;
        validaTipoDireccion = false;
        validaDireccion = false;
        seleccionaPais = false;
        tipoDireccion = EnumTipoDireccion.VALIDADO;
    }

    public void limpiarDatos() {
        tipoViaPriNumerico = null;
        tipoViaSecNumerico = null;
    }

    public boolean isPopup() {
        return popup;
    }

    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    public EnumTipoDireccion getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(EnumTipoDireccion tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public boolean isValidaTipoDireccion() {
        return validaTipoDireccion;
    }

    public void setValidaTipoDireccion(boolean validaTipoDireccion) {
        this.validaTipoDireccion = validaTipoDireccion;
    }

    public boolean isValidaDireccion() {
        return validaDireccion;
    }

    public void setValidaDireccion(boolean validaDireccion) {
        this.validaDireccion = validaDireccion;
    }

    public Boolean getTipoViaPriNumerico() {
        return tipoViaPriNumerico;
    }

    public void setTipoViaPriNumerico(Boolean tipoViaPriNumerico) {
        this.tipoViaPriNumerico = tipoViaPriNumerico;
    }

    public Boolean getTipoViaSecNumerico() {
        return tipoViaSecNumerico;
    }

    public void setTipoViaSecNumerico(Boolean tipoViaSecNumerico) {
        this.tipoViaSecNumerico = tipoViaSecNumerico;
    }

    public boolean isSeleccionaPais() {
        return seleccionaPais;
    }

    public void setSeleccionaPais(boolean seleccionaPais) {
        this.seleccionaPais = seleccionaPais;
    }

}
