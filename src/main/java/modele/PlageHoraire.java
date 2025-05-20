package modele;

public class PlageHoraire implements Comparable<PlageHoraire> {
    private final static int DUREE_MINIMAL = 30;
    private Horaire horaireDebut;
    private Horaire horaireFin;

    public PlageHoraire(Horaire deb, Horaire fin) throws ExceptionPlanning {
        if (deb == null || fin == null) {
            throw new ExceptionPlanning(0);
        }
        if (fin.toMinutes() - deb.toMinutes() < DUREE_MINIMAL) {
            throw new ExceptionPlanning(0);
        }
        horaireDebut = deb;
        horaireFin = fin;
    }

    public String toString() {
        return horaireDebut.toString() + " à " + horaireFin.toString() + ",\nDurée: " + this.duree() / 60 + " h " + this.duree() % 60 + " min";
    }

    public Horaire getHoraireDebut() {
        return horaireDebut;
    }

    public Horaire getHoraireFin() {
        return horaireFin;
    }


    /**
     * estValide retourne true si la plage horaire (objet appelant) est valide, false sinon
     *
     * @return boolean
     */
    public boolean estValide() {
        return this.getHoraireFin().toMinutes() - this.getHoraireDebut().toMinutes() >= DUREE_MINIMAL;
    }

    public int duree() {
        return this.getHoraireFin().toMinutes() - this.getHoraireDebut().toMinutes();
    }

    /**
     * Compare cette plage horaire à une autre selon leurs horaires de début.
     *
     * @param autre La plage horaire à comparer
     * @return Un entier négatif, zéro ou positif selon que cette plage commence avant, en même temps ou après l'autre
     */
    @Override
    public int compareTo(PlageHoraire autre) {
        return Integer.compare(this.getHoraireDebut().toMinutes(), autre.getHoraireDebut().toMinutes());
    }
}
