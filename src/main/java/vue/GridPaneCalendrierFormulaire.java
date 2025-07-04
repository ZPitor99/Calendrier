package vue;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import static modele.ConstantesCalendrier.HEURES;
import static modele.ConstantesCalendrier.MINUTES;
import static vue.HBoxRoot.getChefOrchestre;

public class GridPaneCalendrierFormulaire extends GridPane {

    public Label dateClique = new Label("");
    public TextField nomSeanceField = new TextField();
    public ToggleGroup radioGroupe = new ToggleGroup();
    public ComboBox<Integer> comboBoxHeureDebut;
    public ComboBox<Integer> comboBoxMinutesDebut;
    public ComboBox<Integer> comboBoxHeureFin;
    public ComboBox<Integer> comboBoxMinutesFin;

    public GridPaneCalendrierFormulaire() {
        super();
        this.setGridLinesVisible(false);
        this.setVgap(10);
        this.setHgap(10);
        this.setPrefSize(400, 400);

        //Déclaration et instanciation
        Label titre = new Label("Nouvelle reservation");
        titre.setAlignment(Pos.CENTER);
        titre.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        Label nomSeance = new Label("_Nom Seance");

        nomSeance.setLabelFor(nomSeanceField);
        nomSeance.setMnemonicParsing(true);
        nomSeanceField.setPromptText("Pièce de théatre");
        Platform.runLater(nomSeanceField::requestFocus);


        Label date = new Label("Date choisie");
        dateClique.setStyle("-fx-font-weight: 600;");


        Label emplacement = new Label("Emplacement");
        final String[] NIVEAUX = {"Balcon", "Loge", "Galerie"};
        HBox hbNiveau = new HBox(5);
        for (String s : NIVEAUX) {
            RadioButton radioButton = new RadioButton("_" + s);
            radioButton.setSelected(s.equals(NIVEAUX[0]));
            radioButton.setUserData(s);
            radioButton.setToggleGroup(radioGroupe);
            hbNiveau.getChildren().add(radioButton);
        }


        ToggleGroup combo = new ToggleGroup();
        Label horaire = new Label("Horaire debut/fin");
        comboBoxHeureDebut = peupleComboBox(HEURES);
        comboBoxHeureDebut.getSelectionModel().selectFirst();
        Label hdeb = new Label("h");
        hdeb.setId("simple");
        comboBoxMinutesDebut = peupleComboBox(MINUTES);
        comboBoxMinutesDebut.getSelectionModel().selectFirst();
        Label mindeb = new Label("min");
        mindeb.setId("simple");


        comboBoxHeureFin = peupleComboBox(HEURES);
        comboBoxHeureFin.getSelectionModel().select(1);
        Label hfin = new Label("h");
        hfin.setId("simple");
        comboBoxMinutesFin = peupleComboBox(MINUTES);
        comboBoxMinutesFin.getSelectionModel().select(1);
        Label minfin = new Label("min");
        minfin.setId("simple");


        HBox bntValidation = new HBox();

        Button annuler = new Button("_Annuler");
        annuler.setMnemonicParsing(true);
        annuler.setOnAction(e -> {
            nomSeanceField.clear();
            comboBoxHeureDebut.getSelectionModel().selectFirst();
            comboBoxMinutesDebut.getSelectionModel().selectFirst();
            comboBoxHeureFin.getSelectionModel().selectFirst();
            comboBoxHeureFin.getSelectionModel().selectNext();
            comboBoxMinutesFin.getSelectionModel().selectFirst();
        });

        Button valider = new Button("_Valider");
        valider.setMnemonicParsing(true);
        valider.addEventHandler(ActionEvent.ACTION, getChefOrchestre());

        bntValidation.getChildren().addAll(annuler, valider);
        bntValidation.setSpacing(10);
        bntValidation.setAlignment(Pos.CENTER_RIGHT);

        Separator sep = new Separator();

        //Ajout à la fenetre
        this.add(titre, 0, 0);

        this.add(nomSeance, 0, 1);
        this.add(nomSeanceField, 1, 1, 4, 1);

        this.add(date, 0, 2);
        this.add(dateClique, 1, 2, 5, 1);

        this.add(emplacement, 0, 3);
        this.add(hbNiveau, 1, 3, 4, 1);

        this.add(horaire, 0, 4);
        this.add(comboBoxHeureDebut, 1, 4);
        this.add(hdeb, 2, 4);
        this.add(comboBoxMinutesDebut, 3, 4);
        this.add(mindeb, 4, 4);

        this.add(comboBoxHeureFin, 1, 5);
        this.add(hfin, 2, 5);
        this.add(comboBoxMinutesFin, 3, 5);
        this.add(minfin, 4, 5);

        this.add(sep, 0, 6, 5, 1);

        this.add(bntValidation, 0, 7, 5, 1);

    }

    /**
     * Permet de peupler la comboBox à partir d'une lite d'integer
     * @param peuple
     * @return la comboBox peuplé grâce à l'argument
     */
    private ComboBox<Integer> peupleComboBox(Integer[] peuple) {
        ComboBox<Integer> comboBox = new ComboBox<>();
        for (Integer s : peuple) {
            comboBox.getItems().add(s);
        }
        return comboBox;
    }

    /**
     * Accesseur du texte du TextFiel du nom de la seance
     * @return le texte du champ rempli par l'utilisateur
     */
    public String getNomSeance() {
        return nomSeanceField.getText();
    }

    /**
     * Accesseur de la ComboBoxHeureDebut
     * @return la valeur de la comboBox
     */
    public Integer getComboBoxHeureDebut() {
        return comboBoxHeureDebut.getValue();
    }

    /**
     * Accesseur de la ComboBoxMinutesDebut
     * @return la valeur de la comboBox
     */
    public Integer getComboBoxMinutesDebut() {
        return comboBoxMinutesDebut.getValue();
    }

    /**
     * Accesseur de la ComboBoxHeureFin
     * @return la valeur de la comboBox
     */
    public Integer getComboBoxHeureFin() {
        return comboBoxHeureFin.getValue();
    }

    /**
     * Accesseur de la ComboBoxMinutesFin
     * @return la valeur de la comboBox
     */
    public Integer getComboBoxMinutesFin() {
        return comboBoxMinutesFin.getValue();
    }

}
