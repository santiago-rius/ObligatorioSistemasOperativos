package Consola;

import ClasesAuxiliares.Nodo;
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
//        Directorios dir = new Directorios();
//        dir.agregarDirectorio("/carpeta");
//        dir.agregarDirectorio("/carpeta/carpeta2");
//        Nodo<Directorio> test = dir.buscarDirectorio(dir.getRaiz(), "/carpeta/carpeta2", 1);
//        dir.eliminarDirectorio("/carpeta/carpeta2");

        Sistema sistema = new Sistema();
        while(!sistema.isExit()) {
            sistema.inputConsola();
        }
    }
    
}
