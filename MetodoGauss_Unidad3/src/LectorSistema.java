import java.util.Scanner;

/**
 * Clase encargada de leer y validar desde la consola los datos
 * del sistema lineal: dimensión, matriz de coeficientes A y vector b.
 */
public class LectorSistema {

    private final Scanner scanner;

    /**
     * Recibe una instancia de Scanner para leer la entrada estándar.
     */
    public LectorSistema(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Solicita y valida el número de ecuaciones / incógnitas (n >= 1).
     */
    public int leerDimension() {
        while (true) {
            System.out.print("Ingrese el número de incógnitas / ecuaciones (n >= 1): ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n >= 1) {
                    return n;
                }
                System.out.println("Error: el número de incógnitas debe ser mayor o igual a 1.");
            } else {
                System.out.println("Error: ingrese un número entero válido.");
                scanner.next(); // Descartar entrada inválida
            }
        }
    }

    /**
     * Solicita los n x n coeficientes de la matriz A.
     */
    public double[][] leerMatrizA(int n) {
        double[][] A = new double[n][n];
        System.out.println("\nIngrese los coeficientes de la matriz A (" + n + "x" + n + "):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = leerDouble("A[" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }
        return A;
    }

    /**
     * Solicita los n términos independientes del vector b.
     */
    public double[] leerVectorB(int n) {
        double[] b = new double[n];
        System.out.println("\nIngrese los términos independientes del vector b:");
        for (int i = 0; i < n; i++) {
            b[i] = leerDouble("b[" + (i + 1) + "]: ");
        }
        return b;
    }

    /**
     * Lee un número decimal (double) validando que la entrada sea numérica.
     */
    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Error: ingrese un número válido.");
            scanner.next(); // Descartar entrada inválida
        }
    }
}