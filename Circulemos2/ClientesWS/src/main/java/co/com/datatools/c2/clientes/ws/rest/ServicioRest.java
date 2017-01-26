/**
 * 
 */
package co.com.datatools.c2.clientes.ws.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.logging.Logger;

import co.com.datatools.c2.clientes.ws.rest.enumeration.EnumMetodoEnvio;
import co.com.datatools.c2.clientes.ws.rest.enumeration.EnumTipoRespuesta;

/**
 * Clase que representa un servicio rest
 * 
 * @author julio.pinzon 2016-08-12
 *
 */
public class ServicioRest {

    private final static Logger logger = Logger.getLogger(ServicioRest.class.getName());
    private Map<String, String> parametros;
    private String url;
    private EnumTipoRespuesta tipoRespuesta;
    private EnumMetodoEnvio metodoEnvio;

    public ServicioRest(Map<String, String> parametros, String url, EnumTipoRespuesta tipoRespuesta,
            EnumMetodoEnvio metodoEnvio) {
        this.parametros = parametros;
        this.url = url;
        this.tipoRespuesta = tipoRespuesta;
        this.metodoEnvio = metodoEnvio;
    }

    public String enviar() throws URISyntaxException, IOException {
        logger.debug(ServicioRest.class.getName().concat("::enviar()"));

        StringBuilder respuestaWS = new StringBuilder();
        StringBuilder parametros = new StringBuilder();
        int i = 0;
        for (Entry<String, String> parametro : getParametros().entrySet()) {
            if (i > 0) {
                parametros.append("&");
            }
            parametros.append(parametro.getKey() + "=" + parametro.getValue());
            i++;
        }

        byte[] postData = parametros.toString().getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        URL url = new URL(getUrl());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod(getMetodoEnvio().toString());
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        if (conn.getResponseCode() != 200) {
            logger.info("mensaje_error:" + conn.getHeaderField("mensaje_error"));

            throw new RuntimeException("Error:" + conn.getResponseCode() + " : " + conn.getResponseMessage());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;

        logger.info("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            respuestaWS.append(output);
        }
        conn.disconnect();
        logger.info("respuesta:" + respuestaWS);

        return respuestaWS.toString();
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EnumTipoRespuesta getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(EnumTipoRespuesta tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public EnumMetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(EnumMetodoEnvio metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

}
