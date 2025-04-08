package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class VBoxRoot extends VBox implements ConstantesCalendrier {
    public VBoxRoot(){
        super(10);
        Date aujourdhui = new Date();
        Label dateToday = new Label("Date d'aujourd'hui: " + aujourdhui);
        this.getChildren().add(dateToday);
        Label dateDemain = new Label("Date de demain: " + aujourdhui.dateDuLendemain());
        this.getChildren().add(dateDemain);

        StackPane stackPaneMois = new StackPane();
        for(int i=1; i<13; i++){
            ScrollPane scrollPaneDate = new ScrollPane();
            VBox joursMois = new VBox(10);
            VBox.setMargin(joursMois, new Insets(14));
            CalendrierDuMois calendrier = new CalendrierDuMois(i, new Date().getAnnee());
            Label titre = new Label("Calendrier du mois: " + MOIS[calendrier.getMois()-1].toUpperCase(Locale.ROOT));
            titre.setStyle("-fx-font-size: 2em");
            joursMois.getChildren().add(titre);
            for (DateCalendrier date : calendrier.getDates()){
                Label labelJour = new Label(" " + date.toString());
                joursMois.getChildren().add(labelJour);

                if(date.isToday()){
                    labelJour.setId("today");
                }
                else if (date.getMois() == calendrier.getMois()) {
                    if (date.getJourSemaine() == 7) {
                        labelJour.setId("moisFinSemaine");
                    }
                    else labelJour.setId("mois");
                }
                else
                if (date.getJourSemaine() == 7) {
                    labelJour.setId("autreMoisFinSemaine");
                }
                else labelJour.setId("autreMois");
            }
            scrollPaneDate.setContent(joursMois);
            scrollPaneDate.setAccessibleText(MOIS[i-1]);
            stackPaneMois.getChildren().add(scrollPaneDate);
        }
        this.getChildren().add(stackPaneMois);
        List<Node> liste = stackPaneMois.getChildren();
        while(!Objects.equals(liste.getFirst().getAccessibleText(), MOIS[new DateCalendrier().getMois()])) {
            liste.getFirst().toFront();
        }
        HBox hbox = new HBox(10);

        //Aller au premier mois (janvier)
        Button boutonStart = new Button("<<");
        boutonStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton start");
                while(!Objects.equals(liste.getFirst().getAccessibleText(), MOIS[1])) {
                    liste.getLast().toBack();
                }
            }
        });
        boutonStart.setId("bouton");
        hbox.getChildren().add(boutonStart);

        //Aller au mois précédent (mai → avril)
        Button boutonBefore = new Button("<");
        boutonBefore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton before");
                liste.getLast().toBack();
            }
        });
        boutonBefore.setId("bouton");
        hbox.getChildren().add(boutonBefore);

        //Aller au mois suivant (mai → juin)
        Button boutonNext = new Button(">");
        boutonNext.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton next");
                liste.getFirst().toFront();
            }
        });
        boutonNext.setId("bouton");
        hbox.getChildren().add(boutonNext);

        //Aller au dernier mois (décembre)
        Button boutonEnd = new Button(">>");
        boutonEnd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton end");
                while(!Objects.equals(liste.getFirst().getAccessibleText(), MOIS[0])) {
                    liste.getFirst().toFront();
                }
            }
        });
        boutonEnd.setId("bouton");
        hbox.getChildren().add(boutonEnd);
        hbox.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);
    }

}
