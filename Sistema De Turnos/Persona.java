/**
 * RESPONSABILIDAD: representar a una persona y validar sus datos.
 * El DNI identifica a la persona y no se puede cambiar una vez creada.
 //Aca solo modifique la clase persona que realizamos en clase
 */
public class Persona {

    // Atributos privados: solo se acceden con getters y setters (encapsulamiento)
    private String nombre;
    private final String dni;
    private String telefono;

    // Constructor: valida todos los datos antes de crear la persona
    public Persona(String nombre, String dni, String telefono) {
        setNombre(nombre);
        if (!esSoloDigitos(dni) || (dni.length() != 7 && dni.length() != 8)) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 numeros.");
        }
        this.dni = dni;
        setTelefono(telefono);
    }

    // ---------- Getters ----------

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    // ---------- Setters con validacion ----------

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (nombre.trim().length() > 25) {
            throw new IllegalArgumentException("El nombre no puede superar los 25 caracteres.");
        }
        this.nombre = nombre.trim();
    }

    public void setTelefono(String telefono) {
        if (!esSoloDigitos(telefono) || telefono.length() < 6 || telefono.length() > 15) {
            throw new IllegalArgumentException("El telefono debe tener entre 6 y 15 numeros.");
        }
        this.telefono = telefono;
    }

    // ---------- Auxiliar ----------

    // Revisa, caracter por caracter, que el texto tenga solo digitos del 0 al 9
    private boolean esSoloDigitos(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + " | DNI " + dni + " | Tel " + telefono;
    }
}
