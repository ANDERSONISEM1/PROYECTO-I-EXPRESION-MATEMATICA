# Main

Esta clase representa la interfaz gráfica principal de la aplicación "expresión matemática limitada", que permite al usuario ingresar expresiones matemáticas y realizar diversas operaciones con ellas, como la conversión a postorden (notacion polaca), los recorridos en preorden, inorden y postorden, la evaluación de la expresión y la visualización del árbol de expresión correspondiente.

## Atributos

- `preorderExpression`: instancia de la clase `RECORRIDOPREORDEN` utilizada para realizar operaciones de conversión de expresiones y recorridos en preorden.
- `textFieldInput`: campo de texto para ingresar la expresión matemática.
- `textFieldPreorden`: campo de texto para mostrar el recorrido en preorden.
- `textFieldInorden`: campo de texto para mostrar el recorrido en inorden.
- `textFieldPostorden`: campo de texto para mostrar el recorrido en postorden.
- `textFieldNOTACIONPOLACA`: campo de texto para mostrar el resultado de la evaluación de la expresión en notación polaca.

## Métodos

- `main(String[] args)`: método principal que crea la interfaz gráfica y configura los componentes.
- `realizarButton.addActionListener(ActionListener)`: acción que se realiza al presionar el botón "Realizar". Convierte la expresión a postfija, realiza el recorrido en preorden, evalúa la expresión y muestra los resultados en los campos correspondientes.
- `borrarButton.addActionListener(ActionListener)`: acción que se realiza al presionar el botón "Borrar ejercicio". Borra el contenido de todos los campos de texto.
- `mostrarButton.addActionListener(ActionListener)`: acción que se realiza al presionar el botón "Mostrar Árbol". Construye el árbol de expresión correspondiente a la expresión ingresada y lo muestra gráficamente en una nueva ventana.

## Consideraciones

- La clase hace uso de las clases `RECORRIDOPREORDEN`, `RECORRIDOPOSTORDEN` y `RECORRIDOINORDEN` para realizar las operaciones necesarias.
- Se utiliza el patrón de diseño MVC (Modelo-Vista-Controlador) para separar la lógica de la aplicación de la interfaz gráfica.
- La interfaz gráfica se construye utilizando componentes de `javax.swing` y se organiza en un diseño de cuadrícula para una mejor visualización de los campos y botones.


# Recorrido Preorden

Esta clase proporciona métodos para convertir expresiones infija a preorden.

## Métodos

### `infixToPostfix(String infix)`

Este método toma una expresión infix como entrada y devuelve su equivalente en notación postfija.

#### Parámetros
- `infix`: Una cadena que representa la expresión infix.

#### Devolución
- Una cadena que representa la expresión en notación postfija.

### `postfixToPreorder(String postfix)`

Este método toma una expresión postfija como entrada y devuelve su equivalente en preorden.

#### Parámetros
- `postfix`: Una cadena que representa la expresión postfija.

#### Devolución
- Una cadena que representa la expresión en preorden.

### `main(String[] args)`

Este método es el punto de entrada de la aplicación. Solicita al usuario que ingrese una expresión infija, luego convierte esa expresión a postfija y finalmente a preorden, y muestra el resultado.

## Funciones Auxiliares

### `isOperator(char c)`

Esta función verifica si un carácter dado es un operador válido.

### `precedence(char op)`

Esta función devuelve la precedencia de un operador dado.


# Convertidor y evaluador de expresiones en Java

Este programa Java convierte una expresión matemática en notación infija a notación postfija y luego evalúa el resultado. Utiliza una pila para realizar las conversiones y evaluaciones.

## Librerías utilizadas

- `java.util.Scanner`: Para la entrada de datos desde la consola.
- `java.util.Stack`: Para implementar la pila necesaria en la conversión y evaluación de la expresión.

## Funcionalidades

1. **Entrada de expresión inorden**: El usuario ingresa una expresión matemática en notación infija desde la consola.
2. **Conversión a postorden**: La expresión infija ingresada se convierte a notación postfija (postorden).
3. **Evaluación de la expresión postorden**: La expresión postfija se evalúa y se muestra el resultado.

## Detalles de implementación

- **Método `convertirAPostorden`**: Este método convierte una expresión infija a postfija utilizando el algoritmo Shunting Yard. Se implementa una pila para manejar los operadores y se siguen las reglas de precedencia de operadores.
- **Método `evaluarExpresion`**: Este método evalúa una expresión postfija utilizando una pila para almacenar operandos y realizar las operaciones correspondientes.
- **Método `getPrioridad`**: Devuelve la prioridad de un operador dado.

## Uso del programa

1. Ejecutar el programa.
2. Ingresar la expresión matemática en notación infija cuando se solicite.
3. El programa mostrará la expresión en notación postfija y el resultado de la evaluación.


# Clase RECORRIDO INORDEN

Esta clase contiene métodos para convertir una expresión matemática de notación infija a notación postfija, convertir la expresión postfija a notación infija y evaluar la expresión matemática.

## Métodos

### `convertirAPostfija(expresion: String): String`

Este método recibe una cadena de texto que representa una expresión matemática en notación infija y devuelve la misma expresión en notación postfija.

**Parámetros:**
- `expresion` (String): La expresión matemática en notación infija.

**Retorno:**
- `String`: La expresión matemática en notación postfija.

### `convertirAInorden(expresionPostfija: String): String`

Este método recibe una cadena de texto que representa una expresión matemática en notación postfija y devuelve la misma expresión en notación infija.

**Parámetros:**
- `expresionPostfija` (String): La expresión matemática en notación postfija.

**Retorno:**
- `String`: La expresión matemática en notación infija.

### `evaluarExpresion(expresion: String): double`

Este método recibe una cadena de texto que representa una expresión matemática en notación postfija y devuelve el resultado de evaluar la expresión.

**Parámetros:**
- `expresion` (String): La expresión matemática en notación postfija.

**Retorno:**
- `double`: El resultado de la evaluación de la expresión matemática.


# Clases y métodos

## Clase `Nodo`

- **Atributos:**
  - `valor`: String que representa el valor del nodo.
  - `izquierda`: Referencia al nodo hijo izquierdo.
  - `derecha`: Referencia al nodo hijo derecho.

- **Métodos:**
  - `public Nodo(String valor)`: Constructor que inicializa el nodo con un valor dado.

## Clase `ArbolExpresiongraficado`

- **Atributos:**
  - `raiz`: Referencia al nodo raíz del árbol de expresión.

- **Métodos:**
  - `public ArbolExpresiongraficado()`: Constructor que inicializa el árbol con una raíz nula.
  - `public Nodo construirArbol(String expresion)`: Construye el árbol de expresión a partir de una expresión matemática en formato String.
  - `private void procesarOperador(Stack<Nodo> stack, char operador)`: Procesa un operador y crea un nodo en el árbol.
  - `private int precedencia(char operador)`: Determina la precedencia de un operador.
  - `private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int espacio)`: Dibuja el árbol de expresión en un objeto Graphics.
  - `public void dibujar(Graphics g, int width, int height)`: Método público para dibujar el árbol de expresión en un panel.

# Árbol de Expresión Matemática

Este proyecto en Java permite ingresar un ejercicio matemático y construir un árbol de expresión a partir de él, mostrando el árbol gráficamente.

## Clases

### `Nodo`

- Clase que representa un nodo en el árbol de expresión.
- Atributos:
  - `valor`: String que representa el valor del nodo (operador o número).
  - `izquierda`: Nodo izquierdo del nodo actual.
  - `derecha`: Nodo derecho del nodo actual.
- Constructor:
  - `Nodo(String valor)`: Crea un nuevo nodo con el valor especificado.

### `ArbolExpresiongraficado`

- Clase que construye y dibuja un árbol de expresión.
- Atributos:
  - `raiz`: Nodo raíz del árbol de expresión.
- Constructor:
  - `ArbolExpresiongraficado()`: Crea un nuevo árbol de expresión vacío.
- Métodos:
  - `construirArbol(String expresion)`: Construye el árbol de expresión a partir de la expresión matemática dada.
  - `procesarOperador(Stack<Nodo> stack, char operador)`: Procesa un operador y crea un nodo en el árbol.
  - `precedencia(char operador)`: Determina la precedencia de un operador.
  - `dibujarArbol(Graphics g, Nodo nodo, int x, int y, int espacio)`: Dibuja el árbol de expresión en un contexto gráfico.
  - `dibujar(Graphics g, int width, int height)`: Método público para dibujar el árbol de expresión en un panel.

### `GraficaArbol`

- Clase principal que crea un árbol de expresión y lo muestra gráficamente.
- Método `main(String[] args)`: Pide al usuario ingresar un ejercicio matemático, construye el árbol de expresión y lo muestra en una ventana gráfica.

## Uso

1. Ejecutar el programa `GraficaArbol`.
2. Ingresar un ejercicio matemático.
3. El programa construirá el árbol de expresión y lo mostrará gráficamente en una ventana.

Nota: El programa considera operadores como `+`, `-`, `*`, `/` y valores numéricos.

FIN
