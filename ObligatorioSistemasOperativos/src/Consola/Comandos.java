package Consola;

import ClasesAuxiliares.Nodo;
import FileSystem.Archivo;
import FileSystem.Directorio;
import java.util.Scanner;

public class Comandos {

    Sesion sesionActual;

    public void menu(String comandoInterfaz) {
        String[] comandos = dividirComponentes(comandoInterfaz);

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
                break;
            case "mkdir":
                /*mkdir​ nombreDirectorio
                Crea un directorio de nombre nombreDirectorio*/
                break;
            case "rmdir":
                /*rmdir​ nombreDirectorio
                Elimina el directorio de nombre nombreDirectorio*/
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
                break;
            case "cp":
                /*    cp​ rutaOrigen rutaDestino
                Copia un archivo desde origen a destino*/
                break;
            case "cat":
                /*cat ​nombreArchivo
            Muestra en pantalla el contenido de un archivo*/
                break;
            case "rm":
                /*rm​ nombreArchivo
                Borra un archivo*/
                break;
            case "cd":
                /*cd​ ruta
                Se posiciona dentro de la ruta elegida. Muestra error en caso que la misma no exista.*/
                break;
            case "ls -l":
                /* ls -l
                Muestra el contenido de la carpeta donde se está posicionado. Incluyendo propietario,permisos y fecha y hora de creación (toda la metadata visible es necesario que semodele).*/
                break;

            /*faltan comandos*/
        }

    }

    public String[] dividirComponentes(String comando) {
        String[] comandos = comando.split(" ");
        return comandos;
    }

    public void agregarUsuario(String nombreUsuario) {
        Usuario nuevoUsuario = new UsuarioEstandar(nombreUsuario);

        System.out.print("Ingrese su contraseña: ");
        Scanner contraseñaUsuario = new Scanner(System.in);
        String contraseña = contraseñaUsuario.nextLine();

        System.out.print("Ingrese su contraseña nuevamente para su verificación: ");
        Scanner contraseñaUsuario2 = new Scanner(System.in);
        String contraseña2 = contraseñaUsuario2.nextLine();

        if (contraseña.equals(contraseña2)) {
            nuevoUsuario.setContraseña(contraseña);
        }
        while (!contraseña.equals(contraseña2)) {
            System.out.println("Las contraseñas no coinciden, vuelva a ingresarlas");

            System.out.print("Ingrese su contraseña: ");
            Scanner contUsu = new Scanner(System.in);
            String cont = contUsu.nextLine();

            System.out.print("Ingrese su contraseña nuevamente para su verificación: ");
            Scanner contUsu2 = new Scanner(System.in);
            String cont2 = contUsu2.nextLine();

            contraseña = cont;
            contraseña2 = cont2;
        }
    }

    public void modificarContraseña(String nombreUsuario) {
        //crear lista de todos los usuarios
        if (sesionActual.misUsuarios.contains(nombreUsuario)) { //aca busco en la supuesta lista de usuarios a ver si existe el pibito
            System.out.print("Ingrese la nueva contraseña: ");
            Scanner contraseñaUsuario = new Scanner(System.in);
            String contraseña = contraseñaUsuario.nextLine();

            System.out.print("Ingrese su contraseña nuevamente para su verificación: ");
            Scanner contraseñaUsuario2 = new Scanner(System.in);
            String contraseña2 = contraseñaUsuario2.nextLine();

            if (contraseña.equals(contraseña2)) {
                sesionActual.usuarioActual.setContraseña(contraseña);
            } else {
                System.out.print("ERROR! Las contraseñas no coinciden");
            }

        } else {
            System.out.print("Usuario no existente");
        }
    }

    public void eliminarUsuario(String nombreUsuario) {
        int indice = sesionActual.misUsuarios.indexOf(nombreUsuario);
        Usuario aux = new UsuarioEstandar(nombreUsuario);
        if (indice != -1) { // aca borro al pibito de la lista
            sesionActual.misUsuarios.remove(aux);
        } else {
            System.out.print("No hay registro de ese usuario");
        }
    }

    public void crearArchivo(String nombreArchivo) {
        Archivo nuevoArchivo = new Archivo(nombreArchivo);
    }

    public String[] splitEcho(String aDividir) {
        return aDividir.split("\"|\\>>");
    }

    public void agregarTexto(String comandoADividir) {
        String[] comandos = splitEcho(comandoADividir);
        Nodo<Directorio> directorio = sesionActual.directorios.buscarDirectorio(sesionActual.directorios.getRaiz(), sesionActual.ruta, 1);
        Archivo aModificar = directorio.getDato().devolverArchivo(comandos[2]);
        aModificar.agregarContenido(comandos[1]);
    }

    String whoami() {
        return sesionActual.usuarioActual.getNombre();
    }

    public void su(String nombreU) {
        Usuario aux = new UsuarioEstandar(nombreU);
        if (!sesionActual.misUsuarios.contains(aux)) {
            System.out.print("Usuario no existente");
            return;
        }

        Usuario nuevoAutenticado = null;
        for (Usuario usu : sesionActual.misUsuarios) {
            if (usu.getNombre().equals(nombreU)) {
                nuevoAutenticado = usu;
            }
        }

        System.out.print("Ingrese la contraseña para autenticarse como " + nombreU);
        Scanner contraseñaUsuario = new Scanner(System.in);
        String contraseña = contraseñaUsuario.nextLine();

        if (contraseña.equals(nuevoAutenticado.getContraseña())) {
            sesionActual.setUsuario(nuevoAutenticado);
        } else {
            System.out.print("ERROR! Contraseña incorrecta");
        }
        //acordarse hacer que el equals busque por nombre de usuario
    }
}
