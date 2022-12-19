package com.example.calculadorabasica;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CalculadoraBasica extends Application {

    // Creamos un TextField para mostrar los resultados
    private TextField display = new TextField();

    // Creamos una variable para almacenar el primer operando
    private double operand1 = 0;

    // Creamos una variable para almacenar el segundo operando
    private double operand2 = 0;

    // Creamos una variable para almacenar el resultado
    private double result = 0;

    // Creamos una variable para almacenar la operación a realizar
    private String operation = "";

    // Creamos una variable para controlar si se está esperando por un segundo operando
    private boolean waitingForSecondOperand = false;

    @Override
    public void start(Stage stage) {
        // Creamos la barra de menú
        MenuBar menuBar = new MenuBar();

        // Creamos el menú "Archivo" y sus opciones
        Menu fileMenu = new Menu("Acerca de");
        MenuItem exitMenuItem = new MenuItem("Autor");
        exitMenuItem.setOnAction(event -> {
            //Crea el cuadro de diálogo
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Autor");
            dialog.setContentText("Autor: Antonio Miguel Núñez Ariza | 2ºDAM");
            //Agrega un botón "Aceptar" al cuadro de diálogo
            ButtonType aceptarButton = new ButtonType("Aceptar");
            dialog.getDialogPane().getButtonTypes().add(aceptarButton);
            dialog.show();
        });
        fileMenu.getItems().add(exitMenuItem);

        // Añadimos el menú "Archivo" a la barra de menú
        menuBar.getMenus().add(fileMenu);

        // Creamos el panel con los botones
        GridPane buttonPanel = new GridPane();
        buttonPanel.setHgap(5);
        buttonPanel.setVgap(5);
        buttonPanel.setPadding(new Insets(5));

        // Creamos los botones y los añadimos al panel
        Button btn1 = new Button("1");
        btn1.setOnAction(event -> addDigit(1));
        buttonPanel.add(btn1, 0, 0);

        Button btn2 = new Button("2");
        btn2.setOnAction(event -> addDigit(2));
        buttonPanel.add(btn2, 1, 0);

        Button btn3 = new Button("3");
        btn3.setOnAction(event -> addDigit(3));
        buttonPanel.add(btn3, 2, 0);
        Button btn4 = new Button("4");
        btn4.setOnAction(event -> addDigit(4));
        buttonPanel.add(btn4, 0, 1);

        Button btn5 = new Button("5");
        btn5.setOnAction(event -> addDigit(5));
        buttonPanel.add(btn5, 1, 1);

        Button btn6 = new Button("6");
        btn6.setOnAction(event -> addDigit(6));
        buttonPanel.add(btn6, 2, 1);

        Button btn7 = new Button("7");
        btn7.setOnAction(event -> addDigit(7));
        buttonPanel.add(btn7, 0, 2);

        Button btn8 = new Button("8");
        btn8.setOnAction(event -> addDigit(8));
        buttonPanel.add(btn8, 1, 2);

        Button btn9 = new Button("9");
        btn9.setOnAction(event -> addDigit(9));
        buttonPanel.add(btn9, 2, 2);

        Button btn0 = new Button("0");
        btn0.setOnAction(event -> addDigit(0));
        buttonPanel.add(btn0, 0, 3);

        Button btnDot = new Button(".");
        btnDot.setOnAction(event -> addDot());
        buttonPanel.add(btnDot, 1, 3);

        Button btnEqual = new Button("=");
        btnEqual.setOnAction(event -> calculateResult());
        buttonPanel.add(btnEqual, 2, 3);

        Button btnPlus = new Button("+");
        btnPlus.setOnAction(event -> setOperation("+"));
        buttonPanel.add(btnPlus, 3, 0);

        Button btnMinus = new Button("-");
        btnMinus.setOnAction(event -> setOperation("-"));
        buttonPanel.add(btnMinus, 3, 1);

        Button btnMultiply = new Button("*");
        btnMultiply.setOnAction(event -> setOperation("*"));
        buttonPanel.add(btnMultiply, 3, 2);

        Button btnDivide = new Button("/");
        btnDivide.setOnAction(event -> setOperation("/"));
        buttonPanel.add(btnDivide, 3, 3);

        // Creamos el botón de limpiar y lo añadimos al panel
        Button btnClear = new Button("C");
        btnClear.setOnAction(event -> clear());
        buttonPanel.add(btnClear, 4, 0);

        // Añadimos el panel con los botones y el TextField para mostrar los resultados a la escena
        GridPane root = new GridPane();
        root.add(menuBar, 0, 0);
        root.add(display, 0, 1);
        root.add(buttonPanel, 0, 2);

        // Creamos la escena y asignamos la raíz al árbol de nodos
        Scene scene = new Scene(root, 300, 250);
        // Asignamos la escena al stage y mostramos la ventana
        stage.setScene(scene);
        stage.setTitle("Mi primera Calculadora");
        stage.show();
    }

    // Método para añadir un dígito al TextField
    private void addDigit(int digit) {
        if (waitingForSecondOperand) {
            display.setText("");
            waitingForSecondOperand = false;
        }

        String text = display.getText();
        if (!text.equals("0")) {
            display.setText(text + digit);
        } else {
            display.setText(String.valueOf(digit));
        }
    }

    // Método para añadir un punto decimal al TextField
    private void addDot() {
        if (waitingForSecondOperand) {
            display.setText("");
            waitingForSecondOperand = false;
        }

        String text = display.getText();
        if (!text.contains(".")) {
            display.setText(text + ".");
        }
    }

    // Método para establecer la operación a realizar
    private void setOperation(String op) {
        operand1 = Double.parseDouble(display.getText());
        operation = op;
        waitingForSecondOperand = true;
    }

    // Método para calcular el resultado de la operación
    private void calculateResult() {
        operand2 = Double.parseDouble(display.getText());

        switch (operation) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }

        display.setText(String.valueOf(result));
    }

    private void clear() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}



