package Consola;

import ClasesAuxiliares.Nodo;
import static ClasesAuxiliares.Utils.canRead;
import static ClasesAuxiliares.Utils.canWrite;
import static ClasesAuxiliares.Utils.convertirMascara;
import FileSystem.Archivo;
import FileSystem.Directorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private Sesion sesionActual;
    private boolean exit = false;
    static final String PERMISOS_INSUFICIENTES_MSG = "No tiene permisos para realizar esta accion";
    public static final String DIRECTORIO_INEXISTENTE = ": no existe ese directorio o archivo";

    public Sistema() {
        sesionActual = new Sesion();
    }

    public Sesion getSesionActual() {
        return sesionActual;
    }

    public void setSesionActual(Sesion sesionActual) {
        this.sesionActual = sesionActual;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void comandos(String comandoInterfaz) {
        String[] comandos = dividirComponentes(comandoInterfaz);
        try {
            if (comandoInterfaz.equals("apt -get moo") || comandoInterfaz.equals("moo")) {
                System.out.println("         (__) \n"
                        + "         (oo) \n"
                        + "   /------\\/ \n"
                        + "  / |    ||   \n"
                        + " *  /\\---/\\ \n"
                        + "    ~~   ~~   \n"
                        + "....\"Have you mooed today?\"...");
                return;
            }
            switch (comandos[0]) {
                case "useradd":
                    //useradd ​nombreUsuario
                    //Tiene como parámetro el nombre de usuario a crear.
                    agregarUsuario(comandos[1]);
                    break;
                case "passwd":
                    //passwd ​nombreUsuario                
                    //Modifica la contraseña del usuario. El sistema pide el ingreso de la contraseña 2 veces. Si no coinciden, muestra mensaje de error.
                    modificarContraseña(comandos[1]);
                    break;
                case "su":
                    su(comandos[1]);
                    /*su ​nombreUsuario
                Luego de pedirnos la contraseña, nos permite autenticarnos con otro usuario. Notar quepide la contraseña.*/
                    break;
                case "userdel":
                    /* userdel ​nombreUsuario
                  Elimina el usuario y toda la información relativa a él.*/
                    eliminarUsuario(comandos[1]);
                    break;
                case "whoami":
                    /*whoami
                Muestra el nombre de usuario autenticado(puede parecer irrelevante, ya que el prompt muestra el nombre de usuario, pero noaplica a todos los casos)*/
                    whoami();
                    break;
                case "pwd":
                    /*pwd
                Muestra la ruta donde nos encontramos.*/
                    pwd();
                    break;
                case "mkdir":
                    /*mkdir​ nombreDirectorio
                Crea un directorio de nombre nombreDirectorio*/
                    mkdir(comandos[1]);
                    break;
                case "rmdir":
                    /*rmdir​ nombreDirectorio
                Elimina el directorio de nombre nombreDirectorio*/
                    rmdir(comandos[1]);
                    break;
                case "touch":
                    /*touch ​nombreArchivo
                Crea un archivo vacío. En este obligatorio se pide únicamente crear archivos de tipo txt.*/
                    crearArchivo(comandos[1]);
                    break;
                case "echo":
                    /*echo​ "Texto al final del archivo" ​>>​ archivo.txt
                Agrega al final del archivo, el texto.*/

                    //ver como obtener el archivo
                    agregarTexto(comandoInterfaz);
                    break;
                case "mv":
                    /*mv ​rutaOrigen rutaDestino
                Mueve un archivo desde la ruta al destino*/
                    mv(comandos[1], comandos[2]);
                    break;
                case "cp":
                    /*    cp​ rutaOrigen rutaDestino
                Copia un archivo desde origen a destino*/
                    cp(comandos[1], comandos[2]);
                    break;
                case "cat":
                    /*cat ​nombreArchivo
            Muestra en pantalla el contenido de un archivo*/
                    if (comandos.length == 5 && comandos[2].equals("|") && comandos[3].equals("grep")) {
                        catConFiltro(comandos[1], comandos[4]);
                    } else if (comandos.length == 2) {
                        cat(comandos[1]);
                    } else {
                        System.out.println("Uso incorrecto del comando '" + comandoInterfaz + "'");
                    }
                    break;
                case "rm":
                    /*rm​ nombreArchivo
                Borra un archivo*/
                    rm(comandos[1]);
                    break;
                case "cd":
                    /*cd​ ruta
                Se posiciona dentro de la ruta elegida. Muestra error en caso que la misma no exista.*/
                    cd(comandos[1]);
                    break;
                case "ls -l":
                    /* ls -l
                Muestra el contenido de la carpeta donde se está posicionado. Incluyendo propietario,permisos y fecha y hora de creación (toda la metadata visible es necesario que semodele).*/
                    break;
                case "exit":
                    this.exit = true;
                    break;
                case "chmod":
                    asignarPermisos(comandos[1], comandos[2]);
                    break;
                case "chown":
                    cambiarPropietario(comandos[1], comandos[2]);
                    break;
                case "ls":
                    if (comandos[1].equals("-l")) {
                        if (comandos.length == 5 && comandos[2].equals("|") && comandos[3].equals("grep")) {
                            mostrarContenidoFiltrado(comandos[4]);
                        } else {
                            mostrarContenidoCompleto();
                        }
                    }
                    break;
                case "history":
                    if (comandos.length == 4 && comandos[1].equals("|") && comandos[2].equals("grep")) {
                        System.out.println(historialConFiltro(comandos[3]));
                    } else if (comandos.length == 1) {
                        System.out.println("\n" + sesionActual.getHistorialDeComandosPretty());
                    } else {
                        System.out.println("Uso incorrecto del comando '" + comandoInterfaz + "'");
                    }
                    break;

                default:
                    System.out.println(comandoInterfaz + ": comando no encontrado");
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Uso incorrecto del comando '" + comandoInterfaz + "'");
        }
        sesionActual.getHistorialComandos().add(comandoInterfaz);
    }

    public void inputConsola() {
        System.out.print(sesionActual.usuarioActual.nombre + "@:" + sesionActual.ruta + "$");
        //System.out.print(ANSI_GREEN + sesionActual.usuarioActual.nombre + "@:" + ANSI_BLUE + sesionActual.ruta +  ANSI_RESET + "$");
        Scanner sc = new Scanner(System.in);
        String comando = sc.nextLine();
        comandos(comando);
    }

    public String[] dividirComponentes(String comando) {
        String[] comandos = comando.split(" ");
        return comandos;
    }

    public void agregarUsuario(String nombreUsuario) {
        Usuario nuevoUsuario = new Usuario(nombreUsuario);
        if (sesionActual.misUsuarios.contains(nuevoUsuario)) {
            System.out.println("Ya existe el usuario '" + nombreUsuario + "'");
            return;
        }
        if (nombreUsuario.equals("root") || nombreUsuario.equals("visita")) {
            System.out.println("No puede crear un usuario con ese nombre");
            return;
        }
        System.out.print("Ingrese una contraseña: ");
        Scanner contraseñaUsuario = new Scanner(System.in);
        String contraseña = contraseñaUsuario.nextLine();

        System.out.print("Ingrese la contraseña nuevamente: ");
        Scanner contraseñaUsuario2 = new Scanner(System.in);
        String contraseña2 = contraseñaUsuario2.nextLine();

        if (contraseña.equals(contraseña2)) {
            nuevoUsuario.setContraseña(contraseña);
        }
        while (!contraseña.equals(contraseña2)) {
            System.out.println("Las contraseñas no coinciden, vuelva a ingresarlas");

            System.out.print("Ingrese una contraseña: ");
            Scanner contUsu = new Scanner(System.in);
            String cont = contUsu.nextLine();

            System.out.print("Ingrese la contraseña nuevamente: ");
            Scanner contUsu2 = new Scanner(System.in);
            String cont2 = contUsu2.nextLine();

            contraseña = cont;
            contraseña2 = cont2;
        }
        nuevoUsuario.setContraseña(contraseña);
        sesionActual.misUsuarios.add(nuevoUsuario);
        sesionActual.setUsuarioActual(nuevoUsuario);
        sesionActual.getHistorialComandos().clear();
    }

    public void modificarContraseña(String nombreUsuario) {
        //crear lista de todos los usuarios
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        if (sesionActual.getUsuario().equals(new Usuario(nombreUsuario))) {
            if (sesionActual.misUsuarios.contains(new Usuario(nombreUsuario))) {
                System.out.print("Ingrese la nueva contraseña: ");
                Scanner contraseñaUsuario = new Scanner(System.in);
                String contraseña = contraseñaUsuario.nextLine();

                System.out.print("Ingrese la contraseña nuevamente: ");
                Scanner contraseñaUsuario2 = new Scanner(System.in);
                String contraseña2 = contraseñaUsuario2.nextLine();

                if (contraseña.equals(contraseña2)) {
                    sesionActual.usuarioActual.setContraseña(contraseña);
                } else {
                    System.out.print("Las contraseñas no coinciden, vuelva a ingresarlas");
                }

            } else {
                System.out.println("Usuario no existente");
            }
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }

    public void eliminarUsuario(String nombreUsuario) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Usuario aux = new Usuario(nombreUsuario);
        int indice = sesionActual.misUsuarios.indexOf(aux);
        if (nombreUsuario.equals("root")) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        if (indice != -1) { // aca borro al pibito de la lista
            sesionActual.misUsuarios.remove(aux);
            System.out.println("Usuario " + nombreUsuario + " eliminado.");
            sesionActual.setUsuarioActual(new Usuario("visita"));
        } else {
            System.out.println("Usuario " + nombreUsuario + " no encontrado.");
        }
    }

    public void crearArchivo(String nombreArchivo) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> nodo = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        Archivo nuevoArchivo = new Archivo(nombreArchivo);
        nuevoArchivo.setPropietario(sesionActual.getUsuario());
        nuevoArchivo.setMascara("704");
        nodo.getDato().AgregarArchivo(nuevoArchivo);
    }

    public String[] splitEcho(String aDividir) {
        return aDividir.split("\"|\\>> ");
    }

    public void agregarTexto(String comandoADividir) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        String[] comandos = splitEcho(comandoADividir);
        Nodo<Directorio> directorio = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        Archivo aModificar = directorio.getDato().devolverArchivo(comandos[3]);
        if (canWrite(sesionActual.usuarioActual, aModificar) || sesionActual.getUsuario().esAdmin()) {
            aModificar.agregarContenido(comandos[1]);
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }

    public void whoami() {
        System.out.println(sesionActual.getUsuario().getNombre());
    }

    public void su(String nombreU) {
        Usuario aux = new Usuario(nombreU);
        if (!sesionActual.misUsuarios.contains(aux)) {
            System.out.println("Usuario " + nombreU + " no existente");
            return;
        }

        Usuario nuevoAutenticado = null;
        for (Usuario usu : sesionActual.misUsuarios) {
            if (usu.getNombre().equals(nombreU)) {
                nuevoAutenticado = usu;
            }
        }
        boolean repetir = true;
        while (repetir) {
            System.out.print("Ingrese la contraseña para autenticarse como " + nombreU + ": ");
            Scanner contraseñaUsuario = new Scanner(System.in);
            String contraseña = contraseñaUsuario.nextLine();

            if (contraseña.equals(nuevoAutenticado.getContraseña())) {
                sesionActual.setUsuarioActual(nuevoAutenticado);
                sesionActual.getHistorialComandos().clear();
                repetir = false;
            } else {
                System.out.println("Contraseña incorrecta");
            }
        }
    }

    public void pwd() {
        System.out.println(sesionActual.ruta);
    }

    void mkdir(String nombreDirectorio) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        if (sesionActual.directorios.existeDirectorio(nombreDirectorio)) {
            System.out.println("Directorio ya existente");
            return;
        }
        try {
            if (nombreDirectorio.startsWith("/")) {
                if (sesionActual.directorios.agregarDirectorio(nombreDirectorio) == null) {
                    System.out.println("Imposible agregar, ruta no encontrada");
                }
            } else {
                if (sesionActual.ruta.equals("/")) {
                    sesionActual.directorios.agregarDirectorio(sesionActual.ruta + nombreDirectorio);
                } else {
                    String ruta = sesionActual.ruta + "/" + nombreDirectorio;
                    sesionActual.directorios.agregarDirectorio(ruta);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Imposible agregar, ruta no encontrada");
        }
    }

    void rmdir(String nombreDirectorio) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        if (nombreDirectorio.startsWith("/")) {
            if (sesionActual.ruta.equals(nombreDirectorio)) {
                System.out.println(nombreDirectorio + DIRECTORIO_INEXISTENTE);
                return;
            }
            Nodo<Directorio> aBorrar = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), nombreDirectorio, 1);
            if (aBorrar == null) {
                System.out.println(nombreDirectorio + DIRECTORIO_INEXISTENTE);
                return;
            }
            if (aBorrar.getpH() != null) {
                System.out.println(nombreDirectorio + DIRECTORIO_INEXISTENTE);
                return;
            }
            sesionActual.directorios.eliminarDirectorio(nombreDirectorio);
        } else {
            String ruta = "";
            if (sesionActual.ruta.equals("/")) {
                ruta = sesionActual.ruta + nombreDirectorio;
            } else {
                ruta = sesionActual.ruta + "/" + nombreDirectorio;
            }
            Nodo<Directorio> aBorrar = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), ruta, 1);
            if (aBorrar == null) {
                System.out.println(ruta + DIRECTORIO_INEXISTENTE);
                return;
            }
            if (aBorrar.getpH() != null) {
                System.out.println(nombreDirectorio + DIRECTORIO_INEXISTENTE);
                return;
            }
            sesionActual.directorios.eliminarDirectorio(ruta);
        }
    }

    public void cd(String ruta) {
        if (ruta.startsWith("/")) {
            if (sesionActual.directorios.existeDirectorio(ruta)) {
                sesionActual.ruta = ruta;
            } else {
                System.out.println(ruta + DIRECTORIO_INEXISTENTE);
            }
        } else if (ruta.equals("..") && !sesionActual.ruta.equals("/")) {
            String rutaP = sesionActual.directorios.rutaPadre(sesionActual.ruta);
            sesionActual.ruta = rutaP;
        } else {
            System.out.println(ruta + DIRECTORIO_INEXISTENTE);
        }
    }

    public void mv(String nombreArchivo, String rutaDestino) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> dirDestino = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), rutaDestino, 1);
        if (dirDestino != null && !dirDestino.getDato().contieneArchivo(nombreArchivo)) {
            Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
            if (directorioActual == null) {
                System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
                return;
            }
            Archivo archivo = directorioActual.getDato().devolverArchivo(nombreArchivo);
            if (!sesionActual.getUsuario().esAdmin() && !canWrite(sesionActual.getUsuario(), archivo)) {
                System.out.println(PERMISOS_INSUFICIENTES_MSG);
                return;
            }
            Archivo archivoAMover = directorioActual.getDato().borrarYDevolverArchivo(nombreArchivo);
            if (sesionActual.directorios.existeDirectorio(rutaDestino)) {
                Nodo<Directorio> directorioNuevo = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), rutaDestino, 1);
                directorioNuevo.getDato().AgregarArchivo(archivoAMover);
            }
        } else {
            System.out.println("La ruta " + rutaDestino + " no existe");
        }
    }

    public void cp(String nombreArchivo, String rutaDestino) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        Archivo archivoACopiar = directorioActual.getDato().devolverArchivo(nombreArchivo);
        if (archivoACopiar == null) {
            System.out.println("El archivo " + nombreArchivo + " no existe en " + sesionActual.ruta);
            return;
        }
        if (!archivoACopiar.getPropietario().equals(sesionActual.getUsuario()) && !sesionActual.getUsuario().esAdmin() && !canRead(sesionActual.getUsuario(), archivoACopiar)) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Archivo copia = new Archivo(archivoACopiar.getNombre());
        copia.setMascara(archivoACopiar.getMascara());
        List<String> nuevoContenido = new ArrayList<>();
        copiarContenido(nuevoContenido, archivoACopiar.getContenido());
        copia.setContenido(nuevoContenido);
        copia.setPropietario(archivoACopiar.getPropietario());
        Nodo<Directorio> destino = sesionActual.directorios.buscarDirectorio(sesionActual.getDirectorios().getRaiz(), rutaDestino, 1);
        if (destino != null) {
            if (destino.getDato().contieneArchivo(copia)) {
                System.out.println("Un archivo con nombre " + nombreArchivo + " ya existe en la ruta destino.");
                return;
            }
            destino.getDato().AgregarArchivo(copia);
        } else {
            System.out.println("La ruta " + rutaDestino + " no existe");
        }

    }

    private void copiarContenido(List<String> nuevo, List<String> cont) {
        for (String linea : cont) {
            String copia = linea + "";
            nuevo.add(copia);
        }
    }

    public void cat(String nombreArchivo) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        Archivo archivo = directorioActual.getDato().devolverArchivo(nombreArchivo);
        if (archivo == null) {
            System.out.println("El archivo " + nombreArchivo + " no existe en " + sesionActual.ruta);
            return;
        }
        if (canRead(sesionActual.usuarioActual, archivo) || sesionActual.getUsuario().esAdmin()) {
            List<String> contenido = archivo.getContenido();
            for (String linea : contenido) {
                System.out.println(linea);
            }
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }

    public void rm(String nombreArchivo) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        directorioActual.getDato().borrarYDevolverArchivo(nombreArchivo);
    }

    private void asignarPermisos(String nombreArchivo, String mascara) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        Archivo archivo = directorioActual.getDato().devolverArchivo(nombreArchivo);
        if (archivo == null) {
            System.out.println("Archivo " + nombreArchivo + " no encontrado.");
            return;
        }
        if (archivo.getPropietario().equals(sesionActual.getUsuario()) || sesionActual.getUsuario().esAdmin()) {
            try {
                if (convertirMascara(mascara).equals("")) {
                    System.out.println("Formato de mascara incorrecto");
                }
                archivo.setMascara(mascara);
            } catch (NumberFormatException ex) {
                System.out.println("La mascara solo puede conener numeros");
            }
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }

    private void mostrarContenidoCompleto() {
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        for (Archivo arch : directorioActual.getDato().getListaArchivos()) {
            System.out.println(arch.toString());
        }
    }

    private String historialConFiltro(String palabraABuscar) {
        String historialFiltrado = "";
        for (String hist : sesionActual.getHistorialComandos()) {
            if (hist.contains(palabraABuscar)) {
                historialFiltrado += "\n" + hist;
            }
        }
        System.out.println("");
        return historialFiltrado;
    }

    private void catConFiltro(String nombreArchivo, String palabraABuscar) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA");
            return;
        }
        Archivo archivo = directorioActual.getDato().devolverArchivo(nombreArchivo);
        if (archivo == null) {
            System.out.println("El archivo " + nombreArchivo + " no existe en " + sesionActual.ruta);
            return;
        }
        if (canRead(sesionActual.usuarioActual, archivo) || sesionActual.getUsuario().esAdmin()) {
            List<String> contenido = archivo.getContenido();
            String contenidoFiltrado = "";
            for (String linea : contenido) {
                if (linea.contains(palabraABuscar)) {
                    contenidoFiltrado += linea + "\n";
                }
            }
            System.out.print(contenidoFiltrado);
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }

    private void mostrarContenidoFiltrado(String textoABuscar) {
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        for (Archivo arch : directorioActual.getDato().getListaArchivos()) {
            if (arch.getNombre().contains(textoABuscar)) {
                System.out.println(arch.toString());
            }
        }
    }

    private void cambiarPropietario(String usuario, String nombreArchivo) {
        if (sesionActual.getUsuario().esVisita()) {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
            return;
        }
        Nodo<Directorio> directorioActual = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        if (directorioActual == null) {
            System.out.println("ERROR: RUTA NO ENCONTRADA"); //nunca deberia pasar
            return;
        }
        Archivo archivo = directorioActual.getDato().devolverArchivo(nombreArchivo);
        if (archivo == null) {
            System.out.println("Archivo " + nombreArchivo + " no encontrado.");
            return;
        }
        if (!sesionActual.misUsuarios.contains(new Usuario(usuario))) {
            System.out.println("No se encontro al usuario: " + usuario);
            return;
        }
        Usuario u = null;
        if (archivo.getPropietario().equals(sesionActual.getUsuario()) || sesionActual.getUsuario().esAdmin()) {
            for (Usuario usr : sesionActual.misUsuarios) {
                if (usr.getNombre().equals(usuario)) {
                    u = usr;
                }
            }
            archivo.setPropietario(u);
        } else {
            System.out.println(PERMISOS_INSUFICIENTES_MSG);
        }
    }
}
