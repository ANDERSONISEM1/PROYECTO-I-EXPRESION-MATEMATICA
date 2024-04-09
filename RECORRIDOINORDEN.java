
package arbolexpresion2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class RECORRIDOINORDEN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Solicitar al usuario que ingrese una expresión matemática
        System.out.print("Ingresa una expresión matemática (con paréntesis y raíz cuadrada, ej. sqrt(25)+3): ");
        String expresion = scanner.nextLine();

        // Encontrar y almacenar las variables únicas presentes en la expresión
        Set<Character> variables = new HashSet<>();
        for (char c : expresion.toCharArray()) {
            if (Character.isLetter(c)) {
                variables.add(c);
            }
        }

        // Solicitar valores para las variables
        Map<Character, Double> valores = new HashMap<>();
        for (char variable : variables) {
            System.out.print("Ingresa el valor para " + variable + ": ");
            double valor = scanner.nextDouble();
            valores.put(variable, valor);
        }

        // Reemplazar las variables por sus valores en la expresión
for (Map.Entry<Character, Double> entry : valores.entrySet()) {
    expresion = expresion.replaceAll("\\b" + entry.getKey() + "\\b", String.format("%.0f", entry.getValue()));

}

// Convertir la expresión postfija a inorden
String expresionInorden = convertirAInorden(expresion).replaceAll("\\.0", "");
System.out.println("Expresión en notación Inorden: " + expresionInorden);
}

    public static String convertirAInorden(String expresion) {
        // Creamos una pila para almacenar los operandos y operadores
        Stack<String> stack = new Stack<>();
        
        // Iteramos sobre cada token de la expresión
        for (char caracter : expresion.toCharArray()) {
            // Si el caracter es un dígito o una letra, lo agregamos a la pila
            if (Character.isLetterOrDigit(caracter)) {
                stack.push(String.valueOf(caracter));
            }
            // Si el caracter es '(', lo agregamos a la pila
            else if (caracter == '(') {
                stack.push("(");
            }
            // Si el caracter es ')', construimos la expresión inorden y la colocamos en la pila
            else if (caracter == ')') {
                String operand2 = stack.pop();
                String operator = stack.pop();
                String operand1 = stack.pop();
                stack.push(operand1 + operator + operand2);
            }
            // Si el caracter es un operador, lo agregamos a la pila
            else if (esOperador(caracter)) {
                while (!stack.isEmpty() && precedencia(caracter) <= precedencia(stack.peek().charAt(0))) {
                    String operand2 = stack.pop();
                    String operator = stack.pop();
                    String operand1 = stack.pop();
                    stack.push(operand1 + operator + operand2);
                }
                stack.push(String.valueOf(caracter));
            }
        }
        // Al finalizar, la pila contendrá la expresión inorden resultante
        StringBuilder resultado = new StringBuilder();
        while (!stack.isEmpty()) {
            resultado.insert(0, stack.pop());
        }
        return resultado.toString().replaceAll("[()]", ""); // Eliminar paréntesis del resultado
    }

    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    public static int precedencia(char operador) {
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
