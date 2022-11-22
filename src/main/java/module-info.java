module com.example.SnakeTP {
    requires javafx.controls;
    requires javafx.fxml;


    opens Application to javafx.fxml;
    exports Application;
}