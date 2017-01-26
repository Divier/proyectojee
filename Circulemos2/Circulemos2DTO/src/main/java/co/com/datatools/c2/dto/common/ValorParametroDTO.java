package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException;
import co.com.datatools.c2.excepciones.ConfiguracionParametroException.ErrorParametro;
import co.com.datatools.c2.util.Utilidades;

public class ValorParametroDTO implements Serializable {

    private static final String REGEX_SPLIT = ",";
    private static final long serialVersionUID = 1L;
    private EnumParametro parametro;
    private List<String> valores = new ArrayList<>(1);

    public ValorParametroDTO(EnumParametro parametro) {
        this.parametro = parametro;
    }

    /**
     * Valor vigente para el parametro
     * 
     * @return Valor vigente
     */
    public String getValorParam() {
        return valores.get(0);
    }

    /**
     * Obtiene el Valor vigente del parametro, si el primer valor del parametro no es un boolean valido, lanza excepcion ya que implica un error de
     * configuracion
     * 
     * @return valor del parametro interpretado como booleano
     */
    public boolean getValorParamBoolean() {
        return Utilidades.toBooleanParametro(parametro, getValorParam());
    }

    /**
     * Valor vigente para el parametro llevando a cabo el split del valor encontrado separandolo con el caracter ','.
     * 
     * @return array de strings del valor encontrado llevando a cabo la separacion por el caracter ',', null si el valor del parametro no esta
     *         definido
     * @author luis.forero (mod 2014-11-20)
     */
    public String[] getValorParamSplit() {
        String valor = valores.get(0);
        if (valor != null) {
            return valor.split(REGEX_SPLIT);
        }
        return null;
    }

    /**
     * Obtiene el Valor vigente del parametro, si el primer valor del parametro no es un entero valido, lanza excepcion ya que implica un error de
     * configuracion
     * 
     * @return valor del parametro interpretado como int
     */
    public int getValorParamInt() {
        try {
            return Integer.parseInt(getValorParam());
        } catch (NumberFormatException e) {
            throw new ConfiguracionParametroException(ErrorParametro.PARAMETRO_NO_ENTERO, parametro);
        }
    }

    public List<String> getValores() {
        return valores;
    }

    public EnumParametro getParametro() {
        return parametro;
    }

}