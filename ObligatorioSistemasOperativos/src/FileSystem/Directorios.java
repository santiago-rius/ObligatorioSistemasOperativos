/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import ClasesAuxiliares.Nodo;
import java.util.Arrays;

/**
 *
 * @author Santiago
 */
public class Directorios {

    private Nodo<Directorio> raiz;

    public Directorios() {
        raiz = new Nodo<>(new Directorio("/"), raiz);
    }

    public void AgregarDirectorio(String ruta) { //Mkdir -- falta verificacion
        String rutaPadre = RutaPadre(ruta);
        String nombreDir = ObtenerNombre(ruta);
        Nodo<Directorio> directorioPadre = buscarDirectorio(raiz, rutaPadre);
        AgregarHijo(directorioPadre, nombreDir);
    }

    public Nodo<Directorio> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<Directorio> raiz) {
        this.raiz = raiz;
    }

    private String RutaPadre(String ruta) {
        String[] aux = ruta.split("/");
        String rutaPadre = reformarString(aux, 1, aux.length);
        return rutaPadre;
    }

    private String reformarString(String[] lista, int comienzo, int fin) {
        String ret = "";
        if (comienzo < fin && comienzo < lista.length && fin < lista.length) {
            String[] listaNueva = Arrays.copyOfRange(lista, comienzo, fin);
            for (int i = 0; i < listaNueva.length - 1; i++) {
                ret += listaNueva[i];
            }
        }
        return ret;
    }

    private String ObtenerNombre(String ruta) {
        String[] aux = ruta.split("/");
        int largo = aux.length;
        String nombreDir = aux[largo];
        return nombreDir;
    }

    public Nodo<Directorio> buscarDirectorio(Nodo<Directorio> raiz, String ruta, int primeraVez) {
        if (raiz == null || ruta.equals("")) {
            return null;
        } else if (ruta.equals("/")) {
            return this.raiz;
        } else {
            if (primeraVez == 1) {
                return buscarDirectorio(raiz.getpH(), ruta, 0);
            }
            String[] listaRuta = ruta.split("/");
            if (listaRuta[0].equals("/")) {
                listaRuta = Arrays.copyOfRange(listaRuta, 1, listaRuta.length);
            }
            if (raiz.getDato().nombre.equals(listaRuta[0])) {
                if (listaRuta.length == 1) {
                    return raiz;
                } else {
                    listaRuta = Arrays.copyOfRange(listaRuta, 1, listaRuta.length);
                    String rutaNueva = reformarString(listaRuta, 1, listaRuta.length);
                    return buscarDirectorio(raiz.getpH(), rutaNueva, 0);
                }
            } else {
                return buscarDirectorio(raiz.getsH(), ruta, 0);
            }
        }
    }

    private void AgregarHijo(Nodo<Directorio> directorioPadre, String nombreDir) {
        Directorio nuevoDir = new Directorio(nombreDir);
        if (directorioPadre != null) {
            //falta implementar
        }
    }
}
