import java.util.*;

// Clase para convertir expresiones infix a preorder
public class RECORRIDOPREORDEN {
    
    // Función para verificar si un carácter es un operador
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Función para convertir una expresión infija a postfija
    static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        // Iterar sobre cada carácter de la expresión infija
        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) { // Si es un dígito, añadirlo directamente al resultado
                postfix.append(c);
            } else if (c == '(') { // Si es un paréntesis izquierdo, añadirlo a la pila
                stack.push(c);
            } else if (c == ')') { // Si es un paréntesis derecho, sacar elementos de la pila hasta encontrar el paréntesis izquierdo
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Sacar el paréntesis izquierdo
            } else if (isOperator(c)) { // Si es un operador
                // Sacar elementos de la pila con mayor o igual precedencia y luego añadir el operador actual
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        // Sacar elementos restantes de la pila y añadirlos al resultado
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    // Función para obtener la precedencia de un operador
    private static int precedence(char op) {
        switch (op) {
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

    // Función para convertir una expresión postfija a preorden
    static String postfixToPreorder(String postfix) {
        Stack<String> stack = new Stack<>();
        
        // Iterar sobre cada carácter de la expresión postfija
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) { // Si es un dígito, añadirlo a la pila como cadena
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) { // Si es un operador, sacar los dos operandos anteriores, unirlos con el operador y añadir el resultado a la pila
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                stack.push(c + operand1 + operand2);
            }
        }
        return stack.pop(); // El último elemento en la pila es la expresión en preorden
    }

    // Función principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la expresión :");
        String infixExpression = scanner.nextLine();
        
        // Convertir la expresión infija a postfija
        String postfixExpression = infixToPostfix(infixExpression);
        
        // Convertir la expresión postfija a preorden y mostrarla
        String preorderExpression = postfixToPreorder(postfixExpression);
        System.out.println("Expresión en preorden: " + preorderExpression);
    }
}
