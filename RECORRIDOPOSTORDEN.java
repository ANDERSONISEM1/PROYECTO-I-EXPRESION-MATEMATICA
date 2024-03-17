


//LIBRERIAS A USAR
import java.util.Scanner;
import java.util.Stack;

//INICIO DE CLASE
public class RECORRIDOPOSTORDEN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //SOLICITANDO EXPRESION PARA EVALUAR RECORRIDO
        System.out.println("Ingrese la expresión en recorrido inorden:");
        String expresionInorden = scanner.nextLine();

        // Convertir la expresión inorden a postorden
        String expresionPostorden = convertirAPostorden(expresionInorden);
        System.out.println("Expresión en recorrido postorden: " + expresionPostorden);

        // Evaluar la expresión postorden
        double resultado = evaluarExpresion(expresionPostorden);
        System.out.println("Resultado: " + resultado);

        scanner.close();
    }

    // Método para convertir una expresión inorden a postorden
    public static String convertirAPostorden(String expresionInorden) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (char caracter : expresionInorden.toCharArray()) {
            if (Character.isDigit(caracter)) {
                // Si el caracter es un dígito, lo agrega directamente al resultado
                resultado.append(caracter);
            } else if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '^') {
                // Si el caracter es un operador, lo agrega a la pila de operadores
                // mientras su prioridad sea mayor o igual que la prioridad del operador en la cima de la pila
                while (!pila.isEmpty() && getPrioridad(pila.peek()) >= getPrioridad(caracter)) {
                    resultado.append(pila.pop());
                }
                pila.push(caracter);
            } else if (caracter == '(') {
                // Si el caracter es un paréntesis de apertura, lo agrega a la pila
                pila.push(caracter);
            } else if (caracter == ')') {
                // Si el caracter es un paréntesis de cierre, saca los operadores de la pila
                // y los agrega al resultado hasta encontrar el paréntesis de apertura correspondiente
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop(); // Sacar el paréntesis de apertura de la pila
            }
        }

        // Vaciar la pila al final
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

    // Método para evaluar una expresión postorden
    public static double evaluarExpresion(String expresionPostorden) {
        Stack<Double> pila = new Stack<>();

        for (char caracter : expresionPostorden.toCharArray()) {
            if (Character.isDigit(caracter)) {
                // Si el caracter es un dígito, conviértelo a double y agrega a la pila
                pila.push(Double.parseDouble(String.valueOf(caracter)));
            } else {
                // Si el caracter es un operador, realiza la operación correspondiente con los operandos en la pila
                double operando2 = pila.pop();
                double operando1 = pila.pop();
                switch (caracter) {
                    case '+':
                        pila.push(operando1 + operando2);
                        break;
                    case '-':
                        pila.push(operando1 - operando2);
                        break;
                    case '*':
                        pila.push(operando1 * operando2);
                        break;
                    case '/':
                        pila.push(operando1 / operando2);
                        break;
                    case '^':
                        pila.push(Math.pow(operando1, operando2));
                        break;
                }
            }
        }

        // El resultado final estará en la cima de la pila
        return pila.pop();
    }

    // Método para obtener la prioridad de un operador
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
                return 0; // Para paréntesis o cualquier otro carácter
        }
    }
}
