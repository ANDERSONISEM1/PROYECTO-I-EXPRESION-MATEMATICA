
package arbolexpresion2;

import java.util.Scanner;
import java.util.Stack;


public class RECORRIDOPREORDEN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la expresión en notación infija: ");
        String expresionInfija = scanner.nextLine();
        String expresionPrefija = convertirAPrefija(expresionInfija);
        System.out.println("Expresión en notación prefija: " + expresionPrefija);
        scanner.close();
    }

    public static String convertirAPrefija(String expresionInfija) {
        // Invertir la expresión infija
        StringBuilder expresionInvertida = new StringBuilder(expresionInfija).reverse();

        // Reemplazar paréntesis
        for (int i = 0; i < expresionInvertida.length(); i++) {
            if (expresionInvertida.charAt(i) == '(') {
                expresionInvertida.setCharAt(i, ')');
            } else if (expresionInvertida.charAt(i) == ')') {
                expresionInvertida.setCharAt(i, '(');
            }
        }

        // Convertir a postfija
        String expresionPostfija = convertirAPostfija(expresionInvertida.toString());

        // Invertir la expresión postfija para obtener la prefija
        return new StringBuilder(expresionPostfija).reverse().toString();
    }

    public static String convertirAPostfija(String expresionInfija) {
        Stack<Character> operadores = new Stack<>();
        StringBuilder expresionPostfija = new StringBuilder();

        for (int i = 0; i < expresionInfija.length(); i++) {
            char caracter = expresionInfija.charAt(i);

            if (Character.isLetterOrDigit(caracter)) {
                expresionPostfija.append(caracter);
            } else if (caracter == '(') {
                operadores.push(caracter);
            } else if (caracter == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    expresionPostfija.append(operadores.pop());
                }
                if (!operadores.isEmpty() && operadores.peek() != '(') {
                    return "Expresión inválida";
                } else {
                    operadores.pop();
                }
            } else {
                while (!operadores.isEmpty() && precedencia(caracter) <= precedencia(operadores.peek())) {
                    expresionPostfija.append(operadores.pop());
                }
                operadores.push(caracter);
            }
        }

        while (!operadores.isEmpty()) {
            expresionPostfija.append(operadores.pop());
        }

        return expresionPostfija.toString();
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
        }
        return -1;
    }
    
}
