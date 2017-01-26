package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.formularios.DetalleNumeracionDTO;
import co.com.datatools.c2.dto.formularios.GeneracionNumeroFormularioDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;

/**
 * Clase que encapsula los metodos que permiten realizar ciertas operaciones de negocio sobre rangos de formularios, como los son listar los numeros
 * contenidos en un rango y determinar la cantidad de formularios en un rango
 * 
 * @author claudia.rodriguez
 * 
 */
public class UtilFormularios {

    /**
     * Almacena los numeros generados
     */
    List<String> numeros;

    /**
     * Contiene la configuracion para cada casilla de la numeracion
     */
    private NumeracionFormularioDTO numeracion;

    /**
     * Almacena la cantidad de formularios en un rango
     */
    private Integer contadorFormularios;
    /**
     * Indica si los numeros generados se deben contar, de lo contrario se almacenan en un arreglo
     */
    private boolean contar;

    public UtilFormularios() {
        numeros = new ArrayList<String>();
        contadorFormularios = Integer.valueOf(0);
    }

    private final static Logger logger = Logger.getLogger(UtilFormularios.class.getName());

    /**
     * Genera todos los numeros comprendidos entre un numero inicial y un numero final
     * 
     * @param numeroInicial
     *            Numero inicial del rango
     * @param numeroFinal
     *            Numero final del rango
     * @param numeracionDTO
     *            Dto con los datos de configuracion de la numeracion que sera utilizada para calcular los numeros
     * @param contar
     *            Indica si los numero generados se deben almacenar en un arreglo o si unicamente se deben contar.True para contar los numeros y false
     *            para almcanarlos en un arreglo
     * @return Listado con los numeros generados
     */
    public List<String> listarNumerosDeRango(String numeroInicial, String numeroFinal,
            NumeracionFormularioDTO numeracionDTO, boolean contar, Integer cantidadFormularios) {
        numeracion = numeracionDTO;
        this.contar = contar;
        // logger.debug("UtilFormularios::listarNumerosDeRango");
        if (!this.contar)
            numeros.add(numeroInicial);
        else
            contadorFormularios++;
        if (!numeroInicial.equals(numeroFinal)) {
            List<DetalleNumeracionDTO> detalleNumeracion = numeracionDTO.getDetalleNumeracionList();
            //
            for (int i = numeroInicial.length() - 1; i >= 0; i--) {
                String base = numeroInicial.substring(0, i);
                boolean finalizado = false;
                char maximo = detalleNumeracion.get(i).getCaracterFin();
                if ((char) numeroInicial.charAt(i) + 1 <= maximo) {
                    GeneracionNumeroFormularioDTO dto = new GeneracionNumeroFormularioDTO(base, i,
                            (char) (numeroInicial.charAt(i) + 1), maximo, numeroFinal, cantidadFormularios);
                    finalizado = construirNumRecursivo(dto);
                }
                if (finalizado)
                    break;

            }
        }

        /*
         * if (!this.contar) logger.debugv("***** TOTAL NUMEROS EN RANGO {0} A {1} = {2} ", numeroInicial, numeroFinal, numeros.size()); else
         * logger.debugv("***** CONTEO NUMEROS {0} A {1} = {2} ", numeroInicial, numeroFinal, contadorFormularios);
         */

        return numeros;
    }

    /**
     * Metodo que permite construir numeros de formulario a partir de una base y la variacion de determinadas posiciones dentro del numero desde un
     * caracter inicial y final
     * 
     * @param dto
     *            Contiene los datos necesarios para realizar el llamado recursivo
     */
    private boolean construirNumRecursivo(GeneracionNumeroFormularioDTO dto) {
        /*
         * logger.debugv("UtilFormularios::construirNumRecursivo:: Base={0},Posicion={1}, Minimo={2}, Maximo={3}", dto.getBase(), dto.getPosicion(),
         * dto.getMin(), dto.getMax());
         */
        String num = "";
        for (int i = dto.getMin(); i <= (int) dto.getMax(); i++) {
            num = dto.getBase() + (char) i;
            if (dto.getPosicion() + 1 < numeracion.getDigitos()) {
                GeneracionNumeroFormularioDTO dtoGeneracion = new GeneracionNumeroFormularioDTO(num,
                        dto.getPosicion() + 1, numeracion.getDetalleNumeracionList().get(dto.getPosicion() + 1)
                                .getCaracterInicio(), numeracion.getDetalleNumeracionList().get(dto.getPosicion() + 1)
                                .getCaracterFin(), dto.getNumeroFinal(), dto.getCatidadFormularios());
                boolean finalizado = construirNumRecursivo(dtoGeneracion);

                if (finalizado) {
                    return true;
                }
            } else {
                // logger.debug("Numero= " + num);
                if (!this.contar)
                    numeros.add(num);
                else
                    contadorFormularios++;

                if (dto.getNumeroFinal() != null) {
                    if (num.equals(dto.getNumeroFinal())) {
                        return true;
                    }
                } else if (!this.contar) {
                    if (numeros.size() == dto.getCatidadFormularios()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Calcula la cantidad de formularios comprendidos entre dos numeros de una numeracion particular
     * 
     * @param numeroInicial
     *            Numero desde el cual se va a calcular la cantidad de formularios
     * @param numeroFinal
     *            Numero hasta el cual se va a calcular la cantidad de formularios
     * @param idNumeracion
     *            Id de la configuracion de numeracion utilizada para realizar el calculo
     * @return Cantidad de formularios comprendidos entre los numeros enviados
     * @author claudia.rodriguez
     */
    public Integer calcularCantidadFormularios(String numeroInicial, String numeroFinal,
            NumeracionFormularioDTO numeracionDTO) {
        logger.debug("UtilFormularios::calcularCantidadFormularios Inicio: " + new Date());
        listarNumerosDeRango(numeroInicial, numeroFinal, numeracionDTO, true, null);
        logger.debug("UtilFormularios::calcularCantidadFormularios Fin: " + new Date());
        return contadorFormularios;
    }

    /**
     * Genera la cantidad de numeros requerida iniciando en un numero particular que pertenece a una numeracion configurada
     * 
     * @param numeroInicial
     * @param numeracionDTO
     * @param cantidadFormularios
     * @return
     */
    public List<String> generarNumeros(String numeroInicial, NumeracionFormularioDTO numeracionDTO,
            Integer cantidadFormularios) {
        listarNumerosDeRango(numeroInicial, null, numeracionDTO, false, cantidadFormularios);
        return numeros;
    }

}
