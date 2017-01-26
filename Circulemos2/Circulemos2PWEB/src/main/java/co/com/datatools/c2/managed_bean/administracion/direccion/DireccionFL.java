package co.com.datatools.c2.managed_bean.administracion.direccion;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.NombreViaDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.TipoViaDTO;

public class DireccionFL implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String DIR_FL = "dirFL";

    private DireccionDTO direccionDTO;

    public DireccionFL() {
        limpiarDireccion();
    }

    public void limpiarDireccion() {
        direccionDTO = new DireccionDTO();
        direccionDTO.setTipoUbicabilidad(new TipoUbicabilidadDTO());
        limpiarUbicacion();
        limpiarVias();
    }

    public void limpiarUbicacion() {
        direccionDTO.setPais(new PaisDTO());
        direccionDTO.setDepartamento(new DepartamentoDTO());
        direccionDTO.setMunicipio(new MunicipioDTO());
        direccionDTO.setLocalidad(new LocalidadDTO());
    }

    public void limpiarVias() {
        limpiarViaPrincipal(true);
        limpiarViaSecundaria(true);
    }

    public void limpiarViaPrincipal(boolean limpiarTipoVia) {
        if (limpiarTipoVia)
            direccionDTO.setTipoViaPrincipal(new TipoViaDTO());
        direccionDTO.setNombreViaPrincipal(new NombreViaDTO());
        direccionDTO.setCardinalidadViaPrincipal(new CardinalidadDireccionDTO());
        direccionDTO.setNumeroViaPrincipal(null);
        direccionDTO.setLetraViaPrincipal(null);
        direccionDTO.setBisViaPrincipal(null);
        direccionDTO.setLetraBisViaPrincipal(null);
    }

    public void limpiarViaSecundaria(boolean limpiarTipoVia) {
        if (limpiarTipoVia)
            direccionDTO.setTipoViaSecundaria(new TipoViaDTO());
        direccionDTO.setNombreViaSecundaria(new NombreViaDTO());
        direccionDTO.setCardinalidadViaSecundaria(new CardinalidadDireccionDTO());
        direccionDTO.setNumeroViaSecundaria(null);
        direccionDTO.setLetraViaSecundaria(null);
        direccionDTO.setBisViaSecundaria(null);
        direccionDTO.setLetraBisViaSecundaria(null);
    }

    public DireccionDTO getDireccionDTO() {
        return direccionDTO;
    }

    public void setDireccionDTO(DireccionDTO direccionDTO) {
        this.direccionDTO = direccionDTO;
    }

}
