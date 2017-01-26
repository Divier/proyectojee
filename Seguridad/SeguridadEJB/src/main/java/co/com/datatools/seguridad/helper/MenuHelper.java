package co.com.datatools.seguridad.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.dto.comun.LlaveValorDto;
import co.com.datatools.seguridad.entidades.RecursoMenu;
import co.com.datatools.util.ObjectToXML;

/**
 * Helper que facilita la conversion entre objetos de Entidad y Dtos para RecursoMenu
 * 
 * @author claudia.rodriguez
 * 
 */
public class MenuHelper {

    /**
     * Transforma el Dto enviado como parametro a su correspondiente entidad asignando los atributos simples
     * 
     * @param dto
     *            Contiene los datos de un Dto de Menu
     * @param entidad
     *            Entidad base sobre la cual se asignan los valores del Dto, si es nula se instancia
     * 
     * @return Entidad de RecursoMenu con los correspondientes datos del Dto
     */
    public RecursoMenu toEntity(MenuDto dto, RecursoMenu entidadBase) {
        RecursoMenu entidad = entidadBase;
        if (entidad == null)
            entidad = new RecursoMenu();
        entidad.setIdRecursoMenu(dto.getIdMenu());
        entidad.setOrden((short) dto.getOrden());
        // Atributos
        HashMap<String, String> mapAtributos = new HashMap<String, String>();
        mapAtributos.put("label", dto.getLabel());
        for (LlaveValorDto llaveValorDto : dto.getAtributos()) {
            mapAtributos.put(llaveValorDto.getId(), llaveValorDto.getValor());
        }
        entidad.setRecursoData(ObjectToXML.objectToXml(mapAtributos));
        return entidad;
    }

    /**
     * Transforma la entidad enviada como parametro a su correspondiente Dto asignando los atributos simples y los datos de su recurso asignado
     * 
     * @param entidad
     *            Contiene los datos de la entidad RecursoMenu
     * @param dtoBase
     *            dto que usa como base para asignar los campos de la entidad, opcional
     * @return Dto MenuDto con los correspodientes datos de la entidad
     */

    public MenuDto toDto(RecursoMenu entidad, MenuDto dtoBase) {
        MenuDto dto = dtoBase;
        if (dto == null)
            dto = new MenuDto();
        dto.setIdMenu(entidad.getIdRecursoMenu());
        dto.setOrden(entidad.getOrden());
        // Atributos
        @SuppressWarnings("unchecked")
        HashMap<String, String> mapAtributos = ObjectToXML.xmlToObject(HashMap.class, entidad.getRecursoData());
        List<LlaveValorDto> atributos = new ArrayList<LlaveValorDto>();
        for (Iterator<Entry<String, String>> iterator = mapAtributos.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> atributo = iterator.next();
            if (!atributo.getKey().equals("label")) {
                LlaveValorDto llaveValorDto = new LlaveValorDto(atributo.getKey(), atributo.getValue());
                atributos.add(llaveValorDto);
            } else {
                dto.setLabel(atributo.getValue());
            }
        }
        dto.setAtributos(atributos);

        // recurso
        RecursoDto recursoDto = new RecursoDto();
        recursoDto.setIdRecurso(entidad.getRecurso().getIdRecurso());
        recursoDto.setNombreRecurso(entidad.getRecurso().getNombre());
        recursoDto.setDescripcion(entidad.getRecurso().getDescripcion());
        dto.setRecurso(recursoDto);

        return dto;
    }
}
