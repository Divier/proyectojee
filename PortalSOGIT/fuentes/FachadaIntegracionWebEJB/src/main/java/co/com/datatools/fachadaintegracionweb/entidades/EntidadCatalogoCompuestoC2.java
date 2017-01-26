package co.com.datatools.fachadaintegracionweb.entidades;

/**
 * Interfaz para categorizar las clases que son entidades JPA con la estructura de catalogo compuesto
 * 
 * @author giovanni.velandia
 */
public interface EntidadCatalogoCompuestoC2 extends EntidadCatalogoC2 {

    public abstract EntidadCatalogoC2 getDependencia();

    public abstract void setDependencia(EntidadCatalogoC2 entidadCatalogoC2);
}
