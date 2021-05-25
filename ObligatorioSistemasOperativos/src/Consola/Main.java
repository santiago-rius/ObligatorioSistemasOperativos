package Consola;

import ClasesAuxiliares.Nodo;
import FileSystem.Archivo;
import FileSystem.Directorio;
import FileSystem.Directorios;

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
        System.out.println("Hello World");

//        dir.eliminarDirectorio("/carpeta/carpeta2");

        Sistema sistema = new Sistema();
        crearCarpetasDePrueba(sistema.getSesionActual().getDirectorios(), sistema.getSesionActual());
        while(!sistema.isExit()) {
            sistema.inputConsola();
        }
    }
    
    public static void crearCarpetasDePrueba(Directorios dir, Sesion ses) {
        dir.agregarDirectorio("/carpeta");
        dir.agregarDirectorio("/carpeta/carpeta2");
        Archivo arch = new Archivo("arch.txt");
        arch.setPropietario(ses.getUsuario());
        arch.setMascara(704);
        arch.agregarContenido("prueba prueba \n prueba prueba");
        dir.buscarDirectorio(dir.getRaiz(), "/carpeta", 1).getDato().AgregarArchivo(arch);
    }
    
}
