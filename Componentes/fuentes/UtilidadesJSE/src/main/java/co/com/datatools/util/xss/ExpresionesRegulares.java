package co.com.datatools.util.xss;

/**
 * Clase que identifica todas las posibles expresiones regulares para detectar una inyeccion
 * 
 * @author luis.forero
 * 
 */
public class ExpresionesRegulares {

    private ExpresionesRegulares() {
    }

    /**
     * Evalua si una cadena entrante contiene una comilla sencilla.
     */
    public static final String EXP_COMILLA_SENCILLAS = ".*[']+.*";

    /**
     * EXPRESION PARA IDENTIFICAR UN ESPACIO [ \t\n\x0B\f\r]
     */
    public static final String EXP_ESPACIO = "[ \t\n\\x0B\f\r]";
    /**
     * EXPRESION PARA IDENTIFICAR UNA DECLARACION DE VARIABLE ([A-Za-z0-9_]+){1}
     */
    public static final String EXP_VARIABLE = "([A-Za-z0-9_]+){1}";
    /**
     * EXPRESION VALORES POSIBLES QUE PUEDE TOMAR UNA CADENA (('.+'){1}|([0-9]+([\.][0-9]+)?){1})lj
     */
    public static final String EXP_VALOR = "(('.+'){1}|([0-9]+([\\.][0-9]+)?){1})";

    /*
     * ***************************************************************************************
     * *********************************** SQL INJECTION *************************************
     * ***************************************************************************************
     */
    /**
     * Expresion palabra or [oO][rR]
     */
    public static final String EXP_OR = "[oO][rR]";
    /**
     * EXPRESION SQL SET [sS][eE][tT]
     */
    public static final String EXP_SET = "[sS][eE][tT]";
    /**
     * Expresion WHERE [wW][hH][eE][rR][eE]
     */
    public static final String EXP_WHERE = "[wW][hH][eE][rR][eE]";
    /**
     * Expresion palabra update [uU][pP][dD][aA][tT][eE]
     */
    public static final String EXP_UPDATE = "[uU][pP][dD][aA][tT][eE]";
    /**
     * Expresion palabra like [lL][iI][kK][eE]
     */
    public static final String EXP_LIKE = "[lL][iI][kK][eE]";
    /**
     * Expresion palabra and [aA][nN][dD]
     */
    public static final String EXP_AND = "[aA][nN][dD]";
    /**
     * Expresion palabra table [tT][aA][bB][lL][eE]
     */
    public static final String EXP_TABLE = "[tT][aA][bB][lL][eE]";
    /**
     * Expresion palabra drop [dD][rR][oO][pP]
     */
    public static final String EXP_DROP = "[dD][rR][oO][pP]";
    /**
     * Expresion palabra from [fF][rR][oO][mM]
     */
    public static final String EXP_FROM = "[fF][rR][oO][mM]";
    /**
     * Expresion palabra select [sS][eE][lL][eE][cC][tT]
     */
    public static final String EXP_SELECT = "[sS][eE][lL][eE][cC][tT]";
    /**
     * Expresion para palabra INTO [iI][nN][tT][oO]
     */
    public static final String EXP_INTO = "[iI][nN][tT][oO]";
    /**
     * Expresion para palabra INSERT [iI][nN][sS][eE][rR][tT]
     */
    public static final String EXP_INSERT = "[iI][nN][sS][eE][rR][tT]";
    /**
     * Expresion para palabra VALUES [vV][aA][lL][uU][eE][sS]
     */
    public static final String EXP_VALUES = "[vV][aA][lL][uU][eE][sS]";
    /**
     * Expresion para palabra LIMIT
     */
    public static final String EXP_LIMIT = "[lL][iI][mM][iI][tT]";
    /**
     * OPERADORES IGUAL O LIKE
     */
    public static final String EXP_OPERADOR = "((" + EXP_ESPACIO + ")*=(" + EXP_ESPACIO + ")*|(" + EXP_ESPACIO
            + EXP_LIKE + EXP_ESPACIO + ")){1}";

    /**
     * IGUALDAD VARIABLE(=|LIKE)(0-9|'alfanumerico') EJM: val_01 LIKE 'Hola mundo'
     */
    public static final String EXP_IGUALDAD = "(" + EXP_VARIABLE + "){1}" + EXP_OPERADOR + "(" + EXP_VALOR + "){1}";

    /**
     * Expresion de valores dentro de parentesis EJM: ('10005. ASDF 2215',1.12, 1222)
     */
    public static final String EXP_VALORES_PARENTESIS = "\\(" + EXP_VALOR + "((" + EXP_ESPACIO + ")*[,](" + EXP_ESPACIO
            + ")*" + EXP_VALOR + ")*\\)";
    /**
     * Expresion para identificar variables en parentesis EJM: (UNA_VAR_01,OTRA_VAR, YMAS )
     */
    public static final String EXP_VARIABLES_PARENTESIS = "\\(" + EXP_VARIABLE + "((" + EXP_ESPACIO + ")*[,]("
            + EXP_ESPACIO + ")*" + EXP_VARIABLE + ")*\\)";

    /**
     * EXPRESION SET CON VALORES EJM: SET campo_01=123, campo_02='prueba@ esto'
     */
    public static final String EXP_SET_COMPLETO = "((" + EXP_ESPACIO + ")" + EXP_SET + "(" + EXP_ESPACIO + ")("
            + EXP_IGUALDAD + ")|((" + EXP_ESPACIO + ")" + EXP_SET + "(" + EXP_ESPACIO + ")(" + EXP_IGUALDAD + ")(("
            + EXP_ESPACIO + ")*,(" + EXP_ESPACIO + ")*(" + EXP_IGUALDAD + "))+))";

    /**
     * Expresion de where junto con igualdades unicas o repetidas EJM: WHERE capo_01=123, campo_02 LIKE 'Hola'
     */
    public static final String EXP_WHERE_COMPLETO = "(((" + EXP_ESPACIO + ")*" + EXP_WHERE + "(" + EXP_ESPACIO + ")("
            + EXP_IGUALDAD + "))|(" + EXP_ESPACIO + ")*" + EXP_WHERE + "(" + EXP_ESPACIO + ")(" + EXP_IGUALDAD + ")(("
            + EXP_ESPACIO + ")" + EXP_AND + "(" + EXP_ESPACIO + "){1}" + EXP_IGUALDAD + ")+)";

    /**
     * Expresion de inyeccion de UPDATE
     */
    public static final String EXP_INYECCION_UPDATE = "^[']{1}(" + EXP_ESPACIO + ")+" + EXP_OR + "(" + EXP_ESPACIO
            + ")" + EXP_IGUALDAD + ";(" + EXP_ESPACIO + ")*(" + EXP_UPDATE + "(" + EXP_ESPACIO + ")" + EXP_VARIABLE
            + EXP_SET_COMPLETO + "(" + EXP_ESPACIO + ")" + EXP_WHERE_COMPLETO + ")(" + EXP_ESPACIO + ")*((;--)$|(;')$)";

    /**
     * Expresion d inyeccion de un DROP TABLE
     */
    public static final String EXP_INYECCION_DROP_TABLE = "^[']{1}(" + EXP_ESPACIO + ")+" + EXP_OR + "(" + EXP_ESPACIO
            + ")" + EXP_IGUALDAD + ";(" + EXP_ESPACIO + ")*" + EXP_DROP + "(" + EXP_ESPACIO + ")" + EXP_TABLE + "("
            + EXP_ESPACIO + ")" + EXP_VARIABLE + "(" + EXP_ESPACIO + ")*((;--)$|(;')$)";

    /**
     * Expresion de inyeccion para SELECT
     */
    public static final String EXP_INYECCION_SELECT = "^[']{1}(" + EXP_ESPACIO + ")+" + EXP_OR + "(" + EXP_ESPACIO
            + ")" + EXP_IGUALDAD + ";(" + EXP_ESPACIO + ")*(" + EXP_SELECT + "(" + EXP_ESPACIO + ")[A-Za-z_\\*]+("
            + EXP_ESPACIO + ")" + EXP_FROM + "(" + EXP_ESPACIO + ")" + EXP_VARIABLE + "((" + EXP_ESPACIO + ")"
            + EXP_WHERE_COMPLETO + ")*)(" + EXP_ESPACIO + ")*((;--)$|(;')$)";
    /**
     * Expresion para detectar inyeccion de INSERT <br>
     * EJM: ' OR 1=1; INSERT INTO tabla_x (AJA,OTRO_, OTRO_MAS,OMAS) VALUES ('AJA',12.31, 3222);'
     */
    public static final String EXP_INYECCION_INSERT = "^(.*[[0-9]+(.[0-9]+)?']{1})(" + EXP_ESPACIO + ")+" + EXP_OR
            + "(" + EXP_ESPACIO + ")" + EXP_IGUALDAD + ";(" + EXP_ESPACIO + ")*" + EXP_INSERT + "(" + EXP_ESPACIO + ")"
            + EXP_INTO + "(" + EXP_ESPACIO + ")" + EXP_VARIABLE + "(" + EXP_ESPACIO + ")" + EXP_VARIABLES_PARENTESIS
            + "(" + EXP_ESPACIO + ")" + EXP_VALUES + "(" + EXP_ESPACIO + ")" + EXP_VALORES_PARENTESIS + "("
            + EXP_ESPACIO + ")*((;--)$|(;')$)";
    /**
     * Expresion de inyeccion simple
     */
    public static final String EXP_INYECCION = "^(.*[[0-9]+(.[0-9]+)?']{1})(" + EXP_ESPACIO + ")+" + EXP_OR + "("
            + EXP_ESPACIO + ")" + EXP_IGUALDAD + "(" + EXP_ESPACIO + ")" + EXP_OR + "(" + EXP_ESPACIO + ")[']{2}"
            + EXP_OPERADOR + "'";

    /**
     * Expresion de entrada simple ejm: 'or 1=1 or''='
     */
    public static final String EXP_INYECCION_IN = "(.)*'(" + EXP_ESPACIO + ")?" + EXP_OR + EXP_ESPACIO + EXP_IGUALDAD
            + EXP_ESPACIO + EXP_OR + "(" + EXP_ESPACIO + ")?" + "[']{2}(" + EXP_ESPACIO + ")?" + "(" + EXP_LIKE
            + "|=)(" + EXP_ESPACIO + ")?[']{1}";

    /*
     * ***************************************************************************************
     * *********************************** JAVA SCRIPT ***************************************
     * ***************************************************************************************
     */

    public static final String EXP_JAVASCRIPT_0 = "<script>(.*\\(.*\\);*)+</script>";

    /**
     * Expresion que valida una función javascript<br>
     * ejm: ');function(){alert('Hola')}; <br>
     * "\'\\);([ \t\n\\x0B\f\r])*((function([ \t\n\\x0B\f\r])*([a-zA-Z0-9_]+)?)\\((((\'.+\'){1}|([0-9]+([\\.][0-9]+)?){1}))*\\)\\{(.*)\\};)+"
     */
    public static final String EXP_JAVASCRIPT_FUNCTION = "'\\);(" + EXP_ESPACIO + ")*((function(" + EXP_ESPACIO
            + ")*([a-zA-Z0-9_]+)?)\\((" + EXP_VALOR + ")*\\)\\{(.*)\\};)+";

    /*
     * ***************************************************************************************
     * ********************************* LISTADO DE EXPRESIONES ******************************
     * ***************************************************************************************
     */

    /**
     * Listado de expresiones de inyeccion sql
     */
    protected static final String[] LIST_EXPRESIONES_INYECCION_SQL = { EXP_INYECCION, EXP_INYECCION_IN,
            EXP_INYECCION_DROP_TABLE, EXP_INYECCION_INSERT, EXP_INYECCION_SELECT, EXP_INYECCION_UPDATE };

    /**
     * Listado de expresiones de inyeccion sql
     */
    protected static final String[] LIST_EXPRESIONES_INYECCION_JAVASCRIPT = { EXP_JAVASCRIPT_0, EXP_JAVASCRIPT_FUNCTION };

}
