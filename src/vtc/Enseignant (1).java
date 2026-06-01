package vtc;

public class Enseignant extends Utilisateur {
    private String faculte;
    private int anneeRecrutement;

    public Enseignant(String nom, String prenom, String matricule, String faculte, int anneeRecrutement) {
        super(nom); // Utilise le constructeur de Utilisateur qui accepte seulement le nom
        this.prenom = prenom;
        this.matricule = matricule;
        this.faculte = faculte;
        this.anneeRecrutement = anneeRecrutement;
    }

    // Getters
    public String getFaculte() {
        return faculte;
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    // Setters
    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }
}
