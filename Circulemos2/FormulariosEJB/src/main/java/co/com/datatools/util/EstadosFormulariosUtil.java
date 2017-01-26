package co.com.datatools.util;

import java.util.List;
import java.util.Map;

import co.com.datatools.c2.dto.formularios.CifrasControlEstadoFormularioAsignacionDTO;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;

/**
 * 
 * @author diego.fonseca Clase Auxiliar para el metodo de negocio FormulariosEJB.consultarEstadosFormulariosAsignacion
 * 
 */
public class EstadosFormulariosUtil {

    /**
     * 
     * @param matrizAux
     * @param ccefAsignacionDTOAnt
     * @param ccefAsignacionDTOAct
     * @param ccefAsignacionDTOList
     * @param estados
     * @param results
     * 
     *            Metodo que se encarga de generar reporte agregando los estados que no tienen valores y asignandoles 0
     */
    public static void generarLista(int[][] matrizAux, CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAnt,
            CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAct,
            List<CifrasControlEstadoFormularioAsignacionDTO> ccefAsignacionDTOList, Map<Integer, String> estados,
            List<Object[]> results) {

        for (int i = 0; i < results.size(); i++) {
            Object[] arr = results.get(i);
            ccefAsignacionDTOAct.setIdDetalleCambioEstado(((DetalleCambioEstado) arr[0]).getId());
            ccefAsignacionDTOAct.setFechaAplicacion(((DetalleCambioEstado) arr[0]).getFechaAplicacionEstado());
            ccefAsignacionDTOAct.setIdEstado(((EstadoFormulario) arr[1]).getId());
            ccefAsignacionDTOAct.setNombreEstado(((EstadoFormulario) arr[1]).getNombre());
            ccefAsignacionDTOAct.setCantidad(Integer.parseInt(arr[2].toString()));

            if (i > 0 && i < results.size() - 1) {
                if (ccefAsignacionDTOAct.getIdDetalleCambioEstado() != ccefAsignacionDTOAnt.getIdDetalleCambioEstado()) {
                    for (int auxId = 0; auxId < matrizAux.length; auxId++) {
                        CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAux = new CifrasControlEstadoFormularioAsignacionDTO();
                        ccefAsignacionDTOAux.setNombreEstado(estados.get(auxId + 2));

                        ccefAsignacionDTOAux.setIdDetalleCambioEstado(Long.valueOf(matrizAux[auxId][0]));
                        ccefAsignacionDTOAux.setFechaAplicacion(ccefAsignacionDTOAnt.getFechaAplicacion());
                        ccefAsignacionDTOAux.setIdEstado(matrizAux[auxId][1]);
                        ccefAsignacionDTOAux.setCantidad(matrizAux[auxId][2]);
                        ccefAsignacionDTOList.add(ccefAsignacionDTOAux);

                    }
                    for (int j = 0; j < matrizAux.length; j++) {
                        matrizAux[j][0] = 0;
                        matrizAux[j][2] = 0;
                    }
                    obtenerEstado(matrizAux, ccefAsignacionDTOAct, estados);
                } else {
                    obtenerEstado(matrizAux, ccefAsignacionDTOAct, estados);
                }
            } else {
                for (int auxId = 0; auxId < matrizAux.length; auxId++) {
                    matrizAux[auxId][0] = ccefAsignacionDTOAct.getIdDetalleCambioEstado().intValue();
                    CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAux = new CifrasControlEstadoFormularioAsignacionDTO();
                    ccefAsignacionDTOAux.setNombreEstado(estados.get(auxId + 2));
                    if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.ASIGNADO.getValue()) {
                        matrizAux[0][2] = ccefAsignacionDTOAct.getCantidad();
                    } else if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.DEVUELTO.getValue()) {
                        matrizAux[1][2] = ccefAsignacionDTOAct.getCantidad();
                    } else if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.ANULADO.getValue()) {
                        matrizAux[2][2] = ccefAsignacionDTOAct.getCantidad();
                    } else {
                        matrizAux[3][2] = ccefAsignacionDTOAct.getCantidad();
                    }
                    if (i == results.size() - 1) {
                        ccefAsignacionDTOAux.setIdDetalleCambioEstado(ccefAsignacionDTOAct.getIdDetalleCambioEstado());
                        ccefAsignacionDTOAux.setFechaAplicacion(ccefAsignacionDTOAct.getFechaAplicacion());
                        ccefAsignacionDTOAux.setIdEstado(matrizAux[auxId][0]);
                        ccefAsignacionDTOAux.setCantidad(matrizAux[auxId][2]);
                        ccefAsignacionDTOList.add(ccefAsignacionDTOAux);
                    }
                }

                ccefAsignacionDTOAnt.setIdDetalleCambioEstado(((DetalleCambioEstado) arr[0]).getId());
                ccefAsignacionDTOAnt.setFechaAplicacion(((DetalleCambioEstado) arr[0]).getFechaAplicacionEstado());
                ccefAsignacionDTOAnt.setIdEstado(((EstadoFormulario) arr[1]).getId());
                ccefAsignacionDTOAnt.setNombreEstado(((EstadoFormulario) arr[1]).getNombre());
                ccefAsignacionDTOAnt.setCantidad(Integer.parseInt(arr[2].toString()));

            }
        }
    }

    /**
     * 
     * @param matrizAux
     * @param ccefAsignacionDTOAct
     * @param estados
     * 
     *            meodo auxiliar que valida el estado Formulario
     */
    public static void obtenerEstado(int[][] matrizAux,
            CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAct, Map<Integer, String> estados) {
        for (int auxId = 0; auxId < matrizAux.length; auxId++) {
            matrizAux[auxId][0] = ccefAsignacionDTOAct.getIdDetalleCambioEstado().intValue();
            CifrasControlEstadoFormularioAsignacionDTO ccefAsignacionDTOAux = new CifrasControlEstadoFormularioAsignacionDTO();
            ccefAsignacionDTOAux.setNombreEstado(estados.get(auxId + 2));
            if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.ASIGNADO.getValue()) {
                matrizAux[0][2] = ccefAsignacionDTOAct.getCantidad();
            } else if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.DEVUELTO.getValue()) {
                matrizAux[1][2] = ccefAsignacionDTOAct.getCantidad();
            } else if (ccefAsignacionDTOAct.getIdEstado() == EnumEstadoFomulario.ANULADO.getValue()) {
                matrizAux[2][2] = ccefAsignacionDTOAct.getCantidad();
            } else {
                matrizAux[3][2] = ccefAsignacionDTOAct.getCantidad();
            }
        }
    }

}
