package co.com.datatools.c2.negocio.interfaces.util;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.LogEnvioCorreoDTO;
import co.com.datatools.c2.enumeracion.EnumTipoCorreo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;

@Remote
public interface IRCirculemosMailSender {

    /**
     * Crea el contenido de un correo apartir de la informacion parametrizada para tipoCorreo y codigoOrganismo
     * 
     * @param codigoOrganismo
     *            Codigo de organismo
     * @param tipoCorreo
     *            llave unica de un tipo de correo al q se asocia el contenido del correo a enviar
     * @param direccionDestino
     *            direccion de correo electronico de destino
     * @param variablesMensaje
     *            mapa de llaves y valores q pueden ser agregadas al contenido del mensaje<br>
     *            K: nombre del parametro a reemplazar<br>
     *            V: valor del parametro q se agrega al contenido del mensaje
     */
    LogEnvioCorreoDTO enviarCorreo(int codigoOrganismo, EnumTipoCorreo tipoCorreo, String[] direccionDestino,
            Map<String, Object> variablesMensaje);

    /**
     * <p>
     * <b>ESTE METODO NO DEBE SER USADO POR LOGICA DE NEGOCIO PROPIA DE C2,TODOS LOS CORREOS DE C2 DEBEN ESTAR CLASIFICADOS EN EL ADMINSITRADOR DE
     * CORREOS POR TIPO</b>
     * </p>
     * Permite publicar directamente la peticion de un correo para correos no estandarizados en el sistema
     * 
     * @param direccionesEnvio
     *            direcciones destino del correo
     * @param asunto
     *            asunto del correo
     * @param textoCorreo
     *            texto del correo en formato html
     * @param adjunto
     *            datos del adjunto del correo con nombre
     */
    LogEnvioCorreoDTO publicarCorreo(String[] direccionesEnvio, String asunto, String textoCorreo,
            List<ArchivoTransportableDTO> adjuntos);

    /**
     * Permite el envio de correos de acuerdo a una configuracion
     * 
     * @param codigoOrganismo
     *            Codigo de organismo
     * @param tipoCorreo
     *            llave unica de un tipo de correo al q se asocia el contenido del correo a enviar
     * @param direccionesEnvio
     *            direccion de correo electronico de destino
     * @param variablesMensaje
     *            mapa de llaves y valores q pueden ser agregadas al contenido del mensaje<br>
     *            K: nombre del parametro a reemplazar<br>
     *            V: valor del parametro q se agrega al contenido del mensaje
     * @param adjunto
     *            datos del adjunto del correo con nombre
     */
    LogEnvioCorreoDTO enviarCorreo(int codigoOrganismo, EnumTipoCorreo tipoCorreo, String[] direccionesEnvio,
            Map<String, Object> variablesMensaje, List<ArchivoTransportableDTO> adjuntos);
}