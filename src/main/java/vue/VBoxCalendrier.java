package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.*;

import java.util.List;
import java.util.Objects;

import static vue.HBoxRoot.getChefOrchestre;

public class VBoxCalendrier extends VBox implements ConstantesCalendrier {
    public VBoxCalendrier() {
        super(10);
        this.setPrefSize(300, 400);
        DateCalendrier today = new DateCalendrier();
        String todayStr = JOURS_SEMAINE[today.getJourSemaine() - 1] + " " + today.getJour() + " " + MOIS[today.getMois() - 1] + " " + today.getAnnee();
        Label dateToday = new Label(todayStr);
        dateToday.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().add(dateToday);

        //Initialisation du stackPane : pour empilier 1 conteneur par mois
        StackPane stackPaneMois = new StackPane();

        //les boutons seront inserer dans le groupe : selection de 1 à la fois
        ToggleGroup buttonGroup = new ToggleGroup();

        for (int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());

            //initialisation du Tile Pane + colones
            TilePane tilePane = new TilePane();
            tilePane.setPadding(new Insets(10, 10, 10, 10));
            tilePane.setVgap(10.0);
            tilePane.setHgap(10.0);
            tilePane.setPrefColumns(6);

            //Une ligne pour lun,mar,mer,... et le reste pour les boutons date
            tilePane.setPrefRows(monthCalendar.getDates().size() / 7);

            //Marquage du tilePane
            tilePane.setId("opaque");

            //Affiche le titre des colones lu,ma,me,...
            for (String jourAb : JOURS_SEMAINE_ABR) {
                Label labelJour = new Label(jourAb);
                tilePane.getChildren().add(labelJour);
            }
            int compteurNBJ = 0;
            for (DateCalendrier date : monthCalendar.getDates()) {
                //Creation des boutons, 1 par jour
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));

                //Inserer le tooglebouton dans le groupe
                boutonDate.setToggleGroup(buttonGroup);
                if (date.isToday()) {
                    boutonDate.setId("boutonCalendrierToday");
                } else
                    boutonDate.setId("boutonCalendrier");
                tilePane.getChildren().add(boutonDate);

                //Associer le Togglebouton, utilise par la suite
                boutonDate.setUserData(date);

                boutonDate.addEventHandler(ActionEvent.ACTION, getChefOrchestre());

                compteurNBJ++;
            }
            tilePane.setAccessibleText(MOIS[numMois - 1]);
            if (compteurNBJ < 40) {
                for (int i = 0; i < 7; i++) {
                    Label labelJourAlign = new Label("");
                    tilePane.getChildren().add(labelJourAlign);
                }
            }
            Label labelMois = new Label(MOIS_ABR[numMois - 1]);
            labelMois.setStyle("-fx-font-size: 1em;" + " -fx-font-weight: bold");
            tilePane.getChildren().add(labelMois);
            tilePane.setStyle("-fx-border-color: #4682b4;");
            stackPaneMois.getChildren().add(tilePane);
        }
        stackPaneMois.setAlignment(Pos.CENTER);
        this.getChildren().add(stackPaneMois);

        //---
        //Boutons de déplacement
        //---

        List<Node> liste = stackPaneMois.getChildren();
        while (! Objects.equals(liste.get(0).getAccessibleText(), MOIS[new DateCalendrier().getMois()])) {
            liste.get(0).toFront();
        }

        HBox hbox = new HBox(10);

        //Aller au premier mois (janvier)
        Button boutonStart = new Button("<<");
        boutonStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton start");
                while (! Objects.equals(liste.get(0).getAccessibleText(), MOIS[1])) {
                    liste.get(liste.size() - 1).toBack();
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
                liste.get(liste.size() - 1).toBack();
            }
        });
        boutonBefore.setId("bouton");
        hbox.getChildren().add(boutonBefore);

        //Aller au mois suivant (mai → juin)
        Button boutonNext = new Button(">");
        boutonNext.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton next");
                liste.get(0).toFront();
            }
        });
        boutonNext.setId("bouton");
        hbox.getChildren().add(boutonNext);

        //Aller au dernier mois (décembre)
        Button boutonEnd = new Button(">>");
        boutonEnd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("bouton end");
                while (! Objects.equals(liste.get(0).getAccessibleText(), MOIS[0])) {
                    liste.get(0).toFront();
                }
            }
        });
        boutonEnd.setId("bouton");
        hbox.getChildren().add(boutonEnd);
        hbox.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);
    }
}