package vtc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Utilisateur {
    // Attributs
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected String email;
    protected String motDePasse;
    protected StatutUtilisateur statutUtilisateur;
    protected Itineraire itineraire;
    protected Preferences preferences;
    protected TypeDisponibilite disponibilite;
    protected TypeCourse typeCourse;
    protected List<HistoriqueCourse> historique;
    protected boolean estBanni;
    protected Reputation reputation;
    protected List<Evaluation> evaluationsRecues;
    protected List<Evaluation> evaluationsDonnees;
    protected Point positionActuelle;
    protected String telephone;
    protected String adresse;
    protected Genre genre;
    protected Point localisation;
    private Course courseActuelle;
    // Constructeur principal
    public Utilisateur(String nom) {
        this.nom = nom;
        this.email = "";
        this.telephone = "";
        this.adresse = "";
        this.genre = Genre.INDIFFERENT;
        this.localisation = null;
        this.preferences = new Preferences();
        this.reputation = new Reputation();
        this.evaluationsRecues = new ArrayList<>();
        this.evaluationsDonnees = new ArrayList<>();
        this.historique = new ArrayList<>();
    }

    public Utilisateur(String nom, String prenom, String matricule, Point localisation, Preferences preferences,
                       Reputation reputation, List<Evaluation> evaluationsRecues, List<Evaluation> evaluationsDonnees,
                       List<HistoriqueCourse> historique) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.localisation = localisation;
        this.preferences = preferences;
        this.reputation = reputation;
        this.evaluationsRecues = evaluationsRecues;
        this.evaluationsDonnees = evaluationsDonnees;
        this.historique = historique;
        this.genre = Genre.INDIFFERENT;
        this.email = "";
        this.telephone = "";
        this.adresse = "";
        this.estBanni = false;
    }
    public Utilisateur(String nom, String prenom, String matricule) {
        this(nom); 
        this.prenom = prenom;
        this.matricule = matricule;
    }


    public void bannir() {
        this.estBanni = true;
    }

    // Getters et Setters
    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }


    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "L'email ne peut pas être null");
    }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = Objects.requireNonNull(motDePasse, "Le mot de passe ne peut pas être null");
    }

    public float getReputation() {
        return (float) reputation.calculerMoyenne();
    }

    public void setReputation(Reputation reputation) {
        this.reputation = Objects.requireNonNull(reputation);
    }

    public StatutUtilisateur getStatutUtilisateur() { return statutUtilisateur; }
    public void setStatutUtilisateur(StatutUtilisateur statutUtilisateur) {
        this.statutUtilisateur = statutUtilisateur;
    }

    // Méthode d'accès simple demandée pour Statistique.java
    public StatutUtilisateur getStatut() {
        return getStatutUtilisateur();
    }

    public Preferences getPreferences() { return preferences; }
    public void setPreferences(Preferences preferences) {
        this.preferences = Objects.requireNonNull(preferences);
    }

    public TypeDisponibilite getDisponibilite() { return disponibilite; }
    public void setDisponibilite(TypeDisponibilite disponibilite) { this.disponibilite = disponibilite; }

    public TypeCourse getTypeCourse() { return typeCourse; }
    public void setTypeCourse(TypeCourse typeCourse) { this.typeCourse = typeCourse; }

    public boolean isEstBanni() { return estBanni; }
    public void setEstBanni(boolean estBanni) { this.estBanni = estBanni; }

    public Point getPositionActuelle() { return positionActuelle; }
    public void setPositionActuelle(Point positionActuelle) { this.positionActuelle = positionActuelle; }

    public List<Evaluation> getEvaluationsRecues() { return new ArrayList<>(evaluationsRecues); }
    public List<Evaluation> getEvaluationsDonnees() { return new ArrayList<>(evaluationsDonnees); }

    public void ajouterEvaluationRecue(Evaluation evaluation) {
        Objects.requireNonNull(evaluation);
        if (!evaluation.getCible().equals(this)) {
            throw new IllegalArgumentException("Cette évaluation ne concerne pas cet utilisateur");
        }
        evaluationsRecues.add(evaluation);
        reputation.ajouterEvaluation(evaluation);
        verifierBannissement();
    }

    public void ajouterEvaluationDonnee(Evaluation evaluation) {
        Objects.requireNonNull(evaluation);
        if (!evaluation.getAuteur().equals(this)) {
            throw new IllegalArgumentException("Cette évaluation n’a pas été donnée par cet utilisateur");
        }
        evaluationsDonnees.add(evaluation);
    }

    private void verifierBannissement() {
        if (reputation.doitEtreBanni()) {
            this.estBanni = true;
        }
    }

    public Evaluation evaluerUtilisateur(Utilisateur cible, float note, String commentaire) {
        if (this.equals(cible)) {
            throw new IllegalArgumentException("Un utilisateur ne peut pas s’évaluer lui-même");
        }
        Evaluation eval = new Evaluation(note, commentaire, this, cible);
        cible.ajouterEvaluationRecue(eval);
        this.ajouterEvaluationDonnee(eval);
        return eval;
    }

    public boolean estCompatibleAvec(Utilisateur autre) {
        return this.preferences.estCompatibleAvec(autre.getPreferences());
    }

    public void ajouterHistorique(HistoriqueCourse h) {
        this.historique.add(h);
    }

    public String afficherStatutReputation() {
        return String.format("%s %s (Matricule: %s)%n%s", 
                prenom, nom, matricule, reputation.toString());
    }


public Course getCourseActuelle() {
    return this.courseActuelle;
}
}
