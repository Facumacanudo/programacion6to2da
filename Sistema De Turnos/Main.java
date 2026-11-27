/**
 * 
 * RESPONSABILIDAD: arrancar el programa y repetir el menu hasta que el usuario salga.
 * No guarda datos ni tiene logica: solo deriva a cada pantalla.
 */
public class Main {

    public static void main(String[] args) {
        // Se crean los objetos y se conectan entre si
        Database db = new Database();
        Functions functions = new Functions(db);
        Pantallas pantallas = new Pantallas(functions);

        int opcion;
        do {
            pantallas.mostrarMenu();
            opcion = pantallas.leerEntero("  Elija una opcion: ");
            System.out.println();
            switch (opcion) {
                case 1: pantallas.registrarPersona(); break;
                case 2: pantallas.mostrarPersonas(); break;
                case 3: pantallas.asignarTurno(); break;
                case 4: pantallas.atenderSiguiente(); break;
                case 5: pantallas.verPendientes(); break;
                case 6: pantallas.cancelarTurno(); break;
                case 0: pantallas.despedida(); break;
                default: pantallas.opcionInvalida();
            }
        } while (opcion != 0);
    }
}
