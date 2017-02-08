package co.com.datatools.c2.clientes.ws.simit.historialconductor;

import java.io.IOException;
import java.net.Socket;

/**
 * @author luis.forero
 * 
 */
public class WSHistorialClienteMain {

    public static final String NOMBRE_ARCHIVO_NOTIFICACION = "NOTIFICACION_MULTA";

    /**
     * 
     */
    public WSHistorialClienteMain() {
    }

    public static void main(String[] args) throws IOException {
        // FileOutputStream fos = new FileOutputStream("C:\\Users\\divier.casas\\Desktop\\FIRMA.pdf");
        //
        // FileReader fr = new FileReader("C:\\Users\\divier.casas\\Desktop\\ejemplo.txt");
        // BufferedReader br = new BufferedReader(fr);
        // String linea = "", arch = "";
        // while ((linea = br.readLine()) != null) {
        // arch += linea;
        // }
        //
        // String archivo = DatatypeConverter.printString(arch);
        // System.out.println(arch);
        // System.out.println(archivo);
        // br.close();
        // fr.close();
        //
        // fos.write(Base64.decode(archivo));
        // fos.close();

        int a = hacerAlgo(5);
        System.out.println(a);

        Socket s = new Socket("https://www.e-notifica.co/cOfficeWebServices/CircuitIntegrationService.asmx", 80);
        System.out.println(s.isConnected());

    }

    public static int hacerAlgo(int a) {
        if (a < 3) {
            return a;
        }
        return hacerAlgo(a - 1) * hacerAlgo(a - 2);
    }
}