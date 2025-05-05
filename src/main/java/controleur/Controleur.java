package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import modele.PlanningCollections;
import vue.GridPaneCalendrierFormulaire;
import vue.HBoxRoot;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneCalendrierFormulaire reservationPane = HBoxRoot.getRevervasionPane();

        //A FINIR
    }

}
