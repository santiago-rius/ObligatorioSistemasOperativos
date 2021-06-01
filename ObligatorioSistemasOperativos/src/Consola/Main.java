package Consola;

import FileSystem.Archivo;
import FileSystem.Directorios;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Santiago
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostrarTitulo();
        Sistema sistema = new Sistema();
        crearDatosDePrueba(sistema.getSesionActual().getDirectorios(), sistema.getSesionActual());
        while (!sistema.isExit()) {
            try {
                sistema.inputConsola();
            } catch (Exception e) {
                System.out.println("Error inesperado:");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void crearDatosDePrueba(Directorios dir, Sesion ses) {
        //Usuarios
        ses.misUsuarios.add(new Usuario("prueba"));

        //directorios
        dir.agregarDirectorio("/Documentos");
        dir.agregarDirectorio("/Documentos/ORT");
        dir.agregarDirectorio("/Documentos/Otros");
        dir.agregarDirectorio("/Imagenes");
        dir.agregarDirectorio("/Imagenes/2021");
        dir.agregarDirectorio("/Musica");
        dir.agregarDirectorio("/Musica/Rock");
        dir.agregarDirectorio("/Musica/Jazz");
        dir.agregarDirectorio("/Musica/Rock/ACDC");
        dir.agregarDirectorio("/Musica/Rock/LedZeppelin");

        //archivos
        String m = "704";
        Archivo a1 = new Archivo("materias.txt");
        a1.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a1.setMascara(m);
        a1.setPropietario(new Usuario("prueba"));
        a1.agregarContenido("Arquitectura de Sistemas");
        a1.agregarContenido("Sistemas Operativos");
        a1.agregarContenido("Bases de datos 1");
        a1.agregarContenido("Bases de datos 2");
        a1.agregarContenido("Diseño de aplicaciones 1");
        a1.agregarContenido("Diseño de aplicaciones 2");
        dir.buscarDirectorio(dir.getRaiz(), "/Documentos/ORT", 1).getDato().AgregarArchivo(a1);

        Archivo a2 = new Archivo("carreras.txt");
        a2.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a2.setPropietario(new Usuario("prueba"));
        a2.setMascara(m);
        a2.agregarContenido("Ingenieria en Sistemas");
        a2.agregarContenido("Licenciatura en Sistemas");
        a2.agregarContenido("Ingeneria Electronica");
        a2.agregarContenido("Diseño");
        a2.agregarContenido("Comunicacion");
        dir.buscarDirectorio(dir.getRaiz(), "/Documentos/ORT", 1).getDato().AgregarArchivo(a2);

        Archivo a3 = new Archivo("oso.jpg");
        a3.setMascara(m);
        a3.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a3.setPropietario(new Usuario("prueba"));
        a3.agregarContenido(" __         __\n"
                + "/  \\.-\"\"\"-./  \\\n"
                + "\\    -   -    /\n"
                + " |   o   o   |\n"
                + " \\  .-'''-.  /\n"
                + "  '-\\__Y__/-'\n"
                + "     `---`");
        dir.buscarDirectorio(dir.getRaiz(), "/Imagenes", 1).getDato().AgregarArchivo(a3);

        Archivo a4 = new Archivo("playa.jpg");
        a4.setMascara(m);
        a4.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a4.setPropietario(new Usuario("prueba"));
        a4.agregarContenido("      \\.  -   -  .\n"
                + "     '          _ , -`.\n"
                + "   '        _,'     _,'\n"
                + "  '      ,-'      _/\n"
                + " '    ,-' \\     _/       __,,_\n"
                + "'   ,'     \\  _'        ////6 6\n"
                + "'  '       _\\'          \\\\\\'  >\n"
                + "' ,    _,-'  \\    _______ ) _=\n"
                + "\\,_,--'       \\   \\\\__ __/ /_\\\n"
                + "               \\   \\\\+/   ___ \\\n"
                + "                \\   \\\\| '/ ),__)\n"
                + "                 \\   \\\\ /\\/ ( +\\\n"
                + "                  \\   \\\\ \\___`-.________\n"
                + "                   \\   \\\\__,( \\_____  - \\\n"
                + "                    \\   \\`---\\/\\----), ) \\\n"
                + "                     \\   ||+=+=+=+=/  /\\  \\\n"
                + "                      \\  ||________| /\\ `. \\\n"
                + "                       \\ ||------- )/-\\\\  ) \\\n"
                + "                        \\||      ,'/   \\\\  \\ \\\n"
                + "                                / /         '-`\n"
                + "                                \\/");
        dir.buscarDirectorio(dir.getRaiz(), "/Imagenes", 1).getDato().AgregarArchivo(a4);

        Archivo a5 = new Archivo("Back_in_Black.mp3");
        a5.setMascara(m);
        a5.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a5.setPropietario(new Usuario("prueba"));
        dir.buscarDirectorio(dir.getRaiz(), "/Musica/Rock/ACDC", 1).getDato().AgregarArchivo(a5);
        
        Archivo a6 = new Archivo("TNT.mp3");
        a6.setMascara(m);
        a6.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a6.setPropietario(new Usuario("prueba"));
        dir.buscarDirectorio(dir.getRaiz(), "/Musica/Rock/ACDC", 1).getDato().AgregarArchivo(a6);
        
        Archivo a7 = new Archivo("Stairway_to_heaven.mp3");
        a7.setMascara(m);
        a7.setFechaCreacion(GregorianCalendar.from(ZonedDateTime.now()));
        a7.setPropietario(new Usuario("prueba"));
        dir.buscarDirectorio(dir.getRaiz(), "/Musica/Rock/LedZeppelin", 1).getDato().AgregarArchivo(a7);
    }

    public static void mostrarTitulo() {
        System.out.println("   ____  _     _ _             _             _          _____ _     _                              ____                       _   _                \n"
                + "  / __ \\| |   | (_)           | |           (_)        / ____(_)   | |                            / __ \\                     | | (_)               \n"
                + " | |  | | |__ | |_  __ _  __ _| |_ ___  _ __ _  ___   | (___  _ ___| |_ ___ _ __ ___   __ _ ___  | |  | |_ __   ___ _ __ __ _| |_ ___   _____  ___ \n"
                + " | |  | | '_ \\| | |/ _` |/ _` | __/ _ \\| '__| |/ _ \\   \\___ \\| / __| __/ _ | '_ ` _ \\ / _` / __| | |  | | '_ \\ / _ | '__/ _` | __| \\ \\ / / _ \\/ __|\n"
                + " | |__| | |_) | | | (_| | (_| | || (_) | |  | | (_) |  ____) | \\__ | ||  __| | | | | | (_| \\__ \\ | |__| | |_) |  __| | | (_| | |_| |\\ V | (_) \\__ \\\n"
                + "  \\____/|_.__/|_|_|\\__, |\\__,_|\\__\\___/|_|  |_|\\___/  |_____/|_|___/\\__\\___|_| |_| |_|\\__,_|___/  \\____/| .__/ \\___|_|  \\__,_|\\__|_| \\_/ \\___/|___/\n"
                + "                    __/ |                                                                               | |                                        \n"
                + "                   |___/                                                                                |_|                                        ");
        System.out.println("   ____  __                            __          __                 ____  _           \n"
                + "  / __ \\/ /____  _________            / ____  ____/ __  _______      / __ \\(___  _______\n"
                + " / / / / __/ _ \\/ ___/ __ \\________  / / __ \\/ __  / / / / _________/ /_/ / / / / / ___/\n"
                + "/ /_/ / /_/  __/ /  / /_/ /_____/ /_/ / /_/ / /_/ / /_/ (__  /_____/ _, _/ / /_/ (__  ) \n"
                + "\\____/\\__/\\___/_/   \\____/      \\____/\\____/\\__,_/\\__,_/____/     /_/ |_/_/\\__,_/____/  \n"
                + "                                                                                        ");
    }
}
