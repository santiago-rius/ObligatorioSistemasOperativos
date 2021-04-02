/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAuxiliares;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago
 * @param <T>
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> padre;
    private List<Nodo<T>> hijos;

    public Nodo(T dato, Nodo<T> padre) {
        this.dato = dato;
        this.padre = padre;
        this.hijos = new ArrayList<>();
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }

    public List<Nodo<T>> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodo<T>> hijos) {
        this.hijos = hijos;
    }
}
