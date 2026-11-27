import java.util.ArrayList;
import java.util.Scanner;

/**
 * RESPONSABILIDAD: todo lo que ve y escribe el usuario (entrada y salida por consola).
 * Cada metodo publico es una pantalla del menu: pide datos, llama a Functions
 * y muestra el resultado. No guarda datos ni tiene logica del negocio.
 */
public class Pantallas {

    private final Functions functions;
    private final Scanner scanner;

    public Pantallas(Functions functions) {
        this.functions = functions;
        this.scanner = new Scanner(System.in);
    }

    // ================= MENU =================

    public void mostrarMenu() {
        System.out.println();
        System.out.println("  +========================================+");
        System.out.println("  |           SISTEMA DE TURNOS            |");
        System.out.println("  +========================================+");
        System.out.println(linea("1. Registrar persona"));
        System.out.println(linea("2. Mostrar personas registradas"));
        System.out.println(linea("3. Asignar turno"));
        System.out.println(linea("4. Atender siguiente"));
        System.out.println(linea("5. Ver turnos pendientes"));
        System.out.println(linea("6. Cancelar turno"));
        System.out.println(linea("0. Salir"));
        System.out.println("  +----------------------------------------+");
        System.out.println(linea("Turnos en espera: " + functions.cantidadPendientes()));
        System.out.println("  +----------------------------------------+");
    }

    // Arma una linea del recuadro con el texto alineado a la izquierda
    private String linea(String texto) {
        return "  |  " + String.format("%-38s", texto) + "|";
    }

    public void despedida() {
        System.out.println("  Hasta luego!");
    }

    public void opcionInvalida() {
        System.out.println("  [ERROR] Opcion invalida. Elija de 0 a 6.");
    }

    // ================= PANTALLAS =================

    // Opcion 1
    public void registrarPersona() {
        System.out.println("  >> REGISTRAR PERSONA");
        String nombre = leerTexto("  Nombre y apellido: ");
        String dni = leerTexto("  DNI (7 u 8 numeros): ");
        String telefono = leerTexto("  Telefono: ");

        try {
            Persona p = functions.registrarPersona(nombre, dni, telefono);
            System.out.println("  [OK] Persona registrada: " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // Opcion 2
    public void mostrarPersonas() {
        System.out.println("  >> PERSONAS REGISTRADAS");
        ArrayList<Persona> lista = functions.getPersonas();

        // Validacion: lista vacia
        if (lista.isEmpty()) {
            System.out.println("  [AVISO] No hay personas registradas.");
            return;
        }

        String formato = "  %-26s %-10s %-15s%n";
        System.out.printf(formato, "Nombre", "DNI", "Telefono");
        System.out.println("  ------------------------------------------------------");
        for (Persona p : lista) {
            System.out.printf(formato, p.getNombre(), p.getDni(), p.getTelefono());
        }
        System.out.println("  Total: " + lista.size() + " persona(s).");
    }

    // Opcion 3
    public void asignarTurno() {
        System.out.println("  >> ASIGNAR TURNO");
        String dni = leerTexto("  DNI de la persona: ");
        String motivo = leerTexto("  Motivo del turno: ");

        try {
            Turno t = functions.asignarTurno(dni, motivo);
            System.out.println("  [OK] Turno asignado.");
            System.out.println("      Turno nro : " + t.getNumero());
            System.out.println("      Persona   : " + t.getPersona().getNombre());
            System.out.println("      Motivo    : " + t.getMotivo());
            System.out.println("      Personas delante suyo: " + (functions.cantidadPendientes() - 1));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // Opcion 4: saca al primero de la cola
    public void atenderSiguiente() {
        System.out.println("  >> ATENDER SIGUIENTE");
        Turno atendido = functions.atenderSiguiente();

        // Validacion: cola vacia
        if (atendido == null) {
            System.out.println("  [AVISO] No hay turnos pendientes para atender.");
            return;
        }

        System.out.println("  [OK] Atendiendo ahora:");
        System.out.println("      Turno nro : " + atendido.getNumero());
        System.out.println("      Persona   : " + atendido.getPersona().getNombre()
                + " (DNI " + atendido.getPersona().getDni() + ")");
        System.out.println("      Motivo    : " + atendido.getMotivo());

        Turno proximo = functions.verProximo();
        if (proximo == null) {
            System.out.println("  No quedan mas turnos en espera.");
        } else {
            System.out.println("  Quedan " + functions.cantidadPendientes()
                    + " en espera. Sigue: " + proximo.getPersona().getNombre()
                    + " (turno " + proximo.getNumero() + ")");
        }
    }

    // Opcion 5: muestra la cola en orden de llegada
    public void verPendientes() {
        System.out.println("  >> TURNOS PENDIENTES (orden de llegada)");
        ArrayList<Turno> lista = functions.getPendientes();

        // Validacion: cola vacia
        if (lista.isEmpty()) {
            System.out.println("  [AVISO] No hay turnos pendientes.");
            return;
        }

        String formato = "  %-4s %-6s %-26s %-10s %-25s%n";
        System.out.printf(formato, "Pos", "Turno", "Nombre", "DNI", "Motivo");
        System.out.println("  --------------------------------------------------------------------");
        int posicion = 1;
        for (Turno t : lista) {
            System.out.printf(formato, posicion, t.getNumero(), t.getPersona().getNombre(),
                    t.getPersona().getDni(), t.getMotivo());
            posicion++;
        }
        System.out.println("  Total en espera: " + lista.size());
    }

    // Opcion 6
    public void cancelarTurno() {
        System.out.println("  >> CANCELAR TURNO");
        int numero = leerEntero("  Numero de turno: ");

        Turno turno = functions.buscarTurno(numero);
        if (turno == null) {
            System.out.println("  [AVISO] No hay un turno pendiente con ese numero.");
            return;
        }

        System.out.println("  Se va a cancelar: " + turno);
        String respuesta = leerTexto("  Confirmar? (s/n): ");
        if (respuesta.equalsIgnoreCase("s")) {
            functions.cancelarTurno(numero);
            System.out.println("  [OK] Turno cancelado.");
        } else {
            System.out.println("  [AVISO] Operacion cancelada.");
        }
    }

    // ================= LECTURA POR TECLADO =================

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    // Si el usuario escribe letras, devuelve -1 en vez de romper el programa
    public int leerEntero(String mensaje) {
        String texto = leerTexto(mensaje);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
