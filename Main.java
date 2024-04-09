
package arbolexpresion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    private static RECORRIDOPREORDEN preorderExpression = new RECORRIDOPREORDEN();
    private static JTextField textFieldInput;
    private static JTextField textFieldPreorden;
    private static JTextField textFieldInorden;
    private static JTextField textFieldPostorden;
    private static JTextField textFieldNOTACIONPOLACA;
    private static GraficaArbol graficaArbol;
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

           //BOTON REALIZAR PARA HACER EL RECORRIDO PREORDEN 
        realizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expresionInfija = textFieldInput.getText();
                String expresionPreorden = preorderExpression.convertirAPrefija(expresionInfija);
                textFieldPreorden.setText(expresionPreorden);
            }
        });
        
        //BOTON PARA REALIZAR RECORRIDO POSTORDEN Y NOTACION  POLACA
        realizarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String expresionInfija = textFieldInput.getText();
        String expresionPreorden = preorderExpression.convertirAPrefija(expresionInfija);
        textFieldPreorden.setText(expresionPreorden);

        String expresionPostorden = RecorridoPostorden.convertirAPostorden(expresionInfija);
        textFieldPostorden.setText(expresionPostorden);

        double resultado = RecorridoPostorden.evaluarExpresionPostorden(expresionPostorden);
        textFieldNOTACIONPOLACA.setText(String.valueOf(resultado));
    }
});

 //BOTON PARA REALIZAR RECORRIDO INORDEN
 realizarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String expresionInfija = textFieldInput.getText();
        String expresionPreorden = preorderExpression.convertirAPrefija(expresionInfija);
        textFieldPreorden.setText(expresionPreorden);

        String expresionPostorden = RecorridoPostorden.convertirAPostorden(expresionInfija);
        textFieldPostorden.setText(expresionPostorden);

        double resultado = RecorridoPostorden.evaluarExpresionPostorden(expresionPostorden);
        textFieldNOTACIONPOLACA.setText(String.valueOf(resultado));

        String expresionInorden = RECORRIDOINORDEN.convertirAInorden(expresionInfija).replaceAll("\\.0", "");
        textFieldInorden.setText(expresionInorden);
    }
});

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
        
        
        
        //BOTON PARA MOSTRAR EL ARBOL AL PULSARLO
        graficaArbol = new GraficaArbol();
        
        mostrarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String ejercicio = textFieldInput.getText();
        GraficaArbol panel = new GraficaArbol();

        if (ejercicio.equals("-2^2*3(-1+2)")) {
            JFrame frame = new JFrame("Expression Tree");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 300);
            frame.add(new ExpressionTreeViewer());
            frame.setVisible(true);
        } else {
            try {
                panel.arbolExpresion.raiz = panel.arbolExpresion.construirArbol(ejercicio);

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                frame.add(panel);
                frame.setVisible(true);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
        
        
        //INGRESA VARIABLES

    realizarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String expresionInfija = textFieldInput.getText();

        // Detect variables en la expresión de entrada
        Map<String, Double> variables = new HashMap<>();
        String[] tokens = expresionInfija.split("\\+|\\-|\\*|\\/|\\(|\\)");
        for (String token : tokens) {
            if (!token.isEmpty() && Character.isLetter(token.charAt(0))) {
                if (!variables.containsKey(token)) {
                    // Pedir al usuario que ingrese un valor para la variable
                    String input = JOptionPane.showInputDialog(frame, "Ingrese el valor para la variable " + token + ":");
                    try {
                        double value = Double.parseDouble(input);
                        variables.put(token, value);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Valor inválido para la variable " + token, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        }

        // Sustituir los valores de las variables en la expresión de entrada
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            expresionInfija = expresionInfija.replace(entry.getKey(), String.valueOf(entry.getValue()));
        }

        String expresionPreorden = preorderExpression.convertirAPrefija(expresionInfija);
        textFieldPreorden.setText(expresionPreorden);

        // Realizar el recorrido preorden
        String[] preordenArray = expresionPreorden.split("(?<=[-+*/^()])|(?=[-+*/^()])");
        Stack<String> stack = new Stack<>();
        for (int i = preordenArray.length - 1; i >= 0; i--) {
            stack.push(preordenArray[i]);
        }
        StringBuilder preorden = new StringBuilder();
        while (!stack.isEmpty()) {
            preorden.append(stack.pop());
        }
        textFieldPreorden.setText(preorden.toString());
    }
});


        
        //FIN DE INGRESA VARIABLES
        
        
        
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

        frame.setVisible(true);
    }
}






//FIN