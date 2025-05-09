package modele;

import modele.ExceptionPlanning;
import modele.PlageHoraire;

public class Reservation implements Comparable<Reservation> {
    private Date dateSpectacle;
    private PlageHoraire plageHoraireSpectacle;
    private String TitreSpectacle;

    public Reservation(Date parDateSpectacle, PlageHoraire parPlageHoraireSpectacle, String parTitreSpectacle) throws ExceptionPlanning {
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


    @Override
    public int compareTo(Reservation other) {
        if (other == null) {
            return 1;
        }
        if (this.dateSpectacle.compareTo(other.dateSpectacle) == 0) {
            return this.plageHoraireSpectacle.compareTo(other.plageHoraireSpectacle);
        }
        else
            return dateSpectacle.compareTo(other.dateSpectacle);
    }

    public Date getDateSpectacle() {
        return dateSpectacle;
    }

    public PlageHoraire getPlageHoraire() {
        return plageHoraireSpectacle;
    }

    public String getTitreSpectacle() {
        return TitreSpectacle;
    }

    public boolean estValide() {
        return this.dateSpectacle.estValide() && this.plageHoraireSpectacle.estValide() && this.TitreSpectacle != null;
    }
}
