package vtc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reputation {
    private List<Evaluation> evaluations;
    private static final double SEUIL_BANNISSEMENT = 2.0;
    private static final int NOMBRE_MIN_EVALUATIONS = 3;
    
    public Reputation() {
        this.evaluations = new ArrayList<>();
    }
  
    public void ajouterEvaluation(Evaluation eval) {
        Objects.requireNonNull(eval, "L'évaluation ne peut pas être null");
        evaluations.add(eval);
    }
    public double calculerMoyenne() {
        if (evaluations.isEmpty()) {
            return 5.0;
        }
        
        double somme = 0.0;
        for (Evaluation eval : evaluations) {
            somme += eval.getNote();
        }
        return somme / evaluations.size();
    }
   
    public boolean doitEtreBanni() {
        return calculerMoyenne() < SEUIL_BANNISSEMENT 
               && evaluations.size() >= NOMBRE_MIN_EVALUATIONS;
    }
    
  
    public List<Evaluation> getEvaluations() {
        return new ArrayList<>(evaluations);
    }
    
    @Override
    public String toString() {
        return String.format(
            "Statistiques Réputation:%n- Moyenne: %.1f%n- Nombre d'évaluations: %d%n- Statut: %s",
            calculerMoyenne(),
            evaluations.size(),
            doitEtreBanni() ? "A BANNIR" : "OK"
        );
    }
    
 
    public void reinitialiser() {
        evaluations.clear();
    }
}
