package mx.com.gm.sga.servicio;

import java.util.List;
import javax.ejb.Remote;
import mx.com.gm.sga.domain.Usuario;

@Remote
public interface UsuarioServiceRemote {

    public List<Usuario> listarUsuarios();

    public Usuario encontrarUsuarioPorId(Usuario usuario);

    public void registrarUsuario(Usuario usuarioa);

    public void modificarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
}
