package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxAffichagePlanning extends VBox {

    public Label numSemaine = new Label("Semaine 00");

    public VBoxAffichagePlanning() {
        super(10);
        numSemaine.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().addAll(numSemaine);

    }
}
