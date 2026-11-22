/**
 * Clase encargada de la lógica matemática del Método de Gauss
 * (eliminación gaussiana con pivoteo parcial y sustitución hacia atrás)
 * para resolver sistemas de ecuaciones lineales de la forma A * x = b.
 */
public class MetodoGauss {

    // Umbral numérico para detectar pivotes nulos o muy cercanos a cero
    private static final double EPSILON = 1e-12;

    /**
     * Resuelve el sistema lineal A * x = b.
     *
     * @param A Matriz cuadrada de coeficientes (n x n)
     * @param b Vector de términos independientes (tamaño n)
     * @return Vector solución x (tamaño n)
     * @throws ArithmeticException Si el sistema no tiene solución única
     */
    public static double[] resolver(double[][] A, double[] b) {
        int n = b.length;

        // Construir la matriz aumentada [A | b] de tamaño n x (n + 1)
        double[][] aumentada = construirMatrizAumentada(A, b, n);

        // Fase 1: Eliminación hacia adelante con pivoteo parcial
        for (int col = 0; col < n; col++) {
            // Buscar la fila con el mayor valor absoluto en la columna actual
            int filaPivote = buscarFilaPivote(aumentada, col, n);

            // Verificar que el pivote no sea cero (evita división por cero o sistemas singulares)
            if (Math.abs(aumentada[filaPivote][col]) < EPSILON) {
                throw new ArithmeticException(
                        "El sistema no tiene solución única (pivote nulo o cercano a cero en la columna " + (col + 1) + ")."
                );
            }

            // Intercambiar la fila actual con la fila del pivote máximo si es necesario
            if (filaPivote != col) {
                intercambiarFilas(aumentada, col, filaPivote);
            }

            // Eliminar los elementos debajo del pivote para formar una matriz triangular superior
            eliminarDebajoDelPivote(aumentada, col, n);
        }

        // Fase 2: Sustitución hacia atrás para obtener los valores de x1, x2, ..., xn
        return sustitucionHaciaAtras(aumentada, n);
    }

    /**
     * Combina la matriz de coeficientes A y el vector b en una matriz aumentada [A | b].
     */
    private static double[][] construirMatrizAumentada(double[][] A, double[] b, int n) {
        double[][] aumentada = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, aumentada[i], 0, n);
            aumentada[i][n] = b[i];
        }
        return aumentada;
    }

    /**
     * Encuentra el índice de la fila con mayor magnitud absoluta a partir de 'col' hacia abajo.
     * Esto mejora la estabilidad numérica del método.
     */
    private static int buscarFilaPivote(double[][] aumentada, int col, int n) {
        int filaMax = col;
        double maxAbs = Math.abs(aumentada[col][col]);

        for (int fila = col + 1; fila < n; fila++) {
            double valorAbs = Math.abs(aumentada[fila][col]);
            if (valorAbs > maxAbs) {
                maxAbs = valorAbs;
                filaMax = fila;
            }
        }
        return filaMax;
    }

    /**
     * Intercambia dos filas completas dentro de la matriz aumentada.
     */
    private static void intercambiarFilas(double[][] aumentada, int fila1, int fila2) {
        double[] temp = aumentada[fila1];
        aumentada[fila1] = aumentada[fila2];
        aumentada[fila2] = temp;
    }

    /**
     * Aplica la eliminación gaussiana restando un múltiplo de la fila pivote
     * a cada fila inferior para hacer ceros debajo de la diagonal principal.
     */
    private static void eliminarDebajoDelPivote(double[][] aumentada, int col, int n) {
        double pivote = aumentada[col][col];
        for (int fila = col + 1; fila < n; fila++) {
            double factor = aumentada[fila][col] / pivote;
            for (int k = col; k <= n; k++) {
                aumentada[fila][k] -= factor * aumentada[col][k];
            }
        }
    }

    /**
     * Resuelve el sistema triangular superior comenzando desde la última incógnita (n - 1)
     * hasta la primera (0).
     */
    private static double[] sustitucionHaciaAtras(double[][] aumentada, int n) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double suma = aumentada[i][n];
            for (int j = i + 1; j < n; j++) {
                suma -= aumentada[i][j] * x[j];
            }
            x[i] = suma / aumentada[i][i];
        }
        return x;
    }
}