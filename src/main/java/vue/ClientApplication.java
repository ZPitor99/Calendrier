package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Objects;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) {
        HBox root = new HBoxRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Calendrier Théatre");
        stage.setResizable(true);
        File css = new File("css" + File.separator + "style.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/logo.png"))));
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
