package co.com.datatools.c2.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeracion.EnumLogSistema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ArchivoLogJBoss implements ILog {

    public Logger LOG;
    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public ArchivoLogJBoss(EnumLogSistema enumLogSistema) {
        LOG = Logger.getLogger(enumLogSistema.getNombreLog());
    }

    @Override
    public void escribir(String informacion, ILogueable datos) {
        Gson gson = new GsonBuilder().setDateFormat(DATETIME_FORMAT).create();
        StringBuilder linea = new StringBuilder();
        linea.append(informacion);
        linea.append("(");
        linea.append(new SimpleDateFormat(DATETIME_FORMAT).format(new Date()));
        linea.append(")");
        linea.append("::");
        linea.append(gson.toJson(datos));
        LOG.info(linea.toString());
    }

}
