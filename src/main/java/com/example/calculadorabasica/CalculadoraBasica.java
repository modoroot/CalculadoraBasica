package com.example.calculadorabasica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculadoraBasica extends Application {

    // Creamos un TextField para mostrar el resultado de las operaciones
    TextField tfResultado = new TextField();

    // Creamos una variable para guardar el primer operando
    int operando1 = 0;
    // Creamos una variable para guardar el segundo operando
    int operando2 = 0;
    // Creamos una variable para guardar el resultado de la operación
    int resultado = 0;
    // Creamos una variable para guardar el tipo de operación que se va a realizar
    String operacion = "";

    // Creamos un método para mostrar el resultado en el TextField
    public void mostrarResultado() {
// Convertimos el resultado a String y lo mostramos en el TextField
        tfResultado.setText(String.valueOf(resultado));
    }

    // Creamos un método para realizar las operaciones aritméticas
    public void calcular() {
// Comprobamos qué operación se va a realizar
        switch (operacion) {
            case "+" -> resultado = operando1 + operando2;
            case "-" -> resultado = operando1 - operando2;
            case "*" -> resultado = operando1 * operando2;
            case "/" -> resultado = operando1 / operando2;
        }
    }

    // Sobrescribimos el método start() de la clase Application
    @Override
    public void start(Stage stage) {
        // Creamos la barra de menú
        MenuBar menuBar = new MenuBar();
        // Crea el menú Ayuda
        Menu acercaDeMenu = new Menu("Acerca de");
        //Crea los elementos del menú 'Acerca De'
        MenuItem autor = new MenuItem("Autor");
        //Agrega el elemento al menú Acerca de
        acercaDeMenu.getItems().addAll(autor);
        //Agrega el menú a la barra de menú
        menuBar.getMenus().addAll(acercaDeMenu);
        // Creamos un GridPane para organizar los elementos de la interfaz
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Creamos un TextField para mostrar el resultado de las operaciones
        tfResultado.setEditable(false);
        tfResultado.setPrefWidth(225);
        tfResultado.setPrefHeight(35);
        tfResultado.setStyle("-fx-font: 24 arial;");
        tfResultado.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(tfResultado, 0, 0, 5, 1);
        // Creamos las filas para los botones numéricos
        for (int i = 1; i <= 9; i++) {
            Button btn = new Button(String.valueOf(i));
            btn.setPrefWidth(50);
            btn.setPrefHeight(50);
            btn.setStyle("-fx-font: 24 arial;");
            // Asignamos un evento al botón para obtener el número al hacer clic en él
            btn.setOnAction(event -> {
                // Obtenemos el número del botón
                String numero = btn.getText();
                // Si no hay una operación seleccionada, añadimos el número al primer operando
                if (operacion.equals("")) {
                    operando1 = operando1 * 10 + Integer.parseInt(numero);
                    mostrarResultado();
                } else {
                    // Si hay una operación seleccionada, añadimos el número al segundo operando
                    operando2 = operando2 * 10 + Integer.parseInt(numero);
                    mostrarResultado();
                }
            });
            // Añadimos el botón al GridPane
            grid.add(btn, (i - 1) % 3, (i - 1) / 3 + 1);
        }

        // Creamos un botón para el número 0
        Button btn0 = new Button("0");
        btn0.setPrefWidth(50);
        btn0.setPrefHeight(50);
        btn0.setStyle("-fx-font: 24 arial;");
        // Asignamos un evento al botón para obtener el número al hacer clic en él
        btn0.setOnAction(event -> {
            // Obtenemos el número del botón
            String numero = btn0.getText();
            // Si no hay una operación seleccionada, añadimos el número al primer operando
            if (operacion.equals("")) {
                operando1 = operando1 * 10 + Integer.parseInt(numero);
                mostrarResultado();
            } else {
                // Si hay una operación seleccionada, añadimos el número al segundo operando
                operando2 = operando2 * 10 + Integer.parseInt(numero);
                mostrarResultado();
            }
        });
        // Añadimos el botón al GridPane
        grid.add(btn0, 1, 4);

        // Creamos una fila para los botones de operación
        String[] operaciones = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            Button btn = new Button(operaciones[i]);
            btn.setPrefWidth(50);
            btn.setPrefHeight(50);
            btn.setStyle("-fx-font: 24 arial;");
            // Asignamos un evento al botón para seleccionar la operación al hacer clic en él
            btn.setOnAction(event -> {
                // Obtenemos el símbolo de la operación del botón
                operacion = btn.getText();
            });
            // Añadimos el botón al GridPane
            grid.add(btn, 3, i + 1);
        }

        // Creamos un botón para el signo igual (=)
        Button btnIgual = new Button("=");
        btnIgual.setPrefWidth(50);
        btnIgual.setPrefHeight(105);
        btnIgual.setStyle("-fx-font: 24 arial;");
        // Asignamos un evento al botón para calcular el resultado al hacer clic en él
        btnIgual.setOnAction(event -> {
            // Realizamos la operación seleccionada con los operandos introducidos
            calcular();
            // Mostramos el resultado en el TextField
            mostrarResultado();
            // Vaciamos las variables para poder realizar una nueva operación
            operando1 = 0;
            operando2 = 0;
            resultado = 0;
            operacion = "";
        });
        // Añadimos el botón al GridPane
        grid.add(btnIgual, 3, 4, 1, 2);

        // Creamos un botón para el signo de limpieza de la calculadora (CE)
        Button btnCE = new Button("CE");
        // Asignamos un evento al botón para limpiar la calculadora al hacer clic en él
        btnCE.setOnAction(event -> {
            // Vaciamos las variables y el TextField
            operando1 = 0;
            operando2 = 0;
            resultado = 0;
            operacion = "";
            tfResultado.setText("");
        });
        // Añadimos el botón al GridPane
        grid.add(btnCE, 4, 1);
        grid.add(menuBar,0,0);
        // Creamos una Scene y le asignamos el GridPane
        Scene scene = new Scene(grid, 350, 300);
        // Le asignamos un título a la ventana
        stage.setTitle("Mi primera calculadora");
        // Le asignamos la Scene a la ventana
        stage.setScene(scene);
        // Mostramos la ventana
        stage.show();
    }

    // Creamos el método main() para lanzar la aplicación
    public static void main(String[] args) {
        launch(args);
    }
}
