package modele;

import java.util.Arrays;
import java.util.List;

public class Reservation implements Comparable<Reservation> {
    private DateCalendrier dateSpectacle;
    private PlageHoraire plageHoraireSpectacle;
    private String TitreSpectacle;
    private String PlaceSpectacle;

    public Reservation(DateCalendrier parDateSpectacle, PlageHoraire parPlageHoraireSpectacle, String parTitreSpectacle, String parPlaceSpectacle) throws ExceptionPlanning {
        if (parTitreSpectacle == null) {
            throw new ExceptionPlanning(0);
        }
        dateSpectacle = parDateSpectacle;
        plageHoraireSpectacle = parPlageHoraireSpectacle;
        TitreSpectacle = parTitreSpectacle;
        PlaceSpectacle = parPlaceSpectacle;
    }

    @Override
    public String toString() {
        return TitreSpectacle + ": " + "le " + dateSpectacle + " de " + plageHoraireSpectacle;
    }

    public List<Object> toStringAjout() {
        return Arrays.asList(getSceance(), getDate(), getHoraire(), getPlace());
    }


    @Override
    public int compareTo(Reservation other) {
        if (other == null) {
            return 1;
        }
        if (this.dateSpectacle.compareTo(other.dateSpectacle) == 0) {
            return this.plageHoraireSpectacle.compareTo(other.plageHoraireSpectacle);
        } else
            return dateSpectacle.compareTo(other.dateSpectacle);
    }

    public DateCalendrier getDate() {
        return dateSpectacle;
    }

    public PlageHoraire getHoraire() {
        return plageHoraireSpectacle;
    }

    public String getSceance() {
        return TitreSpectacle;
    }

    public String getPlace() {
        return PlaceSpectacle;
    }

    public boolean estValide() {
        return this.dateSpectacle.estValide() && this.plageHoraireSpectacle.estValide() && this.TitreSpectacle != null;
    }
}
