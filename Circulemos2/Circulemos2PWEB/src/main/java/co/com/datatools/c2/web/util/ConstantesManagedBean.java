package co.com.datatools.c2.web.util;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;

public final class ConstantesManagedBean {

    private ConstantesManagedBean() {
    }

    /**
     * Nombre del objeto en sesion q contiene la informacion del organismo de transito
     */
    public static final String NOMBRE_OBJ_ORGANISMO = "organismoTransito";

    /**
     * Clase del objeto en sesion q contiene la informacion del organismo de transito
     */
    public static final Class<OrganismoTransitoDTO> CLASE_OBJ_ORGANISMO = OrganismoTransitoDTO.class;

    /**
     * Clase del objeto en sesion que contiene informacion de los roles del usuario
     */
    public static final Class<UsuarioDetalleDto> CLASE_OBJ_DETALLE_USU = UsuarioDetalleDto.class;

    /**
     * Nombre del objeto en sesion que contiene la informacion del pais
     */
    public static final String NOMBRE_OBJ_PAIS = "pais";

    /**
     * Clase del objeto en sesion que contiene la informacion del pais
     */
    public static final Class<PaisDTO> CLASE_OBJ_PAIS = PaisDTO.class;

    /**
     * Nombre del objeto en sesion que contiene el login del usuario autenticado
     */
    public static final String NOMBRE_OBJ_LOGIN = "autLogin";

    /**
     * Nombre del objeto en sesion que contienen lso roles del ususario autenticado
     */
    public static final String NOMBRE_OBJ_DETALLE_USU = "detalleUsuario";

    /**
     * Clase del objeto en sesion que contiene el login del usuario autenticado
     */
    public static final Class<String> CLASE_OBJ_LOGIN = String.class;

    /**
     * Nombre del atributo en la sesion en el cual se va a almacenar la ruta de la pagina de inicio de la aplicacion, a la cual se realiza el
     * direccionamiento cuando no se tiene una sesion valida
     */
    public static final String URL_INICIAL = "URL_INICIAL";

    /**
     * Nombre del parametro de contexto q define el identificador de aplicacion a usar en la logica de los metodos de seguridad
     */
    public static final String ID_APLICACION_PROP = "co.com.datatools.seguridad.ID_APLICACION";

    /**
     * Nombre del flujo para restablecer contraseña
     */
    public static final String PAGINA_RESTABLECER_PW = "publico/recuperacionPw.xhtml";

    /**
     * Nombre del flujo main
     */
    public static final String PAGINA_MAIN = "app/main";

    /**
     * Nombre del parametro contenido en el vinculo(URL) de recuperacion corresponde al codigo de verificacion del vinculo de recuperacion
     */
    public static final String NOMBRE_PARAMETRO_KEY_RECUPERACION = "key";

    /**
     * Nombre del objeto en sesion que contiene los datos del UsuarioPersona que esta autenticado
     */
    public static final String OBJ_USUARIO_PERSONA_AUTENTICA = "usuarioPersonaAutenticado";

    /**
     * Clase del objeto en sesion que contiene los datos del UsuarioPersona que esta autenticado
     */
    public static final Class<UsuarioPersonaDTO> CLASE_OBJ_USUARIO_PERSONA_AUTENTICADA = UsuarioPersonaDTO.class;

    /**
     * Nombre del objeto en sesion que contiene la fachada de acceso a los catalogos de aplicacion
     */
    public static final String NOMBRE_FACHADA_CATALOGOS = "fachadaCatalogosMB";

    /**
     * Clase del objeto en sesion que contiene la fachada de acceso a los catalogos de aplicacion
     */
    public static final Class<FachadaCatalogosMB> CLASE_OBJ_FACHADA_CATALOGOS = FachadaCatalogosMB.class;

    /**
     * Nombre del objeto de aplicacion que se inicializa con el despliegue de la aplicacion
     */
    public static final String NOMBRE_MB_INICO_APP = "inicioAppCirculemos";

    /**
     * Constante para llevar a cabo en los componentes de listado de correos ingresados en un texto
     */
    public static final String REGEX_SEPARADOR_EMAIL = ",";

    /**
     * Nombre del estilo css del campo obligatorio para los componentes web
     */
    public static final String ESTILO_CSS_CAMPO_OBLIGATORIO = "campo-obligatorio";

    /**
     * Constantes de los nombres de los bundles de etiquetas internaciolizadas
     * 
     * @author felipe.martinez
     */
    public static class C2Bundles {
        private C2Bundles() {
        }

        public static final String NOMBRE_BUNDLE_ADMIN = "labelAdmin";
        public static final String NOMBRE_BUNDLE_DESCUENTOS = "labelDescuentos";
        public static final String NOMBRE_BUNDLE_GENERAL = CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL;
        public static final String NOMBRE_BUNDLE_COMPARENDO = "labelComparendo";

        public static final String NOMBRE_BUNDLE_COMUN = "lbComun";

        public static final String NOMBRE_BUNDLE_WEB_PARAM = "webPrm";
    }

    public static class ParamsWeb {
        private ParamsWeb() {
        }

        public static final String FORMATO_COMPLETO_FECHA = "lab_calendar_pattern_full";
    }

    public static class C2FlowObjects {
        private C2FlowObjects() {

        }

        /**
         * Nombre unico del objeto de flujo Holder para las paginas/flujo de personas
         */
        public static final String PERSONA_HOLDER_FL = "personaHolderFL";

        /**
         * Nombre unico del objeto de flujo de datos para las paginas/flujo de personas
         */
        public static final String PERSONA_FL = "personaFL";

        /**
         * Nombre unico del objeto de flujo Holder para las paginas/flujo de direccion
         */
        public static final String DIRECCION_HOLDER_FL = "dirHolderFL";

        /**
         * Nombre unico del objeto de flujo de datos para las paginas/flujo de direccion
         */
        public static final String DIRECCION_FL = "dirFL";
    }
}
