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
        
    }

    public Nodo<Directorio> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<Directorio> raiz) {
        this.raiz = raiz;
    }
}
