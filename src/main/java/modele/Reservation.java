package modele;

import java.util.Arrays;
import java.util.List;

public class Reservation implements Comparable<Reservation> {
    private DateCalendrier dateSpectacle;
    private PlageHoraire plageHoraireSpectacle;
    private String TitreSpectacle;

    public Reservation(DateCalendrier parDateSpectacle, PlageHoraire parPlageHoraireSpectacle, String parTitreSpectacle) throws ExceptionPlanning {
        if (parTitreSpectacle == null) {
            throw new ExceptionPlanning(0);
        }
        dateSpectacle = parDateSpectacle;
        plageHoraireSpectacle = parPlageHoraireSpectacle;
        TitreSpectacle = parTitreSpectacle;
    }

    @Override
    public String toString() {
        return TitreSpectacle + ": " + "le " + dateSpectacle + " de " + plageHoraireSpectacle;
    }

    public List<Object> toStringAjout() {
        return Arrays.asList(TitreSpectacle, dateSpectacle, plageHoraireSpectacle);
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

    public boolean estValide() {
        return this.dateSpectacle.estValide() && this.plageHoraireSpectacle.estValide() && this.TitreSpectacle != null;
    }
}
