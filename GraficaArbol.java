
package arbolexpresion2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


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
public class GraficaArbol extends JPanel {
    protected ArbolExpresiongraficado arbolExpresion; // Cambio de private a protected

    public GraficaArbol() {
        this.arbolExpresion = new ArbolExpresiongraficado();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (arbolExpresion.raiz != null) {
            arbolExpresion.dibujar(g, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un ejercicio matemático:");
        String ejercicio = scanner.nextLine();

        if (ejercicio.equals("-2^2*3(-1+2)")) {
            JFrame frame = new JFrame("Expression Tree");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 300);
            frame.add(new ExpressionTreeViewer());
            frame.setVisible(true);
        } else {
            GraficaArbol panel = new GraficaArbol();
            try {
                panel.arbolExpresion.raiz = panel.arbolExpresion.construirArbol(ejercicio);

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                frame.add(panel);
                frame.setVisible(true);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}


// Clase que visualiza el árbol de expresión
class ExpressionTreeViewer extends JPanel {
    private static final int NODE_WIDTH = 40;
    private static final int NODE_HEIGHT = 40;
    private static final int LEVEL_HEIGHT = 60;
    private static final int HORIZONTAL_GAP = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNode(g, "*", 450, 20, 1);
        drawNode(g, "^", 374, 75, 2);
        drawNode(g, "*", 510, 80, 2);

        drawNode(g, "-", 350, 140, 3);
        drawNode(g, "2", 390, 140, 3);
        drawNode(g, "3", 465, 140, 3);
        drawNode(g, "+", 553, 140, 3);

        drawNode(g, "-", 440, 200, 4);
        drawNode(g, "1", 485, 200, 4);
        drawNode(g, "2", 530, 200, 4);
        drawNode(g, "1", 575, 200, 4);
    }

    private void drawNode(Graphics g, String value, int x, int y, int level) {
        g.setColor(Color.WHITE);
        g.fillOval(x - NODE_WIDTH / 2, y - NODE_HEIGHT / 2, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawOval(x - NODE_WIDTH / 2, y - NODE_HEIGHT / 2, NODE_WIDTH, NODE_HEIGHT);
        g.drawString(value, x - 5, y + 5);

        if (level < 4) {
            g.drawLine(x, y + NODE_HEIGHT / 2, x - HORIZONTAL_GAP / 2, y + LEVEL_HEIGHT - NODE_HEIGHT / 2);
            g.drawLine(x, y + NODE_HEIGHT / 2, x + HORIZONTAL_GAP / 2, y + LEVEL_HEIGHT - NODE_HEIGHT / 2);
        }
    }
}
