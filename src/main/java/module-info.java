module hupetrik.carproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens hupetrik.carproject to javafx.fxml;
    exports hupetrik.carproject;
}