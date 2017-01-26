package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionDTO implements EntidadDtoC2, Cloneable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoViaDTO tipoViaPrincipal;
    private Integer numeroViaPrincipal;
    private NombreViaDTO nombreViaPrincipal;
    private Character letraViaPrincipal;
    private String bisViaPrincipal;
    private Character letraBisViaPrincipal;
    private CardinalidadDireccionDTO cardinalidadViaPrincipal;
    private TipoViaDTO tipoViaSecundaria;
    private Integer numeroViaSecundaria;
    private NombreViaDTO nombreViaSecundaria;
    private Character letraViaSecundaria;
    private String bisViaSecundaria;
    private Character letraBisViaSecundaria;
    private CardinalidadDireccionDTO cardinalidadViaSecundaria;
    private Integer numeroPlacaDomiciliaria;
    private String complemento;
    private String barrio;
    private MunicipioDTO municipio;
    private LocalidadDTO localidad;
    private TipoUbicabilidadDTO tipoUbicabilidad;
    private TipoCoordenadaDTO tipoCoordenada;
    private Double latitud;
    private Double longitud;
    private PaisDTO pais;
    private DepartamentoDTO departamento;

    // --- Constructor
    public DireccionDTO() {
    }

    public DireccionDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoViaDTO getTipoViaPrincipal() {
        return this.tipoViaPrincipal;
    }

    public void setTipoViaPrincipal(TipoViaDTO tipoViaPrincipal) {
        this.tipoViaPrincipal = tipoViaPrincipal;
    }

    public Integer getNumeroViaPrincipal() {
        return this.numeroViaPrincipal;
    }

    public void setNumeroViaPrincipal(Integer numeroViaPrincipal) {
        this.numeroViaPrincipal = numeroViaPrincipal;
    }

    public NombreViaDTO getNombreViaPrincipal() {
        return this.nombreViaPrincipal;
    }

    public void setNombreViaPrincipal(NombreViaDTO nombreViaPrincipal) {
        this.nombreViaPrincipal = nombreViaPrincipal;
    }

    public Character getLetraViaPrincipal() {
        return this.letraViaPrincipal;
    }

    public void setLetraViaPrincipal(Character letraViaPrincipal) {
        this.letraViaPrincipal = letraViaPrincipal;
    }

    public String getBisViaPrincipal() {
        return this.bisViaPrincipal;
    }

    public void setBisViaPrincipal(String bisViaPrincipal) {
        this.bisViaPrincipal = bisViaPrincipal;
    }

    public Character getLetraBisViaPrincipal() {
        return this.letraBisViaPrincipal;
    }

    public void setLetraBisViaPrincipal(Character letraBisViaPrincipal) {
        this.letraBisViaPrincipal = letraBisViaPrincipal;
    }

    public CardinalidadDireccionDTO getCardinalidadViaPrincipal() {
        return this.cardinalidadViaPrincipal;
    }

    public void setCardinalidadViaPrincipal(CardinalidadDireccionDTO cardinalidadViaPrincipal) {
        this.cardinalidadViaPrincipal = cardinalidadViaPrincipal;
    }

    public TipoViaDTO getTipoViaSecundaria() {
        return this.tipoViaSecundaria;
    }

    public void setTipoViaSecundaria(TipoViaDTO tipoViaSecundaria) {
        this.tipoViaSecundaria = tipoViaSecundaria;
    }

    public Integer getNumeroViaSecundaria() {
        return this.numeroViaSecundaria;
    }

    public void setNumeroViaSecundaria(Integer numeroViaSecundaria) {
        this.numeroViaSecundaria = numeroViaSecundaria;
    }

    public NombreViaDTO getNombreViaSecundaria() {
        return this.nombreViaSecundaria;
    }

    public void setNombreViaSecundaria(NombreViaDTO nombreViaSecundaria) {
        this.nombreViaSecundaria = nombreViaSecundaria;
    }

    public Character getLetraViaSecundaria() {
        return this.letraViaSecundaria;
    }

    public void setLetraViaSecundaria(Character letraViaSecundaria) {
        this.letraViaSecundaria = letraViaSecundaria;
    }

    public String getBisViaSecundaria() {
        return this.bisViaSecundaria;
    }

    public void setBisViaSecundaria(String bisViaSecundaria) {
        this.bisViaSecundaria = bisViaSecundaria;
    }

    public Character getLetraBisViaSecundaria() {
        return this.letraBisViaSecundaria;
    }

    public void setLetraBisViaSecundaria(Character letraBisViaSecundaria) {
        this.letraBisViaSecundaria = letraBisViaSecundaria;
    }

    public CardinalidadDireccionDTO getCardinalidadViaSecundaria() {
        return this.cardinalidadViaSecundaria;
    }

    public void setCardinalidadViaSecundaria(CardinalidadDireccionDTO cardinalidadViaSecundaria) {
        this.cardinalidadViaSecundaria = cardinalidadViaSecundaria;
    }

    public Integer getNumeroPlacaDomiciliaria() {
        return this.numeroPlacaDomiciliaria;
    }

    public void setNumeroPlacaDomiciliaria(Integer numeroPlacaDomiciliaria) {
        this.numeroPlacaDomiciliaria = numeroPlacaDomiciliaria;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBarrio() {
        return this.barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public MunicipioDTO getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public LocalidadDTO getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }

    public TipoUbicabilidadDTO getTipoUbicabilidad() {
        return this.tipoUbicabilidad;
    }

    public void setTipoUbicabilidad(TipoUbicabilidadDTO tipoUbicabilidad) {
        this.tipoUbicabilidad = tipoUbicabilidad;
    }

    public TipoCoordenadaDTO getTipoCoordenada() {
        return this.tipoCoordenada;
    }

    public void setTipoCoordenada(TipoCoordenadaDTO tipoCoordenada) {
        this.tipoCoordenada = tipoCoordenada;
    }

    public Double getLatitud() {
        return this.latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return this.longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna la dirección en forma de texto
     * 
     * @return Cadena de texto con los valores actualmente asignados a la dirección
     */
    public String toString() {
        return convertirDireccionAString(true);
    }

    /**
     * Retorna la dirección en forma de texto sin municipio
     * 
     * @return Cadena de texto con los valores actualmente asignados a la dirección
     */
    public String toStringSinMunicipio() {
        return convertirDireccionAString(false);
    }

    /**
     * Convierte direccion con la opcion de municipio
     * 
     * @param conMunicipio
     *            imprime el municipio o no
     * @return Direccion en forma de texto
     */
    private String convertirDireccionAString(boolean conMunicipio) {
        final String ESPACIO = " ";
        final String NUMERAL = "#";
        final String GUION = "-";

        StringBuilder direccion = new StringBuilder();

        /*
         * if (null != getTipoViaPrincipal() && null != getTipoViaPrincipal().getNombre()) { direccion.append(getTipoViaPrincipal().getNombre());
         * direccion.append(ESPACIO); }
         */

        if (null != getNombreViaPrincipal() && null != getNombreViaPrincipal().getNombre()) {
            direccion.append(getNombreViaPrincipal().getNombre());
            direccion.append(ESPACIO);
        }

        if (null != getNumeroViaPrincipal()) {
            direccion.append(getNumeroViaPrincipal());
            direccion.append(ESPACIO);
        }

        if (null != getLetraViaPrincipal()) {
            direccion.append(getLetraViaPrincipal());
            direccion.append(ESPACIO);
        }

        if (null != getBisViaPrincipal()) {
            direccion.append(getBisViaPrincipal());
            direccion.append(ESPACIO);
        }

        if (null != getLetraBisViaPrincipal()) {
            direccion.append(getLetraBisViaPrincipal());
            direccion.append(ESPACIO);
        }

        if (null != getCardinalidadViaPrincipal() && null != getCardinalidadViaPrincipal().getNombre()) {
            direccion.append(getCardinalidadViaPrincipal().getNombre());
            direccion.append(ESPACIO);
        }

        if (null != getTipoViaSecundaria() && null != getTipoViaSecundaria().getNombre()
                && null == getNumeroViaSecundaria()) {
            direccion.append(getTipoViaSecundaria().getNombre());
            direccion.append(ESPACIO);
        }

        if (null != getNombreViaSecundaria() && null != getNombreViaSecundaria().getNombre()) {
            direccion.append(getNombreViaSecundaria().getNombre());
            direccion.append(ESPACIO);
        }

        if (null != getNumeroViaSecundaria()) {
            direccion.append(NUMERAL);
            direccion.append(ESPACIO);
            direccion.append(getNumeroViaSecundaria());
            direccion.append(ESPACIO);
        }

        if (null != getLetraViaSecundaria()) {
            direccion.append(getLetraViaSecundaria());
            direccion.append(ESPACIO);
        }

        if (null != getBisViaSecundaria()) {
            direccion.append(getBisViaSecundaria());
            direccion.append(ESPACIO);
        }

        if (null != getLetraBisViaSecundaria()) {
            direccion.append(getLetraBisViaSecundaria());
            direccion.append(ESPACIO);
        }

        if (null != getNumeroPlacaDomiciliaria() && null != getNumeroViaSecundaria()) {
            direccion.append(GUION);
            direccion.append(ESPACIO);
            direccion.append(getNumeroPlacaDomiciliaria());
            direccion.append(ESPACIO);
        }

        if (null != getCardinalidadViaSecundaria() && null != getCardinalidadViaSecundaria().getNombre()) {
            direccion.append(getCardinalidadViaSecundaria().getNombre());
            direccion.append(ESPACIO);
        }

        if (null != getComplemento()) {
            direccion.append(getComplemento());
            direccion.append(ESPACIO);
        }

        if (null != getLocalidad() && null != getLocalidad().getNombre()) {
            direccion.append(getLocalidad().getNombre());
            direccion.append(ESPACIO);
        }

        if (conMunicipio && null != getMunicipio()) {
            if (null != getMunicipio().getNombre()) {
                direccion.append(getMunicipio().getNombre());
                direccion.append(ESPACIO);
            }

            if (null != getMunicipio().getDepartamento()) {
                if (null != getMunicipio().getDepartamento().getNombre()) {
                    direccion.append(getMunicipio().getDepartamento().getNombre());
                    direccion.append(ESPACIO);
                }

                if (null != getMunicipio().getDepartamento().getPais()
                        && null != getMunicipio().getDepartamento().getPais().getNombre()) {
                    direccion.append(getMunicipio().getDepartamento().getPais().getNombre());
                    direccion.append(ESPACIO);
                }
            }
        }

        return direccion.toString();
    }

    public DireccionDTO clone() {
        DireccionDTO direccionCloneDTO = new DireccionDTO();

        direccionCloneDTO.setId(this.getId());

        direccionCloneDTO.setNumeroViaPrincipal(this.getNumeroViaPrincipal());
        direccionCloneDTO.setLetraViaPrincipal(this.getLetraViaPrincipal());
        direccionCloneDTO.setBisViaPrincipal(this.getBisViaPrincipal());
        direccionCloneDTO.setLetraBisViaPrincipal(this.getLetraBisViaPrincipal());
        direccionCloneDTO.setNumeroViaSecundaria(this.getNumeroViaSecundaria());
        direccionCloneDTO.setLetraViaSecundaria(this.getLetraViaSecundaria());
        direccionCloneDTO.setBisViaSecundaria(this.getBisViaSecundaria());
        direccionCloneDTO.setLetraBisViaSecundaria(this.getLetraBisViaSecundaria());
        direccionCloneDTO.setNumeroPlacaDomiciliaria(this.getNumeroPlacaDomiciliaria());
        direccionCloneDTO.setComplemento(this.getComplemento());
        direccionCloneDTO.setBarrio(this.getBarrio());
        direccionCloneDTO.setLatitud(this.getLatitud());
        direccionCloneDTO.setLongitud(this.getLongitud());

        if (this.tipoViaPrincipal != null) {
            TipoViaDTO tipoViaPrincipalDTO = new TipoViaDTO();
            tipoViaPrincipalDTO.setCodigo(this.getTipoViaPrincipal().getCodigo());
            tipoViaPrincipalDTO.setNombre(this.getTipoViaPrincipal().getNombre());
            direccionCloneDTO.setTipoViaPrincipal(tipoViaPrincipalDTO);
        }

        if (this.nombreViaPrincipal != null) {
            NombreViaDTO nombreViaPrincipalDTO = new NombreViaDTO();
            nombreViaPrincipalDTO.setCodigo(this.getNombreViaPrincipal().getCodigo());
            nombreViaPrincipalDTO.setNombre(this.getNombreViaPrincipal().getNombre());
            direccionCloneDTO.setNombreViaPrincipal(nombreViaPrincipalDTO);
        }

        if (this.cardinalidadViaPrincipal != null) {
            CardinalidadDireccionDTO cardinalidadViaPrincipalDTO = new CardinalidadDireccionDTO();
            cardinalidadViaPrincipalDTO.setCodigo(this.getCardinalidadViaPrincipal().getCodigo());
            cardinalidadViaPrincipalDTO.setNombre(this.getCardinalidadViaPrincipal().getNombre());
            cardinalidadViaPrincipalDTO.setAbreviatura(this.getCardinalidadViaPrincipal().getAbreviatura());
            direccionCloneDTO.setCardinalidadViaPrincipal(cardinalidadViaPrincipalDTO);
        }

        if (this.tipoViaSecundaria != null) {
            TipoViaDTO tipoViaSecundariaDTO = new TipoViaDTO();
            tipoViaSecundariaDTO.setCodigo(this.getTipoViaSecundaria().getCodigo());
            tipoViaSecundariaDTO.setNombre(this.getTipoViaSecundaria().getNombre());
            direccionCloneDTO.setTipoViaSecundaria(tipoViaSecundariaDTO);
        }

        if (this.nombreViaSecundaria != null) {
            NombreViaDTO nombreViaSecundariaDTO = new NombreViaDTO();
            nombreViaSecundariaDTO.setCodigo(this.getNombreViaSecundaria().getCodigo());
            nombreViaSecundariaDTO.setNombre(this.getNombreViaSecundaria().getNombre());
            direccionCloneDTO.setNombreViaSecundaria(nombreViaSecundariaDTO);
        }

        if (this.cardinalidadViaSecundaria != null) {
            CardinalidadDireccionDTO cardinalidadViaSecundariaDTO = new CardinalidadDireccionDTO();
            cardinalidadViaSecundariaDTO.setCodigo(this.getCardinalidadViaSecundaria().getCodigo());
            cardinalidadViaSecundariaDTO.setNombre(this.getCardinalidadViaSecundaria().getNombre());
            cardinalidadViaSecundariaDTO.setAbreviatura(this.getCardinalidadViaSecundaria().getAbreviatura());
            direccionCloneDTO.setCardinalidadViaSecundaria(cardinalidadViaSecundariaDTO);
        }

        if (this.municipio != null) {
            PaisDTO paisDTO = new PaisDTO();
            if (this.getMunicipio().getDepartamento() != null) {
                paisDTO.setId(this.getMunicipio().getDepartamento().getPais().getId());
                paisDTO.setCodigo(this.getMunicipio().getDepartamento().getPais().getCodigo());
                paisDTO.setNombre(this.getMunicipio().getDepartamento().getPais().getNombre());
                DepartamentoDTO departamentoDTO = new DepartamentoDTO();
                departamentoDTO.setId(this.getMunicipio().getDepartamento().getId());
                departamentoDTO.setCodigo(this.getMunicipio().getDepartamento().getCodigo());
                departamentoDTO.setNombre(this.getMunicipio().getDepartamento().getNombre());
                departamentoDTO.setPais(paisDTO);

                MunicipioDTO municipioDTO = new MunicipioDTO();
                municipioDTO.setId(this.getMunicipio().getId());
                municipioDTO.setCodigo(this.getMunicipio().getCodigo());
                municipioDTO.setNombre(this.getMunicipio().getNombre());
                municipioDTO.setDepartamento(departamentoDTO);
                direccionCloneDTO.setMunicipio(municipioDTO);

                if (this.localidad != null) {
                    LocalidadDTO localidadDTO = new LocalidadDTO();
                    localidadDTO.setId(this.getLocalidad().getId());
                    localidadDTO.setCodigo(this.getLocalidad().getCodigo());
                    localidadDTO.setNombre(this.getLocalidad().getNombre());
                    localidadDTO.setMunicipio(municipioDTO);
                    direccionCloneDTO.setLocalidad(localidadDTO);
                }
            }
        }

        if (this.tipoUbicabilidad != null) {
            TipoUbicabilidadDTO tipoUbicabilidadDTO = new TipoUbicabilidadDTO();
            tipoUbicabilidadDTO.setCodigo(this.getTipoUbicabilidad().getCodigo());
            tipoUbicabilidadDTO.setNombre(this.getTipoUbicabilidad().getNombre());
            direccionCloneDTO.setTipoUbicabilidad(tipoUbicabilidadDTO);
        }

        if (this.tipoCoordenada != null) {
            TipoCoordenadaDTO tipoCoordenadaDTO = new TipoCoordenadaDTO();
            tipoCoordenadaDTO.setCodigo(this.getTipoCoordenada().getCodigo());
            tipoCoordenadaDTO.setNombre(this.getTipoCoordenada().getNombre());
            direccionCloneDTO.setTipoCoordenada(tipoCoordenadaDTO);
        }

        if (this.pais != null) {
            PaisDTO paisDTO = new PaisDTO();
            paisDTO.setId(this.getPais().getId());
            paisDTO.setCodigo(this.getPais().getCodigo());
            paisDTO.setNombre(this.getPais().getNombre());
            direccionCloneDTO.setPais(paisDTO);
        }

        if (this.departamento != null) {
            DepartamentoDTO departamentoDTO = new DepartamentoDTO();
            departamentoDTO.setId(this.getDepartamento().getId());
            departamentoDTO.setCodigo(this.getDepartamento().getCodigo());
            departamentoDTO.setNombre(this.getDepartamento().getNombre());
            direccionCloneDTO.setDepartamento(departamentoDTO);
        }

        return direccionCloneDTO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof DireccionDTO) {
                boolean iguales = true;
                DireccionDTO direccionDTO = (DireccionDTO) obj;
                // valida si el complemento es diferente
                if (complemento != null && direccionDTO.getComplemento() != null) {
                    if (!complemento.equalsIgnoreCase(direccionDTO.getComplemento())) {
                        return false;
                    }
                }

                if (departamento != null && direccionDTO.departamento != null) {
                    if (departamento.getId() != null && direccionDTO.departamento.getId() != null) {
                        if (!departamento.getId().equals(direccionDTO.departamento.getId())) {
                            return false;
                        }
                    }
                }

                if (municipio != null && direccionDTO.getMunicipio() != null) {
                    if (municipio.getId() != null && direccionDTO.getMunicipio().getId() != null) {
                        if (!municipio.getId().equals(direccionDTO.getMunicipio().getId())) {
                            return false;
                        }
                    }
                }

                if (localidad != null && direccionDTO.localidad != null) {
                    if (localidad.getId() != null && direccionDTO.getLocalidad().getId() != null) {
                        if (!localidad.getId().equals(direccionDTO.getLocalidad().getId())) {
                            return false;
                        }
                    }
                }

                return true;

            }
        }
        return false;
    }

}