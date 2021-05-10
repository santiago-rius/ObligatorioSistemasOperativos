/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago
 */
public class Directorio {

    List<Archivo> listaArchivos;
    String nombre;

    public Directorio(String nombre) {
        this.nombre = nombre;
        listaArchivos = new ArrayList<>();
    }

    public List<Archivo> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(List<Archivo> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void AgregarArchivo(Archivo arch) {
        if(!contieneArchivo(arch)) {
            this.listaArchivos.add(arch);
        }
    }
    
    public void AgregarArchivo(String nombreArchivo) {
        if(!contieneArchivo(nombreArchivo)) {
            this.listaArchivos.add(new Archivo(nombreArchivo));
        }
    }

    public boolean contieneArchivo(Archivo arch) {
        return this.listaArchivos.contains(arch);
    }

    public boolean contieneArchivo(String nombreArchivo) {
        Archivo aux = new Archivo(nombreArchivo);
        return this.listaArchivos.contains(aux);
    }

    public Archivo devolverArchivo(String nombreArchivo) {
        Archivo ret = null;
        if (contieneArchivo(nombreArchivo)) {
            for (int i = 0; i < this.listaArchivos.size(); i++) {
                Archivo aux = this.listaArchivos.get(i);
                if (aux.equals(new Archivo(nombreArchivo))) {
                    ret = aux;
                }
            }
        }
        return ret;
    }

    public Archivo devolverArchivo(Archivo arch) {
        Archivo ret = null;
        if(contieneArchivo(arch))
        {
            for (int i = 0; i < this.listaArchivos.size(); i++) {
                Archivo aux = this.listaArchivos.get(i);
                if (aux.equals(arch)) {
                    ret = aux;
                }
            }
        }
        return ret;
    }
    
    public Archivo borrarYDevolverArchivo(String nombreArchivo) {
        Archivo ret = null;
        if(contieneArchivo(nombreArchivo)) {
            for (int i = 0; i < this.listaArchivos.size(); i++) {
                Archivo aux = this.listaArchivos.get(i);
                if(aux.equals(new Archivo(nombreArchivo))) {
                    ret = aux;
                    this.listaArchivos.remove(i);
                }
            }
        }
        return ret;
    }
    
    public Archivo borrarYDevolverArchivo(Archivo arch) {
        Archivo ret = null;
        if(contieneArchivo(arch)) {
            for (int i = 0; i < this.listaArchivos.size(); i++) {
                Archivo aux = this.listaArchivos.get(i);
                if(aux.equals(arch)) {
                    ret = aux;
                    this.listaArchivos.remove(i);
                }
            }
        }
        return ret;
    }
}
