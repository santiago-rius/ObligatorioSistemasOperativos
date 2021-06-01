package FileSystem;

import static ClasesAuxiliares.Utils.convertirMascara;
import Consola.Usuario;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
    private List<String> contenido;
    private Usuario propietario;
    private String mascara;
    private GregorianCalendar fechaCreacion;

    public Archivo(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.fechaCreacion = new GregorianCalendar();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarContenido(String texto) {
        this.contenido.add(texto);
    }

    public List<String> getContenido() {
        return contenido;
    }

    public void setContenido(List<String> contenido) {
        this.contenido = contenido;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String mascaraString = convertirMascara(mascara);
        sb.append(mascaraString).append(" - ");
        sb.append(propietario.getNombre()).append(" ");
        int largoColumnaUsuario = 20 - propietario.getNombre().length();
        while(largoColumnaUsuario > 0) {
            sb.append(" ");
            largoColumnaUsuario--;
        }
        sb.append(" - ");
        sb.append(contenido.size()).append(" ");
        sb.append(" - ");
        sb.append(nombre);
        sb.append(" - ");
        sb.append(fechaCreacion.toZonedDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy hh mm ss")));
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Archivo other = (Archivo) obj;
        return this.nombre.equals(other.nombre);
    }
}
