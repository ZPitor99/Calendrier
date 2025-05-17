package controleur;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;
import vue.GridPaneCalendrierFormulaire;
import vue.HBoxRoot;
import vue.VBoxAffichagePlanning;

import java.io.File;
import java.util.Objects;

import static modele.ConstantesCalendrier.MOIS;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneCalendrierFormulaire reservationPane = HBoxRoot.getRevervasionPane();
        VBoxAffichagePlanning affichagePlanning = HBoxRoot.getAffichagePlanning();

        DateCalendrier selDate;

        if (event.getSource() instanceof ToggleButton) {
            ToggleButton clikedButton = (ToggleButton) event.getSource();
            selDate = (DateCalendrier) clikedButton.getUserData();
            HBoxRoot.getRevervasionPane().dateClique.setText(selDate.toString());
            HBoxRoot.getAffichagePlanning().numSemaine.setText(jourSemainePetit(String.valueOf(selDate.getWeekOfYear())));
            affichagePlanning.afficherTable(selDate.getWeekOfYear());
        }

        if (event.getSource() instanceof Button) {
            // Récupération
            //Date
            String[] words = HBoxRoot.getRevervasionPane().dateClique.getText().split(" ");
            //Nom
            String nomSeance = HBoxRoot.getRevervasionPane().getNomSeance();
            //Place
            RadioButton selectedRadio = (RadioButton) HBoxRoot.getRevervasionPane().radioGroupe.getSelectedToggle();
            String selectedNiveau = (String) selectedRadio.getUserData();
            //Horaire
            Horaire debutSeance = new Horaire(HBoxRoot.getRevervasionPane().getComboBoxHeureDebut(), HBoxRoot.getRevervasionPane().getComboBoxMinutesDebut());
            Horaire finSeance = new Horaire(HBoxRoot.getRevervasionPane().getComboBoxHeureFin(), HBoxRoot.getRevervasionPane().getComboBoxMinutesFin());
            // Création des éléments et ajout
            try {
                selDate = new DateCalendrier(Integer.parseInt(words[1]), IndexMois(words[2], MOIS) + 1, Integer.parseInt(words[3]));
                PlageHoraire maSeance = new PlageHoraire(debutSeance, finSeance);
                Reservation SeanceTheatre = new Reservation(selDate, maSeance, nomSeance, selectedNiveau);
                planning.ajout(SeanceTheatre);
                ajoutSeance(SeanceTheatre);
            } catch (ExceptionPlanning e) {
                throw new RuntimeException(e);
            }
            // Affichage
            HBoxRoot.getAffichagePlanning().numSemaine.setText(jourSemainePetit(String.valueOf(selDate.getWeekOfYear())));
            affichagePlanning.afficherTable(selDate.getWeekOfYear());
        }
    }

    public int IndexMois(String mois, String[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (Objects.equals(tab[i], mois)) {
                return i;
            }
        }
        return - 1;
    }

    private String jourSemainePetit(String semaine) {
        if (semaine.length() < 2)
            return "Semaine 0" + semaine;
        return "Semaine " + semaine;
    }

    // Popup de confirmation
    public void ajoutSeance(Reservation seanceTheatre) {
        // Information sur l'ajout
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL); // Bloque l'interaction avec la fenêtre principale
        popup.setTitle("Information");
        popup.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/logo.png"))));

        VBox popupRoot = new VBox(15);
        popupRoot.setPadding(new Insets(20));
        popupRoot.setAlignment(Pos.CENTER);

        //Texte de la réservation faite
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefWidth(360); // Largeur adaptée au texte
        textFlow.getStyleClass().add("popup-text");

        Text infoText = new Text("La réservation " + seanceTheatre.toStringAjout().get(0) + " a été ajoutée.\n" + "Le " + seanceTheatre.toStringAjout().get(1) + " de " + seanceTheatre.toStringAjout().get(2) + ".");
        infoText.setStyle("-fx-fill: #4682b4; -fx-font-size: 14px;");

        textFlow.getChildren().add(infoText);
        popupRoot.getChildren().add(textFlow);


        // Boutons fermer
        HBox boutons = new HBox(15);
        boutons.setAlignment(Pos.CENTER);

        Button btnAnnuler = new Button("_Fermer");
        Platform.runLater(btnAnnuler::requestFocus);
        btnAnnuler.setMnemonicParsing(true);

        btnAnnuler.setOnAction(e -> popup.close());

        boutons.getChildren().addAll(btnAnnuler);

        popupRoot.getChildren().addAll(boutons);

        // Setup
        Scene popupScene = new Scene(popupRoot, 400, 200);
        File css = new File("css" + File.separator + "style.css");
        popupScene.getStylesheets().add(css.toURI().toString());
        popup.setScene(popupScene);
        popup.showAndWait();
    }
}