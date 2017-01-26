package co.com.datatools.c2.adaptador.comparendo;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface IRComparendoFinanciacion {

    /**
     * Cambia estado de comparendos a financiado
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @param fechaCambio
     * @param idProceso
     * @author julio.pinzon 2016-05-18
     */
    public void financiarComparendo(String numeroComparendo, int codigoOrganismo, Date fechaCambio, Long idProceso);

    /**
     * Prefinancia el comparendo
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @param fechaCambio
     */
    public void preFinanciarComparendo(String numeroComparendo, int codigoOrganismo, Date fechaCambio, Long idProceso);

    /**
     * Financia todos los comparendos de un proceso de financiacion
     * 
     * @param fechaCambio
     * @param idProceso
     */
    public void financiarProceso(Date fechaCambio, Long idProceso);

    /**
     * Actualiza el estado del comparendo asociado a la financiacion
     * 
     * @param idProceso
     */
    void actualizarEstadoAnteriorComparendo(Long idProceso);

    /**
     * Consulta el medio de imposicion de un comparendo
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @return Nombre del medio de imposicion
     */
    public String consultarMedioImposicionComparendo(String numeroComparendo, Integer codigoOrganismo);

    /**
     * valida si existe no comparendos notificados
     * 
     * @param numeroComparendo
     * @param codigoOrganismo
     * @return retorna falso cuando la fecha de notificacion es null y verdadera cuando existe fecha de notificacion.
     * @author Jeison.Rodriguez (2016-31-08)
     */
    public boolean validarComparendoNotificacion(String numeroComparendo, Integer codigoOrganismo);
}
