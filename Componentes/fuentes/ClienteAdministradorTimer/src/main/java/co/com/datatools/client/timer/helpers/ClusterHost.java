package co.com.datatools.client.timer.helpers;

public class ClusterHost {
    private String Ip;
    private int Puerto;

    public ClusterHost(String Ip, int Puerto) {
        this.Ip = Ip;
        this.Puerto = Puerto;

    }

    public String getIp() {
        return Ip;
    }

    public int getPuerto() {
        return Puerto;
    }

}
