module calc.calc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.accessibility;


    opens calc.calc to javafx.fxml;
    exports calc.calc;
}