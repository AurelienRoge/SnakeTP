module main.java.Application {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.java.Application to javafx.fxml;
    exports main.java.Application;
}