package modele;

import java.util.*;

public class PlanningCollections {
    private List<Reservation> chListReservations;
    private Set<Reservation> chSetReservations;
    private Map<Integer, Set<Reservation>> chMapReservations;

    public PlanningCollections() {
        chListReservations = new ArrayList<Reservation>();
        chSetReservations = new TreeSet<Reservation>();
        chMapReservations = new TreeMap<Integer, Set<Reservation>>();
    }

    @Override
    public String toString() {
        return "ArrayList: " + chListReservations.size() + " - " + chListReservations
                + "\nTreeSet: " + chSetReservations.size() + " - " + chSetReservations
                + "\nTreeMap: " + chMapReservations.size() + " - " + chMapReservations;
    }

    /**
     * Ajout de la reservation r dans les collections (chListReservations, chSetReservations et chMapReservations)
     * Leve ExceptionPlanning quand la reservation r n'est pas compatible avec les réservations déjà ajoutées.
     *
     * @param r
     * @throws ExceptionPlanning
     */
    public void ajout(Reservation r) throws ExceptionPlanning {
        // ajout à la liste
        Iterator<Reservation> iterateur = chListReservations.iterator();
        while (iterateur.hasNext()) {
            Reservation reserve = iterateur.next();
            if (reserve.compareTo(r) == 0) {
                throw new ExceptionPlanning(2);
            }
        }
        chListReservations.add(r);

        //ajout au set
        int sizeInitial = chSetReservations.size();
        chSetReservations.add(r);
        if (sizeInitial == chSetReservations.size()) {
            throw new ExceptionPlanning(2);
        }
        // ajout a la Map
        int numSemaine = r.getDateSpectacle().getJour();
        for (int i = 1; i < r.getDateSpectacle().getMois(); i++) {
            switch (i) {
                case 2:
                    numSemaine += 28;

                case 4:
                case 6:
                case 9:
                case 11:
                    numSemaine += 30;
                default:
                    numSemaine += 31;
            }
        }
        if (! chMapReservations.containsKey(numSemaine / 7)) {
            chMapReservations.put(numSemaine / 7, new TreeSet<Reservation>());
        }
        chMapReservations.get(numSemaine / 7).add(r);
    }

    /**
     * getReservation renvoie un treeSet qui contient les reservations de fait à la date
     * de parDate, null si aucune reservation à cette date
     *
     * @param parDate
     * @return reservationParDate
     */
    public TreeSet<Reservation> getReservations(DateCalendrier parDate) {
        TreeSet<Reservation> reservationParDate = new TreeSet<Reservation>();
        for (Reservation r : chSetReservations) {
            if (r.getDateSpectacle().compareTo(parDate) == 0) {
                reservationParDate.add(r);
            } else if (r.getDateSpectacle().compareTo(parDate) > 0) {
                break;
            }
        }
        if (reservationParDate.isEmpty()) {
            return null;
        }
        return reservationParDate;
    }

    /**
     * getReservation retourne un treeSet de l'ensemble des réservations ayant dans leur intitulé la
     * chaine de charactère parString
     *
     * @param parString
     * @return
     */
    public TreeSet<Reservation> getReservations(String parString) {
        TreeSet<Reservation> reservationString = new TreeSet<Reservation>();
        for (Reservation r : chSetReservations) {
            if (r.getTitreSpectacle().toLowerCase().contains(parString.toLowerCase())) {
                reservationString.add(r);
            }
        }
        if (reservationString.isEmpty()) {
            return null;
        }
        return reservationString;
    }
}
