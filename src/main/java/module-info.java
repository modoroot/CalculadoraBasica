module com.example.calculadorabasica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculadorabasica to javafx.fxml;
    exports com.example.calculadorabasica;
}