package co.com.datatools.util.dto;

import java.io.Serializable;

import co.com.datatools.util.ObjectToXML;

/**
 * Clase abstracta que implementa Serializable y provee algunos comportamientos adicionales:
 * <ul>
 * <li>Sobrescribe #toString para serializar el objeto a XML</li>
 * </ul>
 * 
 * @author Felipe Martinez
 */
public class AbstractDto implements Serializable {
    private static final long serialVersionUID = 1460569027173924783L;

    @Override
    public String toString() {
        return ObjectToXML.objectToXml(this);
    }

}
