package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modele.*;


public class VBoxAffichagePlanning extends VBox {

    public Label numSemaine = new Label("Semaine " + new DateCalendrier().getWeekOfYear());
    public TableView<Reservation> tableDesReservations = new TableView<>();

    public VBoxAffichagePlanning() throws ExceptionPlanning {
        super(10);
        numSemaine.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().addAll(numSemaine);


        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        TableColumn<Reservation, String> sceanceColumn = new TableColumn<>("Sceance");
        TableColumn<Reservation, String> placeColumn = new TableColumn<>("Place");
        TableColumn<Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Horaire");

        // Définir les largeurs des colonnes
        dateColumn.setPrefWidth(150);
        dateColumn.setMinWidth(150);
        dateColumn.setMaxWidth(150);

        sceanceColumn.setPrefWidth(120);
        sceanceColumn.setMinWidth(120);
        sceanceColumn.setMaxWidth(120);

        placeColumn.setPrefWidth(100);
        placeColumn.setMinWidth(100);
        placeColumn.setMaxWidth(100);

        horaireColumn.setPrefWidth(130);
        horaireColumn.setMinWidth(130);
        horaireColumn.setMaxWidth(130);

        // Colonne Date
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellFactory(column -> new TableCell<>() {
            private final Text text = new Text();
            {
                text.wrappingWidthProperty().bind(column.widthProperty().subtract(10));
                text.setStyle("-fx-padding: 5px;");
                setGraphic(text);
            }

            @Override
            protected void updateItem(DateCalendrier item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    text.setText(item.toString());
                    setGraphic(text);
                }
            }
        });
        dateColumn.setReorderable(Boolean.FALSE);
        // Colonne Séance
        sceanceColumn.setCellValueFactory(new PropertyValueFactory<>("sceance"));
        sceanceColumn.setCellFactory(column -> new TableCell<>() {
            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(column.widthProperty().subtract(10));
                text.setStyle("-fx-padding: 5px;");
                setGraphic(text);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    text.setText(item);
                    setGraphic(text);
                }
            }
        });
        sceanceColumn.setReorderable(Boolean.FALSE);
        // Les deux autres colonnes
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        placeColumn.setReorderable(Boolean.FALSE);
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        horaireColumn.setReorderable(Boolean.FALSE);

        // Permettre à la table de s'agrandir avec la fenêtre
        VBox.setVgrow(tableDesReservations, Priority.ALWAYS);

        tableDesReservations.getColumns().addAll(dateColumn, sceanceColumn, placeColumn, horaireColumn);
        tableDesReservations.setPrefSize(500, 400);
        tableDesReservations.setEditable(true);
        this.getChildren().addAll(tableDesReservations);

        tableDesReservations.getItems().add(new Reservation(new DateCalendrier(), new PlageHoraire(new Horaire(19, 30), new Horaire(21, 45)), "McBeth", "Balcon"));
    }

    public void afficherTable(Integer num) {
        tableDesReservations.getItems().clear();
        for (Reservation r : HBoxRoot.getPlanning().getReservations(num)) {
            tableDesReservations.getItems().add(r);
        }
    }
}
