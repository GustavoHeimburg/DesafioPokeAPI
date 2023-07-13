module com.senac.desafiopokeapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.senac.desafiopokeapi to javafx.fxml;
    exports com.senac.desafiopokeapi;
}