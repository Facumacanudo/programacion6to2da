import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Esto solo es para simular un archivo el cual guardar los datos
 * RESPONSABILIDAD: guardar los datos (simula una base de datos).
 * Solo guarda y entrega datos: no valida ni muestra nada.
 *
 * Estructuras dinamicas:
 *  - LISTA (ArrayList) de personas registradas.
 *  - COLA (Queue / LinkedList) de turnos pendientes: el primero que llega es el primero en salir (FIFO).
 */
public class Database {

    private final ArrayList<Persona> personas;
    private final Queue<Turno> pendientes;
    private int proximoNumero;

    public Database() {
        personas = new ArrayList<>();
        pendientes = new LinkedList<>();
        proximoNumero = 1;
    }

    // ---------- Personas (lista) ----------

    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    // Recorre la lista; devuelve null si no encuentra la persona
    public Persona buscarPersona(String dni) {
        for (Persona p : personas) {
            if (p.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }

    // Devuelve una COPIA: asi nadie de afuera puede modificar la lista original
    public ArrayList<Persona> getPersonas() {
        return new ArrayList<>(personas);
    }

    // ---------- Turnos (cola) ----------

    public int getProximoNumero() {
        return proximoNumero;
    }

    // ENQUEUE: el turno entra al final de la cola. Recien ahi avanza el numero.
    public void encolar(Turno turno) {
        pendientes.offer(turno);
        proximoNumero++;
    }

    // DEQUEUE: saca y devuelve el primero de la cola (null si esta vacia)
    public Turno desencolar() {
        return pendientes.poll();
    }

    // PEEK: mira el primero sin sacarlo (null si esta vacia)
    public Turno verProximo() {
        return pendientes.peek();
    }

    // Copia de la cola, en orden de llegada
    public ArrayList<Turno> getPendientes() {
        return new ArrayList<>(pendientes);
    }

    public int cantidadPendientes() {
        return pendientes.size();
    }

    // Quita un turno puntual de la cola (para cancelaciones)
    public void eliminarTurno(Turno turno) {
        pendientes.remove(turno);
    }
}
