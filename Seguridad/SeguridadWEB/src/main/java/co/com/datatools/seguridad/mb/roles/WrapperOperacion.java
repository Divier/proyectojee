package co.com.datatools.seguridad.mb.roles;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;

public class WrapperOperacion extends OperacionDto {

    private static final long serialVersionUID = 1L;

    /**
     * Prefijo del label de la operacion, utilizado para aplicar estilo a la operacion desplegada en el rol
     */
    private String pre;

    /**
     * Posfijo del label de la operacion, utilizado para aplicar estilo a la operacion desplegada en el rol
     */
    private String pos;

    public WrapperOperacion(OperacionDto base) {
        super(base.getIdOperacion(), base.getNombreOperacion());
        pre = "";
        pos = "";
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void resetearEstilo() {
        setPre("");
        setPos("");
    }

    public void aplicarEstiloResaltar() {
        setPre("<span class=\"seleccion-padre\">");
        setPos("</span>");
    }
}