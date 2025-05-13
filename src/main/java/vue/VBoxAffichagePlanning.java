package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.*;

public class VBoxAffichagePlanning extends VBox {

    public Label numSemaine = new Label("Semaine 00");
    public TableView<Reservation> tableDesReservations = new TableView<>();

    public VBoxAffichagePlanning() throws ExceptionPlanning {
        super(10);
        numSemaine.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().addAll(numSemaine);


        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        TableColumn<Reservation, String> SceanceColumn = new TableColumn<>("Sceance");
        TableColumn<Reservation, String> placeColumn = new TableColumn<>("Place");
        TableColumn<Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Horaire");

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        SceanceColumn.setCellValueFactory(new PropertyValueFactory<>("sceance"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("horaire"));

        tableDesReservations.getColumns().addAll(dateColumn, SceanceColumn, placeColumn, horaireColumn);
        tableDesReservations.setPrefSize(400, 400);
        tableDesReservations.setEditable(true);
        this.getChildren().addAll(tableDesReservations);

        tableDesReservations.getItems().add(new Reservation(new DateCalendrier(), new PlageHoraire(new Horaire(19,30), new Horaire(21, 45)), "McBeth"));
    }

    public void afficherTable(Integer num) {
        tableDesReservations.getItems().clear();
        for (Reservation r : HBoxRoot.getPlanning().getReservations(num)) {
            tableDesReservations.getItems().add(r);
        }
    }
}
