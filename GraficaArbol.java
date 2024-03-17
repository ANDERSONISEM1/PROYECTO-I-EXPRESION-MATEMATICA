import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Stack;

// Clase que representa un nodo en el árbol de expresión
class Nodo {
    String valor;
    Nodo izquierda, derecha;

    public Nodo(String valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
    }
}

// Clase que construye y dibuja un árbol de expresión
class ArbolExpresiongraficado {
    Nodo raiz;

    public ArbolExpresiongraficado() {
        this.raiz = null;
    }

    // Método para construir el árbol de expresión a partir de la expresión matemática
    public Nodo construirArbol(String expresion) {
        Stack<Nodo> stack = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isLetterOrDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length() && (Character.isLetterOrDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    sb.append(expresion.charAt(i));
                    i++;
                }
                i--;
                stack.push(new Nodo(sb.toString()));
            } else if (c == '(') {
                operadores.push(c);
            } else if (c == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    procesarOperador(stack, operadores.pop());
                }
                operadores.pop();
            } else {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(c)) {
                    procesarOperador(stack, operadores.pop());
                }
                operadores.push(c);
            }
        }

        while (!operadores.isEmpty()) {
            procesarOperador(stack, operadores.pop());
        }

        return stack.pop();
    }

    // Método privado para procesar un operador y crear un nodo en el árbol
    private void procesarOperador(Stack<Nodo> stack, char operador) {
        Nodo derecha = stack.pop();
        Nodo izquierda = stack.pop();

        Nodo nodoOperador = new Nodo(String.valueOf(operador));
        nodoOperador.izquierda = izquierda;
        nodoOperador.derecha = derecha;

        stack.push(nodoOperador);
    }

    // Método privado para determinar la precedencia de un operador
    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    // Método privado para dibujar el árbol de expresión
    private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int espacio) {
        if (nodo == null) return;

        g.setColor(Color.black);
        g.drawString(nodo.valor, x, y);

        if (nodo.izquierda != null) {
            g.drawLine(x + 7, y + 7, x - espacio + 7, y + 30);
            dibujarArbol(g, nodo.izquierda, x - espacio, y + 30, espacio / 2);
        }

        if (nodo.derecha != null) {
            g.drawLine(x + 7, y + 7, x + espacio + 7, y + 30);
            dibujarArbol(g, nodo.derecha, x + espacio, y + 30, espacio / 2);
        }
    }

    // Método público para dibujar el árbol de expresión en un panel
    public void dibujar(Graphics g, int width, int height) {
        dibujarArbol(g, raiz, width / 2, 30, width / 4);
    }
}

// Clase principal que crea un árbol de expresión y lo muestra gráficamente
public class GraficaArbol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un ejercicio matemático:");
        String ejercicio = scanner.nextLine();

        ArbolExpresiongraficado arbolExpresion = new ArbolExpresiongraficado();
        try {
            arbolExpresion.raiz = arbolExpresion.construirArbol(ejercicio);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    arbolExpresion.dibujar(g, getWidth(), getHeight());
                }
            };

            frame.add(panel);
            frame.setVisible(true);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
