package vue;

import controleur.Controleur;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import modele.ExceptionPlanning;
import modele.PlanningCollections;

public class HBoxRoot extends HBox {

    private static PlanningCollections planningReservation = new PlanningCollections();
    private static Controleur chefOrchestre = new Controleur();
    private static VBoxCalendrier calendrierPane = new VBoxCalendrier();
    private static GridPaneCalendrierFormulaire revervasionPane = new GridPaneCalendrierFormulaire();
    private static VBoxAffichagePlanning affichagePlanning;

    static {
        try {
            affichagePlanning = new VBoxAffichagePlanning();
        } catch (ExceptionPlanning e) {
            throw new RuntimeException(e);
        }
    }

    public HBoxRoot() {
        super(25);
        this.getChildren().add(calendrierPane);
        this.getChildren().add(revervasionPane);
        this.getChildren().add(affichagePlanning);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
    }

    /**
     * Accesseur du planning des réservations (modèle)
     * @return le planning des reservations
     */
    public static PlanningCollections getPlanning() {
        return planningReservation;
    }

    /**
     * Accesseur du contrôleur (contrôleur)
     * @return le contrôleur
     */
    public static Controleur getChefOrchestre() {
        return chefOrchestre;
    }

    /**
     * Accesseur du calendier de l'année (vue)
     * @return le planning des reservations
     */
    public static VBoxCalendrier getCalendrierPane() {
        return calendrierPane;
    }

    /**
     * Accesseur du formulaire pour créer une nouvelle réservation (vue)
     * @return le formulaire des réservations
     */
    public static GridPaneCalendrierFormulaire getRevervasionPane() {
        return revervasionPane;
    }

    /**
     * Accesseur de la vue pour afficher les réservations faites (vue)
     * @return la vue de l'affichage des réservations
     */
    public static VBoxAffichagePlanning getAffichagePlanning() {
        return affichagePlanning;
    }

}
