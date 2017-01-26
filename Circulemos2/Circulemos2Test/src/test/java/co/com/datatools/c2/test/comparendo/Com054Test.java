package co.com.datatools.c2.test.comparendo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoCargueArchivoNotificacionDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeraciones.EnumCampoArchivoAcuse;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.ejb.aud.UsuarioSesionEJB;
import co.com.datatools.c2.negocio.helpers.extencion.SolicitudPruebasBackHelperExtend;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.test.BaseTest;
import co.com.datatools.c2.test.cartera.Car003Test;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.archivo.EnumTipoArchivo;
import co.com.datatools.util.file.archivo.FilaArchivoDTO;
import co.com.datatools.util.file.archivo.FilaArchivoExcelDTO;

@RunWith(Arquillian.class)
public class Com054Test extends BaseTest {

    private static final int CODIGO_ORGANISMO = 11001;
    private static final Logger logger = Logger.getLogger(Car003Test.class.getName());
    private static final String[] scripts = { "scripts/pruebas/comparendo/COM_054001.sql",
            "scripts/pruebas/comparendo/COM_054002.sql", };

    @EJB
    private IRComparendo iComparendo;
    @EJB
    private UsuarioSesionEJB usuarioEJB;

    @Before
    public void before() {
        ejecutarScriptsSQL(scripts[0]);
    }

    @After
    public void after() {
        ejecutarScriptsSQL(scripts[1]);
    }

    /**
     * TEST001: NO SE NOTIFICARON COMPARENDOS
     */
    @Test
    public void test001CargarArchivoNotificacion() {
        try {
            ResultadoCargueArchivoNotificacionDTO resultado = iComparendo.cargarArchivoNotificacion(
                    CODIGO_ORGANISMO,
                    crearArchivoTransportable(Arrays.asList(crearEncabezadoArchivo()),
                            "test001CargarArchivoNotificacion.xls"));

            Assert.assertTrue(resultado.getCantidadRegistros() == 0);
        } catch (CirculemosNegocioException e) {
            logger.debug("ERROR test001CargarArchivoNotificacion");
            Assert.fail("ERROR test001CargarArchivoNotificacion");
        }
    }

    /**
     * TEST002: LOS COMPARENDOS A NOTIFICAR SON INVALIDOS
     */
    @Test
    public void test002CargarArchivoNotificacion() {
        try {
            FilaArchivoExcelDTO filaArchivo = new FilaArchivoExcelDTO();
            filaArchivo.setHoja(0);
            filaArchivo.getCeldas().add("2015-01-01");
            filaArchivo.getCeldas().add(null);
            filaArchivo.getCeldas().add("11001000000000000018");
            filaArchivo.getCeldas().add("2015-11-26 00:00:00");

            ResultadoCargueArchivoNotificacionDTO resultado = iComparendo.cargarArchivoNotificacion(
                    CODIGO_ORGANISMO,
                    crearArchivoTransportable(Arrays.asList(crearEncabezadoArchivo(), filaArchivo),
                            "test002CargarArchivoNotificacion.xls"));

            Assert.assertTrue(resultado.getCantidadRegistros() > 0);
            Assert.assertTrue(resultado.getComparendosInvalidos() == resultado.getCantidadRegistros());
        } catch (CirculemosNegocioException e) {
            logger.debug("ERROR test002CargarArchivoNotificacion");
            Assert.fail("ERROR test002CargarArchivoNotificacion");
        }
    }

    /**
     * TEST003: SE NOTIFICARON ALGUNOS COMPARENDOS
     */
    @Test
    public void test003CargarArchivoNotificacion() {
        try {
            FilaArchivoExcelDTO filaArchivo1 = new FilaArchivoExcelDTO();
            filaArchivo1.setHoja(0);
            filaArchivo1.getCeldas().add("2015-12-01");
            filaArchivo1.getCeldas().add(null);
            filaArchivo1.getCeldas().add("11001000000000000018");
            filaArchivo1.getCeldas().add("2015-11-26 00:00:00");

            FilaArchivoExcelDTO filaArchivo2 = new FilaArchivoExcelDTO();
            filaArchivo2.setHoja(0);
            filaArchivo2.getCeldas().add("01/12/2015");
            filaArchivo2.getCeldas().add(null);
            filaArchivo2.getCeldas().add("11001000000000000025");
            filaArchivo2.getCeldas().add("24/11/2015 10:00:00");

            ResultadoCargueArchivoNotificacionDTO resultado = iComparendo.cargarArchivoNotificacion(
                    CODIGO_ORGANISMO,
                    crearArchivoTransportable(Arrays.asList(crearEncabezadoArchivo(), filaArchivo1, filaArchivo2),
                            "test003CargarArchivoNotificacion.xls"));

            Assert.assertTrue(resultado.getCantidadRegistros() > 0);
            Assert.assertTrue(resultado.getComparendosInvalidos() < resultado.getCantidadRegistros());

            // Consultar numero de comparendo
            StringBuilder consulta = new StringBuilder();
            consulta.append(
                    "SELECT c FROM Comparendo c JOIN FETCH c.estadoComparendo ec LEFT JOIN FETCH c.tipoNotificacion tn")
                    .append(" LEFT JOIN FETCH c.trazabilidadComparendoList tcl")
                    .append(" LEFT JOIN c.comparendoCarteraList ccl")
                    .append(" WHERE c.ordenComparendoNacional.numeroComparendo IN (:numeroComparendo)")
                    .append(" AND c.ordenComparendoNacional.organismoTransito.codigoOrganismo = :codigoOrganismo")
                    .append(" ORDER BY c.ordenComparendoNacional.numeroComparendo");
            GenericDao<Comparendo> comparendoDao = new GenericDao<>(Comparendo.class, em);
            List<Comparendo> comparendoList = comparendoDao.buildAndExecuteQuery(consulta.toString(), GenericDao
                    .buildMap("numeroComparendo", Arrays.asList("11001000000000000018", "11001000000000000025"))
                    .addEntry("codigoOrganismo", CODIGO_ORGANISMO));

            // Estado comparendo
            Assert.assertFalse(comparendoList == null || comparendoList.isEmpty());

            Assert.assertTrue(comparendoList.get(0).getEstadoComparendo().getId()
                    .equals(EnumEstadoComparendo.REGISTRADO.getCodigo()));
            Assert.assertTrue(comparendoList.get(0).getFechaNotificacion() == null);
            Assert.assertTrue(comparendoList.get(0).getTipoNotificacion() == null);

            Assert.assertTrue(comparendoList.get(1).getEstadoComparendo().getId()
                    .equals(EnumEstadoComparendo.VIGENTE.getCodigo()));
            Assert.assertTrue(comparendoList.get(1).getFechaNotificacion()
                    .equals(UtilFecha.stringYYYMMDDToDate("2015/12/01")));
            Assert.assertTrue(comparendoList.get(1).getTipoNotificacion().getId()
                    .equals(EnumTipoNotificacionComparendo.NOTIFICACION_CORREO.getValue()));

            // Trazabilidad
            Assert.assertTrue(comparendoList.get(0).getTrazabilidadComparendoList() == null
                    || comparendoList.get(0).getTrazabilidadComparendoList().isEmpty());
            Assert.assertFalse(comparendoList.get(1).getTrazabilidadComparendoList() == null
                    || comparendoList.get(1).getTrazabilidadComparendoList().isEmpty());

            for (TrazabilidadComparendo tc : comparendoList.get(1).getTrazabilidadComparendoList()) {
                // Consultar numero de comparendo
                StringBuilder consulta2 = new StringBuilder();
                consulta2.append("SELECT tc FROM TrazabilidadComparendo tc JOIN FETCH tc.actividad a").append(
                        " WHERE tc.id = :idTrazabilidadComparendo");
                GenericDao<TrazabilidadComparendo> tComparendoDao = new GenericDao<>(TrazabilidadComparendo.class, em);
                List<TrazabilidadComparendo> trazabilidadComparendoList = tComparendoDao.buildAndExecuteQuery(
                        consulta2, GenericDao.buildMap("idTrazabilidadComparendo", tc.getId()));
                Assert.assertTrue(trazabilidadComparendoList.get(0).getActividad().getId()
                        .equals(EnumActividad.NOTIFICACION_CORREO_CERTIFICADO.getValue()));
                break;
            }

            // TODO Consultar cartera activa
        } catch (CirculemosNegocioException e) {
            logger.debugv("ERROR test003CargarArchivoNotificacion: {0}", e.getMessage());
            Assert.fail("ERROR test003CargarArchivoNotificacion");
        }
    }

    /**
     * TEST004: SE NOTIFICARON TODO S LOS COMPARENDOS
     */
    @Ignore
    @Test
    public void test004CargarArchivoNotificacion() {
        try {
            ResultadoCargueArchivoNotificacionDTO resultado = iComparendo.cargarArchivoNotificacion(111001,
                    new ArchivoTransportableDTO());

            Assert.assertTrue(resultado.getCantidadRegistros() > 0);
            Assert.assertTrue(resultado.getComparendosInvalidos() == resultado.getCantidadRegistros());

            List<ComparendoDTO> comparendoDTOList = iComparendo.consultarComparendos(new ConsultaComparendoDTO());

            Assert.assertFalse(comparendoDTOList == null || comparendoDTOList.isEmpty());

            for (ComparendoDTO comparendoDTO : comparendoDTOList) {
                Assert.assertTrue(comparendoDTO.getEstadoComparendo().getCodigo()
                        .equals(EnumEstadoComparendo.VIGENTE.getCodigo()));
                Assert.assertTrue(comparendoDTO.getFechaNotificacion().equals(new Date()));
                Assert.assertTrue(comparendoDTO.getTipoNotificacion().getCodigo()
                        .equals(EnumTipoNotificacionComparendo.NOTIFICACION_CORREO.getValue()));

                // Consultar trazabilidad
            }
        } catch (CirculemosNegocioException e) {
            logger.debug("ERROR test004CargarArchivoNotificacion");
            Assert.fail("ERROR test004CargarArchivoNotificacion");
        }
    }

    /**
     * Encabezado con nombres de columnas
     * 
     * @return
     */
    private FilaArchivoDTO crearEncabezadoArchivo() {
        List<Object> celdas = new ArrayList<>();
        for (EnumCampoArchivoAcuse campo : EnumCampoArchivoAcuse.values())
            celdas.add(campo.getNombre());
        FilaArchivoExcelDTO filaArchivo = new FilaArchivoExcelDTO();
        filaArchivo.setEncabezado(true);
        filaArchivo.setHoja(0);
        filaArchivo.setCeldas(celdas);
        return filaArchivo;
    }

    /**
     * Crea un archivo en memoria con las filas indicadas
     * 
     * @param filaArchivoDTOList
     * @param nombre
     * @return
     */
    private ArchivoTransportableDTO crearArchivoTransportable(List<FilaArchivoDTO> filaArchivoDTOList, String nombre) {
        byte[] contenido = SolicitudPruebasBackHelperExtend.escribirArchivo(filaArchivoDTOList, EnumTipoArchivo.XLS);
        return new ArchivoTransportableDTO(nombre, contenido);
    }

}
