package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.negocio.helpers.CoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ObligacionCoactivoHelper;
import co.com.datatools.c2.negocio.helpers.ProcesarPersonasHelper;

public class CoactivoHelperExtend extends CoactivoHelper {

    /**
     * Se encarga de adicionar las obligaciones asociadas a los coactivos
     * 
     * @param List<Coactivo>
     *            lsCoactivos
     * @return CoactivoDTO
     * @author divier.casas
     */
    public static List<CoactivoDTO> toLevel1DTOExtendObligaciones(List<Coactivo> lsCoactivos) {
        List<CoactivoDTO> listDto = new ArrayList<CoactivoDTO>();
        CoactivoDTO coactivoDTO = null;
        for (Coactivo entidad : lsCoactivos) {
            coactivoDTO = new CoactivoDTO();
            coactivoDTO = toLevel1DTO(entidad);
            if (entidad.getPersona() != null)
                coactivoDTO.setPersona(ProcesarPersonasHelper.toPersonaLevel1DTO(entidad.getPersona()));
            coactivoDTO
                    .setObligacionCoactivos(ObligacionCoactivoHelper.toListLevel1DTO(entidad.getObligacionCoactivos()));
            listDto.add(coactivoDTO);
        }
        return listDto;
    }
}