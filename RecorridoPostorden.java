
package arbolexpresion2;

import java.util.Scanner;
import java.util.Stack;


public class RecorridoPostorden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la expresión: ");
        String expresionInorden = scanner.nextLine();
        String expresionPostorden = convertirAPostorden(expresionInorden);
        System.out.println("Expresión en recorrido postorden: " + expresionPostorden);
        double resultado = evaluarExpresionPostorden(expresionPostorden);
        System.out.println("Resultado: " + resultado);
        scanner.close();
    }

    public static double evaluarExpresionPostorden(String expresionPostorden) {
        Stack<Double> pila = new Stack<>();
        String[] elementos = expresionPostorden.split(" ");
        for (String elemento : elementos) {
            if (elemento.isEmpty()) {
                continue;
            }
            if (esOperador(elemento)) {
                if (pila.size() < 2) {
                    throw new IllegalArgumentException("Expresión incorrecta: operandos sin operadores suficientes");
                }
                double operando2 = pila.pop();
                double operando1 = pila.pop();
                double resultadoOperacion = realizarOperacion(operando1, operando2, elemento);
                pila.push(resultadoOperacion);
            } else {
                pila.push(Double.parseDouble(elemento));
            }
        }
        if (pila.size() != 1) {
            throw new IllegalArgumentException("Expresión incorrecta: operandos sin operadores suficientes");
        }
        return pila.pop();
    }
    
public static String convertirAPostorden(String expresionInorden) {
    StringBuilder resultado = new StringBuilder();
    Stack<Character> pila = new Stack<>();
    boolean nuevoNumero = true;

    for (int i = 0; i < expresionInorden.length(); i++) {
        char caracter = expresionInorden.charAt(i);

        if (Character.isDigit(caracter) || caracter == '.') {
            int inicioNumero = i;
            while (i + 1 < expresionInorden.length() &&
                    (Character.isDigit(expresionInorden.charAt(i + 1)) || expresionInorden.charAt(i + 1) == '.')) {
                i++;
            }
            resultado.append(expresionInorden.substring(inicioNumero, i + 1)).append(" ");
            nuevoNumero = false;
        } 
        else if (caracter == '-' && (i == 0 || expresionInorden.charAt(i - 1) == '(' || expresionInorden.charAt(i - 1) == '*' || expresionInorden.charAt(i - 1) == '/' || expresionInorden.charAt(i - 1) == '^')) {
            pila.push(caracter);
           resultado.append("0 "); // Añadir un cero al resultado para mantener el número negativo en su lugar
            nuevoNumero = true;
        } 
        
        else if (caracter == '(') {
            pila.push(caracter);
            nuevoNumero = true;
        } else if (caracter == ')') {
            while (!pila.isEmpty() && pila.peek() != '(') {
                resultado.append(pila.pop()).append(" ");
            }
            if (!pila.isEmpty() && pila.peek() == '(') {
                pila.pop(); // Eliminar el '(' de la pila
            } else {
                throw new IllegalArgumentException("Expresión incorrecta: paréntesis desbalanceados");
            }
            nuevoNumero = false;
        } else {
            while (!pila.isEmpty() && getPrioridad(pila.peek()) >= getPrioridad(caracter) && pila.peek() != '(') {
                resultado.append(pila.pop()).append(" ");
            }
            pila.push(caracter);
            nuevoNumero = true;
        }

        if (caracter == '(' && i > 0 && (Character.isDigit(expresionInorden.charAt(i - 1)) || expresionInorden.charAt(i - 1) == '.')) {
            resultado.append("* ");
        }
    }

    while (!pila.isEmpty()) {
        resultado.append(pila.pop()).append(" ");
    }

    return resultado.toString().trim();
}



    public static boolean esOperador(String elemento) {
        return elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/") || elemento.equals("^");
    }

    public static double realizarOperacion(double operando1, double operando2, String operador) {
        switch (operador) {
            case "+":
                return operando1 + operando2;
            case "-":
                return operando1 - operando2;
            case "*":
                return operando1 * operando2;
            case "/":
                return operando1 / operando2;
            case "^":
                return Math.pow(operando1, operando2);
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }

    public static int getPrioridad(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
}
