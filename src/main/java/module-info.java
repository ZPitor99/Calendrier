module org.example.deuxiemeprojet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.deuxiemeprojet to javafx.fxml;
    exports org.example.deuxiemeprojet;

    exports vue;
    exports modele;
}