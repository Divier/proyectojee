package co.com.datatools.client.timer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.NamingException;

import co.com.datatools.client.timer.exception.AdministradorTimerException;
import co.com.datatools.client.timer.helpers.ClusterHost;
import co.com.datatools.client.timer.helpers.TareaProgramada;
import co.com.datatools.datatimer.dto.JobDTO;
import co.com.datatools.datatimer.dto.TriggerDTO;

public class MainCliente {

    private static String Ip;
    private static int Puerto;

    private static List<ClusterHost> Cluster;
    private static List<TareaProgramada> TareasProgramadas;
    private static MainCliente mainCliente;

    public MainCliente() {

        try {
            this.initInfoCluster();
            this.initInfoJndi();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        mainCliente = new MainCliente();

        if (args.length == 2) {

            try {
                Integer.valueOf(args[1]);
                Ip = args[0];
                Puerto = Integer.parseInt(args[1]);
                mainCliente.validarInicioMenu();
            } catch (NumberFormatException e) {
                System.out.println("El parametro 2 debe ser de tipo numerico ya que representa el puerto");
            }

        } else if (Cluster.size() >= 1) {
            mainCliente.seleccionarNodoCluster();
            mainCliente.validarInicioMenu();
        } else {
            System.out
                    .println("Debe definir la IP y el puerto en los parametros de entrada o en el archivo de configuración.");
        }

    }

    private void validarInicioMenu() {

        try {

            if (TareasProgramadas.size() != 0) {
                initMenu();
            } else {
                System.err.println("No existen tareas programadas configuradas en el sistema");
            }
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    private void seleccionarNodoCluster() {
        int indexCluster = -1;
        do {
            System.out.println("Seleccione el nodo al cual quiere conectarse:");
            imprimirCluster();
            System.out.print("Digite el numero de cluster: ");
            indexCluster = LeerEntero();
            if (indexCluster < 0 || indexCluster >= Cluster.size()) {
                System.out.println("El número de cluster debe tener un numero entre 0 y " + (Cluster.size() - 1));
            } else {
                Ip = Cluster.get(indexCluster).getIp();
                Puerto = Cluster.get(indexCluster).getPuerto();
            }
        } while (indexCluster < 0 || indexCluster >= Cluster.size());
    }

    private void imprimirCluster() {
        int i = 0;
        for (ClusterHost nodo : Cluster) {
            System.out.println(i + " = Ip:" + nodo.getIp() + " Puerto:" + nodo.getPuerto());
            i++;
        }
    }

    private void initMenu() throws NamingException {

        String option = "";
        do {
            System.out.println("\n\n----------------------------------------------------");
            System.out.println("        ADMINISTRADOR TAREAS PROGRAMADAS          ");
            System.out.println("----------------------------------------------------\n\n");

            if (TareasProgramadas.size() == 1) {
                System.out.println("Solo existe la tarea programada: " + TareasProgramadas.get(0));
                menuJobTareaProgramada(TareasProgramadas.get(0));
            } else {
                System.out.println("Seleccione la tarea programada:");
                int i = 0;
                for (TareaProgramada tarea : TareasProgramadas) {
                    System.out.println(i + ". " + tarea.getNombre());
                    i++;
                }
                // System.out.println("1. JobCirculemos2Tarea1");
                System.out.println("S. Salir");
                System.out.print("Opcion: ");
                option = LeerString();

                if (option.equals("s") || option.equals("S")) {
                    System.out.println("Saliendo del sistema de administracion");
                    System.exit(0);
                } else {
                    try {
                        int indexTarea = Integer.parseInt(option);
                        if (indexTarea >= 0 && indexTarea < TareasProgramadas.size()) {
                            menuJobTareaProgramada(TareasProgramadas.get(indexTarea));
                        } else if (TareasProgramadas.size() > 1) {
                            System.out
                                    .println("Opcion debe tener un valor entre 0 y " + (TareasProgramadas.size() - 1));
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Opcion debe ser numerico ");
                    }

                }
            }

        } while (option != "s" || option != "S");

    }

    private RemoteTimerEJB getInstanceRemoteTimerEJB(TareaProgramada tarea) throws AdministradorTimerException {
        try {
            return new RemoteTimerEJB(tarea.getJndi(), Ip, Puerto);
        } catch (AdministradorTimerException e) {
            throw e;
        }

    }

    private void menuJobTareaProgramada(TareaProgramada tarea) {
        String option = "";

        RemoteTimerEJB remoteTimerEJB;
        TriggerDTO triggerDTO;
        // try {
        // remoteTimerEJB = new RemoteTimerEJB(tarea.getJndi(), Ip, Puerto);
        // remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
        do {
            // while (remoteTimerEJB != null && !option.equals("8")) {
            System.out.println("\n\n----------------------------------------------------");
            System.out.println("               " + tarea.getNombre() + "               ");
            System.out.println("----------------------------------------------------\n\n");

            System.out.println("Seleccione la opción deseada:");
            System.out.println("1. Mostar Información Job");
            System.out.println("2. Pausar Job");
            System.out.println("3. Reinicar Job");
            System.out.println("4. Pausar Trigger Job");
            System.out.println("5. Reiniciar Trigger Job");
            System.out.println("6. Reprogramar Trigger Job");
            System.out.println("7. Eliminar Job");
            System.out.println("8. Seleccionar Otra Tarea Programada");
            System.out.println("S. Salir");

            System.out.print("Opcion: ");
            option = LeerString();

            switch (option) {
            case "1":
                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    System.out.println(remoteTimerEJB.getJobDTO());
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();
                }

                break;

            case "2":

                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    remoteTimerEJB.pausarJob();
                    if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                        System.err.println("Sincronización no exitosa en cluster para pausar Job");
                    }
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();
                }

                break;

            case "3":

                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    remoteTimerEJB.reiniciarJob();
                    if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                        System.err.println("Sincronización no exitosa en cluster para reiniciar Job");
                    }
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();
                }

                break;

            case "4":

                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    triggerDTO = seleccionarTriggerJob(remoteTimerEJB.getJobDTO().getListTriggers());
                    remoteTimerEJB.pausarTriggerJob(triggerDTO);
                    if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                        System.err.println("Sincronización no exitosa en cluster para pausar Trigger");
                    }
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();

                }

                break;

            case "5":

                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    triggerDTO = seleccionarTriggerJob(remoteTimerEJB.getJobDTO().getListTriggers());
                    remoteTimerEJB.reiniciarTriggerJob(triggerDTO);
                    if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                        System.err.println("Sincronización no exitosa en cluster para reinicar Trigger");
                    }
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();

                }

                break;

            case "6":

                try {
                    remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                    triggerDTO = seleccionarTriggerJob(remoteTimerEJB.getJobDTO().getListTriggers());
                    System.out.println("Ingrese la nueva expresion: ");
                    String newCronExpression = LeerString();
                    remoteTimerEJB.reprogramarTriggerJob(triggerDTO, newCronExpression);
                    if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                        System.err.println("Sincronización no exitosa en cluster para reprogramacion de Trigger");
                    }
                } catch (AdministradorTimerException e) {
                    e.printStackTrace();

                }

                break;

            case "7":

                System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡ADVERTENCIA!!!!!!!!!!!!!!!!!!");
                System.out.println("Este proceso eliminara el Job del sistema y si existe un Cluster,");
                System.out.println("los nodos del Cluster no podran ejecutar la tarea");
                System.out.println("Podra recuperar el Job desplegando de nuevo el sistema de tareas");
                System.out.println("Desea continuar?[S/s]:");
                String continuar = this.LeerString();
                if (continuar.equalsIgnoreCase("s")) {
                    try {
                        remoteTimerEJB = getInstanceRemoteTimerEJB(tarea);
                        remoteTimerEJB.eliminarJob();
                        if (!sincronizarCluster(tarea, remoteTimerEJB.getJobDTO())) {
                            System.err.println("Sincronización no exitosa en cluster eliminacion de Job");
                        }
                    } catch (AdministradorTimerException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case "8":
                System.out.println("Regresando a menu de selección");
                break;

            case "s":
            case "S":
                System.out.println("Saliendo del sistema de administracion");
                System.exit(0);
                break;
            default:
                System.out.println("Opción invalida");
                break;
            }

        } while (!option.equals("8"));

    }

    private boolean sincronizarCluster(TareaProgramada tarea, JobDTO jobDTO) /* throws AdministradorTimerException */{
        boolean sincronizacionExitosa = true;

        if (Cluster.size() >= 2) {
            for (ClusterHost host : Cluster) {
                System.out.println("Sincronizando Información en Servidores de Cluster: " + host.getIp() + " "
                        + host.getPuerto());
                try {
                    RemoteTimerEJB remoteTimerEJB = new RemoteTimerEJB(tarea.getJndi(), host.getIp(), host.getPuerto());
                    /**
                     * Busca sincronizar los nodos de cluster pero no sincroniza el nodo con el cual se encuentra actualmente conectado el cliente de
                     * administración
                     */
                    if (!(host.getIp().equals(Ip) && host.getPuerto() == Puerto)) {
                        remoteTimerEJB.sincronizarDTO(jobDTO);
                    }

                } catch (AdministradorTimerException e) {
                    sincronizacionExitosa = false;
                    System.err.println(e);
                }
            }
        }
        return sincronizacionExitosa;

    }

    private void imprimirInfoTrigger(List<TriggerDTO> listTriggers) {
        int i = 0;
        for (TriggerDTO triggerDTO : listTriggers) {
            System.out.println(i + ". " + triggerDTO);
            i++;
        }

    }

    private TriggerDTO seleccionarTriggerJob(List<TriggerDTO> listTriggers) {

        if (listTriggers.size() >= 2) {

            System.out.println("Lista de trigger");
            imprimirInfoTrigger(listTriggers);
            int indexTrigger;

            do {
                System.out.print("Seleccione Trigger [0 - " + (listTriggers.size() - 1) + "]: ");
                indexTrigger = LeerEntero();
            } while (indexTrigger < 0 || indexTrigger >= listTriggers.size());

            return listTriggers.get(indexTrigger);
        } else if (listTriggers.size() == 1) {
            imprimirInfoTrigger(listTriggers);
            return listTriggers.get(listTriggers.size() - 1);
        } else {
            System.out.println("No hay trigger para seleccionar");
            return null;
        }

    }

    private Integer LeerEntero() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return -100;
        }
    }

    private String LeerString() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextLine();
        } catch (Exception e) {
            return "";
        }
    }

    private void initInfoCluster() throws IOException {
        Properties propertiesCluster = new Properties();
        Cluster = new ArrayList<ClusterHost>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("cluster.properties");
        if (is == null) {
            System.err.println("No es posible encontrar el archivo cluster.properties");
        } else {
            try {
                propertiesCluster.load(is);
                Map<String, String> mapCluster = this.loadPropertiestoMap(propertiesCluster);

                Iterator<?> it = mapCluster.keySet().iterator();
                while (it.hasNext()) {
                    String ipServerCluster;
                    int puertoServerCluster;
                    String key = (String) it.next();
                    ipServerCluster = ((String) (mapCluster.get(key))).split(":")[0];
                    puertoServerCluster = Integer.parseInt(((String) (mapCluster.get(key))).split(":")[1]);
                    Cluster.add(new ClusterHost(ipServerCluster, puertoServerCluster));

                }
            } catch (IOException e) {
                System.err.println("Error cargando las propiedades cluster.properties: " + e.getMessage());
                throw e;
            } catch (NumberFormatException e) {
                System.err
                        .println("Error en la configuración del archivo cluster.properties.  Los puertos deben ser de tipo numerico");
            }

        }

    }

    private Map<String, String> loadPropertiestoMap(Properties properties) {
        Enumeration<?> e = properties.propertyNames();
        Map<String, String> map = new HashMap<String, String>();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = (String) properties.getProperty(key);
            map.put(key, value);

        }

        return map;
    }

    private void initInfoJndi() throws IOException {
        Properties propertiesJNDI = new Properties();
        TareasProgramadas = new ArrayList<TareaProgramada>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jndi.properties");
        if (is == null) {
            System.err.println("No es posible encontrar el archivo jndi.properties");
        } else {
            try {
                propertiesJNDI.load(is);
                Map<String, String> mapJndi = this.loadPropertiestoMap(propertiesJNDI);

                Iterator<?> it = mapJndi.keySet().iterator();
                while (it.hasNext()) {

                    String key = (String) it.next();
                    String jndi = mapJndi.get(key);

                    TareasProgramadas.add(new TareaProgramada(key, jndi));

                }
            } catch (IOException e) {
                System.err.println("Error cargando las propiedades jndi.properties: " + e.getMessage());
                throw e;
            }
        }

    }

}
