package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.*;
import vue.GridPaneCalendrierFormulaire;
import vue.HBoxRoot;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneCalendrierFormulaire reservationPane = HBoxRoot.getRevervasionPane();

        DateCalendrier selDate = new DateCalendrier();

        if (event.getSource() instanceof ToggleButton) {
            ToggleButton clikedButton = (ToggleButton) event.getSource();
            selDate = (DateCalendrier) clikedButton.getUserData();
            HBoxRoot.getRevervasionPane().dateClique.setText(selDate.toString());
        }

        if (event.getSource() instanceof Button) {
            Button clikedButton = (Button) event.getSource();
            String nomSeance = HBoxRoot.getRevervasionPane().getNomSeance();
            Horaire debutSeance = new Horaire(HBoxRoot.getRevervasionPane().getComboBoxHeureDebut(), HBoxRoot.getRevervasionPane().getComboBoxMinutesDebut());
            Horaire finSeance = new Horaire(HBoxRoot.getRevervasionPane().getComboBoxHeureFin(), HBoxRoot.getRevervasionPane().getComboBoxMinutesFin());
            try {
                PlageHoraire maSeance = new PlageHoraire(debutSeance, finSeance);
                Reservation SeanceTheatre = new Reservation(selDate, maSeance, nomSeance);
            } catch (ExceptionPlanning e) {
                throw new RuntimeException(e);
            }

        }
    }

}
