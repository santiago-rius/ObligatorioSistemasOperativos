
import java.util.List;

/**
 *
 * @author evejo
 */
public class Sesion {
    List <Usuario> misUsuarios;
    Usuario usuarioActual;
    
    Usuario getUsuario(){
        return this.usuarioActual;
    }
    void setUsuario(Usuario usuarioA){
        this.usuarioActual.equals(usuarioA);
    }
    
    void agregarAMisUsuarios(Usuario u){
        this.misUsuarios.add(u);
    }
}
