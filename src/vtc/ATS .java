package vtc;

public class ATS extends Utilisateur {
    private String service;
    private int anneeRecrutement;

    public ATS(String nom, String prenom, String matricule, String service, int anneeRecrutement) {
        super(nom); // Utilise le constructeur par défaut de Utilisateur qui accepte seulement `nom`
        this.prenom = prenom;
        this.matricule = matricule;
        this.service = service;
        this.anneeRecrutement = anneeRecrutement;
    }

    // Getters
    public String getService() {
        return service;
    }

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    // Setters
    public void setService(String service) {
        this.service = service;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }
}
