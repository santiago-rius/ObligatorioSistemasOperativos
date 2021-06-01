package Consola;

import FileSystem.Directorios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author evejo
 */
public class Sesion {

    List<Usuario> misUsuarios;
    Usuario usuarioActual;
    String ruta;
    Directorios directorios;
    List<String> historialComandos;

    public Sesion() {
        misUsuarios = new ArrayList<>();
        usuarioActual = new Usuario("visita");
        ruta = "/";
        directorios = new Directorios();
        Usuario root = new Usuario("root");
        root.setContraseña("root");
        misUsuarios.add(root);
        historialComandos = new ArrayList<>();
    }

    Usuario getUsuario() {
        return this.usuarioActual;
    }

    void setUsuarioActual(Usuario usuarioA) {
        this.usuarioActual = usuarioA;
    }

    public void setRuta(String rutaActual) {
        this.ruta = rutaActual;
    }

    public Directorios getDirectorios() {
        return directorios;
    }

    public void setDirectorios(Directorios directorios) {
        this.directorios = directorios;
    }

    public void agregarAMisUsuarios(Usuario u) {
        this.misUsuarios.add(u);
    }

    public List<String> getHistorialComandos() {
        return historialComandos;
    }

    public void setHistorialComandos(List<String> historialComandos) {
        this.historialComandos = historialComandos;
    }

    /**
     *
     * @return el historial de comandos de la sesion ya pronto para mostrar
     */
    public String getHistorialDeComandosPretty() {
        String historial = "";
        for (int i = 0; i < historialComandos.size(); i++) {
            historial += "    " + (i + 1) + " ";
            historial += historialComandos.get(i);
            historial += "\n";
        }
        return historial;
    }
}
