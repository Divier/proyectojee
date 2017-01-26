package co.com.datatools.c2.web.util.comparendo;

import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.util.Utilidades;

public class ComparendosUtil {

    public static RespuestaValidacionVO validarProcesaComparendo(RespuestaValidacionDTO respuestaValidacionDTO) {
        RespuestaValidacionVO respuestaValidacionVO = new RespuestaValidacionVO();

        for (DetalleProcesamientoDTO detalleProceDTO : respuestaValidacionDTO.getDetalleProcesamientoDTOs()) {
            EnumErrorProcesamiento enumErrorProcesamiento = Utilidades.buscarElemEnum(EnumErrorProcesamiento.class,
                    detalleProceDTO.getErrorProcesamiento().getCodigo());
            if (enumErrorProcesamiento.getEnumGrupoCampoComparendo() != null) {
                switch (enumErrorProcesamiento.getEnumGrupoCampoComparendo().getId()) {
                case 0:
                    respuestaValidacionVO.getDetalleProcesamientoVehiculoDTOs().add(detalleProceDTO);
                    break;
                case 1:
                    respuestaValidacionVO.getDetalleProcesamientoInfractorDTOs().add(detalleProceDTO);
                    break;
                case 2:
                    respuestaValidacionVO.getDetalleProcesamientoPropietarioDTOs().add(detalleProceDTO);
                    break;
                case 3:
                    respuestaValidacionVO.getDetalleProcesamientoAgtTransitoDTOs().add(detalleProceDTO);
                    break;
                case 4:
                    respuestaValidacionVO.getDetalleProcesamientoObservacionesDTOs().add(detalleProceDTO);
                    break;
                case 5:
                    respuestaValidacionVO.getDetalleProcesamientoInmovilizacionDTOs().add(detalleProceDTO);
                    break;
                case 6:
                    respuestaValidacionVO.getDetalleProcesamientoTestigoDTOs().add(detalleProceDTO);
                    break;
                case 7:
                    respuestaValidacionVO.getDetalleProcesamientoEmbriaguezDTOs().add(detalleProceDTO);
                    break;
                case 8:
                    respuestaValidacionVO.getDetalleProcesamientoEvidenciasFisicasDTOs().add(detalleProceDTO);
                    break;
                case 10:
                    respuestaValidacionVO.getDetalleProcesamientoEncabezadoDTOs().add(detalleProceDTO);
                    break;
                default:
                    break;
                }
            }
        }
        return respuestaValidacionVO;
    }
}
