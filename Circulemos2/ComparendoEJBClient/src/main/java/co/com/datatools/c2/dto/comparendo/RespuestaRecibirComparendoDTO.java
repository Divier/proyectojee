package co.com.datatools.c2.dto.comparendo;


/**
 * Objeto que almacena la respuesta de un procesar recibir comparendo
 * 
 * @author giovanni.velandia(mod 2016-04-14)
 */
public class RespuestaRecibirComparendoDTO extends RespuestaValidacionDTO {

    private static final long serialVersionUID = 3199301276644992354L;

    private Integer codigoOrganismoTransito;
    private Long cicompareno;

    public Long getCicompareno() {
        return cicompareno;
    }

    public void setCicompareno(Long cicompareno) {
        this.cicompareno = cicompareno;
    }

    public Integer getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(Integer codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

}
