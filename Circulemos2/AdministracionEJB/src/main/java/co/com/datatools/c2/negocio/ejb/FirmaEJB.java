package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.datatools.c2.dto.CapturaFirmaDTO;
import co.com.datatools.c2.dto.FirmaPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.FirmaPersona;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorFirma;
import co.com.datatools.c2.negocio.helpers.v2.FirmaPersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILFirma;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.GenericDao;

@Stateless(name = " FirmaEJB")
@LocalBean
public class FirmaEJB implements ILFirma, IRFirma {

    @EJB
    private IRPersona personaEjb;

    @EJB
    private IRRepositorioArchivo iRepositorioArchivoEjb;

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public Long registrarFirma(CapturaFirmaDTO firmaDTO, boolean actPersona) throws CirculemosNegocioException {
        boolean resultado = false;
        /* Se valida los datos obligatorios */
        checkNotNull(firmaDTO.getFirma(), ErrorFirma.RegistrarFirma.ADM_070001);
        checkNotNull(firmaDTO.getPersonaDTO(), ErrorFirma.RegistrarFirma.ADM_070002);

        try {
            /* Se crea el DTO de firmaPersona */
            PersonaDTO persona = personaEjb.consultarPersona(firmaDTO.getPersonaDTO().getId());
            if (persona.getDireccionPersonaList() != null && !persona.getDireccionPersonaList().isEmpty()
                    && persona.getDireccionPersonaList().get(0) != null
                    && persona.getDireccionPersonaList().get(0).getDireccion() != null
                    && persona.getDireccionPersonaList().get(0).getDireccion().getTipoUbicabilidad() != null
                    && persona.getDireccionPersonaList().get(0).getDireccion().getTipoUbicabilidad()
                            .getCodigo() == null) {
                /* Error */
                persona.setDireccionPersonaList(firmaDTO.getPersonaDTO().getDireccionPersonaList());
            }

            FirmaPersonaDTO firmaPersonaDTO = new FirmaPersonaDTO();
            firmaPersonaDTO.setFechaRegistro(Calendar.getInstance().getTime());
            byte[] firmaByte = Utilidades.getBase64ImgBytes(firmaDTO.getFirma());

            /* Se carga el DTO con la informacion del archivo de la firma y se guarda el archivo */
            ArchivoTransportableDTO archivoDTO = new ArchivoTransportableDTO();
            archivoDTO.setContenido(firmaByte);
            archivoDTO.setNombre(persona.getNumeroIdentificacion() + firmaPersonaDTO.getFechaRegistro() + ".jpg");
            String DocFirma = iRepositorioArchivoEjb.registrarDocumento(EnumCategoriaDocumento.FIRMA_PERSONA,
                    archivoDTO);
            Long idDocFirma = Long.parseLong(DocFirma);

            /* Guarda la firma con le Id del documento de la firma */
            firmaPersonaDTO.setNumeroImagen(idDocFirma);
            firmaPersonaDTO.setPersona(persona);
            FirmaPersona Fpersona = FirmaPersonaHelper.toLevel1Entity(firmaPersonaDTO, null);
            em.persist(Fpersona);
            // Se valida si es necesario actualizar persona
            if (actPersona) {
                /* Se actualiza la la fecha de la persona */
                persona.setFechaUltimaActualizacion(firmaPersonaDTO.getFechaRegistro());
                resultado = personaEjb.modificarPersona(persona, null);
                if (resultado) {
                    return idDocFirma;
                } else {
                    throw new CirculemosNegocioException(ErrorFirma.RegistrarFirma.ADM_070003);
                }
            } else {
                return idDocFirma;
            }
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorFirma.RegistrarFirma.ADM_070004);
        } catch (IOException e) {
            throw new CirculemosNegocioException(ErrorFirma.RegistrarFirma.ADM_070005);
        }
    }

    @Override
    public Long consultarNumeroFirma(long idPersona) {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idPersona", idPersona);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f FROM FirmaPersona f");
        sql.append(" WHERE f.persona.id =:idPersona");

        GenericDao<FirmaPersona> resultado = new GenericDao<>(FirmaPersona.class, em);
        List<FirmaPersona> results = resultado.buildAndExecuteQuery(sql, filtros);

        if (results != null && !results.isEmpty()) {
            Long numeroFirma = results.get(0).getNumeroImagen();
            return numeroFirma;
        } else {
            return null;
        }
    }

    @Override
    public String registrarFirmaFuncionario(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException {
        /* Se valida los datos obligatorios */
        checkNotNull(firmaDTO.getFirma(), ErrorFirma.RegistrarFirma.ADM_070001);

        try {

            FirmaPersonaDTO firmaPersonaDTO = new FirmaPersonaDTO();
            firmaPersonaDTO.setFechaRegistro(Calendar.getInstance().getTime());
            byte[] firmaByte = Utilidades.getBase64ImgBytes(firmaDTO.getFirma());

            /* Se carga el DTO con la informacion del archivo de la firma y se guarda el archivo */
            ArchivoTransportableDTO archivoDTO = new ArchivoTransportableDTO();
            archivoDTO.setContenido(firmaByte);
            archivoDTO.setNombre(firmaDTO.getCargo() + firmaPersonaDTO.getFechaRegistro() + ".jpg");
            return iRepositorioArchivoEjb.registrarDocumento(EnumCategoriaDocumento.FIRMA_PERSONA, archivoDTO);
        } catch (CirculemosAlertaException e) {
            throw new CirculemosNegocioException(ErrorFirma.RegistrarFirma.ADM_070004);
        } catch (IOException e) {
            throw new CirculemosNegocioException(ErrorFirma.RegistrarFirma.ADM_070005);
        }
    }

    @Override
    public String registrarFirmaAgente(CapturaFirmaDTO firmaDTO) throws CirculemosNegocioException {
        firmaDTO.setCargo("AGENTE" + firmaDTO.getPlacaAgente());
        return registrarFirmaFuncionario(firmaDTO);
    }
}
