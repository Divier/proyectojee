package co.com.datatools.c2.managed_bean.administracion.soporte;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionSoporteDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionProcedure;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Se encarga de ejecutar los procedure de la aplicacion
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class AdminProcedureMB extends AbstractC2ManagedBean {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdminProcedureMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelAdminProcedure";
    private static final String SALTO_LINEA = "\n";
    private static final String DOS_PUNTOS = ":";
    private static final Integer SALTOS_POS = 3;
    private static final String ALIAS = "as";

    @EJB
    private IRAdministracionProcedure iRAdminProcedure;

    private ConfiguracionSoporteDTO procedimiento;

    public void ejecutarProcedure() {
        LOGGER.debug("AdminProcedureMB::ejecutarProcedure()");
        AdminProcedureHolderFL adminProcedureHFL = findFlowObject(AdminProcedureHolderFL.class,
                AdminProcedureHolderFL.NOMBRE_BEAN);
        try {
            List<Object[]> lsValrespuesta = iRAdminProcedure.ejecutarProcedure(adminProcedureHFL.getSoporte(),
                    adminProcedureHFL.getDatosEntrada());
            if (lsValrespuesta != null && !lsValrespuesta.isEmpty()) {
                adminProcedureHFL.setRespuesta(formatarRespuesta(lsValrespuesta));
                addInfoMessage(NOMBRE_BUNDLE, "msg_resultado");
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void establecerDescripcion(Integer idSoporte) {
        LOGGER.debug("AdminProcedureMB::establecerDescripcion(Integer)");
        AdminProcedureHolderFL adminProcedureHFL = findFlowObject(AdminProcedureHolderFL.class,
                AdminProcedureHolderFL.NOMBRE_BEAN);
        List<ConfiguracionSoporteDTO> cSoporteDTO = iRAdminProcedure.consultarConfiguracionProcedure(idSoporte);
        if (cSoporteDTO != null && !cSoporteDTO.isEmpty()) {
            for (ConfiguracionSoporteDTO cSoporte : cSoporteDTO) {
                adminProcedureHFL.setDescripcion(cSoporte.getSoporte().getDescripcion());
                setProcedimiento(cSoporte);
            }
        }
    }

    private String formatarRespuesta(List<Object[]> lsValrespuesta) {
        LOGGER.debug("AdminProcedureMB::formatarRespuesta(List<Object[]>)");
        StringBuilder sb = new StringBuilder();
        if (getProcedimiento() != null && getProcedimiento().getRespuesta() != null) {
            String[] aRespuesta = getProcedimiento().getRespuesta().split(",");
            if (lsValrespuesta != null && !lsValrespuesta.isEmpty()) {
                if (lsValrespuesta.get(0) instanceof Object[]) { // Si el SP retorna varios datos
                    for (Object[] aValores : lsValrespuesta) {
                        for (int indice = 0; indice < aValores.length; indice++) {
                            aRespuesta[indice] = aRespuesta[indice].toLowerCase();
                            String campoRespuesta = aRespuesta[indice]
                                    .substring(aRespuesta[indice].indexOf(ALIAS) + SALTOS_POS);
                            if (aValores[indice] instanceof String) {
                                sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                        .append(((String) aValores[indice])).append(SALTO_LINEA));
                            } else if (aValores[indice] instanceof Integer) {
                                sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                        .append(((Integer) aValores[indice])).append(SALTO_LINEA));
                            } else if (aValores[indice] instanceof Long) {
                                sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                        .append(((Long) aValores[indice])).append(SALTO_LINEA));
                            } else if (aValores[indice] instanceof BigInteger) {
                                sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                        .append(((BigInteger) aValores[indice])).append(SALTO_LINEA));
                            } else if (aValores[indice] instanceof BigDecimal) {
                                sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                        .append(((BigDecimal) aValores[indice])).append(SALTO_LINEA));
                            }
                        }
                    }
                } else { // Si el SP retorna un solo dato
                    String respuesta = null;
                    if (aRespuesta != null && aRespuesta.length > 0) {
                        respuesta = aRespuesta[0];
                    }
                    for (Object valor : lsValrespuesta) {
                        respuesta = respuesta.toLowerCase();
                        String campoRespuesta = respuesta.substring(respuesta.indexOf(ALIAS) + SALTOS_POS);
                        if (valor instanceof String) {
                            sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                    .append(((String) valor)).append(SALTO_LINEA));
                        } else if (valor instanceof Integer) {
                            sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                    .append(((Integer) valor)).append(SALTO_LINEA));
                        } else if (valor instanceof Long) {
                            sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                    .append(((Long) valor)).append(SALTO_LINEA));
                        } else if (valor instanceof BigInteger) {
                            sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                    .append(((BigInteger) valor)).append(SALTO_LINEA));
                        } else if (valor instanceof BigDecimal) {
                            sb.append(new StringBuilder().append(campoRespuesta).append(DOS_PUNTOS)
                                    .append(((BigDecimal) valor)).append(SALTO_LINEA));
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public ConfiguracionSoporteDTO getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ConfiguracionSoporteDTO procedimiento) {
        this.procedimiento = procedimiento;
    }
}