import java.util.Scanner;
public class Cuenta {
    private String titular;
    private double saldo;
    private int sucursal;

    // Constructor
    public Cuenta(String titular, double saldo, int sucursal) {
        this.titular = titular;
        this.saldo = saldo;
        this.sucursal = sucursal;
    }

    // Getters
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getSucursal() {
        return sucursal;
    }

    // Setters
    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    // Depositar
    public void depositar(double cantidad) {
        saldo += cantidad;
    }

    // Repite la solicitud hasta recibir un monto positivo que no supere el saldo.
    public void extraer(double cantidad, Scanner lector) {
        while (cantidad > saldo) {
             if(cantidad > saldo) {
                System.out.println("La extraccion no puede superar el saldo actual: $" + saldo);
            }

            System.out.print("Ingrese la cantidad de dinero a extraer: ");
            cantidad = lector.nextDouble();
        }

        saldo -= cantidad;
    }
       

    // ToString
    @Override
    public String toString() {
        return "Titular: " + titular +
               ", Saldo: $" + saldo +
               ", Sucursal: " + sucursal;
    }

}