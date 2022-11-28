module Application {
    requires javafx.controls;
    requires javafx.fxml;


    opens Application to javafx.fxml;
    exports Application;
    exports Application.System.GameMechanics;
    opens Application.System.GameMechanics to javafx.fxml;
    exports Application.System.ScoreAndFile;
    opens Application.System.ScoreAndFile to javafx.fxml;
}
//A MODIFIER ??