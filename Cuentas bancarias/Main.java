import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Cuenta[] cuentas = new Cuenta[5];

        // Ingresar las 5 cuentas
        for (int i = 0; i < cuentas.length; i++) {

            System.out.println("Cuenta " + (i + 1));

            System.out.print("Titular: ");
            String titular = teclado.nextLine();

            System.out.print("Saldo: ");
            double saldo = teclado.nextDouble();

            System.out.print("Sucursal: ");
            int sucursal = teclado.nextInt();

            cuentas[i] = new Cuenta(titular, saldo, sucursal);

            // Limpiar ENTER
            teclado.nextLine();
        }

        // Realizar movimientos
        for (int i = 0; i < cuentas.length; i++) {

            System.out.println("\nCuenta de " + cuentas[i].getTitular());

            System.out.print("¿Cuánto desea depositar? ");
            double deposito = teclado.nextDouble();
            cuentas[i].depositar(deposito);

            System.out.print("¿Cuánto desea extraer? ");
            double extraccion = teclado.nextDouble();
            cuentas[i].extraer(extraccion, teclado);
        }

        // Mostrar resultados
        System.out.println("\n--- RESULTADOS ---");

        System.out.println("Total de dinero del banco: $"
                + totalDinero(cuentas));

        System.out.print("Ingrese una letra: ");
        char letra = teclado.next().charAt(0);

        System.out.println("Titulares que comienzan con " + letra + ": "
                + cantidadTitulares(cuentas, letra));

        cantidadSucursal(cuentas);
    }

    // Total de dinero
    public static double totalDinero(Cuenta[] cuentas) {

        double total = 0;

        for (int i = 0; i < cuentas.length; i++) {
            total += cuentas[i].getSaldo();
        }

        return total;
    }

    // Cantidad de titulares que empiezan con una letra
    public static int cantidadTitulares(Cuenta[] cuentas, char letra) {

        int cantidad = 0;

        for (int i = 0; i < cuentas.length; i++) {

            if (cuentas[i].getTitular().toLowerCase().charAt(0)
                    == Character.toLowerCase(letra)) {

                cantidad++;
            }
        }

        return cantidad;
    }

    // Muestra cada sucursal y la cantidad de cuentas que tiene, en orden ascendente.
    public static void cantidadSucursal(Cuenta[] cuentas) {

        //aca solo estoy testeando el treemap y map
        //https://www.youtube.com/watch?v=1rziRfwiAk4



            //clave - valor 
        Map<Integer, Integer> cantidadesPorSucursal = new TreeMap<>();

        for (int i = 0; i < cuentas.length; i++) {
            int sucursal = cuentas[i].getSucursal();
            int cantidadActual = cantidadesPorSucursal.getOrDefault(sucursal, 0);
            cantidadesPorSucursal.put(sucursal, cantidadActual + 1);
        }


        /* 
        for (Map.Entry<...>: Recorre cada una de esas parejas. (clave-valor)

        entrySet(): Devuelve el conjunto de todas las parejas (clave-valor) que fuimos guardando en el mapa.

        entrada.getKey(): Obtiene la clave (el número de la sucursal).

        entrada.getValue(): Obtiene el valor (el total de cuentas de esa sucursal).*/

        for (Map.Entry<Integer, Integer> entrada : cantidadesPorSucursal.entrySet()) {
            System.out.println("Sucursal " + entrada.getKey() + ": "
                    + entrada.getValue());
        }
    }
}