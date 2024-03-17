
import java.util.*;

public class RECORRIDOINORDEN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Solicitar al usuario que ingrese una expresión matemática
        System.out.print("Ingresa una expresión matemática (con paréntesis y raíz cuadrada, ej. sqrt(25)+3): ");
        String expresion = scanner.nextLine();

        // Convertir la expresión a notación polaca inversa
        String expresionPostfija = convertirAPostfija(expresion);
        System.out.println("Expresión en notación polaca inversa: " + expresionPostfija);

        // Convertir la expresión postfija a inorden
        String expresionInorden = convertirAInorden(expresionPostfija);
        System.out.println("Expresión en notación Inorden: " + expresionInorden);

        // Evaluar la expresión y mostrar el resultado
        double resultado = evaluarExpresion(expresionPostfija);
        System.out.println("Resultado de la expresión: " + resultado);
    }


    public static String convertirAPostfija(String expresion) {
    // Resultado final en notación postfija
    StringBuilder resultado = new StringBuilder();
    // Pila para almacenar operadores temporales
    Stack<Character> stack = new Stack<>();
    // Mapa de precedencia de operadores
    Map<Character, Integer> precedencia = new HashMap<>();
    precedencia.put('+', 1);
    precedencia.put('-', 1);
    precedencia.put('*', 2);
    precedencia.put('/', 2);
    precedencia.put('^', 3);

    // Recorrer cada caracter de la expresión
    for (char caracter : expresion.toCharArray()) {
        // Si el caracter es un dígito, añadirlo al resultado
        if (Character.isDigit(caracter)) {
            resultado.append(caracter);
            resultado.append(" ");
        }
        // Si el caracter es '(', agregarlo a la pila
        else if (caracter == '(') {
            stack.push(caracter);
        }
        // Si el caracter es ')', desapilar operadores hasta encontrar '('
        else if (caracter == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                resultado.append(stack.pop());
                resultado.append(" ");
            }
            stack.pop(); // Sacar el '('
        }
        // Si el caracter es un operador, comparar precedencia y desapilar si es necesario
        else {
            while (!stack.isEmpty() && precedencia.getOrDefault(stack.peek(), 0) >= precedencia.get(caracter)) {
                resultado.append(stack.pop());
                resultado.append(" ");
            }
            stack.push(caracter);
        }
    }

    // Desapilar operadores restantes
    while (!stack.isEmpty()) {
        resultado.append(stack.pop());
        resultado.append(" ");
    }

    // Devolver resultado como String, eliminando espacios extra al final
    return resultado.toString().trim();
}


   public static String convertirAInorden(String expresionPostfija) {
    // Creamos una pila para almacenar los operandos y operadores
    Stack<String> stack = new Stack<>();
    
    // Iteramos sobre cada token de la expresión postfija
    for (String token : expresionPostfija.split("\\s+")) {
        // Si el token es un número, lo colocamos en la pila
        if (token.matches("[0-9]+")) {
            stack.push(token);
        } else {
            // Si el token es un operador, sacamos los dos últimos operandos de la pila
            String operand2 = stack.pop();
            String operand1 = stack.pop();
            // Combinamos los operandos y el operador en una nueva expresión y la colocamos en la pila
            stack.push(operand1 + " " + token + " " + operand2);
        }
    }
    // Al finalizar, la pila contendrá la expresión inorden resultante
    return stack.pop();
}
public static double evaluarExpresion(String expresion) {
    // Creamos una pila para almacenar los operandos
    Stack<Double> stack = new Stack<>();
    
    // Iteramos sobre los tokens de la expresión separados por espacios
    for (String token : expresion.split("\\s+")) {
        // Si el token es un número, lo convertimos a double y lo agregamos a la pila
        if (token.matches("[0-9]+")) {
            stack.push(Double.parseDouble(token));
        } 
        // Si el token es "sqrt", calculamos la raíz cuadrada del último operando y lo agregamos a la pila
        else if (token.equals("sqrt")) {
            double operand = stack.pop();
            stack.push(Math.sqrt(operand));
        } 
        // Si el token es un operador, realizamos la operación correspondiente con los últimos dos operandos de la pila
        else {
            double operand2 = stack.pop();
            double operand1 = stack.pop();
            switch (token) {
                case "+":
                    stack.push(operand1 + operand2);
                    break;
                case "-":
                    stack.push(operand1 - operand2);
                    break;
                case "*":
                    stack.push(operand1 * operand2);
                    break;
                case "/":
                    stack.push(operand1 / operand2);
                    break;
                case "^":
                    stack.push(Math.pow(operand1, operand2));
                    break;
                default:
                    // Si el operador es desconocido, lanzamos una excepción
                    throw new IllegalArgumentException("Operador desconocido: " + token);
            }
        }
    }
    // Al finalizar, el resultado final estará en la cima de la pila
    return stack.pop();
}
}
