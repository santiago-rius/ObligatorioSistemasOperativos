/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import ClasesAuxiliares.Nodo;

/**
 *
 * @author Santiago
 */
public class Directorios {
    private Nodo<Directorio> raiz;
    
    public Directorios() {
        raiz = new Nodo<>(new Directorio("/"), raiz);
    }
    
    public void AgregarDirectorio(String ruta) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String ObtenerNombre(String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Nodo<Directorio> buscarDirectorio(Nodo<Directorio> raiz, String rutaPadre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void AgregarHijo(Nodo<Directorio> directorioPadre, String nombreDir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
