package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.DateCalendrier;
import modele.Horaire;
import modele.PlanningCollections;
import vue.GridPaneCalendrierFormulaire;
import vue.HBoxRoot;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneCalendrierFormulaire reservationPane = HBoxRoot.getRevervasionPane();

        if (event.getSource() instanceof ToggleButton) {
            ToggleButton clikedButton = (ToggleButton)event.getSource();
            DateCalendrier selDate = (DateCalendrier)clikedButton.getUserData();
            HBoxRoot.getRevervasionPane().dateClique.setText(selDate.toString());
        }

        if (event.getSource() instanceof Button) {
            Button clikedButton = (Button)event.getSource();
            String nomSeance = HBoxRoot.getRevervasionPane().getNomSeance();
            //Horaire debutSeance = new Horaire()
        }
    }

}
