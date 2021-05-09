package FileSystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Santiago
 */
public class Archivo {
    private String nombre;
    private String contenido;
    
    
    public Archivo(String nombre) {
        this.nombre = nombre;
        this.contenido = "";
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void agregarContenido (String texto){
        String nuevoTexto = this.contenido+texto;
        this.contenido = nuevoTexto;
    }
    
    public String mostrarContenido(){
        return this.contenido;
    }
    
}
