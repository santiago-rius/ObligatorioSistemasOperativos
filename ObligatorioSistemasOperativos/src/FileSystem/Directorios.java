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
        raiz = new Nodo<>(new Directorio("/"), null, null);
    }

    public Nodo<Directorio> agregarDirectorio(String ruta) { //Mkdir -- falta verificacion
        String rutaPadre = rutaPadre(ruta);
        String nombreDir = obtenerNombre(ruta);
        Nodo<Directorio> nodoPadre = buscarDirectorio(raiz, rutaPadre, 1);
        nodoPadre.setpH(agregarHijo(nodoPadre.getpH(), nombreDir));
        return nodoPadre;
    }

    public Nodo<Directorio> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<Directorio> raiz) {
        this.raiz = raiz;
    }

    private String rutaPadre(String ruta) {
        String rutaPadre;
        int posUltimo = 0;
        for (int i = 0; i < ruta.length(); i++) {
            if(ruta.charAt(i) == '/') {
                posUltimo = i;
            }
        }
        if(posUltimo == 0) {
            return "/";
        }
        rutaPadre = ruta.substring(0, posUltimo+1);
        return rutaPadre;
    }

    private String reformarString(String[] lista, int comienzo, int fin) {
        String ret = "";
        if (comienzo < fin && comienzo < lista.length && fin <= lista.length) {
            String[] listaNueva = Arrays.copyOfRange(lista, comienzo, fin);
            for (int i = 0; i < listaNueva.length; i++) {
                ret += listaNueva[i];
            }
        }
        return ret;
    }

    private String reformarStringConSep(String[] lista, int comienzo, int fin, String sep) {
        String ret = "";
        if (comienzo < fin && comienzo < lista.length && fin <= lista.length) {
            String[] listaNueva = Arrays.copyOfRange(lista, comienzo, fin);
            for (int i = 0; i < listaNueva.length; i++) {
                if (i == listaNueva.length - 1) {
                    ret += listaNueva[i];
                } else {
                    ret += listaNueva[i] + "/";
                }
            }
        }
        return ret;
    }

    private String obtenerNombre(String ruta) {
        String[] aux = ruta.split("/");
        aux[0] = "/";
        int largo = aux.length;
        String nombreDir = aux[largo - 1];
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
            if (listaRuta[0].equals("")) {
                listaRuta = Arrays.copyOfRange(listaRuta, 1, listaRuta.length);
            }
            if (raiz.getDato().nombre.equals(listaRuta[0])) {
                if (listaRuta.length == 1) {
                    return raiz;
                } else {
                    listaRuta = Arrays.copyOfRange(listaRuta, 1, listaRuta.length);
                    String rutaNueva = reformarStringConSep(listaRuta, 0, listaRuta.length, "/");
                    return buscarDirectorio(raiz.getpH(), rutaNueva, 0);
                }
            } else {
                return buscarDirectorio(raiz.getsH(), ruta, 0);
            }
        }
    }

    private Nodo<Directorio> agregarHijo(Nodo<Directorio> nodo, String nombreDir) {
        Directorio nuevoDir = new Directorio(nombreDir);
        Nodo<Directorio> nuevoNodo = new Nodo<>(nuevoDir, null, null);
        if (nodo != null) {
            Nodo<Directorio> aux = nodo;
            while (aux.getsH() != null) {
                aux = aux.getsH();
            }
            aux.setsH(nuevoNodo);
        } else {
            nodo = nuevoNodo;
        }
        return nodo;
    }

    public void eliminarDirectorio(String ruta) {
        String rutaPadre = rutaPadre(ruta);
        Nodo<Directorio> padre = buscarDirectorio(raiz, rutaPadre, 1);
        String rutaNombre = obtenerNombre(ruta);
        padre.setpH(eliminarDirectorioAux(padre.getpH(), rutaNombre));
    }

    private Nodo<Directorio> eliminarDirectorioAux(Nodo<Directorio> nodo, String nombreDirABorrar) {
        if (nodo != null) {
            if (nombreDirABorrar.equals("")) {
                eliminarDirectorioAux(nodo.getpH(), "");
                eliminarDirectorioAux(nodo.getsH(), "");
                nodo.getDato().listaArchivos.clear();
                return null;
            } else {
                if (nombreDirABorrar.equals(nodo.getDato().getNombre())) {
                    nodo = nodo.getsH();
                    System.gc();
                    return nodo;
                } else {
                    return eliminarDirectorioAux(nodo.getsH(), nombreDirABorrar);
                }
            }
        } else {
            return null;
        }
    }

    public boolean existeDirectorio(String ruta) {
        Nodo<Directorio> nodo = buscarDirectorio(raiz, ruta, 1);
        return nodo != null;
    }

}
