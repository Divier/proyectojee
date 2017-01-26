package co.com.datatools.sogit.utilies;

public enum EnumNavegacion {

    comparendos(1, "/views/citaciones/consultaCitaciones.xhtml?faces-redirect=true"), //
    financiacion(2, "/views/financiacion/consultaFinanciacion.xhtml?faces-redirect=true"), //
    impugnacion(3, "/views/impugnacion/consultaImpugnacion.xhtml?faces-redirect=true"), //
    principal(4, "/index.xhmtl?faces-redirect=true"), //
    gaceta(5, "/views/gaceta/gaceta.xhtml?faces-redirect=true"), //
    consultarGaceta(6, "consultarGaceta"), //
    ;

    private String ruta;
    private int codigoRuta;

    private EnumNavegacion(int codigoRuta, String ruta) {
        this.ruta = ruta;
        this.codigoRuta = codigoRuta;
    }

    /**
     * Se encarga de buscar el enum correspondiente por medio de la url para poder saber el recurso
     * 
     * @param url
     * @return
     * @author giovanni.velandia
     */
    public static EnumNavegacion buscarEnumNavegacion(int codigoRuta) {
        for (EnumNavegacion enumNavegacion : EnumNavegacion.values()) {
            if (codigoRuta == enumNavegacion.codigoRuta) {
                return enumNavegacion;
            }
        }
        return null;
    }

    public String getRuta() {
        return ruta;
    }

    public int getCodigoRuta() {
        return codigoRuta;
    }
}
