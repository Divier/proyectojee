package co.com.datatools.c2.ws.comparendo.recepcion.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.6 Fri Oct 18 17:07:09 COT 2013 Generated source version: 2.4.6
 */

@XmlRootElement(name = "recibirComparendo", namespace = "http://recepcion.comparendo.ws.c2.datatools.com.co/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibirComparendo", namespace = "http://recepcion.comparendo.ws.c2.datatools.com.co/")
public class RecibirComparendo {

    @XmlElement(name = "comparendo")
    private co.com.datatools.c2.dto.ws.ComparendoWSDTO comparendo;

    public co.com.datatools.c2.dto.ws.ComparendoWSDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(co.com.datatools.c2.dto.ws.ComparendoWSDTO newComparendo) {
        this.comparendo = newComparendo;
    }

}
