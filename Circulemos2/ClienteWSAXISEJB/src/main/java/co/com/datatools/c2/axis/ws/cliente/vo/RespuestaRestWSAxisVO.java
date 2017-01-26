/**
 * 
 */
package co.com.datatools.c2.axis.ws.cliente.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Utilizada para registros en tablas que pueden no tener un rowKey
 * 
 * @author julio.pinzon 2016-07-08
 *
 */
@XmlRootElement(name = "respuesta")
public class RespuestaRestWSAxisVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String codMensaje;
    private String mensaje;
    private String mensajeTecnico;
    private DataVO data;

    public DataVO getData() {
        return data;
    }

    @XmlElement
    public void setData(DataVO data) {
        this.data = data;
    }

    public String getCodMensaje() {
        return codMensaje;
    }

    public void setCodMensaje(String codMensaje) {
        this.codMensaje = codMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeTecnico() {
        return mensajeTecnico;
    }

    public void setMensajeTecnico(String mensajeTecnico) {
        this.mensajeTecnico = mensajeTecnico;
    }

    @Override
    public String toString() {
        return "RespuestaRestWSAxisVO [codMensaje=" + codMensaje + ", mensaje=" + mensaje + ", mensajeTecnico="
                + mensajeTecnico + ", data=" + data + "]";
    }

}
