import java.util.ArrayList;
//Logica de las principales funciones del sis de turnos
public class Functions {
    private final Database db;

    public Functions(Database db) {
        this.db = db;
    }

    // ---------- Personas ----------

    public Persona registrarPersona(String nombre, String dni, String telefono) {
        Persona existente = db.buscarPersona(dni);

        if (existente != null) {
            throw new IllegalArgumentException(
                    "El DNI ya está registrado a nombre de: " + existente.getNombre()
            );
        }

        Persona nueva = new Persona(nombre, dni, telefono);
        db.agregarPersona(nueva);
        return nueva;
    }

    public ArrayList<Persona> getPersonas() {
        return db.getPersonas();
    }

    public Persona buscarPersona(String dni) {
        Persona persona = db.buscarPersona(dni);

        if (persona == null) {
            throw new IllegalArgumentException("La persona buscada no existe.");
        }

        return persona;
    }

    // ---------- Turnos ----------

    public Turno asignarTurno(String dni, String motivo) {
        Persona persona = db.buscarPersona(dni);

        if (persona == null) {
            throw new IllegalArgumentException(
                    "No hay ninguna persona registrada con ese DNI."
            );
        }

        if (tienePendiente(dni)) {
            throw new IllegalStateException(
                    "Esa persona ya tiene un turno pendiente."
            );
        }

        Turno nuevo = new Turno(db.getProximoNumero(), persona, motivo);
        db.encolar(nuevo);
        return nuevo;
    }

    public Turno atenderSiguiente() {
        return db.desencolar();
    }

    public Turno verProximo() {
        return db.verProximo();
    }

    public ArrayList<Turno> getPendientes() {
        return db.getPendientes();
    }

    public int cantidadPendientes() {
        return db.cantidadPendientes();
    }

    public Turno buscarTurno(int numero) {
        for (Turno turno : db.getPendientes()) {
            if (turno.getNumero() == numero) {
                return turno;
            }
        }
        return null;
    }

    public boolean cancelarTurno(int numero) {
        Turno turno = buscarTurno(numero);

        if (turno == null) {
            return false;
        }

        db.eliminarTurno(turno);
        return true;
    }

    private boolean tienePendiente(String dni) {
        for (Turno turno : db.getPendientes()) {
            if (turno.getPersona().getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }
}