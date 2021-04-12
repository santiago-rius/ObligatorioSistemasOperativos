
import FileSystem.Archivo;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class Comandos {

    public void menu() {
        String comando = "";

        switch (comando) {
            case "useradd":
                //useradd ​nombreUsuario
                //Tiene como parámetro el nombre de usuario a crear.
                agregarUsuario();
                break;
            case "passwd":
                //passwd ​nombreUsuario                
                //Modifica la contraseña del usuario. El sistema pide el ingreso de la contraseña 2 veces. Si no coinciden, muestra mensaje de error.
                break;
            case "su":
                /*su ​nombreUsuario
                Luego de pedirnos la contraseña, nos permite autenticarnos con otro usuario. Notar quepide la contraseña.*/
                break;
            case "userdel":
                /* userdel ​nombreUsuario
                  Elimina el usuario y toda la información relativa a él.*/
                break;
            case "whoami":
                /*whoami
                Muestra el nombre de usuario autenticado(puede parecer irrelevante, ya que el prompt muestra el nombre de usuario, pero noaplica a todos los casos)*/
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
                crearArchivo();
                break;
            case "echo":
                /*echo​ "Texto al final del archivo" ​>>​ archivo.txt
                Agrega al final del archivo, el texto.*/
                
                //ver como obtener el archivo
                agregarTexto(new Archivo("nombre"));
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

    public void agregarUsuario() {
        System.out.print("Ingrese nombre de usuario: ");
        Scanner nombreUsuario = new Scanner(System.in);
        String nombre = nombreUsuario.nextLine();
        Usuario nuevoUsuario = new UsuarioEstandar(nombre);

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

    public void modificarContraseña() {
        System.out.print("Ingrese nombre de usuario que quiere modificar la contraseña: ");
        Scanner nombreUsuario = new Scanner(System.in);
        String nombre = nombreUsuario.nextLine();
        //crear lista de todos los usuarios
        if (misUsuarios.contains(nombre)) { //aca busco en la supuesta lista de usuarios a ver si existe el pibito
            System.out.print("Ingrese la nueva contraseña: ");
            Scanner contraseñaUsuario = new Scanner(System.in);
            String contraseña = contraseñaUsuario.nextLine();

            System.out.print("Ingrese su contraseña nuevamente para su verificación: ");
            Scanner contraseñaUsuario2 = new Scanner(System.in);
            String contraseña2 = contraseñaUsuario2.nextLine();

            if (contraseña.equals(contraseña2)) {
                nuevoUsuario.setContraseña = contraseña;
            } else {
                System.out.print("ERROR! Las contraseñas no coinciden");
            }

        } else {
            System.out.print("Usuario no existente");
        }
    }

    public void eliminarUsuario() {
        System.out.print("Ingrese nombre de usuario que desea eliminar: ");
        Scanner nombreUsuario = new Scanner(System.in);
        String nombre = nombreUsuario.nextLine();

        int indice = misUsuarios.indexOf(nombre);

        if (indice != -1) { // aca borro al pibito de la lista
            misUsuarios.remove(nombre);
        } else {
            System.out.print("No hay registro de ese usuario");
        }
    }

    public void crearArchivo() {
        System.out.print("Ingrese nombre del archivo: ");
        Scanner nombreArchivo = new Scanner(System.in);
        String nombre = nombreArchivo.nextLine();
        Archivo nuevoArchivo = new Archivo(nombre);
    }

    public void agregarTexto(Archivo arch) {
      /*  System.out.print("Ingrese texto a agregar: ");
        Scanner textoNuevo = new Scanner(System.in);
        String texto = textoNuevo.nextLine();
        arch.agregarContenido(texto);*/
    }
}
