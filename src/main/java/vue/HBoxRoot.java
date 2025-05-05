package vue;

import controleur.Controleur;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import modele.PlanningCollections;

public class HBoxRoot extends HBox {

    private static PlanningCollections planningReservation = new PlanningCollections();
    private static Controleur chefOrchestre = new Controleur();
    private static VBoxCalendrier calendrierPane = new VBoxCalendrier();
    private static GridPaneCalendrierFormulaire revervasionPane = new GridPaneCalendrierFormulaire();

    public HBoxRoot() {
        super(25);
        this.getChildren().add(this.calendrierPane);
        this.getChildren().add(this.revervasionPane);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
    }

    public static PlanningCollections getPlanning() {
        return planningReservation;
    }

    public static VBoxCalendrier getCalendrierPane() {
        return calendrierPane;
    }

    public static GridPaneCalendrierFormulaire getRevervasionPane() {
        return revervasionPane;
    }

    public static Controleur getChefOrchestre() {
        return chefOrchestre;
    }
}
