package co.com.datatools.seguridad.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;

import co.com.datatools.seguridad.dto.autenticacion.ParametroSeguridadDto;
import co.com.datatools.seguridad.dto.comun.InstalacionDto;
import co.com.datatools.seguridad.entidades.Aplicacion;
import co.com.datatools.seguridad.entidades.EstadoPassword;
import co.com.datatools.seguridad.entidades.EstadoUsuario;
import co.com.datatools.seguridad.entidades.TipoDato;
import co.com.datatools.seguridad.entidades.Usuario;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.GenericDao;

/**
 * Implementacion de operaciones asociadas a catalogos del modulo de seguridad
 * 
 * @author Felipe Martinez
 */
@Stateless(name = "CatalogosSeguridadEJB")
@LocalBean
public class CatalogosSeguridadEJB implements IRCatalogosSeguridad {

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @EJB
    private ParametrosSeguridadEJB paramEjb;

    @EJB
    private UsuarioEJB usuarioEJB;

    @EJB
    private AutenticacionEJB autenticacionEJB;

    @Override
    public Map<String, String> consultarAplicaciones() {
        Map<String, String> resp = new HashMap<>();
        GenericDao<Aplicacion> appDao = new GenericDao<>(Aplicacion.class, em);
        final List<Aplicacion> result = appDao.findByAttributes(null);
        for (Aplicacion aplicacion : result) {
            resp.put(aplicacion.getNombreAplicacion(), "" + aplicacion.getIdAplicacion());
        }
        return resp;
    }

    @Override
    public String consultarIdAplicacion(String nombreAplicacion) {
        GenericDao<Aplicacion> appDao = new GenericDao<>(Aplicacion.class, em);
        final Aplicacion app = appDao.findUniqueByAttribute("nombreAplicacion", nombreAplicacion);
        if (app == null) {
            throw new SeguridadRuntimeException("");
        } else {
            return "" + app.getIdAplicacion();
        }

    }

    @Override
    public Map<String, String> consultarEstadosUsuario() {
        TypedQuery<EstadoUsuario> q = em.createNamedQuery(EstadoUsuario.SQ_ESTADOUSU_ALL, EstadoUsuario.class);
        List<EstadoUsuario> result = q.getResultList();
        Map<String, String> resp = new HashMap<>();
        for (EstadoUsuario estado : result) {
            resp.put(estado.getNombre(), String.valueOf(estado.getIdEstado()));
        }
        return resp;
    }

    @Override
    public String consultarIdEstadoUsuario(String nombreEstado) {
        GenericDao<EstadoUsuario> appDao = new GenericDao<>(EstadoUsuario.class, em);
        final EstadoUsuario estado = appDao.findUniqueByAttribute("nombre", nombreEstado);
        if (estado == null) {
            throw new SeguridadRuntimeException("");
        } else {
            return "" + estado.getIdEstado();
        }

    }

    @Override
    public String consultarNombreEstadoUsuario(Integer id) {
        GenericDao<EstadoUsuario> appDao = new GenericDao<>(EstadoUsuario.class, em);
        final EstadoUsuario estado = appDao.findUniqueByAttribute("idEstado", id);
        if (estado == null) {
            throw new SeguridadRuntimeException("");
        } else {
            return "" + estado.getNombre();
        }

    }

    @Override
    public String consultarIdEstadoPassword(String nombreEstado) {
        GenericDao<EstadoPassword> appDao = new GenericDao<>(EstadoPassword.class, em);
        final EstadoPassword estado = appDao.findUniqueByAttribute("nombre", nombreEstado);
        if (estado == null) {
            throw new SeguridadRuntimeException("");
        } else {
            return "" + estado.getIdEstado();
        }

    }

    @Override
    public Map<String, String> consultarTiposDato() {
        Map<String, String> tiposDatos = new HashMap<>();
        TypedQuery<TipoDato> query = em.createNamedQuery(TipoDato.SQ_TIPODATO_ALL, TipoDato.class);
        List<TipoDato> resultado = query.getResultList();
        if (!CollectionUtils.isEmpty(resultado)) {
            for (TipoDato tipoDato : resultado) {
                tiposDatos.put(tipoDato.getNombreTipoDato(), "" + tipoDato.getIdTipoDato());
            }
        }
        return tiposDatos;
    }

    @Override
    public void guardarDatosInstalacion(InstalacionDto dto) throws SeguridadException {

        GenericDao<Usuario> usuarioDao = new GenericDao<>(Usuario.class, em);
        final Usuario usrAdmin = usuarioDao.findUniqueByAttribute("login",
                ConstantesSeguridad.LOGIN_USUARIO_SUPER_ADMIN);
        usrAdmin.setNombre(dto.getNombresSuperAdmin());
        usrAdmin.setApellido(dto.getApellidosSuperAdmin());
        usrAdmin.setEmail(dto.getEmailSuperAdmin());
        usrAdmin.setFechaInicioUsuario(new Date());
        em.merge(usrAdmin);// Actualizar los datos del usuario admin

        autenticacionEJB.cambiarPassword(ConstantesSeguridad.LOGIN_USUARIO_SUPER_ADMIN, null, dto.getPwSuperAdmin()); // Actualizar la contraseña del
                                                                                                                      // usuario admin

        for (ParametroSeguridadDto parametroSeguridadDto : dto.getParametrosConfiguracion()) { // Actualizar todos los parametros de configuracion
            paramEjb.actualizarParametroSeguridad(parametroSeguridadDto);
        }

        final ParametroSeguridadDto param = new ParametroSeguridadDto();
        param.setNombre(EnumParametro.INSTALADOR_EJECUTADO.toString());
        param.setValor(ConstantesSeguridad.VALOR_SI);
        paramEjb.actualizarParametroSeguridad(param); // Actualizar parametro de instalador ejecutado
    }
}
