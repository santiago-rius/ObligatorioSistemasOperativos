/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

/**
 *
 * @author Santiago
 * @param <T>
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> sH;
    private Nodo<T> pH;

    public Nodo(T dato, Nodo<T> pH, Nodo<T> sH) {
        this.dato = dato;
        this.pH = pH;
        this.sH = sH;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getsH() {
        return sH;
    }

    public void setsH(Nodo<T> sH) {
        this.sH = sH;
    }

    public Nodo<T> getpH() {
        return pH;
    }

    public void setpH(Nodo<T> pH) {
        this.pH = pH;
    }
}
