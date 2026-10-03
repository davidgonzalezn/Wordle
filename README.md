# Wordle en Java

Implementación del juego Wordle desarrollada en Java y ejecutada desde consola.

El objetivo es adivinar una palabra secreta de 5 letras en un máximo de 6 intentos. Después de cada intento, el programa indica si cada letra está en la posición correcta, aparece en otra posición o no pertenece a la palabra.

## Funcionalidades

- Generación aleatoria de palabras.
- Palabras de 5 letras cargadas desde un fichero externo.
- Comprobación de letras y posiciones.
- Colores en consola mediante códigos ANSI.
- Sistema de puntuación.
- Ranking de jugadores almacenado en un archivo CSV.
- Guardado y carga de partidas.
- Serialización para mantener el estado de la partida.
- Menú interactivo por consola.
- Validación de entradas del usuario.

## Tecnologías utilizadas

- Java
- Programación Orientada a Objetos
- Java Collections
  - `ArrayList`
  - `HashSet`
  - `HashMap`
- Java I/O
- Serialización
- Archivos CSV
- Códigos ANSI para la salida por consola

## Estructura del proyecto

```text
Wordle
├── Wordle.java
├── Partida.java
├── Letra.java
├── Puntuacion.java
├── Palabras5.txt
├── puntuaciones.csv
└── partidaGuardada.dat
```

### Wordle.java

Contiene el punto de entrada de la aplicación y el menú principal:

- Nuevo juego
- Cargar partida
- Ver puntuaciones
- Salir

### Partida.java

Gestiona la lógica principal del juego:

- Selección aleatoria de palabras
- Control de intentos
- Comparación de letras
- Sistema de colores
- Cálculo de puntuación
- Guardado y carga de partidas

### Letra.java

Representa cada letra y su estado dentro de un intento.

### Puntuacion.java

Gestiona las puntuaciones de los jugadores y su almacenamiento en un fichero CSV.

### Palabras5.txt

Contiene las palabras disponibles para las partidas.

## Persistencia

Las partidas pueden guardarse mediante serialización de objetos Java, permitiendo continuar posteriormente desde el mismo estado.

Las puntuaciones se almacenan en un archivo CSV para conservar el ranking entre ejecuciones.

## Conceptos aplicados

El proyecto permite trabajar con distintos conceptos de Java:

- Programación Orientada a Objetos
- Clases y objetos
- Colecciones
- Manejo de archivos
- Excepciones
- Serialización
- Persistencia de datos
- Entrada y salida por consola

## Ejecución

El proyecto puede ejecutarse desde un IDE compatible con Java, como IntelliJ IDEA, Eclipse o Visual Studio Code.

La clase principal es:

```java
Wordle.java
```

El proyecto utiliza el paquete:

```java
practica6
```

Para ejecutarlo correctamente, es necesario mantener la estructura de paquetes y las rutas de los archivos utilizados por la aplicación.

## Autor

David González

GitHub: [davidgonzalezn](https://github.com/davidgonzalezn)
