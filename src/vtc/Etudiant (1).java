package vtc;

public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;

    public Etudiant(String nom, String prenom, String matricule, int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }

    // Getters
    public int getAnneeAdmission() {
        return anneeAdmission;
    }

    public String getFaculte() {
        return faculte;
    }

    public String getSpecialite() {
        return specialite;
    }

    // Setters
    public void setAnneeAdmission(int anneeAdmission) {
        this.anneeAdmission = anneeAdmission;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
