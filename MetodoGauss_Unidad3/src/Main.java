import java.util.Locale;
import java.util.Scanner;

/**
 * Clase principal que coordina la lectura de datos, invoca la resolución
 * por el Método de Gauss e imprime los resultados en consola.
 */
public class Main {

    public static void main(String[] args) {
        // Usar punto decimal de forma consistente en entrada y salida
        Locale.setDefault(Locale.US);

        System.out.println("=== MÉTODO DE GAUSS (ELIMINACIÓN GAUSSIANA) ===");

        try (Scanner scanner = new Scanner(System.in)) {
            // 1. Leer datos del sistema usando LectorSistema
            LectorSistema lector = new LectorSistema(scanner);
            int n = lector.leerDimension();
            double[][] A = lector.leerMatrizA(n);
            double[] b = lector.leerVectorB(n);

            // 2. Resolver el sistema utilizando MetodoGauss
            double[] solucion = MetodoGauss.resolver(A, b);

            // 3. Mostrar el vector solución por consola
            imprimirSolucion(solucion);

        } catch (ArithmeticException e) {
            // Manejar casos donde el sistema no tenga solución única
            System.out.println("\nNo se pudo resolver el sistema: " + e.getMessage());
        }
    }

    /**
     * Imprime cada variable x_i con seis decimales.
     */
    private static void imprimirSolucion(double[] x) {
        System.out.println("\n--- Solución del sistema ---");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d = %.6f%n", (i + 1), x[i]);
        }
    }
}