package FileSystem;

import static ClasesAuxiliares.Utils.convertirMascara;
import Consola.Usuario;

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
    private Usuario propietario;
    private int mascara;

    public Archivo(String nombre) {
        this.nombre = nombre;
        this.contenido = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarContenido(String texto) {
        this.contenido += texto;
    }

    public String getContenido() {
        return this.contenido;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public int getMascara() {
        return mascara;
    }

    public void setMascara(int mascara) {
        this.mascara = mascara;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String mascaraString = convertirMascara(mascara);
        sb.append(mascaraString).append(" - ");
        sb.append(propietario.getNombre()).append("     ");
        sb.append(" - ");
        sb.append(contenido.length()).append(" ");
        sb.append(" - ");
        sb.append(nombre);
        return sb.toString();
    }
}
