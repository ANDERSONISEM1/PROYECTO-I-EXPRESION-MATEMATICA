import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static RECORRIDOPREORDEN preorderExpression = new RECORRIDOPREORDEN();
    private static JTextField textFieldInput;
    private static JTextField textFieldPreorden;
    private static JTextField textFieldInorden;
    private static JTextField textFieldPostorden;
    private static JTextField textFieldNOTACIONPOLACA;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ARBOL DE EXPRESION");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 5, 5));// Layout principal con 6 filas y 1 columna
        frame.add(mainPanel);

         // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelInput = new JLabel("Ingrese el ejercicio:");
        JLabel espacio = new JLabel(" "); // Espacio en blanco
        textFieldInput = new JTextField(20);
        inputPanel.add(labelInput);
        inputPanel.add(espacio); // Agrega el espacio en blanco antes del cuadro de texto
        inputPanel.add(textFieldInput);
        mainPanel.add(inputPanel);

        // Panel de PREORDEN
        JPanel preordenPanel = new JPanel();
        preordenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelPreorden = new JLabel("PREORDEN:");
        JLabel espacio0 = new JLabel("                "); // Espacio en blanco
        textFieldPreorden = new JTextField(20);
        textFieldPreorden.setEditable(false);
        preordenPanel.add(labelPreorden);
        preordenPanel.add(espacio0);
        preordenPanel.add(textFieldPreorden);
        mainPanel.add(preordenPanel);

        // Panel de INORDEN
        JPanel inordenPanel = new JPanel();
        inordenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelInorden = new JLabel("INORDEN:");
        JLabel espacio1 = new JLabel("                    "); // Espacio en blanco
        textFieldInorden = new JTextField(20);
        textFieldInorden.setEditable(false);
        inordenPanel.add(labelInorden);
        inordenPanel.add(espacio1);
        inordenPanel.add(textFieldInorden);
        mainPanel.add(inordenPanel);

        // Panel de POSTORDEN
        JPanel postordenPanel = new JPanel();
        postordenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelPostorden = new JLabel("POSTORDEN:");
        JLabel espacio2 = new JLabel("             "); // Espacio en blanco
        textFieldPostorden = new JTextField(20);
        textFieldPostorden.setEditable(false);
        postordenPanel.add(labelPostorden);
        postordenPanel.add(espacio2);
        postordenPanel.add(textFieldPostorden);
        mainPanel.add(postordenPanel);

        // Panel de NOTACION POLACA RESULTADO
        JPanel NOTACIONPOLACAPanel = new JPanel();
        NOTACIONPOLACAPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelNOTACIONPOLACA = new JLabel("NOTACION POLACA:");
        JLabel espacio3 = new JLabel(""); // Espacio en blanco
        textFieldNOTACIONPOLACA = new JTextField(20);
        textFieldNOTACIONPOLACA.setEditable(false);
        NOTACIONPOLACAPanel.add(labelNOTACIONPOLACA);
        NOTACIONPOLACAPanel.add(espacio3);
        NOTACIONPOLACAPanel.add(textFieldNOTACIONPOLACA);
        mainPanel.add(NOTACIONPOLACAPanel);
         // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton realizarButton = new JButton("Realizar");
        JButton borrarButton = new JButton("Borrar ejercicio");
        JButton mostrarButton = new JButton("Mostrar Árbol");

        // Agrega los botones en el orden deseado
        buttonPanel.add(new JLabel("      ")); // Espacio en blanco antes del botón "Realizar"
        buttonPanel.add(realizarButton); // Botón "Realizar" se agregará al final
        buttonPanel.add(borrarButton);
        buttonPanel.add(mostrarButton);
        mainPanel.add(buttonPanel);

        // Centrar la ventana en medio de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        
        //BOTON REALIZA RECORRIDO POSTORDEN
        realizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infixExpression = textFieldInput.getText();
                String postfixExpression = RECORRIDOPOSTORDEN.convertirAPostorden(infixExpression);
                textFieldPostorden.setText(postfixExpression);
            }
        });
            //BOTON REALIZA RECORRIDO PREORDEN
        realizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infixExpression = textFieldInput.getText();
                String postfixExpression = preorderExpression.infixToPostfix(infixExpression);
                String preorderResult = preorderExpression.postfixToPreorder(postfixExpression);
                textFieldPreorden.setText(preorderResult);
            }
        });

        realizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infixExpression = textFieldInput.getText();
                String postfixExpression = RECORRIDOPOSTORDEN.convertirAPostorden(infixExpression);
                textFieldPostorden.setText(postfixExpression);

                double resultado = RECORRIDOPOSTORDEN.evaluarExpresion(postfixExpression);
                textFieldNOTACIONPOLACA.setText(Double.toString(resultado));
            }
        });

        // Realizar inorden
        realizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String infixExpression = textFieldInput.getText();
                String postfixExpression = RECORRIDOINORDEN.convertirAPostfija(infixExpression);
                String inordenResult = RECORRIDOINORDEN.convertirAInorden(postfixExpression);
                textFieldInorden.setText(inordenResult);
            }
        });
        
        //aca es el boton realizar pero si son variables----------------------------------------------
     realizarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String infixExpression = textFieldInput.getText();

        // Verificar si la expresión contiene variables (letras)
        boolean containsVariables = infixExpression.matches(".*[a-zA-Z].*");

        if (containsVariables) {
            // Abrir una nueva ventana para ingresar los valores de las variables
            JFrame variablesFrame = new JFrame("Valores de Variables");
            variablesFrame.setSize(300, 200);
            variablesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel variablesPanel = new JPanel();
            variablesPanel.setLayout(new GridLayout(0, 2));

            // Crear un campo de texto para cada variable encontrada en la expresión
            // y agregarlo al panel de variables
            for (char c : infixExpression.toCharArray()) {
                if (Character.isLetter(c)) {
                    JLabel label = new JLabel("Valor de " + c + ": ");
                    JTextField textField = new JTextField(10);
                    variablesPanel.add(label);
                    variablesPanel.add(textField);
                }
            }

            // Botón para confirmar los valores de las variables
            JButton confirmButton = new JButton("Aceptar");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener los valores de las variables ingresados por el usuario
                    Map<Character, Double> variableValues = new HashMap<>();
                    Component[] components = variablesPanel.getComponents();
                    for (int i = 0; i < components.length; i += 2) {
                        JLabel label = (JLabel) components[i];
                        JTextField textField = (JTextField) components[i + 1];
                        char variable = label.getText().charAt(10); // Obtener el nombre de la variable del texto del JLabel
                        double value = Double.parseDouble(textField.getText());
                        variableValues.put(variable, value);
                    }

                    // Reemplazar las variables con sus valores en la expresión
                    String expressionWithValues = infixExpression;
                    for (Map.Entry<Character, Double> entry : variableValues.entrySet()) {
                        expressionWithValues = expressionWithValues.replace(entry.getKey().toString(), entry.getValue().toString());
                    }

                    // Continuar con la evaluación de la expresión
                    String postfixExpression = RECORRIDOPOSTORDEN.convertirAPostorden(expressionWithValues);
                    textFieldPostorden.setText(postfixExpression);

                    double resultado = RECORRIDOPOSTORDEN.evaluarExpresion(postfixExpression);
                    textFieldNOTACIONPOLACA.setText(Double.toString(resultado));

                    // Mostrar los recorridos en preorden, inorden y postorden
                    String inorden = RECORRIDOINORDEN.convertirAInorden(postfixExpression);
                    textFieldInorden.setText(inorden);

                    String preorden = RECORRIDOPREORDEN.postfixToPreorder(postfixExpression);
                    textFieldPreorden.setText(preorden);

                    // Cerrar la ventana de valores de variables
                    variablesFrame.dispose();
                }
            });

            variablesPanel.add(confirmButton);
            variablesFrame.add(variablesPanel);
            variablesFrame.setVisible(true);
        } else {
            // Si no hay variables, continuar con las operaciones como lo estás haciendo actualmente
            String postfixExpression = RECORRIDOPOSTORDEN.convertirAPostorden(infixExpression);
            textFieldPostorden.setText(postfixExpression);

            double resultado = RECORRIDOPOSTORDEN.evaluarExpresion(postfixExpression);
            textFieldNOTACIONPOLACA.setText(Double.toString(resultado));
        }
    }
});

//           si son variables aca termina su codigo 

        // Botón Borrar
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldInput.setText("");
                textFieldPreorden.setText("");
                textFieldInorden.setText("");
                textFieldPostorden.setText("");
                textFieldNOTACIONPOLACA.setText("");
            }
        });

        //MOSTRAR ARBOL
        mostrarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String infixExpression = textFieldInput.getText();
        ArbolExpresiongraficado arbolExpresion = new ArbolExpresiongraficado();
        try {
            arbolExpresion.raiz = arbolExpresion.construirArbol(infixExpression);
            
            JFrame frameGrafica = new JFrame();
            frameGrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameGrafica.setSize(400, 400);

            JPanel panelGrafica = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    arbolExpresion.dibujar(g, getWidth(), getHeight());
                }
            };

            frameGrafica.add(panelGrafica);
            frameGrafica.setVisible(true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error al construir el árbol: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

        frame.setVisible(true);
    }
}
//FIN