package co.com.datatools.sogit.utilies;

public final class ConstantesManagedBean {

    private ConstantesManagedBean() {
    }

    /**
     * Clase del objeto en sesion que contiene la fachada de acceso a los catalogos de aplicacion
     */
    public static final Class<FachadaCatalogosMB> CLASE_OBJ_FACHADA_CATALOGOS = FachadaCatalogosMB.class;

    /**
     * Nombre del objeto en sesion que contiene la fachada de acceso a los catalogos de aplicacion
     */
    public static final String NOMBRE_FACHADA_CATALOGOS = "fachadaCatalogosMB";
}
