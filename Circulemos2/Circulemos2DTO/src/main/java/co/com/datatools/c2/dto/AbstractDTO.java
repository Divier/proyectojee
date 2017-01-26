package co.com.datatools.c2.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

public class AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1460569027173924783L;

    protected static XStream xstream;

    public AbstractDTO() {
        super();
        xstream = new XStream();
    }

    @Override
    public String toString() {
        return xstream.toXML(this);
    }

}
