package co.com.datatools.c2.managed_bean.administracion.soporte;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de ejecucion de procedure
 * 
 * @author divier.casas
 * 
 */
public class AdminProcedureHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminProcedureHolderFL";

    private Integer soporte;
    private String descripcion;
    private String datosEntrada;
    private String respuesta;

    public AdminProcedureHolderFL() {
        super();
    }

    public Integer getSoporte() {
        return soporte;
    }

    public void setSoporte(Integer soporte) {
        this.soporte = soporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatosEntrada() {
        return datosEntrada;
    }

    public void setDatosEntrada(String datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}