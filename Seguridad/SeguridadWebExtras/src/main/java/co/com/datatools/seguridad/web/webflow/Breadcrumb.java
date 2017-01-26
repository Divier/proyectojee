/**
 * 
 */
package co.com.datatools.seguridad.web.webflow;

import java.io.Serializable;

/**
 * Clase utilizada para obtener la miga de pan
 * 
 * @author julio.pinzon 2016-09-15
 *
 */
public class Breadcrumb implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String recurso;

    private String operacion;

    private String url;

    public Breadcrumb(String recurso, String operacion, String url) {
        this.recurso = recurso;
        this.operacion = operacion;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecurso() {
        return this.recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getOperacion() {
        return this.operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.recurso == null) ? 0 : this.recurso.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Breadcrumb)) {
            return false;
        }
        Breadcrumb oBreadcrumb = (Breadcrumb) other;
        return this.recurso.equals(oBreadcrumb.recurso);
    }
}
