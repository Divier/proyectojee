package co.com.datatools.c2.reporte.jsf;

/**
 * Interfaz comun que debe implementar una clase para manejar el envio de correo de un reporte a traves del componente de faces
 * 
 * @author felipe.martinez
 */
public interface EmailProcessor {
    /**
     * Metodo usado desde el componente de faces para enviar un correo
     * 
     * @param direccionDestino
     *            direcciones de envio del correo
     * @param asunto
     *            asunto del correo
     * @param contenido
     *            cuerpo del correo
     * @param nombreAdjunto
     *            Nombre del archivo adjunto (reporte)
     * @param contenidoAdjunto
     *            contenido del adjunto
     */
    public void enviarCorreoReporte(String[] direccionDestino, String asunto, String contenido, String nombreAdjunto,
            byte[] contenidoAdjunto);
}