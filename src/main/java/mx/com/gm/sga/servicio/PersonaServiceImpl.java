package mx.com.gm.sga.servicio;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.com.gm.sga.datos.PersonaDAO;
import mx.com.gm.sga.domain.Persona;

@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote, PersonaService {

    @Inject
    private PersonaDAO personaDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Persona> listarPersonas() {
        return personaDao.findAllPersonas();
    }

    @Override
    public Persona encontrarPersonaPorId(Persona persona) {
        return personaDao.findPersonaById(persona);
    }

    @Override
    public Persona encontrarPersonaPorEmail(Persona persona) {
        return personaDao.findPersonaByEmail(persona);
    }

    @Override
    public void registrarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    @Override
    public void modificarPersona(Persona persona) {
        try {
            personaDao.updatePersona(persona);
        } catch (Throwable t) { // Clase padre de todas las excepciones en Java
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }

}
