package vue;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    public HBoxRoot() {
        super(25);
        VBoxCalendrier calendrier = new VBoxCalendrier();
        this.getChildren().add(calendrier);
        GridPaneCalendrierFormulaire calendrierFormulaire = new GridPaneCalendrierFormulaire();
        this.getChildren().add(calendrierFormulaire);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
    }
}
