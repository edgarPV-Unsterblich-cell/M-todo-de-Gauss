# Práctica: Método de Gauss — SCC-1017 Métodos Numéricos

**Materia:** SCC-1017 Métodos Numéricos (Unidad 3)  

**Institución:** Instituto Tecnológico Superior de Xalapa (ITSX)

## Lenguaje de programación utilizado
- **Java** (JDK 8 o superior)

## Estructura modular del proyecto
- `src/MetodoGauss.java`: Implementa la eliminación gaussiana con pivoteo parcial y sustitución hacia atrás.
- `src/LectorSistema.java`: Gestiona la lectura y validación de la dimensión, matriz `A` y vector `b` desde consola.
- `src/Main.java`: Coordina la ejecución del programa y muestra la solución por consola.

## Pasos para compilar y ejecutar

1. Clona el repositorio en tu equipo:
   ```bash
   git clone <URL_DE_TU_REPOSITORIO_PUBLICO>
   cd <NOMBRE_DEL_REPOSITORIO>
=== MÉTODO DE GAUSS (ELIMINACIÓN GAUSSIANA) ===
Ingrese el número de incógnitas / ecuaciones (n >= 1): 3

Ingrese los coeficientes de la matriz A (3x3):
A[1][1]: 2
A[1][2]: 1
A[1][3]: -1
A[2][1]: -3
A[2][2]: -1
A[2][3]: 2
A[3][1]: -2
A[3][2]: 1
A[3][3]: 2

Ingrese los términos independientes del vector b:
b[1]: 8
b[2]: -11
b[3]: -3
--- Solución del sistema ---
x1 = 2.000000
x2 = 3.000000
x3 = -1.000000
