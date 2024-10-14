package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final List<String> motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    public Reponse(String mot) {
        this.motSecret = List.of(mot.split(""));
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        List<String> essaiDecompose = List.of(essai.split(""));
        for(int i =0; i<essaiDecompose.size(); i++) {
            resultat.add(evaluationCaractere(essaiDecompose.get(i), i));
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return !(resultat.contains(Lettre.INCORRECTE)||resultat.contains(Lettre.NON_PLACEE));
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(String carCourant, int i) {
        if(estPlace(carCourant, i)){
            return Lettre.PLACEE;
        }
        if (estPresent(carCourant)){
            return Lettre.NON_PLACEE;
        }
        return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(String carCourant) {
        return motSecret.contains(carCourant);
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(String carCourant, int i) {
        return motSecret.get(i).equals(carCourant);
    }
}
