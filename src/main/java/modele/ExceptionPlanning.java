package modele;

import modele.ConstantesErreur;

public class ExceptionPlanning extends Exception implements ConstantesErreur {
  private int chCodeErreur;

  public ExceptionPlanning(int codeErreur) {
    super();
    this.chCodeErreur = codeErreur;
  }

  public int getChCodeErreur() {
    return chCodeErreur;
  }

  public String toString() {
    return ConstantesErreur.ERREURS_PLANNING[chCodeErreur];
  }
}
