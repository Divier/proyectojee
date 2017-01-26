package co.com.datatools.client.timer.helpers;

public class TareaProgramada {

    private String jndi;
    private String nombre;

    public TareaProgramada(String nombre, String jndi) {
        super();
        this.jndi = jndi;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getJndi() {
        return jndi;
    }

}
