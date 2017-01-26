package co.com.datatools.c2.dto.parametrizacion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import co.com.datatools.c2.dto.parametrizacion.CampoConfiguracionDTO.TipoCampoConfiguracion;

/**
 * Describe la estructura de una configuracion (regla de negocio)
 * 
 * @author Felipe.Martinez
 */
public class TipoConfiguracionDTO extends ItemCatalogoDTO {

    private static final long serialVersionUID = -7326750998329700731L;

    /**
     * Listado de campos referenciados por el codigo del campo de la configuracion<br>
     * K: codigo campo<br>
     * V: informacion del campo<br>
     */
    private final LinkedHashMap<String, CampoConfiguracionDTO> mapaCampos;

    /**
     * Lista que referencia los rangos definidos en la configuracion
     */
    private final List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> rangos = new ArrayList<>(1);
    /**
     * 
     */
    private final List<CampoConfiguracionDTO> entradas = new ArrayList<>(1);
    /**
     */
    private final List<CampoConfiguracionDTO> salidas = new ArrayList<>(1);

    private int numeroEntradas = 0;
    private int numeroSalidas = 0;
    private boolean contieneCatalogos = false;
    private boolean contieneCatalogosCompuestos = false;
    private boolean contieneRangosEntrada = false;

    // --- Constructor
    public TipoConfiguracionDTO(Integer id, String codigo, String descripcion,
            LinkedHashMap<String, CampoConfiguracionDTO> campos) {
        super(id);
        super.setCodigo(codigo);
        super.setDescripcion(descripcion);
        this.mapaCampos = campos;

        CampoConfiguracionDTO campoRangoIni = null;
        for (CampoConfiguracionDTO campo : listaCampos()) {
            if (campo.getTipo().seEncuentraEn(TipoCampoConfiguracion.CATALOGOSIMPLE,
                    TipoCampoConfiguracion.CATALOGOINDEPENDIENTE)) {
                contieneCatalogos = true;
            } else if (campo.getTipo().equals(TipoCampoConfiguracion.CATALOGOCOMPUESTO)) {
                contieneCatalogosCompuestos = true;
            }

            if (campo.isEntrada())
                entradas.add(campo);
            else
                salidas.add(campo);

            if (campo.getAttrRngCodigoRango() != null) {
                if (campo.isEntrada())
                    contieneRangosEntrada = true;
                if (campoRangoIni == null) {
                    campoRangoIni = campo;
                } else {
                    rangos.add(new MutablePair<>(campoRangoIni, campo));
                    campoRangoIni = null;
                }
            }
        }
        numeroEntradas = entradas.size();
        numeroSalidas = salidas.size();
    }

    // --- Getters-Setters
    public LinkedHashMap<String, CampoConfiguracionDTO> getMapaCampos() {
        return mapaCampos;
    }

    /**
     * Permite obtener el listado de campos de la configuracion, con el order definido en el xml
     * 
     * @return copia del listado de campos obtenidos del plantilla de la configuracion
     */
    public List<CampoConfiguracionDTO> listaCampos() {
        return new ArrayList<>(mapaCampos.values());
    }

    public int getNumeroEntradas() {
        return numeroEntradas;
    }

    public int getNumeroSalidas() {
        return numeroSalidas;
    }

    public boolean isContieneCatalogos() {
        return contieneCatalogos;
    }

    public List<Pair<CampoConfiguracionDTO, CampoConfiguracionDTO>> getRangos() {
        return rangos;
    }

    public List<CampoConfiguracionDTO> getEntradas() {
        return entradas;
    }

    public List<CampoConfiguracionDTO> getSalidas() {
        return salidas;
    }

    public boolean isContieneRangosEntrada() {
        return contieneRangosEntrada;
    }

    public boolean isContieneCatalogosCompuestos() {
        return contieneCatalogosCompuestos;
    }

}
