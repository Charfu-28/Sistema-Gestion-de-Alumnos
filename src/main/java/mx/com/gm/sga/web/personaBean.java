package mx.com.gm.sga.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.*;
import javax.inject.*;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;
import org.apache.logging.log4j.*;
import org.primefaces.event.RowEditEvent;

@Named("personaBean")
@RequestScoped
public class personaBean {

    Logger log = LogManager.getRootLogger();

    @Inject
    private PersonaService personaService;

    private Persona personaSeleccionada;

    List<Persona> personas;

    public personaBean() {
        log.debug("Iniciando el Objeto PersonaBean");
    }

    // Caracteristica de los Manage bean
    @PostConstruct  //Una vez creado el objeto, se pueda inicializar de alguna manera
    public void incializar() {
        // Iniciamos las variables
        this.personas = personaService.listarPersonas();
        log.debug("Personas recuperadas en ManageBean: " + this.personas);
        this.personaSeleccionada = new Persona();
    }

    // Con esto recuperamos cualquier persona que se haya modificado
    public void editListener(RowEditEvent event) {
        Persona persona = (Persona) event.getObject();
        personaService.modificarPersona(persona);
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void agregarPersona() {
        this.personaService.registrarPersona(personaSeleccionada);
        this.personas.add(personaSeleccionada);
        this.personaSeleccionada = null;
    }

    public void eliminarPersona() {
        this.personaService.eliminarPersona(personaSeleccionada);
        this.personas.remove(personaSeleccionada);
        this.personaSeleccionada = null;
    }

    public void reiniciarPersonaSeleccionada() {
        this.personaSeleccionada = new Persona();
    }

}
