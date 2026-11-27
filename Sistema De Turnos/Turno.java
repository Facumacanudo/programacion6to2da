/**
 * RESPONSABILIDAD: representar un turno asignado a una persona.
 * Un Turno "tiene una" Persona (relacion de asociacion).
 * Ningun dato se modifica despues de crearse (por eso no hay setters).
 */
public class Turno {

    private final int numero;
    private final Persona persona;
    private final String motivo;

    // Constructor: valida el motivo y que la persona exista
    public Turno(int numero, Persona persona, String motivo) {
        if (persona == null) {
            throw new IllegalArgumentException("El turno necesita una persona.");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo del turno no puede estar vacio.");
        }
        if (motivo.trim().length() > 67) {
            throw new IllegalArgumentException("El motivo no puede superar los 67 caracteres.");
        }
        this.numero = numero;
        this.persona = persona;
        this.motivo = motivo.trim();
    }

    public int getNumero() {
        return numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Turno #" + numero + " | " + persona.getNombre()
                + " | DNI " + persona.getDni() + " | Motivo: " + motivo;
    }
}
